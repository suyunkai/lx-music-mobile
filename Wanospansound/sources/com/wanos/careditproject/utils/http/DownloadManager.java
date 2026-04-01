package com.wanos.careditproject.utils.http;

import android.util.Log;
import androidx.media3.common.MimeTypes;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.careditproject.view.dialog.EditLoadingValue;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: loaded from: classes3.dex */
public class DownloadManager {
    public static DownloadManager DownloadManager = null;
    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse(MimeTypes.IMAGE_PNG);
    private static final String TAG = "wanos:[DownloadManager]";
    private DownloadResponse downloadResponse;
    private DownloadResponse jsonResponse;
    private boolean isStop = false;
    private OkHttpClient mOkHttpClient = new OkHttpClient();

    public interface DownloadResponse {
        void onResponse(boolean z);
    }

    public void cancelAllCalls() {
    }

    public static DownloadManager getInstance() {
        if (DownloadManager == null) {
            DownloadManager = new DownloadManager();
        }
        return DownloadManager;
    }

    DownloadManager() {
    }

    public void downAsynFile(String str, String str2, List<File> list, DownloadResponse downloadResponse) {
        if (this.mOkHttpClient == null) {
            this.mOkHttpClient = new OkHttpClient();
        }
        downFile(str, str2, list, downloadResponse);
    }

    private void downFile(final String str, final String str2, final List<File> list, final DownloadResponse downloadResponse) {
        this.mOkHttpClient.newCall(new Request.Builder().addHeader("Connection", "close").url(str).build()).enqueue(new Callback() { // from class: com.wanos.careditproject.utils.http.DownloadManager.1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                iOException.printStackTrace();
                DownloadResponse downloadResponse2 = downloadResponse;
                if (downloadResponse2 != null) {
                    downloadResponse2.onResponse(false);
                }
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) {
                if (response.getIsSuccessful()) {
                    File file = new File(str2);
                    DownloadManager.this.trimCacheFiles(file, list);
                    InputStream inputStreamByteStream = response.body().byteStream();
                    long jContentLength = response.body().getContentLength();
                    EditingUtils.log("downFile222 , total=" + jContentLength + ", " + str);
                    if (inputStreamByteStream != null) {
                        try {
                            FileOutputStream fileOutputStream = new FileOutputStream(file);
                            byte[] bArr = new byte[2048];
                            long j = 0;
                            while (true) {
                                int i = inputStreamByteStream.read(bArr);
                                if (i != -1) {
                                    fileOutputStream.write(bArr, 0, i);
                                    long j2 = j + ((long) i);
                                    EditLoadingValue.getInstance().setFileProgress(str, j2, jContentLength);
                                    j = j2;
                                } else {
                                    fileOutputStream.flush();
                                    try {
                                        fileOutputStream.close();
                                        inputStreamByteStream.close();
                                        Log.d("wangshu", "文件下载成功");
                                        downloadResponse.onResponse(true);
                                        return;
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            }
                        } catch (IOException e2) {
                            Log.i("wangshu", "IOException");
                            e2.printStackTrace();
                            try {
                                inputStreamByteStream.close();
                                downloadResponse.onResponse(false);
                            } catch (IOException e3) {
                                throw new RuntimeException(e3);
                            }
                        }
                    } else {
                        downloadResponse.onResponse(false);
                    }
                } else {
                    downloadResponse.onResponse(false);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void trimCacheFiles(File file, List<File> list) {
        List<File> lruListFiles = Files.getLruListFiles(file.getParentFile());
        long jCountTotalSize = countTotalSize(lruListFiles);
        for (File file2 : lruListFiles) {
            boolean z = true;
            boolean z2 = jCountTotalSize <= EditingUtils.DEFAULT_LONG_FILE_MAX_CACHE_SIZE;
            if (z2 || list == null || list.size() <= 0) {
                z = false;
            } else {
                Iterator<File> it = list.iterator();
                while (it.hasNext()) {
                    if (file.getCanonicalFile().equals(it.next().getCanonicalFile())) {
                        break;
                    }
                }
                z = false;
            }
            if (!z2 && !z) {
                long length = file2.length();
                if (file2.delete()) {
                    jCountTotalSize -= length;
                    Log.i(TAG, "Cache file " + file2 + " is deleted because it exceeds cache limit");
                } else {
                    Log.e(TAG, "Error deleting file " + file2 + " for trimming cache");
                }
            }
        }
    }

    private long countTotalSize(List<File> list) {
        Iterator<File> it = list.iterator();
        long length = 0;
        while (it.hasNext()) {
            length += it.next().length();
        }
        return length;
    }

    public void stop() {
        OkHttpClient okHttpClient = this.mOkHttpClient;
        if (okHttpClient != null) {
            for (Call call : okHttpClient.dispatcher().queuedCalls()) {
                if (!call.getCanceled()) {
                    call.cancel();
                }
            }
            for (Call call2 : this.mOkHttpClient.dispatcher().runningCalls()) {
                if (!call2.getCanceled()) {
                    call2.cancel();
                }
            }
            this.mOkHttpClient.dispatcher().executorService().shutdown();
            this.mOkHttpClient = null;
        }
    }
}
