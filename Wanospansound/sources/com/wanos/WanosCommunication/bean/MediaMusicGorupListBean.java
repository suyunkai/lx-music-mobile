package com.wanos.WanosCommunication.bean;

import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class MediaMusicGorupListBean {
    private List<MusicGroupInfo> musicGroupList;
    private int total;

    public List<MusicGroupInfo> getMusicGroupList() {
        return this.musicGroupList;
    }

    public void setMusicGroupList(List<MusicGroupInfo> list) {
        this.musicGroupList = list;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int i) {
        this.total = i;
    }
}
