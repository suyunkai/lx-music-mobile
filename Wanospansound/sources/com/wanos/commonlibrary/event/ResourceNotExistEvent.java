package com.wanos.commonlibrary.event;

/* JADX INFO: loaded from: classes3.dex */
public class ResourceNotExistEvent {
    private long mediaId;
    private int mediaType;

    public ResourceNotExistEvent(int i, long j) {
        this.mediaType = i;
        this.mediaId = j;
    }

    public int getMediaType() {
        return this.mediaType;
    }

    public void setMediaType(int i) {
        this.mediaType = i;
    }

    public long getMediaId() {
        return this.mediaId;
    }

    public void setMediaId(long j) {
        this.mediaId = j;
    }
}
