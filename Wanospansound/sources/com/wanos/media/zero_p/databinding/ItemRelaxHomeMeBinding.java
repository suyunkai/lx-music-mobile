package com.wanos.media.zero_p.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.widget.WanosTextView;
import com.wanos.media.zero_p.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ItemRelaxHomeMeBinding implements ViewBinding {
    public final AppCompatImageView btnSpaceOpMore;
    public final CardView caSpaceBg;
    public final AppCompatImageView imSpaceBg;
    public final AppCompatImageView ivIconTag;
    public final AppCompatImageView ivVip;
    private final ConstraintLayout rootView;
    public final WanosTextView tvSpaceName;
    public final com.wanos.media.ui.widget.WanosTextView tvSubTitle;

    private ItemRelaxHomeMeBinding(ConstraintLayout constraintLayout, AppCompatImageView appCompatImageView, CardView cardView, AppCompatImageView appCompatImageView2, AppCompatImageView appCompatImageView3, AppCompatImageView appCompatImageView4, WanosTextView wanosTextView, com.wanos.media.ui.widget.WanosTextView wanosTextView2) {
        this.rootView = constraintLayout;
        this.btnSpaceOpMore = appCompatImageView;
        this.caSpaceBg = cardView;
        this.imSpaceBg = appCompatImageView2;
        this.ivIconTag = appCompatImageView3;
        this.ivVip = appCompatImageView4;
        this.tvSpaceName = wanosTextView;
        this.tvSubTitle = wanosTextView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemRelaxHomeMeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemRelaxHomeMeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.item_relax_home_me, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemRelaxHomeMeBinding bind(View view) {
        int i = R.id.btn_space_op_more;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = R.id.ca_space_bg;
            CardView cardView = (CardView) ViewBindings.findChildViewById(view, i);
            if (cardView != null) {
                i = R.id.im_space_bg;
                AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                if (appCompatImageView2 != null) {
                    i = R.id.iv_icon_tag;
                    AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                    if (appCompatImageView3 != null) {
                        i = R.id.iv_vip;
                        AppCompatImageView appCompatImageView4 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                        if (appCompatImageView4 != null) {
                            i = R.id.tv_space_name;
                            WanosTextView wanosTextView = (WanosTextView) ViewBindings.findChildViewById(view, i);
                            if (wanosTextView != null) {
                                i = R.id.tv_sub_title;
                                com.wanos.media.ui.widget.WanosTextView wanosTextView2 = (com.wanos.media.ui.widget.WanosTextView) ViewBindings.findChildViewById(view, i);
                                if (wanosTextView2 != null) {
                                    return new ItemRelaxHomeMeBinding((ConstraintLayout) view, appCompatImageView, cardView, appCompatImageView2, appCompatImageView3, appCompatImageView4, wanosTextView, wanosTextView2);
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
