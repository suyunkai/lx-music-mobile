package com.wanos.media.net;

import okhttp3.Interceptor;

/* JADX INFO: loaded from: classes3.dex */
public class DomainNameInterceptor implements Interceptor {
    /* JADX WARN: Removed duplicated region for block: B:15:0x0055  */
    @Override // okhttp3.Interceptor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public okhttp3.Response intercept(okhttp3.Interceptor.Chain r7) throws java.io.IOException {
        /*
            r6 = this;
            okhttp3.Request r0 = r7.request()
            okhttp3.HttpUrl r1 = r0.url()
            okhttp3.Request$Builder r2 = r0.newBuilder()
            java.lang.String r3 = "baseUrl"
            java.util.List r4 = r0.headers(r3)
            int r5 = r4.size()
            if (r5 <= 0) goto L97
            r2.removeHeader(r3)
            r0 = 0
            java.lang.Object r0 = r4.get(r0)
            java.lang.String r0 = (java.lang.String) r0
            boolean r3 = android.text.TextUtils.isEmpty(r0)
            if (r3 != 0) goto L55
            java.lang.String r3 = "search"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L37
            java.lang.String r0 = com.wanos.WanosCommunication.URLConstan.BASE_SEARCH_URL
            okhttp3.HttpUrl r0 = okhttp3.HttpUrl.parse(r0)
            goto L56
        L37:
            java.lang.String r3 = "useraction"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L46
            java.lang.String r0 = com.wanos.WanosCommunication.URLConstan.BASE_STAT_URL
            okhttp3.HttpUrl r0 = okhttp3.HttpUrl.parse(r0)
            goto L56
        L46:
            java.lang.String r3 = "login"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L55
            java.lang.String r0 = com.wanos.WanosCommunication.URLConstan.BASE_LOGIN
            okhttp3.HttpUrl r0 = okhttp3.HttpUrl.parse(r0)
            goto L56
        L55:
            r0 = r1
        L56:
            okhttp3.HttpUrl$Builder r1 = r1.newBuilder()
            java.lang.String r3 = r0.scheme()
            okhttp3.HttpUrl$Builder r1 = r1.scheme(r3)
            java.lang.String r3 = r0.host()
            okhttp3.HttpUrl$Builder r1 = r1.host(r3)
            int r0 = r0.port()
            okhttp3.HttpUrl$Builder r0 = r1.port(r0)
            okhttp3.HttpUrl r0 = r0.build()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "intercept："
            r1.<init>(r3)
            java.lang.StringBuilder r1 = r1.append(r0)
            java.lang.String r1 = r1.toString()
            java.lang.String r3 = "URL"
            android.util.Log.i(r3, r1)
            okhttp3.Request$Builder r0 = r2.url(r0)
            okhttp3.Request r0 = r0.build()
            okhttp3.Response r7 = r7.proceed(r0)
            return r7
        L97:
            okhttp3.Response r7 = r7.proceed(r0)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wanos.media.net.DomainNameInterceptor.intercept(okhttp3.Interceptor$Chain):okhttp3.Response");
    }
}
