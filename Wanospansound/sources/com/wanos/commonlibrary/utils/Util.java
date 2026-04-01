package com.wanos.commonlibrary.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Process;
import android.util.Log;
import android.widget.TextView;
import com.blankj.utilcode.util.AppUtils;
import com.wanos.media.ui.advertise.WanosJsBridge;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class Util {
    private static final String TAG = "Util";

    public static int dip2px(Context context, float f) {
        return context == null ? (int) f : (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2dip(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static float limitValue(float f, float f2) {
        float fMin = Math.min(f, f2);
        float fMax = Math.max(f, f2);
        if (0.0f > fMin) {
            fMin = 0.0f;
        }
        return fMin < fMax ? fMin : fMax;
    }

    public static String getProcessName(Context context) {
        int iMyPid = Process.myPid();
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService(WanosJsBridge.H5_KEY_ACTIVITY)).getRunningAppProcesses();
        String str = "";
        if (runningAppProcesses != null && runningAppProcesses.size() != 0) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == iMyPid) {
                    str = runningAppProcessInfo.processName;
                }
            }
        }
        return str;
    }

    public static boolean isMainProcess(Context context) {
        Log.i(TAG, "isMainProcess: " + AppUtils.getAppPackageName());
        return AppUtils.getAppPackageName().equals(getProcessName(context));
    }

    public static void setTextWeight(TextView textView, int i) {
        textView.setTypeface(Typeface.create(Typeface.DEFAULT, i, false));
    }
}
