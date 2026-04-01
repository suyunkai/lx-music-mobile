package com.wanos.media.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import com.wanos.media.base.WanosBaseDialog;
import com.wanos.media.util.CustomClick;
import com.wanos.media.viewmodel.InputShareCodeViewModel;
import com.wanos.media.widget.WanosVerificationView;
import com.wanos.media.zero_p.databinding.DialogInputShareCodeBinding;

/* JADX INFO: loaded from: classes3.dex */
public class InputShareCodeDialog extends WanosBaseDialog<DialogInputShareCodeBinding, InputShareCodeViewModel> {
    private static final String TAG = "InputShareCodeDialog";

    public static void showInputCode(FragmentManager fragmentManager) {
        Fragment fragmentFindFragmentByTag = fragmentManager.findFragmentByTag(TAG);
        if (fragmentFindFragmentByTag instanceof InputShareCodeDialog) {
            ((InputShareCodeDialog) fragmentFindFragmentByTag).dismiss();
        }
        new InputShareCodeDialog().show(fragmentManager, TAG);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.wanos.media.base.WanosBaseDialog
    public DialogInputShareCodeBinding initViewBinding(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return DialogInputShareCodeBinding.inflate(layoutInflater, viewGroup, false);
    }

    @Override // com.wanos.media.base.WanosBaseDialog
    protected Class<InputShareCodeViewModel> initViewModel() {
        return InputShareCodeViewModel.class;
    }

    @Override // com.wanos.media.base.WanosBaseDialog
    protected void initObserve(Bundle bundle) {
        ((InputShareCodeViewModel) this.viewModel).positiveBtnEnableState.observe(this, new Observer() { // from class: com.wanos.media.view.InputShareCodeDialog$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m574lambda$initObserve$0$comwanosmediaviewInputShareCodeDialog((Boolean) obj);
            }
        });
        ((InputShareCodeViewModel) this.viewModel).closeDialogEvent.observe(this, new Observer() { // from class: com.wanos.media.view.InputShareCodeDialog$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m575lambda$initObserve$1$comwanosmediaviewInputShareCodeDialog((Boolean) obj);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initObserve$0$com-wanos-media-view-InputShareCodeDialog, reason: not valid java name */
    /* synthetic */ void m574lambda$initObserve$0$comwanosmediaviewInputShareCodeDialog(Boolean bool) {
        if (this.binding != 0) {
            ((DialogInputShareCodeBinding) this.binding).btnOk.setEnabled(bool.booleanValue());
        }
    }

    /* JADX INFO: renamed from: lambda$initObserve$1$com-wanos-media-view-InputShareCodeDialog, reason: not valid java name */
    /* synthetic */ void m575lambda$initObserve$1$comwanosmediaviewInputShareCodeDialog(Boolean bool) {
        if (bool.booleanValue()) {
            dismiss();
        }
    }

    @Override // com.wanos.media.base.WanosBaseDialog
    protected void initListener(Bundle bundle) {
        if (this.binding == 0) {
            return;
        }
        ((DialogInputShareCodeBinding) this.binding).title.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.view.InputShareCodeDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m572lambda$initListener$2$comwanosmediaviewInputShareCodeDialog(view);
            }
        });
        ((DialogInputShareCodeBinding) this.binding).btnCancel.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.view.InputShareCodeDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m573lambda$initListener$3$comwanosmediaviewInputShareCodeDialog(view);
            }
        });
        ((DialogInputShareCodeBinding) this.binding).btnOk.setOnClickListener(new CustomClick() { // from class: com.wanos.media.view.InputShareCodeDialog.1
            @Override // com.wanos.media.util.CustomClick
            public void onAnitClick(View view) {
                if (InputShareCodeDialog.this.binding != null) {
                    ((InputShareCodeViewModel) InputShareCodeDialog.this.viewModel).addShareCode(((DialogInputShareCodeBinding) InputShareCodeDialog.this.binding).wanosVerification.getInputText());
                }
            }
        });
        ((DialogInputShareCodeBinding) this.binding).wanosVerification.setInputStateCallback(new WanosVerificationView.IInputStateCallback() { // from class: com.wanos.media.view.InputShareCodeDialog.2
            @Override // com.wanos.media.widget.WanosVerificationView.IInputStateCallback
            public void onInputDone(String str) {
            }

            @Override // com.wanos.media.widget.WanosVerificationView.IInputStateCallback
            public void onInputChange(String str) {
                super.onInputChange(str);
                if (InputShareCodeDialog.this.binding == null) {
                    return;
                }
                ((InputShareCodeViewModel) InputShareCodeDialog.this.viewModel).setPositiveBtnEnableState(str.length() == 6);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initListener$2$com-wanos-media-view-InputShareCodeDialog, reason: not valid java name */
    /* synthetic */ void m572lambda$initListener$2$comwanosmediaviewInputShareCodeDialog(View view) {
        dismiss();
    }

    /* JADX INFO: renamed from: lambda$initListener$3$com-wanos-media-view-InputShareCodeDialog, reason: not valid java name */
    /* synthetic */ void m573lambda$initListener$3$comwanosmediaviewInputShareCodeDialog(View view) {
        dismiss();
    }

    @Override // com.wanos.media.base.WanosBaseDialog
    protected void initData(Bundle bundle) {
        if (this.binding != 0) {
            ((DialogInputShareCodeBinding) this.binding).wanosVerification.setEnabled(true);
        }
    }
}
