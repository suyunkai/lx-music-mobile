package com.ecarx.eas.sdk.vehicle.j;

import android.os.RemoteException;
import android.util.Log;
import com.ecarx.eas.framework.sdk.EASCallUtils;
import com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService;
import com.ecarx.eas.sdk.IServiceManager;
import com.ecarx.eas.sdk.vehicle.api.tcu.ITcu;
import com.ecarx.eas.sdk.vehicle.api.tcu.ITcuCallback;
import com.ecarx.eas.sdk.vehicle.api.tcu.ITcuState;
import com.ecarx.eas.sdk.vehicle.api.tcu.Tribool;
import com.ecarx.eas.sdk.vehicle.v3.b.f.b;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public abstract class c implements ITcu {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected IEASFrameworkService f306a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f307b = getClass().getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Map<ITcuCallback, a> f308c = new HashMap();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private com.ecarx.eas.sdk.vehicle.j.a f309d;

    public final void a(IEASFrameworkService iEASFrameworkService) {
        this.f306a = iEASFrameworkService;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.tcu.ITcu
    public ITcuState getTcuState() {
        if (this.f309d == null) {
            this.f309d = new com.ecarx.eas.sdk.vehicle.j.a(this.f306a);
        }
        return this.f309d;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.tcu.ITcu
    public void setTcuCallback(ITcuCallback iTcuCallback) {
        if (this.f308c.get(iTcuCallback) == null) {
            a aVar = new a(iTcuCallback, this.f306a);
            EASCallUtils.call(this.f306a, IServiceManager.SERVICE_VEHICLE, "Tcu", "setTcuCallback", null, aVar);
            this.f308c.put(iTcuCallback, aVar);
            Log.d(this.f307b, "Has attach TcuCallback");
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.tcu.ITcu
    public void unsetTcuCallback(ITcuCallback iTcuCallback) {
        a aVar = this.f308c.get(iTcuCallback);
        if (aVar != null) {
            EASCallUtils.call(this.f306a, IServiceManager.SERVICE_VEHICLE, "Tcu", "unsetTcuCallback", null, aVar);
            this.f308c.remove(iTcuCallback);
        }
    }

    static class a extends b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private ITcuCallback f310a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private IEASFrameworkService f311b;

        public a(ITcuCallback iTcuCallback, IEASFrameworkService iEASFrameworkService) {
            this.f310a = iTcuCallback;
            this.f311b = iEASFrameworkService;
        }

        @Override // com.ecarx.eas.sdk.vehicle.v3.b.f.b
        public final void a(com.ecarx.eas.sdk.vehicle.v3.b.f.c cVar) throws RemoteException {
            this.f310a.onTcuChanged(new b(this.f311b));
        }
    }

    static class b implements ITcuState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private IEASFrameworkService f312a;

        public b(IEASFrameworkService iEASFrameworkService) {
            this.f312a = iEASFrameworkService;
        }

        @Override // com.ecarx.eas.sdk.vehicle.api.tcu.ITcuState
        public final int getGearSelectorPosition() {
            IEASFrameworkService iEASFrameworkService = this.f312a;
            if (iEASFrameworkService != null) {
                return 0;
            }
            return EASCallUtils.callInt(iEASFrameworkService, IServiceManager.SERVICE_VEHICLE, "Tcu", "getGearSelectorPosition", null, null);
        }

        @Override // com.ecarx.eas.sdk.vehicle.api.tcu.ITcuState
        public final int getGear() {
            IEASFrameworkService iEASFrameworkService = this.f312a;
            if (iEASFrameworkService == null) {
                return 0;
            }
            return EASCallUtils.callInt(iEASFrameworkService, IServiceManager.SERVICE_VEHICLE, "Tcu", "getGear", null, null);
        }

        @Override // com.ecarx.eas.sdk.vehicle.api.tcu.ITcuState
        public final Tribool isManualShiftMode() {
            IEASFrameworkService iEASFrameworkService = this.f312a;
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
}
