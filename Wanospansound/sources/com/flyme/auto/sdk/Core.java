package com.flyme.auto.sdk;

import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.flyme.auto.sdk.ICore;
import com.flyme.auto.sdk.restriction.RestrictionManager;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class Core {
    public static final String CORE_SERVICE_NAME = "flyme_auto_coreservice";
    private static final long MAX_RETRY_TIMES = 50;
    private static final long RETRY_INTERVAL_MS = 500;
    private static final String TAG = "FlymeAutoSDK.Core";
    private static volatile Core mInstance;
    private final Object mCoreLock = new Object();
    private int mRetryCount = 0;
    private ICore mCoreService = null;
    private final Map<String, BaseManager> mManagers = new HashMap();
    private final Object mLock = new Object();
    private final Handler mEventHandler = new Handler(Looper.getMainLooper());
    private final Runnable mConnectionRetryRunnable = new Runnable() { // from class: com.flyme.auto.sdk.Core$$ExternalSyntheticLambda0
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.connectCoreService();
        }
    };
    private final Runnable mConnectionRetryFailRunnable = new Runnable() { // from class: com.flyme.auto.sdk.Core$$ExternalSyntheticLambda1
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.m246lambda$new$0$comflymeautosdkCore();
        }
    };
    private final Runnable mConnectionRetrySuccessfulRunnable = new Runnable() { // from class: com.flyme.auto.sdk.Core$$ExternalSyntheticLambda2
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.m247lambda$new$1$comflymeautosdkCore();
        }
    };

    /* JADX INFO: renamed from: lambda$new$0$com-flyme-auto-sdk-Core, reason: not valid java name */
    /* synthetic */ void m246lambda$new$0$comflymeautosdkCore() {
        synchronized (this.mLock) {
            if (!this.mManagers.isEmpty()) {
                for (BaseManager baseManager : this.mManagers.values()) {
                    baseManager.resetRemoteBinder(null);
                    baseManager.binderServiceDisconnected();
                }
            }
        }
    }

    /* JADX INFO: renamed from: lambda$new$1$com-flyme-auto-sdk-Core, reason: not valid java name */
    /* synthetic */ void m247lambda$new$1$comflymeautosdkCore() {
        synchronized (this.mLock) {
            if (!this.mManagers.isEmpty()) {
                for (String str : this.mManagers.keySet()) {
                    try {
                        BaseManager baseManager = this.mManagers.get(str);
                        if (baseManager != null) {
                            IBinder coreService = isCoreServiceConnected() ? this.mCoreService.getCoreService(str) : null;
                            baseManager.resetRemoteBinder(coreService);
                            if (coreService != null) {
                                baseManager.binderServiceReconnected();
                            } else {
                                baseManager.binderServiceDisconnected();
                            }
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private Core() {
    }

    public static Core getInstance() {
        if (mInstance == null) {
            synchronized (Core.class) {
                if (mInstance == null) {
                    mInstance = new Core();
                    mInstance.connectCoreService();
                }
            }
        }
        return mInstance;
    }

    public BaseManager getManager(String str) {
        synchronized (this.mLock) {
            BaseManager restrictionManager = this.mManagers.get(str);
            if (restrictionManager == null) {
                try {
                    if (((str.hashCode() == -1400048318 && str.equals(ServiceName.RESTRICTION_SERVICE)) ? (byte) 0 : (byte) -1) == 0) {
                        restrictionManager = new RestrictionManager(mInstance);
                    }
                    if (restrictionManager == null) {
                        return null;
                    }
                    if (isCoreServiceConnected()) {
                        restrictionManager.resetRemoteBinder(this.mCoreService.getCoreService(str));
                    }
                    this.mManagers.put(str, restrictionManager);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            return restrictionManager;
        }
    }

    public Handler getEventHandler() {
        return this.mEventHandler;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectCoreService() {
        Log.i(TAG, "connectCoreService: ");
        IBinder coreServiceIBinder = getCoreServiceIBinder();
        if (coreServiceIBinder == null) {
            int i = this.mRetryCount + 1;
            this.mRetryCount = i;
            if (i > MAX_RETRY_TIMES) {
                this.mEventHandler.post(this.mConnectionRetryFailRunnable);
                return;
            } else {
                this.mEventHandler.removeCallbacks(this.mConnectionRetryRunnable);
                this.mEventHandler.postDelayed(this.mConnectionRetryRunnable, 500L);
                return;
            }
        }
        this.mEventHandler.removeCallbacks(this.mConnectionRetryRunnable);
        this.mRetryCount = 0;
        synchronized (this.mCoreLock) {
            this.mCoreService = ICore.Stub.asInterface(coreServiceIBinder);
        }
        this.mEventHandler.post(this.mConnectionRetrySuccessfulRunnable);
    }

    public void reconnectCoreService() {
        Log.i(TAG, "reconnectCoreService: ");
        IBinder coreServiceIBinder = getCoreServiceIBinder();
        if (coreServiceIBinder == null) {
            return;
        }
        synchronized (this.mCoreLock) {
            this.mCoreService = ICore.Stub.asInterface(coreServiceIBinder);
        }
        this.mEventHandler.post(this.mConnectionRetrySuccessfulRunnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void coreServiceDisconnected() {
        synchronized (this.mCoreLock) {
            this.mCoreService = null;
        }
        this.mEventHandler.post(this.mConnectionRetryFailRunnable);
        this.mEventHandler.postDelayed(this.mConnectionRetryRunnable, 500L);
    }

    private boolean isCoreServiceConnected() {
        boolean z;
        synchronized (this.mCoreLock) {
            z = this.mCoreService != null;
        }
        return z;
    }

    private IBinder getCoreServiceIBinder() {
        try {
            IBinder iBinder = (IBinder) Class.forName("android.os.ServiceManager").getMethod("getService", String.class).invoke(new Object(), CORE_SERVICE_NAME);
            if (iBinder == null) {
                return null;
            }
            iBinder.linkToDeath(new CoreBinderDeathRecipient(), 0);
            return iBinder;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private class CoreBinderDeathRecipient implements IBinder.DeathRecipient {
        private CoreBinderDeathRecipient() {
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            Core.this.coreServiceDisconnected();
        }
    }
}
