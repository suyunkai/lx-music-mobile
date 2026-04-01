package com.danikula.videocache;

import android.text.TextUtils;
import android.util.Log;
import com.baidubce.BceConfig;
import com.danikula.videocache.headers.EmptyHeadersInjector;
import com.danikula.videocache.headers.HeaderInjector;
import com.danikula.videocache.sourcestorage.SourceInfoStorage;
import com.danikula.videocache.sourcestorage.SourceInfoStorageFactory;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.WanosCommunication.response.GetAudioBookChapterDetailResponse;
import com.wanos.WanosCommunication.response.GetMusicDetailResponse;
import com.wanos.WanosCommunication.response.GetMusicListResponse;
import com.wanos.WanosCommunication.router.HttpRouter;
import com.wanos.commonlibrary.event.DailyExpiredEvent;
import com.wanos.commonlibrary.event.ResourceNotExistEvent;
import com.wanos.commonlibrary.utils.ToastUtil;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import org.greenrobot.eventbus.EventBus;
import retrofit2.Response;

/* JADX INFO: loaded from: classes2.dex */
public class HttpUrlSource implements Source {
    private static final int MAX_REDIRECTS = 5;
    private static final String TAG = "wanos:[HttpUrlSource]";
    private HttpURLConnection connection;
    private int contentType;
    private final HeaderInjector headerInjector;
    private InputStream inputStream;
    private long mediaId;
    private int mediaType;
    int num;
    private SourceInfo sourceInfo;
    private final SourceInfoStorage sourceInfoStorage;

    public HttpUrlSource(String str) {
        this(str, SourceInfoStorageFactory.newEmptySourceInfoStorage());
    }

    public HttpUrlSource(String str, SourceInfoStorage sourceInfoStorage) {
        this(str, sourceInfoStorage, new EmptyHeadersInjector());
    }

    public HttpUrlSource(String str, SourceInfoStorage sourceInfoStorage, HeaderInjector headerInjector) {
        this.mediaType = -1;
        this.mediaId = -1L;
        this.contentType = 1;
        this.num = 1;
        this.sourceInfoStorage = (SourceInfoStorage) Preconditions.checkNotNull(sourceInfoStorage);
        this.headerInjector = (HeaderInjector) Preconditions.checkNotNull(headerInjector);
        SourceInfo sourceInfo = sourceInfoStorage.get(str);
        this.sourceInfo = sourceInfo == null ? new SourceInfo(str, -2147483648L, ProxyCacheUtils.getSupposablyMime(str)) : sourceInfo;
    }

    public HttpUrlSource(HttpUrlSource httpUrlSource) {
        this.mediaType = -1;
        this.mediaId = -1L;
        this.contentType = 1;
        this.num = 1;
        this.sourceInfo = httpUrlSource.sourceInfo;
        this.sourceInfoStorage = httpUrlSource.sourceInfoStorage;
        this.headerInjector = httpUrlSource.headerInjector;
    }

    @Override // com.danikula.videocache.Source
    public synchronized long length() throws ProxyCacheException {
        if (this.sourceInfo.length == -2147483648L) {
            fetchContentInfo();
        }
        return this.sourceInfo.length;
    }

    public void setMediaInfo(int i, long j) {
        this.mediaType = i;
        this.mediaId = j;
    }

    public void setMediaInfo(int i, long j, int i2) {
        this.mediaType = i;
        this.mediaId = j;
        this.contentType = i2;
    }

    @Override // com.danikula.videocache.Source
    public void open(long j) throws ProxyCacheException {
        try {
            HttpURLConnection httpURLConnectionOpenConnection = openConnection(j, -1);
            this.connection = httpURLConnectionOpenConnection;
            String contentType = httpURLConnectionOpenConnection.getContentType();
            this.inputStream = new BufferedInputStream(this.connection.getInputStream(), 8192);
            HttpURLConnection httpURLConnection = this.connection;
            SourceInfo sourceInfo = new SourceInfo(this.sourceInfo.url, readSourceAvailableBytes(httpURLConnection, j, httpURLConnection.getResponseCode()), contentType);
            this.sourceInfo = sourceInfo;
            this.sourceInfoStorage.put(sourceInfo.url, this.sourceInfo);
        } catch (IOException e) {
            throw new ProxyCacheException("Error opening connection for " + this.sourceInfo.url + " with offset " + j, e);
        }
    }

