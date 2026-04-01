package com.wanos.media.entity;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ThemeInfoEntity implements Serializable {
    private static final String TAG = "wanos[WanosVideoPage]";
    private List<AudioSceneInfoEntity> audioInfo;
    private String bgImg;
    private int bgType;
    private String durationRange;
    private long themeId;
    private String themeName;
    private String videoResource;

    public long getThemeId() {
        return this.themeId;
    }

    public String getBgImg() {
        return this.bgImg;
    }

    public String getThemeName() {
        return this.themeName;
    }

    public String getVideoResource() {
        return this.videoResource;
    }

    public int getBgType() {
        return this.bgType;
    }

    public String getDurationRange() {
        return this.durationRange;
    }

    public List<AudioSceneInfoEntity> getAudioInfo() {
        List<AudioSceneInfoEntity> list = this.audioInfo;
        return (list == null || list.isEmpty()) ? Collections.emptyList() : this.audioInfo;
    }
}
