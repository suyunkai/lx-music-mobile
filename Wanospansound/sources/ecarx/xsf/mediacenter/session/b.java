package ecarx.xsf.mediacenter.session;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.ecarx.eas.framework.sdk.EASCallUtils;
import com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService;
import com.ecarx.eas.sdk.IServiceManager;
import com.ecarx.eas.sdk.mediacenter.RegisterRequest;
import ecarx.xsf.mediacenter.session.EasMediaController;
import ecarx.xsf.mediacenter.session.adapter.l;
import ecarx.xsf.mediacenter.session.proto.nano.Controller;
import ecarx.xsf.mediacenter.session.proto.nano.Mediabean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes3.dex */
public class b implements EasMediaController {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile b f734a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private a f735b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private com.ecarx.eas.sdk.mediacenter.b f736c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private ecarx.xsf.mediacenter.session.a f737d;
    private IEASFrameworkService e;
    private byte[] f = new byte[0];
    private int g;

    public static b a() {
        if (f734a == null) {
            synchronized (b.class) {
                if (f734a == null) {
                    f734a = new b();
                }
            }
        }
        return f734a;
    }

    public final void a(IEASFrameworkService iEASFrameworkService) {
        this.e = iEASFrameworkService;
        this.f736c = new com.ecarx.eas.sdk.mediacenter.b();
    }

    @Override // ecarx.xsf.mediacenter.session.EasMediaController
    public EasMediaController.TransportControls getTransportControls() {
        return this.f735b;
    }

    @Override // ecarx.xsf.mediacenter.session.EasMediaController
    public boolean registerCallback(EasMediaController.Callback callback) {
        if (callback == null) {
            return false;
        }
        ecarx.xsf.mediacenter.session.a aVar = new ecarx.xsf.mediacenter.session.a();
        this.f737d = aVar;
        aVar.a(callback);
        this.f736c.a("easControl", this.f737d);
        if (EASCallUtils.call(this.e, IServiceManager.SERVICE_MEDIACENTER, "media_session_controller", "register", this.f, this.f736c.asBinder()) == null) {
            return false;
        }
        this.f735b = new a();
        return true;
    }

    @Override // ecarx.xsf.mediacenter.session.EasMediaController
    public EasMediaController.TransportControls registerCallback(RegisterRequest registerRequest, EasMediaController.Callback callback) {
        if (registerRequest != null && callback != null) {
            int zoneId = registerRequest.getZoneId();
            int displayId = registerRequest.getDisplayId();
            ecarx.xsf.mediacenter.session.a aVar = new ecarx.xsf.mediacenter.session.a();
            this.f737d = aVar;
            aVar.a(callback);
            com.ecarx.eas.sdk.mediacenter.b bVar = new com.ecarx.eas.sdk.mediacenter.b();
            this.f736c = bVar;
            bVar.a("easControl", this.f737d);
            Controller.a aVar2 = new Controller.a();
            aVar2.t = zoneId;
            aVar2.v = displayId;
            if (EASCallUtils.call(this.e, IServiceManager.SERVICE_MEDIACENTER, "media_session_controller", "registerControllerWithRequest", ecarx.xsf.mediacenter.session.adapter.c.a().a(aVar2, (Class<Controller.a>) Controller.a.class), this.f736c.asBinder()) != null) {
                a aVar3 = new a(displayId);
                this.f735b = aVar3;
                return aVar3;
            }
        }
        return null;
    }

    @Override // ecarx.xsf.mediacenter.session.EasMediaController
    public boolean unregisterCallback(EasMediaController.Callback callback) {
        if (callback == null) {
            return false;
        }
        this.f737d.a(null);
        return EASCallUtils.call(this.e, IServiceManager.SERVICE_MEDIACENTER, "media_session_controller", "unregister", this.f, this.f736c.asBinder()) != null;
    }

    @Override // ecarx.xsf.mediacenter.session.EasMediaController
    public List<l> getAllFocusInfoList() {
        Controller.b bVar = (Controller.b) ecarx.xsf.mediacenter.session.adapter.c.a().a(EASCallUtils.call(this.e, IServiceManager.SERVICE_MEDIACENTER, "media_session_controller", "getAllFocusInfoList", this.f, null), Controller.b.class);
        ArrayList arrayList = new ArrayList();
        if (bVar.f753d != null && bVar.f753d.length != 0) {
            for (Mediabean.b bVar2 : bVar.f753d) {
                l lVar = new l();
                lVar.a(!TextUtils.isEmpty(bVar2.f758a) ? bVar2.f758a : "");
                lVar.a(bVar2.f759b);
                lVar.b(bVar2.f760c);
                lVar.c(bVar2.f761d);
                lVar.d(bVar2.e);
                arrayList.add(lVar);
            }
        }
        return arrayList;
    }

