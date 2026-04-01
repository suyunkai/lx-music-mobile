package com.ecarx.eas.sdk.vehicle.g;

import android.os.RemoteException;
import com.ecarx.eas.sdk.vehicle.api.newenergy.IChargeState;

/* JADX INFO: loaded from: classes2.dex */
public final class a implements IChargeState {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.ecarx.eas.sdk.vehicle.v3.a.d f270a;

    public a(com.ecarx.eas.sdk.vehicle.v3.a.d dVar) {
        this.f270a = dVar;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IChargeState
    public final int getChargePlugState() {
        try {
            return this.f270a.a();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IChargeState
    public final int getPreChargeState() {
        try {
            return this.f270a.b();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IChargeState
    public final long getPreChargeDelayTime() {
        try {
            return this.f270a.c();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0L;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IChargeState
    public final long getPreChargeStartTime() {
        try {
            return this.f270a.d();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0L;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IChargeState
    public final long getPreChargeDuration() {
        try {
            return this.f270a.e();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0L;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IChargeState
    public final boolean isPreChargeOutofDate() {
        try {
            return this.f270a.f();
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IChargeState
    public final boolean isPreChargePerDay() {
        try {
            return this.f270a.g();
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }
}
