package com.ecarx.eas.sdk.vehicle.api.newenergy;

/* JADX INFO: loaded from: classes2.dex */
public interface IChargeState {
    public static final int CHARGE_PLUG_STATE_CHARGING = 2;
    public static final int CHARGE_PLUG_STATE_COMPLETED = 3;
    public static final int CHARGE_PLUG_STATE_PREPARED = 1;
    public static final int CHARGE_PLUG_STATE_UNKNOW = 0;
    public static final int PRECHARGE_STATE_CANCEL_FAILURE = 4;
    public static final int PRECHARGE_STATE_CANCEL_SUCCEED = 3;
    public static final int PRECHARGE_STATE_SET_FAILURE = 2;
    public static final int PRECHARGE_STATE_SET_SUCCEED = 1;
    public static final int PRECHARGE_STATE_UNKNOW = 0;

    int getChargePlugState();

    long getPreChargeDelayTime();

    long getPreChargeDuration();

    long getPreChargeStartTime();

    int getPreChargeState();

    boolean isPreChargeOutofDate();

    boolean isPreChargePerDay();
}
