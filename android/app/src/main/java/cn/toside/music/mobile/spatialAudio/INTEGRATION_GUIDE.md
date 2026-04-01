# 多声道音频集成指南

## 概述

本模块使用 **Wanos 全景声 C++ 引擎**（libWanosPlayer.so）实现多声道音频输出：
1. **多声道直通** — 多声道音频（如 5.1 FLAC）直接输出
2. **立体声上混** — 普通2声道音频通过 Wanos 引擎上混为多声道全景声

## 架构

```
JS 层 (React Native)
├── spatialAudio.ts          ← JS 桥接模块
├── MultichannelAudio.tsx    ← 设置界面
└── player.ts                ← 初始化集成

Native 层 (Android/Java)
├── SpatialAudioModule.java          ← RN 原生模块
├── SpatialAudioPackage.java         ← 模块注册
├── MultichannelAudioProcessor.java  ← ExoPlayer AudioProcessor（调用 Wanos 引擎）
├── ChannelLayout.java               ← 声道布局定义（对齐 Wanos AudioLayout）
└── com/wanos/util/NativeMethod.java ← JNI 桥接（包名必须与 .so 一致）

Native Libraries (jniLibs/arm64-v8a/)
├── libWanosPlayer.so        ← Wanos 全景声引擎（含解码+空间渲染）
├── libstereoobjpos.so       ← 立体声对象空间定位
└── libc++_shared.so         ← C++ 标准库
```

## 音频处理流程

```
音频文件 (MP3/FLAC/WAV...)
    ↓
ExoPlayer 解码器 → PCM 16-bit / 48kHz
    ↓
MultichannelAudioProcessor
    ├── 多声道输入 (≥3ch) → 直通
    └── 立体声 + 上混开启:
        ↓ 转为 float 分离 L/R
        ↓ 每 1024 samples 为一块
        ↓
        Wanos playSpaceAudio()  ← C++ 全景声引擎
        (2个音频对象: L在(-0.5,1,0), R在(0.5,1,0))
        ↓
        float[outCh][1024] → 交错 PCM 16-bit
    ↓
DefaultAudioSink → AudioTrack (自动配置 channel mask)
    ↓
多声道扬声器 / DAC
```

## Wanos 引擎调用序列

```java
// 1. 初始化
NativeMethod.getInstance().initWanosRender(30, 0, 1024, 48000, 0, "510");

// 2. 每帧处理
NativeMethod.getInstance().playSpaceAudio(
    inputData,    // float[2][1024] - L/R 两个音频对象
    posX,         // float[]{-0.5, 0.5}
    posY,         // float[]{1.0, 1.0}
    posZ,         // float[]{0.0, 0.0}
    volume,       // float[]{1.0, 1.0}
    null,         // size (不需要)
    2,            // 2个对象
    objectIds,    // int[]{0, 1}
    outputData    // float[6][1024] (5.1 输出)
);

// 3. 释放
NativeMethod.getInstance().freeWanosRender();
```

## 自动补丁机制

react-native-track-player fork 需要注入 AudioProcessor。
`npm install` 后，`patches/apply-patches.js` 会自动修改 `MusicManager.java`，
将 `MultichannelAudioProcessor` 注入到 ExoPlayer 的 `DefaultAudioSink` 中。

## 注意事项

- .so 库仅提供 arm64-v8a 架构，仅在 64 位 ARM 设备上可用
- Wanos 引擎固定使用 48kHz 采样率和 1024 样本缓冲
- 启用多声道时会自动关闭 audio offload（offload 会绕过 AudioProcessor）
