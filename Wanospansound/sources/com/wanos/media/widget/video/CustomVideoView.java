package com.wanos.media.widget.video;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.StringUtils;
import com.wanos.media.util.ZeroLogcatTools;
import com.wanos.media.widget.video.VideoPlayerRender;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes3.dex */
public class CustomVideoView extends GLSurfaceView {
    public static final int STATE_ERROR = 5;
    public static final int STATE_IDLE = 0;
    public static final int STATE_PAUSED = 4;
    public static final int STATE_PLAYING = 3;
    public static final int STATE_PREPARED = 2;
    public static final int STATE_PREPARING = 1;
    private static final String TAG = "CustomVideoView";
    private String filePath;
    private final MediaPlayer mMediaPlayer;
    private OnPreparedListener mOnPreparedListener;
    private int mState;
    private Surface mSurface;

    public interface OnPreparedListener {
        void onPrepared();
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface State {
    }

    public CustomVideoView(Context context) {
        this(context, null);
    }

    public CustomVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mMediaPlayer = new MediaPlayer();
        VideoPlayerRender videoPlayerRender = new VideoPlayerRender(this);
        videoPlayerRender.setIVideoTextureRenderListener(new VideoPlayerRender.IVideoTextureRenderListener() { // from class: com.wanos.media.widget.video.CustomVideoView$$ExternalSyntheticLambda0
            @Override // com.wanos.media.widget.video.VideoPlayerRender.IVideoTextureRenderListener
            public final void onCreate(SurfaceTexture surfaceTexture) {
                this.f$0.m656lambda$new$0$comwanosmediawidgetvideoCustomVideoView(surfaceTexture);
            }
        });
        setEGLContextClientVersion(3);
        setRenderer(videoPlayerRender);
        setRenderMode(0);
    }

    /* JADX INFO: renamed from: lambda$new$0$com-wanos-media-widget-video-CustomVideoView, reason: not valid java name */
    /* synthetic */ void m656lambda$new$0$comwanosmediawidgetvideoCustomVideoView(SurfaceTexture surfaceTexture) {
        Surface surface = this.mSurface;
        if (surface != null) {
            surface.release();
            this.mSurface = null;
        }
        this.mSurface = new Surface(surfaceTexture);
        onLaunchVideo();
    }

    @Override // android.opengl.GLSurfaceView, android.view.SurfaceView, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mState != 0) {
            this.mMediaPlayer.start();
            this.mMediaPlayer.reset();
            this.mMediaPlayer.release();
            this.mState = 0;
            ZeroLogcatTools.d(TAG, "onDetachedFromWindow: 释放MediaPlayer");
        }
        Surface surface = this.mSurface;
        if (surface != null) {
            surface.release();
            this.mSurface = null;
            ZeroLogcatTools.d(TAG, "onDetachedFromWindow: 释放Surface");
        }
    }

    public void setVideoPath(String str) {
        if (!FileUtils.isFileExists(str)) {
            ZeroLogcatTools.e(TAG, "setVideoPath: 视频文件不存在");
            return;
        }
        if (StringUtils.equals(this.filePath, str)) {
            return;
        }
        this.filePath = str;
        int i = this.mState;
        if (i == 1) {
            ZeroLogcatTools.w(TAG, "setVideoPath: 上一个资源正在准备中，请稍后再试");
        } else if (i == 2 || i == 3 || i == 4 || i == 5) {
            this.mMediaPlayer.stop();
            this.mMediaPlayer.reset();
        }
        this.mState = 0;
        onLaunchVideo();
    }

    private void onLaunchVideo() {
        Log.d(TAG, "onLaunchVideo: " + this.filePath);
        Surface surface = this.mSurface;
        if (surface == null || !surface.isValid() || StringUtils.isEmpty(this.filePath)) {
            ZeroLogcatTools.e(TAG, "onLaunchVideo: mSurface = " + this.mSurface + ", filePath = " + this.filePath);
            return;
        }
        try {
            this.mMediaPlayer.stop();
            this.mMediaPlayer.reset();
            this.mMediaPlayer.setDataSource(getContext(), Uri.parse(this.filePath));
            this.mMediaPlayer.setSurface(this.mSurface);
            this.mMediaPlayer.setLooping(true);
            this.mMediaPlayer.setScreenOnWhilePlaying(true);
            this.mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.wanos.media.widget.video.CustomVideoView.1
                @Override // android.media.MediaPlayer.OnPreparedListener
                public void onPrepared(MediaPlayer mediaPlayer) {
                    Log.d(CustomVideoView.TAG, "onPrepared: 视频准备就绪，开始播放");
                    CustomVideoView.this.mState = 2;
                    mediaPlayer.setVolume(0.0f, 0.0f);
                    mediaPlayer.start();
                    CustomVideoView.this.mState = 3;
                    if (CustomVideoView.this.mOnPreparedListener != null) {
                        CustomVideoView.this.mOnPreparedListener.onPrepared();
                    }
                }
            });
            this.mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.wanos.media.widget.video.CustomVideoView$$ExternalSyntheticLambda1
                @Override // android.media.MediaPlayer.OnErrorListener
                public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                    return this.f$0.m657xc4e00379(mediaPlayer, i, i2);
                }
            });
            this.mMediaPlayer.prepareAsync();
            this.mState = 1;
        } catch (IOException e) {
            this.mState = 2;
            ZeroLogcatTools.e(TAG, "onLaunchVideo: " + e.getMessage());
        }
    }

    /* JADX INFO: renamed from: lambda$onLaunchVideo$1$com-wanos-media-widget-video-CustomVideoView, reason: not valid java name */
    /* synthetic */ boolean m657xc4e00379(MediaPlayer mediaPlayer, int i, int i2) {
        ZeroLogcatTools.e(TAG, "onLaunchVideo: what = " + i + ", extra =" + i2);
        this.mState = 5;
        mediaPlayer.reset();
        return true;
    }

    @Override // android.opengl.GLSurfaceView
    public void onResume() {
        ZeroLogcatTools.d(TAG, "onResume: mState = " + this.mState);
        if (this.mState == 4) {
            ZeroLogcatTools.d(TAG, "onResume: 继续播放");
            this.mMediaPlayer.start();
            this.mState = 3;
        }
    }

    @Override // android.opengl.GLSurfaceView
    public void onPause() {
        ZeroLogcatTools.d(TAG, "onPause: mState = " + this.mState);
        if (isPlaying()) {
            ZeroLogcatTools.d(TAG, "onPause: 暂停播放");
            this.mState = 4;
            this.mMediaPlayer.pause();
        }
    }

    public boolean isPlaying() {
        return this.mState == 3 && this.mMediaPlayer.isPlaying();
    }

    public void pause() {
        if (isPlaying()) {
            this.mState = 4;
            this.mMediaPlayer.pause();
        }
    }

    public void setOnPreparedListener(OnPreparedListener onPreparedListener) {
        this.mOnPreparedListener = onPreparedListener;
    }
}
