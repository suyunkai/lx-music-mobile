package com.wanos.WanosCommunication.response;

import com.wanos.WanosCommunication.BaseResponse;
import com.wanos.WanosCommunication.bean.ZeroAudioEntity;

/* JADX INFO: loaded from: classes3.dex */
public class GetZeroAudioResponse extends BaseResponse {
    public ZeroAudioEntity data;

    public String toString() {
        return "GetZeroAudioResponse{data=" + this.data + ", code=" + this.code + ", msg='" + this.msg + "'}";
    }
}
