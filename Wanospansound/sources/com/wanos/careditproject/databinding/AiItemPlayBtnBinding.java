package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;
import com.wanos.media.ui.widget.WanosTextView;

/* JADX INFO: loaded from: classes3.dex */
public final class AiItemPlayBtnBinding implements ViewBinding {
    public final AppCompatImageView ivPlay;
    public final ProgressBar progressPlay;
    private final FrameLayout rootView;
    public final WanosTextView tvPlay;

    private AiItemPlayBtnBinding(FrameLayout frameLayout, AppCompatImageView appCompatImageView, ProgressBar progressBar, WanosTextView wanosTextView) {
        this.rootView = frameLayout;
        this.ivPlay = appCompatImageView;
        this.progressPlay = progressBar;
        this.tvPlay = wanosTextView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static AiItemPlayBtnBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AiItemPlayBtnBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.ai_item_play_btn, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static AiItemPlayBtnBinding bind(View view) {
        int i = R.id.iv_play;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = R.id.progress_play;
            ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, i);
            if (progressBar != null) {
                i = R.id.tv_play;
                WanosTextView wanosTextView = (WanosTextView) ViewBindings.findChildViewById(view, i);
                if (wanosTextView != null) {
                    return new AiItemPlayBtnBinding((FrameLayout) view, appCompatImageView, progressBar, wanosTextView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
