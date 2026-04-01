package com.wanos.media.juyihall.bean;

import com.wanos.WanosCommunication.BaseResponse;
import com.wanos.WanosCommunication.bean.MediaMusicListBean;
import com.wanos.WanosCommunication.response.GetMusicListResponse;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class GetRecMusicListResponse extends BaseResponse {
    public Data data;

    public static class Data {
        List<MusicInfoBean> groups;
    }

    public List<MusicInfoBean> getList() {
        Data data = this.data;
        if (data == null) {
            return null;
        }
        return data.groups;
    }

    public GetMusicListResponse toMusicListResponse() {
        GetMusicListResponse getMusicListResponse = new GetMusicListResponse();
        getMusicListResponse.code = this.code;
        getMusicListResponse.msg = this.msg;
        List<MusicInfoBean> list = getList();
        getMusicListResponse.data = new MediaMusicListBean();
        getMusicListResponse.data.setMusicList(list);
        getMusicListResponse.data.setTotal(list.size());
        return getMusicListResponse;
    }
}
