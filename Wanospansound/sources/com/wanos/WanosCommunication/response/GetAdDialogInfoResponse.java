package com.wanos.WanosCommunication.response;

import com.wanos.WanosCommunication.BaseResponse;
import com.wanos.WanosCommunication.bean.AdDialogInfoBean;

/* JADX INFO: loaded from: classes3.dex */
public class GetAdDialogInfoResponse extends BaseResponse {
    public AdDialogInfoBean data;

    public AdDialogInfoBean getData() {
        return this.data;
    }

    public void setData(AdDialogInfoBean adDialogInfoBean) {
        this.data = adDialogInfoBean;
    }
}
