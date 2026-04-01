package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class IncludeMemberPayCardBinding implements ViewBinding {
    public final ConstraintLayout includeSelectCard;
    public final LinearLayout llPriceLayout;
    public final ImageView payImSelected;
    public final TextView payTvOriginalPrice;
    public final TextView payTvPrice;
    public final TextView payTvPriceSymbol;
    public final TextView payTvTitle;
    private final ConstraintLayout rootView;

    private IncludeMemberPayCardBinding(ConstraintLayout rootView, ConstraintLayout includeSelectCard, LinearLayout llPriceLayout, ImageView payImSelected, TextView payTvOriginalPrice, TextView payTvPrice, TextView payTvPriceSymbol, TextView payTvTitle) {
        this.rootView = rootView;
        this.includeSelectCard = includeSelectCard;
        this.llPriceLayout = llPriceLayout;
        this.payImSelected = payImSelected;
        this.payTvOriginalPrice = payTvOriginalPrice;
        this.payTvPrice = payTvPrice;
        this.payTvPriceSymbol = payTvPriceSymbol;
        this.payTvTitle = payTvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static IncludeMemberPayCardBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static IncludeMemberPayCardBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.include_member_pay_card, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static IncludeMemberPayCardBinding bind(View rootView) {
        ConstraintLayout constraintLayout = (ConstraintLayout) rootView;
        int i = R.id.ll_price_layout;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_price_layout);
        if (linearLayout != null) {
            i = R.id.pay_im_selected;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.pay_im_selected);
            if (imageView != null) {
                i = R.id.pay_tv_original_price;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.pay_tv_original_price);
                if (textView != null) {
                    i = R.id.pay_tv_price;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.pay_tv_price);
                    if (textView2 != null) {
                        i = R.id.pay_tv_price_symbol;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.pay_tv_price_symbol);
                        if (textView3 != null) {
                            i = R.id.pay_tv_title;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.pay_tv_title);
                            if (textView4 != null) {
                                return new IncludeMemberPayCardBinding(constraintLayout, constraintLayout, linearLayout, imageView, textView, textView2, textView3, textView4);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
