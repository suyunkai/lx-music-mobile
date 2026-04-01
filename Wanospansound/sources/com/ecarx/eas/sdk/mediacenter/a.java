package com.ecarx.eas.sdk.mediacenter;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.ecarx.eas.framework.sdk.Singleton;
import com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient;
import com.ecarx.eas.framework.sdk.common.internal.IApi;
import com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService;
import com.ecarx.eas.sdk.IServiceManager;
import com.ecarx.eas.sdk.mediacenter.callback.ApiCallback;
import com.ecarx.eas.sdk.mediacenter.control.IMediaControlClientAPI;
import com.ecarx.eas.sdk.mediacenter.control.IMediaControllerAPI;
import com.ecarx.eas.sdk.mediacenter.exception.MediaCenterException;
import com.ecarx.eas.sdk.vr.channel.VrChannelDataListener;
import com.ecarx.eas.sdk.vr.channel.VrChannelInfo;
import com.ecarx.eas.sdk.vr.channel.VrTtsResultListener;
import com.ecarx.eas.sdk.vr.music.VrMusicCtrlAPI;
import com.ecarx.eas.sdk.vr.news.VrNewsCtrlAPI;
import com.ecarx.eas.sdk.vr.radio.VrLocalRadioCtrlAPI;
import com.ecarx.eas.xsf.mediacenter.IExCallback;
import com.ecarx.eas.xsf.mediacenter.IExContent;
import com.ecarx.sdk.openapi.msg.EASFrameworkMessage;
import com.ecarx.sdk.openapi.msg.EASFrameworkRetMessage;
import ecarx.xsf.mediacenter.IMedia;
import ecarx.xsf.mediacenter.IMediaCenterClientToken;
import ecarx.xsf.mediacenter.IMediaCenterSvc;
import ecarx.xsf.mediacenter.bean.IMediaContentType;
import ecarx.xsf.mediacenter.control.IMediaControlClientApiSvc;
import ecarx.xsf.mediacenter.control.IMediaControllerApiSvc;
import ecarx.xsf.mediacenter.session.EasMediaController;
import ecarx.xsf.mediacenter.session.MediaErrorBean;
import ecarx.xsf.mediacenter.staterecover.IMusicRecoveryListener;
import ecarx.xsf.mediacenter.staterecover.IRecoveryMediaMetaInfo;
import ecarx.xsf.mediacenter.staterecover.IStateRecoverApiSvc;
import ecarx.xsf.mediacenter.vr.VrInternalAPI;
import ecarx.xsf.widget.WidgetAPI;
import ecarx.xsf.widget.interfaces.IControl;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class a extends IApi<IEASFrameworkService> implements InternalMediaCenterAPI {
    private static Singleton<a> q = new Singleton<a>() { // from class: com.ecarx.eas.sdk.mediacenter.a.1
        @Override // com.ecarx.eas.framework.sdk.Singleton
        protected final /* synthetic */ a create() {
            return new a();
        }
    };

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private volatile HandlerThread f95b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Handler f96c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Context f97d;
    private IMediaCenterSvc e;
    private IStateRecoverApiSvc f;
    private IExCallback g;
    private com.ecarx.eas.sdk.mediacenter.a.i h;
    private com.ecarx.eas.sdk.mediacenter.a.j i;
    private com.ecarx.eas.sdk.mediacenter.a.k j;
    private VrInternalAPI k;
    private WidgetAPI l;
    private b n;
    private j o;
    private ecarx.xsf.mediacenter.session.b p;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f94a = "EasMediaCenterAPIImpl";
    private byte[] m = new byte[0];

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    @Deprecated
    public final void connect(Object obj) throws IllegalArgumentException {
    }

    @Override // com.ecarx.eas.framework.sdk.common.internal.IApi
    public final /* synthetic */ void init(IInterface iInterface) {
        super.init((IEASFrameworkService) iInterface);
        this.f97d = EASFrameworkApiClient.getInstance().getAppContext();
        IBinder iBinderA = a("getMainBinder", "");
        this.e = iBinderA != null ? IMediaCenterSvc.Stub.asInterface(iBinderA) : null;
        IBinder iBinderA2 = a("getExBinder", "");
        this.g = iBinderA2 != null ? IExCallback.Stub.asInterface(iBinderA2) : null;
        IMediaCenterSvc iMediaCenterSvc = this.e;
        if (iMediaCenterSvc != null) {
            try {
                this.f = IStateRecoverApiSvc.Stub.asInterface(iMediaCenterSvc.getStateBinder());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            IMediaCenterSvc iMediaCenterSvc2 = this.e;
            com.ecarx.eas.sdk.mediacenter.a.i iVarA = com.ecarx.eas.sdk.mediacenter.a.i.a(this.f97d);
            this.h = iVarA;
            iVarA.a(iMediaCenterSvc2);
            com.ecarx.eas.sdk.mediacenter.a.j jVarA = com.ecarx.eas.sdk.mediacenter.a.j.a(this.f97d);
            this.i = jVarA;
            jVarA.a(iMediaCenterSvc2);
            com.ecarx.eas.sdk.mediacenter.a.k kVarA = com.ecarx.eas.sdk.mediacenter.a.k.a(this.f97d);
            this.j = kVarA;
            kVarA.a(iMediaCenterSvc2);
            this.k = ecarx.xsf.mediacenter.vr.b.a();
            this.l = WidgetAPI.get();
            this.n = new b();
            this.p = ecarx.xsf.mediacenter.session.b.a();
            Log.d("EasMediaCenterAPIImpl", " InitialImpl OK");
        }
    }

    public static a a() {
        return q.get();
    }

    private IBinder a(String str, String str2) {
        if (this.mService == 0) {
            return null;
        }
        try {
            if (TextUtils.isEmpty(str2)) {
                str2 = "NoParam";
            }
            EASFrameworkRetMessage eASFrameworkRetMessageA = a((IEASFrameworkService) this.mService, new EASFrameworkMessage(IServiceManager.SERVICE_MEDIACENTER, "MediaCenterAPI", str, str2.getBytes(), this.m));
            if (eASFrameworkRetMessageA != null) {
                return eASFrameworkRetMessageA.mRetMsg.mBinder;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String a(String str, String str2, IBinder iBinder) {
        if (this.mService == 0) {
            return null;
        }
        try {
            if (TextUtils.isEmpty(str2)) {
                str2 = "NoParam";
            }
            byte[] bArr = a((IEASFrameworkService) this.mService, new EASFrameworkMessage(IServiceManager.SERVICE_MEDIACENTER, "MediaCenterAPI", str, str2.getBytes(), this.m), iBinder).mRetMsg.mData;
            if (bArr != null && bArr.length > 0) {
                return new String(bArr);
            }
        } catch (Exception unused) {
        }
        return null;
    }

    private boolean a(Object obj, int i, int i2, String str, PendingIntent pendingIntent) {
        MediaErrorBean mediaErrorBean = new MediaErrorBean();
        mediaErrorBean.setErrorState(i2);
        mediaErrorBean.setPendingIntent(pendingIntent);
        mediaErrorBean.setSourceType(i);
        mediaErrorBean.setErrorMsg(str);
        try {
            return this.g.onExAction(2, "updateErrorStateAndPendingIntentEx", "", CommercialInfoHelper.getMediaErrorBeanIExContent(mediaErrorBean), ((IMediaCenterClientToken) obj).asBinder()) != null;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    private boolean a(Object obj, VrChannelInfo vrChannelInfo, int[] iArr, VrChannelDataListener vrChannelDataListener) {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        for (int i : iArr) {
            jSONArray.put(i);
        }
        try {
            jSONObject.put("vrChannelInfo", CommercialInfoHelper.getVrChannelInfo2Json(vrChannelInfo));
            jSONObject.put("semanticsTypes", jSONArray);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        String strA = a("declareVrSemanticsCapability", jSONObject.toString(), ((IMediaCenterClientToken) obj).asBinder());
        this.o.a(vrChannelDataListener);
        return strA != null;
    }

    private boolean a(Object obj, VrChannelInfo vrChannelInfo) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("vrChannelInfo", CommercialInfoHelper.getVrChannelInfo2Json(vrChannelInfo));
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return a("cancelVrSemanticsCapability", jSONObject.toString(), ((IMediaCenterClientToken) obj).asBinder()) != null;
    }

    private boolean a(Object obj, String str, String str2, VrTtsResultListener vrTtsResultListener) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(NotificationCompat.CATEGORY_MESSAGE, str2);
            jSONObject.put("hintId", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String strA = a("sendVrTtsActionResult", jSONObject.toString(), ((IMediaCenterClientToken) obj).asBinder());
        this.o.a(vrTtsResultListener);
        return strA != null;
    }

    private static EASFrameworkRetMessage a(IEASFrameworkService iEASFrameworkService, EASFrameworkMessage eASFrameworkMessage) {
        try {
            EASFrameworkRetMessage eASFrameworkRetMessageCall = iEASFrameworkService.call(eASFrameworkMessage);
            if (eASFrameworkRetMessageCall.mCode != 200) {
                Log.w("EasMediaCenterAPIImpl", "sendMsg fail:" + eASFrameworkRetMessageCall.mCode + "," + eASFrameworkRetMessageCall.mMsg);
            }
            return eASFrameworkRetMessageCall;
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static EASFrameworkRetMessage a(IEASFrameworkService iEASFrameworkService, EASFrameworkMessage eASFrameworkMessage, IBinder iBinder) {
        try {
            EASFrameworkRetMessage eASFrameworkRetMessageAsyncBinderCall = iEASFrameworkService.asyncBinderCall(eASFrameworkMessage, iBinder);
            if (eASFrameworkRetMessageAsyncBinderCall.mCode != 200) {
                Log.w("EasMediaCenterAPIImpl", "sendMsg fail:" + eASFrameworkRetMessageAsyncBinderCall.mCode + "," + eASFrameworkRetMessageAsyncBinderCall.mMsg);
            }
            return eASFrameworkRetMessageAsyncBinderCall;
        } catch (RemoteException unused) {
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final int getECarXAPIBaseVERSION_INT() {
        return ecarx.xsf.mediacenter.session.adapter.l.a();
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final VrMusicCtrlAPI getVrMusicApi() {
        com.ecarx.eas.sdk.mediacenter.a.j jVar = this.i;
        if (jVar != null) {
            return jVar;
        }
        return null;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final VrLocalRadioCtrlAPI getVrLocalRadioApi() {
        com.ecarx.eas.sdk.mediacenter.a.i iVar = this.h;
        if (iVar != null) {
            return iVar;
        }
        return null;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final VrNewsCtrlAPI getVrNewsApi() {
        com.ecarx.eas.sdk.mediacenter.a.k kVar = this.j;
        if (kVar != null) {
            return kVar;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    @Deprecated
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public IMediaCenterClientToken registerMusic(IMusicClient iMusicClient) {
        IMediaCenterSvc iMediaCenterSvc = this.e;
        if (iMediaCenterSvc == null) {
            Log.e("EasMediaCenterAPIImpl", "unbind media center service");
            return null;
        }
        try {
            return iMediaCenterSvc.registerMusic(new j(iMusicClient));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final Object registerMusic(String str, MusicClient musicClient) {
        if (this.e == null) {
            Log.e("EasMediaCenterAPIImpl", "unbind media center service");
            return null;
        }
        try {
            Log.d("EasMediaCenterAPIImpl", "svc: " + this.e);
            this.o = new j(musicClient);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("packageName", str);
                jSONObject.put("displayId", 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            IMediaCenterClientToken iMediaCenterClientTokenRegisterInMusic = this.e.registerInMusic(str, this.o);
            updateZoneId(iMediaCenterClientTokenRegisterInMusic, 0);
            Log.d("EasMediaCenterAPIImpl", "token: " + iMediaCenterClientTokenRegisterInMusic);
            a("registerEx", str, this.n.asBinder());
            IMediaCenterClientToken iMediaCenterClientToken = iMediaCenterClientTokenRegisterInMusic;
            a("registerClientWithRequest", jSONObject.toString(), iMediaCenterClientTokenRegisterInMusic.asBinder());
            this.n.a("MusicClient", this.o);
            return iMediaCenterClientTokenRegisterInMusic;
        } catch (Exception e2) {
            e2.printStackTrace();
            Log.d("EasMediaCenterAPIImpl", "Exception: " + Log.getStackTraceString(e2));
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final Object registerMusic(String str, MusicClient musicClient, String str2) {
        if (this.e == null) {
            Log.e("EasMediaCenterAPIImpl", "unbind media center service");
            return null;
        }
        try {
            Log.d("EasMediaCenterAPIImpl", "svc: " + this.e);
            this.o = new j(musicClient);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("packageName", str);
                jSONObject.put("mediaSessionPackageName", str2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            a("registerMediasessionEx", jSONObject.toString(), this.n.asBinder());
            IMediaCenterClientToken iMediaCenterClientTokenRegisterInMusic = this.e.registerInMusic(str, this.o);
            Log.d("EasMediaCenterAPIImpl", "token: " + iMediaCenterClientTokenRegisterInMusic);
            a("registerEx", str, this.n.asBinder());
            this.n.a("MusicClient", this.o);
            return iMediaCenterClientTokenRegisterInMusic;
        } catch (Exception e2) {
            e2.printStackTrace();
            Log.d("EasMediaCenterAPIImpl", "Exception: " + Log.getStackTraceString(e2));
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final Object registerMusic(MusicClient musicClient, RegisterRequest registerRequest) {
        if (this.e == null || musicClient == null || registerRequest == null) {
            Log.e("EasMediaCenterAPIImpl", "unbind media center service");
            return null;
        }
        Log.d("EasMediaCenterAPIImpl", "svc: " + this.e);
        String packageName = registerRequest.getPackageName();
        int displayId = registerRequest.getDisplayId();
        int zoneId = registerRequest.getZoneId();
        try {
            this.o = new j(musicClient);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("packageName", packageName);
                jSONObject.put("displayId", displayId);
            } catch (Exception e) {
                e.printStackTrace();
            }
            IMediaCenterClientToken iMediaCenterClientTokenRegisterInMusic = this.e.registerInMusic(packageName, this.o);
            Log.d("EasMediaCenterAPIImpl", "token: " + iMediaCenterClientTokenRegisterInMusic);
            a("registerEx", packageName, this.n.asBinder());
            IMediaCenterClientToken iMediaCenterClientToken = iMediaCenterClientTokenRegisterInMusic;
            a("registerClientWithRequest", jSONObject.toString(), iMediaCenterClientTokenRegisterInMusic.asBinder());
            this.n.a("MusicClient", this.o);
            if (zoneId != 0) {
                updateZoneId(iMediaCenterClientTokenRegisterInMusic, zoneId);
            }
            return iMediaCenterClientTokenRegisterInMusic;
        } catch (Exception e2) {
            e2.printStackTrace();
            Log.d("EasMediaCenterAPIImpl", "Exception: " + Log.getStackTraceString(e2));
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    @Deprecated
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public IMediaCenterClientToken registerNews(INewsClient iNewsClient) {
        IMediaCenterSvc iMediaCenterSvc = this.e;
        if (iMediaCenterSvc == null) {
            Log.e("EasMediaCenterAPIImpl", "unbind media center service");
            return null;
        }
        try {
            return iMediaCenterSvc.registerNews(new k(iNewsClient));
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final Object registerNews(String str, NewsClient newsClient) {
        IMediaCenterSvc iMediaCenterSvc = this.e;
        if (iMediaCenterSvc == null) {
            Log.e("EasMediaCenterAPIImpl", "unbind media center service");
            return null;
        }
        try {
            return iMediaCenterSvc.registerInNews(str, new k(newsClient));
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    @Deprecated
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public IMediaCenterClientToken registerVideo(IVideoClient iVideoClient) {
        IMediaCenterSvc iMediaCenterSvc = this.e;
        if (iMediaCenterSvc == null) {
            Log.e("EasMediaCenterAPIImpl", "unbind media center service");
            return null;
        }
        try {
            return iMediaCenterSvc.registerVideo(new o(iVideoClient));
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final Object registerVideo(String str, VideoClient videoClient) {
        IMediaCenterSvc iMediaCenterSvc = this.e;
        if (iMediaCenterSvc == null) {
            Log.e("EasMediaCenterAPIImpl", "unbind media center service");
            return null;
        }
        try {
            return iMediaCenterSvc.registerInVideo(str, new o(videoClient));
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final Object register(Binder binder) {
        throw new UnsupportedOperationException("Not implementation");
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean unregister(Object obj) {
        a(obj);
        IMediaCenterSvc iMediaCenterSvc = this.e;
        if (iMediaCenterSvc == null) {
            Log.e("EasMediaCenterAPIImpl", "unbind media center service");
            return false;
        }
        try {
            iMediaCenterSvc.unregister((IMediaCenterClientToken) obj);
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
            return true;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean requestPlay(Object obj) {
        a(obj);
        IMediaCenterSvc iMediaCenterSvc = this.e;
        if (iMediaCenterSvc == null) {
            Log.e("EasMediaCenterAPIImpl", "unbind media center service");
            return false;
        }
        try {
            return iMediaCenterSvc.requestPlay((IMediaCenterClientToken) obj);
        } catch (RemoteException e) {
            e.printStackTrace();
            return true;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final void abandonPlayFocus(Object obj) {
        a(obj);
        if (this.e == null) {
            Log.e("EasMediaCenterAPIImpl", "abandonPlayFocus unbind media center service");
        } else {
            a("abandonPlayFocus", "", ((IMediaCenterClientToken) obj).asBinder());
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    @Deprecated
    public final boolean updateVideoPlaybackState(Object obj, IVideoPlaybackInfo iVideoPlaybackInfo) {
        a(obj);
        if (this.e == null) {
            Log.e("EasMediaCenterAPIImpl", "unbind media center service");
            return false;
        }
        try {
            return this.e.updateVideoPlaybackState((IMediaCenterClientToken) obj, new p(iVideoPlaybackInfo));
        } catch (RemoteException e) {
            e.printStackTrace();
            return true;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean updateVideoPlaybackState(Object obj, VideoPlaybackInfo videoPlaybackInfo) {
        a(obj);
        if (this.e == null) {
            Log.e("EasMediaCenterAPIImpl", "unbind media center service");
            return false;
        }
        try {
            return this.e.updateVideoPlaybackState((IMediaCenterClientToken) obj, new p(videoPlaybackInfo));
        } catch (RemoteException e) {
            e.printStackTrace();
            return true;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    @Deprecated
    public final boolean updateNewsPlaybackState(Object obj, INewsPlaybackInfo iNewsPlaybackInfo) {
        a(obj);
        if (this.e == null) {
            Log.e("EasMediaCenterAPIImpl", "unbind media center service");
            return false;
        }
        try {
            return this.e.updateNewsPlaybackState((IMediaCenterClientToken) obj, new l(iNewsPlaybackInfo));
        } catch (RemoteException e) {
            e.printStackTrace();
            return true;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean updateNewsPlaybackState(Object obj, NewsPlaybackInfo newsPlaybackInfo) {
        a(obj);
        if (this.e == null) {
            Log.e("EasMediaCenterAPIImpl", "unbind media center service");
            return false;
        }
        try {
            return this.e.updateNewsPlaybackState((IMediaCenterClientToken) obj, new l(newsPlaybackInfo));
        } catch (RemoteException e) {
            e.printStackTrace();
            return true;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean updateMediaSourceTypeList(Object obj, int[] iArr) {
        a(obj);
        IMediaCenterSvc iMediaCenterSvc = this.e;
        if (iMediaCenterSvc == null) {
            Log.e("EasMediaCenterAPIImpl", "unbind media center service");
            return false;
        }
        try {
            iMediaCenterSvc.updateMediaSourceTypeList((IMediaCenterClientToken) obj, iArr);
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
            return true;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final void updateCurrentSourceType(Object obj, int i) {
        a(obj);
        IMediaCenterSvc iMediaCenterSvc = this.e;
        if (iMediaCenterSvc == null) {
            Log.e("EasMediaCenterAPIImpl", "unbind media center service");
            return;
        }
        try {
            iMediaCenterSvc.updateCurrentSourceType((IMediaCenterClientToken) obj, i);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    @Deprecated
    public final void updatePlaylist(Object obj, int i, List<IMediaInfo> list) {
        a(obj);
        IMediaCenterSvc iMediaCenterSvc = this.e;
        if (iMediaCenterSvc == null) {
            Log.e("EasMediaCenterAPIImpl", "unbind media center service");
            return;
        }
        try {
            iMediaCenterSvc.updatePlaylist((IMediaCenterClientToken) obj, i, d.a(list));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final void updateCurrentPlaylist(Object obj, int i, List<MediaInfo> list) {
        a(obj);
        try {
            this.e.updatePlaylist((IMediaCenterClientToken) obj, i, d.b(list));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final void updateCurrentProgress(Object obj, long j) {
        a(obj);
        IMediaCenterSvc iMediaCenterSvc = this.e;
        if (iMediaCenterSvc == null) {
            Log.e("EasMediaCenterAPIImpl", "unbind media center service");
            return;
        }
        try {
            iMediaCenterSvc.updateCurrentProgress((IMediaCenterClientToken) obj, j);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    @Deprecated
    public final boolean updateMusicPlaybackState(Object obj, IMusicPlaybackInfo iMusicPlaybackInfo) {
        a(obj);
        if (this.e == null) {
            Log.e("EasMediaCenterAPIImpl", "unbind media center service");
            return false;
        }
        try {
            return this.e.updateMusicPlaybackState((IMediaCenterClientToken) obj, new m(iMusicPlaybackInfo));
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean updateMusicPlaybackState(Object obj, MusicPlaybackInfo musicPlaybackInfo) {
        a(obj);
        if (this.e == null) {
            Log.e("EasMediaCenterAPIImpl", "unbind media center service");
            return false;
        }
        try {
            return this.e.updateMusicPlaybackState((IMediaCenterClientToken) obj, new m(musicPlaybackInfo));
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean declareSupportCollectTypes(Object obj, int[] iArr) {
        a(obj);
        IMediaCenterSvc iMediaCenterSvc = this.e;
        if (iMediaCenterSvc == null) {
            Log.e("EasMediaCenterAPIImpl", "unbind media center service");
            return false;
        }
        try {
            return iMediaCenterSvc.declareSupportCollectTypes((IMediaCenterClientToken) obj, iArr);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean cancelSupportCollectTypes(Object obj, int[] iArr) {
        a(obj);
        IMediaCenterSvc iMediaCenterSvc = this.e;
        if (iMediaCenterSvc == null) {
            Log.e("EasMediaCenterAPIImpl", "unbind media center service");
            return false;
        }
        try {
            return iMediaCenterSvc.cancelSupportCollectTypes((IMediaCenterClientToken) obj, iArr);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean declareSupportDownloadTypes(Object obj, int[] iArr) {
        a(obj);
        IMediaCenterSvc iMediaCenterSvc = this.e;
        if (iMediaCenterSvc == null) {
            Log.e("EasMediaCenterAPIImpl", "unbind media center service");
            return false;
        }
        try {
            return iMediaCenterSvc.declareSupportDownloadTypes((IMediaCenterClientToken) obj, iArr);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean cancelSupportDownloadTypes(Object obj, int[] iArr) {
        a(obj);
        IMediaCenterSvc iMediaCenterSvc = this.e;
        if (iMediaCenterSvc == null) {
            Log.e("EasMediaCenterAPIImpl", "unbind media center service");
            return false;
        }
        try {
            return iMediaCenterSvc.cancelSupportDownloadTypes((IMediaCenterClientToken) obj, iArr);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean updateCurrentRecommendInfo(Object obj, RecommendInfo recommendInfo) {
        a(obj);
        IMediaCenterSvc iMediaCenterSvc = this.e;
        if (iMediaCenterSvc == null) {
            Log.e("EasMediaCenterAPIImpl", "unbind media center service");
            return false;
        }
        try {
            return iMediaCenterSvc.updateCurrentRecommendInfo((IMediaCenterClientToken) obj, new n(recommendInfo));
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final void updateCurrentLyric(Object obj, String str) {
        a(obj);
        IMediaCenterSvc iMediaCenterSvc = this.e;
        if (iMediaCenterSvc == null) {
            Log.e("EasMediaCenterAPIImpl", "unbind media center service");
            return;
        }
        try {
            iMediaCenterSvc.updateCurrentLyric((IMediaCenterClientToken) obj, str);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final void updateMediaList(Object obj, MediaListInfo mediaListInfo) {
        a(obj);
        IMediaCenterSvc iMediaCenterSvc = this.e;
        if (iMediaCenterSvc == null) {
            Log.d("EasMediaCenterAPIImpl", "unbind media center service");
            return;
        }
        try {
            iMediaCenterSvc.updateMediaList((IMediaCenterClientToken) obj, mediaListInfo.getSourceType(), mediaListInfo.getMediaListType(), d.b(mediaListInfo.getMediaList()));
            this.e.updateMediaPlayList((IMediaCenterClientToken) obj, CommercialInfoHelper.getIMediaList(mediaListInfo));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final void updateMediaListWithPagination(Object obj, int i, int i2, int i3, MediaListInfo mediaListInfo) {
        a(obj);
        if (this.e == null) {
            Log.d("EasMediaCenterAPIImpl", "unbind media center service");
            return;
        }
        try {
            IExContent iExContent = new IExContent();
            ArrayList arrayList = new ArrayList();
            if (mediaListInfo.getPendingIntent() != null) {
                arrayList.add(mediaListInfo.getPendingIntent());
            }
            iExContent.setPendingIntents(arrayList);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("currentPage", i);
            jSONObject.put("totalPage", i2);
            jSONObject.put("totalSize", i3);
            jSONObject.put("jsonIMediaList", CommercialInfoHelper.getIMediaListToJson(CommercialInfoHelper.getIMediaList(mediaListInfo)));
            iExContent.setData(jSONObject.toString());
            this.g.onExAction(3, "updateMediaListWithPagination", "", iExContent, ((IMediaCenterClientToken) obj).asBinder());
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("EasMediaCenterAPIImpl", "updateMediaListWithPagination fail");
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final void updateErrorMsg(Object obj, int i, String str) {
        a(obj);
        IMediaCenterSvc iMediaCenterSvc = this.e;
        if (iMediaCenterSvc == null) {
            Log.d("EasMediaCenterAPIImpl", "unbind media center service");
            return;
        }
        try {
            iMediaCenterSvc.updateErrorMsg((IMediaCenterClientToken) obj, i, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final void updateErrorStateAndPendingIntent(Object obj, int i, int i2, String str, PendingIntent pendingIntent) {
        a(obj);
        if (this.e == null) {
            Log.d("EasMediaCenterAPIImpl", "unbind media center service");
            return;
        }
        try {
            a((IMediaCenterClientToken) obj, i, i2, str, pendingIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final void declareMediaCenterCapability(Object obj, int[] iArr) {
        a(obj);
        IMediaCenterSvc iMediaCenterSvc = this.e;
        if (iMediaCenterSvc == null) {
            Log.d("EasMediaCenterAPIImpl", "unbind media center service");
            return;
        }
        try {
            iMediaCenterSvc.declareMediaCenterCapability((IMediaCenterClientToken) obj, iArr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final IMediaControlClientAPI getMediaControlClientApi() {
        if (this.e == null) {
            Log.d("EasMediaCenterAPIImpl", "unbind media center service");
            return null;
        }
        try {
            return com.ecarx.eas.sdk.mediacenter.control.a.a().a(IMediaControlClientApiSvc.Stub.asInterface(this.e.getMediaControlClientApi()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final IMediaControllerAPI getMediaControllerApi() {
        if (this.e == null) {
            Log.d("EasMediaCenterAPIImpl", "unbind media center service");
            return null;
        }
        try {
            return com.ecarx.eas.sdk.mediacenter.control.c.a().a(IMediaControllerApiSvc.Stub.asInterface(this.e.getMediaControllerApi()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean declareVrChannelCapability(Object obj, VrChannelInfo vrChannelInfo, VrChannelDataListener vrChannelDataListener) throws MediaCenterException {
        a(obj);
        IMediaCenterSvc iMediaCenterSvc = this.e;
        if (iMediaCenterSvc == null) {
            Log.d("EasMediaCenterAPIImpl", "declareVrChannelCapability, but unbind media center service");
            throw new com.ecarx.eas.sdk.mediacenter.exception.a();
        }
        try {
            return iMediaCenterSvc.declareVrChannelCapability((IMediaCenterClientToken) obj, VrChannelInfo.wrap(vrChannelInfo), new com.ecarx.eas.sdk.mediacenter.a.a.a(vrChannelDataListener));
        } catch (Throwable th) {
            th.printStackTrace();
            throw new com.ecarx.eas.sdk.mediacenter.exception.a(th.getMessage());
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean cancelVrChannelCapability(Object obj, VrChannelInfo vrChannelInfo) throws MediaCenterException {
        a(obj);
        IMediaCenterSvc iMediaCenterSvc = this.e;
        if (iMediaCenterSvc == null) {
            Log.d("EasMediaCenterAPIImpl", "cancelVrChannelCapability, but unbind media center service");
            throw new com.ecarx.eas.sdk.mediacenter.exception.a();
        }
        try {
            return iMediaCenterSvc.cancelVrChannelCapability((IMediaCenterClientToken) obj, VrChannelInfo.wrap(vrChannelInfo));
        } catch (Throwable th) {
            th.printStackTrace();
            throw new com.ecarx.eas.sdk.mediacenter.exception.a(th.getMessage());
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean asyncSendVrChannelResult(Object obj, VrChannelInfo vrChannelInfo, String str) throws MediaCenterException {
        a(obj);
        IMediaCenterSvc iMediaCenterSvc = this.e;
        if (iMediaCenterSvc == null) {
            Log.d("EasMediaCenterAPIImpl", "asyncSendVrChannelResult, but unbind media center service");
            throw new com.ecarx.eas.sdk.mediacenter.exception.a();
        }
        try {
            return iMediaCenterSvc.asyncSendVrChannelResult((IMediaCenterClientToken) obj, VrChannelInfo.wrap(vrChannelInfo), str);
        } catch (Throwable th) {
            th.printStackTrace();
            throw new com.ecarx.eas.sdk.mediacenter.exception.a(th.getMessage());
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean updateMediaContent(Object obj, List<ContentInfo> list) throws MediaCenterException {
        a(obj);
        IMediaCenterSvc iMediaCenterSvc = this.e;
        if (iMediaCenterSvc == null) {
            Log.d("EasMediaCenterAPIImpl", "unbind media center service");
            return false;
        }
        try {
            return iMediaCenterSvc.updateMediaContent((IMediaCenterClientToken) obj, CommercialInfoHelper.convertToIContent(list));
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean updateMultiMediaList(Object obj, MediaListsInfo mediaListsInfo) throws MediaCenterException {
        Log.d("EasMediaCenterAPIImpl", "updateMultiMediaList");
        a(obj);
        if (this.e == null) {
            Log.d("EasMediaCenterAPIImpl", "unbind media center service");
            return false;
        }
        return a(obj, mediaListsInfo);
    }

    private boolean a(Object obj, MediaListsInfo mediaListsInfo) throws MediaCenterException {
        try {
            return this.g.onExAction(1, "updateMultiMediaListEx", "", CommercialInfoHelper.getMediaListsInfo2IExContent(mediaListsInfo), ((IMediaCenterClientToken) obj).asBinder()) != null;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean registerMusicRecoveryIntent(Object obj, int i, Intent intent) throws MediaCenterException {
        a(obj);
        IStateRecoverApiSvc iStateRecoverApiSvc = this.f;
        if (iStateRecoverApiSvc == null) {
            Log.d("EasMediaCenterAPIImpl", "registerMusicRecoveryIntent, but unbind media center service");
            throw new com.ecarx.eas.sdk.mediacenter.exception.a();
        }
        try {
            return iStateRecoverApiSvc.registerMusicRecoveryIntent((IMediaCenterClientToken) obj, i, a(intent));
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean unRegisterMusicRecoveryIntent(Object obj) throws MediaCenterException {
        a(obj);
        IStateRecoverApiSvc iStateRecoverApiSvc = this.f;
        if (iStateRecoverApiSvc == null) {
            Log.d("EasMediaCenterAPIImpl", "unRegisterMusicRecoveryIntent, but unbind media center service");
            throw new com.ecarx.eas.sdk.mediacenter.exception.a();
        }
        try {
            return iStateRecoverApiSvc.unRegisterMusicRecoveryIntent((IMediaCenterClientToken) obj);
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final MediaListInfo getRecoveryMediaList(Object obj) throws MediaCenterException {
        a(obj);
        IStateRecoverApiSvc iStateRecoverApiSvc = this.f;
        if (iStateRecoverApiSvc == null) {
            Log.d("EasMediaCenterAPIImpl", "getRecoveryMediaList, but unbind media center service");
            throw new com.ecarx.eas.sdk.mediacenter.exception.a();
        }
        try {
            return a(iStateRecoverApiSvc.getRecoveryMediaList((IMediaCenterClientToken) obj));
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final MusicPlaybackInfo getRecoveryMusicPlaybackInfo(Object obj) throws MediaCenterException {
        a(obj);
        IStateRecoverApiSvc iStateRecoverApiSvc = this.f;
        if (iStateRecoverApiSvc == null) {
            Log.d("EasMediaCenterAPIImpl", "getRecoveryMusicPlaybackInfo, but unbind media center service");
            throw new com.ecarx.eas.sdk.mediacenter.exception.a();
        }
        try {
            return a(iStateRecoverApiSvc.getRecoveryMusicPlaybackInfo((IMediaCenterClientToken) obj));
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final void setMusicRecoveryCallback(Object obj, final IMusicRecoveryCallback iMusicRecoveryCallback) throws MediaCenterException {
        a(obj);
        IStateRecoverApiSvc iStateRecoverApiSvc = this.f;
        if (iStateRecoverApiSvc == null) {
            Log.d("EasMediaCenterAPIImpl", "registerMusicRecoveryIntent, but unbind media center service");
            throw new com.ecarx.eas.sdk.mediacenter.exception.a();
        }
        try {
            iStateRecoverApiSvc.setMusicRecoveryCallback((IMediaCenterClientToken) obj, new IMusicRecoveryListener.Stub() { // from class: com.ecarx.eas.sdk.mediacenter.a.2
                @Override // ecarx.xsf.mediacenter.staterecover.IMusicRecoveryListener
                public final void onGetMediaList(IRecoveryMediaMetaInfo iRecoveryMediaMetaInfo) throws RemoteException {
                    MediaListInfo mediaListInfoA = a.this.a(iRecoveryMediaMetaInfo);
                    IMusicRecoveryCallback iMusicRecoveryCallback2 = iMusicRecoveryCallback;
                    if (iMusicRecoveryCallback2 != null) {
                        iMusicRecoveryCallback2.onGetMediaList(mediaListInfoA);
                    }
                }

                @Override // ecarx.xsf.mediacenter.staterecover.IMusicRecoveryListener
                public final void onGetMusicPlaybackInfo(ecarx.xsf.mediacenter.IMusicPlaybackInfo iMusicPlaybackInfo) throws RemoteException {
                    MusicPlaybackInfo musicPlaybackInfoA = a.this.a(iMusicPlaybackInfo);
                    IMusicRecoveryCallback iMusicRecoveryCallback2 = iMusicRecoveryCallback;
                    if (iMusicRecoveryCallback2 != null) {
                        iMusicRecoveryCallback2.onGetMusicPlaybackInfo(musicPlaybackInfoA);
                    }
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final void onMusicRecoveryComplete(Object obj) throws MediaCenterException {
        a(obj);
        IStateRecoverApiSvc iStateRecoverApiSvc = this.f;
        if (iStateRecoverApiSvc == null) {
            Log.d("EasMediaCenterAPIImpl", "registerMusicRecoveryIntent, but unbind media center service");
            throw new com.ecarx.eas.sdk.mediacenter.exception.a();
        }
        try {
            iStateRecoverApiSvc.onMusicRecoveryComplete((IMediaCenterClientToken) obj);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final void updateCollectMsg(Object obj, int i, String str) throws MediaCenterException {
        a(obj);
        if (this.e == null) {
            Log.d("EasMediaCenterAPIImpl", "updateCollectMsg, but unbind media center service");
            throw new com.ecarx.eas.sdk.mediacenter.exception.a();
        }
        try {
            Log.d("EasMediaCenterAPIImpl", "updateCollectMsg resultCode = " + i + " message = " + str);
            this.e.updateCollectMsg((IMediaCenterClientToken) obj, i, str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final String queryCurrentFocusClient(Object obj) throws MediaCenterException {
        a(obj);
        if (this.e == null) {
            Log.d("EasMediaCenterAPIImpl", "queryCurrentFocusClient, but unbind media center service");
            throw new com.ecarx.eas.sdk.mediacenter.exception.a();
        }
        try {
            Log.d("EasMediaCenterAPIImpl", "queryCurrentFocusClient");
            return this.e.queryCurrentFocusClient((IMediaCenterClientToken) obj);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final void updateMediaContentTypeList(Object obj, List<IMediaContentType> list) {
        a(obj);
        IMediaCenterSvc iMediaCenterSvc = this.e;
        if (iMediaCenterSvc == null) {
            Log.d("EasMediaCenterAPIImpl", "unbind media center service");
            return;
        }
        try {
            iMediaCenterSvc.updateMediaContentTypeList((IMediaCenterClientToken) obj, list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final void updateCollectMsg(Object obj, int i, int i2, String str, int i3, String str2) throws MediaCenterException {
        a(obj);
        if (this.e == null) {
            Log.d("EasMediaCenterAPIImpl", "updateCollectMsg, but unbind media center service");
            throw new com.ecarx.eas.sdk.mediacenter.exception.a();
        }
        try {
            Log.d("EasMediaCenterAPIImpl", "updateCollectMsg type = " + i + "operation = " + i2 + " uuid = " + str + " resultCode = " + i3 + " message = " + str2);
            this.e.updateCollectMsgByUUID((IMediaCenterClientToken) obj, i, i2, str, i3, str2);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean declareVrSemanticsCapability(Object obj, VrChannelInfo vrChannelInfo, int[] iArr, VrChannelDataListener vrChannelDataListener) throws MediaCenterException {
        Log.d("EasMediaCenterAPIImpl", "declareVrSemanticsCapability");
        a(obj);
        if (this.e == null) {
            Log.d("EasMediaCenterAPIImpl", "unbind media center service");
            return false;
        }
        return a(obj, vrChannelInfo, iArr, vrChannelDataListener);
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean cancelVrSemanticsCapability(Object obj, VrChannelInfo vrChannelInfo) throws MediaCenterException {
        Log.d("EasMediaCenterAPIImpl", "declareVrSemanticsCapability");
        a(obj);
        if (this.e == null) {
            Log.d("EasMediaCenterAPIImpl", "unbind media center service");
            return false;
        }
        return a(obj, vrChannelInfo);
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean sendVrTtsActionResult(Object obj, String str, String str2, VrTtsResultListener vrTtsResultListener) throws MediaCenterException {
        Log.d("EasMediaCenterAPIImpl", "SendVrTtsActionResult");
        a(obj);
        if (this.e == null) {
            Log.d("EasMediaCenterAPIImpl", "unbind media center service");
            return false;
        }
        return a(obj, str, str2, vrTtsResultListener);
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final Object registerMusicWithZoneId(int i, String str, MusicClient musicClient) {
        Log.d("EasMediaCenterAPIImpl", "registerMusicWithZoneId");
        if (this.e == null) {
            Log.e("EasMediaCenterAPIImpl", "unbind media center service");
            return null;
        }
        try {
            Log.d("EasMediaCenterAPIImpl", "registerMusicWithZoneId svc: " + this.e);
            j jVar = new j(musicClient);
            this.o = jVar;
            IMediaCenterClientToken iMediaCenterClientTokenRegisterInMusic = this.e.registerInMusic(str, jVar);
            updateZoneId(iMediaCenterClientTokenRegisterInMusic, i);
            Log.d("EasMediaCenterAPIImpl", "token: " + iMediaCenterClientTokenRegisterInMusic);
            a("registerEx", str, this.n.asBinder());
            this.n.a("MusicClient", this.o);
            return iMediaCenterClientTokenRegisterInMusic;
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("EasMediaCenterAPIImpl", "Exception: " + Log.getStackTraceString(e));
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final void updateZoneId(Object obj, int i) {
        a(obj);
        if (this.mService == 0) {
            return;
        }
        a("updateZoneId", String.valueOf(i), ((IMediaCenterClientToken) obj).asBinder());
    }

    private static void a(Object obj) {
        if (!(obj instanceof IMediaCenterClientToken)) {
            throw new IllegalArgumentException("token is invalid");
        }
    }

    private static String a(Intent intent) {
        if (intent != null && !TextUtils.isEmpty(intent.getAction())) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("state_action", intent.getAction());
                jSONObject.put("state_package", intent.getPackage());
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    for (String str : extras.keySet()) {
                        jSONObject.put(str, extras.get(str));
                    }
                }
                return jSONObject.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MediaListInfo a(final IRecoveryMediaMetaInfo iRecoveryMediaMetaInfo) {
        if (iRecoveryMediaMetaInfo == null) {
            return null;
        }
        return new MediaListInfo(this) { // from class: com.ecarx.eas.sdk.mediacenter.a.3
            @Override // com.ecarx.eas.sdk.mediacenter.MediaListInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaListInfo
            public final String getTitle() {
                return iRecoveryMediaMetaInfo.getTitle();
            }

            @Override // com.ecarx.eas.sdk.mediacenter.MediaListInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaListInfo
            public final int getSourceType() {
                return iRecoveryMediaMetaInfo.getSourceType();
            }

            @Override // com.ecarx.eas.sdk.mediacenter.MediaListInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaListInfo
            public final String getMediaListId() {
                return iRecoveryMediaMetaInfo.getMediaListId();
            }

            @Override // com.ecarx.eas.sdk.mediacenter.MediaListInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaListInfo
            public final int getMediaListType() {
                return iRecoveryMediaMetaInfo.getMediaListType();
            }

            @Override // com.ecarx.eas.sdk.mediacenter.MediaListInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaListInfo
            public final List<MediaInfo> getMediaList() {
                List<IMedia> mediaList = iRecoveryMediaMetaInfo.getMediaList();
                if (mediaList == null) {
                    return null;
                }
                if (mediaList.isEmpty()) {
                    return new ArrayList();
                }
                ArrayList arrayList = new ArrayList();
                for (final IMedia iMedia : mediaList) {
                    arrayList.add(new MediaInfo(this) { // from class: com.ecarx.eas.sdk.mediacenter.a.3.1
                        @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
                        public final String getUuid() {
                            return iMedia.getUuid();
                        }

                        @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
                        public final String getTitle() {
                            return iMedia.getTitle();
                        }

                        @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
                        public final String getArtist() {
                            return iMedia.getArtist();
                        }

                        @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
                        public final String getAlbum() {
                            return iMedia.getAlbum();
                        }

                        @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
                        public final String getAuthor() {
                            return iMedia.getAuthor();
                        }

                        @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
                        public final String getComposer() {
                            return iMedia.getComposer();
                        }

                        @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
                        public final String getDescription() {
                            return iMedia.getDescription();
                        }

                        @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
                        public final String getSubtitle() {
                            return iMedia.getSubtitle();
                        }

                        @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
                        public final String getRating() {
                            return iMedia.getRating();
                        }

                        @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
                        public final String getYear() {
                            return iMedia.getYear();
                        }

                        @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
                        public final long getDuration() {
                            return iMedia.getDuration();
                        }

                        @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
                        public final int getPlayingItemPositionInQueue() {
                            return iMedia.getPlayingItemPositionInQueue();
                        }

                        @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
                        public final int getAlbumIndex() {
                            return iMedia.getAlbumIndex();
                        }

                        @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
                        public final String getCategoryStr() {
                            return iMedia.getCategoryStr();
                        }

                        @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
                        public final String getSubCategoryStr() {
                            return iMedia.getSubCategoryStr();
                        }

                        @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
                        public final String getMediaId() {
                            return iMedia.getMediaId();
                        }

                        @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
                        public final String getMediaCp() {
                            return iMedia.getMediaCp();
                        }

                        @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
                        public final String getTargetType() {
                            return iMedia.getTargetType();
                        }

                        @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
                        public final int getSourceType() {
                            return iMedia.getSourceType();
                        }

                        @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
                        public final Uri getMediaPath() {
                            return iMedia.getMediaPath();
                        }

                        @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
                        public final String getLyricContent() {
                            return iMedia.getLyricContent();
                        }

                        @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
                        public final Uri getLyric() {
                            return iMedia.getLyric();
                        }

                        @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
                        public final Uri getArtwork() {
                            return iMedia.getArtwork();
                        }

                        @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
                        public final String getRadioFrequency() {
                            return iMedia.getRadioFrequency();
                        }

                        @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
                        public final String getRadioStationName() {
                            return iMedia.getRadioStationName();
                        }

                        @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
                        public final String getPlayingMediaListId() {
                            return iMedia.getPlayingMediaListId();
                        }

                        @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
                        public final int getVip() {
                            return iMedia.getVip();
                        }

                        @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
                        public final int getPlayingMediaListType() {
                            return iMedia.getPlayingMediaListType();
                        }

                        @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
                        public final int getSupportCollect() {
                            return iMedia.getSupportCollect();
                        }

                        @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
                        public final int getCollected() {
                            return iMedia.getCollected();
                        }
                    });
                }
                return arrayList;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MusicPlaybackInfo a(final ecarx.xsf.mediacenter.IMusicPlaybackInfo iMusicPlaybackInfo) {
        if (iMusicPlaybackInfo == null) {
            return null;
        }
        return new MusicPlaybackInfo(this) { // from class: com.ecarx.eas.sdk.mediacenter.a.4
            @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
            public final PendingIntent getLaunchIntent() {
                try {
                    return iMusicPlaybackInfo.getLaunchIntent();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
            public final String getTitle() {
                try {
                    return iMusicPlaybackInfo.getTitle();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
            public final String getArtist() {
                try {
                    return iMusicPlaybackInfo.getArtist();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
            public final String getAlbum() {
                try {
                    return iMusicPlaybackInfo.getAlbum();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
            public final String getRadioFrequency() {
                try {
                    return iMusicPlaybackInfo.getRadioFrequency();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
            public final String getRadioStationName() {
                try {
                    return iMusicPlaybackInfo.getRadioStationName();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
            public final long getDuration() {
                try {
                    return iMusicPlaybackInfo.getDuration();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return 0L;
                }
            }

            @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
            public final int getPlayingItemPositionInQueue() {
                try {
                    return iMusicPlaybackInfo.getPlayingItemPositionInQueue();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return 0;
                }
            }

            @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
            public final int getSourceType() {
                try {
                    return iMusicPlaybackInfo.getSourceType();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return 0;
                }
            }

            @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
            public final Uri getMediaPath() {
                try {
                    return iMusicPlaybackInfo.getMediaPath();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
            public final int getPlaybackStatus() {
                try {
                    return iMusicPlaybackInfo.getPlaybackStatus();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return 0;
                }
            }

            @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
            public final String getLyricContent() {
                try {
                    return iMusicPlaybackInfo.getLyricContent();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
            public final Uri getLyric() {
                try {
                    return iMusicPlaybackInfo.getLyric();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
            public final String getCurrentLyricSentence() {
                try {
                    return iMusicPlaybackInfo.getCurrentLyricSentence();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
            public final Uri getPreviousArtwork() {
                try {
                    return iMusicPlaybackInfo.getPreviousArtwork();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
            public final Uri getArtwork() {
                try {
                    return iMusicPlaybackInfo.getArtwork();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
            public final Uri getNextArtwork() {
                try {
                    return iMusicPlaybackInfo.getNextArtwork();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
            public final int getLoopMode() {
                try {
                    return iMusicPlaybackInfo.getLoopMode();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return 0;
                }
            }

            @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
            public final int getRadioMode() {
                try {
                    return iMusicPlaybackInfo.getRadioMode();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return 0;
                }
            }

            @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
            public final boolean isSupportCollect() {
                try {
                    return iMusicPlaybackInfo.isSupportCollect();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return false;
                }
            }

            @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
            public final boolean isCollected() {
                try {
                    return iMusicPlaybackInfo.isCollected();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return false;
                }
            }

            @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
            public final boolean isSupportDownload() {
                try {
                    return iMusicPlaybackInfo.isSupportDownload();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return false;
                }
            }

            @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
            public final boolean isDownloaded() {
                try {
                    return iMusicPlaybackInfo.isDownloaded();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return false;
                }
            }

            @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
            public final String getUuid() {
                try {
                    return iMusicPlaybackInfo.getUuid();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
            public final String getAppName() {
                try {
                    return iMusicPlaybackInfo.getAppName();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
            public final String getAppIcon() {
                try {
                    return iMusicPlaybackInfo.getAppIcon();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
            public final String getPackageName() {
                try {
                    return iMusicPlaybackInfo.getPackageName();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
            public final boolean isSupportLoopModeSwitch() {
                try {
                    return iMusicPlaybackInfo.isSupportLoopModeSwitch();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return false;
                }
            }

            @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
            public final boolean isSupportVrCtrlPlayStatus() {
                try {
                    return iMusicPlaybackInfo.isSupportVrCtrlPlayStatus();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return false;
                }
            }

            @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
            public final String getPlayingMediaListId() {
                try {
                    return iMusicPlaybackInfo.getPlayingMediaListId();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
            public final int getVip() {
                try {
                    return iMusicPlaybackInfo.getVip();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return 0;
                }
            }

            @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
            public final int getPlayingMediaListType() {
                try {
                    return iMusicPlaybackInfo.getPlayingMediaListType();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return -1;
                }
            }

            @Override // com.ecarx.eas.sdk.mediacenter.MusicPlaybackInfo, com.ecarx.eas.sdk.mediacenter.AbstractMusicPlaybackInfo
            public final PendingIntent getPlayerIntent() {
                try {
                    return iMusicPlaybackInfo.getPlayerIntent();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };
    }

    @Override // com.ecarx.eas.sdk.mediacenter.InternalMediaCenterAPI
    public final VrInternalAPI getVrInternalApi(final ApiCallback<VrInternalAPI> apiCallback) {
        if (this.k == null) {
            return null;
        }
        a(new Runnable() { // from class: com.ecarx.eas.sdk.mediacenter.a.5
            @Override // java.lang.Runnable
            public final void run() {
                IBinder iBinderA = a.a(a.this);
                a.this.k.updateMediaCenterSvc(iBinderA);
                a.this.k.updateIEASFrameworkService((IEASFrameworkService) a.this.mService);
                if (iBinderA != null) {
                    apiCallback.onDataReceived(a.this.k);
                } else {
                    apiCallback.onDataReceived(null);
                }
            }
        });
        return null;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.InternalMediaCenterAPI
    public final IControl getWidgetApi(final ApiCallback<IControl> apiCallback) {
        if (this.l == null) {
            return null;
        }
        a(new Runnable() { // from class: com.ecarx.eas.sdk.mediacenter.a.6
            @Override // java.lang.Runnable
            public final void run() {
                IBinder iBinderD = a.d(a.this);
                a.this.l.updateMediaCenterSvc(iBinderD);
                a.this.l.updateIEASFrameworkService((IEASFrameworkService) a.this.mService);
                if (iBinderD != null) {
                    apiCallback.onDataReceived(a.this.l);
                } else {
                    apiCallback.onDataReceived(null);
                }
            }
        });
        return null;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.InternalMediaCenterAPI
    public final EasMediaController getEasMediaController() {
        if (this.mService == 0) {
            return null;
        }
        this.p.a((IEASFrameworkService) this.mService);
        return this.p;
    }

    private void a(Runnable runnable) {
        b();
        this.f96c.post(runnable);
    }

    private void b() {
        if (this.f95b == null) {
            synchronized (this) {
                if (this.f95b == null) {
                    this.f95b = new HandlerThread(this.f94a);
                    this.f95b.start();
                    this.f96c = new Handler(this.f95b.getLooper());
                }
            }
        }
    }

    static /* synthetic */ IBinder a(a aVar) {
        IBinder iBinderA = aVar.a("getVrInternalBinder", "");
        if (iBinderA != null) {
            return iBinderA;
        }
        return null;
    }

    static /* synthetic */ IBinder d(a aVar) {
        IBinder iBinderA = aVar.a("getWidgetBinder", "");
        if (iBinderA != null) {
            return iBinderA;
        }
        return null;
    }
}
