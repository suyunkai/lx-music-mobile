package com.ecarx.eas.sdk.vehicle.i;

import android.os.RemoteException;
import com.ecarx.eas.sdk.vehicle.FunctionStatus;
import com.ecarx.eas.sdk.vehicle.api.sensor.ISensorListener;
import com.ecarx.eas.sdk.vehicle.v3.b.b.b.b;

/* JADX INFO: loaded from: classes2.dex */
public final class b extends b.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ISensorListener f301a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f302b;

    public b(ISensorListener iSensorListener, int i) {
        this.f301a = iSensorListener;
        this.f302b = i;
    }

    @Override // com.ecarx.eas.sdk.vehicle.v3.b.b.b.b
    public final void a(int i, int i2) throws RemoteException {
        ISensorListener iSensorListener;
        if (this.f302b != i || (iSensorListener = this.f301a) == null) {
            return;
        }
        iSensorListener.onSensorEventChanged(i, i2);
    }

    @Override // com.ecarx.eas.sdk.vehicle.v3.b.b.b.b
    public final void a(int i, float f) throws RemoteException {
        ISensorListener iSensorListener;
        if (this.f302b != i || (iSensorListener = this.f301a) == null) {
            return;
        }
        iSensorListener.onSensorValueChanged(i, f);
    }

    @Override // com.ecarx.eas.sdk.vehicle.v3.b.b.b.b
    public final void b(int i, int i2) throws RemoteException {
        ISensorListener iSensorListener;
        if (this.f302b != i || (iSensorListener = this.f301a) == null) {
            return;
        }
        try {
            if (i2 == 0) {
                iSensorListener.onSensorSupportChanged(i, FunctionStatus.active);
                return;
            }
            if (i2 == 1) {
                iSensorListener.onSensorSupportChanged(i, FunctionStatus.notactive);
            } else if (i2 == 2) {
                iSensorListener.onSensorSupportChanged(i, FunctionStatus.notavailable);
            } else {
                if (i2 != 3) {
                    return;
                }
                iSensorListener.onSensorSupportChanged(i, FunctionStatus.error);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void a() {
        synchronized (this) {
            this.f301a = null;
        }
    }
}
