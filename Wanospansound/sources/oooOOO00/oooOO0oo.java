package oooOOO00;

import android.text.TextUtils;
import android.util.Log;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import com.arcvideo.ivi.res.sdk.ArcResSdk;
import com.arcvideo.ivi.res.sdk.data.ActivateBean;
import com.arcvideo.ivi.res.sdk.data.ActivateBodyBean;
import com.arcvideo.ivi.res.sdk.data.AuthInfo;
import com.arcvideo.ivi.res.sdk.data.AuthInfoResponse;
import com.arcvideo.ivi.res.sdk.data.BannerResponse;
import com.arcvideo.ivi.res.sdk.data.BaseResponse;
import com.arcvideo.ivi.res.sdk.data.CreateTokenBodyBean;
import com.arcvideo.ivi.res.sdk.data.CreateTokenResponse;
import com.arcvideo.ivi.res.sdk.data.CustomCategory;
import com.arcvideo.ivi.res.sdk.data.ErrorBean;
import com.arcvideo.ivi.res.sdk.data.GraphCategory;
import com.arcvideo.ivi.res.sdk.data.IntentData;
import com.arcvideo.ivi.res.sdk.data.IntentDatas;
import com.arcvideo.ivi.res.sdk.data.IntentFilterBean;
import com.arcvideo.ivi.res.sdk.data.RankRequestBean;
import com.arcvideo.ivi.res.sdk.data.RegisterInfo;
import com.arcvideo.ivi.res.sdk.data.RegisterRequestBean;
import com.arcvideo.ivi.res.sdk.data.ResultInfo;
import com.arcvideo.ivi.res.sdk.data.SuggestInfo;
import com.arcvideo.ivi.res.sdk.data.TermQuery;
import com.arcvideo.ivi.res.sdk.data.VideoInfo;
import com.arcvideo.ivi.res.sdk.data.VideoSearchBean;
import com.arcvideo.ivi.res.sdk.data.VideoSearchResponse;
import com.arcvideo.ivi.res.sdk.http.RetrofitManager;
import com.arcvideo.ivi.res.sdk.innerbean.ChannelResponse;
import com.arcvideo.ivi.res.sdk.innerbean.DetailResponse;
import com.arcvideo.ivi.res.sdk.innerbean.DeviceSession;
import com.arcvideo.ivi.res.sdk.innerbean.DolbyResponse;
import com.arcvideo.ivi.res.sdk.innerbean.EpgResponse;
import com.arcvideo.ivi.res.sdk.innerbean.RecFallResponse;
import com.arcvideo.ivi.res.sdk.innerbean.SystemTime;
import com.arcvideo.ivi.res.sdk.innerbean.VideoResponse;
import com.arcvideo.ivi.res.sdk.utils.ArcSignatureUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wanos.media.ui.advertise.WanosJsBridge;
import java.util.HashMap;
import java.util.List;
import kotlin.Deprecated;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import okhttp3.Interceptor;
import retrofit2.Response;

/* JADX INFO: loaded from: classes3.dex */
public final class oooOO0oo extends ArcResSdk {

    /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
    public final String f865oooOO0oo = "Bearer <token>";

    /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
    public String f864oooOO = WanosJsBridge.METHOD_KEY_TOKEN;

    /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
    public final String f866oooOOO00 = "ArcResSdkImpl";

    @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$activateLicense$1", f = "ArcResSdkImpl.kt", i = {}, l = {663}, m = "invokeSuspend", n = {}, s = {})
    public static final class oooOO extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
        public /* synthetic */ Object f867oooOO;

        /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
        public int f868oooOO0oo;
        public final /* synthetic */ ArcResSdk.OnResultListener<Boolean> oooOOO;

        /* JADX INFO: renamed from: oooOOO0, reason: collision with root package name */
        public final /* synthetic */ String f869oooOOO0;

        /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
        public final /* synthetic */ String f870oooOOO00;
        public final /* synthetic */ String oooOOO0O;
        public final /* synthetic */ ActivateBean oooOOO0o;
        public final /* synthetic */ oooOO0oo oooOOOO0;

        /* JADX INFO: renamed from: oooOOO00.oooOO0oo$oooOO$oooOO, reason: collision with other inner class name */
        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$activateLicense$1$2", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class C0123oooOO extends SuspendLambda implements Function2<BaseResponse<Boolean>, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f871oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f872oooOO0oo;

