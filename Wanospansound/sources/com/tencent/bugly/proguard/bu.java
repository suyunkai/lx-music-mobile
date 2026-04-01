package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class bu extends m {
    static Map<String, String> i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f593a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public byte f594b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f595c = "";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f596d = "";
    public String e = "";
    public Map<String, String> f = null;
    public String g = "";
    public boolean h = true;

    @Override // com.tencent.bugly.proguard.m
    public final void a(l lVar) {
        lVar.a(this.f593a, 0);
        lVar.a(this.f594b, 1);
        String str = this.f595c;
        if (str != null) {
            lVar.a(str, 2);
        }
        String str2 = this.f596d;
        if (str2 != null) {
            lVar.a(str2, 3);
        }
        String str3 = this.e;
        if (str3 != null) {
            lVar.a(str3, 4);
        }
        Map<String, String> map = this.f;
        if (map != null) {
            lVar.a((Map) map, 5);
        }
        String str4 = this.g;
        if (str4 != null) {
            lVar.a(str4, 6);
        }
        lVar.a(this.h, 7);
    }

    static {
        HashMap map = new HashMap();
        i = map;
        map.put("", "");
    }

    @Override // com.tencent.bugly.proguard.m
    public final void a(k kVar) {
        this.f593a = kVar.a(this.f593a, 0, true);
        this.f594b = kVar.a(this.f594b, 1, true);
        this.f595c = kVar.b(2, false);
        this.f596d = kVar.b(3, false);
        this.e = kVar.b(4, false);
        this.f = (Map) kVar.a(i, 5, false);
        this.g = kVar.b(6, false);
        this.h = kVar.a(7, false);
    }
}
