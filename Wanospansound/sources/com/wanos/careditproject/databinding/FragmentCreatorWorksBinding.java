package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;
import com.wanos.commonlibrary.databinding.EmptyViewBinding;

/* JADX INFO: loaded from: classes3.dex */
public final class FragmentCreatorWorksBinding implements ViewBinding {
    public final EmptyViewBinding emptyView;
    public final RecyclerView recyclerView;
    private final ConstraintLayout rootView;
    public final RecyclerView rvTagList;

    private FragmentCreatorWorksBinding(ConstraintLayout constraintLayout, EmptyViewBinding emptyViewBinding, RecyclerView recyclerView, RecyclerView recyclerView2) {
        this.rootView = constraintLayout;
        this.emptyView = emptyViewBinding;
        this.recyclerView = recyclerView;
        this.rvTagList = recyclerView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentCreatorWorksBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentCreatorWorksBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_creator_works, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentCreatorWorksBinding bind(View view) {
        int i = R.id.empty_view;
        View viewFindChildViewById = ViewBindings.findChildViewById(view, i);
        if (viewFindChildViewById != null) {
            EmptyViewBinding emptyViewBindingBind = EmptyViewBinding.bind(viewFindChildViewById);
            int i2 = R.id.recycler_view;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i2);
            if (recyclerView != null) {
                i2 = R.id.rv_tag_list;
                RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(view, i2);
                if (recyclerView2 != null) {
                    return new FragmentCreatorWorksBinding((ConstraintLayout) view, emptyViewBindingBind, recyclerView, recyclerView2);
                }
            }
            i = i2;
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
