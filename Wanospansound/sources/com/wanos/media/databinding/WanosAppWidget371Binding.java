package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class WanosAppWidget371Binding implements ViewBinding {
    public final RelativeLayout appwidgetContainer;
    public final ImageView appwidgetIcon;
    public final TextView appwidgetText;
    private final RelativeLayout rootView;

    private WanosAppWidget371Binding(RelativeLayout rootView, RelativeLayout appwidgetContainer, ImageView appwidgetIcon, TextView appwidgetText) {
        this.rootView = rootView;
        this.appwidgetContainer = appwidgetContainer;
        this.appwidgetIcon = appwidgetIcon;
        this.appwidgetText = appwidgetText;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static WanosAppWidget371Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static WanosAppWidget371Binding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.wanos_app_widget_371, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static WanosAppWidget371Binding bind(View rootView) {
        RelativeLayout relativeLayout = (RelativeLayout) rootView;
        int i = R.id.appwidget_icon;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.appwidget_icon);
        if (imageView != null) {
            i = R.id.appwidget_text;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.appwidget_text);
            if (textView != null) {
                return new WanosAppWidget371Binding(relativeLayout, relativeLayout, imageView, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
