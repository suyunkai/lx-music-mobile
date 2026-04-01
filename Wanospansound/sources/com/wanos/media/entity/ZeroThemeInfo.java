package com.wanos.media.entity;

import java.io.Serializable;

/* JADX INFO: loaded from: classes3.dex */
public class ZeroThemeInfo implements Serializable {
    private boolean isVip;
    private String themeBgImage;
    private long themeId;
    private String themeName;

    public ZeroThemeInfo(long j, String str, String str2, boolean z) {
        this.themeId = j;
        this.themeName = str;
        this.themeBgImage = str2;
        this.isVip = z;
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

    public boolean isVip() {
        return this.isVip;
    }

    public String toString() {
        return "ZeroThemeInfo{themeId=" + this.themeId + ", themeName='" + this.themeName + "', themeBgImage='" + this.themeBgImage + "'}";
    }
}
