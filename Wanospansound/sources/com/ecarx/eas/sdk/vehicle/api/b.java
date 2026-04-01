package com.ecarx.eas.sdk.vehicle.api;

import android.content.Context;
import android.util.Log;
import com.ecarx.eas.framework.sdk.ECarXAPIBase;
import com.ecarx.eas.framework.sdk.common.exception.EASFrameworkException;
import com.ecarx.eas.framework.sdk.common.internal.ClientType;
import com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient;
import com.ecarx.eas.framework.sdk.common.internal.safeparcel.ServiceVersionInfo;
import com.ecarx.eas.sdk.ECarXApiClient;
import com.ecarx.eas.sdk.IServiceManager;
import com.ecarx.eas.sdk.vehicle.FunctionStatus;
import com.ecarx.eas.sdk.vehicle.api.audio.IAudio;
import com.ecarx.eas.sdk.vehicle.api.carinfo.ICarInfo;
import com.ecarx.eas.sdk.vehicle.api.dashboard.IDashboard;
import com.ecarx.eas.sdk.vehicle.api.display.IDisplay;
import com.ecarx.eas.sdk.vehicle.api.interfaces.IVehicleManager;
import com.ecarx.eas.sdk.vehicle.api.newenergy.INewEnergy;
import com.ecarx.eas.sdk.vehicle.api.sensor.IGearListener;
import com.ecarx.eas.sdk.vehicle.api.sensor.callback.ICarKeyStatusListener;
import com.ecarx.eas.sdk.vehicle.api.sensor.callback.ICarSpeedListener;
import com.ecarx.eas.sdk.vehicle.api.sensor.callback.ISafeBeltStateListener;
import com.ecarx.eas.sdk.vehicle.api.sensor.callback.IWarningStatusListener;
import com.ecarx.eas.sdk.vehicle.f.x;

/* JADX INFO: loaded from: classes2.dex */
public abstract class b extends ECarXAPIBase implements IVehicleAPI {
    private final String TAG = getClass().getSimpleName();
    private IVehicleAPI mApi;
    private int version;

    abstract IVehicleAPI initVehicle(ClientType clientType);

