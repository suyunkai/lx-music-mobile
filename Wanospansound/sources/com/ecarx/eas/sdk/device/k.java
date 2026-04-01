package com.ecarx.eas.sdk.device;

import com.ecarx.eas.framework.sdk.common.internal.ClientType;
import com.ecarx.eas.sdk.device.api.IDrivingJoyLimit;

/* JADX INFO: loaded from: classes2.dex */
abstract class k {

    /* JADX INFO: renamed from: com.ecarx.eas.sdk.device.k$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f92a;

        static {
            int[] iArr = new int[ClientType.values().length];
            f92a = iArr;
            try {
                iArr[ClientType.OpenAPI.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public static IDrivingJoyLimit a(ClientType clientType) {
        if (AnonymousClass1.f92a[clientType.ordinal()] == 1) {
            return j.a();
        }
        return i.a();
    }
}
