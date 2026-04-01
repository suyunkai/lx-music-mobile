package com.wanos.commonlibrary.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.commonlibrary.R;

/* JADX INFO: loaded from: classes3.dex */
public final class LoadingBinding implements ViewBinding {
    public final ImageView ivLoading;
    public final ProgressBar pbLoading;
    public final ConstraintLayout rootLoading;
    private final ConstraintLayout rootView;
    public final TextView tvLoadingMessage;

    private LoadingBinding(ConstraintLayout constraintLayout, ImageView imageView, ProgressBar progressBar, ConstraintLayout constraintLayout2, TextView textView) {
        this.rootView = constraintLayout;
        this.ivLoading = imageView;
        this.pbLoading = progressBar;
        this.rootLoading = constraintLayout2;
        this.tvLoadingMessage = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LoadingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LoadingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.loading, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LoadingBinding bind(View view) {
        int i = R.id.iv_loading;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.pb_loading;
            ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, i);
            if (progressBar != null) {
                ConstraintLayout constraintLayout = (ConstraintLayout) view;
                i = R.id.tv_loadingMessage;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView != null) {
                    return new LoadingBinding(constraintLayout, imageView, progressBar, constraintLayout, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
