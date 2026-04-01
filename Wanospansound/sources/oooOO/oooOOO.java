package oooOO;

import android.util.Log;
import com.arcvideo.ivi.res.sdk.ArcResSdk;
import com.wanos.media.ui.advertise.WanosJsBridge;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: loaded from: classes3.dex */
public final class oooOOO implements Interceptor {

    /* JADX INFO: renamed from: oooOO, reason: collision with root package name */
    public String f839oooOO;

    /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
    public String f840oooOO0oo;

    public oooOOO(String vin, String uuid) {
        Intrinsics.checkNotNullParameter(vin, "vin");
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        this.f840oooOO0oo = vin;
        this.f839oooOO = uuid;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Request request = chain.request();
        String url = request.url().getUrl();
        Log.d("[ IQYDataServiceImpl ]", "#intercept: " + url);
        if (StringsKt.contains$default((CharSequence) url, (CharSequence) "/api/register", false, 2, (Object) null)) {
            return chain.proceed(chain.request());
        }
        ArcResSdk.Companion companion = ArcResSdk.Companion;
        if (Intrinsics.areEqual(companion.getInstance().token(), WanosJsBridge.METHOD_KEY_TOKEN)) {
            synchronized (this) {
                if (Intrinsics.areEqual(companion.getInstance().token(), WanosJsBridge.METHOD_KEY_TOKEN)) {
                    Log.d("[ IQYDataServiceImpl ]", "tokenInterceptor---register");
                    companion.getInstance().registerSync(this.f840oooOO0oo, this.f839oooOO, System.currentTimeMillis(), "", null, null);
                }
                Unit unit = Unit.INSTANCE;
            }
        }
        return chain.proceed(request.newBuilder().addHeader("Authorization", companion.getInstance().getAuthorization()).build());
    }
}
