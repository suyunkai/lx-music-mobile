package com.wanos.WanosCommunication.response;

import com.wanos.WanosCommunication.BaseResponse;
import com.wanos.WanosCommunication.bean.VideoInfoDataBean;

/* JADX INFO: loaded from: classes3.dex */
public class GetVideoInfoResponse extends BaseResponse {
    public VideoInfo data;

    public class VideoInfo {
        public VideoInfoDataBean video;

        public VideoInfo() {
        }
    }

    public VideoInfo getData() {
        return this.data;
    }

    public void setData(VideoInfo videoInfo) {
        this.data = videoInfo;
    }
}
