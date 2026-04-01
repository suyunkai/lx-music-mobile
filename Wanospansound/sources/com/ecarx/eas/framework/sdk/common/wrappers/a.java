package com.ecarx.eas.framework.sdk.common.wrappers;

import android.content.Context;
import android.content.pm.PackageManager;

/* JADX INFO: loaded from: classes2.dex */
public final class a {
    public static boolean a(Context context) {
        try {
            int i = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.flags;
            if (!((i & 1) != 0)) {
                if (!((i & 128) != 0)) {
                    return false;
                }
            }
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
