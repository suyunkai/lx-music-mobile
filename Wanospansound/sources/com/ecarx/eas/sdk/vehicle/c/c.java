package com.ecarx.eas.sdk.vehicle.c;

import android.os.RemoteException;
import com.ecarx.eas.sdk.vehicle.api.dashboard.IDashboard;
import com.ecarx.eas.sdk.vehicle.v3.b.c.a;

/* JADX INFO: loaded from: classes2.dex */
public final class c extends a.AbstractBinderC0046a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private IDashboard.IVehicleACCStatusObserver f203a;

    public c(IDashboard.IVehicleACCStatusObserver iVehicleACCStatusObserver) {
        this.f203a = iVehicleACCStatusObserver;
    }

    @Override // com.ecarx.eas.sdk.vehicle.v3.b.c.a
    public final void a(int i) throws RemoteException {
        this.f203a.onVehicleACCStatusChanged(i);
    }
}
