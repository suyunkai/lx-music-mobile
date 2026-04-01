package com.wanos.groove;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.wanos.commonlibrary.bean.AudioBookMineChapterItemBean;
import com.wanos.commonlibrary.bean.AudioBookSpeakerBean;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.mediaPlayer.listener.OnMediaInfoListListener;
import com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener;
import com.wanos.commonlibrary.mediaPlayer.listener.OnUpdateSDKLrcListener;
import com.wanos.groove.IGrooveDataInterface;
import com.wanos.groove.utils.GrooveSdkAppGlobal;
import com.wanos.lyric.lrc.impl.LrcRow;
import com.wanos.media.MainApplication;
import com.wanos.wanosplayermodule.MediaPlayEngine;
import com.wanos.wanosplayermodule.MediaPlayServiceConnectListener;
import com.wanos.wanosplayermodule.MediaPlayerManager;
import com.wanos.wanosplayermodule.MediaPlayerService;
import com.wanos.wanosplayermodule.util.MediaInfoChangeUitl;
import com.wanos.wanosplayermodule.util.MediaSharedPreferUtils;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class GrooveDataBinder extends IGrooveDataInterface.Stub {
    public static final String TAG = "wanos:[GrooveDataBinder]";
    private MediaPlayerService.OnMediaInfoCallbackAppListener callbackAppListener;
    IGrooveDataListener mCallBack;
    private OnMediaInfoListListener mediaInfoListListener;
    private OnStatusCallbackListener playStateListener;
    private OnUpdateSDKLrcListener updateLrcListener;
    MediaPlayerService mMediaPlayerService = null;
    Handler mainHandler = new Handler(Looper.getMainLooper());
    private boolean isRandomPlay = false;

    /* JADX INFO: Access modifiers changed from: private */
    public void addPlayListener() {
        if (this.mMediaPlayerService == null) {
            return;
        }
        this.playStateListener = new OnStatusCallbackListener() { // from class: com.wanos.groove.GrooveDataBinder$$ExternalSyntheticLambda0
            @Override // com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener
            public final void onStatusonStatusCallbackNext(MediaPlayerEnum.CallBackState callBackState, Object[] objArr) {
                this.f$0.m461lambda$addPlayListener$0$comwanosgrooveGrooveDataBinder(callBackState, objArr);
            }
        };
        this.mediaInfoListListener = new OnMediaInfoListListener() { // from class: com.wanos.groove.GrooveDataBinder.1
            @Override // com.wanos.commonlibrary.mediaPlayer.listener.OnMediaInfoListListener
            public void onListAdded() {
            }

            @Override // com.wanos.commonlibrary.mediaPlayer.listener.OnMediaInfoListListener
            public void onListChanged() {
                if (GrooveDataBinder.this.mCallBack != null) {
                    try {
                        if (MainApplication.getInstance().centerClient == null || !MainApplication.getInstance().centerClient.hasCurrentFocus()) {
                            return;
                        }
                        GrooveDataBinder.this.mCallBack.onListChanged();
                        return;
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        return;
                    }
                }
                Log.i(GrooveDataBinder.TAG, "----未注册该接口----onIndexChanged()---无法监听歌曲信息变化");
            }
        };
        this.updateLrcListener = new OnUpdateSDKLrcListener() { // from class: com.wanos.groove.GrooveDataBinder$$ExternalSyntheticLambda1
            @Override // com.wanos.commonlibrary.mediaPlayer.listener.OnUpdateSDKLrcListener
            public final void onUpdateSDKLrc(MediaPlayerEnum.UpdateLrcCallbackType updateLrcCallbackType, String str) {
                this.f$0.m463lambda$addPlayListener$2$comwanosgrooveGrooveDataBinder(updateLrcCallbackType, str);
            }
        };
        this.callbackAppListener = new AnonymousClass2();
        this.mMediaPlayerService.addOnStatusCallbackListener(this.playStateListener);
        this.mMediaPlayerService.addOnMediaInfoListListener(this.mediaInfoListListener);
        this.mMediaPlayerService.addOnMediaInfoCallbackAppListener(this.callbackAppListener);
        this.mMediaPlayerService.addOnUpdateSDKLrcCallbackListener(this.updateLrcListener);
    }

    /* JADX INFO: renamed from: lambda$addPlayListener$0$com-wanos-groove-GrooveDataBinder, reason: not valid java name */
    /* synthetic */ void m461lambda$addPlayListener$0$comwanosgrooveGrooveDataBinder(MediaPlayerEnum.CallBackState callBackState, Object[] objArr) {
        if (this.mCallBack != null) {
            if (callBackState == MediaPlayerEnum.CallBackState.STARTED) {
                try {
                    this.mCallBack.playState(1);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            if (callBackState == MediaPlayerEnum.CallBackState.PAUSE) {
                try {
                    this.mCallBack.playState(0);
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
            }
            if (callBackState == MediaPlayerEnum.CallBackState.ERROR || callBackState == MediaPlayerEnum.CallBackState.EXCEPTION) {
                try {
                    this.mCallBack.playState(-1);
                } catch (RemoteException e3) {
                    e3.printStackTrace();
                }
            }
            if (this.mMediaPlayerService.getCurMediaInfo().getMediaType() != MediaPlayerEnum.MediaType.Music || callBackState == MediaPlayerEnum.CallBackState.PREPARING || callBackState == MediaPlayerEnum.CallBackState.PREPARE || callBackState == MediaPlayerEnum.CallBackState.BUFFER_UPDATE || callBackState == MediaPlayerEnum.CallBackState.PAUSE) {
                return;
            }
            LyricLine lrcInfo = getLrcInfo(this.mMediaPlayerService.getCurrentPosition());
            if (lrcInfo != null) {
                try {
                    this.mCallBack.lyricChanged(lrcInfo);
                    return;
                } catch (RemoteException e4) {
                    e4.printStackTrace();
                    return;
                }
            }
            Log.i(TAG, "当前的单行歌词数据为空");
            return;
        }
        Log.i(TAG, "----未注册该接口----playState()---无法监听播放状态变化");
    }

    /* JADX INFO: renamed from: lambda$addPlayListener$2$com-wanos-groove-GrooveDataBinder, reason: not valid java name */
    /* synthetic */ void m463lambda$addPlayListener$2$comwanosgrooveGrooveDataBinder(MediaPlayerEnum.UpdateLrcCallbackType updateLrcCallbackType, String str) {
        if (this.mCallBack != null) {
            if (MediaPlayerEnum.UpdateLrcCallbackType.SUCCESS == updateLrcCallbackType) {
                final LyricInfo lyricInfo = new LyricInfo();
                if (!TextUtils.isEmpty(str)) {
                    lyricInfo.setSong_id_str(this.mMediaPlayerService.getCurMediaInfo().getMusicInfoBean().getMusicId());
                    lyricInfo.setSong_lyric(str);
                }
                this.mainHandler.post(new Runnable() { // from class: com.wanos.groove.GrooveDataBinder$$ExternalSyntheticLambda8
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.m462lambda$addPlayListener$1$comwanosgrooveGrooveDataBinder(lyricInfo);
                    }
                });
                return;
            }
            return;
        }
        Log.i(TAG, "----未注册该接口----getLyricChanged()---无法监听歌词信息变化");
    }

    /* JADX INFO: renamed from: lambda$addPlayListener$1$com-wanos-groove-GrooveDataBinder, reason: not valid java name */
    /* synthetic */ void m462lambda$addPlayListener$1$comwanosgrooveGrooveDataBinder(LyricInfo lyricInfo) {
        try {
            this.mCallBack.getLyricChanged(lyricInfo);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: renamed from: com.wanos.groove.GrooveDataBinder$2, reason: invalid class name */
    class AnonymousClass2 implements MediaPlayerService.OnMediaInfoCallbackAppListener {
        AnonymousClass2() {
        }

        @Override // com.wanos.wanosplayermodule.MediaPlayerService.OnMediaInfoCallbackAppListener
        public void onMediaInfoCallbackAppNext(MediaPlayerEnum.MediaInfoCallbackType type, final com.wanos.commonlibrary.bean.MediaInfo mediaInfo) {
            final int i;
            if (type == MediaPlayerEnum.MediaInfoCallbackType.playMode) {
                MediaPlayerEnum.PlayMode musicMode = MediaSharedPreferUtils.getMusicMode();
                if (musicMode == MediaPlayerEnum.PlayMode.orderplay || musicMode == MediaPlayerEnum.PlayMode.listloopplay) {
                    i = 0;
                } else if (musicMode == MediaPlayerEnum.PlayMode.singleloopplay) {
                    i = 1;
                } else {
                    i = musicMode == MediaPlayerEnum.PlayMode.randomplay ? 2 : -1;
                }
                if (GrooveDataBinder.this.isRandomPlay || i == 2) {
                    GrooveDataBinder.this.notifyAIMusicMvRandomListChange();
                }
                GrooveDataBinder.this.isRandomPlay = i == 2;
                if (GrooveDataBinder.this.mCallBack != null) {
                    GrooveDataBinder.this.mainHandler.post(new Runnable() { // from class: com.wanos.groove.GrooveDataBinder$2$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.m470x29cbee1f(i);
                        }
                    });
                    return;
                } else {
                    Log.i(GrooveDataBinder.TAG, "----未注册该接口----onPlayModeChanged()---无法监听播放状态变化");
                    return;
                }
            }
            if (type == MediaPlayerEnum.MediaInfoCallbackType.mediaInfoType) {
                if (GrooveDataBinder.this.mCallBack != null) {
                    GrooveDataBinder.this.mainHandler.post(new Runnable() { // from class: com.wanos.groove.GrooveDataBinder$2$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.m471x3d73c1a0(mediaInfo);
                        }
                    });
                } else {
                    Log.i(GrooveDataBinder.TAG, "----未注册该接口----onIndexChanged()---无法监听歌曲信息变化");
                }
            }
        }

        /* JADX INFO: renamed from: lambda$onMediaInfoCallbackAppNext$0$com-wanos-groove-GrooveDataBinder$2, reason: not valid java name */
        /* synthetic */ void m470x29cbee1f(int i) {
            try {
                GrooveDataBinder.this.mCallBack.onPlayModeChanged(i);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        /* JADX INFO: renamed from: lambda$onMediaInfoCallbackAppNext$1$com-wanos-groove-GrooveDataBinder$2, reason: not valid java name */
        /* synthetic */ void m471x3d73c1a0(com.wanos.commonlibrary.bean.MediaInfo mediaInfo) {
            if (mediaInfo == null) {
                return;
            }
            try {
                GrooveDataBinder.this.mCallBack.onIndexChanged(GrooveDataBinder.this.getCurGrooveSdkMediaInfo(GrooveDataBinder.this.mMediaPlayerService.getCurMediaInfo()));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyAIMusicMvRandomListChange() {
        if (this.mCallBack != null) {
            try {
                if (MainApplication.getInstance().centerClient == null || !MainApplication.getInstance().centerClient.hasCurrentFocus()) {
                    return;
                }
                this.mCallBack.onListChanged();
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.i(TAG, "----未注册该接口----onIndexChanged()---无法监听歌曲信息变化");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removePlayListener() {
        Log.i(TAG, "----注销播放器服务接口----removePlayListener()");
        OnStatusCallbackListener onStatusCallbackListener = this.playStateListener;
        if (onStatusCallbackListener != null) {
            this.mMediaPlayerService.removeOnStatusCallbackListener(onStatusCallbackListener);
        }
        if (this.playStateListener != null) {
            this.mMediaPlayerService.removeOnMediaInfoCallbackAppListener(this.callbackAppListener);
        }
        OnMediaInfoListListener onMediaInfoListListener = this.mediaInfoListListener;
        if (onMediaInfoListListener != null) {
            this.mMediaPlayerService.removeOnMediaInfoListListener(onMediaInfoListListener);
        }
        OnUpdateSDKLrcListener onUpdateSDKLrcListener = this.updateLrcListener;
        if (onUpdateSDKLrcListener != null) {
            this.mMediaPlayerService.removeOnUpdateSdkLrcCallbackListener(onUpdateSDKLrcListener);
        }
    }

    @Override // com.wanos.groove.IGrooveDataInterface
    public void getMediaPlayer() throws RemoteException {
        MediaPlayerService mediaPlayerService = MediaPlayEngine.getInstance().getMediaPlayerService();
        this.mMediaPlayerService = mediaPlayerService;
        if (mediaPlayerService == null) {
            MediaPlayEngine.getInstance().addMediaPlayServiceConnectListener(new MediaPlayServiceConnectListener() { // from class: com.wanos.groove.GrooveDataBinder.3
                @Override // com.wanos.wanosplayermodule.MediaPlayServiceConnectListener
                public void onServiceConnected(MediaPlayerService mediaPlayerService2) {
                    GrooveDataBinder.this.mMediaPlayerService = mediaPlayerService2;
                    GrooveDataBinder.this.addPlayListener();
                }

                @Override // com.wanos.wanosplayermodule.MediaPlayServiceConnectListener
                public void onServiceDisconnected() {
                    GrooveDataBinder.this.removePlayListener();
                }
            }, true);
        } else {
            addPlayListener();
        }
    }

    @Override // com.wanos.groove.IGrooveDataInterface
    public void registerCallback(IGrooveDataListener callback) {
        this.mCallBack = callback;
    }

    @Override // com.wanos.groove.IGrooveDataInterface
    public void unregisterCallback(IGrooveDataListener callback) {
        this.mCallBack = null;
    }

    @Override // com.wanos.groove.IGrooveDataInterface
    public MediaInfo getCurrentMediaInfo() throws RemoteException {
        MediaPlayerService mediaPlayerService = this.mMediaPlayerService;
        if (mediaPlayerService == null) {
            Log.i(TAG, "----getCurrentMediaInfo()获取的服务是空");
            return null;
        }
        MediaInfo curGrooveSdkMediaInfo = getCurGrooveSdkMediaInfo(mediaPlayerService.getCurMediaInfo());
        if (curGrooveSdkMediaInfo != null) {
            return curGrooveSdkMediaInfo;
        }
        Log.i(TAG, "----getCurrentMediaInfo()获取的播放信息是空");
        return null;
    }

    @Override // com.wanos.groove.IGrooveDataInterface
    public List<MediaInfo> getCurrentList() throws RemoteException {
        MediaPlayerService mediaPlayerService = this.mMediaPlayerService;
        if (mediaPlayerService == null) {
            Log.i(TAG, "----getCurrentList()获取的服务是空");
            return null;
        }
        List<com.wanos.commonlibrary.bean.MediaInfo> currentList = mediaPlayerService.getCurrentList();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < currentList.size(); i++) {
            MediaInfo mediaInfoFromMediaInfoToGrooveSDKMediaInfo = fromMediaInfoToGrooveSDKMediaInfo(currentList.get(i));
            mediaInfoFromMediaInfoToGrooveSDKMediaInfo.setItemIndex(i);
            arrayList.add(mediaInfoFromMediaInfoToGrooveSDKMediaInfo);
        }
        if (arrayList.size() != 0) {
            return arrayList;
        }
        Log.i(TAG, "----getCurrentList()获取的播放列表是空");
        return null;
    }

    @Override // com.wanos.groove.IGrooveDataInterface
    public void doPlay() throws RemoteException {
        if (this.mMediaPlayerService == null) {
            Log.i(TAG, "----doPlay()获取的服务是空");
        } else {
            this.mainHandler.post(new Runnable() { // from class: com.wanos.groove.GrooveDataBinder$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m464lambda$doPlay$3$comwanosgrooveGrooveDataBinder();
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$doPlay$3$com-wanos-groove-GrooveDataBinder, reason: not valid java name */
    /* synthetic */ void m464lambda$doPlay$3$comwanosgrooveGrooveDataBinder() {
        this.mMediaPlayerService.start();
    }

    @Override // com.wanos.groove.IGrooveDataInterface
    public void playAreaContentData() throws RemoteException {
        MediaPlayerService mediaPlayerService = this.mMediaPlayerService;
        if (mediaPlayerService == null) {
            Log.i(TAG, "----playAreaContentData()获取的服务是空");
            return;
        }
        com.wanos.commonlibrary.bean.MediaInfo curMediaInfo = mediaPlayerService.getCurMediaInfo();
        if (curMediaInfo == null || curMediaInfo.getMediaType() != MediaPlayerEnum.MediaType.Music) {
            this.mainHandler.post(new Runnable() { // from class: com.wanos.groove.GrooveDataBinder$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m467lambda$playAreaContentData$4$comwanosgrooveGrooveDataBinder();
                }
            });
        } else {
            if (this.mMediaPlayerService.isPlaying()) {
                return;
            }
            this.mainHandler.post(new Runnable() { // from class: com.wanos.groove.GrooveDataBinder$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m468lambda$playAreaContentData$5$comwanosgrooveGrooveDataBinder();
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$playAreaContentData$4$com-wanos-groove-GrooveDataBinder, reason: not valid java name */
    /* synthetic */ void m467lambda$playAreaContentData$4$comwanosgrooveGrooveDataBinder() {
        this.mMediaPlayerService.playRecommendMusicMedia();
    }

    /* JADX INFO: renamed from: lambda$playAreaContentData$5$com-wanos-groove-GrooveDataBinder, reason: not valid java name */
    /* synthetic */ void m468lambda$playAreaContentData$5$comwanosgrooveGrooveDataBinder() {
        this.mMediaPlayerService.start();
    }

    @Override // com.wanos.groove.IGrooveDataInterface
    public void pre() throws RemoteException {
        if (this.mMediaPlayerService == null) {
            Log.i(TAG, "----pre()获取的服务是空");
        } else {
            this.mainHandler.post(new Runnable() { // from class: com.wanos.groove.GrooveDataBinder$$ExternalSyntheticLambda7
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m469lambda$pre$6$comwanosgrooveGrooveDataBinder();
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$pre$6$com-wanos-groove-GrooveDataBinder, reason: not valid java name */
    /* synthetic */ void m469lambda$pre$6$comwanosgrooveGrooveDataBinder() {
        this.mMediaPlayerService.playPre();
    }

    @Override // com.wanos.groove.IGrooveDataInterface
    public void next() throws RemoteException {
        if (this.mMediaPlayerService == null) {
            Log.i(TAG, "----next()获取的服务是空");
        } else {
            this.mainHandler.post(new Runnable() { // from class: com.wanos.groove.GrooveDataBinder$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m465lambda$next$7$comwanosgrooveGrooveDataBinder();
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$next$7$com-wanos-groove-GrooveDataBinder, reason: not valid java name */
    /* synthetic */ void m465lambda$next$7$comwanosgrooveGrooveDataBinder() {
        this.mMediaPlayerService.playNext();
    }

    @Override // com.wanos.groove.IGrooveDataInterface
    public void pause() throws RemoteException {
        if (this.mMediaPlayerService == null) {
            Log.i(TAG, "----pause()获取的服务是空");
        } else {
            this.mainHandler.post(new Runnable() { // from class: com.wanos.groove.GrooveDataBinder$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m466lambda$pause$8$comwanosgrooveGrooveDataBinder();
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$pause$8$com-wanos-groove-GrooveDataBinder, reason: not valid java name */
    /* synthetic */ void m466lambda$pause$8$comwanosgrooveGrooveDataBinder() {
        this.mMediaPlayerService.pause();
    }

    @Override // com.wanos.groove.IGrooveDataInterface
    public int getCurrentPlayMode() throws RemoteException {
        MediaPlayerService mediaPlayerService = this.mMediaPlayerService;
        if (mediaPlayerService == null) {
            Log.i(TAG, "----getCurrentPlayMode()获取的服务是空");
            return -1;
        }
        MediaPlayerEnum.PlayMode playMode = mediaPlayerService.getPlayMode(MediaPlayerEnum.MediaType.Music);
        if (playMode == MediaPlayerEnum.PlayMode.orderplay || playMode == MediaPlayerEnum.PlayMode.listloopplay) {
            return 0;
        }
        return playMode == MediaPlayerEnum.PlayMode.singleloopplay ? 1 : 2;
    }

    @Override // com.wanos.groove.IGrooveDataInterface
    public LyricInfo getCurrentLyric() throws RemoteException {
        if (this.mMediaPlayerService == null) {
            Log.i(TAG, "----getCurrentLyric()获取的服务是空");
            return null;
        }
        LyricInfo lyricInfo = new LyricInfo();
        if (this.mMediaPlayerService.getCurMediaInfo() != null && this.mMediaPlayerService.getCurMediaInfo().getMusicInfoBean() != null) {
            lyricInfo.setSong_id_str(this.mMediaPlayerService.getCurMediaInfo().getMusicInfoBean().getMusicId());
            lyricInfo.setSong_lyric(this.mMediaPlayerService.mLrcContent);
        }
        return lyricInfo;
    }

    public static Application getApplication() {
        Application application;
        try {
            Method declaredMethod = Class.forName(GrooveSdkAppGlobal.CLASS_FOR_NAME).getDeclaredMethod(GrooveSdkAppGlobal.CURRENT_APPLICATION, new Class[0]);
            declaredMethod.setAccessible(true);
            application = (Application) declaredMethod.invoke(null, new Object[0]);
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException unused) {
            application = null;
        }
        if (application != null) {
            return application;
        }
        try {
            Method declaredMethod2 = Class.forName(GrooveSdkAppGlobal.CLASS_FOR_NAME).getDeclaredMethod(GrooveSdkAppGlobal.GET_INITIAL_APPLICATION, new Class[0]);
            declaredMethod2.setAccessible(true);
            return (Application) declaredMethod2.invoke(null, new Object[0]);
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException unused2) {
            return application;
        }
    }

    @Override // com.wanos.groove.IGrooveDataInterface.Stub, android.os.Binder
    public boolean onTransact(int code, Parcel data, Parcel reply, int flags) {
        try {
            try {
                super.onTransact(code, data, reply, flags);
                return true;
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        } catch (RuntimeException e2) {
            Log.w(TAG, "MyClass------Unexpected remote exception", e2);
            return true;
        }
    }

    public LyricLine getLrcInfo(long time) {
        MediaPlayerService mediaPlayerService = this.mMediaPlayerService;
        if (mediaPlayerService == null) {
            Log.i(TAG, "----getCurrentLyric()获取的服务是空");
            return null;
        }
        if (mediaPlayerService.mLrcRows != null && this.mMediaPlayerService.mLrcRows.size() != 0) {
            LyricLine lyricLine = new LyricLine();
            int i = 0;
            while (i < this.mMediaPlayerService.mLrcRows.size()) {
                LrcRow lrcRow = this.mMediaPlayerService.mLrcRows.get(i);
                int i2 = i + 1;
                LrcRow lrcRow2 = i2 == this.mMediaPlayerService.mLrcRows.size() ? null : this.mMediaPlayerService.mLrcRows.get(i2);
                if ((time >= lrcRow.startTime && lrcRow2 != null && time < lrcRow2.startTime) || (time > lrcRow.startTime && lrcRow2 == null)) {
                    lyricLine.setmLineNumber(i);
                    lyricLine.setmLyric(lrcRow.getContent());
                    lyricLine.setmCurrent(this.mMediaPlayerService.getCurrentPosition());
                    return lyricLine;
                }
                i = i2;
            }
        }
        return null;
    }

    public MediaInfo getCurGrooveSdkMediaInfo(com.wanos.commonlibrary.bean.MediaInfo curMediaInfo) {
        if (curMediaInfo == null) {
            return null;
        }
        MediaInfo mediaInfoFromMediaInfoToGrooveSDKMediaInfo = fromMediaInfoToGrooveSDKMediaInfo(curMediaInfo);
        mediaInfoFromMediaInfoToGrooveSDKMediaInfo.setItemIndex(MediaPlayerManager.getInstance().getCurrentMediaIndex());
        mediaInfoFromMediaInfoToGrooveSDKMediaInfo.setCurrentDuration(this.mMediaPlayerService.getCurrentPosition());
        return mediaInfoFromMediaInfoToGrooveSDKMediaInfo;
    }

    public static MediaInfo fromMediaInfoToGrooveSDKMediaInfo(com.wanos.commonlibrary.bean.MediaInfo mediaInfo) {
        AudioBookMineChapterItemBean audioBookInfoBean;
        if (mediaInfo == null) {
            return null;
        }
        MediaInfo mediaInfo2 = new MediaInfo();
        if (mediaInfo.getMediaType() == MediaPlayerEnum.MediaType.Music) {
            MusicInfoBean musicInfoBean = mediaInfo.getMusicInfoBean();
            if (musicInfoBean != null) {
                mediaInfo2.setItemUUID(musicInfoBean.getMusicId() + "");
                mediaInfo2.setMediaName(musicInfoBean.getName());
                mediaInfo2.setMediaAuthor(MediaInfoChangeUitl.getMusicSingerName(musicInfoBean.getSingerList()));
                mediaInfo2.setMediaImage(musicInfoBean.getAvatar());
                mediaInfo2.setItemType(mediaInfo.getMediaType() + "");
                mediaInfo2.setDuration(((long) musicInfoBean.getDuration()) * 1000);
            }
        } else if (mediaInfo.getMediaType() == MediaPlayerEnum.MediaType.Audiobook && (audioBookInfoBean = mediaInfo.getAudioBookInfoBean()) != null) {
            AudioBookSpeakerBean speaker = audioBookInfoBean.getSpeaker();
            mediaInfo2.setItemUUID(speaker.getId() + "");
            mediaInfo2.setMediaName(speaker.getName());
            if (speaker != null) {
                mediaInfo2.setMediaAuthor(speaker.getAvatar());
            }
            mediaInfo2.setMediaImage(audioBookInfoBean.getAvatar());
            mediaInfo2.setItemType(mediaInfo.getMediaType() + "");
            mediaInfo2.setDuration(audioBookInfoBean.getDuration() * 1000);
        }
        return mediaInfo2;
    }
}
