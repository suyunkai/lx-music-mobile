import { memo, useCallback, useEffect, useRef, useState } from 'react'
import { StyleSheet, View, TouchableOpacity } from 'react-native'
import { useI18n } from '@/lang'
import { useTheme } from '@/store/theme/hook'
import Text from '@/components/common/Text'
import Slider from '@/components/common/Slider'
import SpeakerDiagram from './SpeakerDiagram'
import type { HardwareInfo } from '@/plugins/player/spatialAudio'
import { playTestTone, playTestToneLoop, stopTestTone } from '@/plugins/player/spatialAudio'
import { useSettingValue } from '@/store/setting/hook'

type PlayMode = 'single' | 'continuous' | 'repeat'

interface Props {
  hwInfo: HardwareInfo | null
}

export default memo(({ hwInfo }: Props) => {
  const t = useI18n()
  const theme = useTheme()
  const currentLayout = useSettingValue('player.multichannelLayout')

  const [volume, setVolume] = useState(0.7)
  const [frequency, setFrequency] = useState(440)
  const [playingChannel, setPlayingChannel] = useState<string | null>(null)
  const [playMode, setPlayMode] = useState<PlayMode>('continuous')
  const loopRef = useRef<ReturnType<typeof setInterval> | null>(null)
  const stoppedRef = useRef(false)

  // 自动布局时使用最大声道数的布局；否则使用选中的布局
  const activeLayout = currentLayout === 'auto'
    ? hwInfo?.supportedLayouts[hwInfo.supportedLayouts.length - 1] ?? null
    : hwInfo?.supportedLayouts.find(l => l.name === currentLayout)
      ?? hwInfo?.supportedLayouts[hwInfo.supportedLayouts.length - 1]
      ?? null

  // 清理循环播放
  const clearLoop = useCallback(() => {
    stoppedRef.current = true
    if (loopRef.current) {
      clearInterval(loopRef.current)
      loopRef.current = null
    }
    void stopTestTone()
  }, [])

  useEffect(() => {
    return () => { clearLoop() }
  }, [clearLoop])

  const handleChannelPress = useCallback((channelName: string) => {
    // 点击正在播放的声道 → 停止
    if (playingChannel === channelName) {
      clearLoop()
      setPlayingChannel(null)
      return
    }

    clearLoop()
    setPlayingChannel(channelName)
    stoppedRef.current = false

    if (playMode === 'single') {
      // 单次：播放一声后结束
      void playTestTone(channelName, frequency, volume).finally(() => {
        setTimeout(() => {
          if (!stoppedRef.current) setPlayingChannel(null)
        }, 1600)
      })
    } else if (playMode === 'continuous') {
      // 持续：Native 端循环流式播放，直到用户点击停止
      void playTestToneLoop(channelName, frequency, volume)
    } else {
      // 间断：响一声停一下，重复
      const playOnce = () => {
        if (stoppedRef.current) return
        void playTestTone(channelName, frequency, volume)
      }
      playOnce()
      loopRef.current = setInterval(playOnce, 2000)
    }
  }, [playingChannel, frequency, volume, playMode, clearLoop])

  if (!hwInfo?.available || !activeLayout) return null

  const modeButtons: { id: PlayMode; label: string }[] = [
    { id: 'single', label: '单次' },
    { id: 'continuous', label: '持续' },
    { id: 'repeat', label: '间断' },
  ]

  return (
    <View style={[styles.container, { borderColor: theme['c-border'] }]}>
      <Text style={[styles.sectionTitle, { color: theme['c-font'] }]}>
        {t('multichannel_test')}
      </Text>

      {/* 横向布局：左侧喇叭图 + 右侧控制面板 */}
      <View style={styles.horizontalRow}>
        {/* 喇叭俯视图 */}
        <View style={styles.diagramWrap}>
          <SpeakerDiagram
            channelNames={activeLayout.channelNames}
            playingChannel={playingChannel}
            onChannelPress={handleChannelPress}
          />
        </View>

        {/* 控制面板 */}
        <View style={styles.controlPanel}>
          {/* 播放模式选择 */}
          <View style={styles.modeRow}>
            {modeButtons.map(btn => (
              <TouchableOpacity
                key={btn.id}
                activeOpacity={0.7}
                onPress={() => { clearLoop(); setPlayingChannel(null); setPlayMode(btn.id) }}
                style={[
                  styles.modeBtn,
                  {
                    backgroundColor: playMode === btn.id
                      ? theme['c-primary']
                      : theme['c-button-background'],
                  },
                ]}
              >
                <Text
                  style={{ color: playMode === btn.id ? '#fff' : theme['c-font'] }}
                  size={11}
                >
                  {btn.label}
                </Text>
              </TouchableOpacity>
            ))}
          </View>

          {/* 音量 */}
          <View style={styles.sliderGroup}>
            <Text style={[styles.sliderLabel, { color: theme['c-font-label'] }]} size={11}>
              {t('multichannel_test_volume')}  {Math.round(volume * 100)}%
            </Text>
            <Slider
              value={volume}
              minimumValue={0}
              maximumValue={1}
              step={0.05}
              onValueChange={setVolume}
            />
          </View>

          {/* 频率 */}
          <View style={styles.sliderGroup}>
            <Text style={[styles.sliderLabel, { color: theme['c-font-label'] }]} size={11}>
              {t('multichannel_test_frequency')}  {Math.round(frequency)}Hz
            </Text>
            <Slider
              value={frequency}
              minimumValue={100}
              maximumValue={2000}
              step={10}
              onValueChange={setFrequency}
            />
          </View>

          {/* 当前布局提示 */}
          <Text style={[styles.layoutHint, { color: theme['c-font-label'] }]} size={10}>
            {activeLayout.name} ({activeLayout.channelCount}ch)
            {currentLayout === 'auto' ? ' [auto]' : ''}
          </Text>
        </View>
      </View>
    </View>
  )
})

const styles = StyleSheet.create({
  container: {
    paddingVertical: 10,
    borderBottomWidth: StyleSheet.hairlineWidth,
  },
  sectionTitle: {
    fontSize: 15,
    fontWeight: '600',
    marginBottom: 8,
  },
  horizontalRow: {
    flexDirection: 'row',
    gap: 15,
  },
  diagramWrap: {
    flex: 1,
    aspectRatio: 1.1,
    maxHeight: 280,
  },
  controlPanel: {
    flex: 1,
    justifyContent: 'center',
    gap: 10,
  },
  modeRow: {
    flexDirection: 'row',
    gap: 6,
    marginBottom: 4,
  },
  modeBtn: {
    paddingHorizontal: 12,
    paddingVertical: 5,
    borderRadius: 14,
  },
  sliderGroup: {
    gap: 2,
  },
  sliderLabel: {},
  layoutHint: {
    marginTop: 4,
    textAlign: 'right',
  },
})
