package com.wanos.media.base;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import com.blankj.utilcode.util.ActivityUtils;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.media.MainApplication;
import com.wanos.media.R;
import com.wanos.media.base.BaseDialog;
import com.wanos.media.base.WanosBaseActivity;
import com.wanos.media.ui.actvity.MainActivity;
import com.wanos.media.ui.advertise.dialog.AdvertiseDialog;
import com.wanos.media.ui.audiobook.activity.AudioBookPlayerActivity;
import com.wanos.media.ui.audiobook.dialog.PlayerSpeedDialog;
import com.wanos.media.ui.login.LogoutDialog;
import com.wanos.media.ui.login.activity.MemberPayActivity;
import com.wanos.media.ui.login.dialog.CodeDialog;
import com.wanos.media.ui.login.dialog.LoginDialog;
import com.wanos.media.ui.music.activity.MusicPlayActivity;
import com.wanos.media.ui.search.dialog.CommonDialog;

/* JADX INFO: loaded from: classes3.dex */
public class BaseActBussSetup implements BaseActContent {
    private static final float CLICK_DRAG_TOLERANCE = 5.0f;
    public static final String TAG = "wanos:[BaseActBussSetup]";
    private static boolean aiMusicMvIsInit = false;
    protected static int btnAiMusicMvX;
    protected static int btnAiMusicMvY;
    private int activityId;
    WanosBaseActivity baseActivity;
    private float dX;
    private float dY;
    private LoginDialog loginDialog;
    private LogoutDialog logoutDialog;
    private float preX;
    private float preY;
    private static BaseDialog.DialogShowType dialogShowType = BaseDialog.DialogShowType.NO;
    public static boolean isLoginConfirmed = false;
    public static String loginDialogPhoneNumb = null;
    public static String currentLoginDialogActivityClassName = null;
    public static String loginDialogSmsCode = null;
    public static boolean isAgree = false;
    public static boolean isBindPhone = false;
    public static long remainSeconds = 0;
    private static String dialogShowName = "";
    private boolean btnAiMusicMvMove = false;
    boolean isFromDeepLinkStart = false;

    @Override // com.wanos.media.base.BaseActContent
    public void init(WanosBaseActivity base, Bundle savedInstanceState) {
        this.baseActivity = base;
    }

