package com.wanos.careditproject.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.wanos.careditproject.databinding.DialogAiExitBinding;
import com.wanos.careditproject.view.viewmodel.AiExitDialogViewModel;
import com.wanos.media.base.WanosBaseDialog;
import com.wanos.media.util.AnitClick;

/* JADX INFO: loaded from: classes3.dex */
public class AiExitDialog extends WanosBaseDialog<DialogAiExitBinding, AiExitDialogViewModel> {
    private static final String TAG = "wanos[AI]-AiExitDialog";
    private IExitDialogListener iExitDialogListener;

    public interface IExitDialogListener {
        void onActive();
    }

    @Override // com.wanos.media.base.WanosBaseDialog
    protected void initObserve(Bundle bundle) {
    }

    public static AiExitDialog show(FragmentManager fragmentManager) {
        Fragment fragmentFindFragmentByTag = fragmentManager.findFragmentByTag(TAG);
        if (fragmentFindFragmentByTag instanceof AiExitDialog) {
            return (AiExitDialog) fragmentFindFragmentByTag;
        }
        AiExitDialog aiExitDialog = new AiExitDialog();
        aiExitDialog.show(fragmentManager, TAG);
        return aiExitDialog;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.wanos.media.base.WanosBaseDialog
    public DialogAiExitBinding initViewBinding(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return DialogAiExitBinding.inflate(layoutInflater, viewGroup, false);
    }

    @Override // com.wanos.media.base.WanosBaseDialog
    protected Class<AiExitDialogViewModel> initViewModel() {
        return AiExitDialogViewModel.class;
    }

    @Override // com.wanos.media.base.WanosBaseDialog
    protected void initListener(Bundle bundle) {
        if (this.binding == 0) {
            return;
        }
        ((DialogAiExitBinding) this.binding).btnBack.setOnClickListener(new AnitClick() { // from class: com.wanos.careditproject.view.AiExitDialog.1
            @Override // com.wanos.media.util.AnitClick
            public void onAnitClick(View view) {
                AiExitDialog.this.dismiss();
            }
        });
        ((DialogAiExitBinding) this.binding).btnCancel.setOnClickListener(new AnitClick() { // from class: com.wanos.careditproject.view.AiExitDialog.2
            @Override // com.wanos.media.util.AnitClick
            public void onAnitClick(View view) {
                AiExitDialog.this.dismiss();
            }
        });
        ((DialogAiExitBinding) this.binding).btnActive.setOnClickListener(new AnitClick() { // from class: com.wanos.careditproject.view.AiExitDialog.3
            @Override // com.wanos.media.util.AnitClick
            public void onAnitClick(View view) {
                if (AiExitDialog.this.iExitDialogListener != null) {
                    AiExitDialog.this.iExitDialogListener.onActive();
                }
                AiExitDialog.this.dismiss();
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.wanos.media.base.WanosBaseDialog, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IExitDialogListener) {
            this.iExitDialogListener = (IExitDialogListener) context;
        }
    }

    @Override // com.wanos.media.base.WanosBaseDialog, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.iExitDialogListener = null;
    }
}
