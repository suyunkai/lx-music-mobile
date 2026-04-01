package com.ecarx.eas.sdk.vehicle.i;

import com.ecarx.eas.sdk.vehicle.FunctionStatus;
import com.ecarx.eas.sdk.vehicle.api.sensor.ISensorListener;
import com.ecarx.eas.sdk.vehicle.api.sensor.ISensorListenerPer;
import com.ecarx.eas.sdk.vehicle.api.sensor.callback.ISensorEventListener;
import com.ecarx.eas.sdk.vehicle.api.sensor.callback.ISensorValueListener;

/* JADX INFO: loaded from: classes2.dex */
public final class c implements ISensorListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ISensorListenerPer f303a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f304b;

    public c(ISensorListenerPer iSensorListenerPer, int i) {
        this.f303a = iSensorListenerPer;
        this.f304b = i;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.sensor.ISensorListener
    public final void onSensorEventChanged(int i, int i2) {
        ISensorListenerPer iSensorListenerPer;
        if (this.f304b == i && (iSensorListenerPer = this.f303a) != null && (iSensorListenerPer instanceof ISensorEventListener)) {
            synchronized (this) {
                ISensorListenerPer iSensorListenerPer2 = this.f303a;
                if (iSensorListenerPer2 == null) {
                    return;
                }
                ((ISensorEventListener) iSensorListenerPer2).onSensorEventChanged(i2);
            }
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.sensor.ISensorListener
    public final void onSensorValueChanged(int i, float f) {
        ISensorListenerPer iSensorListenerPer;
        if (this.f304b == i && (iSensorListenerPer = this.f303a) != null && (iSensorListenerPer instanceof ISensorValueListener)) {
            synchronized (this) {
                ISensorListenerPer iSensorListenerPer2 = this.f303a;
                if (iSensorListenerPer2 == null) {
                    return;
                }
                ((ISensorValueListener) iSensorListenerPer2).onSensorValueChanged(f);
            }
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.sensor.ISensorListener
    public final void onSensorSupportChanged(int i, FunctionStatus functionStatus) {
        ISensorListenerPer iSensorListenerPer;
        synchronized (this) {
            if (this.f304b == i && (iSensorListenerPer = this.f303a) != null) {
                iSensorListenerPer.onSensorSupportChanged(functionStatus);
            }
        }
    }
}
