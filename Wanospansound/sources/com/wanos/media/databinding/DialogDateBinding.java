package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.FrameLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class DialogDateBinding implements ViewBinding {
    public final CalendarView dateView;
    private final FrameLayout rootView;

    private DialogDateBinding(FrameLayout rootView, CalendarView dateView) {
        this.rootView = rootView;
        this.dateView = dateView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static DialogDateBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogDateBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.dialog_date, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogDateBinding bind(View rootView) {
        CalendarView calendarView = (CalendarView) ViewBindings.findChildViewById(rootView, R.id.date_view);
        if (calendarView != null) {
            return new DialogDateBinding((FrameLayout) rootView, calendarView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.date_view)));
    }
}
