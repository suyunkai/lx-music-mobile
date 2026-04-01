package com.wanos.WanosCommunication.response;

import com.wanos.WanosCommunication.BaseResponse;
import com.wanos.WanosCommunication.bean.AudioInfoDataBean;

/* JADX INFO: loaded from: classes3.dex */
public class GetAudioInfoResponse extends BaseResponse {
    public AudioInfo data;

    public class AudioInfo {
        public AudioInfoDataBean music;

        public AudioInfo() {
        }
    }
}
