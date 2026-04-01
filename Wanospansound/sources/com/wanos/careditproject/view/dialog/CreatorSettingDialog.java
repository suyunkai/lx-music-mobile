package com.wanos.careditproject.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.view.View;
import android.view.WindowManager;
import com.wanos.careditproject.R;
import com.wanos.careditproject.databinding.DialogCreatorSettingV2Binding;
import com.wanos.careditproject.utils.EditingUtils;

/* JADX INFO: loaded from: classes3.dex */
public class CreatorSettingDialog extends Dialog implements View.OnClickListener {
    public static final String TAG = "wanos:[CreatorSettingDialog]";
    private final DialogCreatorSettingV2Binding dialogCreatorSettingBinding;
    private int inputDeviceId;

    private void updateView() {
    }

    public CreatorSettingDialog(Context context, int i) {
        super(context, i);
        DialogCreatorSettingV2Binding dialogCreatorSettingV2BindingInflate = DialogCreatorSettingV2Binding.inflate(getLayoutInflater());
        this.dialogCreatorSettingBinding = dialogCreatorSettingV2BindingInflate;
        setContentView(dialogCreatorSettingV2BindingInflate.getRoot());
        dialogCreatorSettingV2BindingInflate.btnClose.setOnClickListener(this);
        dialogCreatorSettingV2BindingInflate.dialogView.setOnClickListener(this);
        dialogCreatorSettingV2BindingInflate.dialogWin.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_close) {
            onBackPressed();
        } else if (id != R.id.tv_input_devices && id == R.id.dialog_win) {
            onBackPressed();
        }
    }

    public void show(int i) {
        initWindow();
        this.inputDeviceId = i;
        getAudioDevices();
        updateView();
        super.show();
    }

    private void getAudioDevices() {
        for (AudioDeviceInfo audioDeviceInfo : ((AudioManager) getContext().getSystemService("audio")).getDevices(1)) {
            EditingUtils.log("type : " + audioDeviceInfo.getType() + ",id : " + audioDeviceInfo.getId() + ",name : " + ((Object) audioDeviceInfo.getProductName()) + ",addr : " + audioDeviceInfo.getAddress());
        }
    }

    private void initWindow() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = -1;
        attributes.height = -1;
        getWindow().setAttributes(attributes);
    }
}
