package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ErrorLoginQrCodeBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final TextView tvErrorMessage;

    private ErrorLoginQrCodeBinding(ConstraintLayout rootView, TextView tvErrorMessage) {
        this.rootView = rootView;
        this.tvErrorMessage = tvErrorMessage;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ErrorLoginQrCodeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ErrorLoginQrCodeBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.error_login_qr_code, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ErrorLoginQrCodeBinding bind(View rootView) {
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_error_message);
        if (textView != null) {
            return new ErrorLoginQrCodeBinding((ConstraintLayout) rootView, textView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.tv_error_message)));
    }
}
