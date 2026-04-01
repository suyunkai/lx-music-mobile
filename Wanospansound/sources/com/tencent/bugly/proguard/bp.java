package com.tencent.bugly.proguard;

import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class bp extends m implements Cloneable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static ArrayList<bo> f577b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ArrayList<bo> f578a = null;

    @Override // com.tencent.bugly.proguard.m
    public final void a(StringBuilder sb, int i) {
    }

    @Override // com.tencent.bugly.proguard.m
    public final void a(l lVar) {
        lVar.a((Collection) this.f578a, 0);
    }

    @Override // com.tencent.bugly.proguard.m
    public final void a(k kVar) {
        if (f577b == null) {
            f577b = new ArrayList<>();
            f577b.add(new bo());
        }
        this.f578a = (ArrayList) kVar.a(f577b, 0, true);
    }
}
