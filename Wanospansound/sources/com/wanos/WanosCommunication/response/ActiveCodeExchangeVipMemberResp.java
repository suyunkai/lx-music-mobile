package com.wanos.WanosCommunication.response;

import com.wanos.WanosCommunication.BaseResponse;
import com.wanos.WanosCommunication.bean.ActiveCodeExchangeVipMemberBean;

/* JADX INFO: loaded from: classes3.dex */
public class ActiveCodeExchangeVipMemberResp extends BaseResponse {
    private ActiveCodeExchangeVipMemberBean data;

    public ActiveCodeExchangeVipMemberBean getData() {
        return this.data;
    }

    public void setData(ActiveCodeExchangeVipMemberBean activeCodeExchangeVipMemberBean) {
        this.data = activeCodeExchangeVipMemberBean;
    }
}
