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
public final class LayoutAudioBookHomeAlbumItemBinding implements ViewBinding {
    public final FrameLayout btnAudioBookPlay;
    public final CardView cdItemView;
    public final ImageView imCover;
    public final ImageView ivAlbumState;
    public final ImageView ivPayState;
    public final ImageView ivPlayState;
    public final ProgressBar pbAudiobookPlay;
    private final ConstraintLayout rootView;
    public final TextView tvAudioBookAuthorName;
    public final TextView tvAudioBookName;

    private LayoutAudioBookHomeAlbumItemBinding(ConstraintLayout rootView, FrameLayout btnAudioBookPlay, CardView cdItemView, ImageView imCover, ImageView ivAlbumState, ImageView ivPayState, ImageView ivPlayState, ProgressBar pbAudiobookPlay, TextView tvAudioBookAuthorName, TextView tvAudioBookName) {
        this.rootView = rootView;
        this.btnAudioBookPlay = btnAudioBookPlay;
        this.cdItemView = cdItemView;
        this.imCover = imCover;
        this.ivAlbumState = ivAlbumState;
        this.ivPayState = ivPayState;
        this.ivPlayState = ivPlayState;
        this.pbAudiobookPlay = pbAudiobookPlay;
        this.tvAudioBookAuthorName = tvAudioBookAuthorName;
        this.tvAudioBookName = tvAudioBookName;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutAudioBookHomeAlbumItemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutAudioBookHomeAlbumItemBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.layout_audio_book_home_album_item, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutAudioBookHomeAlbumItemBinding bind(View rootView) {
        int i = R.id.btn_audio_book_play;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.btn_audio_book_play);
        if (frameLayout != null) {
            i = R.id.cd_item_view;
            CardView cardView = (CardView) ViewBindings.findChildViewById(rootView, R.id.cd_item_view);
            if (cardView != null) {
                i = R.id.im_cover;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.im_cover);
                if (imageView != null) {
                    i = R.id.iv_album_state;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_album_state);
                    if (imageView2 != null) {
                        i = R.id.iv_pay_state;
                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_pay_state);
                        if (imageView3 != null) {
                            i = R.id.iv_play_state;
                            ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_play_state);
                            if (imageView4 != null) {
                                i = R.id.pb_audiobook_play;
                                ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(rootView, R.id.pb_audiobook_play);
                                if (progressBar != null) {
                                    i = R.id.tv_audio_book_author_name;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_audio_book_author_name);
                                    if (textView != null) {
                                        i = R.id.tv_audio_book_name;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_audio_book_name);
                                        if (textView2 != null) {
                                            return new LayoutAudioBookHomeAlbumItemBinding((ConstraintLayout) rootView, frameLayout, cardView, imageView, imageView2, imageView3, imageView4, progressBar, textView, textView2);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
