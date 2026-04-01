package com.ecarx.eas.sdk.vehicle.f;

import android.text.TextUtils;
import com.ecarx.eas.framework.sdk.EASCallUtils;
import com.ecarx.eas.framework.sdk.common.internal.ClientType;
import com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService;
import com.ecarx.eas.framework.sdk.proto.MessageNano;
import com.ecarx.eas.sdk.IServiceManager;
import com.ecarx.eas.sdk.vehicle.FunctionStatus;
import com.ecarx.eas.sdk.vehicle.api.IVehicleAPI;
import com.ecarx.eas.sdk.vehicle.api.audio.IAudio;
import com.ecarx.eas.sdk.vehicle.api.carinfo.ICarInfo;
import com.ecarx.eas.sdk.vehicle.api.dashboard.IDashboard;
import com.ecarx.eas.sdk.vehicle.api.display.IDisplay;
import com.ecarx.eas.sdk.vehicle.api.interfaces.IVehicleManager;
import com.ecarx.eas.sdk.vehicle.api.newenergy.INewEnergy;
import com.ecarx.eas.sdk.vehicle.api.sensor.IGearListener;
import com.ecarx.eas.sdk.vehicle.api.sensor.ISensor;
import com.ecarx.eas.sdk.vehicle.api.sensor.ISensorListenerPer;
import com.ecarx.eas.sdk.vehicle.api.sensor.callback.ICarKeyStatusListener;
import com.ecarx.eas.sdk.vehicle.api.sensor.callback.ICarSpeedListener;
import com.ecarx.eas.sdk.vehicle.api.sensor.callback.ISafeBeltStateListener;
import com.ecarx.eas.sdk.vehicle.api.sensor.callback.IWarningStatusListener;
import com.ecarx.openapi.protobuf.ECARXCommon;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes2.dex */
public abstract class x extends k<IEASFrameworkService> implements IVehicleAPI {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    s f257a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    w f258b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    r f259c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    t f260d;
    u e;
    q f;
    private ConcurrentHashMap<ISensorListenerPer, com.ecarx.eas.sdk.vehicle.i.b> g;
    private IVehicleManager h;

