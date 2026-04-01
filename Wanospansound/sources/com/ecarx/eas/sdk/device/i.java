package com.ecarx.eas.sdk.device;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.ecarx.eas.framework.sdk.Singleton;
import com.ecarx.eas.framework.sdk.common.exception.EASFrameworkException;
import com.ecarx.eas.framework.sdk.common.internal.IApi;
import com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService;
import com.ecarx.eas.framework.sdk.common.internal.MsgAPI;
import com.ecarx.eas.framework.sdk.proto.MessageNano;
import com.ecarx.eas.sdk.IServiceManager;
import com.ecarx.eas.sdk.device.api.IDrivingJoyLimit;
import com.ecarx.eas.sdk.device.api.JoyLimitListener;
import com.ecarx.openapi.protobuf.ECARXCommon;
import com.ecarx.sdk.openapi.msg.EASFrameworkMessage;
import com.ecarx.sdk.openapi.msg.SupportServiceRetMessage;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
final class i extends IApi<IEASFrameworkService> implements IDrivingJoyLimit {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Singleton<IDrivingJoyLimit> f88b = new Singleton<IDrivingJoyLimit>() { // from class: com.ecarx.eas.sdk.device.i.1
        @Override // com.ecarx.eas.framework.sdk.Singleton
        protected final /* synthetic */ IDrivingJoyLimit create() {
            return new i();
        }
    };

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Map<Object, JoyLimitListener> f89a = new HashMap();

    i() {
    }

    protected static IDrivingJoyLimit a() {
        return f88b.get();
    }

    @Override // com.ecarx.eas.sdk.device.api.IDrivingJoyLimit
    public final int getState(int i) {
        if (isAlive()) {
            return l.a((IEASFrameworkService) this.mService, i);
        }
        return -1;
    }

    @Override // com.ecarx.eas.sdk.device.api.IDrivingJoyLimit
    public final Object registerListener(int i, JoyLimitListener joyLimitListener) {
        if (!this.mAliveFlag.get() || joyLimitListener == null) {
            return null;
        }
        if (this.f89a.containsValue(joyLimitListener)) {
            Log.e("EasFunPolicyAPI", "registerListener: duplicate register mJoyLimitListeners.contains(listener)");
            return null;
        }
        Object objA = l.a((IEASFrameworkService) this.mService, i, new m(joyLimitListener));
        if (objA != null) {
            this.f89a.put(objA, joyLimitListener);
        } else {
            Log.e("EasFunPolicyAPI", "registerListener fail!!!");
        }
        return objA;
    }

    @Override // com.ecarx.eas.sdk.device.api.IDrivingJoyLimit
    public final void unRegisterListener(Object obj) {
        if (obj == null) {
            Log.e("EasFunPolicyAPI", "unRegisterListener fail: token is NULL!");
            return;
        }
        if (!this.mAliveFlag.get() || !this.f89a.containsKey(obj)) {
            Log.e("EasFunPolicyAPI", "unRegisterListener: mJoyLimitListenerMap.containsKey(token) false!");
            return;
        }
        if (!(obj instanceof IBinder)) {
            Log.e("EasFunPolicyAPI", "unRegisterListener fail: token is Not Binder!");
            return;
        }
        try {
            if (!"ecarx.xsf.gkuiservice.policy.IFunPolicyClientToken".equals(((IBinder) obj).getInterfaceDescriptor())) {
                Log.e("EasFunPolicyAPI", "unRegisterListener fail: token is Not ecarx.xsf.gkuiservice.policy.IFunPolicyClientToken!");
                return;
            }
            IEASFrameworkService iEASFrameworkService = (IEASFrameworkService) this.mService;
            if (iEASFrameworkService != null) {
                ECARXCommon.VoidMsg voidMsg = new ECARXCommon.VoidMsg();
                voidMsg.value = "";
                EASFrameworkMessage eASFrameworkMessage = new EASFrameworkMessage(IServiceManager.SERVICE_DRIVE_POLICY, "funpolicy", "unRegisterListener", MessageNano.toByteArray(voidMsg), new byte[0]);
                try {
                    SupportServiceRetMessage supportServiceRetMessageSendMsgAndBinder = MsgAPI.sendMsgAndBinder(iEASFrameworkService, eASFrameworkMessage, (IBinder) obj);
                    if (supportServiceRetMessageSendMsgAndBinder.mCode != 200) {
                        Log.e("DrivePolicyAPI", String.format(">> method = %s, code=%d, msg=%s <<", eASFrameworkMessage.mMethod, Integer.valueOf(supportServiceRetMessageSendMsgAndBinder.mCode), supportServiceRetMessageSendMsgAndBinder.mMsg));
                    }
                } catch (EASFrameworkException e) {
                    e.printStackTrace();
                    Log.e("DrivePolicyAPI", e.getMessage(), e);
                }
            }
            this.f89a.remove(obj);
        } catch (RemoteException e2) {
            e2.printStackTrace();
            Log.e("EasFunPolicyAPI", "Token is Died!!!");
        }
    }
}
