package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ActivityAudioBookMineBinding implements ViewBinding {
    public final FrameLayout fragmentContainer;
    private final LinearLayout rootView;
    public final TextView tabFollow;
    public final TextView tabLikeChapter;
    public final TextView tabPlayed;

    private ActivityAudioBookMineBinding(LinearLayout rootView, FrameLayout fragmentContainer, TextView tabFollow, TextView tabLikeChapter, TextView tabPlayed) {
        this.rootView = rootView;
        this.fragmentContainer = fragmentContainer;
        this.tabFollow = tabFollow;
        this.tabLikeChapter = tabLikeChapter;
        this.tabPlayed = tabPlayed;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityAudioBookMineBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityAudioBookMineBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_audio_book_mine, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityAudioBookMineBinding bind(View rootView) {
        int i = R.id.fragment_container;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.fragment_container);
        if (frameLayout != null) {
            i = R.id.tab_follow;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tab_follow);
            if (textView != null) {
                i = R.id.tab_like_chapter;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tab_like_chapter);
                if (textView2 != null) {
                    i = R.id.tab_played;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tab_played);
                    if (textView3 != null) {
                        return new ActivityAudioBookMineBinding((LinearLayout) rootView, frameLayout, textView, textView2, textView3);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
