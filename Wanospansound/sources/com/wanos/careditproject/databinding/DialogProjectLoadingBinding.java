package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public final class DialogProjectLoadingBinding implements ViewBinding {
    public final TextView btnCancel;
    public final TextView btnCancelFail;
    public final TextView btnTryFail;
    public final LinearLayout btnsFail;
    public final CardView cvView;
    public final ImageView ivLogo;
    private final FrameLayout rootView;
    public final SeekBar seekbarProgress;
    public final TextView tvLoading;

    private DialogProjectLoadingBinding(FrameLayout frameLayout, TextView textView, TextView textView2, TextView textView3, LinearLayout linearLayout, CardView cardView, ImageView imageView, SeekBar seekBar, TextView textView4) {
        this.rootView = frameLayout;
        this.btnCancel = textView;
        this.btnCancelFail = textView2;
        this.btnTryFail = textView3;
        this.btnsFail = linearLayout;
        this.cvView = cardView;
        this.ivLogo = imageView;
        this.seekbarProgress = seekBar;
        this.tvLoading = textView4;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static DialogProjectLoadingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogProjectLoadingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.dialog_project_loading, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogProjectLoadingBinding bind(View view) {
        int i = R.id.btn_cancel;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
        if (textView != null) {
            i = R.id.btn_cancel_fail;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView2 != null) {
                i = R.id.btn_try_fail;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView3 != null) {
                    i = R.id.btns_fail;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
                    if (linearLayout != null) {
                        i = R.id.cv_view;
                        CardView cardView = (CardView) ViewBindings.findChildViewById(view, i);
                        if (cardView != null) {
                            i = R.id.iv_logo;
                            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                            if (imageView != null) {
                                i = R.id.seekbar_progress;
                                SeekBar seekBar = (SeekBar) ViewBindings.findChildViewById(view, i);
                                if (seekBar != null) {
                                    i = R.id.tv_loading;
                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, i);
                                    if (textView4 != null) {
                                        return new DialogProjectLoadingBinding((FrameLayout) view, textView, textView2, textView3, linearLayout, cardView, imageView, seekBar, textView4);
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
