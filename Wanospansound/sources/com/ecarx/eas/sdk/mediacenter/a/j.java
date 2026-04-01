package com.ecarx.eas.sdk.mediacenter.a;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.ecarx.eas.sdk.vr.common.Response;
import com.ecarx.eas.sdk.vr.music.MusicIntentListener;
import com.ecarx.eas.sdk.vr.music.VrMusicCtrlAPI;
import ecarx.xsf.mediacenter.IMediaCenterSvc;
import ecarx.xsf.mediacenter.vr.QMusicResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class j extends VrMusicCtrlAPI {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile j f122a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Map<String, a> f123b = new HashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private IMediaCenterSvc f124c;

    public static j a(Context context) {
        if (f122a == null) {
            synchronized (j.class) {
                if (f122a == null) {
                    try {
                        f122a = new j(context);
                    } catch (Exception unused) {
                        f122a = null;
                    }
                }
            }
        }
        return f122a;
    }

    private j(Context context) {
        new ArrayList();
    }

    public final void a(IMediaCenterSvc iMediaCenterSvc) {
        this.f124c = iMediaCenterSvc;
    }

    @Override // com.ecarx.eas.sdk.vr.music.VrMusicCtrlAPI
    public boolean declareMusicCtrlCapability(int[] iArr, MusicIntentListener musicIntentListener) {
        if (this.f124c != null && a(musicIntentListener)) {
            Log.d("VrMusicCtrlAPIImpl", "declareMediaCtrlCapability : " + a(musicIntentListener.toString()).toString());
            try {
                return this.f124c.declareMusicCtrlCapability(iArr, a(musicIntentListener.toString()));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override // com.ecarx.eas.sdk.vr.music.VrMusicCtrlAPI
    public boolean cancelMusicCtrlCapabilityDeclaration(MusicIntentListener musicIntentListener) {
        boolean zCancelMusicCtrlCapabilityDeclaration;
        if (this.f124c != null && c(musicIntentListener)) {
            try {
                zCancelMusicCtrlCapabilityDeclaration = this.f124c.cancelMusicCtrlCapabilityDeclaration(a(musicIntentListener.toString()));
            } catch (RemoteException e) {
                e.printStackTrace();
                zCancelMusicCtrlCapabilityDeclaration = false;
            }
            Log.d("VrMusicCtrlAPIImpl", "mSvrMng.cancelMediaCtrlCapabilityDeclaration : " + zCancelMusicCtrlCapabilityDeclaration);
            if (zCancelMusicCtrlCapabilityDeclaration) {
                return b(musicIntentListener);
            }
        }
        return false;
    }

    @Override // com.ecarx.eas.sdk.vr.music.VrMusicCtrlAPI
    public void declareVrCtrlPriority(String str, int i, MusicIntentListener musicIntentListener) {
        if (this.f124c == null || !c(musicIntentListener)) {
            return;
        }
        try {
            Log.d("VrMusicCtrlAPIImpl", "mSvrMng.declareVrCtrlPriority.");
            this.f124c.declareVrCtrlPriority(str, i, a(musicIntentListener.toString()), null, null);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private a a(String str) {
        a aVar;
        synchronized (this.f123b) {
            aVar = this.f123b.get(str);
        }
        return aVar;
    }

    private boolean a(MusicIntentListener musicIntentListener) {
        if (musicIntentListener == null) {
            return false;
        }
        synchronized (this.f123b) {
            String string = musicIntentListener.toString();
            Log.d("VrMusicCtrlAPIImpl", "_attachMediaIntentObserverWrapper request: " + string);
            if (this.f123b.containsKey(string)) {
                return false;
            }
            this.f123b.put(string, new a(musicIntentListener));
            Log.w("VrMusicCtrlAPIImpl", "_attachMediaIntentObserverWrapper callbackMap.put : " + string);
            return true;
        }
    }

    private boolean b(MusicIntentListener musicIntentListener) {
        if (musicIntentListener == null) {
            return false;
        }
        synchronized (this.f123b) {
            String string = musicIntentListener.toString();
            Log.d("VrMusicCtrlAPIImpl", "_detachMediaIntentObserverWrapper request: " + string);
            if (!this.f123b.containsKey(string)) {
                return false;
            }
            this.f123b.remove(string);
            Log.w("VrMusicCtrlAPIImpl", "_detachMediaIntentObserverWrapper callbackMap.remove : " + string);
            return true;
        }
    }

    private boolean c(MusicIntentListener musicIntentListener) {
        if (musicIntentListener == null) {
            return false;
        }
        synchronized (this.f123b) {
            String string = musicIntentListener.toString();
            Log.d("VrMusicCtrlAPIImpl", "_checkMediaIntentObserverWrapper request: " + string);
            if (!this.f123b.containsKey(string)) {
                return false;
            }
            Log.w("VrMusicCtrlAPIImpl", "_checkMediaIntentObserverWrapper callbackMap.remove : " + string);
            return true;
        }
    }

    public static class a extends c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private MusicIntentListener f125a;

        public a(MusicIntentListener musicIntentListener) {
            this.f125a = musicIntentListener;
        }

        @Override // com.ecarx.eas.sdk.mediacenter.a.c, ecarx.xsf.mediacenter.vr.IMusicIntentObserver
        public final boolean handleSearchMusic(QMusicResult qMusicResult) throws RemoteException {
            try {
                this.f125a.handleSearchMusic(new h(qMusicResult), new Response());
                return true;
            } catch (Exception e) {
                Log.e("VrMusicCtrlAPIImpl", "handleSearchMedia error : " + e.getMessage());
                return false;
            }
        }

        @Override // com.ecarx.eas.sdk.mediacenter.a.c, ecarx.xsf.mediacenter.vr.IMusicIntentObserver
        public final boolean handlePlayMusic(QMusicResult qMusicResult) throws RemoteException {
            if (qMusicResult == null) {
                Log.e("VrMusicCtrlAPIImpl", "handlePlayMedia QMusicResult is NULL!!");
                return false;
            }
            try {
                this.f125a.handlePlayMusic(new e(qMusicResult), new Response());
                Log.e("VrMusicCtrlAPIImpl", "handlePlayMedia success : " + qMusicResult.toString());
                return true;
            } catch (Exception e) {
                Log.e("VrMusicCtrlAPIImpl", "handlePlayMedia error : " + e.getMessage());
                return false;
            }
        }

        @Override // com.ecarx.eas.sdk.mediacenter.a.c, ecarx.xsf.mediacenter.vr.IMusicIntentObserver
        public final boolean handleCtrlApp(int i) throws RemoteException {
            try {
                this.f125a.handleCtrlApp(new com.ecarx.eas.sdk.mediacenter.a.a(i), new Response());
                return true;
            } catch (Exception e) {
                Log.e("VrMusicCtrlAPIImpl", "handleCtrlMedia error : " + e.getMessage());
                return false;
            }
        }

        @Override // com.ecarx.eas.sdk.mediacenter.a.c, ecarx.xsf.mediacenter.vr.IMusicIntentObserver
        public final boolean handleCtrlMediaClient(int i, int i2) throws RemoteException {
            try {
                this.f125a.handleCtrlApp(new com.ecarx.eas.sdk.mediacenter.a.a(i, i2), new Response());
                return true;
            } catch (Exception e) {
                Log.e("VrMusicCtrlAPIImpl", "handleCtrlMedia error : " + e.getMessage());
                return false;
            }
        }
    }
}
