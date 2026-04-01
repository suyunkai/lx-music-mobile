package com.wanos.media.juyihall.bean;

import com.wanos.WanosCommunication.BaseResponse;
import com.wanos.commonlibrary.bean.MusicInfoBean;

/* JADX INFO: loaded from: classes3.dex */
public class GetRecMediaDetailResponse extends BaseResponse {
    public RecMediaInfo data;

    public RecMediaInfo getMediaInfo() {
        RecMediaInfo recMediaInfo = this.data;
        if (recMediaInfo == null) {
            return null;
        }
        return recMediaInfo;
    }

    public MusicInfoBean getMusicInfoBean() {
        RecMediaInfo recMediaInfo = this.data;
        if (recMediaInfo == null) {
            return null;
        }
        return recMediaInfo.toMediaInfoBean();
    }
}
