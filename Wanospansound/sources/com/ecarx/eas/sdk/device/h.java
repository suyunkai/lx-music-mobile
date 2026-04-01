package com.ecarx.eas.sdk.device;

import android.content.ComponentName;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.ecarx.eas.framework.sdk.Singleton;
import com.ecarx.eas.framework.sdk.common.exception.EASFrameworkException;
import com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient;
import com.ecarx.eas.framework.sdk.common.internal.IApi;
import com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService;
import com.ecarx.eas.framework.sdk.common.internal.MsgAPI;
import com.ecarx.eas.framework.sdk.proto.MessageNano;
import com.ecarx.eas.sdk.device.a.b;
import com.ecarx.eas.sdk.device.api.IDayNightMode;
import com.ecarx.eas.sdk.device.api.IDeviceState;
import com.ecarx.eas.sdk.device.api.InternalDeviceAPI;
import com.ecarx.openapi.protobuf.ECARXCommon;
import com.ecarx.sdk.openapi.msg.EASFrameworkMessage;
import com.ecarx.sdk.openapi.msg.SupportServiceRetMessage;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
final class h extends IApi<IEASFrameworkService> implements InternalDeviceAPI {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Singleton<h> f84a = new Singleton<h>() { // from class: com.ecarx.eas.sdk.device.h.1
        @Override // com.ecarx.eas.framework.sdk.Singleton
        protected final /* synthetic */ h create() {
            return new h();
        }
    };

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private com.ecarx.eas.sdk.device.a.b f85b = new b.a() { // from class: com.ecarx.eas.sdk.device.h.2
        @Override // com.ecarx.eas.sdk.device.a.b
        public final int a() throws RemoteException {
            return a.a((IEASFrameworkService) h.this.mService, "getDayNightMode");
        }

        @Override // com.ecarx.eas.sdk.device.a.b
        public final int b() throws RemoteException {
            return a.a((IEASFrameworkService) h.this.mService, "getDayNight");
        }

        @Override // com.ecarx.eas.sdk.device.a.b
        public final boolean a(com.ecarx.eas.sdk.device.a.a aVar) throws RemoteException {
            if (aVar == null) {
                return false;
            }
            try {
                IEASFrameworkService iEASFrameworkService = (IEASFrameworkService) h.this.mService;
                if (iEASFrameworkService == null) {
                    return false;
                }
                ECARXCommon.VoidMsg voidMsg = new ECARXCommon.VoidMsg();
                voidMsg.value = "";
                EASFrameworkMessage eASFrameworkMessage = new EASFrameworkMessage("device", "daymode", "registerDayNightModeCallBack", MessageNano.toByteArray(voidMsg), new byte[0]);
                SupportServiceRetMessage supportServiceRetMessageSendMsgAndBinder = MsgAPI.sendMsgAndBinder(iEASFrameworkService, eASFrameworkMessage, aVar.asBinder());
                if (supportServiceRetMessageSendMsgAndBinder.mCode != 200) {
                    Log.e("DayNightAPI", String.format(">> 服务内部错误 method = %s, code=%d, msg=%s <<", eASFrameworkMessage.mMethod, Integer.valueOf(supportServiceRetMessageSendMsgAndBinder.mCode), supportServiceRetMessageSendMsgAndBinder.mMsg));
                    return false;
                }
                return MsgAPI.getBool(supportServiceRetMessageSendMsgAndBinder.mData);
            } catch (EASFrameworkException e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override // com.ecarx.eas.sdk.device.a.b
        public final boolean b(com.ecarx.eas.sdk.device.a.a aVar) throws RemoteException {
            if (aVar == null) {
                return false;
            }
            try {
                IEASFrameworkService iEASFrameworkService = (IEASFrameworkService) h.this.mService;
                if (iEASFrameworkService == null) {
                    return false;
                }
                ECARXCommon.VoidMsg voidMsg = new ECARXCommon.VoidMsg();
                voidMsg.value = "";
                EASFrameworkMessage eASFrameworkMessage = new EASFrameworkMessage("device", "daymode", "unregisterDayNightModeCallBack", MessageNano.toByteArray(voidMsg), new byte[0]);
                SupportServiceRetMessage supportServiceRetMessageSendMsgAndBinder = MsgAPI.sendMsgAndBinder(iEASFrameworkService, eASFrameworkMessage, aVar.asBinder());
                if (supportServiceRetMessageSendMsgAndBinder.mCode != 200) {
                    Log.e("DayNightAPI", String.format(">> 服务内部错误 method = %s, code=%d, msg=%s <<", eASFrameworkMessage.mMethod, Integer.valueOf(supportServiceRetMessageSendMsgAndBinder.mCode), supportServiceRetMessageSendMsgAndBinder.mMsg));
                    return false;
                }
                return MsgAPI.getBool(supportServiceRetMessageSendMsgAndBinder.mData);
            } catch (EASFrameworkException e) {
                e.printStackTrace();
                return false;
            }
        }
    };

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private b f86c;

    h() {
    }

    @Override // com.ecarx.eas.framework.sdk.common.internal.IApi
    public final /* synthetic */ void init(IInterface iInterface) {
        super.init((IEASFrameworkService) iInterface);
        b bVar = this.f86c;
        if (bVar != null) {
            bVar.a();
        }
    }

    public static InternalDeviceAPI a() {
        return f84a.get();
    }

    @Override // com.ecarx.eas.sdk.device.api.InternalDeviceAPI
    public final String getIHUID() {
        return e.a((IEASFrameworkService) this.mService, "getIHUID");
    }

    @Override // com.ecarx.eas.sdk.device.api.InternalDeviceAPI
    public final String getVIN() {
        return e.a((IEASFrameworkService) this.mService, "getVIN");
    }

    @Override // com.ecarx.eas.sdk.device.api.InternalDeviceAPI
    public final String getDVRID() {
        return e.a((IEASFrameworkService) this.mService, "getDVRID");
    }

    @Override // com.ecarx.eas.sdk.device.api.InternalDeviceAPI
    public final String getXDSN() {
        return e.a((IEASFrameworkService) this.mService, "getXDSN");
    }

    @Override // com.ecarx.eas.sdk.device.api.InternalDeviceAPI
    public final String getStringValue(String str) {
        return e.a((IEASFrameworkService) this.mService, "getStringValue", str);
    }

    @Override // com.ecarx.eas.sdk.device.api.InternalDeviceAPI
    public final int getIntValue(String str) {
        return e.c((IEASFrameworkService) this.mService, "getIntValue", str);
    }

    @Override // com.ecarx.eas.sdk.device.api.InternalDeviceAPI
    public final boolean getBooleanValue(String str) {
        return e.b((IEASFrameworkService) this.mService, "getBooleanValue", str);
    }

    @Override // com.ecarx.eas.sdk.device.api.InternalDeviceAPI
    public final long getLongValue(String str) {
        return e.d((IEASFrameworkService) this.mService, "getLongValue", str);
    }

    @Override // com.ecarx.eas.sdk.device.api.InternalDeviceAPI
    public final double getDoubleValue(String str) {
        return e.e((IEASFrameworkService) this.mService, "getDoubleValue", str);
    }

    @Override // com.ecarx.eas.sdk.device.api.InternalDeviceAPI
    public final String getICCID() {
        return e.a((IEASFrameworkService) this.mService, "getICCID");
    }

    @Override // com.ecarx.eas.sdk.device.api.InternalDeviceAPI
    public final String getProjectCode() {
        return e.a((IEASFrameworkService) this.mService, "getProjectCode");
    }

    @Override // com.ecarx.eas.sdk.device.api.IDeviceAPI
    public final IDayNightMode getDayNightMode() {
        if (this.f86c == null) {
            this.f86c = new b(this.f85b);
        }
        return this.f86c;
    }

    @Override // com.ecarx.eas.sdk.device.api.IDeviceAPI
    public final String getOpenIHUID() {
        return e.a((IEASFrameworkService) this.mService, "getOpenIHUID");
    }

    @Override // com.ecarx.eas.sdk.device.api.IDeviceAPI
    public final String getOpenVIN() {
        return e.a((IEASFrameworkService) this.mService, "getOpenVIN");
    }

    @Override // com.ecarx.eas.sdk.device.api.IDeviceAPI
    public final String getVehicleType() {
        return e.a((IEASFrameworkService) this.mService, "getVehicleType");
    }

    @Override // com.ecarx.eas.sdk.device.api.IDeviceAPI
    public final int getOperatorCode() {
        return e.b((IEASFrameworkService) this.mService, "getOperatorCode");
    }

    @Override // com.ecarx.eas.sdk.device.api.IDeviceAPI
    public final String getOperatorName() {
        return e.a((IEASFrameworkService) this.mService, "getOperatorName");
    }

    @Override // com.ecarx.eas.sdk.device.api.IDeviceAPI
    public final IDeviceState getDeviceState() {
        return g.a(EASFrameworkApiClient.getInstance().getAppContext());
    }

    @Override // com.ecarx.eas.sdk.device.api.InternalDeviceAPI, com.ecarx.eas.sdk.device.api.IDeviceAPI
    public final String getSupplierCode() {
        return e.a((IEASFrameworkService) this.mService, "getSupplierCode");
    }

    @Override // com.ecarx.eas.sdk.device.api.IDeviceAPI
    public final String getVehicleTypeConfig() {
        return e.a((IEASFrameworkService) this.mService, "getVehicleTypeConfig");
    }

    @Override // com.ecarx.eas.sdk.device.api.InternalDeviceAPI
    public final List<ComponentName> getSupportedComponents() {
        try {
            return e.a((IEASFrameworkService) this.mService);
        } catch (EASFrameworkException e) {
            e.printStackTrace();
            Log.e("DeviceAPI", String.format(">> 服务错误 method = %s, code=%d, msg=%s <<", "getSupportedComponents", Integer.valueOf(e.getCode()), e.getMsg()), e);
            return Collections.emptyList();
        } catch (Exception e2) {
            e2.printStackTrace();
            Log.e("DeviceAPI", String.format(">> 服务其他异常错误 method = %s, msg=%s <<", "getSupportedComponents", e2.getMessage()), e2);
            return Collections.emptyList();
        }
    }
}
