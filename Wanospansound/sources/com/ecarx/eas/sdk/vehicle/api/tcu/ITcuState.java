package com.ecarx.eas.sdk.vehicle.api.tcu;

/* JADX INFO: loaded from: classes2.dex */
public interface ITcuState {
    public static final int GEAR_1 = 1;
    public static final int GEAR_10 = 10;
    public static final int GEAR_11 = 11;
    public static final int GEAR_2 = 2;
    public static final int GEAR_3 = 3;
    public static final int GEAR_4 = 4;
    public static final int GEAR_5 = 5;
    public static final int GEAR_6 = 6;
    public static final int GEAR_7 = 7;
    public static final int GEAR_8 = 8;
    public static final int GEAR_9 = 9;
    public static final int GEAR_N = 0;
    public static final int GEAR_REVERSE = -1;
    public static final int GEAR_SEL_POS_1 = 1;
    public static final int GEAR_SEL_POS_10 = 10;
    public static final int GEAR_SEL_POS_11 = 11;
    public static final int GEAR_SEL_POS_2 = 2;
    public static final int GEAR_SEL_POS_3 = 3;
    public static final int GEAR_SEL_POS_4 = 4;
    public static final int GEAR_SEL_POS_5 = 5;
    public static final int GEAR_SEL_POS_6 = 6;
    public static final int GEAR_SEL_POS_7 = 7;
    public static final int GEAR_SEL_POS_8 = 8;
    public static final int GEAR_SEL_POS_9 = 9;
    public static final int GEAR_SEL_POS_D = 101;
    public static final int GEAR_SEL_POS_L = 104;
    public static final int GEAR_SEL_POS_N = 0;
    public static final int GEAR_SEL_POS_P = 102;
    public static final int GEAR_SEL_POS_R = -1;
    public static final int GEAR_SEL_POS_S = 103;

    int getGear();

    int getGearSelectorPosition();

    Tribool isManualShiftMode();
}
