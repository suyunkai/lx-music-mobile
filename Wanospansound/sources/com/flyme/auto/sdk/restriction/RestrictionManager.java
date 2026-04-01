package com.flyme.auto.sdk.restriction;

import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.flyme.auto.sdk.BaseManager;
import com.flyme.auto.sdk.Core;
import com.flyme.auto.sdk.ServiceName;
import com.flyme.auto.sdk.restriction.IOnRestrictionStateChangedListener;
import com.flyme.auto.sdk.restriction.IRestrictionService;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class RestrictionManager extends BaseManager {
    public static final int FUNC_OFF = 0;
    private static final int MSG_RESTRICTION_STATE_CHANGE = 0;
    public static final int NO_RESTRICTED = 1;
    public static final int RESTRICTED_RUNNING = 3;
    public static final int RESTRICTED_START = 2;
    private static final String TAG = "FlymeAutoSDK.RestrictionManager";
    public static final int UNKNOWN = -1;
    private static RestrictionManager mInstance;
    private final EventCallbackHandler mEventCallbackHandler;
    private RestrictionsChangeListenerToService mListenerToService;
    private volatile IRestrictionService mRestrictionService;
    private final List<OnRestrictionStateChangedListener> mRestrictionStateListeners;

    public interface OnRestrictionStateChangedListener {
        void onRestrictionStateChange(int i);
    }

    public RestrictionManager(Core core) {
        super(core);
        this.mRestrictionStateListeners = new ArrayList();
        this.mEventCallbackHandler = new EventCallbackHandler(this, getEventHandler().getLooper());
    }

    public static RestrictionManager getInstance() {
        if (mInstance == null) {
            synchronized (RestrictionManager.class) {
                if (mInstance == null) {
                    mInstance = (RestrictionManager) Core.getInstance().getManager(ServiceName.RESTRICTION_SERVICE);
                }
            }
        }
        return mInstance;
    }

    public boolean isServiceConnected() {
        return isBinderServiceConnected();
    }

    private boolean checkServiceState() {
        try {
            boolean z = true;
            if (this.mRestrictionService == null) {
                synchronized (this) {
                    if (this.mRestrictionService == null) {
                        IBinder remoteBinder = getRemoteBinder();
                        if (remoteBinder == null) {
                            Core.getInstance().reconnectCoreService();
                            remoteBinder = getRemoteBinder();
                        }
                        if (remoteBinder == null) {
                            return false;
                        }
                        this.mRestrictionService = IRestrictionService.Stub.asInterface(remoteBinder);
                        if (this.mRestrictionService == null) {
                            z = false;
                        }
                        return z;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            Log.e(TAG, "checkServiceState: ", e);
            return false;
        }
    }

    @Override // com.flyme.auto.sdk.BaseManager
    protected void binderServiceDisconnected() {
        this.mListenerToService = null;
        this.mRestrictionService = null;
        synchronized (this.mRestrictionStateListeners) {
            this.mRestrictionStateListeners.clear();
        }
    }

    @Override // com.flyme.auto.sdk.BaseManager
    protected void binderServiceReconnected() {
        Log.d(TAG, "binderService ReConnected . ");
    }

    public int getRestrictionState() {
        int restrictionState;
        if (checkServiceState()) {
            try {
                restrictionState = this.mRestrictionService.getRestrictionState();
            } catch (RemoteException e) {
                Log.d(TAG, " getRestrictionState Fail:" + e);
                restrictionState = -1;
            }
        } else {
            restrictionState = -1;
        }
        Log.i(TAG, "getRestrictionState: state = " + restrictionState);
        return restrictionState;
    }

    public boolean isPermittedPkg(String str) {
        if (!checkServiceState()) {
            return false;
        }
        try {
            return this.mRestrictionService.isPermittedPkg(str);
        } catch (RemoteException e) {
            Log.d(TAG, " isPermittedPkg E:" + e);
            return false;
        }
    }

    public boolean shouldRestricted(String str) {
        if (!checkServiceState()) {
            return false;
        }
        try {
            return this.mRestrictionService.shouldRestricted(str);
        } catch (RemoteException e) {
            Log.d(TAG, " shouldRestricted E:" + e);
            return false;
        }
    }

    public boolean shouldRestrictedByDisplayId(String str, int i) {
        if (!checkServiceState()) {
            return false;
        }
        try {
            return this.mRestrictionService.shouldRestrictedByDisplayId(str, i);
        } catch (RemoteException e) {
            Log.e(TAG, "shouldRestrictedByDisplayId: ", e);
            return false;
        }
    }

    public void registerListener(OnRestrictionStateChangedListener onRestrictionStateChangedListener) {
        if (checkServiceState()) {
            synchronized (this.mRestrictionStateListeners) {
                if (!this.mRestrictionStateListeners.contains(onRestrictionStateChangedListener) && onRestrictionStateChangedListener != null) {
                    this.mRestrictionStateListeners.add(onRestrictionStateChangedListener);
                    try {
                        if (this.mListenerToService == null) {
                            this.mListenerToService = new RestrictionsChangeListenerToService(this);
                        }
                        this.mRestrictionService.registerListener(this.mListenerToService);
                        return;
                    } catch (RemoteException e) {
                        Log.d(TAG, " registerListener E:" + e);
                        return;
                    }
                }
                Log.d(TAG, "Listener fail to registered");
            }
        }
    }

    public void unregisterListener(OnRestrictionStateChangedListener onRestrictionStateChangedListener) {
        synchronized (this.mRestrictionStateListeners) {
            this.mRestrictionStateListeners.remove(onRestrictionStateChangedListener);
            if (this.mRestrictionStateListeners.isEmpty() && checkServiceState()) {
                try {
                    this.mRestrictionService.unregisterListener(this.mListenerToService);
                } catch (RemoteException e) {
                    Log.d(TAG, " unregisterListener E:" + e);
                }
            }
        }
    }

    public void showToast(String str) {
        if (checkServiceState()) {
            try {
                this.mRestrictionService.showToast(str);
                return;
            } catch (RemoteException e) {
                Log.e(TAG, "showToast: ", e);
                return;
            }
        }
        Log.i(TAG, "showToast: service not running");
    }

    public void showToastByDisplayId(String str, int i) {
        if (checkServiceState()) {
            try {
                this.mRestrictionService.showToastByDisplayId(str, i);
                return;
            } catch (RemoteException e) {
                Log.e(TAG, "showToastByDisplayId:", e);
                return;
            }
        }
        Log.i(TAG, "showToastByDisplayId: service not running");
    }

    private static class RestrictionsChangeListenerToService extends IOnRestrictionStateChangedListener.Stub {
        private final WeakReference<RestrictionManager> mRestrictionsManager;

        RestrictionsChangeListenerToService(RestrictionManager restrictionManager) {
            this.mRestrictionsManager = new WeakReference<>(restrictionManager);
        }

        @Override // com.flyme.auto.sdk.restriction.IOnRestrictionStateChangedListener
        public void onRestrictionStateChange(int i) {
            RestrictionManager restrictionManager = this.mRestrictionsManager.get();
            if (restrictionManager != null) {
                restrictionManager.handleRestrictionStateChanged(i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleRestrictionStateChanged(int i) {
        EventCallbackHandler eventCallbackHandler = this.mEventCallbackHandler;
        eventCallbackHandler.sendMessage(eventCallbackHandler.obtainMessage(0, Integer.valueOf(i)));
    }

    private static final class EventCallbackHandler extends Handler {
        private final WeakReference<RestrictionManager> mRestrictionsManager;

        EventCallbackHandler(RestrictionManager restrictionManager, Looper looper) {
            super(looper);
            this.mRestrictionsManager = new WeakReference<>(restrictionManager);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            RestrictionManager restrictionManager = this.mRestrictionsManager.get();
            if (restrictionManager != null) {
                restrictionManager.dispatchStateChangeToClient(((Integer) message.obj).intValue());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchStateChangeToClient(int i) {
        synchronized (this.mRestrictionStateListeners) {
            List<OnRestrictionStateChangedListener> list = this.mRestrictionStateListeners;
            if (list != null) {
                Iterator<OnRestrictionStateChangedListener> it = list.iterator();
                while (it.hasNext()) {
                    it.next().onRestrictionStateChange(i);
                }
            }
        }
    }
}
