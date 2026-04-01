package com.wanos.media.zero_p.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.zero_p.R;

/* JADX INFO: loaded from: classes3.dex */
public final class DialogVolumeBinding implements ViewBinding {
    public final AppCompatImageView btnFinish;
    public final ConstraintLayout dialogContent;
    public final AppCompatImageView ivBg;
    private final LinearLayoutCompat rootView;
    public final RecyclerView rvContent;
    public final View vBlurColor;

    private DialogVolumeBinding(LinearLayoutCompat linearLayoutCompat, AppCompatImageView appCompatImageView, ConstraintLayout constraintLayout, AppCompatImageView appCompatImageView2, RecyclerView recyclerView, View view) {
        this.rootView = linearLayoutCompat;
        this.btnFinish = appCompatImageView;
        this.dialogContent = constraintLayout;
        this.ivBg = appCompatImageView2;
        this.rvContent = recyclerView;
        this.vBlurColor = view;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayoutCompat getRoot() {
        return this.rootView;
    }

    public static DialogVolumeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogVolumeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.dialog_volume, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogVolumeBinding bind(View view) {
        View viewFindChildViewById;
        int i = R.id.btn_finish;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = R.id.dialog_content;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
            if (constraintLayout != null) {
                i = R.id.iv_bg;
                AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                if (appCompatImageView2 != null) {
                    i = R.id.rv_content;
                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                    if (recyclerView != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.v_blur_color))) != null) {
                        return new DialogVolumeBinding((LinearLayoutCompat) view, appCompatImageView, constraintLayout, appCompatImageView2, recyclerView, viewFindChildViewById);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
