package com.wanos.media.zero_p.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.widget.WanosTextView;
import com.wanos.media.zero_p.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ItemRelaxHomeTitleBinding implements ViewBinding {
    private final LinearLayoutCompat rootView;
    public final WanosTextView tvXqSpaceGroupMore;

    private ItemRelaxHomeTitleBinding(LinearLayoutCompat linearLayoutCompat, WanosTextView wanosTextView) {
        this.rootView = linearLayoutCompat;
        this.tvXqSpaceGroupMore = wanosTextView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayoutCompat getRoot() {
        return this.rootView;
    }

    public static ItemRelaxHomeTitleBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemRelaxHomeTitleBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.item_relax_home_title, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemRelaxHomeTitleBinding bind(View view) {
        int i = R.id.tv_xq_space_group_more;
        WanosTextView wanosTextView = (WanosTextView) ViewBindings.findChildViewById(view, i);
        if (wanosTextView != null) {
            return new ItemRelaxHomeTitleBinding((LinearLayoutCompat) view, wanosTextView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
