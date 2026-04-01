package com.ecarx.eas.framework.sdk.proto;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final int f53a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final byte[] f54b;

    b(int i, byte[] bArr) {
        this.f53a = i;
        this.f54b = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.f53a == bVar.f53a && Arrays.equals(this.f54b, bVar.f54b);
    }

    public final int hashCode() {
        return ((this.f53a + 527) * 31) + Arrays.hashCode(this.f54b);
    }
}
