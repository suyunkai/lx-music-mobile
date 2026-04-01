package cn.toside.music.mobile.spatialAudio;

import android.media.AudioFormat;

import java.util.ArrayList;
import java.util.List;

/**
 * 多声道布局配置
 * 与 Wanos AudioLayout 对齐，wanosName 用于传给 initWanosRender()
 *
 * Android channel mask 常量参考：
 *   CHANNEL_OUT_FRONT_LEFT          = 0x4
 *   CHANNEL_OUT_FRONT_RIGHT         = 0x8
 *   CHANNEL_OUT_FRONT_CENTER        = 0x10
 *   CHANNEL_OUT_LOW_FREQUENCY       = 0x20
 *   CHANNEL_OUT_BACK_LEFT           = 0x40
 *   CHANNEL_OUT_BACK_RIGHT          = 0x80
 *   CHANNEL_OUT_SIDE_LEFT           = 0x200
 *   CHANNEL_OUT_SIDE_RIGHT          = 0x400
 *   CHANNEL_OUT_TOP_FRONT_LEFT      = 0x1000  (API 32+)
 *   CHANNEL_OUT_TOP_FRONT_RIGHT     = 0x2000  (API 32+)
 *   CHANNEL_OUT_TOP_BACK_LEFT       = 0x8000  (API 32+)
 *   CHANNEL_OUT_TOP_BACK_RIGHT      = 0x10000 (API 32+)
 */
public enum ChannelLayout {

    STEREO(2, AudioFormat.CHANNEL_OUT_STEREO, "2.0", "200",
        new String[]{"FL", "FR"}),

    QUAD(4,
        AudioFormat.CHANNEL_OUT_FRONT_LEFT |
        AudioFormat.CHANNEL_OUT_FRONT_RIGHT |
        AudioFormat.CHANNEL_OUT_BACK_LEFT |
        AudioFormat.CHANNEL_OUT_BACK_RIGHT,
        "4.0", "400",
        new String[]{"FL", "FR", "BL", "BR"}),

    SURROUND_5_1(6,
        AudioFormat.CHANNEL_OUT_FRONT_LEFT |
        AudioFormat.CHANNEL_OUT_FRONT_RIGHT |
        AudioFormat.CHANNEL_OUT_FRONT_CENTER |
        AudioFormat.CHANNEL_OUT_LOW_FREQUENCY |
        AudioFormat.CHANNEL_OUT_BACK_LEFT |
        AudioFormat.CHANNEL_OUT_BACK_RIGHT,
        "5.1", "510",
        new String[]{"FL", "FR", "FC", "LFE", "BL", "BR"}),

    // 5.1.2 = 5.1 + 2个天空声道 (Top Front Left/Right)
    SURROUND_5_1_2(8,
        AudioFormat.CHANNEL_OUT_FRONT_LEFT |
        AudioFormat.CHANNEL_OUT_FRONT_RIGHT |
        AudioFormat.CHANNEL_OUT_FRONT_CENTER |
        AudioFormat.CHANNEL_OUT_LOW_FREQUENCY |
        AudioFormat.CHANNEL_OUT_BACK_LEFT |
        AudioFormat.CHANNEL_OUT_BACK_RIGHT |
        0x1000 | 0x2000, // TOP_FRONT_LEFT | TOP_FRONT_RIGHT
        "5.1.2", "512",
        new String[]{"FL", "FR", "FC", "LFE", "BL", "BR", "TFL", "TFR"}),

    // 5.1.4 = 5.1 + 4个天空声道
    SURROUND_5_1_4(10,
        AudioFormat.CHANNEL_OUT_FRONT_LEFT |
        AudioFormat.CHANNEL_OUT_FRONT_RIGHT |
        AudioFormat.CHANNEL_OUT_FRONT_CENTER |
        AudioFormat.CHANNEL_OUT_LOW_FREQUENCY |
        AudioFormat.CHANNEL_OUT_BACK_LEFT |
        AudioFormat.CHANNEL_OUT_BACK_RIGHT |
        0x1000 | 0x2000 | 0x8000 | 0x10000, // TFL | TFR | TBL | TBR
        "5.1.4", "514",
        new String[]{"FL", "FR", "FC", "LFE", "BL", "BR", "TFL", "TFR", "TBL", "TBR"}),

