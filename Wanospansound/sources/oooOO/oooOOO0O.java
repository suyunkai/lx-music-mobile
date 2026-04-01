package oooOO;

import android.util.Log;
import com.arcvideo.ivi.res.sdk.data.ErrorBean;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: loaded from: classes3.dex */
public final class oooOOO0O {

    /* JADX INFO: Add missing generic type declarations: [T] */
    @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.http.FlowManagerKt$onCollect$2", f = "FlowManager.kt", i = {}, l = {26}, m = "invokeSuspend", n = {}, s = {})
    public static final class oooOO<T> extends SuspendLambda implements Function3<FlowCollector<? super T>, Throwable, Continuation<? super Unit>, Object> {

        /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
        public /* synthetic */ Object f842oooOO;

        /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
        public int f843oooOO0oo;

        /* JADX INFO: renamed from: oooOOO0, reason: collision with root package name */
        public final /* synthetic */ Function2<ErrorBean, Continuation<? super Unit>, Object> f844oooOOO0;

        /* JADX INFO: renamed from: oooOOO00, reason: collision with root package name */
        public final /* synthetic */ String f845oooOOO00;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public oooOO(String str, Function2<? super ErrorBean, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super oooOO> continuation) {
            super(3, continuation);
            this.f845oooOOO00 = str;
            this.f844oooOOO0 = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f843oooOO0oo;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Throwable th = (Throwable) this.f842oooOO;
                Log.d(this.f845oooOOO00, "catch: onCollect e:" + th);
                ErrorBean errorBeanOooOO0oo = oooOO.oooOOO0.f841oooOO0oo.oooOO0oo(th);
                Function2<ErrorBean, Continuation<? super Unit>, Object> function2 = this.f844oooOOO0;
                this.f843oooOO0oo = 1;
                if (function2.invoke(errorBeanOooOO0oo, this) == coroutine_suspended) {
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

        @Override // kotlin.jvm.functions.Function3
        /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
        public final Object invoke(FlowCollector<? super T> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            oooOO ooooo = new oooOO(this.f845oooOOO00, this.f844oooOOO0, continuation);
            ooooo.f842oooOO = th;
            return ooooo.invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    public static final class oooOO0oo<T> implements FlowCollector<T> {

        /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
        public final /* synthetic */ Function2 f846oooOO;

        /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
        public final /* synthetic */ String f847oooOO0oo;

        public oooOO0oo(String str, Function2 function2) {
            this.f847oooOO0oo = str;
            this.f846oooOO = function2;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public Object emit(T t, Continuation<? super Unit> continuation) {
            Log.d(this.f847oooOO0oo, "collect:onCollect ");
            Function2 function2 = this.f846oooOO;
            InlineMarker.mark(6);
            Object objInvoke = function2.invoke(t, continuation);
            InlineMarker.mark(7);
            return objInvoke == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objInvoke : Unit.INSTANCE;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.http.FlowManagerKt$onCollect$4", f = "FlowManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    public static final class oooOOO0<T> extends SuspendLambda implements Function2<FlowCollector<? super T>, Continuation<? super Unit>, Object> {

        /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
        public final /* synthetic */ String f848oooOO;

        /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
        public int f849oooOO0oo;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public oooOOO0(String str, Continuation<? super oooOOO0> continuation) {
            super(2, continuation);
            this.f848oooOO = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new oooOOO0(this.f848oooOO, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.f849oooOO0oo != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Log.d(this.f848oooOO, "onStart: onCollect");
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
        public final Object invoke(FlowCollector<? super T> flowCollector, Continuation<? super Unit> continuation) {
            return ((oooOOO0) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.http.FlowManagerKt$onCollect$3", f = "FlowManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    public static final class oooOOO00<T> extends SuspendLambda implements Function2<T, Continuation<? super Unit>, Object> {

        /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
        public final /* synthetic */ String f850oooOO;

        /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
        public int f851oooOO0oo;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public oooOOO00(String str, Continuation<? super oooOOO00> continuation) {
            super(2, continuation);
            this.f850oooOO = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new oooOOO00(this.f850oooOO, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.f851oooOO0oo != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Log.d(this.f850oooOO, "onEach: onCollect");
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
        public final Object invoke(T t, Continuation<? super Unit> continuation) {
            return ((oooOOO00) create(t, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: oooOO.oooOOO0O$oooOOO0O, reason: collision with other inner class name */
    @DebugMetadata(c = "com.arcvideo.ivi.res.sdk.http.FlowManagerKt$onCollect$5", f = "FlowManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    public static final class C0120oooOOO0O<T> extends SuspendLambda implements Function3<FlowCollector<? super T>, Throwable, Continuation<? super Unit>, Object> {

        /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
        public final /* synthetic */ String f852oooOO;

        /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
        public int f853oooOO0oo;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C0120oooOOO0O(String str, Continuation<? super C0120oooOOO0O> continuation) {
            super(3, continuation);
            this.f852oooOO = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.f853oooOO0oo != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Log.d(this.f852oooOO, "onCompletion: onCollect");
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function3
        /* JADX INFO: renamed from: oooOO0oo, reason: merged with bridge method [inline-methods] */
        public final Object invoke(FlowCollector<? super T> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            return new C0120oooOOO0O(this.f852oooOO, continuation).invokeSuspend(Unit.INSTANCE);
        }
    }

    public static final <T> Object oooOO0oo(Flow<? extends T> flow, Function2<? super ErrorBean, ? super Continuation<? super Unit>, ? extends Object> function2, Function2<? super T, ? super Continuation<? super Unit>, ? extends Object> function22, Continuation<? super Unit> continuation) {
        Object objCollect = FlowKt.onCompletion(FlowKt.onStart(FlowKt.onEach(FlowKt.m2248catch(FlowKt.flowOn(flow, Dispatchers.getIO()), new oooOO("FlowManager", function2, null)), new oooOOO00("FlowManager", null)), new oooOOO0("FlowManager", null)), new C0120oooOOO0O("FlowManager", null)).collect(new oooOO0oo("FlowManager", function22), continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }
}
