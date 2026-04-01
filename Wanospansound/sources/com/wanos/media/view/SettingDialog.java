package com.wanos.media.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.wanos.media.adapter.SpaceClockAlertSoundAdapter;
import com.wanos.media.base.WanosBaseDialog;
import com.wanos.media.entity.AlarmDownStateEntity;
import com.wanos.media.entity.BellsEntity;
import com.wanos.media.util.CustomClick;
import com.wanos.media.util.LinearItemDecoration;
import com.wanos.media.util.ZeroSettingMateData;
import com.wanos.media.viewmodel.SettingDialogViewModel;
import com.wanos.media.zero_p.R;
import com.wanos.media.zero_p.databinding.DialogRelaxSettingBinding;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class SettingDialog extends WanosBaseDialog<DialogRelaxSettingBinding, SettingDialogViewModel> {
    public static final String TAG = "SettingDialog";
    private IDismissListener iDismissListener;
    private SpaceClockAlertSoundAdapter mAdapter;

    public interface IDismissListener {
        void onDismiss();
    }

    public static void show(FragmentManager fragmentManager) {
        if (fragmentManager.findFragmentByTag(TAG) instanceof SettingDialog) {
            return;
        }
        new SettingDialog().showNow(fragmentManager, TAG);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.wanos.media.base.WanosBaseDialog
    public DialogRelaxSettingBinding initViewBinding(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return DialogRelaxSettingBinding.inflate(layoutInflater, viewGroup, false);
    }

    @Override // com.wanos.media.base.WanosBaseDialog
    protected Class<SettingDialogViewModel> initViewModel() {
        return SettingDialogViewModel.class;
    }

    @Override // com.wanos.media.base.WanosBaseDialog
    protected void initAdapter(Bundle bundle) {
        super.initAdapter(bundle);
        if (this.binding == 0) {
            return;
        }
        this.mAdapter = new SpaceClockAlertSoundAdapter();
        ((DialogRelaxSettingBinding) this.binding).rvAlbum.setLayoutManager(new LinearLayoutManager(requireContext(), 0, false));
        ((DialogRelaxSettingBinding) this.binding).rvAlbum.addItemDecoration(new LinearItemDecoration(0, (int) getResources().getDimension(R.dimen.zero_setting_item_space)));
        ((DialogRelaxSettingBinding) this.binding).rvAlbum.setAdapter(this.mAdapter);
    }

    @Override // com.wanos.media.base.WanosBaseDialog
    protected void initObserve(Bundle bundle) {
        ((SettingDialogViewModel) this.viewModel).bellsInfo.observe(this, new Observer() { // from class: com.wanos.media.view.SettingDialog$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m591lambda$initObserve$0$comwanosmediaviewSettingDialog((List) obj);
            }
        });
        ((SettingDialogViewModel) this.viewModel).state.observe(this, new Observer() { // from class: com.wanos.media.view.SettingDialog$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m592lambda$initObserve$1$comwanosmediaviewSettingDialog((AlarmDownStateEntity) obj);
            }
        });
        ((SettingDialogViewModel) this.viewModel).selectIndex.observe(this, new Observer() { // from class: com.wanos.media.view.SettingDialog$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m593lambda$initObserve$2$comwanosmediaviewSettingDialog((Integer) obj);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initObserve$0$com-wanos-media-view-SettingDialog, reason: not valid java name */
    /* synthetic */ void m591lambda$initObserve$0$comwanosmediaviewSettingDialog(List list) {
        SpaceClockAlertSoundAdapter spaceClockAlertSoundAdapter;
        if (list == null || (spaceClockAlertSoundAdapter = this.mAdapter) == null) {
            return;
        }
        spaceClockAlertSoundAdapter.setBellsList(list);
    }

    /* JADX INFO: renamed from: lambda$initObserve$1$com-wanos-media-view-SettingDialog, reason: not valid java name */
    /* synthetic */ void m592lambda$initObserve$1$comwanosmediaviewSettingDialog(AlarmDownStateEntity alarmDownStateEntity) {
        SpaceClockAlertSoundAdapter spaceClockAlertSoundAdapter;
        if (alarmDownStateEntity == null || (spaceClockAlertSoundAdapter = this.mAdapter) == null) {
            return;
        }
        spaceClockAlertSoundAdapter.setDownLoadState(alarmDownStateEntity.getPosition(), alarmDownStateEntity.isDown(), alarmDownStateEntity.isState());
    }

    /* JADX INFO: renamed from: lambda$initObserve$2$com-wanos-media-view-SettingDialog, reason: not valid java name */
    /* synthetic */ void m593lambda$initObserve$2$comwanosmediaviewSettingDialog(Integer num) {
        SpaceClockAlertSoundAdapter spaceClockAlertSoundAdapter;
        if (num == null || (spaceClockAlertSoundAdapter = this.mAdapter) == null) {
            return;
        }
        spaceClockAlertSoundAdapter.setSelectIndex(num.intValue());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.wanos.media.base.WanosBaseDialog, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IDismissListener) {
            this.iDismissListener = (IDismissListener) context;
        }
    }

    @Override // com.wanos.media.base.WanosBaseDialog, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.iDismissListener = null;
        ((SettingDialogViewModel) this.viewModel).setDismissListener(null);
    }

    @Override // com.wanos.media.base.WanosBaseDialog
    protected void initListener(Bundle bundle) {
        if (this.binding == 0 || this.mAdapter == null) {
            return;
        }
        ((DialogRelaxSettingBinding) this.binding).btnCancel.setOnClickListener(new CustomClick() { // from class: com.wanos.media.view.SettingDialog.1
            @Override // com.wanos.media.util.CustomClick
            public void onAnitClick(View view) {
                SettingDialog.this.dismiss();
            }
        });
        ((DialogRelaxSettingBinding) this.binding).title.setOnClickListener(new CustomClick() { // from class: com.wanos.media.view.SettingDialog.2
            @Override // com.wanos.media.util.CustomClick
            public void onAnitClick(View view) {
                SettingDialog.this.dismiss();
            }
        });
        ((DialogRelaxSettingBinding) this.binding).btnConfirm.setOnClickListener(new CustomClick() { // from class: com.wanos.media.view.SettingDialog.3
            @Override // com.wanos.media.util.CustomClick
            public void onAnitClick(View view) {
                ZeroSettingMateData.insertAlarmPath(SettingDialog.this.mAdapter.getSelectAudioPath());
                SettingDialog.this.dismiss();
            }
        });
        this.mAdapter.setOnItemClickListener(new SpaceClockAlertSoundAdapter.OnItemClickListener() { // from class: com.wanos.media.view.SettingDialog$$ExternalSyntheticLambda3
            @Override // com.wanos.media.adapter.SpaceClockAlertSoundAdapter.OnItemClickListener
            public final void onItemClick(int i, BellsEntity.BellsInfoEntity bellsInfoEntity) {
                this.f$0.m590lambda$initListener$3$comwanosmediaviewSettingDialog(i, bellsInfoEntity);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initListener$3$com-wanos-media-view-SettingDialog, reason: not valid java name */
    /* synthetic */ void m590lambda$initListener$3$comwanosmediaviewSettingDialog(int i, BellsEntity.BellsInfoEntity bellsInfoEntity) {
        ((SettingDialogViewModel) this.viewModel).onItemClickIntent(i, bellsInfoEntity);
    }

    @Override // com.wanos.media.base.WanosBaseDialog
    protected void initData(Bundle bundle) {
        super.initData(bundle);
        ((SettingDialogViewModel) this.viewModel).setDismissListener(this.iDismissListener);
    }
}
