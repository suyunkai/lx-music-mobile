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
public final class DialogAlarmBinding implements ViewBinding {
    public final WanosTextView btnStopAlarm;
    private final LinearLayoutCompat rootView;
    public final CustomDialogTitle title;

    private DialogAlarmBinding(LinearLayoutCompat linearLayoutCompat, WanosTextView wanosTextView, CustomDialogTitle customDialogTitle) {
        this.rootView = linearLayoutCompat;
        this.btnStopAlarm = wanosTextView;
        this.title = customDialogTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayoutCompat getRoot() {
        return this.rootView;
    }

    public static DialogAlarmBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogAlarmBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.dialog_alarm, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogAlarmBinding bind(View view) {
        int i = R.id.btn_stop_alarm;
        WanosTextView wanosTextView = (WanosTextView) ViewBindings.findChildViewById(view, i);
        if (wanosTextView != null) {
            i = R.id.title;
            CustomDialogTitle customDialogTitle = (CustomDialogTitle) ViewBindings.findChildViewById(view, i);
            if (customDialogTitle != null) {
                return new DialogAlarmBinding((LinearLayoutCompat) view, wanosTextView, customDialogTitle);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
