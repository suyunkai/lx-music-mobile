package com.wanos.careditproject.view.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import androidx.fragment.app.Fragment;
import com.wanos.careditproject.R;
import com.wanos.careditproject.databinding.PopBottomPosdrawTemplateBinding;

/* JADX INFO: loaded from: classes3.dex */
public class CreatorPosdrawTemplateDialog extends Fragment implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    private PopBottomPosdrawTemplateBinding binding;
    private OnDialogListener onDialogListener;

    public interface OnDialogListener {
        void onHide();
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    public void updateView() {
    }

    public void setOnDialogListener(OnDialogListener onDialogListener) {
        this.onDialogListener = onDialogListener;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.binding = PopBottomPosdrawTemplateBinding.inflate(layoutInflater, viewGroup, false);
        initView();
        return this.binding.getRoot();
    }

    private void initView() {
        this.binding.btnClose.setOnClickListener(this);
        this.binding.viewRoot.setOnClickListener(this);
        updateView();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        OnDialogListener onDialogListener;
        if (view.getId() != R.id.btn_close || (onDialogListener = this.onDialogListener) == null) {
            return;
        }
        onDialogListener.onHide();
    }
}
