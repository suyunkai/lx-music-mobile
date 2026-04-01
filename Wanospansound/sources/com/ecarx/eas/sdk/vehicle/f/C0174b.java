package com.ecarx.eas.sdk.vehicle.f;

import com.ecarx.eas.sdk.vehicle.api.interfaces.ILeftTurnLight;
import com.ecarx.eas.sdk.vehicle.api.interfaces.ILight;
import com.ecarx.eas.sdk.vehicle.f.c;

/* JADX INFO: renamed from: com.ecarx.eas.sdk.vehicle.f.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0174b extends c.a implements ILeftTurnLight {
    @Override // com.ecarx.eas.sdk.vehicle.f.c.a
    protected final int b() {
        return 553980160;
    }

    @Override // com.ecarx.eas.sdk.vehicle.f.c.a, com.ecarx.eas.sdk.vehicle.able.ICallbackable
    public final /* synthetic */ boolean registerCallback(Object obj) throws UnsupportedOperationException {
        return super.registerCallback((ILight.ILightCallback) obj);
    }

    @Override // com.ecarx.eas.sdk.vehicle.f.c.a, com.ecarx.eas.sdk.vehicle.able.ICallbackable
    public final /* synthetic */ boolean unregisterCallback(Object obj) throws UnsupportedOperationException {
        return super.unregisterCallback((ILight.ILightCallback) obj);
    }

    public C0174b(com.ecarx.eas.sdk.vehicle.api.a aVar, String str, String str2) {
        super(aVar, str, str2);
    }

    @Override // com.ecarx.eas.sdk.vehicle.f.c.a
    protected final String a(String str) {
        if ("registerCallback".equals(str)) {
            return "registerLeftTurnLightCallback";
        }
        if ("unregisterCallback".equals(str)) {
            return "unregisterLeftTurnLightCallback";
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
