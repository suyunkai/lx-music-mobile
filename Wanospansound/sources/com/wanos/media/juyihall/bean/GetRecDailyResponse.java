package com.wanos.media.juyihall.bean;

import com.wanos.WanosCommunication.BaseResponse;
import com.wanos.WanosCommunication.bean.MediaMusicListBean;
import com.wanos.WanosCommunication.response.GetMusicListResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class GetRecDailyResponse extends BaseResponse {
    public Data data;

    public static class Data {
        List<RecMediaInfo> mediaList;
    }

    public List<RecMediaInfo> getList() {
        Data data = this.data;
        if (data == null) {
            return null;
        }
        return data.mediaList;
    }

    public GetMusicListResponse toMusicListResponse() {
        GetMusicListResponse getMusicListResponse = new GetMusicListResponse();
        getMusicListResponse.code = this.code;
        getMusicListResponse.msg = this.msg;
        if (getList() == null) {
            getMusicListResponse.data = null;
            return getMusicListResponse;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<RecMediaInfo> it = getList().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().toMediaInfoBean());
        }
        getMusicListResponse.data = new MediaMusicListBean();
        getMusicListResponse.data.setMusicList(arrayList);
        getMusicListResponse.data.setTotal(arrayList.size());
        return getMusicListResponse;
    }
}
