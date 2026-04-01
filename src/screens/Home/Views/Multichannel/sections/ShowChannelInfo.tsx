import { memo, useCallback } from 'react'
import { StyleSheet, View } from 'react-native'
import CheckBox from '@/components/common/CheckBox'
import { useSettingValue } from '@/store/setting/hook'
import { updateSetting } from '@/core/common'
import { useI18n } from '@/lang'
import { useTheme } from '@/store/theme/hook'
import Text from '@/components/common/Text'

export default memo(() => {
  const t = useI18n()
  const theme = useTheme()
  const isShow = useSettingValue('player.isShowChannelInfo')

  const handleToggle = useCallback((val: boolean) => {
    updateSetting({ 'player.isShowChannelInfo': val })
  }, [])

  return (
    <View style={[styles.container, { borderColor: theme['c-border'] }]}>
      <Text style={[styles.sectionTitle, { color: theme['c-font'] }]}>
        {t('multichannel_channel_info')}
      </Text>
      <View style={styles.content}>
        <CheckBox
          check={isShow}
          onChange={handleToggle}
          label={t('multichannel_channel_info_enable')}
        />
        <Text style={[styles.desc, { color: theme['c-font-label'] }]} size={12}>
          {t('multichannel_channel_info_desc')}
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
    marginBottom: 8,
  },
  content: {
    paddingLeft: 10,
  },
  desc: {
    marginTop: 5,
    paddingLeft: 18,
  },
})
