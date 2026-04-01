package com.wanos.media.widget.video;

import android.content.Context;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import com.blankj.utilcode.util.ScreenUtils;
import com.wanos.commonlibrary.utils.GlideOptions;
import com.wanos.media.util.VideoCacheUtils;
import com.wanos.media.widget.video.ZeroVideoSurfaceListener;
import com.wanos.media.zero_p.databinding.WidgetBackgroundVideoBinding;

/* JADX INFO: loaded from: classes3.dex */
public class ZeroBackgroundVideo extends FrameLayout {
    private static final String TAG = "ZeroBackgroundVideo";
    private final ZeroVideoSurfaceListener mSurfaceListener;
    private final WidgetBackgroundVideoBinding mViewBinding;

    public ZeroBackgroundVideo(Context context) {
        this(context, null);
    }

    public ZeroBackgroundVideo(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ZeroBackgroundVideo(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        ZeroVideoSurfaceListener zeroVideoSurfaceListener = new ZeroVideoSurfaceListener(new ZeroVideoSurfaceListener.OnMediaStateCallback() { // from class: com.wanos.media.widget.video.ZeroBackgroundVideo.1
            @Override // com.wanos.media.widget.video.ZeroVideoSurfaceListener.OnMediaStateCallback
            public void onTextureSizeChange(Matrix matrix) {
                ZeroBackgroundVideo.this.mViewBinding.videoView.setTransform(matrix);
                ZeroBackgroundVideo.this.mViewBinding.videoView.postInvalidate();
            }

            @Override // com.wanos.media.widget.video.ZeroVideoSurfaceListener.OnMediaStateCallback
            public void onCoverLoaded() {
                ZeroBackgroundVideo.this.mViewBinding.ivVideoCover.setVisibility(0);
            }

            @Override // com.wanos.media.widget.video.ZeroVideoSurfaceListener.OnMediaStateCallback
            public void onVideoPrepared() {
                ZeroBackgroundVideo.this.mViewBinding.ivVideoCover.setVisibility(8);
            }
        });
        this.mSurfaceListener = zeroVideoSurfaceListener;
        WidgetBackgroundVideoBinding widgetBackgroundVideoBindingInflate = WidgetBackgroundVideoBinding.inflate(LayoutInflater.from(context), this);
        this.mViewBinding = widgetBackgroundVideoBindingInflate;
        widgetBackgroundVideoBindingInflate.videoView.setSurfaceTextureListener(zeroVideoSurfaceListener);
    }

    public void setCoverData(String str) {
        new GlideOptions().setSize(ScreenUtils.getScreenWidth(), ScreenUtils.getScreenHeight()).setCovertDimens(false).onLoad(str, this.mViewBinding.ivVideoCover);
    }

    public void setVideoData(String str) {
        Log.d(TAG, "setVideoData: videoUrl = " + str);
        VideoCacheUtils.onLaunchTask(str, new VideoCacheUtils.IVideoCacheListener() { // from class: com.wanos.media.widget.video.ZeroBackgroundVideo$$ExternalSyntheticLambda0
            @Override // com.wanos.media.util.VideoCacheUtils.IVideoCacheListener
            public final void onTaskSuccess(int i, String str2, Object obj) {
                this.f$0.m662x5e4c737c(i, str2, obj);
            }
        }, null);
    }

    /* JADX INFO: renamed from: lambda$setVideoData$0$com-wanos-media-widget-video-ZeroBackgroundVideo, reason: not valid java name */
    /* synthetic */ void m662x5e4c737c(int i, String str, Object obj) {
        Log.d(TAG, "setVideoData: onTaskSuccess = " + str);
        this.mSurfaceListener.setVideoPath(str);
    }
}
