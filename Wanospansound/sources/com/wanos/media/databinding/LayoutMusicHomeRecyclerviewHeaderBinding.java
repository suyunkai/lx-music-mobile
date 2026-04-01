package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;
import com.wanos.media.ui.widget.banner.Banner;

/* JADX INFO: loaded from: classes3.dex */
public final class LayoutMusicHomeRecyclerviewHeaderBinding implements ViewBinding {
    public final Barrier barrier;
    public final FrameLayout btnMusicPlayThree;
    public final FrameLayout btnMusicPlayTwo;
    public final CardView cdTopOne;
    public final CardView cdTopThree;
    public final CardView cdTopTwo;
    public final ImageView ivMusicPlayThree;
    public final ImageView ivMusicPlayTwo;
    public final ImageView ivTopThree;
    public final ImageView ivTopTwo;
    public final Banner musicHomeBanner;
    public final TextView myMusicContent;
    public final TextView myMusicTitle;
    public final ProgressBar pbMusicPlayThree;
    public final ProgressBar pbMusicPlayTwo;
    private final ConstraintLayout rootView;
    public final RecyclerView rvRecommendedMusicGroup;
    public final TextView tvMusicGroupMore;
    public final TextView tvMusicRankingListMore;

    private LayoutMusicHomeRecyclerviewHeaderBinding(ConstraintLayout rootView, Barrier barrier, FrameLayout btnMusicPlayThree, FrameLayout btnMusicPlayTwo, CardView cdTopOne, CardView cdTopThree, CardView cdTopTwo, ImageView ivMusicPlayThree, ImageView ivMusicPlayTwo, ImageView ivTopThree, ImageView ivTopTwo, Banner musicHomeBanner, TextView myMusicContent, TextView myMusicTitle, ProgressBar pbMusicPlayThree, ProgressBar pbMusicPlayTwo, RecyclerView rvRecommendedMusicGroup, TextView tvMusicGroupMore, TextView tvMusicRankingListMore) {
        this.rootView = rootView;
        this.barrier = barrier;
        this.btnMusicPlayThree = btnMusicPlayThree;
        this.btnMusicPlayTwo = btnMusicPlayTwo;
        this.cdTopOne = cdTopOne;
        this.cdTopThree = cdTopThree;
        this.cdTopTwo = cdTopTwo;
        this.ivMusicPlayThree = ivMusicPlayThree;
        this.ivMusicPlayTwo = ivMusicPlayTwo;
        this.ivTopThree = ivTopThree;
        this.ivTopTwo = ivTopTwo;
        this.musicHomeBanner = musicHomeBanner;
        this.myMusicContent = myMusicContent;
        this.myMusicTitle = myMusicTitle;
        this.pbMusicPlayThree = pbMusicPlayThree;
        this.pbMusicPlayTwo = pbMusicPlayTwo;
        this.rvRecommendedMusicGroup = rvRecommendedMusicGroup;
        this.tvMusicGroupMore = tvMusicGroupMore;
        this.tvMusicRankingListMore = tvMusicRankingListMore;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutMusicHomeRecyclerviewHeaderBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutMusicHomeRecyclerviewHeaderBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.layout_music_home_recyclerview_header, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutMusicHomeRecyclerviewHeaderBinding bind(View rootView) {
        int i = R.id.barrier;
        Barrier barrier = (Barrier) ViewBindings.findChildViewById(rootView, R.id.barrier);
        if (barrier != null) {
            i = R.id.btn_music_play_three;
            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.btn_music_play_three);
            if (frameLayout != null) {
                i = R.id.btn_music_play_two;
                FrameLayout frameLayout2 = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.btn_music_play_two);
                if (frameLayout2 != null) {
                    i = R.id.cd_top_one;
                    CardView cardView = (CardView) ViewBindings.findChildViewById(rootView, R.id.cd_top_one);
                    if (cardView != null) {
                        i = R.id.cd_top_three;
                        CardView cardView2 = (CardView) ViewBindings.findChildViewById(rootView, R.id.cd_top_three);
                        if (cardView2 != null) {
                            i = R.id.cd_top_two;
                            CardView cardView3 = (CardView) ViewBindings.findChildViewById(rootView, R.id.cd_top_two);
                            if (cardView3 != null) {
                                i = R.id.iv_music_play_three;
                                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_music_play_three);
                                if (imageView != null) {
                                    i = R.id.iv_music_play_two;
                                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_music_play_two);
                                    if (imageView2 != null) {
                                        i = R.id.iv_top_three;
                                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_top_three);
                                        if (imageView3 != null) {
                                            i = R.id.iv_top_two;
                                            ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_top_two);
                                            if (imageView4 != null) {
                                                i = R.id.music_home_banner;
                                                Banner banner = (Banner) ViewBindings.findChildViewById(rootView, R.id.music_home_banner);
                                                if (banner != null) {
                                                    i = R.id.my_music_content;
                                                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.my_music_content);
                                                    if (textView != null) {
                                                        i = R.id.my_music_title;
                                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.my_music_title);
                                                        if (textView2 != null) {
                                                            i = R.id.pb_music_play_three;
                                                            ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(rootView, R.id.pb_music_play_three);
                                                            if (progressBar != null) {
                                                                i = R.id.pb_music_play_two;
                                                                ProgressBar progressBar2 = (ProgressBar) ViewBindings.findChildViewById(rootView, R.id.pb_music_play_two);
                                                                if (progressBar2 != null) {
                                                                    i = R.id.rv_recommended_music_group;
                                                                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.rv_recommended_music_group);
                                                                    if (recyclerView != null) {
                                                                        i = R.id.tv_music_group_more;
                                                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_music_group_more);
                                                                        if (textView3 != null) {
                                                                            i = R.id.tv_music_ranking_list_more;
                                                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_music_ranking_list_more);
                                                                            if (textView4 != null) {
                                                                                return new LayoutMusicHomeRecyclerviewHeaderBinding((ConstraintLayout) rootView, barrier, frameLayout, frameLayout2, cardView, cardView2, cardView3, imageView, imageView2, imageView3, imageView4, banner, textView, textView2, progressBar, progressBar2, recyclerView, textView3, textView4);
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
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
