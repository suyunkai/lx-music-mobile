import { memo, useCallback } from 'react'
import { StyleSheet, View } from 'react-native'
import CheckBox from '@/components/common/CheckBox'
import { useSettingValue } from '@/store/setting/hook'
import { updateSetting } from '@/core/common'
import { useI18n } from '@/lang'
import { useTheme } from '@/store/theme/hook'
import Text from '@/components/common/Text'
import { setRemapMode, type RemapMode } from '@/plugins/player/spatialAudio'

const REMAP_OPTIONS: { id: RemapMode; labelKey: string }[] = [
  { id: 'auto', labelKey: 'multichannel_remap_auto' },
  { id: 'passthrough', labelKey: 'multichannel_remap_passthrough' },
  { id: 'remap', labelKey: 'multichannel_remap_remap' },
  { id: 'fill', labelKey: 'multichannel_remap_fill' },
]

export default memo(() => {
  const t = useI18n()
  const theme = useTheme()
  const currentMode = useSettingValue('player.multichannelRemapMode')

  const handleSelect = useCallback((mode: RemapMode) => {
    updateSetting({ 'player.multichannelRemapMode': mode })
    void setRemapMode(mode)
  }, [])

  return (
    <View style={[styles.container, { borderColor: theme['c-border'] }]}>
      <Text style={[styles.sectionTitle, { color: theme['c-font'] }]}>
        {t('multichannel_remap')}
      </Text>
      <Text style={[styles.desc, { color: theme['c-font-label'] }]} size={12}>
        {t('multichannel_remap_desc')}
      </Text>
      <View style={styles.list}>
        {REMAP_OPTIONS.map(opt => (
          <CheckBox
            key={opt.id}
            marginRight={8}
            check={currentMode === opt.id}
            label={t(opt.labelKey)}
            onChange={() => handleSelect(opt.id)}
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
    marginBottom: 4,
  },
  desc: {
    marginBottom: 8,
    paddingLeft: 10,
  },
  list: {
    flexDirection: 'row',
    flexWrap: 'wrap',
    paddingLeft: 10,
    gap: 4,
  },
})
