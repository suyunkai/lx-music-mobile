package com.ecarx.eas.sdk.vehicle.api.newenergy;

/* JADX INFO: loaded from: classes2.dex */
public interface INewEnergy {
    public static final int Battery_REMIND_CHARGE_COMPLETE = 4;
    public static final int Battery_REMIND_CHARGE_ERROR = 5;
    public static final int Battery_REMIND_CHARGE_HEATING = 6;
    public static final int Battery_REMIND_CHARGE_PAUSE = 7;
    public static final int Battery_REMIND_CHARGING = 3;
    public static final int Battery_REMIND_PREPARE_CHARGE = 1;
    public static final int Battery_REMIND_PRE_CHARGE_WAIT = 8;
    public static final int Battery_REMIND_READY_TO_CHARGE = 2;
    public static final int NEV_TYPE_BEV = 7;
    public static final int NEV_TYPE_EREV = 3;
    public static final int NEV_TYPE_FCEV = 4;
    public static final int NEV_TYPE_FCV = 5;
    public static final int NEV_TYPE_HEV = 1;
    public static final int NEV_TYPE_MHEV = 6;
    public static final int NEV_TYPE_NONE = 2147483646;
    public static final int NEV_TYPE_PHEV = 2;

    public interface IBatteryStatusObserver {
        void onBatteryReminder(int i);
    }

    boolean attachBatteryStatusObserver(IBatteryStatusObserver iBatteryStatusObserver);

    void detachBatteryStatusObserver(IBatteryStatusObserver iBatteryStatusObserver);

    ICharge getChargeManager();

    int getCurrentBatteryStatus();

    IEpt getIEpt();

    int getNewEnergyType();

    IPHEV getPHEV();
}
