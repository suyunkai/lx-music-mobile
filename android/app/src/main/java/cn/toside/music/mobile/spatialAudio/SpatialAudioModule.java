package cn.toside.music.mobile.spatialAudio;

import android.content.Context;
import android.media.AudioManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.media3.common.util.UnstableApi;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.guichaguri.trackplayer.service.MusicManager;

/**
 * React Native 原生模块 - 多声道空间音频控制
 *
 * 双模式：
 * 1. 系统级 Wanos（魅族/Flyme 车机）— 通过 AudioManager.setParameters 激活系统内置 Wanos
 * 2. AudioProcessor 级 Wanos — 通过 ExoPlayer AudioProcessor 在应用层处理
 *
 * 系统级优先，因为它在 HAL 层处理，不受 offload 影响，效果更好。
 */
@UnstableApi
public class SpatialAudioModule extends ReactContextBaseJavaModule {

    private static final String TAG = "SpatialAudioModule";
    private static final String MODULE_NAME = "SpatialAudioModule";

    private static MultichannelAudioProcessor audioProcessor;
    private static boolean systemWanosAvailable = false;
    private static boolean systemWanosEnabled = false;

    public SpatialAudioModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @NonNull
    @Override
    public String getName() {
        return MODULE_NAME;
    }

    /**
     * 获取全局 AudioProcessor 实例（供 ExoPlayer 集成使用）
     */
    public static MultichannelAudioProcessor getAudioProcessor() {
        if (audioProcessor == null) {
            audioProcessor = new MultichannelAudioProcessor();
        }
        return audioProcessor;
    }

    // ==================== 系统级 Wanos 控制 ====================

    private AudioManager getAudioManager() {
        ReactApplicationContext ctx = getReactApplicationContext();
        return (AudioManager) ctx.getSystemService(Context.AUDIO_SERVICE);
    }

    /**
     * 检测系统是否支持 Wanos
     */
    private boolean checkSystemWanosSupport() {
        try {
            AudioManager am = getAudioManager();
            if (am == null) return false;
            // 查询系统 Wanos 集成状态
            String state = am.getParameters("wanos_integrated_state");
            Log.d(TAG, "System Wanos integrated state: " + state);
            // 查询系统支持的输出声道数
            String channels = am.getParameters("wanos_audio_out_put_channel");
            Log.d(TAG, "System Wanos output channels: " + channels);
            // 如果返回非空，说明系统支持 Wanos
            return state != null && !state.isEmpty();
        } catch (Exception e) {
            Log.d(TAG, "System Wanos not available: " + e.getMessage());
            return false;
        }
    }

    /**
     * 获取系统支持的输出声道数
     */
    private int getSystemOutputChannels() {
        try {
            AudioManager am = getAudioManager();
            if (am == null) return 0;
            String val = am.getParameters("wanos_audio_out_put_channel");
            if (val != null && !val.isEmpty()) {
                return Integer.parseInt(val.replaceAll("[^0-9]", ""));
            }
        } catch (Exception e) {
            Log.w(TAG, "Failed to get system output channels: " + e.getMessage());
        }
        return 0;
    }

    /**
     * 激活系统级 Wanos 上混
     * @param channelCount 输出声道数（如 6=5.1, 8=7.1, 10=默认）
     */
    private void activateSystemWanos(int channelCount) {
        try {
            AudioManager am = getAudioManager();
            if (am == null) return;
            // 激活上混
            am.setParameters(String.format("wanos_upmix=(%d)", channelCount));
            // 激活音效
            am.setParameters("wanos_effect=(1)");
            systemWanosEnabled = true;
            Log.i(TAG, "System Wanos ACTIVATED: channels=" + channelCount);
        } catch (Exception e) {
            Log.e(TAG, "Failed to activate system Wanos: " + e.getMessage());
        }
    }

    /**
     * 关闭系统级 Wanos 上混
     */
    private void deactivateSystemWanos() {
        try {
            AudioManager am = getAudioManager();
            if (am == null) return;
            am.setParameters("wanos_upmix=(-1)");
            am.setParameters("wanos_effect=(0)");
            systemWanosEnabled = false;
            Log.i(TAG, "System Wanos DEACTIVATED");
        } catch (Exception e) {
            Log.e(TAG, "Failed to deactivate system Wanos: " + e.getMessage());
        }
    }

    // ==================== React Native 接口 ====================

