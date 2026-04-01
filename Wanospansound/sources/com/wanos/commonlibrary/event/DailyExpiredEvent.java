package com.wanos.commonlibrary.event;

import com.wanos.commonlibrary.bean.MusicInfoBean;

/* JADX INFO: loaded from: classes3.dex */
public class DailyExpiredEvent {
    public MusicInfoBean musicInfoBean;

    public DailyExpiredEvent(MusicInfoBean musicInfoBean) {
        this.musicInfoBean = musicInfoBean;
    }
}
