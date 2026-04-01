package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class FragmentOrderInfoItemBinding implements ViewBinding {
    public final CardView orderCdBg;
    public final CardView orderCdImgBg;
    public final ImageView orderImg;
    public final ImageView orderImgVip;
    public final TextView orderTvBehind;
    public final TextView orderTvNumber;
    public final TextView orderTvPrice;
    public final TextView orderTvTitle;
    private final ConstraintLayout rootView;

    private FragmentOrderInfoItemBinding(ConstraintLayout rootView, CardView orderCdBg, CardView orderCdImgBg, ImageView orderImg, ImageView orderImgVip, TextView orderTvBehind, TextView orderTvNumber, TextView orderTvPrice, TextView orderTvTitle) {
        this.rootView = rootView;
        this.orderCdBg = orderCdBg;
        this.orderCdImgBg = orderCdImgBg;
        this.orderImg = orderImg;
        this.orderImgVip = orderImgVip;
        this.orderTvBehind = orderTvBehind;
        this.orderTvNumber = orderTvNumber;
        this.orderTvPrice = orderTvPrice;
        this.orderTvTitle = orderTvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentOrderInfoItemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentOrderInfoItemBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_order_info_item, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentOrderInfoItemBinding bind(View rootView) {
        int i = R.id.order_cd_bg;
        CardView cardView = (CardView) ViewBindings.findChildViewById(rootView, R.id.order_cd_bg);
        if (cardView != null) {
            i = R.id.order_cd_img_bg;
            CardView cardView2 = (CardView) ViewBindings.findChildViewById(rootView, R.id.order_cd_img_bg);
            if (cardView2 != null) {
                i = R.id.order_img;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.order_img);
                if (imageView != null) {
                    i = R.id.order_img_vip;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.order_img_vip);
                    if (imageView2 != null) {
                        i = R.id.order_tv_behind;
                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.order_tv_behind);
                        if (textView != null) {
                            i = R.id.order_tv_number;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.order_tv_number);
                            if (textView2 != null) {
                                i = R.id.order_tv_price;
                                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.order_tv_price);
                                if (textView3 != null) {
                                    i = R.id.order_tv_title;
                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.order_tv_title);
                                    if (textView4 != null) {
                                        return new FragmentOrderInfoItemBinding((ConstraintLayout) rootView, cardView, cardView2, imageView, imageView2, textView, textView2, textView3, textView4);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
