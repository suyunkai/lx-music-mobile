package com.ecarx.eas.sdk.vehicle.f;

import android.os.RemoteException;
import com.ecarx.eas.framework.sdk.EASCallUtils;
import com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService;
import com.ecarx.eas.sdk.IServiceManager;
import com.ecarx.eas.sdk.vehicle.api.newenergy.ICharge;
import com.ecarx.eas.sdk.vehicle.api.newenergy.IEpt;
import com.ecarx.eas.sdk.vehicle.api.newenergy.INewEnergy;
import com.ecarx.eas.sdk.vehicle.api.newenergy.IPHEV;
import com.ecarx.eas.sdk.vehicle.v3.a.b;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public abstract class w implements INewEnergy {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected IEASFrameworkService f252a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected com.ecarx.eas.sdk.vehicle.g.f f253b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected com.ecarx.eas.sdk.vehicle.g.d f254c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private HashMap<INewEnergy.IBatteryStatusObserver, a> f255d = new HashMap<>();

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.INewEnergy
    public ICharge getChargeManager() {
        return null;
    }

    public final void a(IEASFrameworkService iEASFrameworkService) {
        this.f252a = iEASFrameworkService;
        this.f253b = new com.ecarx.eas.sdk.vehicle.g.f(this.f252a);
        this.f254c = new com.ecarx.eas.sdk.vehicle.g.d(this.f252a);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.INewEnergy
    public int getNewEnergyType() {
        IEASFrameworkService iEASFrameworkService = this.f252a;
        if (iEASFrameworkService == null) {
            return 0;
        }
        return EASCallUtils.callInt(iEASFrameworkService, IServiceManager.SERVICE_VEHICLE, "NewEnergy", "getNewEnergyType", null, null);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.INewEnergy
    public int getCurrentBatteryStatus() {
        IEASFrameworkService iEASFrameworkService = this.f252a;
        if (iEASFrameworkService == null) {
            return 0;
        }
        return EASCallUtils.callInt(iEASFrameworkService, IServiceManager.SERVICE_VEHICLE, "NewEnergy", "getCurrentBatteryStatus", null, null);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.INewEnergy
    public boolean attachBatteryStatusObserver(INewEnergy.IBatteryStatusObserver iBatteryStatusObserver) {
        boolean zCallBoolean = false;
        if (this.f252a != null && iBatteryStatusObserver != null && this.f255d.get(iBatteryStatusObserver) == null) {
            a aVar = new a(iBatteryStatusObserver);
            zCallBoolean = EASCallUtils.callBoolean(this.f252a, IServiceManager.SERVICE_VEHICLE, "NewEnergy", "attachBatteryStatusObserver", null, aVar);
            if (zCallBoolean) {
                this.f255d.put(iBatteryStatusObserver, aVar);
            }
        }
        return zCallBoolean;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.INewEnergy
    public void detachBatteryStatusObserver(INewEnergy.IBatteryStatusObserver iBatteryStatusObserver) {
        a aVar = this.f255d.get(iBatteryStatusObserver);
        if (aVar != null) {
            EASCallUtils.call(this.f252a, IServiceManager.SERVICE_VEHICLE, "NewEnergy", "detachBatteryStatusObserver", null, aVar);
            this.f255d.remove(iBatteryStatusObserver);
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.INewEnergy
    public IEpt getIEpt() {
        return this.f254c;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.INewEnergy
    public IPHEV getPHEV() {
        return this.f253b;
    }

    static class a extends b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private INewEnergy.IBatteryStatusObserver f256a;

        public a(INewEnergy.IBatteryStatusObserver iBatteryStatusObserver) {
            this.f256a = iBatteryStatusObserver;
        }

        @Override // com.ecarx.eas.sdk.vehicle.v3.a.b
        public final void a(int i) throws RemoteException {
            this.f256a.onBatteryReminder(i);
        }
    }
}
