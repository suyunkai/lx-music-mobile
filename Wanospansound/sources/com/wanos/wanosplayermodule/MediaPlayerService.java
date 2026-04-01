package com.wanos.wanosplayermodule;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.PlaybackParams;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import androidx.core.app.NotificationCompat;
import androidx.media3.common.C;
import com.blankj.utilcode.util.ThreadUtils;
import com.blankj.utilcode.util.Utils;
import com.danikula.videocache.CacheListener;
import com.danikula.videocache.HttpProxyCacheServer;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.WanosCommunication.bean.MediaMusicListBean;
import com.wanos.WanosCommunication.response.GetMusicListResponse;
import com.wanos.autoservice.MediaPlayer2AppInterface;
import com.wanos.bean.ProgressInfo;
import com.wanos.commonlibrary.autoservices.MediaPlayer2MediaCenterIInterface;
import com.wanos.commonlibrary.bean.AudioBookMineChapterItemBean;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.bean.MediaInfoBean;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import com.wanos.commonlibrary.event.DailyExpiredEvent;
import com.wanos.commonlibrary.event.LoginOrLogoutEvent;
import com.wanos.commonlibrary.event.MediaCollectChangeEvent;
import com.wanos.commonlibrary.event.ResourceNotExistEvent;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.mediaPlayer.listener.OnMediaInfoCallbackListener;
import com.wanos.commonlibrary.mediaPlayer.listener.OnMediaInfoListListener;
import com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener;
import com.wanos.commonlibrary.mediaPlayer.listener.OnUpdateSDKLrcListener;
import com.wanos.commonlibrary.utils.CommonUtils;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.lyric.DownloadUtil;
import com.wanos.lyric.lrc.impl.DefaultLrcBuilder;
import com.wanos.lyric.lrc.impl.LrcRow;
import com.wanos.lyric.lyric;
import com.wanos.media.playerlib.R;
import com.wanos.wanosplayermodule.MediaPlayerManager;
import com.wanos.wanosplayermodule.VolumeGradient;
import com.wanos.wanosplayermodule.audioTrack.drouteclient.WanosFadeInOutManager;
import com.wanos.wanosplayermodule.data.ExtendOperaData;
import com.wanos.wanosplayermodule.playPermission.AudioFocusManager;
import com.wanos.wanosplayermodule.playPermission.NetWorkChangeLis;
import com.wanos.wanosplayermodule.playPermission.NetWorkStatus;
import com.wanos.wanosplayermodule.playPermission.PlayPermission;
import com.wanos.wanosplayermodule.receiver.PhoneStateReceiver;
import com.wanos.wanosplayermodule.util.MediaSharedPreferUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: loaded from: classes3.dex */
public class MediaPlayerService extends Service implements MediaPlayerManager.RequestCallbackListener {
    private static final long CHANGE_MEDIA_DELAY_TIME = 1000;
    private static final String PLAY_URL = "play_url";
    public static final String TAG = "wanos:[MediaPlayerService]";
    static boolean isFirstPrepare = true;
    public static boolean playerProgressEnable = true;
    public static long startErrorTime = -1;
    private static final long waitChangeMediaTime = 2000;
    private int avaCache;
    private String currentUrl;
    private boolean isLoadingRecommendMusicMedia;
    private AudioFocusManager mAudioFocusManager;
    public String mLrcContent;
    public List<LrcRow> mLrcRows;
    private PlayPermission mPlayPermission;
    private ProgressInfo mProgressInfo;
    private List<OnAudioFocusChangeCallbackListener> onAudioFocusChangeCallbackListenerList;
    private List<OnCacheProgressCallbackListener> onCacheProgressCallbackListenerList;
    private OnHolderCreateListener onHolderCreateListener;
    private List<OnLoadMediaInfoPlayListCallbackListener> onLoadMediaInfoPlayListCallbackListenerList;
    private List<OnLoginStatusCallbackListener> onLoginStatusCallbackListenerList;
    private List<OnMediaInfoCallbackAppListener> onMediaInfoCallbackAppListenerList;
    private List<OnMediaInfoCallbackListener> onMediaInfoCallbackListenerList;
    private List<OnMediaInfoListListener> onMediaInfoListListenerList;
    private List<OnPlayTimeOutListener> onPlayTimeOutListenerList;
    private List<OnStatusCallbackListener> onStatusCallbackListenerList;
    private List<OnUpdateAppLrcListener> onUpdateAppLrcListenerList;
    private List<OnUpdateSDKLrcListener> onUpdateSDKLrcListenerList;
    int prePositionPercent;
    private MediaPlayerEnum.CallBackState preStatus;
    private String recordErrorMessage;
    private Holder uiHolder;
    private final String[] ext = {".3gp", ".3GP", ".mp4", ".MP4", ".mp3", ".ogg", ".OGG", ".MP3", ".wav", ".WAV"};
    private int delaySecondTime = 500;
    private long changeMediaDelayAlreadyWaited = 0;
    private boolean isHolderCreate = false;
    private HttpProxyCacheServer proxy = null;
    private int mDuration = 0;
    private MediaPlayerEnum.PreprareStatus preprareStatus = MediaPlayerEnum.PreprareStatus.NO_PREPRARE;
    private MediaPlayerEnum.PauseStatus pauseStatus = MediaPlayerEnum.PauseStatus.NO_PAUSE;
    private MediaPlayerEnum.PlayListLoadStatus playListLoadStatus = MediaPlayerEnum.PlayListLoadStatus.NO_LOAD;
    private final int loadCompleteDataPlayDeal = -1;
    private long mediaClickPreNextTime = 0;
    private boolean isOutWidgetPlayRecommedMusic = false;
    private int curMediaBufferingUpdatePercent = 0;
    private long waitPositionBufferingUpdateTime = 0;
    private int playMediaPosition = 0;
    private int netErrorMediaPosition = 0;
    private int playerErrorMediaPosition = 0;
    private final int NOTIFICATION_ID = 1;
    private volatile boolean isResetMeidaplayComplete = true;
    volatile boolean playerInterceptorFlag = false;
    boolean audioServiceDiedAndPauseFlag = false;
    private long startTimeStamp = 0;
    private final VolumeGradient volumeGradient = new VolumeGradient();
    public Runnable checkRunnable = new Runnable() { // from class: com.wanos.wanosplayermodule.MediaPlayerService.5
        int cacheP;

        @Override // java.lang.Runnable
        public void run() {
            if (MediaPlayerService.this.avaCache != 100) {
                if (MediaPlayerService.startErrorTime == -1) {
                    MediaPlayerService.startErrorTime = System.currentTimeMillis();
                } else if (MediaPlayerService.this.getCurMediaInfo() != null && MediaPlayerService.this.getCurMediaInfo().getMediaType() != null) {
                    if (System.currentTimeMillis() - MediaPlayerService.startErrorTime > (MediaPlayerService.this.getCurMediaInfo().getMediaType() == MediaPlayerEnum.MediaType.Audiobook ? 30000 : 5000)) {
                        MediaPlayerService.startErrorTime = System.currentTimeMillis();
                        if (MediaPlayerService.this.playMediaPosition == this.cacheP && MediaPlayerService.this.uiHolder.player != null && MediaPlayerService.this.uiHolder.player.isPlaying()) {
                            Log.e(MediaPlayerService.TAG, "onNetError");
                            NetWorkStatus.onNetError();
                        }
                    }
                }
                this.cacheP = MediaPlayerService.this.playMediaPosition;
            }
            MediaPlayerService.this.refress_time_handler.postDelayed(MediaPlayerService.this.checkRunnable, 3000L);
        }
    };
    int count = 0;
    int percent = 0;
    private final CacheListener cacheListener = new CacheListener() { // from class: com.wanos.wanosplayermodule.MediaPlayerService.6
        @Override // com.danikula.videocache.CacheListener
        public void onCacheAvailable(File file, String str, int i) {
            MediaPlayerService.this.percent = i;
            MediaPlayerService.this.onCacheProgressCallNext(i);
            Log.e(MediaPlayerService.TAG, "percentsAvailable:" + i + " url:" + str);
            if (i != MediaPlayerService.this.avaCache || i == 100) {
                MediaPlayerService.this.avaCache = i;
                if (MediaPlayerService.startErrorTime != -1) {
                    MediaPlayerService.startErrorTime = -1L;
                    NetWorkStatus.onNetSuccess();
                }
            }
        }
    };
    private final Handler refress_time_handler = new Handler();
    private final Runnable refress_time_Thread = new Runnable() { // from class: com.wanos.wanosplayermodule.MediaPlayerService.7
        @Override // java.lang.Runnable
        public void run() {
            MediaPlayerService.this.refress_time_handler.removeCallbacks(MediaPlayerService.this.refress_time_Thread);
            try {
                if (MediaPlayerService.this.uiHolder.player != null && MediaPlayerService.this.uiHolder.player.isPlaying()) {
                    int duration = MediaPlayerService.this.getDuration();
                    if (duration > 0) {
                        int iRound = Math.round(((MediaPlayerService.this.getCurrentPosition() * 1.0f) / duration) * 100.0f);
                        if (MediaPlayerService.this.playMediaPosition == MediaPlayerService.this.getCurrentPosition()) {
                            if (MediaPlayerService.playerProgressEnable) {
                                if (MediaPlayerService.this.percent > iRound || MediaPlayerService.this.percent == 100) {
                                    MediaPlayerService.this.onStatusCallbackNext(MediaPlayerEnum.CallBackState.PROGRESS, Integer.valueOf(iRound), false);
                                } else {
                                    MediaPlayerService.this.onStatusCallbackNext(MediaPlayerEnum.CallBackState.PROGRESS, Integer.valueOf(iRound), true);
                                }
                            }
                            MediaPlayerService.this.onStatusCallbackNext(MediaPlayerEnum.CallBackState.POSITION, Integer.valueOf(MediaPlayerService.this.getCurrentPosition()), Integer.valueOf(duration), true);
                            MediaPlayerService mediaPlayerService = MediaPlayerService.this;
                            mediaPlayerService.playMediaPosition = mediaPlayerService.getCurrentPosition();
                            MediaPlayerService.this.waitPositionBufferingUpdateTime += (long) MediaPlayerService.this.delaySecondTime;
                            if (MediaPlayerService.this.waitPositionBufferingUpdateTime == C.DEFAULT_SEEK_FORWARD_INCREMENT_MS) {
                                MediaPlayerService.this.onPlayTimeOutListenerList();
                            }
                        } else {
                            if (MediaPlayerService.playerProgressEnable) {
                                MediaPlayerService.this.onStatusCallbackNext(MediaPlayerEnum.CallBackState.PROGRESS, Integer.valueOf(iRound), false);
                            }
                            MediaPlayerService.this.onStatusCallbackNext(MediaPlayerEnum.CallBackState.POSITION, Integer.valueOf(MediaPlayerService.this.getCurrentPosition()), Integer.valueOf(duration), false);
                            if (MediaPlayerService.this.uiHolder.player != null && MediaPlayerService.this.uiHolder.player.isPlaying()) {
                                MediaPlayerService mediaPlayerService2 = MediaPlayerService.this;
                                mediaPlayerService2.playMediaPosition = mediaPlayerService2.getCurrentPosition();
                            }
                            MediaPlayerService.this.waitPositionBufferingUpdateTime = 0L;
                        }
                        if (MediaPlayerService.this.prePositionPercent != iRound) {
                            Log.i(MediaPlayerService.TAG, "duraction -> " + iRound);
                        } else {
                            Log.i(MediaPlayerService.TAG, "duraction -> " + iRound + " ，没变 ");
                        }
                        MediaPlayerService.this.prePositionPercent = iRound;
                        if (iRound == 1) {
                            MediaPlayerService.this.recordErrorMessage = null;
                        }
                        MediaInfo curMediaInfo = MediaPlayerService.this.getCurMediaInfo();
                        ProgressInfo progressInfo = new ProgressInfo();
                        progressInfo.setProgress(MediaPlayerService.this.getCurrentPosition());
                        progressInfo.setMediaType(curMediaInfo.getMediaType());
                        MediaPlayerEnum.MediaType mediaType = curMediaInfo.getMediaType();
                        if (mediaType == MediaPlayerEnum.MediaType.Music) {
                            progressInfo.setMediaId(curMediaInfo.getMusicInfoBean().getMusicId());
                            progressInfo.setGroupId(curMediaInfo.getGroupId());
                            progressInfo.setMediaGroupType(curMediaInfo.getMediaGroupType());
                        } else if (mediaType == MediaPlayerEnum.MediaType.Audiobook) {
                            progressInfo.setMediaId(curMediaInfo.getAudioBookInfoBean().getId());
                            progressInfo.setGroupId(curMediaInfo.getAudioBookInfoBean().getRadioId());
                        }
                        MediaSharedPreferUtils.putProgress(progressInfo);
                    }
                    if (MediaPlayerService.this.getCurMediaInfo() != null) {
                        MediaPlayerService mediaPlayerService3 = MediaPlayerService.this;
                        if (mediaPlayerService3.updateAudiobookProgressIfNeed(mediaPlayerService3.getCurMediaInfo(), MediaPlayerService.this.getCurrentPosition())) {
                            MediaPlayerManager.getInstance().updatePlayHistory(MediaPlayerService.this.getCurMediaInfo());
                        }
                    }
                }
            } catch (IllegalStateException e) {
                MediaPlayerService.this.onStatusCallbackNext(MediaPlayerEnum.CallBackState.EXCEPTION, e.toString());
            }
            MediaPlayerService.this.refress_time_handler.postDelayed(MediaPlayerService.this.refress_time_Thread, MediaPlayerService.this.delaySecondTime);
        }
    };
    private final Runnable prepareOutTimeRunnable = new Runnable() { // from class: com.wanos.wanosplayermodule.MediaPlayerService.8
        @Override // java.lang.Runnable
        public void run() {
            if (MediaPlayerService.this.preprareStatus == MediaPlayerEnum.PreprareStatus.PREPRAREING) {
                MediaPlayerService.this.onPlayTimeOutListenerList();
            }
        }
    };
    private final Runnable dealPlayerDelayRunnable = new Runnable() { // from class: com.wanos.wanosplayermodule.MediaPlayerService.9
        @Override // java.lang.Runnable
        public void run() {
            MediaPlayerService.this.refress_time_handler.removeCallbacks(this);
            if (MediaPlayerService.this.changeMediaDelayAlreadyWaited >= 1000) {
                Log.i(MediaPlayerService.TAG, "dealPlayerDelayRunnable: 1");
                try {
                    MediaPlayerService.this.reset();
                    MediaPlayerService mediaPlayerService = MediaPlayerService.this;
                    mediaPlayerService.setDataSource(mediaPlayerService.currentUrl);
                    MediaPlayerService.this.prepareAsync();
                    MediaPlayerService.this.isResetMeidaplayComplete = false;
                    Log.i(MediaPlayerService.TAG, "dealPlayerDelayRunnable: 2");
                    return;
                } catch (Exception e) {
                    MediaPlayerService.this.onStatusCallbackNext(MediaPlayerEnum.CallBackState.ERROR, e.toString());
                    return;
                }
            }
            MediaPlayerService.this.changeMediaDelayAlreadyWaited += 50;
            MediaPlayerService.this.refress_time_handler.postDelayed(this, 50L);
            Log.i(MediaPlayerService.TAG, "dealPlayerDelayRunnable: 3");
        }
    };
    private long lastInvokeTime = 0;
    private long interval = 5000;
    private long audioServieDiedTime = 0;
    private long audioServerErrorTime = 0;
    boolean isShutdown = false;
    Runnable netResumeRunnable = new Runnable() { // from class: com.wanos.wanosplayermodule.MediaPlayerService.13
        @Override // java.lang.Runnable
        public void run() {
            Log.e(MediaPlayerService.TAG, "try connect...");
            if (!MediaPlayerService.this.isShutdown) {
                MediaPlayerService mediaPlayerService = MediaPlayerService.this;
                mediaPlayerService.netErrorMediaPosition = mediaPlayerService.getCurrentPosition();
                MediaPlayerService.this.isShutdown = true;
                MediaPlayerService.this.proxyShutdown();
            }
            MediaPlayerService mediaPlayerService2 = MediaPlayerService.this;
            mediaPlayerService2.play(mediaPlayerService2.getCurMediaInfo());
        }
    };