    @Override // com.ecarx.eas.framework.sdk.ECarXAPIBase
    public void init(Context context, ECarXApiClient.Callback callback) {
        super.init(context, callback);
        try {
            EASFrameworkApiClient.getInstance().init(context, callback, IServiceManager.SERVICE_VEHICLE, new EASFrameworkApiClient.IServiceConnectionCallback() { // from class: com.ecarx.eas.sdk.vehicle.api.b.1
                @Override // com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient.IServiceConnectionCallback
                public final boolean onConnected(String str, ClientType clientType, boolean z) {
                    b.this.version = 0;
                    if (z) {
                        b bVar = b.this;
                        bVar.mApi = bVar.initVehicle(clientType);
                    }
                    return b.this.mApi != null;
                }

                @Override // com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient.IServiceConnectionCallback
                public final boolean onConnected(String str, ClientType clientType, ServiceVersionInfo serviceVersionInfo, boolean z) {
                    b.this.version = 0;
                    if (z) {
                        b bVar = b.this;
                        bVar.mApi = bVar.initVehicle(clientType);
                        if (serviceVersionInfo != null && serviceVersionInfo.versionInfos != null && serviceVersionInfo.versionInfos.size() > 0) {
                            b.this.version = serviceVersionInfo.versionInfos.get(0).version;
                        }
                        if (b.this.mApi != null && (b.this.mApi instanceof a)) {
                            ((a) b.this.mApi).a(clientType, serviceVersionInfo);
                        }
                    }
                    return b.this.mApi != null;
                }
            });
        } catch (EASFrameworkException e) {
            e.printStackTrace();
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public IDashboard getDashboard() {
        IVehicleAPI iVehicleAPI = this.mApi;
        if (iVehicleAPI == null) {
            return null;
        }
        return iVehicleAPI.getDashboard();
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public INewEnergy getNewEnergy() {
        IVehicleAPI iVehicleAPI = this.mApi;
        if (iVehicleAPI == null) {
            return null;
        }
        return iVehicleAPI.getNewEnergy();
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public ICarInfo getCarInfo() {
        IVehicleAPI iVehicleAPI = this.mApi;
        if (iVehicleAPI == null) {
            return null;
        }
        return iVehicleAPI.getCarInfo();
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public IDisplay getDisplay() {
        IVehicleAPI iVehicleAPI = this.mApi;
        if (iVehicleAPI == null) {
            return null;
        }
        return iVehicleAPI.getDisplay();
    }

    private boolean serviceNotSupport() {
        if (!(this.mApi instanceof x) || this.version >= 4) {
            return false;
        }
        Log.i(this.TAG, "service version is less than 4");
        return true;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public FunctionStatus isCarSpeedSupported() {
        if (this.mApi == null) {
            return FunctionStatus.error;
        }
        if (serviceNotSupport()) {
            return FunctionStatus.error;
        }
        return this.mApi.isCarSpeedSupported();
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public boolean registerCarSpeedListener(ICarSpeedListener iCarSpeedListener) {
        if (this.mApi == null || serviceNotSupport()) {
            return false;
        }
        return this.mApi.registerCarSpeedListener(iCarSpeedListener);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public boolean unRegisterCarSpeedListener(ICarSpeedListener iCarSpeedListener) {
        if (this.mApi == null || serviceNotSupport()) {
            return false;
        }
        return this.mApi.unRegisterCarSpeedListener(iCarSpeedListener);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public float getCarSpeed() {
        if (this.mApi == null || serviceNotSupport()) {
            return -2.1474836E9f;
        }
        return this.mApi.getCarSpeed();
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public FunctionStatus isCarKeyStatusSupported() {
        if (this.mApi == null) {
            return FunctionStatus.error;
        }
        if (serviceNotSupport()) {
            return FunctionStatus.error;
        }
        return this.mApi.isCarKeyStatusSupported();
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public int getCarKeyStatus() {
        if (this.mApi == null || serviceNotSupport()) {
            return Integer.MIN_VALUE;
        }
        return this.mApi.getCarKeyStatus();
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public boolean registerCarKeyStatusListener(ICarKeyStatusListener iCarKeyStatusListener) {
        if (this.mApi == null || serviceNotSupport()) {
            return false;
        }
        return this.mApi.registerCarKeyStatusListener(iCarKeyStatusListener);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public boolean unRegisterCarKeyStatusListener(ICarKeyStatusListener iCarKeyStatusListener) {
        if (this.mApi == null || serviceNotSupport()) {
            return false;
        }
        return this.mApi.unRegisterCarKeyStatusListener(iCarKeyStatusListener);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public FunctionStatus isGearSupported() {
        if (this.mApi == null) {
            return FunctionStatus.error;
        }
        if (serviceNotSupport()) {
            return FunctionStatus.error;
        }
        return this.mApi.isGearSupported();
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public int getGear() {
        if (this.mApi == null || serviceNotSupport()) {
            return Integer.MIN_VALUE;
        }
        return this.mApi.getGear();
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public boolean registerGearListener(IGearListener iGearListener) {
        if (this.mApi == null || serviceNotSupport()) {
            return false;
        }
        return this.mApi.registerGearListener(iGearListener);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public boolean unRegisterGearListener(IGearListener iGearListener) {
        if (this.mApi == null || serviceNotSupport()) {
            return false;
        }
        return this.mApi.unRegisterGearListener(iGearListener);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public FunctionStatus isEvBatteryPercentageSupported() {
        if (this.mApi == null) {
            return FunctionStatus.error;
        }
        if (serviceNotSupport()) {
            return FunctionStatus.error;
        }
        return this.mApi.isEvBatteryPercentageSupported();
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public float getEvBatteryPercentage() {
        if (this.mApi == null || serviceNotSupport()) {
            return -2.1474836E9f;
        }
        return this.mApi.getEvBatteryPercentage();
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public FunctionStatus isEvBatteryStateSupported() {
        if (this.mApi == null) {
            return FunctionStatus.error;
        }
        if (serviceNotSupport()) {
            return FunctionStatus.error;
        }
        return this.mApi.isEvBatteryStateSupported();
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public int getEvBatteryState() {
        if (this.mApi == null || serviceNotSupport()) {
            return Integer.MIN_VALUE;
        }
        return this.mApi.getEvBatteryState();
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public FunctionStatus isEnduranceMileageSupported() {
        if (this.mApi == null) {
            return FunctionStatus.error;
        }
        if (serviceNotSupport()) {
            return FunctionStatus.error;
        }
        return this.mApi.isEnduranceMileageSupported();
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public float getEnduranceMileage() {
        if (this.mApi == null || serviceNotSupport()) {
            return -1.0f;
        }
        return this.mApi.getEnduranceMileage();
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public FunctionStatus isFuelWarnSupported() {
        if (this.mApi == null) {
            return FunctionStatus.error;
        }
        if (serviceNotSupport()) {
            return FunctionStatus.error;
        }
        return this.mApi.isFuelWarnSupported();
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public boolean registerFuelWarnListener(IWarningStatusListener iWarningStatusListener) {
        if (this.mApi == null || serviceNotSupport()) {
            return false;
        }
        return this.mApi.registerFuelWarnListener(iWarningStatusListener);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public boolean unRegisterFuelWarnListener(IWarningStatusListener iWarningStatusListener) {
        if (this.mApi == null || serviceNotSupport()) {
            return false;
        }
        return this.mApi.unRegisterFuelWarnListener(iWarningStatusListener);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public FunctionStatus isEvBatteryWarnSupported() {
        if (this.mApi == null) {
            return FunctionStatus.error;
        }
        if (serviceNotSupport()) {
            return FunctionStatus.error;
        }
        return this.mApi.isEvBatteryWarnSupported();
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public boolean registerEvBatteryWarnListener(IWarningStatusListener iWarningStatusListener) {
        if (this.mApi == null || serviceNotSupport()) {
            return false;
        }
        return this.mApi.registerEvBatteryWarnListener(iWarningStatusListener);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public boolean unRegisterEvBatteryWarnListener(IWarningStatusListener iWarningStatusListener) {
        if (this.mApi == null || serviceNotSupport()) {
            return false;
        }
        return this.mApi.unRegisterEvBatteryWarnListener(iWarningStatusListener);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public FunctionStatus isEvBatteryLevelSupported() {
        if (this.mApi == null) {
            return FunctionStatus.error;
        }
        if (serviceNotSupport()) {
            return FunctionStatus.error;
        }
        return this.mApi.isEvBatteryLevelSupported();
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public float getEvBatteryLevel() {
        if (this.mApi == null || serviceNotSupport()) {
            return -2.1474836E9f;
        }
        return this.mApi.getEvBatteryLevel();
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public FunctionStatus isSafeBeltStatusSupported(int i) {
        if (this.mApi == null) {
            return FunctionStatus.error;
        }
        if (serviceNotSupport()) {
            return FunctionStatus.error;
        }
        return this.mApi.isSafeBeltStatusSupported(i);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public int getSafeBeltStateByType(int i) {
        if (this.mApi == null || serviceNotSupport()) {
            return Integer.MIN_VALUE;
        }
        return this.mApi.getSafeBeltStateByType(i);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public boolean registerSafeBeltListenerByType(int i, ISafeBeltStateListener iSafeBeltStateListener) {
        if (this.mApi == null || serviceNotSupport()) {
            return false;
        }
        return this.mApi.registerSafeBeltListenerByType(i, iSafeBeltStateListener);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public boolean unRegisterSafeBeltListener(int i, ISafeBeltStateListener iSafeBeltStateListener) {
        if (this.mApi == null || serviceNotSupport()) {
            return false;
        }
        return this.mApi.unRegisterSafeBeltListener(i, iSafeBeltStateListener);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public FunctionStatus isDriveModeSupported() {
        if (this.mApi == null) {
            return FunctionStatus.error;
        }
        if (serviceNotSupport()) {
            return FunctionStatus.error;
        }
        return this.mApi.isDriveModeSupported();
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public int getCurrentDriveMode() {
        if (this.mApi == null || serviceNotSupport()) {
            return Integer.MIN_VALUE;
        }
        return this.mApi.getCurrentDriveMode();
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public FunctionStatus isFuelLevelSupported() {
        if (this.mApi == null) {
            return FunctionStatus.error;
        }
        if (serviceNotSupport()) {
            return FunctionStatus.error;
        }
        return this.mApi.isFuelLevelSupported();
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public float getLastFuelLevel() {
        if (this.mApi == null || serviceNotSupport()) {
            return -2.1474836E9f;
        }
        return this.mApi.getLastFuelLevel();
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public IAudio getAudio() {
        if (this.mApi == null || serviceNotSupport()) {
            return null;
        }
        return this.mApi.getAudio();
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public IVehicleManager getVehicleManager() {
        IVehicleAPI iVehicleAPI = this.mApi;
        if (iVehicleAPI == null) {
            return null;
        }
        return iVehicleAPI.getVehicleManager();
    }
}
