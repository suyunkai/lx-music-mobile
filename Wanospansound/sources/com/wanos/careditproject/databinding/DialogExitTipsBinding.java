package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;
import com.wanos.media.ui.widget.WanosTextView;

/* JADX INFO: loaded from: classes3.dex */
public final class DialogExitTipsBinding implements ViewBinding {
    public final WanosTextView btnCancel;
    public final WanosTextView btnRight;
    private final FrameLayout rootView;
    public final WanosTextView tvTitle;

    private DialogExitTipsBinding(FrameLayout frameLayout, WanosTextView wanosTextView, WanosTextView wanosTextView2, WanosTextView wanosTextView3) {
        this.rootView = frameLayout;
        this.btnCancel = wanosTextView;
        this.btnRight = wanosTextView2;
        this.tvTitle = wanosTextView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static DialogExitTipsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogExitTipsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.dialog_exit_tips, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogExitTipsBinding bind(View view) {
        int i = R.id.btn_cancel;
        WanosTextView wanosTextView = (WanosTextView) ViewBindings.findChildViewById(view, i);
        if (wanosTextView != null) {
            i = R.id.btn_right;
            WanosTextView wanosTextView2 = (WanosTextView) ViewBindings.findChildViewById(view, i);
            if (wanosTextView2 != null) {
                i = R.id.tv_title;
                WanosTextView wanosTextView3 = (WanosTextView) ViewBindings.findChildViewById(view, i);
                if (wanosTextView3 != null) {
                    return new DialogExitTipsBinding((FrameLayout) view, wanosTextView, wanosTextView2, wanosTextView3);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
