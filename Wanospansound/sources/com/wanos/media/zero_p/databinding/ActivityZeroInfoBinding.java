package com.wanos.media.zero_p.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.widget.controller.WanosZeroControlView;
import com.wanos.media.zero_p.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ActivityZeroInfoBinding implements ViewBinding {
    public final WanosZeroControlView controlView;
    public final FragmentContainerView flModel;
    public final AppCompatImageView ivBgImage;
    private final FrameLayout rootView;

    private ActivityZeroInfoBinding(FrameLayout frameLayout, WanosZeroControlView wanosZeroControlView, FragmentContainerView fragmentContainerView, AppCompatImageView appCompatImageView) {
        this.rootView = frameLayout;
        this.controlView = wanosZeroControlView;
        this.flModel = fragmentContainerView;
        this.ivBgImage = appCompatImageView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static ActivityZeroInfoBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityZeroInfoBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.activity_zero_info, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityZeroInfoBinding bind(View view) {
        int i = R.id.control_view;
        WanosZeroControlView wanosZeroControlView = (WanosZeroControlView) ViewBindings.findChildViewById(view, i);
        if (wanosZeroControlView != null) {
            i = R.id.fl_model;
            FragmentContainerView fragmentContainerView = (FragmentContainerView) ViewBindings.findChildViewById(view, i);
            if (fragmentContainerView != null) {
                i = R.id.iv_bg_image;
                AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                if (appCompatImageView != null) {
                    return new ActivityZeroInfoBinding((FrameLayout) view, wanosZeroControlView, fragmentContainerView, appCompatImageView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
