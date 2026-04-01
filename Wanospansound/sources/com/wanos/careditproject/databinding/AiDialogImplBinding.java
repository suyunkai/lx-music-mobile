package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;
import com.wanos.media.ui.widget.WanosTextView;

/* JADX INFO: loaded from: classes3.dex */
public final class AiDialogImplBinding implements ViewBinding {
    public final WanosTextView btnCancel;
    public final AppCompatImageView ivLogo;
    private final FrameLayout rootView;
    public final WanosTextView tvLoading;

    private AiDialogImplBinding(FrameLayout frameLayout, WanosTextView wanosTextView, AppCompatImageView appCompatImageView, WanosTextView wanosTextView2) {
        this.rootView = frameLayout;
        this.btnCancel = wanosTextView;
        this.ivLogo = appCompatImageView;
        this.tvLoading = wanosTextView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static AiDialogImplBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AiDialogImplBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.ai_dialog_impl, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static AiDialogImplBinding bind(View view) {
        int i = R.id.btn_cancel;
        WanosTextView wanosTextView = (WanosTextView) ViewBindings.findChildViewById(view, i);
        if (wanosTextView != null) {
            i = R.id.iv_logo;
            AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
            if (appCompatImageView != null) {
                i = R.id.tv_loading;
                WanosTextView wanosTextView2 = (WanosTextView) ViewBindings.findChildViewById(view, i);
                if (wanosTextView2 != null) {
                    return new AiDialogImplBinding((FrameLayout) view, wanosTextView, appCompatImageView, wanosTextView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
