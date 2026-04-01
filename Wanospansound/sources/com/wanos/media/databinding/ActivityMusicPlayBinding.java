package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ActivityMusicPlayBinding implements ViewBinding {
    public final LayoutMusicPlayDetailsBinding leftContent;
    public final LayoutMusicPlayRightContentBinding rightContent;
    private final LinearLayout rootView;

    private ActivityMusicPlayBinding(LinearLayout rootView, LayoutMusicPlayDetailsBinding leftContent, LayoutMusicPlayRightContentBinding rightContent) {
        this.rootView = rootView;
        this.leftContent = leftContent;
        this.rightContent = rightContent;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMusicPlayBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMusicPlayBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_music_play, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityMusicPlayBinding bind(View rootView) {
        int i = R.id.left_content;
        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.left_content);
        if (viewFindChildViewById != null) {
            LayoutMusicPlayDetailsBinding layoutMusicPlayDetailsBindingBind = LayoutMusicPlayDetailsBinding.bind(viewFindChildViewById);
            View viewFindChildViewById2 = ViewBindings.findChildViewById(rootView, R.id.right_content);
            if (viewFindChildViewById2 != null) {
                return new ActivityMusicPlayBinding((LinearLayout) rootView, layoutMusicPlayDetailsBindingBind, LayoutMusicPlayRightContentBinding.bind(viewFindChildViewById2));
            }
            i = R.id.right_content;
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
