package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public final class LayoutGainTotalBinding implements ViewBinding {
    public final LinearLayout layoutTotalDb;
    private final LinearLayout rootView;
    public final SeekBar seekBarGain;
    public final TextView tvDb;

    private LayoutGainTotalBinding(LinearLayout linearLayout, LinearLayout linearLayout2, SeekBar seekBar, TextView textView) {
        this.rootView = linearLayout;
        this.layoutTotalDb = linearLayout2;
        this.seekBarGain = seekBar;
        this.tvDb = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static LayoutGainTotalBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutGainTotalBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.layout_gain_total, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutGainTotalBinding bind(View view) {
        LinearLayout linearLayout = (LinearLayout) view;
        int i = R.id.seek_bar_gain;
        SeekBar seekBar = (SeekBar) ViewBindings.findChildViewById(view, i);
        if (seekBar != null) {
            i = R.id.tv_db;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView != null) {
                return new LayoutGainTotalBinding(linearLayout, linearLayout, seekBar, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
