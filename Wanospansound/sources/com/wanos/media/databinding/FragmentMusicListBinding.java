package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.MaterialAutoRefreshLayout;
import com.wanos.commonlibrary.databinding.EmptyViewBinding;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class FragmentMusicListBinding implements ViewBinding {
    public final EmptyViewBinding emptyView;
    public final MaterialAutoRefreshLayout meterialAutoRefreshLayout;
    public final RecyclerView recyclerviewMusicList;
    private final FrameLayout rootView;

    private FragmentMusicListBinding(FrameLayout rootView, EmptyViewBinding emptyView, MaterialAutoRefreshLayout meterialAutoRefreshLayout, RecyclerView recyclerviewMusicList) {
        this.rootView = rootView;
        this.emptyView = emptyView;
        this.meterialAutoRefreshLayout = meterialAutoRefreshLayout;
        this.recyclerviewMusicList = recyclerviewMusicList;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static FragmentMusicListBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentMusicListBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_music_list, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentMusicListBinding bind(View rootView) {
        int i = R.id.empty_view;
        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.empty_view);
        if (viewFindChildViewById != null) {
            EmptyViewBinding emptyViewBindingBind = EmptyViewBinding.bind(viewFindChildViewById);
            int i2 = R.id.meterial_auto_refresh_layout;
            MaterialAutoRefreshLayout materialAutoRefreshLayout = (MaterialAutoRefreshLayout) ViewBindings.findChildViewById(rootView, R.id.meterial_auto_refresh_layout);
            if (materialAutoRefreshLayout != null) {
                i2 = R.id.recyclerview_music_list;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.recyclerview_music_list);
                if (recyclerView != null) {
                    return new FragmentMusicListBinding((FrameLayout) rootView, emptyViewBindingBind, materialAutoRefreshLayout, recyclerView);
                }
            }
            i = i2;
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
