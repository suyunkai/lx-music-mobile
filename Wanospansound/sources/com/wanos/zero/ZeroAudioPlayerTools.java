package com.wanos.zero;

import android.media.MediaPlayer;
import android.media.VolumeShaper;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.blankj.utilcode.util.Utils;
import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public class ZeroAudioPlayerTools {
    private static final int DURATION = 200;
    private static volatile ZeroAudioPlayerTools INSTANCE = null;
    private static final int MSG_WHAT_PAUSE_END = 202;
    private static final int MSG_WHAT_STOP_END = 201;
    private static final String TAG = "wanos[Zero]-ZeroAudioPlayerTools";
    private final MutableLiveData<AudioPlayerState> audioPlayerState = new MutableLiveData<>(AudioPlayerState.IDLE);
    private final VolumeShaper.Configuration mFadeFocusShaper = new VolumeShaper.Configuration.Builder().setCurve(new float[]{0.0f, 0.2f, 0.4f, 0.8f, 1.0f}, new float[]{0.0f, 0.2f, 0.4f, 0.8f, 1.0f}).setDuration(200).setInterpolatorType(1).build();
    private final Handler mHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.wanos.zero.ZeroAudioPlayerTools.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i != 201) {
                if (i != 202 || ZeroAudioPlayerTools.this.audioPlayerState.getValue() != AudioPlayerState.PAUSING) {
                    return false;
                }
                ZeroAudioPlayerTools.this.mMediaPlayer.pause();
                ZeroAudioPlayerTools.this.audioPlayerState.setValue(AudioPlayerState.PAUSED);
                return false;
            }
            if (ZeroAudioPlayerTools.this.audioPlayerState.getValue() != AudioPlayerState.STOPPING) {
                return false;
            }
            ZeroAudioPlayerTools.this.mMediaPlayer.stop();
            ZeroAudioPlayerTools.this.audioPlayerState.setValue(AudioPlayerState.STOPPED);
            return false;
        }
    });
    private final MediaPlayer mMediaPlayer;
    private VolumeShaper mVolumeShaper;

    public enum AudioPlayerState {
        IDLE,
        PREPARING,
        PREPARED,
        PLAYING,
        PAUSING,
        PAUSED,
        STOPPING,
        STOPPED
    }

    public static ZeroAudioPlayerTools getInstance() {
        if (INSTANCE == null) {
            synchronized (ZeroAudioPlayerTools.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ZeroAudioPlayerTools();
                }
            }
        }
        return INSTANCE;
    }

    private ZeroAudioPlayerTools() {
        MediaPlayer mediaPlayer = new MediaPlayer();
        this.mMediaPlayer = mediaPlayer;
        mediaPlayer.setAudioAttributes(ZeroAudioFocusTools.AUDIO_ATTRIBUTES);
    }

    public LiveData<AudioPlayerState> getAudioPlayerState() {
        return this.audioPlayerState;
    }

    public void onMediaLaunch(File file, boolean z) {
        if (!ZeroAudioFocusTools.getInstance().isCanFocus()) {
            Log.e(TAG, "onMediaLaunch: 当前未获取到音频焦点，播放失败。");
            return;
        }
        if (file == null || !file.exists() || !file.isFile()) {
            Log.e(TAG, "onMediaLaunch: 不存在");
            return;
        }
        Log.i(TAG, "onMediaLaunch: filePath = " + file.getAbsolutePath() + ", isLoop = " + z + ", ThreadName = " + Thread.currentThread().getName());
        AudioPlayerState value = this.audioPlayerState.getValue();
        if (value == AudioPlayerState.PREPARING || value == AudioPlayerState.STOPPING || value == AudioPlayerState.PAUSING || value == AudioPlayerState.PLAYING) {
            Log.w(TAG, "onMediaLaunch: 播放器状态异常，当前状态 = " + value);
            return;
        }
        if (value == AudioPlayerState.PREPARED || value == AudioPlayerState.PAUSED || value == AudioPlayerState.STOPPED) {
            Log.i(TAG, "onMediaLaunch: 当前状态 = " + value + ", 重置播放器，准备播放");
            this.mMediaPlayer.reset();
        }
        try {
            this.mMediaPlayer.setLooping(z);
            this.mMediaPlayer.setDataSource(Utils.getApp(), Uri.parse(file.getAbsolutePath()));
            this.mMediaPlayer.prepareAsync();
            this.audioPlayerState.setValue(AudioPlayerState.PREPARING);
            this.mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.wanos.zero.ZeroAudioPlayerTools$$ExternalSyntheticLambda0
                @Override // android.media.MediaPlayer.OnPreparedListener
                public final void onPrepared(MediaPlayer mediaPlayer) {
                    this.f$0.m705lambda$onMediaLaunch$0$comwanoszeroZeroAudioPlayerTools(mediaPlayer);
                }
            });
            this.mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.wanos.zero.ZeroAudioPlayerTools$$ExternalSyntheticLambda1
                @Override // android.media.MediaPlayer.OnErrorListener
                public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                    return this.f$0.m706lambda$onMediaLaunch$1$comwanoszeroZeroAudioPlayerTools(mediaPlayer, i, i2);
                }
            });
            this.mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.wanos.zero.ZeroAudioPlayerTools$$ExternalSyntheticLambda2
                @Override // android.media.MediaPlayer.OnCompletionListener
                public final void onCompletion(MediaPlayer mediaPlayer) {
                    this.f$0.m707lambda$onMediaLaunch$2$comwanoszeroZeroAudioPlayerTools(mediaPlayer);
                }
            });
        } catch (IOException e) {
            Log.d(TAG, "onMediaLaunch: IOException = " + e.getMessage());
        }
    }

    /* JADX INFO: renamed from: lambda$onMediaLaunch$0$com-wanos-zero-ZeroAudioPlayerTools, reason: not valid java name */
    /* synthetic */ void m705lambda$onMediaLaunch$0$comwanoszeroZeroAudioPlayerTools(MediaPlayer mediaPlayer) {
        Log.i(TAG, "onMediaLaunch: 播放资源准备就绪");
        if (this.audioPlayerState.getValue() == AudioPlayerState.PAUSING || this.audioPlayerState.getValue() == AudioPlayerState.STOPPING) {
            Log.w(TAG, "onMediaLaunch: 播放器正在暂停或者停止");
            return;
        }
        this.mVolumeShaper = this.mMediaPlayer.createVolumeShaper(this.mFadeFocusShaper);
        this.audioPlayerState.setValue(AudioPlayerState.PREPARED);
        fadeIn();
        this.mMediaPlayer.start();
        this.audioPlayerState.setValue(AudioPlayerState.PLAYING);
    }

    /* JADX INFO: renamed from: lambda$onMediaLaunch$1$com-wanos-zero-ZeroAudioPlayerTools, reason: not valid java name */
    /* synthetic */ boolean m706lambda$onMediaLaunch$1$comwanoszeroZeroAudioPlayerTools(MediaPlayer mediaPlayer, int i, int i2) {
        Log.e(TAG, "onMediaLaunch: what = " + i);
        this.mMediaPlayer.reset();
        this.audioPlayerState.setValue(AudioPlayerState.IDLE);
        return true;
    }

    /* JADX INFO: renamed from: lambda$onMediaLaunch$2$com-wanos-zero-ZeroAudioPlayerTools, reason: not valid java name */
    /* synthetic */ void m707lambda$onMediaLaunch$2$comwanoszeroZeroAudioPlayerTools(MediaPlayer mediaPlayer) {
        Log.d(TAG, "onCompletion: isLooping = " + mediaPlayer.isLooping());
        if (mediaPlayer.isLooping()) {
            return;
        }
        this.mMediaPlayer.reset();
        this.audioPlayerState.setValue(AudioPlayerState.IDLE);
    }

    public void onMediaStop(int i) {
        AudioPlayerState value = this.audioPlayerState.getValue();
        if (value == AudioPlayerState.PLAYING) {
            fadeOut();
            this.audioPlayerState.setValue(AudioPlayerState.STOPPING);
            this.mHandler.sendEmptyMessageDelayed(201, i + 200);
        } else if (value == AudioPlayerState.PAUSED) {
            this.mMediaPlayer.stop();
            this.audioPlayerState.setValue(AudioPlayerState.STOPPED);
        } else {
            if (value == AudioPlayerState.PAUSING) {
                this.mHandler.removeMessages(202);
                this.audioPlayerState.setValue(AudioPlayerState.STOPPING);
                this.mHandler.sendEmptyMessageDelayed(201, i + 200);
                return;
            }
            Log.w(TAG, "onMediaStop: 当前状态 = " + value);
        }
    }

    public void onMediaPause() {
        AudioPlayerState value = this.audioPlayerState.getValue();
        if (value == AudioPlayerState.PLAYING) {
            fadeOut();
            this.audioPlayerState.setValue(AudioPlayerState.PAUSING);
            this.mHandler.sendEmptyMessageDelayed(202, 200L);
            return;
        }
        Log.w(TAG, "onMediaPause: 当前状态 = " + value);
    }

    public void onMediaResume() {
        if (!ZeroAudioFocusTools.getInstance().isCanFocus()) {
            Log.e(TAG, "onMediaResume: 当前未获取到音频焦点，播放失败。");
            return;
        }
        AudioPlayerState value = this.audioPlayerState.getValue();
        if (value == AudioPlayerState.PAUSED) {
            fadeIn();
            this.audioPlayerState.setValue(AudioPlayerState.PLAYING);
            this.mMediaPlayer.start();
            return;
        }
        Log.w(TAG, "onMediaResume: 当前状态 = " + value);
    }

    public boolean isPlaying() {
        return this.audioPlayerState.getValue() == AudioPlayerState.PLAYING && this.mMediaPlayer.isPlaying();
    }

    private void fadeIn() {
        VolumeShaper volumeShaper = this.mVolumeShaper;
        if (volumeShaper == null) {
            return;
        }
        try {
            volumeShaper.apply(VolumeShaper.Operation.PLAY);
        } catch (Exception e) {
            Log.d(TAG, "fadeIn: " + e.getMessage());
        }
    }

    private void fadeOut() {
        VolumeShaper volumeShaper = this.mVolumeShaper;
        if (volumeShaper == null) {
            return;
        }
        try {
            volumeShaper.apply(VolumeShaper.Operation.REVERSE);
        } catch (Exception e) {
            Log.d(TAG, "fadeOut: " + e.getMessage());
        }
    }
}
