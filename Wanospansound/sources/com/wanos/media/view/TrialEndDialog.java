package com.wanos.media.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import com.wanos.commonlibrary.event.UserInfoChangeEvent;
import com.wanos.media.base.WanosBaseDialog;
import com.wanos.media.util.CustomClick;
import com.wanos.media.util.LoginUtils;
import com.wanos.media.util.ZeroLogcatTools;
import com.wanos.media.viewmodel.TrialEndDialogViewModel;
import com.wanos.media.zero_p.databinding.DialogTrialEndBinding;

/* JADX INFO: loaded from: classes3.dex */
public class TrialEndDialog extends WanosBaseDialog<DialogTrialEndBinding, TrialEndDialogViewModel> {
    private static final String TAG = "TrialEndDialog";
    private ITrialCallback iTrialCallback;

    public interface ITrialCallback {
        void onContinuePlay(TrialEndDialog trialEndDialog);

        void onExitScene(TrialEndDialog trialEndDialog);
    }

    @Override // com.wanos.media.base.WanosBaseDialog
    protected boolean isCancel() {
        return false;
    }

    public static TrialEndDialog show(FragmentManager fragmentManager, ITrialCallback iTrialCallback) {
        Fragment fragmentFindFragmentByTag = fragmentManager.findFragmentByTag(TAG);
        if (fragmentFindFragmentByTag instanceof TrialEndDialog) {
            TrialEndDialog trialEndDialog = (TrialEndDialog) fragmentFindFragmentByTag;
            trialEndDialog.setITrialCallback(iTrialCallback);
            return trialEndDialog;
        }
        TrialEndDialog trialEndDialog2 = new TrialEndDialog();
        trialEndDialog2.setITrialCallback(iTrialCallback);
        trialEndDialog2.showAllowingStateLoss(fragmentManager, TAG);
        return trialEndDialog2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.wanos.media.base.WanosBaseDialog
    public DialogTrialEndBinding initViewBinding(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return DialogTrialEndBinding.inflate(layoutInflater, viewGroup, false);
    }

    @Override // com.wanos.media.base.WanosBaseDialog
    protected Class<TrialEndDialogViewModel> initViewModel() {
        return TrialEndDialogViewModel.class;
    }

    @Override // com.wanos.media.base.WanosBaseDialog
    protected void initSetting(Bundle bundle) {
        super.initSetting(bundle);
        if (this.iTrialCallback != null) {
            ((TrialEndDialogViewModel) this.viewModel).setTrialCallback(this.iTrialCallback);
        }
    }

    @Override // com.wanos.media.base.WanosBaseDialog
    protected void initObserve(Bundle bundle) {
        LoginUtils.getInstance().onUserInfoChange.observe(getViewLifecycleOwner(), new Observer() { // from class: com.wanos.media.view.TrialEndDialog$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m599lambda$initObserve$0$comwanosmediaviewTrialEndDialog((UserInfoChangeEvent) obj);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initObserve$0$com-wanos-media-view-TrialEndDialog, reason: not valid java name */
    /* synthetic */ void m599lambda$initObserve$0$comwanosmediaviewTrialEndDialog(UserInfoChangeEvent userInfoChangeEvent) {
        if (LoginUtils.getInstance().isLogin() && LoginUtils.getInstance().isVip()) {
            ((TrialEndDialogViewModel) this.viewModel).onContinue(this);
        }
    }

    @Override // com.wanos.media.base.WanosBaseDialog
    protected void initListener(Bundle bundle) {
        if (this.binding == 0) {
            return;
        }
        ((DialogTrialEndBinding) this.binding).btnOpenVip.setOnClickListener(new CustomClick() { // from class: com.wanos.media.view.TrialEndDialog.1
            @Override // com.wanos.media.util.CustomClick
            public void onAnitClick(View view) {
                LoginUtils.getInstance().showOpenVipDialog();
            }
        });
        ((DialogTrialEndBinding) this.binding).btnExit.setOnClickListener(new CustomClick() { // from class: com.wanos.media.view.TrialEndDialog.2
            @Override // com.wanos.media.util.CustomClick
            public void onAnitClick(View view) {
                ZeroLogcatTools.i(TrialEndDialog.TAG, "Exit Click Event");
                ((TrialEndDialogViewModel) TrialEndDialog.this.viewModel).onExitScene(TrialEndDialog.this);
            }
        });
        ((DialogTrialEndBinding) this.binding).title.setOnClickListener(new CustomClick() { // from class: com.wanos.media.view.TrialEndDialog.3
            @Override // com.wanos.media.util.CustomClick
            public void onAnitClick(View view) {
                ZeroLogcatTools.i(TrialEndDialog.TAG, "Exit Click Event");
                ((TrialEndDialogViewModel) TrialEndDialog.this.viewModel).onExitScene(TrialEndDialog.this);
            }
        });
    }

    public void setITrialCallback(ITrialCallback iTrialCallback) {
        this.iTrialCallback = iTrialCallback;
        if (this.viewModel != 0) {
            ((TrialEndDialogViewModel) this.viewModel).setTrialCallback(iTrialCallback);
        }
    }
}
