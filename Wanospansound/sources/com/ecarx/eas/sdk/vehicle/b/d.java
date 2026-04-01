package com.ecarx.eas.sdk.vehicle.b;

import com.ecarx.eas.framework.sdk.EASCallUtils;
import com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService;
import com.ecarx.eas.framework.sdk.proto.MessageNano;
import com.ecarx.eas.sdk.IServiceManager;
import com.ecarx.eas.sdk.vehicle.api.carinfo.audio.InternalCarAudioManager;
import com.ecarx.openapi.protobuf.ECARXCommon;
import com.ecarx.openapi.protobuf.vehicle.ECARXAdaptVehicle;

/* JADX INFO: loaded from: classes2.dex */
public final class d extends g implements InternalCarAudioManager {
    public d(IEASFrameworkService iEASFrameworkService) {
        super(iEASFrameworkService);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.carinfo.audio.InternalCarAudioManager
    public final void setUsageVolume(String str, int i, int i2) {
        ECARXAdaptVehicle.UsageVolumeInfo usageVolumeInfo = new ECARXAdaptVehicle.UsageVolumeInfo();
        usageVolumeInfo.usage = str;
        usageVolumeInfo.volume = i;
        usageVolumeInfo.flags = i2;
        EASCallUtils.call(this.f199a, IServiceManager.SERVICE_VEHICLE, "CarInfo", "setUsageVolume", MessageNano.toByteArray(usageVolumeInfo), null);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.carinfo.audio.InternalCarAudioManager
    public final boolean setZoneIdForUid(int i, int i2) {
        if (this.f199a == null) {
            return false;
        }
        ECARXCommon.IntMsgList intMsgList = new ECARXCommon.IntMsgList();
        intMsgList.value = new int[]{i, i2};
        return EASCallUtils.callBoolean(this.f199a, IServiceManager.SERVICE_VEHICLE, "CarInfo", "setZoneIdForUid", MessageNano.toByteArray(intMsgList), null);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.carinfo.audio.InternalCarAudioManager
    public final boolean clearZoneIdForUid(int i) {
        if (this.f199a == null) {
            return false;
        }
        ECARXCommon.IntMsg intMsg = new ECARXCommon.IntMsg();
        intMsg.value = i;
        return EASCallUtils.callBoolean(this.f199a, IServiceManager.SERVICE_VEHICLE, "CarInfo", "clearZoneIdForUid", MessageNano.toByteArray(intMsg), null);
    }
}
