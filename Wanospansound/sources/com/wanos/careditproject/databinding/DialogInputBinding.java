package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public final class DialogInputBinding implements ViewBinding {
    public final LinearLayout dialogInput;
    public final EditText editInput;
    public final Button inputCancel;
    public final Button inputSure;
    private final LinearLayout rootView;

    private DialogInputBinding(LinearLayout linearLayout, LinearLayout linearLayout2, EditText editText, Button button, Button button2) {
        this.rootView = linearLayout;
        this.dialogInput = linearLayout2;
        this.editInput = editText;
        this.inputCancel = button;
        this.inputSure = button2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static DialogInputBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogInputBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.dialog_input, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogInputBinding bind(View view) {
        LinearLayout linearLayout = (LinearLayout) view;
        int i = R.id.edit_input;
        EditText editText = (EditText) ViewBindings.findChildViewById(view, i);
        if (editText != null) {
            i = R.id.input_cancel;
            Button button = (Button) ViewBindings.findChildViewById(view, i);
            if (button != null) {
                i = R.id.input_sure;
                Button button2 = (Button) ViewBindings.findChildViewById(view, i);
                if (button2 != null) {
                    return new DialogInputBinding(linearLayout, linearLayout, editText, button, button2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
