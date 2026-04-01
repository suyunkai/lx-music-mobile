package com.wanos.careditproject.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Window;
import com.wanos.careditproject.R;
import com.wanos.careditproject.databinding.PopBottomDbBinding;

/* JADX INFO: loaded from: classes3.dex */
public class BottomDialogDB extends Dialog {
    PopBottomDbBinding binding;

    public BottomDialogDB(Context context) {
        super(context);
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(PopBottomDbBinding.inflate(LayoutInflater.from(getContext())).getRoot());
        Window window = getWindow();
        window.setGravity(16);
        window.setLayout(-1, -2);
        window.setGravity(80);
        window.getDecorView().setBackgroundColor(R.color.editing_page_bg);
        window.getDecorView().setPadding(0, 0, 0, 0);
    }
}
