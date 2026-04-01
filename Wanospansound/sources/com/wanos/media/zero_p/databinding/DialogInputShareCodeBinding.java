package com.wanos.media.zero_p.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.widget.CustomDialogTitle;
import com.wanos.media.widget.WanosTextView;
import com.wanos.media.widget.WanosVerificationView;
import com.wanos.media.zero_p.R;

/* JADX INFO: loaded from: classes3.dex */
public final class DialogInputShareCodeBinding implements ViewBinding {
    public final WanosTextView btnCancel;
    public final WanosTextView btnOk;
    private final ConstraintLayout rootView;
    public final CustomDialogTitle title;
    public final WanosVerificationView wanosVerification;

    private DialogInputShareCodeBinding(ConstraintLayout constraintLayout, WanosTextView wanosTextView, WanosTextView wanosTextView2, CustomDialogTitle customDialogTitle, WanosVerificationView wanosVerificationView) {
        this.rootView = constraintLayout;
        this.btnCancel = wanosTextView;
        this.btnOk = wanosTextView2;
        this.title = customDialogTitle;
        this.wanosVerification = wanosVerificationView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DialogInputShareCodeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogInputShareCodeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.dialog_input_share_code, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogInputShareCodeBinding bind(View view) {
        int i = R.id.btn_cancel;
        WanosTextView wanosTextView = (WanosTextView) ViewBindings.findChildViewById(view, i);
        if (wanosTextView != null) {
            i = R.id.btn_ok;
            WanosTextView wanosTextView2 = (WanosTextView) ViewBindings.findChildViewById(view, i);
            if (wanosTextView2 != null) {
                i = R.id.title;
                CustomDialogTitle customDialogTitle = (CustomDialogTitle) ViewBindings.findChildViewById(view, i);
                if (customDialogTitle != null) {
                    i = R.id.wanos_verification;
                    WanosVerificationView wanosVerificationView = (WanosVerificationView) ViewBindings.findChildViewById(view, i);
                    if (wanosVerificationView != null) {
                        return new DialogInputShareCodeBinding((ConstraintLayout) view, wanosTextView, wanosTextView2, customDialogTitle, wanosVerificationView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