    public static final class Holder {
        public AssetFileDescriptor assetDescriptor;
        public MediaPlayer player;
        public SurfaceHolder surfaceHolder;
        public SurfaceView surfaceView;
    }

    public interface OnAudioFocusChangeCallbackListener {
        void onAudioFocusChangeCallback();
    }

    public interface OnCacheProgressCallbackListener {
        void onCacheProgressCallbackNext(int i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    interface OnHolderCreateListener {
        void onHolderCreate();
    }

    public interface OnLoadMediaInfoPlayListCallbackListener {
        void onFailLoadMediaInfoPlayListCallback();

        void onStartLoadMediaInfoPlayListCallback();

        void onSuccessfulLoadMediaInfoPlayListCallback();
    }

    public interface OnLoginStatusCallbackListener {
        void onLoginStatusCallback();

        void onVipStatusCallback();
    }

    public interface OnMediaInfoCallbackAppListener {
        void onMediaInfoCallbackAppNext(MediaPlayerEnum.MediaInfoCallbackType mediaInfoCallbackType, MediaInfo mediaInfo);
    }

    public interface OnPlayTimeOutListener {
        void onPlayTimeOut();
    }

    public interface OnUpdateAppLrcListener {
        void onUpdateAppLrc(MediaPlayerEnum.UpdateLrcCallbackType updateLrcCallbackType, List<LrcRow> list);
    }

    static /* synthetic */ void lambda$seekTo$1(boolean z) {
    }

    private File getAudioCacheDir(Context context) {
        return new File(context.getExternalFilesDir("music"), "audio-cache");
    }

    public File getVideoFile(Context context, boolean z) {
        String str = z ? "/video" : "/audio";
        File cacheDir = context.getCacheDir();
        if (cacheDir == null) {
            return null;
        }
        File file = new File(cacheDir.getPath() + str);
        if (!file.exists()) {
            file.mkdir();
        }
        return file;
    }

    public MediaPlayerService setProgressInterval(int i) {
        this.delaySecondTime = i;
        return this;
    }

    public void setConnect(boolean z) {
        HttpProxyCacheServer httpProxyCacheServer = this.proxy;
        if (httpProxyCacheServer != null) {
            httpProxyCacheServer.setConnect(z);
        }
    }

    public MediaPlayerEnum.PlayListLoadStatus getPlayListLoadStatus() {
        return this.playListLoadStatus;
    }

    public void start() {
        playerProgressEnable = true;
        if (this.preprareStatus == MediaPlayerEnum.PreprareStatus.PREPRAREED) {
            if (this.uiHolder.player != null) {
                if (this.playerInterceptorFlag) {
                    if (this.playerInterceptorFlag) {
                        Log.i(TAG, "被其他音源抢占，拦截了");
                    }
                    this.playerInterceptorFlag = false;
                    this.uiHolder.player.start();
                    this.uiHolder.player.pause();
                    onStatusCallbackNext(MediaPlayerEnum.CallBackState.PAUSE, new Object[0]);
                    return;
                }
                AudioFocusManager audioFocusManager = this.mAudioFocusManager;
                if (audioFocusManager != null && audioFocusManager.audioFocusRequest()) {
                    this.uiHolder.player.start();
                    this.refress_time_handler.removeCallbacks(this.checkRunnable);
                    this.refress_time_handler.post(this.checkRunnable);
                    Log.i(TAG, "开始start");
                    this.startTimeStamp = System.currentTimeMillis();
                    if (isPlaying()) {
                        Log.d(TAG, "正常播放");
                        this.waitPositionBufferingUpdateTime = 0L;
                        this.playMediaPosition = 0;
                        onStatusCallbackNext(MediaPlayerEnum.CallBackState.STARTED, new Object[0]);
                    }
                    if (getCurMediaInfo() == null || getCurMediaInfo().getMediaType() != MediaPlayerEnum.MediaType.Audiobook) {
                        return;
                    }
                    Log.d(TAG, "有声书校验");
                    this.mPlayPermission.audioBookPermissionVerify(getCurMediaInfo(), new PlayPermission.AudioBookCallback() { // from class: com.wanos.wanosplayermodule.MediaPlayerService.1
                        @Override // com.wanos.wanosplayermodule.playPermission.PlayPermission.AudioBookCallback
                        public void action() {
                            MediaPlayerService.this.pause();
                            Log.d(MediaPlayerService.TAG, "audio Book pause");
                        }
                    });
                    return;
                }
                onAudioFocusChangeCallback();
                Log.d(TAG, "没有获得焦点 不能播放");
                return;
            }
            return;
        }
        if (this.preprareStatus == MediaPlayerEnum.PreprareStatus.NO_PREPRARE || this.preprareStatus == MediaPlayerEnum.PreprareStatus.PREPRARE_ERROR) {
            Log.i(TAG, "preprareStatus error");
            if (getCurMediaInfo() != null) {
                play(getCurMediaInfo());
                Log.i(TAG, "start: requestMediaDataListData");
                MediaPlayerManager.getInstance().requestMediaDataListData(-1, this);
                return;
            }
            return;
        }
        Log.i(TAG, "start: preprareStatus  else >> preprareStatus = " + this.preprareStatus.name());
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x00c4 A[PHI: r3
  0x00c4: PHI (r3v2 long) = (r3v0 long), (r3v0 long), (r3v3 long) binds: [B:37:0x00c2, B:34:0x00b7, B:32:0x00a0] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void dealMusicOrderPlayToLastSongPlayComplete() {
        /*
            Method dump skipped, instruction units count: 203
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wanos.wanosplayermodule.MediaPlayerService.dealMusicOrderPlayToLastSongPlayComplete():void");
    }

    private void setCurMediainfo(MediaInfo mediaInfo) {
        MediaPlayerManager.getInstance().setCurMediaInfo(mediaInfo);
        onMediaInfoCallbackNext(MediaPlayerEnum.MediaInfoCallbackType.mediaInfoType, mediaInfo);
        onMediaInfoCallbackAppNext(MediaPlayerEnum.MediaInfoCallbackType.mediaInfoType, mediaInfo);
    }

    private void onMediaInfoListListenerListChanged() {
        List<OnMediaInfoListListener> list = this.onMediaInfoListListenerList;
        if (list == null || list.size() <= 0) {
            return;
        }
        for (OnMediaInfoListListener onMediaInfoListListener : this.onMediaInfoListListenerList) {
            if (onMediaInfoListListener != null) {
                onMediaInfoListListener.onListChanged();
            }
        }
    }

    public MediaInfo getCurMediaInfo() {
        return MediaPlayerManager.getInstance().getCurMediaInfo();
    }

    private void listDataLoadComplete(int i) {
        if (i == -1) {
            return;
        }
        if (i == 1) {
            onPlayPre();
        } else if (i == 0) {
            onPlayNext();
        } else if (i == 2) {
            autoPlayNext();
        }
    }

    private void onPlayPre() {
        this.mediaClickPreNextTime = 0L;
        playPre();
    }

    private void onPlayNext() {
        this.mediaClickPreNextTime = 0L;
        playNext();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void autoPlayNext() {
        if (MediaPlayerManager.getInstance().getMediaPlayList() != null && MediaPlayerManager.getInstance().getMediaPlayList().size() > 0) {
            MediaPlayerEnum.MediaType mediaType = getCurMediaInfo().getMediaType();
            if (playChange()) {
                if (getPlayMode(mediaType) == MediaPlayerEnum.PlayMode.orderplay) {
                    listLoopPlay();
                    Log.d(TAG, "playMode:orderPlay");
                    return;
                }
                if (getPlayMode(mediaType) == MediaPlayerEnum.PlayMode.singleloopplay) {
                    singleLoopPlay();
                    Log.d(TAG, "playMode:singleLoopPlay");
                    return;
                } else if (getPlayMode(mediaType) == MediaPlayerEnum.PlayMode.randomplay) {
                    randomPlayBack();
                    Log.d(TAG, "playMode:randomPlayBack");
                    return;
                } else if (getPlayMode(mediaType) == MediaPlayerEnum.PlayMode.listloopplay) {
                    listLoopPlay();
                    Log.d(TAG, "playMode:listLoopPlay");
                    return;
                } else {
                    Log.d(TAG, "no playMode");
                    return;
                }
            }
            Log.d(TAG, "playChange is false");
            return;
        }
        Log.d(TAG, "requestMediaDataListData:autoPlayNext");
        MediaPlayerManager.getInstance().requestMediaDataListData(2, this);
    }

    public boolean playChange() {
        List<MediaInfo> currentList = getCurrentList();
        MediaInfo curMediaInfo = getCurMediaInfo();
        if (getPlayMode(curMediaInfo.getMediaType()) != MediaPlayerEnum.PlayMode.singleloopplay && curMediaInfo.getMediaType() == MediaPlayerEnum.MediaType.Audiobook) {
            AudioBookMineChapterItemBean audioBookInfoBean = curMediaInfo.getAudioBookInfoBean();
            if (currentList != null && currentList.size() != 0) {
                for (int i = 0; i < currentList.size(); i++) {
                    AudioBookMineChapterItemBean audioBookInfoBean2 = currentList.get(i).getAudioBookInfoBean();
                    if (audioBookInfoBean2 != null && audioBookInfoBean2.getId() == audioBookInfoBean.getId()) {
                        int i2 = i + 1;
                        if (i2 >= currentList.size() || currentList.get(i2).getAudioBookInfoBean().getIsVip() != 1 || UserInfoUtil.isVipAndUnexpired()) {
                            return true;
                        }
                        if (UserInfoUtil.isLogin()) {
                            onVipStatusCallback();
                        } else {
                            onLoginStatusCallback();
                        }
                        seekTo(0L);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void collect(boolean z) {
        if (!UserInfoUtil.isLogin()) {
            onLoginStatusCallback();
        } else {
            MediaPlayerManager.getInstance().collectOrCannelRequest(getCurMediaInfo(), z, getCurMediaInfo().getSource(), this);
        }
    }

    public void onCollect(MediaPlayerEnum.MediaType mediaType, long j, boolean z) {
        boolean zUpdateCurMediaCollectStatus = MediaPlayerManager.getInstance().updateCurMediaCollectStatus(mediaType, j, z);
        MediaInfo mediaInfoUpdatePlayListCollectStatus = MediaPlayerManager.getInstance().updatePlayListCollectStatus(mediaType, j, z);
        if (zUpdateCurMediaCollectStatus) {
            onMediaInfoCallbackNext(MediaPlayerEnum.MediaInfoCallbackType.favorStatus, getCurMediaInfo());
            onMediaInfoCallbackAppNext(MediaPlayerEnum.MediaInfoCallbackType.favorStatus, getCurMediaInfo());
            MediaInfo curMediaInfo = getCurMediaInfo();
            if (curMediaInfo.getMediaType() == MediaPlayerEnum.MediaType.Music) {
                curMediaInfo.getMusicInfoBean().setCollection(z);
            } else if (curMediaInfo.getMediaType() == MediaPlayerEnum.MediaType.Audiobook) {
                curMediaInfo.getAudioBookInfoBean().setIsCollect(z ? 1 : 0);
            }
            MediaSharedPreferUtils.putMediainfo(curMediaInfo);
        } else if (mediaInfoUpdatePlayListCollectStatus != null) {
            onMediaInfoCallbackAppNext(MediaPlayerEnum.MediaInfoCallbackType.favorStatus, mediaInfoUpdatePlayListCollectStatus);
        }
        EventBus.getDefault().post(new MediaCollectChangeEvent(mediaType == MediaPlayerEnum.MediaType.Music, j, z));
    }

    private void updateMediaInfoIndex(MediaInfo mediaInfo, boolean z) {
        if (mediaInfo.getMediaType() == MediaPlayerEnum.MediaType.Music) {
            int index = getCurMediaInfo().getMusicInfoBean().getIndex();
            Log.i(TAG, "index1 = " + index);
            if (z) {
                if (index >= 0) {
                    mediaInfo.getMusicInfoBean().setIndex(index + 1);
                    return;
                }
                return;
            } else {
                if (index > 0) {
                    mediaInfo.getMusicInfoBean().setIndex(index - 1);
                    return;
                }
                return;
            }
        }
        if (mediaInfo.getMediaType() == MediaPlayerEnum.MediaType.Audiobook) {
            int index2 = getCurMediaInfo().getAudioBookInfoBean().getIndex();
            if (z) {
                if (index2 >= 0) {
                    mediaInfo.getAudioBookInfoBean().setIndex(index2 + 1);
                }
            } else if (index2 > 0) {
                mediaInfo.getAudioBookInfoBean().setIndex(index2 - 1);
            }
        }
    }

    public void playNext() {
        AudioFocusManager audioFocusManager;
        int i;
        AudioFocusManager audioFocusManager2;
        if ((PhoneStateReceiver.lastState == 2 || PhoneStateReceiver.lastState == 3 || PhoneStateReceiver.lastState == 4 || PhoneStateReceiver.lastState == 0) && (audioFocusManager = this.mAudioFocusManager) != null && !audioFocusManager.audioFocusRequest()) {
            onAudioFocusChangeCallback();
            return;
        }
        if (CommonUtils.isIs371() && (audioFocusManager2 = this.mAudioFocusManager) != null && !audioFocusManager2.audioFocusRequest()) {
            onAudioFocusChangeCallback();
            return;
        }
        int currentMediaIndex = MediaPlayerManager.getInstance().getCurrentMediaIndex();
        Log.i(TAG, "currentindex = " + currentMediaIndex);
        if (getPlayMode(getCurMediaInfo().getMediaType()) == MediaPlayerEnum.PlayMode.randomplay) {
            if (MediaPlayerManager.getInstance().getMediaRandomList() != null && MediaPlayerManager.getInstance().getMediaRandomList().size() > 0) {
                MediaInfo curMediaInfo = getCurMediaInfo();
                if (curMediaInfo != null) {
                    i = 0;
                    while (i < MediaPlayerManager.getInstance().getMediaRandomList().size()) {
                        MediaInfo mediaInfo = MediaPlayerManager.getInstance().getMediaRandomList().get(i);
                        if (mediaInfo != null && curMediaInfo.getMediaType() == mediaInfo.getMediaType() && ((curMediaInfo.getMediaType() == MediaPlayerEnum.MediaType.Music && curMediaInfo.getMusicInfoBean() != null && mediaInfo.getMusicInfoBean() != null && curMediaInfo.getMusicInfoBean().getMusicId() == mediaInfo.getMusicInfoBean().getMusicId()) || (curMediaInfo.getMediaType() == MediaPlayerEnum.MediaType.Audiobook && curMediaInfo.getAudioBookInfoBean() != null && mediaInfo.getAudioBookInfoBean() != null && curMediaInfo.getAudioBookInfoBean().getId() == mediaInfo.getAudioBookInfoBean().getId()))) {
                            break;
                        } else {
                            i++;
                        }
                    }
                    i = 0;
                } else {
                    i = 0;
                }
                int i2 = i + 1;
                if (i2 >= MediaPlayerManager.getInstance().getMediaRandomList().size()) {
                    i2 = 0;
                }
                MediaInfo mediaInfo2 = MediaPlayerManager.getInstance().getMediaRandomList().get(i2);
                while (i < MediaPlayerManager.getInstance().getMediaPlayList().size()) {
                    MediaInfo mediaInfo3 = MediaPlayerManager.getInstance().getMediaPlayList().get(i);
                    if (mediaInfo3 != null && mediaInfo2 != null && mediaInfo2.getMediaType() == mediaInfo3.getMediaType() && ((mediaInfo2.getMediaType() == MediaPlayerEnum.MediaType.Music && mediaInfo2.getMusicInfoBean() != null && mediaInfo3.getMusicInfoBean() != null && mediaInfo2.getMusicInfoBean().getMusicId() == mediaInfo3.getMusicInfoBean().getMusicId()) || (mediaInfo2.getMediaType() == MediaPlayerEnum.MediaType.Audiobook && mediaInfo2.getAudioBookInfoBean() != null && mediaInfo3.getAudioBookInfoBean() != null && mediaInfo2.getAudioBookInfoBean().getId() == mediaInfo3.getAudioBookInfoBean().getId()))) {
                        currentMediaIndex = i;
                        break;
                    }
                    i++;
                }
                MediaPlayerManager.getInstance().updateCurrentIndex(currentMediaIndex);
                MediaInfo mediaInfo4 = MediaPlayerManager.getInstance().getMediaPlayList().get(currentMediaIndex);
                updateAudiobookProgressIfNeed(mediaInfo4, 0L);
                play(mediaInfo4);
                return;
            }
            Log.i(TAG, "playNext: requestMediaDataListData 1");
            MediaPlayerManager.getInstance().requestMediaDataListData(0, this);
            return;
        }
        if (MediaPlayerManager.getInstance().getMediaPlayList() != null && MediaPlayerManager.getInstance().getMediaPlayList().size() > 0) {
            int i3 = currentMediaIndex + 1;
            i = i3 < MediaPlayerManager.getInstance().getMediaPlayList().size() ? i3 : 0;
            MediaPlayerManager.getInstance().updateCurrentIndex(i);
            play(MediaPlayerManager.getInstance().getMediaPlayList().get(i));
            return;
        }
        Log.i(TAG, "playNext: requestMediaDataListData 2");
        MediaPlayerManager.getInstance().requestMediaDataListData(0, this);
    }

    public void playPre() {
        AudioFocusManager audioFocusManager;
        int i;
        AudioFocusManager audioFocusManager2;
        if ((PhoneStateReceiver.lastState == 2 || PhoneStateReceiver.lastState == 3 || PhoneStateReceiver.lastState == 4 || PhoneStateReceiver.lastState == 0) && (audioFocusManager = this.mAudioFocusManager) != null && !audioFocusManager.audioFocusRequest()) {
            onAudioFocusChangeCallback();
            return;
        }
        if (CommonUtils.isIs371() && (audioFocusManager2 = this.mAudioFocusManager) != null && !audioFocusManager2.audioFocusRequest()) {
            onAudioFocusChangeCallback();
            return;
        }
        int currentMediaIndex = MediaPlayerManager.getInstance().getCurrentMediaIndex();
        if (getPlayMode(getCurMediaInfo().getMediaType()) == MediaPlayerEnum.PlayMode.randomplay) {
            if (MediaPlayerManager.getInstance().getMediaRandomList() != null && MediaPlayerManager.getInstance().getMediaRandomList().size() > 0) {
                MediaInfo curMediaInfo = getCurMediaInfo();
                if (curMediaInfo != null) {
                    i = 0;
                    while (i < MediaPlayerManager.getInstance().getMediaRandomList().size()) {
                        MediaInfo mediaInfo = MediaPlayerManager.getInstance().getMediaRandomList().get(i);
                        if (mediaInfo != null && curMediaInfo.getMediaType() == mediaInfo.getMediaType() && ((curMediaInfo.getMediaType() == MediaPlayerEnum.MediaType.Music && curMediaInfo.getMusicInfoBean() != null && mediaInfo.getMusicInfoBean() != null && curMediaInfo.getMusicInfoBean().getMusicId() == mediaInfo.getMusicInfoBean().getMusicId()) || (curMediaInfo.getMediaType() == MediaPlayerEnum.MediaType.Audiobook && curMediaInfo.getAudioBookInfoBean() != null && mediaInfo.getAudioBookInfoBean() != null && curMediaInfo.getAudioBookInfoBean().getId() == mediaInfo.getAudioBookInfoBean().getId()))) {
                            break;
                        } else {
                            i++;
                        }
                    }
                    i = 0;
                } else {
                    i = 0;
                }
                int size = i - 1;
                if (size < 0) {
                    size = MediaPlayerManager.getInstance().getMediaRandomList().size() - 1;
                }
                MediaInfo mediaInfo2 = MediaPlayerManager.getInstance().getMediaRandomList().get(size);
                for (int i2 = 0; i2 < MediaPlayerManager.getInstance().getMediaPlayList().size(); i2++) {
                    MediaInfo mediaInfo3 = MediaPlayerManager.getInstance().getMediaPlayList().get(i2);
                    if (mediaInfo3 != null && mediaInfo2 != null && mediaInfo2.getMediaType() == mediaInfo3.getMediaType() && ((mediaInfo2.getMediaType() == MediaPlayerEnum.MediaType.Music && mediaInfo2.getMusicInfoBean() != null && mediaInfo3.getMusicInfoBean() != null && mediaInfo2.getMusicInfoBean().getMusicId() == mediaInfo3.getMusicInfoBean().getMusicId()) || (mediaInfo2.getMediaType() == MediaPlayerEnum.MediaType.Audiobook && mediaInfo2.getAudioBookInfoBean() != null && mediaInfo3.getAudioBookInfoBean() != null && mediaInfo2.getAudioBookInfoBean().getId() == mediaInfo3.getAudioBookInfoBean().getId()))) {
                        currentMediaIndex = i2;
                        break;
                    }
                }
                MediaPlayerManager.getInstance().updateCurrentIndex(currentMediaIndex);
                MediaInfo mediaInfo4 = MediaPlayerManager.getInstance().getMediaPlayList().get(currentMediaIndex);
                updateAudiobookProgressIfNeed(mediaInfo4, 0L);
                play(mediaInfo4);
                return;
            }
            Log.i(TAG, "playPre: requestMediaDataListData 1");
            MediaPlayerManager.getInstance().requestMediaDataListData(1, this);
            return;
        }
        if (MediaPlayerManager.getInstance().getMediaPlayList() != null && MediaPlayerManager.getInstance().getMediaPlayList().size() > 0) {
            int size2 = currentMediaIndex - 1;
            if (size2 < 0) {
                size2 = MediaPlayerManager.getInstance().getMediaPlayList().size() - 1;
            }
            MediaPlayerManager.getInstance().updateCurrentIndex(size2);
            MediaInfo mediaInfo5 = MediaPlayerManager.getInstance().getMediaPlayList().get(size2);
            updateAudiobookProgressIfNeed(mediaInfo5, 0L);
            play(mediaInfo5);
            return;
        }
        Log.i(TAG, "playPre: requestMediaDataListData 2");
        MediaPlayerManager.getInstance().requestMediaDataListData(1, this);
    }

    private boolean orderPlay() {
        int currentMediaIndex = MediaPlayerManager.getInstance().getCurrentMediaIndex() + 1;
        if (MediaPlayerManager.getInstance().getMediaPlayList() == null || MediaPlayerManager.getInstance().getMediaPlayList().size() <= 0 || currentMediaIndex >= MediaPlayerManager.getInstance().getMediaPlayList().size()) {
            return false;
        }
        MediaPlayerManager.getInstance().updateCurrentIndex(currentMediaIndex);
        play(MediaPlayerManager.getInstance().getMediaPlayList().get(currentMediaIndex));
        return true;
    }

    private void randomPlayBack() {
        onPlayNext();
    }

    private boolean singleLoopPlay() {
        int currentMediaIndex = MediaPlayerManager.getInstance().getCurrentMediaIndex();
        if (currentMediaIndex != -1) {
            if (MediaPlayerManager.getInstance().getMediaPlayList() == null || MediaPlayerManager.getInstance().getMediaPlayList().size() <= 0) {
                return true;
            }
            updateAudiobookProgressIfNeed(MediaPlayerManager.getInstance().getMediaPlayList().get(currentMediaIndex), 0L);
            play(MediaPlayerManager.getInstance().getMediaPlayList().get(currentMediaIndex));
            return true;
        }
        play(MediaPlayerManager.getInstance().getCurMediaInfo());
        Log.i(TAG, "singleLoopPlay: 单曲循环2");
        return true;
    }

    public boolean listLoopPlay() {
        int currentMediaIndex = MediaPlayerManager.getInstance().getCurrentMediaIndex();
        if (MediaPlayerManager.getInstance().getMediaPlayList() != null && MediaPlayerManager.getInstance().getMediaPlayList().size() > 0) {
            int i = currentMediaIndex + 1;
            if (i >= MediaPlayerManager.getInstance().getMediaPlayList().size()) {
                i = 0;
            }
            MediaPlayerManager.getInstance().updateCurrentIndex(i);
            MediaInfo mediaInfo = MediaPlayerManager.getInstance().getMediaPlayList().get(i);
            if (mediaInfo.getMediaType() == MediaPlayerEnum.MediaType.Audiobook) {
                mediaInfo.getAudioBookInfoBean().setProgress(0L);
            }
            play(mediaInfo);
        }
        return false;
    }

    public boolean isPlaying() {
        if (this.uiHolder.player != null) {
            try {
                return this.uiHolder.player.isPlaying();
            } catch (IllegalStateException unused) {
            }
        }
        return false;
    }

    public boolean isPreparing() {
        return this.preStatus == MediaPlayerEnum.CallBackState.PREPARING;
    }

    public void pause(boolean z) {
        if (this.uiHolder.player != null) {
            try {
                Log.i(TAG, "pause: isLoseFocus = true");
                this.uiHolder.player.pause();
                this.refress_time_handler.removeCallbacks(this.checkRunnable);
                onStatusCallbackNext(MediaPlayerEnum.CallBackState.PAUSE, true, Boolean.valueOf(z));
            } catch (IllegalStateException e) {
                Log.i(TAG, "pause: illegalStateException ，" + e);
                onStatusCallbackNext(MediaPlayerEnum.CallBackState.ERROR, e.toString());
            }
        }
    }

    public void pause() {
        pause((VolumeGradientCompleteListener) null);
    }

    public void pause(final VolumeGradientCompleteListener volumeGradientCompleteListener) {
        Log.i(TAG, "pause: listener = " + volumeGradientCompleteListener);
        if (this.uiHolder.player == null) {
            if (volumeGradientCompleteListener != null) {
                volumeGradientCompleteListener.onVolumeFadeComplete();
                return;
            }
            return;
        }
        try {
            if (CommonUtils.isIs371()) {
                this.pauseStatus = MediaPlayerEnum.PauseStatus.PAUSING;
                onStatusCallbackNext(MediaPlayerEnum.CallBackState.PAUSING, new Object[0]);
                WanosFadeInOutManager.getInstance().pause(new WanosFadeInOutManager.PauseInner() { // from class: com.wanos.wanosplayermodule.MediaPlayerService$$ExternalSyntheticLambda1
                    @Override // com.wanos.wanosplayermodule.audioTrack.drouteclient.WanosFadeInOutManager.PauseInner
                    public final void innerPause() {
                        this.f$0.m699lambda$pause$0$comwanoswanosplayermoduleMediaPlayerService(volumeGradientCompleteListener);
                    }
                });
            } else {
                this.uiHolder.player.pause();
                this.refress_time_handler.removeCallbacks(this.checkRunnable);
                onStatusCallbackNext(MediaPlayerEnum.CallBackState.PAUSE, new Object[0]);
                if (volumeGradientCompleteListener != null) {
                    volumeGradientCompleteListener.onVolumeFadeComplete();
                }
            }
        } catch (IllegalStateException e) {
            onStatusCallbackNext(MediaPlayerEnum.CallBackState.ERROR, e.toString());
            if (volumeGradientCompleteListener != null) {
                volumeGradientCompleteListener.onVolumeFadeComplete();
            }
        }
    }

    /* JADX INFO: renamed from: lambda$pause$0$com-wanos-wanosplayermodule-MediaPlayerService, reason: not valid java name */
    /* synthetic */ void m699lambda$pause$0$comwanoswanosplayermoduleMediaPlayerService(VolumeGradientCompleteListener volumeGradientCompleteListener) {
        this.uiHolder.player.pause();
        this.refress_time_handler.removeCallbacks(this.checkRunnable);
        this.pauseStatus = MediaPlayerEnum.PauseStatus.PAUSED;
        onStatusCallbackNext(MediaPlayerEnum.CallBackState.PAUSE, new Object[0]);
        if (volumeGradientCompleteListener != null) {
            volumeGradientCompleteListener.onVolumeFadeComplete();
        }
    }

    public void onStopPlayBecauseFileNotExist() {
        this.preprareStatus = MediaPlayerEnum.PreprareStatus.PREPRARE_ERROR;
        onStatusCallbackNext(MediaPlayerEnum.CallBackState.PAUSE, new Object[0]);
        Log.i(TAG, "onStopPlayBecauseFileNotExist()----");
    }

    public void stop() {
        if (this.uiHolder.player != null) {
            Log.i(TAG, "非preparing状态下调用stop()");
            try {
                this.uiHolder.player.stop();
            } catch (IllegalStateException e) {
                e.printStackTrace();
                Log.i(TAG, "stop: e: " + e);
            }
        }
    }

    public void reset() {
        if (this.uiHolder.player != null) {
            Log.i(TAG, "reset()调用");
            try {
                this.uiHolder.player.reset();
            } catch (IllegalStateException e) {
                e.printStackTrace();
                Log.i(TAG, "reset: e: " + e);
            }
        }
    }

    public void setDataSource(String str) {
        if (this.uiHolder.player != null) {
            Log.i(TAG, "setDataSource()调用----" + str);
            try {
                this.uiHolder.player.setDataSource(str);
            } catch (IOException e) {
                e.printStackTrace();
                Log.i(TAG, "setDataSource: e: " + e);
            }
        }
    }

    public boolean setPlaySpeed(float f) {
        if (!isPlaying()) {
            Log.i(TAG, "not playing ");
            MediaSharedPreferUtils.putDoubleSpeed(f);
            return false;
        }
        try {
            Log.i(TAG, "is playing ");
            PlaybackParams playbackParams = this.uiHolder.player.getPlaybackParams();
            playbackParams.setSpeed(f);
            this.uiHolder.player.setPlaybackParams(playbackParams);
            MediaSharedPreferUtils.putDoubleSpeed(f);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void prepareAsync() {
        if (this.uiHolder.player != null) {
            Log.i(TAG, "prepareAsync()调用");
            try {
                this.uiHolder.player.prepareAsync();
            } catch (IllegalStateException e) {
                onStatusCallbackNext(MediaPlayerEnum.CallBackState.ERROR, e.toString());
            }
            this.refress_time_handler.removeCallbacks(this.prepareOutTimeRunnable);
            if (this.preprareStatus == MediaPlayerEnum.PreprareStatus.PREPRAREING) {
                this.refress_time_handler.postDelayed(this.prepareOutTimeRunnable, ((long) this.delaySecondTime) * 15);
            }
        }
    }

    public MediaPlayerEnum.PreprareStatus getPreprareStatus() {
        return this.preprareStatus;
    }

    public MediaPlayerEnum.PauseStatus getPauseStatus() {
        return this.pauseStatus;
    }

    public int getCurrentPosition() {
        if (this.uiHolder.player == null || this.preprareStatus != MediaPlayerEnum.PreprareStatus.PREPRAREED) {
            return 0;
        }
        return this.uiHolder.player.getCurrentPosition();
    }

    public int getDuration() {
        if (this.uiHolder.player != null && this.preprareStatus == MediaPlayerEnum.PreprareStatus.PREPRAREED) {
            return this.uiHolder.player.getDuration();
        }
        Log.i(TAG, "getDuration: 异常：当前播放状态=" + this.preprareStatus);
        return -1;
    }

    public void seekTo(long j) {
        seekTo(j, 3);
    }

    public void seekTo(final long j, final int i) {
        if (this.uiHolder.player == null) {
            return;
        }
        long duration = getDuration();
        if (duration <= 0 || j < 0 || j > duration) {
            return;
        }
        if (!playerProgressEnable) {
            this.volumeGradient.cancleAnimator();
        }
        playerProgressEnable = false;
        this.volumeGradient.volumeGradient(this.uiHolder.player, this.volumeGradient.getCurV(), 0.0f, new VolumeGradient.VolumeGradientCallback() { // from class: com.wanos.wanosplayermodule.MediaPlayerService$$ExternalSyntheticLambda3
            @Override // com.wanos.wanosplayermodule.VolumeGradient.VolumeGradientCallback
            public final void onGradEnd(boolean z) {
                this.f$0.m701lambda$seekTo$2$comwanoswanosplayermoduleMediaPlayerService(j, i, z);
            }
        }, false);
        this.waitPositionBufferingUpdateTime = 0L;
        this.playMediaPosition = getCurrentPosition();
    }

    /* JADX INFO: renamed from: lambda$seekTo$2$com-wanos-wanosplayermodule-MediaPlayerService, reason: not valid java name */
    /* synthetic */ void m701lambda$seekTo$2$comwanoswanosplayermoduleMediaPlayerService(long j, int i, boolean z) {
        if (z) {
            return;
        }
        playerProgressEnable = true;
        this.uiHolder.player.seekTo(j, i);
        this.volumeGradient.volumeGradient(this.uiHolder.player, 0.0f, this.volumeGradient.getCurV(), new VolumeGradient.VolumeGradientCallback() { // from class: com.wanos.wanosplayermodule.MediaPlayerService$$ExternalSyntheticLambda2
            @Override // com.wanos.wanosplayermodule.VolumeGradient.VolumeGradientCallback
            public final void onGradEnd(boolean z2) {
                MediaPlayerService.lambda$seekTo$1(z2);
            }
        }, false);
    }

    public void release() {
        HttpProxyCacheServer httpProxyCacheServer = this.proxy;
        if (httpProxyCacheServer != null) {
            CacheListener cacheListener = this.cacheListener;
            if (cacheListener != null) {
                httpProxyCacheServer.unregisterCacheListener(cacheListener);
            }
            this.proxy.shutdown();
        }
        if (this.uiHolder.player != null) {
            if (this.uiHolder.player.isPlaying()) {
                this.uiHolder.player.stop();
            }
            this.uiHolder.player.reset();
            this.uiHolder.player.release();
            this.uiHolder.player = null;
        }
        this.refress_time_handler.removeCallbacks(this.refress_time_Thread);
        this.refress_time_handler.removeCallbacks(this.prepareOutTimeRunnable);
    }

    public void setSurfaceView(SurfaceView surfaceView) {
        if (surfaceView == null) {
            onStatusCallbackNext(MediaPlayerEnum.CallBackState.SURFACEVIEW_NULL, this.uiHolder.player);
            return;
        }
        this.uiHolder.surfaceView = surfaceView;
        Holder holder = this.uiHolder;
        holder.surfaceHolder = holder.surfaceView.getHolder();
        this.uiHolder.surfaceHolder.addCallback(new AnonymousClass2());
    }

    /* JADX INFO: renamed from: com.wanos.wanosplayermodule.MediaPlayerService$2, reason: invalid class name */
    class AnonymousClass2 implements SurfaceHolder.Callback {
        AnonymousClass2() {
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceCreated(final SurfaceHolder surfaceHolder) {
            MediaPlayerService.this.isHolderCreate = true;
            if (MediaPlayerService.this.uiHolder.player != null && surfaceHolder != null) {
                if (MediaPlayerService.this.uiHolder.surfaceView != null) {
                    MediaPlayerService.this.uiHolder.surfaceView.post(new Runnable() { // from class: com.wanos.wanosplayermodule.MediaPlayerService$2$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.m702x46921ac7(surfaceHolder);
                        }
                    });
                }
                try {
                    MediaPlayerService.this.uiHolder.player.setDataSource(MediaPlayerService.this.currentUrl);
                    MediaPlayerService.this.uiHolder.player.setSurface(surfaceHolder.getSurface());
                    MediaPlayerService.this.prepareAsync();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            MediaPlayerService.this.onStatusCallbackNext(MediaPlayerEnum.CallBackState.SURFACEVIEW_CREATE, surfaceHolder);
            MediaPlayerService.this.onHolderCreateNext();
        }

        /* JADX INFO: renamed from: lambda$surfaceCreated$0$com-wanos-wanosplayermodule-MediaPlayerService$2, reason: not valid java name */
        /* synthetic */ void m702x46921ac7(SurfaceHolder surfaceHolder) {
            surfaceHolder.setFixedSize(MediaPlayerService.this.uiHolder.surfaceView.getWidth(), MediaPlayerService.this.uiHolder.surfaceView.getHeight());
            MediaPlayerService.this.uiHolder.player.setDisplay(surfaceHolder);
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            MediaPlayerService.this.onStatusCallbackNext(MediaPlayerEnum.CallBackState.SURFACEVIEW_CHANGE, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            MediaPlayerService.this.isHolderCreate = false;
            MediaPlayerService.this.uiHolder.player.pause();
            MediaPlayerService.this.onStatusCallbackNext(MediaPlayerEnum.CallBackState.SURFACEVIEW_DESTROY, surfaceHolder);
        }
    }

    public void playMedia(MediaInfo mediaInfo) {
        playMedia(mediaInfo, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x005d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void playMedia(com.wanos.commonlibrary.bean.MediaInfo r19, boolean r20) {
        /*
            Method dump skipped, instruction units count: 262
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wanos.wanosplayermodule.MediaPlayerService.playMedia(com.wanos.commonlibrary.bean.MediaInfo, boolean):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void play(MediaInfo mediaInfo) {
        String path;
        long id;
        Log.i(TAG, "play()调用");
        this.avaCache = 100;
        if (mediaInfo.getMediaType() == MediaPlayerEnum.MediaType.Music) {
            path = mediaInfo.getMusicInfoBean().getMusicPath();
            id = mediaInfo.getMusicInfoBean().getMusicId();
            mediaInfo.getMusicInfoBean().setPageSize(100);
        } else if (mediaInfo.getMediaType() == MediaPlayerEnum.MediaType.Audiobook) {
            path = mediaInfo.getAudioBookInfoBean().getPath();
            id = mediaInfo.getAudioBookInfoBean().getId();
            mediaInfo.getAudioBookInfoBean().setPageSize(100);
        } else {
            path = "";
            id = 0;
        }
        setCurMediainfo(mediaInfo);
        this.playerErrorMediaPosition = 0;
        this.audioServiceDiedAndPauseFlag = false;
        playUrl(path, false, id, null);
        MediaSharedPreferUtils.putMediainfo(mediaInfo);
    }

    private void playNoSetMediainfo(MediaInfo mediaInfo) {
        String path;
        long id;
        Log.i(TAG, "playNoSetMediainfo: ");
        this.avaCache = 100;
        if (mediaInfo.getMediaType() == MediaPlayerEnum.MediaType.Music) {
            path = mediaInfo.getMusicInfoBean().getMusicPath();
            id = mediaInfo.getMusicInfoBean().getMusicId();
            mediaInfo.getMusicInfoBean().setPageSize(100);
        } else if (mediaInfo.getMediaType() == MediaPlayerEnum.MediaType.Audiobook) {
            path = mediaInfo.getAudioBookInfoBean().getPath();
            id = mediaInfo.getAudioBookInfoBean().getId();
            mediaInfo.getAudioBookInfoBean().setPageSize(100);
        } else {
            path = "";
            id = 0;
        }
        this.playerErrorMediaPosition = 0;
        this.audioServiceDiedAndPauseFlag = false;
        playUrl(path, false, id, null);
        MediaSharedPreferUtils.putMediainfo(mediaInfo);
    }

    private void playUrl(String str, boolean z, long j, SurfaceView surfaceView) {
        File videoFile;
        Log.i(TAG, "playUrl()调用");
        this.waitPositionBufferingUpdateTime = 0L;
        String str2 = str.split("\\?")[0];
        if (this.proxy == null && (videoFile = getVideoFile(this, z)) != null) {
            this.proxy = new HttpProxyCacheServer.Builder(this).maxCacheFilesCount(10).maxCacheSize(IjkMediaMeta.AV_CH_STEREO_LEFT).cacheDirectory(videoFile).build();
        }
        registerCacheListener(str2);
        this.proxy.setMediaInfo(getCurMediaInfo().getMediaType().ordinal(), j, (getCurMediaInfo() == null || getCurMediaInfo().getMusicInfoBean() == null) ? 0 : getCurMediaInfo().getMusicInfoBean().getContentType());
        final String proxyUrl = this.proxy.getProxyUrl(str2, true);
        if (this.proxy.isCached(str2)) {
            this.curMediaBufferingUpdatePercent = 100;
        } else {
            this.curMediaBufferingUpdatePercent = 0;
        }
        Log.i(TAG, "proxyUrl----" + proxyUrl);
        this.currentUrl = proxyUrl;
        if (!z) {
            beginPlayUrl(this, proxyUrl);
            return;
        }
        if (surfaceView == null) {
            if (this.isHolderCreate) {
                beginPlayUrl(this, proxyUrl);
                return;
            } else {
                setOnHolderCreateListener(new OnHolderCreateListener() { // from class: com.wanos.wanosplayermodule.MediaPlayerService$$ExternalSyntheticLambda10
                    @Override // com.wanos.wanosplayermodule.MediaPlayerService.OnHolderCreateListener
                    public final void onHolderCreate() {
                        this.f$0.m700lambda$playUrl$3$comwanoswanosplayermoduleMediaPlayerService(proxyUrl);
                    }
                });
                return;
            }
        }
        setSurfaceView(surfaceView);
    }

    /* JADX INFO: renamed from: lambda$playUrl$3$com-wanos-wanosplayermodule-MediaPlayerService, reason: not valid java name */
    /* synthetic */ void m700lambda$playUrl$3$comwanoswanosplayermoduleMediaPlayerService(String str) {
        beginPlayUrl(this, str);
    }

    private void beginPlayUrl(Context context, String str) {
        if (this.recordErrorMessage != null) {
            Log.i(TAG, "保存上一次的错误码 " + this.recordErrorMessage);
        }
        Log.i(TAG, "beginPlayUrl()调用");
        if (!this.isResetMeidaplayComplete) {
            this.refress_time_handler.removeCallbacks(this.prepareOutTimeRunnable);
            try {
                this.uiHolder.player.setDisplay(null);
                Log.i(TAG, "当前状态----" + this.preStatus);
                Log.i(TAG, "beginPlayUrl: preprareStatus = " + this.preprareStatus);
                if (this.preStatus != MediaPlayerEnum.CallBackState.PREPARING && this.preprareStatus == MediaPlayerEnum.PreprareStatus.PREPRAREED) {
                    stop();
                }
            } catch (Exception e) {
                onStatusCallbackNext(MediaPlayerEnum.CallBackState.ERROR, e.toString());
            }
            this.isResetMeidaplayComplete = true;
        }
        if (this.preprareStatus == MediaPlayerEnum.PreprareStatus.NO_PREPRARE && MediaSharedPreferUtils.getMediainfo() != null) {
            this.preprareStatus = MediaPlayerEnum.PreprareStatus.ONLY_PREPRAREING;
        } else {
            this.preprareStatus = MediaPlayerEnum.PreprareStatus.PREPRAREING;
        }
        onStatusCallbackNext(MediaPlayerEnum.CallBackState.PREPARING, new Object[0]);
        this.changeMediaDelayAlreadyWaited = 0L;
        this.refress_time_handler.postDelayed(this.dealPlayerDelayRunnable, 50L);
    }

    private void initPlayerListener() {
        this.uiHolder.player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.wanos.wanosplayermodule.MediaPlayerService$$ExternalSyntheticLambda4
            @Override // android.media.MediaPlayer.OnCompletionListener
            public final void onCompletion(MediaPlayer mediaPlayer) {
                this.f$0.m693x8a642d4b(mediaPlayer);
            }
        });
        this.uiHolder.player.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.wanos.wanosplayermodule.MediaPlayerService$$ExternalSyntheticLambda5
            @Override // android.media.MediaPlayer.OnErrorListener
            public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                return this.f$0.m694xc42ecf2a(mediaPlayer, i, i2);
            }
        });
        this.uiHolder.player.setOnInfoListener(new MediaPlayer.OnInfoListener() { // from class: com.wanos.wanosplayermodule.MediaPlayerService$$ExternalSyntheticLambda6
            @Override // android.media.MediaPlayer.OnInfoListener
            public final boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
                return this.f$0.m695xfdf97109(mediaPlayer, i, i2);
            }
        });
        this.uiHolder.player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.wanos.wanosplayermodule.MediaPlayerService$$ExternalSyntheticLambda7
            @Override // android.media.MediaPlayer.OnPreparedListener
            public final void onPrepared(MediaPlayer mediaPlayer) {
                this.f$0.m697x718eb4c7(mediaPlayer);
            }
        });
        this.uiHolder.player.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() { // from class: com.wanos.wanosplayermodule.MediaPlayerService$$ExternalSyntheticLambda8
            @Override // android.media.MediaPlayer.OnSeekCompleteListener
            public final void onSeekComplete(MediaPlayer mediaPlayer) {
                this.f$0.m698xab5956a6(mediaPlayer);
            }
        });
        this.uiHolder.player.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() { // from class: com.wanos.wanosplayermodule.MediaPlayerService$$ExternalSyntheticLambda9
            @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
            public final void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
                this.f$0.m692xddc1ff3c(mediaPlayer, i, i2);
            }
        });
        this.uiHolder.player.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() { // from class: com.wanos.wanosplayermodule.MediaPlayerService.4
            @Override // android.media.MediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
                MediaPlayerService.this.onStatusCallbackNext(MediaPlayerEnum.CallBackState.BUFFER_UPDATE, mediaPlayer, Integer.valueOf(i));
                MediaPlayerService.this.curMediaBufferingUpdatePercent = i;
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initPlayerListener$4$com-wanos-wanosplayermodule-MediaPlayerService, reason: not valid java name */
    /* synthetic */ void m693x8a642d4b(MediaPlayer mediaPlayer) {
        onStatusCallbackNext(MediaPlayerEnum.CallBackState.PROGRESS, 100, false);
        onStatusCallbackNext(MediaPlayerEnum.CallBackState.COMPLETE, mediaPlayer);
        autoPlayNext();
    }

    /* JADX INFO: renamed from: lambda$initPlayerListener$5$com-wanos-wanosplayermodule-MediaPlayerService, reason: not valid java name */
    /* synthetic */ boolean m694xc42ecf2a(MediaPlayer mediaPlayer, int i, int i2) {
        onStatusCallbackNext(MediaPlayerEnum.CallBackState.ERROR, "what:" + i + " extra:" + i2);
        return true;
    }

    /* JADX INFO: renamed from: lambda$initPlayerListener$6$com-wanos-wanosplayermodule-MediaPlayerService, reason: not valid java name */
    /* synthetic */ boolean m695xfdf97109(MediaPlayer mediaPlayer, int i, int i2) {
        onStatusCallbackNext(MediaPlayerEnum.CallBackState.INFO, mediaPlayer, Integer.valueOf(i), Integer.valueOf(i2));
        return false;
    }

    /* JADX INFO: renamed from: lambda$initPlayerListener$8$com-wanos-wanosplayermodule-MediaPlayerService, reason: not valid java name */
    /* synthetic */ void m697x718eb4c7(MediaPlayer mediaPlayer) {
        boolean z;
        boolean zIsVipMusic;
        boolean zIsFree;
        long previewStartTime;
        long j;
        boolean z2;
        Log.i(TAG, "player prepared!");
        this.waitPositionBufferingUpdateTime = 0L;
        this.playMediaPosition = 0;
        if (this.preprareStatus == MediaPlayerEnum.PreprareStatus.ONLY_PREPRAREING || this.audioServiceDiedAndPauseFlag) {
            Log.i(TAG, "initPlayerListener: (preprareStatus == MediaPlayerEnum.PreprareStatus.ONLY_PREPRAREING) = " + (this.preprareStatus == MediaPlayerEnum.PreprareStatus.ONLY_PREPRAREING));
            Log.i(TAG, "initPlayerListener: audioServiceDiedAndPauseFlag = " + this.audioServiceDiedAndPauseFlag);
            Log.i(TAG, "首次有持久化默认只准备不播放");
            this.preprareStatus = MediaPlayerEnum.PreprareStatus.PREPRAREED;
            z = false;
        } else {
            this.preprareStatus = MediaPlayerEnum.PreprareStatus.PREPRAREED;
            z = true;
        }
        onStatusCallbackNext(MediaPlayerEnum.CallBackState.PREPARE, new Object[0]);
        MediaInfo curMediaInfo = getCurMediaInfo();
        if (getCurMediaInfo() != null) {
            MediaPlayerManager.getInstance().updatePlayHistory(curMediaInfo);
            if (curMediaInfo.getMusicInfoBean() != null) {
                updateLrc(curMediaInfo.getMusicInfoBean().getLrcPath(), MediaPlayerEnum.AppType.Sdk);
            }
        }
        boolean zIsVipAndUnexpired = UserInfoUtil.isVipAndUnexpired();
        this.mPlayPermission.musicPermissionVerify(curMediaInfo, new AnonymousClass3());
        if (curMediaInfo == null) {
            zIsVipMusic = false;
            zIsFree = false;
            previewStartTime = 0;
            j = 0;
        } else {
            if (curMediaInfo.getMediaType() == MediaPlayerEnum.MediaType.Music) {
                MediaStatistic.getInstance().saveRecordMediaMusic(getCurMediaInfo().getMusicInfoBean().getMusicId(), "", getCurMediaInfo().getSource());
                MusicInfoBean musicInfoBean = curMediaInfo.getMusicInfoBean();
                if (musicInfoBean != null) {
                    zIsVipMusic = musicInfoBean.isVipMusic();
                    zIsFree = musicInfoBean.isFree();
                    previewStartTime = musicInfoBean.getPreviewStartTime() * 1000;
                    long previewEndTime = musicInfoBean.getPreviewEndTime() * 1000;
                    Log.d(TAG, "isVipMusic " + zIsVipMusic);
                    j = previewEndTime;
                }
            } else {
                if (curMediaInfo.getMediaType() == MediaPlayerEnum.MediaType.Audiobook) {
                    MediaStatistic.getInstance().saveRecordAudioBookChapter(getCurMediaInfo().getAudioBookInfoBean().getId(), "", getCurMediaInfo().getSource());
                    AudioBookMineChapterItemBean audioBookInfoBean = curMediaInfo.getAudioBookInfoBean();
                    if (audioBookInfoBean != null) {
                        zIsVipMusic = audioBookInfoBean.getIsVip() == 1;
                    } else {
                        zIsVipMusic = false;
                    }
                    setPlaySpeed(MediaSharedPreferUtils.getDoubleSpeed());
                    zIsFree = false;
                }
                previewStartTime = 0;
                j = 0;
            }
            zIsVipMusic = false;
            zIsFree = false;
            previewStartTime = 0;
            j = 0;
        }
        int i = this.netErrorMediaPosition;
        if (i != 0) {
            seekTo(i);
        }
        int i2 = this.playerErrorMediaPosition;
        if (i2 != 0) {
            if (i2 > 1000) {
                this.playerErrorMediaPosition = i2 - 1000;
            }
            seekTo(this.playerErrorMediaPosition);
            int duration = getDuration();
            z2 = z;
            onStatusCallbackNext(MediaPlayerEnum.CallBackState.PROGRESS, Integer.valueOf(Math.round(((this.playerErrorMediaPosition * 1.0f) / duration) * 100.0f)), true);
            onStatusCallbackNext(MediaPlayerEnum.CallBackState.POSITION, Integer.valueOf(this.playerErrorMediaPosition), Integer.valueOf(duration), true);
            Log.i(TAG, "seek playerErrorMediaPosition " + this.playerErrorMediaPosition);
            this.playerErrorMediaPosition = 0;
        } else {
            z2 = z;
        }
        if (this.audioServiceDiedAndPauseFlag) {
            this.audioServiceDiedAndPauseFlag = false;
            Log.i(TAG, "mediaserver died 后播放器暂停");
            this.uiHolder.player.start();
            this.uiHolder.player.pause();
            onStatusCallbackNext(MediaPlayerEnum.CallBackState.PAUSE, new Object[0]);
        }
        ProgressInfo progressInfo = this.mProgressInfo;
        if (progressInfo != null && progressInfo.getMediaType() == curMediaInfo.getMediaType()) {
            long progress = this.mProgressInfo.getProgress();
            if (this.mProgressInfo.getMediaType() == MediaPlayerEnum.MediaType.Music) {
                if (this.mProgressInfo.getMediaId() == curMediaInfo.getMusicInfoBean().getMusicId() && progress > 0) {
                    if (zIsVipAndUnexpired || zIsFree || !zIsVipMusic) {
                        seekTo(progress);
                    } else if (progress < j && progress > previewStartTime) {
                        seekTo(progress);
                    }
                }
            } else if (this.mProgressInfo.getMediaType() == MediaPlayerEnum.MediaType.Audiobook) {
                if (this.mProgressInfo.getMediaId() != curMediaInfo.getAudioBookInfoBean().getId()) {
                    long progress2 = curMediaInfo.getAudioBookInfoBean().getProgress();
                    if (progress2 > 0 && (zIsVipAndUnexpired || !zIsVipMusic)) {
                        seekTo(progress2);
                    }
                } else if (progress > 0) {
                    long duration2 = curMediaInfo.getAudioBookInfoBean().getDuration();
                    if ((zIsVipAndUnexpired || !zIsVipMusic) && progress < (duration2 * 1000) - 100) {
                        seekTo(progress);
                    }
                }
            }
            this.mProgressInfo = null;
        } else if (curMediaInfo.getMediaType() == MediaPlayerEnum.MediaType.Audiobook) {
            long progress3 = curMediaInfo.getAudioBookInfoBean().getProgress();
            if (progress3 > 0 && (zIsVipAndUnexpired || !zIsVipMusic)) {
                seekTo(progress3);
            }
        }
        try {
            if (this.uiHolder.surfaceView != null) {
                this.uiHolder.surfaceView.post(new Runnable() { // from class: com.wanos.wanosplayermodule.MediaPlayerService$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.m696x37c412e8();
                    }
                });
            }
            if (z2) {
                Log.i(TAG, "initPlayerListener: isPrepareAndStart = true");
                start();
            }
            this.refress_time_handler.postDelayed(this.refress_time_Thread, this.delaySecondTime);
        } catch (Exception e) {
            onStatusCallbackNext(MediaPlayerEnum.CallBackState.EXCEPTION, e.toString());
        }
        if (this.uiHolder.surfaceHolder != null) {
            String str = ("holder - height：" + this.uiHolder.surfaceHolder.getSurfaceFrame().height()) + " width：" + this.uiHolder.surfaceHolder.getSurfaceFrame().width();
        }
    }

    /* JADX INFO: renamed from: com.wanos.wanosplayermodule.MediaPlayerService$3, reason: invalid class name */
    class AnonymousClass3 implements PlayPermission.MusicPreviewCallback {
        AnonymousClass3() {
        }

        @Override // com.wanos.wanosplayermodule.playPermission.PlayPermission.MusicPreviewCallback
        public void PreviewTimeDone() {
            Log.d(MediaPlayerService.TAG, "试听结束");
            MediaPlayerService.this.pause(new VolumeGradientCompleteListener() { // from class: com.wanos.wanosplayermodule.MediaPlayerService$3$$ExternalSyntheticLambda0
                @Override // com.wanos.wanosplayermodule.VolumeGradientCompleteListener
                public final void onVolumeFadeComplete() {
                    this.f$0.m703x8b8c8aa6();
                }
            });
        }

        /* JADX INFO: renamed from: lambda$PreviewTimeDone$0$com-wanos-wanosplayermodule-MediaPlayerService$3, reason: not valid java name */
        /* synthetic */ void m703x8b8c8aa6() {
            MediaPlayerService.this.autoPlayNext();
        }

        @Override // com.wanos.wanosplayermodule.playPermission.PlayPermission.MusicPreviewCallback
        public void seek(long j) {
            Log.d(MediaPlayerService.TAG, "seek to " + j);
            MediaPlayerService.this.seekTo(j);
        }
    }

    /* JADX INFO: renamed from: lambda$initPlayerListener$7$com-wanos-wanosplayermodule-MediaPlayerService, reason: not valid java name */
    /* synthetic */ void m696x37c412e8() {
        this.uiHolder.surfaceHolder.setFixedSize(this.uiHolder.surfaceView.getWidth(), this.uiHolder.surfaceView.getHeight());
        this.uiHolder.player.setDisplay(this.uiHolder.surfaceHolder);
    }

    /* JADX INFO: renamed from: lambda$initPlayerListener$9$com-wanos-wanosplayermodule-MediaPlayerService, reason: not valid java name */
    /* synthetic */ void m698xab5956a6(MediaPlayer mediaPlayer) {
        onStatusCallbackNext(MediaPlayerEnum.CallBackState.SEEK_COMPLETE, mediaPlayer);
    }

    /* JADX INFO: renamed from: lambda$initPlayerListener$10$com-wanos-wanosplayermodule-MediaPlayerService, reason: not valid java name */
    /* synthetic */ void m692xddc1ff3c(MediaPlayer mediaPlayer, int i, int i2) {
        onStatusCallbackNext(MediaPlayerEnum.CallBackState.VIDEO_SIZE_CHANGE, Integer.valueOf(i), Integer.valueOf(i2));
    }

    public void registerCacheListener(String str) {
        HttpProxyCacheServer httpProxyCacheServer = this.proxy;
        if (httpProxyCacheServer != null) {
            CacheListener cacheListener = this.cacheListener;
            if (cacheListener != null) {
                httpProxyCacheServer.unregisterCacheListener(cacheListener);
            }
            this.proxy.registerCacheListener(this.cacheListener, str);
        }
    }

    private boolean checkAvalable(String str) {
        boolean z;
        String[] strArr = this.ext;
        int length = strArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = false;
                break;
            }
            if (str.endsWith(strArr[i])) {
                z = true;
                break;
            }
            i++;
        }
        if (z) {
            return true;
        }
        onStatusCallbackNext(MediaPlayerEnum.CallBackState.FORMATE_NOT_SURPORT, this.uiHolder.player);
        return false;
    }

