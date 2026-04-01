package com.ecarx.eas.sdk.mediacenter.a;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.ecarx.eas.sdk.vr.common.Response;
import com.ecarx.eas.sdk.vr.news.NewsIntentListener;
import com.ecarx.eas.sdk.vr.news.VrNewsCtrlAPI;
import ecarx.xsf.mediacenter.IMediaCenterSvc;
import ecarx.xsf.mediacenter.vr.QNewsResult;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class k extends VrNewsCtrlAPI {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile k f126a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f127b = "k";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Map<String, a> f128c = new HashMap();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private IMediaCenterSvc f129d;

    public static k a(Context context) {
        if (f126a == null) {
            synchronized (k.class) {
                if (f126a == null) {
                    try {
                        f126a = new k(context);
                    } catch (Exception unused) {
                        f126a = null;
                    }
                }
            }
        }
        return f126a;
    }

    private k(Context context) {
    }

    public final void a(IMediaCenterSvc iMediaCenterSvc) {
        this.f129d = iMediaCenterSvc;
    }

    private a a(String str) {
        a aVar;
        synchronized (this.f128c) {
            aVar = this.f128c.get(str);
        }
        return aVar;
    }

    private boolean a(NewsIntentListener newsIntentListener) {
        if (newsIntentListener == null) {
            return false;
        }
        synchronized (this.f128c) {
            String string = newsIntentListener.toString();
            String str = f127b;
            Log.d(str, "_attachNewsIntentObserverWrapper request: " + string);
            if (this.f128c.containsKey(string)) {
                return false;
            }
            this.f128c.put(string, new a(newsIntentListener));
            Log.w(str, "_attachNewsIntentObserverWrapper callbackMap.put : " + string);
            return true;
        }
    }

    private boolean b(NewsIntentListener newsIntentListener) {
        if (newsIntentListener == null) {
            return false;
        }
        synchronized (this.f128c) {
            String string = newsIntentListener.toString();
            String str = f127b;
            Log.d(str, "_detachNewsIntentObserverWrapper request: " + string);
            if (!this.f128c.containsKey(string)) {
                return false;
            }
            this.f128c.remove(string);
            Log.w(str, "_detachNewsIntentObserverWrapper callbackMap.remove : " + string);
            return true;
        }
    }

    private boolean c(NewsIntentListener newsIntentListener) {
        if (newsIntentListener == null) {
            return false;
        }
        synchronized (this.f128c) {
            String string = newsIntentListener.toString();
            String str = f127b;
            Log.d(str, "_checkNewsIntentObserverWrapper request: " + string);
            if (!this.f128c.containsKey(string)) {
                return false;
            }
            Log.w(str, "_checkNewsIntentObserverWrapper callbackMap.remove : " + string);
            return true;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.news.VrNewsCtrlAPI
    public boolean declareNewsCtrlCapability(NewsIntentListener newsIntentListener) {
        if (this.f129d == null || !a(newsIntentListener)) {
            return false;
        }
        Log.d(f127b, "declareNewsCtrlCapability : " + a(newsIntentListener.toString()).toString());
        try {
            return this.f129d.declareNewsCtrlCapability(a(newsIntentListener.toString()));
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.eas.sdk.vr.news.VrNewsCtrlAPI
    public boolean cancelNewsCtrlCapabilityDeclaration(NewsIntentListener newsIntentListener) {
        boolean zCancelNewsCtrlCapabilityDeclaration;
        if (this.f129d != null && c(newsIntentListener)) {
            try {
                zCancelNewsCtrlCapabilityDeclaration = this.f129d.cancelNewsCtrlCapabilityDeclaration(a(newsIntentListener.toString()));
            } catch (RemoteException e) {
                e.printStackTrace();
                zCancelNewsCtrlCapabilityDeclaration = false;
            }
            Log.d(f127b, "mSvrMng.cancelNewsCtrlCapabilityDeclaration : " + zCancelNewsCtrlCapabilityDeclaration);
            if (zCancelNewsCtrlCapabilityDeclaration) {
                return b(newsIntentListener);
            }
        }
        return false;
    }

    @Override // com.ecarx.eas.sdk.vr.news.VrNewsCtrlAPI
    public void declareVrCtrlPriority(String str, int i, NewsIntentListener newsIntentListener) {
        if (this.f129d == null || !c(newsIntentListener)) {
            return;
        }
        try {
            Log.d(f127b, "mSvrMng.declareVrCtrlPriority.");
            this.f129d.declareVrCtrlPriority(str, i, null, null, a(newsIntentListener.toString()));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static class a extends d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private NewsIntentListener f130a;

        public a(NewsIntentListener newsIntentListener) {
            this.f130a = newsIntentListener;
        }

        @Override // com.ecarx.eas.sdk.mediacenter.a.d, ecarx.xsf.mediacenter.vr.INewsIntentObserver
        public final boolean handlePlayNews(QNewsResult qNewsResult) throws RemoteException {
            try {
                this.f130a.handlePlayNews(new f(qNewsResult), new Response());
                return true;
            } catch (Exception e) {
                Log.e(k.f127b, "handlePlayNews error : " + e.getMessage());
                return false;
            }
        }
    }
}
