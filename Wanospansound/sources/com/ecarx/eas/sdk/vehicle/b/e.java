package com.ecarx.eas.sdk.vehicle.b;

import com.ecarx.eas.sdk.vehicle.api.carinfo.audio.InternalCarAudioManager;

/* JADX INFO: loaded from: classes2.dex */
public final class e extends f implements InternalCarAudioManager {
    public e(com.ecarx.eas.sdk.vehicle.v3.b.b.a.a aVar) {
        super(aVar);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.carinfo.audio.InternalCarAudioManager
    public final void setUsageVolume(String str, int i, int i2) {
        try {
            this.f195a.a(str, i, i2);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.carinfo.audio.InternalCarAudioManager
    public final boolean setZoneIdForUid(int i, int i2) {
        try {
            return this.f195a.a(i, i2);
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.carinfo.audio.InternalCarAudioManager
    public final boolean clearZoneIdForUid(int i) {
        try {
            return this.f195a.a(i);
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }
}
