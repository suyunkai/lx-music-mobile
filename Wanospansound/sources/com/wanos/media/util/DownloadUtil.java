package com.wanos.media.util;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import com.baidubce.BceConfig;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/* JADX INFO: loaded from: classes3.dex */
public class DownloadUtil {
    private static DownloadUtil downloadUtil;
    private final String TAG = "wanos:[download]";
    private final OkHttpClient okHttpClient = new OkHttpClient();

    public interface OnDownloadListener {
        void onDownloadFailed();

        void onDownloadSuccess();

        void onDownloading(int i);
    }

    public static DownloadUtil get() {
        if (downloadUtil == null) {
            downloadUtil = new DownloadUtil();
        }
        return downloadUtil;
    }

    private DownloadUtil() {
    }

    public void download(final String str, final OnDownloadListener onDownloadListener) {
        this.okHttpClient.newCall(new Request.Builder().url(str).build()).enqueue(new Callback() { // from class: com.wanos.media.util.DownloadUtil.1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                onDownloadListener.onDownloadFailed();
            }

            /* JADX WARN: Removed duplicated region for block: B:54:0x00ad A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:56:0x00a8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:69:? A[SYNTHETIC] */
            @Override // okhttp3.Callback
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onResponse(okhttp3.Call r11, okhttp3.Response r12) throws java.lang.Throwable {
                /*
                    r10 = this;
                    java.lang.String r11 = "wanos:[download]"
                    r0 = 2048(0x800, float:2.87E-42)
                    byte[] r0 = new byte[r0]
                    com.wanos.media.util.DownloadUtil r1 = com.wanos.media.util.DownloadUtil.this
                    android.app.Application r2 = com.blankj.utilcode.util.Utils.getApp()
                    r3 = 1
                    java.lang.String r1 = r1.getCacheDirectory(r2, r3)
                    r2 = 0
                    okhttp3.ResponseBody r3 = r12.body()     // Catch: java.lang.Throwable -> L7c java.lang.Exception -> L7f
                    java.io.InputStream r3 = r3.byteStream()     // Catch: java.lang.Throwable -> L7c java.lang.Exception -> L7f
                    okhttp3.ResponseBody r12 = r12.body()     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L78
                    long r4 = r12.getContentLength()     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L78
                    java.io.File r12 = new java.io.File     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L78
                    java.lang.String r6 = r3     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L78
                    java.lang.String r6 = com.wanos.media.util.DownloadUtil.getNameFromUrl(r6)     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L78
                    r12.<init>(r1, r6)     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L78
                    boolean r1 = r12.exists()     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L78
                    if (r1 != 0) goto L36
                    r12.createNewFile()     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L78
                L36:
                    java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L78
                    r1.<init>(r12)     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L78
                    r6 = 0
                L3d:
                    int r12 = r3.read(r0)     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
                    r2 = -1
                    if (r12 == r2) goto L5a
                    r2 = 0
                    r1.write(r0, r2, r12)     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
                    long r8 = (long) r12     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
                    long r6 = r6 + r8
                    float r12 = (float) r6     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
                    r2 = 1065353216(0x3f800000, float:1.0)
                    float r12 = r12 * r2
                    float r2 = (float) r4     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
                    float r12 = r12 / r2
                    r2 = 1120403456(0x42c80000, float:100.0)
                    float r12 = r12 * r2
                    int r12 = (int) r12     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
                    com.wanos.media.util.DownloadUtil$OnDownloadListener r2 = r2     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
                    r2.onDownloading(r12)     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
                    goto L3d
                L5a:
                    r1.flush()     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
                    java.lang.String r12 = "11111111111111111"
                    android.util.Log.i(r11, r12)     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
                    com.wanos.media.util.DownloadUtil$OnDownloadListener r12 = r2     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
                    r12.onDownloadSuccess()     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
                    if (r3 == 0) goto L6c
                    r3.close()     // Catch: java.io.IOException -> L6c
                L6c:
                    r1.close()     // Catch: java.io.IOException -> La4
                    goto La4
                L70:
                    r11 = move-exception
                    goto L76
                L72:
                    r12 = move-exception
                    goto L7a
                L74:
                    r11 = move-exception
                    r1 = r2
                L76:
                    r2 = r3
                    goto La6
                L78:
                    r12 = move-exception
                    r1 = r2
                L7a:
                    r2 = r3
                    goto L81
                L7c:
                    r11 = move-exception
                    r1 = r2
                    goto La6
                L7f:
                    r12 = move-exception
                    r1 = r2
                L81:
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La5
                    r0.<init>()     // Catch: java.lang.Throwable -> La5
                    java.lang.String r3 = "2222222222222222"
                    java.lang.StringBuilder r0 = r0.append(r3)     // Catch: java.lang.Throwable -> La5
                    java.lang.StringBuilder r12 = r0.append(r12)     // Catch: java.lang.Throwable -> La5
                    java.lang.String r12 = r12.toString()     // Catch: java.lang.Throwable -> La5
                    android.util.Log.i(r11, r12)     // Catch: java.lang.Throwable -> La5
                    com.wanos.media.util.DownloadUtil$OnDownloadListener r11 = r2     // Catch: java.lang.Throwable -> La5
                    r11.onDownloadFailed()     // Catch: java.lang.Throwable -> La5
                    if (r2 == 0) goto La1
                    r2.close()     // Catch: java.io.IOException -> La1
                La1:
                    if (r1 == 0) goto La4
                    goto L6c
                La4:
                    return
                La5:
                    r11 = move-exception
                La6:
                    if (r2 == 0) goto Lab
                    r2.close()     // Catch: java.io.IOException -> Lab
                Lab:
                    if (r1 == 0) goto Lb0
                    r1.close()     // Catch: java.io.IOException -> Lb0
                Lb0:
                    throw r11
                */
                throw new UnsupportedOperationException("Method not decompiled: com.wanos.media.util.DownloadUtil.AnonymousClass1.onResponse(okhttp3.Call, okhttp3.Response):void");
            }
        });
    }

    private String isExistDir(String str) throws IOException {
        File file = new File(Environment.getDownloadCacheDirectory(), str);
        if (!file.exists()) {
            file.mkdirs();
        }
        String absolutePath = file.getAbsolutePath();
        Log.i("wanos:[download]", "download path = " + absolutePath);
        return absolutePath;
    }

    public String getCacheDirectory(Context context, boolean z) {
        String externalStorageState;
        try {
            externalStorageState = Environment.getExternalStorageState();
        } catch (NullPointerException unused) {
            externalStorageState = "";
        }
        File externalCacheDir = (z && "mounted".equals(externalStorageState)) ? getExternalCacheDir(context) : null;
        if (externalCacheDir == null) {
            externalCacheDir = new File("/data/data/" + context.getPackageName() + "/wanoslrc/");
        }
        if (!externalCacheDir.exists()) {
            externalCacheDir.mkdirs();
        }
        Log.i("wanos:[download]", "lrcpath = " + externalCacheDir.getAbsolutePath());
        return externalCacheDir.getAbsolutePath();
    }

    private File getExternalCacheDir(Context context) {
        File file = new File(new File(new File(new File(Environment.getExternalStorageDirectory(), "Android"), "data"), context.getPackageName()), "wanoslrc");
        if (file.exists() || file.mkdirs()) {
            return file;
        }
        Log.w("wanos:[download]", "Unable to create external cache directory");
        return null;
    }

    public static String getNameFromUrl(String str) {
        return str.substring(str.lastIndexOf(BceConfig.BOS_DELIMITER) + 1);
    }

    public static void savePcm(float[] fArr, String str) {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + BceConfig.BOS_DELIMITER + str);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException unused) {
                throw new IllegalStateException("未能创建" + file);
            }
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file.getPath(), true);
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(fArr.length * 4);
            byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
            for (float f : fArr) {
                byteBufferAllocate.putFloat(f);
            }
            fileOutputStream.write(byteBufferAllocate.array());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
