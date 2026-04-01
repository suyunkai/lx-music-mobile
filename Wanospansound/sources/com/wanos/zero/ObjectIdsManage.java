package com.wanos.zero;

import android.util.SparseBooleanArray;

/* JADX INFO: loaded from: classes3.dex */
class ObjectIdsManage {
    private static final ObjectIdsManage sInstance = new ObjectIdsManage();
    private final SparseBooleanArray mIds = new SparseBooleanArray(128);

    public static ObjectIdsManage getInstance() {
        return sInstance;
    }

    private ObjectIdsManage() {
        for (int i = 0; i < 128; i++) {
            this.mIds.put(i, false);
        }
    }

    synchronized int getId() {
        for (int i = 0; i < this.mIds.size(); i++) {
            if (!this.mIds.get(i, false)) {
                this.mIds.put(i, true);
                return i;
            }
        }
        return -1;
    }

    synchronized boolean releaseId(int i) {
        if (i < 0 || i >= 128) {
            return false;
        }
        this.mIds.put(i, false);
        return true;
    }
}
