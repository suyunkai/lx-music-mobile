package com.wanos.media.zero_p.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.widget.CustomEditActionView;
import com.wanos.media.widget.ball.BallSurfaceView;
import com.wanos.media.zero_p.R;

/* JADX INFO: loaded from: classes3.dex */
public final class FragmentZeroProBinding implements ViewBinding {
    public final BallSurfaceView ballView;
    public final CustomEditActionView btnAdd;
    public final CustomEditActionView btnCollect;
    public final CustomEditActionView btnHelp;
    public final CustomEditActionView btnReset;
    public final CustomEditActionView btnVolume;
    public final Guideline guideLine;
    public final AppCompatImageView ivBackgroundImg;
    public final LinearLayoutCompat llActionRoom;
    private final ConstraintLayout rootView;

    private FragmentZeroProBinding(ConstraintLayout constraintLayout, BallSurfaceView ballSurfaceView, CustomEditActionView customEditActionView, CustomEditActionView customEditActionView2, CustomEditActionView customEditActionView3, CustomEditActionView customEditActionView4, CustomEditActionView customEditActionView5, Guideline guideline, AppCompatImageView appCompatImageView, LinearLayoutCompat linearLayoutCompat) {
        this.rootView = constraintLayout;
        this.ballView = ballSurfaceView;
        this.btnAdd = customEditActionView;
        this.btnCollect = customEditActionView2;
        this.btnHelp = customEditActionView3;
        this.btnReset = customEditActionView4;
        this.btnVolume = customEditActionView5;
        this.guideLine = guideline;
        this.ivBackgroundImg = appCompatImageView;
        this.llActionRoom = linearLayoutCompat;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentZeroProBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentZeroProBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_zero_pro, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentZeroProBinding bind(View view) {
        int i = R.id.ball_view;
        BallSurfaceView ballSurfaceView = (BallSurfaceView) ViewBindings.findChildViewById(view, i);
        if (ballSurfaceView != null) {
            i = R.id.btn_add;
            CustomEditActionView customEditActionView = (CustomEditActionView) ViewBindings.findChildViewById(view, i);
            if (customEditActionView != null) {
                i = R.id.btn_collect;
                CustomEditActionView customEditActionView2 = (CustomEditActionView) ViewBindings.findChildViewById(view, i);
                if (customEditActionView2 != null) {
                    i = R.id.btn_help;
                    CustomEditActionView customEditActionView3 = (CustomEditActionView) ViewBindings.findChildViewById(view, i);
                    if (customEditActionView3 != null) {
                        i = R.id.btn_reset;
                        CustomEditActionView customEditActionView4 = (CustomEditActionView) ViewBindings.findChildViewById(view, i);
                        if (customEditActionView4 != null) {
                            i = R.id.btn_volume;
                            CustomEditActionView customEditActionView5 = (CustomEditActionView) ViewBindings.findChildViewById(view, i);
                            if (customEditActionView5 != null) {
                                i = R.id.guide_line;
                                Guideline guideline = (Guideline) ViewBindings.findChildViewById(view, i);
                                if (guideline != null) {
                                    i = R.id.iv_background_img;
                                    AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                    if (appCompatImageView != null) {
                                        i = R.id.ll_action_room;
                                        LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, i);
                                        if (linearLayoutCompat != null) {
                                            return new FragmentZeroProBinding((ConstraintLayout) view, ballSurfaceView, customEditActionView, customEditActionView2, customEditActionView3, customEditActionView4, customEditActionView5, guideline, appCompatImageView, linearLayoutCompat);
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
