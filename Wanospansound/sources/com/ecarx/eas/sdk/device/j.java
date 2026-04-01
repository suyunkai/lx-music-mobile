package com.ecarx.eas.sdk.device;

import android.os.RemoteException;
import android.util.Log;
import com.ecarx.eas.framework.sdk.Singleton;
import com.ecarx.eas.framework.sdk.common.internal.IApi;
import com.ecarx.eas.sdk.device.api.IDrivingJoyLimit;
import com.ecarx.eas.sdk.device.api.JoyLimitListener;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
final class j extends IApi<com.ecarx.eas.sdk.device.a.a.b> implements IDrivingJoyLimit {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Singleton<IDrivingJoyLimit> f90b = new Singleton<IDrivingJoyLimit>() { // from class: com.ecarx.eas.sdk.device.j.1
        @Override // com.ecarx.eas.framework.sdk.Singleton
        protected final /* synthetic */ IDrivingJoyLimit create() {
            return new j();
        }
    };

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Map<Object, JoyLimitListener> f91a = new HashMap();

    j() {
    }

    protected static IDrivingJoyLimit a() {
        return f90b.get();
    }

    @Override // com.ecarx.eas.sdk.device.api.IDrivingJoyLimit
    public final int getState(int i) {
        if (!this.mAliveFlag.get()) {
            Log.e("FunPolicyAPI", String.format(">> getState(%d) and mFunPolicy is NULL!!! <<", Integer.valueOf(i)));
            return -1;
        }
        try {
            return ((com.ecarx.eas.sdk.device.a.a.b) this.mService).a(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e("FunPolicyAPI", String.format(">> getState(%d) RemoteException: %s <<", Integer.valueOf(i), e.getMessage()), e);
            return -1;
        }
    }

    @Override // com.ecarx.eas.sdk.device.api.IDrivingJoyLimit
    public final Object registerListener(int i, JoyLimitListener joyLimitListener) {
        com.ecarx.eas.sdk.device.a.a.c cVarA;
        if (this.mAliveFlag.get() && joyLimitListener != null) {
            if (this.f91a.containsValue(joyLimitListener)) {
                Log.e("FunPolicyAPI", "registerListener: duplicate register mJoyLimitListeners.contains(listener)");
                return null;
            }
            try {
                cVarA = ((com.ecarx.eas.sdk.device.a.a.b) this.mService).a(i, new m(joyLimitListener));
            } catch (RemoteException e) {
                e.printStackTrace();
                Log.e("FunPolicyAPI", "registerListener: " + e.getMessage(), e);
                cVarA = null;
            }
            if (cVarA != null) {
                this.f91a.put(cVarA, joyLimitListener);
            } else {
                Log.e("FunPolicyAPI", "registerListener fail!!!");
            }
        }
        return null;
    }

    @Override // com.ecarx.eas.sdk.device.api.IDrivingJoyLimit
    public final void unRegisterListener(Object obj) {
        if (!this.mAliveFlag.get() || !this.f91a.containsKey(obj)) {
            Log.e("FunPolicyAPI", "unRegisterListener: mJoyLimitListenerMap.containsKey(token) false!");
            return;
        }
        try {
            com.ecarx.eas.sdk.device.a.a.b bVar = (com.ecarx.eas.sdk.device.a.a.b) this.mService;
            if (obj == null) {
                throw new IllegalArgumentException("token is null!");
            }
            if (obj instanceof com.ecarx.eas.sdk.device.a.a.c) {
                bVar.a((com.ecarx.eas.sdk.device.a.a.c) obj);
                this.f91a.remove(obj);
                return;
            }
            throw new IllegalArgumentException("token is invalid !");
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e("FunPolicyAPI", "unRegisterListener: " + e.getMessage(), e);
        }
    }
}
