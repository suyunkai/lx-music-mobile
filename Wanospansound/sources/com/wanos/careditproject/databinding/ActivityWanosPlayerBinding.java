package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;
import com.wanos.careditproject.view.audiotrackedit.ActivityTopLayout;

/* JADX INFO: loaded from: classes3.dex */
public final class ActivityWanosPlayerBinding implements ViewBinding {
    public final ImageView btnEdit;
    public final ImageView editingBack;
    public final ImageView ivShowName;
    public final FrameLayout pagePlayer;
    private final FrameLayout rootView;
    public final Spinner spinnerShowType;
    public final ActivityTopLayout topLayout;

    private ActivityWanosPlayerBinding(FrameLayout frameLayout, ImageView imageView, ImageView imageView2, ImageView imageView3, FrameLayout frameLayout2, Spinner spinner, ActivityTopLayout activityTopLayout) {
        this.rootView = frameLayout;
        this.btnEdit = imageView;
        this.editingBack = imageView2;
        this.ivShowName = imageView3;
        this.pagePlayer = frameLayout2;
        this.spinnerShowType = spinner;
        this.topLayout = activityTopLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static ActivityWanosPlayerBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityWanosPlayerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.activity_wanos_player, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityWanosPlayerBinding bind(View view) {
        int i = R.id.btn_edit;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.editing_back;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
            if (imageView2 != null) {
                i = R.id.iv_show_name;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, i);
                if (imageView3 != null) {
                    i = R.id.page_player;
                    FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
                    if (frameLayout != null) {
                        i = R.id.spinner_show_type;
                        Spinner spinner = (Spinner) ViewBindings.findChildViewById(view, i);
                        if (spinner != null) {
                            i = R.id.top_layout;
                            ActivityTopLayout activityTopLayout = (ActivityTopLayout) ViewBindings.findChildViewById(view, i);
                            if (activityTopLayout != null) {
                                return new ActivityWanosPlayerBinding((FrameLayout) view, imageView, imageView2, imageView3, frameLayout, spinner, activityTopLayout);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
