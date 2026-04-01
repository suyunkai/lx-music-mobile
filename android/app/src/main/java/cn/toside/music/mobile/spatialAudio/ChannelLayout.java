package cn.toside.music.mobile.spatialAudio;

import android.media.AudioFormat;

/**
 * 多声道布局配置
 * 与 Wanos AudioLayout 对齐，wanosName 用于传给 initWanosRender()
 */
public enum ChannelLayout {
    STEREO(2, AudioFormat.CHANNEL_OUT_STEREO, "2.0", "200"),

    QUAD(4,
        AudioFormat.CHANNEL_OUT_FRONT_LEFT |
        AudioFormat.CHANNEL_OUT_FRONT_RIGHT |
        AudioFormat.CHANNEL_OUT_BACK_LEFT |
        AudioFormat.CHANNEL_OUT_BACK_RIGHT,
        "4.0", "400"),

    SURROUND_5_1(6,
        AudioFormat.CHANNEL_OUT_FRONT_LEFT |
        AudioFormat.CHANNEL_OUT_FRONT_RIGHT |
        AudioFormat.CHANNEL_OUT_FRONT_CENTER |
        AudioFormat.CHANNEL_OUT_LOW_FREQUENCY |
        AudioFormat.CHANNEL_OUT_BACK_LEFT |
        AudioFormat.CHANNEL_OUT_BACK_RIGHT,
        "5.1", "510"),

    SURROUND_7_1(8,
        AudioFormat.CHANNEL_OUT_FRONT_LEFT |
        AudioFormat.CHANNEL_OUT_FRONT_RIGHT |
        AudioFormat.CHANNEL_OUT_FRONT_CENTER |
        AudioFormat.CHANNEL_OUT_LOW_FREQUENCY |
        AudioFormat.CHANNEL_OUT_BACK_LEFT |
        AudioFormat.CHANNEL_OUT_BACK_RIGHT |
        AudioFormat.CHANNEL_OUT_SIDE_LEFT |
        AudioFormat.CHANNEL_OUT_SIDE_RIGHT,
        "7.1", "512");

    private final int channelCount;
    private final int channelMask;
    private final String displayName;
    private final String wanosName; // Wanos initWanosRender 所需的布局名

    ChannelLayout(int channelCount, int channelMask, String displayName, String wanosName) {
        this.channelCount = channelCount;
        this.channelMask = channelMask;
        this.displayName = displayName;
        this.wanosName = wanosName;
    }

    public int getChannelCount() { return channelCount; }
    public int getChannelMask() { return channelMask; }
    public String getDisplayName() { return displayName; }
    public String getWanosName() { return wanosName; }

    public static ChannelLayout fromName(String name) {
        if (name == null) return STEREO;
        switch (name) {
            case "4.0": return QUAD;
            case "5.1": return SURROUND_5_1;
            case "7.1": return SURROUND_7_1;
            default: return STEREO;
        }
    }

    public static ChannelLayout fromInputChannels(int inputChannels) {
        switch (inputChannels) {
            case 4: return QUAD;
            case 6: return SURROUND_5_1;
            case 8: return SURROUND_7_1;
            default: return STEREO;
        }
    }
}
