package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public final class LayoutCreatorHomeRecyclerviewHeaderBinding implements ViewBinding {
    public final Barrier barrier;
    public final CardView cdTopFour;
    public final CardView cdTopOne;
    public final CardView cdTopThree;
    public final CardView cdTopTwo;
    public final ImageView ivAdd;
    public final ImageView ivTopFour;
    public final ImageView ivTopThree;
    public final ImageView ivTopTwo;
    private final ConstraintLayout rootView;
    public final TextView tvTempleteAll;

    private LayoutCreatorHomeRecyclerviewHeaderBinding(ConstraintLayout constraintLayout, Barrier barrier, CardView cardView, CardView cardView2, CardView cardView3, CardView cardView4, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, TextView textView) {
        this.rootView = constraintLayout;
        this.barrier = barrier;
        this.cdTopFour = cardView;
        this.cdTopOne = cardView2;
        this.cdTopThree = cardView3;
        this.cdTopTwo = cardView4;
        this.ivAdd = imageView;
        this.ivTopFour = imageView2;
        this.ivTopThree = imageView3;
        this.ivTopTwo = imageView4;
        this.tvTempleteAll = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutCreatorHomeRecyclerviewHeaderBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutCreatorHomeRecyclerviewHeaderBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.layout_creator_home_recyclerview_header, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutCreatorHomeRecyclerviewHeaderBinding bind(View view) {
        int i = R.id.barrier;
        Barrier barrier = (Barrier) ViewBindings.findChildViewById(view, i);
        if (barrier != null) {
            i = R.id.cd_top_four;
            CardView cardView = (CardView) ViewBindings.findChildViewById(view, i);
            if (cardView != null) {
                i = R.id.cd_top_one;
                CardView cardView2 = (CardView) ViewBindings.findChildViewById(view, i);
                if (cardView2 != null) {
                    i = R.id.cd_top_three;
                    CardView cardView3 = (CardView) ViewBindings.findChildViewById(view, i);
                    if (cardView3 != null) {
                        i = R.id.cd_top_two;
                        CardView cardView4 = (CardView) ViewBindings.findChildViewById(view, i);
                        if (cardView4 != null) {
                            i = R.id.iv_add;
                            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                            if (imageView != null) {
                                i = R.id.iv_top_four;
                                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
                                if (imageView2 != null) {
                                    i = R.id.iv_top_three;
                                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, i);
                                    if (imageView3 != null) {
                                        i = R.id.iv_top_two;
                                        ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, i);
                                        if (imageView4 != null) {
                                            i = R.id.tv_templete_all;
                                            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                                            if (textView != null) {
                                                return new LayoutCreatorHomeRecyclerviewHeaderBinding((ConstraintLayout) view, barrier, cardView, cardView2, cardView3, cardView4, imageView, imageView2, imageView3, imageView4, textView);
                                            }
                                        }
                                    }
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
