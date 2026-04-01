package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.commonlibrary.databinding.EmptyViewBinding;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ActivityMoviesListBinding implements ViewBinding {
    public final EmptyViewBinding emptyView;
    private final ConstraintLayout rootView;
    public final RecyclerView videoList;

    private ActivityMoviesListBinding(ConstraintLayout rootView, EmptyViewBinding emptyView, RecyclerView videoList) {
        this.rootView = rootView;
        this.emptyView = emptyView;
        this.videoList = videoList;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMoviesListBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMoviesListBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_movies_list, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityMoviesListBinding bind(View rootView) {
        int i = R.id.empty_view;
        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.empty_view);
        if (viewFindChildViewById != null) {
            EmptyViewBinding emptyViewBindingBind = EmptyViewBinding.bind(viewFindChildViewById);
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.video_list);
            if (recyclerView != null) {
                return new ActivityMoviesListBinding((ConstraintLayout) rootView, emptyViewBindingBind, recyclerView);
            }
            i = R.id.video_list;
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
