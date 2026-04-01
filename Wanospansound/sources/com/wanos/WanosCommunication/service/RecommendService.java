package com.wanos.WanosCommunication.service;

import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.response.GetMusicDetailResponse;
import com.wanos.WanosCommunication.response.GetMusicListResponse;
import java.io.IOException;
import retrofit2.Response;

/* JADX INFO: loaded from: classes3.dex */
public interface RecommendService {
    Response<GetMusicDetailResponse> getRecommendMusicDetail(String str, int i) throws IOException;

    Response<GetMusicListResponse> getRecommendMusicList(int i, int i2, long j, String str) throws IOException;

    void getRecommendMusicList(int i, int i2, long j, ResponseCallBack<GetMusicListResponse> responseCallBack);
}
