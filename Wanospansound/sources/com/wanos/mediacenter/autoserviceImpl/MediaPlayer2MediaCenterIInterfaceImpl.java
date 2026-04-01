package com.wanos.mediacenter.autoserviceImpl;

import com.wanos.commonlibrary.autoservices.MediaPlayer2MediaCenterIInterface;
import com.wanos.commonlibrary.bean.MediaInfoBean;
import com.wanos.mediacenter.WanosMusicPlaybackInfo;

/* JADX INFO: loaded from: classes3.dex */
public class MediaPlayer2MediaCenterIInterfaceImpl implements MediaPlayer2MediaCenterIInterface {
    @Override // com.wanos.commonlibrary.autoservices.MediaPlayer2MediaCenterIInterface
    public MediaInfoBean getMediaCenterMediaInfoBean() {
        return WanosMusicPlaybackInfo.getInstance().mMediaInfoBean;
    }
}
