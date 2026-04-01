package com.wanos.careditproject.data.response;

import com.wanos.WanosCommunication.BaseResponse;

/* JADX INFO: loaded from: classes3.dex */
public class ProjectUploadResponse extends BaseResponse {
    public ProjectUploadBean data;

    public static class ProjectUploadBean {
        public int assetId;
        public int taskId;
    }
}
