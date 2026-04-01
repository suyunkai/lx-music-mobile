package com.tencent.bugly.proguard;

import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class bm extends m implements Cloneable {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static ArrayList<String> f566c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f567a = "";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ArrayList<String> f568b = null;

    @Override // com.tencent.bugly.proguard.m
    public final void a(StringBuilder sb, int i) {
    }

    @Override // com.tencent.bugly.proguard.m
    public final void a(l lVar) {
        lVar.a(this.f567a, 0);
        ArrayList<String> arrayList = this.f568b;
        if (arrayList != null) {
            lVar.a((Collection) arrayList, 1);
        }
    }

    @Override // com.tencent.bugly.proguard.m
    public final void a(k kVar) {
        this.f567a = kVar.b(0, true);
        if (f566c == null) {
            ArrayList<String> arrayList = new ArrayList<>();
            f566c = arrayList;
            arrayList.add("");
        }
        this.f568b = (ArrayList) kVar.a(f566c, 1, false);
    }
}
