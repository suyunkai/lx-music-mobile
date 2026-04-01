package com.wanos.careditproject.view.dialog;

import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.baidubce.BceConfig;
import com.wanos.careditproject.R;
import com.wanos.careditproject.databinding.PopBottomSettingBinding;
import com.wanos.careditproject.model.BeatNumberInfo;
import com.wanos.careditproject.utils.StorageUtils;
import com.wanos.careditproject.view.dialog.BeatNumDialog;
import com.wanos.careditproject.view.widget.LongClickImageView;
import com.wanos.media.ui.widget.banner.util.BannerUtils;

/* JADX INFO: loaded from: classes3.dex */
public class CreatorSettingFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, BeatNumDialog.ProjectBeatSelectListener, LongClickImageView.LongClickRepeatListener {
    public static final String TAG = "wanos:[CreatorSettingDialog]";
    private BeatNumDialog beatNumDialog;
    private PopBottomSettingBinding dialogCreatorSettingBinding;
    private int inputDeviceId;
    private boolean isOnBeatSwitch;
    private OnDialogOperateClickListener onDialogOperateClickListener;
    private Toast toast;
    private int speed = 120;
    private int beat = 4;
    private int beatTotal = 4;

    public interface OnDialogOperateClickListener {
        void onDialogBeatNumberChangeListener(int i, int i2);

        void onDialogBeatSpeedChangeListener(int i);

        void onDialogBeatSwitchListener(boolean z);

        void onDialogInputDeviceChangeListener(int i);

        void onHide();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.dialogCreatorSettingBinding = PopBottomSettingBinding.inflate(getLayoutInflater(), viewGroup, false);
        this.isOnBeatSwitch = StorageUtils.getInstance().getBeatSwitch();
        int i = this.speed;
        if (i < 40 || i > 240) {
            this.speed = 120;
        } else {
            this.speed = StorageUtils.getInstance().getBeatSpeed();
        }
        this.beat = StorageUtils.getInstance().getBeatNum();
        this.beatTotal = StorageUtils.getInstance().getBeatTotal();
        this.inputDeviceId = 0;
        getAudioDevices();
        updateView();
        this.dialogCreatorSettingBinding.btnBack.setOnClickListener(this);
        this.dialogCreatorSettingBinding.swichBeat.setOnClickListener(this);
        this.dialogCreatorSettingBinding.btnSpeedMinus.setOnClickListener(this);
        this.dialogCreatorSettingBinding.btnSpeedPlus.setOnClickListener(this);
        this.dialogCreatorSettingBinding.btnSpeedMinus.setLongClickRepeatListener(this);
        this.dialogCreatorSettingBinding.btnSpeedPlus.setLongClickRepeatListener(this);
        this.dialogCreatorSettingBinding.tvBeatNumber.setOnClickListener(this);
        this.dialogCreatorSettingBinding.tvInputDevices.setOnClickListener(this);
        this.dialogCreatorSettingBinding.dialogView.setOnClickListener(this);
        this.dialogCreatorSettingBinding.dialogWin.setOnClickListener(this);
        return this.dialogCreatorSettingBinding.getRoot();
    }

    public void setOnDialogOperateClickListener(OnDialogOperateClickListener onDialogOperateClickListener) {
        this.onDialogOperateClickListener = onDialogOperateClickListener;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        OnDialogOperateClickListener onDialogOperateClickListener;
        int id = view.getId();
        if (id == R.id.btn_back) {
            OnDialogOperateClickListener onDialogOperateClickListener2 = this.onDialogOperateClickListener;
            if (onDialogOperateClickListener2 != null) {
                onDialogOperateClickListener2.onHide();
                return;
            }
            return;
        }
        if (id == R.id.btn_speed_minus) {
            int i = this.speed;
            if (i > 40) {
                this.speed = i - 1;
            } else {
                if (this.toast == null) {
                    this.toast = Toast.makeText(getContext(), R.string.speed_min, 0);
                }
                this.toast.setText(R.string.speed_min);
                this.toast.show();
            }
            this.dialogCreatorSettingBinding.tvBeatSpeed.setText(this.speed + "");
            OnDialogOperateClickListener onDialogOperateClickListener3 = this.onDialogOperateClickListener;
            if (onDialogOperateClickListener3 != null) {
                onDialogOperateClickListener3.onDialogBeatSpeedChangeListener(this.speed);
                return;
            }
            return;
        }
        if (id == R.id.btn_speed_plus) {
            int i2 = this.speed;
            if (i2 < 240) {
                this.speed = i2 + 1;
            } else {
                if (this.toast == null) {
                    this.toast = Toast.makeText(getContext(), R.string.speed_max, 0);
                }
                this.toast.setText(R.string.speed_max);
                this.toast.show();
            }
            this.dialogCreatorSettingBinding.tvBeatSpeed.setText(this.speed + "");
            OnDialogOperateClickListener onDialogOperateClickListener4 = this.onDialogOperateClickListener;
            if (onDialogOperateClickListener4 != null) {
                onDialogOperateClickListener4.onDialogBeatSpeedChangeListener(this.speed);
                return;
            }
            return;
        }
        if (id == R.id.tv_beat_number) {
            showBeatNumberPopupWindow();
            return;
        }
        if (id == R.id.tv_input_devices) {
            return;
        }
        if (id == R.id.swich_beat) {
            this.isOnBeatSwitch = !this.isOnBeatSwitch;
            updateView();
            OnDialogOperateClickListener onDialogOperateClickListener5 = this.onDialogOperateClickListener;
            if (onDialogOperateClickListener5 != null) {
                onDialogOperateClickListener5.onDialogBeatSwitchListener(this.isOnBeatSwitch);
                return;
            }
            return;
        }
        if (id != R.id.dialog_win || (onDialogOperateClickListener = this.onDialogOperateClickListener) == null) {
            return;
        }
        onDialogOperateClickListener.onHide();
    }

