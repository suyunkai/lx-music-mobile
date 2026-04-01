package com.ecarx.eas.sdk.vehicle.f;

import android.os.RemoteException;
import android.util.Log;
import com.ecarx.eas.sdk.vehicle.api.newenergy.ICharge;
import com.ecarx.eas.sdk.vehicle.api.newenergy.IEpt;
import com.ecarx.eas.sdk.vehicle.api.newenergy.INewEnergy;
import com.ecarx.eas.sdk.vehicle.api.newenergy.IPHEV;
import com.ecarx.eas.sdk.vehicle.v3.a.b;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public abstract class y implements INewEnergy {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected com.ecarx.eas.sdk.vehicle.v3.a.j f261a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected com.ecarx.eas.sdk.vehicle.g.h f262b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected com.ecarx.eas.sdk.vehicle.g.b f263c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected com.ecarx.eas.sdk.vehicle.g.j f264d;
    private final String e = getClass().getSimpleName();
    private HashMap<INewEnergy.IBatteryStatusObserver, com.ecarx.eas.sdk.vehicle.v3.a.b> f = new HashMap<>();

    public final void a(com.ecarx.eas.sdk.vehicle.v3.e eVar) {
        if (eVar != null) {
            try {
                this.f261a = eVar.b();
                this.f262b = new com.ecarx.eas.sdk.vehicle.g.h(this.f261a.c());
                this.f263c = new com.ecarx.eas.sdk.vehicle.g.b(this.f261a.d());
                this.f264d = new com.ecarx.eas.sdk.vehicle.g.j(this.f261a.e());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.INewEnergy
    public int getNewEnergyType() {
        try {
            return this.f261a.a();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.INewEnergy
    public int getCurrentBatteryStatus() {
        try {
            return this.f261a.b();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.INewEnergy
    public boolean attachBatteryStatusObserver(INewEnergy.IBatteryStatusObserver iBatteryStatusObserver) {
        if (this.f.get(iBatteryStatusObserver) == null) {
            a aVar = new a(iBatteryStatusObserver);
            try {
                boolean zA = this.f261a.a(aVar);
                if (zA) {
                    this.f.put(iBatteryStatusObserver, aVar);
                }
                return zA;
            } catch (RemoteException e) {
                e.printStackTrace();
                return false;
            }
        }
        Log.d(this.e, "Has attach IBatteryStatusObserver");
        return true;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.INewEnergy
    public void detachBatteryStatusObserver(INewEnergy.IBatteryStatusObserver iBatteryStatusObserver) {
        com.ecarx.eas.sdk.vehicle.v3.a.b bVar = this.f.get(iBatteryStatusObserver);
        if (bVar != null) {
            try {
                this.f261a.b(bVar);
                this.f.remove(iBatteryStatusObserver);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.INewEnergy
    public IEpt getIEpt() {
        return this.f262b;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.INewEnergy
    public ICharge getChargeManager() {
        return this.f263c;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.INewEnergy
    public IPHEV getPHEV() {
        return this.f264d;
    }

    static class a extends b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private INewEnergy.IBatteryStatusObserver f265a;

        public a(INewEnergy.IBatteryStatusObserver iBatteryStatusObserver) {
            this.f265a = iBatteryStatusObserver;
        }

        @Override // com.ecarx.eas.sdk.vehicle.v3.a.b
        public final void a(int i) throws RemoteException {
            this.f265a.onBatteryReminder(i);
        }
    }
}
