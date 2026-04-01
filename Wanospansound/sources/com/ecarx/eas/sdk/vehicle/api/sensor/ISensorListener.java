package com.ecarx.eas.sdk.vehicle.api.sensor;

import com.ecarx.eas.sdk.vehicle.FunctionStatus;

/* JADX INFO: loaded from: classes2.dex */
public interface ISensorListener {
    void onSensorEventChanged(int i, int i2);

    void onSensorSupportChanged(int i, FunctionStatus functionStatus);

    void onSensorValueChanged(int i, float f);
}
