package com.ecarx.eas.sdk.vehicle.g;

import com.ecarx.eas.framework.sdk.EASCallUtils;
import com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService;
import com.ecarx.eas.sdk.IServiceManager;
import com.ecarx.eas.sdk.vehicle.api.newenergy.IEptState;

/* JADX INFO: loaded from: classes2.dex */
public final class c implements IEptState {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private IEASFrameworkService f276a;

    public c(IEASFrameworkService iEASFrameworkService) {
        this.f276a = iEASFrameworkService;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IEptState
    public final int getEptActualMode() {
        IEASFrameworkService iEASFrameworkService = this.f276a;
        if (iEASFrameworkService == null) {
            return -1;
        }
        return EASCallUtils.callInt(iEASFrameworkService, IServiceManager.SERVICE_VEHICLE, "NewEnergyEpt", "getEptActualMode", null, null);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IEptState
    public final int getSOCPointLevel() {
        IEASFrameworkService iEASFrameworkService = this.f276a;
        if (iEASFrameworkService == null) {
            return -1;
        }
        return EASCallUtils.callInt(iEASFrameworkService, IServiceManager.SERVICE_VEHICLE, "NewEnergyEpt", "getSOCPointLevel", null, null);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IEptState
    public final boolean isAvasDisable() {
        IEASFrameworkService iEASFrameworkService = this.f276a;
        if (iEASFrameworkService == null) {
            return false;
        }
        return EASCallUtils.callBoolean(iEASFrameworkService, IServiceManager.SERVICE_VEHICLE, "NewEnergyEpt", "isAvasDisable", null, null);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IEptState
    public final int getAvasVolumeType() {
        IEASFrameworkService iEASFrameworkService = this.f276a;
        if (iEASFrameworkService == null) {
            return -1;
        }
        return EASCallUtils.callInt(iEASFrameworkService, IServiceManager.SERVICE_VEHICLE, "NewEnergyEpt", "getAvasVolumeType", null, null);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IEptState
    public final boolean isInfrequentChargingModeOn() {
        IEASFrameworkService iEASFrameworkService = this.f276a;
        if (iEASFrameworkService == null) {
            return false;
        }
        return EASCallUtils.callBoolean(iEASFrameworkService, IServiceManager.SERVICE_VEHICLE, "NewEnergyEpt", "isInfrequentChargingModeOn", null, null);
    }
}
