package com.ecarx.eas.sdk.vehicle.api.carinfo.audio;

/* JADX INFO: loaded from: classes2.dex */
public interface InternalCarAudioManager extends ICarAudioManager {
    boolean clearZoneIdForUid(int i);

    void setUsageVolume(String str, int i, int i2);

    boolean setZoneIdForUid(int i, int i2);
}
