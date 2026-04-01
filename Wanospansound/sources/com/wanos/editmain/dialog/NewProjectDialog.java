package com.wanos.editmain.dialog;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.DialogFragment;
import com.wanos.careditproject.R;
import com.wanos.careditproject.databinding.DialogNewProjectBinding;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.careditproject.view.AiEditActivity;
import com.wanos.careditproject.view.EditingActivity;

/* JADX INFO: loaded from: classes3.dex */
public class NewProjectDialog extends DialogFragment implements View.OnClickListener, View.OnTouchListener {
    public static final String TAG = "wanos:[CreatorSettingDialog]";
    private DialogNewProjectBinding binding;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.binding = DialogNewProjectBinding.inflate(getLayoutInflater());
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        initView();
        setCancelable(true);
        return this.binding.getRoot();
    }

    private void initView() {
        this.binding.btnClose.setOnClickListener(this);
        this.binding.btnAi.setOnTouchListener(this);
        this.binding.btnAudiobook.setOnTouchListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.btn_close) {
            EditingUtils.log("CreatorNewTrackDialog btn_close");
            dismissAllowingStateLoss();
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            view.animate().scaleX(0.9f).scaleY(0.9f).setDuration(100L).start();
        } else if (action == 1 || action == 3) {
            EditingUtils.log("CreatorNewTrackDialog btn_ai : " + motionEvent.getAction());
            view.animate().scaleX(1.0f).scaleY(1.0f).setDuration(100L).start();
            dismissAllowingStateLoss();
            if (motionEvent.getAction() == 1) {
                int id = view.getId();
                if (id == R.id.btn_ai) {
                    AiEditActivity.onLaunchActivity(this);
                } else if (id == R.id.btn_audiobook) {
                    EditingActivity.openEditActivity(getContext(), EditingUtils.EditProjectType.AUDIOBOOK, "0", "", false);
                }
            }
        }
        return true;
    }
}
