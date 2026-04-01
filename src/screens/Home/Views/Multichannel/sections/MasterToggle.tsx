import { memo, useCallback } from 'react'
import { StyleSheet, View } from 'react-native'
import CheckBox from '@/components/common/CheckBox'
import { useSettingValue } from '@/store/setting/hook'
import { updateSetting } from '@/core/common'
import { useI18n } from '@/lang'
import { toast } from '@/utils/tools'
import { setEnabled } from '@/plugins/player/spatialAudio'
import { useTheme } from '@/store/theme/hook'
import Text from '@/components/common/Text'

export default memo(() => {
  const t = useI18n()
  const theme = useTheme()
  const isEnabled = useSettingValue('player.isEnableMultichannel')

  const handleToggle = useCallback((val: boolean) => {
    updateSetting({ 'player.isEnableMultichannel': val })
    if (val) {
      updateSetting({ 'player.isEnableAudioOffload': false })
    }
    void setEnabled(val)
    toast(t('multichannel_enable_tip'))
  }, [t])

  return (
    <View style={[styles.container, { borderColor: theme['c-border'] }]}>
      <CheckBox
        check={isEnabled}
        onChange={handleToggle}
        label={t('multichannel_enable')}
      />
      <Text style={[styles.desc, { color: theme['c-font-label'] }]} size={12}>
        {t('multichannel_enable_desc')}
      </Text>
    </View>
  )
})

const styles = StyleSheet.create({
  container: {
    paddingVertical: 10,
    borderBottomWidth: StyleSheet.hairlineWidth,
  },
  desc: {
    marginTop: 5,
    paddingLeft: 28,
  },
})
