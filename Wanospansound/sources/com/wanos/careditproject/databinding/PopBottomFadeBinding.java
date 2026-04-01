package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.jaygoo.widget.RangeSeekBar;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public final class PopBottomFadeBinding implements ViewBinding {
    public final ImageView btnCloseFade;
    public final ImageButton btnFade0;
    public final ImageButton btnFade1;
    public final ImageButton btnFade2;
    public final ImageButton btnFade3;
    private final LinearLayout rootView;
    public final RangeSeekBar seekbarFade;
    public final TextView tvTitleFade;
    public final View viewFadeTop;

    private PopBottomFadeBinding(LinearLayout linearLayout, ImageView imageView, ImageButton imageButton, ImageButton imageButton2, ImageButton imageButton3, ImageButton imageButton4, RangeSeekBar rangeSeekBar, TextView textView, View view) {
        this.rootView = linearLayout;
        this.btnCloseFade = imageView;
        this.btnFade0 = imageButton;
        this.btnFade1 = imageButton2;
        this.btnFade2 = imageButton3;
        this.btnFade3 = imageButton4;
        this.seekbarFade = rangeSeekBar;
        this.tvTitleFade = textView;
        this.viewFadeTop = view;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static PopBottomFadeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopBottomFadeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.pop_bottom_fade, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static PopBottomFadeBinding bind(View view) {
        View viewFindChildViewById;
        int i = R.id.btn_close_fade;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.btn_fade_0;
            ImageButton imageButton = (ImageButton) ViewBindings.findChildViewById(view, i);
            if (imageButton != null) {
                i = R.id.btn_fade_1;
                ImageButton imageButton2 = (ImageButton) ViewBindings.findChildViewById(view, i);
                if (imageButton2 != null) {
                    i = R.id.btn_fade_2;
                    ImageButton imageButton3 = (ImageButton) ViewBindings.findChildViewById(view, i);
                    if (imageButton3 != null) {
                        i = R.id.btn_fade_3;
                        ImageButton imageButton4 = (ImageButton) ViewBindings.findChildViewById(view, i);
                        if (imageButton4 != null) {
                            i = R.id.seekbar_fade;
                            RangeSeekBar rangeSeekBar = (RangeSeekBar) ViewBindings.findChildViewById(view, i);
                            if (rangeSeekBar != null) {
                                i = R.id.tv_title_fade;
                                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                                if (textView != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.view_fade_top))) != null) {
                                    return new PopBottomFadeBinding((LinearLayout) view, imageView, imageButton, imageButton2, imageButton3, imageButton4, rangeSeekBar, textView, viewFindChildViewById);
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
