package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.wanos.careditproject.R;
import com.wanos.media.ui.widget.WanosTextView;

/* JADX INFO: loaded from: classes3.dex */
public final class ItemCreatorMineTabItemBinding implements ViewBinding {
    private final WanosTextView rootView;
    public final WanosTextView tvTableText;

    private ItemCreatorMineTabItemBinding(WanosTextView wanosTextView, WanosTextView wanosTextView2) {
        this.rootView = wanosTextView;
        this.tvTableText = wanosTextView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public WanosTextView getRoot() {
        return this.rootView;
    }

    public static ItemCreatorMineTabItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemCreatorMineTabItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.item_creator_mine_tab_item, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemCreatorMineTabItemBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        WanosTextView wanosTextView = (WanosTextView) view;
        return new ItemCreatorMineTabItemBinding(wanosTextView, wanosTextView);
    }
}