    // 6.1 = 5.1 + Back Center
    SURROUND_6_1(7,
        AudioFormat.CHANNEL_OUT_FRONT_LEFT |
        AudioFormat.CHANNEL_OUT_FRONT_RIGHT |
        AudioFormat.CHANNEL_OUT_FRONT_CENTER |
        AudioFormat.CHANNEL_OUT_LOW_FREQUENCY |
        AudioFormat.CHANNEL_OUT_BACK_LEFT |
        AudioFormat.CHANNEL_OUT_BACK_RIGHT |
        0x100, // BACK_CENTER
        "6.1", "610",
        new String[]{"FL", "FR", "FC", "LFE", "BL", "BR", "BC"}),

    // 7.1 = 5.1 + Side Left/Right（注意 wanosName 是 "710"，不是 "512"）
    SURROUND_7_1(8,
        AudioFormat.CHANNEL_OUT_FRONT_LEFT |
        AudioFormat.CHANNEL_OUT_FRONT_RIGHT |
        AudioFormat.CHANNEL_OUT_FRONT_CENTER |
        AudioFormat.CHANNEL_OUT_LOW_FREQUENCY |
        AudioFormat.CHANNEL_OUT_BACK_LEFT |
        AudioFormat.CHANNEL_OUT_BACK_RIGHT |
        AudioFormat.CHANNEL_OUT_SIDE_LEFT |
        AudioFormat.CHANNEL_OUT_SIDE_RIGHT,
        "7.1", "710",
        new String[]{"FL", "FR", "FC", "LFE", "BL", "BR", "SL", "SR"}),

    // 7.1.2 = 7.1 + 2个天空声道
    SURROUND_7_1_2(10,
        AudioFormat.CHANNEL_OUT_FRONT_LEFT |
        AudioFormat.CHANNEL_OUT_FRONT_RIGHT |
        AudioFormat.CHANNEL_OUT_FRONT_CENTER |
        AudioFormat.CHANNEL_OUT_LOW_FREQUENCY |
        AudioFormat.CHANNEL_OUT_BACK_LEFT |
        AudioFormat.CHANNEL_OUT_BACK_RIGHT |
        AudioFormat.CHANNEL_OUT_SIDE_LEFT |
        AudioFormat.CHANNEL_OUT_SIDE_RIGHT |
        0x1000 | 0x2000, // TFL | TFR
        "7.1.2", "712",
        new String[]{"FL", "FR", "FC", "LFE", "BL", "BR", "SL", "SR", "TFL", "TFR"}),

    // 7.1.4 = 7.1 + 4个天空声道（Dolby Atmos 级）
    SURROUND_7_1_4(12,
        AudioFormat.CHANNEL_OUT_FRONT_LEFT |
        AudioFormat.CHANNEL_OUT_FRONT_RIGHT |
        AudioFormat.CHANNEL_OUT_FRONT_CENTER |
        AudioFormat.CHANNEL_OUT_LOW_FREQUENCY |
        AudioFormat.CHANNEL_OUT_BACK_LEFT |
        AudioFormat.CHANNEL_OUT_BACK_RIGHT |
        AudioFormat.CHANNEL_OUT_SIDE_LEFT |
        AudioFormat.CHANNEL_OUT_SIDE_RIGHT |
        0x1000 | 0x2000 | 0x8000 | 0x10000, // TFL | TFR | TBL | TBR
        "7.1.4", "714",
        new String[]{"FL", "FR", "FC", "LFE", "BL", "BR", "SL", "SR", "TFL", "TFR", "TBL", "TBR"}),

