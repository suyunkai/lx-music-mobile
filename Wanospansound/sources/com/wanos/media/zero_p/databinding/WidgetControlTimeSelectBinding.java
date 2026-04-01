package com.wanos.media.zero_p.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.widget.WanosTextView;
import com.wanos.media.widget.WanosTimePicker;
import com.wanos.media.zero_p.R;

/* JADX INFO: loaded from: classes3.dex */
public final class WidgetControlTimeSelectBinding implements ViewBinding {
    public final FrameLayout flNumberPicker;
    public final WanosTimePicker numberPicker;
    private final FrameLayout rootView;
    public final RecyclerView rvMingXiang;
    public final WanosTextView tvFocus1;
    public final WanosTextView tvFocus2;

    private WidgetControlTimeSelectBinding(FrameLayout frameLayout, FrameLayout frameLayout2, WanosTimePicker wanosTimePicker, RecyclerView recyclerView, WanosTextView wanosTextView, WanosTextView wanosTextView2) {
        this.rootView = frameLayout;
        this.flNumberPicker = frameLayout2;
        this.numberPicker = wanosTimePicker;
        this.rvMingXiang = recyclerView;
        this.tvFocus1 = wanosTextView;
        this.tvFocus2 = wanosTextView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static WidgetControlTimeSelectBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static WidgetControlTimeSelectBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.widget_control_time_select, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static WidgetControlTimeSelectBinding bind(View view) {
        int i = R.id.fl_number_picker;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
        if (frameLayout != null) {
            i = R.id.number_picker;
            WanosTimePicker wanosTimePicker = (WanosTimePicker) ViewBindings.findChildViewById(view, i);
            if (wanosTimePicker != null) {
                i = R.id.rv_ming_xiang;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                if (recyclerView != null) {
                    i = R.id.tv_focus_1;
                    WanosTextView wanosTextView = (WanosTextView) ViewBindings.findChildViewById(view, i);
                    if (wanosTextView != null) {
                        i = R.id.tv_focus_2;
                        WanosTextView wanosTextView2 = (WanosTextView) ViewBindings.findChildViewById(view, i);
                        if (wanosTextView2 != null) {
                            return new WidgetControlTimeSelectBinding((FrameLayout) view, frameLayout, wanosTimePicker, recyclerView, wanosTextView, wanosTextView2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
