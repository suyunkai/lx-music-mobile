package com.wanos.WanosCommunication.service;

import com.wanos.WanosCommunication.BaseResponse;
import com.wanos.WanosCommunication.BaseResponse2;
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
import com.wanos.commonlibrary.utils.AppConstants;
import com.wanos.media.ui.music.activity.MusicPlayActivity;
import com.wanos.media.viewmodel.SendShareCodeViewModel;
import java.util.List;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/* JADX INFO: loaded from: classes3.dex */
public interface WanOSRetrofitService {
    @POST("jili/order/activeCodeExchangeVipMember")
    Call<ActiveCodeExchangeVipMemberResp> activeCodeExchange(@Body RequestBody requestBody);

    @Headers({"baseUrl:zero"})
    @POST("comfortspace/theme/copy")
    Call<AddWorkForShareCodeResponse> addWorkFromShareCode(@Body RequestBody requestBody);

    @GET("jili/radio/LikeMediaIsExist")
    Call<GetAudioBookChapterListIsLikeResponse> audioBookChapterListIsLike(@Query("chapterId") List<Long> list);

    @POST("jili/radio/addLikeMediaRadioChapter")
    Call<AudioBookLikeChapterResponse> audioBookLikeChapter(@Body RequestBody requestBody);

    @POST("jili/radio/addLikeMediaRadio")
    Call<AudioBookLikeRadioResponse> audioBookLikeRadio(@Body RequestBody requestBody);

    @Headers({"baseUrl:zero"})
    @POST("comfortspace/audio/config/change")
    Call<BaseResponse2> cancelCollectAudio(@Body RequestBody requestBody);

    @Headers({"baseUrl:zero"})
    @POST("comfortspace/theme/user/collect")
    Call<BaseResponse2<CreateOrCollectUserThemeReply>> collectTheme(@Body RequestBody requestBody);

    @POST("jili/order/createVipMember")
    Call<CreateVipOrderResponse> createVipOrder(@Body RequestBody requestBody);

    @Headers({"baseUrl:zero"})
    @POST("comfortspace/theme/user/delete")
    Call<BaseResponse2> deleteTheme(@Body RequestBody requestBody);

    @GET("jili/activityJili/getActivityInfo")
    Call<GetAdDialogInfoResponse> getAdDialogInfo(@Query("queryChanType") int i, @Query("isRestart") boolean z);

    @GET("jili/radio/getBannerList")
    Call<GetMusicBannerListResponse> getAudioBookBannerList();

    @GET("jili/radio/chapterDetail")
    Call<GetAudioBookChapterDetailResponse> getAudioBookChapterDetail(@Query("radioChapterId") long j);

    @GET("jili/radio/chapterList")
    Call<GetAudioBookMineChapterListResponse> getAudioBookChapterList(@Query(AppConstants.ACTIVITY_TAB_PAGE) int i, @Query("pageSize") int i2, @Query("radioId") long j);

    @GET("jili/radio/chapterList")
    Call<GetAudioBookChapterListResponse> getAudioBookChapterListV1(@Query(AppConstants.ACTIVITY_TAB_PAGE) int i, @Query("pageSize") int i2, @Query("radioId") long j);

    @GET("jili/radio/getLikeMediaRadioChapterList")
    Call<GetAudioBookLikeChapterListResponse> getAudioBookLikeChapterList(@Query(AppConstants.ACTIVITY_TAB_PAGE) int i, @Query("pageSize") int i2);

    @GET("jili/radio/getLikeMediaRadioList")
    Call<GetAudioBookLikeRadioListResponse> getAudioBookLikeRadioList(@Query(AppConstants.ACTIVITY_TAB_PAGE) int i, @Query("pageSize") int i2);

    @GET("jili/radio/detail")
    Call<GetAudioBookRadioDetailResponse> getAudioBookRadioDetail(@Query("radioId") long j);

    @GET("jili/radio/list")
    Call<GetAudioBookRadioListResponse> getAudioBookRadioList(@Query(AppConstants.ACTIVITY_TAB_PAGE) int i, @Query("pageSize") int i2, @Query("tagId") int i3);

    @GET("jili/radio/recommendList")
    Call<GetAudioBookRecommendListResponse> getAudioBookRecommendList(@Query(AppConstants.ACTIVITY_TAB_PAGE) int i, @Query("pageSize") int i2);

