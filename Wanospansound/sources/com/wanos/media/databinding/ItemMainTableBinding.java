package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;
import com.wanos.media.ui.widget.WanosTextView;

/* JADX INFO: loaded from: classes3.dex */
public final class ItemMainTableBinding implements ViewBinding {
    public final AppCompatImageView ivTableIcon;
    private final LinearLayoutCompat rootView;
    public final WanosTextView tvTableText;

    private ItemMainTableBinding(LinearLayoutCompat rootView, AppCompatImageView ivTableIcon, WanosTextView tvTableText) {
        this.rootView = rootView;
        this.ivTableIcon = ivTableIcon;
        this.tvTableText = tvTableText;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayoutCompat getRoot() {
        return this.rootView;
    }

    public static ItemMainTableBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemMainTableBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_main_table, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemMainTableBinding bind(View rootView) {
        int i = R.id.iv_table_icon;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(rootView, R.id.iv_table_icon);
        if (appCompatImageView != null) {
            i = R.id.tv_table_text;
            WanosTextView wanosTextView = (WanosTextView) ViewBindings.findChildViewById(rootView, R.id.tv_table_text);
            if (wanosTextView != null) {
                return new ItemMainTableBinding((LinearLayoutCompat) rootView, appCompatImageView, wanosTextView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
