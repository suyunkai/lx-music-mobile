package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.MaterialAutoRefreshLayout;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ActivityTempleteListBinding implements ViewBinding {
    private final MaterialAutoRefreshLayout rootView;
    public final RecyclerView templeteList;
    public final MaterialAutoRefreshLayout templeteRefreshLayout;

    private ActivityTempleteListBinding(MaterialAutoRefreshLayout materialAutoRefreshLayout, RecyclerView recyclerView, MaterialAutoRefreshLayout materialAutoRefreshLayout2) {
        this.rootView = materialAutoRefreshLayout;
        this.templeteList = recyclerView;
        this.templeteRefreshLayout = materialAutoRefreshLayout2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public MaterialAutoRefreshLayout getRoot() {
        return this.rootView;
    }

    public static ActivityTempleteListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityTempleteListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.activity_templete_list, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityTempleteListBinding bind(View view) {
        int i = R.id.templete_list;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
        if (recyclerView != null) {
            MaterialAutoRefreshLayout materialAutoRefreshLayout = (MaterialAutoRefreshLayout) view;
            return new ActivityTempleteListBinding(materialAutoRefreshLayout, recyclerView, materialAutoRefreshLayout);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
