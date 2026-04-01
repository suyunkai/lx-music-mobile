package com.wanos.media.ui.advertise;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import androidx.appcompat.app.AppCompatDelegate;
import com.wanos.commonlibrary.base.BaseActivity;
import com.wanos.commonlibrary.event.LoginOrLogoutEvent;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.commonlibrary.utils.Util;
import com.wanos.media.R;
import com.wanos.media.databinding.ActivityWanosBaseBinding;
import com.wanos.media.presenter.IPresenter;
import com.wanos.media.ui.login.activity.MemberPayActivity;
import com.wanos.media.ui.login.dialog.LoginDialog;
import com.wanos.media.util.SearchEditText;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes3.dex */
public abstract class WanosBaseWebActivity<P extends IPresenter> extends BaseActivity {
    protected static int btnAiMusicMvX;
    protected static int btnAiMusicMvY;
    private int activityId;
    protected ActivityWanosBaseBinding activityWanosBaseBinding;
    private int currentNightMode;
    private final boolean isUserClosePlayBar = false;
    private String jumpUrl = "";
    private LoginDialog loginDialog;
    protected P mPresenter;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loginOrLogout(LoginOrLogoutEvent event) {
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("activityId", this.activityId);
    }

    @Override // android.app.Activity
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int i = savedInstanceState.getInt("activityId", this.activityId);
        this.activityId = i;
        LoginDialog loginDialog = this.loginDialog;
        if (loginDialog != null) {
            loginDialog.activityId = i;
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        this.currentNightMode = getResources().getConfiguration().uiMode & 48;
        super.onCreate(savedInstanceState);
        this.activityWanosBaseBinding = ActivityWanosBaseBinding.inflate(getLayoutInflater());
        if (!"E171".equals(Build.MODEL)) {
            this.activityWanosBaseBinding.getRoot().setBackgroundResource(R.drawable.big_bg);
        } else if (getWindowManager().getDefaultDisplay().getDisplayId() == 1002) {
            this.activityWanosBaseBinding.getRoot().setBackgroundColor(getColor(android.R.color.transparent));
        }
        super.setContentView(this.activityWanosBaseBinding.getRoot());
        getWindow().setSoftInputMode(48);
        initTitleBar();
        EventBus.getDefault().register(this);
    }

