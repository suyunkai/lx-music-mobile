package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public final class PopMidiEditQuantizationBinding implements ViewBinding {
    public final RadioButton quantization16;
    public final RadioButton quantization2;
    public final RadioButton quantization4;
    public final RadioButton quantization8;
    public final RadioGroup radioGroup;
    private final RadioGroup rootView;

    private PopMidiEditQuantizationBinding(RadioGroup radioGroup, RadioButton radioButton, RadioButton radioButton2, RadioButton radioButton3, RadioButton radioButton4, RadioGroup radioGroup2) {
        this.rootView = radioGroup;
        this.quantization16 = radioButton;
        this.quantization2 = radioButton2;
        this.quantization4 = radioButton3;
        this.quantization8 = radioButton4;
        this.radioGroup = radioGroup2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RadioGroup getRoot() {
        return this.rootView;
    }

    public static PopMidiEditQuantizationBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopMidiEditQuantizationBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.pop_midi_edit_quantization, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static PopMidiEditQuantizationBinding bind(View view) {
        int i = R.id.quantization_16;
        RadioButton radioButton = (RadioButton) ViewBindings.findChildViewById(view, i);
        if (radioButton != null) {
            i = R.id.quantization_2;
            RadioButton radioButton2 = (RadioButton) ViewBindings.findChildViewById(view, i);
            if (radioButton2 != null) {
                i = R.id.quantization_4;
                RadioButton radioButton3 = (RadioButton) ViewBindings.findChildViewById(view, i);
                if (radioButton3 != null) {
                    i = R.id.quantization_8;
                    RadioButton radioButton4 = (RadioButton) ViewBindings.findChildViewById(view, i);
                    if (radioButton4 != null) {
                        RadioGroup radioGroup = (RadioGroup) view;
                        return new PopMidiEditQuantizationBinding(radioGroup, radioButton, radioButton2, radioButton3, radioButton4, radioGroup);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
