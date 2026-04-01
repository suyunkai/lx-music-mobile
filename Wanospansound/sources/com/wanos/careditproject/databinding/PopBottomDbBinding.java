package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public final class PopBottomDbBinding implements ViewBinding {
    public final ImageView btnCloseDb;
    private final LinearLayout rootView;
    public final SeekBar seekbarGainPop;
    public final TextView tvMusicDb;
    public final TextView tvTitleDb;
    public final View viewDbTop;

    private PopBottomDbBinding(LinearLayout linearLayout, ImageView imageView, SeekBar seekBar, TextView textView, TextView textView2, View view) {
        this.rootView = linearLayout;
        this.btnCloseDb = imageView;
        this.seekbarGainPop = seekBar;
        this.tvMusicDb = textView;
        this.tvTitleDb = textView2;
        this.viewDbTop = view;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static PopBottomDbBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopBottomDbBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.pop_bottom_db, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static PopBottomDbBinding bind(View view) {
        View viewFindChildViewById;
        int i = R.id.btn_close_db;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.seekbar_gain_pop;
            SeekBar seekBar = (SeekBar) ViewBindings.findChildViewById(view, i);
            if (seekBar != null) {
                i = R.id.tv_music_db;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView != null) {
                    i = R.id.tv_title_db;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView2 != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.view_db_top))) != null) {
                        return new PopBottomDbBinding((LinearLayout) view, imageView, seekBar, textView, textView2, viewFindChildViewById);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
