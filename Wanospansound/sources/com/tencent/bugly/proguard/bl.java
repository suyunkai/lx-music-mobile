package com.tencent.bugly.proguard;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class bl extends m implements Cloneable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f562a = "";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f563b = "";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f564c = "";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f565d = "";
    public String e = "";

    @Override // com.tencent.bugly.proguard.m
    public final void a(StringBuilder sb, int i) {
    }

    @Override // com.tencent.bugly.proguard.m
    public final void a(l lVar) {
        lVar.a(this.f562a, 0);
        String str = this.f563b;
        if (str != null) {
            lVar.a(str, 1);
        }
        String str2 = this.f564c;
        if (str2 != null) {
            lVar.a(str2, 2);
        }
        String str3 = this.f565d;
        if (str3 != null) {
            lVar.a(str3, 3);
        }
        String str4 = this.e;
        if (str4 != null) {
            lVar.a(str4, 4);
        }
    }

    @Override // com.tencent.bugly.proguard.m
    public final void a(k kVar) {
        this.f562a = kVar.b(0, true);
        this.f563b = kVar.b(1, false);
        this.f564c = kVar.b(2, false);
        this.f565d = kVar.b(3, false);
        this.e = kVar.b(4, false);
    }
}
