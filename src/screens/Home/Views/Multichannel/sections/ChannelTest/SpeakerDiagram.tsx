import { memo, useMemo } from 'react'
import { StyleSheet, View, TouchableOpacity, Animated } from 'react-native'
import { useTheme } from '@/store/theme/hook'
import { useI18n } from '@/lang'
import Text from '@/components/common/Text'

/**
 * 车舱俯视图喇叭位置示意
 * 每个声道用一个圆形按钮表示，点击可测试发声
 *
 * 布局坐标系（百分比）：
 *   x: 0=最左, 50=中间, 100=最右
 *   y: 0=最前(前挡风), 100=最后(后备箱)
 */

// 各声道在俯视图中的位置（百分比）
const CHANNEL_POSITIONS: Record<string, { x: number; y: number; isHeight?: boolean }> = {
  FL:  { x: 15, y: 10 },
  FC:  { x: 50, y: 5 },
  FR:  { x: 85, y: 10 },
  LFE: { x: 50, y: 28 },
  SL:  { x: 5,  y: 50 },
  SR:  { x: 95, y: 50 },
  BL:  { x: 15, y: 85 },
  BC:  { x: 50, y: 92 },
  BR:  { x: 85, y: 85 },
  TFL: { x: 25, y: 20, isHeight: true },
  TFR: { x: 75, y: 20, isHeight: true },
  TBL: { x: 25, y: 75, isHeight: true },
  TBR: { x: 75, y: 75, isHeight: true },
  FWL: { x: 5,  y: 15, isHeight: false },
  FWR: { x: 95, y: 15, isHeight: false },
}

interface Props {
  channelNames: string[]
  playingChannel: string | null
  onChannelPress: (channelName: string) => void
}

const SpeakerButton = memo(({
  name,
  position,
  isPlaying,
  onPress,
}: {
  name: string
  position: { x: number; y: number; isHeight?: boolean }
  isPlaying: boolean
  onPress: () => void
}) => {
  const theme = useTheme()
  const t = useI18n()
  const channelLabel = t(`channel_${name}`) || name
  const isHeight = position.isHeight

  return (
    <TouchableOpacity
      activeOpacity={0.7}
      onPress={onPress}
      style={[
        styles.speakerBtn,
        {
          left: `${position.x - 7}%`,
          top: `${position.y - 5}%`,
          backgroundColor: isPlaying
            ? theme['c-primary']
            : isHeight
              ? theme['c-primary-alpha-200']
              : theme['c-button-background'],
          borderColor: isHeight ? theme['c-primary'] : 'transparent',
          borderWidth: isHeight ? 1.5 : 0,
          borderStyle: isHeight ? 'dashed' : 'solid',
        },
      ]}
    >
      <Text
        style={{ color: isPlaying ? '#fff' : theme['c-font'] }}
        size={11}
        numberOfLines={1}
      >
        {name}
      </Text>
      <Text
        style={{ color: isPlaying ? '#ffffffcc' : theme['c-font-label'] }}
        size={8}
        numberOfLines={1}
      >
        {channelLabel}
      </Text>
    </TouchableOpacity>
  )
})

export default memo(({ channelNames, playingChannel, onChannelPress }: Props) => {
  const theme = useTheme()

  const buttons = useMemo(() => {
    return channelNames
      .filter(name => CHANNEL_POSITIONS[name])
      .map(name => ({
        name,
        position: CHANNEL_POSITIONS[name],
      }))
  }, [channelNames])

  return (
    <View style={[styles.diagram, { backgroundColor: theme['c-primary-alpha-100'] }]}>
      {/* 车舱轮廓提示 */}
      <View style={[styles.carOutline, { borderColor: theme['c-border'] }]}>
        <View style={[styles.seatRow, { borderBottomColor: theme['c-border'] }]}>
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
    aspectRatio: 0.75,
    borderRadius: 12,
    marginVertical: 10,
    position: 'relative',
    overflow: 'hidden',
  },
  carOutline: {
    position: 'absolute',
    top: '30%',
    left: '20%',
    width: '60%',
    height: '40%',
    borderWidth: 1,
    borderRadius: 8,
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
    width: 30,
    height: 24,
    borderRadius: 4,
  },
  speakerBtn: {
    position: 'absolute',
    width: '14%',
    paddingVertical: 6,
    borderRadius: 8,
    alignItems: 'center',
    justifyContent: 'center',
    elevation: 2,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 1 },
    shadowOpacity: 0.15,
    shadowRadius: 2,
  },
})
