package com.ecarx.eas.sdk.log;

import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public final class LogProxy {
    public static final String TAG = "OpenAPI_V4";
    private boolean enable;
    private int level;
    private ILog logImpl;

    public final void setLogEnable(boolean z) {
        this.enable = z;
    }

    public final void setLogLevel(int i) {
        this.level = i;
    }

    public final void setLogImpl(ILog iLog) {
        this.logImpl = iLog;
    }

    public final void v(String str, String str2) {
        if (!this.enable || this.level > 2) {
            return;
        }
        ILog iLog = this.logImpl;
        if (iLog == null) {
            Log.v(str, str2);
        } else {
            iLog.v(str, str2);
        }
    }

    public final void v(String str, String str2, Throwable th) {
        if (!this.enable || this.level > 2) {
            return;
        }
        ILog iLog = this.logImpl;
        if (iLog == null) {
            Log.v(str, str2, th);
        } else {
            iLog.v(str, str2 + '\n' + Log.getStackTraceString(th));
        }
    }

    public final void d(String str, String str2) {
        if (!this.enable || this.level > 3) {
            return;
        }
        ILog iLog = this.logImpl;
        if (iLog == null) {
            Log.d(str, str2);
        } else {
            iLog.d(str, str2);
        }
    }

    public final void d(String str, String str2, Throwable th) {
        if (!this.enable || this.level > 3) {
            return;
        }
        ILog iLog = this.logImpl;
        if (iLog == null) {
            Log.d(str, str2, th);
        } else {
            iLog.d(str, str2 + '\n' + Log.getStackTraceString(th));
        }
    }

    public final void i(String str, String str2) {
        if (!this.enable || this.level > 4) {
            return;
        }
        ILog iLog = this.logImpl;
        if (iLog == null) {
            Log.i(str, str2);
        } else {
            iLog.i(str, str2);
        }
    }

    public final void i(String str, String str2, Throwable th) {
        if (!this.enable || this.level > 4) {
            return;
        }
        ILog iLog = this.logImpl;
        if (iLog == null) {
            Log.i(str, str2, th);
        } else {
            iLog.i(str, str2 + '\n' + Log.getStackTraceString(th));
        }
    }

    public final void w(String str, String str2) {
        if (!this.enable || this.level > 5) {
            return;
        }
        ILog iLog = this.logImpl;
        if (iLog == null) {
            Log.w(str, str2);
        } else {
            iLog.w(str, str2);
        }
    }

    public final void w(String str, String str2, Throwable th) {
        if (!this.enable || this.level > 5) {
            return;
        }
        ILog iLog = this.logImpl;
        if (iLog == null) {
            Log.w(str, str2, th);
        } else {
            iLog.w(str, str2 + '\n' + Log.getStackTraceString(th));
        }
    }

    public final void e(String str, String str2) {
        if (!this.enable || this.level > 6) {
            return;
        }
        ILog iLog = this.logImpl;
        if (iLog == null) {
            Log.e(str, str2);
        } else {
            iLog.e(str, str2);
        }
    }

    public final void e(String str, String str2, Throwable th) {
        if (!this.enable || this.level > 6) {
            return;
        }
        ILog iLog = this.logImpl;
        if (iLog == null) {
            Log.e(str, str2, th);
        } else {
            iLog.e(str, str2 + '\n' + Log.getStackTraceString(th));
        }
    }
}
