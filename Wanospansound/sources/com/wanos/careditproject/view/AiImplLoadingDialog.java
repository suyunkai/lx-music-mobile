package com.wanos.careditproject.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import com.wanos.careditproject.R;
import com.wanos.careditproject.databinding.AiDialogImplBinding;

/* JADX INFO: loaded from: classes3.dex */
public class AiImplLoadingDialog extends Dialog {
    private final AiDialogImplBinding binding;

    public AiImplLoadingDialog(Context context, View.OnClickListener onClickListener) {
        super(context, R.style.FullScreenDialogTheme);
        AiDialogImplBinding aiDialogImplBindingInflate = AiDialogImplBinding.inflate(getLayoutInflater());
        this.binding = aiDialogImplBindingInflate;
        setContentView(aiDialogImplBindingInflate.getRoot());
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        aiDialogImplBindingInflate.btnCancel.setOnClickListener(onClickListener);
    }
}
