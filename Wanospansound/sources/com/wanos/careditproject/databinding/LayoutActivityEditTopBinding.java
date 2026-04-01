package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public final class LayoutActivityEditTopBinding implements ViewBinding {
    public final EditText etProjectName;
    private final LinearLayout rootView;
    public final TextView tabEditCon;
    public final TextView tvProjectName;

    private LayoutActivityEditTopBinding(LinearLayout linearLayout, EditText editText, TextView textView, TextView textView2) {
        this.rootView = linearLayout;
        this.etProjectName = editText;
        this.tabEditCon = textView;
        this.tvProjectName = textView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static LayoutActivityEditTopBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutActivityEditTopBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.layout_activity_edit_top, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutActivityEditTopBinding bind(View view) {
        int i = R.id.et_project_name;
        EditText editText = (EditText) ViewBindings.findChildViewById(view, i);
        if (editText != null) {
            i = R.id.tab_edit_con;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView != null) {
                i = R.id.tv_project_name;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView2 != null) {
                    return new LayoutActivityEditTopBinding((LinearLayout) view, editText, textView, textView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
