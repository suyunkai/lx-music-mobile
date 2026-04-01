package com.wanos.lyric;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import com.baidubce.BceConfig;
import java.io.File;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/* JADX INFO: loaded from: classes3.dex */
public class DownloadUtil {
    private static DownloadUtil downloadUtil;
    private final String TAG = "wanos:[DownloadUtil]";
    private final OkHttpClient okHttpClient = new OkHttpClient();

    public interface OnDownloadListener {
        void onDownloadFailed();

        void onDownloadStart();

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

    public void download(final Context context, final String str, final OnDownloadListener onDownloadListener) {
        try {
            onDownloadListener.onDownloadStart();
            this.okHttpClient.newCall(new Request.Builder().url(str).build()).enqueue(new Callback() { // from class: com.wanos.lyric.DownloadUtil.1
                @Override // okhttp3.Callback
                public void onFailure(Call call, IOException iOException) {
                    onDownloadListener.onDownloadFailed();
                }

                /* JADX WARN: Removed duplicated region for block: B:48:0x00a6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:58:0x00ab A[EXC_TOP_SPLITTER, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:69:? A[SYNTHETIC] */
                @Override // okhttp3.Callback
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public void onResponse(okhttp3.Call r11, okhttp3.Response r12) throws java.lang.Throwable {
                    /*
                        r10 = this;
                        java.lang.String r11 = "wanos:[DownloadUtil]"
                        r0 = 2048(0x800, float:2.87E-42)
                        byte[] r0 = new byte[r0]
                        com.wanos.lyric.DownloadUtil r1 = com.wanos.lyric.DownloadUtil.this
                        android.content.Context r2 = r3
                        r3 = 1
                        java.lang.String r1 = r1.getCacheDirectory(r2, r3)
                        r2 = 0
                        okhttp3.ResponseBody r3 = r12.body()     // Catch: java.lang.Throwable -> L7a java.lang.Exception -> L7d
                        java.io.InputStream r3 = r3.byteStream()     // Catch: java.lang.Throwable -> L7a java.lang.Exception -> L7d
                        okhttp3.ResponseBody r12 = r12.body()     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L76
                        long r4 = r12.contentLength()     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L76
                        java.io.File r12 = new java.io.File     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L76
                        java.lang.String r6 = r4     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L76
                        java.lang.String r6 = com.wanos.lyric.DownloadUtil.getNameFromUrl(r6)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L76
                        r12.<init>(r1, r6)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L76
                        boolean r1 = r12.exists()     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L76
                        if (r1 != 0) goto L34
                        r12.createNewFile()     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L76
                    L34:
                        java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L76
                        r1.<init>(r12)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L76
                        r6 = 0
                    L3b:
                        int r12 = r3.read(r0)     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L70
                        r2 = -1
                        if (r12 == r2) goto L58
                        r2 = 0
                        r1.write(r0, r2, r12)     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L70
                        long r8 = (long) r12     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L70
                        long r6 = r6 + r8
                        float r12 = (float) r6     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L70
                        r2 = 1065353216(0x3f800000, float:1.0)
                        float r12 = r12 * r2
                        float r2 = (float) r4     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L70
                        float r12 = r12 / r2
                        r2 = 1120403456(0x42c80000, float:100.0)
                        float r12 = r12 * r2
                        int r12 = (int) r12     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L70
                        com.wanos.lyric.DownloadUtil$OnDownloadListener r2 = r2     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L70
                        r2.onDownloading(r12)     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L70
                        goto L3b
                    L58:
                        r1.flush()     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L70
                        java.lang.String r12 = "onDownloadSuccess"
                        android.util.Log.i(r11, r12)     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L70
                        com.wanos.lyric.DownloadUtil$OnDownloadListener r12 = r2     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L70
                        r12.onDownloadSuccess()     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L70
                        if (r3 == 0) goto L6a
                        r3.close()     // Catch: java.io.IOException -> L6a
                    L6a:
                        r1.close()     // Catch: java.io.IOException -> La2
                        goto La2
                    L6e:
                        r11 = move-exception
                        goto L74
                    L70:
                        r12 = move-exception
                        goto L78
                    L72:
                        r11 = move-exception
                        r1 = r2
                    L74:
                        r2 = r3
                        goto La4
                    L76:
                        r12 = move-exception
                        r1 = r2
                    L78:
                        r2 = r3
                        goto L7f
                    L7a:
                        r11 = move-exception
                        r1 = r2
                        goto La4
                    L7d:
                        r12 = move-exception
                        r1 = r2
                    L7f:
                        java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La3
                        r0.<init>()     // Catch: java.lang.Throwable -> La3
                        java.lang.String r3 = "onDownloadFailed"
                        java.lang.StringBuilder r0 = r0.append(r3)     // Catch: java.lang.Throwable -> La3
                        java.lang.StringBuilder r12 = r0.append(r12)     // Catch: java.lang.Throwable -> La3
                        java.lang.String r12 = r12.toString()     // Catch: java.lang.Throwable -> La3
                        android.util.Log.i(r11, r12)     // Catch: java.lang.Throwable -> La3
                        com.wanos.lyric.DownloadUtil$OnDownloadListener r11 = r2     // Catch: java.lang.Throwable -> La3
                        r11.onDownloadFailed()     // Catch: java.lang.Throwable -> La3
                        if (r2 == 0) goto L9f
                        r2.close()     // Catch: java.io.IOException -> L9f
                    L9f:
                        if (r1 == 0) goto La2
                        goto L6a
                    La2:
                        return
                    La3:
                        r11 = move-exception
                    La4:
                        if (r2 == 0) goto La9
                        r2.close()     // Catch: java.io.IOException -> La9
                    La9:
                        if (r1 == 0) goto Lae
                        r1.close()     // Catch: java.io.IOException -> Lae
                    Lae:
                        throw r11
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.wanos.lyric.DownloadUtil.AnonymousClass1.onResponse(okhttp3.Call, okhttp3.Response):void");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String isExistDir(String str) throws IOException {
        File file = new File(Environment.getDownloadCacheDirectory(), str);
        if (!file.exists()) {
            file.mkdirs();
        }
        String absolutePath = file.getAbsolutePath();
        Log.i("wanos:[DownloadUtil]", "download path = " + absolutePath);
        return absolutePath;
    }

    public String getCacheDirectory(Context context, boolean z) {
        File cacheDir = context.getCacheDir();
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
        Log.i("wanos:[DownloadUtil]", "lrcpath = " + cacheDir.getAbsolutePath());
        return cacheDir.getAbsolutePath();
    }

    private File getExternalCacheDir(Context context) {
        File file = new File(new File(new File(new File(Environment.getExternalStorageDirectory(), "Android"), "data"), context.getPackageName()), "wanoslrc");
        if (file.exists() || file.mkdirs()) {
            return file;
        }
        Log.w("wanos:[DownloadUtil]", "Unable to create external cache directory");
        return null;
    }

    public static String getNameFromUrl(String str) {
        return str.substring(str.lastIndexOf(BceConfig.BOS_DELIMITER) + 1);
    }
}
