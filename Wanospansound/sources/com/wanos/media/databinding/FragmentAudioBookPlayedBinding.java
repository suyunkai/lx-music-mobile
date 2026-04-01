package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.commonlibrary.databinding.EmptyViewBinding;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class FragmentAudioBookPlayedBinding implements ViewBinding {
    public final RecyclerView audiobookMinePlayedList;
    public final EmptyViewBinding emptyView;
    private final FrameLayout rootView;

    private FragmentAudioBookPlayedBinding(FrameLayout rootView, RecyclerView audiobookMinePlayedList, EmptyViewBinding emptyView) {
        this.rootView = rootView;
        this.audiobookMinePlayedList = audiobookMinePlayedList;
        this.emptyView = emptyView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static FragmentAudioBookPlayedBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentAudioBookPlayedBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_audio_book_played, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentAudioBookPlayedBinding bind(View rootView) {
        int i = R.id.audiobook_mine_played_list;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.audiobook_mine_played_list);
        if (recyclerView != null) {
            i = R.id.empty_view;
            View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.empty_view);
            if (viewFindChildViewById != null) {
                return new FragmentAudioBookPlayedBinding((FrameLayout) rootView, recyclerView, EmptyViewBinding.bind(viewFindChildViewById));
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
