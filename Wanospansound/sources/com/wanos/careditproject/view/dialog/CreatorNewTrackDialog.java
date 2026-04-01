package com.wanos.careditproject.view.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.DialogFragment;
import com.wanos.careditproject.R;
import com.wanos.careditproject.databinding.DialogCreatorNewTrackBinding;
import com.wanos.careditproject.utils.EditingUtils;

/* JADX INFO: loaded from: classes3.dex */
public class CreatorNewTrackDialog extends DialogFragment implements View.OnClickListener {
    public static final String TAG = "wanos:[CreatorSettingDialog]";
    private DialogCreatorNewTrackBinding binding;
    private DismissListener listener = null;

    public interface DismissListener {
        void onDismiss(int i);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        DialogCreatorNewTrackBinding dialogCreatorNewTrackBindingInflate = DialogCreatorNewTrackBinding.inflate(getLayoutInflater());
        this.binding = dialogCreatorNewTrackBindingInflate;
        dialogCreatorNewTrackBindingInflate.btnClose.setOnClickListener(this);
        this.binding.btnRecord.setOnClickListener(this);
        this.binding.btnRes.setOnClickListener(this);
        setCancelable(true);
        return this.binding.getRoot();
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        EditingUtils.log("CreatorNewTrackDialog onCancel");
        DismissListener dismissListener = this.listener;
        if (dismissListener != null) {
            dismissListener.onDismiss(0);
        }
        super.onCancel(dialogInterface);
    }

    public void setOnDismissListener(DismissListener dismissListener) {
        this.listener = dismissListener;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_close) {
            EditingUtils.log("CreatorNewTrackDialog btn_close");
            DismissListener dismissListener = this.listener;
            if (dismissListener != null) {
                dismissListener.onDismiss(0);
            }
            dismiss();
            return;
        }
        if (id == R.id.btn_record) {
            EditingUtils.log("CreatorNewTrackDialog btn_record");
            DismissListener dismissListener2 = this.listener;
            if (dismissListener2 != null) {
                dismissListener2.onDismiss(1);
            }
            dismiss();
            return;
        }
        if (id == R.id.btn_res) {
            EditingUtils.log("CreatorNewTrackDialog btn_res");
            DismissListener dismissListener3 = this.listener;
            if (dismissListener3 != null) {
                dismissListener3.onDismiss(2);
            }
            dismiss();
        }
    }
}