    public MediaPlayerService addOnStatusCallbackListener(OnStatusCallbackListener onStatusCallbackListener) {
        if (onStatusCallbackListener != null) {
            if (this.onStatusCallbackListenerList == null) {
                this.onStatusCallbackListenerList = new ArrayList();
            }
            this.onStatusCallbackListenerList.add(onStatusCallbackListener);
        }
        return this;
    }

    public MediaPlayerService addOnMediaInfoCallbackListener(OnMediaInfoCallbackListener onMediaInfoCallbackListener) {
        if (onMediaInfoCallbackListener != null) {
            if (this.onMediaInfoCallbackListenerList == null) {
                this.onMediaInfoCallbackListenerList = new ArrayList();
            }
            this.onMediaInfoCallbackListenerList.add(onMediaInfoCallbackListener);
        }
        return this;
    }

    public MediaPlayerService addOnMediaInfoCallbackAppListener(OnMediaInfoCallbackAppListener onMediaInfoCallbackAppListener) {
        if (onMediaInfoCallbackAppListener != null) {
            if (this.onMediaInfoCallbackAppListenerList == null) {
                this.onMediaInfoCallbackAppListenerList = new ArrayList();
            }
            this.onMediaInfoCallbackAppListenerList.add(onMediaInfoCallbackAppListener);
        }
        return this;
    }

