package com.wanos.media.zero_p.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.widget.WanosTextView;
import com.wanos.media.zero_p.R;

/* JADX INFO: loaded from: classes3.dex */
public final class WidgetRelaxEditActionBinding implements ViewBinding {
    public final AppCompatImageView ivActionIcon;
    public final WanosTextView ivActionName;
    private final LinearLayoutCompat rootView;

    private WidgetRelaxEditActionBinding(LinearLayoutCompat linearLayoutCompat, AppCompatImageView appCompatImageView, WanosTextView wanosTextView) {
        this.rootView = linearLayoutCompat;
        this.ivActionIcon = appCompatImageView;
        this.ivActionName = wanosTextView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayoutCompat getRoot() {
        return this.rootView;
    }

    public static WidgetRelaxEditActionBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static WidgetRelaxEditActionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.widget_relax_edit_action, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static WidgetRelaxEditActionBinding bind(View view) {
        int i = R.id.iv_action_icon;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = R.id.iv_action_name;
            WanosTextView wanosTextView = (WanosTextView) ViewBindings.findChildViewById(view, i);
            if (wanosTextView != null) {
                return new WidgetRelaxEditActionBinding((LinearLayoutCompat) view, appCompatImageView, wanosTextView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
