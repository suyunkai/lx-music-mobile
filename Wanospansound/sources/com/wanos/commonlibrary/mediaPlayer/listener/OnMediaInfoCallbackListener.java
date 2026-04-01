package com.wanos.commonlibrary.mediaPlayer.listener;

import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;

/* JADX INFO: loaded from: classes3.dex */
public interface OnMediaInfoCallbackListener {
    void onMediaInfoCallbackNext(MediaPlayerEnum.MediaInfoCallbackType mediaInfoCallbackType, MediaInfo mediaInfo);
}
