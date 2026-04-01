package com.wanos.media.zero_p.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.wanos.commonlibrary.databinding.FragmentLoadingBinding;
import com.wanos.media.zero_p.R;

/* JADX INFO: loaded from: classes3.dex */
public final class FragmentRelaxHomeBinding implements ViewBinding {
    public final com.wanos.commonlibrary.databinding.EmptyViewBinding emptyView;
    public final com.wanos.commonlibrary.databinding.ErrorBinding errorView;
    public final FragmentLoadingBinding loadingView;
    public final SmartRefreshLayout refresh;
    private final ConstraintLayout rootView;
    public final RecyclerView rvRelaxHome;

    private FragmentRelaxHomeBinding(ConstraintLayout constraintLayout, com.wanos.commonlibrary.databinding.EmptyViewBinding emptyViewBinding, com.wanos.commonlibrary.databinding.ErrorBinding errorBinding, FragmentLoadingBinding fragmentLoadingBinding, SmartRefreshLayout smartRefreshLayout, RecyclerView recyclerView) {
        this.rootView = constraintLayout;
        this.emptyView = emptyViewBinding;
        this.errorView = errorBinding;
        this.loadingView = fragmentLoadingBinding;
        this.refresh = smartRefreshLayout;
        this.rvRelaxHome = recyclerView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentRelaxHomeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentRelaxHomeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_relax_home, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentRelaxHomeBinding bind(View view) {
        int i = R.id.empty_view;
        View viewFindChildViewById = ViewBindings.findChildViewById(view, i);
        if (viewFindChildViewById != null) {
            com.wanos.commonlibrary.databinding.EmptyViewBinding emptyViewBindingBind = com.wanos.commonlibrary.databinding.EmptyViewBinding.bind(viewFindChildViewById);
            i = R.id.error_view;
            View viewFindChildViewById2 = ViewBindings.findChildViewById(view, i);
            if (viewFindChildViewById2 != null) {
                com.wanos.commonlibrary.databinding.ErrorBinding errorBindingBind = com.wanos.commonlibrary.databinding.ErrorBinding.bind(viewFindChildViewById2);
                i = R.id.loading_view;
                View viewFindChildViewById3 = ViewBindings.findChildViewById(view, i);
                if (viewFindChildViewById3 != null) {
                    FragmentLoadingBinding fragmentLoadingBindingBind = FragmentLoadingBinding.bind(viewFindChildViewById3);
                    i = R.id.refresh;
                    SmartRefreshLayout smartRefreshLayout = (SmartRefreshLayout) ViewBindings.findChildViewById(view, i);
                    if (smartRefreshLayout != null) {
                        i = R.id.rv_relax_home;
                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                        if (recyclerView != null) {
                            return new FragmentRelaxHomeBinding((ConstraintLayout) view, emptyViewBindingBind, errorBindingBind, fragmentLoadingBindingBind, smartRefreshLayout, recyclerView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
