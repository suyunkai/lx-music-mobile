package com.wanos.careditproject.data.response;

import com.wanos.WanosCommunication.BaseResponse;
import com.wanos.careditproject.data.bean.CloudInfoEntity;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class CloudResponse extends BaseResponse {
    public CloudListEntity data;

    public static class CloudListEntity {
        public List<CloudInfoEntity> list;
        private int total;

        public int getTotal() {
            return this.total;
        }

        public List<CloudInfoEntity> getList() {
            return this.list;
        }
    }
}
