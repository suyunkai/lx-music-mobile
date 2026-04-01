package com.wanos.media.zero_p.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.widget.WanosTextView;
import com.wanos.media.zero_p.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ItemSoundMenuBinding implements ViewBinding {
    public final AppCompatImageView ivFileFlag;
    public final AppCompatImageView ivSound;
    public final AppCompatImageView ivVip;
    public final ProgressBar loading;
    private final LinearLayoutCompat rootView;
    public final WanosTextView tvSoundName;
    public final View vBg;

    private ItemSoundMenuBinding(LinearLayoutCompat linearLayoutCompat, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2, AppCompatImageView appCompatImageView3, ProgressBar progressBar, WanosTextView wanosTextView, View view) {
        this.rootView = linearLayoutCompat;
        this.ivFileFlag = appCompatImageView;
        this.ivSound = appCompatImageView2;
        this.ivVip = appCompatImageView3;
        this.loading = progressBar;
        this.tvSoundName = wanosTextView;
        this.vBg = view;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayoutCompat getRoot() {
        return this.rootView;
    }

    public static ItemSoundMenuBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemSoundMenuBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.item_sound_menu, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemSoundMenuBinding bind(View view) {
        View viewFindChildViewById;
        int i = R.id.iv_file_flag;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = R.id.iv_sound;
            AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
            if (appCompatImageView2 != null) {
                i = R.id.iv_vip;
                AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                if (appCompatImageView3 != null) {
                    i = R.id.loading;
                    ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, i);
                    if (progressBar != null) {
                        i = R.id.tv_sound_name;
                        WanosTextView wanosTextView = (WanosTextView) ViewBindings.findChildViewById(view, i);
                        if (wanosTextView != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.v_bg))) != null) {
                            return new ItemSoundMenuBinding((LinearLayoutCompat) view, appCompatImageView, appCompatImageView2, appCompatImageView3, progressBar, wanosTextView, viewFindChildViewById);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
