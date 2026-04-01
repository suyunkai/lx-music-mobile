package com.ecarx.eas.sdk.vehicle.b;

import com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService;
import com.ecarx.eas.sdk.vehicle.api.carinfo.audio.CarAudioManager;
import com.ecarx.eas.sdk.vehicle.f.r;

/* JADX INFO: loaded from: classes2.dex */
public final class c extends r {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private volatile a f193b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private volatile d f194c;

    public c(IEASFrameworkService iEASFrameworkService) {
        this.f238a = iEASFrameworkService;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.carinfo.ICarInfo
    public final CarAudioManager getCarAudioManager() {
        if (this.f193b == null) {
            synchronized (this) {
                if (this.f193b == null) {
                    this.f194c = new d(this.f238a);
                    this.f193b = new a(this.f194c);
                }
            }
        }
        return this.f193b;
    }

    @Override // com.ecarx.eas.sdk.vehicle.f.r
    public final void a(IEASFrameworkService iEASFrameworkService) {
        this.f238a = iEASFrameworkService;
        synchronized (this) {
            if (this.f193b != null) {
                this.f194c.a(this.f238a);
                this.f193b.update(this.f194c);
            }
        }
    }
}
