package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public final class DialogCreatorShareBinding implements ViewBinding {
    public final ImageView btnBack;
    public final CardView btnDialogLeft;
    public final CardView btnDialogRight;
    public final TextView code0;
    public final TextView code1;
    public final TextView code2;
    public final TextView code3;
    public final TextView code4;
    public final TextView code5;
    public final CardView cvView;
    public final EditText etCode;
    public final LinearLayout layoutEdit;
    public final LinearLayout layoutShare;
    private final FrameLayout rootView;
    public final TextView tvDialogLeft;
    public final TextView tvDialogRight;
    public final TextView tvTitle;

    private DialogCreatorShareBinding(FrameLayout frameLayout, ImageView imageView, CardView cardView, CardView cardView2, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, CardView cardView3, EditText editText, LinearLayout linearLayout, LinearLayout linearLayout2, TextView textView7, TextView textView8, TextView textView9) {
        this.rootView = frameLayout;
        this.btnBack = imageView;
        this.btnDialogLeft = cardView;
        this.btnDialogRight = cardView2;
        this.code0 = textView;
        this.code1 = textView2;
        this.code2 = textView3;
        this.code3 = textView4;
        this.code4 = textView5;
        this.code5 = textView6;
        this.cvView = cardView3;
        this.etCode = editText;
        this.layoutEdit = linearLayout;
        this.layoutShare = linearLayout2;
        this.tvDialogLeft = textView7;
        this.tvDialogRight = textView8;
        this.tvTitle = textView9;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static DialogCreatorShareBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogCreatorShareBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.dialog_creator_share, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogCreatorShareBinding bind(View view) {
        int i = R.id.btn_back;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.btn_dialog_left;
            CardView cardView = (CardView) ViewBindings.findChildViewById(view, i);
            if (cardView != null) {
                i = R.id.btn_dialog_right;
                CardView cardView2 = (CardView) ViewBindings.findChildViewById(view, i);
                if (cardView2 != null) {
                    i = R.id.code_0;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView != null) {
                        i = R.id.code_1;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView2 != null) {
                            i = R.id.code_2;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                            if (textView3 != null) {
                                i = R.id.code_3;
                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, i);
                                if (textView4 != null) {
                                    i = R.id.code_4;
                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view, i);
                                    if (textView5 != null) {
                                        i = R.id.code_5;
                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(view, i);
                                        if (textView6 != null) {
                                            i = R.id.cv_view;
                                            CardView cardView3 = (CardView) ViewBindings.findChildViewById(view, i);
                                            if (cardView3 != null) {
                                                i = R.id.et_code;
                                                EditText editText = (EditText) ViewBindings.findChildViewById(view, i);
                                                if (editText != null) {
                                                    i = R.id.layout_edit;
                                                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                    if (linearLayout != null) {
                                                        i = R.id.layout_share;
                                                        LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                                        if (linearLayout2 != null) {
                                                            i = R.id.tv_dialog_left;
                                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(view, i);
                                                            if (textView7 != null) {
                                                                i = R.id.tv_dialog_right;
                                                                TextView textView8 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                if (textView8 != null) {
                                                                    i = R.id.tv_title;
                                                                    TextView textView9 = (TextView) ViewBindings.findChildViewById(view, i);
                                                                    if (textView9 != null) {
                                                                        return new DialogCreatorShareBinding((FrameLayout) view, imageView, cardView, cardView2, textView, textView2, textView3, textView4, textView5, textView6, cardView3, editText, linearLayout, linearLayout2, textView7, textView8, textView9);
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
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
