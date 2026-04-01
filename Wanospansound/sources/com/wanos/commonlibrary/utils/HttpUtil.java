package com.wanos.commonlibrary.utils;

import android.util.Log;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.OkHttpClient;

/* JADX INFO: loaded from: classes3.dex */
public class HttpUtil {
    private static final String TAG = "wanos:[HttpUtil]";
    private static final TrustManager[] TRUST_MANAGERS = {new X509TrustManager() { // from class: com.wanos.commonlibrary.utils.HttpUtil.1
        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }};

    static /* synthetic */ boolean lambda$initFileDownHttpClient$1(String str, SSLSession sSLSession) {
        return true;
    }

    static /* synthetic */ boolean lambda$initGlideHttpClient$0(String str, SSLSession sSLSession) {
        return true;
    }

    public static boolean isUrlAvailable(String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setRequestMethod("HEAD");
            return httpURLConnection.getResponseCode() == 200;
        } catch (Exception unused) {
            Log.e(TAG, "url resource does not exist");
            return false;
        }
    }

    public static OkHttpClient initGlideHttpClient() {
        try {
            SSLContext sSLContext = SSLContext.getInstance("SSL");
            TrustManager[] trustManagerArr = TRUST_MANAGERS;
            sSLContext.init(null, trustManagerArr, new SecureRandom());
            SSLSocketFactory socketFactory = sSLContext.getSocketFactory();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(socketFactory, (X509TrustManager) trustManagerArr[0]);
            builder.hostnameVerifier(new HostnameVerifier() { // from class: com.wanos.commonlibrary.utils.HttpUtil$$ExternalSyntheticLambda1
                @Override // javax.net.ssl.HostnameVerifier
                public final boolean verify(String str, SSLSession sSLSession) {
                    return HttpUtil.lambda$initGlideHttpClient$0(str, sSLSession);
                }
            });
            builder.connectTimeout(10L, TimeUnit.SECONDS);
            builder.readTimeout(20L, TimeUnit.SECONDS);
            return builder.build();
        } catch (KeyManagementException | NoSuchAlgorithmException unused) {
            return getDefaultClient();
        }
    }

    public static OkHttpClient.Builder initFileDownHttpClient() {
        try {
            SSLContext sSLContext = SSLContext.getInstance("SSL");
            TrustManager[] trustManagerArr = TRUST_MANAGERS;
            sSLContext.init(null, trustManagerArr, new SecureRandom());
            SSLSocketFactory socketFactory = sSLContext.getSocketFactory();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(socketFactory, (X509TrustManager) trustManagerArr[0]);
            builder.hostnameVerifier(new HostnameVerifier() { // from class: com.wanos.commonlibrary.utils.HttpUtil$$ExternalSyntheticLambda0
                @Override // javax.net.ssl.HostnameVerifier
                public final boolean verify(String str, SSLSession sSLSession) {
                    return HttpUtil.lambda$initFileDownHttpClient$1(str, sSLSession);
                }
            });
            builder.connectTimeout(10L, TimeUnit.SECONDS);
            builder.readTimeout(20L, TimeUnit.SECONDS);
            return builder;
        } catch (KeyManagementException | NoSuchAlgorithmException unused) {
            return new OkHttpClient.Builder();
        }
    }

    private static OkHttpClient getDefaultClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(10L, TimeUnit.SECONDS);
        builder.readTimeout(20L, TimeUnit.SECONDS);
        return builder.build();
    }

    public static String appendQueryParamsManually(String str, Map<String, String> map) {
        StringBuilder sb = new StringBuilder(str);
        if (str.contains("?")) {
            sb.append("&");
        } else {
            sb.append("?");
        }
        boolean z = false;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (z) {
                sb.append("&");
            }
            sb.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue()));
            z = true;
        }
        return sb.toString();
    }
}
