package com.wanos.WanosCommunication.bean;

/* JADX INFO: loaded from: classes3.dex */
public class BannerBean {
    String coverImg;
    long id;
    String path;

    public BannerBean() {
    }

    public BannerBean(long j, String str, String str2) {
        this.id = j;
        this.path = str;
        this.coverImg = str2;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public String getCoverImg() {
        return this.coverImg;
    }

    public void setCoverImg(String str) {
        this.coverImg = str;
    }
}
