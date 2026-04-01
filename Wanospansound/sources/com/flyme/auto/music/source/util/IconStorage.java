package com.flyme.auto.music.source.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import androidx.core.content.ContextCompat;
import com.baidubce.BceConfig;
import com.flyme.auto.music.source.R;
import java.io.File;
import java.io.FileInputStream;

/* JADX INFO: loaded from: classes2.dex */
public class IconStorage {
    public static final String TAG = "IconStorage";

    public static Bitmap getIcon(Context context, String str, String str2, int i) {
        Bitmap iconSpecial = getIconSpecial(context, str2);
        if (iconSpecial != null) {
            return iconSpecial;
        }
        Bitmap iconFromStorage = getIconFromStorage(context, str, str2);
        if (iconFromStorage != null) {
            return iconFromStorage;
        }
        if (context.getPackageName().equals(str2)) {
            saveSelfIconImageUrl(context, str);
        }
        Bitmap iconFromPackage = getIconFromPackage(context, str2);
        return iconFromPackage == null ? getIconDefault(context, i) : iconFromPackage;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00d6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void updateIconFromNet(android.content.Context r6, java.lang.String r7, java.lang.String r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 218
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flyme.auto.music.source.util.IconStorage.updateIconFromNet(android.content.Context, java.lang.String, java.lang.String):void");
    }

    private static String getIconFilePath(Context context, String str, String str2) {
        File file = new File(context.getFilesDir().getAbsolutePath() + "/icons");
        if (!file.exists()) {
            file.mkdir();
        }
        if (!TextUtils.isEmpty(str)) {
            return file.getAbsolutePath() + BceConfig.BOS_DELIMITER + str.hashCode();
        }
        return file.getAbsolutePath() + BceConfig.BOS_DELIMITER + str2.hashCode();
    }

    private static void saveSelfIconImageUrl(Context context, String str) {
        context.getSharedPreferences("SP_MUSIC_SOURCE", 0).edit().putString("SELF_ICON", str).apply();
    }

    public static String getSelfIconPath(Context context) {
        String string = context.getSharedPreferences("SP_MUSIC_SOURCE", 0).getString("SELF_ICON", null);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return getIconFilePath(context, string, context.getPackageName());
    }

    private static Bitmap getIconFromStorage(Context context, String str, String str2) {
        String iconFilePath = getIconFilePath(context, str, str2);
        Bitmap bitmapDecodeStream = null;
        try {
            File file = new File(iconFilePath);
            if (!file.exists()) {
                return null;
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            bitmapDecodeStream = BitmapFactory.decodeStream(fileInputStream);
            fileInputStream.close();
            return bitmapDecodeStream;
        } catch (Exception e) {
            MusicSourceLog.e(TAG, "getIconFromStorage failed: " + e.getMessage());
            return bitmapDecodeStream;
        }
    }

    private static Bitmap getIconFromPackage(Context context, String str) {
        try {
            return BitmapUtil.toRoundBitmap(context.getPackageManager().getApplicationIcon(str));
        } catch (Exception e) {
            MusicSourceLog.e(TAG, "getIconFromPackage failed: " + e.getMessage());
            return null;
        }
    }

    private static Bitmap getIconDefault(Context context, int i) {
        if (i == 32) {
            return BitmapUtil.toRoundBitmap(ContextCompat.getDrawable(context, R.drawable.ic_default_icon_night), 54);
        }
        return BitmapUtil.toRoundBitmap(ContextCompat.getDrawable(context, R.drawable.ic_default_icon), 54);
    }

    private static Bitmap getIconSpecial(Context context, String str) {
        Drawable drawable;
        if (str.equals(context.getString(R.string.pkg_qq_music))) {
            drawable = ContextCompat.getDrawable(context, R.drawable.ic_qq_logo);
        } else if (str.equals(context.getString(R.string.pkg_bluetooth_music))) {
            drawable = ContextCompat.getDrawable(context, R.drawable.ic_bluetooth_logo);
        } else if (str.equals(context.getString(R.string.pkg_usb_music))) {
            drawable = ContextCompat.getDrawable(context, R.drawable.ic_usb_logo);
        } else {
            drawable = str.equals(context.getString(R.string.pkg_cloud_music_by_geely)) ? ContextCompat.getDrawable(context, R.drawable.ic_cloud_logo) : null;
        }
        return BitmapUtil.drawableToBitmap(drawable);
    }
}
