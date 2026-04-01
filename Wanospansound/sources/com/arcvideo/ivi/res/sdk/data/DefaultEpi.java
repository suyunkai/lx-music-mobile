package com.arcvideo.ivi.res.sdk.data;

/* JADX INFO: loaded from: classes.dex */
public final class DefaultEpi {
    private int contentType;
    private int is3D;
    private int len;
    private String name;
    private String publishTime;
    private String qipuId;

    public final int getContentType() {
        return this.contentType;
    }

    public final int getLen() {
        return this.len;
    }

    public final String getName() {
        return this.name;
    }

    public final String getPublishTime() {
        return this.publishTime;
    }

    public final String getQipuId() {
        return this.qipuId;
    }

    public final int is3D() {
        return this.is3D;
    }

    public final void set3D(int i) {
        this.is3D = i;
    }

    public final void setContentType(int i) {
        this.contentType = i;
    }

    public final void setLen(int i) {
        this.len = i;
    }

    public final void setName(String str) {
        this.name = str;
    }

    public final void setPublishTime(String str) {
        this.publishTime = str;
    }

    public final void setQipuId(String str) {
        this.qipuId = str;
    }
}
