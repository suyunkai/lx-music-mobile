package com.flyme.auto.music.source.util;

import android.content.Context;
import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;

/* JADX INFO: loaded from: classes2.dex */
public class MusicSourceLog {
    private static String versionName = "";

    public static void initVersionName(Context context) {
        try {
            versionName = context.getString(context.getResources().getIdentifier("versionName", TypedValues.Custom.S_STRING, context.getPackageName()));
        } catch (Exception unused) {
        }
    }

    public static int d(String str, String str2) {
        return Log.d(str + versionName, str2);
    }

    public static int e(String str, String str2) {
        return Log.e(str + versionName, str2);
    }

    public static int i(String str, String str2) {
        return Log.i(str + versionName, str2);
    }

    public static int w(String str, String str2) {
        return Log.w(str + versionName, str2);
    }

    public static int v(String str, String str2) {
        return Log.v(str + versionName, str2);
    }
}
