package com.wanos.media.util;

import android.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class ZeroLogcatTools {
    private static final String BASE_TAG = "wanos[Zero]-";

    public static void e(String str, String str2) {
        Log.e(BASE_TAG + str, str2);
    }

    public static void d(String str, String str2) {
        Log.d(BASE_TAG + str, str2);
    }

    public static void w(String str, String str2) {
        Log.w(BASE_TAG + str, str2);
    }

    public static void i(String str, String str2) {
        Log.i(BASE_TAG + str, str2);
    }
}
