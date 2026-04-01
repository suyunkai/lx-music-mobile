package com.flyme.auto.sdk;

import android.os.Handler;
import android.os.IBinder;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseManager {
    protected final Core mCore;
    protected IBinder mRemoteBinder = null;

    protected abstract void binderServiceDisconnected();

    protected abstract void binderServiceReconnected();

    public BaseManager(Core core) {
        this.mCore = core;
    }

    protected synchronized void resetRemoteBinder(IBinder iBinder) {
        this.mRemoteBinder = iBinder;
    }

    protected synchronized IBinder getRemoteBinder() {
        return this.mRemoteBinder;
    }

    protected Handler getEventHandler() {
        return this.mCore.getEventHandler();
    }

    public synchronized boolean isBinderServiceConnected() {
        return this.mRemoteBinder != null;
    }
}
