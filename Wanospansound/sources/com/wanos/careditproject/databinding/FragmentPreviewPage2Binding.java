package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;
import com.wanos.careditproject.view.widget.PlayerPageSeekBar;

/* JADX INFO: loaded from: classes3.dex */
public final class FragmentPreviewPage2Binding implements ViewBinding {
    public final AppCompatImageView btnLoading;
    public final AppCompatImageView btnPlayPreview;
    public final CardView cardView;
    public final ImageView ivAlbum;
    public final ImageView ivAvatar;
    public final ImageView ivCollect;
    public final ProgressBar pbLoading;
    private final ConstraintLayout rootView;
    public final PlayerPageSeekBar seekbarProgress;
    public final TextView textPlayTime;
    public final TextView textPlayTime0;
    public final TextView tvCreateAnother;
    public final TextView tvDesc;
    public final TextView tvNickname;
    public final TextView tvSongName;
    public final TextView tvSongType;
    public final CardView viewCard;
    public final ConstraintLayout viewLoading;

    private FragmentPreviewPage2Binding(ConstraintLayout constraintLayout, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2, CardView cardView, ImageView imageView, ImageView imageView2, ImageView imageView3, ProgressBar progressBar, PlayerPageSeekBar playerPageSeekBar, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, CardView cardView2, ConstraintLayout constraintLayout2) {
        this.rootView = constraintLayout;
        this.btnLoading = appCompatImageView;
        this.btnPlayPreview = appCompatImageView2;
        this.cardView = cardView;
        this.ivAlbum = imageView;
        this.ivAvatar = imageView2;
        this.ivCollect = imageView3;
        this.pbLoading = progressBar;
        this.seekbarProgress = playerPageSeekBar;
        this.textPlayTime = textView;
        this.textPlayTime0 = textView2;
        this.tvCreateAnother = textView3;
        this.tvDesc = textView4;
        this.tvNickname = textView5;
        this.tvSongName = textView6;
        this.tvSongType = textView7;
        this.viewCard = cardView2;
        this.viewLoading = constraintLayout2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentPreviewPage2Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentPreviewPage2Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_preview_page2, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentPreviewPage2Binding bind(View view) {
        int i = R.id.btn_loading;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = R.id.btn_play_preview;
            AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
            if (appCompatImageView2 != null) {
                i = R.id.cardView;
                CardView cardView = (CardView) ViewBindings.findChildViewById(view, i);
                if (cardView != null) {
                    i = R.id.iv_album;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                    if (imageView != null) {
                        i = R.id.iv_avatar;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
                        if (imageView2 != null) {
                            i = R.id.iv_collect;
                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, i);
                            if (imageView3 != null) {
                                i = R.id.pb_loading;
                                ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, i);
                                if (progressBar != null) {
                                    i = R.id.seekbar_progress;
                                    PlayerPageSeekBar playerPageSeekBar = (PlayerPageSeekBar) ViewBindings.findChildViewById(view, i);
                                    if (playerPageSeekBar != null) {
                                        i = R.id.text_play_time;
                                        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                                        if (textView != null) {
                                            i = R.id.text_play_time0;
                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                                            if (textView2 != null) {
                                                i = R.id.tv_create_another;
                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                                                if (textView3 != null) {
                                                    i = R.id.tv_desc;
                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, i);
                                                    if (textView4 != null) {
                                                        i = R.id.tv_nickname;
                                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(view, i);
                                                        if (textView5 != null) {
                                                            i = R.id.tv_song_name;
                                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(view, i);
                                                            if (textView6 != null) {
                                                                i = R.id.tv_song_type;
                                                                TextView textView7 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                if (textView7 != null) {
                                                                    i = R.id.viewCard;
                                                                    CardView cardView2 = (CardView) ViewBindings.findChildViewById(view, i);
                                                                    if (cardView2 != null) {
                                                                        i = R.id.view_loading;
                                                                        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                                                                        if (constraintLayout != null) {
                                                                            return new FragmentPreviewPage2Binding((ConstraintLayout) view, appCompatImageView, appCompatImageView2, cardView, imageView, imageView2, imageView3, progressBar, playerPageSeekBar, textView, textView2, textView3, textView4, textView5, textView6, textView7, cardView2, constraintLayout);
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
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
