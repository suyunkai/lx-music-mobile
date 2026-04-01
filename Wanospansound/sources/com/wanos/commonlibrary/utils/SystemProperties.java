package com.wanos.commonlibrary.utils;

import android.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class SystemProperties {
    private static final String TAG = "SystemProperties";

    public static String get(String str) {
        String str2;
        String str3 = null;
        try {
            str2 = (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class).invoke(null, str);
        } catch (Exception e) {
            e = e;
        }
        try {
            Log.i(TAG, "value：" + str2);
            return str2;
        } catch (Exception e2) {
            e = e2;
            str3 = str2;
            Log.e(TAG, "read SystemProperties error", e);
            return str3;
        }
    }

    public static String get(String str, String str2) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class, String.class).invoke(null, str, str2);
        } catch (Exception e) {
            Log.e(TAG, "read SystemProperties error", e);
            return null;
        }
    }

    public static int getInt(String str, int i) {
        try {
            return ((Integer) Class.forName("android.os.SystemProperties").getMethod("getInt", String.class, Integer.TYPE).invoke(null, str, Integer.valueOf(i))).intValue();
        } catch (Exception e) {
            Log.e(TAG, "read SystemProperties error", e);
            return 0;
        }
    }

    public static long getLong(String str, long j) {
        try {
            return ((Long) Class.forName("android.os.SystemProperties").getMethod("getLong", String.class, Long.TYPE).invoke(null, str, Long.valueOf(j))).longValue();
        } catch (Exception e) {
            Log.e(TAG, "read SystemProperties error", e);
            return 0L;
        }
    }

    public static boolean getBoolean(String str, boolean z) {
        try {
            return ((Boolean) Class.forName("android.os.SystemProperties").getMethod("getBoolean", String.class, Boolean.TYPE).invoke(null, str, Boolean.valueOf(z))).booleanValue();
        } catch (Exception e) {
            Log.e(TAG, "read SystemProperties error", e);
            return false;
        }
    }

    public static void set(String str, String str2) {
        try {
            Class.forName("android.os.SystemProperties").getMethod("set", String.class, String.class).invoke(null, str, str2);
        } catch (Exception e) {
            Log.e(TAG, "read SystemProperties error", e);
        }
    }

    public static void addChangeCallback(Runnable runnable) {
        try {
            Class.forName("android.os.SystemProperties").getMethod("addChangeCallback", Runnable.class).invoke(null, runnable);
        } catch (Exception e) {
            Log.e(TAG, "read SystemProperties error", e);
        }
    }
}
