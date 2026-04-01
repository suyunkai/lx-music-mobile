package cn.toside.music.mobile.spatialAudio;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.media3.common.util.UnstableApi;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;

/**
 * React Native 原生模块 - 多声道空间音频控制
 *
 * 提供给 JS 层的接口：
 * - enable/disable 多声道处理
 * - 配置目标声道布局
 * - 启用/禁用上混
 * - 查询当前状态
 */
@UnstableApi
public class SpatialAudioModule extends ReactContextBaseJavaModule {

    private static final String TAG = "SpatialAudioModule";
    private static final String MODULE_NAME = "SpatialAudioModule";

    /**
     * 全局单例 AudioProcessor 实例，需要在 ExoPlayer 创建时注入
     */
    private static MultichannelAudioProcessor audioProcessor;

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

    /**
     * 启用多声道处理
     */
    @ReactMethod
    public void setEnabled(boolean enabled, Promise promise) {
        try {
            getAudioProcessor().setEnabled(enabled);
            Log.d(TAG, "Multichannel processing " + (enabled ? "enabled" : "disabled"));
            promise.resolve(null);
        } catch (Exception e) {
            promise.reject("SPATIAL_AUDIO_ERROR", "Failed to set enabled: " + e.getMessage(), e);
        }
    }

    /**
     * 启用/禁用立体声上混
     */
    @ReactMethod
    public void setUpmixEnabled(boolean enabled, Promise promise) {
        try {
            getAudioProcessor().setUpmixEnabled(enabled);
            Log.d(TAG, "Upmix " + (enabled ? "enabled" : "disabled"));
            promise.resolve(null);
        } catch (Exception e) {
            promise.reject("SPATIAL_AUDIO_ERROR", "Failed to set upmix: " + e.getMessage(), e);
        }
    }

    /**
     * 设置目标声道布局
     * @param layoutName "2.0", "4.0", "5.1", "7.1"
     */
    @ReactMethod
    public void setChannelLayout(String layoutName, Promise promise) {
        try {
            ChannelLayout layout = ChannelLayout.fromName(layoutName);
            getAudioProcessor().setTargetLayout(layout);
            Log.d(TAG, "Channel layout set to " + layout.getDisplayName());
            promise.resolve(null);
        } catch (Exception e) {
            promise.reject("SPATIAL_AUDIO_ERROR", "Failed to set layout: " + e.getMessage(), e);
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
            map.putBoolean("enabled", proc.isEnabled());
            map.putBoolean("upmixEnabled", proc.isUpmixEnabled());
            map.putString("targetLayout", proc.getTargetLayout().getDisplayName());
            map.putInt("targetChannelCount", proc.getTargetLayout().getChannelCount());
            map.putString("processMode", proc.getProcessModeDescription());
            map.putInt("inputChannelCount", proc.getInputChannelCount());
            promise.resolve(map);
        } catch (Exception e) {
            promise.reject("SPATIAL_AUDIO_ERROR", "Failed to get status: " + e.getMessage(), e);
        }
    }

    /**
     * 初始化多声道音频处理
     * @param layoutName 目标声道布局名
     * @param upmixEnabled 是否启用上混
     */
    @ReactMethod
    public void initialize(String layoutName, boolean upmixEnabled, Promise promise) {
        try {
            MultichannelAudioProcessor proc = getAudioProcessor();
            ChannelLayout layout = ChannelLayout.fromName(layoutName);
            proc.setTargetLayout(layout);
            proc.setUpmixEnabled(upmixEnabled);
            proc.setEnabled(true);
            Log.d(TAG, "Initialized: layout=" + layout.getDisplayName() +
                    ", upmix=" + upmixEnabled);
            promise.resolve(null);
        } catch (Exception e) {
            promise.reject("SPATIAL_AUDIO_ERROR", "Failed to initialize: " + e.getMessage(), e);
        }
    }
}
