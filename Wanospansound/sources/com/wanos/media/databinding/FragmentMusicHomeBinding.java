package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class FragmentMusicHomeBinding implements ViewBinding {
    public final SmartRefreshLayout homeMusicRefreshLayout;
    public final RecyclerView recyclerviewMusicHome;
    private final ConstraintLayout rootView;

    private FragmentMusicHomeBinding(ConstraintLayout rootView, SmartRefreshLayout homeMusicRefreshLayout, RecyclerView recyclerviewMusicHome) {
        this.rootView = rootView;
        this.homeMusicRefreshLayout = homeMusicRefreshLayout;
        this.recyclerviewMusicHome = recyclerviewMusicHome;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentMusicHomeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentMusicHomeBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_music_home, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentMusicHomeBinding bind(View rootView) {
        int i = R.id.home_music_refresh_layout;
        SmartRefreshLayout smartRefreshLayout = (SmartRefreshLayout) ViewBindings.findChildViewById(rootView, R.id.home_music_refresh_layout);
        if (smartRefreshLayout != null) {
            i = R.id.recyclerview_music_home;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.recyclerview_music_home);
            if (recyclerView != null) {
                return new FragmentMusicHomeBinding((ConstraintLayout) rootView, smartRefreshLayout, recyclerView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
