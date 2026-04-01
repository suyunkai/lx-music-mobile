package com.wanos.media.ui;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import androidx.media3.exoplayer.ExoPlayer;
import com.wanos.bean.ProgressInfo;
import com.wanos.commonlibrary.bean.AudioBookMineChapterItemBean;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import com.wanos.commonlibrary.mediaCenter.AudioConfig;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.utils.SystemProperties;
import com.wanos.media.MainApplication;
import com.wanos.media.base.CarConstants;
import com.wanos.mediacenter.MediaCenterManager;
import com.wanos.mediacenter.bean.MediaCenterInitCompleteEvent;
import com.wanos.wanosplayermodule.MediaPlayEngine;
import com.wanos.wanosplayermodule.util.MediaSharedPreferUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes3.dex */
public class RecoveryMediaService extends Service {
    public static final String TAG = "wanos:[RecoveryMediaService]";
    private Handler handler;
    private boolean isMediaCenterInitComplete = false;
    private int retryCount = 0;

    static /* synthetic */ int access$308(RecoveryMediaService recoveryMediaService) {
        int i = recoveryMediaService.retryCount;
        recoveryMediaService.retryCount = i + 1;
        return i;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        EventBus.getDefault().register(this);
        StrReceiver.setRecovery(true);
        if (!AudioConfig.isNeedRecovery) {
            return null;
        }
        if (this.handler == null) {
            this.handler = new Handler();
        }
        this.handler.postDelayed(new Runnable() { // from class: com.wanos.media.ui.RecoveryMediaService.1
            @Override // java.lang.Runnable
            public void run() {
                Log.d(RecoveryMediaService.TAG, "在延迟后执行");
                Object token = MediaCenterManager.getInstance().getToken();
                if (!RecoveryMediaService.this.isMediaCenterInitComplete) {
                    Log.i(RecoveryMediaService.TAG, "run: 检查token 是否为null");
                    RecoveryMediaService.this.isMediaCenterInitComplete = token != null;
                }
                Log.d(RecoveryMediaService.TAG, "empty:" + MainApplication.getInstance().centerClient.currentFocusIsEmpty());
                if (RecoveryMediaService.this.isMediaCenterInitComplete) {
                    Log.d(RecoveryMediaService.TAG, "isMediaCenterInitComplete = true");
                    RecoveryMediaService.checkIfIsPlayingBeforeRestart();
                } else {
                    if (token == null) {
                        RecoveryMediaService.this.handler.postDelayed(this, ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS);
                        Log.i(RecoveryMediaService.TAG, "run: isMediaCenterInitComplete = false, retryCount = " + RecoveryMediaService.this.retryCount);
                    }
                    RecoveryMediaService.access$308(RecoveryMediaService.this);
                }
            }
        }, ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void checkIfIsPlayingBeforeRestart() {
        AudioBookMineChapterItemBean audioBookInfoBean;
        boolean z = SystemProperties.getBoolean("sys.audio.release", true);
        Log.d(TAG, "Recovery: 获取sys.audio.release:" + z);
        if (MainApplication.getInstance() != null && MainApplication.getInstance().centerClient != null) {
            if (MainApplication.getInstance().centerClient.currentFocusIsEmpty() || MainApplication.getInstance().centerClient.hasCurrentFocus()) {
                if (CarConstants.needActResumeStartPlay[CarConstants.buildIndex]) {
                    if (MediaSharedPreferUtils.getIsPlaying()) {
                        Log.d(TAG, "开始播放了");
                        if (z) {
                            MediaPlayEngine.getInstance().getMediaPlayerService().playMedia(MediaSharedPreferUtils.getMediainfo());
                            return;
                        }
                        return;
                    }
                    Log.d(TAG, "暂停状态");
                    MainApplication.getInstance().centerClient.updateMusicPlayState(false);
                } else {
                    Log.d(TAG, "暂停状态，此车型不需要恢复播放");
                    MainApplication.getInstance().centerClient.updateMusicPlayState(false);
                }
            } else {
                Log.d(TAG, "暂停状态1111");
                return;
            }
        } else {
            Log.d(TAG, "else");
        }
        final MediaInfo mediainfo = MediaSharedPreferUtils.getMediainfo();
        ProgressInfo progress = MediaSharedPreferUtils.getProgress();
        if (mediainfo == null || progress == null) {
            return;
        }
        MediaPlayerEnum.MediaType mediaType = mediainfo.getMediaType();
        if (mediaType == MediaPlayerEnum.MediaType.Music) {
            MusicInfoBean musicInfoBean = mediainfo.getMusicInfoBean();
            if (musicInfoBean != null && musicInfoBean.getMusicId() == progress.getMediaId() && MainApplication.getInstance() != null && MainApplication.getInstance().centerClient != null) {
                MainApplication.getInstance().centerClient.updateCurrentProgress(progress.getProgress());
            }
            if (mediainfo.getMusicInfoBean() == null || mediainfo.getMusicInfoBean().getLrcPath() == null) {
                return;
            }
            new Thread(new Runnable() { // from class: com.wanos.media.ui.RecoveryMediaService.2
                @Override // java.lang.Runnable
                public void run() {
                    Log.d(RecoveryMediaService.TAG, "开始执行耗时任务");
                    if (mediainfo.getMusicInfoBean().getLrcPath() != null) {
                        try {
                            MediaPlayEngine.getInstance().getMediaPlayerService().updateLrc(mediainfo.getMusicInfoBean().getLrcPath(), MediaPlayerEnum.AppType.Sdk);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    Log.d(RecoveryMediaService.TAG, "完成耗时任务");
                }
            }).start();
            return;
        }
        if (mediaType == MediaPlayerEnum.MediaType.Audiobook && (audioBookInfoBean = mediainfo.getAudioBookInfoBean()) != null && audioBookInfoBean.getRadioId() == progress.getGroupId()) {
            Log.d("Recovery", " 更新进度~~~~" + progress.getProgress());
            MainApplication.getInstance().centerClient.updateCurrentProgress(progress.getProgress());
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMediaCenterInitCompleteEvent(MediaCenterInitCompleteEvent event) {
        Log.d(TAG, "收到事件:onMediaCenterInitCompleteEvent" + event);
        this.isMediaCenterInitComplete = true;
        checkIfIsPlayingBeforeRestart();
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        EventBus.getDefault().unregister(this);
        return super.onUnbind(intent);
    }
}
