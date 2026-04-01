package com.wanos;

import android.content.Context;

/* JADX INFO: loaded from: classes3.dex */
public class Util {
    public static int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
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
}
