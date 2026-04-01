package com.wanos.media.juyihall.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.juyihall.R;
import com.wanos.media.juyihall.view.PlayableLayout;

/* JADX INFO: loaded from: classes3.dex */
public final class LayoutRec2CommonPlayBinding implements ViewBinding {
    public final PlayableLayout btnMusicPlay;
    public final ImageView ivMusicPlay;
    public final ProgressBar pbMusicPlay;
    private final PlayableLayout rootView;

    private LayoutRec2CommonPlayBinding(PlayableLayout playableLayout, PlayableLayout playableLayout2, ImageView imageView, ProgressBar progressBar) {
        this.rootView = playableLayout;
        this.btnMusicPlay = playableLayout2;
        this.ivMusicPlay = imageView;
        this.pbMusicPlay = progressBar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public PlayableLayout getRoot() {
        return this.rootView;
    }

    public static LayoutRec2CommonPlayBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutRec2CommonPlayBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.layout_rec2_common_play, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutRec2CommonPlayBinding bind(View view) {
        PlayableLayout playableLayout = (PlayableLayout) view;
        int i = R.id.iv_music_play;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.pb_music_play;
            ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, i);
            if (progressBar != null) {
                return new LayoutRec2CommonPlayBinding(playableLayout, playableLayout, imageView, progressBar);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
