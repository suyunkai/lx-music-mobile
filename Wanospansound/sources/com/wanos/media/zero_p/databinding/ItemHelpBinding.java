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
public final class ItemHelpBinding implements ViewBinding {
    public final AppCompatImageView ivImage;
    private final LinearLayoutCompat rootView;
    public final WanosTextView tvHint;

    private ItemHelpBinding(LinearLayoutCompat linearLayoutCompat, AppCompatImageView appCompatImageView, WanosTextView wanosTextView) {
        this.rootView = linearLayoutCompat;
        this.ivImage = appCompatImageView;
        this.tvHint = wanosTextView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayoutCompat getRoot() {
        return this.rootView;
    }

    public static ItemHelpBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemHelpBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.item_help, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemHelpBinding bind(View view) {
        int i = R.id.iv_image;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = R.id.tv_hint;
            WanosTextView wanosTextView = (WanosTextView) ViewBindings.findChildViewById(view, i);
            if (wanosTextView != null) {
                return new ItemHelpBinding((LinearLayoutCompat) view, appCompatImageView, wanosTextView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
