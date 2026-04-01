package com.wanos.careditproject.view.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import androidx.fragment.app.Fragment;
import com.wanos.careditproject.R;
import com.wanos.careditproject.databinding.PopBottomSpeedBinding;
import com.wanos.careditproject.utils.DataHelpAudioTrack;
import com.wanos.careditproject.utils.EditingParams;
import com.wanos.careditproject.utils.cmd.CmdSetClipSpeed;

/* JADX INFO: loaded from: classes3.dex */
public class CreatorSpeedDialog extends Fragment implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    PopBottomSpeedBinding binding;
    private OnSpeedDialogListener onSpeedDialogListener;

    public interface OnSpeedDialogListener {
        void onHide();
    }

    public void setOnSpeedDialogListener(OnSpeedDialogListener onSpeedDialogListener) {
        this.onSpeedDialogListener = onSpeedDialogListener;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.binding = PopBottomSpeedBinding.inflate(layoutInflater, viewGroup, false);
        initView();
        return this.binding.getRoot();
    }

    private void initView() {
        this.binding.btnCloseSpeed.setOnClickListener(this);
        this.binding.viewRoot.setOnClickListener(this);
        this.binding.seekbarSpeed.setOnSeekBarChangeListener(this);
        updateView();
    }

    public void updateView() {
        float clipSpeed = DataHelpAudioTrack.getClipSpeed(EditingParams.getInstance().getCurSelectPcmWaveId());
        this.binding.seekbarSpeed.setProgress((int) (10.0f * clipSpeed));
        this.binding.tvSpeed.setText(clipSpeed + "x");
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        OnSpeedDialogListener onSpeedDialogListener;
        if (view.getId() != R.id.btn_close_speed || (onSpeedDialogListener = this.onSpeedDialogListener) == null) {
            return;
        }
        onSpeedDialogListener.onHide();
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        float f = i / 10.0f;
        DataHelpAudioTrack.setClipSpeed(EditingParams.getInstance().getCurSelectPcmWaveId(), f);
        this.binding.tvSpeed.setText(f + "x");
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
        new CmdSetClipSpeed().saveOld(EditingParams.getInstance().getCurSelectPcmWaveId());
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
        CmdSetClipSpeed currentCmd = CmdSetClipSpeed.getCurrentCmd();
        if (currentCmd != null) {
            currentCmd.saveNew();
        }
    }
}
