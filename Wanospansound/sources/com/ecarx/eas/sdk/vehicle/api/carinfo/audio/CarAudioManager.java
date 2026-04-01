package com.ecarx.eas.sdk.vehicle.api.carinfo.audio;

/* JADX INFO: loaded from: classes2.dex */
public abstract class CarAudioManager implements InternalCarAudioManager {
    private ICarAudioManager carAudioManager;

    public CarAudioManager(ICarAudioManager iCarAudioManager) {
        this.carAudioManager = iCarAudioManager;
    }

    public void update(ICarAudioManager iCarAudioManager) {
        this.carAudioManager = iCarAudioManager;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.carinfo.audio.ICarAudioManager
    public int getUsageMaxVolume(String str) {
        return this.carAudioManager.getUsageMaxVolume(str);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.carinfo.audio.ICarAudioManager
    public int getUsageMinVolume(String str) {
        return this.carAudioManager.getUsageMinVolume(str);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.carinfo.audio.ICarAudioManager
    public int getUsageVolume(String str) {
        return this.carAudioManager.getUsageVolume(str);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.carinfo.audio.ICarAudioManager
    public boolean registerCarVolumeChangeCallback(ICarVolumeChangeCallback iCarVolumeChangeCallback) {
        return this.carAudioManager.registerCarVolumeChangeCallback(iCarVolumeChangeCallback);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.carinfo.audio.ICarAudioManager
    public boolean unRegisterCarVolumeChangeCallback(ICarVolumeChangeCallback iCarVolumeChangeCallback) {
        return this.carAudioManager.unRegisterCarVolumeChangeCallback(iCarVolumeChangeCallback);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.carinfo.audio.InternalCarAudioManager
    public void setUsageVolume(String str, int i, int i2) {
        ICarAudioManager iCarAudioManager = this.carAudioManager;
        if (iCarAudioManager instanceof InternalCarAudioManager) {
            ((InternalCarAudioManager) iCarAudioManager).setUsageVolume(str, i, i2);
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.carinfo.audio.InternalCarAudioManager
    public boolean setZoneIdForUid(int i, int i2) {
        ICarAudioManager iCarAudioManager = this.carAudioManager;
        if (iCarAudioManager instanceof InternalCarAudioManager) {
            return ((InternalCarAudioManager) iCarAudioManager).setZoneIdForUid(i, i2);
        }
        return false;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.carinfo.audio.InternalCarAudioManager
    public boolean clearZoneIdForUid(int i) {
        ICarAudioManager iCarAudioManager = this.carAudioManager;
        if (iCarAudioManager instanceof InternalCarAudioManager) {
            return ((InternalCarAudioManager) iCarAudioManager).clearZoneIdForUid(i);
        }
        return false;
    }
}
