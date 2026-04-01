package cn.toside.music.mobile.spatialAudio;

import android.content.Context;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.os.Build;
import android.util.Log;

import java.util.List;

/**
 * 多声道硬件检测器
 * 检测车机音频总线中支持多声道输出的设备，解析其支持的声道配置
 */
public class HardwareDetector {

    private static final String TAG = "HardwareDetector";

    // 检测结果
    public static class Result {
        public final boolean available;
        public final String busAddress;
        public final int[] supportedChannelMasks;
        public final List<ChannelLayout> supportedLayouts;
        public final int maxChannelCount;
        public final int deviceId;

        public Result(boolean available, String busAddress, int[] supportedChannelMasks,
                      List<ChannelLayout> supportedLayouts, int maxChannelCount, int deviceId) {
            this.available = available;
            this.busAddress = busAddress;
            this.supportedChannelMasks = supportedChannelMasks;
            this.supportedLayouts = supportedLayouts;
            this.maxChannelCount = maxChannelCount;
            this.deviceId = deviceId;
        }

        /** 不可用时的默认结果 */
        public static Result unavailable() {
            return new Result(false, "", new int[0],
                    java.util.Collections.emptyList(), 2, -1);
        }
    }

    // 缓存检测结果
    private static Result cachedResult = null;

    /**
     * 检测多声道输出设备
     * @param context Android Context
     * @return 检测结果
     */
    public static Result detect(Context context) {
        if (cachedResult != null) return cachedResult;

        if (Build.VERSION.SDK_INT < 23) {
            Log.w(TAG, "API < 23, multichannel detection not supported");
            cachedResult = Result.unavailable();
            return cachedResult;
        }

        AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        if (am == null) {
            cachedResult = Result.unavailable();
            return cachedResult;
        }

        AudioDeviceInfo[] devices = am.getDevices(AudioManager.GET_DEVICES_OUTPUTS);
        AudioDeviceInfo bestDevice = null;
        int bestMaxChannels = 0;
        int[] bestMasks = new int[0];

        for (AudioDeviceInfo device : devices) {
            // 车机音频总线类型
            if (device.getType() != AudioDeviceInfo.TYPE_BUS) continue;

            // 检查 channel masks
            int[] masks = device.getChannelMasks();
            int maxCh = 0;
            boolean hasMultichannel = false;

            for (int mask : masks) {
                int count = Integer.bitCount(mask);
                if (count >= 6) {
                    hasMultichannel = true;
                    if (count > maxCh) maxCh = count;
                }
            }

            // 也检查 channel counts（部分设备不报 mask 但报 count）
            if (!hasMultichannel) {
                int[] counts = device.getChannelCounts();
                for (int count : counts) {
                    if (count >= 6) {
                        hasMultichannel = true;
                        if (count > maxCh) maxCh = count;
                    }
                }
            }

            if (hasMultichannel && maxCh > bestMaxChannels) {
                bestMaxChannels = maxCh;
                bestDevice = device;
                bestMasks = masks;
            }
        }

        if (bestDevice == null) {
            Log.w(TAG, "No multichannel bus device found");
            cachedResult = Result.unavailable();
            return cachedResult;
        }

        String address = Build.VERSION.SDK_INT >= 28
                ? bestDevice.getAddress() : "unknown_bus";

        List<ChannelLayout> layouts = ChannelLayout.getCompatibleLayouts(bestMasks);

        Log.i(TAG, "Detected multichannel device: " + address +
                ", maxChannels=" + bestMaxChannels +
                ", layouts=" + layoutListToString(layouts) +
                ", masks=" + masksToString(bestMasks));

        cachedResult = new Result(true, address, bestMasks, layouts, bestMaxChannels, bestDevice.getId());
        return cachedResult;
    }

    /**
     * 清除缓存（设备变化时调用）
     */
    public static void invalidateCache() {
        cachedResult = null;
    }

    /**
     * 获取缓存结果（不触发检测）
     */
    public static Result getCachedResult() {
        return cachedResult;
    }

    private static String layoutListToString(List<ChannelLayout> layouts) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < layouts.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(layouts.get(i).getDisplayName());
        }
        sb.append("]");
        return sb.toString();
    }

    private static String masksToString(int[] masks) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < masks.length; i++) {
            if (i > 0) sb.append(", ");
            sb.append(String.format("0x%04x(%dch)", masks[i], Integer.bitCount(masks[i])));
        }
        sb.append("]");
        return sb.toString();
    }
}
