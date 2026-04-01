package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class IncludeLoginDialogAgreementBinding implements ViewBinding {
    public final ImageView ivSelect;
    public final LinearLayout llConfirm;
    private final LinearLayout rootView;
    public final TextView tvChildPrivacyProtocol;
    public final TextView tvPrivacyProtocol;
    public final TextView tvServiceProtocol;

    private IncludeLoginDialogAgreementBinding(LinearLayout rootView, ImageView ivSelect, LinearLayout llConfirm, TextView tvChildPrivacyProtocol, TextView tvPrivacyProtocol, TextView tvServiceProtocol) {
        this.rootView = rootView;
        this.ivSelect = ivSelect;
        this.llConfirm = llConfirm;
        this.tvChildPrivacyProtocol = tvChildPrivacyProtocol;
        this.tvPrivacyProtocol = tvPrivacyProtocol;
        this.tvServiceProtocol = tvServiceProtocol;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static IncludeLoginDialogAgreementBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static IncludeLoginDialogAgreementBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.include_login_dialog_agreement, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static IncludeLoginDialogAgreementBinding bind(View rootView) {
        int i = R.id.iv_select;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_select);
        if (imageView != null) {
            i = R.id.ll_confirm;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_confirm);
            if (linearLayout != null) {
                i = R.id.tv_child_privacy_protocol;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_child_privacy_protocol);
                if (textView != null) {
                    i = R.id.tv_privacy_protocol;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_privacy_protocol);
                    if (textView2 != null) {
                        i = R.id.tv_service_protocol;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_service_protocol);
                        if (textView3 != null) {
                            return new IncludeLoginDialogAgreementBinding((LinearLayout) rootView, imageView, linearLayout, textView, textView2, textView3);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
