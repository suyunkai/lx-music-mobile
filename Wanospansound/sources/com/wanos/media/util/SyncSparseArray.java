package com.wanos.media.util;

import android.util.SparseArray;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes3.dex */
public class SyncSparseArray<T> extends SparseArray<T> {
    private final ReentrantLock mLock;

    public SyncSparseArray() {
        this(true);
    }

    public SyncSparseArray(boolean z) {
        this(0, z);
    }

    public SyncSparseArray(int i, boolean z) {
        super(i);
        this.mLock = new ReentrantLock(z);
    }

    @Override // android.util.SparseArray
    public void put(int i, T t) {
        try {
            this.mLock.lock();
            super.put(i, t);
        } finally {
            this.mLock.unlock();
        }
    }

    @Override // android.util.SparseArray
    public void set(int i, T t) {
        try {
            this.mLock.lock();
            put(i, t);
        } finally {
            this.mLock.unlock();
        }
    }

    @Override // android.util.SparseArray
    public void remove(int i) {
        try {
            this.mLock.lock();
            super.remove(i);
        } finally {
            this.mLock.unlock();
        }
    }

    @Override // android.util.SparseArray
    public void removeAt(int i) {
        try {
            this.mLock.lock();
            super.removeAt(i);
        } finally {
            this.mLock.unlock();
        }
    }

    @Override // android.util.SparseArray
    public void delete(int i) {
        try {
            this.mLock.lock();
            super.delete(i);
        } finally {
            this.mLock.unlock();
        }
    }

    @Override // android.util.SparseArray
    public int indexOfKey(int i) {
        try {
            this.mLock.lock();
            return super.indexOfKey(i);
        } finally {
            this.mLock.unlock();
        }
    }

    @Override // android.util.SparseArray
    public int indexOfValue(T t) {
        try {
            this.mLock.lock();
            return super.indexOfValue(t);
        } finally {
            this.mLock.unlock();
        }
    }

    @Override // android.util.SparseArray
    public T valueAt(int i) {
        try {
            this.mLock.lock();
            return (T) super.valueAt(i);
        } finally {
            this.mLock.unlock();
        }
    }

    @Override // android.util.SparseArray
    public int keyAt(int i) {
        try {
            this.mLock.lock();
            return super.keyAt(i);
        } finally {
            this.mLock.unlock();
        }
    }

    public boolean isContains(int i) {
        try {
            this.mLock.lock();
            return indexOfKey(i) >= 0;
        } finally {
            this.mLock.unlock();
        }
    }

    @Override // android.util.SparseArray
    public T get(int i) {
        try {
            this.mLock.lock();
            return (T) super.get(i);
        } finally {
            this.mLock.unlock();
        }
    }

    @Override // android.util.SparseArray
    public T get(int i, T t) {
        try {
            this.mLock.lock();
            return (T) super.get(i, t);
        } finally {
            this.mLock.unlock();
        }
    }

    @Override // android.util.SparseArray
    public void clear() {
        try {
            this.mLock.lock();
            super.clear();
        } finally {
            this.mLock.unlock();
        }
    }
}
