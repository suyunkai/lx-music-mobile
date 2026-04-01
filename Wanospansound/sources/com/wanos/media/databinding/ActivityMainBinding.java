package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ActivityMainBinding implements ViewBinding {
    public final CardView cardRightTopRight;
    public final ImageView ivRightTopRight;
    public final View ivTopLogo;
    public final FrameLayout layoutRightBottom;
    public final RecyclerView llMainLeft;
    public final LinearLayout llWanosSerach;
    public final ConstraintLayout parentC;
    private final ConstraintLayout rootView;
    public final TextView tvRightTopLeft;

    private ActivityMainBinding(ConstraintLayout rootView, CardView cardRightTopRight, ImageView ivRightTopRight, View ivTopLogo, FrameLayout layoutRightBottom, RecyclerView llMainLeft, LinearLayout llWanosSerach, ConstraintLayout parentC, TextView tvRightTopLeft) {
        this.rootView = rootView;
        this.cardRightTopRight = cardRightTopRight;
        this.ivRightTopRight = ivRightTopRight;
        this.ivTopLogo = ivTopLogo;
        this.layoutRightBottom = layoutRightBottom;
        this.llMainLeft = llMainLeft;
        this.llWanosSerach = llWanosSerach;
        this.parentC = parentC;
        this.tvRightTopLeft = tvRightTopLeft;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMainBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMainBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_main, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityMainBinding bind(View rootView) {
        int i = R.id.card_right_top_right;
        CardView cardView = (CardView) ViewBindings.findChildViewById(rootView, R.id.card_right_top_right);
        if (cardView != null) {
            i = R.id.iv_right_top_right;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_right_top_right);
            if (imageView != null) {
                i = R.id.iv_top_logo;
                View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.iv_top_logo);
                if (viewFindChildViewById != null) {
                    i = R.id.layout_right_bottom;
                    FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.layout_right_bottom);
                    if (frameLayout != null) {
                        i = R.id.ll_main_left;
                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.ll_main_left);
                        if (recyclerView != null) {
                            i = R.id.ll_wanos_serach;
                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_wanos_serach);
                            if (linearLayout != null) {
                                ConstraintLayout constraintLayout = (ConstraintLayout) rootView;
                                i = R.id.tv_right_top_left;
                                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_right_top_left);
                                if (textView != null) {
                                    return new ActivityMainBinding(constraintLayout, cardView, imageView, viewFindChildViewById, frameLayout, recyclerView, linearLayout, constraintLayout, textView);
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
