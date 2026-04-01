package com.danikula.videocache;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.danikula.videocache.file.FileCache;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes2.dex */
final class HttpProxyCacheServerClients {
    public static final String TAG = "wanos:[HttpProxyCacheServerClients]";
    private final AtomicInteger clientsCount = new AtomicInteger(0);
    private final Config config;
    private int contentType;
    private final List<CacheListener> listeners;
    private long mediaId;
    private int meidaType;
    private volatile HttpProxyCache proxyCache;
    private final CacheListener uiCacheListener;
    private final String url;

    public HttpProxyCacheServerClients(String str, Config config) {
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        this.listeners = copyOnWriteArrayList;
        this.meidaType = -1;
        this.mediaId = -1L;
        this.contentType = 1;
        this.url = (String) Preconditions.checkNotNull(str);
        this.config = (Config) Preconditions.checkNotNull(config);
        this.uiCacheListener = new UiListenerHandler(str, copyOnWriteArrayList);
        Log.i(TAG, "HttpProxyCacheServerClients init");
    }

    public void setMediaInfo(int i, long j) {
        this.meidaType = i;
        this.mediaId = j;
    }

    public void setMediaInfo(int i, long j, int i2) {
        this.meidaType = i;
        this.mediaId = j;
        this.contentType = i2;
    }

    public void processRequest(GetRequest getRequest, Socket socket) throws IOException, ProxyCacheException {
        startProcessRequest();
        try {
            this.clientsCount.incrementAndGet();
            this.proxyCache.setMediaInfo(this.meidaType, this.mediaId, this.contentType);
            this.proxyCache.processRequest(getRequest, socket);
        } finally {
            finishProcessRequest();
        }
    }

    private synchronized void startProcessRequest() throws ProxyCacheException {
        this.proxyCache = this.proxyCache == null ? newHttpProxyCache() : this.proxyCache;
    }

    private synchronized void finishProcessRequest() {
        if (this.clientsCount.decrementAndGet() <= 0) {
            this.proxyCache.shutdown();
            this.proxyCache = null;
        }
    }

    public void registerCacheListener(CacheListener cacheListener) {
        this.listeners.add(cacheListener);
    }

    public void unregisterCacheListener(CacheListener cacheListener) {
        this.listeners.remove(cacheListener);
    }

    public void initCacheComplete(File file, String str) {
        Iterator<CacheListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onCacheAvailable(file, str, 100);
        }
    }

    public void shutdown() {
        this.listeners.clear();
        if (this.proxyCache != null) {
            this.proxyCache.registerCacheListener(null);
            this.proxyCache.shutdown();
            this.proxyCache = null;
        }
        this.clientsCount.set(0);
    }

    public int getClientsCount() {
        return this.clientsCount.get();
    }

    private HttpProxyCache newHttpProxyCache() throws ProxyCacheException {
        HttpUrlSource httpUrlSource = new HttpUrlSource(this.url, this.config.sourceInfoStorage, this.config.headerInjector);
        Log.i(TAG, "config.generateCacheFile(url):" + this.config.generateCacheFile(this.url));
        HttpProxyCache httpProxyCache = new HttpProxyCache(httpUrlSource, new FileCache(this.config.generateCacheFile(this.url), this.config.diskUsage));
        httpProxyCache.registerCacheListener(this.uiCacheListener);
        return httpProxyCache;
    }

    private static final class UiListenerHandler extends Handler implements CacheListener {
        private final List<CacheListener> listeners;
        private final String url;

        public UiListenerHandler(String str, List<CacheListener> list) {
            super(Looper.getMainLooper());
            this.url = str;
            this.listeners = list;
        }

        @Override // com.danikula.videocache.CacheListener
        public void onCacheAvailable(File file, String str, int i) {
            Message messageObtainMessage = obtainMessage();
            messageObtainMessage.arg1 = i;
            messageObtainMessage.obj = file;
            sendMessage(messageObtainMessage);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Iterator<CacheListener> it = this.listeners.iterator();
            while (it.hasNext()) {
                it.next().onCacheAvailable((File) message.obj, this.url, message.arg1);
            }
        }
    }

    public void setConnect(boolean z) {
        this.proxyCache.setConnect(z);
    }
}
