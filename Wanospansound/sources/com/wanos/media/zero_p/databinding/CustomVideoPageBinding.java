package com.wanos.media.zero_p.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.media3.ui.PlayerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.wanos.media.widget.video.WanosIndicatorView;
import com.wanos.media.zero_p.R;

/* JADX INFO: loaded from: classes3.dex */
public final class CustomVideoPageBinding implements ViewBinding {
    public final AppCompatImageView ivGif;
    private final View rootView;
    public final WanosIndicatorView wanosIndicator;
    public final ViewPager2 wanosPage2;
    public final PlayerView wanosVideo;

    private CustomVideoPageBinding(View view, AppCompatImageView appCompatImageView, WanosIndicatorView wanosIndicatorView, ViewPager2 viewPager2, PlayerView playerView) {
        this.rootView = view;
        this.ivGif = appCompatImageView;
        this.wanosIndicator = wanosIndicatorView;
        this.wanosPage2 = viewPager2;
        this.wanosVideo = playerView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public View getRoot() {
        return this.rootView;
    }

    public static CustomVideoPageBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        if (viewGroup == null) {
            throw new NullPointerException("parent");
        }
        layoutInflater.inflate(R.layout.custom_video_page, viewGroup);
        return bind(viewGroup);
    }

    public static CustomVideoPageBinding bind(View view) {
        int i = R.id.iv_gif;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = R.id.wanos_indicator;
            WanosIndicatorView wanosIndicatorView = (WanosIndicatorView) ViewBindings.findChildViewById(view, i);
            if (wanosIndicatorView != null) {
                i = R.id.wanos_page2;
                ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, i);
                if (viewPager2 != null) {
                    i = R.id.wanos_video;
                    PlayerView playerView = (PlayerView) ViewBindings.findChildViewById(view, i);
                    if (playerView != null) {
                        return new CustomVideoPageBinding(view, appCompatImageView, wanosIndicatorView, viewPager2, playerView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
