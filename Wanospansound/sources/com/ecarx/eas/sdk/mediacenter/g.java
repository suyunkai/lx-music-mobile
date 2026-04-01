package com.ecarx.eas.sdk.mediacenter;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Binder;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import com.ecarx.eas.framework.sdk.Singleton;
import com.ecarx.eas.framework.sdk.common.exception.EASFrameworkException;
import com.ecarx.eas.framework.sdk.common.internal.ClientType;
import com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient;
import com.ecarx.eas.framework.sdk.common.internal.IApi;
import com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService;
import com.ecarx.eas.framework.sdk.common.internal.IServicePool;
import com.ecarx.eas.framework.sdk.common.internal.safeparcel.ServiceVersionInfo;
import com.ecarx.eas.sdk.ECarXApiClient;
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
import ecarx.xsf.mediacenter.IMediaCenterSvc;
import ecarx.xsf.mediacenter.bean.IMediaContentType;
import ecarx.xsf.mediacenter.session.EasMediaController;
import ecarx.xsf.mediacenter.vr.VrInternalAPI;
import ecarx.xsf.widget.interfaces.IControl;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class g extends MediaCenterAPI {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static Singleton<g> f161d = new Singleton<g>() { // from class: com.ecarx.eas.sdk.mediacenter.g.1
        @Override // com.ecarx.eas.framework.sdk.Singleton
        protected final /* synthetic */ g create() {
            return new g();
        }
    };

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private volatile InternalMediaCenterAPI f162a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private volatile String f163b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f164c;

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final void connect(Object obj) throws IllegalArgumentException {
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final Object register(Binder binder) {
        return null;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final Object registerMusic(IMusicClient iMusicClient) {
        return null;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final Object registerNews(INewsClient iNewsClient) {
        return null;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final Object registerVideo(IVideoClient iVideoClient) {
        return null;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean updateMusicPlaybackState(Object obj, IMusicPlaybackInfo iMusicPlaybackInfo) {
        return false;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean updateNewsPlaybackState(Object obj, INewsPlaybackInfo iNewsPlaybackInfo) {
        return false;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final void updatePlaylist(Object obj, int i, List<IMediaInfo> list) {
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean updateVideoPlaybackState(Object obj, IVideoPlaybackInfo iVideoPlaybackInfo) {
        return false;
    }

    public static g a() {
        return f161d.get();
    }

    @Override // com.ecarx.eas.framework.sdk.ECarXAPIBase
    public final void init(Context context, ECarXApiClient.Callback callback) {
        super.init(context, callback);
        this.f164c = context;
        this.f163b = context.getPackageName();
        try {
            EASFrameworkApiClient.getInstance().init(context, callback, IServiceManager.SERVICE_MEDIACENTER, new EASFrameworkApiClient.IServiceConnectionCallback() { // from class: com.ecarx.eas.sdk.mediacenter.g.2
                @Override // com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient.IServiceConnectionCallback
                public final boolean onConnected(String str, ClientType clientType, boolean z) {
                    if (z) {
                        g.a(g.this, clientType);
                    }
                    return g.this.f162a != null;
                }

                @Override // com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient.IServiceConnectionCallback
                public final boolean onConnected(String str, ClientType clientType, ServiceVersionInfo serviceVersionInfo, boolean z) {
                    if (z) {
                        g.a(g.this, clientType);
                    }
                    return g.this.f162a != null;
                }
            });
        } catch (EASFrameworkException e) {
            e.printStackTrace();
        }
    }

    private static int a(Context context, String str, String str2) {
        int i = -1;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 128);
            Log.d("MediaCenterAPI", "getMetaDataInt:" + applicationInfo);
            i = applicationInfo.metaData.getInt(str2, 0);
            Log.d("MediaCenterAPI", "getMetaDataInt: data:" + i);
            return i;
        } catch (Throwable th) {
            th.printStackTrace();
            return i;
        }
    }

    @Override // com.ecarx.eas.framework.sdk.ECarXAPIBase
    public final void release() {
        super.release();
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final int getECarXAPIBaseVERSION_INT() {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
            return -1;
        }
        return this.f162a.getECarXAPIBaseVERSION_INT();
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean unregister(Object obj) {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
            return false;
        }
        return this.f162a.unregister(obj);
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean requestPlay(Object obj) {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
            return false;
        }
        return this.f162a.requestPlay(obj);
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final void abandonPlayFocus(Object obj) {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
        } else {
            this.f162a.abandonPlayFocus(obj);
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean updateMediaSourceTypeList(Object obj, int[] iArr) {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
            return false;
        }
        return this.f162a.updateMediaSourceTypeList(obj, iArr);
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final void updateCurrentSourceType(Object obj, int i) {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
        } else {
            this.f162a.updateCurrentSourceType(obj, i);
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final void updateCurrentProgress(Object obj, long j) {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
        } else {
            this.f162a.updateCurrentProgress(obj, j);
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final Object registerMusic(String str, MusicClient musicClient) {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
            return null;
        }
        return this.f162a.registerMusic(str, musicClient);
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final Object registerMusic(String str, MusicClient musicClient, String str2) {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
            return null;
        }
        return this.f162a.registerMusic(str, musicClient, str2);
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final Object registerMusic(MusicClient musicClient, RegisterRequest registerRequest) {
        if (this.f162a == null || musicClient == null || registerRequest == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
            return null;
        }
        return this.f162a.registerMusic(musicClient, registerRequest);
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final Object registerNews(String str, NewsClient newsClient) {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
            return null;
        }
        return this.f162a.registerNews(str, newsClient);
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final Object registerVideo(String str, VideoClient videoClient) {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
            return null;
        }
        return this.f162a.registerVideo(str, videoClient);
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean updateMusicPlaybackState(Object obj, MusicPlaybackInfo musicPlaybackInfo) {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
            return false;
        }
        return this.f162a.updateMusicPlaybackState(obj, musicPlaybackInfo);
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean updateVideoPlaybackState(Object obj, VideoPlaybackInfo videoPlaybackInfo) {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
            return false;
        }
        return this.f162a.updateVideoPlaybackState(obj, videoPlaybackInfo);
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean updateNewsPlaybackState(Object obj, NewsPlaybackInfo newsPlaybackInfo) {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
            return false;
        }
        return this.f162a.updateNewsPlaybackState(obj, newsPlaybackInfo);
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final VrMusicCtrlAPI getVrMusicApi() {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
            return null;
        }
        return this.f162a.getVrMusicApi();
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final VrLocalRadioCtrlAPI getVrLocalRadioApi() {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
            return null;
        }
        return this.f162a.getVrLocalRadioApi();
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final VrNewsCtrlAPI getVrNewsApi() {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
            return null;
        }
        return this.f162a.getVrNewsApi();
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final void updateCurrentPlaylist(Object obj, int i, List<MediaInfo> list) {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
        } else {
            this.f162a.updateCurrentPlaylist(obj, i, list);
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean declareSupportCollectTypes(Object obj, int[] iArr) {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
            return false;
        }
        return this.f162a.declareSupportCollectTypes(obj, iArr);
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean cancelSupportCollectTypes(Object obj, int[] iArr) {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
            return false;
        }
        return this.f162a.cancelSupportCollectTypes(obj, iArr);
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean declareSupportDownloadTypes(Object obj, int[] iArr) {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
            return false;
        }
        return this.f162a.declareSupportDownloadTypes(obj, iArr);
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean cancelSupportDownloadTypes(Object obj, int[] iArr) {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
            return false;
        }
        return this.f162a.cancelSupportDownloadTypes(obj, iArr);
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean updateCurrentRecommendInfo(Object obj, RecommendInfo recommendInfo) {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
            return false;
        }
        return this.f162a.updateCurrentRecommendInfo(obj, recommendInfo);
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final void updateCurrentLyric(Object obj, String str) {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
        } else {
            this.f162a.updateCurrentLyric(obj, str);
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final void updateMediaList(Object obj, MediaListInfo mediaListInfo) {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
        } else {
            this.f162a.updateMediaList(obj, mediaListInfo);
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final void updateMediaListWithPagination(Object obj, int i, int i2, int i3, MediaListInfo mediaListInfo) {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
        } else {
            this.f162a.updateMediaListWithPagination(obj, i, i2, i3, mediaListInfo);
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final void updateErrorMsg(Object obj, int i, String str) {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
        } else {
            this.f162a.updateErrorMsg(obj, i, str);
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final void updateErrorStateAndPendingIntent(Object obj, int i, int i2, String str, PendingIntent pendingIntent) {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
        } else {
            this.f162a.updateErrorStateAndPendingIntent(obj, i, i2, str, pendingIntent);
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final void declareMediaCenterCapability(Object obj, int[] iArr) {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
        } else {
            this.f162a.declareMediaCenterCapability(obj, iArr);
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final IMediaControlClientAPI getMediaControlClientApi() {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
            return null;
        }
        return this.f162a.getMediaControlClientApi();
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final IMediaControllerAPI getMediaControllerApi() {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
            return null;
        }
        return this.f162a.getMediaControllerApi();
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean declareVrChannelCapability(Object obj, VrChannelInfo vrChannelInfo, VrChannelDataListener vrChannelDataListener) throws MediaCenterException {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
            return false;
        }
        return this.f162a.declareVrChannelCapability(obj, vrChannelInfo, vrChannelDataListener);
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean cancelVrChannelCapability(Object obj, VrChannelInfo vrChannelInfo) throws MediaCenterException {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
            return false;
        }
        return this.f162a.cancelVrChannelCapability(obj, vrChannelInfo);
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean asyncSendVrChannelResult(Object obj, VrChannelInfo vrChannelInfo, String str) throws MediaCenterException {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
            return false;
        }
        return this.f162a.asyncSendVrChannelResult(obj, vrChannelInfo, str);
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean updateMediaContent(Object obj, List<ContentInfo> list) throws MediaCenterException {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
            return false;
        }
        return this.f162a.updateMediaContent(obj, list);
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean updateMultiMediaList(Object obj, MediaListsInfo mediaListsInfo) throws MediaCenterException {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
            return false;
        }
        return this.f162a.updateMultiMediaList(obj, mediaListsInfo);
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean registerMusicRecoveryIntent(Object obj, int i, Intent intent) throws MediaCenterException {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
            return false;
        }
        return this.f162a.registerMusicRecoveryIntent(obj, i, intent);
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean unRegisterMusicRecoveryIntent(Object obj) throws MediaCenterException {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
            return false;
        }
        return this.f162a.unRegisterMusicRecoveryIntent(obj);
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final MediaListInfo getRecoveryMediaList(Object obj) throws MediaCenterException {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
            return null;
        }
        return this.f162a.getRecoveryMediaList(obj);
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final MusicPlaybackInfo getRecoveryMusicPlaybackInfo(Object obj) throws MediaCenterException {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
            return null;
        }
        return this.f162a.getRecoveryMusicPlaybackInfo(obj);
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final void setMusicRecoveryCallback(Object obj, IMusicRecoveryCallback iMusicRecoveryCallback) throws MediaCenterException {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
        } else {
            this.f162a.setMusicRecoveryCallback(obj, iMusicRecoveryCallback);
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final void onMusicRecoveryComplete(Object obj) throws MediaCenterException {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
        } else {
            this.f162a.onMusicRecoveryComplete(obj);
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final void updateCollectMsg(Object obj, int i, String str) throws MediaCenterException {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
        } else {
            this.f162a.updateCollectMsg(obj, i, str);
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final String queryCurrentFocusClient(Object obj) throws MediaCenterException {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
            return null;
        }
        return this.f162a.queryCurrentFocusClient(obj);
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final void updateMediaContentTypeList(Object obj, List<IMediaContentType> list) {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
        } else {
            this.f162a.updateMediaContentTypeList(obj, list);
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final void updateCollectMsg(Object obj, int i, int i2, String str, int i3, String str2) throws MediaCenterException {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
        } else {
            this.f162a.updateCollectMsg(obj, i, i2, str, i3, str2);
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean declareVrSemanticsCapability(Object obj, VrChannelInfo vrChannelInfo, int[] iArr, VrChannelDataListener vrChannelDataListener) throws MediaCenterException {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
            return false;
        }
        return this.f162a.declareVrSemanticsCapability(obj, vrChannelInfo, iArr, vrChannelDataListener);
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean cancelVrSemanticsCapability(Object obj, VrChannelInfo vrChannelInfo) throws MediaCenterException {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
            return false;
        }
        return this.f162a.cancelVrSemanticsCapability(obj, vrChannelInfo);
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final boolean sendVrTtsActionResult(Object obj, String str, String str2, VrTtsResultListener vrTtsResultListener) throws MediaCenterException {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
            return false;
        }
        return this.f162a.sendVrTtsActionResult(obj, str, str2, vrTtsResultListener);
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final Object registerMusicWithZoneId(int i, String str, MusicClient musicClient) {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
            return null;
        }
        return this.f162a.registerMusicWithZoneId(i, str, musicClient);
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaCenterAPI
    public final void updateZoneId(Object obj, int i) {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
        } else {
            this.f162a.updateZoneId(obj, i);
        }
    }

    @Override // com.ecarx.eas.sdk.mediacenter.InternalMediaCenterAPI
    public final VrInternalAPI getVrInternalApi(ApiCallback<VrInternalAPI> apiCallback) {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
            return null;
        }
        return this.f162a.getVrInternalApi(apiCallback);
    }

    @Override // com.ecarx.eas.sdk.mediacenter.InternalMediaCenterAPI
    public final IControl getWidgetApi(ApiCallback<IControl> apiCallback) {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
            return null;
        }
        return this.f162a.getWidgetApi(apiCallback);
    }

    @Override // com.ecarx.eas.sdk.mediacenter.InternalMediaCenterAPI
    public final EasMediaController getEasMediaController() {
        if (this.f162a == null) {
            Log.e("MediaCenterAPI", ">> please wait for mediacenter init success!!! <<");
            return null;
        }
        return this.f162a.getEasMediaController();
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void a(g gVar, ClientType clientType) {
        ClientType clientType2;
        IEASFrameworkService eASServiceManager;
        IServicePool serviceManager;
        if (a(gVar.f164c, "ecarx.xsf.mediacenter", "EAS_SUPPORT") == 0) {
            clientType2 = ClientType.OpenAPI;
        } else {
            clientType2 = ClientType.EASFramework;
        }
        IBinder service = null;
        IBinder service2 = null;
        IEASFrameworkService eASServiceManager2 = null;
        if (clientType == ClientType.OpenAPI) {
            gVar.f162a = f.a(ClientType.OpenAPI);
            try {
                serviceManager = EASFrameworkApiClient.getInstance().getServiceManager();
            } catch (DeadObjectException e) {
                e.printStackTrace();
                serviceManager = null;
            }
            if (serviceManager == null) {
                Log.e("MediaCenterAPI", String.format(">> OpenAPIService getServicePool is NULL <<", new Object[0]));
                return;
            }
            try {
                service = serviceManager.getService(Process.myPid(), Process.myUid(), gVar.f163b, IServiceManager.SERVICE_MEDIACENTER);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
            if (service == null) {
                Log.e("MediaCenterAPI", String.format(">> OpenAPIService mediacenter binder is NULL <<", new Object[0]));
                return;
            }
            IMediaCenterSvc iMediaCenterSvcAsInterface = IMediaCenterSvc.Stub.asInterface(service);
            if (gVar.f162a == null || !(gVar.f162a instanceof IApi)) {
                return;
            }
            ((IApi) gVar.f162a).init(iMediaCenterSvcAsInterface);
            return;
        }
        if (clientType == ClientType.EASFramework && clientType2 == ClientType.EASFramework) {
            gVar.f162a = f.a(ClientType.EASFramework);
            try {
                eASServiceManager2 = EASFrameworkApiClient.getInstance().getEASServiceManager();
            } catch (DeadObjectException e3) {
                e3.printStackTrace();
            }
            if (eASServiceManager2 == null) {
                Log.e("MediaCenterAPI", String.format(">> EASFrameworkService is NULL <<", new Object[0]));
                return;
            } else {
                if (gVar.f162a == null || !(gVar.f162a instanceof IApi)) {
                    return;
                }
                ((IApi) gVar.f162a).init(eASServiceManager2);
                return;
            }
        }
        if (clientType == ClientType.EASFramework && clientType2 == ClientType.OpenAPI) {
            gVar.f162a = f.a(ClientType.OpenAPI);
            try {
                eASServiceManager = EASFrameworkApiClient.getInstance().getEASServiceManager();
            } catch (DeadObjectException e4) {
                e4.printStackTrace();
                eASServiceManager = null;
            }
            if (eASServiceManager == null) {
                Log.e("MediaCenterAPI", String.format(">> EASFrameworkService is NULL <<", new Object[0]));
                return;
            }
            try {
                service2 = eASServiceManager.getService(Process.myPid(), Process.myUid(), gVar.f163b, IServiceManager.SERVICE_MEDIACENTER);
            } catch (RemoteException e5) {
                e5.printStackTrace();
            }
            if (service2 == null) {
                Log.e("MediaCenterAPI", String.format(">> EASFrameworkService mediacenter binder is NULL <<", new Object[0]));
                return;
            }
            IMediaCenterSvc iMediaCenterSvcAsInterface2 = IMediaCenterSvc.Stub.asInterface(service2);
            if (gVar.f162a == null || !(gVar.f162a instanceof IApi)) {
                return;
            }
            ((IApi) gVar.f162a).init(iMediaCenterSvcAsInterface2);
        }
    }
}