    @Override // ecarx.xsf.mediacenter.session.EasMediaController
    public int[] getMediaSourceTypeList() {
        Controller.a aVar = new Controller.a();
        aVar.t = this.g;
        return ((Controller.b) ecarx.xsf.mediacenter.session.adapter.c.a().a(EASCallUtils.call(this.e, IServiceManager.SERVICE_MEDIACENTER, "media_session_controller", "getMediaSourceTypeList", ecarx.xsf.mediacenter.session.adapter.c.a().a(aVar, (Class<Controller.a>) Controller.a.class), null), Controller.b.class)).f751b;
    }

    @Override // ecarx.xsf.mediacenter.session.EasMediaController
    public int getCurrentMediaSourceType() {
        Controller.a aVar = new Controller.a();
        aVar.t = this.g;
        return ((Controller.b) ecarx.xsf.mediacenter.session.adapter.c.a().a(EASCallUtils.call(this.e, IServiceManager.SERVICE_MEDIACENTER, "media_session_controller", "getCurrentMediaSourceType", ecarx.xsf.mediacenter.session.adapter.c.a().a(aVar, (Class<Controller.a>) Controller.a.class), null), Controller.b.class)).f750a;
    }

    @Override // ecarx.xsf.mediacenter.session.EasMediaController
    public PlaybackState getPlaybackState() {
        Controller.a aVar = new Controller.a();
        aVar.t = this.g;
        return (PlaybackState) ecarx.xsf.mediacenter.session.adapter.c.a().a(EASCallUtils.call(this.e, IServiceManager.SERVICE_MEDIACENTER, "media_session_controller", "getPlaybackState", ecarx.xsf.mediacenter.session.adapter.c.a().a(aVar, (Class<Controller.a>) Controller.a.class), null), PlaybackState.class);
    }

