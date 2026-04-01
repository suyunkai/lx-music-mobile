package oooOO0oo;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

/* JADX INFO: loaded from: classes3.dex */
public final class oooOO extends CallAdapter.Factory {

    /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
    public static final oooOO0oo f854oooOO = new oooOO0oo(null);

    /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
    public boolean f855oooOO0oo;

    public static final class oooOO0oo {
        public oooOO0oo() {
        }

        public /* synthetic */ oooOO0oo(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final oooOO oooOO0oo() {
            return new oooOO(false, null);
        }
    }

    public oooOO(boolean z) {
        this.f855oooOO0oo = z;
    }

    public /* synthetic */ oooOO(boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(z);
    }

    @Override // retrofit2.CallAdapter.Factory
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        Intrinsics.checkNotNullParameter(returnType, "returnType");
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        Intrinsics.checkNotNullParameter(retrofit, "retrofit");
        if (!Intrinsics.areEqual(CallAdapter.Factory.getRawType(returnType), Flow.class)) {
            return null;
        }
        Type observableType = CallAdapter.Factory.getParameterUpperBound(0, (ParameterizedType) returnType);
        Intrinsics.checkNotNullExpressionValue(observableType, "observableType");
        return new oooOO0oo.oooOO0oo(observableType, this.f855oooOO0oo);
    }
}
