package com.ecarx.eas.sdk.vehicle.f;

import android.os.RemoteException;
import android.util.Log;
import com.ecarx.eas.sdk.vehicle.FunctionStatus;
import com.ecarx.eas.sdk.vehicle.api.sensor.ISensor;
import com.ecarx.eas.sdk.vehicle.api.sensor.ISensorListener;
import com.ecarx.eas.sdk.vehicle.v3.b.b.b.b;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public abstract class z implements ISensor {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected com.ecarx.eas.sdk.vehicle.v3.b.b.b.a f266a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f267b = "ISensorWrapper";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private HashMap<ISensorListener, a> f268c = new HashMap<>();

    public final void a(com.ecarx.eas.sdk.vehicle.v3.e eVar) {
        if (eVar != null) {
            try {
                this.f266a = eVar.e();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.sensor.ISensor
    public FunctionStatus isSensorSupported(int i) {
        int iA;
        try {
            iA = this.f266a.a(i);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if (iA == 0) {
            return FunctionStatus.active;
        }
        if (iA == 1) {
            return FunctionStatus.notactive;
        }
        if (iA == 2) {
            return FunctionStatus.notavailable;
        }
        if (iA == 3) {
            return FunctionStatus.error;
        }
        return FunctionStatus.error;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.sensor.ISensor
    public int getSensorEvent(int i) throws RemoteException {
        return this.f266a.b(i);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.sensor.ISensor
    public float getSensorLatestValue(int i) {
        try {
            return this.f266a.c(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.sensor.ISensor
    public boolean registerListener(ISensorListener iSensorListener, int i) {
        return registerListener(iSensorListener, i, -99);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.sensor.ISensor
    public boolean registerListener(ISensorListener iSensorListener, int i, int i2) {
        boolean zA;
        if (this.f268c.get(iSensorListener) == null) {
            a aVar = new a(iSensorListener);
            try {
                if (i2 == -99) {
                    zA = this.f266a.a(aVar, i);
                } else {
                    zA = this.f266a.a(aVar, i, i2);
                }
                if (zA) {
                    this.f268c.put(iSensorListener, aVar);
                    Log.d(this.f267b, "Has register ISensorListener");
                    return zA;
                }
                Log.d(this.f267b, "Can't register ISensorListener");
                return false;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.d(this.f267b, "Has register ISensorListener");
        return true;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.sensor.ISensor
    public void unregisterListener(ISensorListener iSensorListener) {
        a aVar = this.f268c.get(iSensorListener);
        if (aVar != null) {
            try {
                this.f268c.remove(iSensorListener);
                this.f266a.a(aVar);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    static class a extends b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private ISensorListener f269a;

        public a(ISensorListener iSensorListener) {
            this.f269a = iSensorListener;
        }

        @Override // com.ecarx.eas.sdk.vehicle.v3.b.b.b.b
        public final void a(int i, int i2) throws RemoteException {
            this.f269a.onSensorEventChanged(i, i2);
        }

        @Override // com.ecarx.eas.sdk.vehicle.v3.b.b.b.b
        public final void a(int i, float f) throws RemoteException {
            this.f269a.onSensorValueChanged(i, f);
        }

        @Override // com.ecarx.eas.sdk.vehicle.v3.b.b.b.b
        public final void b(int i, int i2) throws RemoteException {
            try {
                if (i2 == 0) {
                    this.f269a.onSensorSupportChanged(i, FunctionStatus.active);
                    return;
                }
                if (i2 == 1) {
                    this.f269a.onSensorSupportChanged(i, FunctionStatus.notactive);
                } else if (i2 == 2) {
                    this.f269a.onSensorSupportChanged(i, FunctionStatus.notavailable);
                } else {
                    if (i2 != 3) {
                        return;
                    }
                    this.f269a.onSensorSupportChanged(i, FunctionStatus.error);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
