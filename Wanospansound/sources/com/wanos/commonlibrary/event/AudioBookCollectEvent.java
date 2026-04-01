package com.wanos.commonlibrary.event;

/* JADX INFO: loaded from: classes3.dex */
public class AudioBookCollectEvent {
    private long audioBookId;
    private boolean isCollected;
    private boolean isFromBar;

    public boolean isCollected() {
        return this.isCollected;
    }

    public void setCollected(boolean z) {
        this.isCollected = z;
    }

    public AudioBookCollectEvent() {
        this.isFromBar = false;
        this.audioBookId = -1L;
        this.isCollected = false;
    }

    public AudioBookCollectEvent(boolean z) {
        this.audioBookId = -1L;
        this.isCollected = false;
        this.isFromBar = z;
    }

    public AudioBookCollectEvent(long j) {
        this.isFromBar = false;
        this.isCollected = false;
        this.audioBookId = j;
    }

    public boolean isFromBar() {
        return this.isFromBar;
    }

    public void setFromBar(boolean z) {
        this.isFromBar = z;
    }

    public long getAudioBookId() {
        return this.audioBookId;
    }

    public void setAudioBookId(long j) {
        this.audioBookId = j;
    }
}
