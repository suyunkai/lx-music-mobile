package com.wanos.WanosCommunication.bean;

import com.wanos.WanosCommunication.BaseResponse;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class VideoSolarBean extends BaseResponse {
    public VideoSolarList data;

    public class VideoSolarList {
        public List<VideoInfoBean> list;

        public VideoSolarList() {
        }
    }
}
