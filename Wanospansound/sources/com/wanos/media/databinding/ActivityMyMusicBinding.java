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
public final class ActivityMyMusicBinding implements ViewBinding {
    public final FrameLayout layoutRight;
    public final LinearLayout llLeft;
    private final ConstraintLayout rootView;
    public final TextView tabMyMusic;
    public final TextView tabMyMusicGroup;
    public final TextView tabMyMusicHistory;

    private ActivityMyMusicBinding(ConstraintLayout rootView, FrameLayout layoutRight, LinearLayout llLeft, TextView tabMyMusic, TextView tabMyMusicGroup, TextView tabMyMusicHistory) {
        this.rootView = rootView;
        this.layoutRight = layoutRight;
        this.llLeft = llLeft;
        this.tabMyMusic = tabMyMusic;
        this.tabMyMusicGroup = tabMyMusicGroup;
        this.tabMyMusicHistory = tabMyMusicHistory;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMyMusicBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMyMusicBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_my_music, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityMyMusicBinding bind(View rootView) {
        int i = R.id.layout_right;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.layout_right);
        if (frameLayout != null) {
            i = R.id.ll_left;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_left);
            if (linearLayout != null) {
                i = R.id.tab_my_music;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tab_my_music);
                if (textView != null) {
                    i = R.id.tab_my_music_group;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tab_my_music_group);
                    if (textView2 != null) {
                        i = R.id.tab_my_music_history;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tab_my_music_history);
                        if (textView3 != null) {
                            return new ActivityMyMusicBinding((ConstraintLayout) rootView, frameLayout, linearLayout, textView, textView2, textView3);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
