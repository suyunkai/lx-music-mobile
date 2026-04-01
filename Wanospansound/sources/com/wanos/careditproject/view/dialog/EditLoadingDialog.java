package com.wanos.careditproject.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import com.wanos.careditproject.R;
import com.wanos.careditproject.databinding.DialogProjectLoadingBinding;
import com.wanos.careditproject.view.EditingActivity;

/* JADX INFO: loaded from: classes3.dex */
public class EditLoadingDialog extends Dialog implements View.OnClickListener {
    private DialogProjectLoadingBinding binding;
    private Context context;
    private Thread dProgressThread;
    private Handler handler;
    private boolean isDownloading;
    private Runnable runnable;

    public void initView() {
    }

    public EditLoadingDialog(Context context) {
        super(context, R.style.FullScreenDialogTheme);
        this.isDownloading = true;
        initDialog(context);
    }

    public void initDialog(Context context) {
        this.context = context;
        DialogProjectLoadingBinding dialogProjectLoadingBindingInflate = DialogProjectLoadingBinding.inflate(getLayoutInflater());
        this.binding = dialogProjectLoadingBindingInflate;
        setContentView(dialogProjectLoadingBindingInflate.getRoot());
        initView();
        initListener();
        showLoading();
    }

    public void showLoading() {
        this.binding.tvLoading.setText(R.string.edit_loading_text);
        this.binding.btnCancel.setVisibility(0);
        this.binding.seekbarProgress.setVisibility(0);
        this.binding.btnsFail.setVisibility(8);
    }

    public void showFail() {
        this.binding.tvLoading.setText(R.string.edit_loading_fail_text);
        this.binding.btnCancel.setVisibility(8);
        this.binding.seekbarProgress.setVisibility(8);
        this.binding.btnsFail.setVisibility(0);
    }

    public void initListener() {
        this.binding.btnCancel.setOnClickListener(this);
        this.binding.seekbarProgress.setEnabled(false);
        this.binding.btnCancelFail.setOnClickListener(this);
        this.binding.btnTryFail.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_cancel) {
            Context context = this.context;
            if (context instanceof EditingActivity) {
                ((EditingActivity) context).finish();
                return;
            }
            return;
        }
        if (id == R.id.btn_cancel_fail) {
            Context context2 = this.context;
            if (context2 instanceof EditingActivity) {
                ((EditingActivity) context2).finish();
                return;
            }
            return;
        }
        if (id == R.id.btn_try_fail) {
            Context context3 = this.context;
            if (context3 instanceof EditingActivity) {
                ((EditingActivity) context3).retryGetEditData();
            }
        }
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        startDownloadProgress();
    }

    @Override // android.app.Dialog
    public void hide() {
        super.dismiss();
        stopProgress();
    }

    public void startDownloadProgress() {
        this.isDownloading = true;
        EditLoadingValue.getInstance().startLoadJson();
        this.handler = new Handler(Looper.getMainLooper());
        Runnable runnable = new Runnable() { // from class: com.wanos.careditproject.view.dialog.EditLoadingDialog.1
            @Override // java.lang.Runnable
            public void run() {
                if (EditLoadingDialog.this.isDownloading) {
                    EditLoadingDialog.this.binding.seekbarProgress.setProgress(EditLoadingValue.getInstance().getProgress());
                    EditLoadingDialog.this.handler.postDelayed(this, 60L);
                }
            }
        };
        this.runnable = runnable;
        this.handler.post(runnable);
    }

    public void stopProgress() {
        this.isDownloading = false;
    }
}
