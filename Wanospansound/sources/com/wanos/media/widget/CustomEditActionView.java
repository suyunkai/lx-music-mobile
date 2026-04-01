package com.wanos.media.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import androidx.appcompat.widget.LinearLayoutCompat;
import com.wanos.media.zero_p.R;
import com.wanos.media.zero_p.databinding.WidgetRelaxEditActionBinding;

/* JADX INFO: loaded from: classes3.dex */
public class CustomEditActionView extends LinearLayoutCompat {
    private final WidgetRelaxEditActionBinding binding;

    public CustomEditActionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        WidgetRelaxEditActionBinding widgetRelaxEditActionBindingInflate = WidgetRelaxEditActionBinding.inflate(LayoutInflater.from(context), this, true);
        this.binding = widgetRelaxEditActionBindingInflate;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CustomEditActionView);
        widgetRelaxEditActionBindingInflate.ivActionIcon.setImageResource(typedArrayObtainStyledAttributes.getResourceId(R.styleable.CustomEditActionView_action_icon, -1));
        widgetRelaxEditActionBindingInflate.ivActionName.setText(typedArrayObtainStyledAttributes.getString(R.styleable.CustomEditActionView_action_name));
        typedArrayObtainStyledAttributes.recycle();
    }
}
