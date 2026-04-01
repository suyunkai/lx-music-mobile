package com.ecarx.eas.sdk.vehicle.api.ems;

/* JADX INFO: loaded from: classes2.dex */
public interface IEms {
    public static final int ENGINE_RUNNING_STATUS_CRANKING = 2;
    public static final int ENGINE_RUNNING_STATUS_RUNNING = 3;
    public static final int ENGINE_RUNNING_STATUS_STOP = 1;

    int getEngineRunningStatus();

    int getEngineSpeed();
}
