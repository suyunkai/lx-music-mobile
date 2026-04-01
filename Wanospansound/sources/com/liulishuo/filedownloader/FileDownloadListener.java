package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.util.FileDownloadLog;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FileDownloadListener {
    protected void blockComplete(BaseDownloadTask baseDownloadTask) throws Throwable {
    }

    protected abstract void completed(BaseDownloadTask baseDownloadTask);

    protected void connected(BaseDownloadTask baseDownloadTask, String str, boolean z, int i, int i2) {
    }

    protected abstract void error(BaseDownloadTask baseDownloadTask, Throwable th);

    protected boolean isInvalid() {
        return false;
    }

    protected abstract void paused(BaseDownloadTask baseDownloadTask, int i, int i2);

    protected abstract void pending(BaseDownloadTask baseDownloadTask, int i, int i2);

    protected abstract void progress(BaseDownloadTask baseDownloadTask, int i, int i2);

    protected void retry(BaseDownloadTask baseDownloadTask, Throwable th, int i, int i2) {
    }

    protected void started(BaseDownloadTask baseDownloadTask) {
    }

    protected abstract void warn(BaseDownloadTask baseDownloadTask);

    public FileDownloadListener() {
    }

    public FileDownloadListener(int i) {
        FileDownloadLog.w(this, "not handle priority any more", new Object[0]);
    }
}
