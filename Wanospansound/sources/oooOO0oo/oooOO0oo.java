package oooOO0oo;

import com.arcvideo.ivi.res.sdk.flow.CallFlowKt;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import retrofit2.Call;
import retrofit2.CallAdapter;

/* JADX INFO: loaded from: classes3.dex */
public final class oooOO0oo<R> implements CallAdapter<R, Flow<? extends R>> {

    /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
    public final boolean f856oooOO;

    /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
    public final Type f857oooOO0oo;

    /* JADX INFO: renamed from: oooOO0oo.oooOO0oo$oooOO0oo, reason: collision with other inner class name */
    @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.flow.FlowCallAdapter$callFlow$1", f = "FlowCallAdapter.kt", i = {}, l = {29}, m = "invokeSuspend", n = {}, s = {})
    public static final class C0121oooOO0oo extends SuspendLambda implements Function2<ProducerScope<? super R>, Continuation<? super Unit>, Object> {

        /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
        public /* synthetic */ Object f858oooOO;

        /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
        public int f859oooOO0oo;

        /* JADX INFO: renamed from: oooOOO0, reason: collision with root package name */
        public final /* synthetic */ boolean f860oooOOO0;

        /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
        public final /* synthetic */ AtomicBoolean f861oooOOO00;
        public final /* synthetic */ Call<R> oooOOO0O;

        /* JADX INFO: renamed from: oooOO0oo.oooOO0oo$oooOO0oo$oooOO0oo, reason: collision with other inner class name */
        public static final class C0122oooOO0oo extends Lambda implements Function0<Unit> {

            /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
            public final /* synthetic */ Call<R> f862oooOO0oo;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0122oooOO0oo(Call<R> call) {
                super(0);
                this.f862oooOO0oo = call;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                oooOO0oo();
                return Unit.INSTANCE;
            }

            public final void oooOO0oo() {
                this.f862oooOO0oo.cancel();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C0121oooOO0oo(AtomicBoolean atomicBoolean, boolean z, Call<R> call, Continuation<? super C0121oooOO0oo> continuation) {
            super(2, continuation);
            this.f861oooOOO00 = atomicBoolean;
            this.f860oooOOO0 = z;
            this.oooOOO0O = call;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C0121oooOO0oo c0121oooOO0oo = new C0121oooOO0oo(this.f861oooOOO00, this.f860oooOOO0, this.oooOOO0O, continuation);
            c0121oooOO0oo.f858oooOO = obj;
            return c0121oooOO0oo;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f859oooOO0oo;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ProducerScope producerScope = (ProducerScope) this.f858oooOO;
                if (this.f861oooOOO00.compareAndSet(false, true)) {
                    if (this.f860oooOOO0) {
                        CallFlowKt.callEnqueueFlow(producerScope, this.oooOOO0O);
                    } else {
                        CallFlowKt.oooOO0oo(producerScope, this.oooOOO0O);
                    }
                    C0122oooOO0oo c0122oooOO0oo = new C0122oooOO0oo(this.oooOOO0O);
                    this.f859oooOO0oo = 1;
                    if (ProduceKt.awaitClose(producerScope, c0122oooOO0oo, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
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
        public final Object invoke(ProducerScope<? super R> producerScope, Continuation<? super Unit> continuation) {
            return ((C0121oooOO0oo) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    public oooOO0oo(Type responseType, boolean z) {
        Intrinsics.checkNotNullParameter(responseType, "responseType");
        this.f857oooOO0oo = responseType;
        this.f856oooOO = z;
    }

    @Override // retrofit2.CallAdapter
    /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
    public Flow<R> adapt(Call<R> call) {
        Intrinsics.checkNotNullParameter(call, "call");
        return oooOO0oo(call, this.f856oooOO);
    }

    @Override // retrofit2.CallAdapter
    public Type responseType() {
        return this.f857oooOO0oo;
    }

    public final <R> Flow<R> oooOO0oo(Call<R> call, boolean z) {
        return FlowKt.callbackFlow(new C0121oooOO0oo(new AtomicBoolean(false), z, call, null));
    }
}
