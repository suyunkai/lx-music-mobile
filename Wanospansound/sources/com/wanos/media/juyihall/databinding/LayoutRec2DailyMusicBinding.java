package com.wanos.media.juyihall.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.juyihall.R;
import com.wanos.media.juyihall.view.PlayableLayout;

/* JADX INFO: loaded from: classes3.dex */
public final class LayoutRec2DailyMusicBinding implements ViewBinding {
    public final CardView avatar;
    public final PlayableLayout btnMusicPlay;
    public final ImageView ivAvatar;
    public final ImageView ivMusicPlay;
    public final ProgressBar pbMusicPlay;
    public final RecyclerView recyclerView;
    private final ConstraintLayout rootView;
    public final TextView title;

    private LayoutRec2DailyMusicBinding(ConstraintLayout constraintLayout, CardView cardView, PlayableLayout playableLayout, ImageView imageView, ImageView imageView2, ProgressBar progressBar, RecyclerView recyclerView, TextView textView) {
        this.rootView = constraintLayout;
        this.avatar = cardView;
        this.btnMusicPlay = playableLayout;
        this.ivAvatar = imageView;
        this.ivMusicPlay = imageView2;
        this.pbMusicPlay = progressBar;
        this.recyclerView = recyclerView;
        this.title = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutRec2DailyMusicBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutRec2DailyMusicBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.layout_rec2_daily_music, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutRec2DailyMusicBinding bind(View view) {
        int i = R.id.avatar;
        CardView cardView = (CardView) ViewBindings.findChildViewById(view, i);
        if (cardView != null) {
            i = R.id.btn_music_play;
            PlayableLayout playableLayout = (PlayableLayout) ViewBindings.findChildViewById(view, i);
            if (playableLayout != null) {
                i = R.id.iv_avatar;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                if (imageView != null) {
                    i = R.id.iv_music_play;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
                    if (imageView2 != null) {
                        i = R.id.pb_music_play;
                        ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, i);
                        if (progressBar != null) {
                            i = R.id.recycler_view;
                            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                            if (recyclerView != null) {
                                i = R.id.title;
                                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                                if (textView != null) {
                                    return new LayoutRec2DailyMusicBinding((ConstraintLayout) view, cardView, playableLayout, imageView, imageView2, progressBar, recyclerView, textView);
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
