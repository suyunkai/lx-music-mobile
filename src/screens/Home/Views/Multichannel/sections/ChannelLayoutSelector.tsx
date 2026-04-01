import { memo, useCallback } from 'react'
import { StyleSheet, View } from 'react-native'
import CheckBox from '@/components/common/CheckBox'
import { useSettingValue } from '@/store/setting/hook'
import { updateSetting } from '@/core/common'
import { useI18n } from '@/lang'
import { useTheme } from '@/store/theme/hook'
import Text from '@/components/common/Text'
import { setChannelLayout, type ChannelLayoutName, type HardwareInfo } from '@/plugins/player/spatialAudio'

interface Props {
  hwInfo: HardwareInfo | null
}

export default memo(({ hwInfo }: Props) => {
  const t = useI18n()
  const theme = useTheme()
  const currentLayout = useSettingValue('player.multichannelLayout')

  const handleSelect = useCallback((name: string) => {
    updateSetting({ 'player.multichannelLayout': name })
    void setChannelLayout(name as ChannelLayoutName)
  }, [])

  if (!hwInfo?.available) return null

  return (
    <View style={[styles.container, { borderColor: theme['c-border'] }]}>
      <Text style={[styles.sectionTitle, { color: theme['c-font'] }]}>
        {t('multichannel_layout')}
      </Text>
      <View style={styles.list}>
        <CheckBox
          marginRight={8}
          check={currentLayout === 'auto'}
          label={t('multichannel_layout_auto')}
          onChange={() => handleSelect('auto')}
          need
        />
        {hwInfo.supportedLayouts.map(layout => (
          <CheckBox
            key={layout.name}
            marginRight={8}
            check={currentLayout === layout.name}
            label={`${layout.name} (${layout.channelCount}ch)`}
            onChange={() => handleSelect(layout.name)}
            need
          />
        ))}
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
  list: {
    flexDirection: 'row',
    flexWrap: 'wrap',
    paddingLeft: 10,
    gap: 4,
  },
})
