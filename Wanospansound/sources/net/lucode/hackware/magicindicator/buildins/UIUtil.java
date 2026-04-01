package net.lucode.hackware.magicindicator.buildins;

import android.content.Context;

/* JADX INFO: loaded from: classes3.dex */
public final class UIUtil {
    public static int dip2px(Context context, double d2) {
        return (int) ((d2 * ((double) context.getResources().getDisplayMetrics().density)) + 0.5d);
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }
}
