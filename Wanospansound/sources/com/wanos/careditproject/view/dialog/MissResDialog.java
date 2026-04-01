package com.wanos.careditproject.view.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.DialogFragment;
import com.wanos.careditproject.R;
import com.wanos.careditproject.databinding.DialogMissResBinding;

/* JADX INFO: loaded from: classes3.dex */
public class MissResDialog extends DialogFragment implements View.OnClickListener {
    private DialogMissResBinding binding;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.binding = DialogMissResBinding.inflate(getLayoutInflater());
        setCancelable(true);
        iniView();
        return this.binding.getRoot();
    }

    protected void iniView() {
        this.binding.btnCloseHelp.setOnClickListener(this);
        this.binding.btnSure.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_close_help) {
            dismiss();
        } else if (id == R.id.btn_sure) {
            dismiss();
        }
    }
}
