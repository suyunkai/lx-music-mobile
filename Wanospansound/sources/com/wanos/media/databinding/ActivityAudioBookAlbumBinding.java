package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.MaterialAutoRefreshLayout;
import com.wanos.commonlibrary.databinding.EmptyViewBinding;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ActivityAudioBookAlbumBinding implements ViewBinding {
    public final CardView albumCover;
    public final LinearLayout albumPage;
    public final MaterialAutoRefreshLayout audiobookRefreshLayout;
    public final ImageView btnBack;
    public final FrameLayout btnCollect;
    public final LinearLayout btnPlay;
    public final RecyclerView chapterList;
    public final EmptyViewBinding emptyView;
    public final ImageView imCover;
    public final ImageView ivAlbumState;
    public final ImageView ivCollect;
    public final ImageView ivPayState;
    public final ImageView ivPlay;
    private final LinearLayout rootView;
    public final TextView tvAlbumName;
    public final TextView tvAudioBookAuthorName;
    public final TextView tvPlay;

    private ActivityAudioBookAlbumBinding(LinearLayout rootView, CardView albumCover, LinearLayout albumPage, MaterialAutoRefreshLayout audiobookRefreshLayout, ImageView btnBack, FrameLayout btnCollect, LinearLayout btnPlay, RecyclerView chapterList, EmptyViewBinding emptyView, ImageView imCover, ImageView ivAlbumState, ImageView ivCollect, ImageView ivPayState, ImageView ivPlay, TextView tvAlbumName, TextView tvAudioBookAuthorName, TextView tvPlay) {
        this.rootView = rootView;
        this.albumCover = albumCover;
        this.albumPage = albumPage;
        this.audiobookRefreshLayout = audiobookRefreshLayout;
        this.btnBack = btnBack;
        this.btnCollect = btnCollect;
        this.btnPlay = btnPlay;
        this.chapterList = chapterList;
        this.emptyView = emptyView;
        this.imCover = imCover;
        this.ivAlbumState = ivAlbumState;
        this.ivCollect = ivCollect;
        this.ivPayState = ivPayState;
        this.ivPlay = ivPlay;
        this.tvAlbumName = tvAlbumName;
        this.tvAudioBookAuthorName = tvAudioBookAuthorName;
        this.tvPlay = tvPlay;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityAudioBookAlbumBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityAudioBookAlbumBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_audio_book_album, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityAudioBookAlbumBinding bind(View rootView) {
        int i = R.id.album_cover;
        CardView cardView = (CardView) ViewBindings.findChildViewById(rootView, R.id.album_cover);
        if (cardView != null) {
            i = R.id.album_page;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.album_page);
            if (linearLayout != null) {
                i = R.id.audiobook_refresh_layout;
                MaterialAutoRefreshLayout materialAutoRefreshLayout = (MaterialAutoRefreshLayout) ViewBindings.findChildViewById(rootView, R.id.audiobook_refresh_layout);
                if (materialAutoRefreshLayout != null) {
                    i = R.id.btn_back;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.btn_back);
                    if (imageView != null) {
                        i = R.id.btn_collect;
                        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.btn_collect);
                        if (frameLayout != null) {
                            i = R.id.btn_play;
                            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.btn_play);
                            if (linearLayout2 != null) {
                                i = R.id.chapter_list;
                                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.chapter_list);
                                if (recyclerView != null) {
                                    i = R.id.empty_view;
                                    View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.empty_view);
                                    if (viewFindChildViewById != null) {
                                        EmptyViewBinding emptyViewBindingBind = EmptyViewBinding.bind(viewFindChildViewById);
                                        i = R.id.im_cover;
                                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.im_cover);
                                        if (imageView2 != null) {
                                            i = R.id.iv_album_state;
                                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_album_state);
                                            if (imageView3 != null) {
                                                i = R.id.iv_collect;
                                                ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_collect);
                                                if (imageView4 != null) {
                                                    i = R.id.iv_pay_state;
                                                    ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_pay_state);
                                                    if (imageView5 != null) {
                                                        i = R.id.iv_play;
                                                        ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_play);
                                                        if (imageView6 != null) {
                                                            i = R.id.tv_album_name;
                                                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_album_name);
                                                            if (textView != null) {
                                                                i = R.id.tv_audio_book_author_name;
                                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_audio_book_author_name);
                                                                if (textView2 != null) {
                                                                    i = R.id.tv_play;
                                                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_play);
                                                                    if (textView3 != null) {
                                                                        return new ActivityAudioBookAlbumBinding((LinearLayout) rootView, cardView, linearLayout, materialAutoRefreshLayout, imageView, frameLayout, linearLayout2, recyclerView, emptyViewBindingBind, imageView2, imageView3, imageView4, imageView5, imageView6, textView, textView2, textView3);
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
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