    public x() {
        getClass().getSimpleName();
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public IDashboard getDashboard() {
        return this.f257a;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public INewEnergy getNewEnergy() {
        return this.f258b;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public ICarInfo getCarInfo() {
        return this.f259c;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public IDisplay getDisplay() {
        return this.f260d;
    }

    private FunctionStatus a(String str, int i) {
        if (this.mService == 0) {
            return FunctionStatus.error;
        }
        ECARXCommon.IntMsg intMsg = new ECARXCommon.IntMsg();
        intMsg.value = i;
        return FunctionStatus.getFunctionStatus(EASCallUtils.callInt((IEASFrameworkService) this.mService, IServiceManager.SERVICE_VEHICLE, "Sensor", str, MessageNano.toByteArray(intMsg), null));
    }

    private int b(String str, int i) {
        if (this.mService == 0) {
            return Integer.MIN_VALUE;
        }
        ECARXCommon.IntMsg intMsg = new ECARXCommon.IntMsg();
        intMsg.value = i;
        return EASCallUtils.callInt((IEASFrameworkService) this.mService, IServiceManager.SERVICE_VEHICLE, "Sensor", str, MessageNano.toByteArray(intMsg), null);
    }

    private float a(String str) {
        if (this.mService == 0) {
            return -2.1474836E9f;
        }
        return EASCallUtils.callFloat((IEASFrameworkService) this.mService, IServiceManager.SERVICE_VEHICLE, "Sensor", str, null, null);
    }

    private boolean a(ISensorListenerPer iSensorListenerPer, String str, int i) {
        if (TextUtils.isEmpty(str) || this.mService == 0 || iSensorListenerPer == null) {
            return false;
        }
        if (this.g == null) {
            this.g = new ConcurrentHashMap<>();
        }
        if (this.g.get(iSensorListenerPer) != null) {
            return true;
        }
        com.ecarx.eas.sdk.vehicle.i.b bVar = new com.ecarx.eas.sdk.vehicle.i.b(new com.ecarx.eas.sdk.vehicle.i.c(iSensorListenerPer, i), i);
        ECARXCommon.IntMsg intMsg = new ECARXCommon.IntMsg();
        intMsg.value = i;
        boolean zCallBoolean = EASCallUtils.callBoolean((IEASFrameworkService) this.mService, IServiceManager.SERVICE_VEHICLE, "Sensor", str, MessageNano.toByteArray(intMsg), bVar);
        if (zCallBoolean) {
            this.g.put(iSensorListenerPer, bVar);
        }
        return zCallBoolean;
    }

    private boolean a(ISensorListenerPer iSensorListenerPer, String str) {
        com.ecarx.eas.sdk.vehicle.i.b bVar;
        if (iSensorListenerPer == null || this.g == null || this.mService == 0 || (bVar = this.g.get(iSensorListenerPer)) == null) {
            return false;
        }
        EASCallUtils.call((IEASFrameworkService) this.mService, IServiceManager.SERVICE_VEHICLE, "Sensor", str, null, bVar);
        bVar.a();
        this.g.remove(iSensorListenerPer);
        return true;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public boolean registerCarSpeedListener(ICarSpeedListener iCarSpeedListener) {
        return a(iCarSpeedListener, "registerCarSpeedListener", 1048832);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public boolean unRegisterCarSpeedListener(ICarSpeedListener iCarSpeedListener) {
        return a(iCarSpeedListener, "unRegisterCarSpeedListener");
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public float getCarSpeed() {
        return a("getCarSpeed");
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public boolean registerCarKeyStatusListener(ICarKeyStatusListener iCarKeyStatusListener) {
        return a(iCarKeyStatusListener, "registerCarKeyStatusListener", 2097408);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public boolean unRegisterCarKeyStatusListener(ICarKeyStatusListener iCarKeyStatusListener) {
        return a(iCarKeyStatusListener, "unRegisterCarKeyStatusListener");
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public boolean registerGearListener(IGearListener iGearListener) {
        return a(iGearListener, "registerGearListener", 2097664);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public boolean unRegisterGearListener(IGearListener iGearListener) {
        return a(iGearListener, "unRegisterGearListener");
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public float getEvBatteryPercentage() {
        return a("getEvBatteryPercentage");
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public float getEnduranceMileage() {
        return a("getEnduranceMileage");
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public boolean registerFuelWarnListener(IWarningStatusListener iWarningStatusListener) {
        return a(iWarningStatusListener, "registerFuelWarnListener", ISensor.SENSOR_TYPE_WARN_FUEL_RED);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public boolean unRegisterFuelWarnListener(IWarningStatusListener iWarningStatusListener) {
        return a(iWarningStatusListener, "unRegisterFuelWarnListener");
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public boolean registerEvBatteryWarnListener(IWarningStatusListener iWarningStatusListener) {
        return a(iWarningStatusListener, "registerEvBatteryWarnListener", 3146240);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public boolean unRegisterEvBatteryWarnListener(IWarningStatusListener iWarningStatusListener) {
        return a(iWarningStatusListener, "unRegisterEvBatteryWarnListener");
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public float getEvBatteryLevel() {
        return a("getEvBatteryLevel");
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public FunctionStatus isSafeBeltStatusSupported(int i) {
        return a("isSafeBeltStatusSupported", i);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public int getSafeBeltStateByType(int i) {
        return b("getSafeBeltStateByType", i);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public boolean registerSafeBeltListenerByType(int i, ISafeBeltStateListener iSafeBeltStateListener) {
        return a(iSafeBeltStateListener, "registerSafeBeltListenerByType", i);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public boolean unRegisterSafeBeltListener(int i, ISafeBeltStateListener iSafeBeltStateListener) {
        return a(iSafeBeltStateListener, "unRegisterSafeBeltListener");
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public FunctionStatus isDriveModeSupported() {
        u uVar = this.e;
        if (uVar == null) {
            return FunctionStatus.error;
        }
        return uVar.a();
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public int getCurrentDriveMode() {
        u uVar = this.e;
        if (uVar == null) {
            return Integer.MIN_VALUE;
        }
        return uVar.b();
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public float getLastFuelLevel() {
        return a("getLastFuelLevel");
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public IAudio getAudio() {
        return this.f;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public IVehicleManager getVehicleManager() {
        if (!a(ClientType.EASFramework, 7)) {
            return null;
        }
        IVehicleManager iVehicleManager = this.h;
        if (iVehicleManager != null) {
            return iVehicleManager;
        }
        h hVar = new h(this, IServiceManager.SERVICE_VEHICLE, "Sensor");
        this.h = hVar;
        return hVar;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public FunctionStatus isCarSpeedSupported() {
        return a("isCarSpeedSupported", -1);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public FunctionStatus isCarKeyStatusSupported() {
        return a("isCarKeyStatusSupported", -1);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public int getCarKeyStatus() {
        return b("getCarKeyStatus", -1);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public FunctionStatus isGearSupported() {
        return a("isGearSupported", -1);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public int getGear() {
        return b("getGear", -1);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public FunctionStatus isEvBatteryPercentageSupported() {
        return a("isEvBatteryPercentageSupported", -1);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public FunctionStatus isEvBatteryStateSupported() {
        return a("isEvBatteryStateSupported", -1);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public int getEvBatteryState() {
        return b("getEvBatteryState", -1);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public FunctionStatus isEnduranceMileageSupported() {
        return a("isEnduranceMileageSupported", -1);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public FunctionStatus isFuelWarnSupported() {
        return a("isFuelWarnSupported", -1);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public FunctionStatus isEvBatteryWarnSupported() {
        return a("isEvBatteryWarnSupported", -1);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public FunctionStatus isEvBatteryLevelSupported() {
        return a("isEvBatteryLevelSupported", -1);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public FunctionStatus isFuelLevelSupported() {
        return a("isFuelLevelSupported", -1);
    }
}
