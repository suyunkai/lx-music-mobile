package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public final class PopAudioTrackEditBinding implements ViewBinding {
    public final ListView listView;
    private final LinearLayout rootView;

    private PopAudioTrackEditBinding(LinearLayout linearLayout, ListView listView) {
        this.rootView = linearLayout;
        this.listView = listView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static PopAudioTrackEditBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopAudioTrackEditBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.pop_audio_track_edit, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static PopAudioTrackEditBinding bind(View view) {
        int i = R.id.list_view;
        ListView listView = (ListView) ViewBindings.findChildViewById(view, i);
        if (listView != null) {
            return new PopAudioTrackEditBinding((LinearLayout) view, listView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
