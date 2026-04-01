package com.wanos.commonlibrary.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public class FileUtil {
    public static final String TAG = "wanos:[FileUtil]";

    public static String getCacheDirectory(Context context, boolean z) {
        String externalStorageState;
        try {
            externalStorageState = Environment.getExternalStorageState();
        } catch (NullPointerException unused) {
            externalStorageState = "";
        }
        File externalCacheDir = (z && "mounted".equals(externalStorageState)) ? getExternalCacheDir(context) : null;
        if (externalCacheDir == null) {
            externalCacheDir = context.getCacheDir();
        }
        if (externalCacheDir == null) {
            externalCacheDir = new File("/data/data/" + context.getPackageName() + "/wanos/head/");
        }
        if (!externalCacheDir.exists()) {
            externalCacheDir.mkdirs();
        }
        return externalCacheDir.getAbsolutePath();
    }

    private static File getExternalCacheDir(Context context) {
        File file = new File(new File(new File(new File(Environment.getExternalStorageDirectory(), "Android"), "data"), context.getPackageName()), "cache");
        if (file.exists() || file.mkdirs()) {
            return file;
        }
        Log.w(TAG, "Unable to create external cache directory");
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v16 */
    /* JADX WARN: Type inference failed for: r5v17 */
    /* JADX WARN: Type inference failed for: r5v18 */
    /* JADX WARN: Type inference failed for: r5v19 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v20 */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x0053 -> B:46:0x0056). Please report as a decompilation issue!!! */
    public static Uri saveBitmapToPrivateDir(Context context, String str, Bitmap bitmap) throws Throwable {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2;
        FileOutputStream fileOutputStream3;
        ?? r1 = 0;
        File file = new File(context.getExternalFilesDir(null), (String) str);
        try {
            try {
                try {
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    fileOutputStream3 = new FileOutputStream(file);
                } catch (Throwable th) {
                    th = th;
                    r1 = str;
                }
                try {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 10, fileOutputStream3);
                    Uri uriFromFile = Uri.fromFile(file);
                    try {
                        fileOutputStream3.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return uriFromFile;
                } catch (FileNotFoundException e2) {
                    fileOutputStream2 = fileOutputStream3;
                    e = e2;
                    e.printStackTrace();
                    str = fileOutputStream2;
                    if (fileOutputStream2 != null) {
                        fileOutputStream2.close();
                        str = fileOutputStream2;
                    }
                    return null;
                } catch (IOException e3) {
                    fileOutputStream = fileOutputStream3;
                    e = e3;
                    e.printStackTrace();
                    str = fileOutputStream;
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                        str = fileOutputStream;
                    }
                    return null;
                } catch (Throwable th2) {
                    r1 = fileOutputStream3;
                    th = th2;
                    if (r1 != 0) {
                        try {
                            r1.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (FileNotFoundException e5) {
                e = e5;
                fileOutputStream2 = null;
            } catch (IOException e6) {
                e = e6;
                fileOutputStream = null;
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (IOException e7) {
            e7.printStackTrace();
            str = str;
        }
    }
}
