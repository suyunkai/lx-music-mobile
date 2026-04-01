package com.wanos.careditproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public final class PopPrivacyChangeBinding implements ViewBinding {
    public final Button btnDelete;
    public final Button btnPrivacy;
    private final CardView rootView;

    private PopPrivacyChangeBinding(CardView cardView, Button button, Button button2) {
        this.rootView = cardView;
        this.btnDelete = button;
        this.btnPrivacy = button2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CardView getRoot() {
        return this.rootView;
    }

    public static PopPrivacyChangeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopPrivacyChangeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.pop_privacy_change, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static PopPrivacyChangeBinding bind(View view) {
        int i = R.id.btn_delete;
        Button button = (Button) ViewBindings.findChildViewById(view, i);
        if (button != null) {
            i = R.id.btn_privacy;
            Button button2 = (Button) ViewBindings.findChildViewById(view, i);
            if (button2 != null) {
                return new PopPrivacyChangeBinding((CardView) view, button, button2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