    @Override // ecarx.xsf.mediacenter.session.EasMediaController
    public MediaQueue getMediaQueue() {
        c cVar = new c();
        Controller.a aVar = new Controller.a();
        aVar.t = this.g;
        EASCallUtils.call(this.e, IServiceManager.SERVICE_MEDIACENTER, "media_session_controller", "getMediaQueue", ecarx.xsf.mediacenter.session.adapter.c.a().a(aVar, (Class<Controller.a>) Controller.a.class), cVar.asBinder());
        try {
            return (MediaQueue) ecarx.xsf.mediacenter.session.adapter.c.a().a(cVar.get(1000L, TimeUnit.MILLISECONDS), MediaQueue.class);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e2) {
            e2.printStackTrace();
            return null;
        } catch (TimeoutException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    @Override // ecarx.xsf.mediacenter.session.EasMediaController
    public PlaybackInfo getPlaybackInfo() {
        c cVar = new c();
        Controller.a aVar = new Controller.a();
        aVar.t = this.g;
        EASCallUtils.call(this.e, IServiceManager.SERVICE_MEDIACENTER, "media_session_controller", "getPlaybackInfo", ecarx.xsf.mediacenter.session.adapter.c.a().a(aVar, (Class<Controller.a>) Controller.a.class), cVar.asBinder());
        try {
            return (PlaybackInfo) ecarx.xsf.mediacenter.session.adapter.c.a().a(cVar.get(1000L, TimeUnit.MILLISECONDS), PlaybackInfo.class);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e2) {
            e2.printStackTrace();
            return null;
        } catch (TimeoutException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    @Override // ecarx.xsf.mediacenter.session.EasMediaController
    public List<AppSourceInfo> getAppSourceInfo() {
        Controller.a aVar = new Controller.a();
        aVar.t = this.g;
        return l.a((Controller.a) ecarx.xsf.mediacenter.session.adapter.c.a().a(EASCallUtils.call(this.e, IServiceManager.SERVICE_MEDIACENTER, "media_session_controller", "getAppSourceInfo", ecarx.xsf.mediacenter.session.adapter.c.a().a(aVar, (Class<Controller.a>) Controller.a.class), null), Controller.a.class));
    }

    @Override // ecarx.xsf.mediacenter.session.EasMediaController
    public List<AppSourceInfo> getCustomAppSourceInfo() {
        Controller.a aVar = new Controller.a();
        aVar.t = this.g;
        return l.a((Controller.a) ecarx.xsf.mediacenter.session.adapter.c.a().a(EASCallUtils.call(this.e, IServiceManager.SERVICE_MEDIACENTER, "media_session_controller", "getCustomAppSourceInfo", ecarx.xsf.mediacenter.session.adapter.c.a().a(aVar, (Class<Controller.a>) Controller.a.class), null), Controller.a.class));
    }

    @Override // ecarx.xsf.mediacenter.session.EasMediaController
    public List<AppSourceInfo> getAppSourceConfig() {
        Controller.a aVar = new Controller.a();
        aVar.t = this.g;
        return l.a((Controller.a) ecarx.xsf.mediacenter.session.adapter.c.a().a(EASCallUtils.call(this.e, IServiceManager.SERVICE_MEDIACENTER, "media_session_controller", "getAppSourceConfig", ecarx.xsf.mediacenter.session.adapter.c.a().a(aVar, (Class<Controller.a>) Controller.a.class), null), Controller.a.class));
    }

    @Override // ecarx.xsf.mediacenter.session.EasMediaController
    public boolean registerCallbackWithZoneId(int i, EasMediaController.Callback callback) {
        if (callback == null) {
            return false;
        }
        ecarx.xsf.mediacenter.session.a aVar = new ecarx.xsf.mediacenter.session.a();
        this.f737d = aVar;
        aVar.a(callback);
        this.f736c.a("easControl", this.f737d);
        Controller.a aVar2 = new Controller.a();
        aVar2.t = i;
        if (EASCallUtils.call(this.e, IServiceManager.SERVICE_MEDIACENTER, "media_session_controller", "registerWithZoneId", ecarx.xsf.mediacenter.session.adapter.c.a().a(aVar2, (Class<Controller.a>) Controller.a.class), this.f736c.asBinder()) == null) {
            return false;
        }
        this.f735b = new a();
        return true;
    }

    @Override // ecarx.xsf.mediacenter.session.EasMediaController
    public int[] getZones() {
        return ((Controller.b) ecarx.xsf.mediacenter.session.adapter.c.a().a(EASCallUtils.call(this.e, IServiceManager.SERVICE_MEDIACENTER, "media_session_controller", "getZones", this.f, null), Controller.b.class)).f752c;
    }

    @Override // ecarx.xsf.mediacenter.session.EasMediaController
    public void updateZoneId(int i) {
        this.g = i;
        Controller.a aVar = new Controller.a();
        aVar.t = i;
        EASCallUtils.call(this.e, IServiceManager.SERVICE_MEDIACENTER, "media_session_controller", "updateZoneId", ecarx.xsf.mediacenter.session.adapter.c.a().a(aVar, (Class<Controller.a>) Controller.a.class), this.f736c.asBinder());
    }

    class a implements EasMediaController.TransportControls {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f738a;

        public a() {
            this.f738a = 100;
        }

        public a(int i) {
            this.f738a = i;
        }

        @Override // ecarx.xsf.mediacenter.session.EasMediaController.TransportControls
        public final void transportControl(int i, Bundle bundle) {
            Controller.a aVar = new Controller.a();
            aVar.f746a = i;
            aVar.t = b.this.g;
            aVar.v = this.f738a;
            if (bundle != null) {
                HashMap map = new HashMap();
                for (String str : bundle.keySet()) {
                    map.put(str, bundle.get(str).toString());
                    Log.d("EasMediaControllerImp", "key:" + str + "value :" + bundle.get(str).toString());
                }
                aVar.o = map;
            }
            Log.d("EasMediaControllerImp", "transportControls:" + i);
            EASCallUtils.call(b.this.e, IServiceManager.SERVICE_MEDIACENTER, "media_session_controller", "transportControl", ecarx.xsf.mediacenter.session.adapter.c.a().a(aVar, (Class<Controller.a>) Controller.a.class), null);
        }

        @Override // ecarx.xsf.mediacenter.session.EasMediaController.TransportControls
        public final void select(String str, String str2, boolean z) {
            Controller.a aVar = new Controller.a();
            aVar.f749d = b.a(b.this, str);
            aVar.e = b.a(b.this, str2);
            aVar.g = z;
            aVar.t = b.this.g;
            aVar.v = this.f738a;
            EASCallUtils.call(b.this.e, IServiceManager.SERVICE_MEDIACENTER, "media_session_controller", "select", ecarx.xsf.mediacenter.session.adapter.c.a().a(aVar, (Class<Controller.a>) Controller.a.class), null);
        }

        @Override // ecarx.xsf.mediacenter.session.EasMediaController.TransportControls
        public final void selectFrom(String str, String str2, boolean z, String str3) {
            Controller.a aVar = new Controller.a();
            aVar.f749d = b.a(b.this, str);
            aVar.e = b.a(b.this, str2);
            aVar.g = z;
            aVar.m = b.a(b.this, str3);
            aVar.t = b.this.g;
            aVar.v = this.f738a;
            EASCallUtils.call(b.this.e, IServiceManager.SERVICE_MEDIACENTER, "media_session_controller", "selectFrom", ecarx.xsf.mediacenter.session.adapter.c.a().a(aVar, (Class<Controller.a>) Controller.a.class), null);
        }

        @Override // ecarx.xsf.mediacenter.session.EasMediaController.TransportControls
        public final void setMediaSource(int i) {
            Controller.a aVar = new Controller.a();
            aVar.f747b = i;
            aVar.t = b.this.g;
            aVar.v = this.f738a;
            EASCallUtils.call(b.this.e, IServiceManager.SERVICE_MEDIACENTER, "media_session_controller", "setMediaSourceType", ecarx.xsf.mediacenter.session.adapter.c.a().a(aVar, (Class<Controller.a>) Controller.a.class), null);
        }

        @Override // ecarx.xsf.mediacenter.session.EasMediaController.TransportControls
        public final void setLoopMode(int i) {
            Controller.a aVar = new Controller.a();
            aVar.f748c = i;
            aVar.t = b.this.g;
            aVar.v = this.f738a;
            EASCallUtils.call(b.this.e, IServiceManager.SERVICE_MEDIACENTER, "media_session_controller", "setLoopMode", ecarx.xsf.mediacenter.session.adapter.c.a().a(aVar, (Class<Controller.a>) Controller.a.class), null);
        }

        @Override // ecarx.xsf.mediacenter.session.EasMediaController.TransportControls
        public final void setMediaQuality(int i) {
            Controller.a aVar = new Controller.a();
            aVar.l = i;
            aVar.t = b.this.g;
            aVar.v = this.f738a;
            EASCallUtils.call(b.this.e, IServiceManager.SERVICE_MEDIACENTER, "media_session_controller", "setMediaQuality", ecarx.xsf.mediacenter.session.adapter.c.a().a(aVar, (Class<Controller.a>) Controller.a.class), null);
        }

        @Override // ecarx.xsf.mediacenter.session.EasMediaController.TransportControls
        public final void favorite(String str, String str2, boolean z) {
            Controller.a aVar = new Controller.a();
            aVar.f749d = b.a(b.this, str);
            aVar.e = b.a(b.this, str2);
            aVar.h = z;
            aVar.t = b.this.g;
            EASCallUtils.call(b.this.e, IServiceManager.SERVICE_MEDIACENTER, "media_session_controller", "favorite", ecarx.xsf.mediacenter.session.adapter.c.a().a(aVar, (Class<Controller.a>) Controller.a.class), null);
        }

        @Override // ecarx.xsf.mediacenter.session.EasMediaController.TransportControls
        public final void download(String str, String str2, boolean z) {
            Controller.a aVar = new Controller.a();
            aVar.f749d = b.a(b.this, str);
            aVar.e = b.a(b.this, str2);
            aVar.i = z;
            aVar.t = b.this.g;
            aVar.v = this.f738a;
            EASCallUtils.call(b.this.e, IServiceManager.SERVICE_MEDIACENTER, "media_session_controller", "download", ecarx.xsf.mediacenter.session.adapter.c.a().a(aVar, (Class<Controller.a>) Controller.a.class), null);
        }

        @Override // ecarx.xsf.mediacenter.session.EasMediaController.TransportControls
        public final void setMediaSource(String str, int i) {
            Controller.a aVar = new Controller.a();
            aVar.m = b.a(b.this, str);
            aVar.f747b = i;
            aVar.t = b.this.g;
            EASCallUtils.call(b.this.e, IServiceManager.SERVICE_MEDIACENTER, "media_session_controller", "setMediaPackageSourceType", ecarx.xsf.mediacenter.session.adapter.c.a().a(aVar, (Class<Controller.a>) Controller.a.class), null);
        }

        @Override // ecarx.xsf.mediacenter.session.EasMediaController.TransportControls
        public final void setMediaSource(String str, String str2, int i) {
            Controller.a aVar = new Controller.a();
            aVar.s = b.a(b.this, str);
            aVar.m = b.a(b.this, str2);
            aVar.f747b = i;
            aVar.t = b.this.g;
            EASCallUtils.call(b.this.e, IServiceManager.SERVICE_MEDIACENTER, "media_session_controller", "setMediaPackageControlSourceType", ecarx.xsf.mediacenter.session.adapter.c.a().a(aVar, (Class<Controller.a>) Controller.a.class), null);
        }
    }

    @Override // ecarx.xsf.mediacenter.session.EasMediaController
    public void setAppSourceConfig(List<AppSourceInfo> list) {
        Controller.a aVar = new Controller.a();
        if (list != null && list.size() != 0) {
            aVar.p = new Mediabean.a[list.size()];
            for (int i = 0; i < list.size(); i++) {
                aVar.p[i] = l.a(list.get(i));
            }
        }
        aVar.t = this.g;
        EASCallUtils.call(this.e, IServiceManager.SERVICE_MEDIACENTER, "media_session_controller", "setAppSourceConfig", ecarx.xsf.mediacenter.session.adapter.c.a().a(aVar, (Class<Controller.a>) Controller.a.class), null);
    }

    static /* synthetic */ String a(b bVar, String str) {
        return TextUtils.isEmpty(str) ? "" : str;
    }
}
