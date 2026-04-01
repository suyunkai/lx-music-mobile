package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public final class DialogMissResBinding implements ViewBinding {
    public final ImageView btnCloseHelp;
    public final TextView btnSure;
    public final ConstraintLayout dialogView;
    public final FrameLayout dialogWin;
    private final FrameLayout rootView;

    private DialogMissResBinding(FrameLayout frameLayout, ImageView imageView, TextView textView, ConstraintLayout constraintLayout, FrameLayout frameLayout2) {
        this.rootView = frameLayout;
        this.btnCloseHelp = imageView;
        this.btnSure = textView;
        this.dialogView = constraintLayout;
        this.dialogWin = frameLayout2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static DialogMissResBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogMissResBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.dialog_miss_res, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogMissResBinding bind(View view) {
        int i = R.id.btn_close_help;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.btn_sure;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView != null) {
                i = R.id.dialog_view;
                ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                if (constraintLayout != null) {
                    FrameLayout frameLayout = (FrameLayout) view;
                    return new DialogMissResBinding(frameLayout, imageView, textView, constraintLayout, frameLayout);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
