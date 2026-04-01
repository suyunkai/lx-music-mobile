package com.ecarx.eas.sdk.vehicle.f;

import android.os.RemoteException;
import android.util.Log;
import com.ecarx.eas.framework.sdk.EASCallUtils;
import com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService;
import com.ecarx.eas.framework.sdk.proto.MessageNano;
import com.ecarx.eas.sdk.IServiceManager;
import com.ecarx.eas.sdk.vehicle.FunctionStatus;
import com.ecarx.eas.sdk.vehicle.api.sensor.ISensor;
import com.ecarx.eas.sdk.vehicle.api.sensor.ISensorListener;
import com.ecarx.eas.sdk.vehicle.v3.b.b.b.b;
import com.ecarx.openapi.protobuf.ECARXCommon;
import com.ecarx.openapi.protobuf.vehicle.ECARXAdaptVehicle;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public abstract class v implements ISensor {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected IEASFrameworkService f248a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f249b = getClass().getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private HashMap<ISensorListener, a> f250c = new HashMap<>();

    public final void a(IEASFrameworkService iEASFrameworkService) {
        this.f248a = iEASFrameworkService;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.sensor.ISensor
    public FunctionStatus isSensorSupported(int i) {
        if (this.f248a == null) {
            return FunctionStatus.error;
        }
        ECARXCommon.IntMsg intMsg = new ECARXCommon.IntMsg();
        intMsg.value = i;
        int iCallInt = EASCallUtils.callInt(this.f248a, IServiceManager.SERVICE_VEHICLE, "Sensor", "isSensorSupported", MessageNano.toByteArray(intMsg), null);
        if (iCallInt == 0) {
            return FunctionStatus.active;
        }
        if (iCallInt == 1) {
            return FunctionStatus.notactive;
        }
        if (iCallInt == 2) {
            return FunctionStatus.notavailable;
        }
        return FunctionStatus.error;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.sensor.ISensor
    public int getSensorEvent(int i) {
        if (this.f248a == null) {
            return Integer.MIN_VALUE;
        }
        ECARXCommon.IntMsg intMsg = new ECARXCommon.IntMsg();
        intMsg.value = i;
        return EASCallUtils.callInt(this.f248a, IServiceManager.SERVICE_VEHICLE, "Sensor", "getSensorEvent", MessageNano.toByteArray(intMsg), null);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.sensor.ISensor
    public float getSensorLatestValue(int i) {
        if (this.f248a == null) {
            return -2.1474836E9f;
        }
        ECARXCommon.IntMsg intMsg = new ECARXCommon.IntMsg();
        intMsg.value = i;
        return EASCallUtils.callFloat(this.f248a, IServiceManager.SERVICE_VEHICLE, "Sensor", "getSensorLatestValue", MessageNano.toByteArray(intMsg), null);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.sensor.ISensor
    public boolean registerListener(ISensorListener iSensorListener, int i) {
        if (iSensorListener == null || this.f248a == null) {
            return false;
        }
        return registerListener(iSensorListener, i, -99);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.sensor.ISensor
    public boolean registerListener(ISensorListener iSensorListener, int i, int i2) {
        boolean zCallBoolean;
        if (this.f250c.get(iSensorListener) == null) {
            a aVar = new a(iSensorListener);
            if (i2 == -99) {
                ECARXCommon.IntMsg intMsg = new ECARXCommon.IntMsg();
                intMsg.value = i;
                zCallBoolean = EASCallUtils.callBoolean(this.f248a, IServiceManager.SERVICE_VEHICLE, "Sensor", "registerListener0", MessageNano.toByteArray(intMsg), aVar);
            } else {
                ECARXAdaptVehicle.RegisterSensorInfo registerSensorInfo = new ECARXAdaptVehicle.RegisterSensorInfo();
                registerSensorInfo.rate = i2;
                registerSensorInfo.sensor = i;
                zCallBoolean = EASCallUtils.callBoolean(this.f248a, IServiceManager.SERVICE_VEHICLE, "Sensor", "registerListener1", MessageNano.toByteArray(registerSensorInfo), aVar);
            }
            if (zCallBoolean) {
                this.f250c.put(iSensorListener, aVar);
                Log.d(this.f249b, "Has register ISensorListener");
                return zCallBoolean;
            }
            Log.d(this.f249b, "Can't register ISensorListener");
            return false;
        }
        Log.d(this.f249b, "Has register ISensorListener");
        return true;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.sensor.ISensor
    public void unregisterListener(ISensorListener iSensorListener) {
        a aVar = this.f250c.get(iSensorListener);
        if (aVar != null) {
            this.f250c.remove(iSensorListener);
            EASCallUtils.call(this.f248a, IServiceManager.SERVICE_VEHICLE, "Sensor", "unregisterListener", null, aVar);
        }
    }

    static class a extends b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private ISensorListener f251a;

        public a(ISensorListener iSensorListener) {
            this.f251a = iSensorListener;
        }

        @Override // com.ecarx.eas.sdk.vehicle.v3.b.b.b.b
        public final void a(int i, int i2) throws RemoteException {
            this.f251a.onSensorEventChanged(i, i2);
        }

        @Override // com.ecarx.eas.sdk.vehicle.v3.b.b.b.b
        public final void a(int i, float f) throws RemoteException {
            this.f251a.onSensorValueChanged(i, f);
        }

        @Override // com.ecarx.eas.sdk.vehicle.v3.b.b.b.b
        public final void b(int i, int i2) throws RemoteException {
            try {
                if (i2 == 0) {
                    this.f251a.onSensorSupportChanged(i, FunctionStatus.active);
                    return;
                }
                if (i2 == 1) {
                    this.f251a.onSensorSupportChanged(i, FunctionStatus.notactive);
                } else if (i2 == 2) {
                    this.f251a.onSensorSupportChanged(i, FunctionStatus.notavailable);
                } else {
                    if (i2 != 3) {
                        return;
                    }
                    this.f251a.onSensorSupportChanged(i, FunctionStatus.error);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
