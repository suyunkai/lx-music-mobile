package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class FragmentCodeDialogBinding implements ViewBinding {
    public final CardView btnDialogLeft;
    public final CardView btnDialogRight;
    public final ConstraintLayout clDialogLayout;
    public final CardView cvDialog;
    public final EditText etDialogView;
    public final ImageView ivDialogBack;
    public final LinearLayout llDialogTitleLayout;
    private final ConstraintLayout rootView;
    public final TextView tvDialogLeft;
    public final TextView tvDialogProtocol;
    public final TextView tvDialogRight;
    public final TextView tvDialogTitle;

    private FragmentCodeDialogBinding(ConstraintLayout rootView, CardView btnDialogLeft, CardView btnDialogRight, ConstraintLayout clDialogLayout, CardView cvDialog, EditText etDialogView, ImageView ivDialogBack, LinearLayout llDialogTitleLayout, TextView tvDialogLeft, TextView tvDialogProtocol, TextView tvDialogRight, TextView tvDialogTitle) {
        this.rootView = rootView;
        this.btnDialogLeft = btnDialogLeft;
        this.btnDialogRight = btnDialogRight;
        this.clDialogLayout = clDialogLayout;
        this.cvDialog = cvDialog;
        this.etDialogView = etDialogView;
        this.ivDialogBack = ivDialogBack;
        this.llDialogTitleLayout = llDialogTitleLayout;
        this.tvDialogLeft = tvDialogLeft;
        this.tvDialogProtocol = tvDialogProtocol;
        this.tvDialogRight = tvDialogRight;
        this.tvDialogTitle = tvDialogTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentCodeDialogBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentCodeDialogBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_code_dialog, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentCodeDialogBinding bind(View rootView) {
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
                    i = R.id.et_dialog_view;
                    EditText editText = (EditText) ViewBindings.findChildViewById(rootView, R.id.et_dialog_view);
                    if (editText != null) {
                        i = R.id.iv_dialog_back;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_dialog_back);
                        if (imageView != null) {
                            i = R.id.ll_dialog_title_layout;
                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_dialog_title_layout);
                            if (linearLayout != null) {
                                i = R.id.tv_dialog_left;
                                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_dialog_left);
                                if (textView != null) {
                                    i = R.id.tv_dialog_protocol;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_dialog_protocol);
                                    if (textView2 != null) {
                                        i = R.id.tv_dialog_right;
                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_dialog_right);
                                        if (textView3 != null) {
                                            i = R.id.tv_dialog_title;
                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_dialog_title);
                                            if (textView4 != null) {
                                                return new FragmentCodeDialogBinding(constraintLayout, cardView, cardView2, constraintLayout, cardView3, editText, imageView, linearLayout, textView, textView2, textView3, textView4);
                                            }
                                        }
                                    }
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
