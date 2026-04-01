package com.ecarx.eas.sdk.mediacenter.a;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.ecarx.eas.sdk.vr.common.Response;
import com.ecarx.eas.sdk.vr.radio.RadioIntentHandling;
import com.ecarx.eas.sdk.vr.radio.VrLocalRadioCtrlAPI;
import ecarx.xsf.mediacenter.IMediaCenterSvc;
import ecarx.xsf.mediacenter.vr.QRadioResult;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class i extends VrLocalRadioCtrlAPI {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile i f117a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f118b = "i";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Map<String, a> f119c = new HashMap();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private IMediaCenterSvc f120d;

    public static i a(Context context) {
        if (f117a == null) {
            synchronized (i.class) {
                if (f117a == null) {
                    try {
                        f117a = new i(context);
                    } catch (Exception unused) {
                        f117a = null;
                    }
                }
            }
        }
        return f117a;
    }

    private i(Context context) {
    }

    public final void a(IMediaCenterSvc iMediaCenterSvc) {
        this.f120d = iMediaCenterSvc;
    }

    @Override // com.ecarx.eas.sdk.vr.radio.VrLocalRadioCtrlAPI
    public boolean declareRadioCtrlCapability(int[] iArr, RadioIntentHandling radioIntentHandling) {
        if (this.f120d != null && a(radioIntentHandling)) {
            Log.d(f118b, "declareRadioCtrlCapability : " + a(radioIntentHandling.toString()).toString());
            try {
                return this.f120d.declareRadioCtrlCapability(iArr, a(radioIntentHandling.toString()));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override // com.ecarx.eas.sdk.vr.radio.VrLocalRadioCtrlAPI
    public boolean cancelRadioCtrlCapabilityDeclaration(RadioIntentHandling radioIntentHandling) {
        boolean zCancelRadioCtrlCapabilityDeclaration;
        if ((this.f120d != null) & c(radioIntentHandling)) {
            try {
                zCancelRadioCtrlCapabilityDeclaration = this.f120d.cancelRadioCtrlCapabilityDeclaration(a(radioIntentHandling.toString()));
            } catch (RemoteException unused) {
                zCancelRadioCtrlCapabilityDeclaration = false;
            }
            if (zCancelRadioCtrlCapabilityDeclaration) {
                return b(radioIntentHandling);
            }
        }
        return false;
    }

    @Override // com.ecarx.eas.sdk.vr.radio.VrLocalRadioCtrlAPI
    public void declareVrCtrlPriority(String str, int i, RadioIntentHandling radioIntentHandling) {
        if ((this.f120d != null) && c(radioIntentHandling)) {
            try {
                Log.d(f118b, "mSvrMng.declareVrCtrlPriority.");
                this.f120d.declareVrCtrlPriority(str, i, null, a(radioIntentHandling.toString()), null);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private a a(String str) {
        a aVar;
        synchronized (this.f119c) {
            aVar = this.f119c.get(str);
        }
        return aVar;
    }

    private boolean a(RadioIntentHandling radioIntentHandling) {
        if (radioIntentHandling == null) {
            return false;
        }
        synchronized (this.f119c) {
            String string = radioIntentHandling.toString();
            String str = f118b;
            Log.d(str, "_attachRadioIntentObserverWrapper request: " + string);
            if (this.f119c.containsKey(string)) {
                return false;
            }
            this.f119c.put(string, new a(this, radioIntentHandling));
            Log.w(str, "_attachRadioIntentObserverWrapper callbackMap.put : " + string);
            return true;
        }
    }

    private boolean b(RadioIntentHandling radioIntentHandling) {
        if (radioIntentHandling == null) {
            return false;
        }
        synchronized (this.f119c) {
            String string = radioIntentHandling.toString();
            String str = f118b;
            Log.d(str, "_detachRadioIntentObserverWrapper request: " + string);
            if (!this.f119c.containsKey(string)) {
                return false;
            }
            this.f119c.remove(string);
            Log.w(str, "_detachRadioIntentObserverWrapper callbackMap.remove : " + string);
            return true;
        }
    }

    private boolean c(RadioIntentHandling radioIntentHandling) {
        if (radioIntentHandling == null) {
            return false;
        }
        synchronized (this.f119c) {
            String string = radioIntentHandling.toString();
            String str = f118b;
            Log.d(str, "_checkRadioIntentObserverWrapper request: " + string);
            if (!this.f119c.containsKey(string)) {
                return false;
            }
            Log.w(str, "_checkRadioIntentObserverWrapper callbackMap.remove : " + string);
            return true;
        }
    }

    public class a extends g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private RadioIntentHandling f121a;

        public a(i iVar, RadioIntentHandling radioIntentHandling) {
            this.f121a = radioIntentHandling;
        }

        @Override // com.ecarx.eas.sdk.mediacenter.a.g, ecarx.xsf.mediacenter.vr.IRadioIntentObserver
        public final boolean handlePlayRadio(QRadioResult qRadioResult) throws RemoteException {
            try {
                this.f121a.handleCtrlRadio(new b(qRadioResult), new Response());
                return true;
            } catch (Exception e) {
                Log.e(i.f118b, "handleCtrlRadio error : " + e.getMessage());
                return false;
            }
        }

        @Override // com.ecarx.eas.sdk.mediacenter.a.g, ecarx.xsf.mediacenter.vr.IRadioIntentObserver
        public final boolean handleCtrlApp(int i) throws RemoteException {
            try {
                this.f121a.handleCtrlApp(new com.ecarx.eas.sdk.mediacenter.a.a(i), new Response());
                return true;
            } catch (Exception e) {
                Log.e(i.f118b, "handleCtrlApp error : " + e.getMessage());
                return false;
            }
        }

        @Override // com.ecarx.eas.sdk.mediacenter.a.g, ecarx.xsf.mediacenter.vr.IRadioIntentObserver
        public final boolean handleCtrlMediaClient(int i, int i2) throws RemoteException {
            try {
                this.f121a.handleCtrlApp(new com.ecarx.eas.sdk.mediacenter.a.a(i, i2), new Response());
                return true;
            } catch (Exception e) {
                Log.e(i.f118b, "handleCtrlMediaClient error : " + e.getMessage());
                return false;
            }
        }
    }
}
