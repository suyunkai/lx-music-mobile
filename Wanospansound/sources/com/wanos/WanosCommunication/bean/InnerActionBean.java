package com.wanos.WanosCommunication.bean;

import com.wanos.WanosCommunication.BaseResponse;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class InnerActionBean extends BaseResponse {
    private List<AudioInfoBean> AudioList;
    private List<AudioInfoBean> MyList;
    private List<AudioInfoBean> TopList;
    private int audioTotal;
    private int myTotal;
    private int topTotal;

    public List<AudioInfoBean> getTopList() {
        return this.TopList;
    }

    public void setTopList(List<AudioInfoBean> list) {
        this.TopList = list;
    }

    public int getTopTotal() {
        return this.topTotal;
    }

    public void setTopTotal(int i) {
        this.topTotal = i;
    }

    public List<AudioInfoBean> getMyList() {
        return this.MyList;
    }

    public void setMyList(List<AudioInfoBean> list) {
        this.MyList = list;
    }

    public int getMyTotal() {
        return this.myTotal;
    }

    public void setMyTotal(int i) {
        this.myTotal = i;
    }

    public List<AudioInfoBean> getAudioList() {
        return this.AudioList;
    }

    public void setAudioList(List<AudioInfoBean> list) {
        this.AudioList = list;
    }

    public int getAudioTotal() {
        return this.audioTotal;
    }

    public void setAudioTotal(int i) {
        this.audioTotal = i;
    }
}
