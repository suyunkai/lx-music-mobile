import { memo, useCallback, useMemo } from 'react'
import { StyleSheet, View } from 'react-native'

import SubTitle from '../../components/SubTitle'
import CheckBoxItem from '../../components/CheckBoxItem'
import CheckBox from '@/components/common/CheckBox'
import { useSettingValue } from '@/store/setting/hook'
import { updateSetting } from '@/core/common'
import { useI18n } from '@/lang'
import { toast } from '@/utils/tools'
import {
  setEnabled,
  setUpmixEnabled,
  setChannelLayout,
  type ChannelLayoutName,
} from '@/plugins/player/spatialAudio'

const CHANNEL_LAYOUTS: { id: ChannelLayoutName; name: string }[] = [
  { id: '4.0', name: '4.0 四声道' },
  { id: '5.1', name: '5.1 环绕声' },
  { id: '7.1', name: '7.1 环绕声' },
]

const useActiveLayout = (id: string) => {
  const layout = useSettingValue('player.multichannelLayout')
  return useMemo(() => layout === id, [layout, id])
}

const LayoutItem = ({ id, name }: { id: ChannelLayoutName; name: string }) => {
  const isActive = useActiveLayout(id)
  const handleChange = useCallback(() => {
    updateSetting({ 'player.multichannelLayout': id })
    void setChannelLayout(id)
  }, [id])
  return <CheckBox marginRight={8} check={isActive} label={name} onChange={handleChange} need />
}

export default memo(() => {
  const t = useI18n()
  const isEnableMultichannel = useSettingValue('player.isEnableMultichannel')
  const isEnableUpmix = useSettingValue('player.isEnableUpmix')

  const handleToggleMultichannel = useCallback((val: boolean) => {
    updateSetting({ 'player.isEnableMultichannel': val })
    // 启用多声道时必须关闭 audio offload，否则 AudioProcessor 会被绕过
    if (val) {
      updateSetting({ 'player.isEnableAudioOffload': false })
    }
    void setEnabled(val)
    toast(t('setting_play_multichannel_tip'))
  }, [t])

  const handleToggleUpmix = useCallback((val: boolean) => {
    updateSetting({ 'player.isEnableUpmix': val })
    void setUpmixEnabled(val)
  }, [])

  return (
    <SubTitle title={t('setting_play_multichannel')}>
      <View style={styles.content}>
        <CheckBoxItem
          check={isEnableMultichannel}
          onChange={handleToggleMultichannel}
          label={t('setting_play_multichannel_enable')}
          helpDesc={t('setting_play_multichannel_desc')}
        />
        {isEnableMultichannel ? (
          <>
            <View style={styles.layoutList}>
              {CHANNEL_LAYOUTS.map(l => (
                <LayoutItem key={l.id} id={l.id} name={l.name} />
              ))}
            </View>
            <CheckBoxItem
              check={isEnableUpmix}
              onChange={handleToggleUpmix}
              label={t('setting_play_upmix_enable')}
              helpDesc={t('setting_play_upmix_desc')}
            />
          </>
        ) : null}
      </View>
    </SubTitle>
  )
})

const styles = StyleSheet.create({
  content: {
    marginTop: 5,
  },
  layoutList: {
    flexDirection: 'row',
    flexWrap: 'wrap',
    marginTop: 5,
    marginBottom: 5,
    paddingLeft: 10,
  },
})
