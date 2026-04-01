package com.wanos.media.zero_p.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.widget.video.ZeroVideoPageView;
import com.wanos.media.zero_p.R;

/* JADX INFO: loaded from: classes3.dex */
public final class FragmentZeroNormalBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final ZeroVideoPageView wanosPage;

    private FragmentZeroNormalBinding(ConstraintLayout constraintLayout, ZeroVideoPageView zeroVideoPageView) {
        this.rootView = constraintLayout;
        this.wanosPage = zeroVideoPageView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentZeroNormalBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentZeroNormalBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_zero_normal, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentZeroNormalBinding bind(View view) {
        int i = R.id.wanos_page;
        ZeroVideoPageView zeroVideoPageView = (ZeroVideoPageView) ViewBindings.findChildViewById(view, i);
        if (zeroVideoPageView != null) {
            return new FragmentZeroNormalBinding((ConstraintLayout) view, zeroVideoPageView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
