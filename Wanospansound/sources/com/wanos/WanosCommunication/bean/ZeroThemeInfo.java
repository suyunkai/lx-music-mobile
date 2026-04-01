package com.wanos.WanosCommunication.bean;

/* JADX INFO: loaded from: classes3.dex */
public class ZeroThemeInfo {
    private String themeBgImage;
    private long themeId;
    private String themeName;

    public ZeroThemeInfo(long j, String str, String str2) {
        this.themeId = j;
        this.themeName = str;
        this.themeBgImage = str2;
    }

    public long getThemeId() {
        return this.themeId;
    }

    public String getThemeName() {
        return this.themeName;
    }

    public String getThemeBgImage() {
        return this.themeBgImage;
    }

    public void setThemeBgImage(String str) {
        this.themeBgImage = str;
    }

    public String toString() {
        return "ZeroThemeInfo{themeId=" + this.themeId + ", themeName='" + this.themeName + "', themeBgImage='" + this.themeBgImage + "'}";
    }
}
