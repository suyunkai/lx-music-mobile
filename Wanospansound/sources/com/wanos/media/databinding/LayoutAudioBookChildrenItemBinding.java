package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class LayoutAudioBookChildrenItemBinding implements ViewBinding {
    public final FrameLayout btnAudioBookPlay;
    public final CardView cdItemView;
    public final LinearLayout cv0;
    public final LinearLayout cv1;
    public final CardView cvMain;
    public final ImageView imCover;
    public final ImageView ivAlbumState;
    public final ImageView ivPayState;
    public final ImageView ivPlayState;
    public final ProgressBar pbAudiobookPlay;
    private final FrameLayout rootView;
    public final TextView tvAudioBookAuthorName;
    public final TextView tvAudioBookName;

    private LayoutAudioBookChildrenItemBinding(FrameLayout rootView, FrameLayout btnAudioBookPlay, CardView cdItemView, LinearLayout cv0, LinearLayout cv1, CardView cvMain, ImageView imCover, ImageView ivAlbumState, ImageView ivPayState, ImageView ivPlayState, ProgressBar pbAudiobookPlay, TextView tvAudioBookAuthorName, TextView tvAudioBookName) {
        this.rootView = rootView;
        this.btnAudioBookPlay = btnAudioBookPlay;
        this.cdItemView = cdItemView;
        this.cv0 = cv0;
        this.cv1 = cv1;
        this.cvMain = cvMain;
        this.imCover = imCover;
        this.ivAlbumState = ivAlbumState;
        this.ivPayState = ivPayState;
        this.ivPlayState = ivPlayState;
        this.pbAudiobookPlay = pbAudiobookPlay;
        this.tvAudioBookAuthorName = tvAudioBookAuthorName;
        this.tvAudioBookName = tvAudioBookName;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static LayoutAudioBookChildrenItemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutAudioBookChildrenItemBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.layout_audio_book_children_item, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutAudioBookChildrenItemBinding bind(View rootView) {
        int i = R.id.btn_audio_book_play;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.btn_audio_book_play);
        if (frameLayout != null) {
            i = R.id.cd_item_view;
            CardView cardView = (CardView) ViewBindings.findChildViewById(rootView, R.id.cd_item_view);
            if (cardView != null) {
                i = R.id.cv_0;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.cv_0);
                if (linearLayout != null) {
                    i = R.id.cv_1;
                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.cv_1);
                    if (linearLayout2 != null) {
                        i = R.id.cv_main;
                        CardView cardView2 = (CardView) ViewBindings.findChildViewById(rootView, R.id.cv_main);
                        if (cardView2 != null) {
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
                                                        return new LayoutAudioBookChildrenItemBinding((FrameLayout) rootView, frameLayout, cardView, linearLayout, linearLayout2, cardView2, imageView, imageView2, imageView3, imageView4, progressBar, textView, textView2);
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
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
