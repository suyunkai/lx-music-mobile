package com.wanos.media.zero_p.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.zero_p.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ItemSoundCollectBinding implements ViewBinding {
    public final AppCompatImageView ivCove;
    public final AppCompatImageView ivState;
    private final CardView rootView;
    public final View vState;

    private ItemSoundCollectBinding(CardView cardView, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2, View view) {
        this.rootView = cardView;
        this.ivCove = appCompatImageView;
        this.ivState = appCompatImageView2;
        this.vState = view;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CardView getRoot() {
        return this.rootView;
    }

    public static ItemSoundCollectBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemSoundCollectBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.item_sound_collect, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemSoundCollectBinding bind(View view) {
        View viewFindChildViewById;
        int i = R.id.iv_cove;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = R.id.iv_state;
            AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
            if (appCompatImageView2 != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.v_state))) != null) {
                return new ItemSoundCollectBinding((CardView) view, appCompatImageView, appCompatImageView2, viewFindChildViewById);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
