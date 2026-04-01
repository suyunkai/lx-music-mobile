package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class WanosAppWidgetConfigureBinding implements ViewBinding {
    public final Button addButton;
    public final EditText appwidgetText;
    private final LinearLayout rootView;

    private WanosAppWidgetConfigureBinding(LinearLayout rootView, Button addButton, EditText appwidgetText) {
        this.rootView = rootView;
        this.addButton = addButton;
        this.appwidgetText = appwidgetText;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static WanosAppWidgetConfigureBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static WanosAppWidgetConfigureBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.wanos_app_widget_configure, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static WanosAppWidgetConfigureBinding bind(View rootView) {
        int i = R.id.add_button;
        Button button = (Button) ViewBindings.findChildViewById(rootView, R.id.add_button);
        if (button != null) {
            i = R.id.appwidget_text;
            EditText editText = (EditText) ViewBindings.findChildViewById(rootView, R.id.appwidget_text);
            if (editText != null) {
                return new WanosAppWidgetConfigureBinding((LinearLayout) rootView, button, editText);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
