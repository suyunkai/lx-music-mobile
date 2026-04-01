package com.wanos.WanosCommunication.bean;

import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class AudioProjectBean {
    private List<AudioSourceBean> AudioSourceList;
    private AudioInfoBean Base;

    public AudioInfoBean getBase() {
        return this.Base;
    }

    public void setBase(AudioInfoBean audioInfoBean) {
        this.Base = audioInfoBean;
    }

    public List<AudioSourceBean> getAudioSourceList() {
        return this.AudioSourceList;
    }

    public void setAudioSourceList(List<AudioSourceBean> list) {
        this.AudioSourceList = list;
    }
}
