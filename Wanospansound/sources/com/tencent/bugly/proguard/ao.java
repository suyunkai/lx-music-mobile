package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Process;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class ao {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f465a = true;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f466b = true;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static SimpleDateFormat f467c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static int f468d = 30720;
    private static StringBuilder e = null;
    private static StringBuilder f = null;
    private static boolean g = false;
    private static a h = null;
    private static String i = null;
    private static String j = null;
    private static Context k = null;
    private static String l = null;
    private static boolean m = false;
    private static boolean n = false;
    private static ExecutorService o;
    private static int p;
    private static final Object q = new Object();

    static {
        try {
            f467c = new SimpleDateFormat("MM-dd HH:mm:ss");
        } catch (Throwable th) {
            al.b(th.getCause());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean d(String str, String str2, String str3) {
        try {
            aa aaVarB = aa.b();
            if (aaVarB == null || aaVarB.N == null) {
                return false;
            }
            return aaVarB.N.appendLogToNative(str, str2, str3);
        } catch (Throwable th) {
            if (al.a(th)) {
                return false;
            }
            th.printStackTrace();
            return false;
        }
    }

    private static String b() {
        try {
            aa aaVarB = aa.b();
            if (aaVarB == null || aaVarB.N == null) {
                return null;
            }
            return aaVarB.N.getLogFromNative();
        } catch (Throwable th) {
            if (al.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    public static synchronized void a(Context context) {
        if (m || context == null || !f466b) {
            return;
        }
        try {
            o = Executors.newSingleThreadExecutor();
            f = new StringBuilder(0);
            e = new StringBuilder(0);
            k = context;
            aa aaVarA = aa.a(context);
            i = aaVarA.f418d;
            aaVarA.getClass();
            j = "";
            l = k.getFilesDir().getPath() + "/buglylog_" + i + "_" + j + ".txt";
            p = Process.myPid();
        } catch (Throwable unused) {
        }
        m = true;
    }

    public static void a(int i2) {
        synchronized (q) {
            f468d = i2;
            if (i2 < 0) {
                f468d = 0;
            } else if (i2 > 30720) {
                f468d = 30720;
            }
        }
    }

    public static void a(String str, String str2, Throwable th) {
        if (th == null) {
            return;
        }
        String message = th.getMessage();
        if (message == null) {
            message = "";
        }
        a(str, str2, message + '\n' + ap.b(th));
    }

    public static synchronized void a(final String str, final String str2, final String str3) {
        if (m && f466b) {
            try {
                if (n) {
                    o.execute(new Runnable() { // from class: com.tencent.bugly.proguard.ao.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            ao.d(str, str2, str3);
                        }
                    });
                } else {
                    o.execute(new Runnable() { // from class: com.tencent.bugly.proguard.ao.2
                        @Override // java.lang.Runnable
                        public final void run() {
                            ao.e(str, str2, str3);
                        }
                    });
                }
            } catch (Exception e2) {
                al.b(e2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized void e(String str, String str2, String str3) {
        if (f465a) {
            f(str, str2, str3);
        } else {
            g(str, str2, str3);
        }
    }

    private static synchronized void f(String str, String str2, String str3) {
        String strA = a(str, str2, str3, Process.myTid());
        synchronized (q) {
            try {
                f.append(strA);
                if (f.length() >= f468d) {
                    StringBuilder sb = f;
                    f = sb.delete(0, sb.indexOf("\u0001\r\n") + 1);
                }
            } finally {
            }
        }
    }

    private static synchronized void g(String str, String str2, String str3) {
        String strA = a(str, str2, str3, Process.myTid());
        synchronized (q) {
            try {
                f.append(strA);
                if (f.length() <= f468d) {
                    return;
                }
                if (g) {
                    return;
                }
                g = true;
                a aVar = h;
                if (aVar == null) {
                    h = new a(l);
                } else if (aVar.f476b == null || h.f476b.length() + ((long) f.length()) > h.f477c) {
                    h.a();
                }
                if (h.a(f.toString())) {
                    f.setLength(0);
                    g = false;
                }
            } catch (Throwable unused) {
            }
        }
    }

    private static String a(String str, String str2, String str3, long j2) {
        String string;
        e.setLength(0);
        if (str3.length() > 30720) {
            str3 = str3.substring(str3.length() - 30720, str3.length() - 1);
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = f467c;
        if (simpleDateFormat != null) {
            string = simpleDateFormat.format(date);
        } else {
            string = date.toString();
        }
        e.append(string).append(" ").append(p).append(" ").append(j2).append(" ").append(str).append(" ").append(str2).append(": ").append(str3).append("\u0001\r\n");
        return e.toString();
    }

    public static byte[] a() {
        if (!f465a) {
            return c();
        }
        if (f466b) {
            return ap.a(f.toString(), "BuglyLog.txt");
        }
        return null;
    }

    private static byte[] c() {
        if (!f466b) {
            return null;
        }
        if (n) {
            al.a("[LogUtil] Get user log from native.", new Object[0]);
            String strB = b();
            if (strB != null) {
                al.a("[LogUtil] Got user log from native: %d bytes", Integer.valueOf(strB.length()));
                return ap.a(strB, "BuglyNativeLog.txt");
            }
        }
        StringBuilder sb = new StringBuilder();
        synchronized (q) {
            a aVar = h;
            if (aVar != null && aVar.f475a && h.f476b != null && h.f476b.length() > 0) {
                sb.append(ap.a(h.f476b, 30720, true));
            }
            StringBuilder sb2 = f;
            if (sb2 != null && sb2.length() > 0) {
                sb.append(f.toString());
            }
        }
        return ap.a(sb.toString(), "BuglyLog.txt");
    }

    /* JADX INFO: compiled from: BUGLY */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        boolean f475a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        File f476b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        long f477c = 30720;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private String f478d;
        private long e;

        public a(String str) {
            if (str == null || str.equals("")) {
                return;
            }
            this.f478d = str;
            this.f475a = a();
        }

        final boolean a() {
            try {
                File file = new File(this.f478d);
                this.f476b = file;
                if (file.exists() && !this.f476b.delete()) {
                    this.f475a = false;
                    return false;
                }
                if (this.f476b.createNewFile()) {
                    return true;
                }
                this.f475a = false;
                return false;
            } catch (Throwable th) {
                al.a(th);
                this.f475a = false;
                return false;
            }
        }

        public final boolean a(String str) {
            if (!this.f475a) {
                return false;
            }
            FileOutputStream fileOutputStream = null;
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(this.f476b, true);
                try {
                    byte[] bytes = str.getBytes("UTF-8");
                    fileOutputStream2.write(bytes);
                    fileOutputStream2.flush();
                    fileOutputStream2.close();
                    this.e += (long) bytes.length;
                    this.f475a = true;
                    try {
                        fileOutputStream2.close();
                    } catch (IOException unused) {
                    }
                    return true;
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    try {
                        al.a(th);
                        this.f475a = false;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException unused2) {
                            }
                        }
                        return false;
                    } catch (Throwable th2) {
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException unused3) {
                            }
                        }
                        throw th2;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
    }
}
