package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class DialogLogoutBinding implements ViewBinding {
    private final FrameLayout rootView;
    public final TextView tvLogoutCancel;
    public final TextView tvLogoutConfirm;
    public final TextView tvTitle;

    private DialogLogoutBinding(FrameLayout rootView, TextView tvLogoutCancel, TextView tvLogoutConfirm, TextView tvTitle) {
        this.rootView = rootView;
        this.tvLogoutCancel = tvLogoutCancel;
        this.tvLogoutConfirm = tvLogoutConfirm;
        this.tvTitle = tvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static DialogLogoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogLogoutBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.dialog_logout, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogLogoutBinding bind(View rootView) {
        int i = R.id.tv_logout_cancel;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_logout_cancel);
        if (textView != null) {
            i = R.id.tv_logout_confirm;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_logout_confirm);
            if (textView2 != null) {
                i = R.id.tv_title;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_title);
                if (textView3 != null) {
                    return new DialogLogoutBinding((FrameLayout) rootView, textView, textView2, textView3);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
