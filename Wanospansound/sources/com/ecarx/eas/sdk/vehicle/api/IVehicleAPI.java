package com.ecarx.eas.sdk.vehicle.api;

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

/* JADX INFO: loaded from: classes2.dex */
public interface IVehicleAPI {
    IAudio getAudio();

    ICarInfo getCarInfo();

    int getCarKeyStatus();

    float getCarSpeed();

    int getCurrentDriveMode();

    IDashboard getDashboard();

    IDisplay getDisplay();

    float getEnduranceMileage();

    float getEvBatteryLevel();

    float getEvBatteryPercentage();

    int getEvBatteryState();

    int getGear();

    float getLastFuelLevel();

    INewEnergy getNewEnergy();

    int getSafeBeltStateByType(int i);

    IVehicleManager getVehicleManager();

    FunctionStatus isCarKeyStatusSupported();

    FunctionStatus isCarSpeedSupported();

    FunctionStatus isDriveModeSupported();

    FunctionStatus isEnduranceMileageSupported();

    FunctionStatus isEvBatteryLevelSupported();

    FunctionStatus isEvBatteryPercentageSupported();

    FunctionStatus isEvBatteryStateSupported();

    FunctionStatus isEvBatteryWarnSupported();

    FunctionStatus isFuelLevelSupported();

    FunctionStatus isFuelWarnSupported();

    FunctionStatus isGearSupported();

    FunctionStatus isSafeBeltStatusSupported(int i);

    boolean registerCarKeyStatusListener(ICarKeyStatusListener iCarKeyStatusListener);

    boolean registerCarSpeedListener(ICarSpeedListener iCarSpeedListener);

    boolean registerEvBatteryWarnListener(IWarningStatusListener iWarningStatusListener);

    boolean registerFuelWarnListener(IWarningStatusListener iWarningStatusListener);

    boolean registerGearListener(IGearListener iGearListener);

    boolean registerSafeBeltListenerByType(int i, ISafeBeltStateListener iSafeBeltStateListener);

    boolean unRegisterCarKeyStatusListener(ICarKeyStatusListener iCarKeyStatusListener);

    boolean unRegisterCarSpeedListener(ICarSpeedListener iCarSpeedListener);

    boolean unRegisterEvBatteryWarnListener(IWarningStatusListener iWarningStatusListener);

    boolean unRegisterFuelWarnListener(IWarningStatusListener iWarningStatusListener);

    boolean unRegisterGearListener(IGearListener iGearListener);

    boolean unRegisterSafeBeltListener(int i, ISafeBeltStateListener iSafeBeltStateListener);
}
