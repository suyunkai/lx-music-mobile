package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ActivityMusicListBinding implements ViewBinding {
    public final ImageView btnBack;
    public final LayoutMusicGroupDetailsBinding leftContent;
    public final LayoutMusicListBinding listContent;
    private final ConstraintLayout rootView;

    private ActivityMusicListBinding(ConstraintLayout rootView, ImageView btnBack, LayoutMusicGroupDetailsBinding leftContent, LayoutMusicListBinding listContent) {
        this.rootView = rootView;
        this.btnBack = btnBack;
        this.leftContent = leftContent;
        this.listContent = listContent;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMusicListBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMusicListBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_music_list, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityMusicListBinding bind(View rootView) {
        int i = R.id.btn_back;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.btn_back);
        if (imageView != null) {
            i = R.id.left_content;
            View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.left_content);
            if (viewFindChildViewById != null) {
                LayoutMusicGroupDetailsBinding layoutMusicGroupDetailsBindingBind = LayoutMusicGroupDetailsBinding.bind(viewFindChildViewById);
                View viewFindChildViewById2 = ViewBindings.findChildViewById(rootView, R.id.list_content);
                if (viewFindChildViewById2 != null) {
                    return new ActivityMusicListBinding((ConstraintLayout) rootView, imageView, layoutMusicGroupDetailsBindingBind, LayoutMusicListBinding.bind(viewFindChildViewById2));
                }
                i = R.id.list_content;
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
