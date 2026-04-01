package com.wanos.bean;

import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;

/* JADX INFO: loaded from: classes3.dex */
public class ProgressInfo {
    long groupId;
    long mediaGroupType;
    long mediaId;
    MediaPlayerEnum.MediaType mediaType;
    long progress;

    public long getGroupId() {
        return this.groupId;
    }

    public void setGroupId(long j) {
        this.groupId = j;
    }

    public long getProgress() {
        return this.progress;
    }

    public void setProgress(long j) {
        this.progress = j;
    }

    public MediaPlayerEnum.MediaType getMediaType() {
        return this.mediaType;
    }

    public void setMediaType(MediaPlayerEnum.MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public long getMediaId() {
        return this.mediaId;
    }

    public void setMediaId(long j) {
        this.mediaId = j;
    }

    public long getMediaGroupType() {
        return this.mediaGroupType;
    }

    public void setMediaGroupType(long j) {
        this.mediaGroupType = j;
    }
}
