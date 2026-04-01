package com.ecarx.eas.sdk.mediacenter;

import android.os.IBinder;
import com.ecarx.eas.xsf.mediacenter.IExCallback;
import com.ecarx.eas.xsf.mediacenter.IExContent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class b extends IExCallback.Stub {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Map<String, a> f131a = new HashMap();

    public interface a {
        IExContent a(int i, String str, String str2, IExContent iExContent, IBinder iBinder);

        String a(int i, String str, String str2, IBinder iBinder);
    }

    public final void a(String str, a aVar) {
        this.f131a.put(str, aVar);
    }

    @Override // com.ecarx.eas.xsf.mediacenter.IExCallback
    public final String onAction(int i, String str, String str2, IBinder iBinder) {
        Iterator<Map.Entry<String, a>> it = this.f131a.entrySet().iterator();
        if (it.hasNext()) {
            return it.next().getValue().a(i, str, str2, iBinder);
        }
        return null;
    }

    @Override // com.ecarx.eas.xsf.mediacenter.IExCallback
    public final IExContent onExAction(int i, String str, String str2, IExContent iExContent, IBinder iBinder) {
        Iterator<Map.Entry<String, a>> it = this.f131a.entrySet().iterator();
        if (it.hasNext()) {
            return it.next().getValue().a(i, str, str2, iExContent, iBinder);
        }
        return null;
    }
}
