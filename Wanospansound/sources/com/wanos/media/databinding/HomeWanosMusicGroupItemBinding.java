package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class HomeWanosMusicGroupItemBinding implements ViewBinding {
    public final FrameLayout btnMusicPlay;
    public final CardView cdItemView;
    public final ImageView imMusicBg;
    public final ImageView ivMusicPlay;
    public final ProgressBar pbMusicPlay;
    private final ConstraintLayout rootView;
    public final TextView tvMusicName;
    public final ConstraintLayout viewItem;

    private HomeWanosMusicGroupItemBinding(ConstraintLayout rootView, FrameLayout btnMusicPlay, CardView cdItemView, ImageView imMusicBg, ImageView ivMusicPlay, ProgressBar pbMusicPlay, TextView tvMusicName, ConstraintLayout viewItem) {
        this.rootView = rootView;
        this.btnMusicPlay = btnMusicPlay;
        this.cdItemView = cdItemView;
        this.imMusicBg = imMusicBg;
        this.ivMusicPlay = ivMusicPlay;
        this.pbMusicPlay = pbMusicPlay;
        this.tvMusicName = tvMusicName;
        this.viewItem = viewItem;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static HomeWanosMusicGroupItemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static HomeWanosMusicGroupItemBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.home_wanos_music_group_item, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static HomeWanosMusicGroupItemBinding bind(View rootView) {
        int i = R.id.btn_music_play;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.btn_music_play);
        if (frameLayout != null) {
            i = R.id.cd_item_view;
            CardView cardView = (CardView) ViewBindings.findChildViewById(rootView, R.id.cd_item_view);
            if (cardView != null) {
                i = R.id.im_music_bg;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.im_music_bg);
                if (imageView != null) {
                    i = R.id.iv_music_play;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_music_play);
                    if (imageView2 != null) {
                        i = R.id.pb_music_play;
                        ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(rootView, R.id.pb_music_play);
                        if (progressBar != null) {
                            i = R.id.tv_music_name;
                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_music_name);
                            if (textView != null) {
                                ConstraintLayout constraintLayout = (ConstraintLayout) rootView;
                                return new HomeWanosMusicGroupItemBinding(constraintLayout, frameLayout, cardView, imageView, imageView2, progressBar, textView, constraintLayout);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
