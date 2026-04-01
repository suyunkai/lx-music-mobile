package com.wanos.media.zero_p.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.commonlibrary.databinding.FragmentLoadingBinding;
import com.wanos.media.widget.CustomDialogTitle;
import com.wanos.media.widget.WanosTextView;
import com.wanos.media.zero_p.R;

/* JADX INFO: loaded from: classes3.dex */
public final class DialogCollectionBinding implements ViewBinding {
    public final WanosTextView btnCancel;
    public final WanosTextView btnSave;
    public final com.wanos.commonlibrary.databinding.ErrorBinding includeError;
    public final FragmentLoadingBinding includeLoading;
    public final AppCompatEditText inputName;
    private final ConstraintLayout rootView;
    public final RecyclerView rvCove;
    public final CustomDialogTitle title;
    public final WanosTextView tvCoveTitle;
    public final WanosTextView tvInputTitle;

    private DialogCollectionBinding(ConstraintLayout constraintLayout, WanosTextView wanosTextView, WanosTextView wanosTextView2, com.wanos.commonlibrary.databinding.ErrorBinding errorBinding, FragmentLoadingBinding fragmentLoadingBinding, AppCompatEditText appCompatEditText, RecyclerView recyclerView, CustomDialogTitle customDialogTitle, WanosTextView wanosTextView3, WanosTextView wanosTextView4) {
        this.rootView = constraintLayout;
        this.btnCancel = wanosTextView;
        this.btnSave = wanosTextView2;
        this.includeError = errorBinding;
        this.includeLoading = fragmentLoadingBinding;
        this.inputName = appCompatEditText;
        this.rvCove = recyclerView;
        this.title = customDialogTitle;
        this.tvCoveTitle = wanosTextView3;
        this.tvInputTitle = wanosTextView4;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DialogCollectionBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogCollectionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.dialog_collection, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogCollectionBinding bind(View view) {
        View viewFindChildViewById;
        int i = R.id.btn_cancel;
        WanosTextView wanosTextView = (WanosTextView) ViewBindings.findChildViewById(view, i);
        if (wanosTextView != null) {
            i = R.id.btn_save;
            WanosTextView wanosTextView2 = (WanosTextView) ViewBindings.findChildViewById(view, i);
            if (wanosTextView2 != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.include_error))) != null) {
                com.wanos.commonlibrary.databinding.ErrorBinding errorBindingBind = com.wanos.commonlibrary.databinding.ErrorBinding.bind(viewFindChildViewById);
                i = R.id.include_loading;
                View viewFindChildViewById2 = ViewBindings.findChildViewById(view, i);
                if (viewFindChildViewById2 != null) {
                    FragmentLoadingBinding fragmentLoadingBindingBind = FragmentLoadingBinding.bind(viewFindChildViewById2);
                    i = R.id.input_name;
                    AppCompatEditText appCompatEditText = (AppCompatEditText) ViewBindings.findChildViewById(view, i);
                    if (appCompatEditText != null) {
                        i = R.id.rv_cove;
                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                        if (recyclerView != null) {
                            i = R.id.title;
                            CustomDialogTitle customDialogTitle = (CustomDialogTitle) ViewBindings.findChildViewById(view, i);
                            if (customDialogTitle != null) {
                                i = R.id.tv_cove_title;
                                WanosTextView wanosTextView3 = (WanosTextView) ViewBindings.findChildViewById(view, i);
                                if (wanosTextView3 != null) {
                                    i = R.id.tv_input_title;
                                    WanosTextView wanosTextView4 = (WanosTextView) ViewBindings.findChildViewById(view, i);
                                    if (wanosTextView4 != null) {
                                        return new DialogCollectionBinding((ConstraintLayout) view, wanosTextView, wanosTextView2, errorBindingBind, fragmentLoadingBindingBind, appCompatEditText, recyclerView, customDialogTitle, wanosTextView3, wanosTextView4);
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
