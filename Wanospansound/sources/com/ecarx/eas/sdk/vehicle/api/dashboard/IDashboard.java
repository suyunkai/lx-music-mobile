package com.ecarx.eas.sdk.vehicle.api.dashboard;

import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IDashboard {
    public static final int CAR_KEY_STATUS_ACC = 2;
    public static final int CAR_KEY_STATUS_ACC_LIMITED = 6;
    public static final int CAR_KEY_STATUS_CRANK = 3;
    public static final int CAR_KEY_STATUS_OFF = 1;
    public static final int CAR_KEY_STATUS_REMOVE = 5;
    public static final int CAR_KEY_STATUS_RUN = 4;
    public static final int CAR_KEY_STATUS_UNKNOWN = Integer.MIN_VALUE;
    public static final int HANDBRAKE_STATE_LOCK = 2097921;
    public static final int HANDBRAKE_STATE_UNLOCK = 2097922;
    public static final int HANDBREAK_STATUS_LOCKED = 0;
    public static final int HANDBREAK_STATUS_RELEASED = 1;
    public static final int ID_HANDBREAK_HINT = 1;
    public static final int IGNITION_STATE_ACC = 2097412;
    public static final int IGNITION_STATE_DRIVING = 2097415;
    public static final int IGNITION_STATE_LOCK = 2097410;
    public static final int IGNITION_STATE_OFF = 2097411;
    public static final int IGNITION_STATE_ON = 2097413;
    public static final int IGNITION_STATE_START = 2097414;
    public static final int IGNITION_STATE_UNDEFINED = 2097409;
    public static final int SENSOR_TYPE_CAR_SPEED = 1048832;
    public static final int SENSOR_TYPE_HANDBRAKE_STATE = 2097920;
    public static final int SENSOR_TYPE_IGNITION_STATE = 2097408;

    public interface IDashboardHintObserver {
        void onDashboardHintChanged(int i, int i2);
    }

    public interface IVehicleACCStatusObserver {
        void onVehicleACCStatusChanged(int i);
    }

    public interface IVehicleSpeedObserver {
        void onVehicleSpeedChanged(double d2) throws RemoteException;

        void onVehicleSpeedChanged(double d2, int i) throws RemoteException;

        @Deprecated
        void onVehicleSpeedChanged(int i) throws RemoteException;
    }

    boolean attachDashboardHintChangeObserver(IDashboardHintObserver iDashboardHintObserver);

    boolean attachVehicleACCStatusObserver(IVehicleACCStatusObserver iVehicleACCStatusObserver);

    boolean attachVehicleSpeedObserver(IVehicleSpeedObserver iVehicleSpeedObserver);

    boolean attachVehicleSpeedObserver(IVehicleSpeedObserver iVehicleSpeedObserver, double[] dArr, int i, int i2);

    void detachDashboardHintChangeObserver(IDashboardHintObserver iDashboardHintObserver);

    void detachVehicleACCStatusObserver(IVehicleACCStatusObserver iVehicleACCStatusObserver);

    void detachVehicleSpeedObserver(IVehicleSpeedObserver iVehicleSpeedObserver);

    IMileageInfo geMileageInfo();

    int getAccStatus();

    double getAverageFuelConsumptionLevel();

    int getFuelLevel();

    int getFullFuelLevel();

    int getHandBrakeStatus();

    double getInstantaneousFuelConsumptionLevel();

    int getVehicleSpeed();

    double getVehicleSpeedHighPrecision();

    @Deprecated
    IWarningInfo[] getWarningInfo();

    IWarningInfo getWarningInformation();
}
