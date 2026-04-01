package com.wanos.careditproject.service.AudioPlayerManager;

/* JADX INFO: loaded from: classes3.dex */
public class PlayProgress {
    private String currentTime;
    private int duration;
    private int progress;
    private String totalTime;

    public PlayProgress(int i, int i2, String str, String str2) {
        this.progress = i;
        this.duration = i2;
        this.currentTime = str;
        this.totalTime = str2;
    }

    public int getProgress() {
        return this.progress;
    }

    public int getDuration() {
        return this.duration;
    }

    public String getCurrentTime() {
        return this.currentTime;
    }

    public String getTotalTime() {
        return this.totalTime;
    }
}
