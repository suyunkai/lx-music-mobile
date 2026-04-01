package com.wanos.media.widget.video;

import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.wanos.commonlibrary.utils.GlideOptions;
import com.wanos.media.entity.ZeroThemeInfo;
import com.wanos.media.util.VideoCacheUtils;
import com.wanos.media.util.ZeroLogcatTools;
import com.wanos.media.zero_p.databinding.FragmentBackgroundVideoBinding;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public class BackgroundVideoFragment extends Fragment {
    private static final String TAG = "BackgroundVideoFragment";
    private boolean isReadyMedia = false;
    private MediaPlayer mMediaPlayer;
    private SurfaceTexture mSurfaceTexture;
    private FragmentBackgroundVideoBinding mViewBinding;
    private ZeroThemeInfo mZeroThemeInfo;

    public static BackgroundVideoFragment getInstance(ZeroThemeInfo zeroThemeInfo) {
        BackgroundVideoFragment backgroundVideoFragment = new BackgroundVideoFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("themeInfo", zeroThemeInfo);
        backgroundVideoFragment.setArguments(bundle);
        return backgroundVideoFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentBackgroundVideoBinding fragmentBackgroundVideoBindingInflate = FragmentBackgroundVideoBinding.inflate(layoutInflater, viewGroup, false);
        this.mViewBinding = fragmentBackgroundVideoBindingInflate;
        return fragmentBackgroundVideoBindingInflate.getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (this.mViewBinding == null) {
            return;
        }
        this.mMediaPlayer = new MediaPlayer();
        this.mViewBinding.videoView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() { // from class: com.wanos.media.widget.video.BackgroundVideoFragment.1
            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
                if (BackgroundVideoFragment.this.mSurfaceTexture == null) {
                    BackgroundVideoFragment.this.mSurfaceTexture = surfaceTexture;
                    BackgroundVideoFragment.this.mMediaPlayer.setSurface(new Surface(BackgroundVideoFragment.this.mSurfaceTexture));
                }
                if (!BackgroundVideoFragment.this.isReadyMedia || BackgroundVideoFragment.this.mMediaPlayer.getDuration() <= 0) {
                    return;
                }
                BackgroundVideoFragment.this.mMediaPlayer.start();
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                BackgroundVideoFragment.this.mMediaPlayer.pause();
                return BackgroundVideoFragment.this.mSurfaceTexture == null;
            }
        });
        this.mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.wanos.media.widget.video.BackgroundVideoFragment$$ExternalSyntheticLambda1
            @Override // android.media.MediaPlayer.OnPreparedListener
            public final void onPrepared(MediaPlayer mediaPlayer) {
                this.f$0.m654x7c325f2c(mediaPlayer);
            }
        });
        this.mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.wanos.media.widget.video.BackgroundVideoFragment$$ExternalSyntheticLambda2
            @Override // android.media.MediaPlayer.OnCompletionListener
            public final void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });
        ZeroThemeInfo zeroThemeInfo = getArguments() != null ? (ZeroThemeInfo) getArguments().getSerializable("themeInfo") : null;
        this.mZeroThemeInfo = zeroThemeInfo;
        if (zeroThemeInfo != null) {
            setCoverData(zeroThemeInfo.getThemeBgImage());
        }
    }

    /* JADX INFO: renamed from: lambda$onViewCreated$1$com-wanos-media-widget-video-BackgroundVideoFragment, reason: not valid java name */
    /* synthetic */ void m654x7c325f2c(MediaPlayer mediaPlayer) {
        if (this.mViewBinding == null) {
            return;
        }
        this.isReadyMedia = true;
        mediaPlayer.setVolume(0.0f, 0.0f);
        int videoWidth = mediaPlayer.getVideoWidth();
        int videoHeight = mediaPlayer.getVideoHeight();
        float videoViewWidth = getVideoViewWidth();
        float videoViewHeight = getVideoViewHeight();
        float f = videoWidth;
        float f2 = videoHeight;
        Matrix matrix = new Matrix();
        float fMax = Math.max(videoViewWidth / f, videoViewHeight / f2);
        matrix.preTranslate((videoViewWidth - f) / 2.0f, (videoViewHeight - f2) / 2.0f);
        matrix.preScale(f / videoViewWidth, f2 / videoViewHeight);
        matrix.postScale(fMax, fMax, videoViewWidth / 2.0f, videoViewHeight / 2.0f);
        this.mViewBinding.videoView.setTransform(matrix);
        mediaPlayer.start();
        ThreadUtils.getMainHandler().postDelayed(new Runnable() { // from class: com.wanos.media.widget.video.BackgroundVideoFragment$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m653x6216e08d();
            }
        }, 200L);
    }

    /* JADX INFO: renamed from: lambda$onViewCreated$0$com-wanos-media-widget-video-BackgroundVideoFragment, reason: not valid java name */
    /* synthetic */ void m653x6216e08d() {
        FragmentBackgroundVideoBinding fragmentBackgroundVideoBinding = this.mViewBinding;
        if (fragmentBackgroundVideoBinding == null) {
            return;
        }
        fragmentBackgroundVideoBinding.ivVideoCover.setVisibility(8);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mViewBinding = null;
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
        }
    }

    private void setCoverData(String str) {
        if (this.mViewBinding == null) {
            return;
        }
        new GlideOptions().setSize(ScreenUtils.getScreenWidth(), ScreenUtils.getScreenHeight()).setCovertDimens(false).onLoad(str, this.mViewBinding.ivVideoCover);
    }

    public void setVideoData(String str, long j) {
        ZeroLogcatTools.d(TAG, "setVideoData: videoUrl = " + str + ", themeId = " + j);
        ZeroThemeInfo zeroThemeInfo = this.mZeroThemeInfo;
        if (zeroThemeInfo == null || j != zeroThemeInfo.getThemeId()) {
            StringBuilder sbAppend = new StringBuilder("setVideoData: 入参主题ID = ").append(j).append("，当前主题ID = ");
            ZeroThemeInfo zeroThemeInfo2 = this.mZeroThemeInfo;
            ZeroLogcatTools.d(TAG, sbAppend.append(zeroThemeInfo2 == null ? "NULL" : zeroThemeInfo2.toString()).toString());
        } else if (this.isReadyMedia && this.mMediaPlayer.getDuration() > 0) {
            ZeroLogcatTools.d(TAG, "该背景已设置资源");
        } else {
            VideoCacheUtils.onLaunchTask(str, new VideoCacheUtils.IVideoCacheListener() { // from class: com.wanos.media.widget.video.BackgroundVideoFragment$$ExternalSyntheticLambda0
                @Override // com.wanos.media.util.VideoCacheUtils.IVideoCacheListener
                public final void onTaskSuccess(int i, String str2, Object obj) {
                    this.f$0.m655x98296272(i, str2, obj);
                }
            }, null);
        }
    }

    /* JADX INFO: renamed from: lambda$setVideoData$2$com-wanos-media-widget-video-BackgroundVideoFragment, reason: not valid java name */
    /* synthetic */ void m655x98296272(int i, String str, Object obj) {
        ZeroLogcatTools.d(TAG, "setVideoData: onTaskSuccess = " + str);
        try {
            this.mMediaPlayer.setDataSource(str);
            this.mMediaPlayer.prepareAsync();
        } catch (IOException e) {
            ZeroLogcatTools.d(TAG, "setVideoData: onTaskSuccess = " + e.getMessage());
        }
    }

    private float getVideoViewWidth() {
        FragmentBackgroundVideoBinding fragmentBackgroundVideoBinding = this.mViewBinding;
        if (fragmentBackgroundVideoBinding == null) {
            return 0.0f;
        }
        int width = fragmentBackgroundVideoBinding.videoView.getWidth();
        return width == 0 ? ScreenUtils.getScreenWidth() : width;
    }

    private float getVideoViewHeight() {
        FragmentBackgroundVideoBinding fragmentBackgroundVideoBinding = this.mViewBinding;
        if (fragmentBackgroundVideoBinding == null) {
            return 0.0f;
        }
        int height = fragmentBackgroundVideoBinding.videoView.getHeight();
        return height == 0 ? ScreenUtils.getScreenHeight() : height;
    }
}
