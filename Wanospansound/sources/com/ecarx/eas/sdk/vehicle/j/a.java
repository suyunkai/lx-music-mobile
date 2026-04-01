package com.ecarx.eas.sdk.vehicle.j;

import com.ecarx.eas.framework.sdk.EASCallUtils;
import com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService;
import com.ecarx.eas.sdk.IServiceManager;
import com.ecarx.eas.sdk.vehicle.api.tcu.ITcuState;
import com.ecarx.eas.sdk.vehicle.api.tcu.Tribool;

/* JADX INFO: loaded from: classes2.dex */
public final class a implements ITcuState {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private IEASFrameworkService f305a;

    public a(IEASFrameworkService iEASFrameworkService) {
        this.f305a = iEASFrameworkService;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.tcu.ITcuState
    public final int getGearSelectorPosition() {
        IEASFrameworkService iEASFrameworkService = this.f305a;
        if (iEASFrameworkService != null) {
            return 0;
        }
        return EASCallUtils.callInt(iEASFrameworkService, IServiceManager.SERVICE_VEHICLE, "Tcu", "getGearSelectorPosition", null, null);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.tcu.ITcuState
    public final int getGear() {
        IEASFrameworkService iEASFrameworkService = this.f305a;
        if (iEASFrameworkService == null) {
            return 0;
        }
        return EASCallUtils.callInt(iEASFrameworkService, IServiceManager.SERVICE_VEHICLE, "Tcu", "getGear", null, null);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.tcu.ITcuState
    public final Tribool isManualShiftMode() {
        IEASFrameworkService iEASFrameworkService = this.f305a;
        if (iEASFrameworkService == null) {
            return Tribool.indeterminate;
        }
        int iCallInt = EASCallUtils.callInt(iEASFrameworkService, IServiceManager.SERVICE_VEHICLE, "Tcu", "isManualShiftMode", null, null);
        if (iCallInt == 0) {
            return Tribool.indeterminate;
        }
        if (iCallInt == 1) {
            return Tribool.yes;
        }
        if (iCallInt == 2) {
            return Tribool.no;
        }
        return Tribool.indeterminate;
    }
}
