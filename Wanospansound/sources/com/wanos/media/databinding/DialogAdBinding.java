package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class DialogAdBinding implements ViewBinding {
    public final ConstraintLayout clDialogLayout;
    public final ImageView ivCancel;
    public final ImageView ivConfirm;
    private final ConstraintLayout rootView;

    private DialogAdBinding(ConstraintLayout rootView, ConstraintLayout clDialogLayout, ImageView ivCancel, ImageView ivConfirm) {
        this.rootView = rootView;
        this.clDialogLayout = clDialogLayout;
        this.ivCancel = ivCancel;
        this.ivConfirm = ivConfirm;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DialogAdBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogAdBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.dialog_ad, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogAdBinding bind(View rootView) {
        ConstraintLayout constraintLayout = (ConstraintLayout) rootView;
        int i = R.id.iv_cancel;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_cancel);
        if (imageView != null) {
            i = R.id.iv_confirm;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_confirm);
            if (imageView2 != null) {
                return new DialogAdBinding(constraintLayout, constraintLayout, imageView, imageView2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