    public MediaPlayerService addOnLoginStatusCallbackListener(OnLoginStatusCallbackListener onLoginStatusCallbackListener) {
        if (onLoginStatusCallbackListener != null) {
            if (this.onLoginStatusCallbackListenerList == null) {
                this.onLoginStatusCallbackListenerList = new ArrayList();
            }
            this.onLoginStatusCallbackListenerList.add(onLoginStatusCallbackListener);
        }
        return this;
    }

    public MediaPlayerService addOnAudioFocusChangeCallbackListener(OnAudioFocusChangeCallbackListener onAudioFocusChangeCallbackListener) {
        if (onAudioFocusChangeCallbackListener != null) {
            if (this.onAudioFocusChangeCallbackListenerList == null) {
                this.onAudioFocusChangeCallbackListenerList = new ArrayList();
            }
            this.onAudioFocusChangeCallbackListenerList.add(onAudioFocusChangeCallbackListener);
        }
        return this;
    }

    public MediaPlayerService addOnCacheProgressCallbackListener(OnCacheProgressCallbackListener onCacheProgressCallbackListener) {
        if (onCacheProgressCallbackListener != null) {
            if (this.onCacheProgressCallbackListenerList == null) {
                this.onCacheProgressCallbackListenerList = new ArrayList();
            }
            this.onCacheProgressCallbackListenerList.add(onCacheProgressCallbackListener);
        }
        return this;
    }

