import { memo, useCallback, useEffect, useState } from 'react'
import { ScrollView, StyleSheet, View, Text } from 'react-native'
import { useI18n } from '@/lang'
import { useTheme } from '@/store/theme/hook'
import MasterToggle from './sections/MasterToggle'
import HardwareInfo from './sections/HardwareInfo'
import ChannelLayoutSelector from './sections/ChannelLayoutSelector'
import StereoUpmix from './sections/StereoUpmix'
import RemappingConfig from './sections/RemappingConfig'
import ChannelTest from './sections/ChannelTest'
import { useSettingValue } from '@/store/setting/hook'
import { detectHardware, type HardwareInfo as HWInfo } from '@/plugins/player/spatialAudio'

export default memo(() => {
  const t = useI18n()
  const theme = useTheme()
  const isEnabled = useSettingValue('player.isEnableMultichannel')
  const [hwInfo, setHwInfo] = useState<HWInfo | null>(null)

  const refreshHardware = useCallback(() => {
    void detectHardware().then(setHwInfo).catch(() => setHwInfo(null))
  }, [])

  useEffect(() => {
    refreshHardware()
  }, [refreshHardware])

  return (
    <ScrollView style={styles.container}>
      <View style={[styles.header, { borderBottomColor: theme['c-border'] }]}>
        <Text style={[styles.title, { color: theme['c-font'] }]}>
          {t('multichannel_title')}
        </Text>
      </View>

      <MasterToggle />

      {isEnabled ? (
        <>
          <HardwareInfo hwInfo={hwInfo} onRefresh={refreshHardware} />
          <ChannelLayoutSelector hwInfo={hwInfo} />
          <StereoUpmix />
          <RemappingConfig />
          <ChannelTest hwInfo={hwInfo} />
        </>
      ) : null}
    </ScrollView>
  )
})

const styles = StyleSheet.create({
  container: {
    flex: 1,
    paddingHorizontal: 15,
  },
  header: {
    paddingVertical: 15,
    borderBottomWidth: StyleSheet.hairlineWidth,
    marginBottom: 10,
  },
  title: {
    fontSize: 20,
    fontWeight: 'bold',
  },
})
