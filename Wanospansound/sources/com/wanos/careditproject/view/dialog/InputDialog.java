package com.wanos.careditproject.view.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import com.blankj.utilcode.util.ToastUtils;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public class InputDialog {
    static InputDialog instance;
    private Context activity;
    private AlertDialog alertDialog;
    private Button btnCancel;
    private Button btnSure;
    private EditText inputView;

    public interface InputCallback {
        void input(String str);
    }

    public static InputDialog getInstance() {
        if (instance == null) {
            instance = new InputDialog();
        }
        return instance;
    }

    public void showDialog(Context context, String str, final InputCallback inputCallback) {
        this.activity = context;
        AlertDialog alertDialogCreate = new AlertDialog.Builder(context).create();
        this.alertDialog = alertDialogCreate;
        alertDialogCreate.setCancelable(false);
        this.alertDialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.wanos.careditproject.view.dialog.InputDialog.1
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                return i == 84 || i == 4;
            }
        });
        this.alertDialog.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.wanos.careditproject.view.dialog.InputDialog.2
            @Override // android.content.DialogInterface.OnShowListener
            public void onShow(DialogInterface dialogInterface) {
                InputDialog inputDialog = InputDialog.this;
                inputDialog.showKeyboard(inputDialog.inputView);
            }
        });
        this.alertDialog.show();
        this.alertDialog.setContentView(R.layout.dialog_input);
        this.alertDialog.setCanceledOnTouchOutside(true);
        this.alertDialog.getWindow().clearFlags(131072);
        EditText editText = (EditText) this.alertDialog.findViewById(R.id.edit_input);
        this.inputView = editText;
        editText.setFocusable(true);
        this.inputView.setText(str);
        Button button = (Button) this.alertDialog.findViewById(R.id.input_sure);
        this.btnSure = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.careditproject.view.dialog.InputDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String string = InputDialog.this.inputView.getText().toString();
                if (!string.equals("")) {
                    InputDialog.this.dismissDialog();
                    inputCallback.input(string);
                } else {
                    ToastUtils.showShort("名字不能为空！");
                }
            }
        });
        Button button2 = (Button) this.alertDialog.findViewById(R.id.input_cancel);
        this.btnCancel = button2;
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.careditproject.view.dialog.InputDialog.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                InputDialog.this.dismissDialog();
            }
        });
    }

    public void showKeyboard(EditText editText) {
        if (editText != null) {
            editText.setFocusable(true);
            editText.setFocusableInTouchMode(true);
            editText.requestFocus();
        }
    }

    public void dismissDialog() {
        AlertDialog alertDialog = this.alertDialog;
        if (alertDialog == null || !alertDialog.isShowing()) {
            return;
        }
        this.alertDialog.dismiss();
    }
}
