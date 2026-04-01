package com.wanos.media.base;

import android.app.Activity;
import android.content.DialogInterface;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.media.MainApplication;
import com.wanos.media.ui.login.activity.MemberPayActivity;
import com.wanos.media.ui.login.dialog.LoginDialog;

/* JADX INFO: loaded from: classes3.dex */
public class BaseFragBussSetup implements BaseFragContent {
    public Activity baseActivity;
    public boolean isResume = false;
    private LoginDialog loginDialog;

    @Override // com.wanos.media.base.BaseFragContent
    public void init(Activity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Override // com.wanos.media.base.BaseFragContent
    public void openWeChatPayActivity() {
        if (MainApplication.getTopActivity() != null) {
            if (!UserInfoUtil.isLogin()) {
                LoginDialog loginDialog = this.loginDialog;
                if ((loginDialog == null || !loginDialog.isShowing()) && this.isResume) {
                    LoginDialog loginDialog2 = new LoginDialog(MainApplication.getTopActivity());
                    this.loginDialog = loginDialog2;
                    loginDialog2.isJumpVip = true;
                    this.loginDialog.setCancelable(true);
                    this.loginDialog.show();
                    return;
                }
                return;
            }
            MainApplication.getTopActivity().startActivity(MemberPayActivity.getIntent(MainApplication.getTopActivity()));
        }
    }

    @Override // com.wanos.media.base.BaseFragContent
    public void openWeChatPayActivity(int from, int source) {
        if (MainApplication.getTopActivity() != null) {
            if (!UserInfoUtil.isLogin()) {
                LoginDialog loginDialog = this.loginDialog;
                if ((loginDialog == null || !loginDialog.isShowing()) && this.isResume) {
                    LoginDialog loginDialog2 = new LoginDialog(MainApplication.getTopActivity());
                    this.loginDialog = loginDialog2;
                    loginDialog2.isJumpVip = true;
                    this.loginDialog.setCancelable(true);
                    this.loginDialog.show();
                    return;
                }
                return;
            }
            MainApplication.getTopActivity().startActivity(MemberPayActivity.getIntent(MainApplication.getTopActivity(), from, source));
        }
    }

    @Override // com.wanos.media.base.BaseFragContent
    public void showLoginDialog(DialogInterface.OnDismissListener dis, boolean ignoreResume) {
        LoginDialog loginDialog = this.loginDialog;
        if ((loginDialog == null || !loginDialog.isShowing()) && this.baseActivity != null) {
            LoginDialog loginDialog2 = new LoginDialog(this.baseActivity);
            this.loginDialog = loginDialog2;
            loginDialog2.setCancelable(true);
            this.loginDialog.show();
            if (dis != null) {
                this.loginDialog.setOnDismissListener(dis);
            }
        }
    }

    @Override // com.wanos.media.base.BaseFragContent
    public void showLoginDialog(int activityId, String jumpUrl) {
        LoginDialog loginDialog = this.loginDialog;
        if ((loginDialog == null || !loginDialog.isShowing()) && this.baseActivity != null) {
            LoginDialog loginDialog2 = new LoginDialog(this.baseActivity);
            this.loginDialog = loginDialog2;
            loginDialog2.activityId = activityId;
            this.loginDialog.jumpUrl = jumpUrl;
            this.loginDialog.setCancelable(true);
            this.loginDialog.show();
        }
    }

    @Override // com.wanos.media.base.BaseFragContent
    public void onResume() {
        this.isResume = true;
    }

    @Override // com.wanos.media.base.BaseFragContent
    public void onPause() {
        this.isResume = false;
    }
}
