package com.arcvideo.ivi.res.sdk;

import com.arcvideo.ivi.res.sdk.data.AuthInfo;
import com.arcvideo.ivi.res.sdk.data.CreateTokenResponse;
import com.arcvideo.ivi.res.sdk.data.RegisterInfo;
import com.arcvideo.ivi.res.sdk.data.ResultInfo;
import com.arcvideo.ivi.res.sdk.data.SuggestInfo;
import com.arcvideo.ivi.res.sdk.data.VideoInfo;
import com.arcvideo.ivi.res.sdk.data.VideoSearchBean;
import java.util.List;
import kotlin.Deprecated;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import oooOOO00.oooOO0oo;

/* JADX INFO: loaded from: classes.dex */
public abstract class ArcResSdk {
    public static final Companion Companion = new Companion(null);
    private static ArcResSdk sInstance;

    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final synchronized ArcResSdk getInstance() {
            ArcResSdk arcResSdk;
            if (ArcResSdk.sInstance == null) {
                ArcResSdk.sInstance = new oooOO0oo();
            }
            arcResSdk = ArcResSdk.sInstance;
            Intrinsics.checkNotNull(arcResSdk);
            return arcResSdk;
        }
    }

    public interface OnResultListener<T> {
        void onError(int i, String str);

        void onResult(T t);
    }

    @JvmStatic
    public static final synchronized ArcResSdk getInstance() {
        return Companion.getInstance();
    }

    @Deprecated(message = "Deprecated function, please use activateLicense（2.1 version） ")
    public abstract void activate(String str, String str2, String str3, String str4, String str5, OnResultListener<Boolean> onResultListener);

    public abstract void activateLicense(String str, String str2, String str3, String str4, String str5, OnResultListener<Boolean> onResultListener);

    public abstract void addInterceptor(Interceptor interceptor);

    public abstract void createToken(String str, String str2, OnResultListener<CreateTokenResponse.TokenInfo> onResultListener);

    public abstract void getArcBannerList(String str, String str2, int i, OnResultListener<List<VideoInfo>> onResultListener);

    public abstract String getAuthorization();

    public abstract void getCarLicenseInfo(String str, String str2, String str3, String str4, String str5, OnResultListener<AuthInfo> onResultListener);

    public abstract void getDetail(String str, OnResultListener<VideoInfo> onResultListener);

    public abstract void getDolby(int i, int i2, int i3, OnResultListener<ResultInfo> onResultListener);

    public abstract void getDolby(int i, OnResultListener<ResultInfo> onResultListener);

    public abstract void getDolby(OnResultListener<ResultInfo> onResultListener);

    public abstract void getEpisodes(String str, int i, int i2, OnResultListener<ResultInfo> onResultListener);

    public abstract void getEpisodes(String str, OnResultListener<ResultInfo> onResultListener);

    public abstract void getHotWord(OnResultListener<List<SuggestInfo>> onResultListener);

    public abstract void getHotWord(OnResultListener<List<SuggestInfo>> onResultListener, int i);

    public abstract void getIntentSearchList(String str, int i, int i2, int i3, OnResultListener<List<VideoInfo>> onResultListener);

    public abstract void getRank(int i, int i2, int i3, OnResultListener<List<VideoInfo>> onResultListener);

    public abstract void getRank(int i, int i2, int i3, boolean z, int i4, OnResultListener<List<VideoInfo>> onResultListener);

    public abstract void getRank(int i, int i2, int i3, boolean z, OnResultListener<List<VideoInfo>> onResultListener);

    public abstract void getRank(int i, OnResultListener<List<VideoInfo>> onResultListener);

    public abstract void getRank(OnResultListener<List<VideoInfo>> onResultListener);

    public abstract void getRecommend(String str, int i, int i2, OnResultListener<List<VideoInfo>> onResultListener);

    public abstract void getRecommend(String str, OnResultListener<List<VideoInfo>> onResultListener);

    public abstract void getRelevantRecommend(String str, OnResultListener<List<VideoInfo>> onResultListener);

    public abstract void getRelevantRecommendV2(String str, String str2, int i, OnResultListener<List<VideoInfo>> onResultListener);

    public abstract void getResource(String str, OnResultListener<List<VideoInfo>> onResultListener);

    public abstract void getResource(String str, Integer num, Integer num2, OnResultListener<List<VideoInfo>> onResultListener);

    public abstract String getSdkVersion();

    public abstract void getSuggest(String str, OnResultListener<List<SuggestInfo>> onResultListener);

    public abstract void getVideoByTag(int i, String str, String str2, int i2, int i3, String str3, String str4, Integer num, OnResultListener<ResultInfo> onResultListener);

    public abstract void getVideoLib(int i, String str, int i2, int i3, OnResultListener<ResultInfo> onResultListener);

    public abstract void getVideoLib(int i, String str, int i2, int i3, String str2, OnResultListener<ResultInfo> onResultListener);

    public abstract void getVideoLib(int i, String str, int i2, int i3, String str2, String str3, String str4, Integer num, String str5, Integer num2, OnResultListener<ResultInfo> onResultListener);

    public abstract void getVideoLib(int i, String str, OnResultListener<ResultInfo> onResultListener);

    public abstract void getVideoLib(int i, String str, String str2, OnResultListener<ResultInfo> onResultListener);

    public abstract void register(String str, String str2, long j, OnResultListener<RegisterInfo> onResultListener);

    public abstract void register(String str, String str2, long j, String str3, OnResultListener<RegisterInfo> onResultListener);

    public abstract void register(String str, String str2, long j, String str3, String str4, OnResultListener<RegisterInfo> onResultListener);

    public abstract void register(String str, String str2, long j, String str3, String str4, String str5, OnResultListener<RegisterInfo> onResultListener);

    public abstract boolean registerSync(String str, String str2, long j, String str3, String str4, String str5);

    public abstract void search(String str, int i, int i2, OnResultListener<ResultInfo> onResultListener);

    public abstract void search(String str, OnResultListener<ResultInfo> onResultListener);

    public abstract void search(String str, String str2, int i, int i2, OnResultListener<ResultInfo> onResultListener);

    public abstract void sendBugReport(OnResultListener<String> onResultListener);

    public abstract void setArcStg(boolean z);

    public abstract void setArcUserAgent(String str);

    public abstract void setRegisterInfo(String str, String str2);

    public abstract String token();

    public abstract void videoSearch(String str, int i, int i2, int i3, int i4, OnResultListener<VideoSearchBean> onResultListener);

    public abstract void videoSearch(String str, OnResultListener<VideoSearchBean> onResultListener);
}
