package com.wanos.careditproject.service.AudioPlayerManager;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.wanos.bean.ProjectInfo;
import com.wanos.careditproject.service.AudioPlayerManager.PlayState;
import com.wanos.careditproject.utils.MediaPlayerHelperUtil;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener;
import com.wanos.wanosplayermodule.MediaPlayerHelper;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes3.dex */
public class AudioPlayerManager {
    private static volatile AudioPlayerManager instance;
    private String currentPlayId;
    private int duration;
    private Runnable progressUpdateRunnable;
    private WeakReference<PlayStateViewModel> viewModelWeakReference;
    private boolean isPlaying = false;
    private Handler handler = new Handler(Looper.getMainLooper());
    private MediaPlayerHelper mediaPlayerHelper = MediaPlayerHelperUtil.create();

    private AudioPlayerManager() {
        initMediaPlayerCallback();
    }

    public static AudioPlayerManager getInstance() {
        if (instance == null) {
            synchronized (AudioPlayerManager.class) {
                if (instance == null) {
                    instance = new AudioPlayerManager();
                }
            }
        }
        return instance;
    }

    public void setViewModel(PlayStateViewModel playStateViewModel) {
        this.viewModelWeakReference = new WeakReference<>(playStateViewModel);
    }

    /* JADX INFO: renamed from: com.wanos.careditproject.service.AudioPlayerManager.AudioPlayerManager$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$wanos$commonlibrary$mediaPlayer$MediaPlayerEnum$CallBackState;

        static {
            int[] iArr = new int[MediaPlayerEnum.CallBackState.values().length];
            $SwitchMap$com$wanos$commonlibrary$mediaPlayer$MediaPlayerEnum$CallBackState = iArr;
            try {
                iArr[MediaPlayerEnum.CallBackState.PREPARE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$wanos$commonlibrary$mediaPlayer$MediaPlayerEnum$CallBackState[MediaPlayerEnum.CallBackState.STARTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$wanos$commonlibrary$mediaPlayer$MediaPlayerEnum$CallBackState[MediaPlayerEnum.CallBackState.PAUSE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$wanos$commonlibrary$mediaPlayer$MediaPlayerEnum$CallBackState[MediaPlayerEnum.CallBackState.COMPLETE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$wanos$commonlibrary$mediaPlayer$MediaPlayerEnum$CallBackState[MediaPlayerEnum.CallBackState.ERROR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private void initMediaPlayerCallback() {
        this.mediaPlayerHelper.setOnStatusCallbackListener(new OnStatusCallbackListener() { // from class: com.wanos.careditproject.service.AudioPlayerManager.AudioPlayerManager$$ExternalSyntheticLambda0
            @Override // com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener
            public final void onStatusonStatusCallbackNext(MediaPlayerEnum.CallBackState callBackState, Object[] objArr) {
                this.f$0.m389x75ef5a6f(callBackState, objArr);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initMediaPlayerCallback$0$com-wanos-careditproject-service-AudioPlayerManager-AudioPlayerManager, reason: not valid java name */
    /* synthetic */ void m389x75ef5a6f(MediaPlayerEnum.CallBackState callBackState, Object[] objArr) {
        int i = AnonymousClass2.$SwitchMap$com$wanos$commonlibrary$mediaPlayer$MediaPlayerEnum$CallBackState[callBackState.ordinal()];
        if (i == 1) {
            updatePlayState(PlayState.State.PREPARING);
            return;
        }
        if (i == 2) {
            this.isPlaying = true;
            updatePlayState(PlayState.State.PLAYING);
            startProgressUpdate();
            return;
        }
        if (i == 3) {
            this.isPlaying = false;
            updatePlayState(PlayState.State.PAUSED);
            pauseProgressUpdate();
        } else if (i == 4) {
            this.isPlaying = false;
            updatePlayState(PlayState.State.COMPLETED);
            stopProgressUpdate();
        } else {
            if (i != 5) {
                return;
            }
            this.isPlaying = false;
            updatePlayState(PlayState.State.ERROR);
            stopProgressUpdate();
        }
    }

