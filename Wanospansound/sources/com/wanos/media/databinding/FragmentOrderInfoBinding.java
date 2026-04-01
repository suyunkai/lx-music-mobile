package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.MaterialAutoRefreshLayout;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class FragmentOrderInfoBinding implements ViewBinding {
    public final RecyclerView orderListCy;
    public final MaterialAutoRefreshLayout orderRefreshLayout;
    private final ConstraintLayout rootView;

    private FragmentOrderInfoBinding(ConstraintLayout rootView, RecyclerView orderListCy, MaterialAutoRefreshLayout orderRefreshLayout) {
        this.rootView = rootView;
        this.orderListCy = orderListCy;
        this.orderRefreshLayout = orderRefreshLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentOrderInfoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentOrderInfoBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_order_info, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentOrderInfoBinding bind(View rootView) {
        int i = R.id.order_list_cy;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.order_list_cy);
        if (recyclerView != null) {
            i = R.id.order_refresh_layout;
            MaterialAutoRefreshLayout materialAutoRefreshLayout = (MaterialAutoRefreshLayout) ViewBindings.findChildViewById(rootView, R.id.order_refresh_layout);
            if (materialAutoRefreshLayout != null) {
                return new FragmentOrderInfoBinding((ConstraintLayout) rootView, recyclerView, materialAutoRefreshLayout);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
