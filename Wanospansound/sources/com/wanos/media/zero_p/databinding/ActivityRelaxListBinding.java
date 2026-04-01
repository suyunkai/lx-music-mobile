package com.wanos.media.zero_p.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.commonlibrary.databinding.FragmentLoadingBinding;
import com.wanos.media.widget.WanosTextView;
import com.wanos.media.zero_p.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ActivityRelaxListBinding implements ViewBinding {
    public final AppCompatImageView btnBack;
    public final com.wanos.commonlibrary.databinding.EmptyViewBinding emptyView;
    public final com.wanos.commonlibrary.databinding.ErrorBinding errorView;
    public final FragmentLoadingBinding loadingView;
    private final ConstraintLayout rootView;
    public final RecyclerView rvRelaxList;
    public final WanosTextView tvTitle;

    private ActivityRelaxListBinding(ConstraintLayout constraintLayout, AppCompatImageView appCompatImageView, com.wanos.commonlibrary.databinding.EmptyViewBinding emptyViewBinding, com.wanos.commonlibrary.databinding.ErrorBinding errorBinding, FragmentLoadingBinding fragmentLoadingBinding, RecyclerView recyclerView, WanosTextView wanosTextView) {
        this.rootView = constraintLayout;
        this.btnBack = appCompatImageView;
        this.emptyView = emptyViewBinding;
        this.errorView = errorBinding;
        this.loadingView = fragmentLoadingBinding;
        this.rvRelaxList = recyclerView;
        this.tvTitle = wanosTextView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityRelaxListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityRelaxListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.activity_relax_list, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityRelaxListBinding bind(View view) {
        View viewFindChildViewById;
        int i = R.id.btn_back;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.empty_view))) != null) {
            com.wanos.commonlibrary.databinding.EmptyViewBinding emptyViewBindingBind = com.wanos.commonlibrary.databinding.EmptyViewBinding.bind(viewFindChildViewById);
            i = R.id.error_view;
            View viewFindChildViewById2 = ViewBindings.findChildViewById(view, i);
            if (viewFindChildViewById2 != null) {
                com.wanos.commonlibrary.databinding.ErrorBinding errorBindingBind = com.wanos.commonlibrary.databinding.ErrorBinding.bind(viewFindChildViewById2);
                i = R.id.loading_view;
                View viewFindChildViewById3 = ViewBindings.findChildViewById(view, i);
                if (viewFindChildViewById3 != null) {
                    FragmentLoadingBinding fragmentLoadingBindingBind = FragmentLoadingBinding.bind(viewFindChildViewById3);
                    i = R.id.rv_relax_list;
                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                    if (recyclerView != null) {
                        i = R.id.tv_title;
                        WanosTextView wanosTextView = (WanosTextView) ViewBindings.findChildViewById(view, i);
                        if (wanosTextView != null) {
                            return new ActivityRelaxListBinding((ConstraintLayout) view, appCompatImageView, emptyViewBindingBind, errorBindingBind, fragmentLoadingBindingBind, recyclerView, wanosTextView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
