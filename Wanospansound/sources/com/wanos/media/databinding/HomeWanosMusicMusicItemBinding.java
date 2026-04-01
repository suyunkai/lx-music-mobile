package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class HomeWanosMusicMusicItemBinding implements ViewBinding {
    public final CardView cardBgView;
    public final CardView cdMusicBg;
    public final FrameLayout flIndexView;
    public final ImageView imMusicBg;
    public final ImageView imPalyingStatus;
    public final ImageView ivVip;
    public final LinearLayout llSinger;
    private final ConstraintLayout rootView;
    public final TextView tvMusicName;
    public final TextView tvMusicNum;
    public final TextView tvMusicTime;
    public final TextView tvSingerName;

    private HomeWanosMusicMusicItemBinding(ConstraintLayout rootView, CardView cardBgView, CardView cdMusicBg, FrameLayout flIndexView, ImageView imMusicBg, ImageView imPalyingStatus, ImageView ivVip, LinearLayout llSinger, TextView tvMusicName, TextView tvMusicNum, TextView tvMusicTime, TextView tvSingerName) {
        this.rootView = rootView;
        this.cardBgView = cardBgView;
        this.cdMusicBg = cdMusicBg;
        this.flIndexView = flIndexView;
        this.imMusicBg = imMusicBg;
        this.imPalyingStatus = imPalyingStatus;
        this.ivVip = ivVip;
        this.llSinger = llSinger;
        this.tvMusicName = tvMusicName;
        this.tvMusicNum = tvMusicNum;
        this.tvMusicTime = tvMusicTime;
        this.tvSingerName = tvSingerName;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static HomeWanosMusicMusicItemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static HomeWanosMusicMusicItemBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.home_wanos_music_music_item, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static HomeWanosMusicMusicItemBinding bind(View rootView) {
        int i = R.id.card_bg_view;
        CardView cardView = (CardView) ViewBindings.findChildViewById(rootView, R.id.card_bg_view);
        if (cardView != null) {
            i = R.id.cd_music_bg;
            CardView cardView2 = (CardView) ViewBindings.findChildViewById(rootView, R.id.cd_music_bg);
            if (cardView2 != null) {
                i = R.id.fl_index_view;
                FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.fl_index_view);
                if (frameLayout != null) {
                    i = R.id.im_music_bg;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.im_music_bg);
                    if (imageView != null) {
                        i = R.id.im_palying_status;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.im_palying_status);
                        if (imageView2 != null) {
                            i = R.id.iv_vip;
                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_vip);
                            if (imageView3 != null) {
                                i = R.id.ll_singer;
                                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_singer);
                                if (linearLayout != null) {
                                    i = R.id.tv_music_name;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_music_name);
                                    if (textView != null) {
                                        i = R.id.tv_music_num;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_music_num);
                                        if (textView2 != null) {
                                            i = R.id.tv_music_time;
                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_music_time);
                                            if (textView3 != null) {
                                                i = R.id.tv_singer_name;
                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_singer_name);
                                                if (textView4 != null) {
                                                    return new HomeWanosMusicMusicItemBinding((ConstraintLayout) rootView, cardView, cardView2, frameLayout, imageView, imageView2, imageView3, linearLayout, textView, textView2, textView3, textView4);
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
