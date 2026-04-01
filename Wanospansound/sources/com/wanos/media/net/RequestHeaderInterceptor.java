package com.wanos.media.net;

import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.loopj.android.http.RequestParams;
import com.wanos.commonlibrary.utils.CommonUtils;
import com.wanos.commonlibrary.utils.SystemAndServerTimeSynchUtil;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.media.BuildConfig;
import com.wanos.media.MainApplication;
import com.wanos.media.util.EncryptUtils;
import com.wanos.media.util.RequestParameterUtils;
import com.wanos.media.util.SignUtils;
import cz.msebera.android.httpclient.cookie.ClientCookie;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: loaded from: classes3.dex */
public class RequestHeaderInterceptor implements Interceptor {
    public static final String TAG = "wanos:[RequestHeaderInterceptor]";

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        if (!RequestParameterUtils.isInit) {
            RequestParameterUtils.init();
        }
        Request request = chain.request();
        Request.Builder builderNewBuilder = request.newBuilder();
        List<String> listHeaders = request.headers("source");
        builderNewBuilder.headers(Headers.of(getV2Headers(request)));
        if (!listHeaders.isEmpty()) {
            String str = listHeaders.get(0);
            if (!TextUtils.isEmpty(str)) {
                builderNewBuilder.removeHeader("source");
                if (ExifInterface.GPS_MEASUREMENT_2D.equals(str)) {
                    builderNewBuilder.addHeader("source", ExifInterface.GPS_MEASUREMENT_2D);
                }
            }
        }
        return chain.proceed(builderNewBuilder.build());
    }

    private Map<String, String> getHeaders() {
        long sytemTrueTime = SystemAndServerTimeSynchUtil.getSytemTrueTime() / 1000;
        String deviceToken = RequestParameterUtils.getDeviceToken(sytemTrueTime);
        String sign = RequestParameterUtils.getSign(sytemTrueTime);
        HashMap map = new HashMap();
        map.put("accept", RequestParams.APPLICATION_JSON);
        map.put("deviceToken", deviceToken);
        map.put("sign", sign);
        if (!TextUtils.isEmpty(UserInfoUtil.getToken())) {
            map.put("UserToken", UserInfoUtil.getToken());
        }
        map.put("channel", CommonUtils.getAppChannelId());
        map.put(ClientCookie.VERSION_ATTR, BuildConfig.VERSION_NAME);
        map.put("vid", EncryptUtils.sha256(CommonUtils.getDeviceId(MainApplication.getInstance()) + CommonUtils.getAppChannelId()));
        return map;
    }

    private Map<String, String> getV2Headers(Request request) {
        String nonceStr = SignUtils.getInstance().getNonceStr();
        String sign = SignUtils.getInstance().getSign(request, nonceStr);
        HashMap map = new HashMap();
        map.put("accept", RequestParams.APPLICATION_JSON);
        if (!request.url().url().getPath().contains("/api/commonUser/deviceLogin")) {
            map.put("deviceToken", SignUtils.getInstance().getDeviceToken());
        }
        map.put("sign", sign);
        map.put("encryptType", ExifInterface.GPS_MEASUREMENT_2D);
        map.put("nonceStr", nonceStr);
        map.put("clientIp", CommonUtils.getIPAddress());
        map.put("timeStamp", String.valueOf(SystemAndServerTimeSynchUtil.getSytemTrueTime() / 1000));
        if (!TextUtils.isEmpty(UserInfoUtil.getToken())) {
            map.put("UserToken", UserInfoUtil.getToken());
        }
        map.put("Decoder", SignUtils.getInstance().getEncryptedDecodeVersion());
        map.put("channel", CommonUtils.getAppChannelId());
        map.put(ClientCookie.VERSION_ATTR, BuildConfig.VERSION_NAME);
        return map;
    }
}