    @Headers({"baseUrl:zero"})
    @GET("comfortspace/bell/list")
    Call<BaseResponse2<BellListResponse>> getBellList();

    @GET("jili/stat/getCcData")
    Call<GetCcDataResponse> getCcData(@Query("cc") String str, @Query(AppConstants.ACTIVITY_TAB_PAGE) int i, @Query("pageSize") int i2);

    @Headers({"baseUrl:zero"})
    @GET("comfortspace/coverImg/list")
    Call<CollectCoveResponse> getCollectCoveLis();

    @GET("jili/music/getMusicCollectList")
    Call<GetMusicListResponse> getCollectMusicList(@Query(AppConstants.ACTIVITY_TAB_PAGE) int i, @Query("pageSize") int i2);

    @GET("commonmedia/feedback/h5Url")
    Call<GetH5UrlResponse> getFeedBackH5Url(@Query("env") String str);

    @GET("jili/music/getRecommendMusicList")
    Call<GetMusicListResponse> getHotMusicList(@Query(AppConstants.ACTIVITY_TAB_PAGE) int i, @Query("pageSize") int i2);

    @Headers({"baseUrl:search"})
    @GET("mediasearch/text/hotrecommendword")
    Call<RecommendWordsResponse> getHotRecommendWords();

    @GET("jili/freeGroup/detail")
    Call<GetFreeMusicGroupCoverResponse> getLimitedFreeMusicGroupCover();

    @GET("jili/freeGroup/musicList")
    Call<GetMusicListResponse> getLimitedFreeMusicList(@Query(AppConstants.ACTIVITY_TAB_PAGE) int i, @Query("pageSize") int i2, @Query("limit_page") boolean z);

    @POST("jili/music/getMediaFreeStatusByIds")
    Call<MusicVipAndLimitedFreeStatuListResponse> getMediaFreeStatusByIds(@Body RequestBody requestBody);

    @GET("jili/pageModule/coverDetail")
    Call<GetModuleCoverDetailsResponse> getModuleCoverDetails(@Query("coverType") int i, @Query("location") String str);

    @GET("jili/h5Page/musicList")
    Call<GetMusicListResponse> getModuleMusicListByGroupId(@Query("moduleId") long j, @Query(AppConstants.ACTIVITY_TAB_PAGE) int i, @Query("pageSize") int i2);

    @GET("jili/music/getOperateADList")
    Call<GetMusicBannerListResponse> getMusicBannerListData();

    @POST("jili/music/getMusicCollectStatusByIds")
    Call<MusicCollectStatuListResponse> getMusicCollectStatusByIds(@Body RequestBody requestBody);

    @GET("jili/music/getMusicGroupCollectList")
    Call<GetMusicGroupListResponse> getMusicGroupCollectList(@Query(AppConstants.ACTIVITY_TAB_PAGE) int i, @Query("pageSize") int i2);

    @GET("jili/music/getMusicGroupDetail")
    Call<GetMusicGroupDetailResponse> getMusicGroupDetail(@Query("musicGroupId") long j, @Query(AppConstants.ACTIVITY_TAB_PAGE) int i, @Query("pageSize") int i2);

    @GET("jili/music/getMusicGroupList")
    Call<GetMusicGroupListResponse> getMusicGroupList(@Query(AppConstants.ACTIVITY_TAB_PAGE) int i, @Query("pageSize") int i2);

    @GET("jili/music/getMusicList")
    Call<GetMusicListResponse> getMusicList(@Query(AppConstants.ACTIVITY_TAB_PAGE) int i, @Query("pageSize") int i2);

    @GET("jili/music/getMusicDetail")
    Call<GetMusicDetailResponse> getMusickDetail(@Query(MusicPlayActivity.MUSIC_ID_KEY) long j);

    @GET("jili/order/getOrderVipMemberDetail")
    Call<GetOrderInfoByOrderNoResponse> getOrderDetail(@Query("orderNo") String str);

    @GET("jili/order/getOrderVipMemberList")
    Call<GetOrderInfoListResponse> getOrderInfoList(@Query(AppConstants.ACTIVITY_TAB_PAGE) String str, @Query("pageSize") String str2);

    @GET("commonmedia/feedback/qaList")
    Call<GetQAListResponse> getQAList(@Query(AppConstants.ACTIVITY_TAB_PAGE) String str, @Query("pageSize") String str2);

