package com.ecarx.eas.sdk.device;

import com.ecarx.eas.framework.sdk.common.internal.ClientType;

/* JADX INFO: loaded from: classes2.dex */
abstract class d {

    /* JADX INFO: renamed from: com.ecarx.eas.sdk.device.d$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f71a;

        static {
            int[] iArr = new int[ClientType.values().length];
            f71a = iArr;
            try {
                iArr[ClientType.OpenAPI.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }
}