    private void updatePlayState(PlayState.State state) {
        PlayState playState = new PlayState();
        playState.setState(state);
        playState.setPlayId(this.currentPlayId);
        WeakReference<PlayStateViewModel> weakReference = this.viewModelWeakReference;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.viewModelWeakReference.get().updatePlayState(playState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePlayProgress() {
        this.duration = this.mediaPlayerHelper.getDuration();
        PlayProgress playProgress = new PlayProgress(this.mediaPlayerHelper.getCurrentPosition(), this.duration, formatTime(this.mediaPlayerHelper.getCurrentPosition()), formatTime(this.duration));
        WeakReference<PlayStateViewModel> weakReference = this.viewModelWeakReference;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.viewModelWeakReference.get().updatePlayProgress(playProgress);
    }

    private void resetPlayProgress() {
        PlayProgress playProgress = new PlayProgress(0, this.duration, formatTime(0), formatTime(this.duration));
        WeakReference<PlayStateViewModel> weakReference = this.viewModelWeakReference;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.viewModelWeakReference.get().updatePlayProgressImmediately(playProgress);
    }

    public void play(Context context, ProjectInfo projectInfo) {
        int i;
        String wanosPath = projectInfo.getWanosPath();
        if (wanosPath == null || wanosPath.isEmpty()) {
            return;
        }
        if (TextUtils.equals(this.currentPlayId, projectInfo.getId())) {
            resume();
            updatePlayState(PlayState.State.PLAYING);
        } else {
            stop();
            this.currentPlayId = projectInfo.getId();
            try {
                i = Integer.parseInt(projectInfo.getId());
            } catch (NumberFormatException e) {
                e.printStackTrace();
                i = 0;
            }
            this.mediaPlayerHelper.playUrl(context, wanosPath, false, i);
            updatePlayState(PlayState.State.PREPARING);
        }
        this.isPlaying = true;
    }

    public void pause() {
        if (this.isPlaying) {
            this.mediaPlayerHelper.pause();
            this.isPlaying = false;
            updatePlayState(PlayState.State.PAUSED);
            pauseProgressUpdate();
        }
    }

    public void resume() {
        if (this.isPlaying) {
            return;
        }
        this.mediaPlayerHelper.start();
        this.isPlaying = true;
        updatePlayState(PlayState.State.PLAYING);
        startProgressUpdate();
    }

    public void stop() {
        stopProgressUpdate();
        this.mediaPlayerHelper.stop();
        this.isPlaying = false;
        this.currentPlayId = null;
        updatePlayState(PlayState.State.IDLE);
    }

    public boolean isPlaying() {
        return this.isPlaying;
    }

    public void release() {
        stopProgressUpdate();
        this.mediaPlayerHelper.release();
    }

    public String getCurrentPlayId() {
        return this.currentPlayId;
    }

    private void startProgressUpdate() {
        pauseProgressUpdate();
        Runnable runnable = new Runnable() { // from class: com.wanos.careditproject.service.AudioPlayerManager.AudioPlayerManager.1
            @Override // java.lang.Runnable
            public void run() {
                AudioPlayerManager.this.updatePlayProgress();
                AudioPlayerManager.this.handler.postDelayed(this, 200L);
            }
        };
        this.progressUpdateRunnable = runnable;
        this.handler.post(runnable);
    }

    private void pauseProgressUpdate() {
        Runnable runnable = this.progressUpdateRunnable;
        if (runnable != null) {
            this.handler.removeCallbacks(runnable);
        }
    }

    private void stopProgressUpdate() {
        resetPlayProgress();
        Runnable runnable = this.progressUpdateRunnable;
        if (runnable != null) {
            this.handler.removeCallbacks(runnable);
        }
    }

    private String formatTime(int i) {
        int i2 = i / 1000;
        return String.format("%02d:%02d", Integer.valueOf(i2 / 60), Integer.valueOf(i2 % 60));
    }
}
