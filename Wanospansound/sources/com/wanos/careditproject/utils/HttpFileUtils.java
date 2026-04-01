package com.wanos.careditproject.utils;

import android.util.Log;
import androidx.media3.common.MimeTypes;
import java.io.File;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/* JADX INFO: loaded from: classes3.dex */
public class HttpFileUtils {
    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse(MimeTypes.IMAGE_PNG);
    private OkHttpClient mOkHttpClient;

    private void downAsynFile(String str, final String str2) {
        this.mOkHttpClient.newCall(new Request.Builder().url(str).build()).enqueue(new Callback() { // from class: com.wanos.careditproject.utils.HttpFileUtils.1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
            }

            /* JADX WARN: Removed duplicated region for block: B:39:0x0062 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            @Override // okhttp3.Callback
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onResponse(okhttp3.Call r6, okhttp3.Response r7) throws java.lang.Throwable {
                /*
                    r5 = this;
                    java.lang.String r6 = "wangshu"
                    okhttp3.ResponseBody r7 = r7.body()
                    java.io.InputStream r7 = r7.byteStream()
                    r0 = 0
                    java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L3a java.io.IOException -> L3c
                    java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> L3a java.io.IOException -> L3c
                    java.lang.String r3 = r2     // Catch: java.lang.Throwable -> L3a java.io.IOException -> L3c
                    r2.<init>(r3)     // Catch: java.lang.Throwable -> L3a java.io.IOException -> L3c
                    r1.<init>(r2)     // Catch: java.lang.Throwable -> L3a java.io.IOException -> L3c
                    r0 = 2048(0x800, float:2.87E-42)
                    byte[] r0 = new byte[r0]     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L5e
                L1b:
                    int r2 = r7.read(r0)     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L5e
                    r3 = -1
                    if (r2 == r3) goto L27
                    r3 = 0
                    r1.write(r0, r3, r2)     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L5e
                    goto L1b
                L27:
                    r1.flush()     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L5e
                    r1.close()     // Catch: java.io.IOException -> L31
                    r7.close()     // Catch: java.io.IOException -> L31
                    goto L50
                L31:
                    r6 = move-exception
                    java.lang.RuntimeException r7 = new java.lang.RuntimeException
                    r7.<init>(r6)
                    throw r7
                L38:
                    r0 = move-exception
                    goto L40
                L3a:
                    r6 = move-exception
                    goto L60
                L3c:
                    r1 = move-exception
                    r4 = r1
                    r1 = r0
                    r0 = r4
                L40:
                    java.lang.String r2 = "IOException"
                    android.util.Log.i(r6, r2)     // Catch: java.lang.Throwable -> L5e
                    r0.printStackTrace()     // Catch: java.lang.Throwable -> L5e
                    if (r1 == 0) goto L4d
                    r1.close()     // Catch: java.io.IOException -> L57
                L4d:
                    r7.close()     // Catch: java.io.IOException -> L57
                L50:
                    java.lang.String r7 = "文件下载成功"
                    android.util.Log.d(r6, r7)
                    return
                L57:
                    r6 = move-exception
                    java.lang.RuntimeException r7 = new java.lang.RuntimeException
                    r7.<init>(r6)
                    throw r7
                L5e:
                    r6 = move-exception
                    r0 = r1
                L60:
                    if (r0 == 0) goto L65
                    r0.close()     // Catch: java.io.IOException -> L69
                L65:
                    r7.close()     // Catch: java.io.IOException -> L69
                    throw r6
                L69:
                    r6 = move-exception
                    java.lang.RuntimeException r7 = new java.lang.RuntimeException
                    r7.<init>(r6)
                    throw r7
                */
                throw new UnsupportedOperationException("Method not decompiled: com.wanos.careditproject.utils.HttpFileUtils.AnonymousClass1.onResponse(okhttp3.Call, okhttp3.Response):void");
            }
        });
    }

    private void postAsynFile() {
        this.mOkHttpClient.newCall(new Request.Builder().url("https://api.github.com/markdown/raw").post(RequestBody.create(MEDIA_TYPE_MARKDOWN, new File("/sdcard/wangshu.txt"))).build()).enqueue(new Callback() { // from class: com.wanos.careditproject.utils.HttpFileUtils.2
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("wangshu", response.body().string());
            }
        });
    }
}
