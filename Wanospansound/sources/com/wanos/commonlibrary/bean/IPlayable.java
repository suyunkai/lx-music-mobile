package com.wanos.commonlibrary.bean;

import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;

/* JADX INFO: loaded from: classes3.dex */
public interface IPlayable {
    MediaPlayerEnum.CallBackState getPlayStatus();

    void setPlayStatus(MediaPlayerEnum.CallBackState callBackState);
}
