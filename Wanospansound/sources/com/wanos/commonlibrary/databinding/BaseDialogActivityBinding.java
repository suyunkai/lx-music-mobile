package com.wanos.commonlibrary.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.commonlibrary.R;
import net.center.blurview.ShapeBlurView;

/* JADX INFO: loaded from: classes3.dex */
public final class BaseDialogActivityBinding implements ViewBinding {
    public final ShapeBlurView baseBlur;
    public final FrameLayout baseContentViewgroup;
    public final ImageView ivBg;
    private final FrameLayout rootView;

    private BaseDialogActivityBinding(FrameLayout frameLayout, ShapeBlurView shapeBlurView, FrameLayout frameLayout2, ImageView imageView) {
        this.rootView = frameLayout;
        this.baseBlur = shapeBlurView;
        this.baseContentViewgroup = frameLayout2;
        this.ivBg = imageView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static BaseDialogActivityBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static BaseDialogActivityBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.base_dialog_activity, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static BaseDialogActivityBinding bind(View view) {
        int i = R.id.base_blur;
        ShapeBlurView shapeBlurView = (ShapeBlurView) ViewBindings.findChildViewById(view, i);
        if (shapeBlurView != null) {
            i = R.id.base_content_viewgroup;
            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
            if (frameLayout != null) {
                i = R.id.iv_bg;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                if (imageView != null) {
                    return new BaseDialogActivityBinding((FrameLayout) view, shapeBlurView, frameLayout, imageView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
