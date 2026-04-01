package com.tencent.bugly.proguard;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class ar implements Comparable<ar> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f486a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f487b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f488c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f489d = false;
    public boolean e = false;
    public int f = 0;

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(ar arVar) {
        ar arVar2 = arVar;
        if (arVar2 == null) {
            return 1;
        }
        long j = this.f487b - arVar2.f487b;
        if (j <= 0) {
            return j < 0 ? -1 : 0;
        }
        return 1;
    }
}