    private void showBeatNumberPopupWindow() {
        BeatNumberInfo beatNumberInfo = new BeatNumberInfo(this.beat, this.beatTotal);
        if (this.beatNumDialog == null) {
            BeatNumDialog beatNumDialog = new BeatNumDialog(getContext(), com.wanos.commonlibrary.R.style.Dialog, beatNumberInfo, this.dialogCreatorSettingBinding.llBeatNumber.getMeasuredWidth());
            this.beatNumDialog = beatNumDialog;
            beatNumDialog.setProjectBeatSelectListener(this);
        }
        this.beatNumDialog.showAsDropDown(this.dialogCreatorSettingBinding.llBeatNumber, 0, (this.beatNumDialog.getMeasuredHeight() + this.dialogCreatorSettingBinding.llBeatNumber.getMeasuredHeight() + BannerUtils.dp2px(10.0f)) * (-1), beatNumberInfo);
    }

    public void show(boolean z, int i, int i2, int i3, int i4) {
        this.isOnBeatSwitch = z;
        if (i < 40 || i > 240) {
            this.speed = 120;
        } else {
            this.speed = i;
        }
        this.beat = i2;
        this.beatTotal = i3;
        this.inputDeviceId = i4;
    }

    private void getAudioDevices() {
        for (AudioDeviceInfo audioDeviceInfo : ((AudioManager) getContext().getSystemService("audio")).getDevices(1)) {
            Log.d("glg", "type : " + audioDeviceInfo.getType() + ",id : " + audioDeviceInfo.getId() + ",name : " + ((Object) audioDeviceInfo.getProductName()) + ",addr : " + audioDeviceInfo.getAddress());
        }
    }

    private void updateView() {
        this.dialogCreatorSettingBinding.swichBeat.setSelected(this.isOnBeatSwitch);
        this.dialogCreatorSettingBinding.tvBeatSpeed.setText(this.speed + "");
        this.dialogCreatorSettingBinding.tvBeatNumber.setText(this.beat + BceConfig.BOS_DELIMITER + this.beatTotal);
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        this.isOnBeatSwitch = z;
        OnDialogOperateClickListener onDialogOperateClickListener = this.onDialogOperateClickListener;
        if (onDialogOperateClickListener != null) {
            onDialogOperateClickListener.onDialogBeatSwitchListener(z);
        }
    }

    @Override // com.wanos.careditproject.view.dialog.BeatNumDialog.ProjectBeatSelectListener
    public void onProjectBeatSelectListener(BeatNumberInfo beatNumberInfo) {
        this.beat = beatNumberInfo.getBar();
        this.beatTotal = beatNumberInfo.getBeatTotal();
        this.dialogCreatorSettingBinding.tvBeatNumber.setText(this.beat + BceConfig.BOS_DELIMITER + this.beatTotal);
        OnDialogOperateClickListener onDialogOperateClickListener = this.onDialogOperateClickListener;
        if (onDialogOperateClickListener != null) {
            onDialogOperateClickListener.onDialogBeatNumberChangeListener(beatNumberInfo.getBar(), beatNumberInfo.getBeatTotal());
        }
    }

    @Override // com.wanos.careditproject.view.widget.LongClickImageView.LongClickRepeatListener
    public void repeatAction(View view) {
        onClick(view);
    }
}
