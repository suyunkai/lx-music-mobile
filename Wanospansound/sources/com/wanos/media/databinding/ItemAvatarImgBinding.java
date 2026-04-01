package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ItemAvatarImgBinding implements ViewBinding {
    public final ImageView ivSelect;
    public final ImageView pro;
    private final FrameLayout rootView;

    private ItemAvatarImgBinding(FrameLayout rootView, ImageView ivSelect, ImageView pro) {
        this.rootView = rootView;
        this.ivSelect = ivSelect;
        this.pro = pro;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static ItemAvatarImgBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemAvatarImgBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_avatar_img, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemAvatarImgBinding bind(View rootView) {
        int i = R.id.iv_select;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_select);
        if (imageView != null) {
            i = R.id.pro;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.pro);
            if (imageView2 != null) {
                return new ItemAvatarImgBinding((FrameLayout) rootView, imageView, imageView2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
