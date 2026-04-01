package com.wanos.media.zero_p.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.widget.CustomDialogTitle;
import com.wanos.media.widget.WanosTextView;
import com.wanos.media.zero_p.R;

/* JADX INFO: loaded from: classes3.dex */
public final class DialogTrialEndBinding implements ViewBinding {
    public final WanosTextView btnExit;
    public final WanosTextView btnOpenVip;
    private final LinearLayoutCompat rootView;
    public final CustomDialogTitle title;

    private DialogTrialEndBinding(LinearLayoutCompat linearLayoutCompat, WanosTextView wanosTextView, WanosTextView wanosTextView2, CustomDialogTitle customDialogTitle) {
        this.rootView = linearLayoutCompat;
        this.btnExit = wanosTextView;
        this.btnOpenVip = wanosTextView2;
        this.title = customDialogTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayoutCompat getRoot() {
        return this.rootView;
    }

    public static DialogTrialEndBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogTrialEndBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.dialog_trial_end, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogTrialEndBinding bind(View view) {
        int i = R.id.btn_exit;
        WanosTextView wanosTextView = (WanosTextView) ViewBindings.findChildViewById(view, i);
        if (wanosTextView != null) {
            i = R.id.btn_open_vip;
            WanosTextView wanosTextView2 = (WanosTextView) ViewBindings.findChildViewById(view, i);
            if (wanosTextView2 != null) {
                i = R.id.title;
                CustomDialogTitle customDialogTitle = (CustomDialogTitle) ViewBindings.findChildViewById(view, i);
                if (customDialogTitle != null) {
                    return new DialogTrialEndBinding((LinearLayoutCompat) view, wanosTextView, wanosTextView2, customDialogTitle);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
