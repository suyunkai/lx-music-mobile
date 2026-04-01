package com.wanos.careditproject.data.response;

import com.wanos.WanosCommunication.BaseResponse;
import com.wanos.WanosCommunication.bean.MaterialDataInfo;

/* JADX INFO: loaded from: classes3.dex */
public class CloudInfoResponse extends BaseResponse {
    public CloudInfoEntity data;

    public static class CloudInfoEntity {
        public MaterialDataInfo info;

        public MaterialDataInfo getInfo() {
            return this.info;
        }

        public void setInfo(MaterialDataInfo materialDataInfo) {
            this.info = materialDataInfo;
        }
    }
}
