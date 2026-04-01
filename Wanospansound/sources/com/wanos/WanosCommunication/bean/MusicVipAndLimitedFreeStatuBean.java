package com.wanos.WanosCommunication.bean;

/* JADX INFO: loaded from: classes3.dex */
public class MusicVipAndLimitedFreeStatuBean {
    private long freeEndTime;
    private long freeStartTime;
    private long id;
    private boolean isFree;
    private boolean isVip;
    private long previewEndTime;
    private long previewStartTime;

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public boolean isFree() {
        return this.isFree;
    }

    public void setFree(boolean z) {
        this.isFree = z;
    }

    public long getFreeStartTime() {
        return this.freeStartTime;
    }

    public void setFreeStartTime(long j) {
        this.freeStartTime = j;
    }

    public long getFreeEndTime() {
        return this.freeEndTime;
    }

    public void setFreeEndTime(long j) {
        this.freeEndTime = j;
    }

    public boolean isVip() {
        return this.isVip;
    }

    public void setVip(boolean z) {
        this.isVip = z;
    }

    public long getPreviewStartTime() {
        return this.previewStartTime;
    }

    public void setPreviewStartTime(long j) {
        this.previewStartTime = j;
    }

    public long getPreviewEndTime() {
        return this.previewEndTime;
    }

    public void setPreviewEndTime(long j) {
        this.previewEndTime = j;
    }
}
