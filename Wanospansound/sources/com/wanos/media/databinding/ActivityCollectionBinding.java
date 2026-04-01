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
public final class ActivityCollectionBinding implements ViewBinding {
    public final FrameLayout fragmentContainer;
    public final LinearLayout llLeft;
    private final ConstraintLayout rootView;
    public final TextView tabAudiobookAlbum;
    public final TextView tabChapter;
    public final TextView tabMusic;
    public final TextView tabMusicAlbum;

    private ActivityCollectionBinding(ConstraintLayout rootView, FrameLayout fragmentContainer, LinearLayout llLeft, TextView tabAudiobookAlbum, TextView tabChapter, TextView tabMusic, TextView tabMusicAlbum) {
        this.rootView = rootView;
        this.fragmentContainer = fragmentContainer;
        this.llLeft = llLeft;
        this.tabAudiobookAlbum = tabAudiobookAlbum;
        this.tabChapter = tabChapter;
        this.tabMusic = tabMusic;
        this.tabMusicAlbum = tabMusicAlbum;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityCollectionBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityCollectionBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_collection, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityCollectionBinding bind(View rootView) {
        int i = R.id.fragment_container;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.fragment_container);
        if (frameLayout != null) {
            i = R.id.ll_left;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_left);
            if (linearLayout != null) {
                i = R.id.tab_audiobook_album;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tab_audiobook_album);
                if (textView != null) {
                    i = R.id.tab_chapter;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tab_chapter);
                    if (textView2 != null) {
                        i = R.id.tab_music;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tab_music);
                        if (textView3 != null) {
                            i = R.id.tab_music_album;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tab_music_album);
                            if (textView4 != null) {
                                return new ActivityCollectionBinding((ConstraintLayout) rootView, frameLayout, linearLayout, textView, textView2, textView3, textView4);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
