package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class FragmentCommonDialogBinding implements ViewBinding {
    public final CardView btnDialogLeft;
    public final CardView btnDialogRight;
    public final ConstraintLayout clDialogLayout;
    public final CardView cvDialog;
    private final ConstraintLayout rootView;
    public final TextView tvDialogLeft;
    public final TextView tvDialogMsg;
    public final TextView tvDialogRight;
    public final TextView tvDialogTitle;

    private FragmentCommonDialogBinding(ConstraintLayout rootView, CardView btnDialogLeft, CardView btnDialogRight, ConstraintLayout clDialogLayout, CardView cvDialog, TextView tvDialogLeft, TextView tvDialogMsg, TextView tvDialogRight, TextView tvDialogTitle) {
        this.rootView = rootView;
        this.btnDialogLeft = btnDialogLeft;
        this.btnDialogRight = btnDialogRight;
        this.clDialogLayout = clDialogLayout;
        this.cvDialog = cvDialog;
        this.tvDialogLeft = tvDialogLeft;
        this.tvDialogMsg = tvDialogMsg;
        this.tvDialogRight = tvDialogRight;
        this.tvDialogTitle = tvDialogTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentCommonDialogBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentCommonDialogBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_common_dialog, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentCommonDialogBinding bind(View rootView) {
        int i = R.id.btn_dialog_left;
        CardView cardView = (CardView) ViewBindings.findChildViewById(rootView, R.id.btn_dialog_left);
        if (cardView != null) {
            i = R.id.btn_dialog_right;
            CardView cardView2 = (CardView) ViewBindings.findChildViewById(rootView, R.id.btn_dialog_right);
            if (cardView2 != null) {
                ConstraintLayout constraintLayout = (ConstraintLayout) rootView;
                i = R.id.cv_dialog;
                CardView cardView3 = (CardView) ViewBindings.findChildViewById(rootView, R.id.cv_dialog);
                if (cardView3 != null) {
                    i = R.id.tv_dialog_left;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_dialog_left);
                    if (textView != null) {
                        i = R.id.tv_dialog_msg;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_dialog_msg);
                        if (textView2 != null) {
                            i = R.id.tv_dialog_right;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_dialog_right);
                            if (textView3 != null) {
                                i = R.id.tv_dialog_title;
                                TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_dialog_title);
                                if (textView4 != null) {
                                    return new FragmentCommonDialogBinding(constraintLayout, cardView, cardView2, constraintLayout, cardView3, textView, textView2, textView3, textView4);
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
