package com.ecarx.eas.sdk.vehicle.f;

import com.ecarx.eas.sdk.vehicle.api.interfaces.IDippedBeamLight;
import com.ecarx.eas.sdk.vehicle.api.interfaces.ILight;
import com.ecarx.eas.sdk.vehicle.f.c;

/* JADX INFO: renamed from: com.ecarx.eas.sdk.vehicle.f.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0173a extends c.a implements IDippedBeamLight {
    @Override // com.ecarx.eas.sdk.vehicle.f.c.a
    protected final int b() {
        return 553976064;
    }

    @Override // com.ecarx.eas.sdk.vehicle.f.c.a, com.ecarx.eas.sdk.vehicle.able.ICallbackable
    public final /* synthetic */ boolean registerCallback(Object obj) throws UnsupportedOperationException {
        return super.registerCallback((ILight.ILightCallback) obj);
    }

    @Override // com.ecarx.eas.sdk.vehicle.f.c.a, com.ecarx.eas.sdk.vehicle.able.ICallbackable
    public final /* synthetic */ boolean unregisterCallback(Object obj) throws UnsupportedOperationException {
        return super.unregisterCallback((ILight.ILightCallback) obj);
    }

    public C0173a(com.ecarx.eas.sdk.vehicle.api.a aVar, String str, String str2) {
        super(aVar, str, str2);
    }

    @Override // com.ecarx.eas.sdk.vehicle.f.c.a
    protected final String a(String str) {
        if ("registerCallback".equals(str)) {
            return "registerDippedBeamLightCallback";
        }
        if ("unregisterCallback".equals(str)) {
            return "unregisterDippedBeamLightCallback";
        }
        return null;
    }

    @Override // com.ecarx.eas.sdk.vehicle.f.c.a
    /* JADX INFO: renamed from: a */
    public final boolean registerCallback(ILight.ILightCallback iLightCallback) throws UnsupportedOperationException {
        return super.registerCallback(iLightCallback);
    }

    @Override // com.ecarx.eas.sdk.vehicle.f.c.a
    /* JADX INFO: renamed from: b */
    public final boolean unregisterCallback(ILight.ILightCallback iLightCallback) throws UnsupportedOperationException {
        return super.unregisterCallback(iLightCallback);
    }
}
