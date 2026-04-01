package com.wanos.WanosCommunication.bean;

/* JADX INFO: loaded from: classes3.dex */
public class VideoInfoDataBean {
    String cover;
    String description;
    int timeLen;
    String title;
    String url;
    int videoId;

    public int getVideoId() {
        return this.videoId;
    }

    public void setVideoId(int i) {
        this.videoId = i;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getCover() {
        return this.cover;
    }

    public void setCover(String str) {
        this.cover = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public int getTimeLen() {
        return this.timeLen;
    }

    public void setTimeLen(int i) {
        this.timeLen = i;
    }
}
