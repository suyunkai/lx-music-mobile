package com.ecarx.eas.sdk.vehicle.f;

import com.ecarx.eas.framework.sdk.common.internal.ClientType;
import com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService;
import com.ecarx.eas.framework.sdk.common.internal.safeparcel.ServiceVersionInfo;
import com.ecarx.eas.sdk.vehicle.api.interfaces.ILight;
import com.ecarx.eas.sdk.vehicle.api.interfaces.IVehicleManager;
import com.ecarx.eas.sdk.vehicle.api.interfaces.IWindow;

/* JADX INFO: loaded from: classes2.dex */
public final class h extends e implements IVehicleManager {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private ILight f216d;
    private IWindow e;

    @Override // com.ecarx.eas.sdk.vehicle.f.e, com.ecarx.eas.sdk.vehicle.api.a
    public final /* bridge */ /* synthetic */ void a(ClientType clientType, ServiceVersionInfo serviceVersionInfo) {
        super.a(clientType, serviceVersionInfo);
    }

    @Override // com.ecarx.eas.sdk.vehicle.f.e, com.ecarx.eas.sdk.vehicle.api.a
    public final /* bridge */ /* synthetic */ boolean a(ClientType clientType, int i) {
        return super.a(clientType, i);
    }

    @Override // com.ecarx.eas.sdk.vehicle.f.e
    /* JADX INFO: renamed from: a_ */
    public final /* bridge */ /* synthetic */ IEASFrameworkService a() {
        return super.a();
    }

    public h(com.ecarx.eas.sdk.vehicle.api.a aVar, String str, String str2) {
        super(aVar, str, str2);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.interfaces.IVehicleManager
    public final ILight getLight() {
        if (!super.a(ClientType.EASFramework, 7)) {
            return null;
        }
        ILight iLight = this.f216d;
        if (iLight != null) {
            return iLight;
        }
        c cVar = new c(this.f213a, this.f214b, this.f215c);
        this.f216d = cVar;
        return cVar;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.interfaces.IVehicleManager
    public final IWindow getWindow() {
        if (!super.a(ClientType.EASFramework, 7)) {
            return null;
        }
        IWindow iWindow = this.e;
        if (iWindow != null) {
            return iWindow;
        }
        i iVar = new i(this.f213a, this.f214b, this.f215c);
        this.e = iVar;
        return iVar;
    }
}
