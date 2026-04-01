package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public final class LayoutAudioTrackAddBinding implements ViewBinding {
    private final FrameLayout rootView;
    public final LinearLayout viewAdd;

    private LayoutAudioTrackAddBinding(FrameLayout frameLayout, LinearLayout linearLayout) {
        this.rootView = frameLayout;
        this.viewAdd = linearLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static LayoutAudioTrackAddBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutAudioTrackAddBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.layout_audio_track_add, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutAudioTrackAddBinding bind(View view) {
        int i = R.id.view_add;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
        if (linearLayout != null) {
            return new LayoutAudioTrackAddBinding((FrameLayout) view, linearLayout);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
