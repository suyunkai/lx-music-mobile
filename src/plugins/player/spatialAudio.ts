import { NativeModules } from 'react-native'

const { SpatialAudioModule } = NativeModules

export type ChannelLayoutName = '2.0' | '4.0' | '5.1' | '7.1'

export interface SpatialAudioStatus {
  enabled: boolean
  upmixEnabled: boolean
  targetLayout: string
  targetChannelCount: number
  processMode: string
  inputChannelCount: number
}

/**
 * 启用/禁用多声道处理
 */
export const setEnabled = async(enabled: boolean): Promise<void> => {
  return SpatialAudioModule.setEnabled(enabled)
}

/**
 * 启用/禁用立体声上混
 */
export const setUpmixEnabled = async(enabled: boolean): Promise<void> => {
  return SpatialAudioModule.setUpmixEnabled(enabled)
}

/**
 * 设置目标声道布局
 */
export const setChannelLayout = async(layout: ChannelLayoutName): Promise<void> => {
  return SpatialAudioModule.setChannelLayout(layout)
}

/**
 * 获取当前状态
 */
export const getStatus = async(): Promise<SpatialAudioStatus> => {
  return SpatialAudioModule.getStatus()
}

/**
 * 初始化多声道音频处理
 */
export const initialize = async(layout: ChannelLayoutName, upmixEnabled: boolean): Promise<void> => {
  return SpatialAudioModule.initialize(layout, upmixEnabled)
}

/**
 * 根据设置初始化空间音频
 */
export const initFromSettings = async(settings: {
  isEnableMultichannel: boolean
  multichannelLayout: string
  isEnableUpmix: boolean
}): Promise<void> => {
  if (settings.isEnableMultichannel) {
    await initialize(
      settings.multichannelLayout as ChannelLayoutName,
      settings.isEnableUpmix,
    )
  } else {
    await setEnabled(false)
  }
}
