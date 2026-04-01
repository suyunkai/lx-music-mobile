package com.liulishuo.filedownloader.notification;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadList;
import com.liulishuo.filedownloader.FileDownloadListener;

/* JADX INFO: loaded from: classes3.dex */
public abstract class FileDownloadNotificationListener extends FileDownloadListener {
    private final FileDownloadNotificationHelper helper;

    @Override // com.liulishuo.filedownloader.FileDownloadListener
    protected void blockComplete(BaseDownloadTask baseDownloadTask) {
    }

    protected abstract BaseNotificationItem create(BaseDownloadTask baseDownloadTask);

    protected boolean disableNotification(BaseDownloadTask baseDownloadTask) {
        return false;
    }

    protected boolean interceptCancel(BaseDownloadTask baseDownloadTask, BaseNotificationItem baseNotificationItem) {
        return false;
    }

    @Override // com.liulishuo.filedownloader.FileDownloadListener
    protected void warn(BaseDownloadTask baseDownloadTask) {
    }

    public FileDownloadNotificationListener(FileDownloadNotificationHelper fileDownloadNotificationHelper) {
        if (fileDownloadNotificationHelper == null) {
            throw new IllegalArgumentException("helper must not be null!");
        }
        this.helper = fileDownloadNotificationHelper;
    }

    public FileDownloadNotificationHelper getHelper() {
        return this.helper;
    }

    public void addNotificationItem(int i) {
        BaseDownloadTask.IRunningTask iRunningTask;
        if (i == 0 || (iRunningTask = FileDownloadList.getImpl().get(i)) == null) {
            return;
        }
        addNotificationItem(iRunningTask.getOrigin());
    }

    public void addNotificationItem(BaseDownloadTask baseDownloadTask) {
        BaseNotificationItem baseNotificationItemCreate;
        if (disableNotification(baseDownloadTask) || (baseNotificationItemCreate = create(baseDownloadTask)) == null) {
            return;
        }
        this.helper.add(baseNotificationItemCreate);
    }

    public void destroyNotification(BaseDownloadTask baseDownloadTask) {
        if (disableNotification(baseDownloadTask)) {
            return;
        }
        this.helper.showIndeterminate(baseDownloadTask.getId(), baseDownloadTask.getStatus());
        BaseNotificationItem baseNotificationItemRemove = this.helper.remove(baseDownloadTask.getId());
        if (interceptCancel(baseDownloadTask, baseNotificationItemRemove) || baseNotificationItemRemove == null) {
            return;
        }
        baseNotificationItemRemove.cancel();
    }

    public void showIndeterminate(BaseDownloadTask baseDownloadTask) {
        if (disableNotification(baseDownloadTask)) {
            return;
        }
        this.helper.showIndeterminate(baseDownloadTask.getId(), baseDownloadTask.getStatus());
    }

    public void showProgress(BaseDownloadTask baseDownloadTask, int i, int i2) {
        if (disableNotification(baseDownloadTask)) {
            return;
        }
        this.helper.showProgress(baseDownloadTask.getId(), baseDownloadTask.getSmallFileSoFarBytes(), baseDownloadTask.getSmallFileTotalBytes());
    }

    @Override // com.liulishuo.filedownloader.FileDownloadListener
    protected void pending(BaseDownloadTask baseDownloadTask, int i, int i2) {
        addNotificationItem(baseDownloadTask);
        showIndeterminate(baseDownloadTask);
    }

    @Override // com.liulishuo.filedownloader.FileDownloadListener
    protected void started(BaseDownloadTask baseDownloadTask) {
        super.started(baseDownloadTask);
        showIndeterminate(baseDownloadTask);
    }

    @Override // com.liulishuo.filedownloader.FileDownloadListener
    protected void progress(BaseDownloadTask baseDownloadTask, int i, int i2) {
        showProgress(baseDownloadTask, i, i2);
    }

    @Override // com.liulishuo.filedownloader.FileDownloadListener
    protected void retry(BaseDownloadTask baseDownloadTask, Throwable th, int i, int i2) {
        super.retry(baseDownloadTask, th, i, i2);
        showIndeterminate(baseDownloadTask);
    }

    @Override // com.liulishuo.filedownloader.FileDownloadListener
    protected void completed(BaseDownloadTask baseDownloadTask) {
        destroyNotification(baseDownloadTask);
    }

    @Override // com.liulishuo.filedownloader.FileDownloadListener
    protected void paused(BaseDownloadTask baseDownloadTask, int i, int i2) {
        destroyNotification(baseDownloadTask);
    }

    @Override // com.liulishuo.filedownloader.FileDownloadListener
    protected void error(BaseDownloadTask baseDownloadTask, Throwable th) {
        destroyNotification(baseDownloadTask);
    }
}
