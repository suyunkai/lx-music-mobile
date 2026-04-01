package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;
import com.wanos.media.ui.widget.WanosTextView;

/* JADX INFO: loaded from: classes3.dex */
public final class DialogAiExitBinding implements ViewBinding {
    public final AppCompatTextView btnActive;
    public final AppCompatImageView btnBack;
    public final AppCompatTextView btnCancel;
    private final ConstraintLayout rootView;
    public final WanosTextView tvMsg;

    private DialogAiExitBinding(ConstraintLayout constraintLayout, AppCompatTextView appCompatTextView, AppCompatImageView appCompatImageView, AppCompatTextView appCompatTextView2, WanosTextView wanosTextView) {
        this.rootView = constraintLayout;
        this.btnActive = appCompatTextView;
        this.btnBack = appCompatImageView;
        this.btnCancel = appCompatTextView2;
        this.tvMsg = wanosTextView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DialogAiExitBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogAiExitBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.dialog_ai_exit, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogAiExitBinding bind(View view) {
        int i = R.id.btn_active;
        AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
        if (appCompatTextView != null) {
            i = R.id.btn_back;
            AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
            if (appCompatImageView != null) {
                i = R.id.btn_cancel;
                AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
                if (appCompatTextView2 != null) {
                    i = R.id.tv_msg;
                    WanosTextView wanosTextView = (WanosTextView) ViewBindings.findChildViewById(view, i);
                    if (wanosTextView != null) {
                        return new DialogAiExitBinding((ConstraintLayout) view, appCompatTextView, appCompatImageView, appCompatTextView2, wanosTextView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
