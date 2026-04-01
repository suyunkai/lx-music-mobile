package com.ecarx.eas.sdk.vehicle.api.sensor;

import com.ecarx.eas.sdk.vehicle.api.sensor.callback.ISensorEventListener;

/* JADX INFO: loaded from: classes2.dex */
public interface IGearListener extends ISensorEventListener {
    @Override // com.ecarx.eas.sdk.vehicle.api.sensor.callback.ISensorEventListener
    void onSensorEventChanged(int i);
}
