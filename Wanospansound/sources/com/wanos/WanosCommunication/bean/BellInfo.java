package com.wanos.WanosCommunication.bean;

/* JADX INFO: loaded from: classes3.dex */
public class BellInfo {
    private String coverImg;
    private int id;
    private boolean isDownloading;
    private boolean isVip;
    private String name;
    private String path;

    public BellInfo() {
    }

    public BellInfo(int i, String str, String str2, String str3, boolean z) {
        this.id = i;
        this.name = str;
        this.coverImg = str2;
        this.path = str3;
        this.isVip = z;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getCoverImg() {
        return this.coverImg;
    }

    public void setCoverImg(String str) {
        this.coverImg = str;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public boolean isVip() {
        return this.isVip;
    }

    public void setVip(boolean z) {
        this.isVip = z;
    }

    public boolean isDownloading() {
        return this.isDownloading;
    }

    public void setDownloading(boolean z) {
        this.isDownloading = z;
    }

    public String toString() {
        return "BellInfo{id=" + this.id + ", name='" + this.name + "', coverImg='" + this.coverImg + "', path='" + this.path + "', isVip=" + this.isVip + '}';
    }
}
