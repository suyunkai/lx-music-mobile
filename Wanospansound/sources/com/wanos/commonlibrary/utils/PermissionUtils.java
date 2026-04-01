package com.wanos.commonlibrary.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class PermissionUtils {
    private static final String TAG = "PermissionUtils";

    public static boolean requestManageAllFilesPermission(Context context) {
        try {
            Log.i(TAG, "requestManageAllFilesPermission");
            Intent intent = new Intent("android.settings.MANAGE_APP_ALL_FILES_ACCESS_PERMISSION");
            intent.setData(Uri.parse("package:" + context.getPackageName()));
            intent.addFlags(268435456);
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                Log.i(TAG, "跳转到权限管理页面");
                context.startActivity(intent);
                return true;
            }
            return openAppSettings(context);
        } catch (Exception unused) {
            return openAppSettings(context);
        }
    }

    public static boolean isAllFilesAccessGranted(Context context) {
        return Environment.isExternalStorageManager();
    }

    public static boolean openAppSettings(Context context) {
        try {
            Log.i(TAG, "openAppSettings");
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.parse("package:" + context.getPackageName()));
            intent.addFlags(268435456);
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
