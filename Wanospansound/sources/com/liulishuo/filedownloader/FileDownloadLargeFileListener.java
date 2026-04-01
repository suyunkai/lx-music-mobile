package com.liulishuo.filedownloader;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FileDownloadLargeFileListener extends FileDownloadListener {
    @Override // com.liulishuo.filedownloader.FileDownloadListener
    protected void connected(BaseDownloadTask baseDownloadTask, String str, boolean z, int i, int i2) {
    }

    protected void connected(BaseDownloadTask baseDownloadTask, String str, boolean z, long j, long j2) {
    }

    @Override // com.liulishuo.filedownloader.FileDownloadListener
    protected void paused(BaseDownloadTask baseDownloadTask, int i, int i2) {
    }

    protected abstract void paused(BaseDownloadTask baseDownloadTask, long j, long j2);

    @Override // com.liulishuo.filedownloader.FileDownloadListener
    protected void pending(BaseDownloadTask baseDownloadTask, int i, int i2) {
    }

    protected abstract void pending(BaseDownloadTask baseDownloadTask, long j, long j2);

    @Override // com.liulishuo.filedownloader.FileDownloadListener
    protected void progress(BaseDownloadTask baseDownloadTask, int i, int i2) {
    }

    protected abstract void progress(BaseDownloadTask baseDownloadTask, long j, long j2);

    @Override // com.liulishuo.filedownloader.FileDownloadListener
    protected void retry(BaseDownloadTask baseDownloadTask, Throwable th, int i, int i2) {
    }

    protected void retry(BaseDownloadTask baseDownloadTask, Throwable th, int i, long j) {
    }

    public FileDownloadLargeFileListener() {
    }

    public FileDownloadLargeFileListener(int i) {
        super(i);
    }
}
