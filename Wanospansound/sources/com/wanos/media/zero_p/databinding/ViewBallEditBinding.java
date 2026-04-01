package com.wanos.media.zero_p.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.widget.sound.SoundSeekBar;
import com.wanos.media.zero_p.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ViewBallEditBinding implements ViewBinding {
    public final FrameLayout flEditView;
    public final AppCompatImageView ivDelete;
    private final FrameLayout rootView;
    public final SoundSeekBar seekBarEdit;

    private ViewBallEditBinding(FrameLayout frameLayout, FrameLayout frameLayout2, AppCompatImageView appCompatImageView, SoundSeekBar soundSeekBar) {
        this.rootView = frameLayout;
        this.flEditView = frameLayout2;
        this.ivDelete = appCompatImageView;
        this.seekBarEdit = soundSeekBar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static ViewBallEditBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ViewBallEditBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.view_ball_edit, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ViewBallEditBinding bind(View view) {
        FrameLayout frameLayout = (FrameLayout) view;
        int i = R.id.iv_delete;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = R.id.seekBar_edit;
            SoundSeekBar soundSeekBar = (SoundSeekBar) ViewBindings.findChildViewById(view, i);
            if (soundSeekBar != null) {
                return new ViewBallEditBinding(frameLayout, frameLayout, appCompatImageView, soundSeekBar);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
