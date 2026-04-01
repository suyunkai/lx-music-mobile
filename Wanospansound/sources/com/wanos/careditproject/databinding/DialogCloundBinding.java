package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;
import com.wanos.commonlibrary.databinding.FragmentLoadingBinding;
import com.wanos.media.ui.widget.WanosTextView;

/* JADX INFO: loaded from: classes3.dex */
public final class DialogCloundBinding implements ViewBinding {
    public final AppCompatImageView btnBack;
    public final WanosTextView btnCancel;
    public final WanosTextView btnCreateDir;
    public final AppCompatImageView btnCreateDirCancel;
    public final AppCompatImageView btnCreateDirDone;
    public final WanosTextView btnUpload;
    public final FrameLayout dialogContentLoadingView;
    public final ConstraintLayout dialogContentView;
    public final FragmentLoadingBinding dialogLoadingView;
    public final AppCompatEditText etDirName;
    public final LinearLayoutCompat llCreateDir;
    private final FrameLayout rootView;
    public final RecyclerView rvDirContent;
    public final RecyclerView rvDirTitle;
    public final WanosTextView tvEmptyDirName;
    public final WanosTextView tvTitle;
    public final LinearLayoutCompat vEmpty;

    private DialogCloundBinding(FrameLayout frameLayout, AppCompatImageView appCompatImageView, WanosTextView wanosTextView, WanosTextView wanosTextView2, AppCompatImageView appCompatImageView2, AppCompatImageView appCompatImageView3, WanosTextView wanosTextView3, FrameLayout frameLayout2, ConstraintLayout constraintLayout, FragmentLoadingBinding fragmentLoadingBinding, AppCompatEditText appCompatEditText, LinearLayoutCompat linearLayoutCompat, RecyclerView recyclerView, RecyclerView recyclerView2, WanosTextView wanosTextView4, WanosTextView wanosTextView5, LinearLayoutCompat linearLayoutCompat2) {
        this.rootView = frameLayout;
        this.btnBack = appCompatImageView;
        this.btnCancel = wanosTextView;
        this.btnCreateDir = wanosTextView2;
        this.btnCreateDirCancel = appCompatImageView2;
        this.btnCreateDirDone = appCompatImageView3;
        this.btnUpload = wanosTextView3;
        this.dialogContentLoadingView = frameLayout2;
        this.dialogContentView = constraintLayout;
        this.dialogLoadingView = fragmentLoadingBinding;
        this.etDirName = appCompatEditText;
        this.llCreateDir = linearLayoutCompat;
        this.rvDirContent = recyclerView;
        this.rvDirTitle = recyclerView2;
        this.tvEmptyDirName = wanosTextView4;
        this.tvTitle = wanosTextView5;
        this.vEmpty = linearLayoutCompat2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static DialogCloundBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogCloundBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.dialog_clound, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogCloundBinding bind(View view) {
        View viewFindChildViewById;
        int i = R.id.btn_back;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = R.id.btn_cancel;
            WanosTextView wanosTextView = (WanosTextView) ViewBindings.findChildViewById(view, i);
            if (wanosTextView != null) {
                i = R.id.btn_create_dir;
                WanosTextView wanosTextView2 = (WanosTextView) ViewBindings.findChildViewById(view, i);
                if (wanosTextView2 != null) {
                    i = R.id.btn_create_dir_cancel;
                    AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                    if (appCompatImageView2 != null) {
                        i = R.id.btn_create_dir_done;
                        AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                        if (appCompatImageView3 != null) {
                            i = R.id.btn_upload;
                            WanosTextView wanosTextView3 = (WanosTextView) ViewBindings.findChildViewById(view, i);
                            if (wanosTextView3 != null) {
                                i = R.id.dialog_content_loading_view;
                                FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
                                if (frameLayout != null) {
                                    i = R.id.dialog_content_view;
                                    ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                                    if (constraintLayout != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.dialog_loading_view))) != null) {
                                        FragmentLoadingBinding fragmentLoadingBindingBind = FragmentLoadingBinding.bind(viewFindChildViewById);
                                        i = R.id.et_dir_name;
                                        AppCompatEditText appCompatEditText = (AppCompatEditText) ViewBindings.findChildViewById(view, i);
                                        if (appCompatEditText != null) {
                                            i = R.id.ll_create_dir;
                                            LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, i);
                                            if (linearLayoutCompat != null) {
                                                i = R.id.rv_dir_content;
                                                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                                                if (recyclerView != null) {
                                                    i = R.id.rv_dir_title;
                                                    RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(view, i);
                                                    if (recyclerView2 != null) {
                                                        i = R.id.tv_empty_dir_name;
                                                        WanosTextView wanosTextView4 = (WanosTextView) ViewBindings.findChildViewById(view, i);
                                                        if (wanosTextView4 != null) {
                                                            i = R.id.tv_title;
                                                            WanosTextView wanosTextView5 = (WanosTextView) ViewBindings.findChildViewById(view, i);
                                                            if (wanosTextView5 != null) {
                                                                i = R.id.v_empty;
                                                                LinearLayoutCompat linearLayoutCompat2 = (LinearLayoutCompat) ViewBindings.findChildViewById(view, i);
                                                                if (linearLayoutCompat2 != null) {
                                                                    return new DialogCloundBinding((FrameLayout) view, appCompatImageView, wanosTextView, wanosTextView2, appCompatImageView2, appCompatImageView3, wanosTextView3, frameLayout, constraintLayout, fragmentLoadingBindingBind, appCompatEditText, linearLayoutCompat, recyclerView, recyclerView2, wanosTextView4, wanosTextView5, linearLayoutCompat2);
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
