package com.wanos.media.zero_p.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.widget.WanosTextView;
import com.wanos.media.zero_p.R;

/* JADX INFO: loaded from: classes3.dex */
public final class WidgetRelaxInfoActionButtonBinding implements ViewBinding {
    public final AppCompatImageView ivIcon;
    public final FrameLayout layoutLoading;
    public final LinearLayoutCompat layoutNormal;
    public final FrameLayout layoutTrial;
    public final ProgressBar progressTrial;
    private final FrameLayout rootView;
    public final WanosTextView tvTitle;

    private WidgetRelaxInfoActionButtonBinding(FrameLayout frameLayout, AppCompatImageView appCompatImageView, FrameLayout frameLayout2, LinearLayoutCompat linearLayoutCompat, FrameLayout frameLayout3, ProgressBar progressBar, WanosTextView wanosTextView) {
        this.rootView = frameLayout;
        this.ivIcon = appCompatImageView;
        this.layoutLoading = frameLayout2;
        this.layoutNormal = linearLayoutCompat;
        this.layoutTrial = frameLayout3;
        this.progressTrial = progressBar;
        this.tvTitle = wanosTextView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static WidgetRelaxInfoActionButtonBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static WidgetRelaxInfoActionButtonBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.widget_relax_info_action_button, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static WidgetRelaxInfoActionButtonBinding bind(View view) {
        int i = R.id.iv_icon;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = R.id.layout_loading;
            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
            if (frameLayout != null) {
                i = R.id.layout_normal;
                LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, i);
                if (linearLayoutCompat != null) {
                    i = R.id.layout_trial;
                    FrameLayout frameLayout2 = (FrameLayout) ViewBindings.findChildViewById(view, i);
                    if (frameLayout2 != null) {
                        i = R.id.progress_trial;
                        ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, i);
                        if (progressBar != null) {
                            i = R.id.tv_title;
                            WanosTextView wanosTextView = (WanosTextView) ViewBindings.findChildViewById(view, i);
                            if (wanosTextView != null) {
                                return new WidgetRelaxInfoActionButtonBinding((FrameLayout) view, appCompatImageView, frameLayout, linearLayoutCompat, frameLayout2, progressBar, wanosTextView);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
