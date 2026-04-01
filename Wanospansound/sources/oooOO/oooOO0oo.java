package oooOO;

import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import com.arcvideo.ivi.res.sdk.data.ActivateBean;
import com.arcvideo.ivi.res.sdk.data.ActivateBodyBean;
import com.arcvideo.ivi.res.sdk.data.AuthInfoResponse;
import com.arcvideo.ivi.res.sdk.data.BaseResponse;
import com.arcvideo.ivi.res.sdk.data.CreateTokenBodyBean;
import com.arcvideo.ivi.res.sdk.data.CreateTokenResponse;
import kotlinx.coroutines.flow.Flow;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/* JADX INFO: loaded from: classes3.dex */
public interface oooOO0oo {
    @Headers({"X-Plt-Action: vehicleLicenseGetCarLicenseInfo", "X-Plt-Ver: 3.0"})
    @POST("/rest")
    Flow<AuthInfoResponse> oooOO(@Header("X-Plt-Ts") String str, @Header("X-Plt-Ak") String str2, @Header("X-Plt-Signature") String str3, @Body ActivateBean activateBean);

    @Headers({"X-Plt-Action: commonBfeLoadBannerList", "X-Plt-Ver: 3.0"})
    @GET("/rest")
    Flow<BaseResponse<Boolean>> oooOO0oo(@Header("X-Plt-Ts") String str, @Header("X-Plt-Ak") String str2, @Header("X-Plt-Signature") String str3, @Query(CmcdConfiguration.KEY_CONTENT_ID) int i);

    @Headers({"X-Plt-Action: vehicleLicenseActivateClient", "X-Plt-Ver: 3.0"})
    @POST("/rest")
    Flow<BaseResponse<Boolean>> oooOO0oo(@Header("X-Plt-Ts") String str, @Header("X-Plt-Ak") String str2, @Header("X-Plt-Signature") String str3, @Body ActivateBean activateBean);

    @Headers({"X-Plt-Action: vehicleLicenseExtendActivate", "X-Plt-Ver: 3.0"})
    @POST("/rest")
    Flow<BaseResponse<Boolean>> oooOO0oo(@Header("X-Plt-Ts") String str, @Header("X-Plt-Ak") String str2, @Header("X-Plt-Signature") String str3, @Body ActivateBodyBean activateBodyBean);

    @Headers({"X-Plt-Action: commonAuthCreateTokenByAK", "X-Plt-Ver: 3.0"})
    @POST("/rest")
    Flow<CreateTokenResponse<Boolean>> oooOO0oo(@Header("X-Plt-Ts") String str, @Header("X-Plt-Ak") String str2, @Header("X-Plt-Signature") String str3, @Body CreateTokenBodyBean createTokenBodyBean);
}
