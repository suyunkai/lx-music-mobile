package com.wanos.WanosCommunication.bean;

import com.wanos.commonlibrary.bean.MusicInfoBean;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class MediaMusicListBean {
    private List<MusicInfoBean> musicList;
    private int total;

    public List<MusicInfoBean> getMusicList() {
        return this.musicList;
    }

    public void setMusicList(List<MusicInfoBean> list) {
        this.musicList = list;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int i) {
        this.total = i;
    }
}
