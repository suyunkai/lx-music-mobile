package com.wanos.media.zero_p.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.widget.sound.SoundSeekBar;
import com.wanos.media.zero_p.R;

/* JADX INFO: loaded from: classes3.dex */
public final class WeightSoundChildBinding implements ViewBinding {
    public final AppCompatImageView ivAnimation;
    public final AppCompatImageView ivDelete;
    public final AppCompatImageView ivIcon;
    private final View rootView;
    public final SoundSeekBar seekbarSound;
    public final View vIcon;

    private WeightSoundChildBinding(View view, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2, AppCompatImageView appCompatImageView3, SoundSeekBar soundSeekBar, View view2) {
        this.rootView = view;
        this.ivAnimation = appCompatImageView;
        this.ivDelete = appCompatImageView2;
        this.ivIcon = appCompatImageView3;
        this.seekbarSound = soundSeekBar;
        this.vIcon = view2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public View getRoot() {
        return this.rootView;
    }

    public static WeightSoundChildBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        if (viewGroup == null) {
            throw new NullPointerException("parent");
        }
        layoutInflater.inflate(R.layout.weight_sound_child, viewGroup);
        return bind(viewGroup);
    }

    public static WeightSoundChildBinding bind(View view) {
        View viewFindChildViewById;
        int i = R.id.iv_animation;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = R.id.iv_delete;
            AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
            if (appCompatImageView2 != null) {
                i = R.id.iv_icon;
                AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                if (appCompatImageView3 != null) {
                    i = R.id.seekbar_sound;
                    SoundSeekBar soundSeekBar = (SoundSeekBar) ViewBindings.findChildViewById(view, i);
                    if (soundSeekBar != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.v_icon))) != null) {
                        return new WeightSoundChildBinding(view, appCompatImageView, appCompatImageView2, appCompatImageView3, soundSeekBar, viewFindChildViewById);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
