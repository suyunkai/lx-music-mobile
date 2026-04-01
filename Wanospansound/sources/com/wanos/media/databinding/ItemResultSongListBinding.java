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
public final class ItemResultSongListBinding implements ViewBinding {
    public final FrameLayout btnMusicPlay;
    public final CardView cdItemView;
    public final ImageView imMusicBg;
    public final ImageView ivAlbumState;
    public final ImageView ivMusicPlay;
    public final ImageView ivPayState;
    public final ProgressBar pbMusicPlay;
    private final ConstraintLayout rootView;
    public final TextView tvAudioBookName;
    public final TextView tvAuthorName;

    private ItemResultSongListBinding(ConstraintLayout rootView, FrameLayout btnMusicPlay, CardView cdItemView, ImageView imMusicBg, ImageView ivAlbumState, ImageView ivMusicPlay, ImageView ivPayState, ProgressBar pbMusicPlay, TextView tvAudioBookName, TextView tvAuthorName) {
        this.rootView = rootView;
        this.btnMusicPlay = btnMusicPlay;
        this.cdItemView = cdItemView;
        this.imMusicBg = imMusicBg;
        this.ivAlbumState = ivAlbumState;
        this.ivMusicPlay = ivMusicPlay;
        this.ivPayState = ivPayState;
        this.pbMusicPlay = pbMusicPlay;
        this.tvAudioBookName = tvAudioBookName;
        this.tvAuthorName = tvAuthorName;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemResultSongListBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemResultSongListBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_result_song_list, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemResultSongListBinding bind(View rootView) {
        int i = R.id.btn_music_play;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.btn_music_play);
        if (frameLayout != null) {
            i = R.id.cd_item_view;
            CardView cardView = (CardView) ViewBindings.findChildViewById(rootView, R.id.cd_item_view);
            if (cardView != null) {
                i = R.id.im_music_bg;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.im_music_bg);
                if (imageView != null) {
                    i = R.id.iv_album_state;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_album_state);
                    if (imageView2 != null) {
                        i = R.id.iv_music_play;
                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_music_play);
                        if (imageView3 != null) {
                            i = R.id.iv_pay_state;
                            ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_pay_state);
                            if (imageView4 != null) {
                                i = R.id.pb_music_play;
                                ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(rootView, R.id.pb_music_play);
                                if (progressBar != null) {
                                    i = R.id.tv_audio_book_name;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_audio_book_name);
                                    if (textView != null) {
                                        i = R.id.tv_author_name;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_author_name);
                                        if (textView2 != null) {
                                            return new ItemResultSongListBinding((ConstraintLayout) rootView, frameLayout, cardView, imageView, imageView2, imageView3, imageView4, progressBar, textView, textView2);
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
