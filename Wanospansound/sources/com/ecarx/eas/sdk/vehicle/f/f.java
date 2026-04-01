package com.ecarx.eas.sdk.vehicle.f;

import com.ecarx.eas.sdk.vehicle.api.interfaces.ILight;
import com.ecarx.eas.sdk.vehicle.api.interfaces.IStopLight;
import com.ecarx.eas.sdk.vehicle.f.c;

/* JADX INFO: loaded from: classes2.dex */
public final class f extends c.a implements IStopLight {
    @Override // com.ecarx.eas.sdk.vehicle.f.c.a
    protected final int b() {
        return 553979136;
    }

    @Override // com.ecarx.eas.sdk.vehicle.f.c.a, com.ecarx.eas.sdk.vehicle.able.ICallbackable
    public final /* synthetic */ boolean registerCallback(Object obj) throws UnsupportedOperationException {
        return super.registerCallback((ILight.ILightCallback) obj);
    }

    @Override // com.ecarx.eas.sdk.vehicle.f.c.a, com.ecarx.eas.sdk.vehicle.able.ICallbackable
    public final /* synthetic */ boolean unregisterCallback(Object obj) throws UnsupportedOperationException {
        return super.unregisterCallback((ILight.ILightCallback) obj);
    }

    public f(com.ecarx.eas.sdk.vehicle.api.a aVar, String str, String str2) {
        super(aVar, str, str2);
    }

    @Override // com.ecarx.eas.sdk.vehicle.f.c.a
    protected final String a(String str) {
        if ("registerCallback".equals(str)) {
            return "registerStopLightCallback";
        }
        if ("unregisterCallback".equals(str)) {
            return "unregisterStopLightCallback";
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
