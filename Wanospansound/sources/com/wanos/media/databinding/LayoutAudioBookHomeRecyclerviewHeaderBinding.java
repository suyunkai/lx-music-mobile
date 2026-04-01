package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;
import com.wanos.media.ui.widget.banner.Banner;

/* JADX INFO: loaded from: classes3.dex */
public final class LayoutAudioBookHomeRecyclerviewHeaderBinding implements ViewBinding {
    public final Banner abHomeBanner;
    public final Barrier barrier;
    public final CardView cdTopOne;
    public final CardView cdTopThree;
    public final CardView cdTopTwo;
    public final ImageView ivTopThree;
    public final ImageView ivTopTwo;
    private final ConstraintLayout rootView;
    public final TextView tvAlbumAll;
    public final TextView tvChildren;
    public final TextView tvChildrenContent;
    public final TextView tvMine;
    public final TextView tvMineContent;

    private LayoutAudioBookHomeRecyclerviewHeaderBinding(ConstraintLayout rootView, Banner abHomeBanner, Barrier barrier, CardView cdTopOne, CardView cdTopThree, CardView cdTopTwo, ImageView ivTopThree, ImageView ivTopTwo, TextView tvAlbumAll, TextView tvChildren, TextView tvChildrenContent, TextView tvMine, TextView tvMineContent) {
        this.rootView = rootView;
        this.abHomeBanner = abHomeBanner;
        this.barrier = barrier;
        this.cdTopOne = cdTopOne;
        this.cdTopThree = cdTopThree;
        this.cdTopTwo = cdTopTwo;
        this.ivTopThree = ivTopThree;
        this.ivTopTwo = ivTopTwo;
        this.tvAlbumAll = tvAlbumAll;
        this.tvChildren = tvChildren;
        this.tvChildrenContent = tvChildrenContent;
        this.tvMine = tvMine;
        this.tvMineContent = tvMineContent;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutAudioBookHomeRecyclerviewHeaderBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutAudioBookHomeRecyclerviewHeaderBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.layout_audio_book_home_recyclerview_header, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutAudioBookHomeRecyclerviewHeaderBinding bind(View rootView) {
        int i = R.id.ab_home_banner;
        Banner banner = (Banner) ViewBindings.findChildViewById(rootView, R.id.ab_home_banner);
        if (banner != null) {
            i = R.id.barrier;
            Barrier barrier = (Barrier) ViewBindings.findChildViewById(rootView, R.id.barrier);
            if (barrier != null) {
                i = R.id.cd_top_one;
                CardView cardView = (CardView) ViewBindings.findChildViewById(rootView, R.id.cd_top_one);
                if (cardView != null) {
                    i = R.id.cd_top_three;
                    CardView cardView2 = (CardView) ViewBindings.findChildViewById(rootView, R.id.cd_top_three);
                    if (cardView2 != null) {
                        i = R.id.cd_top_two;
                        CardView cardView3 = (CardView) ViewBindings.findChildViewById(rootView, R.id.cd_top_two);
                        if (cardView3 != null) {
                            i = R.id.iv_top_three;
                            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_top_three);
                            if (imageView != null) {
                                i = R.id.iv_top_two;
                                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_top_two);
                                if (imageView2 != null) {
                                    i = R.id.tv_album_all;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_album_all);
                                    if (textView != null) {
                                        i = R.id.tv_children;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_children);
                                        if (textView2 != null) {
                                            i = R.id.tv_children_content;
                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_children_content);
                                            if (textView3 != null) {
                                                i = R.id.tv_mine;
                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_mine);
                                                if (textView4 != null) {
                                                    i = R.id.tv_mine_content;
                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_mine_content);
                                                    if (textView5 != null) {
                                                        return new LayoutAudioBookHomeRecyclerviewHeaderBinding((ConstraintLayout) rootView, banner, barrier, cardView, cardView2, cardView3, imageView, imageView2, textView, textView2, textView3, textView4, textView5);
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
