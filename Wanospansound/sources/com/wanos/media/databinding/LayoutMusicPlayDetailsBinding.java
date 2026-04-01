package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;
import com.wanos.media.ui.widget.WanosSeekBar;

/* JADX INFO: loaded from: classes3.dex */
public final class LayoutMusicPlayDetailsBinding implements ViewBinding {
    public final ImageView btnLast;
    public final ImageView btnNext;
    public final FrameLayout btnPlay;
    public final FrameLayout flTopCard;
    public final View guideline;
    public final View guideline2;
    public final View guidelineRight1;
    public final View guidelineRight2;
    public final ImageFilterView imTopCard;
    public final ImageView ivCollect;
    public final ImageView ivPlay;
    public final ImageView ivVip;
    public final LinearLayout llAction;
    public final LinearLayout llSinger;
    public final ProgressBar pbMusicPlay;
    public final ImageView playList;
    public final ImageView playLrc;
    public final ImageView playType;
    public final Barrier rightBarrier;
    private final ConstraintLayout rootView;
    public final WanosSeekBar seekbarMusicProgress;
    public final ImageView titleLeftBtn;
    public final TextView tvCurrTime;
    public final TextView tvDurTime;
    public final TextView tvMusicName;
    public final TextView tvSingerName;

    private LayoutMusicPlayDetailsBinding(ConstraintLayout rootView, ImageView btnLast, ImageView btnNext, FrameLayout btnPlay, FrameLayout flTopCard, View guideline, View guideline2, View guidelineRight1, View guidelineRight2, ImageFilterView imTopCard, ImageView ivCollect, ImageView ivPlay, ImageView ivVip, LinearLayout llAction, LinearLayout llSinger, ProgressBar pbMusicPlay, ImageView playList, ImageView playLrc, ImageView playType, Barrier rightBarrier, WanosSeekBar seekbarMusicProgress, ImageView titleLeftBtn, TextView tvCurrTime, TextView tvDurTime, TextView tvMusicName, TextView tvSingerName) {
        this.rootView = rootView;
        this.btnLast = btnLast;
        this.btnNext = btnNext;
        this.btnPlay = btnPlay;
        this.flTopCard = flTopCard;
        this.guideline = guideline;
        this.guideline2 = guideline2;
        this.guidelineRight1 = guidelineRight1;
        this.guidelineRight2 = guidelineRight2;
        this.imTopCard = imTopCard;
        this.ivCollect = ivCollect;
        this.ivPlay = ivPlay;
        this.ivVip = ivVip;
        this.llAction = llAction;
        this.llSinger = llSinger;
        this.pbMusicPlay = pbMusicPlay;
        this.playList = playList;
        this.playLrc = playLrc;
        this.playType = playType;
        this.rightBarrier = rightBarrier;
        this.seekbarMusicProgress = seekbarMusicProgress;
        this.titleLeftBtn = titleLeftBtn;
        this.tvCurrTime = tvCurrTime;
        this.tvDurTime = tvDurTime;
        this.tvMusicName = tvMusicName;
        this.tvSingerName = tvSingerName;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutMusicPlayDetailsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutMusicPlayDetailsBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.layout_music_play_details, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutMusicPlayDetailsBinding bind(View rootView) {
        int i = R.id.btn_last;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.btn_last);
        if (imageView != null) {
            i = R.id.btn_next;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.btn_next);
            if (imageView2 != null) {
                i = R.id.btn_play;
                FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.btn_play);
                if (frameLayout != null) {
                    i = R.id.fl_top_card;
                    FrameLayout frameLayout2 = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.fl_top_card);
                    if (frameLayout2 != null) {
                        i = R.id.guideline;
                        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.guideline);
                        if (viewFindChildViewById != null) {
                            i = R.id.guideline_2;
                            View viewFindChildViewById2 = ViewBindings.findChildViewById(rootView, R.id.guideline_2);
                            if (viewFindChildViewById2 != null) {
                                i = R.id.guideline_right_1;
                                View viewFindChildViewById3 = ViewBindings.findChildViewById(rootView, R.id.guideline_right_1);
                                if (viewFindChildViewById3 != null) {
                                    i = R.id.guideline_right_2;
                                    View viewFindChildViewById4 = ViewBindings.findChildViewById(rootView, R.id.guideline_right_2);
                                    if (viewFindChildViewById4 != null) {
                                        i = R.id.im_top_card;
                                        ImageFilterView imageFilterView = (ImageFilterView) ViewBindings.findChildViewById(rootView, R.id.im_top_card);
                                        if (imageFilterView != null) {
                                            i = R.id.iv_collect;
                                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_collect);
                                            if (imageView3 != null) {
                                                i = R.id.iv_play;
                                                ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_play);
                                                if (imageView4 != null) {
                                                    i = R.id.iv_vip;
                                                    ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_vip);
                                                    if (imageView5 != null) {
                                                        i = R.id.ll_action;
                                                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_action);
                                                        if (linearLayout != null) {
                                                            i = R.id.ll_singer;
                                                            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_singer);
                                                            if (linearLayout2 != null) {
                                                                i = R.id.pb_music_play;
                                                                ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(rootView, R.id.pb_music_play);
                                                                if (progressBar != null) {
                                                                    i = R.id.play_list;
                                                                    ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.play_list);
                                                                    if (imageView6 != null) {
                                                                        i = R.id.play_lrc;
                                                                        ImageView imageView7 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.play_lrc);
                                                                        if (imageView7 != null) {
                                                                            i = R.id.play_type;
                                                                            ImageView imageView8 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.play_type);
                                                                            if (imageView8 != null) {
                                                                                i = R.id.right_barrier;
                                                                                Barrier barrier = (Barrier) ViewBindings.findChildViewById(rootView, R.id.right_barrier);
                                                                                if (barrier != null) {
                                                                                    i = R.id.seekbar_music_progress;
                                                                                    WanosSeekBar wanosSeekBar = (WanosSeekBar) ViewBindings.findChildViewById(rootView, R.id.seekbar_music_progress);
                                                                                    if (wanosSeekBar != null) {
                                                                                        i = R.id.title_left_btn;
                                                                                        ImageView imageView9 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.title_left_btn);
                                                                                        if (imageView9 != null) {
                                                                                            i = R.id.tv_curr_time;
                                                                                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_curr_time);
                                                                                            if (textView != null) {
                                                                                                i = R.id.tv_dur_time;
                                                                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_dur_time);
                                                                                                if (textView2 != null) {
                                                                                                    i = R.id.tv_music_name;
                                                                                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_music_name);
                                                                                                    if (textView3 != null) {
                                                                                                        i = R.id.tv_singer_name;
                                                                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_singer_name);
                                                                                                        if (textView4 != null) {
                                                                                                            return new LayoutMusicPlayDetailsBinding((ConstraintLayout) rootView, imageView, imageView2, frameLayout, frameLayout2, viewFindChildViewById, viewFindChildViewById2, viewFindChildViewById3, viewFindChildViewById4, imageFilterView, imageView3, imageView4, imageView5, linearLayout, linearLayout2, progressBar, imageView6, imageView7, imageView8, barrier, wanosSeekBar, imageView9, textView, textView2, textView3, textView4);
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
