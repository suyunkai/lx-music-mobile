package com.wanos.WanosCommunication;

import android.util.Log;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import com.baidubce.AbstractBceClient;
import com.google.gson.Gson;
import com.wanos.WanosCommunication.interceptor.LoggingInterceptor;
import com.wanos.WanosCommunication.response.ActiveCodeExchangeVipMemberResp;
import com.wanos.WanosCommunication.response.AddWorkForShareCodeResponse;
import com.wanos.WanosCommunication.response.AudioBookLikeChapterResponse;
import com.wanos.WanosCommunication.response.AudioBookLikeRadioResponse;
import com.wanos.WanosCommunication.response.BellListResponse;
import com.wanos.WanosCommunication.response.CollectCoveResponse;
import com.wanos.WanosCommunication.response.CreateOrCollectUserThemeReply;
import com.wanos.WanosCommunication.response.CreateVipOrderResponse;
import com.wanos.WanosCommunication.response.DeviceInfoResponse;
import com.wanos.WanosCommunication.response.DeviceTokenResponse;
import com.wanos.WanosCommunication.response.GetAdDialogInfoResponse;
import com.wanos.WanosCommunication.response.GetAudioBookAlbumResponse;
import com.wanos.WanosCommunication.response.GetAudioBookChapterDetailResponse;
import com.wanos.WanosCommunication.response.GetAudioBookChapterListIsLikeResponse;
import com.wanos.WanosCommunication.response.GetAudioBookChapterListResponse;
import com.wanos.WanosCommunication.response.GetAudioBookLikeChapterListResponse;
import com.wanos.WanosCommunication.response.GetAudioBookLikeRadioListResponse;
import com.wanos.WanosCommunication.response.GetAudioBookMineChapterListResponse;
import com.wanos.WanosCommunication.response.GetAudioBookRadioDetailResponse;
import com.wanos.WanosCommunication.response.GetAudioBookRadioListResponse;
import com.wanos.WanosCommunication.response.GetAudioBookRecommendListResponse;
import com.wanos.WanosCommunication.response.GetAudioBookStatistic;
import com.wanos.WanosCommunication.response.GetAudioInfoResp;
import com.wanos.WanosCommunication.response.GetCcDataResponse;
import com.wanos.WanosCommunication.response.GetFreeMusicGroupCoverResponse;
import com.wanos.WanosCommunication.response.GetGiveVipMemberResponse;
import com.wanos.WanosCommunication.response.GetH5UrlResponse;
import com.wanos.WanosCommunication.response.GetModuleCoverDetailsResponse;
import com.wanos.WanosCommunication.response.GetMusicBannerListResponse;
import com.wanos.WanosCommunication.response.GetMusicDetailResponse;
import com.wanos.WanosCommunication.response.GetMusicGroupDetailResponse;
import com.wanos.WanosCommunication.response.GetMusicGroupListResponse;
import com.wanos.WanosCommunication.response.GetMusicListResponse;
import com.wanos.WanosCommunication.response.GetMusicStatistic;
import com.wanos.WanosCommunication.response.GetOrderInfoByOrderNoResponse;
import com.wanos.WanosCommunication.response.GetOrderInfoListResponse;
import com.wanos.WanosCommunication.response.GetQAListResponse;
import com.wanos.WanosCommunication.response.GetServiceProtocolResponse;
import com.wanos.WanosCommunication.response.GetServiceStatResponse;
import com.wanos.WanosCommunication.response.GetShareCodeResponse;
import com.wanos.WanosCommunication.response.GetUserEventStatistic;
import com.wanos.WanosCommunication.response.GetUserInfoResponse;
import com.wanos.WanosCommunication.response.GetVideoInfoResponse;
import com.wanos.WanosCommunication.response.GetVipGoodsListResponse;
import com.wanos.WanosCommunication.response.GetVipPriceStatistic;
import com.wanos.WanosCommunication.response.GetWeChatQrCodeResponse;
import com.wanos.WanosCommunication.response.GetZeroAudioInfoResponse;
import com.wanos.WanosCommunication.response.GetZeroAudioResponse;
import com.wanos.WanosCommunication.response.MusicCollectStatuListResponse;
import com.wanos.WanosCommunication.response.MusicVipAndLimitedFreeStatuListResponse;
import com.wanos.WanosCommunication.response.PayVipMemberResponse;
import com.wanos.WanosCommunication.response.RecommendWordsResponse;
import com.wanos.WanosCommunication.response.SaveCcDataResponse;
import com.wanos.WanosCommunication.response.UserAvatarListResponse;
import com.wanos.WanosCommunication.response.ZeroThemeInfoResponse;
import com.wanos.WanosCommunication.response.ZeroThemeListResponse;
import com.wanos.WanosCommunication.response.ZeroUserThemeListResponse;
import com.wanos.WanosCommunication.service.WanOSRetrofitService;
import com.wanos.commonlibrary.utils.CommonUtils;
import com.wanos.media.ui.music.activity.MusicPlayActivity;
import com.wanos.media.viewmodel.SendShareCodeViewModel;
import cz.msebera.android.httpclient.cookie.ClientCookie;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/* JADX INFO: loaded from: classes3.dex */
public class WanOSRetrofitUtil {
    private static final long DEBOUNCE_DELAY = 3000;
    public static final String TAG = "wanos:[WanOSRetrofitUtil]";
    private static List<Interceptor> interceptorList = null;
    private static boolean isDebug = true;
    public static boolean isNetConnect = true;
    private static long lastInvokeTime;
    private static WanOSRetrofitService service;
    private static WanOSRetrofitService serviceK8;

