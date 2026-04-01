package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public final class DialogCreatorProjectModifyBinding implements ViewBinding {
    public final ImageView btnBack;
    public final CardView btnDialogLeft;
    public final CardView btnDialogRight;
    public final CardView cvView;
    private final FrameLayout rootView;
    public final TextView tvDialogLeft;
    public final TextView tvDialogRight;
    public final TextView tvTitle;

    private DialogCreatorProjectModifyBinding(FrameLayout frameLayout, ImageView imageView, CardView cardView, CardView cardView2, CardView cardView3, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = frameLayout;
        this.btnBack = imageView;
        this.btnDialogLeft = cardView;
        this.btnDialogRight = cardView2;
        this.cvView = cardView3;
        this.tvDialogLeft = textView;
        this.tvDialogRight = textView2;
        this.tvTitle = textView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static DialogCreatorProjectModifyBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogCreatorProjectModifyBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.dialog_creator_project_modify, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogCreatorProjectModifyBinding bind(View view) {
        int i = R.id.btn_back;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.btn_dialog_left;
            CardView cardView = (CardView) ViewBindings.findChildViewById(view, i);
            if (cardView != null) {
                i = R.id.btn_dialog_right;
                CardView cardView2 = (CardView) ViewBindings.findChildViewById(view, i);
                if (cardView2 != null) {
                    i = R.id.cv_view;
                    CardView cardView3 = (CardView) ViewBindings.findChildViewById(view, i);
                    if (cardView3 != null) {
                        i = R.id.tv_dialog_left;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView != null) {
                            i = R.id.tv_dialog_right;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                            if (textView2 != null) {
                                i = R.id.tv_title;
                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                                if (textView3 != null) {
                                    return new DialogCreatorProjectModifyBinding((FrameLayout) view, imageView, cardView, cardView2, cardView3, textView, textView2, textView3);
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
