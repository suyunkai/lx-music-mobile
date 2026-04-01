package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class FragmentSearchResultBinding implements ViewBinding {
    public final LinearLayout llAudioBook;
    public final LinearLayout llSong;
    public final LinearLayout llSongList;
    private final ConstraintLayout rootView;
    public final ConstraintLayout searchResultLayout;
    public final TextView tvAudioVideo;
    public final TextView tvSongListText;
    public final TextView tvSongText;
    public final ViewPager2 viewPager;

    private FragmentSearchResultBinding(ConstraintLayout rootView, LinearLayout llAudioBook, LinearLayout llSong, LinearLayout llSongList, ConstraintLayout searchResultLayout, TextView tvAudioVideo, TextView tvSongListText, TextView tvSongText, ViewPager2 viewPager) {
        this.rootView = rootView;
        this.llAudioBook = llAudioBook;
        this.llSong = llSong;
        this.llSongList = llSongList;
        this.searchResultLayout = searchResultLayout;
        this.tvAudioVideo = tvAudioVideo;
        this.tvSongListText = tvSongListText;
        this.tvSongText = tvSongText;
        this.viewPager = viewPager;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentSearchResultBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentSearchResultBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_search_result, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentSearchResultBinding bind(View rootView) {
        int i = R.id.ll_audio_book;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_audio_book);
        if (linearLayout != null) {
            i = R.id.ll_song;
            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_song);
            if (linearLayout2 != null) {
                i = R.id.ll_song_list;
                LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_song_list);
                if (linearLayout3 != null) {
                    i = R.id.search_result_layout;
                    ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.search_result_layout);
                    if (constraintLayout != null) {
                        i = R.id.tv_audio_video;
                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_audio_video);
                        if (textView != null) {
                            i = R.id.tv_song_list_text;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_song_list_text);
                            if (textView2 != null) {
                                i = R.id.tv_song_text;
                                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_song_text);
                                if (textView3 != null) {
                                    i = R.id.view_pager;
                                    ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(rootView, R.id.view_pager);
                                    if (viewPager2 != null) {
                                        return new FragmentSearchResultBinding((ConstraintLayout) rootView, linearLayout, linearLayout2, linearLayout3, constraintLayout, textView, textView2, textView3, viewPager2);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
