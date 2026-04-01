package com.wanos.WanosCommunication.bean;

import java.io.Serializable;

/* JADX INFO: loaded from: classes3.dex */
public class SpaceThemeBaseInfo implements Serializable {
    private String author;
    private String bgImg;
    private boolean canShare;
    private String coverImg;
    private String durationRange;
    private String id;
    private String sourceUserName;
    private int tempCoverPath;
    private long themeId;
    private String themeName;
    private long time;
    private int type;
    private String videoPath;

    public boolean isCanShare() {
        return this.canShare;
    }

    public void setCanShare(boolean z) {
        this.canShare = z;
    }

    public SpaceThemeBaseInfo() {
    }

    public SpaceThemeBaseInfo(String str, String str2, String str3, int i, String str4, long j) {
        this.id = str;
        this.coverImg = str2;
        this.themeName = str3;
        this.type = i;
        this.author = str4;
        this.time = j;
    }

    public SpaceThemeBaseInfo(String str, String str2, String str3, int i, String str4, long j, int i2, String str5) {
        this.id = str;
        this.coverImg = str2;
        this.themeName = str3;
        this.type = i;
        this.author = str4;
        this.time = j;
        this.videoPath = str5;
        this.tempCoverPath = i2;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getCoverPath() {
        return this.coverImg;
    }

    public void setCoverPath(String str) {
        this.coverImg = str;
    }

    public String getName() {
        return this.themeName;
    }

    public String getSourceUserName() {
        return this.sourceUserName;
    }

    public void setSourceUserName(String str) {
        this.sourceUserName = str;
    }

    public void setName(String str) {
        this.themeName = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String str) {
        this.author = str;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long j) {
        this.time = j;
    }

    public String getVideoPath() {
        return this.videoPath;
    }

    public void setVideoPath(String str) {
        this.videoPath = str;
    }

    public int getTempCoverPath() {
        return this.tempCoverPath;
    }

    public void setTempCoverPath(int i) {
        this.tempCoverPath = i;
    }

    public long getThemeId() {
        return this.themeId;
    }

    public void setThemeId(long j) {
        this.themeId = j;
    }

    public String getBgImg() {
        return this.bgImg;
    }

    public void setBgImg(String str) {
        this.bgImg = str;
    }

    public String getDurationRange() {
        return this.durationRange;
    }

    public void setDurationRange(String str) {
        this.durationRange = str;
    }
}
