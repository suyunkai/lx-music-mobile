package com.tencent.bugly.proguard;

import java.io.Serializable;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class t implements Serializable, Comparable<t> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f645a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f646b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f647c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f648d;
    public String e;
    public String f;
    public long g;

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(t tVar) {
        return (int) (this.f647c - tVar.f647c);
    }
}
