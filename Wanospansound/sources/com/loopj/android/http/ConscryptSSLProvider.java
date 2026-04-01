package com.loopj.android.http;

import android.util.Log;
import java.security.Security;
import org.conscrypt.Conscrypt;

/* JADX INFO: loaded from: classes3.dex */
public class ConscryptSSLProvider {
    public static void install() {
        try {
            Security.insertProviderAt(Conscrypt.newProviderBuilder().build(), 1);
        } catch (NoClassDefFoundError unused) {
            Log.e(AsyncHttpClient.LOG_TAG, "java.lang.NoClassDefFoundError: org.conscrypt.Conscrypt, Please add org.conscrypt.Conscrypt to your dependency");
        }
    }
}