    public MediaPlayerService addOnUpdateSDKLrcCallbackListener(OnUpdateSDKLrcListener onUpdateSDKLrcListener) {
        if (onUpdateSDKLrcListener != null) {
            if (this.onUpdateSDKLrcListenerList == null) {
                this.onUpdateSDKLrcListenerList = new ArrayList();
            }
            this.onUpdateSDKLrcListenerList.add(onUpdateSDKLrcListener);
        }
        return this;
    }

    public MediaPlayerService addOnUpdateAppLrcCallbackListener(OnUpdateAppLrcListener onUpdateAppLrcListener) {
        if (onUpdateAppLrcListener != null) {
            if (this.onUpdateAppLrcListenerList == null) {
                this.onUpdateAppLrcListenerList = new ArrayList();
            }
            this.onUpdateAppLrcListenerList.add(onUpdateAppLrcListener);
        }
        return this;
    }

    public MediaPlayerService addOnMediaInfoListListener(OnMediaInfoListListener onMediaInfoListListener) {
        if (onMediaInfoListListener != null) {
            if (this.onMediaInfoListListenerList == null) {
                this.onMediaInfoListListenerList = new ArrayList();
            }
            this.onMediaInfoListListenerList.add(onMediaInfoListListener);
        }
        return this;
    }

