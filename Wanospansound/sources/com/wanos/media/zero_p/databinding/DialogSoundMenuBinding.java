package com.wanos.media.zero_p.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.wanos.commonlibrary.databinding.FragmentLoadingBinding;
import com.wanos.media.zero_p.R;

/* JADX INFO: loaded from: classes3.dex */
public final class DialogSoundMenuBinding implements ViewBinding {
    public final AppCompatImageView btnFinish;
    public final ConstraintLayout clDialog;
    public final AppCompatImageView ivBg;
    public final ErrorCcBinding layoutError;
    public final FragmentLoadingBinding layoutLoading;
    private final LinearLayoutCompat rootView;
    public final TabLayout tabSound;
    public final View vBlurColor;
    public final ViewPager2 vpTab;

    private DialogSoundMenuBinding(LinearLayoutCompat linearLayoutCompat, AppCompatImageView appCompatImageView, ConstraintLayout constraintLayout, AppCompatImageView appCompatImageView2, ErrorCcBinding errorCcBinding, FragmentLoadingBinding fragmentLoadingBinding, TabLayout tabLayout, View view, ViewPager2 viewPager2) {
        this.rootView = linearLayoutCompat;
        this.btnFinish = appCompatImageView;
        this.clDialog = constraintLayout;
        this.ivBg = appCompatImageView2;
        this.layoutError = errorCcBinding;
        this.layoutLoading = fragmentLoadingBinding;
        this.tabSound = tabLayout;
        this.vBlurColor = view;
        this.vpTab = viewPager2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayoutCompat getRoot() {
        return this.rootView;
    }

    public static DialogSoundMenuBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogSoundMenuBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.dialog_sound_menu, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogSoundMenuBinding bind(View view) {
        View viewFindChildViewById;
        View viewFindChildViewById2;
        int i = R.id.btn_finish;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = R.id.cl_dialog;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
            if (constraintLayout != null) {
                i = R.id.iv_bg;
                AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                if (appCompatImageView2 != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.layout_error))) != null) {
                    ErrorCcBinding errorCcBindingBind = ErrorCcBinding.bind(viewFindChildViewById);
                    i = R.id.layout_loading;
                    View viewFindChildViewById3 = ViewBindings.findChildViewById(view, i);
                    if (viewFindChildViewById3 != null) {
                        FragmentLoadingBinding fragmentLoadingBindingBind = FragmentLoadingBinding.bind(viewFindChildViewById3);
                        i = R.id.tab_sound;
                        TabLayout tabLayout = (TabLayout) ViewBindings.findChildViewById(view, i);
                        if (tabLayout != null && (viewFindChildViewById2 = ViewBindings.findChildViewById(view, (i = R.id.v_blur_color))) != null) {
                            i = R.id.vp_tab;
                            ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, i);
                            if (viewPager2 != null) {
                                return new DialogSoundMenuBinding((LinearLayoutCompat) view, appCompatImageView, constraintLayout, appCompatImageView2, errorCcBindingBind, fragmentLoadingBindingBind, tabLayout, viewFindChildViewById2, viewPager2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
