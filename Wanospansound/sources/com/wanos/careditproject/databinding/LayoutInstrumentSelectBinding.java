package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public final class LayoutInstrumentSelectBinding implements ViewBinding {
    public final ImageView ivClose;
    public final LinearLayout llKeyboardType;
    public final LinearLayout llType;
    private final ConstraintLayout rootView;
    public final TextView tvFixedKeyboard;
    public final TextView tvInstrument;
    public final TextView tvInstrumentName;
    public final TextView tvMidiKeyboard;
    public final TextView tvName;
    public final TextView tvSlideKeyboard;
    public final TextView tvTweak;
    public final TextView tvValue;
    public final ImageView viewLeftArrow;
    public final ImageView viewLeftArrow2;
    public final ImageView viewRightArrow;
    public final ImageView viewRightArrow2;

    private LayoutInstrumentSelectBinding(ConstraintLayout constraintLayout, ImageView imageView, LinearLayout linearLayout, LinearLayout linearLayout2, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5) {
        this.rootView = constraintLayout;
        this.ivClose = imageView;
        this.llKeyboardType = linearLayout;
        this.llType = linearLayout2;
        this.tvFixedKeyboard = textView;
        this.tvInstrument = textView2;
        this.tvInstrumentName = textView3;
        this.tvMidiKeyboard = textView4;
        this.tvName = textView5;
        this.tvSlideKeyboard = textView6;
        this.tvTweak = textView7;
        this.tvValue = textView8;
        this.viewLeftArrow = imageView2;
        this.viewLeftArrow2 = imageView3;
        this.viewRightArrow = imageView4;
        this.viewRightArrow2 = imageView5;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutInstrumentSelectBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutInstrumentSelectBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.layout_instrument_select, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutInstrumentSelectBinding bind(View view) {
        int i = R.id.iv_close;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.ll_keyboard_type;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
            if (linearLayout != null) {
                i = R.id.ll_type;
                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                if (linearLayout2 != null) {
                    i = R.id.tv_fixed_keyboard;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView != null) {
                        i = R.id.tv_instrument;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView2 != null) {
                            i = R.id.tv_instrument_name;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                            if (textView3 != null) {
                                i = R.id.tv_midi_keyboard;
                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, i);
                                if (textView4 != null) {
                                    i = R.id.tv_name;
                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view, i);
                                    if (textView5 != null) {
                                        i = R.id.tv_slide_keyboard;
                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(view, i);
                                        if (textView6 != null) {
                                            i = R.id.tv_tweak;
                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(view, i);
                                            if (textView7 != null) {
                                                i = R.id.tv_value;
                                                TextView textView8 = (TextView) ViewBindings.findChildViewById(view, i);
                                                if (textView8 != null) {
                                                    i = R.id.view_left_arrow;
                                                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                    if (imageView2 != null) {
                                                        i = R.id.view_left_arrow2;
                                                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                        if (imageView3 != null) {
                                                            i = R.id.view_right_arrow;
                                                            ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                            if (imageView4 != null) {
                                                                i = R.id.view_right_arrow2;
                                                                ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, i);
                                                                if (imageView5 != null) {
                                                                    return new LayoutInstrumentSelectBinding((ConstraintLayout) view, imageView, linearLayout, linearLayout2, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, imageView2, imageView3, imageView4, imageView5);
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
