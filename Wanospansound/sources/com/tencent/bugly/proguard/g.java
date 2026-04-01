package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class g extends m {
    static byte[] k = null;
    static Map<String, String> l = null;
    static final /* synthetic */ boolean m = true;
    public byte[] g;
    public Map<String, String> i;
    public Map<String, String> j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public short f607a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public byte f608b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f609c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f610d = 0;
    public String e = null;
    public String f = null;
    public int h = 0;

    public final boolean equals(Object obj) {
        g gVar = (g) obj;
        return n.a(1, (int) gVar.f607a) && n.a(1, (int) gVar.f608b) && n.a(1, gVar.f609c) && n.a(1, gVar.f610d) && n.a((Object) 1, (Object) gVar.e) && n.a((Object) 1, (Object) gVar.f) && n.a((Object) 1, (Object) gVar.g) && n.a(1, gVar.h) && n.a((Object) 1, (Object) gVar.i) && n.a((Object) 1, (Object) gVar.j);
    }

    public final Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            if (m) {
                return null;
            }
            throw new AssertionError();
        }
    }

    @Override // com.tencent.bugly.proguard.m
    public final void a(l lVar) {
        lVar.a(this.f607a, 1);
        lVar.a(this.f608b, 2);
        lVar.a(this.f609c, 3);
        lVar.a(this.f610d, 4);
        lVar.a(this.e, 5);
        lVar.a(this.f, 6);
        lVar.a(this.g, 7);
        lVar.a(this.h, 8);
        lVar.a((Map) this.i, 9);
        lVar.a((Map) this.j, 10);
    }

    @Override // com.tencent.bugly.proguard.m
    public final void a(k kVar) {
        try {
            this.f607a = kVar.a(this.f607a, 1, true);
            this.f608b = kVar.a(this.f608b, 2, true);
            this.f609c = kVar.a(this.f609c, 3, true);
            this.f610d = kVar.a(this.f610d, 4, true);
            this.e = kVar.b(5, true);
            this.f = kVar.b(6, true);
            if (k == null) {
                k = new byte[]{0};
            }
            this.g = kVar.c(7, true);
            this.h = kVar.a(this.h, 8, true);
            if (l == null) {
                HashMap map = new HashMap();
                l = map;
                map.put("", "");
            }
            this.i = (Map) kVar.a(l, 9, true);
            if (l == null) {
                HashMap map2 = new HashMap();
                l = map2;
                map2.put("", "");
            }
            this.j = (Map) kVar.a(l, 10, true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("RequestPacket decode error " + f.a(this.g));
            throw new RuntimeException(e);
        }
    }

    @Override // com.tencent.bugly.proguard.m
    public final void a(StringBuilder sb, int i) {
        i iVar = new i(sb, i);
        iVar.a(this.f607a, "iVersion");
        iVar.a(this.f608b, "cPacketType");
        iVar.a(this.f609c, "iMessageType");
        iVar.a(this.f610d, "iRequestId");
        iVar.a(this.e, "sServantName");
        iVar.a(this.f, "sFuncName");
        iVar.a(this.g, "sBuffer");
        iVar.a(this.h, "iTimeout");
        iVar.a((Map) this.i, "context");
        iVar.a((Map) this.j, "status");
    }
}
