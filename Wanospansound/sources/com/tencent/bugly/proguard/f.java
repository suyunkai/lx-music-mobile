package com.tencent.bugly.proguard;

import com.google.common.base.Ascii;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final char[] f606b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f605a = new byte[0];

    public static String a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            byte b2 = bArr[i];
            int i2 = i * 2;
            char[] cArr2 = f606b;
            cArr[i2 + 1] = cArr2[b2 & Ascii.SI];
            cArr[i2 + 0] = cArr2[((byte) (b2 >>> 4)) & Ascii.SI];
        }
        return new String(cArr);
    }
}
