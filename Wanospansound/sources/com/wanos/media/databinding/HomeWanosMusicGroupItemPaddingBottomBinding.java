package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class HomeWanosMusicGroupItemPaddingBottomBinding implements ViewBinding {
    private final View rootView;

    private HomeWanosMusicGroupItemPaddingBottomBinding(View rootView) {
        this.rootView = rootView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public View getRoot() {
        return this.rootView;
    }

    public static HomeWanosMusicGroupItemPaddingBottomBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static HomeWanosMusicGroupItemPaddingBottomBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.home_wanos_music_group_item_padding_bottom, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static HomeWanosMusicGroupItemPaddingBottomBinding bind(View rootView) {
        if (rootView == null) {
            throw new NullPointerException("rootView");
        }
        return new HomeWanosMusicGroupItemPaddingBottomBinding(rootView);
    }
}
