import { memo, useMemo } from 'react'
import { StyleSheet, View, TouchableOpacity } from 'react-native'
import { useTheme } from '@/store/theme/hook'
import { useI18n } from '@/lang'
import Text from '@/components/common/Text'

/**
 * 车舱俯视图喇叭位置示意（紧凑版，适配横屏车机 160dpi）
 * 每个声道用小圆形按钮表示，点击测试发声
 */

// 各声道在俯视图中的位置（百分比坐标）
const CHANNEL_POSITIONS: Record<string, { x: number; y: number; isHeight?: boolean }> = {
  FL:  { x: 12, y: 8 },
  FC:  { x: 50, y: 2 },
  FR:  { x: 88, y: 8 },
  LFE: { x: 50, y: 26 },
  SL:  { x: 2,  y: 50 },
  SR:  { x: 98, y: 50 },
  BL:  { x: 12, y: 88 },
  BC:  { x: 50, y: 95 },
  BR:  { x: 88, y: 88 },
  TFL: { x: 28, y: 18, isHeight: true },
  TFR: { x: 72, y: 18, isHeight: true },
  TSL: { x: 14, y: 50, isHeight: true },
  TSR: { x: 86, y: 50, isHeight: true },
  TBL: { x: 28, y: 78, isHeight: true },
  TBR: { x: 72, y: 78, isHeight: true },
  FWL: { x: 2,  y: 18 },
  FWR: { x: 98, y: 18 },
}

interface Props {
  channelNames: string[]
  playingChannel: string | null
  onChannelPress: (channelName: string) => void
}

const SpeakerButton = memo(({
  name, position, isPlaying, onPress,
}: {
  name: string
  position: { x: number; y: number; isHeight?: boolean }
  isPlaying: boolean
  onPress: () => void
}) => {
  const theme = useTheme()
  const isHeight = position.isHeight

  return (
    <TouchableOpacity
      activeOpacity={0.6}
      onPress={onPress}
      style={[
        styles.speakerBtn,
        {
          left: `${position.x - 8}%`,
          top: `${position.y - 8}%`,
          backgroundColor: isPlaying
            ? theme['c-primary']
            : isHeight
              ? theme['c-primary-alpha-200']
              : theme['c-button-background'],
          borderColor: isPlaying
            ? theme['c-primary']
            : isHeight
              ? theme['c-primary']
              : theme['c-primary-alpha-500'],
          borderWidth: 1.5,
          borderStyle: isHeight ? 'dashed' : 'solid',
        },
      ]}
    >
      <Text
        style={{ color: isPlaying ? '#fff' : theme['c-primary'], fontWeight: '600' }}
        size={11}
        numberOfLines={1}
      >
        {name}
      </Text>
    </TouchableOpacity>
  )
})

export default memo(({ channelNames, playingChannel, onChannelPress }: Props) => {
  const theme = useTheme()

  const buttons = useMemo(() => {
    return channelNames
      .filter(name => CHANNEL_POSITIONS[name])
      .map(name => ({ name, position: CHANNEL_POSITIONS[name] }))
  }, [channelNames])

  return (
    <View style={[styles.diagram, { backgroundColor: theme['c-primary-alpha-100'], borderColor: theme['c-border'] }]}>
      {/* 车舱轮廓 */}
      <View style={[styles.carBody, { borderColor: theme['c-primary-alpha-200'] }]}>
        <View style={[styles.seatRow, { borderBottomColor: theme['c-primary-alpha-200'] }]}>
          <View style={[styles.seat, { backgroundColor: theme['c-primary-alpha-100'] }]} />
          <View style={[styles.seat, { backgroundColor: theme['c-primary-alpha-100'] }]} />
        </View>
        <View style={styles.seatRow}>
          <View style={[styles.seat, { backgroundColor: theme['c-primary-alpha-100'] }]} />
          <View style={[styles.seat, { backgroundColor: theme['c-primary-alpha-100'] }]} />
        </View>
      </View>

      {/* 喇叭按钮 */}
      {buttons.map(({ name, position }) => (
        <SpeakerButton
          key={name}
          name={name}
          position={position}
          isPlaying={playingChannel === name}
          onPress={() => onChannelPress(name)}
        />
      ))}
    </View>
  )
})

const styles = StyleSheet.create({
  diagram: {
    width: '100%',
    height: '100%',
    borderRadius: 10,
    borderWidth: 1,
    position: 'relative',
    overflow: 'hidden',
  },
  carBody: {
    position: 'absolute',
    top: '28%',
    left: '22%',
    width: '56%',
    height: '42%',
    borderWidth: 1,
    borderRadius: 6,
    overflow: 'hidden',
  },
  seatRow: {
    flex: 1,
    flexDirection: 'row',
    justifyContent: 'space-around',
    alignItems: 'center',
    borderBottomWidth: StyleSheet.hairlineWidth,
  },
  seat: {
    width: 20,
    height: 16,
    borderRadius: 3,
  },
  speakerBtn: {
    position: 'absolute',
    width: '16%',
    minWidth: 36,
    paddingVertical: 6,
    borderRadius: 8,
    alignItems: 'center',
    justifyContent: 'center',
    elevation: 2,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 1 },
    shadowOpacity: 0.2,
    shadowRadius: 2,
  },
})
