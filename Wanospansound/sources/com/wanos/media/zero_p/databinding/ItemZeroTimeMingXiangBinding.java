package com.wanos.media.zero_p.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.ui.widget.WanosTextView;
import com.wanos.media.zero_p.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ItemZeroTimeMingXiangBinding implements ViewBinding {
    public final AppCompatImageView ivBackgroundImg;
    public final ProgressBar loadingBar;
    private final FrameLayout rootView;
    public final WanosTextView tvTimeSize;
    public final WanosTextView tvTimeTitle;

    private ItemZeroTimeMingXiangBinding(FrameLayout frameLayout, AppCompatImageView appCompatImageView, ProgressBar progressBar, WanosTextView wanosTextView, WanosTextView wanosTextView2) {
        this.rootView = frameLayout;
        this.ivBackgroundImg = appCompatImageView;
        this.loadingBar = progressBar;
        this.tvTimeSize = wanosTextView;
        this.tvTimeTitle = wanosTextView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static ItemZeroTimeMingXiangBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemZeroTimeMingXiangBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.item_zero_time_ming_xiang, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemZeroTimeMingXiangBinding bind(View view) {
        int i = R.id.iv_background_img;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = R.id.loading_bar;
            ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, i);
            if (progressBar != null) {
                i = R.id.tv_time_size;
                WanosTextView wanosTextView = (WanosTextView) ViewBindings.findChildViewById(view, i);
                if (wanosTextView != null) {
                    i = R.id.tv_time_title;
                    WanosTextView wanosTextView2 = (WanosTextView) ViewBindings.findChildViewById(view, i);
                    if (wanosTextView2 != null) {
                        return new ItemZeroTimeMingXiangBinding((FrameLayout) view, appCompatImageView, progressBar, wanosTextView, wanosTextView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
