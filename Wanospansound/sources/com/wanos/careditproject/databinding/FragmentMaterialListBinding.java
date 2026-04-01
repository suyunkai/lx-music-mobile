package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public final class FragmentMaterialListBinding implements ViewBinding {
    public final EmptyViewCBinding emptyView;
    public final RecyclerView materialRecyclerList;
    private final FrameLayout rootView;

    private FragmentMaterialListBinding(FrameLayout frameLayout, EmptyViewCBinding emptyViewCBinding, RecyclerView recyclerView) {
        this.rootView = frameLayout;
        this.emptyView = emptyViewCBinding;
        this.materialRecyclerList = recyclerView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static FragmentMaterialListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentMaterialListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_material_list, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentMaterialListBinding bind(View view) {
        int i = R.id.empty_view;
        View viewFindChildViewById = ViewBindings.findChildViewById(view, i);
        if (viewFindChildViewById != null) {
            EmptyViewCBinding emptyViewCBindingBind = EmptyViewCBinding.bind(viewFindChildViewById);
            int i2 = R.id.material_recycler_list;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i2);
            if (recyclerView != null) {
                return new FragmentMaterialListBinding((FrameLayout) view, emptyViewCBindingBind, recyclerView);
            }
            i = i2;
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
