package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;
import com.wanos.media.ui.widget.lrc.LrcView;

/* JADX INFO: loaded from: classes3.dex */
public final class LayoutMusicPlayRightContentBinding implements ViewBinding {
    public final LinearLayout btnVip;
    public final FrameLayout flConverLrc;
    public final ImageView ivRightMusicCover;
    public final LrcView lrcView;
    public final LayoutMusicListBinding musicListContent;
    public final CardView rightCdCover;
    private final FrameLayout rootView;

    private LayoutMusicPlayRightContentBinding(FrameLayout rootView, LinearLayout btnVip, FrameLayout flConverLrc, ImageView ivRightMusicCover, LrcView lrcView, LayoutMusicListBinding musicListContent, CardView rightCdCover) {
        this.rootView = rootView;
        this.btnVip = btnVip;
        this.flConverLrc = flConverLrc;
        this.ivRightMusicCover = ivRightMusicCover;
        this.lrcView = lrcView;
        this.musicListContent = musicListContent;
        this.rightCdCover = rightCdCover;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static LayoutMusicPlayRightContentBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutMusicPlayRightContentBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.layout_music_play_right_content, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutMusicPlayRightContentBinding bind(View rootView) {
        int i = R.id.btn_vip;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.btn_vip);
        if (linearLayout != null) {
            i = R.id.fl_conver_lrc;
            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.fl_conver_lrc);
            if (frameLayout != null) {
                i = R.id.iv_right_music_cover;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_right_music_cover);
                if (imageView != null) {
                    i = R.id.lrc_view;
                    LrcView lrcView = (LrcView) ViewBindings.findChildViewById(rootView, R.id.lrc_view);
                    if (lrcView != null) {
                        i = R.id.music_list_content;
                        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.music_list_content);
                        if (viewFindChildViewById != null) {
                            LayoutMusicListBinding layoutMusicListBindingBind = LayoutMusicListBinding.bind(viewFindChildViewById);
                            i = R.id.right_cd_cover;
                            CardView cardView = (CardView) ViewBindings.findChildViewById(rootView, R.id.right_cd_cover);
                            if (cardView != null) {
                                return new LayoutMusicPlayRightContentBinding((FrameLayout) rootView, linearLayout, frameLayout, imageView, lrcView, layoutMusicListBindingBind, cardView);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
