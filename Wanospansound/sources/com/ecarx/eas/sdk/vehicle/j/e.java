package com.ecarx.eas.sdk.vehicle.j;

import android.os.RemoteException;
import com.ecarx.eas.sdk.vehicle.api.tcu.ITcuState;
import com.ecarx.eas.sdk.vehicle.api.tcu.Tribool;

/* JADX INFO: loaded from: classes2.dex */
public final class e implements ITcuState {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.ecarx.eas.sdk.vehicle.v3.b.f.c f318a;

    public e(com.ecarx.eas.sdk.vehicle.v3.b.f.c cVar) {
        this.f318a = cVar;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.tcu.ITcuState
    public final int getGearSelectorPosition() {
        try {
            return this.f318a.a();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.tcu.ITcuState
    public final int getGear() {
        try {
            return this.f318a.b();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.tcu.ITcuState
    public final Tribool isManualShiftMode() {
        int iC;
        try {
            iC = this.f318a.c();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if (iC == 0) {
            return Tribool.indeterminate;
        }
        if (iC == 1) {
            return Tribool.yes;
        }
        if (iC == 2) {
            return Tribool.no;
        }
        return Tribool.indeterminate;
    }
}