    private void initTitleBar() {
        Util.setTextWeight(this.activityWanosBaseBinding.titleBar.tvTitle, 500);
        this.activityWanosBaseBinding.titleBar.titleLeftBtn.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.ui.advertise.WanosBaseWebActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                WanosBaseWebActivity.this.onBackPressed();
            }
        });
    }

    public void setTitleBarVisibility(int visibility) {
        this.activityWanosBaseBinding.titleBar.getRoot().setVisibility(visibility);
    }

    public void setTitleText(CharSequence text) {
        this.activityWanosBaseBinding.titleBar.tvTitle.setText(text);
    }

    public void setTitleText(int resid) {
        this.activityWanosBaseBinding.titleBar.tvTitle.setText(resid);
    }

    public void setLeftBtnImg(int resId) {
        this.activityWanosBaseBinding.titleBar.titleLeftBtn.setImageResource(resId);
    }

    public View getTitleRightView() {
        this.activityWanosBaseBinding.titleBar.searchEdit.setVisibility(0);
        this.activityWanosBaseBinding.titleBar.btnSearch.setVisibility(0);
        return this.activityWanosBaseBinding.titleBar.searchEdit;
    }

    public void setOnClickClearListener(SearchEditText.OnClickClearListener listener) {
        this.activityWanosBaseBinding.titleBar.searchEdit.setOnClickClearListener(listener);
    }

    public void setOnClickSearchListener(View.OnClickListener listener) {
        this.activityWanosBaseBinding.titleBar.btnSearch.setOnClickListener(listener);
    }

    public void setLeftBtnOnClickListener(View.OnClickListener clickListener) {
        this.activityWanosBaseBinding.titleBar.titleLeftBtn.setOnClickListener(clickListener);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(int layoutResID) {
        LayoutInflater.from(this).inflate(layoutResID, this.activityWanosBaseBinding.baseContentViewgroup);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(View view) {
        this.activityWanosBaseBinding.baseContentViewgroup.addView(view);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int i = newConfig.uiMode & 48;
        if ("E171".equals(Build.MODEL) && getWindowManager().getDefaultDisplay().getDisplayId() == 1002) {
            this.activityWanosBaseBinding.getRoot().setBackgroundColor(getColor(android.R.color.transparent));
        }
        if (this.currentNightMode != i) {
            this.currentNightMode = i;
            if (i == 16) {
                AppCompatDelegate.setDefaultNightMode(1);
                recreate();
            } else {
                if (i != 32) {
                    return;
                }
                AppCompatDelegate.setDefaultNightMode(2);
                recreate();
            }
        }
    }

    @Override // com.wanos.commonlibrary.base.BaseActivity
    public void showLoadingView() {
        this.activityWanosBaseBinding.baseLoadingView.getRoot().setVisibility(0);
        this.activityWanosBaseBinding.baseContentViewgroup.setVisibility(4);
        this.activityWanosBaseBinding.baseErrorView.getRoot().setVisibility(8);
    }

    public void showLoadingView(String msg) {
        this.activityWanosBaseBinding.baseLoadingView.tvLoading.setText(msg);
        this.activityWanosBaseBinding.baseLoadingView.getRoot().setVisibility(0);
        this.activityWanosBaseBinding.baseContentViewgroup.setVisibility(4);
        this.activityWanosBaseBinding.baseErrorView.getRoot().setVisibility(8);
    }

    @Override // com.wanos.commonlibrary.base.BaseActivity
    public void closeLoadingView() {
        this.activityWanosBaseBinding.baseLoadingView.getRoot().setVisibility(8);
        this.activityWanosBaseBinding.baseContentViewgroup.setVisibility(0);
        this.activityWanosBaseBinding.baseErrorView.getRoot().setVisibility(8);
    }

    public void showLoadErrorView(View.OnClickListener clickListener) {
        this.activityWanosBaseBinding.baseLoadingView.getRoot().setVisibility(8);
        this.activityWanosBaseBinding.baseContentViewgroup.setVisibility(4);
        this.activityWanosBaseBinding.baseErrorView.getRoot().setVisibility(0);
        this.activityWanosBaseBinding.baseErrorView.btnRetry.setOnClickListener(clickListener);
    }

    public void showLoadErrorView(String msg, View.OnClickListener clickListener) {
        this.activityWanosBaseBinding.baseLoadingView.getRoot().setVisibility(8);
        this.activityWanosBaseBinding.baseContentViewgroup.setVisibility(4);
        this.activityWanosBaseBinding.baseErrorView.getRoot().setVisibility(0);
        this.activityWanosBaseBinding.baseErrorView.tvErrorMessage.setText(msg);
        this.activityWanosBaseBinding.baseErrorView.btnRetry.setOnClickListener(clickListener);
    }

    @Override // com.wanos.commonlibrary.base.BaseActivity
    public void showLoginDialog() {
        showLoginDialog((DialogInterface.OnDismissListener) null, false);
    }

    public void showLoginDialog(DialogInterface.OnDismissListener dis, boolean ignoreResume) {
        LoginDialog loginDialog = this.loginDialog;
        if (loginDialog == null || !loginDialog.isShowing()) {
            if (this.isResume || ignoreResume) {
                LoginDialog loginDialog2 = new LoginDialog(this);
                this.loginDialog = loginDialog2;
                loginDialog2.setCancelable(true);
                this.loginDialog.show();
                if (dis != null) {
                    this.loginDialog.setOnDismissListener(dis);
                }
            }
        }
    }

    @Override // com.wanos.commonlibrary.base.BaseActivity
    public void showLoginDialog(int activityId, String jumpUrl) {
        LoginDialog loginDialog = this.loginDialog;
        if ((loginDialog == null || !loginDialog.isShowing()) && this.isResume) {
            LoginDialog loginDialog2 = new LoginDialog(this);
            this.loginDialog = loginDialog2;
            this.activityId = activityId;
            this.jumpUrl = jumpUrl;
            loginDialog2.activityId = activityId;
            this.loginDialog.jumpUrl = jumpUrl;
            this.loginDialog.setCancelable(true);
            this.loginDialog.show();
        }
    }

    @Override // com.wanos.commonlibrary.base.BaseActivity
    public void openWeChatPayActivity() {
        if (!UserInfoUtil.isLogin()) {
            LoginDialog loginDialog = this.loginDialog;
            if ((loginDialog == null || !loginDialog.isShowing()) && this.isResume) {
                LoginDialog loginDialog2 = new LoginDialog(this);
                this.loginDialog = loginDialog2;
                loginDialog2.isJumpVip = true;
                this.loginDialog.setCancelable(true);
                this.loginDialog.show();
                return;
            }
            return;
        }
        startActivity(MemberPayActivity.getIntent(getBaseContext()));
    }

    @Override // com.wanos.commonlibrary.base.BaseActivity
    public void openWeChatPayActivity(int from, int source) {
        if (!UserInfoUtil.isLogin()) {
            LoginDialog loginDialog = this.loginDialog;
            if ((loginDialog == null || !loginDialog.isShowing()) && this.isResume) {
                LoginDialog loginDialog2 = new LoginDialog(this);
                this.loginDialog = loginDialog2;
                loginDialog2.isJumpVip = true;
                this.loginDialog.setCancelable(true);
                this.loginDialog.show();
                return;
            }
            return;
        }
        startActivity(MemberPayActivity.getIntent(getBaseContext(), from, source));
    }

    private void onMediaStart() {
        this.activityWanosBaseBinding.btnPlayPause.setImageResource(R.drawable.ic_play_bar_play);
    }

    private void onMediaPause() {
        this.activityWanosBaseBinding.btnPlayPause.setImageResource(R.drawable.ic_play_bar_pause);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override // com.wanos.commonlibrary.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
