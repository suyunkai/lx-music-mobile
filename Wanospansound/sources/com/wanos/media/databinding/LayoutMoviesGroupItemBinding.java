package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class LayoutMoviesGroupItemBinding implements ViewBinding {
    public final CardView cardCover;
    public final AppCompatImageView imCover;
    public final AppCompatImageView imTag;
    private final ConstraintLayout rootView;
    public final AppCompatTextView tvJi;
    public final AppCompatTextView tvName;
    public final AppCompatTextView tvNameEx;

    private LayoutMoviesGroupItemBinding(ConstraintLayout rootView, CardView cardCover, AppCompatImageView imCover, AppCompatImageView imTag, AppCompatTextView tvJi, AppCompatTextView tvName, AppCompatTextView tvNameEx) {
        this.rootView = rootView;
        this.cardCover = cardCover;
        this.imCover = imCover;
        this.imTag = imTag;
        this.tvJi = tvJi;
        this.tvName = tvName;
        this.tvNameEx = tvNameEx;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutMoviesGroupItemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutMoviesGroupItemBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.layout_movies_group_item, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutMoviesGroupItemBinding bind(View rootView) {
        int i = R.id.card_cover;
        CardView cardView = (CardView) ViewBindings.findChildViewById(rootView, R.id.card_cover);
        if (cardView != null) {
            i = R.id.im_cover;
            AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(rootView, R.id.im_cover);
            if (appCompatImageView != null) {
                i = R.id.im_tag;
                AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(rootView, R.id.im_tag);
                if (appCompatImageView2 != null) {
                    i = R.id.tv_ji;
                    AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(rootView, R.id.tv_ji);
                    if (appCompatTextView != null) {
                        i = R.id.tv_name;
                        AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(rootView, R.id.tv_name);
                        if (appCompatTextView2 != null) {
                            i = R.id.tv_name_ex;
                            AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(rootView, R.id.tv_name_ex);
                            if (appCompatTextView3 != null) {
                                return new LayoutMoviesGroupItemBinding((ConstraintLayout) rootView, cardView, appCompatImageView, appCompatImageView2, appCompatTextView, appCompatTextView2, appCompatTextView3);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
