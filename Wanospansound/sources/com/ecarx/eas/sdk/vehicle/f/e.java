package com.ecarx.eas.sdk.vehicle.f;

import com.ecarx.eas.framework.sdk.common.internal.ClientType;
import com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService;
import com.ecarx.eas.framework.sdk.common.internal.safeparcel.ServiceVersionInfo;

/* JADX INFO: loaded from: classes2.dex */
class e implements com.ecarx.eas.sdk.vehicle.api.a<IEASFrameworkService> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final com.ecarx.eas.sdk.vehicle.api.a<IEASFrameworkService> f213a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final String f214b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected String f215c;

    @Override // com.ecarx.eas.sdk.vehicle.api.a
    public void a(ClientType clientType, ServiceVersionInfo serviceVersionInfo) {
    }

    e(com.ecarx.eas.sdk.vehicle.api.a aVar, String str, String str2) {
        this.f213a = aVar;
        this.f214b = str;
        this.f215c = str2;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.a
    public boolean a(ClientType clientType, int i) {
        com.ecarx.eas.sdk.vehicle.api.a<IEASFrameworkService> aVar = this.f213a;
        if (aVar == null) {
            return false;
        }
        return aVar.a(clientType, i);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.a
    /* JADX INFO: renamed from: a_, reason: merged with bridge method [inline-methods] */
    public IEASFrameworkService a() {
        com.ecarx.eas.sdk.vehicle.api.a<IEASFrameworkService> aVar = this.f213a;
        if (aVar == null) {
            return null;
        }
        return aVar.a();
    }
}
