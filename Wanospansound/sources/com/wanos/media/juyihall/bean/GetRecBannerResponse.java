package com.wanos.media.juyihall.bean;

import com.wanos.WanosCommunication.BaseResponse;
import com.wanos.WanosCommunication.bean.BannerBean;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class GetRecBannerResponse extends BaseResponse {
    public Data data;

    public static class Data {
        List<BannerBean> list;
    }

    public List<BannerBean> getList() {
        Data data = this.data;
        if (data == null) {
            return null;
        }
        return data.list;
    }
}
