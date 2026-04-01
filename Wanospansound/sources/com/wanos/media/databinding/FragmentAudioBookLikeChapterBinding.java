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
public final class FragmentAudioBookLikeChapterBinding implements ViewBinding {
    public final RecyclerView audiobookMineChapterList;
    public final MaterialAutoRefreshLayout audiobookRefreshLayout;
    public final EmptyViewBinding emptyView;
    private final FrameLayout rootView;

    private FragmentAudioBookLikeChapterBinding(FrameLayout rootView, RecyclerView audiobookMineChapterList, MaterialAutoRefreshLayout audiobookRefreshLayout, EmptyViewBinding emptyView) {
        this.rootView = rootView;
        this.audiobookMineChapterList = audiobookMineChapterList;
        this.audiobookRefreshLayout = audiobookRefreshLayout;
        this.emptyView = emptyView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static FragmentAudioBookLikeChapterBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentAudioBookLikeChapterBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_audio_book_like_chapter, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentAudioBookLikeChapterBinding bind(View rootView) {
        int i = R.id.audiobook_mine_chapter_list;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.audiobook_mine_chapter_list);
        if (recyclerView != null) {
            i = R.id.audiobook_refresh_layout;
            MaterialAutoRefreshLayout materialAutoRefreshLayout = (MaterialAutoRefreshLayout) ViewBindings.findChildViewById(rootView, R.id.audiobook_refresh_layout);
            if (materialAutoRefreshLayout != null) {
                i = R.id.empty_view;
                View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.empty_view);
                if (viewFindChildViewById != null) {
                    return new FragmentAudioBookLikeChapterBinding((FrameLayout) rootView, recyclerView, materialAutoRefreshLayout, EmptyViewBinding.bind(viewFindChildViewById));
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
