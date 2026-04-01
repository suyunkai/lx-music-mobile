package com.wanos.media.NetWork;

import com.wanos.commonlibrary.utils.AppConstants;
import com.wanos.media.entity.BaseEntity;
import com.wanos.media.entity.BellsEntity;
import com.wanos.media.entity.CollectCoveEntity;
import com.wanos.media.entity.CreateOrCollectUserThemeReply;
import com.wanos.media.entity.EmptyEntity;
import com.wanos.media.entity.GetAlarmAudioInfo;
import com.wanos.media.entity.GetAudioInfoResp;
import com.wanos.media.entity.GetMaterialAudioInfo;
import com.wanos.media.entity.GetShareCodeEntity;
import com.wanos.media.entity.ThemeInfoEntity;
import com.wanos.media.entity.ZeroThemeListEntity;
import com.wanos.media.viewmodel.SendShareCodeViewModel;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/* JADX INFO: loaded from: classes3.dex */
public interface ApiService {
    @POST("comfortspace/theme/copy")
    Call<BaseEntity<EmptyEntity>> addThemeFromShareCode(@Body RequestBody requestBody);

    @POST("comfortspace/audio/config/change")
    Call<BaseEntity<EmptyEntity>> cancelCollectAudio(@Body RequestBody requestBody);

    @POST("comfortspace/theme/user/delete")
    Call<BaseEntity<EmptyEntity>> deleteTheme(@Body RequestBody requestBody);

    @GET("comfortspace/bell/detail")
    Call<BaseEntity<GetAlarmAudioInfo>> getAlarmAudioInfo(@Query("bellId") int i);

    @GET("comfortspace/audio/info/get")
    Call<BaseEntity<GetAudioInfoResp>> getAudioLibrary();

    @GET("comfortspace/bell/list")
    Call<BaseEntity<BellsEntity>> getBellList();

    @GET("comfortspace/coverImg/list")
    Call<BaseEntity<CollectCoveEntity>> getCollectCoveLis();

    @GET("comfortspace/sound/materials/detail")
    Call<BaseEntity<GetMaterialAudioInfo>> getMaterialAudioInfo(@Query("id") int i);

    @GET("comfortspace/theme/model/get")
    Call<BaseEntity<ThemeInfoEntity>> getThemeInfo(@Query(SendShareCodeViewModel.KEY_THEME_ID) long j);

    @GET("comfortspace/theme/shareCode/get")
    Call<BaseEntity<GetShareCodeEntity>> getThemeShareCode(@Query(SendShareCodeViewModel.KEY_THEME_ID) String str);

    @GET("comfortspace/theme/user/get")
    Call<BaseEntity<ThemeInfoEntity>> getUserThemeInfo(@Query("themeID") long j);

    @GET("comfortspace/theme/model/list")
    Call<BaseEntity<ZeroThemeListEntity>> getZeroThemeList(@Query("label") int i, @Query(AppConstants.ACTIVITY_TAB_PAGE) int i2, @Query("pageSize") int i3);

    @GET("comfortspace/theme/user/list")
    Call<BaseEntity<ZeroThemeListEntity>> getZeroUserThemeList(@Query(AppConstants.ACTIVITY_TAB_PAGE) int i, @Query("pageSize") int i2, @Query("label") int i3);

    @POST("comfortspace/theme/user/create")
    Call<BaseEntity<CreateOrCollectUserThemeReply>> saveAsTheme(@Body RequestBody requestBody);

    @POST("comfortspace/theme/user/update")
    Call<BaseEntity<EmptyEntity>> setTopTheme(@Body RequestBody requestBody);
}
