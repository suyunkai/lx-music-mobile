package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ActivityRecentPlayBinding implements ViewBinding {
    public final FrameLayout fragmentContainer;
    public final LinearLayout llLeft;
    private final ConstraintLayout rootView;
    public final TextView tabAudiobook;
    public final TextView tabMusic;

    private ActivityRecentPlayBinding(ConstraintLayout rootView, FrameLayout fragmentContainer, LinearLayout llLeft, TextView tabAudiobook, TextView tabMusic) {
        this.rootView = rootView;
        this.fragmentContainer = fragmentContainer;
        this.llLeft = llLeft;
        this.tabAudiobook = tabAudiobook;
        this.tabMusic = tabMusic;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityRecentPlayBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityRecentPlayBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_recent_play, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityRecentPlayBinding bind(View rootView) {
        int i = R.id.fragment_container;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.fragment_container);
        if (frameLayout != null) {
            i = R.id.ll_left;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_left);
            if (linearLayout != null) {
                i = R.id.tab_audiobook;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tab_audiobook);
                if (textView != null) {
                    i = R.id.tab_music;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tab_music);
                    if (textView2 != null) {
                        return new ActivityRecentPlayBinding((ConstraintLayout) rootView, frameLayout, linearLayout, textView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
