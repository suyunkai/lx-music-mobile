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
public final class DialogNewProjectBinding implements ViewBinding {
    public final ImageView btnAi;
    public final ImageView btnAudiobook;
    public final ImageView btnClose;
    public final ConstraintLayout dialogView;
    public final FrameLayout dialogWin;
    private final FrameLayout rootView;
    public final TextView tvTitleSpeed;

    private DialogNewProjectBinding(FrameLayout frameLayout, ImageView imageView, ImageView imageView2, ImageView imageView3, ConstraintLayout constraintLayout, FrameLayout frameLayout2, TextView textView) {
        this.rootView = frameLayout;
        this.btnAi = imageView;
        this.btnAudiobook = imageView2;
        this.btnClose = imageView3;
        this.dialogView = constraintLayout;
        this.dialogWin = frameLayout2;
        this.tvTitleSpeed = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static DialogNewProjectBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogNewProjectBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.dialog_new_project, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogNewProjectBinding bind(View view) {
        int i = R.id.btn_ai;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.btn_audiobook;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
            if (imageView2 != null) {
                i = R.id.btn_close;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, i);
                if (imageView3 != null) {
                    i = R.id.dialog_view;
                    ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                    if (constraintLayout != null) {
                        FrameLayout frameLayout = (FrameLayout) view;
                        i = R.id.tv_title_speed;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView != null) {
                            return new DialogNewProjectBinding(frameLayout, imageView, imageView2, imageView3, constraintLayout, frameLayout, textView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
