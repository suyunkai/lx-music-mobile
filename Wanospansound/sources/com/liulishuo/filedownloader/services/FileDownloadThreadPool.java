package com.liulishuo.filedownloader.services;

import android.util.SparseArray;
import com.liulishuo.filedownloader.download.DownloadLaunchRunnable;
import com.liulishuo.filedownloader.util.FileDownloadExecutors;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadProperties;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/* JADX INFO: loaded from: classes3.dex */
class FileDownloadThreadPool {
    private int mMaxThreadCount;
    private ThreadPoolExecutor mThreadPool;
    private SparseArray<DownloadLaunchRunnable> runnablePool = new SparseArray<>();
    private final String threadPrefix = "Network";
    private int mIgnoreCheckTimes = 0;

    FileDownloadThreadPool(int i) {
        this.mThreadPool = FileDownloadExecutors.newDefaultThreadPool(i, "Network");
        this.mMaxThreadCount = i;
    }

    public synchronized boolean setMaxNetworkThreadCount(int i) {
        if (exactSize() > 0) {
            FileDownloadLog.w(this, "Can't change the max network thread count, because the  network thread pool isn't in IDLE, please try again after all running tasks are completed or invoking FileDownloader#pauseAll directly.", new Object[0]);
            return false;
        }
        int validNetworkThreadCount = FileDownloadProperties.getValidNetworkThreadCount(i);
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "change the max network thread count, from %d to %d", Integer.valueOf(this.mMaxThreadCount), Integer.valueOf(validNetworkThreadCount));
        }
        List<Runnable> listShutdownNow = this.mThreadPool.shutdownNow();
        this.mThreadPool = FileDownloadExecutors.newDefaultThreadPool(validNetworkThreadCount, "Network");
        if (listShutdownNow.size() > 0) {
            FileDownloadLog.w(this, "recreate the network thread pool and discard %d tasks", Integer.valueOf(listShutdownNow.size()));
        }
        this.mMaxThreadCount = validNetworkThreadCount;
        return true;
    }

    public void execute(DownloadLaunchRunnable downloadLaunchRunnable) {
        downloadLaunchRunnable.pending();
        synchronized (this) {
            this.runnablePool.put(downloadLaunchRunnable.getId(), downloadLaunchRunnable);
        }
        this.mThreadPool.execute(downloadLaunchRunnable);
        int i = this.mIgnoreCheckTimes;
        if (i >= 600) {
            filterOutNoExist();
            this.mIgnoreCheckTimes = 0;
        } else {
            this.mIgnoreCheckTimes = i + 1;
        }
    }

    public void cancel(int i) {
        filterOutNoExist();
        synchronized (this) {
            DownloadLaunchRunnable downloadLaunchRunnable = this.runnablePool.get(i);
            if (downloadLaunchRunnable != null) {
                downloadLaunchRunnable.pause();
                boolean zRemove = this.mThreadPool.remove(downloadLaunchRunnable);
                if (FileDownloadLog.NEED_LOG) {
                    FileDownloadLog.d(this, "successful cancel %d %B", Integer.valueOf(i), Boolean.valueOf(zRemove));
                }
            }
            this.runnablePool.remove(i);
        }
    }

    private synchronized void filterOutNoExist() {
        SparseArray<DownloadLaunchRunnable> sparseArray = new SparseArray<>();
        int size = this.runnablePool.size();
        for (int i = 0; i < size; i++) {
            int iKeyAt = this.runnablePool.keyAt(i);
            DownloadLaunchRunnable downloadLaunchRunnable = this.runnablePool.get(iKeyAt);
            if (downloadLaunchRunnable != null && downloadLaunchRunnable.isAlive()) {
                sparseArray.put(iKeyAt, downloadLaunchRunnable);
            }
        }
        this.runnablePool = sparseArray;
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized boolean isInThreadPool(int r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            android.util.SparseArray<com.liulishuo.filedownloader.download.DownloadLaunchRunnable> r0 = r1.runnablePool     // Catch: java.lang.Throwable -> L16
            java.lang.Object r2 = r0.get(r2)     // Catch: java.lang.Throwable -> L16
            com.liulishuo.filedownloader.download.DownloadLaunchRunnable r2 = (com.liulishuo.filedownloader.download.DownloadLaunchRunnable) r2     // Catch: java.lang.Throwable -> L16
            if (r2 == 0) goto L13
            boolean r2 = r2.isAlive()     // Catch: java.lang.Throwable -> L16
            if (r2 == 0) goto L13
            r2 = 1
            goto L14
        L13:
            r2 = 0
        L14:
            monitor-exit(r1)
            return r2
        L16:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.services.FileDownloadThreadPool.isInThreadPool(int):boolean");
    }

    public synchronized int findRunningTaskIdBySameTempPath(String str, int i) {
        if (str == null) {
            return 0;
        }
        int size = this.runnablePool.size();
        for (int i2 = 0; i2 < size; i2++) {
            DownloadLaunchRunnable downloadLaunchRunnableValueAt = this.runnablePool.valueAt(i2);
            if (downloadLaunchRunnableValueAt != null && downloadLaunchRunnableValueAt.isAlive() && downloadLaunchRunnableValueAt.getId() != i && str.equals(downloadLaunchRunnableValueAt.getTempFilePath())) {
                return downloadLaunchRunnableValueAt.getId();
            }
        }
        return 0;
    }

    public synchronized int exactSize() {
        filterOutNoExist();
        return this.runnablePool.size();
    }

    public synchronized List<Integer> getAllExactRunningDownloadIds() {
        ArrayList arrayList;
        filterOutNoExist();
        arrayList = new ArrayList();
        for (int i = 0; i < this.runnablePool.size(); i++) {
            SparseArray<DownloadLaunchRunnable> sparseArray = this.runnablePool;
            arrayList.add(Integer.valueOf(sparseArray.get(sparseArray.keyAt(i)).getId()));
        }
        return arrayList;
    }
}
