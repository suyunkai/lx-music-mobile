package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class DialogFeedbackBinding implements ViewBinding {
    public final ImageView btnBack;
    public final ImageView ivQr;
    public final CardView layoutQr;
    private final ConstraintLayout rootView;

    private DialogFeedbackBinding(ConstraintLayout rootView, ImageView btnBack, ImageView ivQr, CardView layoutQr) {
        this.rootView = rootView;
        this.btnBack = btnBack;
        this.ivQr = ivQr;
        this.layoutQr = layoutQr;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DialogFeedbackBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogFeedbackBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.dialog_feedback, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogFeedbackBinding bind(View rootView) {
        int i = R.id.btn_back;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.btn_back);
        if (imageView != null) {
            i = R.id.iv_qr;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_qr);
            if (imageView2 != null) {
                i = R.id.layout_qr;
                CardView cardView = (CardView) ViewBindings.findChildViewById(rootView, R.id.layout_qr);
                if (cardView != null) {
                    return new DialogFeedbackBinding((ConstraintLayout) rootView, imageView, imageView2, cardView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
