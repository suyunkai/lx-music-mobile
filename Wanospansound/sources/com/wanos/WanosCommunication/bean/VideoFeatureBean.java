package com.wanos.WanosCommunication.bean;

import com.wanos.WanosCommunication.BaseResponse;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class VideoFeatureBean extends BaseResponse {
    public VideoFeatureList data;

    public class VideoFeatureList {
        public List<VideoInfoBean> list;

        public VideoFeatureList() {
        }
    }
}