    /**
     * 启用多声道处理（自动选择系统级或 AudioProcessor 级）
     */
    @ReactMethod
    public void setEnabled(boolean enabled, Promise promise) {
        try {
            if (enabled) {
                systemWanosAvailable = checkSystemWanosSupport();
                if (systemWanosAvailable) {
                    int channels = getSystemOutputChannels();
                    if (channels <= 0) channels = 10; // 默认值（与原版 Wanos App 一致）
                    activateSystemWanos(channels);
                }
            } else {
                if (systemWanosEnabled) {
                    deactivateSystemWanos();
                }
            }
            // 同时控制 AudioProcessor（作为备用）
            getAudioProcessor().setEnabled(enabled && !systemWanosAvailable);
            // 设置多声道输出路由：将音频路由到支持多声道的总线（如 bus3）
            MusicManager.setMultichannelOutput(getReactApplicationContext(), enabled);
            Log.d(TAG, "Multichannel " + (enabled ? "enabled" : "disabled") +
                    " (system=" + systemWanosAvailable + ")");
            promise.resolve(null);
        } catch (Exception e) {
            promise.reject("SPATIAL_AUDIO_ERROR", e.getMessage(), e);
        }
    }

    /**
     * 启用/禁用立体声上混
     */
    @ReactMethod
    public void setUpmixEnabled(boolean enabled, Promise promise) {
        try {
            if (systemWanosAvailable) {
                if (enabled) {
                    int channels = getSystemOutputChannels();
                    if (channels <= 0) channels = 10;
                    activateSystemWanos(channels);
                } else {
                    deactivateSystemWanos();
                }
            }
            getAudioProcessor().setUpmixEnabled(enabled);
            promise.resolve(null);
        } catch (Exception e) {
            promise.reject("SPATIAL_AUDIO_ERROR", e.getMessage(), e);
        }
    }

    /**
     * 设置目标声道布局
     */
    @ReactMethod
    public void setChannelLayout(String layoutName, Promise promise) {
        try {
            ChannelLayout layout = ChannelLayout.fromName(layoutName);
            getAudioProcessor().setTargetLayout(layout);
            if (systemWanosAvailable && systemWanosEnabled) {
                activateSystemWanos(layout.getChannelCount());
            }
            Log.d(TAG, "Channel layout set to " + layout.getDisplayName());
            promise.resolve(null);
        } catch (Exception e) {
            promise.reject("SPATIAL_AUDIO_ERROR", e.getMessage(), e);
        }
    }

    /**
     * 获取当前状态
     */
    @ReactMethod
    public void getStatus(Promise promise) {
        try {
            MultichannelAudioProcessor proc = getAudioProcessor();
            WritableMap map = Arguments.createMap();
            map.putBoolean("enabled", proc.isEnabled() || systemWanosEnabled);
            map.putBoolean("upmixEnabled", proc.isUpmixEnabled());
            map.putString("targetLayout", proc.getTargetLayout().getDisplayName());
            map.putInt("targetChannelCount", proc.getTargetLayout().getChannelCount());
            map.putString("processMode", systemWanosEnabled ? "system_wanos" : proc.getProcessModeDescription());
            map.putBoolean("systemWanosAvailable", systemWanosAvailable);
            map.putBoolean("systemWanosEnabled", systemWanosEnabled);
            map.putInt("systemOutputChannels", getSystemOutputChannels());
            map.putInt("inputChannelCount", proc.getCurrentInputChannelCount());
            map.putInt("outputChannelCount", proc.getCurrentOutputChannelCount());
            map.putString("processModeDetail", proc.getProcessModeDetail());
            promise.resolve(map);
        } catch (Exception e) {
            promise.reject("SPATIAL_AUDIO_ERROR", e.getMessage(), e);
        }
    }

    /**
     * 初始化多声道音频处理
     */
    @ReactMethod
    public void initialize(String layoutName, boolean upmixEnabled, Promise promise) {
        try {
            // 1. 检测系统 Wanos
            systemWanosAvailable = checkSystemWanosSupport();
            ChannelLayout layout = ChannelLayout.fromName(layoutName);

            if (systemWanosAvailable) {
                // 系统级 Wanos：上混由 HAL 层处理
                int channels = getSystemOutputChannels();
                if (channels <= 0) channels = layout.getChannelCount();
                if (upmixEnabled) {
                    activateSystemWanos(channels);
                }
                // AudioProcessor 不做上混，但保留多声道直通保护
                // 防止 ExoPlayer 把 5.1/7.1 内容降混为立体声
                getAudioProcessor().setEnabled(false);
                Log.i(TAG, "Initialized with SYSTEM Wanos: channels=" + channels +
                        ", upmix=" + upmixEnabled +
                        ", multichannel passthrough protection=ON");
            } else {
                // 降级：使用 AudioProcessor
                MultichannelAudioProcessor proc = getAudioProcessor();
                proc.setTargetLayout(layout);
                proc.setUpmixEnabled(upmixEnabled);
                proc.setEnabled(true);
                Log.i(TAG, "Initialized with AudioProcessor: layout=" +
                        layout.getDisplayName() + ", upmix=" + upmixEnabled);
            }
            // 设置多声道输出路由：将音频路由到支持多声道的总线
            MusicManager.setMultichannelOutput(getReactApplicationContext(), true);
            promise.resolve(null);
        } catch (Exception e) {
            promise.reject("SPATIAL_AUDIO_ERROR", e.getMessage(), e);
        }
    }

