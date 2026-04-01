package com.flyme.auto.music.source.usage;

/* JADX INFO: loaded from: classes2.dex */
public class MusicSourceSdkManager {
    private MusicSourceUsageCallback usageCallback;

    public void setUsageCallback(MusicSourceUsageCallback musicSourceUsageCallback) {
        this.usageCallback = musicSourceUsageCallback;
    }

    public MusicSourceUsageCallback getUsageCallback() {
        return this.usageCallback;
    }

    private MusicSourceSdkManager() {
    }

    private static class SingleTon {
        private static final MusicSourceSdkManager instance = new MusicSourceSdkManager();

        private SingleTon() {
        }
    }

    public static MusicSourceSdkManager getInstance() {
        return SingleTon.instance;
    }
}
