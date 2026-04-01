package com.ecarx.eas.sdk.vehicle.api.havc;

/* JADX INFO: loaded from: classes2.dex */
public interface IHvacState {
    public static final int AQS_LEVEL_DEFAULT = Integer.MAX_VALUE;
    public static final int AQS_LEVEL_HIGH = 2147483644;
    public static final int AQS_LEVEL_LOW = 2147483646;
    public static final int AQS_LEVEL_MID = 2147483645;
    public static final int BLOWING_MODE_DEFROSTING = 4;
    public static final int BLOWING_MODE_FACE = 1;
    public static final int BLOWING_MODE_FACE_AND_LEG = 3;
    public static final int BLOWING_MODE_LEG = 2;
    public static final int BLOWING_MODE_LEG_AND_DEFROSTING = 6;
    public static final int IONS_MODE_AUTO = 2;
    public static final int IONS_MODE_ERROR = 16;
    public static final int IONS_MODE_HIGH = 64;
    public static final int IONS_MODE_LOW = 32;
    public static final int IONS_MODE_MANUAL = 4;
    public static final int IONS_MODE_OFF = 0;
    public static final int IONS_MODE_ON = 128;
    public static final int IONS_MODE_QUITE = 1;
    public static final int IONS_MODE_SLEEP = 8;
    public static final int LOOP_MODE_DEFAULT = 1;
    public static final int LOOP_MODE_EXTERNAL = 2;
    public static final int LOOP_MODE_INTERNAL = 4;

    int getAQSLevel();

    int getAmbientAQSLevel();

    int getAmbientPM2_5AQI();

    double getAmbientTemperature();

    int getBlowingLevel();

    int getBlowingMode();

    int getIonsMode();

    double getLeftAreaTemperature();

    int getLoopMode();

    int getPM2_5AQI();

    double getRightAreaTemperature();

    boolean isAcAutoOn();

    boolean isAcMax();

    boolean isAcOn();

    boolean isAqsOn();

    boolean isDualOn();

    boolean isFrontDefrosting();

    boolean isHvacOn();

    boolean isRearDefrosting();

    boolean isRearDefrostingMax();
}
