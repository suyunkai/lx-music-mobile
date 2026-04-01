package com.wanos.careditproject.data.response;

import com.wanos.WanosCommunication.BaseResponse;

/* JADX INFO: loaded from: classes3.dex */
public class ProjectUploadAssetTaskResponse extends BaseResponse {
    public ProjectUploadAssetTaskBean data;

    public static class ProjectUploadAssetTaskBean {
        public int assetId;
        public int channel;
        public int fileSize;
        public String format;
        public String md5;
        public String name;
        public int sampleNum;
        public int sampleRate;
        public int taskStatus;
        public String timeLen;
        public String urlSrc;
    }
}
