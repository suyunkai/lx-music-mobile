import { memo, useCallback, useState } from 'react'
import { StyleSheet, View } from 'react-native'
import { useI18n } from '@/lang'
import { useTheme } from '@/store/theme/hook'
import Text from '@/components/common/Text'
import Slider from '@/components/common/Slider'
import SpeakerDiagram from './SpeakerDiagram'
import type { HardwareInfo } from '@/plugins/player/spatialAudio'
import { playTestTone, stopTestTone } from '@/plugins/player/spatialAudio'
import { useSettingValue } from '@/store/setting/hook'

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

  // 找到当前选中的布局信息
  const activeLayout = hwInfo?.supportedLayouts.find(l => l.name === currentLayout)
    ?? hwInfo?.supportedLayouts[hwInfo.supportedLayouts.length - 1]
    ?? null

  const handleChannelPress = useCallback((channelName: string) => {
    if (playingChannel === channelName) {
      void stopTestTone()
      setPlayingChannel(null)
    } else {
      setPlayingChannel(channelName)
      void playTestTone(channelName, frequency, volume).finally(() => {
        // 播放结束后清除状态
        setTimeout(() => setPlayingChannel(null), 1600)
      })
    }
  }, [playingChannel, frequency, volume])

  if (!hwInfo?.available || !activeLayout) return null

  return (
    <View style={[styles.container, { borderColor: theme['c-border'] }]}>
      <Text style={[styles.sectionTitle, { color: theme['c-font'] }]}>
        {t('multichannel_test')}
      </Text>
      <Text style={[styles.desc, { color: theme['c-font-label'] }]} size={12}>
        {t('multichannel_test_desc')}
      </Text>

      <SpeakerDiagram
        channelNames={activeLayout.channelNames}
        playingChannel={playingChannel}
        onChannelPress={handleChannelPress}
      />

      {/* 音量控制 */}
      <View style={styles.sliderRow}>
        <Text style={[styles.sliderLabel, { color: theme['c-font'] }]} size={13}>
          {t('multichannel_test_volume')}
        </Text>
        <View style={styles.sliderWrap}>
          <Slider
            value={volume}
            minimumValue={0}
            maximumValue={1}
            step={0.05}
            onValueChange={setVolume}
          />
        </View>
        <Text style={[styles.sliderValue, { color: theme['c-font-label'] }]} size={12}>
          {Math.round(volume * 100)}%
        </Text>
      </View>

      {/* 频率控制 */}
      <View style={styles.sliderRow}>
        <Text style={[styles.sliderLabel, { color: theme['c-font'] }]} size={13}>
          {t('multichannel_test_frequency')}
        </Text>
        <View style={styles.sliderWrap}>
          <Slider
            value={frequency}
            minimumValue={100}
            maximumValue={2000}
            step={10}
            onValueChange={setFrequency}
          />
        </View>
        <Text style={[styles.sliderValue, { color: theme['c-font-label'] }]} size={12}>
          {Math.round(frequency)}Hz
        </Text>
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
    marginBottom: 4,
  },
  desc: {
    marginBottom: 10,
    paddingLeft: 10,
  },
  sliderRow: {
    flexDirection: 'row',
    alignItems: 'center',
    paddingHorizontal: 10,
    marginTop: 8,
  },
  sliderLabel: {
    width: 45,
  },
  sliderWrap: {
    flex: 1,
    marginHorizontal: 8,
  },
  sliderValue: {
    width: 55,
    textAlign: 'right',
  },
})
