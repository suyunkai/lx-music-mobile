package com.liulishuo.filedownloader.notification;

import android.util.SparseArray;
import com.liulishuo.filedownloader.notification.BaseNotificationItem;

/* JADX INFO: loaded from: classes3.dex */
public class FileDownloadNotificationHelper<T extends BaseNotificationItem> {
    private final SparseArray<T> notificationArray = new SparseArray<>();

    public T get(int i) {
        return this.notificationArray.get(i);
    }

    public boolean contains(int i) {
        return get(i) != null;
    }

    public T remove(int i) {
        T t = (T) get(i);
        if (t == null) {
            return null;
        }
        this.notificationArray.remove(i);
        return t;
    }

    public void add(T t) {
        this.notificationArray.remove(t.getId());
        this.notificationArray.put(t.getId(), t);
    }

    public void showProgress(int i, int i2, int i3) {
        BaseNotificationItem baseNotificationItem = get(i);
        if (baseNotificationItem == null) {
            return;
        }
        baseNotificationItem.updateStatus(3);
        baseNotificationItem.update(i2, i3);
    }

    public void showIndeterminate(int i, int i2) {
        BaseNotificationItem baseNotificationItem = get(i);
        if (baseNotificationItem == null) {
            return;
        }
        baseNotificationItem.updateStatus(i2);
        baseNotificationItem.show(false);
    }

    public void cancel(int i) {
        BaseNotificationItem baseNotificationItemRemove = remove(i);
        if (baseNotificationItemRemove == null) {
            return;
        }
        baseNotificationItemRemove.cancel();
    }

    public void clear() {
        SparseArray<T> sparseArrayClone = this.notificationArray.clone();
        this.notificationArray.clear();
        for (int i = 0; i < sparseArrayClone.size(); i++) {
            sparseArrayClone.get(sparseArrayClone.keyAt(i)).cancel();
        }
    }
}
