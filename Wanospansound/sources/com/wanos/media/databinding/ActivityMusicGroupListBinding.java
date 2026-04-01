package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.MaterialAutoRefreshLayout;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ActivityMusicGroupListBinding implements ViewBinding {
    public final MaterialAutoRefreshLayout meterialAutoRefreshLayout;
    public final RecyclerView recyclerviewMusicGroupList;
    private final MaterialAutoRefreshLayout rootView;

    private ActivityMusicGroupListBinding(MaterialAutoRefreshLayout rootView, MaterialAutoRefreshLayout meterialAutoRefreshLayout, RecyclerView recyclerviewMusicGroupList) {
        this.rootView = rootView;
        this.meterialAutoRefreshLayout = meterialAutoRefreshLayout;
        this.recyclerviewMusicGroupList = recyclerviewMusicGroupList;
    }

    @Override // androidx.viewbinding.ViewBinding
    public MaterialAutoRefreshLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMusicGroupListBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMusicGroupListBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_music_group_list, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityMusicGroupListBinding bind(View rootView) {
        MaterialAutoRefreshLayout materialAutoRefreshLayout = (MaterialAutoRefreshLayout) rootView;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.recyclerview_music_group_list);
        if (recyclerView != null) {
            return new ActivityMusicGroupListBinding(materialAutoRefreshLayout, materialAutoRefreshLayout, recyclerView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.recyclerview_music_group_list)));
    }
}
