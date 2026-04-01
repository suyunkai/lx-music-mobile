package com.arcvideo.ivi.res.sdk.http;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import okhttp3.ConnectionPool;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import oooOO.oooOOO;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/* JADX INFO: loaded from: classes.dex */
public final class RetrofitManager {
    private static final String BASE_URL = "https://sdkcar.iqiyi.com";
    private static final String BUG_REPORT_BASE_URL = "https://oapi.dingtalk.com";
    private static boolean STG_ENABLE;
    public static final RetrofitManager INSTANCE = new RetrofitManager();
    private static long TIME_OUT = 10;
    private static final List<Interceptor> interceptors = new ArrayList();
    private static String arcUserAgent = "";
    private static String vin = "";
    private static String uuid = "";
    private static final Lazy arcResApiService$delegate = LazyKt.lazy(oooOO.f15oooOO0oo);
    private static final Lazy arcResBugApiService$delegate = LazyKt.lazy(oooOOO00.f17oooOO0oo);
    private static final Lazy arcActiveApiService$delegate = LazyKt.lazy(oooOO0oo.f16oooOO0oo);

    public static final class oooOO extends Lambda implements Function0<oooOO.oooOO> {

        /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
        public static final oooOO f15oooOO0oo = new oooOO();

        public oooOO() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
        public final oooOO.oooOO invoke() {
            return (oooOO.oooOO) RetrofitManager.INSTANCE.provideRetrofit().create(oooOO.oooOO.class);
        }
    }

    public static final class oooOO0oo extends Lambda implements Function0<oooOO.oooOO0oo> {

        /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
        public static final oooOO0oo f16oooOO0oo = new oooOO0oo();

        public oooOO0oo() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
        public final oooOO.oooOO0oo invoke() {
            return (oooOO.oooOO0oo) RetrofitManager.INSTANCE.createActiveRetrofit().create(oooOO.oooOO0oo.class);
        }
    }

    public static final class oooOOO00 extends Lambda implements Function0<oooOO.oooOOO00> {

        /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
        public static final oooOOO00 f17oooOO0oo = new oooOOO00();

        public oooOOO00() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
        public final oooOO.oooOOO00 invoke() {
            return (oooOO.oooOOO00) RetrofitManager.INSTANCE.bugRetrofit().create(oooOO.oooOOO00.class);
        }
    }

    private RetrofitManager() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Retrofit bugRetrofit() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(BUG_REPORT_BASE_URL).addCallAdapterFactory(oooOO0oo.oooOO.f854oooOO.oooOO0oo()).addConverterFactory(GsonConverterFactory.create()).client(INSTANCE.provideOkHttpClient(2)).build();
        Retrofit retrofit = builder.build();
        Intrinsics.checkNotNullExpressionValue(retrofit, "retrofit");
        return retrofit;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Retrofit createActiveRetrofit() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(STG_ENABLE ? "http://openapi-stg.danghongyun.com/" : "https://openapi.danghongyun.com").addCallAdapterFactory(oooOO0oo.oooOO.f854oooOO.oooOO0oo()).addConverterFactory(GsonConverterFactory.create()).client(INSTANCE.provideOkHttpClient(3)).build();
        Retrofit retrofit = builder.build();
        Intrinsics.checkNotNullExpressionValue(retrofit, "retrofit");
        return retrofit;
    }

    private final OkHttpClient provideOkHttpClient(int i) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        long j = TIME_OUT;
        TimeUnit timeUnit = TimeUnit.SECONDS;
        builder.connectTimeout(j, timeUnit);
        builder.readTimeout(TIME_OUT, timeUnit);
        builder.writeTimeout(TIME_OUT, timeUnit);
        builder.connectionPool(new ConnectionPool(8, 15L, timeUnit));
        builder.retryOnConnectionFailure(true);
        if (i == 1) {
            builder.addInterceptor(new oooOOO(vin, uuid));
        } else if (i == 3) {
            builder.addInterceptor(new Interceptor() { // from class: com.arcvideo.ivi.res.sdk.http.RetrofitManager$$ExternalSyntheticLambda0
                @Override // okhttp3.Interceptor
                public final Response intercept(Interceptor.Chain chain) {
                    return RetrofitManager.m226provideOkHttpClient$lambda5$lambda3(chain);
                }
            });
        }
        Iterator<T> it = interceptors.iterator();
        while (it.hasNext()) {
            builder.addInterceptor((Interceptor) it.next());
        }
        return builder.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: provideOkHttpClient$lambda-5$lambda-3, reason: not valid java name */
    public static final Response m226provideOkHttpClient$lambda5$lambda3(Interceptor.Chain it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.proceed(it.request().newBuilder().addHeader("User-Agent", arcUserAgent).build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Retrofit provideRetrofit() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(BASE_URL).addCallAdapterFactory(oooOO0oo.oooOO.f854oooOO.oooOO0oo()).addConverterFactory(GsonConverterFactory.create()).client(INSTANCE.provideOkHttpClient(1)).build();
        Retrofit retrofit = builder.build();
        Intrinsics.checkNotNullExpressionValue(retrofit, "retrofit");
        return retrofit;
    }

    public final void addInterceptor(Interceptor interceptor) {
        Intrinsics.checkNotNullParameter(interceptor, "interceptor");
        List<Interceptor> list = interceptors;
        if (list.contains(interceptor)) {
            return;
        }
        list.add(interceptor);
    }

    public final oooOO.oooOO0oo getArcActiveApiService() {
        Object value = arcActiveApiService$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-arcActiveApiService>(...)");
        return (oooOO.oooOO0oo) value;
    }

    public final oooOO.oooOO getArcResApiService() {
        Object value = arcResApiService$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-arcResApiService>(...)");
        return (oooOO.oooOO) value;
    }

    public final oooOO.oooOOO00 getArcResBugApiService() {
        Object value = arcResBugApiService$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-arcResBugApiService>(...)");
        return (oooOO.oooOOO00) value;
    }

    public final boolean getSTG_ENABLE() {
        return STG_ENABLE;
    }

    public final void setArcUserAgent(String arcUserAgent2) {
        Intrinsics.checkNotNullParameter(arcUserAgent2, "arcUserAgent");
        arcUserAgent = arcUserAgent2;
    }

    public final void setRegisterInfo(String vin2, String deviceUUId) {
        Intrinsics.checkNotNullParameter(vin2, "vin");
        Intrinsics.checkNotNullParameter(deviceUUId, "deviceUUId");
        vin = vin2;
        uuid = deviceUUId;
    }

    public final void setSTG_ENABLE(boolean z) {
        STG_ENABLE = z;
    }
}
