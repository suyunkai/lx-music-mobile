package com.ecarx.eas.framework.sdk.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class IApi<T extends IInterface> {
    protected static final int ERROR_CODE = -1;
    private static final String TAG = "IApi";
    protected final AtomicBoolean mAliveFlag = new AtomicBoolean(false);
    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() { // from class: com.ecarx.eas.framework.sdk.common.internal.IApi.1
        @Override // android.os.IBinder.DeathRecipient
        public final void binderDied() {
            Log.d(IApi.TAG, IApi.this.getClass().getName() + ">>DeathRecipient");
            IApi.this.mAliveFlag.set(false);
            IApi.this.onBinderDied();
        }
    };
    protected volatile T mService;

    public void init(T t) {
        String str = TAG;
        Log.e(str, getClass().getName() + " init(T api)");
        if (this.mAliveFlag.get() && this.mService != null) {
            if (t != null) {
                Log.i(str, "IApi->init(" + t.getClass() + ")");
            }
            if (this.mService.asBinder() != null && this.mService.asBinder().isBinderAlive() && this.mService.asBinder().pingBinder()) {
                try {
                    this.mService.asBinder().unlinkToDeath(this.mDeathRecipient, 0);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
        this.mService = t;
        if (this.mService != null) {
            try {
                if (this.mService.asBinder() != null && this.mService.asBinder().isBinderAlive() && this.mService.asBinder().pingBinder()) {
                    this.mService.asBinder().linkToDeath(this.mDeathRecipient, 0);
                }
            } catch (RemoteException e) {
                if (t != null) {
                    Log.e(TAG, t.getClass() + " 异常>> ", e);
                }
                e.printStackTrace();
            }
            this.mAliveFlag.set(true);
            return;
        }
        this.mAliveFlag.set(false);
    }

    public boolean isAlive() {
        return this.mAliveFlag.get();
    }

    public boolean isNotAlive() {
        return !this.mAliveFlag.get();
    }

    protected void onBinderDied() {
        if (this.mService == null || this.mService.asBinder() == null) {
            return;
        }
        try {
            this.mService.asBinder().unlinkToDeath(this.mDeathRecipient, 0);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
