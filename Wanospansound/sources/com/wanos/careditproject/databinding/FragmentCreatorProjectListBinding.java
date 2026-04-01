package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.MaterialRefreshLayout;
import com.wanos.careditproject.R;
import com.wanos.commonlibrary.databinding.EmptyViewBinding;

/* JADX INFO: loaded from: classes3.dex */
public final class FragmentCreatorProjectListBinding implements ViewBinding {
    public final EmptyViewBinding emptyView;
    public final MaterialRefreshLayout refreshLayout;
    private final MaterialRefreshLayout rootView;
    public final RecyclerView rvProjectList;

    private FragmentCreatorProjectListBinding(MaterialRefreshLayout materialRefreshLayout, EmptyViewBinding emptyViewBinding, MaterialRefreshLayout materialRefreshLayout2, RecyclerView recyclerView) {
        this.rootView = materialRefreshLayout;
        this.emptyView = emptyViewBinding;
        this.refreshLayout = materialRefreshLayout2;
        this.rvProjectList = recyclerView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public MaterialRefreshLayout getRoot() {
        return this.rootView;
    }

    public static FragmentCreatorProjectListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentCreatorProjectListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_creator_project_list, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentCreatorProjectListBinding bind(View view) {
        int i = R.id.empty_view;
        View viewFindChildViewById = ViewBindings.findChildViewById(view, i);
        if (viewFindChildViewById != null) {
            EmptyViewBinding emptyViewBindingBind = EmptyViewBinding.bind(viewFindChildViewById);
            MaterialRefreshLayout materialRefreshLayout = (MaterialRefreshLayout) view;
            int i2 = R.id.rv_project_list;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i2);
            if (recyclerView != null) {
                return new FragmentCreatorProjectListBinding(materialRefreshLayout, emptyViewBindingBind, materialRefreshLayout, recyclerView);
            }
            i = i2;
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