    // 9.1.4 = 7.1.4 + Front Wide Left/Right（Wanos 最高配置）
    SURROUND_9_1_4(14,
        AudioFormat.CHANNEL_OUT_FRONT_LEFT |
        AudioFormat.CHANNEL_OUT_FRONT_RIGHT |
        AudioFormat.CHANNEL_OUT_FRONT_CENTER |
        AudioFormat.CHANNEL_OUT_LOW_FREQUENCY |
        AudioFormat.CHANNEL_OUT_BACK_LEFT |
        AudioFormat.CHANNEL_OUT_BACK_RIGHT |
        AudioFormat.CHANNEL_OUT_SIDE_LEFT |
        AudioFormat.CHANNEL_OUT_SIDE_RIGHT |
        0x1000 | 0x2000 | 0x8000 | 0x10000 |
        0x4000000 | 0x8000000, // FRONT_WIDE_LEFT | FRONT_WIDE_RIGHT
        "9.1.4", "914",
        new String[]{"FL", "FR", "FC", "LFE", "BL", "BR", "SL", "SR", "TFL", "TFR", "TBL", "TBR", "FWL", "FWR"});

    private final int channelCount;
    private final int channelMask;
    private final String displayName;
    private final String wanosName; // Wanos initWanosRender 所需的布局名
    private final String[] channelNames; // 各声道缩写

    ChannelLayout(int channelCount, int channelMask, String displayName, String wanosName, String[] channelNames) {
        this.channelCount = channelCount;
        this.channelMask = channelMask;
        this.displayName = displayName;
        this.wanosName = wanosName;
        this.channelNames = channelNames;
    }

    public int getChannelCount() { return channelCount; }
    public int getChannelMask() { return channelMask; }
    public String getDisplayName() { return displayName; }
    public String getWanosName() { return wanosName; }
    public String[] getChannelNames() { return channelNames; }

    /**
     * 通过显示名查找布局
     */
    public static ChannelLayout fromName(String name) {
        if (name == null) return STEREO;
        for (ChannelLayout layout : values()) {
            if (layout.displayName.equals(name)) return layout;
        }
        return STEREO;
    }

    /**
     * 通过输入声道数查找最匹配的布局（优先匹配 bed-level 布局）
     */
    public static ChannelLayout fromInputChannels(int inputChannels) {
        // 优先精确匹配常见布局
        switch (inputChannels) {
            case 4: return QUAD;
            case 6: return SURROUND_5_1;
            case 7: return SURROUND_6_1;
            case 8: return SURROUND_7_1;
            case 10: return SURROUND_7_1_2;
            case 12: return SURROUND_7_1_4;
            case 14: return SURROUND_9_1_4;
            default: return STEREO;
        }
    }

    /**
     * 通过 Android channel mask 查找精确匹配的布局
     */
    public static ChannelLayout fromChannelMask(int mask) {
        for (ChannelLayout layout : values()) {
            if (layout.channelMask == mask) return layout;
        }
        // 未精确匹配，按声道数回退
        return fromInputChannels(Integer.bitCount(mask));
    }

    /**
     * 根据硬件支持的 channel masks，返回所有兼容的布局列表
     * @param supportedMasks 硬件支持的 Android channel mask 数组
     * @return 兼容布局列表（按声道数从小到大排序）
     */
    public static List<ChannelLayout> getCompatibleLayouts(int[] supportedMasks) {
        List<ChannelLayout> result = new ArrayList<>();
        int maxHwChannels = 0;

        // 计算硬件最大声道数
        for (int mask : supportedMasks) {
            int count = Integer.bitCount(mask);
            if (count > maxHwChannels) maxHwChannels = count;
        }

        // 添加所有声道数 ≤ 硬件最大声道数的布局
        for (ChannelLayout layout : values()) {
            if (layout == STEREO) continue; // 排除立体声
            if (layout.channelCount <= maxHwChannels) {
                result.add(layout);
            }
        }

        return result;
    }

    /**
     * 通过声道缩写名（如 "FL"）获取该声道在此布局中的索引
     * @return 声道索引，-1 表示不存在
     */
    public int getChannelIndex(String channelName) {
        for (int i = 0; i < channelNames.length; i++) {
            if (channelNames[i].equals(channelName)) return i;
        }
        return -1;
    }
}
