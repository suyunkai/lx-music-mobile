package com.alibaba.android.arouter.utils;

import android.util.Log;
import com.alibaba.android.arouter.facade.template.ILogger;

/* JADX INFO: loaded from: classes.dex */
public class DefaultLogger implements ILogger {
    private static boolean isMonitorMode = false;
    private static boolean isShowLog = false;
    private static boolean isShowStackTrace = false;
    private String defaultTag;

    @Override // com.alibaba.android.arouter.facade.template.ILogger
    public void showLog(boolean z) {
        isShowLog = z;
    }

    @Override // com.alibaba.android.arouter.facade.template.ILogger
    public void showStackTrace(boolean z) {
        isShowStackTrace = z;
    }

    public void showMonitor(boolean z) {
        isMonitorMode = z;
    }

    public DefaultLogger() {
        this.defaultTag = Consts.SDK_NAME;
    }

    public DefaultLogger(String str) {
        this.defaultTag = str;
    }

    @Override // com.alibaba.android.arouter.facade.template.ILogger
    public void debug(String str, String str2) {
        if (isShowLog) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            if (android.text.TextUtils.isEmpty(str)) {
                str = getDefaultTag();
            }
            Log.d(str, str2 + getExtInfo(stackTraceElement));
        }
    }

    @Override // com.alibaba.android.arouter.facade.template.ILogger
    public void info(String str, String str2) {
        if (isShowLog) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            if (android.text.TextUtils.isEmpty(str)) {
                str = getDefaultTag();
            }
            Log.i(str, str2 + getExtInfo(stackTraceElement));
        }
    }

    @Override // com.alibaba.android.arouter.facade.template.ILogger
    public void warning(String str, String str2) {
        if (isShowLog) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            if (android.text.TextUtils.isEmpty(str)) {
                str = getDefaultTag();
            }
            Log.w(str, str2 + getExtInfo(stackTraceElement));
        }
    }

    @Override // com.alibaba.android.arouter.facade.template.ILogger
    public void error(String str, String str2) {
        if (isShowLog) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            if (android.text.TextUtils.isEmpty(str)) {
                str = getDefaultTag();
            }
            Log.e(str, str2 + getExtInfo(stackTraceElement));
        }
    }

    @Override // com.alibaba.android.arouter.facade.template.ILogger
    public void error(String str, String str2, Throwable th) {
        if (isShowLog) {
            if (android.text.TextUtils.isEmpty(str)) {
                str = getDefaultTag();
            }
            Log.e(str, str2, th);
        }
    }

    @Override // com.alibaba.android.arouter.facade.template.ILogger
    public void monitor(String str) {
        if (isShowLog && isMonitorMode()) {
            Log.d(this.defaultTag + "::monitor", str + getExtInfo(Thread.currentThread().getStackTrace()[3]));
        }
    }

    @Override // com.alibaba.android.arouter.facade.template.ILogger
    public boolean isMonitorMode() {
        return isMonitorMode;
    }

    @Override // com.alibaba.android.arouter.facade.template.ILogger
    public String getDefaultTag() {
        return this.defaultTag;
    }

    public static String getExtInfo(StackTraceElement stackTraceElement) {
        StringBuilder sb = new StringBuilder("[");
        if (isShowStackTrace) {
            String name = Thread.currentThread().getName();
            String fileName = stackTraceElement.getFileName();
            String className = stackTraceElement.getClassName();
            String methodName = stackTraceElement.getMethodName();
            long id = Thread.currentThread().getId();
            int lineNumber = stackTraceElement.getLineNumber();
            sb.append("ThreadId=").append(id).append(" & ThreadName=");
            sb.append(name).append(" & FileName=");
            sb.append(fileName).append(" & ClassName=");
            sb.append(className).append(" & MethodName=");
            sb.append(methodName).append(" & LineNumber=");
            sb.append(lineNumber);
        }
        sb.append(" ] ");
        return sb.toString();
    }
}
