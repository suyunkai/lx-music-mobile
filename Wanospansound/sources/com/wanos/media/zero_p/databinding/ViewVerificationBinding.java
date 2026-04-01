package com.wanos.media.zero_p.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.widget.WanosTextView;
import com.wanos.media.zero_p.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ViewVerificationBinding implements ViewBinding {
    public final AppCompatEditText etInput;
    private final FrameLayout rootView;
    public final WanosTextView tvInput1;
    public final WanosTextView tvInput2;
    public final WanosTextView tvInput3;
    public final WanosTextView tvInput4;
    public final WanosTextView tvInput5;
    public final WanosTextView tvInput6;

    private ViewVerificationBinding(FrameLayout frameLayout, AppCompatEditText appCompatEditText, WanosTextView wanosTextView, WanosTextView wanosTextView2, WanosTextView wanosTextView3, WanosTextView wanosTextView4, WanosTextView wanosTextView5, WanosTextView wanosTextView6) {
        this.rootView = frameLayout;
        this.etInput = appCompatEditText;
        this.tvInput1 = wanosTextView;
        this.tvInput2 = wanosTextView2;
        this.tvInput3 = wanosTextView3;
        this.tvInput4 = wanosTextView4;
        this.tvInput5 = wanosTextView5;
        this.tvInput6 = wanosTextView6;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static ViewVerificationBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ViewVerificationBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.view_verification, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ViewVerificationBinding bind(View view) {
        int i = R.id.et_input;
        AppCompatEditText appCompatEditText = (AppCompatEditText) ViewBindings.findChildViewById(view, i);
        if (appCompatEditText != null) {
            i = R.id.tv_input_1;
            WanosTextView wanosTextView = (WanosTextView) ViewBindings.findChildViewById(view, i);
            if (wanosTextView != null) {
                i = R.id.tv_input_2;
                WanosTextView wanosTextView2 = (WanosTextView) ViewBindings.findChildViewById(view, i);
                if (wanosTextView2 != null) {
                    i = R.id.tv_input_3;
                    WanosTextView wanosTextView3 = (WanosTextView) ViewBindings.findChildViewById(view, i);
                    if (wanosTextView3 != null) {
                        i = R.id.tv_input_4;
                        WanosTextView wanosTextView4 = (WanosTextView) ViewBindings.findChildViewById(view, i);
                        if (wanosTextView4 != null) {
                            i = R.id.tv_input_5;
                            WanosTextView wanosTextView5 = (WanosTextView) ViewBindings.findChildViewById(view, i);
                            if (wanosTextView5 != null) {
                                i = R.id.tv_input_6;
                                WanosTextView wanosTextView6 = (WanosTextView) ViewBindings.findChildViewById(view, i);
                                if (wanosTextView6 != null) {
                                    return new ViewVerificationBinding((FrameLayout) view, appCompatEditText, wanosTextView, wanosTextView2, wanosTextView3, wanosTextView4, wanosTextView5, wanosTextView6);
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