    public static void init(boolean z, Interceptor interceptor) {
        ArrayList arrayList = new ArrayList();
        interceptorList = arrayList;
        arrayList.add(interceptor);
        isDebug = z;
    }

    public static void init(boolean z, List<Interceptor> list) {
        interceptorList = list;
        isDebug = z;
    }

    public static boolean isDebug() {
        return isDebug;
    }

    public static void setDebug(boolean z) {
        isDebug = z;
    }

    public static RequestBody mapToJsonRequestBody(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        map.put("channel", CommonUtils.getAppChannelId());
        map.put("clientIp", CommonUtils.getIPAddress());
        return RequestBody.create(new Gson().toJson(map), MediaType.parse(AbstractBceClient.DEFAULT_CONTENT_TYPE));
    }

    public static Retrofit getRetrofit() {
        return getRetrofit(URLConstan.BASE_URL_NEW);
    }

    public static Retrofit getRetrofit4Creator() {
        return getRetrofit(URLConstan.BASE_CREATOR_URL);
    }

    public static Retrofit getRetrofit(String str) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        List<Interceptor> list = interceptorList;
        if (list != null && list.size() > 0) {
            for (Interceptor interceptor : interceptorList) {
                if (interceptor != null) {
                    builder.addInterceptor(interceptor);
                }
            }
        }
        builder.addInterceptor(new ErrorLoggingInterceptor());
        builder.addInterceptor(new UnknownErrorRetryInterceptor(3));
        if (isDebug) {
            builder.addInterceptor(new LoggingInterceptor());
        }
        builder.connectTimeout(15L, TimeUnit.SECONDS);
        builder.readTimeout(20L, TimeUnit.SECONDS);
        builder.writeTimeout(15L, TimeUnit.SECONDS);
        return new Retrofit.Builder().baseUrl(str).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
    }

    public static WanOSRetrofitService getWanOSRetrofitService() {
        if (service == null) {
            service = (WanOSRetrofitService) getRetrofit(URLConstan.BASE_URL).create(WanOSRetrofitService.class);
        }
        return service;
    }

    public static WanOSRetrofitService getWanOSRetrofitServiceV8() {
        if (serviceK8 == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            List<Interceptor> list = interceptorList;
            if (list != null && list.size() > 0) {
                for (Interceptor interceptor : interceptorList) {
                    if (interceptor != null) {
                        builder.addInterceptor(interceptor);
                    }
                }
            }
            builder.addInterceptor(new UnknownErrorRetryInterceptor(3));
            if (isDebug) {
                builder.addInterceptor(new LoggingInterceptor());
            }
            builder.connectTimeout(15L, TimeUnit.SECONDS);
            builder.readTimeout(20L, TimeUnit.SECONDS);
            builder.writeTimeout(15L, TimeUnit.SECONDS);
            serviceK8 = (WanOSRetrofitService) new Retrofit.Builder().baseUrl(URLConstan.BASE_URL_NEW).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build().create(WanOSRetrofitService.class);
        }
        return serviceK8;
    }

    public static void getVideoInfoInfobyId(int i, ResponseCallBack<GetVideoInfoResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put("videoId", Integer.valueOf(i));
        getWanOSRetrofitService().getVideoInfo(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static Response<GetVideoInfoResponse> getVideoInfoInfobyId(long j) throws IOException {
        HashMap map = new HashMap();
        map.put("videoId", Long.valueOf(j));
        return getWanOSRetrofitService().getVideoInfo(mapToJsonRequestBody(map)).execute();
    }

    public static void getRecommendMusicGroupList(int i, int i2, ResponseCallBack<GetMusicGroupListResponse> responseCallBack) {
        getWanOSRetrofitService().getRecommendMusicGroupList(i, i2).enqueue(responseCallBack);
    }

    public static void getMusicGroupCollectList(int i, int i2, ResponseCallBack<GetMusicGroupListResponse> responseCallBack) {
        getWanOSRetrofitService().getMusicGroupCollectList(i, i2).enqueue(responseCallBack);
    }

    public static void getMusicGroupList(int i, int i2, ResponseCallBack<GetMusicGroupListResponse> responseCallBack) {
        getWanOSRetrofitService().getMusicGroupList(i, i2).enqueue(responseCallBack);
    }

    public static void getMusicGroupDetail(long j, int i, int i2, ResponseCallBack<GetMusicGroupDetailResponse> responseCallBack) {
        getWanOSRetrofitService().getMusicGroupDetail(j, i, i2).enqueue(responseCallBack);
    }

    public static Response<GetMusicGroupDetailResponse> getMusicGroupDetail(long j, int i, int i2) throws IOException {
        return getWanOSRetrofitService().getMusicGroupDetail(j, i, i2).execute();
    }

    public static void musicGroupCollect(long j, int i, ResponseCallBack<BaseResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put("musicGroupId", Long.valueOf(j));
        map.put("source", Integer.valueOf(i));
        getWanOSRetrofitService().musicGroupCollect(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static void musicGroupCollectCancel(long j, int i, ResponseCallBack<BaseResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put("musicGroupId", Long.valueOf(j));
        map.put("source", Integer.valueOf(i));
        getWanOSRetrofitService().musicGroupCollectCancel(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static void getMusicDetail(long j, ResponseCallBack<GetMusicDetailResponse> responseCallBack) {
        getWanOSRetrofitService().getMusickDetail(j).enqueue(responseCallBack);
    }

    public static Response<GetMusicDetailResponse> getSynchMusicDetail(long j) throws IOException {
        return getWanOSRetrofitService().getMusickDetail(j).execute();
    }

    public static void getHotMusicList(int i, int i2, ResponseCallBack<GetMusicListResponse> responseCallBack) {
        getWanOSRetrofitService().getHotMusicList(i, i2).enqueue(responseCallBack);
    }

    public static Response<GetMusicListResponse> getHotMusicList(int i, int i2) throws IOException {
        return getWanOSRetrofitService().getHotMusicList(i, i2).execute();
    }

    public static void getRankMusicList(int i, int i2, ResponseCallBack<GetMusicListResponse> responseCallBack) {
        getWanOSRetrofitService().getRankMusicList(i, i2).enqueue(responseCallBack);
    }

    public static void getRankMusicListDetails(int i, int i2, ResponseCallBack<GetMusicListResponse> responseCallBack) {
        getWanOSRetrofitService().getRankMusicListDetails(i, i2).enqueue(responseCallBack);
    }

    public static Response<GetMusicListResponse> getRankMusicListDetailsSync(int i, int i2) throws IOException {
        return getWanOSRetrofitService().getRankMusicListDetails(i, i2).execute();
    }

    public static Response<GetMusicListResponse> getRankMusicList(int i, int i2) throws IOException {
        return getWanOSRetrofitService().getRankMusicList(i, i2).execute();
    }

    public static void getCollectMusicList(int i, int i2, ResponseCallBack<GetMusicListResponse> responseCallBack) {
        getWanOSRetrofitService().getCollectMusicList(i, i2).enqueue(responseCallBack);
    }

    public static Response<GetMusicListResponse> getCollectMusicList(int i, int i2) throws IOException {
        return getWanOSRetrofitService().getCollectMusicList(i, i2).execute();
    }

    public static void getModuleMusicListByGroupId(long j, int i, int i2, ResponseCallBack<GetMusicListResponse> responseCallBack) {
        getWanOSRetrofitService().getModuleMusicListByGroupId(j, i, i2).enqueue(responseCallBack);
    }

    public static Response<GetMusicListResponse> getModuleMusicListByGroupId(long j, int i, int i2) throws IOException {
        return getWanOSRetrofitService().getModuleMusicListByGroupId(j, i, i2).execute();
    }

    public static void getMusicList(int i, int i2, ResponseCallBack<GetMusicListResponse> responseCallBack) {
        getWanOSRetrofitService().getMusicList(i, i2).enqueue(responseCallBack);
    }

    public static void musicCollect(long j, int i, ResponseCallBack<BaseResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put(MusicPlayActivity.MUSIC_ID_KEY, Long.valueOf(j));
        map.put("source", Integer.valueOf(i));
        getWanOSRetrofitService().musicCollect(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static void musicCollectCancel(long j, int i, ResponseCallBack<BaseResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put(MusicPlayActivity.MUSIC_ID_KEY, Long.valueOf(j));
        map.put("source", Integer.valueOf(i));
        getWanOSRetrofitService().musicCollectCancel(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static void getMusicCollectStatusByIds(long[] jArr, ResponseCallBack<MusicCollectStatuListResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put("ids", jArr);
        getWanOSRetrofitService().getMusicCollectStatusByIds(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static Response<MusicCollectStatuListResponse> getMusicCollectStatusByIds(long[] jArr) throws IOException {
        HashMap map = new HashMap();
        map.put("ids", jArr);
        map.put("mediaType", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
        return getWanOSRetrofitService().getMusicCollectStatusByIds(mapToJsonRequestBody(map)).execute();
    }

    public static void getMusicBannerListData(ResponseCallBack<GetMusicBannerListResponse> responseCallBack) {
        getWanOSRetrofitService().getMusicBannerListData().enqueue(responseCallBack);
    }

    public static void getLimitedFreeMusicGroupCover(ResponseCallBack<GetFreeMusicGroupCoverResponse> responseCallBack) {
        getWanOSRetrofitService().getLimitedFreeMusicGroupCover().enqueue(responseCallBack);
    }

    public static void getModuleCoverDetails(int i, String str, ResponseCallBack<GetModuleCoverDetailsResponse> responseCallBack) {
        getWanOSRetrofitService().getModuleCoverDetails(i, str).enqueue(responseCallBack);
    }

    public static void getMediaFreeStatusByIds(long[] jArr, ResponseCallBack<MusicVipAndLimitedFreeStatuListResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put("ids", jArr);
        map.put("mediaType", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
        getWanOSRetrofitService().getMediaFreeStatusByIds(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static Response<MusicVipAndLimitedFreeStatuListResponse> getMediaFreeStatusByIds(long[] jArr) throws IOException {
        HashMap map = new HashMap();
        map.put("ids", jArr);
        map.put("mediaType", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
        return getWanOSRetrofitService().getMediaFreeStatusByIds(mapToJsonRequestBody(map)).execute();
    }

    public static void getLimitedFreeMusicList(int i, int i2, boolean z, ResponseCallBack<GetMusicListResponse> responseCallBack) {
        getWanOSRetrofitService().getLimitedFreeMusicList(i, i2, z).enqueue(responseCallBack);
    }

    public static Response<GetMusicListResponse> getLimitedFreeMusicList(int i, int i2, boolean z) throws IOException {
        return getWanOSRetrofitService().getLimitedFreeMusicList(i, i2, z).execute();
    }

    public static void recordeMusicPlayData(String str, String str2, ResponseCallBack<BaseResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put(MusicPlayActivity.MUSIC_ID_KEY, str);
        map.put("deviceId", str2);
        map.put("productId", null);
        mapToJsonRequestBody(map);
    }

    public static void getVipGoodsList(ResponseCallBack<GetVipGoodsListResponse> responseCallBack) {
        getWanOSRetrofitService().getVipGoodsList().enqueue(responseCallBack);
    }

    public static void mobileCodeLogin(String str, String str2, ResponseCallBack<GetUserInfoResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put("mobile", str);
        map.put("verifyCode", str2);
        getWanOSRetrofitService().mobileCodeLogin(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static void bindMobile(String str, String str2, String str3, ResponseCallBack<GetUserInfoResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put("mobile", str);
        map.put("verifyCode", str2);
        map.put("authCode", str3);
        getWanOSRetrofitService().wechatBindMobile(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static void getUserInfo(ResponseCallBack<GetUserInfoResponse> responseCallBack) {
        getWanOSRetrofitService().getUserInfo(mapToJsonRequestBody(new HashMap())).enqueue(responseCallBack);
    }

    public static void getUserAvatarList(ResponseCallBack<UserAvatarListResponse> responseCallBack) {
        getWanOSRetrofitService().getUserAvatarList(mapToJsonRequestBody(new HashMap())).enqueue(responseCallBack);
    }

    public static void getVerifyCode(String str, ResponseCallBack<BaseResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put("mobile", str);
        getWanOSRetrofitService().getVerifyCode(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static void logout(ResponseCallBack<BaseResponse> responseCallBack) {
        getWanOSRetrofitService().logout(mapToJsonRequestBody(new HashMap())).enqueue(responseCallBack);
    }

    public static void createVipOrder(String str, float f, String str2, float f2, ResponseCallBack<CreateVipOrderResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put("goodsNo", str);
        map.put("goodsPrice", Float.valueOf(f));
        map.put("goodsName", str2);
        map.put("goodsOriginalPrice", Float.valueOf(f2));
        getWanOSRetrofitService().createVipOrder(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static void payVipMember(String str, ResponseCallBack<PayVipMemberResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put("orderNo", str);
        getWanOSRetrofitService().payVipMember(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static void getOrderDetail(String str, ResponseCallBack<GetOrderInfoByOrderNoResponse> responseCallBack) {
        getWanOSRetrofitService().getOrderDetail(str).enqueue(responseCallBack);
    }

    public static void GetOrderInfoList(String str, String str2, ResponseCallBack<GetOrderInfoListResponse> responseCallBack) {
        getWanOSRetrofitService().getOrderInfoList(str, str2).enqueue(responseCallBack);
    }

    public static void getQAList(String str, String str2, ResponseCallBack<GetQAListResponse> responseCallBack) {
        getWanOSRetrofitServiceV8().getQAList(str, str2).enqueue(responseCallBack);
    }

    public static void getFeedbackUrl(String str, ResponseCallBack<GetH5UrlResponse> responseCallBack) {
        getWanOSRetrofitServiceV8().getFeedBackH5Url(str).enqueue(responseCallBack);
    }

    public static void updateUserinfo(String str, int i, long j, ResponseCallBack<BaseResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put("userName", str);
        map.put("sex", Integer.valueOf(i));
        map.put("birthday", Long.valueOf(j));
        getWanOSRetrofitService().updateUserinfo(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static void updateAvatar(String str, ResponseCallBack<BaseResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put("userAvatarId", str);
        getWanOSRetrofitService().updateAvatar(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static void getWeChatUserInfo(String str, ResponseCallBack<GetUserInfoResponse> responseCallBack) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - lastInvokeTime < 3000) {
            return;
        }
        lastInvokeTime = jCurrentTimeMillis;
        HashMap map = new HashMap();
        map.put("authCode", str);
        getWanOSRetrofitService().getWeChatUserInfo(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static void getWeChatQrCode(int i, ResponseCallBack<GetWeChatQrCodeResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put("colorType", Integer.valueOf(i));
        getWanOSRetrofitService().getWeChatQrCode(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static void GetHotRecommendWords(ResponseCallBack<RecommendWordsResponse> responseCallBack) {
        getWanOSRetrofitService().getHotRecommendWords().enqueue(responseCallBack);
    }

    public static void GetSearchMusicList(String str, String str2, String str3, ResponseCallBack<GetMusicListResponse> responseCallBack) {
        getWanOSRetrofitService().getSearchMusicList(str, str2, str3).enqueue(responseCallBack);
    }

    public static void GetSearchMusicGroupList(String str, String str2, String str3, ResponseCallBack<GetMusicGroupListResponse> responseCallBack) {
        getWanOSRetrofitService().getSearchMusicGroupList(str, str2, str3).enqueue(responseCallBack);
    }

    public static void GetSearchAudioBookAlbumList(String str, String str2, String str3, ResponseCallBack<GetAudioBookAlbumResponse> responseCallBack) {
        getWanOSRetrofitService().getSearchAudioBookList(str, str2, str3).enqueue(responseCallBack);
    }

    public static void getAudioBookAlbumList(int i, int i2, int i3, ResponseCallBack<GetAudioBookRadioListResponse> responseCallBack) {
        getWanOSRetrofitService().getAudioBookRadioList(i, i2, i3).enqueue(responseCallBack);
    }

    public static void getAudioBookAlbumDetail(long j, ResponseCallBack<GetAudioBookRadioDetailResponse> responseCallBack) {
        getWanOSRetrofitService().getAudioBookRadioDetail(j).enqueue(responseCallBack);
    }

    public static void getAudioBookChapterListV1(int i, int i2, long j, ResponseCallBack<GetAudioBookChapterListResponse> responseCallBack) {
        getWanOSRetrofitService().getAudioBookChapterListV1(i, i2, j).enqueue(responseCallBack);
    }

    public static void getAudioBookChapterList(int i, int i2, long j, ResponseCallBack<GetAudioBookMineChapterListResponse> responseCallBack) {
        Log.i(TAG, "requestChapterList: ->4");
        getWanOSRetrofitService().getAudioBookChapterList(i, i2, j).enqueue(responseCallBack);
    }

    public static Response<GetAudioBookMineChapterListResponse> getAudioBookChapterList(int i, int i2, long j) throws IOException {
        Log.i(TAG, "requestChapterList: ->5");
        return getWanOSRetrofitService().getAudioBookChapterList(i, i2, j).execute();
    }

    public static void getAudioBookChapterDetail(long j, ResponseCallBack<GetAudioBookChapterDetailResponse> responseCallBack) {
        getWanOSRetrofitService().getAudioBookChapterDetail(j).enqueue(responseCallBack);
    }

    public static Response<GetAudioBookChapterDetailResponse> getAudioBookChapterDetail(long j) throws IOException {
        return getWanOSRetrofitService().getAudioBookChapterDetail(j).execute();
    }

    public static void getAudioBookRecommendList(int i, int i2, ResponseCallBack<GetAudioBookRecommendListResponse> responseCallBack) {
        getWanOSRetrofitService().getAudioBookRecommendList(i, i2).enqueue(responseCallBack);
    }

    public static void getAudioBookLikeAlbumList(int i, int i2, ResponseCallBack<GetAudioBookLikeRadioListResponse> responseCallBack) {
        getWanOSRetrofitService().getAudioBookLikeRadioList(i, i2).enqueue(responseCallBack);
    }

    public static void getAudioBookBannerList(ResponseCallBack<GetMusicBannerListResponse> responseCallBack) {
        getWanOSRetrofitService().getAudioBookBannerList().enqueue(responseCallBack);
    }

    public static void audioBookLikeAlbum(long j, int i, ResponseCallBack<AudioBookLikeRadioResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put("radioId", Long.valueOf(j));
        map.put("status", Integer.valueOf(i));
        getWanOSRetrofitService().audioBookLikeRadio(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static void getAudioBookLikeChapterList(int i, int i2, ResponseCallBack<GetAudioBookLikeChapterListResponse> responseCallBack) {
        getWanOSRetrofitService().getAudioBookLikeChapterList(i, i2).enqueue(responseCallBack);
    }

    public static void audioBookLikeChapter(long j, int i, ResponseCallBack<AudioBookLikeChapterResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put("chapterId", Long.valueOf(j));
        map.put("status", Integer.valueOf(i));
        getWanOSRetrofitService().audioBookLikeChapter(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static void audioBookChapterListIsLike(List<Long> list, ResponseCallBack<GetAudioBookChapterListIsLikeResponse> responseCallBack) {
        getWanOSRetrofitService().audioBookChapterListIsLike(list).enqueue(responseCallBack);
    }

    public static void getServiceProtocol(ResponseCallBack<GetServiceProtocolResponse> responseCallBack, int i, int i2) {
        HashMap map = new HashMap();
        map.put(ClientCookie.VERSION_ATTR, Integer.valueOf(i));
        map.put("ptype", Integer.valueOf(i2));
        getWanOSRetrofitService().getServiceProtocol(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static void getServerStat(ResponseCallBack<GetServiceStatResponse> responseCallBack) {
        getWanOSRetrofitService().getServiceStat().enqueue(responseCallBack);
    }

    public static void saveRecordMediaMusic(long j, String str, int i, ResponseCallBack<GetMusicStatistic> responseCallBack) {
        HashMap map = new HashMap();
        map.put(MusicPlayActivity.MUSIC_ID_KEY, Long.valueOf(j));
        map.put("externalUid", str);
        map.put("source", Integer.valueOf(i));
        mapToJsonRequestBody(map);
    }

    public static void saveRecordMediaGroup(long j, String str, ResponseCallBack<GetMusicStatistic> responseCallBack) {
        HashMap map = new HashMap();
        map.put("mediaGroupId", Long.valueOf(j));
        map.put("externalUid", str);
        mapToJsonRequestBody(map);
    }

    public static void saveRecordDevice(String str, ResponseCallBack<DeviceInfoResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put("externalUid", str);
        mapToJsonRequestBody(map);
    }

    public static void saveRecordAudioBookChapter(long j, String str, int i, ResponseCallBack<GetAudioBookStatistic> responseCallBack) {
        HashMap map = new HashMap();
        map.put("audioBookChapterId", Long.valueOf(j));
        map.put("externalUid", str);
        map.put("source", Integer.valueOf(i));
        mapToJsonRequestBody(map);
    }

    public static void saveRecordAudioBookAlbum(long j, String str, ResponseCallBack<GetAudioBookStatistic> responseCallBack) {
        HashMap map = new HashMap();
        map.put("audioBookAlbumId", Long.valueOf(j));
        map.put("externalUid", str);
        mapToJsonRequestBody(map);
    }

    public static void saveCcData(String str, String str2, ResponseCallBack<SaveCcDataResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put("cc", str);
        map.put("content", str2);
        getWanOSRetrofitService().saveCcData(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static void getCcData(String str, int i, int i2, ResponseCallBack<GetCcDataResponse> responseCallBack) {
        getWanOSRetrofitService().getCcData(str, i, i2).enqueue(responseCallBack);
    }

    public static void saveVipPrice(int i, String str, int i2, ResponseCallBack<GetVipPriceStatistic> responseCallBack) {
        HashMap map = new HashMap();
        map.put("vipPriceId", Integer.valueOf(i));
        map.put("externalUid", str);
        map.put("source", Integer.valueOf(i2));
        getWanOSRetrofitService().postVipPriceStatistic(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static void saveRecordWidgetMeida(int i, int i2, long j, ResponseCallBack<BaseResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put("adSpace", Integer.valueOf(i));
        if (i2 == 0) {
            map.put(MusicPlayActivity.MUSIC_ID_KEY, Long.valueOf(j));
        } else if (i2 == 1) {
            map.put("musicGroupId", Long.valueOf(j));
        } else if (i2 == 2) {
            map.put("audioBookAlbumId", Long.valueOf(j));
        } else if (i2 == 3) {
            map.put("audioBookChapterId", Long.valueOf(j));
        }
        getWanOSRetrofitService().postOutWidgetStatistic(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static void activeCodeExchange(String str, ResponseCallBack<ActiveCodeExchangeVipMemberResp> responseCallBack) {
        HashMap map = new HashMap();
        map.put("activeCode", str);
        getWanOSRetrofitService().activeCodeExchange(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static Call<GetAdDialogInfoResponse> getAdDialogInfo(int i, boolean z, ResponseCallBack<GetAdDialogInfoResponse> responseCallBack) {
        Call<GetAdDialogInfoResponse> adDialogInfo = getWanOSRetrofitService().getAdDialogInfo(i, z);
        adDialogInfo.enqueue(responseCallBack);
        return adDialogInfo;
    }

    public static void giveVipMember(int i, ResponseCallBack<GetGiveVipMemberResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put("activityConfigId", Integer.valueOf(i));
        getWanOSRetrofitService().giveVipMember(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static void getZeroTable(ResponseCallBack<GetZeroAudioResponse> responseCallBack) {
        getWanOSRetrofitService().getZeroAudioTable().enqueue(responseCallBack);
    }

    public static void getZeroAudioInfoData(ResponseCallBack<BaseResponse2<GetAudioInfoResp>> responseCallBack) {
        getWanOSRetrofitService().getZeroAudioInfoData().enqueue(responseCallBack);
    }

    public static void getZeroTableInfo(int i, ResponseCallBack<GetZeroAudioInfoResponse> responseCallBack) {
        getWanOSRetrofitService().getZeroAudioTableInfo(i).enqueue(responseCallBack);
    }

    public static void saveAsUserTheme(long j, String str, String str2, String str3, ResponseCallBack<BaseResponse2<CreateOrCollectUserThemeReply>> responseCallBack) {
        HashMap map = new HashMap();
        map.put("bindId", Long.valueOf(j));
        map.put("themeName", str);
        map.put("coverImg", str2);
        map.put("audioConfig", str3);
        getWanOSRetrofitService().saveAsTheme(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static void collectUserTheme(long j, ResponseCallBack<BaseResponse2<CreateOrCollectUserThemeReply>> responseCallBack) {
        HashMap map = new HashMap();
        map.put("bindId", Long.valueOf(j));
        getWanOSRetrofitService().collectTheme(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static void cancelCollectTheme(long j, ResponseCallBack<BaseResponse2> responseCallBack) {
        HashMap map = new HashMap();
        map.put("bindId", Long.valueOf(j));
        getWanOSRetrofitService().cancelCollectAudio(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static void deleteTheme(long j, ResponseCallBack<BaseResponse2> responseCallBack) {
        HashMap map = new HashMap();
        map.put(SendShareCodeViewModel.KEY_THEME_ID, Long.valueOf(j));
        getWanOSRetrofitService().deleteTheme(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static void setTopTheme(long j, ResponseCallBack<BaseResponse2> responseCallBack) {
        HashMap map = new HashMap();
        map.put(SendShareCodeViewModel.KEY_THEME_ID, Long.valueOf(j));
        map.put("power", 1);
        getWanOSRetrofitService().updateTheme(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static void getCollectCoveImages(ResponseCallBack<CollectCoveResponse> responseCallBack) {
        getWanOSRetrofitService().getCollectCoveLis().enqueue(responseCallBack);
    }

    public static void getBellList(ResponseCallBack<BaseResponse2<BellListResponse>> responseCallBack) {
        getWanOSRetrofitService().getBellList().enqueue(responseCallBack);
    }

    public static void getZeroThemeList(int i, int i2, int i3, ResponseCallBack<ZeroThemeListResponse> responseCallBack) {
        getWanOSRetrofitService().getZeroThemeList(i, i2, i3).enqueue(responseCallBack);
    }

    public static void getZeroUserThemeList(int i, int i2, ResponseCallBack<ZeroUserThemeListResponse> responseCallBack) {
        getWanOSRetrofitService().getZeroUserThemeList(i, i2).enqueue(responseCallBack);
    }

    public static void addWorkForShare(String str, ResponseCallBack<AddWorkForShareCodeResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put("shareCode", str);
        getWanOSRetrofitService().addWorkFromShareCode(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static void getShareCode(String str, ResponseCallBack<GetShareCodeResponse> responseCallBack) {
        getWanOSRetrofitService().getShareCode(str).enqueue(responseCallBack);
    }

    public static DeviceTokenResponse initDeviceToken(String str) throws IOException {
        HashMap map = new HashMap();
        map.put("cipherText", str);
        Response<DeviceTokenResponse> responseExecute = getWanOSRetrofitService().initDeviceToken(mapToJsonRequestBody(map)).execute();
        if (responseExecute.code() != 200 && !responseExecute.isSuccessful()) {
            throw new IOException("获取DeviceToken失败:" + responseExecute.code() + "||" + responseExecute.message());
        }
        DeviceTokenResponse deviceTokenResponseBody = responseExecute.body();
        if (deviceTokenResponseBody != null) {
            return deviceTokenResponseBody;
        }
        throw new IOException("获取DeviceToken失败:数据获取失败");
    }

    public static void getThemeInfo(long j, ResponseCallBack<ZeroThemeInfoResponse> responseCallBack) {
        getWanOSRetrofitService().getThemeInfo(j).enqueue(responseCallBack);
    }

    public static void getUserThemeInfo(long j, ResponseCallBack<ZeroThemeInfoResponse> responseCallBack) {
        getWanOSRetrofitService().getUserThemeInfo(j).enqueue(responseCallBack);
    }

    public static void saveUserEventStatistic(String str, String str2, String str3, String str4, String str5, int i, ResponseCallBack<GetUserEventStatistic> responseCallBack) {
        HashMap map = new HashMap();
        map.put("eventCode", str);
        map.put("eventObjectId", str2);
        map.put("productId", str3);
        map.put("extData", str4);
        map.put("externalUid", str5);
        map.put("source", Integer.valueOf(i));
        getWanOSRetrofitService().postUserEventStatistic(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }
}
