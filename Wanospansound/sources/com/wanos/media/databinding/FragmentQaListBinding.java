package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.MaterialAutoRefreshLayout;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class FragmentQaListBinding implements ViewBinding {
    public final TextView btnFeedback;
    public final RecyclerView recyclerView;
    public final MaterialAutoRefreshLayout refreshLayout;
    private final ConstraintLayout rootView;

    private FragmentQaListBinding(ConstraintLayout rootView, TextView btnFeedback, RecyclerView recyclerView, MaterialAutoRefreshLayout refreshLayout) {
        this.rootView = rootView;
        this.btnFeedback = btnFeedback;
        this.recyclerView = recyclerView;
        this.refreshLayout = refreshLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentQaListBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentQaListBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_qa_list, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentQaListBinding bind(View rootView) {
        int i = R.id.btn_feedback;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.btn_feedback);
        if (textView != null) {
            i = R.id.recycler_view;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.recycler_view);
            if (recyclerView != null) {
                i = R.id.refresh_layout;
                MaterialAutoRefreshLayout materialAutoRefreshLayout = (MaterialAutoRefreshLayout) ViewBindings.findChildViewById(rootView, R.id.refresh_layout);
                if (materialAutoRefreshLayout != null) {
                    return new FragmentQaListBinding((ConstraintLayout) rootView, textView, recyclerView, materialAutoRefreshLayout);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
