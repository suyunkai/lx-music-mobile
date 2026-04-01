package com.wanos.media.zero_p.databinding;

import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.zero_p.R;

/* JADX INFO: loaded from: classes3.dex */
public final class WidgetBackgroundVideoBinding implements ViewBinding {
    public final AppCompatImageView ivVideoCover;
    private final View rootView;
    public final TextureView videoView;

    private WidgetBackgroundVideoBinding(View view, AppCompatImageView appCompatImageView, TextureView textureView) {
        this.rootView = view;
        this.ivVideoCover = appCompatImageView;
        this.videoView = textureView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public View getRoot() {
        return this.rootView;
    }

    public static WidgetBackgroundVideoBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        if (viewGroup == null) {
            throw new NullPointerException("parent");
        }
        layoutInflater.inflate(R.layout.widget_background_video, viewGroup);
        return bind(viewGroup);
    }

    public static WidgetBackgroundVideoBinding bind(View view) {
        int i = R.id.iv_video_cover;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = R.id.video_view;
            TextureView textureView = (TextureView) ViewBindings.findChildViewById(view, i);
            if (textureView != null) {
                return new WidgetBackgroundVideoBinding(view, appCompatImageView, textureView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
