package com.wanos.media.juyihall.bean;

import com.wanos.WanosCommunication.BaseResponse;
import com.wanos.WanosCommunication.bean.MusicGroupInfo;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class GetRecMusicGroupListResponse extends BaseResponse {
    public Data data;

    public static class Data {
        List<MusicGroupInfo> groups;
    }

    public List<MusicGroupInfo> getList() {
        Data data = this.data;
        if (data == null) {
            return null;
        }
        return data.groups;
    }
}
