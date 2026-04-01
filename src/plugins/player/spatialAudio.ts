import { NativeModules } from 'react-native'

const { SpatialAudioModule } = NativeModules

// ==================== 类型定义 ====================

/** 所有 Wanos 支持的声道布局名称 */
export type ChannelLayoutName =
  | '2.0' | '4.0' | '5.1' | '5.1.2' | '5.1.4'
  | '6.1' | '7.1' | '7.1.2' | '7.1.4' | '9.1.4'
  | 'auto'

/** 重映射模式 */
export type RemapMode = 'auto' | 'passthrough' | 'remap' | 'fill'

/** 声道布局详情 */
export interface LayoutInfo {
  name: string         // "5.1", "7.1" 等
  wanosName: string    // "510", "710" 等
  channelCount: number
  channelNames: string[] // ["FL", "FR", "FC", ...]
}

/** 硬件检测结果 */
export interface HardwareInfo {
  available: boolean
  busAddress: string
  maxChannelCount: number
  deviceId: number
  supportedLayouts: LayoutInfo[]
  supportedChannelMasks: number[]
}

/** 模块状态 */
export interface SpatialAudioStatus {
  enabled: boolean
  upmixEnabled: boolean
  targetLayout: string
  targetChannelCount: number
  processMode: string
  systemWanosAvailable: boolean
  systemWanosEnabled: boolean
  systemOutputChannels: number
  inputChannelCount: number
  outputChannelCount: number
  processModeDetail: string
}

// ==================== 基础控制 ====================

/** 启用/禁用多声道处理 */
export const setEnabled = async(enabled: boolean): Promise<void> => {
  return SpatialAudioModule.setEnabled(enabled)
}

/** 启用/禁用立体声上混 */
export const setUpmixEnabled = async(enabled: boolean): Promise<void> => {
  return SpatialAudioModule.setUpmixEnabled(enabled)
}

/** 设置目标声道布局 */
export const setChannelLayout = async(layout: ChannelLayoutName): Promise<void> => {
  return SpatialAudioModule.setChannelLayout(layout)
}

/** 获取当前状态 */
export const getStatus = async(): Promise<SpatialAudioStatus> => {
  return SpatialAudioModule.getStatus()
}

/** 初始化多声道音频处理 */
export const initialize = async(layout: ChannelLayoutName, upmixEnabled: boolean): Promise<void> => {
  return SpatialAudioModule.initialize(layout, upmixEnabled)
}

// ==================== 硬件检测 ====================

/** 检测多声道硬件能力 */
export const detectHardware = async(): Promise<HardwareInfo> => {
  return SpatialAudioModule.detectHardware()
}

// ==================== 声道测试 ====================

/** 播放声道测试音（单次，约1.5秒） */
export const playTestTone = async(
  channelName: string,
  frequency: number,
  volume: number,
): Promise<void> => {
  return SpatialAudioModule.playTestTone(channelName, frequency, volume)
}

/** 持续播放声道测试音（循环，直到调用 stopTestTone） */
export const playTestToneLoop = async(
  channelName: string,
  frequency: number,
  volume: number,
): Promise<void> => {
  return SpatialAudioModule.playTestToneLoop(channelName, frequency, volume)
}

/** 停止声道测试音 */
export const stopTestTone = async(): Promise<void> => {
  return SpatialAudioModule.stopTestTone()
}

// ==================== 重映射 ====================

/** 设置重映射模式 */
export const setRemapMode = async(mode: RemapMode): Promise<void> => {
  return SpatialAudioModule.setRemapMode(mode)
}

// ==================== 设置初始化 ====================

/** 根据设置初始化空间音频 */
export const initFromSettings = async(settings: {
  isEnableMultichannel: boolean
  multichannelLayout: string
  isEnableUpmix: boolean
  multichannelRemapMode?: string
}): Promise<void> => {
  if (settings.isEnableMultichannel) {
    await initialize(
      settings.multichannelLayout as ChannelLayoutName,
      settings.isEnableUpmix,
    )
    if (settings.multichannelRemapMode) {
      await setRemapMode(settings.multichannelRemapMode as RemapMode)
    }
  } else {
    await setEnabled(false)
  }
}
