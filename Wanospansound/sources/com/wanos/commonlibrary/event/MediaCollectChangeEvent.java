package com.wanos.commonlibrary.event;

/* JADX INFO: loaded from: classes3.dex */
public class MediaCollectChangeEvent {
    private long id;
    private boolean isCollect;
    private boolean isMusic;

    public MediaCollectChangeEvent(boolean z, long j, boolean z2) {
        this.isMusic = z;
        this.id = j;
        this.isCollect = z2;
    }

    public boolean isMusic() {
        return this.isMusic;
    }

    public void setMusic(boolean z) {
        this.isMusic = z;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public boolean isCollect() {
        return this.isCollect;
    }

    public void setCollect(boolean z) {
        this.isCollect = z;
    }
}
