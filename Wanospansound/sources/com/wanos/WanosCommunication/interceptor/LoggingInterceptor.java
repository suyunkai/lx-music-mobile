package com.wanos.WanosCommunication.interceptor;

import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import java.io.IOException;
import java.net.URLDecoder;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/* JADX INFO: loaded from: classes3.dex */
public class LoggingInterceptor implements Interceptor {
    public static final String TAG = "wanos:[LoggingInterceptor]";

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        long jNanoTime = System.nanoTime();
        if (HttpPost.METHOD_NAME.equals(request.method())) {
            StringBuilder sb = new StringBuilder();
            if (request.body() instanceof FormBody) {
                FormBody formBody = (FormBody) request.body();
                for (int i = 0; i < formBody.size(); i++) {
                    sb.append(formBody.encodedName(i) + "=" + formBody.encodedValue(i) + ",");
                }
                sb.delete(sb.length() - 1, sb.length());
                Log.d(TAG, String.format("发送请求 %s on %s %n%s %nRequestParams:{%s}", request.url(), chain.connection(), request.headers(), sb));
            } else if (request.body() instanceof RequestBody) {
                Log.d(TAG, String.format("发送请求 %s on %s %n%s %nRequestParams:%s", request.url(), chain.connection(), request.headers(), getParam(request.body())));
            }
        } else {
            Log.d(TAG, String.format("发送请求 %s on %s%n%s", request.url(), chain.connection(), request.headers()));
        }
        Response responseProceed = chain.proceed(request);
        Log.d(TAG, String.format("接收响应: [%s] %n返回json:【%s】 %.1fms %n%s", responseProceed.request().url(), responseProceed.peekBody(PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED).string(), Double.valueOf((System.nanoTime() - jNanoTime) / 1000000.0d), responseProceed.headers()));
        return responseProceed;
    }

    private String getParam(RequestBody requestBody) {
        Buffer buffer = new Buffer();
        try {
            requestBody.writeTo(buffer);
            return URLDecoder.decode(buffer.readUtf8().replaceAll("%(?![0-9a-fA-F]{2})", "%25").replaceAll("\\+", "%2B"), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
