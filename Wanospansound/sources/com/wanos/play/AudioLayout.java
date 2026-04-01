package com.wanos.play;

/* JADX INFO: loaded from: classes3.dex */
public enum AudioLayout {
    WANOS_CH_100("100", 1, 4),
    WANOS_CH_200("200", 2, 12),
    WANOS_CH_400("400", 4, 204),
    WANOS_CH_510("510", 6, 252),
    WANOS_CH_512("512", 8, 3145980),
    WANOS_CH_514("514", 10, 737532),
    WANOS_CH_610("610", 7, 1276),
    WANOS_CH_710("710", 8, 6396),
    WANOS_CH_712("712", 10, 3152124),
    WANOS_CH_714("714", 12, 743676),
    WANOS_CH_914("914", 14, 202070268);

    private int audioFormat;
    private int channelNum;
    private String name;

    AudioLayout(String str, int i, int i2) {
        this.name = str;
        this.channelNum = i;
        this.audioFormat = i2;
    }

    public String getName() {
        return this.name;
    }

    public int getChannelNum() {
        return this.channelNum;
    }

    public int getAudioFormat() {
        return this.audioFormat;
    }

    @Override // java.lang.Enum
    public String toString() {
        return "name : " + this.name + ",channelNum : " + this.channelNum + ",audioFormat : " + this.audioFormat;
    }
}
