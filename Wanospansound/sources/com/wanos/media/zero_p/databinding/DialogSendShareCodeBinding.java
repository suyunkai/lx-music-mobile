package com.wanos.media.zero_p.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.widget.CustomDialogTitle;
import com.wanos.media.widget.WanosTextView;
import com.wanos.media.widget.WanosVerificationView;
import com.wanos.media.zero_p.R;

/* JADX INFO: loaded from: classes3.dex */
public final class DialogSendShareCodeBinding implements ViewBinding {
    public final WanosTextView btnCancel;
    public final WanosTextView btnOk;
    public final LinearLayoutCompat errorView;
    public final LinearLayoutCompat loadingView;
    private final ConstraintLayout rootView;
    public final CustomDialogTitle title;
    public final WanosTextView tvSendHint;
    public final WanosVerificationView wanosVerification;

    private DialogSendShareCodeBinding(ConstraintLayout constraintLayout, WanosTextView wanosTextView, WanosTextView wanosTextView2, LinearLayoutCompat linearLayoutCompat, LinearLayoutCompat linearLayoutCompat2, CustomDialogTitle customDialogTitle, WanosTextView wanosTextView3, WanosVerificationView wanosVerificationView) {
        this.rootView = constraintLayout;
        this.btnCancel = wanosTextView;
        this.btnOk = wanosTextView2;
        this.errorView = linearLayoutCompat;
        this.loadingView = linearLayoutCompat2;
        this.title = customDialogTitle;
        this.tvSendHint = wanosTextView3;
        this.wanosVerification = wanosVerificationView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DialogSendShareCodeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogSendShareCodeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.dialog_send_share_code, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogSendShareCodeBinding bind(View view) {
        int i = R.id.btn_cancel;
        WanosTextView wanosTextView = (WanosTextView) ViewBindings.findChildViewById(view, i);
        if (wanosTextView != null) {
            i = R.id.btn_ok;
            WanosTextView wanosTextView2 = (WanosTextView) ViewBindings.findChildViewById(view, i);
            if (wanosTextView2 != null) {
                i = R.id.error_view;
                LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, i);
                if (linearLayoutCompat != null) {
                    i = R.id.loading_view;
                    LinearLayoutCompat linearLayoutCompat2 = (LinearLayoutCompat) ViewBindings.findChildViewById(view, i);
                    if (linearLayoutCompat2 != null) {
                        i = R.id.title;
                        CustomDialogTitle customDialogTitle = (CustomDialogTitle) ViewBindings.findChildViewById(view, i);
                        if (customDialogTitle != null) {
                            i = R.id.tv_send_hint;
                            WanosTextView wanosTextView3 = (WanosTextView) ViewBindings.findChildViewById(view, i);
                            if (wanosTextView3 != null) {
                                i = R.id.wanos_verification;
                                WanosVerificationView wanosVerificationView = (WanosVerificationView) ViewBindings.findChildViewById(view, i);
                                if (wanosVerificationView != null) {
                                    return new DialogSendShareCodeBinding((ConstraintLayout) view, wanosTextView, wanosTextView2, linearLayoutCompat, linearLayoutCompat2, customDialogTitle, wanosTextView3, wanosVerificationView);
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
