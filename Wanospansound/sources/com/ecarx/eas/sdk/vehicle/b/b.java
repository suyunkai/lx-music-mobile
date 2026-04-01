package com.ecarx.eas.sdk.vehicle.b;

import android.os.RemoteException;
import com.ecarx.eas.sdk.vehicle.api.carinfo.audio.CarAudioManager;
import com.ecarx.eas.sdk.vehicle.f.m;

/* JADX INFO: loaded from: classes2.dex */
public final class b extends m {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private volatile CarAudioManager f191b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private volatile e f192c;

    public b(com.ecarx.eas.sdk.vehicle.v3.a aVar) {
        this.f225a = aVar;
    }

    @Override // com.ecarx.eas.sdk.vehicle.f.m
    public final void a(com.ecarx.eas.sdk.vehicle.v3.e eVar) {
        if (eVar != null) {
            try {
                this.f225a = eVar.c();
                synchronized (this) {
                    if (this.f191b == null) {
                        return;
                    }
                    this.f192c.a(this.f225a.a());
                    this.f191b.update(this.f192c);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.carinfo.ICarInfo
    public final CarAudioManager getCarAudioManager() {
        if (this.f191b == null) {
            synchronized (this) {
                if (this.f191b == null) {
                    try {
                        this.f192c = new e(this.f225a.a());
                        this.f191b = new a(this.f192c);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return this.f191b;
    }
}
