package com.tencent.bugly.proguard;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class bn extends m implements Cloneable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static byte[] f569d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte f570a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f571b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f572c;

    @Override // com.tencent.bugly.proguard.m
    public final void a(StringBuilder sb, int i) {
    }

    public bn() {
        this.f570a = (byte) 0;
        this.f571b = "";
        this.f572c = null;
    }

    public bn(byte b2, String str, byte[] bArr) {
        this.f570a = b2;
        this.f571b = str;
        this.f572c = bArr;
    }

    @Override // com.tencent.bugly.proguard.m
    public final void a(l lVar) {
        lVar.a(this.f570a, 0);
        lVar.a(this.f571b, 1);
        byte[] bArr = this.f572c;
        if (bArr != null) {
            lVar.a(bArr, 2);
        }
    }

    @Override // com.tencent.bugly.proguard.m
    public final void a(k kVar) {
        this.f570a = kVar.a(this.f570a, 0, true);
        this.f571b = kVar.b(1, true);
        if (f569d == null) {
            f569d = new byte[]{0};
        }
        this.f572c = kVar.c(2, false);
    }
}
