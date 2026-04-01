package com.ecarx.eas.sdk.vehicle.f;

import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient;
import com.ecarx.eas.sdk.log.LogProxy;
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
import com.ecarx.eas.sdk.vehicle.api.sensor.IVirtualSensor;
import com.ecarx.eas.sdk.vehicle.api.sensor.callback.ICarKeyStatusListener;
import com.ecarx.eas.sdk.vehicle.api.sensor.callback.ICarSpeedListener;
import com.ecarx.eas.sdk.vehicle.api.sensor.callback.ISafeBeltStateListener;
import com.ecarx.eas.sdk.vehicle.api.sensor.callback.IWarningStatusListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes2.dex */
public abstract class A extends k<com.ecarx.eas.sdk.vehicle.v3.e> implements IVehicleAPI {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    volatile n f205a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    volatile y f206b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    volatile m f207c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    volatile o f208d;
    volatile p e;
    private volatile l f;
    private ConcurrentHashMap<ISensorListenerPer, com.ecarx.eas.sdk.vehicle.i.b> g;
    private SparseArray<Map<ISensorListenerPer, com.ecarx.eas.sdk.vehicle.i.b>> h;

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public ICarInfo getCarInfo() {
        return null;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public IVehicleManager getVehicleManager() {
        return null;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public IDashboard getDashboard() {
        if (this.f205a == null) {
            synchronized (this) {
                if (this.f205a == null) {
                    try {
                        this.f205a = new com.ecarx.eas.sdk.vehicle.c.a(((com.ecarx.eas.sdk.vehicle.v3.e) this.mService).a());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return this.f205a;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public INewEnergy getNewEnergy() {
        if (this.f206b == null) {
            synchronized (this) {
                if (this.f206b == null) {
                    try {
                        this.f206b = new com.ecarx.eas.sdk.vehicle.g.i(((com.ecarx.eas.sdk.vehicle.v3.e) this.mService).b());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return this.f206b;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public IDisplay getDisplay() {
        if (this.f208d != null) {
            return this.f208d;
        }
        synchronized (this) {
            if (this.f208d != null) {
                return this.f208d;
            }
            com.ecarx.eas.sdk.vehicle.d.a aVar = null;
            if (EASFrameworkApiClient.getInstance().getVersion() <= 0) {
                Log.e(LogProxy.TAG, getClass().getSimpleName() + ">> 服务版本小于等于0");
                return null;
            }
            try {
                com.ecarx.eas.sdk.vehicle.v3.b.d.a aVarF = ((com.ecarx.eas.sdk.vehicle.v3.e) this.mService).f();
                if (aVarF != null) {
                    aVar = new com.ecarx.eas.sdk.vehicle.d.a(aVarF);
                }
                this.f208d = aVar;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return this.f208d;
        }
    }

    private FunctionStatus a(int i) {
        if (this.mService == 0) {
            return FunctionStatus.error;
        }
        try {
            return FunctionStatus.getFunctionStatus(((com.ecarx.eas.sdk.vehicle.v3.e) this.mService).e().a(i));
        } catch (RemoteException e) {
            e.printStackTrace();
            return FunctionStatus.error;
        }
    }

    private float b(int i) {
        if (this.mService == 0) {
            return -2.1474836E9f;
        }
        try {
            return ((com.ecarx.eas.sdk.vehicle.v3.e) this.mService).e().c(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -2.1474836E9f;
        }
    }

    private int c(int i) {
        if (this.mService == 0) {
            return Integer.MIN_VALUE;
        }
        try {
            return ((com.ecarx.eas.sdk.vehicle.v3.e) this.mService).e().b(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return Integer.MIN_VALUE;
        }
    }

    private boolean a(ISensorListenerPer iSensorListenerPer, int i) {
        if (this.mService == 0 || iSensorListenerPer == null) {
            return false;
        }
        if (this.g == null) {
            this.g = new ConcurrentHashMap<>();
        }
        try {
            com.ecarx.eas.sdk.vehicle.i.b bVar = this.g.get(iSensorListenerPer);
            if (bVar == null) {
                bVar = new com.ecarx.eas.sdk.vehicle.i.b(new com.ecarx.eas.sdk.vehicle.i.c(iSensorListenerPer, i), i);
            }
            boolean zA = ((com.ecarx.eas.sdk.vehicle.v3.e) this.mService).e().a(bVar, i);
            if (zA) {
                this.g.put(iSensorListenerPer, bVar);
            }
            return zA;
        } catch (RemoteException e) {
            e.printStackTrace();
            return true;
        }
    }

    private boolean a(ISensorListenerPer iSensorListenerPer) {
        ConcurrentHashMap<ISensorListenerPer, com.ecarx.eas.sdk.vehicle.i.b> concurrentHashMap;
        com.ecarx.eas.sdk.vehicle.i.b bVar;
        if (iSensorListenerPer != null && this.mService != 0 && (concurrentHashMap = this.g) != null && (bVar = concurrentHashMap.get(iSensorListenerPer)) != null) {
            try {
                ((com.ecarx.eas.sdk.vehicle.v3.e) this.mService).e().a(bVar);
                bVar.a();
                this.g.remove(iSensorListenerPer);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private boolean a(int i, ISensorListenerPer iSensorListenerPer) {
        if (this.mService == 0 || iSensorListenerPer == null) {
            return false;
        }
        if (this.h == null) {
            this.h = new SparseArray<>();
        }
        Map<ISensorListenerPer, com.ecarx.eas.sdk.vehicle.i.b> mapSynchronizedMap = this.h.get(i);
        if (mapSynchronizedMap != null && mapSynchronizedMap.containsKey(iSensorListenerPer)) {
            return true;
        }
        if (mapSynchronizedMap == null) {
            mapSynchronizedMap = Collections.synchronizedMap(new HashMap());
        }
        try {
            com.ecarx.eas.sdk.vehicle.i.b bVar = new com.ecarx.eas.sdk.vehicle.i.b(new com.ecarx.eas.sdk.vehicle.i.c(iSensorListenerPer, i), i);
            boolean zA = ((com.ecarx.eas.sdk.vehicle.v3.e) this.mService).e().a(bVar, i);
            if (zA) {
                mapSynchronizedMap.put(iSensorListenerPer, bVar);
                this.h.put(i, mapSynchronizedMap);
            }
            return zA;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    private boolean b(int i, ISensorListenerPer iSensorListenerPer) {
        Map<ISensorListenerPer, com.ecarx.eas.sdk.vehicle.i.b> map;
        com.ecarx.eas.sdk.vehicle.i.b bVar;
        if (this.mService == 0 || iSensorListenerPer == null || (map = this.h.get(i)) == null || !map.containsKey(iSensorListenerPer) || (bVar = map.get(iSensorListenerPer)) == null) {
            return false;
        }
        try {
            ((com.ecarx.eas.sdk.vehicle.v3.e) this.mService).e().a(bVar);
            map.remove(iSensorListenerPer);
            bVar.a();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public FunctionStatus isCarSpeedSupported() {
        return a(1048832);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public boolean registerCarSpeedListener(ICarSpeedListener iCarSpeedListener) {
        return a(iCarSpeedListener, 1048832);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public boolean unRegisterCarSpeedListener(ICarSpeedListener iCarSpeedListener) {
        return a(iCarSpeedListener);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public float getCarSpeed() {
        return b(1048832);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public FunctionStatus isCarKeyStatusSupported() {
        return a(2097408);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public int getCarKeyStatus() {
        return c(2097408);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public boolean registerCarKeyStatusListener(ICarKeyStatusListener iCarKeyStatusListener) {
        return a(iCarKeyStatusListener, 2097408);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public boolean unRegisterCarKeyStatusListener(ICarKeyStatusListener iCarKeyStatusListener) {
        return a(iCarKeyStatusListener);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public FunctionStatus isGearSupported() {
        return a(2097664);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public int getGear() {
        return c(2097664);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public boolean registerGearListener(IGearListener iGearListener) {
        return a(iGearListener, 2097664);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public boolean unRegisterGearListener(IGearListener iGearListener) {
        return a(iGearListener);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public FunctionStatus isEvBatteryPercentageSupported() {
        return a(IVirtualSensor.TYPE_EV_BATTERY_PERCENTAGE);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public float getEvBatteryPercentage() {
        return b(IVirtualSensor.TYPE_EV_BATTERY_PERCENTAGE);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public FunctionStatus isEvBatteryStateSupported() {
        return a(ISensor.SENSOR_TYPE_EV_BATTERY_STATE);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public int getEvBatteryState() {
        return c(ISensor.SENSOR_TYPE_EV_BATTERY_STATE);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public FunctionStatus isEnduranceMileageSupported() {
        return a(ISensor.SENSOR_TYPE_ENDURANCE_MILEAGE);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public float getEnduranceMileage() {
        return b(ISensor.SENSOR_TYPE_ENDURANCE_MILEAGE);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public FunctionStatus isFuelWarnSupported() {
        return a(ISensor.SENSOR_TYPE_WARN_FUEL_RED);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public boolean registerFuelWarnListener(IWarningStatusListener iWarningStatusListener) {
        return a(iWarningStatusListener, ISensor.SENSOR_TYPE_WARN_FUEL_RED);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public boolean unRegisterFuelWarnListener(IWarningStatusListener iWarningStatusListener) {
        return a(iWarningStatusListener);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public FunctionStatus isEvBatteryWarnSupported() {
        return a(3146240);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public boolean registerEvBatteryWarnListener(IWarningStatusListener iWarningStatusListener) {
        return a(iWarningStatusListener, 3146240);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public boolean unRegisterEvBatteryWarnListener(IWarningStatusListener iWarningStatusListener) {
        return a(iWarningStatusListener);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public FunctionStatus isEvBatteryLevelSupported() {
        return a(ISensor.SENSOR_TYPE_EV_BATTERY_LEVEL);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public float getEvBatteryLevel() {
        return b(ISensor.SENSOR_TYPE_EV_BATTERY_LEVEL);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public FunctionStatus isSafeBeltStatusSupported(int i) {
        return a(i);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public int getSafeBeltStateByType(int i) {
        return c(i);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public boolean registerSafeBeltListenerByType(int i, ISafeBeltStateListener iSafeBeltStateListener) {
        return a(i, iSafeBeltStateListener);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public boolean unRegisterSafeBeltListener(int i, ISafeBeltStateListener iSafeBeltStateListener) {
        return b(i, iSafeBeltStateListener);
    }

    private p b() {
        if (this.e == null) {
            synchronized (this) {
                if (this.e == null) {
                    try {
                        this.e = new com.ecarx.eas.sdk.vehicle.e.a(((com.ecarx.eas.sdk.vehicle.v3.e) this.mService).g());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return this.e;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public FunctionStatus isDriveModeSupported() {
        if (b() == null) {
            return FunctionStatus.error;
        }
        return b().a();
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public int getCurrentDriveMode() {
        if (b() == null) {
            return Integer.MIN_VALUE;
        }
        return b().b();
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public FunctionStatus isFuelLevelSupported() {
        return a(ISensor.SENSOR_TYPE_FUEL_LEVEL);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public float getLastFuelLevel() {
        return b(ISensor.SENSOR_TYPE_FUEL_LEVEL);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.IVehicleAPI
    public IAudio getAudio() {
        if (this.f == null) {
            synchronized (this) {
                if (this.f == null) {
                    try {
                        this.f = new com.ecarx.eas.sdk.vehicle.a.a(this, ((com.ecarx.eas.sdk.vehicle.v3.e) this.mService).h());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return this.f;
    }
}
