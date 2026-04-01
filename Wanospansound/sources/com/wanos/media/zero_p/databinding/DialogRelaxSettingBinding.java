package com.wanos.media.zero_p.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.widget.CustomDialogTitle;
import com.wanos.media.widget.WanosTextView;
import com.wanos.media.zero_p.R;

/* JADX INFO: loaded from: classes3.dex */
public final class DialogRelaxSettingBinding implements ViewBinding {
    public final WanosTextView btnCancel;
    public final WanosTextView btnConfirm;
    private final ConstraintLayout rootView;
    public final RecyclerView rvAlbum;
    public final CustomDialogTitle title;
    public final WanosTextView tvAlarmSelect;

    private DialogRelaxSettingBinding(ConstraintLayout constraintLayout, WanosTextView wanosTextView, WanosTextView wanosTextView2, RecyclerView recyclerView, CustomDialogTitle customDialogTitle, WanosTextView wanosTextView3) {
        this.rootView = constraintLayout;
        this.btnCancel = wanosTextView;
        this.btnConfirm = wanosTextView2;
        this.rvAlbum = recyclerView;
        this.title = customDialogTitle;
        this.tvAlarmSelect = wanosTextView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DialogRelaxSettingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogRelaxSettingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.dialog_relax_setting, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogRelaxSettingBinding bind(View view) {
        int i = R.id.btn_cancel;
        WanosTextView wanosTextView = (WanosTextView) ViewBindings.findChildViewById(view, i);
        if (wanosTextView != null) {
            i = R.id.btn_confirm;
            WanosTextView wanosTextView2 = (WanosTextView) ViewBindings.findChildViewById(view, i);
            if (wanosTextView2 != null) {
                i = R.id.rv_album;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                if (recyclerView != null) {
                    i = R.id.title;
                    CustomDialogTitle customDialogTitle = (CustomDialogTitle) ViewBindings.findChildViewById(view, i);
                    if (customDialogTitle != null) {
                        i = R.id.tv_alarm_select;
                        WanosTextView wanosTextView3 = (WanosTextView) ViewBindings.findChildViewById(view, i);
                        if (wanosTextView3 != null) {
                            return new DialogRelaxSettingBinding((ConstraintLayout) view, wanosTextView, wanosTextView2, recyclerView, customDialogTitle, wanosTextView3);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
