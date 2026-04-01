import { memo } from 'react'
import { StyleSheet, View } from 'react-native'
import { useI18n } from '@/lang'
import { useTheme } from '@/store/theme/hook'
import Text from '@/components/common/Text'
import type { HardwareInfo as HWInfo } from '@/plugins/player/spatialAudio'

interface Props {
  hwInfo: HWInfo | null
  onRefresh: () => void
}

export default memo(({ hwInfo }: Props) => {
  const t = useI18n()
  const theme = useTheme()

  if (!hwInfo) return null

  return (
    <View style={[styles.container, { borderColor: theme['c-border'] }]}>
      <Text style={[styles.sectionTitle, { color: theme['c-font'] }]}>
        {t('multichannel_hardware')}
      </Text>
      {hwInfo.available ? (
        <View style={styles.infoList}>
          <View style={styles.infoRow}>
            <Text style={[styles.label, { color: theme['c-font-label'] }]} size={13}>
              {t('multichannel_hardware_bus')}
            </Text>
            <Text style={[styles.value, { color: theme['c-font'] }]} size={13}>
              {hwInfo.busAddress}
            </Text>
          </View>
          <View style={styles.infoRow}>
            <Text style={[styles.label, { color: theme['c-font-label'] }]} size={13}>
              {t('multichannel_hardware_max_ch')}
            </Text>
            <Text style={[styles.value, { color: theme['c-font'] }]} size={13}>
              {hwInfo.maxChannelCount}
            </Text>
          </View>
          <View style={styles.layoutBadges}>
            {hwInfo.supportedLayouts.map(layout => (
              <View
                key={layout.name}
                style={[styles.badge, { backgroundColor: theme['c-primary-alpha-200'] }]}
              >
                <Text style={{ color: theme['c-primary'] }} size={12}>
                  {layout.name} ({layout.channelCount}ch)
                </Text>
              </View>
            ))}
          </View>
        </View>
      ) : (
        <Text style={[styles.notFound, { color: theme['c-font-label'] }]} size={13}>
          {t('multichannel_hardware_not_found')}
        </Text>
      )}
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
  infoList: {
    paddingLeft: 10,
  },
  infoRow: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    paddingVertical: 3,
  },
  label: {},
  value: {},
  layoutBadges: {
    flexDirection: 'row',
    flexWrap: 'wrap',
    marginTop: 8,
    gap: 6,
  },
  badge: {
    paddingHorizontal: 10,
    paddingVertical: 4,
    borderRadius: 12,
  },
  notFound: {
    paddingLeft: 10,
  },
})
