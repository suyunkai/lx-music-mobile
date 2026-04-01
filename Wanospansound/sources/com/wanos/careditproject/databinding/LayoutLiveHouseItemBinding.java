package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public final class LayoutLiveHouseItemBinding implements ViewBinding {
    public final FrameLayout btnAudioBookPlay;
    public final CardView cvItem;
    public final ImageView ivProjectItem;
    public final FrameLayout pauseLayout;
    public final ProgressBar pbAudiobookPlay;
    public final FrameLayout playLayout;
    public final ImageView playState;
    public final ConstraintLayout projectContentLl;
    private final CardView rootView;
    public final SeekBar seekbarProgress;
    public final TextView tvDuration0;
    public final TextView tvDuration1;
    public final TextView tvPlayTime;
    public final TextView tvProjectName;

    private LayoutLiveHouseItemBinding(CardView cardView, FrameLayout frameLayout, CardView cardView2, ImageView imageView, FrameLayout frameLayout2, ProgressBar progressBar, FrameLayout frameLayout3, ImageView imageView2, ConstraintLayout constraintLayout, SeekBar seekBar, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        this.rootView = cardView;
        this.btnAudioBookPlay = frameLayout;
        this.cvItem = cardView2;
        this.ivProjectItem = imageView;
        this.pauseLayout = frameLayout2;
        this.pbAudiobookPlay = progressBar;
        this.playLayout = frameLayout3;
        this.playState = imageView2;
        this.projectContentLl = constraintLayout;
        this.seekbarProgress = seekBar;
        this.tvDuration0 = textView;
        this.tvDuration1 = textView2;
        this.tvPlayTime = textView3;
        this.tvProjectName = textView4;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CardView getRoot() {
        return this.rootView;
    }

    public static LayoutLiveHouseItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutLiveHouseItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.layout_live_house_item, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutLiveHouseItemBinding bind(View view) {
        int i = R.id.btn_audio_book_play;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
        if (frameLayout != null) {
            i = R.id.cv_item;
            CardView cardView = (CardView) ViewBindings.findChildViewById(view, i);
            if (cardView != null) {
                i = R.id.iv_project_item;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                if (imageView != null) {
                    i = R.id.pause_layout;
                    FrameLayout frameLayout2 = (FrameLayout) ViewBindings.findChildViewById(view, i);
                    if (frameLayout2 != null) {
                        i = R.id.pb_audiobook_play;
                        ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, i);
                        if (progressBar != null) {
                            i = R.id.play_layout;
                            FrameLayout frameLayout3 = (FrameLayout) ViewBindings.findChildViewById(view, i);
                            if (frameLayout3 != null) {
                                i = R.id.play_state;
                                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
                                if (imageView2 != null) {
                                    i = R.id.project_content_ll;
                                    ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                                    if (constraintLayout != null) {
                                        i = R.id.seekbar_progress;
                                        SeekBar seekBar = (SeekBar) ViewBindings.findChildViewById(view, i);
                                        if (seekBar != null) {
                                            i = R.id.tv_duration_0;
                                            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                                            if (textView != null) {
                                                i = R.id.tv_duration_1;
                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                                                if (textView2 != null) {
                                                    i = R.id.tv_play_time;
                                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                                                    if (textView3 != null) {
                                                        i = R.id.tv_project_name;
                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, i);
                                                        if (textView4 != null) {
                                                            return new LayoutLiveHouseItemBinding((CardView) view, frameLayout, cardView, imageView, frameLayout2, progressBar, frameLayout3, imageView2, constraintLayout, seekBar, textView, textView2, textView3, textView4);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
