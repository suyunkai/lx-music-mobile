package com.tencent.bugly.proguard;

import android.util.Log;
import java.util.Locale;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class al {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f459a = "CrashReportInfo";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static String f460b = "CrashReport";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static boolean f461c = false;

    private static boolean a(int i, String str, Object... objArr) {
        if (!f461c) {
            return false;
        }
        if (str == null) {
            str = "null";
        } else if (objArr != null && objArr.length != 0) {
            str = String.format(Locale.US, str, objArr);
        }
        if (i == 0) {
            Log.i(f460b, str);
            return true;
        }
        if (i == 1) {
            Log.d(f460b, str);
            return true;
        }
        if (i == 2) {
            Log.w(f460b, str);
            return true;
        }
        if (i == 3) {
            Log.e(f460b, str);
            return true;
        }
        if (i != 5) {
            return false;
        }
        Log.i(f459a, str);
        return true;
    }

    private static boolean a(int i, Throwable th) {
        if (f461c) {
            return a(i, ap.a(th), new Object[0]);
        }
        return false;
    }

    public static boolean a(String str, Object... objArr) {
        return a(0, str, objArr);
    }

    public static boolean a(Class cls, String str, Object... objArr) {
        return a(0, String.format(Locale.US, "[%s] %s", cls.getSimpleName(), str), objArr);
    }

    public static boolean b(String str, Object... objArr) {
        return a(5, str, objArr);
    }

    public static boolean c(String str, Object... objArr) {
        return a(1, str, objArr);
    }

    public static boolean d(String str, Object... objArr) {
        return a(2, str, objArr);
    }

    public static boolean a(Throwable th) {
        return a(2, th);
    }

    public static boolean e(String str, Object... objArr) {
        return a(3, str, objArr);
    }

    public static boolean b(Throwable th) {
        return a(3, th);
    }
}
