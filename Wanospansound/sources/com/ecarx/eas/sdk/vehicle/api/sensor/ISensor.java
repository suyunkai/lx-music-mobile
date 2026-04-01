package com.ecarx.eas.sdk.vehicle.api.sensor;

import android.os.RemoteException;
import com.ecarx.eas.sdk.vehicle.FunctionStatus;

/* JADX INFO: loaded from: classes2.dex */
public interface ISensor {
    public static final int RATE_FAST = 1;
    public static final int RATE_FASTEST = 0;
    public static final int RATE_NORMAL = 3;
    public static final int RATE_SLOW = 4;
    public static final int RATE_SLOWEST = 5;
    public static final int RATE_UI = 2;
    public static final int SENSOR_TYPE_AQI_AMBIENT = 1049600;
    public static final int SENSOR_TYPE_AQI_INDOOR = 1049856;
    public static final int SENSOR_TYPE_BRAKE_FLUID_LEVEL = 2098688;
    public static final int SENSOR_TYPE_CAR_MODE = 2102272;
    public static final int SENSOR_TYPE_CAR_SPEED = 1048832;
    public static final int SENSOR_TYPE_CO2_INDOOR = 1051904;
    public static final int SENSOR_TYPE_ENDURANCE_MILEAGE = 1050624;
    public static final int SENSOR_TYPE_ENGINE_COOLANT_LEVEL = 2098432;
    public static final int SENSOR_TYPE_ENGINE_COOLANT_TEMPERATURE = 1052416;
    public static final int SENSOR_TYPE_ENGINE_OIL_LEVEL = 2098176;
    public static final int SENSOR_TYPE_EV_BATTERY_LEVEL = 1051136;
    public static final int SENSOR_TYPE_EV_BATTERY_STATE = 2102528;
    public static final int SENSOR_TYPE_FUEL_LEVEL = 1050112;
    public static final int SENSOR_TYPE_GEAR = 2097664;
    public static final int SENSOR_TYPE_HANDBRAKE_STATE = 2097920;
    public static final int SENSOR_TYPE_IGNITION_STATE = 2097408;
    public static final int SENSOR_TYPE_ODOMETER = 1050368;
    public static final int SENSOR_TYPE_PM25_AMBIENT = 1049088;
    public static final int SENSOR_TYPE_PM25_INDOOR = 1049344;
    public static final int SENSOR_TYPE_RAIN = 1052160;
    public static final int SENSOR_TYPE_RPM = 1050880;
    public static final int SENSOR_TYPE_TEMPERATURE_AMBIENT = 1051392;
    public static final int SENSOR_TYPE_TEMPERATURE_INDOOR = 1051648;
    public static final int SENSOR_TYPE_USG_MODE = 2102016;
    public static final int SENSOR_TYPE_WARN_ENGINE_COOLANT_TEMP_HIGH = 3146752;
    public static final int SENSOR_TYPE_WARN_ENGINE_OIL_PRESSURE = 3146496;
    public static final int SENSOR_TYPE_WARN_EV_BATTERY_LOW = 3146240;
    public static final int SENSOR_TYPE_WARN_FUEL_RED = 3145984;
    public static final int SENSOR_TYPE_WARN_TRANSMISSION_TEMP_HIGH = 3147008;

    int getSensorEvent(int i) throws RemoteException;

    float getSensorLatestValue(int i);

    FunctionStatus isSensorSupported(int i);

    boolean registerListener(ISensorListener iSensorListener, int i);

    boolean registerListener(ISensorListener iSensorListener, int i, int i2);

    void unregisterListener(ISensorListener iSensorListener);
}
