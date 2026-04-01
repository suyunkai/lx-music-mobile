package com.wanos.media.juyihall.service;

import com.wanos.WanosCommunication.response.GetMusicListResponse;
import com.wanos.WanosCommunication.response.GetTagListResponse;
import com.wanos.commonlibrary.utils.AppConstants;
import com.wanos.media.juyihall.bean.GetRecBannerResponse;
import com.wanos.media.juyihall.bean.GetRecDailyResponse;
import com.wanos.media.juyihall.bean.GetRecMediaDetailResponse;
import com.wanos.media.juyihall.bean.GetRecMusicGroupListResponse;
import com.wanos.media.juyihall.bean.GetRecMusicListResponse;
import com.wanos.media.juyihall.bean.GetRecRadioListResponse;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/* JADX INFO: loaded from: classes3.dex */
public interface RecommendService {
    @GET("commonmedia/recommendConfig/GetRadio")
    Call<GetRecRadioListResponse> getRecommendAudioBookList(@Query("is_refresh") boolean z);

    @GET("commonmedia/banner/getCommonBannerList")
    Call<GetRecBannerResponse> getRecommendBannerList(@Query("bannerType") int i);

    @GET("commonmedia/dailyRecommend/getData")
    Call<GetRecDailyResponse> getRecommendDailyList(@Query(AppConstants.ACTIVITY_TAB_PAGE) int i, @Query("pageSize") int i2);

    @GET("commonmedia/recommendConfig/GetRecommendFunctionMusic")
    Call<GetRecMusicListResponse> getRecommendFunctionList(@Query("is_refresh") boolean z);

    @GET("commonmedia/dailyRecommend/mediaDetail")
    Call<GetRecMediaDetailResponse> getRecommendMediaDetail(@Query("id") String str, @Query("mediaType") int i);

    @GET("commonmedia/recommendConfig/GetPreciousMusicGroup")
    Call<GetRecMusicGroupListResponse> getRecommendMusicGroupList(@Query("is_refresh") boolean z);

    @GET("commonmedia/recommendConfig/GetRecommendSelectionMusic")
    Call<GetRecMusicListResponse> getRecommendMusicList(@Query("is_refresh") boolean z);

    @GET("commonmedia/music/getMusicShowTagList")
    Call<GetTagListResponse> getRecommendTagList();

    @GET("commonmedia/music/getMusicList")
    Call<GetMusicListResponse> getTagMusicList(@Query(AppConstants.ACTIVITY_TAB_PAGE) int i, @Query("pageSize") int i2, @Query("tags") List<String> list);
}
