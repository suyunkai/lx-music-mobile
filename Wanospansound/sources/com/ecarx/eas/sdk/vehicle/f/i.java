package com.ecarx.eas.sdk.vehicle.f;

import com.ecarx.eas.framework.sdk.common.internal.ClientType;
import com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService;
import com.ecarx.eas.framework.sdk.common.internal.safeparcel.ServiceVersionInfo;
import com.ecarx.eas.sdk.vehicle.api.interfaces.IWindow;
import com.ecarx.eas.sdk.vehicle.api.interfaces.IWindowPos;

/* JADX INFO: loaded from: classes2.dex */
public final class i extends e implements IWindow {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private IWindowPos f217d;

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

    public i(com.ecarx.eas.sdk.vehicle.api.a aVar, String str, String str2) {
        super(aVar, str, str2);
        this.f215c = "Havc";
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.interfaces.IWindow
    public final IWindowPos getWindowPos() {
        IWindowPos iWindowPos = this.f217d;
        if (iWindowPos != null) {
            return iWindowPos;
        }
        j jVar = new j(this.f213a, this.f214b, this.f215c);
        this.f217d = jVar;
        return jVar;
    }
}
