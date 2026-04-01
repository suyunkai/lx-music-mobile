package com.ecarx.eas.sdk.vehicle.api.tcu;

/* JADX INFO: loaded from: classes2.dex */
public interface ITcu {
    ITcuState getTcuState();

    void setTcuCallback(ITcuCallback iTcuCallback);

    void unsetTcuCallback(ITcuCallback iTcuCallback);
}
