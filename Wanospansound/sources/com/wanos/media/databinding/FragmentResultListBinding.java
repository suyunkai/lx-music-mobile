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
public final class FragmentResultListBinding implements ViewBinding {
    public final RecyclerView resultListCy;
    private final ConstraintLayout rootView;
    public final MaterialAutoRefreshLayout searchResultRefreshLayout;

    private FragmentResultListBinding(ConstraintLayout rootView, RecyclerView resultListCy, MaterialAutoRefreshLayout searchResultRefreshLayout) {
        this.rootView = rootView;
        this.resultListCy = resultListCy;
        this.searchResultRefreshLayout = searchResultRefreshLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentResultListBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentResultListBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_result_list, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentResultListBinding bind(View rootView) {
        int i = R.id.result_list_cy;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.result_list_cy);
        if (recyclerView != null) {
            i = R.id.search_result_refresh_layout;
            MaterialAutoRefreshLayout materialAutoRefreshLayout = (MaterialAutoRefreshLayout) ViewBindings.findChildViewById(rootView, R.id.search_result_refresh_layout);
            if (materialAutoRefreshLayout != null) {
                return new FragmentResultListBinding((ConstraintLayout) rootView, recyclerView, materialAutoRefreshLayout);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
