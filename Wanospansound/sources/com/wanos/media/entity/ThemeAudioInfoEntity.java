package com.wanos.media.entity;

import java.io.Serializable;

/* JADX INFO: loaded from: classes3.dex */
public class ThemeAudioInfoEntity implements Serializable {
    private static final String TAG = "wanos[WanosVideoPage]";
    private String imagePath;
    private String imagePathV2;
    private int isVip;
    private int isVocal;
    private int modify;
    private ThemeSoundInfoEntity soundDetail;
    private long soundId;
    private String soundName;
    private String tagName;
    private String wanosPath;

    public long getSoundId() {
        return this.soundId;
    }

    public String getSoundName() {
        return this.soundName;
    }

    public String getWanosPath() {
        return this.wanosPath;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public String getImagePathV2() {
        return this.imagePathV2;
    }

    public String getTagName() {
        return this.tagName;
    }

    public int getModify() {
        return this.modify;
    }

    public void setTagName(String str) {
        this.tagName = str;
    }

    public void setSoundId(long j) {
        this.soundId = j;
    }

    public void setSoundName(String str) {
        this.soundName = str;
    }

    public void setWanosPath(String str) {
        this.wanosPath = str;
    }

    public void setImagePath(String str) {
        this.imagePath = str;
    }

    public void setIsVip(int i) {
        this.isVip = i;
    }

    public boolean isVoice() {
        return this.isVocal == 1;
    }

    public ThemeSoundInfoEntity getSoundInfo() {
        return this.soundDetail;
    }

    public int getIsVip() {
        return this.isVip;
    }

    public String toString() {
        return "ThemeAudioInfoEntity{soundId=" + this.soundId + ", soundName='" + this.soundName + "', wanosPath='" + this.wanosPath + "', imagePath='" + this.imagePath + "', isVip=" + this.isVip + ", tagName='" + this.tagName + "', themeSoundInfoEntity=" + getSoundInfo() + '}';
    }
}
