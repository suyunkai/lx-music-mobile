package com.ecarx.eas.sdk.mediacenter;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
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
import androidx.media3.exoplayer.Renderer;
import com.ecarx.eas.framework.sdk.Singleton;
import com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient;
import com.ecarx.eas.framework.sdk.common.internal.IApi;
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
import ecarx.xsf.mediacenter.IMedia;
import ecarx.xsf.mediacenter.IMediaCenterClientToken;
import ecarx.xsf.mediacenter.IMediaCenterSvc;
import ecarx.xsf.mediacenter.bean.IMediaContentType;
import ecarx.xsf.mediacenter.control.IMediaControlClientApiSvc;
import ecarx.xsf.mediacenter.control.IMediaControllerApiSvc;
import ecarx.xsf.mediacenter.session.EasMediaController;
import ecarx.xsf.mediacenter.staterecover.IMusicRecoveryListener;
import ecarx.xsf.mediacenter.staterecover.IRecoveryMediaMetaInfo;
import ecarx.xsf.mediacenter.staterecover.IStateRecoverApiSvc;
import ecarx.xsf.mediacenter.vr.VrInternalAPI;
import ecarx.xsf.widget.WidgetAPI;
import ecarx.xsf.widget.interfaces.IControl;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class e extends IApi<IMediaCenterSvc> implements InternalMediaCenterAPI {
    private static Singleton<e> l = new Singleton<e>() { // from class: com.ecarx.eas.sdk.mediacenter.e.1
        @Override // com.ecarx.eas.framework.sdk.Singleton
        protected final /* synthetic */ e create() {
            return new e();
        }
    };

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f143a = "MediaCenterAPIImpl";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private volatile HandlerThread f144b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Handler f145c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Context f146d;
    private IMediaCenterSvc e;
    private IStateRecoverApiSvc f;
    private com.ecarx.eas.sdk.mediacenter.a.i g;
    private com.ecarx.eas.sdk.mediacenter.a.j h;
    private com.ecarx.eas.sdk.mediacenter.a.k i;
    private VrInternalAPI j;
    private WidgetAPI k;

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final void abandonPlayFocus(Object obj) {
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean cancelVrSemanticsCapability(Object obj, VrChannelInfo vrChannelInfo) throws MediaCenterException {
        return false;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    @Deprecated
    public final void connect(Object obj) throws IllegalArgumentException {
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean declareVrSemanticsCapability(Object obj, VrChannelInfo vrChannelInfo, int[] iArr, VrChannelDataListener vrChannelDataListener) throws MediaCenterException {
        return false;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.InternalMediaCenterAPI
    public final EasMediaController getEasMediaController() {
        return null;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final Object registerMusicWithZoneId(int i, String str, MusicClient musicClient) {
        return null;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean sendVrTtsActionResult(Object obj, String str, String str2, VrTtsResultListener vrTtsResultListener) throws MediaCenterException {
        return false;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final void updateErrorStateAndPendingIntent(Object obj, int i, int i2, String str, PendingIntent pendingIntent) {
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final void updateMediaListWithPagination(Object obj, int i, int i2, int i3, MediaListInfo mediaListInfo) {
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final void updateZoneId(Object obj, int i) {
    }

    @Override // com.ecarx.eas.framework.sdk.common.internal.IApi
    public final /* synthetic */ void init(IInterface iInterface) {
        IMediaCenterSvc iMediaCenterSvc = (IMediaCenterSvc) iInterface;
        super.init(iMediaCenterSvc);
        this.f146d = EASFrameworkApiClient.getInstance().getAppContext();
        this.e = iMediaCenterSvc;
        if (iMediaCenterSvc != null) {
            try {
                this.f = IStateRecoverApiSvc.Stub.asInterface(iMediaCenterSvc.getStateBinder());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            IMediaCenterSvc iMediaCenterSvc2 = this.e;
            com.ecarx.eas.sdk.mediacenter.a.i iVarA = com.ecarx.eas.sdk.mediacenter.a.i.a(this.f146d);
            this.g = iVarA;
            iVarA.a(iMediaCenterSvc2);
            com.ecarx.eas.sdk.mediacenter.a.j jVarA = com.ecarx.eas.sdk.mediacenter.a.j.a(this.f146d);
            this.h = jVarA;
            jVarA.a(iMediaCenterSvc2);
            com.ecarx.eas.sdk.mediacenter.a.k kVarA = com.ecarx.eas.sdk.mediacenter.a.k.a(this.f146d);
            this.i = kVarA;
            kVarA.a(iMediaCenterSvc2);
            this.j = ecarx.xsf.mediacenter.vr.b.a();
            this.k = WidgetAPI.get();
            Log.d("MediaCenterAPIImpl", " InitialImpl OK");
        }
    }

    public static e a() {
        return l.get();
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final int getECarXAPIBaseVERSION_INT() {
        return ecarx.xsf.mediacenter.session.adapter.l.a();
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final VrMusicCtrlAPI getVrMusicApi() {
        com.ecarx.eas.sdk.mediacenter.a.j jVar = this.h;
        if (jVar != null) {
            return jVar;
        }
        return null;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final VrLocalRadioCtrlAPI getVrLocalRadioApi() {
        com.ecarx.eas.sdk.mediacenter.a.i iVar = this.g;
        if (iVar != null) {
            return iVar;
        }
        return null;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final VrNewsCtrlAPI getVrNewsApi() {
        com.ecarx.eas.sdk.mediacenter.a.k kVar = this.i;
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
            Log.e("MediaCenterAPIImpl", "unbind media center service");
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
            Log.e("MediaCenterAPIImpl", "unbind media center service");
            return null;
        }
        try {
            Log.d("MediaCenterAPIImpl", "svc: " + this.e);
            return this.e.registerInMusic(str, new j(musicClient));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final Object registerMusic(String str, MusicClient musicClient, String str2) {
        if (this.e == null) {
            Log.e("MediaCenterAPIImpl", "unbind media center service");
            return null;
        }
        try {
            Log.d("MediaCenterAPIImpl", "svc: " + this.e);
            return this.e.registerInMusic(str, new j(musicClient));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final Object registerMusic(MusicClient musicClient, RegisterRequest registerRequest) {
        if (this.e == null || musicClient == null || registerRequest == null) {
            Log.e("MediaCenterAPIImpl", "unbind media center service");
            return null;
        }
        try {
            Log.d("MediaCenterAPIImpl", "svc: " + this.e);
            return this.e.registerInMusic(registerRequest.getPackageName(), new j(musicClient));
        } catch (Exception e) {
            e.printStackTrace();
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
            Log.e("MediaCenterAPIImpl", "unbind media center service");
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
            Log.e("MediaCenterAPIImpl", "unbind media center service");
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
            Log.e("MediaCenterAPIImpl", "unbind media center service");
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
            Log.e("MediaCenterAPIImpl", "unbind media center service");
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
            Log.e("MediaCenterAPIImpl", "unbind media center service");
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
            Log.e("MediaCenterAPIImpl", "unbind media center service");
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
    @Deprecated
    public final boolean updateVideoPlaybackState(Object obj, IVideoPlaybackInfo iVideoPlaybackInfo) {
        a(obj);
        if (this.e == null) {
            Log.e("MediaCenterAPIImpl", "unbind media center service");
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
            Log.e("MediaCenterAPIImpl", "unbind media center service");
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
            Log.e("MediaCenterAPIImpl", "unbind media center service");
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
            Log.e("MediaCenterAPIImpl", "unbind media center service");
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
            Log.e("MediaCenterAPIImpl", "unbind media center service");
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
            Log.e("MediaCenterAPIImpl", "unbind media center service");
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
            Log.e("MediaCenterAPIImpl", "unbind media center service");
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
            Log.e("MediaCenterAPIImpl", "unbind media center service");
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
            Log.e("MediaCenterAPIImpl", "unbind media center service");
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
            Log.e("MediaCenterAPIImpl", "unbind media center service");
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
            Log.e("MediaCenterAPIImpl", "unbind media center service");
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
            Log.e("MediaCenterAPIImpl", "unbind media center service");
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
            Log.e("MediaCenterAPIImpl", "unbind media center service");
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
            Log.e("MediaCenterAPIImpl", "unbind media center service");
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
            Log.e("MediaCenterAPIImpl", "unbind media center service");
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
            Log.e("MediaCenterAPIImpl", "unbind media center service");
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
            Log.d("MediaCenterAPIImpl", "unbind media center service");
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
    public final void updateErrorMsg(Object obj, int i, String str) {
        a(obj);
        IMediaCenterSvc iMediaCenterSvc = this.e;
        if (iMediaCenterSvc == null) {
            Log.d("MediaCenterAPIImpl", "unbind media center service");
            return;
        }
        try {
            iMediaCenterSvc.updateErrorMsg((IMediaCenterClientToken) obj, i, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final void declareMediaCenterCapability(Object obj, int[] iArr) {
        a(obj);
        IMediaCenterSvc iMediaCenterSvc = this.e;
        if (iMediaCenterSvc == null) {
            Log.d("MediaCenterAPIImpl", "unbind media center service");
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
            Log.d("MediaCenterAPIImpl", "unbind media center service");
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
            Log.d("MediaCenterAPIImpl", "unbind media center service");
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
            Log.d("MediaCenterAPIImpl", "declareVrChannelCapability, but unbind media center service");
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
            Log.d("MediaCenterAPIImpl", "cancelVrChannelCapability, but unbind media center service");
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
            Log.d("MediaCenterAPIImpl", "asyncSendVrChannelResult, but unbind media center service");
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
            Log.d("MediaCenterAPIImpl", "unbind media center service");
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
        Log.d("MediaCenterAPIImpl", "updateMultiMediaList");
        a(obj);
        IMediaCenterSvc iMediaCenterSvc = this.e;
        if (iMediaCenterSvc == null) {
            Log.d("MediaCenterAPIImpl", "unbind media center service");
            return false;
        }
        try {
            return iMediaCenterSvc.updateMultiMediaList((IMediaCenterClientToken) obj, CommercialInfoHelper.getIMediaLists(mediaListsInfo));
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean registerMusicRecoveryIntent(Object obj, int i, Intent intent) throws MediaCenterException {
        a(obj);
        IStateRecoverApiSvc iStateRecoverApiSvc = this.f;
        if (iStateRecoverApiSvc == null) {
            Log.d("MediaCenterAPIImpl", "registerMusicRecoveryIntent, but unbind media center service");
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
            Log.d("MediaCenterAPIImpl", "unRegisterMusicRecoveryIntent, but unbind media center service");
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
            Log.d("MediaCenterAPIImpl", "getRecoveryMediaList, but unbind media center service");
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
            Log.d("MediaCenterAPIImpl", "getRecoveryMusicPlaybackInfo, but unbind media center service");
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
            Log.d("MediaCenterAPIImpl", "registerMusicRecoveryIntent, but unbind media center service");
            throw new com.ecarx.eas.sdk.mediacenter.exception.a();
        }
        try {
            iStateRecoverApiSvc.setMusicRecoveryCallback((IMediaCenterClientToken) obj, new IMusicRecoveryListener.Stub() { // from class: com.ecarx.eas.sdk.mediacenter.e.2
                @Override // ecarx.xsf.mediacenter.staterecover.IMusicRecoveryListener
                public final void onGetMediaList(IRecoveryMediaMetaInfo iRecoveryMediaMetaInfo) throws RemoteException {
                    MediaListInfo mediaListInfoA = e.this.a(iRecoveryMediaMetaInfo);
                    IMusicRecoveryCallback iMusicRecoveryCallback2 = iMusicRecoveryCallback;
                    if (iMusicRecoveryCallback2 != null) {
                        iMusicRecoveryCallback2.onGetMediaList(mediaListInfoA);
                    }
                }

                @Override // ecarx.xsf.mediacenter.staterecover.IMusicRecoveryListener
                public final void onGetMusicPlaybackInfo(ecarx.xsf.mediacenter.IMusicPlaybackInfo iMusicPlaybackInfo) throws RemoteException {
                    MusicPlaybackInfo musicPlaybackInfoA = e.this.a(iMusicPlaybackInfo);
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
            Log.d("MediaCenterAPIImpl", "registerMusicRecoveryIntent, but unbind media center service");
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
            Log.d("MediaCenterAPIImpl", "updateCollectMsg, but unbind media center service");
            throw new com.ecarx.eas.sdk.mediacenter.exception.a();
        }
        try {
            Log.d("MediaCenterAPIImpl", "updateCollectMsg resultCode = " + i + " message = " + str);
            this.e.updateCollectMsg((IMediaCenterClientToken) obj, i, str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final String queryCurrentFocusClient(Object obj) throws MediaCenterException {
        a(obj);
        if (this.e == null) {
            Log.d("MediaCenterAPIImpl", "queryCurrentFocusClient, but unbind media center service");
            throw new com.ecarx.eas.sdk.mediacenter.exception.a();
        }
        try {
            Log.d("MediaCenterAPIImpl", "queryCurrentFocusClient");
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
            Log.d("MediaCenterAPIImpl", "unbind media center service");
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
            Log.d("MediaCenterAPIImpl", "updateCollectMsg, but unbind media center service");
            throw new com.ecarx.eas.sdk.mediacenter.exception.a();
        }
        try {
            Log.d("MediaCenterAPIImpl", "updateCollectMsg type = " + i + "operation = " + i2 + " uuid = " + str + " resultCode = " + i3 + " message = " + str2);
            this.e.updateCollectMsgByUUID((IMediaCenterClientToken) obj, i, i2, str, i3, str2);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static void a(Object obj) {
        Log.d("MediaCenterAPIImpl", "verifyToken: " + obj);
        if (!(obj instanceof IMediaCenterClientToken)) {
            throw new IllegalArgumentException();
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
        return new MediaListInfo(this) { // from class: com.ecarx.eas.sdk.mediacenter.e.3
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
                    arrayList.add(new MediaInfo(this) { // from class: com.ecarx.eas.sdk.mediacenter.e.3.1
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
        return new MusicPlaybackInfo(this) { // from class: com.ecarx.eas.sdk.mediacenter.e.4
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
        if (this.j == null) {
            return null;
        }
        a(new Runnable() { // from class: com.ecarx.eas.sdk.mediacenter.e.5
            @Override // java.lang.Runnable
            public final void run() {
                final Object obj = new Object();
                Intent intent = new Intent("ecarx.xsf.MEDIA_CENTER_INTERNAL_API_SERVICE");
                intent.setComponent(new ComponentName("ecarx.xsf.mediacenter", "ecarx.xsf.mediacenter.MediaCenterService"));
                e.this.f146d.bindService(intent, new ServiceConnection() { // from class: com.ecarx.eas.sdk.mediacenter.e.5.1
                    @Override // android.content.ServiceConnection
                    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                        e.this.j.updateMediaCenterSvc(iBinder);
                        if (iBinder != null) {
                            apiCallback.onDataReceived(e.this.j);
                        } else {
                            apiCallback.onDataReceived(null);
                        }
                        synchronized (obj) {
                            obj.notifyAll();
                        }
                    }

                    @Override // android.content.ServiceConnection
                    public final void onServiceDisconnected(ComponentName componentName) {
                        e.this.j.updateMediaCenterSvc(null);
                        apiCallback.onDataReceived(null);
                        synchronized (obj) {
                            obj.notifyAll();
                        }
                    }
                }, 1);
                synchronized (obj) {
                    try {
                        obj.wait(Renderer.DEFAULT_DURATION_TO_PROGRESS_US);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return null;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.InternalMediaCenterAPI
    public final IControl getWidgetApi(final ApiCallback<IControl> apiCallback) {
        if (this.k == null) {
            return null;
        }
        a(new Runnable() { // from class: com.ecarx.eas.sdk.mediacenter.e.6
            @Override // java.lang.Runnable
            public final void run() {
                final Object obj = new Object();
                Intent intent = new Intent("ecarx.xsf.ACTION_MEDIA_CENTER_WIDGET_API_SERVICE");
                intent.setComponent(new ComponentName("ecarx.xsf.mediacenter", "ecarx.xsf.mediacenter.MediaCenterService"));
                e.this.f146d.bindService(intent, new ServiceConnection() { // from class: com.ecarx.eas.sdk.mediacenter.e.6.1
                    @Override // android.content.ServiceConnection
                    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                        e.this.k.updateMediaCenterSvc(iBinder);
                        if (iBinder != null) {
                            apiCallback.onDataReceived(e.this.k);
                        } else {
                            apiCallback.onDataReceived(null);
                        }
                        synchronized (obj) {
                            obj.notifyAll();
                        }
                    }

                    @Override // android.content.ServiceConnection
                    public final void onServiceDisconnected(ComponentName componentName) {
                        e.this.k.updateMediaCenterSvc(null);
                        apiCallback.onDataReceived(null);
                        synchronized (obj) {
                            obj.notifyAll();
                        }
                    }
                }, 1);
                synchronized (obj) {
                    try {
                        obj.wait(Renderer.DEFAULT_DURATION_TO_PROGRESS_US);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return null;
    }

    private void a(Runnable runnable) {
        b();
        this.f145c.post(runnable);
    }

    private void b() {
        if (this.f144b == null) {
            synchronized (this) {
                if (this.f144b == null) {
                    this.f144b = new HandlerThread(this.f143a);
                    this.f144b.start();
                    this.f145c = new Handler(this.f144b.getLooper());
                }
            }
        }
    }
}
