package com.wanos.media.zero_p.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.widget.WanosTextView;
import com.wanos.media.zero_p.R;

/* JADX INFO: loaded from: classes3.dex */
public final class WidgetClockBinding implements ViewBinding {
    private final View rootView;
    public final WanosTextView tvHour;
    public final WanosTextView tvMinute;

    private WidgetClockBinding(View view, WanosTextView wanosTextView, WanosTextView wanosTextView2) {
        this.rootView = view;
        this.tvHour = wanosTextView;
        this.tvMinute = wanosTextView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public View getRoot() {
        return this.rootView;
    }

    public static WidgetClockBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        if (viewGroup == null) {
            throw new NullPointerException("parent");
        }
        layoutInflater.inflate(R.layout.widget_clock, viewGroup);
        return bind(viewGroup);
    }

    public static WidgetClockBinding bind(View view) {
        int i = R.id.tv_hour;
        WanosTextView wanosTextView = (WanosTextView) ViewBindings.findChildViewById(view, i);
        if (wanosTextView != null) {
            i = R.id.tv_minute;
            WanosTextView wanosTextView2 = (WanosTextView) ViewBindings.findChildViewById(view, i);
            if (wanosTextView2 != null) {
                return new WidgetClockBinding(view, wanosTextView, wanosTextView2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
