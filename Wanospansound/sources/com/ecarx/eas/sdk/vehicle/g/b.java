package com.ecarx.eas.sdk.vehicle.g;

import android.os.RemoteException;
import android.util.Log;
import com.ecarx.eas.sdk.vehicle.api.newenergy.ICharge;
import com.ecarx.eas.sdk.vehicle.api.newenergy.IChargeState;
import com.ecarx.eas.sdk.vehicle.v3.a.e;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class b implements ICharge {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private com.ecarx.eas.sdk.vehicle.g.a f272b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private com.ecarx.eas.sdk.vehicle.v3.a.c f273c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f271a = "b";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private HashMap<ICharge.IChargeStateListener, com.ecarx.eas.sdk.vehicle.v3.a.e> f274d = new HashMap<>();

    public b(com.ecarx.eas.sdk.vehicle.v3.a.c cVar) {
        this.f273c = cVar;
        try {
            this.f272b = new com.ecarx.eas.sdk.vehicle.g.a(this.f273c.a());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.ICharge
    public IChargeState getChargeState() {
        return this.f272b;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.ICharge
    public boolean requestPreCharge(long j) {
        try {
            return this.f273c.a(j);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.ICharge
    public boolean requestPreCharge(long j, long j2) {
        try {
            return this.f273c.a(j, j2);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.ICharge
    public boolean requestPreChargePerDay(long j) {
        try {
            return this.f273c.a(j);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.ICharge
    public boolean requestPreChargePerDay(long j, long j2) {
        try {
            return this.f273c.a(j, j2);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.ICharge
    public boolean requestChargeImmediately() {
        try {
            return this.f273c.b();
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.ICharge
    public boolean requestChargeImmediately(long j) {
        try {
            return this.f273c.c(j);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.ICharge
    public boolean isSupportPreChargePerDay() {
        try {
            return this.f273c.c();
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.ICharge
    public void cancelPreCharge() {
        try {
            this.f273c.d();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.ICharge
    public long getSupportMaxPreChargeDelay() {
        try {
            return this.f273c.e();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0L;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.ICharge
    public long getSupportMinPreChargeDelay() {
        try {
            return this.f273c.f();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0L;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.ICharge
    public long getSupportMaxPreChargeDuration() {
        try {
            return this.f273c.g();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0L;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.ICharge
    public long getSupportMinPreChargeDuration() {
        try {
            return this.f273c.h();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0L;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.ICharge
    public void registerStateListener(ICharge.IChargeStateListener iChargeStateListener) {
        if (this.f274d.get(iChargeStateListener) == null) {
            a aVar = new a(iChargeStateListener);
            try {
                this.f273c.a(aVar);
                this.f274d.put(iChargeStateListener, aVar);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.d(this.f271a, "Has register IChargeStateListener");
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.ICharge
    public void unregisterStateListener(ICharge.IChargeStateListener iChargeStateListener) {
        com.ecarx.eas.sdk.vehicle.v3.a.e eVar = this.f274d.get(iChargeStateListener);
        if (eVar != null) {
            try {
                this.f273c.b(eVar);
                this.f274d.remove(iChargeStateListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    static class a extends e.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private ICharge.IChargeStateListener f275a;

        public a(ICharge.IChargeStateListener iChargeStateListener) {
            this.f275a = iChargeStateListener;
        }

        @Override // com.ecarx.eas.sdk.vehicle.v3.a.e
        public final void a(com.ecarx.eas.sdk.vehicle.v3.a.d dVar) throws RemoteException {
            this.f275a.onChargeStateChanged(new com.ecarx.eas.sdk.vehicle.g.a(dVar));
        }

        @Override // com.ecarx.eas.sdk.vehicle.v3.a.e
        public final void a(int i) throws RemoteException {
            this.f275a.onPreChargeStateChanged(i);
        }

        @Override // com.ecarx.eas.sdk.vehicle.v3.a.e
        public final void b(int i) throws RemoteException {
            this.f275a.onPlugStateChanged(i);
        }
    }
}
