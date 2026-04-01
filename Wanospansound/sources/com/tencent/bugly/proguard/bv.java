package com.tencent.bugly.proguard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class bv extends m implements Cloneable {
    static ArrayList<bu> f;
    static Map<String, String> g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte f597a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f598b = "";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f599c = "";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ArrayList<bu> f600d = null;
    public Map<String, String> e = null;

    @Override // com.tencent.bugly.proguard.m
    public final void a(StringBuilder sb, int i) {
    }

    @Override // com.tencent.bugly.proguard.m
    public final void a(l lVar) {
        lVar.a(this.f597a, 0);
        String str = this.f598b;
        if (str != null) {
            lVar.a(str, 1);
        }
        String str2 = this.f599c;
        if (str2 != null) {
            lVar.a(str2, 2);
        }
        ArrayList<bu> arrayList = this.f600d;
        if (arrayList != null) {
            lVar.a((Collection) arrayList, 3);
        }
        Map<String, String> map = this.e;
        if (map != null) {
            lVar.a((Map) map, 4);
        }
    }

    @Override // com.tencent.bugly.proguard.m
    public final void a(k kVar) {
        this.f597a = kVar.a(this.f597a, 0, true);
        this.f598b = kVar.b(1, false);
        this.f599c = kVar.b(2, false);
        if (f == null) {
            f = new ArrayList<>();
            f.add(new bu());
        }
        this.f600d = (ArrayList) kVar.a(f, 3, false);
        if (g == null) {
            HashMap map = new HashMap();
            g = map;
            map.put("", "");
        }
        this.e = (Map) kVar.a(g, 4, false);
    }
}
