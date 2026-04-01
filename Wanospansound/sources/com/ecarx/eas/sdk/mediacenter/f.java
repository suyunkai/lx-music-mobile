package com.ecarx.eas.sdk.mediacenter;

import com.ecarx.eas.framework.sdk.common.internal.ClientType;

/* JADX INFO: loaded from: classes2.dex */
public abstract class f {

    /* JADX INFO: renamed from: com.ecarx.eas.sdk.mediacenter.f$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f160a;

        static {
            int[] iArr = new int[ClientType.values().length];
            f160a = iArr;
            try {
                iArr[ClientType.OpenAPI.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public static InternalMediaCenterAPI a(ClientType clientType) {
        if (AnonymousClass1.f160a[clientType.ordinal()] == 1) {
            return e.a();
        }
        return a.a();
    }
}
