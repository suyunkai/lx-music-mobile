package com.wanos.WanosCommunication.bean;

import com.wanos.commonlibrary.bean.MusicInfoBean;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class FreeGroupInfo {
    private int FreeGroupId;
    private String avatar;
    private long endTime;
    private String introduction;
    private List<MusicInfoBean> musicList;
    private String name;
    private long startTime;

    public int getFreeGroupId() {
        return this.FreeGroupId;
    }

    public void setFreeGroupId(int i) {
        this.FreeGroupId = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public String getIntroduction() {
        return this.introduction;
    }

    public void setIntroduction(String str) {
        this.introduction = str;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(long j) {
        this.startTime = j;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public void setEndTime(long j) {
        this.endTime = j;
    }

    public List<MusicInfoBean> getMusicList() {
        return this.musicList;
    }

    public void setMusicList(List<MusicInfoBean> list) {
        this.musicList = list;
    }
}
