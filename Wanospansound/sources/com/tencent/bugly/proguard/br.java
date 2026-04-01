package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class br extends m {
    static byte[] i = {0};
    static Map<String, String> j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte f583a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f584b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f585c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f586d = "";
    public long e = 0;
    public String f = "";
    public String g = "";
    public Map<String, String> h = null;

    @Override // com.tencent.bugly.proguard.m
    public final void a(l lVar) {
        lVar.a(this.f583a, 0);
        lVar.a(this.f584b, 1);
        byte[] bArr = this.f585c;
        if (bArr != null) {
            lVar.a(bArr, 2);
        }
        String str = this.f586d;
        if (str != null) {
            lVar.a(str, 3);
        }
        lVar.a(this.e, 4);
        String str2 = this.f;
        if (str2 != null) {
            lVar.a(str2, 5);
        }
        String str3 = this.g;
        if (str3 != null) {
            lVar.a(str3, 6);
        }
        Map<String, String> map = this.h;
        if (map != null) {
            lVar.a((Map) map, 7);
        }
    }

    static {
        HashMap map = new HashMap();
        j = map;
        map.put("", "");
    }

    @Override // com.tencent.bugly.proguard.m
    public final void a(k kVar) {
        this.f583a = kVar.a(this.f583a, 0, true);
        this.f584b = kVar.a(this.f584b, 1, true);
        this.f585c = kVar.c(2, false);
        this.f586d = kVar.b(3, false);
        this.e = kVar.a(this.e, 4, false);
        this.f = kVar.b(5, false);
        this.g = kVar.b(6, false);
        this.h = (Map) kVar.a(j, 7, false);
    }
}
