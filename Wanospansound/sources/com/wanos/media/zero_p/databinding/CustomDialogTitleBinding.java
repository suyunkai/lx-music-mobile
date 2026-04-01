package com.wanos.media.zero_p.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.widget.WanosTextView;
import com.wanos.media.zero_p.R;

/* JADX INFO: loaded from: classes3.dex */
public final class CustomDialogTitleBinding implements ViewBinding {
    public final AppCompatImageView btnBack;
    public final WanosTextView ivTitle;
    private final FrameLayout rootView;

    private CustomDialogTitleBinding(FrameLayout frameLayout, AppCompatImageView appCompatImageView, WanosTextView wanosTextView) {
        this.rootView = frameLayout;
        this.btnBack = appCompatImageView;
        this.ivTitle = wanosTextView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static CustomDialogTitleBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static CustomDialogTitleBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.custom_dialog_title, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static CustomDialogTitleBinding bind(View view) {
        int i = R.id.btn_back;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = R.id.iv_title;
            WanosTextView wanosTextView = (WanosTextView) ViewBindings.findChildViewById(view, i);
            if (wanosTextView != null) {
                return new CustomDialogTitleBinding((FrameLayout) view, appCompatImageView, wanosTextView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
