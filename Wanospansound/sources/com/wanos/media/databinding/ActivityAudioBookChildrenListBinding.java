package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.MaterialAutoRefreshLayout;
import com.wanos.commonlibrary.databinding.EmptyViewBinding;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ActivityAudioBookChildrenListBinding implements ViewBinding {
    public final RecyclerView audiobookList;
    public final MaterialAutoRefreshLayout audiobookRefreshLayout;
    public final EmptyViewBinding emptyView;
    private final ConstraintLayout rootView;

    private ActivityAudioBookChildrenListBinding(ConstraintLayout rootView, RecyclerView audiobookList, MaterialAutoRefreshLayout audiobookRefreshLayout, EmptyViewBinding emptyView) {
        this.rootView = rootView;
        this.audiobookList = audiobookList;
        this.audiobookRefreshLayout = audiobookRefreshLayout;
        this.emptyView = emptyView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityAudioBookChildrenListBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityAudioBookChildrenListBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_audio_book_children_list, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityAudioBookChildrenListBinding bind(View rootView) {
        int i = R.id.audiobook_list;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.audiobook_list);
        if (recyclerView != null) {
            i = R.id.audiobook_refresh_layout;
            MaterialAutoRefreshLayout materialAutoRefreshLayout = (MaterialAutoRefreshLayout) ViewBindings.findChildViewById(rootView, R.id.audiobook_refresh_layout);
            if (materialAutoRefreshLayout != null) {
                i = R.id.empty_view;
                View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.empty_view);
                if (viewFindChildViewById != null) {
                    return new ActivityAudioBookChildrenListBinding((ConstraintLayout) rootView, recyclerView, materialAutoRefreshLayout, EmptyViewBinding.bind(viewFindChildViewById));
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
