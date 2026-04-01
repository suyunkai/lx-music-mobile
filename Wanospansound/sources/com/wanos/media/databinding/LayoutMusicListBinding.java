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
import com.wanos.commonlibrary.databinding.ErrorBinding;
import com.wanos.commonlibrary.databinding.FragmentLoadingBinding;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class LayoutMusicListBinding implements ViewBinding {
    public final EmptyViewBinding emptyView;
    public final ErrorBinding listErrorView;
    public final FragmentLoadingBinding listLoadingView;
    public final MaterialAutoRefreshLayout meterialAutoRefreshLayout;
    public final RecyclerView recyclerviewMusicList;
    private final FrameLayout rootView;

    private LayoutMusicListBinding(FrameLayout rootView, EmptyViewBinding emptyView, ErrorBinding listErrorView, FragmentLoadingBinding listLoadingView, MaterialAutoRefreshLayout meterialAutoRefreshLayout, RecyclerView recyclerviewMusicList) {
        this.rootView = rootView;
        this.emptyView = emptyView;
        this.listErrorView = listErrorView;
        this.listLoadingView = listLoadingView;
        this.meterialAutoRefreshLayout = meterialAutoRefreshLayout;
        this.recyclerviewMusicList = recyclerviewMusicList;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static LayoutMusicListBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutMusicListBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.layout_music_list, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutMusicListBinding bind(View rootView) {
        int i = R.id.empty_view;
        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.empty_view);
        if (viewFindChildViewById != null) {
            EmptyViewBinding emptyViewBindingBind = EmptyViewBinding.bind(viewFindChildViewById);
            i = R.id.list_error_view;
            View viewFindChildViewById2 = ViewBindings.findChildViewById(rootView, R.id.list_error_view);
            if (viewFindChildViewById2 != null) {
                ErrorBinding errorBindingBind = ErrorBinding.bind(viewFindChildViewById2);
                i = R.id.list_loading_view;
                View viewFindChildViewById3 = ViewBindings.findChildViewById(rootView, R.id.list_loading_view);
                if (viewFindChildViewById3 != null) {
                    FragmentLoadingBinding fragmentLoadingBindingBind = FragmentLoadingBinding.bind(viewFindChildViewById3);
                    i = R.id.meterial_auto_refresh_layout;
                    MaterialAutoRefreshLayout materialAutoRefreshLayout = (MaterialAutoRefreshLayout) ViewBindings.findChildViewById(rootView, R.id.meterial_auto_refresh_layout);
                    if (materialAutoRefreshLayout != null) {
                        i = R.id.recyclerview_music_list;
                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.recyclerview_music_list);
                        if (recyclerView != null) {
                            return new LayoutMusicListBinding((FrameLayout) rootView, emptyViewBindingBind, errorBindingBind, fragmentLoadingBindingBind, materialAutoRefreshLayout, recyclerView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
