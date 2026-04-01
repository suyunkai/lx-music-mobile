package com.wanos.commonlibrary.fragment;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import com.wanos.commonlibrary.R;

/* JADX INFO: loaded from: classes3.dex */
public class LoadingFragment extends DialogFragment {
    private final String tag = "Loading_tag";
    public final String TEXT_COLOR_WHITE_FLAG = "text_color_white";

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_loading, viewGroup, false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        setCancelable(true);
        return viewInflate;
    }

    public void show(FragmentManager fragmentManager) {
        super.show(fragmentManager, "Loading_tag");
    }
}
