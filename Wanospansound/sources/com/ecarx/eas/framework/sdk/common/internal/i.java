package com.ecarx.eas.framework.sdk.common.internal;

/* JADX INFO: loaded from: classes2.dex */
final class i {
    public static <T> T a(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }
}
