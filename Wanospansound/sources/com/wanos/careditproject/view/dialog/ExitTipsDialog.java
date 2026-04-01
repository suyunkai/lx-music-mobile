package com.wanos.careditproject.view.dialog;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.blankj.utilcode.util.BarUtils;
import com.wanos.careditproject.databinding.DialogExitTipsBinding;
import com.wanos.media.util.AnitClick;

/* JADX INFO: loaded from: classes3.dex */
public class ExitTipsDialog extends DialogFragment {
    private static final String FRAGMENT_TAG = "ExitTipsDialog";
    private static final String TAG = "wanos:[ExitTipsDialog]";
    private OnDialogClickListener listener;
    private DialogExitTipsBinding mViewBinding;

    public interface OnDialogClickListener {
        default void onCancelClick() {
        }

        default void onRightClick() {
        }
    }

    public static ExitTipsDialog show(FragmentManager fragmentManager) {
        if (fragmentManager == null) {
            Log.e(TAG, "show: FragmentManager = NULL");
            return null;
        }
        Fragment fragmentFindFragmentByTag = fragmentManager.findFragmentByTag(FRAGMENT_TAG);
        if (fragmentFindFragmentByTag instanceof ExitTipsDialog) {
            ((ExitTipsDialog) fragmentFindFragmentByTag).dismiss();
        }
        ExitTipsDialog exitTipsDialog = new ExitTipsDialog();
        exitTipsDialog.show(fragmentManager, FRAGMENT_TAG);
        return exitTipsDialog;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        DialogExitTipsBinding dialogExitTipsBindingInflate = DialogExitTipsBinding.inflate(layoutInflater, viewGroup, false);
        this.mViewBinding = dialogExitTipsBindingInflate;
        return dialogExitTipsBindingInflate.getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        DialogExitTipsBinding dialogExitTipsBinding = this.mViewBinding;
        if (dialogExitTipsBinding == null) {
            return;
        }
        dialogExitTipsBinding.btnCancel.setOnClickListener(new AnitClick() { // from class: com.wanos.careditproject.view.dialog.ExitTipsDialog.1
            @Override // com.wanos.media.util.AnitClick
            public void onAnitClick(View view2) {
                if (ExitTipsDialog.this.listener != null) {
                    ExitTipsDialog.this.listener.onCancelClick();
                }
                ExitTipsDialog.this.dismiss();
            }
        });
        this.mViewBinding.btnRight.setOnClickListener(new AnitClick() { // from class: com.wanos.careditproject.view.dialog.ExitTipsDialog.2
            @Override // com.wanos.media.util.AnitClick
            public void onAnitClick(View view2) {
                if (ExitTipsDialog.this.listener != null) {
                    ExitTipsDialog.this.listener.onRightClick();
                }
                ExitTipsDialog.this.dismiss();
            }
        });
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        Window window;
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog == null || (window = dialog.getWindow()) == null) {
            return;
        }
        window.setBackgroundDrawable(new ColorDrawable(0));
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -1;
        attributes.gravity = 17;
        window.setAttributes(attributes);
        BarUtils.transparentStatusBar(window);
    }

    public void setOnDialogClickListener(OnDialogClickListener onDialogClickListener) {
        this.listener = onDialogClickListener;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mViewBinding = null;
    }
}
