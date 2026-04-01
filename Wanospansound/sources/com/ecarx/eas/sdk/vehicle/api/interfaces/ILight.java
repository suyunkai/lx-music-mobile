package com.ecarx.eas.sdk.vehicle.api.interfaces;

/* JADX INFO: loaded from: classes2.dex */
public interface ILight {

    public interface ILightCallback {
        void onLightOff();

        void onLightOn();
    }

    IDippedBeamLight getDippedBeamLight();

    ILeftTurnLight getLeftTurnLight();

    IRightTurnLight getRightTurnLight();

    IStopLight getStopLight();
}