    // ==================== 硬件检测 ====================

    /**
     * 检测多声道硬件能力
     * 返回：{ available, busAddress, supportedLayouts, maxChannelCount }
     */
    @ReactMethod
    public void detectHardware(Promise promise) {
        try {
            HardwareDetector.Result result = HardwareDetector.detect(getReactApplicationContext());
            WritableMap map = Arguments.createMap();
            map.putBoolean("available", result.available);
            map.putString("busAddress", result.busAddress);
            map.putInt("maxChannelCount", result.maxChannelCount);
            map.putInt("deviceId", result.deviceId);

            WritableArray layouts = Arguments.createArray();
            for (ChannelLayout layout : result.supportedLayouts) {
                WritableMap layoutMap = Arguments.createMap();
                layoutMap.putString("name", layout.getDisplayName());
                layoutMap.putString("wanosName", layout.getWanosName());
                layoutMap.putInt("channelCount", layout.getChannelCount());
                WritableArray names = Arguments.createArray();
                for (String ch : layout.getChannelNames()) {
                    names.pushString(ch);
                }
                layoutMap.putArray("channelNames", names);
                layouts.pushMap(layoutMap);
            }
            map.putArray("supportedLayouts", layouts);

            WritableArray masks = Arguments.createArray();
            for (int mask : result.supportedChannelMasks) {
                masks.pushInt(mask);
            }
            map.putArray("supportedChannelMasks", masks);

            promise.resolve(map);
        } catch (Exception e) {
            promise.reject("SPATIAL_AUDIO_ERROR", e.getMessage(), e);
        }
    }

    // ==================== 声道测试 ====================

    private TestToneGenerator testToneGenerator;

    private TestToneGenerator getTestToneGenerator() {
        if (testToneGenerator == null) {
            testToneGenerator = new TestToneGenerator();
        }
        return testToneGenerator;
    }

    /**
     * 播放声道测试音（单次，约1.5秒）
     */
    @ReactMethod
    public void playTestTone(String channelName, double frequency, double volume, Promise promise) {
        try {
            ChannelLayout layout = resolveTestLayout(channelName);
            if (layout == null) {
                promise.reject("INVALID_CHANNEL",
                        "Channel '" + channelName + "' not found");
                return;
            }
            int channelIndex = layout.getChannelIndex(channelName);
            getTestToneGenerator().play(
                    getReactApplicationContext(), layout,
                    channelIndex, (float) frequency, (float) volume);
            promise.resolve(null);
        } catch (Exception e) {
            promise.reject("SPATIAL_AUDIO_ERROR", e.getMessage(), e);
        }
    }

    /**
     * 持续播放声道测试音（循环，直到调用 stopTestTone）
     */
    @ReactMethod
    public void playTestToneLoop(String channelName, double frequency, double volume, Promise promise) {
        try {
            ChannelLayout layout = resolveTestLayout(channelName);
            if (layout == null) {
                promise.reject("INVALID_CHANNEL",
                        "Channel '" + channelName + "' not found");
                return;
            }
            int channelIndex = layout.getChannelIndex(channelName);
            getTestToneGenerator().playLoop(
                    getReactApplicationContext(), layout,
                    channelIndex, (float) frequency, (float) volume);
            promise.resolve(null);
        } catch (Exception e) {
            promise.reject("SPATIAL_AUDIO_ERROR", e.getMessage(), e);
        }
    }

    /** 根据声道名找到包含该声道的最大布局 */
    private ChannelLayout resolveTestLayout(String channelName) {
        // 优先用 targetLayout
        ChannelLayout layout = getAudioProcessor().getTargetLayout();
        if (layout.getChannelIndex(channelName) >= 0) return layout;
        // 回退：从所有布局中找包含该声道的最大的
        ChannelLayout[] all = ChannelLayout.values();
        for (int i = all.length - 1; i >= 0; i--) {
            if (all[i].getChannelIndex(channelName) >= 0) return all[i];
        }
        return null;
    }

    /**
     * 停止声道测试音
     */
    @ReactMethod
    public void stopTestTone(Promise promise) {
        try {
            getTestToneGenerator().stop();
            promise.resolve(null);
        } catch (Exception e) {
            promise.reject("SPATIAL_AUDIO_ERROR", e.getMessage(), e);
        }
    }

    // ==================== 重映射模式 ====================

    /**
     * 设置重映射模式
     * @param mode "auto" | "passthrough" | "remap" | "fill"
     */
    @ReactMethod
    public void setRemapMode(String mode, Promise promise) {
        try {
            getAudioProcessor().setRemapMode(mode);
            promise.resolve(null);
        } catch (Exception e) {
            promise.reject("SPATIAL_AUDIO_ERROR", e.getMessage(), e);
        }
    }
}
