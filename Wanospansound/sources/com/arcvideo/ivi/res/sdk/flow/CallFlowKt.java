package com.arcvideo.ivi.res.sdk.flow;

import java.util.concurrent.CancellationException;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.ExceptionsKt;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.ChannelsKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.SendChannel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* JADX INFO: loaded from: classes.dex */
public final class CallFlowKt {

    /* JADX INFO: Add missing generic type declarations: [R] */
    public static final class oooOO0oo<R> implements Callback<R> {

        /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
        public final /* synthetic */ ProducerScope<R> f14oooOO0oo;

        /* JADX WARN: Multi-variable type inference failed */
        public oooOO0oo(ProducerScope<? super R> producerScope) {
            this.f14oooOO0oo = producerScope;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<R> call, Throwable throwable) {
            Intrinsics.checkNotNullParameter(call, "call");
            Intrinsics.checkNotNullParameter(throwable, "throwable");
            CoroutineScopeKt.cancel(this.f14oooOO0oo, ExceptionsKt.CancellationException(throwable.getLocalizedMessage(), throwable));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<R> call, Response<R> response) {
            Intrinsics.checkNotNullParameter(call, "call");
            Intrinsics.checkNotNullParameter(response, "response");
            CallFlowKt.oooOO0oo(this.f14oooOO0oo, response);
        }
    }

    public static final <R> void callEnqueueFlow(ProducerScope<? super R> producerScope, Call<R> call) {
        Intrinsics.checkNotNullParameter(producerScope, "<this>");
        Intrinsics.checkNotNullParameter(call, "call");
        try {
            call.enqueue(new oooOO0oo(producerScope));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static final <R> void oooOO0oo(ProducerScope<? super R> producerScope, Call<R> call) {
        Intrinsics.checkNotNullParameter(producerScope, "<this>");
        Intrinsics.checkNotNullParameter(call, "call");
        try {
            Response<R> responseExecute = call.execute();
            Intrinsics.checkNotNullExpressionValue(responseExecute, "call.execute()");
            oooOO0oo(producerScope, responseExecute);
        } catch (Throwable th) {
            CoroutineScopeKt.cancel(producerScope, ExceptionsKt.CancellationException(th.getLocalizedMessage(), th));
        }
    }

    public static final <R> void oooOO0oo(ProducerScope<? super R> producerScope, Response<R> response) {
        Intrinsics.checkNotNullParameter(producerScope, "<this>");
        Intrinsics.checkNotNullParameter(response, "response");
        try {
            boolean z = true;
            if (response.isSuccessful()) {
                R rBody = response.body();
                if (rBody != null && response.code() != 204) {
                    Object objTrySendBlocking = ChannelsKt.trySendBlocking(producerScope, rBody);
                    if (!(objTrySendBlocking instanceof ChannelResult.Failed)) {
                        SendChannel.DefaultImpls.close$default(producerScope, null, 1, null);
                    }
                    if (objTrySendBlocking instanceof ChannelResult.Closed) {
                        Throwable thM2234exceptionOrNullimpl = ChannelResult.m2234exceptionOrNullimpl(objTrySendBlocking);
                        CoroutineScopeKt.cancel(producerScope, ExceptionsKt.CancellationException(thM2234exceptionOrNullimpl != null ? thM2234exceptionOrNullimpl.getLocalizedMessage() : null, thM2234exceptionOrNullimpl));
                    }
                    if (objTrySendBlocking instanceof ChannelResult.Failed) {
                        Throwable thM2234exceptionOrNullimpl2 = ChannelResult.m2234exceptionOrNullimpl(objTrySendBlocking);
                        CoroutineScopeKt.cancel(producerScope, ExceptionsKt.CancellationException(thM2234exceptionOrNullimpl2 != null ? thM2234exceptionOrNullimpl2.getLocalizedMessage() : null, thM2234exceptionOrNullimpl2));
                        return;
                    }
                    return;
                }
                CoroutineScopeKt.cancel(producerScope, new CancellationException("HTTP status code: " + response.code()));
                return;
            }
            ResponseBody responseBodyErrorBody = response.errorBody();
            String strString = responseBodyErrorBody != null ? responseBodyErrorBody.string() : null;
            if (strString != null && strString.length() != 0) {
                z = false;
            }
            if (z) {
                strString = response.message();
            }
            if (strString == null) {
                strString = "unknown error";
            }
            CoroutineScopeKt.cancel(producerScope, new CancellationException(strString));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
