package com.ecarx.eas.sdk.vehicle.api.audio;

import com.ecarx.eas.sdk.vehicle.FunctionStatus;

/* JADX INFO: loaded from: classes2.dex */
public interface IAudio {
    public static final int COMMON_VALUE_OFF = 0;
    public static final int COMMON_VALUE_ON = 1;

    int getAudioProvider();

    int getCaeSwitchValue();

    FunctionStatus isCaeSwitchSupported();

    boolean registerCaeSwitchValueWatcher(ICaeSwitchChangeCallback iCaeSwitchChangeCallback);

    boolean setCaeSwitchValue(int i);

    boolean unregisterCaeSwitchValueWatcher(ICaeSwitchChangeCallback iCaeSwitchChangeCallback);
}
