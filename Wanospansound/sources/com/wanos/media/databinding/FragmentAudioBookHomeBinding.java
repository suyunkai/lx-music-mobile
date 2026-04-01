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
public final class FragmentAudioBookHomeBinding implements ViewBinding {
    public final SmartRefreshLayout homeAudioBookRefreshLayout;
    public final RecyclerView recyclerviewAudioBookHome;
    private final ConstraintLayout rootView;

    private FragmentAudioBookHomeBinding(ConstraintLayout rootView, SmartRefreshLayout homeAudioBookRefreshLayout, RecyclerView recyclerviewAudioBookHome) {
        this.rootView = rootView;
        this.homeAudioBookRefreshLayout = homeAudioBookRefreshLayout;
        this.recyclerviewAudioBookHome = recyclerviewAudioBookHome;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentAudioBookHomeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentAudioBookHomeBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_audio_book_home, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentAudioBookHomeBinding bind(View rootView) {
        int i = R.id.home_audio_book_refresh_layout;
        SmartRefreshLayout smartRefreshLayout = (SmartRefreshLayout) ViewBindings.findChildViewById(rootView, R.id.home_audio_book_refresh_layout);
        if (smartRefreshLayout != null) {
            i = R.id.recyclerview_audio_book_home;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.recyclerview_audio_book_home);
            if (recyclerView != null) {
                return new FragmentAudioBookHomeBinding((ConstraintLayout) rootView, smartRefreshLayout, recyclerView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
