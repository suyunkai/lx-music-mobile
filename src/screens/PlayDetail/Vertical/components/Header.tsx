import { memo, useEffect, useRef, useState } from 'react'

import { View, StyleSheet } from 'react-native'

import { pop } from '@/navigation'
import StatusBar from '@/components/common/StatusBar'
import { useTheme } from '@/store/theme/hook'
import { usePlayerMusicInfo } from '@/store/player/hook'
import { useSettingValue } from '@/store/setting/hook'
import Text from '@/components/common/Text'
import { getStatus } from '@/plugins/player/spatialAudio'

const chCountToName = (ch: number): string => {
  switch (ch) {
    case 1: return '单声道'
    case 2: return '双声道'
    case 4: return '4.0'
    case 6: return '5.1'
    case 7: return '6.1'
    case 8: return '7.1'
    case 10: return '7.1.2'
    case 12: return '7.1.4'
    case 14: return '9.1.4'
    default: return ch + '声道'
  }
}
import { scaleSizeH } from '@/utils/pixelRatio'
import { HEADER_HEIGHT as _HEADER_HEIGHT, NAV_SHEAR_NATIVE_IDS } from '@/config/constant'
import commonState from '@/store/common/state'
import SettingPopup, { type SettingPopupType } from '../../components/SettingPopup'
import { useStatusbarHeight } from '@/store/common/hook'
import Btn from './Btn'
import TimeoutExitBtn from './TimeoutExitBtn'

export const HEADER_HEIGHT = scaleSizeH(_HEADER_HEIGHT)


const ChannelBadge = memo(() => {
  const theme = useTheme()
  const isShow = useSettingValue('player.isShowChannelInfo')
  const musicInfo = usePlayerMusicInfo()
  const [channelText, setChannelText] = useState('')

  useEffect(() => {
    if (!isShow) { setChannelText(''); return }
    const timer = setTimeout(() => {
      void getStatus().then(s => {
        if (s.inputChannelCount > 0) {
          const src = chCountToName(s.inputChannelCount)
          const out = s.outputChannelCount > 0 ? chCountToName(s.outputChannelCount) : ''
          setChannelText(out && s.inputChannelCount !== s.outputChannelCount
            ? `${src} → ${out}` : src)
        } else {
          setChannelText('')
        }
      }).catch(() => setChannelText(''))
    }, 500) // 延迟一下等 AudioProcessor configure 完成
    return () => clearTimeout(timer)
  }, [isShow, musicInfo.id])

  if (!channelText) return null

  return (
    <View style={[styles.channelBadge, { backgroundColor: 'rgba(0,0,0,0.55)' }]}>
      <Text size={9} style={{ color: '#fff', fontWeight: '600' }}>{channelText}</Text>
    </View>
  )
})

const Title = () => {
  const theme = useTheme()
  const musicInfo = usePlayerMusicInfo()

  return (
    <View style={styles.titleContent}>
      <View style={styles.titleRow}>
        <Text numberOfLines={1} style={styles.title}>{musicInfo.name}</Text>
        <ChannelBadge />
      </View>
      <Text numberOfLines={1} style={styles.title} size={12} color={theme['c-font-label']}>{musicInfo.singer}</Text>
    </View>
  )
}

export default memo(() => {
  const popupRef = useRef<SettingPopupType>(null)
  const statusBarHeight = useStatusbarHeight()

  const back = () => {
    void pop(commonState.componentIds.playDetail!)
  }
  const showSetting = () => {
    popupRef.current?.show()
  }

  return (
    <View style={{ height: HEADER_HEIGHT + statusBarHeight, paddingTop: statusBarHeight }} nativeID={NAV_SHEAR_NATIVE_IDS.playDetail_header}>
      <StatusBar />
      <View style={styles.container}>
        <Btn icon="chevron-left" onPress={back} />
        <Title />
        <TimeoutExitBtn />
        <Btn icon="slider" onPress={showSetting} />
      </View>
      <SettingPopup ref={popupRef} direction="vertical" />
    </View>
  )
})


const styles = StyleSheet.create({
  container: {
    flexDirection: 'row',
    // justifyContent: 'center',
    height: '100%',
  },
  titleContent: {
    flex: 1,
    paddingHorizontal: 5,
    // alignItems: 'center',
    justifyContent: 'center',
  },
  title: {
    // flex: 1,
    // textAlign: 'center',
  },
  titleRow: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 6,
  },
  channelBadge: {
    paddingHorizontal: 5,
    paddingVertical: 1,
    borderRadius: 4,
  },
  icon: {
    paddingLeft: 4,
    paddingRight: 4,
  },
})
