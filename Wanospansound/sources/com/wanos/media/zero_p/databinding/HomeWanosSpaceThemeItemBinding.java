package com.wanos.media.zero_p.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.widget.SquareCardView;
import com.wanos.media.widget.WanosTextView;
import com.wanos.media.zero_p.R;

/* JADX INFO: loaded from: classes3.dex */
public final class HomeWanosSpaceThemeItemBinding implements ViewBinding {
    public final AppCompatImageView btnSpaceOpMore;
    public final AppCompatImageView imSpaceBg;
    public final AppCompatImageView ivVip;
    private final SquareCardView rootView;
    public final WanosTextView tvSpaceName;
    public final SquareCardView viewItem;

    private HomeWanosSpaceThemeItemBinding(SquareCardView squareCardView, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2, AppCompatImageView appCompatImageView3, WanosTextView wanosTextView, SquareCardView squareCardView2) {
        this.rootView = squareCardView;
        this.btnSpaceOpMore = appCompatImageView;
        this.imSpaceBg = appCompatImageView2;
        this.ivVip = appCompatImageView3;
        this.tvSpaceName = wanosTextView;
        this.viewItem = squareCardView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public SquareCardView getRoot() {
        return this.rootView;
    }

    public static HomeWanosSpaceThemeItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HomeWanosSpaceThemeItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.home_wanos_space_theme_item, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static HomeWanosSpaceThemeItemBinding bind(View view) {
        int i = R.id.btn_space_op_more;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = R.id.im_space_bg;
            AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
            if (appCompatImageView2 != null) {
                i = R.id.iv_vip;
                AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                if (appCompatImageView3 != null) {
                    i = R.id.tv_space_name;
                    WanosTextView wanosTextView = (WanosTextView) ViewBindings.findChildViewById(view, i);
                    if (wanosTextView != null) {
                        SquareCardView squareCardView = (SquareCardView) view;
                        return new HomeWanosSpaceThemeItemBinding(squareCardView, appCompatImageView, appCompatImageView2, appCompatImageView3, wanosTextView, squareCardView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
