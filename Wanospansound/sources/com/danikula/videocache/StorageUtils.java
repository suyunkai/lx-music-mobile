package com.danikula.videocache;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
final class StorageUtils {
    private static final String INDIVIDUAL_DIR_NAME = "video-cache";
    private static final String TAG = "wanos:[StorageUtils]";

    StorageUtils() {
    }

    public static File getIndividualCacheDirectory(Context context) {
        return new File(getCacheDirectory(context, true), INDIVIDUAL_DIR_NAME);
    }

    private static File getCacheDirectory(Context context, boolean z) {
        return context.getCacheDir();
    }

    private static File getExternalCacheDir(Context context) {
        File file = new File(new File(new File(new File(Environment.getExternalStorageDirectory(), "Android"), "data"), context.getPackageName()), "cache");
        if (file.exists() || file.mkdirs()) {
            return file;
        }
        Log.w(TAG, "Unable to create external cache directory");
        return null;
    }
}
