package oooOO;

import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import com.arcvideo.ivi.res.sdk.data.IntentFilterBean;
import com.arcvideo.ivi.res.sdk.data.RegisterRequestBean;
import com.arcvideo.ivi.res.sdk.data.SuggestInfo;
import com.arcvideo.ivi.res.sdk.data.VideoSearchResponse;
import com.arcvideo.ivi.res.sdk.innerbean.ChannelResponse;
import com.arcvideo.ivi.res.sdk.innerbean.DetailResponse;
import com.arcvideo.ivi.res.sdk.innerbean.DeviceSession;
import com.arcvideo.ivi.res.sdk.innerbean.DolbyResponse;
import com.arcvideo.ivi.res.sdk.innerbean.EpgResponse;
import com.arcvideo.ivi.res.sdk.innerbean.RecFallResponse;
import com.arcvideo.ivi.res.sdk.innerbean.SystemTime;
import com.arcvideo.ivi.res.sdk.innerbean.VideoResponse;
import com.ecarx.eas.sdk.IServiceManager;
import com.wanos.media.viewmodel.RelaxPlayerInfoEditViewModel;
import java.util.List;
import kotlinx.coroutines.flow.Flow;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/* JADX INFO: loaded from: classes3.dex */
public interface oooOO {
    @GET("/api/systime")
    Flow<SystemTime> oooOO();

    @POST("/api/register")
    Flow<DeviceSession> oooOO(@Body RegisterRequestBean registerRequestBean);

    @GET("/api/suggest")
    Flow<List<SuggestInfo>> oooOO(@Query("key") String str);

    @GET("/api/episodeList/{qipuId}")
    Flow<EpgResponse> oooOO(@Path("qipuId") String str, @Query(IServiceManager.SERVICE_POS) int i, @Query("num") int i2);

    @GET("/api/hotwords")
    Flow<List<SuggestInfo>> oooOO0oo();

    @GET("/api/hotwords")
    Flow<List<SuggestInfo>> oooOO0oo(@Query("count") int i);

    @GET("/api/dolbyAtmos")
    Flow<DolbyResponse> oooOO0oo(@Query("chnId") int i, @Query("pageNum") int i2, @Query("pageSize") int i3);

    @GET("/api/rankCharts")
    Flow<List<VideoResponse>> oooOO0oo(@Query(CmcdConfiguration.KEY_CONTENT_ID) int i, @Query("pageNumber") int i2, @Query("pageSize") int i3, @Query("vip") int i4, @Query("charts") String str);

    @GET("/api/videolib")
    Flow<ChannelResponse> oooOO0oo(@Query("chnId") int i, @Query("deviceId") String str, @Query("pageNum") int i2, @Query("pageSize") int i3, @Query("threeCategoryId") String str2, @Query("requireTags") String str3, @Query(RelaxPlayerInfoEditViewModel.KEY_OPEN_MODE) String str4, @Query("ispurchase") Integer num, @Query("session") String str5, @Query("is3d") Integer num2);

    @GET("/api/tagSelectVideo")
    Flow<ChannelResponse> oooOO0oo(@Query("chnId") int i, @Query("deviceId") String str, @Query("paramQuery") String str2, @Query("pageNum") int i2, @Query("pageSize") int i3, @Query("requireTags") String str3, @Query("session") String str4, @Query("is3d") Integer num);

    @GET("/api/epgInfo/{qipuId}")
    Flow<DetailResponse> oooOO0oo(@Path("qipuId") String str);

    @GET("/api/recFall")
    Flow<RecFallResponse> oooOO0oo(@Query("deviceId") String str, @Query("offset") int i, @Query("pageSize") int i2);

    @GET("/api/intentList")
    Flow<IntentFilterBean> oooOO0oo(@Query("termQuery") String str, @Query("pageNum") int i, @Query("pageSize") int i2, @Query(RelaxPlayerInfoEditViewModel.KEY_OPEN_MODE) int i3);

    @GET("/api/videoSearch")
    Flow<VideoSearchResponse> oooOO0oo(@Query("key") String str, @Query("pageNum") int i, @Query("pageSize") int i2, @Query(RelaxPlayerInfoEditViewModel.KEY_OPEN_MODE) int i3, @Query("intentResultNumber") int i4);

    @GET("/api/resource")
    Flow<DolbyResponse> oooOO0oo(@Query("resourceId") String str, @Query("pageNum") Integer num, @Query("pageSize") Integer num2);

    @GET("/api/recommendv2/{qipuId}")
    Flow<DolbyResponse> oooOO0oo(@Path("qipuId") String str, @Query("deviceId") String str2, @Query("num") int i);

    @GET("/api/search")
    Flow<ChannelResponse> oooOO0oo(@Query("key") String str, @Query(RelaxPlayerInfoEditViewModel.KEY_OPEN_MODE) String str2, @Query("pageNum") int i, @Query("pageSize") int i2);

    @POST("/api/register")
    Call<DeviceSession> oooOO0oo(@Body RegisterRequestBean registerRequestBean);

    @GET("/api/recommend/{qipuId}")
    Flow<DolbyResponse> oooOOO00(@Path("qipuId") String str);
}
