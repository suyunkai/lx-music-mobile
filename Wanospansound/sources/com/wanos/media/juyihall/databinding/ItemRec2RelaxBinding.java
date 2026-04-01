package com.wanos.media.juyihall.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.juyihall.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ItemRec2RelaxBinding implements ViewBinding {
    public final CardView avatar;
    public final ImageView ivAvatar;
    private final ConstraintLayout rootView;
    public final TextView tvName;

    private ItemRec2RelaxBinding(ConstraintLayout constraintLayout, CardView cardView, ImageView imageView, TextView textView) {
        this.rootView = constraintLayout;
        this.avatar = cardView;
        this.ivAvatar = imageView;
        this.tvName = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemRec2RelaxBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemRec2RelaxBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.item_rec2_relax, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemRec2RelaxBinding bind(View view) {
        int i = R.id.avatar;
        CardView cardView = (CardView) ViewBindings.findChildViewById(view, i);
        if (cardView != null) {
            i = R.id.iv_avatar;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
            if (imageView != null) {
                i = R.id.tv_name;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView != null) {
                    return new ItemRec2RelaxBinding((ConstraintLayout) view, cardView, imageView, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
