package ecarx.xsf.mediacenter.session;

/* JADX INFO: loaded from: classes3.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f742a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f743b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f744c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private MediaQueue f745d;

    public final int a() {
        return this.f742a;
    }

    public final void a(int i) {
        this.f742a = i;
    }

    public final int b() {
        return this.f743b;
    }

    public final void b(int i) {
        this.f743b = i;
    }

    public final MediaQueue c() {
        return this.f745d;
    }

    public final void a(MediaQueue mediaQueue) {
        this.f745d = mediaQueue;
    }

    public final int d() {
        return this.f744c;
    }

    public final void c(int i) {
        this.f744c = i;
    }

    public String toString() {
        return "MediaQueueWithPagination{currentPage=" + this.f742a + ", totalPage=" + this.f743b + ", totalSize=" + this.f744c + ", MediaQueue=" + this.f745d + '}';
    }
}
