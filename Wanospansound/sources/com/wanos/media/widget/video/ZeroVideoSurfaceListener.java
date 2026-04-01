package com.wanos.media.widget.video;

import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.wanos.media.util.ZeroLogcatTools;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
class ZeroVideoSurfaceListener implements TextureView.SurfaceTextureListener {
    private static final String TAG = "ZeroVideoSurfaceListene";
    private final Runnable mHandlerRunnable = new Runnable() { // from class: com.wanos.media.widget.video.ZeroVideoSurfaceListener.1
        @Override // java.lang.Runnable
        public void run() {
            ZeroVideoSurfaceListener.this.onMediaStateCallback.onVideoPrepared();
        }
    };
    private MediaPlayer mMediaPlayer;
    private String mVideoPath;
    private final OnMediaStateCallback onMediaStateCallback;

    interface OnMediaStateCallback {
        void onCoverLoaded();

        void onTextureSizeChange(Matrix matrix);

        void onVideoPrepared();
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    ZeroVideoSurfaceListener(OnMediaStateCallback onMediaStateCallback) {
        this.onMediaStateCallback = onMediaStateCallback;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, final int i, final int i2) {
        Log.d(TAG, "onSurfaceTextureAvailable: width = " + i + ", height = " + i2 + ",MediaPlayer = " + this.mMediaPlayer);
        this.onMediaStateCallback.onCoverLoaded();
        MediaPlayer mediaPlayer = new MediaPlayer();
        this.mMediaPlayer = mediaPlayer;
        mediaPlayer.setSurface(new Surface(surfaceTexture));
        try {
            if (!StringUtils.isEmpty(this.mVideoPath)) {
                this.mMediaPlayer.setDataSource(this.mVideoPath);
                this.mMediaPlayer.prepareAsync();
            }
            this.mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.wanos.media.widget.video.ZeroVideoSurfaceListener$$ExternalSyntheticLambda0
                @Override // android.media.MediaPlayer.OnCompletionListener
                public final void onCompletion(MediaPlayer mediaPlayer2) {
                    mediaPlayer2.start();
                }
            });
            this.mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.wanos.media.widget.video.ZeroVideoSurfaceListener$$ExternalSyntheticLambda1
                @Override // android.media.MediaPlayer.OnPreparedListener
                public final void onPrepared(MediaPlayer mediaPlayer2) {
                    this.f$0.m668x3f1df456(i, i2, mediaPlayer2);
                }
            });
        } catch (IOException e) {
            ZeroLogcatTools.e(TAG, "onSurfaceTextureAvailable:" + e.getMessage());
        }
    }

    /* JADX INFO: renamed from: lambda$onSurfaceTextureAvailable$0$com-wanos-media-widget-video-ZeroVideoSurfaceListener, reason: not valid java name */
    /* synthetic */ void m668x3f1df456(int i, int i2, MediaPlayer mediaPlayer) {
        mediaPlayer.setVolume(0.0f, 0.0f);
        int videoWidth = mediaPlayer.getVideoWidth();
        float f = i;
        float f2 = videoWidth;
        float f3 = i2;
        float videoHeight = mediaPlayer.getVideoHeight();
        Matrix matrix = new Matrix();
        float fMax = Math.max(f / f2, f3 / videoHeight);
        matrix.preTranslate((i - videoWidth) / 2.0f, (i2 - r1) / 2.0f);
        matrix.preScale(f2 / f, videoHeight / f3);
        matrix.postScale(fMax, fMax, f / 2.0f, f3 / 2.0f);
        this.onMediaStateCallback.onTextureSizeChange(matrix);
        mediaPlayer.start();
        ThreadUtils.getMainHandler().postDelayed(this.mHandlerRunnable, 400L);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        Log.d(TAG, "onSurfaceTextureSizeChanged: width = " + i + ", height = " + i2 + ",MediaPlayer = " + this.mMediaPlayer);
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer == null || mediaPlayer.getDuration() == -1) {
            return;
        }
        int videoWidth = this.mMediaPlayer.getVideoWidth();
        float f = i;
        float f2 = videoWidth;
        float f3 = i2;
        float videoHeight = this.mMediaPlayer.getVideoHeight();
        Matrix matrix = new Matrix();
        float fMax = Math.max(f / f2, f3 / videoHeight);
        matrix.preTranslate((i - videoWidth) / 2.0f, (i2 - r0) / 2.0f);
        matrix.preScale(f2 / f, videoHeight / f3);
        matrix.postScale(fMax, fMax, f / 2.0f, f3 / 2.0f);
        this.onMediaStateCallback.onTextureSizeChange(matrix);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        Log.d(TAG, "onSurfaceTextureDestroyed: 释放MediaPlayer");
        ThreadUtils.getMainHandler().removeCallbacks(this.mHandlerRunnable);
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer == null) {
            return false;
        }
        mediaPlayer.stop();
        this.mMediaPlayer.setSurface(null);
        this.mMediaPlayer.release();
        this.mMediaPlayer = null;
        return false;
    }

    void setVideoPath(String str) {
        if (StringUtils.isEmpty(this.mVideoPath) || !this.mVideoPath.equals(str)) {
            ZeroLogcatTools.d(TAG, "setDataSource: MediaPlayer = " + this.mMediaPlayer + ", filePath = " + str);
            this.mVideoPath = str;
            try {
                MediaPlayer mediaPlayer = this.mMediaPlayer;
                if (mediaPlayer != null) {
                    mediaPlayer.reset();
                    this.mMediaPlayer.setDataSource(str);
                    this.mMediaPlayer.prepareAsync();
                }
            } catch (IOException e) {
                ZeroLogcatTools.e(TAG, "setDataSource: " + e.getMessage());
            }
        }
    }
}
