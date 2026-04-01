package com.wanos.media.juyihall.repo;

import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.WanosCommunication.response.GetMusicListResponse;
import com.wanos.WanosCommunication.response.GetTagListResponse;
import com.wanos.media.juyihall.bean.GetRecBannerResponse;
import com.wanos.media.juyihall.bean.GetRecDailyResponse;
import com.wanos.media.juyihall.bean.GetRecMediaDetailResponse;
import com.wanos.media.juyihall.bean.GetRecMusicGroupListResponse;
import com.wanos.media.juyihall.bean.GetRecMusicListResponse;
import com.wanos.media.juyihall.bean.GetRecRadioListResponse;
import com.wanos.media.juyihall.service.RecommendService;
import java.io.IOException;
import java.util.ArrayList;
import retrofit2.Response;
import retrofit2.Retrofit;

/* JADX INFO: loaded from: classes3.dex */
public class RecommendRepository {
    private final RecommendService service = (RecommendService) getRetrofit().create(RecommendService.class);

    private Retrofit getRetrofit() {
        return WanOSRetrofitUtil.getRetrofit();
    }

    public void getRecommendMusicList(boolean z, ResponseCallBack<GetRecMusicListResponse> responseCallBack) {
        this.service.getRecommendMusicList(z).enqueue(responseCallBack);
    }

    public Response<GetRecMusicListResponse> getRecommendMusicList(boolean z) throws IOException {
        return this.service.getRecommendMusicList(z).execute();
    }

    public void getRecommendDailyList(int i, int i2, ResponseCallBack<GetRecDailyResponse> responseCallBack) {
        this.service.getRecommendDailyList(i, i2).enqueue(responseCallBack);
    }

    public Response<GetRecMediaDetailResponse> getRecommendDailyMediaDetail(String str, int i) throws IOException {
        return this.service.getRecommendMediaDetail(str, i).execute();
    }

    public Response<GetRecDailyResponse> getRecommendDailyList(int i, int i2) throws IOException {
        return this.service.getRecommendDailyList(i, i2).execute();
    }

    public void getRecommendAudioBookList(boolean z, ResponseCallBack<GetRecRadioListResponse> responseCallBack) {
        this.service.getRecommendAudioBookList(z).enqueue(responseCallBack);
    }

    public void getRecommendBannerList(ResponseCallBack<GetRecBannerResponse> responseCallBack) {
        this.service.getRecommendBannerList(3).enqueue(responseCallBack);
    }

    public void getRecommendFunctionList(boolean z, ResponseCallBack<GetRecMusicListResponse> responseCallBack) {
        this.service.getRecommendFunctionList(z).enqueue(responseCallBack);
    }

    public Response<GetRecMusicListResponse> getRecommendFunctionList(boolean z) throws IOException {
        return this.service.getRecommendFunctionList(z).execute();
    }

    public void getRecommendMusicGroupList(boolean z, ResponseCallBack<GetRecMusicGroupListResponse> responseCallBack) {
        this.service.getRecommendMusicGroupList(z).enqueue(responseCallBack);
    }

    public void getRecommendTagList(ResponseCallBack<GetTagListResponse> responseCallBack) {
        this.service.getRecommendTagList().enqueue(responseCallBack);
    }

    public void getTagMusicList(int i, int i2, String str, ResponseCallBack<GetMusicListResponse> responseCallBack) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        this.service.getTagMusicList(i, i2, arrayList).enqueue(responseCallBack);
    }

    public Response<GetMusicListResponse> getTagMusicList(int i, int i2, String str) throws IOException {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        return this.service.getTagMusicList(i, i2, arrayList).execute();
    }
}
