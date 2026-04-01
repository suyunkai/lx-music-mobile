package ecarx.xsf.mediacenter.session.adapter;

import com.ecarx.eas.xsf.mediacenter.IExContent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes3.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile c f719a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Map<Class<?>, m<?>> f720b = new ConcurrentHashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final List<TypeAdapterFactory> f721c;

    public static c a() {
        if (f719a == null) {
            synchronized (c.class) {
                if (f719a == null) {
                    f719a = new c();
                }
            }
        }
        return f719a;
    }

    public c() {
        ArrayList arrayList = new ArrayList();
        this.f721c = arrayList;
        arrayList.add(f.f724a);
        arrayList.add(g.f725a);
        arrayList.add(h.f726a);
        arrayList.add(j.f728a);
        arrayList.add(k.f729a);
        arrayList.add(a.f717a);
        arrayList.add(b.f718a);
        arrayList.add(d.f722a);
        arrayList.add(h.f726a);
        arrayList.add(e.f723a);
        arrayList.add(i.f727a);
    }

    private <T> m<T> a(Class<T> cls) {
        m<T> mVar = (m) this.f720b.get(cls);
        if (mVar != null) {
            return mVar;
        }
        Iterator<TypeAdapterFactory> it = this.f721c.iterator();
        while (it.hasNext()) {
            m<T> mVarCreate = it.next().create(cls);
            if (mVarCreate != null) {
                this.f720b.put(cls, mVarCreate);
                return mVarCreate;
            }
        }
        throw new IllegalArgumentException("Unsupported type " + cls);
    }

    public final <T> byte[] a(T t, Class<T> cls) {
        if (t == null) {
            return null;
        }
        m<T> mVarA = a(cls);
        if (mVarA != null) {
            return mVarA.a(t);
        }
        throw new IllegalArgumentException("Unsupported type " + cls);
    }

    public final <T> T a(byte[] bArr, Class<T> cls) {
        m<T> mVarA = a(cls);
        if (mVarA != null) {
            return mVarA.a(bArr);
        }
        throw new IllegalArgumentException("Unsupported type " + cls);
    }

    public final <T> T a(IExContent iExContent, Class<T> cls) {
        m<T> mVarA = a(cls);
        if (mVarA != null) {
            return mVarA.a(iExContent);
        }
        throw new IllegalArgumentException("Unsupported type " + cls);
    }
}
