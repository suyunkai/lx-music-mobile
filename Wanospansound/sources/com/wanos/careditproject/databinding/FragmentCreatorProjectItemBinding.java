package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public final class FragmentCreatorProjectItemBinding implements ViewBinding {
    public final CardView cardUserHead;
    public final CardView cvItem;
    public final LinearLayout itemCommunity;
    public final ImageView ivCollect;
    public final ImageView ivMyProjectItem;
    public final ImageView ivMyProjectMore;
    public final ImageView ivState;
    public final ImageView ivUserHead;
    public final ConstraintLayout projectContentLl;
    private final ConstraintLayout rootView;
    public final TextView tvProjectNameItem;
    public final TextView tvProjectTimeItem;

    private FragmentCreatorProjectItemBinding(ConstraintLayout constraintLayout, CardView cardView, CardView cardView2, LinearLayout linearLayout, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ConstraintLayout constraintLayout2, TextView textView, TextView textView2) {
        this.rootView = constraintLayout;
        this.cardUserHead = cardView;
        this.cvItem = cardView2;
        this.itemCommunity = linearLayout;
        this.ivCollect = imageView;
        this.ivMyProjectItem = imageView2;
        this.ivMyProjectMore = imageView3;
        this.ivState = imageView4;
        this.ivUserHead = imageView5;
        this.projectContentLl = constraintLayout2;
        this.tvProjectNameItem = textView;
        this.tvProjectTimeItem = textView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentCreatorProjectItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentCreatorProjectItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_creator_project_item, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentCreatorProjectItemBinding bind(View view) {
        int i = R.id.card_user_head;
        CardView cardView = (CardView) ViewBindings.findChildViewById(view, i);
        if (cardView != null) {
            i = R.id.cv_item;
            CardView cardView2 = (CardView) ViewBindings.findChildViewById(view, i);
            if (cardView2 != null) {
                i = R.id.item_community;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
                if (linearLayout != null) {
                    i = R.id.iv_collect;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                    if (imageView != null) {
                        i = R.id.iv_my_project_item;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
                        if (imageView2 != null) {
                            i = R.id.iv_my_project_more;
                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, i);
                            if (imageView3 != null) {
                                i = R.id.iv_state;
                                ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, i);
                                if (imageView4 != null) {
                                    i = R.id.iv_user_head;
                                    ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, i);
                                    if (imageView5 != null) {
                                        ConstraintLayout constraintLayout = (ConstraintLayout) view;
                                        i = R.id.tv_project_name_item;
                                        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                                        if (textView != null) {
                                            i = R.id.tv_project_time_item;
                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                                            if (textView2 != null) {
                                                return new FragmentCreatorProjectItemBinding(constraintLayout, cardView, cardView2, linearLayout, imageView, imageView2, imageView3, imageView4, imageView5, constraintLayout, textView, textView2);
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
