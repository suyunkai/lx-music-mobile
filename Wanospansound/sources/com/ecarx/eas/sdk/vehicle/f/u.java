package com.ecarx.eas.sdk.vehicle.f;

import android.util.Log;
import com.ecarx.eas.framework.sdk.EASCallUtils;
import com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService;
import com.ecarx.eas.sdk.IServiceManager;
import com.ecarx.eas.sdk.vehicle.FunctionStatus;
import com.ecarx.eas.sdk.vehicle.api.IDriveMode;

/* JADX INFO: loaded from: classes2.dex */
public abstract class u implements IDriveMode {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected IEASFrameworkService f246a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f247b = getClass().getSimpleName();

    public final void a(IEASFrameworkService iEASFrameworkService) {
        this.f246a = iEASFrameworkService;
    }

    public final FunctionStatus a() {
        IEASFrameworkService iEASFrameworkService = this.f246a;
        if (iEASFrameworkService == null) {
            Log.e(this.f247b, "isDriveModeSupported  mService = null ");
            return FunctionStatus.error;
        }
        int iCallInt = EASCallUtils.callInt(iEASFrameworkService, IServiceManager.SERVICE_VEHICLE, "DriveMode", "isDriveModeSupported", null, null);
        if (iCallInt == 0) {
            return FunctionStatus.active;
        }
        if (iCallInt == 1) {
            return FunctionStatus.notactive;
        }
        if (iCallInt == 2) {
            return FunctionStatus.notavailable;
        }
        return FunctionStatus.error;
    }

    public final int b() {
        IEASFrameworkService iEASFrameworkService = this.f246a;
        if (iEASFrameworkService == null) {
            return Integer.MIN_VALUE;
        }
        return EASCallUtils.callInt(iEASFrameworkService, IServiceManager.SERVICE_VEHICLE, "DriveMode", "getCurrentDriveMode", null, null);
    }
}
