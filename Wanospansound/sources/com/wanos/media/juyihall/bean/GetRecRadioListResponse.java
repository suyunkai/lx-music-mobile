package com.wanos.media.juyihall.bean;

import com.wanos.WanosCommunication.BaseResponse;
import com.wanos.WanosCommunication.bean.AudioBookAlbumInfoBean;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class GetRecRadioListResponse extends BaseResponse {
    public Data data;

    public static class Data {
        List<AudioBookAlbumInfoBean> radio;
    }

    public List<AudioBookAlbumInfoBean> getList() {
        Data data = this.data;
        if (data == null) {
            return null;
        }
        return data.radio;
    }
}
