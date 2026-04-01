package com.wanos.media.zero_p.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.widget.WanosTextView;
import com.wanos.media.widget.WanosVolumeView;
import com.wanos.media.zero_p.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ItemVolumeBinding implements ViewBinding {
    public final AppCompatImageView ivAudioIcon;
    private final LinearLayoutCompat rootView;
    public final WanosTextView tvAudioName;
    public final WanosVolumeView volume;

    private ItemVolumeBinding(LinearLayoutCompat linearLayoutCompat, AppCompatImageView appCompatImageView, WanosTextView wanosTextView, WanosVolumeView wanosVolumeView) {
        this.rootView = linearLayoutCompat;
        this.ivAudioIcon = appCompatImageView;
        this.tvAudioName = wanosTextView;
        this.volume = wanosVolumeView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayoutCompat getRoot() {
        return this.rootView;
    }

    public static ItemVolumeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemVolumeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.item_volume, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemVolumeBinding bind(View view) {
        int i = R.id.iv_audio_icon;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = R.id.tv_audio_name;
            WanosTextView wanosTextView = (WanosTextView) ViewBindings.findChildViewById(view, i);
            if (wanosTextView != null) {
                i = R.id.volume;
                WanosVolumeView wanosVolumeView = (WanosVolumeView) ViewBindings.findChildViewById(view, i);
                if (wanosVolumeView != null) {
                    return new ItemVolumeBinding((LinearLayoutCompat) view, appCompatImageView, wanosTextView, wanosVolumeView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