    @GET("jili/music/getMusicRankList")
    Call<GetMusicListResponse> getRankMusicList(@Query(AppConstants.ACTIVITY_TAB_PAGE) int i, @Query("pageSize") int i2);

    @GET("jili/music/getMusicList")
    Call<GetMusicListResponse> getRankMusicListDetails(@Query(AppConstants.ACTIVITY_TAB_PAGE) int i, @Query("pageSize") int i2);

    @GET("jili/music/getRecommendMusicGroupList")
    Call<GetMusicGroupListResponse> getRecommendMusicGroupList(@Query(AppConstants.ACTIVITY_TAB_PAGE) int i, @Query("pageSize") int i2);

    @Headers({"baseUrl:search"})
    @GET("mediasearch/text/audiobookalbumsearch")
    Call<GetAudioBookAlbumResponse> getSearchAudioBookList(@Query("searchKeyword") String str, @Query(AppConstants.ACTIVITY_TAB_PAGE) String str2, @Query("pageSize") String str3);

    @Headers({"baseUrl:search"})
    @GET("mediasearch/text/musicgroupsearch")
    Call<GetMusicGroupListResponse> getSearchMusicGroupList(@Query("searchKeyword") String str, @Query(AppConstants.ACTIVITY_TAB_PAGE) String str2, @Query("pageSize") String str3);

    @Headers({"baseUrl:search"})
    @GET("mediasearch/text/musicsearch")
    Call<GetMusicListResponse> getSearchMusicList(@Query("searchKeyword") String str, @Query(AppConstants.ACTIVITY_TAB_PAGE) String str2, @Query("pageSize") String str3);

    @POST("jili/stat/getUserServiceProtocol")
    Call<GetServiceProtocolResponse> getServiceProtocol(@Body RequestBody requestBody);

    @GET("jili/stat/getServerStat")
    Call<GetServiceStatResponse> getServiceStat();

    @Headers({"baseUrl:zero"})
    @GET("comfortspace/theme/shareCode/get")
    Call<GetShareCodeResponse> getShareCode(@Query(SendShareCodeViewModel.KEY_THEME_ID) String str);

    @Headers({"baseUrl:zero"})
    @GET("comfortspace/theme/model/get")
    Call<ZeroThemeInfoResponse> getThemeInfo(@Query(SendShareCodeViewModel.KEY_THEME_ID) long j);

    @POST("jili/user/getUserAvatarList")
    Call<UserAvatarListResponse> getUserAvatarList(@Body RequestBody requestBody);

    @POST("jili/user/getUserinfo")
    Call<GetUserInfoResponse> getUserInfo(@Body RequestBody requestBody);

    @Headers({"baseUrl:zero"})
    @GET("comfortspace/theme/user/get")
    Call<ZeroThemeInfoResponse> getUserThemeInfo(@Query("themeID") long j);

    @POST("jili/user/getVerifyCode")
    Call<BaseResponse> getVerifyCode(@Body RequestBody requestBody);

    @POST("jili/media/api/media/wanos_detail")
    Call<GetVideoInfoResponse> getVideoInfo(@Body RequestBody requestBody);

    @GET("jili/order/getVipMemberGoodsList")
    Call<GetVipGoodsListResponse> getVipGoodsList();

    @POST("jili/user/getWechatQrCode")
    Call<GetWeChatQrCodeResponse> getWeChatQrCode(@Body RequestBody requestBody);

    @POST("jili/user/wechatLogin")
    Call<GetUserInfoResponse> getWeChatUserInfo(@Body RequestBody requestBody);

    @Headers({"baseUrl:zero"})
    @GET("comfortspace/audio/info/get")
    Call<BaseResponse2<GetAudioInfoResp>> getZeroAudioInfoData();

    @Headers({"baseUrl:zero"})
    @GET("comfortspace/audio/tag/get")
    Call<GetZeroAudioResponse> getZeroAudioTable();

    @Headers({"baseUrl:zero"})
    @GET("comfortspace/audio/info/get")
    Call<GetZeroAudioInfoResponse> getZeroAudioTableInfo(@Query("tagId") int i);