    public MediaPlayerService removeOnMediaInfoListListener(OnMediaInfoListListener onMediaInfoListListener) {
        List<OnMediaInfoListListener> list = this.onMediaInfoListListenerList;
        if (list != null && onMediaInfoListListener != null) {
            list.remove(onMediaInfoListListener);
        }
        return this;
    }

    public MediaPlayerService removeOnStatusCallbackListener(OnStatusCallbackListener onStatusCallbackListener) {
        List<OnStatusCallbackListener> list = this.onStatusCallbackListenerList;
        if (list != null && onStatusCallbackListener != null) {
            list.remove(onStatusCallbackListener);
        }
        return this;
    }

    public MediaPlayerService removeOnMediaInfoCallbackListener(OnMediaInfoCallbackListener onMediaInfoCallbackListener) {
        List<OnMediaInfoCallbackListener> list = this.onMediaInfoCallbackListenerList;
        if (list != null && onMediaInfoCallbackListener != null) {
            list.remove(onMediaInfoCallbackListener);
        }
        return this;
    }

    public MediaPlayerService removeOnMediaInfoCallbackAppListener(OnMediaInfoCallbackAppListener onMediaInfoCallbackAppListener) {
        List<OnMediaInfoCallbackAppListener> list = this.onMediaInfoCallbackAppListenerList;
        if (list != null && onMediaInfoCallbackAppListener != null) {
            list.remove(onMediaInfoCallbackAppListener);
        }
        return this;
    }

    public MediaPlayerService removeOnLoginStatusCallbackListener(OnLoginStatusCallbackListener onLoginStatusCallbackListener) {
        List<OnLoginStatusCallbackListener> list = this.onLoginStatusCallbackListenerList;
        if (list != null && onLoginStatusCallbackListener != null) {
            list.remove(onLoginStatusCallbackListener);
        }
        return this;
    }

    public MediaPlayerService removeOnAudioFocusChangeCallbackListener(OnAudioFocusChangeCallbackListener onAudioFocusChangeCallbackListener) {
        List<OnAudioFocusChangeCallbackListener> list = this.onAudioFocusChangeCallbackListenerList;
        if (list != null && onAudioFocusChangeCallbackListener != null) {
            list.remove(onAudioFocusChangeCallbackListener);
        }
        return this;
    }

    public MediaPlayerService removeOnCacheProgressCallbackListener(OnCacheProgressCallbackListener onCacheProgressCallbackListener) {
        List<OnCacheProgressCallbackListener> list = this.onCacheProgressCallbackListenerList;
        if (list != null && onCacheProgressCallbackListener != null) {
            list.remove(onCacheProgressCallbackListener);
        }
        return this;
    }

    public MediaPlayerService addOnLoadMediaInfoPlayListCallbackListener(OnLoadMediaInfoPlayListCallbackListener onLoadMediaInfoPlayListCallbackListener) {
        if (onLoadMediaInfoPlayListCallbackListener != null) {
            if (this.onLoadMediaInfoPlayListCallbackListenerList == null) {
                this.onLoadMediaInfoPlayListCallbackListenerList = new ArrayList();
            }
            this.onLoadMediaInfoPlayListCallbackListenerList.add(onLoadMediaInfoPlayListCallbackListener);
        }
        return this;
    }

    public MediaPlayerService removeOnLoadMediaInfoPlayListCallbackListener(OnLoadMediaInfoPlayListCallbackListener onLoadMediaInfoPlayListCallbackListener) {
        List<OnLoadMediaInfoPlayListCallbackListener> list = this.onLoadMediaInfoPlayListCallbackListenerList;
        if (list != null && onLoadMediaInfoPlayListCallbackListener != null) {
            list.remove(onLoadMediaInfoPlayListCallbackListener);
        }
        return this;
    }

    public MediaPlayerService removeOnUpdateSdkLrcCallbackListener(OnUpdateSDKLrcListener onUpdateSDKLrcListener) {
        List<OnUpdateSDKLrcListener> list = this.onUpdateSDKLrcListenerList;
        if (list != null && onUpdateSDKLrcListener != null) {
            list.remove(onUpdateSDKLrcListener);
        }
        return this;
    }

    public MediaPlayerService removeOnUpdateAppLrcCallbackListener(OnUpdateAppLrcListener onUpdateAppLrcListener) {
        List<OnUpdateAppLrcListener> list = this.onUpdateAppLrcListenerList;
        if (list != null && onUpdateAppLrcListener != null) {
            list.remove(onUpdateAppLrcListener);
        }
        return this;
    }

    public MediaPlayerService addOnPlayTimeOutListener(OnPlayTimeOutListener onPlayTimeOutListener) {
        if (onPlayTimeOutListener != null) {
            if (this.onPlayTimeOutListenerList == null) {
                this.onPlayTimeOutListenerList = new ArrayList();
            }
            this.onPlayTimeOutListenerList.add(onPlayTimeOutListener);
        }
        return this;
    }

