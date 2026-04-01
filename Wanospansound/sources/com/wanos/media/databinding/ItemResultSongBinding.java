package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ItemResultSongBinding implements ViewBinding {
    public final CardView cdMusicBg;
    public final FrameLayout flCollectView;
    public final FrameLayout flIndexView;
    public final ImageView imMusicBg;
    public final ImageView imPalyingStatus;
    public final ImageView ivCollect;
    public final ImageView ivVip;
    public final LinearLayout llSinger;
    public final CardView resultItemLayout;
    private final CardView rootView;
    public final TextView tvMusicName;
    public final TextView tvMusicNum;
    public final TextView tvMusicTime;
    public final TextView tvSingerName;

    private ItemResultSongBinding(CardView rootView, CardView cdMusicBg, FrameLayout flCollectView, FrameLayout flIndexView, ImageView imMusicBg, ImageView imPalyingStatus, ImageView ivCollect, ImageView ivVip, LinearLayout llSinger, CardView resultItemLayout, TextView tvMusicName, TextView tvMusicNum, TextView tvMusicTime, TextView tvSingerName) {
        this.rootView = rootView;
        this.cdMusicBg = cdMusicBg;
        this.flCollectView = flCollectView;
        this.flIndexView = flIndexView;
        this.imMusicBg = imMusicBg;
        this.imPalyingStatus = imPalyingStatus;
        this.ivCollect = ivCollect;
        this.ivVip = ivVip;
        this.llSinger = llSinger;
        this.resultItemLayout = resultItemLayout;
        this.tvMusicName = tvMusicName;
        this.tvMusicNum = tvMusicNum;
        this.tvMusicTime = tvMusicTime;
        this.tvSingerName = tvSingerName;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CardView getRoot() {
        return this.rootView;
    }

    public static ItemResultSongBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemResultSongBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_result_song, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemResultSongBinding bind(View rootView) {
        int i = R.id.cd_music_bg;
        CardView cardView = (CardView) ViewBindings.findChildViewById(rootView, R.id.cd_music_bg);
        if (cardView != null) {
            i = R.id.fl_collect_view;
            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.fl_collect_view);
            if (frameLayout != null) {
                i = R.id.fl_index_view;
                FrameLayout frameLayout2 = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.fl_index_view);
                if (frameLayout2 != null) {
                    i = R.id.im_music_bg;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.im_music_bg);
                    if (imageView != null) {
                        i = R.id.im_palying_status;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.im_palying_status);
                        if (imageView2 != null) {
                            i = R.id.iv_collect;
                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_collect);
                            if (imageView3 != null) {
                                i = R.id.iv_vip;
                                ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_vip);
                                if (imageView4 != null) {
                                    i = R.id.ll_singer;
                                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_singer);
                                    if (linearLayout != null) {
                                        CardView cardView2 = (CardView) rootView;
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
                                                        return new ItemResultSongBinding(cardView2, cardView, frameLayout, frameLayout2, imageView, imageView2, imageView3, imageView4, linearLayout, cardView2, textView, textView2, textView3, textView4);
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
