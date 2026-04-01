package com.wanos.media.widget.video;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.viewpager2.widget.ViewPager2;
import com.wanos.media.zero_p.databinding.WidgetWanosVideoPageBinding;

/* JADX INFO: loaded from: classes3.dex */
public class WanosVideoPageView extends FrameLayout {
    private final ExoPlayer mExoPlayer;
    private final ViewPager2.OnPageChangeCallback mPageChangeCallback;
    private final WidgetWanosVideoPageBinding mViewBinding;

    public WanosVideoPageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPageChangeCallback = new ViewPager2.OnPageChangeCallback() { // from class: com.wanos.media.widget.video.WanosVideoPageView.1
            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageScrolled(int i, float f, int i2) {
                super.onPageScrolled(i, f, i2);
                WanosVideoPageView.this.mViewBinding.wanosIndicator.onPageScrolled(i, f, i2);
            }

            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageSelected(int i) {
                super.onPageSelected(i);
            }

            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageScrollStateChanged(int i) {
                super.onPageScrollStateChanged(i);
            }
        };
        WidgetWanosVideoPageBinding widgetWanosVideoPageBindingInflate = WidgetWanosVideoPageBinding.inflate(LayoutInflater.from(context), this, true);
        this.mViewBinding = widgetWanosVideoPageBindingInflate;
        ExoPlayer exoPlayerBuild = new ExoPlayer.Builder(getContext()).build();
        this.mExoPlayer = exoPlayerBuild;
        exoPlayerBuild.setPlaybackParameters(new PlaybackParameters(1.0f, 1.0E-7f));
        exoPlayerBuild.setRepeatMode(1);
        exoPlayerBuild.setTrackSelectionParameters(exoPlayerBuild.getTrackSelectionParameters().buildUpon().setTrackTypeDisabled(1, true).build());
        widgetWanosVideoPageBindingInflate.wanosVideo.setPlayer(exoPlayerBuild);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mViewBinding.wanosViewPage.registerOnPageChangeCallback(this.mPageChangeCallback);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mViewBinding.wanosViewPage.unregisterOnPageChangeCallback(this.mPageChangeCallback);
    }
}