    private long readSourceAvailableBytes(HttpURLConnection httpURLConnection, long j, int i) throws IOException {
        long contentLength = getContentLength(httpURLConnection);
        return i == 200 ? contentLength : i == 206 ? contentLength + j : this.sourceInfo.length;
    }

    private long getContentLength(HttpURLConnection httpURLConnection) {
        String headerField = httpURLConnection.getHeaderField("Content-Length");
        if (headerField == null) {
            return -1L;
        }
        return Long.parseLong(headerField);
    }

    @Override // com.danikula.videocache.Source
    public void close() throws ProxyCacheException {
        HttpURLConnection httpURLConnection = this.connection;
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (ArrayIndexOutOfBoundsException e) {
                Log.e(TAG, "Error closing connection correctly. Should happen only on Android L. If anybody know how to fix it, please visit https://github.com/danikula/AndroidVideoCache/issues/88. Until good solution is not know, just ignore this issue :(", e);
            } catch (IllegalArgumentException e2) {
                e = e2;
                throw new RuntimeException("Wait... but why? WTF!? Really shouldn't happen any more after fixing https://github.com/danikula/AndroidVideoCache/issues/43. If you read it on your device log, please, notify me danikula@gmail.com or create issue here https://github.com/danikula/AndroidVideoCache/issues.", e);
            } catch (NullPointerException e3) {
                e = e3;
                throw new RuntimeException("Wait... but why? WTF!? Really shouldn't happen any more after fixing https://github.com/danikula/AndroidVideoCache/issues/43. If you read it on your device log, please, notify me danikula@gmail.com or create issue here https://github.com/danikula/AndroidVideoCache/issues.", e);
            }
        }
    }

    @Override // com.danikula.videocache.Source
    public int read(byte[] bArr) throws ProxyCacheException {
        InputStream inputStream = this.inputStream;
        if (inputStream == null) {
            throw new ProxyCacheException("Error reading data from " + this.sourceInfo.url + ": connection is absent!");
        }
        try {
            return inputStream.read(bArr, 0, bArr.length);
        } catch (InterruptedIOException e) {
            throw new InterruptedProxyCacheException("Reading source " + this.sourceInfo.url + " is interrupted", e);
        } catch (IOException e2) {
            throw new ProxyCacheException("Error reading data from " + this.sourceInfo.url, e2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0088  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void fetchContentInfo() throws java.lang.Throwable {
        /*
            r10 = this;
            java.lang.String r0 = "Source info fetched: "
            java.lang.String r1 = "Error fetching info from "
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Read content info from "
            r2.<init>(r3)
            com.danikula.videocache.SourceInfo r3 = r10.sourceInfo
            java.lang.String r3 = r3.url
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            java.lang.String r3 = "wanos:[HttpUrlSource]"
            android.util.Log.d(r3, r2)
            r4 = 0
            r2 = 10000(0x2710, float:1.4013E-41)
            r6 = 0
            java.net.HttpURLConnection r2 = r10.openConnection(r4, r2)     // Catch: java.lang.Throwable -> L61 java.io.IOException -> L64
            long r4 = r10.getContentLength(r2)     // Catch: java.lang.Throwable -> L5d java.io.IOException -> L5f
            java.lang.String r7 = r2.getContentType()     // Catch: java.lang.Throwable -> L5d java.io.IOException -> L5f
            java.io.InputStream r6 = r2.getInputStream()     // Catch: java.lang.Throwable -> L5d java.io.IOException -> L5f
            com.danikula.videocache.SourceInfo r8 = new com.danikula.videocache.SourceInfo     // Catch: java.lang.Throwable -> L5d java.io.IOException -> L5f
            com.danikula.videocache.SourceInfo r9 = r10.sourceInfo     // Catch: java.lang.Throwable -> L5d java.io.IOException -> L5f
            java.lang.String r9 = r9.url     // Catch: java.lang.Throwable -> L5d java.io.IOException -> L5f
            r8.<init>(r9, r4, r7)     // Catch: java.lang.Throwable -> L5d java.io.IOException -> L5f
            r10.sourceInfo = r8     // Catch: java.lang.Throwable -> L5d java.io.IOException -> L5f
            com.danikula.videocache.sourcestorage.SourceInfoStorage r4 = r10.sourceInfoStorage     // Catch: java.lang.Throwable -> L5d java.io.IOException -> L5f
            java.lang.String r5 = r8.url     // Catch: java.lang.Throwable -> L5d java.io.IOException -> L5f
            com.danikula.videocache.SourceInfo r7 = r10.sourceInfo     // Catch: java.lang.Throwable -> L5d java.io.IOException -> L5f
            r4.put(r5, r7)     // Catch: java.lang.Throwable -> L5d java.io.IOException -> L5f
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L5d java.io.IOException -> L5f
            r4.<init>(r0)     // Catch: java.lang.Throwable -> L5d java.io.IOException -> L5f
            com.danikula.videocache.SourceInfo r0 = r10.sourceInfo     // Catch: java.lang.Throwable -> L5d java.io.IOException -> L5f
            java.lang.StringBuilder r0 = r4.append(r0)     // Catch: java.lang.Throwable -> L5d java.io.IOException -> L5f
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L5d java.io.IOException -> L5f
            android.util.Log.d(r3, r0)     // Catch: java.lang.Throwable -> L5d java.io.IOException -> L5f
            com.danikula.videocache.ProxyCacheUtils.close(r6)
            if (r2 == 0) goto L82
            goto L7f
        L5d:
            r0 = move-exception
            goto L83
        L5f:
            r0 = move-exception
            goto L66
        L61:
            r0 = move-exception
            r2 = r6
            goto L83
        L64:
            r0 = move-exception
            r2 = r6
        L66:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L5d
            r4.<init>(r1)     // Catch: java.lang.Throwable -> L5d
            com.danikula.videocache.SourceInfo r1 = r10.sourceInfo     // Catch: java.lang.Throwable -> L5d
            java.lang.String r1 = r1.url     // Catch: java.lang.Throwable -> L5d
            java.lang.StringBuilder r1 = r4.append(r1)     // Catch: java.lang.Throwable -> L5d
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L5d
            android.util.Log.e(r3, r1, r0)     // Catch: java.lang.Throwable -> L5d
            com.danikula.videocache.ProxyCacheUtils.close(r6)
            if (r2 == 0) goto L82
        L7f:
            r2.disconnect()
        L82:
            return
        L83:
            com.danikula.videocache.ProxyCacheUtils.close(r6)
            if (r2 == 0) goto L8b
            r2.disconnect()
        L8b:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.danikula.videocache.HttpUrlSource.fetchContentInfo():void");
    }

    private HttpURLConnection openConnection(long j, int i) throws IOException, ProxyCacheException {
        HttpURLConnection httpURLConnection;
        boolean z;
        int i2;
        String headerField = this.sourceInfo.url;
        String[] strArrSplit = headerField.split(BceConfig.BOS_DELIMITER);
        if (!strArrSplit[strArrSplit.length - 1].equals("ping")) {
            long j2 = this.mediaId;
            if (j2 != -1 && this.mediaType != 10 && ((i2 = this.contentType) == 1 || i2 == 4 || i2 == 6)) {
                Log.i(TAG, "聚艺厅今日推荐鉴权");
                Response<GetMusicDetailResponse> recommendMusicDetail = HttpRouter.getRecommendService().getRecommendMusicDetail(String.valueOf(this.mediaId), this.contentType);
                if (recommendMusicDetail.code() == 200) {
                    if (recommendMusicDetail.body() != null && recommendMusicDetail.body().data != null && recommendMusicDetail.body().data.getMusicInfo() != null) {
                        headerField = recommendMusicDetail.body().data.getMusicInfo().getMusicPath();
                        if (TextUtils.isEmpty(headerField)) {
                            throw new ProxyCacheException("url is null");
                        }
                        Log.i(TAG, "latest url=" + headerField);
                    } else if (recommendMusicDetail.body().code == 2200) {
                        Log.i(TAG, "聚艺厅每日推荐列表过期");
                        Response<GetMusicListResponse> recommendMusicList = HttpRouter.getRecommendService().getRecommendMusicList(1, 1, -12L, "-12");
                        if (recommendMusicList.isSuccessful() && recommendMusicList.body().data != null && recommendMusicList.body().data.getMusicList() != null && !recommendMusicList.body().data.getMusicList().isEmpty()) {
                            EventBus.getDefault().post(new DailyExpiredEvent(recommendMusicList.body().data.getMusicList().get(0)));
                        }
                    } else {
                        if (recommendMusicDetail.body().code == 100 || recommendMusicDetail.body().code == 101 || recommendMusicDetail.body().code == 207) {
                            EventBus.getDefault().post(new ResourceNotExistEvent(this.mediaType, this.mediaId));
                        }
                        ToastUtil.showMsg("获取播放资源失败");
                    }
                }
            } else {
                int i3 = this.mediaType;
                if (i3 == 0 && j2 != -1) {
                    Log.i(TAG, "音乐鉴权");
                    Response<GetMusicDetailResponse> synchMusicDetail = WanOSRetrofitUtil.getSynchMusicDetail(this.mediaId);
                    if (synchMusicDetail.code() == 200) {
                        if (synchMusicDetail.body().data != null && synchMusicDetail.body().data.getMusicInfo() != null) {
                            headerField = synchMusicDetail.body().data.getMusicInfo().getMusicPath();
                            Log.i(TAG, "latest url=" + headerField);
                        } else {
                            if (synchMusicDetail.body() != null && (synchMusicDetail.body().code == 101 || synchMusicDetail.body().code == 100 || synchMusicDetail.body().code == 207)) {
                                EventBus.getDefault().post(new ResourceNotExistEvent(this.mediaType, this.mediaId));
                            }
                            ToastUtil.showMsg("获取音乐失败");
                        }
                    }
                } else if (i3 == 1 && j2 != -1) {
                    Log.i(TAG, "有声书鉴权");
                    Response<GetAudioBookChapterDetailResponse> audioBookChapterDetail = WanOSRetrofitUtil.getAudioBookChapterDetail(this.mediaId);
                    if (audioBookChapterDetail.code() == 200) {
                        if (audioBookChapterDetail.body().data != null) {
                            headerField = audioBookChapterDetail.body().data.getPath();
                            if (headerField.equals("")) {
                                throw new ProxyCacheException("url is null");
                            }
                            Log.i(TAG, "latest url=" + headerField);
                        } else {
                            if (audioBookChapterDetail.body().code == 100 || audioBookChapterDetail.body().code == 101 || audioBookChapterDetail.body().code == 207) {
                                EventBus.getDefault().post(new ResourceNotExistEvent(this.mediaType, this.mediaId));
                            }
                            ToastUtil.showMsg("获取有声书失败");
                        }
                    }
                } else if (i3 == 10) {
                    headerField = HttpRouter.getCreatorService().getCreatorResUrl(String.valueOf(this.mediaId), this.contentType);
                }
            }
        }
        int i4 = 0;
        do {
            httpURLConnection = (HttpURLConnection) new URL(headerField).openConnection();
            injectCustomHeaders(httpURLConnection, headerField);
            if (j > 0) {
                httpURLConnection.setRequestProperty("Range", "bytes=" + j + "-");
            }
            if (i > 0) {
                httpURLConnection.setConnectTimeout(i);
                httpURLConnection.setReadTimeout(i);
            }
            int responseCode = httpURLConnection.getResponseCode();
            z = responseCode == 301 || responseCode == 302 || responseCode == 303;
            if (z) {
                headerField = httpURLConnection.getHeaderField("Location");
                i4++;
                httpURLConnection.disconnect();
            }
            if (i4 > 5) {
                throw new ProxyCacheException("Too many redirects: " + i4);
            }
        } while (z);
        return httpURLConnection;
    }

    private void injectCustomHeaders(HttpURLConnection httpURLConnection, String str) {
        for (Map.Entry<String, String> entry : this.headerInjector.addHeaders(str).entrySet()) {
            httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
        }
    }

    public synchronized String getMime() throws ProxyCacheException {
        if (TextUtils.isEmpty(this.sourceInfo.mime)) {
            fetchContentInfo();
        }
        return this.sourceInfo.mime;
    }

    public String getUrl() {
        return this.sourceInfo.url;
    }

    public String toString() {
        return "HttpUrlSource{sourceInfo='" + this.sourceInfo + "}";
    }
}
