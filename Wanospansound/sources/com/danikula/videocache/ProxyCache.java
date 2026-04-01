package com.danikula.videocache;

import android.util.Log;
import java.lang.Thread;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes2.dex */
class ProxyCache {
    private static final int MAX_READ_SOURCE_ATTEMPTS = 10;
    private static final String TAG = "wanos:[ProxyCache]";
    private final Cache cache;
    private final Source source;
    private volatile Thread sourceReaderThread;
    private volatile boolean stopped;
    private final Object wc = new Object();
    private final Object stopLock = new Object();
    private volatile int percentsAvailable = -1;
    protected boolean bConnect = true;
    int num = 0;
    private final AtomicInteger readSourceErrorsCount = new AtomicInteger();

    protected void onCachePercentsAvailableChanged(int i) {
    }

    public ProxyCache(Source source, Cache cache) {
        this.source = (Source) Preconditions.checkNotNull(source);
        this.cache = (Cache) Preconditions.checkNotNull(cache);
    }

    public int read(byte[] bArr, long j, int i) throws ProxyCacheException {
        ProxyCacheUtils.assertBuffer(bArr, j, i);
        int i2 = this.readSourceErrorsCount.get();
        while (!this.cache.isCompleted() && this.cache.available() < ((long) i) + j && !this.stopped) {
            int i3 = this.readSourceErrorsCount.get();
            if (i2 != i3 && i3 != 0) {
                synchronized (this.wc) {
                    try {
                        try {
                            this.wc.wait(((long) i3) * 2 * 1000);
                        } catch (InterruptedException e) {
                            throw new ProxyCacheException("Waiting source data is interrupted!", e);
                        }
                    } finally {
                    }
                }
                i2 = i3;
            }
            readSourceAsync();
            waitForSourceData();
            checkReadSourceErrorsCount();
        }
        int i4 = this.cache.read(bArr, j, i);
        if (this.cache.isCompleted() && this.percentsAvailable != 100) {
            this.percentsAvailable = 100;
            onCachePercentsAvailableChanged(100);
        }
        return i4;
    }

    private void checkReadSourceErrorsCount() throws ProxyCacheException {
        int i = this.readSourceErrorsCount.get();
        if (i < 10) {
            return;
        }
        this.readSourceErrorsCount.set(0);
        onCachePercentsAvailableChanged(-1);
        throw new ProxyCacheException("Error reading source " + i + " times");
    }

    public void shutdown() {
        synchronized (this.stopLock) {
            Log.d(TAG, "Shutdown proxy for " + this.source);
            try {
                this.stopped = true;
                if (this.sourceReaderThread != null) {
                    this.sourceReaderThread.interrupt();
                }
                this.cache.close();
            } catch (ProxyCacheException e) {
                onError(e);
            }
        }
    }

    private synchronized void readSourceAsync() throws ProxyCacheException {
        boolean z = (this.sourceReaderThread == null || this.sourceReaderThread.getState() == Thread.State.TERMINATED) ? false : true;
        if (!this.stopped && !this.cache.isCompleted() && !z) {
            this.sourceReaderThread = new Thread(new SourceReaderRunnable(), "Source reader for " + this.source);
            this.sourceReaderThread.start();
        }
    }

    private void waitForSourceData() throws ProxyCacheException {
        synchronized (this.wc) {
            try {
                try {
                    this.wc.wait(1000L);
                } catch (InterruptedException e) {
                    throw new ProxyCacheException("Waiting source data is interrupted!", e);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void notifyNewCacheDataAvailable(long j, long j2) {
        onCacheAvailable(j, j2);
        synchronized (this.wc) {
            this.wc.notifyAll();
        }
    }

    protected void onCacheAvailable(long j, long j2) {
        int i = (j2 > 0L ? 1 : (j2 == 0L ? 0 : -1)) == 0 ? 100 : (int) ((j / j2) * 100.0f);
        boolean z = i != this.percentsAvailable;
        if ((j2 >= 0) && z) {
            onCachePercentsAvailableChanged(i);
        }
        this.percentsAvailable = i;
    }

    public void setConnect(boolean z) {
        this.bConnect = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void readSource() {
        if (!this.bConnect) {
            return;
        }
        long length = -1;
        long jAvailable = 0;
        try {
            jAvailable = this.cache.available();
            this.source.open(jAvailable);
            length = this.source.length();
            byte[] bArr = new byte[8192];
            while (true) {
                int i = this.source.read(bArr);
                if (i != -1) {
                    synchronized (this.stopLock) {
                        if (isStopped()) {
                            return;
                        } else {
                            this.cache.append(bArr, i);
                        }
                    }
                    jAvailable += (long) i;
                    notifyNewCacheDataAvailable(jAvailable, length);
                } else {
                    tryComplete();
                    onSourceRead();
                    break;
                }
            }
        } finally {
            try {
            } finally {
            }
        }
    }

    private void onSourceRead() {
        this.percentsAvailable = 100;
        onCachePercentsAvailableChanged(this.percentsAvailable);
    }

    private void tryComplete() throws ProxyCacheException {
        synchronized (this.stopLock) {
            if (!isStopped() && this.cache.available() == this.source.length()) {
                this.cache.complete();
            }
        }
    }

    private boolean isStopped() {
        return Thread.currentThread().isInterrupted() || this.stopped;
    }

    private void closeSource() {
        try {
            this.source.close();
        } catch (ProxyCacheException e) {
            onError(new ProxyCacheException("Error closing source " + this.source, e));
        }
    }

    protected final void onError(Throwable th) {
        if (th instanceof InterruptedProxyCacheException) {
            Log.d(TAG, "ProxyCache is interrupted");
        } else {
            Log.i(TAG, "ProxyCache error: 线程：" + Thread.currentThread().getName());
            Log.e(TAG, "ProxyCache error", th);
        }
    }

    private class SourceReaderRunnable implements Runnable {
        private SourceReaderRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ProxyCache.this.readSource();
        }
    }

    public void pauseCache(boolean z) {
        this.stopped = z;
    }
}
