import { memo, useEffect, useRef, useState } from 'react'

import { View, StyleSheet, TouchableOpacity } from 'react-native'

import { Icon } from '@/components/common/Icon'
import { pop } from '@/navigation'
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
import CommentBtn from './CommentBtn'
import Btn from './Btn'
import SettingPopup, { type SettingPopupType } from '../../components/SettingPopup'
import DesktopLyricBtn from './DesktopLyricBtn'

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
    }, 500)
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
        <Text numberOfLines={1} style={styles.title} size={14}>{musicInfo.name}</Text>
        <ChannelBadge />
      </View>
      <Text numberOfLines={1} style={styles.title} size={12} color={theme['c-font-label']}>{musicInfo.singer}</Text>
    </View>
  )
}

export default memo(() => {
  const popupRef = useRef<SettingPopupType>(null)

  const back = () => {
    void pop(commonState.componentIds.playDetail!)
  }
  const showSetting = () => {
    popupRef.current?.show()
  }

  return (
    <View style={{ height: HEADER_HEIGHT }} nativeID={NAV_SHEAR_NATIVE_IDS.playDetail_header}>
      <View style={styles.container}>
        <TouchableOpacity onPress={back} style={{ ...styles.button, width: HEADER_HEIGHT }}>
          <Icon name="chevron-left" size={18} />
        </TouchableOpacity>
        <Title />
        <DesktopLyricBtn />
        <CommentBtn />
        <Btn icon="slider" onPress={showSetting} />
      </View>
      <SettingPopup ref={popupRef} position="left" direction="horizontal" />
    </View>
  )
})


const styles = StyleSheet.create({
  container: {
    flex: 0,
    // backgroundColor: '#ccc',
    flexDirection: 'row',
    // justifyContent: 'center',
    height: '100%',
  },
  button: {
    justifyContent: 'center',
    alignItems: 'center',
    height: '100%',
    flex: 0,
  },
  titleContent: {
    flex: 1,
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
