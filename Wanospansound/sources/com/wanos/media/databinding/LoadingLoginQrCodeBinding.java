package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class LoadingLoginQrCodeBinding implements ViewBinding {
    public final ProgressBar pbLoading;
    private final ConstraintLayout rootView;

    private LoadingLoginQrCodeBinding(ConstraintLayout rootView, ProgressBar pbLoading) {
        this.rootView = rootView;
        this.pbLoading = pbLoading;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LoadingLoginQrCodeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LoadingLoginQrCodeBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.loading_login_qr_code, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LoadingLoginQrCodeBinding bind(View rootView) {
        ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(rootView, R.id.pb_loading);
        if (progressBar != null) {
            return new LoadingLoginQrCodeBinding((ConstraintLayout) rootView, progressBar);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.pb_loading)));
    }
}
