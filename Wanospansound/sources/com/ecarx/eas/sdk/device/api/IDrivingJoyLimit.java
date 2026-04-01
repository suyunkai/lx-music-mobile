package com.ecarx.eas.sdk.device.api;

/* JADX INFO: loaded from: classes2.dex */
public interface IDrivingJoyLimit {
    public static final int STATE_JOY_FORBID_OFF = 1048833;
    public static final int STATE_JOY_FORBID_ON = 1048834;
    public static final int STATE_JOY_LIMIT_OFF = 1049089;
    public static final int STATE_JOY_LIMIT_ON = 1049090;
    public static final int STATE_JOY_UNKNOWN = -1;
    public static final int TYPE_JOY_FORBID = 1048832;
    public static final int TYPE_JOY_LIMIT = 1049088;

    int getState(int i);

    Object registerListener(int i, JoyLimitListener joyLimitListener);

    void unRegisterListener(Object obj);
}
