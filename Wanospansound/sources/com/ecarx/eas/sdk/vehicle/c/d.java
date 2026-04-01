package com.ecarx.eas.sdk.vehicle.c;

import android.os.RemoteException;
import com.ecarx.eas.sdk.vehicle.api.dashboard.IDashboard;
import com.ecarx.eas.sdk.vehicle.v3.b.c.b;

/* JADX INFO: loaded from: classes2.dex */
public final class d extends b.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private IDashboard.IVehicleSpeedObserver f204a;

    public d(IDashboard.IVehicleSpeedObserver iVehicleSpeedObserver) {
        this.f204a = iVehicleSpeedObserver;
    }

    @Override // com.ecarx.eas.sdk.vehicle.v3.b.c.b
    public final void a(int i) throws RemoteException {
        this.f204a.onVehicleSpeedChanged(i);
    }

    @Override // com.ecarx.eas.sdk.vehicle.v3.b.c.b
    public final void a(double d2) throws RemoteException {
        this.f204a.onVehicleSpeedChanged(d2);
    }

    @Override // com.ecarx.eas.sdk.vehicle.v3.b.c.b
    public final void a(double d2, int i) throws RemoteException {
        this.f204a.onVehicleSpeedChanged(d2, i);
    }
}
