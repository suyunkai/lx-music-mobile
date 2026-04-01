package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public final class PopMidiSelectInstrumentBinding implements ViewBinding {
    public final ImageView ivInstrument1;
    public final ImageView ivInstrument2;
    public final ImageView ivInstrument3;
    public final ImageView ivInstrument4;
    public final LinearLayout layoutInstrument1;
    public final LinearLayout layoutInstrument2;
    public final LinearLayout layoutInstrument3;
    public final LinearLayout layoutInstrument4;
    public final LinearLayout radioGroup;
    private final LinearLayout rootView;

    private PopMidiSelectInstrumentBinding(LinearLayout linearLayout, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, LinearLayout linearLayout5, LinearLayout linearLayout6) {
        this.rootView = linearLayout;
        this.ivInstrument1 = imageView;
        this.ivInstrument2 = imageView2;
        this.ivInstrument3 = imageView3;
        this.ivInstrument4 = imageView4;
        this.layoutInstrument1 = linearLayout2;
        this.layoutInstrument2 = linearLayout3;
        this.layoutInstrument3 = linearLayout4;
        this.layoutInstrument4 = linearLayout5;
        this.radioGroup = linearLayout6;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static PopMidiSelectInstrumentBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopMidiSelectInstrumentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.pop_midi_select_instrument, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static PopMidiSelectInstrumentBinding bind(View view) {
        int i = R.id.iv_instrument1;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.iv_instrument2;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
            if (imageView2 != null) {
                i = R.id.iv_instrument3;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, i);
                if (imageView3 != null) {
                    i = R.id.iv_instrument4;
                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, i);
                    if (imageView4 != null) {
                        i = R.id.layout_instrument1;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
                        if (linearLayout != null) {
                            i = R.id.layout_instrument2;
                            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                            if (linearLayout2 != null) {
                                i = R.id.layout_instrument3;
                                LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                if (linearLayout3 != null) {
                                    i = R.id.layout_instrument4;
                                    LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                    if (linearLayout4 != null) {
                                        LinearLayout linearLayout5 = (LinearLayout) view;
                                        return new PopMidiSelectInstrumentBinding(linearLayout5, imageView, imageView2, imageView3, imageView4, linearLayout, linearLayout2, linearLayout3, linearLayout4, linearLayout5);
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
