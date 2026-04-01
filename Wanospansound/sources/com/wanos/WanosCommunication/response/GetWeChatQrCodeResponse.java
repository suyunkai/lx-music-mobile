package com.wanos.WanosCommunication.response;

import com.wanos.WanosCommunication.BaseResponse;

/* JADX INFO: loaded from: classes3.dex */
public class GetWeChatQrCodeResponse extends BaseResponse {
    public WeChatQrCode data;

    public class WeChatQrCode {
        public String qrCodeUrl;

        public WeChatQrCode() {
        }
    }
}
