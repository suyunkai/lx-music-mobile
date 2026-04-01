package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public final class PopBottomSpeedBinding implements ViewBinding {
    public final ImageView btnCloseSpeed;
    private final ConstraintLayout rootView;
    public final SeekBar seekbarSpeed;
    public final TextView tvSpeed;
    public final TextView tvTitleSpeed;
    public final LinearLayout viewRoot;

    private PopBottomSpeedBinding(ConstraintLayout constraintLayout, ImageView imageView, SeekBar seekBar, TextView textView, TextView textView2, LinearLayout linearLayout) {
        this.rootView = constraintLayout;
        this.btnCloseSpeed = imageView;
        this.seekbarSpeed = seekBar;
        this.tvSpeed = textView;
        this.tvTitleSpeed = textView2;
        this.viewRoot = linearLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static PopBottomSpeedBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopBottomSpeedBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.pop_bottom_speed, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static PopBottomSpeedBinding bind(View view) {
        int i = R.id.btn_close_speed;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.seekbar_speed;
            SeekBar seekBar = (SeekBar) ViewBindings.findChildViewById(view, i);
            if (seekBar != null) {
                i = R.id.tv_speed;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView != null) {
                    i = R.id.tv_title_speed;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView2 != null) {
                        i = R.id.view_root;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
                        if (linearLayout != null) {
                            return new PopBottomSpeedBinding((ConstraintLayout) view, imageView, seekBar, textView, textView2, linearLayout);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
