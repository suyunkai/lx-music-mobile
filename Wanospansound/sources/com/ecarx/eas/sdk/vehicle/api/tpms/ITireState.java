package com.ecarx.eas.sdk.vehicle.api.tpms;

/* JADX INFO: loaded from: classes2.dex */
public interface ITireState {
    float getPressure();

    int getTemperature();

    boolean hasPressureWarning();

    boolean isQuickLeaking();
}
