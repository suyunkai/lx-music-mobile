package com.wanos.WanosCommunication.response;

import com.wanos.WanosCommunication.BaseResponse;
import com.wanos.WanosCommunication.bean.ZeroAudioInfo;

/* JADX INFO: loaded from: classes3.dex */
public class GetZeroAudioInfoResponse extends BaseResponse {
    public ZeroAudioInfo data;

    public String toString() {
        return "GetZeroAudioInfoResponse{data=" + this.data.toString() + ", code=" + this.code + ", msg='" + this.msg + "'}";
    }
}
