package com.wanos.media.zero_p.databinding;

import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.zero_p.R;

/* JADX INFO: loaded from: classes3.dex */
public final class FragmentBackgroundVideoBinding implements ViewBinding {
    public final AppCompatImageView ivVideoCover;
    private final FrameLayout rootView;
    public final TextureView videoView;

    private FragmentBackgroundVideoBinding(FrameLayout frameLayout, AppCompatImageView appCompatImageView, TextureView textureView) {
        this.rootView = frameLayout;
        this.ivVideoCover = appCompatImageView;
        this.videoView = textureView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static FragmentBackgroundVideoBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentBackgroundVideoBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_background_video, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentBackgroundVideoBinding bind(View view) {
        int i = R.id.iv_video_cover;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = R.id.video_view;
            TextureView textureView = (TextureView) ViewBindings.findChildViewById(view, i);
            if (textureView != null) {
                return new FragmentBackgroundVideoBinding((FrameLayout) view, appCompatImageView, textureView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
