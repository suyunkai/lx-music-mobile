package com.ecarx.eas.sdk.vehicle.api.newenergy;

/* JADX INFO: loaded from: classes2.dex */
public interface IEpt {

    public interface IEptStateListener {
        void onEptStateChanged(IEptState iEptState);
    }

    IEptState getEptState();

    int getSOCPointLevelMax();

    int getSOCPointLevelMin();

    int getSOCPointLevelStep();

    void registerStateListener(IEptStateListener iEptStateListener);

    void setAvasDisable(boolean z);

    void setAvasVolumeType(int i);

    void setInfrequentChargingMode(boolean z);

    void setSOCPointLevel(int i);

    void unregisterStateListener(IEptStateListener iEptStateListener);
}
