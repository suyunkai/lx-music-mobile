package com.wanos.careditproject.view;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import com.wanos.commonlibrary.R;

/* JADX INFO: loaded from: classes3.dex */
public class EditLoadingFragment extends DialogFragment {
    private final String tag = "Loading_tag";
    public final String TEXT_COLOR_WHITE_FLAG = "text_color_white";
    public final String TEXT_CANCEL_FLAG = "is_cancel";

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        boolean z;
        boolean z2;
        View viewInflate = layoutInflater.inflate(R.layout.fragment_loading, viewGroup, false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        Bundle arguments = getArguments();
        if (arguments != null) {
            z2 = arguments.getBoolean("text_color_white", false);
            z = arguments.getBoolean("is_cancel", false);
        } else {
            z = false;
            z2 = false;
        }
        if (z2) {
            ((TextView) viewInflate.findViewById(com.wanos.careditproject.R.id.tv_loading)).setTextColor(getResources().getColor(com.wanos.careditproject.R.color.loading_text_color, null));
        }
        setCancelable(z);
        getDialog().setCanceledOnTouchOutside(false);
        return viewInflate;
    }

    public void show(FragmentManager fragmentManager) {
        super.show(fragmentManager, "Loading_tag");
    }
}
