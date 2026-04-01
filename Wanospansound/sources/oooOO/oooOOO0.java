package oooOO;

import com.arcvideo.ivi.res.sdk.data.ErrorBean;
import com.google.gson.Gson;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import java.io.InterruptedIOException;
import java.io.NotSerializableException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.text.ParseException;
import javax.net.ssl.SSLHandshakeException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import retrofit2.HttpException;

/* JADX INFO: loaded from: classes3.dex */
public final class oooOOO0 {

    /* JADX INFO: renamed from: oooOO0oo, reason: collision with root package name */
    public static final oooOO0oo f841oooOO0oo = new oooOO0oo(null);

    public static final class oooOO0oo {
        public oooOO0oo() {
        }

        public /* synthetic */ oooOO0oo(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ErrorBean oooOO0oo(Throwable e) {
            ErrorBean errorBean;
            Intrinsics.checkNotNullParameter(e, "e");
            ErrorBean errorBean2 = new ErrorBean("-1", "未知错误");
            if (e instanceof HttpException) {
                return oooOO0oo(String.valueOf(((HttpException) e).code()));
            }
            if (e instanceof ConnectException) {
                errorBean2.setMsg("连接失败");
                return errorBean2;
            }
            if (e instanceof SocketTimeoutException) {
                errorBean2.setMsg("连接超时");
                return errorBean2;
            }
            if (e instanceof InterruptedIOException) {
                errorBean2.setMsg("连接中断");
                return errorBean2;
            }
            if (e instanceof SSLHandshakeException) {
                errorBean2.setMsg("证书验证失败");
                return errorBean2;
            }
            if (e instanceof UnknownHostException) {
                errorBean2.setMsg("无法解析该域名");
                return errorBean2;
            }
            if (e instanceof UnknownServiceException) {
                errorBean2.setMsg("无法解析该域名");
                return errorBean2;
            }
            if (e instanceof JSONException) {
                errorBean2.setMsg("数据解析错误");
                return errorBean2;
            }
            if (e instanceof JsonSyntaxException) {
                errorBean2.setMsg("数据解析错误");
                return errorBean2;
            }
            if (e instanceof JsonSerializer) {
                errorBean2.setMsg("数据解析错误");
                return errorBean2;
            }
            if (e instanceof ParseException) {
                errorBean2.setMsg("数据解析错误");
                return errorBean2;
            }
            if (e instanceof NotSerializableException) {
                errorBean2.setMsg("数据解析错误");
                return errorBean2;
            }
            if (e instanceof NullPointerException) {
                errorBean2.setMsg("NullPointer");
                return errorBean2;
            }
            if (e instanceof ClassCastException) {
                errorBean2.setMsg("类型转换错误");
                return errorBean2;
            }
            if (e instanceof NumberFormatException) {
                errorBean2.setMsg("NumberFormat");
                return errorBean2;
            }
            try {
                Object objFromJson = new Gson().fromJson(e.getMessage(), (Class<Object>) ErrorBean.class);
                Intrinsics.checkNotNullExpressionValue(objFromJson, "Gson().fromJson(e.message, ErrorBean::class.java)");
                errorBean = (ErrorBean) objFromJson;
            } catch (Exception unused) {
            }
            try {
                return oooOO0oo(errorBean.getCode());
            } catch (Exception unused2) {
                errorBean2 = errorBean;
                errorBean2.setMsg(String.valueOf(e.getMessage()));
                errorBean2.setCode("-1");
                return errorBean2;
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:53:0x00b4  */
        /* JADX WARN: Removed duplicated region for block: B:63:0x00db  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final com.arcvideo.ivi.res.sdk.data.ErrorBean oooOO0oo(java.lang.String r4) {
            /*
                Method dump skipped, instruction units count: 274
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: oooOO.oooOOO0.oooOO0oo.oooOO0oo(java.lang.String):com.arcvideo.ivi.res.sdk.data.ErrorBean");
        }
    }
}
