package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class LoadingQrCodeBinding implements ViewBinding {
    public final ImageView qrLoadingIm;
    public final ConstraintLayout qrLoadingLl;
    public final TextView qrLoadingTv;
    private final ConstraintLayout rootView;

    private LoadingQrCodeBinding(ConstraintLayout rootView, ImageView qrLoadingIm, ConstraintLayout qrLoadingLl, TextView qrLoadingTv) {
        this.rootView = rootView;
        this.qrLoadingIm = qrLoadingIm;
        this.qrLoadingLl = qrLoadingLl;
        this.qrLoadingTv = qrLoadingTv;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LoadingQrCodeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LoadingQrCodeBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.loading_qr_code, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LoadingQrCodeBinding bind(View rootView) {
        int i = R.id.qr_loading_im;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.qr_loading_im);
        if (imageView != null) {
            ConstraintLayout constraintLayout = (ConstraintLayout) rootView;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.qr_loading_tv);
            if (textView != null) {
                return new LoadingQrCodeBinding(constraintLayout, imageView, constraintLayout, textView);
            }
            i = R.id.qr_loading_tv;
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
