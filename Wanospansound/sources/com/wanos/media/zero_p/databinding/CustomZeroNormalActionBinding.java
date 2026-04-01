package com.wanos.media.zero_p.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.widget.RelaxInfoActionButton;
import com.wanos.media.widget.WanosClockView;
import com.wanos.media.widget.WanosTextView;
import com.wanos.media.widget.controller.WanosZeroControlTimeSelectView;
import com.wanos.media.zero_p.R;

/* JADX INFO: loaded from: classes3.dex */
public final class CustomZeroNormalActionBinding implements ViewBinding {
    public final Guideline baselineClock;
    public final RelaxInfoActionButton btnAction;
    public final AppCompatImageView btnControlBack;
    public final LinearLayoutCompat btnControlPersonalize;
    public final LinearLayoutCompat btnOpenVip;
    public final AppCompatImageView btnSetting;
    public final WanosClockView clockView;
    public final AppCompatImageView ivVipFlag;
    private final ConstraintLayout rootView;
    public final AppCompatTextView tvControlTitle;
    public final WanosTextView tvCountDownTime;
    public final View vMask;
    public final WanosZeroControlTimeSelectView wanosZeroTimeSelect;

    private CustomZeroNormalActionBinding(ConstraintLayout constraintLayout, Guideline guideline, RelaxInfoActionButton relaxInfoActionButton, AppCompatImageView appCompatImageView, LinearLayoutCompat linearLayoutCompat, LinearLayoutCompat linearLayoutCompat2, AppCompatImageView appCompatImageView2, WanosClockView wanosClockView, AppCompatImageView appCompatImageView3, AppCompatTextView appCompatTextView, WanosTextView wanosTextView, View view, WanosZeroControlTimeSelectView wanosZeroControlTimeSelectView) {
        this.rootView = constraintLayout;
        this.baselineClock = guideline;
        this.btnAction = relaxInfoActionButton;
        this.btnControlBack = appCompatImageView;
        this.btnControlPersonalize = linearLayoutCompat;
        this.btnOpenVip = linearLayoutCompat2;
        this.btnSetting = appCompatImageView2;
        this.clockView = wanosClockView;
        this.ivVipFlag = appCompatImageView3;
        this.tvControlTitle = appCompatTextView;
        this.tvCountDownTime = wanosTextView;
        this.vMask = view;
        this.wanosZeroTimeSelect = wanosZeroControlTimeSelectView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static CustomZeroNormalActionBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static CustomZeroNormalActionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.custom_zero_normal_action, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static CustomZeroNormalActionBinding bind(View view) {
        View viewFindChildViewById;
        int i = R.id.baseline_clock;
        Guideline guideline = (Guideline) ViewBindings.findChildViewById(view, i);
        if (guideline != null) {
            i = R.id.btn_action;
            RelaxInfoActionButton relaxInfoActionButton = (RelaxInfoActionButton) ViewBindings.findChildViewById(view, i);
            if (relaxInfoActionButton != null) {
                i = R.id.btn_control_back;
                AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                if (appCompatImageView != null) {
                    i = R.id.btn_control_personalize;
                    LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, i);
                    if (linearLayoutCompat != null) {
                        i = R.id.btn_open_vip;
                        LinearLayoutCompat linearLayoutCompat2 = (LinearLayoutCompat) ViewBindings.findChildViewById(view, i);
                        if (linearLayoutCompat2 != null) {
                            i = R.id.btn_setting;
                            AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                            if (appCompatImageView2 != null) {
                                i = R.id.clock_view;
                                WanosClockView wanosClockView = (WanosClockView) ViewBindings.findChildViewById(view, i);
                                if (wanosClockView != null) {
                                    i = R.id.iv_vip_flag;
                                    AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                                    if (appCompatImageView3 != null) {
                                        i = R.id.tv_control_title;
                                        AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
                                        if (appCompatTextView != null) {
                                            i = R.id.tv_count_down_time;
                                            WanosTextView wanosTextView = (WanosTextView) ViewBindings.findChildViewById(view, i);
                                            if (wanosTextView != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.v_mask))) != null) {
                                                i = R.id.wanos_zero_time_select;
                                                WanosZeroControlTimeSelectView wanosZeroControlTimeSelectView = (WanosZeroControlTimeSelectView) ViewBindings.findChildViewById(view, i);
                                                if (wanosZeroControlTimeSelectView != null) {
                                                    return new CustomZeroNormalActionBinding((ConstraintLayout) view, guideline, relaxInfoActionButton, appCompatImageView, linearLayoutCompat, linearLayoutCompat2, appCompatImageView2, wanosClockView, appCompatImageView3, appCompatTextView, wanosTextView, viewFindChildViewById, wanosZeroControlTimeSelectView);
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