    @Override // com.wanos.media.base.BaseActContent
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("activityId", this.activityId);
    }

    @Override // com.wanos.media.base.BaseActContent
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        int i = savedInstanceState.getInt("activityId", this.activityId);
        this.activityId = i;
        LoginDialog loginDialog = this.loginDialog;
        if (loginDialog != null) {
            loginDialog.activityId = i;
        }
    }

    @Override // com.wanos.media.base.BaseActContent
    public void onCreate(Bundle savedInstanceState) {
        if (this.baseActivity == null) {
            return;
        }
        if (!aiMusicMvIsInit) {
            initAiMusicMvPos();
        }
        if (this.baseActivity.getIntent().getData() != null) {
            this.isFromDeepLinkStart = true;
        }
        initAiMusicMv();
    }

    @Override // com.wanos.media.base.BaseActContent
    public void onResume() {
        if (dialogShowType == BaseDialog.DialogShowType.Login) {
            if (getClass().getName().equals(dialogShowName) && MainApplication.topActivity.getClass().getName().equals(currentLoginDialogActivityClassName)) {
                boolean z = isLoginConfirmed;
                LoginDialog loginDialog = new LoginDialog(this.baseActivity);
                this.loginDialog = loginDialog;
                loginDialog.setHasShownSelectLoginWay(true);
                this.loginDialog.setCancelable(true);
                this.loginDialog.show();
                if (z) {
                    this.loginDialog.findViewById(R.id.ll_confirm).performClick();
                }
                if (isAgree) {
                    this.loginDialog.setAgree(true);
                    this.loginDialog.dialogLoginBinding.llAgreement.getRoot().setVisibility(8);
                    this.loginDialog.dialogLoginBinding.llAgreement.ivSelect.performClick();
                    if (isBindPhone) {
                        this.loginDialog.setAllowToastSelectLoginWay(false);
                        this.loginDialog.dialogLoginBinding.tvPhoneNumberLogin.performClick();
                        this.loginDialog.setPhoneNum(loginDialogPhoneNumb);
                        this.loginDialog.setSmsCode(loginDialogSmsCode);
                        long j = remainSeconds;
                        if (j != 0) {
                            this.loginDialog.startCountDown(j);
                        }
                    } else {
                        this.loginDialog.doAgree();
                    }
                }
                dialogShowType = BaseDialog.DialogShowType.NO;
            }
        } else if (dialogShowType == BaseDialog.DialogShowType.Logout) {
            if (getClass().getName().equals(dialogShowName)) {
                LogoutDialog logoutDialog = new LogoutDialog(this.baseActivity);
                this.logoutDialog = logoutDialog;
                logoutDialog.setCancelable(true);
                this.logoutDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.wanos.media.base.BaseActBussSetup.1
                    @Override // android.content.DialogInterface.OnDismissListener
                    public void onDismiss(DialogInterface dialogInterface) {
                        if (UserInfoUtil.getUserInfo() == null || TextUtils.isEmpty(UserInfoUtil.getUserInfo().getToken())) {
                            BaseActBussSetup.this.baseActivity.finish();
                        }
                    }
                });
                this.logoutDialog.show();
                dialogShowType = BaseDialog.DialogShowType.NO;
            }
        } else if (dialogShowType == BaseDialog.DialogShowType.speedSet && getClass().getName().equals(dialogShowName)) {
            WanosBaseActivity wanosBaseActivity = this.baseActivity;
            if (wanosBaseActivity instanceof AudioBookPlayerActivity) {
                ((AudioBookPlayerActivity) wanosBaseActivity).showPlayerSpeedDialog();
                dialogShowType = BaseDialog.DialogShowType.NO;
            }
        }
        setAiMusicMvPos();
    }

    @Override // com.wanos.media.base.BaseActContent
    public void onConfigurationChanged(Configuration newConfig) {
        if (BaseDialog.getInstance() != null) {
            dialogShowType = BaseDialog.getInstance().getType();
            if (BaseDialog.getInstance().getType() == BaseDialog.DialogShowType.Login) {
                LoginDialog loginDialog = (LoginDialog) LoginDialog.getInstance();
                loginDialogPhoneNumb = loginDialog.getPhoneNum();
                loginDialogSmsCode = loginDialog.getSmsCode();
                isAgree = loginDialog.isAgree();
                isBindPhone = loginDialog.isBindPhone();
                remainSeconds = loginDialog.getRemainSeconds();
                currentLoginDialogActivityClassName = MainApplication.topActivity.getClass().getName();
                LoginDialog.getInstance().dismiss();
            } else if (BaseDialog.getInstance().getType() == BaseDialog.DialogShowType.Logout) {
                LogoutDialog.getInstance().dismiss();
            } else if (BaseDialog.getInstance().getType() == BaseDialog.DialogShowType.speedSet) {
                PlayerSpeedDialog.getInstance().dismiss();
            }
            dialogShowName = getClass().getName();
            System.out.println("onConfigurationChanged clazz=" + getClass().getName());
        }
        if (BaseAdDialog.getInstance() != null) {
            dialogShowType = BaseAdDialog.getInstance().getType();
            if (BaseAdDialog.getInstance().getType() == BaseDialog.DialogShowType.AD) {
                AdvertiseDialog.getInstance().dismissAllowingStateLoss();
            } else if (BaseAdDialog.getInstance().getType() == BaseDialog.DialogShowType.History) {
                CommonDialog.getInstance().dismissAllowingStateLoss();
            } else if (BaseAdDialog.getInstance().getType() == BaseDialog.DialogShowType.Code) {
                CodeDialog.getInstance().dismissAllowingStateLoss();
            }
        }
    }

    @Override // com.wanos.media.base.BaseActContent
    public void showLoginDialog(DialogInterface.OnDismissListener dis, boolean ignoreResume) {
        WanosBaseActivity wanosBaseActivity;
        LoginDialog loginDialog = this.loginDialog;
        if ((loginDialog == null || !loginDialog.isShowing()) && (wanosBaseActivity = this.baseActivity) != null) {
            if (wanosBaseActivity.isResume || ignoreResume) {
                LoginDialog loginDialog2 = new LoginDialog(this.baseActivity);
                this.loginDialog = loginDialog2;
                loginDialog2.setCancelable(true);
                this.loginDialog.show();
                if (dis != null) {
                    this.loginDialog.setOnDismissListener(dis);
                }
            }
        }
    }

    @Override // com.wanos.media.base.BaseActContent
    public void showLoginDialog(int activityId, String jumpUrl) {
        WanosBaseActivity wanosBaseActivity;
        LoginDialog loginDialog = this.loginDialog;
        if ((loginDialog == null || !loginDialog.isShowing()) && (wanosBaseActivity = this.baseActivity) != null && wanosBaseActivity.isResume) {
            LoginDialog loginDialog2 = new LoginDialog(this.baseActivity);
            this.loginDialog = loginDialog2;
            this.activityId = activityId;
            loginDialog2.activityId = activityId;
            this.loginDialog.jumpUrl = jumpUrl;
            this.loginDialog.setCancelable(true);
            this.loginDialog.show();
        }
    }

    @Override // com.wanos.media.base.BaseActContent
    public void dismissLoginDialog() {
        LoginDialog loginDialog = this.loginDialog;
        if (loginDialog == null || !loginDialog.isShowing()) {
            return;
        }
        this.loginDialog.dismiss();
    }

    @Override // com.wanos.media.base.BaseActContent
    public void openWeChatPayActivity() {
        WanosBaseActivity wanosBaseActivity;
        if (!UserInfoUtil.isLogin()) {
            LoginDialog loginDialog = this.loginDialog;
            if ((loginDialog == null || !loginDialog.isShowing()) && (wanosBaseActivity = this.baseActivity) != null && wanosBaseActivity.isResume) {
                LoginDialog loginDialog2 = new LoginDialog(this.baseActivity);
                this.loginDialog = loginDialog2;
                loginDialog2.isJumpVip = true;
                this.loginDialog.setCancelable(true);
                this.loginDialog.show();
                return;
            }
            return;
        }
        WanosBaseActivity wanosBaseActivity2 = this.baseActivity;
        wanosBaseActivity2.startActivity(MemberPayActivity.getIntent(wanosBaseActivity2));
    }

    @Override // com.wanos.media.base.BaseActContent
    public void openWeChatPayActivity(int from, int source) {
        WanosBaseActivity wanosBaseActivity;
        if (!UserInfoUtil.isLogin()) {
            LoginDialog loginDialog = this.loginDialog;
            if ((loginDialog == null || !loginDialog.isShowing()) && (wanosBaseActivity = this.baseActivity) != null && wanosBaseActivity.isResume) {
                LoginDialog loginDialog2 = new LoginDialog(this.baseActivity);
                this.loginDialog = loginDialog2;
                loginDialog2.isJumpVip = true;
                this.loginDialog.setCancelable(true);
                this.loginDialog.show();
                return;
            }
            return;
        }
        WanosBaseActivity wanosBaseActivity2 = this.baseActivity;
        wanosBaseActivity2.startActivity(MemberPayActivity.getIntent(wanosBaseActivity2, from, source));
    }

    @Override // com.wanos.media.base.BaseActContent
    public void onBackPressed() {
        if (this.baseActivity == null || !this.isFromDeepLinkStart || MainApplication.getInstance().isMainActvityCreated()) {
            return;
        }
        this.baseActivity.startActivity(new Intent(this.baseActivity, (Class<?>) MainActivity.class));
    }

    protected void initAiMusicMvPos() {
        btnAiMusicMvX = this.baseActivity.getResources().getDimensionPixelSize(R.dimen.card_ai_groove_left);
        btnAiMusicMvY = this.baseActivity.getResources().getDimensionPixelSize(R.dimen.card_ai_groove_bottom);
        aiMusicMvIsInit = true;
    }

    private void initAiMusicMv() {
        WanosBaseActivity wanosBaseActivity = this.baseActivity;
        if (wanosBaseActivity != null) {
            wanosBaseActivity.activityWanosBaseBinding.ivAiMusicmv.setVisibility(8);
            this.baseActivity.activityWanosBaseBinding.ivAiMusicmv.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.base.BaseActBussSetup.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    Log.i(BaseActBussSetup.TAG, "open  activityWanosBaseBinding.ivAiMusicmv");
                    Intent intent = new Intent("com.geely.aimusicmv.action");
                    intent.setPackage("com.geely.aimusicmv");
                    intent.putExtra("intention", "open");
                    intent.putExtra("source", "wanos");
                    ActivityUtils.getTopActivity().startService(intent);
                }
            });
        }
    }

    protected void setAiMusicMvPos() {
        WanosBaseActivity wanosBaseActivity = this.baseActivity;
        if (wanosBaseActivity != null) {
            wanosBaseActivity.activityWanosBaseBinding.ivAiMusicmv.setX(btnAiMusicMvX);
            this.baseActivity.activityWanosBaseBinding.ivAiMusicmv.setY(btnAiMusicMvY);
        }
    }

    @Override // com.wanos.media.base.BaseActContent
    public void hideAiMusicMv() {
        WanosBaseActivity wanosBaseActivity = this.baseActivity;
        if (wanosBaseActivity != null) {
            wanosBaseActivity.activityWanosBaseBinding.ivAiMusicmv.setVisibility(8);
        }
    }

    @Override // com.wanos.media.base.BaseActContent
    public void showAiMusicMv() {
        WanosBaseActivity wanosBaseActivity = this.baseActivity;
        if (wanosBaseActivity != null) {
            wanosBaseActivity.activityWanosBaseBinding.ivAiMusicmv.setVisibility(0);
        }
    }

    @Override // com.wanos.media.base.BaseActContent
    public boolean aiMusic() {
        return CarConstants.aiMusic[CarConstants.buildIndex];
    }

    @Override // com.wanos.media.base.BaseActContent
    public void openMusicPlayActivity(WanosBaseActivity activity) {
        MusicPlayActivity.startMusicPlayActivity(activity);
    }

    @Override // com.wanos.media.base.BaseActContent
    public void openAudioBookPlayActivity(WanosBaseActivity activity) {
        AudioBookPlayerActivity.buildPlayPageFromBar(activity);
    }

    @Override // com.wanos.media.base.BaseActContent
    public void showLoadDialog(final WanosBaseActivity activity, WanosBaseActivity.DialogShowType dialogShowType2, boolean isCo) {
        if (dialogShowType2 == WanosBaseActivity.DialogShowType.Login) {
            if (getClass().getName().equals(dialogShowName)) {
                boolean z = isLoginConfirmed;
                LoginDialog loginDialog = new LoginDialog(activity);
                this.loginDialog = loginDialog;
                loginDialog.setCancelable(true);
                this.loginDialog.show();
                if (z) {
                    this.loginDialog.findViewById(R.id.ll_confirm).performClick();
                }
                WanosBaseActivity.DialogShowType dialogShowType3 = WanosBaseActivity.DialogShowType.NO;
                return;
            }
            return;
        }
        if (dialogShowType2 == WanosBaseActivity.DialogShowType.Logout) {
            if (getClass().getName().equals(dialogShowName)) {
                LogoutDialog logoutDialog = new LogoutDialog(activity);
                this.logoutDialog = logoutDialog;
                logoutDialog.setCancelable(true);
                this.logoutDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.wanos.media.base.BaseActBussSetup.3
                    @Override // android.content.DialogInterface.OnDismissListener
                    public void onDismiss(DialogInterface dialogInterface) {
                        if (UserInfoUtil.getUserInfo() == null || TextUtils.isEmpty(UserInfoUtil.getUserInfo().getToken())) {
                            activity.finish();
                        }
                    }
                });
                this.logoutDialog.show();
                WanosBaseActivity.DialogShowType dialogShowType4 = WanosBaseActivity.DialogShowType.NO;
                return;
            }
            return;
        }
        if (dialogShowType2 == WanosBaseActivity.DialogShowType.speedSet && getClass().getName().equals(dialogShowName)) {
            ((AudioBookPlayerActivity) activity).showPlayerSpeedDialog();
            WanosBaseActivity.DialogShowType dialogShowType5 = WanosBaseActivity.DialogShowType.NO;
        }
    }
}
