package com.danikula.videocache;

import android.text.TextUtils;
import android.util.Log;
import com.danikula.videocache.file.FileCache;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
class HttpProxyCache extends ProxyCache {
    private static final float NO_CACHE_BARRIER = 0.2f;
    private static final long NO_CACHE_OFFSIZE = 2097152;
    public static final String TAG = "wanos:[HttpProxyCache]";
    private final FileCache cache;
    private int contentType;
    private CacheListener listener;
    private long mediaId;
    private int mediaType;
    private final HttpUrlSource source;

    public HttpProxyCache(HttpUrlSource httpUrlSource, FileCache fileCache) {
        super(httpUrlSource, fileCache);
        this.mediaType = -1;
        this.mediaId = -1L;
        this.contentType = 1;
        this.cache = fileCache;
        this.source = httpUrlSource;
    }

    public void registerCacheListener(CacheListener cacheListener) {
        this.listener = cacheListener;
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

    public void processRequest(GetRequest getRequest, Socket socket) throws IOException, ProxyCacheException {
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
        bufferedOutputStream.write(newResponseHeaders(getRequest).getBytes(StandardCharsets.UTF_8));
        long j = getRequest.rangeOffset;
        if (isUseCache(getRequest)) {
            Log.i(TAG, "HttpProxyCache with cache");
            responseWithCache(bufferedOutputStream, j);
        } else {
            responseWithoutCache(bufferedOutputStream, j);
            Log.i(TAG, "HttpProxyCache without cache");
        }
    }

    private boolean isUseCache(GetRequest getRequest) throws ProxyCacheException {
        long length = this.source.length();
        return (((length > 0L ? 1 : (length == 0L ? 0 : -1)) > 0) && getRequest.partial && ((float) getRequest.rangeOffset) > ((float) this.cache.available()) + (((float) length) * 0.2f)) ? false : true;
    }

    private String newResponseHeaders(GetRequest getRequest) throws IOException, ProxyCacheException {
        this.source.setMediaInfo(this.mediaType, this.mediaId, this.contentType);
        String mime = this.source.getMime();
        boolean z = !TextUtils.isEmpty(mime);
        long jAvailable = this.cache.isCompleted() ? this.cache.available() : this.source.length();
        Log.i(TAG, "cache length " + jAvailable);
        boolean z2 = jAvailable >= 0;
        return (getRequest.partial ? "HTTP/1.1 206 PARTIAL CONTENT\n" : "HTTP/1.1 200 OK\n") + "Accept-Ranges: bytes\n" + (z2 ? format("Content-Length: %d\n", Long.valueOf(getRequest.partial ? jAvailable - getRequest.rangeOffset : jAvailable)) : "") + (z2 && getRequest.partial ? format("Content-Range: bytes %d-%d/%d\n", Long.valueOf(getRequest.rangeOffset), Long.valueOf(jAvailable - 1), Long.valueOf(jAvailable)) : "") + (z ? format("Content-Type: %s\n", mime) : "") + "\n";
    }

    private void responseWithCache(OutputStream outputStream, long j) throws IOException, ProxyCacheException {
        byte[] bArr = new byte[8192];
        Log.i(TAG, "offset:" + j);
        while (true) {
            int i = read(bArr, j, 8192);
            if (i != -1) {
                outputStream.write(bArr, 0, i);
                j += (long) i;
            } else {
                Log.i(TAG, "offset flush:" + j);
                outputStream.flush();
                return;
            }
        }
    }

    private void responseWithoutCache(OutputStream outputStream, long j) throws IOException, ProxyCacheException {
        HttpUrlSource httpUrlSource = new HttpUrlSource(this.source);
        try {
            httpUrlSource.setMediaInfo(this.mediaType, this.mediaId, this.contentType);
            httpUrlSource.open((int) j);
            Log.i(TAG, "open:" + j);
            byte[] bArr = new byte[8192];
            while (true) {
                int i = httpUrlSource.read(bArr);
                if (i != -1) {
                    outputStream.write(bArr, 0, i);
                    j += (long) i;
                } else {
                    outputStream.flush();
                    Log.i(TAG, "flush :" + j);
                    return;
                }
            }
        } finally {
            httpUrlSource.close();
        }
    }

    private String format(String str, Object... objArr) {
        return String.format(Locale.US, str, objArr);
    }

    @Override // com.danikula.videocache.ProxyCache
    protected void onCachePercentsAvailableChanged(int i) {
        CacheListener cacheListener = this.listener;
        if (cacheListener != null) {
            cacheListener.onCacheAvailable(this.cache.file, this.source.getUrl(), i);
        }
    }
}