            /* JADX INFO: renamed from: oooOOO0, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<Boolean> f873oooOOO0;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ CoroutineScope f874oooOOO00;
            public final /* synthetic */ oooOO0oo oooOOO0O;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0123oooOO(CoroutineScope coroutineScope, ArcResSdk.OnResultListener<Boolean> onResultListener, oooOO0oo ooooo0oo, Continuation<? super C0123oooOO> continuation) {
                super(2, continuation);
                this.f874oooOOO00 = coroutineScope;
                this.f873oooOOO0 = onResultListener;
                this.oooOOO0O = ooooo0oo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C0123oooOO c0123oooOO = new C0123oooOO(this.f874oooOOO00, this.f873oooOOO0, this.oooOOO0O, continuation);
                c0123oooOO.f871oooOO = obj;
                return c0123oooOO;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f872oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                BaseResponse baseResponse = (BaseResponse) this.f871oooOO;
                if (baseResponse != null) {
                    ArcResSdk.OnResultListener<Boolean> onResultListener = this.f873oooOOO0;
                    Object success = baseResponse.getSuccess();
                    Intrinsics.checkNotNullExpressionValue(success, "it.success");
                    onResultListener.onResult(success);
                } else {
                    Boxing.boxInt(Log.e(this.oooOOO0O.f866oooOOO00, "activate quest failed: baseResponse is null "));
                }
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(BaseResponse<Boolean> baseResponse, Continuation<? super Unit> continuation) {
                return ((C0123oooOO) create(baseResponse, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX INFO: renamed from: oooOOO00.oooOO0oo$oooOO$oooOO0oo, reason: collision with other inner class name */
        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$activateLicense$1$1", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class C0124oooOO0oo extends SuspendLambda implements Function2<ErrorBean, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f875oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f876oooOO0oo;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<Boolean> f877oooOOO00;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0124oooOO0oo(ArcResSdk.OnResultListener<Boolean> onResultListener, Continuation<? super C0124oooOO0oo> continuation) {
                super(2, continuation);
                this.f877oooOOO00 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C0124oooOO0oo c0124oooOO0oo = new C0124oooOO0oo(this.f877oooOOO00, continuation);
                c0124oooOO0oo.f875oooOO = obj;
                return c0124oooOO0oo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f876oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                ErrorBean errorBean = (ErrorBean) this.f875oooOO;
                if (errorBean != null) {
                    this.f877oooOOO00.onError(Integer.parseInt(errorBean.getCode()), errorBean.getMsg());
                }
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(ErrorBean errorBean, Continuation<? super Unit> continuation) {
                return ((C0124oooOO0oo) create(errorBean, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public oooOO(String str, String str2, String str3, ActivateBean activateBean, ArcResSdk.OnResultListener<Boolean> onResultListener, oooOO0oo ooooo0oo, Continuation<? super oooOO> continuation) {
            super(2, continuation);
            this.f870oooOOO00 = str;
            this.f869oooOOO0 = str2;
            this.oooOOO0O = str3;
            this.oooOOO0o = activateBean;
            this.oooOOO = onResultListener;
            this.oooOOOO0 = ooooo0oo;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            oooOO ooooo = new oooOO(this.f870oooOOO00, this.f869oooOOO0, this.oooOOO0O, this.oooOOO0o, this.oooOOO, this.oooOOOO0, continuation);
            ooooo.f867oooOO = obj;
            return ooooo;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f868oooOO0oo;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.f867oooOO;
                Flow<BaseResponse<Boolean>> flowOooOO0oo = RetrofitManager.INSTANCE.getArcActiveApiService().oooOO0oo(this.f870oooOOO00, this.f869oooOOO0, this.oooOOO0O, this.oooOOO0o);
                C0124oooOO0oo c0124oooOO0oo = new C0124oooOO0oo(this.oooOOO, null);
                C0123oooOO c0123oooOO = new C0123oooOO(coroutineScope, this.oooOOO, this.oooOOOO0, null);
                this.f868oooOO0oo = 1;
                if (oooOO.oooOOO0O.oooOO0oo(flowOooOO0oo, c0124oooOO0oo, c0123oooOO, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((oooOO) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: oooOOO00.oooOO0oo$oooOO0oo, reason: collision with other inner class name */
    @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$activate$1", f = "ArcResSdkImpl.kt", i = {}, l = {623}, m = "invokeSuspend", n = {}, s = {})
    public static final class C0125oooOO0oo extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
        public /* synthetic */ Object f878oooOO;

        /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
        public int f879oooOO0oo;
        public final /* synthetic */ oooOO0oo oooOOO;

        /* JADX INFO: renamed from: oooOOO0, reason: collision with root package name */
        public final /* synthetic */ String f880oooOOO0;

        /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
        public final /* synthetic */ String f881oooOOO00;
        public final /* synthetic */ ActivateBodyBean oooOOO0O;
        public final /* synthetic */ ArcResSdk.OnResultListener<Boolean> oooOOO0o;

        /* JADX INFO: renamed from: oooOOO00.oooOO0oo$oooOO0oo$oooOO */
        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$activate$1$2", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class oooOO extends SuspendLambda implements Function2<BaseResponse<Boolean>, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f882oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f883oooOO0oo;

            /* JADX INFO: renamed from: oooOOO0, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<Boolean> f884oooOOO0;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ CoroutineScope f885oooOOO00;
            public final /* synthetic */ oooOO0oo oooOOO0O;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public oooOO(CoroutineScope coroutineScope, ArcResSdk.OnResultListener<Boolean> onResultListener, oooOO0oo ooooo0oo, Continuation<? super oooOO> continuation) {
                super(2, continuation);
                this.f885oooOOO00 = coroutineScope;
                this.f884oooOOO0 = onResultListener;
                this.oooOOO0O = ooooo0oo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                oooOO ooooo = new oooOO(this.f885oooOOO00, this.f884oooOOO0, this.oooOOO0O, continuation);
                ooooo.f882oooOO = obj;
                return ooooo;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f883oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                BaseResponse baseResponse = (BaseResponse) this.f882oooOO;
                if (baseResponse != null) {
                    ArcResSdk.OnResultListener<Boolean> onResultListener = this.f884oooOOO0;
                    Object success = baseResponse.getSuccess();
                    Intrinsics.checkNotNullExpressionValue(success, "it.success");
                    onResultListener.onResult(success);
                } else {
                    Boxing.boxInt(Log.e(this.oooOOO0O.f866oooOOO00, "activate quest failed: baseResponse is null "));
                }
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(BaseResponse<Boolean> baseResponse, Continuation<? super Unit> continuation) {
                return ((oooOO) create(baseResponse, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX INFO: renamed from: oooOOO00.oooOO0oo$oooOO0oo$oooOO0oo, reason: collision with other inner class name */
        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$activate$1$1", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class C0126oooOO0oo extends SuspendLambda implements Function2<ErrorBean, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f886oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f887oooOO0oo;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<Boolean> f888oooOOO00;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0126oooOO0oo(ArcResSdk.OnResultListener<Boolean> onResultListener, Continuation<? super C0126oooOO0oo> continuation) {
                super(2, continuation);
                this.f888oooOOO00 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C0126oooOO0oo c0126oooOO0oo = new C0126oooOO0oo(this.f888oooOOO00, continuation);
                c0126oooOO0oo.f886oooOO = obj;
                return c0126oooOO0oo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f887oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                ErrorBean errorBean = (ErrorBean) this.f886oooOO;
                if (errorBean != null) {
                    this.f888oooOOO00.onError(Integer.parseInt(errorBean.getCode()), errorBean.getMsg());
                }
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(ErrorBean errorBean, Continuation<? super Unit> continuation) {
                return ((C0126oooOO0oo) create(errorBean, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C0125oooOO0oo(String str, String str2, ActivateBodyBean activateBodyBean, ArcResSdk.OnResultListener<Boolean> onResultListener, oooOO0oo ooooo0oo, Continuation<? super C0125oooOO0oo> continuation) {
            super(2, continuation);
            this.f881oooOOO00 = str;
            this.f880oooOOO0 = str2;
            this.oooOOO0O = activateBodyBean;
            this.oooOOO0o = onResultListener;
            this.oooOOO = ooooo0oo;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C0125oooOO0oo c0125oooOO0oo = new C0125oooOO0oo(this.f881oooOOO00, this.f880oooOOO0, this.oooOOO0O, this.oooOOO0o, this.oooOOO, continuation);
            c0125oooOO0oo.f878oooOO = obj;
            return c0125oooOO0oo;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f879oooOO0oo;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.f878oooOO;
                Flow<BaseResponse<Boolean>> flowOooOO0oo = RetrofitManager.INSTANCE.getArcActiveApiService().oooOO0oo(this.f881oooOOO00, "5wo1db5j81ae", this.f880oooOOO0, this.oooOOO0O);
                C0126oooOO0oo c0126oooOO0oo = new C0126oooOO0oo(this.oooOOO0o, null);
                oooOO ooooo = new oooOO(coroutineScope, this.oooOOO0o, this.oooOOO, null);
                this.f879oooOO0oo = 1;
                if (oooOO.oooOOO0O.oooOO0oo(flowOooOO0oo, c0126oooOO0oo, ooooo, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C0125oooOO0oo) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getDetail$1", f = "ArcResSdkImpl.kt", i = {}, l = {549}, m = "invokeSuspend", n = {}, s = {})
    public static final class oooOOO extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
        public final /* synthetic */ String f889oooOO;

        /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
        public int f890oooOO0oo;

        /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
        public final /* synthetic */ ArcResSdk.OnResultListener<VideoInfo> f891oooOOO00;

        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getDetail$1$2", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class oooOO extends SuspendLambda implements Function2<DetailResponse, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f892oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f893oooOO0oo;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<VideoInfo> f894oooOOO00;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public oooOO(ArcResSdk.OnResultListener<VideoInfo> onResultListener, Continuation<? super oooOO> continuation) {
                super(2, continuation);
                this.f894oooOOO00 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                oooOO ooooo = new oooOO(this.f894oooOOO00, continuation);
                ooooo.f892oooOO = obj;
                return ooooo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f893oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                DetailResponse detailResponse = (DetailResponse) this.f892oooOO;
                this.f894oooOOO00.onResult(oooOOO0.oooOO0oo.f863oooOO0oo.oooOO0oo(detailResponse != null ? detailResponse.getData() : null));
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(DetailResponse detailResponse, Continuation<? super Unit> continuation) {
                return ((oooOO) create(detailResponse, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX INFO: renamed from: oooOOO00.oooOO0oo$oooOOO$oooOO0oo, reason: collision with other inner class name */
        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getDetail$1$1", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class C0127oooOO0oo extends SuspendLambda implements Function2<ErrorBean, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f895oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f896oooOO0oo;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<VideoInfo> f897oooOOO00;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0127oooOO0oo(ArcResSdk.OnResultListener<VideoInfo> onResultListener, Continuation<? super C0127oooOO0oo> continuation) {
                super(2, continuation);
                this.f897oooOOO00 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C0127oooOO0oo c0127oooOO0oo = new C0127oooOO0oo(this.f897oooOOO00, continuation);
                c0127oooOO0oo.f895oooOO = obj;
                return c0127oooOO0oo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f896oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                ErrorBean errorBean = (ErrorBean) this.f895oooOO;
                if (errorBean != null) {
                    this.f897oooOOO00.onError(Integer.parseInt(errorBean.getCode()), errorBean.getMsg());
                }
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(ErrorBean errorBean, Continuation<? super Unit> continuation) {
                return ((C0127oooOO0oo) create(errorBean, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public oooOOO(String str, ArcResSdk.OnResultListener<VideoInfo> onResultListener, Continuation<? super oooOOO> continuation) {
            super(2, continuation);
            this.f889oooOO = str;
            this.f891oooOOO00 = onResultListener;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new oooOOO(this.f889oooOO, this.f891oooOOO00, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f890oooOO0oo;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow<DetailResponse> flowOooOO0oo = RetrofitManager.INSTANCE.getArcResApiService().oooOO0oo(this.f889oooOO);
                C0127oooOO0oo c0127oooOO0oo = new C0127oooOO0oo(this.f891oooOOO00, null);
                oooOO ooooo = new oooOO(this.f891oooOOO00, null);
                this.f890oooOO0oo = 1;
                if (oooOO.oooOOO0O.oooOO0oo(flowOooOO0oo, c0127oooOO0oo, ooooo, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((oooOOO) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getArcBannerList$1", f = "ArcResSdkImpl.kt", i = {}, l = {907}, m = "invokeSuspend", n = {}, s = {})
    public static final class oooOOO0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
        public final /* synthetic */ String f898oooOO;

        /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
        public int f899oooOO0oo;

        /* JADX INFO: renamed from: oooOOO0, reason: collision with root package name */
        public final /* synthetic */ String f900oooOOO0;

        /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
        public final /* synthetic */ String f901oooOOO00;
        public final /* synthetic */ int oooOOO0O;
        public final /* synthetic */ ArcResSdk.OnResultListener<List<VideoInfo>> oooOOO0o;

        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getArcBannerList$1$2", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class oooOO extends SuspendLambda implements Function2<BaseResponse<Boolean>, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f902oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f903oooOO0oo;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<List<VideoInfo>> f904oooOOO00;

            /* JADX INFO: renamed from: oooOOO00.oooOO0oo$oooOOO0$oooOO$oooOO0oo, reason: collision with other inner class name */
            public static final class C0128oooOO0oo extends TypeToken<List<? extends BannerResponse>> {
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public oooOO(ArcResSdk.OnResultListener<List<VideoInfo>> onResultListener, Continuation<? super oooOO> continuation) {
                super(2, continuation);
                this.f904oooOOO00 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                oooOO ooooo = new oooOO(this.f904oooOOO00, continuation);
                ooooo.f902oooOO = obj;
                return ooooo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                String result;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f903oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                BaseResponse baseResponse = (BaseResponse) this.f902oooOO;
                boolean z = false;
                if (baseResponse != null && (result = baseResponse.getResult()) != null) {
                    if (result.length() > 0) {
                        z = true;
                    }
                }
                if (z) {
                    try {
                        this.f904oooOOO00.onResult(oooOOO0.oooOO0oo.f863oooOO0oo.oooOO0oo((List<BannerResponse>) new Gson().fromJson(baseResponse.getResult(), new C0128oooOO0oo().getType())));
                    } catch (Throwable unused) {
                        this.f904oooOOO00.onError(baseResponse.getCode(), baseResponse.getMessage());
                    }
                } else {
                    this.f904oooOOO00.onError(baseResponse != null ? baseResponse.getCode() : Integer.parseInt("-1"), baseResponse != null ? baseResponse.getMessage() : null);
                }
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(BaseResponse<Boolean> baseResponse, Continuation<? super Unit> continuation) {
                return ((oooOO) create(baseResponse, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX INFO: renamed from: oooOOO00.oooOO0oo$oooOOO0$oooOO0oo, reason: collision with other inner class name */
        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getArcBannerList$1$1", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class C0129oooOO0oo extends SuspendLambda implements Function2<ErrorBean, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f905oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f906oooOO0oo;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<List<VideoInfo>> f907oooOOO00;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0129oooOO0oo(ArcResSdk.OnResultListener<List<VideoInfo>> onResultListener, Continuation<? super C0129oooOO0oo> continuation) {
                super(2, continuation);
                this.f907oooOOO00 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C0129oooOO0oo c0129oooOO0oo = new C0129oooOO0oo(this.f907oooOOO00, continuation);
                c0129oooOO0oo.f905oooOO = obj;
                return c0129oooOO0oo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f906oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                ErrorBean errorBean = (ErrorBean) this.f905oooOO;
                if (errorBean != null) {
                    this.f907oooOOO00.onError(Integer.parseInt(errorBean.getCode()), errorBean.getMsg());
                }
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(ErrorBean errorBean, Continuation<? super Unit> continuation) {
                return ((C0129oooOO0oo) create(errorBean, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public oooOOO0(String str, String str2, String str3, int i, ArcResSdk.OnResultListener<List<VideoInfo>> onResultListener, Continuation<? super oooOOO0> continuation) {
            super(2, continuation);
            this.f898oooOO = str;
            this.f901oooOOO00 = str2;
            this.f900oooOOO0 = str3;
            this.oooOOO0O = i;
            this.oooOOO0o = onResultListener;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new oooOOO0(this.f898oooOO, this.f901oooOOO00, this.f900oooOOO0, this.oooOOO0O, this.oooOOO0o, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f899oooOO0oo;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow<BaseResponse<Boolean>> flowOooOO0oo = RetrofitManager.INSTANCE.getArcActiveApiService().oooOO0oo(this.f898oooOO, this.f901oooOOO00, this.f900oooOOO0, this.oooOOO0O);
                C0129oooOO0oo c0129oooOO0oo = new C0129oooOO0oo(this.oooOOO0o, null);
                oooOO ooooo = new oooOO(this.oooOOO0o, null);
                this.f899oooOO0oo = 1;
                if (oooOO.oooOOO0O.oooOO0oo(flowOooOO0oo, c0129oooOO0oo, ooooo, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((oooOOO0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$createToken$1", f = "ArcResSdkImpl.kt", i = {}, l = {876}, m = "invokeSuspend", n = {}, s = {})
    public static final class oooOOO00 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
        public /* synthetic */ Object f908oooOO;

        /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
        public int f909oooOO0oo;
        public final /* synthetic */ ArcResSdk.OnResultListener<CreateTokenResponse.TokenInfo> oooOOO;

        /* JADX INFO: renamed from: oooOOO0, reason: collision with root package name */
        public final /* synthetic */ String f910oooOOO0;

        /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
        public final /* synthetic */ String f911oooOOO00;
        public final /* synthetic */ String oooOOO0O;
        public final /* synthetic */ CreateTokenBodyBean oooOOO0o;
        public final /* synthetic */ oooOO0oo oooOOOO0;

        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$createToken$1$2", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class oooOO extends SuspendLambda implements Function2<CreateTokenResponse<Boolean>, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f912oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f913oooOO0oo;

            /* JADX INFO: renamed from: oooOOO0, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<CreateTokenResponse.TokenInfo> f914oooOOO0;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ CoroutineScope f915oooOOO00;
            public final /* synthetic */ oooOO0oo oooOOO0O;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public oooOO(CoroutineScope coroutineScope, ArcResSdk.OnResultListener<CreateTokenResponse.TokenInfo> onResultListener, oooOO0oo ooooo0oo, Continuation<? super oooOO> continuation) {
                super(2, continuation);
                this.f915oooOOO00 = coroutineScope;
                this.f914oooOOO0 = onResultListener;
                this.oooOOO0O = ooooo0oo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                oooOO ooooo = new oooOO(this.f915oooOOO00, this.f914oooOOO0, this.oooOOO0O, continuation);
                ooooo.f912oooOO = obj;
                return ooooo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                CreateTokenResponse.TokenInfo result;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f913oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                CreateTokenResponse createTokenResponse = (CreateTokenResponse) this.f912oooOO;
                if (createTokenResponse == null || (result = createTokenResponse.getResult()) == null) {
                    Boxing.boxInt(Log.e(this.oooOOO0O.f866oooOOO00, "getToken quest failed: baseResponse is null "));
                } else {
                    this.f914oooOOO0.onResult(result);
                }
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(CreateTokenResponse<Boolean> createTokenResponse, Continuation<? super Unit> continuation) {
                return ((oooOO) create(createTokenResponse, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX INFO: renamed from: oooOOO00.oooOO0oo$oooOOO00$oooOO0oo, reason: collision with other inner class name */
        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$createToken$1$1", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class C0130oooOO0oo extends SuspendLambda implements Function2<ErrorBean, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f916oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f917oooOO0oo;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<CreateTokenResponse.TokenInfo> f918oooOOO00;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0130oooOO0oo(ArcResSdk.OnResultListener<CreateTokenResponse.TokenInfo> onResultListener, Continuation<? super C0130oooOO0oo> continuation) {
                super(2, continuation);
                this.f918oooOOO00 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C0130oooOO0oo c0130oooOO0oo = new C0130oooOO0oo(this.f918oooOOO00, continuation);
                c0130oooOO0oo.f916oooOO = obj;
                return c0130oooOO0oo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f917oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                ErrorBean errorBean = (ErrorBean) this.f916oooOO;
                if (errorBean != null) {
                    this.f918oooOOO00.onError(Integer.parseInt(errorBean.getCode()), errorBean.getMsg());
                }
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(ErrorBean errorBean, Continuation<? super Unit> continuation) {
                return ((C0130oooOO0oo) create(errorBean, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public oooOOO00(String str, String str2, String str3, CreateTokenBodyBean createTokenBodyBean, ArcResSdk.OnResultListener<CreateTokenResponse.TokenInfo> onResultListener, oooOO0oo ooooo0oo, Continuation<? super oooOOO00> continuation) {
            super(2, continuation);
            this.f911oooOOO00 = str;
            this.f910oooOOO0 = str2;
            this.oooOOO0O = str3;
            this.oooOOO0o = createTokenBodyBean;
            this.oooOOO = onResultListener;
            this.oooOOOO0 = ooooo0oo;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            oooOOO00 oooooo00 = new oooOOO00(this.f911oooOOO00, this.f910oooOOO0, this.oooOOO0O, this.oooOOO0o, this.oooOOO, this.oooOOOO0, continuation);
            oooooo00.f908oooOO = obj;
            return oooooo00;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f909oooOO0oo;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.f908oooOO;
                Flow<CreateTokenResponse<Boolean>> flowOooOO0oo = RetrofitManager.INSTANCE.getArcActiveApiService().oooOO0oo(this.f911oooOOO00, this.f910oooOOO0, this.oooOOO0O, this.oooOOO0o);
                C0130oooOO0oo c0130oooOO0oo = new C0130oooOO0oo(this.oooOOO, null);
                oooOO ooooo = new oooOO(coroutineScope, this.oooOOO, this.oooOOOO0, null);
                this.f909oooOO0oo = 1;
                if (oooOO.oooOOO0O.oooOO0oo(flowOooOO0oo, c0130oooOO0oo, ooooo, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((oooOOO00) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getCarLicenseInfo$1", f = "ArcResSdkImpl.kt", i = {}, l = {787}, m = "invokeSuspend", n = {}, s = {})
    public static final class oooOOO0O extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
        public final /* synthetic */ String f919oooOO;

        /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
        public int f920oooOO0oo;

        /* JADX INFO: renamed from: oooOOO0, reason: collision with root package name */
        public final /* synthetic */ String f921oooOOO0;

        /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
        public final /* synthetic */ String f922oooOOO00;
        public final /* synthetic */ ActivateBean oooOOO0O;
        public final /* synthetic */ ArcResSdk.OnResultListener<AuthInfo> oooOOO0o;

        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getCarLicenseInfo$1$2", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class oooOO extends SuspendLambda implements Function2<AuthInfoResponse, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f923oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f924oooOO0oo;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<AuthInfo> f925oooOOO00;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public oooOO(ArcResSdk.OnResultListener<AuthInfo> onResultListener, Continuation<? super oooOO> continuation) {
                super(2, continuation);
                this.f925oooOOO00 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                oooOO ooooo = new oooOO(this.f925oooOOO00, continuation);
                ooooo.f923oooOO = obj;
                return ooooo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                AuthInfoResponse.Result result;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f924oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                AuthInfoResponse authInfoResponse = (AuthInfoResponse) this.f923oooOO;
                if (((authInfoResponse == null || (result = authInfoResponse.getResult()) == null) ? null : result.getAuthInfo()) != null) {
                    this.f925oooOOO00.onResult(authInfoResponse.getResult().getAuthInfo());
                } else {
                    this.f925oooOOO00.onError(authInfoResponse != null ? authInfoResponse.getCode() : Integer.parseInt("-1"), authInfoResponse != null ? authInfoResponse.getMessage() : null);
                }
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(AuthInfoResponse authInfoResponse, Continuation<? super Unit> continuation) {
                return ((oooOO) create(authInfoResponse, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX INFO: renamed from: oooOOO00.oooOO0oo$oooOOO0O$oooOO0oo, reason: collision with other inner class name */
        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getCarLicenseInfo$1$1", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class C0131oooOO0oo extends SuspendLambda implements Function2<ErrorBean, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f926oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f927oooOO0oo;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<AuthInfo> f928oooOOO00;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0131oooOO0oo(ArcResSdk.OnResultListener<AuthInfo> onResultListener, Continuation<? super C0131oooOO0oo> continuation) {
                super(2, continuation);
                this.f928oooOOO00 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C0131oooOO0oo c0131oooOO0oo = new C0131oooOO0oo(this.f928oooOOO00, continuation);
                c0131oooOO0oo.f926oooOO = obj;
                return c0131oooOO0oo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f927oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                ErrorBean errorBean = (ErrorBean) this.f926oooOO;
                if (errorBean != null) {
                    this.f928oooOOO00.onError(Integer.parseInt(errorBean.getCode()), errorBean.getMsg());
                }
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(ErrorBean errorBean, Continuation<? super Unit> continuation) {
                return ((C0131oooOO0oo) create(errorBean, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public oooOOO0O(String str, String str2, String str3, ActivateBean activateBean, ArcResSdk.OnResultListener<AuthInfo> onResultListener, Continuation<? super oooOOO0O> continuation) {
            super(2, continuation);
            this.f919oooOO = str;
            this.f922oooOOO00 = str2;
            this.f921oooOOO0 = str3;
            this.oooOOO0O = activateBean;
            this.oooOOO0o = onResultListener;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new oooOOO0O(this.f919oooOO, this.f922oooOOO00, this.f921oooOOO0, this.oooOOO0O, this.oooOOO0o, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f920oooOO0oo;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow<AuthInfoResponse> flowOooOO = RetrofitManager.INSTANCE.getArcActiveApiService().oooOO(this.f919oooOO, this.f922oooOOO00, this.f921oooOOO0, this.oooOOO0O);
                C0131oooOO0oo c0131oooOO0oo = new C0131oooOO0oo(this.oooOOO0o, null);
                oooOO ooooo = new oooOO(this.oooOOO0o, null);
                this.f920oooOO0oo = 1;
                if (oooOO.oooOOO0O.oooOO0oo(flowOooOO, c0131oooOO0oo, ooooo, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((oooOOO0O) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getEpisodes$1", f = "ArcResSdkImpl.kt", i = {}, l = {452}, m = "invokeSuspend", n = {}, s = {})
    public static final class oooOOOO extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
        public final /* synthetic */ String f929oooOO;

        /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
        public int f930oooOO0oo;

        /* JADX INFO: renamed from: oooOOO0, reason: collision with root package name */
        public final /* synthetic */ int f931oooOOO0;

        /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
        public final /* synthetic */ int f932oooOOO00;
        public final /* synthetic */ ArcResSdk.OnResultListener<ResultInfo> oooOOO0O;

        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getEpisodes$1$2", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class oooOO extends SuspendLambda implements Function2<EpgResponse, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f933oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f934oooOO0oo;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<ResultInfo> f935oooOOO00;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public oooOO(ArcResSdk.OnResultListener<ResultInfo> onResultListener, Continuation<? super oooOO> continuation) {
                super(2, continuation);
                this.f935oooOOO00 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                oooOO ooooo = new oooOO(this.f935oooOOO00, continuation);
                ooooo.f933oooOO = obj;
                return ooooo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f934oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                EpgResponse epgResponse = (EpgResponse) this.f933oooOO;
                ResultInfo resultInfo = new ResultInfo(null, null, null, null, null, 31, null);
                resultInfo.setTotal(epgResponse != null ? Boxing.boxInt(epgResponse.getTotal()) : null);
                resultInfo.setMixedCount(epgResponse != null ? Boxing.boxInt(epgResponse.getMixedCount()) : null);
                resultInfo.setSourceCode(epgResponse != null ? Boxing.boxLong(epgResponse.getSourceCode()) : null);
                resultInfo.setVideoInfoList(oooOOO0.oooOO0oo.f863oooOO0oo.oooOOO00(epgResponse != null ? epgResponse.getEpg() : null));
                this.f935oooOOO00.onResult(resultInfo);
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(EpgResponse epgResponse, Continuation<? super Unit> continuation) {
                return ((oooOO) create(epgResponse, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX INFO: renamed from: oooOOO00.oooOO0oo$oooOOOO$oooOO0oo, reason: collision with other inner class name */
        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getEpisodes$1$1", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class C0132oooOO0oo extends SuspendLambda implements Function2<ErrorBean, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f936oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f937oooOO0oo;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<ResultInfo> f938oooOOO00;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0132oooOO0oo(ArcResSdk.OnResultListener<ResultInfo> onResultListener, Continuation<? super C0132oooOO0oo> continuation) {
                super(2, continuation);
                this.f938oooOOO00 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C0132oooOO0oo c0132oooOO0oo = new C0132oooOO0oo(this.f938oooOOO00, continuation);
                c0132oooOO0oo.f936oooOO = obj;
                return c0132oooOO0oo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f937oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                ErrorBean errorBean = (ErrorBean) this.f936oooOO;
                if (errorBean != null) {
                    this.f938oooOOO00.onError(Integer.parseInt(errorBean.getCode()), errorBean.getMsg());
                }
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(ErrorBean errorBean, Continuation<? super Unit> continuation) {
                return ((C0132oooOO0oo) create(errorBean, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public oooOOOO(String str, int i, int i2, ArcResSdk.OnResultListener<ResultInfo> onResultListener, Continuation<? super oooOOOO> continuation) {
            super(2, continuation);
            this.f929oooOO = str;
            this.f932oooOOO00 = i;
            this.f931oooOOO0 = i2;
            this.oooOOO0O = onResultListener;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new oooOOOO(this.f929oooOO, this.f932oooOOO00, this.f931oooOOO0, this.oooOOO0O, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f930oooOO0oo;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                oooOO.oooOO arcResApiService = RetrofitManager.INSTANCE.getArcResApiService();
                String str = this.f929oooOO;
                int i2 = this.f932oooOOO00;
                int i3 = this.f931oooOOO0;
                Flow<EpgResponse> flowOooOO = arcResApiService.oooOO(str, i2 * i3, i3);
                C0132oooOO0oo c0132oooOO0oo = new C0132oooOO0oo(this.oooOOO0O, null);
                oooOO ooooo = new oooOO(this.oooOOO0O, null);
                this.f930oooOO0oo = 1;
                if (oooOO.oooOOO0O.oooOO0oo(flowOooOO, c0132oooOO0oo, ooooo, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((oooOOOO) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getDolby$1", f = "ArcResSdkImpl.kt", i = {}, l = {284}, m = "invokeSuspend", n = {}, s = {})
    public static final class oooOOOO0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
        public final /* synthetic */ int f939oooOO;

        /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
        public int f940oooOO0oo;

        /* JADX INFO: renamed from: oooOOO0, reason: collision with root package name */
        public final /* synthetic */ int f941oooOOO0;

        /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
        public final /* synthetic */ int f942oooOOO00;
        public final /* synthetic */ ArcResSdk.OnResultListener<ResultInfo> oooOOO0O;

        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getDolby$1$2", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class oooOO extends SuspendLambda implements Function2<DolbyResponse, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f943oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f944oooOO0oo;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<ResultInfo> f945oooOOO00;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public oooOO(ArcResSdk.OnResultListener<ResultInfo> onResultListener, Continuation<? super oooOO> continuation) {
                super(2, continuation);
                this.f945oooOOO00 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                oooOO ooooo = new oooOO(this.f945oooOOO00, continuation);
                ooooo.f943oooOO = obj;
                return ooooo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f944oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                DolbyResponse dolbyResponse = (DolbyResponse) this.f943oooOO;
                ResultInfo resultInfo = new ResultInfo(null, null, null, null, null, 31, null);
                resultInfo.setTotal(dolbyResponse != null ? Boxing.boxInt(dolbyResponse.getTotal()) : null);
                resultInfo.setVideoInfoList(oooOOO0.oooOO0oo.f863oooOO0oo.oooOOO00(dolbyResponse != null ? dolbyResponse.getEpg() : null));
                this.f945oooOOO00.onResult(resultInfo);
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(DolbyResponse dolbyResponse, Continuation<? super Unit> continuation) {
                return ((oooOO) create(dolbyResponse, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX INFO: renamed from: oooOOO00.oooOO0oo$oooOOOO0$oooOO0oo, reason: collision with other inner class name */
        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getDolby$1$1", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class C0133oooOO0oo extends SuspendLambda implements Function2<ErrorBean, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f946oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f947oooOO0oo;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<ResultInfo> f948oooOOO00;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0133oooOO0oo(ArcResSdk.OnResultListener<ResultInfo> onResultListener, Continuation<? super C0133oooOO0oo> continuation) {
                super(2, continuation);
                this.f948oooOOO00 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C0133oooOO0oo c0133oooOO0oo = new C0133oooOO0oo(this.f948oooOOO00, continuation);
                c0133oooOO0oo.f946oooOO = obj;
                return c0133oooOO0oo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f947oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                ErrorBean errorBean = (ErrorBean) this.f946oooOO;
                if (errorBean != null) {
                    this.f948oooOOO00.onError(Integer.parseInt(errorBean.getCode()), errorBean.getMsg());
                }
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(ErrorBean errorBean, Continuation<? super Unit> continuation) {
                return ((C0133oooOO0oo) create(errorBean, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public oooOOOO0(int i, int i2, int i3, ArcResSdk.OnResultListener<ResultInfo> onResultListener, Continuation<? super oooOOOO0> continuation) {
            super(2, continuation);
            this.f939oooOO = i;
            this.f942oooOOO00 = i2;
            this.f941oooOOO0 = i3;
            this.oooOOO0O = onResultListener;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new oooOOOO0(this.f939oooOO, this.f942oooOOO00, this.f941oooOOO0, this.oooOOO0O, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f940oooOO0oo;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow<DolbyResponse> flowOooOO0oo = RetrofitManager.INSTANCE.getArcResApiService().oooOO0oo(this.f939oooOO, this.f942oooOOO00, this.f941oooOOO0);
                C0133oooOO0oo c0133oooOO0oo = new C0133oooOO0oo(this.oooOOO0O, null);
                oooOO ooooo = new oooOO(this.oooOOO0O, null);
                this.f940oooOO0oo = 1;
                if (oooOO.oooOOO0O.oooOO0oo(flowOooOO0oo, c0133oooOO0oo, ooooo, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((oooOOOO0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getHotWord$1", f = "ArcResSdkImpl.kt", i = {}, l = {523}, m = "invokeSuspend", n = {}, s = {})
    public static final class oooOOOOo extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
        public final /* synthetic */ ArcResSdk.OnResultListener<List<SuggestInfo>> f949oooOO;

        /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
        public int f950oooOO0oo;

        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getHotWord$1$2", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class oooOO extends SuspendLambda implements Function2<List<? extends SuggestInfo>, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f951oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f952oooOO0oo;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<List<SuggestInfo>> f953oooOOO00;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public oooOO(ArcResSdk.OnResultListener<List<SuggestInfo>> onResultListener, Continuation<? super oooOO> continuation) {
                super(2, continuation);
                this.f953oooOOO00 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                oooOO ooooo = new oooOO(this.f953oooOOO00, continuation);
                ooooo.f951oooOO = obj;
                return ooooo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f952oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.f953oooOOO00.onResult((List) this.f951oooOO);
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(List<SuggestInfo> list, Continuation<? super Unit> continuation) {
                return ((oooOO) create(list, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX INFO: renamed from: oooOOO00.oooOO0oo$oooOOOOo$oooOO0oo, reason: collision with other inner class name */
        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getHotWord$1$1", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class C0134oooOO0oo extends SuspendLambda implements Function2<ErrorBean, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f954oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f955oooOO0oo;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<List<SuggestInfo>> f956oooOOO00;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0134oooOO0oo(ArcResSdk.OnResultListener<List<SuggestInfo>> onResultListener, Continuation<? super C0134oooOO0oo> continuation) {
                super(2, continuation);
                this.f956oooOOO00 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C0134oooOO0oo c0134oooOO0oo = new C0134oooOO0oo(this.f956oooOOO00, continuation);
                c0134oooOO0oo.f954oooOO = obj;
                return c0134oooOO0oo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f955oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                ErrorBean errorBean = (ErrorBean) this.f954oooOO;
                if (errorBean != null) {
                    this.f956oooOOO00.onError(Integer.parseInt(errorBean.getCode()), errorBean.getMsg());
                }
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(ErrorBean errorBean, Continuation<? super Unit> continuation) {
                return ((C0134oooOO0oo) create(errorBean, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public oooOOOOo(ArcResSdk.OnResultListener<List<SuggestInfo>> onResultListener, Continuation<? super oooOOOOo> continuation) {
            super(2, continuation);
            this.f949oooOO = onResultListener;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new oooOOOOo(this.f949oooOO, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f950oooOO0oo;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow<List<SuggestInfo>> flowOooOO0oo = RetrofitManager.INSTANCE.getArcResApiService().oooOO0oo();
                C0134oooOO0oo c0134oooOO0oo = new C0134oooOO0oo(this.f949oooOO, null);
                oooOO ooooo = new oooOO(this.f949oooOO, null);
                this.f950oooOO0oo = 1;
                if (oooOO.oooOOO0O.oooOO0oo(flowOooOO0oo, c0134oooOO0oo, ooooo, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((oooOOOOo) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getIntentSearchList$1", f = "ArcResSdkImpl.kt", i = {}, l = {751}, m = "invokeSuspend", n = {}, s = {})
    public static final class oooOo00 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
        public final /* synthetic */ String f957oooOO;

        /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
        public int f958oooOO0oo;

        /* JADX INFO: renamed from: oooOOO0, reason: collision with root package name */
        public final /* synthetic */ int f959oooOOO0;

        /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
        public final /* synthetic */ int f960oooOOO00;
        public final /* synthetic */ int oooOOO0O;
        public final /* synthetic */ ArcResSdk.OnResultListener<List<VideoInfo>> oooOOO0o;

        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getIntentSearchList$1$2", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class oooOO extends SuspendLambda implements Function2<IntentFilterBean, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f961oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f962oooOO0oo;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<List<VideoInfo>> f963oooOOO00;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public oooOO(ArcResSdk.OnResultListener<List<VideoInfo>> onResultListener, Continuation<? super oooOO> continuation) {
                super(2, continuation);
                this.f963oooOOO00 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                oooOO ooooo = new oooOO(this.f963oooOOO00, continuation);
                ooooo.f961oooOO = obj;
                return ooooo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f962oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                IntentFilterBean intentFilterBean = (IntentFilterBean) this.f961oooOO;
                this.f963oooOOO00.onResult(oooOOO0.oooOO0oo.f863oooOO0oo.oooOOO00(intentFilterBean != null ? intentFilterBean.getEpgs() : null));
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(IntentFilterBean intentFilterBean, Continuation<? super Unit> continuation) {
                return ((oooOO) create(intentFilterBean, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX INFO: renamed from: oooOOO00.oooOO0oo$oooOo00$oooOO0oo, reason: collision with other inner class name */
        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getIntentSearchList$1$1", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class C0135oooOO0oo extends SuspendLambda implements Function2<ErrorBean, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f964oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f965oooOO0oo;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<List<VideoInfo>> f966oooOOO00;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0135oooOO0oo(ArcResSdk.OnResultListener<List<VideoInfo>> onResultListener, Continuation<? super C0135oooOO0oo> continuation) {
                super(2, continuation);
                this.f966oooOOO00 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C0135oooOO0oo c0135oooOO0oo = new C0135oooOO0oo(this.f966oooOOO00, continuation);
                c0135oooOO0oo.f964oooOO = obj;
                return c0135oooOO0oo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f965oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                ErrorBean errorBean = (ErrorBean) this.f964oooOO;
                if (errorBean != null) {
                    this.f966oooOOO00.onError(Integer.parseInt(errorBean.getCode()), errorBean.getMsg());
                }
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(ErrorBean errorBean, Continuation<? super Unit> continuation) {
                return ((C0135oooOO0oo) create(errorBean, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public oooOo00(String str, int i, int i2, int i3, ArcResSdk.OnResultListener<List<VideoInfo>> onResultListener, Continuation<? super oooOo00> continuation) {
            super(2, continuation);
            this.f957oooOO = str;
            this.f960oooOOO00 = i;
            this.f959oooOOO0 = i2;
            this.oooOOO0O = i3;
            this.oooOOO0o = onResultListener;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new oooOo00(this.f957oooOO, this.f960oooOOO00, this.f959oooOOO0, this.oooOOO0O, this.oooOOO0o, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f958oooOO0oo;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow<IntentFilterBean> flowOooOO0oo = RetrofitManager.INSTANCE.getArcResApiService().oooOO0oo(this.f957oooOO, this.f960oooOOO00, this.f959oooOOO0, this.oooOOO0O);
                C0135oooOO0oo c0135oooOO0oo = new C0135oooOO0oo(this.oooOOO0o, null);
                oooOO ooooo = new oooOO(this.oooOOO0o, null);
                this.f958oooOO0oo = 1;
                if (oooOO.oooOOO0O.oooOO0oo(flowOooOO0oo, c0135oooOO0oo, ooooo, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((oooOo00) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getHotWord$2", f = "ArcResSdkImpl.kt", i = {}, l = {536}, m = "invokeSuspend", n = {}, s = {})
    public static final class oooOo000 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
        public final /* synthetic */ int f967oooOO;

        /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
        public int f968oooOO0oo;

        /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
        public final /* synthetic */ ArcResSdk.OnResultListener<List<SuggestInfo>> f969oooOOO00;

        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getHotWord$2$2", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class oooOO extends SuspendLambda implements Function2<List<? extends SuggestInfo>, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f970oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f971oooOO0oo;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<List<SuggestInfo>> f972oooOOO00;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public oooOO(ArcResSdk.OnResultListener<List<SuggestInfo>> onResultListener, Continuation<? super oooOO> continuation) {
                super(2, continuation);
                this.f972oooOOO00 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                oooOO ooooo = new oooOO(this.f972oooOOO00, continuation);
                ooooo.f970oooOO = obj;
                return ooooo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f971oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.f972oooOOO00.onResult((List) this.f970oooOO);
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(List<SuggestInfo> list, Continuation<? super Unit> continuation) {
                return ((oooOO) create(list, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX INFO: renamed from: oooOOO00.oooOO0oo$oooOo000$oooOO0oo, reason: collision with other inner class name */
        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getHotWord$2$1", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class C0136oooOO0oo extends SuspendLambda implements Function2<ErrorBean, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f973oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f974oooOO0oo;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<List<SuggestInfo>> f975oooOOO00;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0136oooOO0oo(ArcResSdk.OnResultListener<List<SuggestInfo>> onResultListener, Continuation<? super C0136oooOO0oo> continuation) {
                super(2, continuation);
                this.f975oooOOO00 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C0136oooOO0oo c0136oooOO0oo = new C0136oooOO0oo(this.f975oooOOO00, continuation);
                c0136oooOO0oo.f973oooOO = obj;
                return c0136oooOO0oo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f974oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                ErrorBean errorBean = (ErrorBean) this.f973oooOO;
                if (errorBean != null) {
                    this.f975oooOOO00.onError(Integer.parseInt(errorBean.getCode()), errorBean.getMsg());
                }
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(ErrorBean errorBean, Continuation<? super Unit> continuation) {
                return ((C0136oooOO0oo) create(errorBean, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public oooOo000(int i, ArcResSdk.OnResultListener<List<SuggestInfo>> onResultListener, Continuation<? super oooOo000> continuation) {
            super(2, continuation);
            this.f967oooOO = i;
            this.f969oooOOO00 = onResultListener;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new oooOo000(this.f967oooOO, this.f969oooOOO00, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f968oooOO0oo;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow<List<SuggestInfo>> flowOooOO0oo = RetrofitManager.INSTANCE.getArcResApiService().oooOO0oo(this.f967oooOO);
                C0136oooOO0oo c0136oooOO0oo = new C0136oooOO0oo(this.f969oooOOO00, null);
                oooOO ooooo = new oooOO(this.f969oooOOO00, null);
                this.f968oooOO0oo = 1;
                if (oooOO.oooOOO0O.oooOO0oo(flowOooOO0oo, c0136oooOO0oo, ooooo, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((oooOo000) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getSuggest$1", f = "ArcResSdkImpl.kt", i = {}, l = {510}, m = "invokeSuspend", n = {}, s = {})
    public static final class oooo0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
        public final /* synthetic */ String f976oooOO;

        /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
        public int f977oooOO0oo;

        /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
        public final /* synthetic */ ArcResSdk.OnResultListener<List<SuggestInfo>> f978oooOOO00;

        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getSuggest$1$2", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class oooOO extends SuspendLambda implements Function2<List<? extends SuggestInfo>, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f979oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f980oooOO0oo;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<List<SuggestInfo>> f981oooOOO00;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public oooOO(ArcResSdk.OnResultListener<List<SuggestInfo>> onResultListener, Continuation<? super oooOO> continuation) {
                super(2, continuation);
                this.f981oooOOO00 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                oooOO ooooo = new oooOO(this.f981oooOOO00, continuation);
                ooooo.f979oooOO = obj;
                return ooooo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f980oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.f981oooOOO00.onResult((List) this.f979oooOO);
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(List<SuggestInfo> list, Continuation<? super Unit> continuation) {
                return ((oooOO) create(list, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX INFO: renamed from: oooOOO00.oooOO0oo$oooo0$oooOO0oo, reason: collision with other inner class name */
        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getSuggest$1$1", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class C0137oooOO0oo extends SuspendLambda implements Function2<ErrorBean, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f982oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f983oooOO0oo;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<List<SuggestInfo>> f984oooOOO00;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0137oooOO0oo(ArcResSdk.OnResultListener<List<SuggestInfo>> onResultListener, Continuation<? super C0137oooOO0oo> continuation) {
                super(2, continuation);
                this.f984oooOOO00 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C0137oooOO0oo c0137oooOO0oo = new C0137oooOO0oo(this.f984oooOOO00, continuation);
                c0137oooOO0oo.f982oooOO = obj;
                return c0137oooOO0oo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f983oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                ErrorBean errorBean = (ErrorBean) this.f982oooOO;
                if (errorBean != null) {
                    this.f984oooOOO00.onError(Integer.parseInt(errorBean.getCode()), errorBean.getMsg());
                }
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(ErrorBean errorBean, Continuation<? super Unit> continuation) {
                return ((C0137oooOO0oo) create(errorBean, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public oooo0(String str, ArcResSdk.OnResultListener<List<SuggestInfo>> onResultListener, Continuation<? super oooo0> continuation) {
            super(2, continuation);
            this.f976oooOO = str;
            this.f978oooOOO00 = onResultListener;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new oooo0(this.f976oooOO, this.f978oooOOO00, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f977oooOO0oo;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow<List<SuggestInfo>> flowOooOO = RetrofitManager.INSTANCE.getArcResApiService().oooOO(this.f976oooOO);
                C0137oooOO0oo c0137oooOO0oo = new C0137oooOO0oo(this.f978oooOOO00, null);
                oooOO ooooo = new oooOO(this.f978oooOOO00, null);
                this.f977oooOO0oo = 1;
                if (oooOO.oooOOO0O.oooOO0oo(flowOooOO, c0137oooOO0oo, ooooo, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((oooo0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getRelevantRecommend$1", f = "ArcResSdkImpl.kt", i = {}, l = {845}, m = "invokeSuspend", n = {}, s = {})
    public static final class oooo00 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
        public final /* synthetic */ String f985oooOO;

        /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
        public int f986oooOO0oo;

        /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
        public final /* synthetic */ ArcResSdk.OnResultListener<List<VideoInfo>> f987oooOOO00;

        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getRelevantRecommend$1$2", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class oooOO extends SuspendLambda implements Function2<DolbyResponse, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f988oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f989oooOO0oo;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<List<VideoInfo>> f990oooOOO00;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public oooOO(ArcResSdk.OnResultListener<List<VideoInfo>> onResultListener, Continuation<? super oooOO> continuation) {
                super(2, continuation);
                this.f990oooOOO00 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                oooOO ooooo = new oooOO(this.f990oooOOO00, continuation);
                ooooo.f988oooOO = obj;
                return ooooo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f989oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                DolbyResponse dolbyResponse = (DolbyResponse) this.f988oooOO;
                this.f990oooOOO00.onResult(oooOOO0.oooOO0oo.f863oooOO0oo.oooOOO00(dolbyResponse != null ? dolbyResponse.getEpg() : null));
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(DolbyResponse dolbyResponse, Continuation<? super Unit> continuation) {
                return ((oooOO) create(dolbyResponse, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX INFO: renamed from: oooOOO00.oooOO0oo$oooo00$oooOO0oo, reason: collision with other inner class name */
        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getRelevantRecommend$1$1", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class C0138oooOO0oo extends SuspendLambda implements Function2<ErrorBean, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f991oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f992oooOO0oo;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<List<VideoInfo>> f993oooOOO00;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0138oooOO0oo(ArcResSdk.OnResultListener<List<VideoInfo>> onResultListener, Continuation<? super C0138oooOO0oo> continuation) {
                super(2, continuation);
                this.f993oooOOO00 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C0138oooOO0oo c0138oooOO0oo = new C0138oooOO0oo(this.f993oooOOO00, continuation);
                c0138oooOO0oo.f991oooOO = obj;
                return c0138oooOO0oo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f992oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                ErrorBean errorBean = (ErrorBean) this.f991oooOO;
                if (errorBean != null) {
                    this.f993oooOOO00.onError(Integer.parseInt(errorBean.getCode()), errorBean.getMsg());
                }
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(ErrorBean errorBean, Continuation<? super Unit> continuation) {
                return ((C0138oooOO0oo) create(errorBean, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public oooo00(String str, ArcResSdk.OnResultListener<List<VideoInfo>> onResultListener, Continuation<? super oooo00> continuation) {
            super(2, continuation);
            this.f985oooOO = str;
            this.f987oooOOO00 = onResultListener;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new oooo00(this.f985oooOO, this.f987oooOOO00, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f986oooOO0oo;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow<DolbyResponse> flowOooOOO00 = RetrofitManager.INSTANCE.getArcResApiService().oooOOO00(this.f985oooOO);
                C0138oooOO0oo c0138oooOO0oo = new C0138oooOO0oo(this.f987oooOOO00, null);
                oooOO ooooo = new oooOO(this.f987oooOOO00, null);
                this.f986oooOO0oo = 1;
                if (oooOO.oooOOO0O.oooOO0oo(flowOooOOO00, c0138oooOO0oo, ooooo, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((oooo00) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getRank$1", f = "ArcResSdkImpl.kt", i = {}, l = {253}, m = "invokeSuspend", n = {}, s = {})
    public static final class oooo0000 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
        public final /* synthetic */ int f994oooOO;

        /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
        public int f995oooOO0oo;
        public final /* synthetic */ ArcResSdk.OnResultListener<List<VideoInfo>> oooOOO;

        /* JADX INFO: renamed from: oooOOO0, reason: collision with root package name */
        public final /* synthetic */ int f996oooOOO0;

        /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
        public final /* synthetic */ boolean f997oooOOO00;
        public final /* synthetic */ int oooOOO0O;
        public final /* synthetic */ int oooOOO0o;

        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getRank$1$2", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class oooOO extends SuspendLambda implements Function2<List<? extends VideoResponse>, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f998oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f999oooOO0oo;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<List<VideoInfo>> f1000oooOOO00;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public oooOO(ArcResSdk.OnResultListener<List<VideoInfo>> onResultListener, Continuation<? super oooOO> continuation) {
                super(2, continuation);
                this.f1000oooOOO00 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                oooOO ooooo = new oooOO(this.f1000oooOOO00, continuation);
                ooooo.f998oooOO = obj;
                return ooooo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f999oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.f1000oooOOO00.onResult(oooOOO0.oooOO0oo.f863oooOO0oo.oooOOO00((List) this.f998oooOO));
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(List<VideoResponse> list, Continuation<? super Unit> continuation) {
                return ((oooOO) create(list, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX INFO: renamed from: oooOOO00.oooOO0oo$oooo0000$oooOO0oo, reason: collision with other inner class name */
        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getRank$1$1", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class C0139oooOO0oo extends SuspendLambda implements Function2<ErrorBean, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f1001oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f1002oooOO0oo;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<List<VideoInfo>> f1003oooOOO00;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0139oooOO0oo(ArcResSdk.OnResultListener<List<VideoInfo>> onResultListener, Continuation<? super C0139oooOO0oo> continuation) {
                super(2, continuation);
                this.f1003oooOOO00 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C0139oooOO0oo c0139oooOO0oo = new C0139oooOO0oo(this.f1003oooOOO00, continuation);
                c0139oooOO0oo.f1001oooOO = obj;
                return c0139oooOO0oo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f1002oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                ErrorBean errorBean = (ErrorBean) this.f1001oooOO;
                if (errorBean != null) {
                    this.f1003oooOOO00.onError(Integer.parseInt(errorBean.getCode()), errorBean.getMsg());
                }
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(ErrorBean errorBean, Continuation<? super Unit> continuation) {
                return ((C0139oooOO0oo) create(errorBean, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public oooo0000(int i, boolean z, int i2, int i3, int i4, ArcResSdk.OnResultListener<List<VideoInfo>> onResultListener, Continuation<? super oooo0000> continuation) {
            super(2, continuation);
            this.f994oooOO = i;
            this.f997oooOOO00 = z;
            this.f996oooOOO0 = i2;
            this.oooOOO0O = i3;
            this.oooOOO0o = i4;
            this.oooOOO = onResultListener;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new oooo0000(this.f994oooOO, this.f997oooOOO00, this.f996oooOOO0, this.oooOOO0O, this.oooOOO0o, this.oooOOO, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f995oooOO0oo;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                RankRequestBean rankRequestBeanOooOO0oo = oooOOO0.oooOO0oo.f863oooOO0oo.oooOO0oo(this.f994oooOO, this.f997oooOOO00, this.f996oooOOO0);
                Flow<List<VideoResponse>> flowOooOO0oo = RetrofitManager.INSTANCE.getArcResApiService().oooOO0oo(rankRequestBeanOooOO0oo.getChnId(), this.oooOOO0O, this.oooOOO0o, rankRequestBeanOooOO0oo.isVip(), rankRequestBeanOooOO0oo.getType());
                C0139oooOO0oo c0139oooOO0oo = new C0139oooOO0oo(this.oooOOO, null);
                oooOO ooooo = new oooOO(this.oooOOO, null);
                this.f995oooOO0oo = 1;
                if (oooOO.oooOOO0O.oooOO0oo(flowOooOO0oo, c0139oooOO0oo, ooooo, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((oooo0000) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getRecommend$1", f = "ArcResSdkImpl.kt", i = {}, l = {194}, m = "invokeSuspend", n = {}, s = {})
    public static final class oooo000O extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
        public final /* synthetic */ String f1004oooOO;

        /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
        public int f1005oooOO0oo;

        /* JADX INFO: renamed from: oooOOO0, reason: collision with root package name */
        public final /* synthetic */ int f1006oooOOO0;

        /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
        public final /* synthetic */ int f1007oooOOO00;
        public final /* synthetic */ ArcResSdk.OnResultListener<List<VideoInfo>> oooOOO0O;

        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getRecommend$1$2", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class oooOO extends SuspendLambda implements Function2<RecFallResponse, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f1008oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f1009oooOO0oo;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<List<VideoInfo>> f1010oooOOO00;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public oooOO(ArcResSdk.OnResultListener<List<VideoInfo>> onResultListener, Continuation<? super oooOO> continuation) {
                super(2, continuation);
                this.f1010oooOOO00 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                oooOO ooooo = new oooOO(this.f1010oooOOO00, continuation);
                ooooo.f1008oooOO = obj;
                return ooooo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f1009oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                RecFallResponse recFallResponse = (RecFallResponse) this.f1008oooOO;
                this.f1010oooOOO00.onResult(oooOOO0.oooOO0oo.f863oooOO0oo.oooOOO00(recFallResponse != null ? recFallResponse.getEpg() : null));
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(RecFallResponse recFallResponse, Continuation<? super Unit> continuation) {
                return ((oooOO) create(recFallResponse, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX INFO: renamed from: oooOOO00.oooOO0oo$oooo000O$oooOO0oo, reason: collision with other inner class name */
        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getRecommend$1$1", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class C0140oooOO0oo extends SuspendLambda implements Function2<ErrorBean, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f1011oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f1012oooOO0oo;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<List<VideoInfo>> f1013oooOOO00;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0140oooOO0oo(ArcResSdk.OnResultListener<List<VideoInfo>> onResultListener, Continuation<? super C0140oooOO0oo> continuation) {
                super(2, continuation);
                this.f1013oooOOO00 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C0140oooOO0oo c0140oooOO0oo = new C0140oooOO0oo(this.f1013oooOOO00, continuation);
                c0140oooOO0oo.f1011oooOO = obj;
                return c0140oooOO0oo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f1012oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                ErrorBean errorBean = (ErrorBean) this.f1011oooOO;
                if (errorBean != null) {
                    Log.d("---", errorBean.getCode());
                    this.f1013oooOOO00.onError(Integer.parseInt(errorBean.getCode()), errorBean.getMsg());
                }
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(ErrorBean errorBean, Continuation<? super Unit> continuation) {
                return ((C0140oooOO0oo) create(errorBean, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public oooo000O(String str, int i, int i2, ArcResSdk.OnResultListener<List<VideoInfo>> onResultListener, Continuation<? super oooo000O> continuation) {
            super(2, continuation);
            this.f1004oooOO = str;
            this.f1007oooOOO00 = i;
            this.f1006oooOOO0 = i2;
            this.oooOOO0O = onResultListener;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new oooo000O(this.f1004oooOO, this.f1007oooOOO00, this.f1006oooOOO0, this.oooOOO0O, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f1005oooOO0oo;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                oooOO.oooOO arcResApiService = RetrofitManager.INSTANCE.getArcResApiService();
                String str = this.f1004oooOO;
                int i2 = this.f1007oooOOO00 - 1;
                int i3 = this.f1006oooOOO0;
                Flow<RecFallResponse> flowOooOO0oo = arcResApiService.oooOO0oo(str, i2 * i3, i3);
                C0140oooOO0oo c0140oooOO0oo = new C0140oooOO0oo(this.oooOOO0O, null);
                oooOO ooooo = new oooOO(this.oooOOO0O, null);
                this.f1005oooOO0oo = 1;
                if (oooOO.oooOOO0O.oooOO0oo(flowOooOO0oo, c0140oooOO0oo, ooooo, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((oooo000O) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getResource$1", f = "ArcResSdkImpl.kt", i = {}, l = {569}, m = "invokeSuspend", n = {}, s = {})
    public static final class oooo00OO extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
        public final /* synthetic */ String f1014oooOO;

        /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
        public int f1015oooOO0oo;

        /* JADX INFO: renamed from: oooOOO0, reason: collision with root package name */
        public final /* synthetic */ Integer f1016oooOOO0;

        /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
        public final /* synthetic */ Integer f1017oooOOO00;
        public final /* synthetic */ ArcResSdk.OnResultListener<List<VideoInfo>> oooOOO0O;

        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getResource$1$2", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class oooOO extends SuspendLambda implements Function2<DolbyResponse, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f1018oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f1019oooOO0oo;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<List<VideoInfo>> f1020oooOOO00;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public oooOO(ArcResSdk.OnResultListener<List<VideoInfo>> onResultListener, Continuation<? super oooOO> continuation) {
                super(2, continuation);
                this.f1020oooOOO00 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                oooOO ooooo = new oooOO(this.f1020oooOOO00, continuation);
                ooooo.f1018oooOO = obj;
                return ooooo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f1019oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                DolbyResponse dolbyResponse = (DolbyResponse) this.f1018oooOO;
                this.f1020oooOOO00.onResult(oooOOO0.oooOO0oo.f863oooOO0oo.oooOOO00(dolbyResponse != null ? dolbyResponse.getEpg() : null));
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(DolbyResponse dolbyResponse, Continuation<? super Unit> continuation) {
                return ((oooOO) create(dolbyResponse, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX INFO: renamed from: oooOOO00.oooOO0oo$oooo00OO$oooOO0oo, reason: collision with other inner class name */
        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getResource$1$1", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class C0141oooOO0oo extends SuspendLambda implements Function2<ErrorBean, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f1021oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f1022oooOO0oo;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<List<VideoInfo>> f1023oooOOO00;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0141oooOO0oo(ArcResSdk.OnResultListener<List<VideoInfo>> onResultListener, Continuation<? super C0141oooOO0oo> continuation) {
                super(2, continuation);
                this.f1023oooOOO00 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C0141oooOO0oo c0141oooOO0oo = new C0141oooOO0oo(this.f1023oooOOO00, continuation);
                c0141oooOO0oo.f1021oooOO = obj;
                return c0141oooOO0oo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f1022oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                ErrorBean errorBean = (ErrorBean) this.f1021oooOO;
                if (errorBean != null) {
                    this.f1023oooOOO00.onError(Integer.parseInt(errorBean.getCode()), errorBean.getMsg());
                }
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(ErrorBean errorBean, Continuation<? super Unit> continuation) {
                return ((C0141oooOO0oo) create(errorBean, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public oooo00OO(String str, Integer num, Integer num2, ArcResSdk.OnResultListener<List<VideoInfo>> onResultListener, Continuation<? super oooo00OO> continuation) {
            super(2, continuation);
            this.f1014oooOO = str;
            this.f1017oooOOO00 = num;
            this.f1016oooOOO0 = num2;
            this.oooOOO0O = onResultListener;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new oooo00OO(this.f1014oooOO, this.f1017oooOOO00, this.f1016oooOOO0, this.oooOOO0O, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f1015oooOO0oo;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow<DolbyResponse> flowOooOO0oo = RetrofitManager.INSTANCE.getArcResApiService().oooOO0oo(this.f1014oooOO, this.f1017oooOOO00, this.f1016oooOOO0);
                C0141oooOO0oo c0141oooOO0oo = new C0141oooOO0oo(this.oooOOO0O, null);
                oooOO ooooo = new oooOO(this.oooOOO0O, null);
                this.f1015oooOO0oo = 1;
                if (oooOO.oooOOO0O.oooOO0oo(flowOooOO0oo, c0141oooOO0oo, ooooo, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((oooo00OO) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getRelevantRecommendV2$1", f = "ArcResSdkImpl.kt", i = {}, l = {730}, m = "invokeSuspend", n = {}, s = {})
    public static final class oooo00o0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
        public final /* synthetic */ String f1024oooOO;

        /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
        public int f1025oooOO0oo;

        /* JADX INFO: renamed from: oooOOO0, reason: collision with root package name */
        public final /* synthetic */ int f1026oooOOO0;

        /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
        public final /* synthetic */ String f1027oooOOO00;
        public final /* synthetic */ ArcResSdk.OnResultListener<List<VideoInfo>> oooOOO0O;

        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getRelevantRecommendV2$1$2", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class oooOO extends SuspendLambda implements Function2<DolbyResponse, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f1028oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f1029oooOO0oo;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<List<VideoInfo>> f1030oooOOO00;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public oooOO(ArcResSdk.OnResultListener<List<VideoInfo>> onResultListener, Continuation<? super oooOO> continuation) {
                super(2, continuation);
                this.f1030oooOOO00 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                oooOO ooooo = new oooOO(this.f1030oooOOO00, continuation);
                ooooo.f1028oooOO = obj;
                return ooooo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f1029oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                DolbyResponse dolbyResponse = (DolbyResponse) this.f1028oooOO;
                this.f1030oooOOO00.onResult(oooOOO0.oooOO0oo.f863oooOO0oo.oooOOO00(dolbyResponse != null ? dolbyResponse.getEpg() : null));
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(DolbyResponse dolbyResponse, Continuation<? super Unit> continuation) {
                return ((oooOO) create(dolbyResponse, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX INFO: renamed from: oooOOO00.oooOO0oo$oooo00o0$oooOO0oo, reason: collision with other inner class name */
        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getRelevantRecommendV2$1$1", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class C0142oooOO0oo extends SuspendLambda implements Function2<ErrorBean, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f1031oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f1032oooOO0oo;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<List<VideoInfo>> f1033oooOOO00;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0142oooOO0oo(ArcResSdk.OnResultListener<List<VideoInfo>> onResultListener, Continuation<? super C0142oooOO0oo> continuation) {
                super(2, continuation);
                this.f1033oooOOO00 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C0142oooOO0oo c0142oooOO0oo = new C0142oooOO0oo(this.f1033oooOOO00, continuation);
                c0142oooOO0oo.f1031oooOO = obj;
                return c0142oooOO0oo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f1032oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                ErrorBean errorBean = (ErrorBean) this.f1031oooOO;
                if (errorBean != null) {
                    this.f1033oooOOO00.onError(Integer.parseInt(errorBean.getCode()), errorBean.getMsg());
                }
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(ErrorBean errorBean, Continuation<? super Unit> continuation) {
                return ((C0142oooOO0oo) create(errorBean, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public oooo00o0(String str, String str2, int i, ArcResSdk.OnResultListener<List<VideoInfo>> onResultListener, Continuation<? super oooo00o0> continuation) {
            super(2, continuation);
            this.f1024oooOO = str;
            this.f1027oooOOO00 = str2;
            this.f1026oooOOO0 = i;
            this.oooOOO0O = onResultListener;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new oooo00o0(this.f1024oooOO, this.f1027oooOOO00, this.f1026oooOOO0, this.oooOOO0O, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f1025oooOO0oo;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow<DolbyResponse> flowOooOO0oo = RetrofitManager.INSTANCE.getArcResApiService().oooOO0oo(this.f1024oooOO, this.f1027oooOOO00, this.f1026oooOOO0);
                C0142oooOO0oo c0142oooOO0oo = new C0142oooOO0oo(this.oooOOO0O, null);
                oooOO ooooo = new oooOO(this.oooOOO0O, null);
                this.f1025oooOO0oo = 1;
                if (oooOO.oooOOO0O.oooOO0oo(flowOooOO0oo, c0142oooOO0oo, ooooo, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((oooo00o0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$register$1", f = "ArcResSdkImpl.kt", i = {}, l = {120}, m = "invokeSuspend", n = {}, s = {})
    public static final class oooo0O extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
        public final /* synthetic */ RegisterRequestBean f1034oooOO;

        /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
        public int f1035oooOO0oo;

        /* JADX INFO: renamed from: oooOOO0, reason: collision with root package name */
        public final /* synthetic */ ArcResSdk.OnResultListener<RegisterInfo> f1036oooOOO0;

        /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
        public final /* synthetic */ oooOO0oo f1037oooOOO00;

        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$register$1$2", f = "ArcResSdkImpl.kt", i = {}, l = {128}, m = "invokeSuspend", n = {}, s = {})
        public static final class oooOO extends SuspendLambda implements Function2<DeviceSession, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f1038oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f1039oooOO0oo;

            /* JADX INFO: renamed from: oooOOO0, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<RegisterInfo> f1040oooOOO0;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ oooOO0oo f1041oooOOO00;

            /* JADX INFO: renamed from: oooOOO00.oooOO0oo$oooo0O$oooOO$oooOO, reason: collision with other inner class name */
            @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$register$1$2$2", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            public static final class C0143oooOO extends SuspendLambda implements Function2<SystemTime, Continuation<? super Unit>, Object> {

                /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
                public /* synthetic */ Object f1042oooOO;

                /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
                public int f1043oooOO0oo;

                /* JADX INFO: renamed from: oooOOO0, reason: collision with root package name */
                public final /* synthetic */ ArcResSdk.OnResultListener<RegisterInfo> f1044oooOOO0;

                /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
                public final /* synthetic */ oooOO0oo f1045oooOOO00;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0143oooOO(oooOO0oo ooooo0oo, ArcResSdk.OnResultListener<RegisterInfo> onResultListener, Continuation<? super C0143oooOO> continuation) {
                    super(2, continuation);
                    this.f1045oooOOO00 = ooooo0oo;
                    this.f1044oooOOO0 = onResultListener;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    C0143oooOO c0143oooOO = new C0143oooOO(this.f1045oooOOO00, this.f1044oooOOO0, continuation);
                    c0143oooOO.f1042oooOO = obj;
                    return c0143oooOO;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) throws Throwable {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.f1043oooOO0oo != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    SystemTime systemTime = (SystemTime) this.f1042oooOO;
                    RegisterInfo registerInfo = new RegisterInfo();
                    registerInfo.setSysTime(systemTime != null ? Boxing.boxLong(systemTime.getTime()) : null);
                    registerInfo.setSuccess(true);
                    registerInfo.setToken(this.f1045oooOOO00.f864oooOO);
                    this.f1044oooOOO0.onResult(registerInfo);
                    return Unit.INSTANCE;
                }

                @Override // kotlin.jvm.functions.Function2
                /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
                public final Object invoke(SystemTime systemTime, Continuation<? super Unit> continuation) {
                    return ((C0143oooOO) create(systemTime, continuation)).invokeSuspend(Unit.INSTANCE);
                }
            }

            /* JADX INFO: renamed from: oooOOO00.oooOO0oo$oooo0O$oooOO$oooOO0oo, reason: collision with other inner class name */
            @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$register$1$2$1", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            public static final class C0144oooOO0oo extends SuspendLambda implements Function2<ErrorBean, Continuation<? super Unit>, Object> {

                /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
                public /* synthetic */ Object f1046oooOO;

                /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
                public int f1047oooOO0oo;

                /* JADX INFO: renamed from: oooOOO0, reason: collision with root package name */
                public final /* synthetic */ ArcResSdk.OnResultListener<RegisterInfo> f1048oooOOO0;

                /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
                public final /* synthetic */ oooOO0oo f1049oooOOO00;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0144oooOO0oo(oooOO0oo ooooo0oo, ArcResSdk.OnResultListener<RegisterInfo> onResultListener, Continuation<? super C0144oooOO0oo> continuation) {
                    super(2, continuation);
                    this.f1049oooOOO00 = ooooo0oo;
                    this.f1048oooOOO0 = onResultListener;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    C0144oooOO0oo c0144oooOO0oo = new C0144oooOO0oo(this.f1049oooOOO00, this.f1048oooOOO0, continuation);
                    c0144oooOO0oo.f1046oooOO = obj;
                    return c0144oooOO0oo;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) throws Throwable {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.f1047oooOO0oo != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    ErrorBean errorBean = (ErrorBean) this.f1046oooOO;
                    if (errorBean != null) {
                        oooOO0oo ooooo0oo = this.f1049oooOOO00;
                        ArcResSdk.OnResultListener<RegisterInfo> onResultListener = this.f1048oooOOO0;
                        Log.d(ooooo0oo.f866oooOOO00, "getSystemTime error:" + errorBean);
                        onResultListener.onError(Integer.parseInt(errorBean.getCode()), errorBean.getMsg());
                    }
                    return Unit.INSTANCE;
                }

                @Override // kotlin.jvm.functions.Function2
                /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
                public final Object invoke(ErrorBean errorBean, Continuation<? super Unit> continuation) {
                    return ((C0144oooOO0oo) create(errorBean, continuation)).invokeSuspend(Unit.INSTANCE);
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public oooOO(oooOO0oo ooooo0oo, ArcResSdk.OnResultListener<RegisterInfo> onResultListener, Continuation<? super oooOO> continuation) {
                super(2, continuation);
                this.f1041oooOOO00 = ooooo0oo;
                this.f1040oooOOO0 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                oooOO ooooo = new oooOO(this.f1041oooOOO00, this.f1040oooOOO0, continuation);
                ooooo.f1038oooOO = obj;
                return ooooo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.f1039oooOO0oo;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    DeviceSession deviceSession = (DeviceSession) this.f1038oooOO;
                    this.f1041oooOOO00.f864oooOO = String.valueOf(deviceSession != null ? deviceSession.getToken() : null);
                    Flow<SystemTime> flowOooOO = RetrofitManager.INSTANCE.getArcResApiService().oooOO();
                    C0144oooOO0oo c0144oooOO0oo = new C0144oooOO0oo(this.f1041oooOOO00, this.f1040oooOOO0, null);
                    C0143oooOO c0143oooOO = new C0143oooOO(this.f1041oooOOO00, this.f1040oooOOO0, null);
                    this.f1039oooOO0oo = 1;
                    if (oooOO.oooOOO0O.oooOO0oo(flowOooOO, c0144oooOO0oo, c0143oooOO, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(DeviceSession deviceSession, Continuation<? super Unit> continuation) {
                return ((oooOO) create(deviceSession, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX INFO: renamed from: oooOOO00.oooOO0oo$oooo0O$oooOO0oo, reason: collision with other inner class name */
        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$register$1$1", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class C0145oooOO0oo extends SuspendLambda implements Function2<ErrorBean, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f1050oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f1051oooOO0oo;

            /* JADX INFO: renamed from: oooOOO0, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<RegisterInfo> f1052oooOOO0;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ oooOO0oo f1053oooOOO00;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0145oooOO0oo(oooOO0oo ooooo0oo, ArcResSdk.OnResultListener<RegisterInfo> onResultListener, Continuation<? super C0145oooOO0oo> continuation) {
                super(2, continuation);
                this.f1053oooOOO00 = ooooo0oo;
                this.f1052oooOOO0 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C0145oooOO0oo c0145oooOO0oo = new C0145oooOO0oo(this.f1053oooOOO00, this.f1052oooOOO0, continuation);
                c0145oooOO0oo.f1050oooOO = obj;
                return c0145oooOO0oo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f1051oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                ErrorBean errorBean = (ErrorBean) this.f1050oooOO;
                if (errorBean != null) {
                    oooOO0oo ooooo0oo = this.f1053oooOOO00;
                    ArcResSdk.OnResultListener<RegisterInfo> onResultListener = this.f1052oooOOO0;
                    Log.d(ooooo0oo.f866oooOOO00, "getRegister error:" + errorBean);
                    onResultListener.onError(Integer.parseInt(errorBean.getCode()), errorBean.getMsg());
                }
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(ErrorBean errorBean, Continuation<? super Unit> continuation) {
                return ((C0145oooOO0oo) create(errorBean, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public oooo0O(RegisterRequestBean registerRequestBean, oooOO0oo ooooo0oo, ArcResSdk.OnResultListener<RegisterInfo> onResultListener, Continuation<? super oooo0O> continuation) {
            super(2, continuation);
            this.f1034oooOO = registerRequestBean;
            this.f1037oooOOO00 = ooooo0oo;
            this.f1036oooOOO0 = onResultListener;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new oooo0O(this.f1034oooOO, this.f1037oooOOO00, this.f1036oooOOO0, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f1035oooOO0oo;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow<DeviceSession> flowOooOO = RetrofitManager.INSTANCE.getArcResApiService().oooOO(this.f1034oooOO);
                C0145oooOO0oo c0145oooOO0oo = new C0145oooOO0oo(this.f1037oooOOO00, this.f1036oooOOO0, null);
                oooOO ooooo = new oooOO(this.f1037oooOOO00, this.f1036oooOOO0, null);
                this.f1035oooOO0oo = 1;
                if (oooOO.oooOOO0O.oooOO0oo(flowOooOO, c0145oooOO0oo, ooooo, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((oooo0O) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getVideoLib$1", f = "ArcResSdkImpl.kt", i = {}, l = {348}, m = "invokeSuspend", n = {}, s = {})
    public static final class oooo0O0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
        public final /* synthetic */ int f1054oooOO;

        /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
        public int f1055oooOO0oo;
        public final /* synthetic */ ArcResSdk.OnResultListener<ResultInfo> oooOOO;

        /* JADX INFO: renamed from: oooOOO0, reason: collision with root package name */
        public final /* synthetic */ int f1056oooOOO0;

        /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
        public final /* synthetic */ String f1057oooOOO00;
        public final /* synthetic */ int oooOOO0O;
        public final /* synthetic */ Ref.ObjectRef<String> oooOOO0o;

        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getVideoLib$1$2", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class oooOO extends SuspendLambda implements Function2<ChannelResponse, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f1058oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f1059oooOO0oo;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<ResultInfo> f1060oooOOO00;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public oooOO(ArcResSdk.OnResultListener<ResultInfo> onResultListener, Continuation<? super oooOO> continuation) {
                super(2, continuation);
                this.f1060oooOOO00 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                oooOO ooooo = new oooOO(this.f1060oooOOO00, continuation);
                ooooo.f1058oooOO = obj;
                return ooooo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f1059oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                ChannelResponse channelResponse = (ChannelResponse) this.f1058oooOO;
                ResultInfo resultInfo = new ResultInfo(null, null, null, null, null, 31, null);
                resultInfo.setTotal(channelResponse != null ? Boxing.boxInt(channelResponse.getTotal()) : null);
                oooOOO0.oooOO0oo ooooo0oo = oooOOO0.oooOO0oo.f863oooOO0oo;
                resultInfo.setTags(ooooo0oo.oooOO(channelResponse != null ? channelResponse.getTags() : null));
                resultInfo.setVideoInfoList(ooooo0oo.oooOOO00(channelResponse != null ? channelResponse.getVideos() : null));
                this.f1060oooOOO00.onResult(resultInfo);
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(ChannelResponse channelResponse, Continuation<? super Unit> continuation) {
                return ((oooOO) create(channelResponse, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX INFO: renamed from: oooOOO00.oooOO0oo$oooo0O0$oooOO0oo, reason: collision with other inner class name */
        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getVideoLib$1$1", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class C0146oooOO0oo extends SuspendLambda implements Function2<ErrorBean, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f1061oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f1062oooOO0oo;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<ResultInfo> f1063oooOOO00;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0146oooOO0oo(ArcResSdk.OnResultListener<ResultInfo> onResultListener, Continuation<? super C0146oooOO0oo> continuation) {
                super(2, continuation);
                this.f1063oooOOO00 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C0146oooOO0oo c0146oooOO0oo = new C0146oooOO0oo(this.f1063oooOOO00, continuation);
                c0146oooOO0oo.f1061oooOO = obj;
                return c0146oooOO0oo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f1062oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                ErrorBean errorBean = (ErrorBean) this.f1061oooOO;
                if (errorBean != null) {
                    this.f1063oooOOO00.onError(Integer.parseInt(errorBean.getCode()), errorBean.getMsg());
                }
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(ErrorBean errorBean, Continuation<? super Unit> continuation) {
                return ((C0146oooOO0oo) create(errorBean, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public oooo0O0(int i, String str, int i2, int i3, Ref.ObjectRef<String> objectRef, ArcResSdk.OnResultListener<ResultInfo> onResultListener, Continuation<? super oooo0O0> continuation) {
            super(2, continuation);
            this.f1054oooOO = i;
            this.f1057oooOOO00 = str;
            this.f1056oooOOO0 = i2;
            this.oooOOO0O = i3;
            this.oooOOO0o = objectRef;
            this.oooOOO = onResultListener;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new oooo0O0(this.f1054oooOO, this.f1057oooOOO00, this.f1056oooOOO0, this.oooOOO0O, this.oooOOO0o, this.oooOOO, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f1055oooOO0oo;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow<ChannelResponse> flowOooOO0oo = RetrofitManager.INSTANCE.getArcResApiService().oooOO0oo(this.f1054oooOO, this.f1057oooOOO00, this.f1056oooOOO0, this.oooOOO0O, this.oooOOO0o.element, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE, null, null, null, null);
                C0146oooOO0oo c0146oooOO0oo = new C0146oooOO0oo(this.oooOOO, null);
                oooOO ooooo = new oooOO(this.oooOOO, null);
                this.f1055oooOO0oo = 1;
                if (oooOO.oooOOO0O.oooOO0oo(flowOooOO0oo, c0146oooOO0oo, ooooo, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((oooo0O0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getVideoByTag$1", f = "ArcResSdkImpl.kt", i = {}, l = {424}, m = "invokeSuspend", n = {}, s = {})
    public static final class oooo0O00 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
        public final /* synthetic */ int f1064oooOO;

        /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
        public int f1065oooOO0oo;
        public final /* synthetic */ String oooOOO;

        /* JADX INFO: renamed from: oooOOO0, reason: collision with root package name */
        public final /* synthetic */ String f1066oooOOO0;

        /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
        public final /* synthetic */ String f1067oooOOO00;
        public final /* synthetic */ int oooOOO0O;
        public final /* synthetic */ int oooOOO0o;
        public final /* synthetic */ Integer oooOOOO;
        public final /* synthetic */ String oooOOOO0;
        public final /* synthetic */ ArcResSdk.OnResultListener<ResultInfo> oooOOOOo;

        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getVideoByTag$1$2", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class oooOO extends SuspendLambda implements Function2<ChannelResponse, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f1068oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f1069oooOO0oo;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<ResultInfo> f1070oooOOO00;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public oooOO(ArcResSdk.OnResultListener<ResultInfo> onResultListener, Continuation<? super oooOO> continuation) {
                super(2, continuation);
                this.f1070oooOOO00 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                oooOO ooooo = new oooOO(this.f1070oooOOO00, continuation);
                ooooo.f1068oooOO = obj;
                return ooooo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f1069oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                ChannelResponse channelResponse = (ChannelResponse) this.f1068oooOO;
                ResultInfo resultInfo = new ResultInfo(null, null, null, null, null, 31, null);
                resultInfo.setTotal(channelResponse != null ? Boxing.boxInt(channelResponse.getTotal()) : null);
                oooOOO0.oooOO0oo ooooo0oo = oooOOO0.oooOO0oo.f863oooOO0oo;
                resultInfo.setTags(ooooo0oo.oooOO(channelResponse != null ? channelResponse.getTags() : null));
                resultInfo.setVideoInfoList(ooooo0oo.oooOOO00(channelResponse != null ? channelResponse.getVideos() : null));
                this.f1070oooOOO00.onResult(resultInfo);
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(ChannelResponse channelResponse, Continuation<? super Unit> continuation) {
                return ((oooOO) create(channelResponse, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX INFO: renamed from: oooOOO00.oooOO0oo$oooo0O00$oooOO0oo, reason: collision with other inner class name */
        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getVideoByTag$1$1", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class C0147oooOO0oo extends SuspendLambda implements Function2<ErrorBean, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f1071oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f1072oooOO0oo;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<ResultInfo> f1073oooOOO00;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0147oooOO0oo(ArcResSdk.OnResultListener<ResultInfo> onResultListener, Continuation<? super C0147oooOO0oo> continuation) {
                super(2, continuation);
                this.f1073oooOOO00 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C0147oooOO0oo c0147oooOO0oo = new C0147oooOO0oo(this.f1073oooOOO00, continuation);
                c0147oooOO0oo.f1071oooOO = obj;
                return c0147oooOO0oo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f1072oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                ErrorBean errorBean = (ErrorBean) this.f1071oooOO;
                if (errorBean != null) {
                    this.f1073oooOOO00.onError(Integer.parseInt(errorBean.getCode()), errorBean.getMsg());
                }
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(ErrorBean errorBean, Continuation<? super Unit> continuation) {
                return ((C0147oooOO0oo) create(errorBean, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public oooo0O00(int i, String str, String str2, int i2, int i3, String str3, String str4, Integer num, ArcResSdk.OnResultListener<ResultInfo> onResultListener, Continuation<? super oooo0O00> continuation) {
            super(2, continuation);
            this.f1064oooOO = i;
            this.f1067oooOOO00 = str;
            this.f1066oooOOO0 = str2;
            this.oooOOO0O = i2;
            this.oooOOO0o = i3;
            this.oooOOO = str3;
            this.oooOOOO0 = str4;
            this.oooOOOO = num;
            this.oooOOOOo = onResultListener;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new oooo0O00(this.f1064oooOO, this.f1067oooOOO00, this.f1066oooOOO0, this.oooOOO0O, this.oooOOO0o, this.oooOOO, this.oooOOOO0, this.oooOOOO, this.oooOOOOo, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f1065oooOO0oo;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow<ChannelResponse> flowOooOO0oo = RetrofitManager.INSTANCE.getArcResApiService().oooOO0oo(this.f1064oooOO, this.f1067oooOOO00, this.f1066oooOOO0, this.oooOOO0O, this.oooOOO0o, this.oooOOO, this.oooOOOO0, this.oooOOOO);
                C0147oooOO0oo c0147oooOO0oo = new C0147oooOO0oo(this.oooOOOOo, null);
                oooOO ooooo = new oooOO(this.oooOOOOo, null);
                this.f1065oooOO0oo = 1;
                if (oooOO.oooOOO0O.oooOO0oo(flowOooOO0oo, c0147oooOO0oo, ooooo, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((oooo0O00) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getVideoLib$2", f = "ArcResSdkImpl.kt", i = {}, l = {388}, m = "invokeSuspend", n = {}, s = {})
    public static final class oooo0O0O extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
        public final /* synthetic */ int f1074oooOO;

        /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
        public int f1075oooOO0oo;
        public final /* synthetic */ String oooOOO;

        /* JADX INFO: renamed from: oooOOO0, reason: collision with root package name */
        public final /* synthetic */ int f1076oooOOO0;

        /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
        public final /* synthetic */ String f1077oooOOO00;
        public final /* synthetic */ int oooOOO0O;
        public final /* synthetic */ String oooOOO0o;
        public final /* synthetic */ Integer oooOOOO;
        public final /* synthetic */ String oooOOOO0;
        public final /* synthetic */ String oooOOOOo;
        public final /* synthetic */ ArcResSdk.OnResultListener<ResultInfo> oooOOOo;
        public final /* synthetic */ Integer oooOOOo0;

        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getVideoLib$2$2", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class oooOO extends SuspendLambda implements Function2<ChannelResponse, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f1078oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f1079oooOO0oo;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<ResultInfo> f1080oooOOO00;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public oooOO(ArcResSdk.OnResultListener<ResultInfo> onResultListener, Continuation<? super oooOO> continuation) {
                super(2, continuation);
                this.f1080oooOOO00 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                oooOO ooooo = new oooOO(this.f1080oooOOO00, continuation);
                ooooo.f1078oooOO = obj;
                return ooooo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f1079oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                ChannelResponse channelResponse = (ChannelResponse) this.f1078oooOO;
                ResultInfo resultInfo = new ResultInfo(null, null, null, null, null, 31, null);
                resultInfo.setTotal(channelResponse != null ? Boxing.boxInt(channelResponse.getTotal()) : null);
                oooOOO0.oooOO0oo ooooo0oo = oooOOO0.oooOO0oo.f863oooOO0oo;
                resultInfo.setTags(ooooo0oo.oooOO(channelResponse != null ? channelResponse.getTags() : null));
                resultInfo.setVideoInfoList(ooooo0oo.oooOOO00(channelResponse != null ? channelResponse.getVideos() : null));
                this.f1080oooOOO00.onResult(resultInfo);
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(ChannelResponse channelResponse, Continuation<? super Unit> continuation) {
                return ((oooOO) create(channelResponse, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX INFO: renamed from: oooOOO00.oooOO0oo$oooo0O0O$oooOO0oo, reason: collision with other inner class name */
        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$getVideoLib$2$1", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class C0148oooOO0oo extends SuspendLambda implements Function2<ErrorBean, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f1081oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f1082oooOO0oo;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<ResultInfo> f1083oooOOO00;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0148oooOO0oo(ArcResSdk.OnResultListener<ResultInfo> onResultListener, Continuation<? super C0148oooOO0oo> continuation) {
                super(2, continuation);
                this.f1083oooOOO00 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C0148oooOO0oo c0148oooOO0oo = new C0148oooOO0oo(this.f1083oooOOO00, continuation);
                c0148oooOO0oo.f1081oooOO = obj;
                return c0148oooOO0oo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f1082oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                ErrorBean errorBean = (ErrorBean) this.f1081oooOO;
                if (errorBean != null) {
                    this.f1083oooOOO00.onError(Integer.parseInt(errorBean.getCode()), errorBean.getMsg());
                }
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(ErrorBean errorBean, Continuation<? super Unit> continuation) {
                return ((C0148oooOO0oo) create(errorBean, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public oooo0O0O(int i, String str, int i2, int i3, String str2, String str3, String str4, Integer num, String str5, Integer num2, ArcResSdk.OnResultListener<ResultInfo> onResultListener, Continuation<? super oooo0O0O> continuation) {
            super(2, continuation);
            this.f1074oooOO = i;
            this.f1077oooOOO00 = str;
            this.f1076oooOOO0 = i2;
            this.oooOOO0O = i3;
            this.oooOOO0o = str2;
            this.oooOOO = str3;
            this.oooOOOO0 = str4;
            this.oooOOOO = num;
            this.oooOOOOo = str5;
            this.oooOOOo0 = num2;
            this.oooOOOo = onResultListener;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new oooo0O0O(this.f1074oooOO, this.f1077oooOOO00, this.f1076oooOOO0, this.oooOOO0O, this.oooOOO0o, this.oooOOO, this.oooOOOO0, this.oooOOOO, this.oooOOOOo, this.oooOOOo0, this.oooOOOo, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f1075oooOO0oo;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow<ChannelResponse> flowOooOO0oo = RetrofitManager.INSTANCE.getArcResApiService().oooOO0oo(this.f1074oooOO, this.f1077oooOOO00, this.f1076oooOOO0, this.oooOOO0O, this.oooOOO0o, this.oooOOO, this.oooOOOO0, this.oooOOOO, this.oooOOOOo, this.oooOOOo0);
                C0148oooOO0oo c0148oooOO0oo = new C0148oooOO0oo(this.oooOOOo, null);
                oooOO ooooo = new oooOO(this.oooOOOo, null);
                this.f1075oooOO0oo = 1;
                if (oooOO.oooOOO0O.oooOO0oo(flowOooOO0oo, c0148oooOO0oo, ooooo, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((oooo0O0O) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$search$1", f = "ArcResSdkImpl.kt", i = {}, l = {492}, m = "invokeSuspend", n = {}, s = {})
    public static final class oooo0OO extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
        public final /* synthetic */ String f1084oooOO;

        /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
        public int f1085oooOO0oo;

        /* JADX INFO: renamed from: oooOOO0, reason: collision with root package name */
        public final /* synthetic */ int f1086oooOOO0;

        /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
        public final /* synthetic */ String f1087oooOOO00;
        public final /* synthetic */ int oooOOO0O;
        public final /* synthetic */ ArcResSdk.OnResultListener<ResultInfo> oooOOO0o;

        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$search$1$2", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class oooOO extends SuspendLambda implements Function2<ChannelResponse, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f1088oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f1089oooOO0oo;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<ResultInfo> f1090oooOOO00;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public oooOO(ArcResSdk.OnResultListener<ResultInfo> onResultListener, Continuation<? super oooOO> continuation) {
                super(2, continuation);
                this.f1090oooOOO00 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                oooOO ooooo = new oooOO(this.f1090oooOOO00, continuation);
                ooooo.f1088oooOO = obj;
                return ooooo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f1089oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                ChannelResponse channelResponse = (ChannelResponse) this.f1088oooOO;
                ResultInfo resultInfo = new ResultInfo(null, null, null, null, null, 31, null);
                resultInfo.setTotal(channelResponse != null ? Boxing.boxInt(channelResponse.getTotal()) : null);
                resultInfo.setVideoInfoList(oooOOO0.oooOO0oo.f863oooOO0oo.oooOOO00(channelResponse != null ? channelResponse.getVideos() : null));
                this.f1090oooOOO00.onResult(resultInfo);
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(ChannelResponse channelResponse, Continuation<? super Unit> continuation) {
                return ((oooOO) create(channelResponse, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX INFO: renamed from: oooOOO00.oooOO0oo$oooo0OO$oooOO0oo, reason: collision with other inner class name */
        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$search$1$1", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class C0149oooOO0oo extends SuspendLambda implements Function2<ErrorBean, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f1091oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f1092oooOO0oo;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<ResultInfo> f1093oooOOO00;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0149oooOO0oo(ArcResSdk.OnResultListener<ResultInfo> onResultListener, Continuation<? super C0149oooOO0oo> continuation) {
                super(2, continuation);
                this.f1093oooOOO00 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C0149oooOO0oo c0149oooOO0oo = new C0149oooOO0oo(this.f1093oooOOO00, continuation);
                c0149oooOO0oo.f1091oooOO = obj;
                return c0149oooOO0oo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f1092oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                ErrorBean errorBean = (ErrorBean) this.f1091oooOO;
                if (errorBean != null) {
                    this.f1093oooOOO00.onError(Integer.parseInt(errorBean.getCode()), errorBean.getMsg());
                }
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(ErrorBean errorBean, Continuation<? super Unit> continuation) {
                return ((C0149oooOO0oo) create(errorBean, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public oooo0OO(String str, String str2, int i, int i2, ArcResSdk.OnResultListener<ResultInfo> onResultListener, Continuation<? super oooo0OO> continuation) {
            super(2, continuation);
            this.f1084oooOO = str;
            this.f1087oooOOO00 = str2;
            this.f1086oooOOO0 = i;
            this.oooOOO0O = i2;
            this.oooOOO0o = onResultListener;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new oooo0OO(this.f1084oooOO, this.f1087oooOOO00, this.f1086oooOOO0, this.oooOOO0O, this.oooOOO0o, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f1085oooOO0oo;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow<ChannelResponse> flowOooOO0oo = RetrofitManager.INSTANCE.getArcResApiService().oooOO0oo(this.f1084oooOO, this.f1087oooOOO00, this.f1086oooOOO0, this.oooOOO0O);
                C0149oooOO0oo c0149oooOO0oo = new C0149oooOO0oo(this.oooOOO0o, null);
                oooOO ooooo = new oooOO(this.oooOOO0o, null);
                this.f1085oooOO0oo = 1;
                if (oooOO.oooOOO0O.oooOO0oo(flowOooOO0oo, c0149oooOO0oo, ooooo, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((oooo0OO) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$sendBugReport$1", f = "ArcResSdkImpl.kt", i = {}, l = {582}, m = "invokeSuspend", n = {}, s = {})
    public static final class oooo0OOO extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
        public final /* synthetic */ ArcResSdk.OnResultListener<String> f1094oooOO;

        /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
        public int f1095oooOO0oo;

        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$sendBugReport$1$2", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class oooOO extends SuspendLambda implements Function2<String, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f1096oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f1097oooOO0oo;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<String> f1098oooOOO00;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public oooOO(ArcResSdk.OnResultListener<String> onResultListener, Continuation<? super oooOO> continuation) {
                super(2, continuation);
                this.f1098oooOOO00 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                oooOO ooooo = new oooOO(this.f1098oooOOO00, continuation);
                ooooo.f1096oooOO = obj;
                return ooooo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f1097oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.f1098oooOOO00.onResult((String) this.f1096oooOO);
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(String str, Continuation<? super Unit> continuation) {
                return ((oooOO) create(str, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX INFO: renamed from: oooOOO00.oooOO0oo$oooo0OOO$oooOO0oo, reason: collision with other inner class name */
        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$sendBugReport$1$1", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class C0150oooOO0oo extends SuspendLambda implements Function2<ErrorBean, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f1099oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f1100oooOO0oo;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<String> f1101oooOOO00;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0150oooOO0oo(ArcResSdk.OnResultListener<String> onResultListener, Continuation<? super C0150oooOO0oo> continuation) {
                super(2, continuation);
                this.f1101oooOOO00 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C0150oooOO0oo c0150oooOO0oo = new C0150oooOO0oo(this.f1101oooOOO00, continuation);
                c0150oooOO0oo.f1099oooOO = obj;
                return c0150oooOO0oo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f1100oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                ErrorBean errorBean = (ErrorBean) this.f1099oooOO;
                if (errorBean != null) {
                    this.f1101oooOOO00.onError(Integer.parseInt(errorBean.getCode()), errorBean.getMsg());
                }
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(ErrorBean errorBean, Continuation<? super Unit> continuation) {
                return ((C0150oooOO0oo) create(errorBean, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public oooo0OOO(ArcResSdk.OnResultListener<String> onResultListener, Continuation<? super oooo0OOO> continuation) {
            super(2, continuation);
            this.f1094oooOO = onResultListener;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new oooo0OOO(this.f1094oooOO, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f1095oooOO0oo;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow<String> flowOooOO0oo = RetrofitManager.INSTANCE.getArcResBugApiService().oooOO0oo();
                C0150oooOO0oo c0150oooOO0oo = new C0150oooOO0oo(this.f1094oooOO, null);
                oooOO ooooo = new oooOO(this.f1094oooOO, null);
                this.f1095oooOO0oo = 1;
                if (oooOO.oooOOO0O.oooOO0oo(flowOooOO0oo, c0150oooOO0oo, ooooo, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((oooo0OOO) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$videoSearch$1", f = "ArcResSdkImpl.kt", i = {}, l = {690}, m = "invokeSuspend", n = {}, s = {})
    public static final class oooo0Oo0 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
        public final /* synthetic */ String f1102oooOO;

        /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
        public int f1103oooOO0oo;
        public final /* synthetic */ ArcResSdk.OnResultListener<VideoSearchBean> oooOOO;

        /* JADX INFO: renamed from: oooOOO0, reason: collision with root package name */
        public final /* synthetic */ int f1104oooOOO0;

        /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
        public final /* synthetic */ int f1105oooOOO00;
        public final /* synthetic */ int oooOOO0O;
        public final /* synthetic */ int oooOOO0o;

        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$videoSearch$1$2", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class oooOO extends SuspendLambda implements Function2<VideoSearchResponse, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f1106oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f1107oooOO0oo;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<VideoSearchBean> f1108oooOOO00;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public oooOO(ArcResSdk.OnResultListener<VideoSearchBean> onResultListener, Continuation<? super oooOO> continuation) {
                super(2, continuation);
                this.f1108oooOOO00 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                oooOO ooooo = new oooOO(this.f1108oooOOO00, continuation);
                ooooo.f1106oooOO = obj;
                return ooooo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntentData intentData;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f1107oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                VideoSearchResponse videoSearchResponse = (VideoSearchResponse) this.f1106oooOO;
                VideoSearchBean videoSearchBean = new VideoSearchBean(null, null, null, 7, null);
                videoSearchBean.setTotal(videoSearchResponse != null ? videoSearchResponse.getTotal() : null);
                oooOOO0.oooOO0oo ooooo0oo = oooOOO0.oooOO0oo.f863oooOO0oo;
                videoSearchBean.setVideoInfoList(ooooo0oo.oooOOO00(videoSearchResponse != null ? videoSearchResponse.getEpgs() : null));
                if (videoSearchResponse != null && (intentData = videoSearchResponse.getIntentData()) != null) {
                    List<CustomCategory> listComponent1 = intentData.component1();
                    List<VideoResponse> listComponent2 = intentData.component2();
                    List<GraphCategory> listComponent3 = intentData.component3();
                    Integer numComponent4 = intentData.component4();
                    Integer numComponent5 = intentData.component5();
                    List<TermQuery> listComponent6 = intentData.component6();
                    IntentDatas intentDatas = new IntentDatas(null, null, null, null, null, null, 63, null);
                    intentDatas.setCustomCategories(listComponent1);
                    intentDatas.setGraphCategories(listComponent3);
                    intentDatas.setIntentResultNum(numComponent4);
                    intentDatas.setMode(numComponent5);
                    intentDatas.setTermQuery(listComponent6);
                    intentDatas.setVideoInfo(ooooo0oo.oooOOO00(listComponent2));
                    videoSearchBean.setIntentData(intentDatas);
                }
                this.f1108oooOOO00.onResult(videoSearchBean);
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(VideoSearchResponse videoSearchResponse, Continuation<? super Unit> continuation) {
                return ((oooOO) create(videoSearchResponse, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX INFO: renamed from: oooOOO00.oooOO0oo$oooo0Oo0$oooOO0oo, reason: collision with other inner class name */
        @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.impl.ArcResSdkImpl$videoSearch$1$1", f = "ArcResSdkImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class C0151oooOO0oo extends SuspendLambda implements Function2<ErrorBean, Continuation<? super Unit>, Object> {

            /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
            public /* synthetic */ Object f1109oooOO;

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public int f1110oooOO0oo;

            /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
            public final /* synthetic */ ArcResSdk.OnResultListener<VideoSearchBean> f1111oooOOO00;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0151oooOO0oo(ArcResSdk.OnResultListener<VideoSearchBean> onResultListener, Continuation<? super C0151oooOO0oo> continuation) {
                super(2, continuation);
                this.f1111oooOOO00 = onResultListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C0151oooOO0oo c0151oooOO0oo = new C0151oooOO0oo(this.f1111oooOOO00, continuation);
                c0151oooOO0oo.f1109oooOO = obj;
                return c0151oooOO0oo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.f1110oooOO0oo != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                ErrorBean errorBean = (ErrorBean) this.f1109oooOO;
                if (errorBean != null) {
                    this.f1111oooOOO00.onError(Integer.parseInt(errorBean.getCode()), errorBean.getMsg());
                }
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
            public final Object invoke(ErrorBean errorBean, Continuation<? super Unit> continuation) {
                return ((C0151oooOO0oo) create(errorBean, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public oooo0Oo0(String str, int i, int i2, int i3, int i4, ArcResSdk.OnResultListener<VideoSearchBean> onResultListener, Continuation<? super oooo0Oo0> continuation) {
            super(2, continuation);
            this.f1102oooOO = str;
            this.f1105oooOOO00 = i;
            this.f1104oooOOO0 = i2;
            this.oooOOO0O = i3;
            this.oooOOO0o = i4;
            this.oooOOO = onResultListener;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new oooo0Oo0(this.f1102oooOO, this.f1105oooOOO00, this.f1104oooOOO0, this.oooOOO0O, this.oooOOO0o, this.oooOOO, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f1103oooOO0oo;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow<VideoSearchResponse> flowOooOO0oo = RetrofitManager.INSTANCE.getArcResApiService().oooOO0oo(this.f1102oooOO, this.f1105oooOOO00, this.f1104oooOOO0, this.oooOOO0O, this.oooOOO0o);
                C0151oooOO0oo c0151oooOO0oo = new C0151oooOO0oo(this.oooOOO, null);
                oooOO ooooo = new oooOO(this.oooOOO, null);
                this.f1103oooOO0oo = 1;
                if (oooOO.oooOOO0O.oooOO0oo(flowOooOO0oo, c0151oooOO0oo, ooooo, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((oooo0Oo0) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    @Deprecated(message = "Deprecated function, please use activateLicense（2.1 version） ")
    public void activate(String str, String str2, String str3, String str4, String str5, ArcResSdk.OnResultListener<Boolean> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        oooOO0oo(str, str2, str5, null, null);
        ActivateBodyBean activateBodyBean = new ActivateBodyBean();
        activateBodyBean.setSnSdk(str);
        activateBodyBean.setSnApp(str2);
        activateBodyBean.setVinSdk(str3);
        activateBodyBean.setVinApp(str4);
        activateBodyBean.setModelApp(str5);
        String strValueOf = String.valueOf(System.currentTimeMillis());
        HashMap map = new HashMap(16);
        map.put("X-Plt-Action", "vehicleLicenseActivateClient");
        map.put("X-Plt-Ver", "3.0");
        map.put("X-Plt-Ts", strValueOf);
        map.put("X-Plt-Ak", "5wo1db5j81ae");
        String strBuildNewVersionSignature = ArcSignatureUtils.buildNewVersionSignature(map, activateBodyBean);
        Intrinsics.checkNotNullExpressionValue(strBuildNewVersionSignature, "buildNewVersionSignature…eaders, activateBodyBean)");
        map.put("X-Plt-Signature", strBuildNewVersionSignature);
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new C0125oooOO0oo(strValueOf, strBuildNewVersionSignature, activateBodyBean, listener, this, null), 3, null);
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void activateLicense(String sn, String vin, String ak, String sk, String vehicleModelId, ArcResSdk.OnResultListener<Boolean> listener) {
        Intrinsics.checkNotNullParameter(sn, "sn");
        Intrinsics.checkNotNullParameter(vin, "vin");
        Intrinsics.checkNotNullParameter(ak, "ak");
        Intrinsics.checkNotNullParameter(sk, "sk");
        Intrinsics.checkNotNullParameter(vehicleModelId, "vehicleModelId");
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (oooOO0oo(sn, vin, vehicleModelId, ak, sk)) {
            ActivateBean activateBean = new ActivateBean(vin, sn, "DHIQYAndroidAPP1", vehicleModelId);
            String strValueOf = String.valueOf(System.currentTimeMillis());
            HashMap map = new HashMap(16);
            map.put("X-Plt-Action", "vehicleLicenseActivateClient");
            map.put("X-Plt-Ver", "3.0");
            map.put("X-Plt-Ts", strValueOf);
            map.put("X-Plt-Ak", ak);
            String strBuildVersionSignature = ArcSignatureUtils.buildVersionSignature((HashMap<String, String>) map, sk, ak, activateBean);
            Intrinsics.checkNotNullExpressionValue(strBuildVersionSignature, "buildVersionSignature(he…rs, sk, ak, activateBean)");
            map.put("X-Plt-Signature", strBuildVersionSignature);
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new oooOO(strValueOf, ak, strBuildVersionSignature, activateBean, listener, this, null), 3, null);
        }
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void addInterceptor(Interceptor interceptor) {
        Intrinsics.checkNotNullParameter(interceptor, "interceptor");
        RetrofitManager.INSTANCE.addInterceptor(interceptor);
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void createToken(String ak, String sk, ArcResSdk.OnResultListener<CreateTokenResponse.TokenInfo> listener) {
        Intrinsics.checkNotNullParameter(ak, "ak");
        Intrinsics.checkNotNullParameter(sk, "sk");
        Intrinsics.checkNotNullParameter(listener, "listener");
        CreateTokenBodyBean createTokenBodyBean = new CreateTokenBodyBean();
        String strValueOf = String.valueOf(System.currentTimeMillis());
        HashMap map = new HashMap(16);
        map.put("X-Plt-Action", "commonAuthCreateTokenByAK");
        map.put("X-Plt-Ver", "3.0");
        map.put("X-Plt-Ts", strValueOf);
        map.put("X-Plt-Ak", ak);
        String strBuildVersionSignature = ArcSignatureUtils.buildVersionSignature((HashMap<String, String>) map, sk, ak, createTokenBodyBean);
        Intrinsics.checkNotNullExpressionValue(strBuildVersionSignature, "buildVersionSignature(he… ak, createTokenBodyBean)");
        map.put("X-Plt-Signature", strBuildVersionSignature);
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new oooOOO00(strValueOf, ak, strBuildVersionSignature, createTokenBodyBean, listener, this, null), 3, null);
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void getArcBannerList(String ak, String sk, int i, ArcResSdk.OnResultListener<List<VideoInfo>> listener) {
        Intrinsics.checkNotNullParameter(ak, "ak");
        Intrinsics.checkNotNullParameter(sk, "sk");
        Intrinsics.checkNotNullParameter(listener, "listener");
        String strValueOf = String.valueOf(System.currentTimeMillis());
        HashMap map = new HashMap(16);
        map.put("X-Plt-Action", "commonBfeLoadBannerList");
        map.put("X-Plt-Ver", "3.0");
        map.put("X-Plt-Ts", strValueOf);
        map.put("X-Plt-Ak", ak);
        map.put(CmcdConfiguration.KEY_CONTENT_ID, i + "");
        String strBuildVersionSignature = ArcSignatureUtils.buildVersionSignature(map, sk, ak);
        Intrinsics.checkNotNullExpressionValue(strBuildVersionSignature, "buildVersionSignature(headers, sk, ak)");
        map.put("X-Plt-Signature", strBuildVersionSignature);
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new oooOOO0(strValueOf, ak, strBuildVersionSignature, i, listener, null), 3, null);
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public String getAuthorization() {
        return StringsKt.replace$default(this.f865oooOO0oo, "<token>", this.f864oooOO, false, 4, (Object) null);
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void getCarLicenseInfo(String sn, String vin, String ak, String sk, String vehicleModelId, ArcResSdk.OnResultListener<AuthInfo> listener) {
        Intrinsics.checkNotNullParameter(sn, "sn");
        Intrinsics.checkNotNullParameter(vin, "vin");
        Intrinsics.checkNotNullParameter(ak, "ak");
        Intrinsics.checkNotNullParameter(sk, "sk");
        Intrinsics.checkNotNullParameter(vehicleModelId, "vehicleModelId");
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (oooOO0oo(sn, vin, vehicleModelId, ak, sk)) {
            ActivateBean activateBean = new ActivateBean(vin, sn, "DHIQYAndroidAPP1", vehicleModelId);
            String strValueOf = String.valueOf(System.currentTimeMillis());
            HashMap map = new HashMap(16);
            map.put("X-Plt-Action", "vehicleLicenseGetCarLicenseInfo");
            map.put("X-Plt-Ver", "3.0");
            map.put("X-Plt-Ts", strValueOf);
            map.put("X-Plt-Ak", ak);
            String strBuildVersionSignature = ArcSignatureUtils.buildVersionSignature((HashMap<String, String>) map, sk, ak, activateBean);
            Intrinsics.checkNotNullExpressionValue(strBuildVersionSignature, "buildVersionSignature(he…rs, sk, ak, activateBean)");
            map.put("X-Plt-Signature", strBuildVersionSignature);
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new oooOOO0O(strValueOf, ak, strBuildVersionSignature, activateBean, listener, null), 3, null);
        }
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void getDetail(String tvId, ArcResSdk.OnResultListener<VideoInfo> listener) {
        Intrinsics.checkNotNullParameter(tvId, "tvId");
        Intrinsics.checkNotNullParameter(listener, "listener");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new oooOOO(tvId, listener, null), 3, null);
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void getDolby(ArcResSdk.OnResultListener<ResultInfo> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        getDolby(-1, listener);
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void getEpisodes(String tvId, ArcResSdk.OnResultListener<ResultInfo> listener) {
        Intrinsics.checkNotNullParameter(tvId, "tvId");
        Intrinsics.checkNotNullParameter(listener, "listener");
        getEpisodes(tvId, 1, 60, listener);
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void getHotWord(ArcResSdk.OnResultListener<List<SuggestInfo>> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new oooOOOOo(listener, null), 3, null);
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void getIntentSearchList(String termQuery, int i, int i2, int i3, ArcResSdk.OnResultListener<List<VideoInfo>> listener) {
        Intrinsics.checkNotNullParameter(termQuery, "termQuery");
        Intrinsics.checkNotNullParameter(listener, "listener");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new oooOo00(termQuery, i, i2, i3, listener, null), 3, null);
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void getRank(ArcResSdk.OnResultListener<List<VideoInfo>> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        getRank(-1, listener);
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void getRecommend(String deviceId, ArcResSdk.OnResultListener<List<VideoInfo>> listener) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(listener, "listener");
        getRecommend(deviceId, 1, 30, listener);
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void getRelevantRecommend(String albumId, ArcResSdk.OnResultListener<List<VideoInfo>> listener) {
        Intrinsics.checkNotNullParameter(albumId, "albumId");
        Intrinsics.checkNotNullParameter(listener, "listener");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new oooo00(albumId, listener, null), 3, null);
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void getRelevantRecommendV2(String qipuId, String deviceId, int i, ArcResSdk.OnResultListener<List<VideoInfo>> listener) {
        Intrinsics.checkNotNullParameter(qipuId, "qipuId");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(listener, "listener");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new oooo00o0(qipuId, deviceId, i, listener, null), 3, null);
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void getResource(String resourceId, ArcResSdk.OnResultListener<List<VideoInfo>> listener) {
        Intrinsics.checkNotNullParameter(resourceId, "resourceId");
        Intrinsics.checkNotNullParameter(listener, "listener");
        getResource(resourceId, null, null, listener);
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public String getSdkVersion() {
        return "2.0.0.20231207";
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void getSuggest(String key, ArcResSdk.OnResultListener<List<SuggestInfo>> listener) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(listener, "listener");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new oooo0(key, listener, null), 3, null);
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void getVideoByTag(int i, String deviceId, String str, int i2, int i3, String requireTags, String str2, Integer num, ArcResSdk.OnResultListener<ResultInfo> listener) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(requireTags, "requireTags");
        Intrinsics.checkNotNullParameter(listener, "listener");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain()), null, null, new oooo0O00(i, deviceId, str, i2, i3, requireTags, str2, num, listener, null), 3, null);
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void getVideoLib(int i, String deviceId, ArcResSdk.OnResultListener<ResultInfo> listener) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(listener, "listener");
        getVideoLib(i, deviceId, null, listener);
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void register(String vin, String deviceUUId, long j, ArcResSdk.OnResultListener<RegisterInfo> listener) {
        Intrinsics.checkNotNullParameter(vin, "vin");
        Intrinsics.checkNotNullParameter(deviceUUId, "deviceUUId");
        Intrinsics.checkNotNullParameter(listener, "listener");
        register(vin, deviceUUId, j, null, listener);
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public boolean registerSync(String vin, String deviceUUId, long j, String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(vin, "vin");
        Intrinsics.checkNotNullParameter(deviceUUId, "deviceUUId");
        try {
            Response<DeviceSession> responseExecute = RetrofitManager.INSTANCE.getArcResApiService().oooOO0oo(new RegisterRequestBean(vin, deviceUUId, j, str, str2, str3)).execute();
            if (responseExecute.isSuccessful()) {
                DeviceSession deviceSessionBody = responseExecute.body();
                String token = deviceSessionBody != null ? deviceSessionBody.getToken() : null;
                if (!(token == null || token.length() == 0)) {
                    try {
                        this.f864oooOO = String.valueOf(deviceSessionBody != null ? deviceSessionBody.getToken() : null);
                        return true;
                    } catch (Exception e) {
                        e = e;
                        e.printStackTrace();
                        return false;
                    }
                }
            }
        } catch (Exception e2) {
            e = e2;
        }
        return false;
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void search(String key, ArcResSdk.OnResultListener<ResultInfo> listener) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(listener, "listener");
        search(key, 1, 60, listener);
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void sendBugReport(ArcResSdk.OnResultListener<String> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new oooo0OOO(listener, null), 3, null);
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void setArcStg(boolean z) {
        RetrofitManager.INSTANCE.setSTG_ENABLE(z);
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void setArcUserAgent(String arcUserAgent) {
        Intrinsics.checkNotNullParameter(arcUserAgent, "arcUserAgent");
        RetrofitManager.INSTANCE.setArcUserAgent(arcUserAgent);
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void setRegisterInfo(String vin, String deviceUUId) {
        Intrinsics.checkNotNullParameter(vin, "vin");
        Intrinsics.checkNotNullParameter(deviceUUId, "deviceUUId");
        RetrofitManager.INSTANCE.setRegisterInfo(vin, deviceUUId);
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public String token() {
        return this.f864oooOO;
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void videoSearch(String key, int i, int i2, int i3, int i4, ArcResSdk.OnResultListener<VideoSearchBean> listener) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(listener, "listener");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new oooo0Oo0(key, i2, i3, i, i4, listener, null), 3, null);
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void getDolby(int i, ArcResSdk.OnResultListener<ResultInfo> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        getDolby(-1, 1, 20, listener);
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void getEpisodes(String tvId, int i, int i2, ArcResSdk.OnResultListener<ResultInfo> listener) {
        Intrinsics.checkNotNullParameter(tvId, "tvId");
        Intrinsics.checkNotNullParameter(listener, "listener");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new oooOOOO(tvId, i, i2, listener, null), 3, null);
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void getHotWord(ArcResSdk.OnResultListener<List<SuggestInfo>> listener, int i) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new oooOo000(i, listener, null), 3, null);
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void getRank(int i, ArcResSdk.OnResultListener<List<VideoInfo>> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        getRank(i, 1, 20, listener);
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void getRecommend(String deviceId, int i, int i2, ArcResSdk.OnResultListener<List<VideoInfo>> listener) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(listener, "listener");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new oooo000O(deviceId, i, i2, listener, null), 3, null);
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void getResource(String resourceId, Integer num, Integer num2, ArcResSdk.OnResultListener<List<VideoInfo>> listener) {
        Intrinsics.checkNotNullParameter(resourceId, "resourceId");
        Intrinsics.checkNotNullParameter(listener, "listener");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new oooo00OO(resourceId, num, num2, listener, null), 3, null);
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void getVideoLib(int i, String deviceId, String str, ArcResSdk.OnResultListener<ResultInfo> listener) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(listener, "listener");
        getVideoLib(i, deviceId, 1, 60, str, listener);
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void register(String vin, String deviceUUId, long j, String str, ArcResSdk.OnResultListener<RegisterInfo> listener) {
        Intrinsics.checkNotNullParameter(vin, "vin");
        Intrinsics.checkNotNullParameter(deviceUUId, "deviceUUId");
        Intrinsics.checkNotNullParameter(listener, "listener");
        register(vin, deviceUUId, j, str, null, listener);
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void search(String key, int i, int i2, ArcResSdk.OnResultListener<ResultInfo> listener) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(listener, "listener");
        search(key, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE, i, i2, listener);
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void videoSearch(String key, ArcResSdk.OnResultListener<VideoSearchBean> listener) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(listener, "listener");
        videoSearch(key, 1, 1, 60, 30, listener);
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void getDolby(int i, int i2, int i3, ArcResSdk.OnResultListener<ResultInfo> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new oooOOOO0(i, i2, i3, listener, null), 3, null);
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void getRank(int i, int i2, int i3, ArcResSdk.OnResultListener<List<VideoInfo>> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        getRank(i, i2, i3, false, listener);
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void getVideoLib(int i, String deviceId, int i2, int i3, ArcResSdk.OnResultListener<ResultInfo> listener) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(listener, "listener");
        getVideoLib(i, deviceId, i2, i3, null, listener);
    }

    public final boolean oooOO0oo(String str, String str2, String str3, String str4, String str5) {
        if (TextUtils.isEmpty(str)) {
            Log.d(this.f866oooOOO00, "checkParams: vin must be not empty ");
            return false;
        }
        if (TextUtils.isEmpty(str2)) {
            Log.d(this.f866oooOOO00, "checkParams: sn must be not empty ");
            return false;
        }
        if (TextUtils.isEmpty(str3)) {
            Log.d(this.f866oooOOO00, "checkParams: vehicleModelId must be not empty ");
            return false;
        }
        if (TextUtils.isEmpty(str4)) {
            Log.d(this.f866oooOOO00, "checkParams: ak must be not empty ");
            return false;
        }
        if (!TextUtils.isEmpty(str5)) {
            return true;
        }
        Log.d(this.f866oooOOO00, "checkParams: sk must be not empty ");
        return false;
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void register(String vin, String deviceUUId, long j, String str, String str2, ArcResSdk.OnResultListener<RegisterInfo> listener) {
        Intrinsics.checkNotNullParameter(vin, "vin");
        Intrinsics.checkNotNullParameter(deviceUUId, "deviceUUId");
        Intrinsics.checkNotNullParameter(listener, "listener");
        register(vin, deviceUUId, j, str, str2, null, listener);
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void search(String key, String mode, int i, int i2, ArcResSdk.OnResultListener<ResultInfo> listener) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(listener, "listener");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new oooo0OO(key, mode, i, i2, listener, null), 3, null);
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void getRank(int i, int i2, int i3, boolean z, ArcResSdk.OnResultListener<List<VideoInfo>> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        getRank(i, i2, i3, z, 0, listener);
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void register(String vin, String deviceUUId, long j, String str, String str2, String str3, ArcResSdk.OnResultListener<RegisterInfo> listener) {
        Intrinsics.checkNotNullParameter(vin, "vin");
        Intrinsics.checkNotNullParameter(deviceUUId, "deviceUUId");
        Intrinsics.checkNotNullParameter(listener, "listener");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new oooo0O(new RegisterRequestBean(vin, deviceUUId, System.currentTimeMillis(), str, str2, str3), this, listener, null), 3, null);
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void getRank(int i, int i2, int i3, boolean z, int i4, ArcResSdk.OnResultListener<List<VideoInfo>> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new oooo0000(i, z, i4, i2, i3, listener, null), 3, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v4, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r14v4, types: [T, java.lang.String] */
    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void getVideoLib(int i, String deviceId, int i2, int i3, String str, ArcResSdk.OnResultListener<ResultInfo> listener) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(listener, "listener");
        String str2 = i == 2 ? "s-4" : "s-11";
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = str2;
        if (str != null) {
            objectRef.element = ((String) str2) + ',' + str;
        }
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new oooo0O0(i, deviceId, i2, i3, objectRef, listener, null), 3, null);
    }

    @Override // com.arcvideo.ivi.res.sdk.ArcResSdk
    public void getVideoLib(int i, String deviceId, int i2, int i3, String str, String requireTags, String str2, Integer num, String str3, Integer num2, ArcResSdk.OnResultListener<ResultInfo> listener) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(requireTags, "requireTags");
        Intrinsics.checkNotNullParameter(listener, "listener");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new oooo0O0O(i, deviceId, i2, i3, str, requireTags, str2, num, str3, num2, listener, null), 3, null);
    }
}
