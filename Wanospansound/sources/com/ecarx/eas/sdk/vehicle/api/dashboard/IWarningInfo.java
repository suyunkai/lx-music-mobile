package com.ecarx.eas.sdk.vehicle.api.dashboard;

/* JADX INFO: loaded from: classes2.dex */
public interface IWarningInfo {
    public static final int ID_ADAPTIVE_CRUISE_CONTROL = 11;
    public static final int ID_AIRBAG = 2;
    public static final int ID_CAR_RADAR = 22;
    public static final int ID_CAR_WINDOW = 20;
    public static final int ID_DRIVING_RECORDER = 21;
    public static final int ID_ELECTRIC_BRAKEFORCE_DISTRIBUTION = 6;
    public static final int ID_ELECTRIC_PARKING_BRAKE = 7;
    public static final int ID_ELECTRONIC_STABILITY_CONTROL = 8;
    public static final int ID_ENGINE = 4;
    public static final int ID_ENGINE_EMISSION = 3;
    public static final int ID_ENGINE_OIL_PRESSURE = 5;
    public static final int ID_FUEL_LEVEL = 9;
    public static final int ID_FUEL_LEVEL_RED = 16;
    public static final int ID_HILL_ASSIST_CONTROL = 13;
    public static final int ID_LANE_DEPARTURE_WARNING = 12;
    public static final int ID_SEAT_HEATING = 17;
    public static final int ID_SEAT_MASSAGE = 19;
    public static final int ID_SEAT_VENTILATION = 18;
    public static final int ID_TRANSMISSION_CONTROL_UNIT = 10;
    public static final int ID_TRANS_TEMP_WARN_LEVEL_1 = 23;
    public static final int ID_TRANS_TEMP_WARN_LEVEL_2 = 24;
    public static final int ID_WATER_TEMPERATURE = 1;

    int getWarningId();

    int getWarningPriority();
}
