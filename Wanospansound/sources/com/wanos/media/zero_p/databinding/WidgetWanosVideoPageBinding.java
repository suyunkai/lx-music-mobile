package com.wanos.media.zero_p.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.media3.ui.PlayerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.wanos.media.widget.video.WanosIndicatorView;
import com.wanos.media.zero_p.R;

/* JADX INFO: loaded from: classes3.dex */
public final class WidgetWanosVideoPageBinding implements ViewBinding {
    private final FrameLayout rootView;
    public final WanosIndicatorView wanosIndicator;
    public final PlayerView wanosVideo;
    public final ViewPager2 wanosViewPage;

    private WidgetWanosVideoPageBinding(FrameLayout frameLayout, WanosIndicatorView wanosIndicatorView, PlayerView playerView, ViewPager2 viewPager2) {
        this.rootView = frameLayout;
        this.wanosIndicator = wanosIndicatorView;
        this.wanosVideo = playerView;
        this.wanosViewPage = viewPager2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static WidgetWanosVideoPageBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static WidgetWanosVideoPageBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.widget_wanos_video_page, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static WidgetWanosVideoPageBinding bind(View view) {
        int i = R.id.wanos_indicator;
        WanosIndicatorView wanosIndicatorView = (WanosIndicatorView) ViewBindings.findChildViewById(view, i);
        if (wanosIndicatorView != null) {
            i = R.id.wanos_video;
            PlayerView playerView = (PlayerView) ViewBindings.findChildViewById(view, i);
            if (playerView != null) {
                i = R.id.wanos_view_page;
                ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, i);
                if (viewPager2 != null) {
                    return new WidgetWanosVideoPageBinding((FrameLayout) view, wanosIndicatorView, playerView, viewPager2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
