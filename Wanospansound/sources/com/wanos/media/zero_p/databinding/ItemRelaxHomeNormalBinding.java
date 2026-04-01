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
public final class ItemRelaxHomeNormalBinding implements ViewBinding {
    public final AppCompatImageView imSpaceBg;
    public final AppCompatImageView ivVip;
    private final LinearLayoutCompat rootView;
    public final WanosTextView tvSpaceName;

    private ItemRelaxHomeNormalBinding(LinearLayoutCompat linearLayoutCompat, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2, WanosTextView wanosTextView) {
        this.rootView = linearLayoutCompat;
        this.imSpaceBg = appCompatImageView;
        this.ivVip = appCompatImageView2;
        this.tvSpaceName = wanosTextView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayoutCompat getRoot() {
        return this.rootView;
    }

    public static ItemRelaxHomeNormalBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemRelaxHomeNormalBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.item_relax_home_normal, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemRelaxHomeNormalBinding bind(View view) {
        int i = R.id.im_space_bg;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = R.id.iv_vip;
            AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
            if (appCompatImageView2 != null) {
                i = R.id.tv_space_name;
                WanosTextView wanosTextView = (WanosTextView) ViewBindings.findChildViewById(view, i);
                if (wanosTextView != null) {
                    return new ItemRelaxHomeNormalBinding((LinearLayoutCompat) view, appCompatImageView, appCompatImageView2, wanosTextView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
