package com.wanos.media.juyihall.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.juyihall.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ItemRec2TagBinding implements ViewBinding {
    public final CardView avatar;
    private final CardView rootView;
    public final TextView tvName;

    private ItemRec2TagBinding(CardView cardView, CardView cardView2, TextView textView) {
        this.rootView = cardView;
        this.avatar = cardView2;
        this.tvName = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CardView getRoot() {
        return this.rootView;
    }

    public static ItemRec2TagBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemRec2TagBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.item_rec2_tag, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemRec2TagBinding bind(View view) {
        CardView cardView = (CardView) view;
        int i = R.id.tv_name;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
        if (textView != null) {
            return new ItemRec2TagBinding(cardView, cardView, textView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
