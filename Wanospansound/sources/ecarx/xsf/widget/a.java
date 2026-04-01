package ecarx.xsf.widget;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService;
import com.ecarx.eas.sdk.IServiceManager;
import com.ecarx.eas.sdk.mediacenter.b;
import com.ecarx.sdk.openapi.msg.EASFrameworkMessage;
import com.ecarx.sdk.openapi.msg.EASFrameworkRetMessage;
import ecarx.xsf.mediacenter.IMediaCenterWidgetApiSvc;
import ecarx.xsf.mediacenter.session.adapter.l;
import ecarx.xsf.widget.interfaces.IControl;

/* JADX INFO: loaded from: classes3.dex */
public class a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private IControl f827b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Handler f828c;
    private IReceiveWidgetInfoCallback e;
    private IMediaCenterWidgetApiSvc f;
    private IEASFrameworkService g;
    private b h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f826a = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private long f829d = 0;
    private byte[] i = new byte[0];
    private final ServiceConnection j = new ServiceConnection() { // from class: ecarx.xsf.widget.a.1
        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (iBinder != null) {
                IMediaCenterWidgetApiSvc iMediaCenterWidgetApiSvcAsInterface = IMediaCenterWidgetApiSvc.Stub.asInterface(iBinder);
                try {
                    iMediaCenterWidgetApiSvcAsInterface.setWidgetApiSvc(new ecarx.xsf.widget.b.a(a.this.e));
                    a.this.f827b = new ecarx.xsf.widget.a.a(iMediaCenterWidgetApiSvcAsInterface, null);
                    a.this.e.updateMediaList(iMediaCenterWidgetApiSvcAsInterface.getMediaListSourceType(), iMediaCenterWidgetApiSvcAsInterface.getMediaListType(), iMediaCenterWidgetApiSvcAsInterface.getPlayList());
                    if (iMediaCenterWidgetApiSvcAsInterface.getMusicPlaybackInfo() != null) {
                        a.this.e.updateMusicPlayInfo(iMediaCenterWidgetApiSvcAsInterface.getMusicPlaybackInfo());
                    }
                    a.this.e.updateMediaContent(a.this.f827b.getContentList());
                    a.this.e.updateMultiMediaListEx(a.this.f827b.getMultiMediaListEx());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                a.this.f826a = true;
                a.a(a.this, 0L);
                a.this.f828c.removeMessages(1);
            }
        }

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
            a.this.f826a = false;
            a.a(a.this, 0L);
            a.d(a.this);
        }

        @Override // android.content.ServiceConnection
        public final void onBindingDied(ComponentName componentName) {
            a.this.f826a = false;
            a.a(a.this, 0L);
            a.d(a.this);
        }

        @Override // android.content.ServiceConnection
        public final void onNullBinding(ComponentName componentName) {
            a.e(a.this);
        }
    };

    static /* synthetic */ long a(a aVar, long j) {
        aVar.f829d = 0L;
        return 0L;
    }

    public a() {
        Looper looperMyLooper = Looper.myLooper();
        if (looperMyLooper == null) {
            HandlerThread handlerThread = new HandlerThread("WidgetService");
            handlerThread.start();
            looperMyLooper = handlerThread.getLooper();
        }
        this.f828c = new HandlerC0115a(looperMyLooper);
    }

    public final void a(IBinder iBinder, IEASFrameworkService iEASFrameworkService) {
        if (iBinder != null) {
            this.f = IMediaCenterWidgetApiSvc.Stub.asInterface(iBinder);
        } else {
            this.f = null;
        }
        this.g = iEASFrameworkService;
    }

    public final void a(IReceiveWidgetInfoCallback iReceiveWidgetInfoCallback) {
        this.e = iReceiveWidgetInfoCallback;
        ecarx.xsf.widget.b.a aVar = new ecarx.xsf.widget.b.a(this.e);
        if (this.g != null) {
            b bVar = new b();
            this.h = bVar;
            a("registerWidgetEx", "widget", bVar.asBinder());
            this.h.a("WidgetApi", aVar);
        }
        try {
            this.f.setWidgetApiSvc(aVar);
            this.f827b = new ecarx.xsf.widget.a.a(this.f, this.g);
            this.e.updateMediaList(this.f.getMediaListSourceType(), this.f.getMediaListType(), this.f.getPlayList());
            if (this.f.getMusicPlaybackInfo() != null) {
                this.e.updateMusicPlayInfo(this.f.getMusicPlaybackInfo());
            }
            this.e.updateMediaContent(this.f827b.getContentList());
            this.e.updateMultiMediaListEx(this.f827b.getMultiMediaListEx());
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.f826a = true;
        this.f829d = 0L;
        this.f828c.removeMessages(1);
    }

    private String a(String str, String str2, IBinder iBinder) {
        if (this.g == null) {
            return null;
        }
        try {
            if (TextUtils.isEmpty(str2)) {
                str2 = "NoParam";
            }
            byte[] bArr = a(this.g, new EASFrameworkMessage(IServiceManager.SERVICE_MEDIACENTER, "MediaCenterAPI", str, str2.getBytes(), this.i), iBinder).mRetMsg.mData;
            if (bArr != null && bArr.length > 0) {
                return new String(bArr);
            }
        } catch (Exception unused) {
        }
        return null;
    }

    private static EASFrameworkRetMessage a(IEASFrameworkService iEASFrameworkService, EASFrameworkMessage eASFrameworkMessage, IBinder iBinder) {
        try {
            EASFrameworkRetMessage eASFrameworkRetMessageAsyncBinderCall = iEASFrameworkService.asyncBinderCall(eASFrameworkMessage, iBinder);
            if (eASFrameworkRetMessageAsyncBinderCall.mCode != 200) {
                Log.w("WidgetAPIImpl", "sendMsg fail:" + eASFrameworkRetMessageAsyncBinderCall.mCode + "," + eASFrameworkRetMessageAsyncBinderCall.mMsg);
            }
            return eASFrameworkRetMessageAsyncBinderCall;
        } catch (RemoteException unused) {
            return null;
        }
    }

    public final IControl a() {
        return this.f827b;
    }

    /* JADX INFO: renamed from: ecarx.xsf.widget.a$a, reason: collision with other inner class name */
    class HandlerC0115a extends Handler {
        HandlerC0115a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            if (message.what != 1 || a.this.f826a) {
                return;
            }
            a.g(a.this);
        }
    }

    static /* synthetic */ void d(a aVar) {
        long j = aVar.f829d;
        if (j < 60000) {
            aVar.f829d = j + 5000;
            aVar.f828c.sendEmptyMessageDelayed(1, 5000L);
        }
    }

    static /* synthetic */ void e(a aVar) {
        l.b().unbindService(aVar.j);
    }

    static /* synthetic */ void g(a aVar) {
        Intent intent = new Intent("ecarx.xsf.ACTION_MEDIA_CENTER_WIDGET_API_SERVICE");
        intent.setComponent(new ComponentName("ecarx.xsf.mediacenter", "ecarx.xsf.mediacenter.MediaCenterService"));
        l.b().bindService(intent, aVar.j, 1);
    }
}
