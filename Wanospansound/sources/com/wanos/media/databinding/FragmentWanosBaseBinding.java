package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.commonlibrary.databinding.ErrorBinding;
import com.wanos.commonlibrary.databinding.FragmentLoadingBinding;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class FragmentWanosBaseBinding implements ViewBinding {
    public final FrameLayout baseFContentViewgroup;
    public final FrameLayout baseFErrorLoadingView;
    public final ErrorBinding baseFErrorView;
    public final FragmentLoadingBinding baseFLoadingView;
    private final FrameLayout rootView;

    private FragmentWanosBaseBinding(FrameLayout frameLayout, FrameLayout frameLayout2, FrameLayout frameLayout3, ErrorBinding errorBinding, FragmentLoadingBinding fragmentLoadingBinding) {
        this.rootView = frameLayout;
        this.baseFContentViewgroup = frameLayout2;
        this.baseFErrorLoadingView = frameLayout3;
        this.baseFErrorView = errorBinding;
        this.baseFLoadingView = fragmentLoadingBinding;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static FragmentWanosBaseBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentWanosBaseBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_wanos_base, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentWanosBaseBinding bind(View view) {
        View viewFindChildViewById;
        int i = R.id.base_f_content_viewgroup;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
        if (frameLayout != null) {
            i = R.id.base_f_error_loading_view;
            FrameLayout frameLayout2 = (FrameLayout) ViewBindings.findChildViewById(view, i);
            if (frameLayout2 != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.base_f_error_view))) != null) {
                ErrorBinding errorBindingBind = ErrorBinding.bind(viewFindChildViewById);
                i = R.id.base_f_loading_view;
                View viewFindChildViewById2 = ViewBindings.findChildViewById(view, i);
                if (viewFindChildViewById2 != null) {
                    return new FragmentWanosBaseBinding((FrameLayout) view, frameLayout, frameLayout2, errorBindingBind, FragmentLoadingBinding.bind(viewFindChildViewById2));
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
