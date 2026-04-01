package com.wanos.wanosplayermodule.data;

import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;

/* JADX INFO: loaded from: classes3.dex */
public abstract class ExtendOperaData {
    public abstract void requestMeidaInfoList(MediaPlayerEnum.MediaType mediaType, MediaInfoCallBack mediaInfoCallBack);

    public abstract void updateMeidaInfo(MediaInfo mediaInfo, MediaInfoCallBack mediaInfoCallBack);
}
