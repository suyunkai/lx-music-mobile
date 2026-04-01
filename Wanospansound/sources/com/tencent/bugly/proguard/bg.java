package com.tencent.bugly.proguard;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class bg extends Thread {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public bf f556a;
    private a g;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f558c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f559d = true;
    private boolean e = false;
    private int f = 1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f557b = true;

    /* JADX INFO: compiled from: BUGLY */
    public interface a {
    }

    public final boolean a() {
        this.f558c = true;
        if (!isAlive()) {
            return false;
        }
        try {
            interrupt();
        } catch (Exception e) {
            al.b(e);
        }
        al.d("MainHandlerChecker is reset to null.", new Object[0]);
        this.f556a = null;
        return true;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        while (!this.f558c) {
            try {
                bf bfVar = this.f556a;
                boolean z = false;
                if (bfVar == null) {
                    al.c("Main handler checker is null. Stop thread monitor.", new Object[0]);
                    return;
                }
                if (bfVar.f554c) {
                    bfVar.f554c = false;
                    bfVar.f555d = SystemClock.uptimeMillis();
                    bfVar.f552a.post(bfVar);
                }
                a(bfVar);
                if (this.f557b && this.f559d) {
                    long jB = bfVar.b();
                    if (jB > 1510 && jB < 199990) {
                        boolean z2 = true;
                        if (jB <= 5010) {
                            this.f = 1;
                            al.c("timeSinceMsgSent in [2s, 5s], record stack", new Object[0]);
                        } else {
                            int i = this.f + 1;
                            this.f = i;
                            if ((i & (i - 1)) != 0) {
                                z2 = false;
                            }
                            if (z2) {
                                al.c("timeSinceMsgSent in (5s, 200s), should record stack:true", new Object[0]);
                            }
                        }
                        z = z2;
                    }
                }
                if (z) {
                    bfVar.d();
                }
                if (this.g != null && this.f559d) {
                    bfVar.a();
                    bfVar.b();
                }
                ap.b(500 - ((System.currentTimeMillis() - jCurrentTimeMillis) % 500));
            } catch (Exception e) {
                al.b(e);
            } catch (OutOfMemoryError e2) {
                al.b(e2);
            }
        }
    }

    private synchronized void a(bf bfVar) {
        if (this.f559d) {
            return;
        }
        if (this.e && !bfVar.a()) {
            al.c("Restart getting main stack trace.", new Object[0]);
            this.f559d = true;
            this.e = false;
        }
    }

    public final synchronized void c() {
        this.f559d = false;
        al.c("Record stack trace is disabled.", new Object[0]);
    }

    public final synchronized void d() {
        this.e = true;
    }

    public final boolean b() {
        Handler handler = new Handler(Looper.getMainLooper());
        bf bfVar = this.f556a;
        if (bfVar == null) {
            this.f556a = new bf(handler, handler.getLooper().getThread().getName());
        } else {
            bfVar.f553b = 5000L;
        }
        if (isAlive()) {
            return false;
        }
        try {
            start();
            return true;
        } catch (Exception e) {
            al.b(e);
            return false;
        }
    }
}
