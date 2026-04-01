package com.wanos.media.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.wanos.media.base.WanosBaseDialog;
import com.wanos.media.util.AnitClick;
import com.wanos.media.util.ZeroLogcatTools;
import com.wanos.media.viewmodel.AlarmDialogViewModel;
import com.wanos.media.viewmodel.CollectionDialogViewModel;
import com.wanos.media.zero_p.databinding.DialogAlarmBinding;

/* JADX INFO: loaded from: classes3.dex */
public class AlarmDialog extends WanosBaseDialog<DialogAlarmBinding, AlarmDialogViewModel> {
    public static final String TAG = "AlarmDialog";
    private IDialogDismissCallback mIDialogDismissCallback;

    public interface IDialogDismissCallback {
        void onAlarmDialogDismiss();
    }

    @Override // com.wanos.media.base.WanosBaseDialog
    protected void initObserve(Bundle bundle) {
    }

    public static void showDialog(FragmentManager fragmentManager, String str, IDialogDismissCallback iDialogDismissCallback) {
        if (fragmentManager == null) {
            ZeroLogcatTools.e(TAG, "showDialog: FragmentManager == NULL");
            return;
        }
        Fragment fragmentFindFragmentByTag = fragmentManager.findFragmentByTag(TAG);
        if (fragmentFindFragmentByTag instanceof AlarmDialog) {
            ((AlarmDialog) fragmentFindFragmentByTag).dismiss();
            ZeroLogcatTools.e(TAG, "showDialog: 重复弹出");
            return;
        }
        AlarmDialog alarmDialog = new AlarmDialog();
        Bundle bundle = new Bundle();
        bundle.putString(CollectionDialogViewModel.KEY_TITLE, str);
        alarmDialog.setArguments(bundle);
        alarmDialog.setIDialogDismissCallback(iDialogDismissCallback);
        alarmDialog.showAllowingStateLoss(fragmentManager, TAG);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.wanos.media.base.WanosBaseDialog
    public DialogAlarmBinding initViewBinding(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return DialogAlarmBinding.inflate(layoutInflater, viewGroup, false);
    }

    @Override // com.wanos.media.base.WanosBaseDialog
    protected Class<AlarmDialogViewModel> initViewModel() {
        return AlarmDialogViewModel.class;
    }

    @Override // com.wanos.media.base.WanosBaseDialog
    protected void initListener(Bundle bundle) {
        if (this.binding == 0) {
            return;
        }
        ((DialogAlarmBinding) this.binding).btnStopAlarm.setOnClickListener(new AnitClick() { // from class: com.wanos.media.view.AlarmDialog.1
            @Override // com.wanos.media.util.AnitClick
            public void onAnitClick(View view) {
                if (AlarmDialog.this.mIDialogDismissCallback != null) {
                    AlarmDialog.this.mIDialogDismissCallback.onAlarmDialogDismiss();
                }
                AlarmDialog.this.dismiss();
            }
        });
        ((DialogAlarmBinding) this.binding).title.setOnClickListener(new AnitClick() { // from class: com.wanos.media.view.AlarmDialog.2
            @Override // com.wanos.media.util.AnitClick
            public void onAnitClick(View view) {
                if (AlarmDialog.this.mIDialogDismissCallback != null) {
                    AlarmDialog.this.mIDialogDismissCallback.onAlarmDialogDismiss();
                }
                AlarmDialog.this.dismiss();
            }
        });
    }

    @Override // com.wanos.media.base.WanosBaseDialog
    protected void initData(Bundle bundle) {
        ((AlarmDialogViewModel) this.viewModel).setDialogDismissCallback(this.mIDialogDismissCallback);
        Bundle arguments = getArguments();
        if (arguments == null || this.binding == 0) {
            return;
        }
        ((DialogAlarmBinding) this.binding).title.setText(arguments.getString(CollectionDialogViewModel.KEY_TITLE));
    }

    public void setIDialogDismissCallback(IDialogDismissCallback iDialogDismissCallback) {
        this.mIDialogDismissCallback = iDialogDismissCallback;
    }
}
