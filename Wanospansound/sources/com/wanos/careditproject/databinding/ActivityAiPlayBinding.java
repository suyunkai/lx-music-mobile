package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.airbnb.lottie.LottieAnimationView;
import com.wanos.careditproject.R;
import com.wanos.careditproject.view.widget.AiButtonView;
import com.wanos.careditproject.view.widget.AiStateTextView;
import com.wanos.media.ui.widget.WanosTextView;

/* JADX INFO: loaded from: classes3.dex */
public final class ActivityAiPlayBinding implements ViewBinding {
    public final AiStateTextView aiStateView;
    public final AppCompatImageView btnBack;
    public final AiButtonView btnImplStudio;
    public final WanosTextView btnRecreate;
    public final AiButtonView btnSendCommunity;
    public final Guideline guideCove;
    public final LottieAnimationView lottieCreate;
    public final LottieAnimationView lottiePlay;
    private final ConstraintLayout rootView;
    public final RecyclerView rvPlayerBtn;
    public final AppCompatSeekBar seekBar;
    public final WanosTextView tvEndTime;
    public final WanosTextView tvStartTime;
    public final WanosTextView tvTitle;

    private ActivityAiPlayBinding(ConstraintLayout constraintLayout, AiStateTextView aiStateTextView, AppCompatImageView appCompatImageView, AiButtonView aiButtonView, WanosTextView wanosTextView, AiButtonView aiButtonView2, Guideline guideline, LottieAnimationView lottieAnimationView, LottieAnimationView lottieAnimationView2, RecyclerView recyclerView, AppCompatSeekBar appCompatSeekBar, WanosTextView wanosTextView2, WanosTextView wanosTextView3, WanosTextView wanosTextView4) {
        this.rootView = constraintLayout;
        this.aiStateView = aiStateTextView;
        this.btnBack = appCompatImageView;
        this.btnImplStudio = aiButtonView;
        this.btnRecreate = wanosTextView;
        this.btnSendCommunity = aiButtonView2;
        this.guideCove = guideline;
        this.lottieCreate = lottieAnimationView;
        this.lottiePlay = lottieAnimationView2;
        this.rvPlayerBtn = recyclerView;
        this.seekBar = appCompatSeekBar;
        this.tvEndTime = wanosTextView2;
        this.tvStartTime = wanosTextView3;
        this.tvTitle = wanosTextView4;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityAiPlayBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityAiPlayBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.activity_ai_play, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityAiPlayBinding bind(View view) {
        int i = R.id.ai_state_view;
        AiStateTextView aiStateTextView = (AiStateTextView) ViewBindings.findChildViewById(view, i);
        if (aiStateTextView != null) {
            i = R.id.btn_back;
            AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
            if (appCompatImageView != null) {
                i = R.id.btn_impl_studio;
                AiButtonView aiButtonView = (AiButtonView) ViewBindings.findChildViewById(view, i);
                if (aiButtonView != null) {
                    i = R.id.btn_recreate;
                    WanosTextView wanosTextView = (WanosTextView) ViewBindings.findChildViewById(view, i);
                    if (wanosTextView != null) {
                        i = R.id.btn_send_community;
                        AiButtonView aiButtonView2 = (AiButtonView) ViewBindings.findChildViewById(view, i);
                        if (aiButtonView2 != null) {
                            i = R.id.guide_cove;
                            Guideline guideline = (Guideline) ViewBindings.findChildViewById(view, i);
                            if (guideline != null) {
                                i = R.id.lottie_create;
                                LottieAnimationView lottieAnimationView = (LottieAnimationView) ViewBindings.findChildViewById(view, i);
                                if (lottieAnimationView != null) {
                                    i = R.id.lottie_play;
                                    LottieAnimationView lottieAnimationView2 = (LottieAnimationView) ViewBindings.findChildViewById(view, i);
                                    if (lottieAnimationView2 != null) {
                                        i = R.id.rv_player_btn;
                                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                                        if (recyclerView != null) {
                                            i = R.id.seek_bar;
                                            AppCompatSeekBar appCompatSeekBar = (AppCompatSeekBar) ViewBindings.findChildViewById(view, i);
                                            if (appCompatSeekBar != null) {
                                                i = R.id.tv_end_time;
                                                WanosTextView wanosTextView2 = (WanosTextView) ViewBindings.findChildViewById(view, i);
                                                if (wanosTextView2 != null) {
                                                    i = R.id.tv_start_time;
                                                    WanosTextView wanosTextView3 = (WanosTextView) ViewBindings.findChildViewById(view, i);
                                                    if (wanosTextView3 != null) {
                                                        i = R.id.tv_title;
                                                        WanosTextView wanosTextView4 = (WanosTextView) ViewBindings.findChildViewById(view, i);
                                                        if (wanosTextView4 != null) {
                                                            return new ActivityAiPlayBinding((ConstraintLayout) view, aiStateTextView, appCompatImageView, aiButtonView, wanosTextView, aiButtonView2, guideline, lottieAnimationView, lottieAnimationView2, recyclerView, appCompatSeekBar, wanosTextView2, wanosTextView3, wanosTextView4);
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
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