    @Headers({"baseUrl:zero"})
    @GET("comfortspace/theme/model/list")
    Call<ZeroThemeListResponse> getZeroThemeList(@Query("label") int i, @Query(AppConstants.ACTIVITY_TAB_PAGE) int i2, @Query("pageSize") int i3);

    @Headers({"baseUrl:zero"})
    @GET("comfortspace/theme/user/list")
    Call<ZeroUserThemeListResponse> getZeroUserThemeList(@Query(AppConstants.ACTIVITY_TAB_PAGE) int i, @Query("pageSize") int i2);

    @POST("jili/giveVip/giveUse")
    Call<GetGiveVipMemberResponse> giveVipMember(@Body RequestBody requestBody);

    @Headers({"baseUrl:login"})
    @POST("wanosuser/api/commonUser/deviceLogin")
    Call<DeviceTokenResponse> initDeviceToken(@Body RequestBody requestBody);

    @POST("jili/user/logout")
    Call<BaseResponse> logout(@Body RequestBody requestBody);

    @POST("jili/user/mobileLogin")
    Call<GetUserInfoResponse> mobileCodeLogin(@Body RequestBody requestBody);

    @POST("jili/music/musicCollect")
    Call<BaseResponse> musicCollect(@Body RequestBody requestBody);

    @POST("jili/music/musicCollectCancel")
    Call<BaseResponse> musicCollectCancel(@Body RequestBody requestBody);

    @POST("jili/music/musicGroupCollect")
    Call<BaseResponse> musicGroupCollect(@Body RequestBody requestBody);

    @POST("jili/music/musicGroupCollectCancel")
    Call<BaseResponse> musicGroupCollectCancel(@Body RequestBody requestBody);

    @POST("jili/order/vipMember")
    Call<PayVipMemberResponse> payVipMember(@Body RequestBody requestBody);

    @Headers({"baseUrl:useraction"})
    @POST("useraction/dataReport/audioBookAlbum/save")
    Call<GetAudioBookStatistic> postAudioBookAlbumStatistic(@Body RequestBody requestBody);

    @Headers({"baseUrl:useraction"})
    @POST("useraction/dataReport/audioBookChapter/save")
    Call<GetAudioBookStatistic> postAudioBookChaterStatistic(@Body RequestBody requestBody);

    @Headers({"baseUrl:useraction"})
    @POST("useraction/dataReport/recordDevice/save")
    Call<DeviceInfoResponse> postDeviceStatistic(@Body RequestBody requestBody);

    @Headers({"baseUrl:useraction"})
    @POST("useraction/dataReport/mediaGroup/save")
    Call<GetMusicStatistic> postMusicGroupStatistic(@Body RequestBody requestBody);

    @Headers({"baseUrl:useraction"})
    @POST("useraction/dataReport/mediaMusic/save")
    Call<GetMusicStatistic> postMusicStatistic(@Body RequestBody requestBody);

    @Headers({"baseUrl:useraction"})
    @POST("useraction/dataReport/outWidget/save")
    Call<BaseResponse> postOutWidgetStatistic(@Body RequestBody requestBody);

    @Headers({"baseUrl:useraction"})
    @POST("useraction/dataReport/userEventLog/save")
    Call<GetUserEventStatistic> postUserEventStatistic(@Body RequestBody requestBody);

    @Headers({"baseUrl:useraction"})
    @POST("useraction/dataReport/vipPrice/save")
    Call<GetVipPriceStatistic> postVipPriceStatistic(@Body RequestBody requestBody);

    @Headers({"baseUrl:zero"})
    @POST("comfortspace/theme/user/create")
    Call<BaseResponse2<CreateOrCollectUserThemeReply>> saveAsTheme(@Body RequestBody requestBody);

    @POST("jili/stat/saveCcData")
    Call<SaveCcDataResponse> saveCcData(@Body RequestBody requestBody);

    @POST("jili/user/updateUserAvatar")
    Call<BaseResponse> updateAvatar(@Body RequestBody requestBody);

    @Headers({"baseUrl:zero"})
    @POST("comfortspace/theme/user/update")
    Call<BaseResponse2> updateTheme(@Body RequestBody requestBody);

    @POST("jili/user/updateUserinfo")
    Call<BaseResponse> updateUserinfo(@Body RequestBody requestBody);

    @POST("jili/user/wechatBindMobile")
    Call<GetUserInfoResponse> wechatBindMobile(@Body RequestBody requestBody);
}
