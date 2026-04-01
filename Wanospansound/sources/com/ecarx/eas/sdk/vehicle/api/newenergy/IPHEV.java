package com.ecarx.eas.sdk.vehicle.api.newenergy;

/* JADX INFO: loaded from: classes2.dex */
public interface IPHEV {

    public interface IAvgEnergyInfo {
        float getAvgEleConsumption();

        float getAvgEnergyFeedback();

        float getAvgFuelConsumption();
    }

    public interface IDrivingInfo {
        int getCurrentTripDistance();

        long getCurrentTripDuration();

        int getDrivingDistanceInCurrentDay();

        float getEleEnduranceMileagePercentage();

        int getEnduranceMileageByEle();

        int getEnduranceMileageByFuel();
    }

    public interface IPHEVListener {
        void onAvgEnergyInfoUpdate(IAvgEnergyInfo iAvgEnergyInfo);

        void onDrivingInfoUpdate(IDrivingInfo iDrivingInfo);
    }

    int getUpdateFrequencyUnit();

    void registerPHEVListener(IPHEVListener iPHEVListener);

    void setUpdateFrequencyUnit(int i);

    void unregisterPHEVListener(IPHEVListener iPHEVListener);
}
