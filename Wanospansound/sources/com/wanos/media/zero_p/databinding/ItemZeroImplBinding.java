package com.wanos.media.zero_p.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.widget.WanosTextView;
import com.wanos.media.zero_p.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ItemZeroImplBinding implements ViewBinding {
    public final AppCompatImageView ivBox;
    private final LinearLayoutCompat rootView;
    public final WanosTextView tvNumber;
    public final WanosTextView tvTime;
    public final WanosTextView tvTitle;

    private ItemZeroImplBinding(LinearLayoutCompat linearLayoutCompat, AppCompatImageView appCompatImageView, WanosTextView wanosTextView, WanosTextView wanosTextView2, WanosTextView wanosTextView3) {
        this.rootView = linearLayoutCompat;
        this.ivBox = appCompatImageView;
        this.tvNumber = wanosTextView;
        this.tvTime = wanosTextView2;
        this.tvTitle = wanosTextView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayoutCompat getRoot() {
        return this.rootView;
    }

    public static ItemZeroImplBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemZeroImplBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.item_zero_impl, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemZeroImplBinding bind(View view) {
        int i = R.id.iv_box;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = R.id.tv_number;
            WanosTextView wanosTextView = (WanosTextView) ViewBindings.findChildViewById(view, i);
            if (wanosTextView != null) {
                i = R.id.tv_time;
                WanosTextView wanosTextView2 = (WanosTextView) ViewBindings.findChildViewById(view, i);
                if (wanosTextView2 != null) {
                    i = R.id.tv_title;
                    WanosTextView wanosTextView3 = (WanosTextView) ViewBindings.findChildViewById(view, i);
                    if (wanosTextView3 != null) {
                        return new ItemZeroImplBinding((LinearLayoutCompat) view, appCompatImageView, wanosTextView, wanosTextView2, wanosTextView3);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
