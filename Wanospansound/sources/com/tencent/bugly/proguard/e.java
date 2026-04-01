package com.tencent.bugly.proguard;

import com.alibaba.android.arouter.utils.Consts;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class e extends d {
    static HashMap<String, byte[]> h;
    static HashMap<String, HashMap<String, byte[]>> i;
    protected g g;
    private int j;

    public e() {
        g gVar = new g();
        this.g = gVar;
        this.j = 0;
        gVar.f607a = (short) 2;
    }

    @Override // com.tencent.bugly.proguard.d, com.tencent.bugly.proguard.c
    public final <T> void a(String str, T t) {
        if (str.startsWith(Consts.DOT)) {
            throw new IllegalArgumentException("put name can not startwith . , now is ".concat(String.valueOf(str)));
        }
        super.a(str, t);
    }

    @Override // com.tencent.bugly.proguard.d
    public final void b() {
        super.b();
        this.g.f607a = (short) 3;
    }

    @Override // com.tencent.bugly.proguard.d, com.tencent.bugly.proguard.c
    public final byte[] a() {
        if (this.g.f607a == 2) {
            if (this.g.e.equals("")) {
                throw new IllegalArgumentException("servantName can not is null");
            }
            if (this.g.f.equals("")) {
                throw new IllegalArgumentException("funcName can not is null");
            }
        } else {
            if (this.g.e == null) {
                this.g.e = "";
            }
            if (this.g.f == null) {
                this.g.f = "";
            }
        }
        l lVar = new l(0);
        lVar.a(this.f603c);
        if (this.g.f607a == 2) {
            lVar.a((Map) this.f601a, 0);
        } else {
            lVar.a((Map) this.e, 0);
        }
        this.g.g = n.a(lVar.f617a);
        l lVar2 = new l(0);
        lVar2.a(this.f603c);
        this.g.a(lVar2);
        byte[] bArrA = n.a(lVar2.f617a);
        int length = bArrA.length + 4;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(length);
        byteBufferAllocate.putInt(length).put(bArrA).flip();
        return byteBufferAllocate.array();
    }

    @Override // com.tencent.bugly.proguard.d, com.tencent.bugly.proguard.c
    public final void a(byte[] bArr) {
        if (bArr.length < 4) {
            throw new IllegalArgumentException("decode package must include size head");
        }
        try {
            k kVar = new k(bArr, (byte) 0);
            kVar.a(this.f603c);
            this.g.a(kVar);
            if (this.g.f607a == 3) {
                k kVar2 = new k(this.g.g);
                kVar2.a(this.f603c);
                if (h == null) {
                    HashMap<String, byte[]> map = new HashMap<>();
                    h = map;
                    map.put("", new byte[0]);
                }
                this.e = kVar2.a((Map) h, 0, false);
                return;
            }
            k kVar3 = new k(this.g.g);
            kVar3.a(this.f603c);
            if (i == null) {
                i = new HashMap<>();
                HashMap<String, byte[]> map2 = new HashMap<>();
                map2.put("", new byte[0]);
                i.put("", map2);
            }
            this.f601a = kVar3.a((Map) i, 0, false);
            this.f602b = new HashMap<>();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public final void b(String str) {
        this.g.e = str;
    }

    public final void c(String str) {
        this.g.f = str;
    }

    public final void c() {
        this.g.f610d = 1;
    }
}