    public MediaPlayerService removeOnPlayTimeOutListener(OnPlayTimeOutListener onPlayTimeOutListener) {
        List<OnPlayTimeOutListener> list = this.onPlayTimeOutListenerList;
        if (list != null && onPlayTimeOutListener != null) {
            list.remove(onPlayTimeOutListener);
        }
        return this;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x00a4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void rateLimitedInvoke(boolean r9) {
        /*
            Method dump skipped, instruction units count: 213
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wanos.wanosplayermodule.MediaPlayerService.rateLimitedInvoke(boolean):void");
    }

    public MediaInfoBean getCurrentMediaInfo() {
        ServiceLoader serviceLoaderLoad = ServiceLoader.load(MediaPlayer2MediaCenterIInterface.class);
        if (serviceLoaderLoad.iterator().hasNext()) {
            return ((MediaPlayer2MediaCenterIInterface) serviceLoaderLoad.iterator().next()).getMediaCenterMediaInfoBean();
        }
        return null;
    }

    public MediaPlayerEnum.CallBackState getPreStatus() {
        return this.preStatus;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onStatusCallbackNext(final MediaPlayerEnum.CallBackState callBackState, final Object... objArr) {
        Object obj;
        Log.i(TAG, "==status:" + callBackState.name());
        if (MediaPlayerEnum.CallBackState.POSITION != callBackState && MediaPlayerEnum.CallBackState.PROGRESS != callBackState && MediaPlayerEnum.CallBackState.BUFFER_UPDATE != callBackState) {
            Log.i(TAG, "status:" + callBackState.name());
        }
        if (callBackState != MediaPlayerEnum.CallBackState.ERROR) {
            this.preStatus = callBackState;
            if (callBackState != MediaPlayerEnum.CallBackState.BUFFER_UPDATE && callBackState != MediaPlayerEnum.CallBackState.SEEK_COMPLETE && callBackState != MediaPlayerEnum.CallBackState.PREPARING && callBackState != MediaPlayerEnum.CallBackState.PREPARE) {
                if (callBackState == MediaPlayerEnum.CallBackState.PAUSE) {
                    this.pauseStatus = MediaPlayerEnum.PauseStatus.PAUSED;
                } else {
                    Log.i(TAG, "当前播放状态(设置暂停状态为NO_PAUSE)----" + callBackState);
                    this.pauseStatus = MediaPlayerEnum.PauseStatus.NO_PAUSE;
                }
            }
        }
        if (callBackState == MediaPlayerEnum.CallBackState.STARTED) {
            MediaSharedPreferUtils.putIsPlaying(true);
        }
        if (callBackState == MediaPlayerEnum.CallBackState.PAUSE || callBackState == MediaPlayerEnum.CallBackState.ERROR || callBackState == MediaPlayerEnum.CallBackState.COMPLETE) {
            if (callBackState == MediaPlayerEnum.CallBackState.ERROR) {
                Log.i(TAG, "onStatusCallbackNext: status = ERROR了");
                String string = objArr[0].toString();
                if (string != null && string.length() > 0) {
                    if (string.contains("-38")) {
                        if (this.startTimeStamp == 0 || System.currentTimeMillis() - this.startTimeStamp >= 500) {
                            return;
                        }
                        playNoSetMediainfo(getCurMediaInfo());
                        Log.i(TAG, "onStatusCallbackNext: -38 error, reset,start方法执行之后500毫秒之内");
                        return;
                    }
                    this.recordErrorMessage = string;
                }
            }
            if (callBackState == MediaPlayerEnum.CallBackState.PAUSE && objArr.length >= 2) {
                boolean zBooleanValue = ((Boolean) objArr[1]).booleanValue();
                Log.i(TAG, "onStatusCallbackNext: isLoseAudioFocusTransient = " + zBooleanValue);
                if (zBooleanValue) {
                    Log.i(TAG, "onStatusCallbackNext: 短暂失去音频焦点而引起的播放暂停,不保存播放状态为暂停");
                } else {
                    Log.i(TAG, "onStatusCallbackNext: 永久失去音频焦点而引起的播放暂停，保存播放状态为暂停");
                    MediaSharedPreferUtils.putIsPlaying(false);
                }
            } else {
                Log.i(TAG, "onStatusCallbackNext: 其他，保存本地播放状态为暂停");
                MediaSharedPreferUtils.putIsPlaying(false);
            }
        }
        if ((MediaPlayerEnum.CallBackState.ERROR == callBackState || MediaPlayerEnum.CallBackState.EXCEPTION == callBackState) && (obj = objArr[0]) != null && (obj instanceof String)) {
            Log.e(TAG, "error info:" + ((String) objArr[0]));
            String string2 = objArr[0].toString();
            if (string2 != null && string2.length() > 0) {
                boolean zContains = string2.contains("-1004");
                boolean zContains2 = string2.contains("-19");
                boolean zContains3 = string2.contains("what:100");
                boolean zContains4 = string2.contains("-2147483648");
                if (zContains3) {
                    this.audioServerErrorTime = System.currentTimeMillis();
                    Log.i(TAG, "onStatusCallbackNext: audioServiceDied, audioServerErrorTime = " + this.audioServerErrorTime);
                }
                if (zContains2) {
                    this.audioServerErrorTime = System.currentTimeMillis();
                    Log.i(TAG, "onStatusCallbackNext: audioServiceError,audioServerErrorTime = " + this.audioServerErrorTime);
                }
                Log.e(TAG, "audioServiceError is " + zContains2);
                Log.e(TAG, "audioServiceDied is " + zContains3);
                if (zContains || zContains2 || zContains4) {
                    if (zContains) {
                        rateLimitedInvoke(true);
                    }
                    if (zContains2 || zContains4) {
                        resetPrepareStatus(zContains3 ? "-19" : "-2147483648");
                        rateLimitedInvoke(false);
                    }
                }
                if (zContains3) {
                    resetPrepareStatus("-100");
                    if (checkPauseStatusAndPreStatus()) {
                        playNoSetMediainfo(getCurMediaInfo());
                        this.playerErrorMediaPosition = this.playMediaPosition;
                    } else {
                        Log.i(TAG, "reset");
                        Log.i(TAG, "之前是否在播放: " + MediaSharedPreferUtils.getIsPlaying());
                        MediaInfo mediainfo = MediaSharedPreferUtils.getMediainfo();
                        this.mProgressInfo = MediaSharedPreferUtils.getProgress();
                        if (mediainfo != null) {
                            playNoSetMediainfo(mediainfo);
                            this.audioServiceDiedAndPauseFlag = true;
                            Log.i(TAG, "onStatusCallbackNext: audioServiceDiedAndPauseFlag = true;");
                            this.playerErrorMediaPosition = this.playMediaPosition;
                        }
                    }
                }
            }
        }
        ThreadUtils.runOnUiThread(new Runnable() { // from class: com.wanos.wanosplayermodule.MediaPlayerService.10
            @Override // java.lang.Runnable
            public void run() {
                if (MediaPlayerService.this.onStatusCallbackListenerList == null || MediaPlayerService.this.onStatusCallbackListenerList.size() <= 0) {
                    return;
                }
                Iterator it = MediaPlayerService.this.onStatusCallbackListenerList.iterator();
                while (it.hasNext()) {
                    ((OnStatusCallbackListener) it.next()).onStatusonStatusCallbackNext(callBackState, objArr);
                }
            }
        });
    }

    private boolean checkPauseStatusAndPreStatus() {
        Log.i(TAG, "checkPauseStatusAndPreStatus: pauseStatus = " + this.pauseStatus + " , preStatus = " + this.preStatus);
        return this.pauseStatus == MediaPlayerEnum.PauseStatus.NO_PAUSE && (this.preStatus == MediaPlayerEnum.CallBackState.STARTED || this.preStatus == MediaPlayerEnum.CallBackState.PREPARING || this.preStatus == MediaPlayerEnum.CallBackState.PROGRESS || this.preStatus == MediaPlayerEnum.CallBackState.POSITION);
    }

    private void resetPrepareStatus(String str) {
        Log.i(TAG, "播放器死亡前播放状态（" + str + ")----" + this.preStatus);
        Log.i(TAG, "播放器死亡前暂停状态（" + str + ")----" + this.pauseStatus);
        if (this.pauseStatus == MediaPlayerEnum.PauseStatus.PAUSED) {
            this.preprareStatus = MediaPlayerEnum.PreprareStatus.NO_PREPRARE;
            Log.i(TAG, "重置准备状态----" + this.preprareStatus);
        }
    }

    private void onMediaInfoCallbackNext(final MediaPlayerEnum.MediaInfoCallbackType mediaInfoCallbackType, final MediaInfo mediaInfo) {
        ThreadUtils.runOnUiThread(new Runnable() { // from class: com.wanos.wanosplayermodule.MediaPlayerService.11
            @Override // java.lang.Runnable
            public void run() {
                if (MediaPlayerService.this.onMediaInfoCallbackListenerList == null || MediaPlayerService.this.onMediaInfoCallbackListenerList.size() <= 0) {
                    return;
                }
                Iterator it = MediaPlayerService.this.onMediaInfoCallbackListenerList.iterator();
                while (it.hasNext()) {
                    ((OnMediaInfoCallbackListener) it.next()).onMediaInfoCallbackNext(mediaInfoCallbackType, mediaInfo);
                }
            }
        });
    }

    private void onMediaInfoCallbackAppNext(final MediaPlayerEnum.MediaInfoCallbackType mediaInfoCallbackType, final MediaInfo mediaInfo) {
        ThreadUtils.runOnUiThread(new Runnable() { // from class: com.wanos.wanosplayermodule.MediaPlayerService.12
            @Override // java.lang.Runnable
            public void run() {
                if (MediaPlayerService.this.onMediaInfoCallbackAppListenerList == null || MediaPlayerService.this.onMediaInfoCallbackAppListenerList.size() <= 0) {
                    return;
                }
                Iterator it = MediaPlayerService.this.onMediaInfoCallbackAppListenerList.iterator();
                while (it.hasNext()) {
                    ((OnMediaInfoCallbackAppListener) it.next()).onMediaInfoCallbackAppNext(mediaInfoCallbackType, mediaInfo);
                }
            }
        });
    }

    public void onLoginStatusCallback() {
        List<OnLoginStatusCallbackListener> list = this.onLoginStatusCallbackListenerList;
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<OnLoginStatusCallbackListener> it = this.onLoginStatusCallbackListenerList.iterator();
        while (it.hasNext()) {
            it.next().onLoginStatusCallback();
        }
    }

    public void onVipStatusCallback() {
        List<OnLoginStatusCallbackListener> list = this.onLoginStatusCallbackListenerList;
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<OnLoginStatusCallbackListener> it = this.onLoginStatusCallbackListenerList.iterator();
        while (it.hasNext()) {
            it.next().onVipStatusCallback();
        }
    }

    private void onAudioFocusChangeCallback() {
        List<OnAudioFocusChangeCallbackListener> list = this.onAudioFocusChangeCallbackListenerList;
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<OnAudioFocusChangeCallbackListener> it = this.onAudioFocusChangeCallbackListenerList.iterator();
        while (it.hasNext()) {
            it.next().onAudioFocusChangeCallback();
        }
    }

    private long getBootCompleteTime() {
        ServiceLoader serviceLoaderLoad = ServiceLoader.load(MediaPlayer2AppInterface.class);
        if (serviceLoaderLoad.iterator().hasNext()) {
            return ((MediaPlayer2AppInterface) serviceLoaderLoad.iterator().next()).getBootCompletedTime();
        }
        return 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onUpdateSDKLrc(MediaPlayerEnum.UpdateLrcCallbackType updateLrcCallbackType, String str) {
        List<OnUpdateSDKLrcListener> list = this.onUpdateSDKLrcListenerList;
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<OnUpdateSDKLrcListener> it = this.onUpdateSDKLrcListenerList.iterator();
        while (it.hasNext()) {
            it.next().onUpdateSDKLrc(updateLrcCallbackType, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onUpdateAppLrc(MediaPlayerEnum.UpdateLrcCallbackType updateLrcCallbackType, List<LrcRow> list) {
        List<OnUpdateAppLrcListener> list2 = this.onUpdateAppLrcListenerList;
        if (list2 == null || list2.size() <= 0) {
            return;
        }
        Iterator<OnUpdateAppLrcListener> it = this.onUpdateAppLrcListenerList.iterator();
        while (it.hasNext()) {
            it.next().onUpdateAppLrc(updateLrcCallbackType, list);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onCacheProgressCallNext(int i) {
        List<OnCacheProgressCallbackListener> list = this.onCacheProgressCallbackListenerList;
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<OnCacheProgressCallbackListener> it = this.onCacheProgressCallbackListenerList.iterator();
        while (it.hasNext()) {
            it.next().onCacheProgressCallbackNext(i);
        }
    }

    private void onStartLoadMediaInfoPlayListCallback() {
        List<OnLoadMediaInfoPlayListCallbackListener> list = this.onLoadMediaInfoPlayListCallbackListenerList;
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<OnLoadMediaInfoPlayListCallbackListener> it = this.onLoadMediaInfoPlayListCallbackListenerList.iterator();
        while (it.hasNext()) {
            it.next().onStartLoadMediaInfoPlayListCallback();
        }
    }

    private void onSuccessfulLoadMediaInfoPlayListCallback() {
        List<OnLoadMediaInfoPlayListCallbackListener> list = this.onLoadMediaInfoPlayListCallbackListenerList;
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<OnLoadMediaInfoPlayListCallbackListener> it = this.onLoadMediaInfoPlayListCallbackListenerList.iterator();
        while (it.hasNext()) {
            it.next().onSuccessfulLoadMediaInfoPlayListCallback();
        }
    }

    private void onFailLoadMediaInfoPlayListCallback() {
        List<OnLoadMediaInfoPlayListCallbackListener> list = this.onLoadMediaInfoPlayListCallbackListenerList;
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<OnLoadMediaInfoPlayListCallbackListener> it = this.onLoadMediaInfoPlayListCallbackListenerList.iterator();
        while (it.hasNext()) {
            it.next().onFailLoadMediaInfoPlayListCallback();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onPlayTimeOutListenerList() {
        List<OnPlayTimeOutListener> list = this.onPlayTimeOutListenerList;
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<OnPlayTimeOutListener> it = this.onPlayTimeOutListenerList.iterator();
        while (it.hasNext()) {
            it.next().onPlayTimeOut();
        }
    }

    @Override // com.wanos.wanosplayermodule.MediaPlayerManager.RequestCallbackListener
    public void onLoadListDealStarted() {
        onStartLoadMediaInfoPlayListCallback();
        this.playListLoadStatus = MediaPlayerEnum.PlayListLoadStatus.START_LOAD;
    }

    @Override // com.wanos.wanosplayermodule.MediaPlayerManager.RequestCallbackListener
    public void onLoadListDealComplete(int i) {
        listDataLoadComplete(i);
        onSuccessfulLoadMediaInfoPlayListCallback();
        onMediaInfoListListenerListChanged();
        this.playListLoadStatus = MediaPlayerEnum.PlayListLoadStatus.COMPLETE_LOAD;
    }

    @Override // com.wanos.wanosplayermodule.MediaPlayerManager.RequestCallbackListener
    public void onLoadListFailed() {
        onFailLoadMediaInfoPlayListCallback();
        this.playListLoadStatus = MediaPlayerEnum.PlayListLoadStatus.FAIL_LOAD;
    }

    @Override // com.wanos.wanosplayermodule.MediaPlayerManager.RequestCallbackListener
    public void onUpdateListCollect() {
        onSuccessfulLoadMediaInfoPlayListCallback();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0022  */
    @Override // com.wanos.wanosplayermodule.MediaPlayerManager.RequestCallbackListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void collectRequestComplete(boolean r5, com.wanos.commonlibrary.bean.MediaInfo r6) {
        /*
            r4 = this;
            if (r6 == 0) goto L2e
            com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum$MediaType r0 = r6.getMediaType()
            com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum$MediaType r1 = com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum.MediaType.Music
            r2 = -1
            if (r0 != r1) goto L17
            com.wanos.commonlibrary.bean.MusicInfoBean r0 = r6.getMusicInfoBean()
            if (r0 == 0) goto L22
            long r0 = r0.getMusicId()
            goto L23
        L17:
            com.wanos.commonlibrary.bean.AudioBookMineChapterItemBean r0 = r6.getAudioBookInfoBean()
            if (r0 == 0) goto L22
            long r0 = r0.getId()
            goto L23
        L22:
            r0 = r2
        L23:
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 == 0) goto L2e
            com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum$MediaType r6 = r6.getMediaType()
            r4.onCollect(r6, r0, r5)
        L2e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wanos.wanosplayermodule.MediaPlayerService.collectRequestComplete(boolean, com.wanos.commonlibrary.bean.MediaInfo):void");
    }

    public void setExtendOperaData(ExtendOperaData extendOperaData) {
        MediaPlayerManager.getInstance().setExtendOperaData(extendOperaData);
    }

    private void setOnHolderCreateListener(OnHolderCreateListener onHolderCreateListener) {
        this.onHolderCreateListener = onHolderCreateListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onHolderCreateNext() {
        OnHolderCreateListener onHolderCreateListener = this.onHolderCreateListener;
        if (onHolderCreateListener != null) {
            onHolderCreateListener.onHolderCreate();
        }
    }

    public class LocalBinder extends Binder {
        public LocalBinder() {
        }

        public MediaPlayerService getMediaPlayerService() {
            return MediaPlayerService.this;
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return new LocalBinder();
    }

    @Override // android.app.Service
    public void onCreate() {
        MediaPlayerEnum.PlayMode audioBookMode;
        super.onCreate();
        Log.i(TAG, "播放服务启动,准备播放音乐");
        EventBus.getDefault().register(this);
        Holder holder = new Holder();
        this.uiHolder = holder;
        holder.player = new MediaPlayer();
        this.mPlayPermission = new PlayPermission(this);
        this.mAudioFocusManager = new AudioFocusManager(this);
        initPlayerListener();
        NetWorkStatus.setListener(new NetWorkChangeLis() { // from class: com.wanos.wanosplayermodule.MediaPlayerService.14
            @Override // com.wanos.wanosplayermodule.playPermission.NetWorkChangeLis
            public void onNetError() {
                MediaPlayerService.this.refress_time_handler.removeCallbacks(MediaPlayerService.this.netResumeRunnable);
                MediaPlayerService.this.refress_time_handler.post(MediaPlayerService.this.netResumeRunnable);
            }

            @Override // com.wanos.wanosplayermodule.playPermission.NetWorkChangeLis
            public void onNetSuccess() {
                MediaPlayerService.this.isShutdown = false;
                MediaPlayerService.this.netErrorMediaPosition = 0;
                MediaPlayerService.this.refress_time_handler.removeCallbacks(MediaPlayerService.this.netResumeRunnable);
            }
        });
        if (MediaSharedPreferUtils.getMediainfo() != null) {
            MediaInfo mediainfo = MediaSharedPreferUtils.getMediainfo();
            if (mediainfo.getMediaType() == MediaPlayerEnum.MediaType.Music && mediainfo.getMediaGroupType() == 0) {
                if (mediainfo.getGroupId() > -1) {
                    mediainfo.setMediaGroupType(-6L);
                } else {
                    mediainfo.setMediaGroupType(mediainfo.getGroupId());
                }
                MediaSharedPreferUtils.putMediainfo(mediainfo);
                ProgressInfo progress = MediaSharedPreferUtils.getProgress();
                if (progress != null) {
                    if (progress.getGroupId() > -1) {
                        progress.setMediaGroupType(-6L);
                    } else {
                        progress.setMediaGroupType(progress.getGroupId());
                    }
                    MediaSharedPreferUtils.putProgress(progress);
                }
            }
        }
        MediaInfo mediainfo2 = MediaSharedPreferUtils.getMediainfo();
        this.mProgressInfo = MediaSharedPreferUtils.getProgress();
        if (mediainfo2 != null) {
            Log.i(TAG, "onCreate()----当前MediaInfo不为空");
            playMedia(mediainfo2);
            MediaPlayerEnum.MediaType mediaType = mediainfo2.getMediaType();
            if (mediaType == MediaPlayerEnum.MediaType.Music) {
                MediaPlayerEnum.PlayMode musicMode = MediaSharedPreferUtils.getMusicMode();
                if (musicMode != null) {
                    setPlayMode(mediaType, musicMode);
                }
            } else if (mediaType == MediaPlayerEnum.MediaType.Audiobook && (audioBookMode = MediaSharedPreferUtils.getAudioBookMode()) != null) {
                setPlayMode(mediaType, audioBookMode);
            }
            if (mediainfo2.getMediaType() == MediaPlayerEnum.MediaType.Music && mediainfo2.getMusicInfoBean() != null) {
                String lrcPath = mediainfo2.getMusicInfoBean().getLrcPath();
                if (!TextUtils.isEmpty(lrcPath)) {
                    onUpdateSDKLrc(MediaPlayerEnum.UpdateLrcCallbackType.SUCCESS, getLrcContext(lrcPath));
                } else {
                    onUpdateSDKLrc(MediaPlayerEnum.UpdateLrcCallbackType.SUCCESS, "请欣赏，此音乐为纯音乐");
                }
            }
        }
        MediaStatistic.getInstance().saveRecordDevice("");
        if (CommonUtils.isIs371()) {
            WanosFadeInOutManager.getInstance().init(new WanosFadeInOutManager.VolumeControl() { // from class: com.wanos.wanosplayermodule.MediaPlayerService.15
                @Override // com.wanos.wanosplayermodule.audioTrack.drouteclient.WanosFadeInOutManager.VolumeControl
                public float getVolume() {
                    AudioManager audioManager = (AudioManager) Utils.getApp().getSystemService("audio");
                    int streamVolume = audioManager.getStreamVolume(3);
                    int streamMaxVolume = audioManager.getStreamMaxVolume(3);
                    if (streamMaxVolume <= 0) {
                        return 1.0f;
                    }
                    return streamVolume / (streamMaxVolume * 1.0f);
                }

                @Override // com.wanos.wanosplayermodule.audioTrack.drouteclient.WanosFadeInOutManager.VolumeControl
                public void setVolume(float f) {
                    if (MediaPlayerService.this.uiHolder == null || MediaPlayerService.this.uiHolder.player == null) {
                        return;
                    }
                    MediaPlayerService.this.uiHolder.player.setVolume(f, f);
                }
            });
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        Log.i(TAG, "service startcommand");
        if (MediaPlayEngine.getInstance().startFroeSuc) {
            startForeground(1, createNot());
        }
        return super.onStartCommand(intent, i, i2);
    }

    private Notification createNot() {
        ((NotificationManager) getSystemService(NotificationManager.class)).createNotificationChannel(new NotificationChannel("wanos.channel", "wanos.channel.name", 3));
        return new NotificationCompat.Builder(this, "wanos.channel").setSmallIcon(R.drawable.ic_launcher).setContentTitle("全景声").setContentText("播放中...").setPriority(0).build();
    }

    public void requestPlayingMediaDataListData() {
        Log.i(TAG, "requestPlayingMediaDataListData: requestMediaDataListData");
        MediaPlayerManager.getInstance().requestMediaDataListData(MediaPlayerManager.getInstance().getLoadCompleteDataPlayDeal(), this);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);
        EventBus.getDefault().unregister(this);
        release();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loginOrLogout(LoginOrLogoutEvent loginOrLogoutEvent) {
        onMediaInfoCallbackNext(MediaPlayerEnum.MediaInfoCallbackType.supporFavor, getCurMediaInfo());
        if (getCurMediaInfo() != null && getCurMediaInfo().getMediaType() == MediaPlayerEnum.MediaType.Music && loginOrLogoutEvent.isLogin()) {
            MediaPlayerManager.getInstance().updateMusicPlayListCollectStatus(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onListDataExpired(DailyExpiredEvent dailyExpiredEvent) {
        Log.i(TAG, "list expired, reload and play");
        proxyShutdown();
        MediaInfo mediaInfo = new MediaInfo();
        mediaInfo.setMediaType(MediaPlayerEnum.MediaType.Music);
        mediaInfo.setGroupId(-12L);
        mediaInfo.setMediaGroupType(-12L);
        mediaInfo.setMusicInfoBean(dailyExpiredEvent.musicInfoBean);
        play(mediaInfo);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onResourceNotExistEvent(ResourceNotExistEvent resourceNotExistEvent) {
        proxyShutdown();
        onStopPlayBecauseFileNotExist();
    }

    public MediaPlayerEnum.PlayMode getPlayMode(MediaPlayerEnum.MediaType mediaType) {
        if (mediaType == MediaPlayerEnum.MediaType.Music) {
            return MediaSharedPreferUtils.getMusicMode();
        }
        if (mediaType == MediaPlayerEnum.MediaType.Audiobook) {
            return MediaSharedPreferUtils.getAudioBookMode();
        }
        return MediaPlayerEnum.PlayMode.orderplay;
    }

    public void setPlayMode(MediaPlayerEnum.MediaType mediaType, MediaPlayerEnum.PlayMode playMode) {
        if (mediaType == MediaPlayerEnum.MediaType.Music) {
            MediaSharedPreferUtils.putMusicMode(playMode);
        } else if (mediaType == MediaPlayerEnum.MediaType.Audiobook) {
            MediaSharedPreferUtils.putAudioBookMode(playMode);
        }
        MediaPlayerManager.getInstance().updatePlayMode(mediaType, playMode);
        onMediaInfoCallbackNext(MediaPlayerEnum.MediaInfoCallbackType.playMode, getCurMediaInfo());
        onMediaInfoCallbackAppNext(MediaPlayerEnum.MediaInfoCallbackType.playMode, getCurMediaInfo());
    }

    public String getLrcContext(String str) {
        return lyric.getInstance().getLrcContext(this, str);
    }

    public void downLoadLrc(final String str, final MediaPlayerEnum.AppType appType) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        DownloadUtil.get().download(this, str, new DownloadUtil.OnDownloadListener() { // from class: com.wanos.wanosplayermodule.MediaPlayerService.16
            @Override // com.wanos.lyric.DownloadUtil.OnDownloadListener
            public void onDownloadSuccess() {
                MediaPlayerService mediaPlayerService = MediaPlayerService.this;
                mediaPlayerService.mLrcContent = mediaPlayerService.getLrcContext(str);
                DefaultLrcBuilder defaultLrcBuilder = new DefaultLrcBuilder();
                MediaPlayerService mediaPlayerService2 = MediaPlayerService.this;
                mediaPlayerService2.mLrcRows = defaultLrcBuilder.getLrcRows(mediaPlayerService2.mLrcContent);
                if (appType == MediaPlayerEnum.AppType.App) {
                    MediaPlayerService.this.onUpdateAppLrc(MediaPlayerEnum.UpdateLrcCallbackType.SUCCESS, MediaPlayerService.this.mLrcRows);
                } else {
                    MediaPlayerService.this.onUpdateSDKLrc(MediaPlayerEnum.UpdateLrcCallbackType.SUCCESS, MediaPlayerService.this.mLrcContent);
                }
            }

            @Override // com.wanos.lyric.DownloadUtil.OnDownloadListener
            public void onDownloadStart() {
                if (appType == MediaPlayerEnum.AppType.App) {
                    MediaPlayerService.this.onUpdateAppLrc(MediaPlayerEnum.UpdateLrcCallbackType.START, null);
                } else {
                    MediaPlayerService.this.onUpdateSDKLrc(MediaPlayerEnum.UpdateLrcCallbackType.START, "");
                }
            }

            @Override // com.wanos.lyric.DownloadUtil.OnDownloadListener
            public void onDownloading(int i) {
                if (appType == MediaPlayerEnum.AppType.App) {
                    MediaPlayerService.this.onUpdateAppLrc(MediaPlayerEnum.UpdateLrcCallbackType.LOADING, null);
                } else {
                    MediaPlayerService.this.onUpdateSDKLrc(MediaPlayerEnum.UpdateLrcCallbackType.LOADING, "");
                }
            }

            @Override // com.wanos.lyric.DownloadUtil.OnDownloadListener
            public void onDownloadFailed() {
                if (appType == MediaPlayerEnum.AppType.App) {
                    MediaPlayerService.this.onUpdateAppLrc(MediaPlayerEnum.UpdateLrcCallbackType.FAILED, null);
                } else {
                    MediaPlayerService.this.onUpdateSDKLrc(MediaPlayerEnum.UpdateLrcCallbackType.FAILED, "");
                }
            }
        });
    }

    public void updateLrc(String str, MediaPlayerEnum.AppType appType) {
        if (TextUtils.isEmpty(str)) {
            this.mLrcRows = null;
            if (appType == MediaPlayerEnum.AppType.App) {
                onUpdateAppLrc(MediaPlayerEnum.UpdateLrcCallbackType.SUCCESS, null);
                return;
            } else {
                onUpdateSDKLrc(MediaPlayerEnum.UpdateLrcCallbackType.SUCCESS, "请欣赏，此音乐为纯音乐");
                return;
            }
        }
        String lrcContext = getLrcContext(str);
        this.mLrcContent = lrcContext;
        if (lrcContext == null) {
            this.mLrcRows = null;
            downLoadLrc(str, appType);
            return;
        }
        this.mLrcRows = new DefaultLrcBuilder().getLrcRows(this.mLrcContent);
        if (appType == MediaPlayerEnum.AppType.App) {
            onUpdateAppLrc(MediaPlayerEnum.UpdateLrcCallbackType.SUCCESS, this.mLrcRows);
        } else {
            onUpdateSDKLrc(MediaPlayerEnum.UpdateLrcCallbackType.SUCCESS, this.mLrcContent);
        }
    }

    public List<MediaInfo> getCurrentList() {
        if (getPlayMode(MediaPlayerEnum.MediaType.Music) != MediaPlayerEnum.PlayMode.randomplay) {
            return MediaPlayerManager.getInstance().getMediaPlayList();
        }
        return MediaPlayerManager.getInstance().getMediaRandomList();
    }

    public void proxyShutdown() {
        Log.i(TAG, "proxyShutdown()----" + this.proxy);
        HttpProxyCacheServer httpProxyCacheServer = this.proxy;
        if (httpProxyCacheServer != null) {
            CacheListener cacheListener = this.cacheListener;
            if (cacheListener != null) {
                httpProxyCacheServer.unregisterCacheListener(cacheListener);
            }
            this.proxy.shutdown();
            this.proxy = null;
        }
    }

    public void playRecommendMusicMedia() {
        if (this.isLoadingRecommendMusicMedia) {
            return;
        }
        this.isLoadingRecommendMusicMedia = true;
        this.isOutWidgetPlayRecommedMusic = true;
        WanOSRetrofitUtil.getHotMusicList(1, 1, new ResponseCallBack<GetMusicListResponse>(null) { // from class: com.wanos.wanosplayermodule.MediaPlayerService.17
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetMusicListResponse getMusicListResponse) {
                MediaMusicListBean mediaMusicListBean;
                MediaPlayerService.this.isLoadingRecommendMusicMedia = false;
                if (!MediaPlayerService.this.isOutWidgetPlayRecommedMusic || (mediaMusicListBean = getMusicListResponse.data) == null || mediaMusicListBean.getMusicList() == null || mediaMusicListBean.getMusicList().size() <= 0) {
                    return;
                }
                MediaInfo mediaInfo = new MediaInfo();
                mediaInfo.setMediaType(MediaPlayerEnum.MediaType.Music);
                mediaInfo.setGroupId(-1L);
                mediaInfo.setMediaGroupType(-1L);
                mediaInfo.setMusicInfoBean(mediaMusicListBean.getMusicList().get(0));
                Log.i(MediaPlayerService.TAG, "onResponseSuccessful: playMedia 2");
                MediaPlayEngine.getInstance().getMediaPlayerService().playMedia(mediaInfo);
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i, String str) {
                MediaPlayerService.this.isLoadingRecommendMusicMedia = false;
            }
        });
    }

    public void setPlayerInterceptorFlag(boolean z) {
        this.playerInterceptorFlag = z;
    }

    public void getMediaPlayerServiceAndSetListener() {
        PlayPermission playPermission = this.mPlayPermission;
        if (playPermission == null) {
            return;
        }
        playPermission.getMediaPlayerServiceAndSetListener();
    }

    public void hasDisplayOffMessages() {
        AudioFocusManager audioFocusManager = this.mAudioFocusManager;
        if (audioFocusManager == null) {
            return;
        }
        audioFocusManager.hasDisplayOffMessages();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean updateAudiobookProgressIfNeed(MediaInfo mediaInfo, long j) {
        if (mediaInfo.getMediaType() != MediaPlayerEnum.MediaType.Audiobook && (mediaInfo.getMediaType() != MediaPlayerEnum.MediaType.Music || mediaInfo.getMusicInfoBean() == null || mediaInfo.getMusicInfoBean().getContentType() <= 2 || mediaInfo.getAudioBookInfoBean() == null)) {
            return false;
        }
        mediaInfo.getAudioBookInfoBean().setProgress(j);
        return true;
    }
}
