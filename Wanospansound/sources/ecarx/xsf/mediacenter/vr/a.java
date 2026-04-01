package ecarx.xsf.mediacenter.vr;

/* JADX INFO: loaded from: classes3.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private long f814a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f815b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private long f816c;

    public final void a(long j) {
        this.f814a = j;
    }

    public final void a(String str) {
        this.f815b = str;
    }

    public final void b(long j) {
        this.f816c = j;
    }

    public String toString() {
        return "VRControlResponseInfo{uuid=" + this.f814a + ", message='" + this.f815b + "', timeStamp=" + this.f816c + '}';
    }
}
