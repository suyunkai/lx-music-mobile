package com.wanos.media.ui.login.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.wanos.WanosCommunication.BaseResponse;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.WanosCommunication.response.GetGiveVipMemberResponse;
import com.wanos.WanosCommunication.response.GetUserInfoResponse;
import com.wanos.WanosCommunication.response.GetWeChatQrCodeResponse;
import com.wanos.WanosCommunication.service.MediaStatistic;
import com.wanos.commonlibrary.bean.UserInfo;
import com.wanos.commonlibrary.event.LoginOrLogoutEvent;
import com.wanos.commonlibrary.event.UserInfoChangeEvent;
import com.wanos.commonlibrary.fragment.LoadingController;
import com.wanos.commonlibrary.utils.CommonUtils;
import com.wanos.commonlibrary.utils.StatisticUtil;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.commonlibrary.utils.Util;
import com.wanos.media.MainApplication;
import com.wanos.media.R;
import com.wanos.media.base.BaseDialog;
import com.wanos.media.base.WanosBaseActivity;
import com.wanos.media.databinding.DialogLoginBinding;
import com.wanos.media.databinding.ErrorLoginQrCodeBinding;
import com.wanos.media.databinding.LoadingLoginQrCodeBinding;
import com.wanos.media.ui.advertise.WebViewActivity;
import com.wanos.media.ui.advertise.WebViewMessengerService;
import com.wanos.media.ui.advertise.WebViewMsgClient;
import com.wanos.media.ui.login.activity.MemberPayActivity;
import com.wanos.media.util.DownTimer;
import java.lang.ref.WeakReference;
import jp.wasabeef.glide.transformations.ColorFilterTransformation;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes3.dex */
public class LoginDialog extends BaseDialog implements View.OnClickListener {
    private static final String TAG = "wanos:[LoginDialog]";
    private static final int delayTime = 300000;
    public int activityId;
    private boolean allowToastSelectLoginWay;
    private String cacheUrl;
    private boolean confirmToRefresh;
    private boolean connectS;
    private final Context context;
    private LoadingController controller;
    public final DialogLoginBinding dialogLoginBinding;
    private DownTimer downTimer;
    private ConstraintLayout errorView;
    private final Handler handler;
    private boolean hasShownSelectLoginWay;
    private InputMethodManager imm;
    private boolean isAgree;
    private boolean isBindPhone;
    public boolean isJumpVip;
    private boolean isQrCodeLoaded;
    private final boolean isShowSoft;
    public String jumpUrl;
    private int layoutHeight;
    private boolean needAutoRefresh;
    private final Runnable refreshWeChatrLoginRunnable;
    private RequestOptions requestOptionsCover;
    WebViewMsgClient.WeChatLis weChatLis;
    private String weChateCode;
    private String webviewUrl;

    public interface OnDialogComfirmClickListener {
        void onDialogComfirmClickListener(String code);
    }

    public void setHasShownSelectLoginWay(boolean hasShownSelectLoginWay) {
        this.hasShownSelectLoginWay = hasShownSelectLoginWay;
    }

    public void setAllowToastSelectLoginWay(boolean allowToastSelectLoginWay) {
        this.allowToastSelectLoginWay = allowToastSelectLoginWay;
    }

    public long getRemainSeconds() {
        DownTimer downTimer = this.downTimer;
        if (downTimer == null) {
            return 0L;
        }
        return downTimer.getRemainingSeconds();
    }

    private static class RefreshRunnable implements Runnable {
        private final WeakReference<LoginDialog> dialogRef;

        public RefreshRunnable(LoginDialog dialog) {
            this.dialogRef = new WeakReference<>(dialog);
        }

        @Override // java.lang.Runnable
        public void run() {
            LoginDialog loginDialog = this.dialogRef.get();
            if (loginDialog == null || loginDialog.controller == null) {
                return;
            }
            loginDialog.needAutoRefresh = true;
            loginDialog.confirmToRefresh = true;
            loginDialog.handler.removeCallbacks(this);
            ((TextView) loginDialog.errorView.findViewById(R.id.tv_error_message)).setText(R.string.qr_error_timeout);
            loginDialog.controller.showError();
            Log.i(LoginDialog.TAG, "run: showError 1");
        }
    }

    @Override // android.app.Dialog
    public Bundle onSaveInstanceState() {
        Log.i(TAG, "onSaveInstanceState: 保存状态");
        return super.onSaveInstanceState();
    }

    @Override // com.wanos.media.base.BaseDialog
    public BaseDialog.DialogShowType getType() {
        return BaseDialog.DialogShowType.Login;
    }

    public LoginDialog(Context context) {
        super(context, R.style.DialogStyle);
        this.allowToastSelectLoginWay = true;
        this.isAgree = false;
        this.handler = new Handler();
        this.isJumpVip = false;
        this.activityId = 0;
        this.jumpUrl = "";
        this.isBindPhone = false;
        this.isShowSoft = false;
        this.layoutHeight = 0;
        this.needAutoRefresh = true;
        this.confirmToRefresh = false;
        this.requestOptionsCover = new RequestOptions();
        this.connectS = false;
        this.weChatLis = new WebViewMsgClient.WeChatLis() { // from class: com.wanos.media.ui.login.dialog.LoginDialog.1
            @Override // com.wanos.media.ui.advertise.WebViewMsgClient.WeChatLis
            public void onConnect() {
                WebViewActivity.webViewMsgClient.sendOpenWebMessage(LoginDialog.this.webviewUrl);
                LoginDialog.this.connectS = true;
            }

            @Override // com.wanos.media.ui.advertise.WebViewMsgClient.WeChatLis
            public void onWechatImgUrl(String url) {
                LogUtils.i(LoginDialog.TAG, "onWechatImgUrl()----" + LoginDialog.this.cacheUrl + "\n\rneedAutoRefresh----" + LoginDialog.this.needAutoRefresh);
                if (LoginDialog.this.needAutoRefresh) {
                    LoginDialog.this.cacheUrl = url;
                    LoginDialog.this.handler.post(new Runnable() { // from class: com.wanos.media.ui.login.dialog.LoginDialog.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            LoginDialog.this.loadWeChat(LoginDialog.this.cacheUrl, false);
                        }
                    });
                }
            }

            @Override // com.wanos.media.ui.advertise.WebViewMsgClient.WeChatLis
            public void onWechatCode(String code) {
                LogUtils.i(LoginDialog.TAG, "onWechatCode()----" + code);
                if (StringUtils.isEmpty(code)) {
                    return;
                }
                LoginDialog.this.weChateCode = code;
                WanOSRetrofitUtil.getWeChatUserInfo(code, new ResponseCallBack<GetUserInfoResponse>(null) { // from class: com.wanos.media.ui.login.dialog.LoginDialog.1.2
                    @Override // com.wanos.WanosCommunication.ResponseCallBack
                    public void onResponseSuccessful(GetUserInfoResponse response) {
                        LoginDialog.this.handler.removeCallbacks(LoginDialog.this.refreshWeChatrLoginRunnable);
                        if (response != null && response.data != null && !TextUtils.isEmpty(response.data.getMobile())) {
                            LoginDialog.this.onLoginResponse(response);
                        } else {
                            LoginDialog.this.showBindPhone();
                        }
                    }

                    @Override // com.wanos.WanosCommunication.ResponseCallBack
                    public void onResponseFailure(int code2, String msg) {
                        LogUtils.e("code : " + code2);
                        if (!LoginDialog.this.allowToastSelectLoginWay || LoginDialog.this.hasShownSelectLoginWay) {
                            return;
                        }
                        LoginDialog.this.showMsg(msg);
                        LoginDialog.this.hasShownSelectLoginWay = true;
                    }
                });
            }
        };
        this.refreshWeChatrLoginRunnable = new RefreshRunnable(this);
        this.isQrCodeLoaded = false;
        this.imm = (InputMethodManager) context.getSystemService("input_method");
        getWindow().setBackgroundDrawableResource(R.color.p_big_bg_alpha);
        this.context = context;
        DialogLoginBinding dialogLoginBindingInflate = DialogLoginBinding.inflate(getLayoutInflater());
        this.dialogLoginBinding = dialogLoginBindingInflate;
        setContentView(dialogLoginBindingInflate.getRoot());
        dialogLoginBindingInflate.btLogin.setOnClickListener(this);
        dialogLoginBindingInflate.btLoginCancel.setOnClickListener(this);
        dialogLoginBindingInflate.btnBack.setOnClickListener(this);
        dialogLoginBindingInflate.btSmsCode.setOnClickListener(this);
        dialogLoginBindingInflate.tvScaleLogin.setOnClickListener(this);
        dialogLoginBindingInflate.llAgreement.llConfirm.setOnClickListener(this);
        dialogLoginBindingInflate.tvPhoneNumberLogin.setOnClickListener(this);
        dialogLoginBindingInflate.llAgreement.tvServiceProtocol.setOnClickListener(this);
        dialogLoginBindingInflate.llAgreement.tvPrivacyProtocol.setOnClickListener(this);
        dialogLoginBindingInflate.llAgreement.tvChildPrivacyProtocol.setOnClickListener(this);
        Util.setTextWeight(dialogLoginBindingInflate.tvScaleLogin, 500);
        Util.setTextWeight(dialogLoginBindingInflate.tvPhoneNumberLogin, 500);
        initQrLoading();
        initVisibleIsSpoken();
        Activity activity = (Activity) context;
        requestQrCodeUrl(activity);
        if (context instanceof Activity) {
            activity.getWindow().getDecorView().findViewById(android.R.id.content);
            getWindow().setSoftInputMode(16);
            autoScrollView(dialogLoginBindingInflate.etSmsCode);
        }
        this.requestOptionsCover = this.requestOptionsCover.transform(new ColorFilterTransformation(Color.parseColor("#cc000000")));
        WanosBaseActivity.isLoginConfirmed = false;
    }

    private void initVisibleIsSpoken() {
        if (CommonUtils.isChannelNot245()) {
            this.dialogLoginBinding.btnBack.setContentDescription(this.context.getResources().getString(R.string.close_back_click));
            this.dialogLoginBinding.llAgreement.llConfirm.setContentDescription(this.context.getResources().getString(R.string.agree_protocol));
            this.dialogLoginBinding.llAgreement.tvServiceProtocol.setContentDescription(this.context.getResources().getString(R.string.open_one_protocol));
            this.dialogLoginBinding.llAgreement.tvPrivacyProtocol.setContentDescription(this.context.getResources().getString(R.string.open_two_protocol));
            this.dialogLoginBinding.llAgreement.tvChildPrivacyProtocol.setContentDescription(this.context.getResources().getString(R.string.open_three_protocol));
        }
    }

    @Override // android.app.Dialog
    protected void onStart() {
        super.onStart();
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = -1;
        attributes.height = -1;
        getWindow().setAttributes(attributes);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadWeChat(String url, boolean isNeedCover) {
        RequestBuilder<Drawable> requestBuilderAddListener;
        this.isQrCodeLoaded = false;
        try {
            RequestBuilder<Drawable> requestBuilderLoad = Glide.with(getContext()).load(url);
            if (isNeedCover) {
                LogUtils.i(TAG, "需要覆盖----" + this.cacheUrl);
                requestBuilderAddListener = requestBuilderLoad.apply((BaseRequestOptions<?>) this.requestOptionsCover);
            } else {
                LogUtils.i(TAG, "不需要覆盖----" + this.cacheUrl);
                requestBuilderAddListener = requestBuilderLoad.addListener(new RequestListener<Drawable>() { // from class: com.wanos.media.ui.login.dialog.LoginDialog.2
                    @Override // com.bumptech.glide.request.RequestListener
                    public boolean onLoadFailed(GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        if (LoginDialog.this.cacheUrl != null) {
                            LoginDialog loginDialog = LoginDialog.this;
                            loginDialog.loadWeChat(loginDialog.cacheUrl, true);
                        }
                        Log.e(LoginDialog.TAG, "onLoadFailed:" + LoginDialog.this.cacheUrl);
                        ((TextView) LoginDialog.this.errorView.findViewById(R.id.tv_error_message)).setText(R.string.p_login_loading);
                        LoginDialog.this.controller.showError();
                        Log.i(LoginDialog.TAG, "run: showError 2");
                        return false;
                    }

                    @Override // com.bumptech.glide.request.RequestListener
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        LoginDialog.this.controller.dismissLoading();
                        LoginDialog.this.isQrCodeLoaded = true;
                        Log.i(LoginDialog.TAG, "onResourceReady: 二维码加载完毕");
                        LoginDialog.this.handler.removeCallbacks(LoginDialog.this.refreshWeChatrLoginRunnable);
                        if (!LoginDialog.this.isAgree) {
                            return false;
                        }
                        LoginDialog.this.handler.postDelayed(LoginDialog.this.refreshWeChatrLoginRunnable, 300000L);
                        return false;
                    }
                });
            }
            requestBuilderAddListener.into(this.dialogLoginBinding.ivWeChatCode);
        } catch (Exception e) {
            Log.e(TAG, "e:" + e.getMessage());
        }
    }

    @Override // com.wanos.media.base.BaseDialog, android.app.Dialog
    public void show() {
        super.show();
    }

    private void requestQrCodeUrl(final Activity context) {
        WanOSRetrofitUtil.getWeChatQrCode(0, new ResponseCallBack<GetWeChatQrCodeResponse>(null) { // from class: com.wanos.media.ui.login.dialog.LoginDialog.3
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetWeChatQrCodeResponse response) {
                LoginDialog.this.webviewUrl = response.data.qrCodeUrl;
                LogUtils.i(LoginDialog.TAG, "请求二维码成功----" + LoginDialog.this.webviewUrl + "\n\r当前是否已连接子进程----" + LoginDialog.this.connectS);
                if (WebViewMessengerService.messengerService != null) {
                    WebViewMessengerService.messengerService.loadUrl(LoginDialog.this.webviewUrl);
                    WebViewMessengerService.messengerService.weChatLis = LoginDialog.this.weChatLis;
                } else if (LoginDialog.this.connectS) {
                    WebViewActivity.webViewMsgClient.sendOpenWebMessage(LoginDialog.this.webviewUrl);
                } else {
                    LoginDialog.this.connectService(context);
                }
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int code, String msg) {
                LogUtils.i(LoginDialog.TAG, "请求二维码失败----\n\r当前二维码地址----" + LoginDialog.this.cacheUrl);
                if (LoginDialog.this.cacheUrl != null) {
                    LoginDialog loginDialog = LoginDialog.this;
                    loginDialog.loadWeChat(loginDialog.cacheUrl, true);
                }
                ((TextView) LoginDialog.this.errorView.findViewById(R.id.tv_error_message)).setText(R.string.p_login_loading);
                LoginDialog.this.controller.showError();
                Log.i(LoginDialog.TAG, "run: showError 3");
                LoginDialog.this.showMsg("网络错误");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectService(Activity context) {
        if (WebViewActivity.webViewMsgClient == null) {
            WebViewActivity.webViewMsgClient = new WebViewMsgClient();
        }
        WebViewActivity.webViewMsgClient.setLis(this.weChatLis);
        WebViewActivity.webViewMsgClient.bindService(MainApplication.getInstance());
    }

    public void autoScrollView(final EditText scrollToView) {
        final View viewFindViewById = getWindow().getDecorView().findViewById(android.R.id.content);
        viewFindViewById.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.wanos.media.ui.login.dialog.LoginDialog$$ExternalSyntheticLambda1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                this.f$0.m520xa09b1775(viewFindViewById, scrollToView);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$autoScrollView$0$com-wanos-media-ui-login-dialog-LoginDialog, reason: not valid java name */
    /* synthetic */ void m520xa09b1775(View view, EditText editText) {
        Rect rect = new Rect();
        view.getWindowVisibleDisplayFrame(rect);
        if (view.getRootView().getHeight() - rect.bottom > Util.dip2px(getContext(), 150.0f)) {
            int[] iArr = new int[2];
            editText.getLocationInWindow(iArr);
            this.layoutHeight += ((iArr[1] + editText.getHeight()) + Util.dip2px(getContext(), 30.0f)) - rect.bottom;
        } else {
            this.layoutHeight = 0;
        }
        if (this.layoutHeight < 0) {
            this.layoutHeight = 0;
        }
        view.scrollTo(0, this.layoutHeight);
    }

    private void initQrLoading() {
        ConstraintLayout root = ErrorLoginQrCodeBinding.inflate(getLayoutInflater()).getRoot();
        this.errorView = root;
        root.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.ui.login.dialog.LoginDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m521x376813e6(view);
            }
        });
        this.controller = new LoadingController.Builder(getContext(), this.dialogLoginBinding.ivWeChatCodeLoad).setLoadingView(LoadingLoginQrCodeBinding.inflate(getLayoutInflater()).getRoot()).setErrorView(this.errorView).build();
        String str = this.cacheUrl;
        if (str != null) {
            loadWeChat(str, true);
        }
        this.controller.showLoading();
    }

    /* JADX INFO: renamed from: lambda$initQrLoading$1$com-wanos-media-ui-login-dialog-LoginDialog, reason: not valid java name */
    /* synthetic */ void m521x376813e6(View view) {
        this.needAutoRefresh = true;
        if (this.cacheUrl != null) {
            LogUtils.i(TAG, "点击刷新二维码----" + this.cacheUrl);
            loadWeChat(this.cacheUrl, true);
        }
        this.controller.showLoading();
        requestQrCodeUrl((Activity) this.context);
    }

    public void showBindPhone() {
        this.isBindPhone = true;
        this.dialogLoginBinding.tvPhoneNumberLogin.setText(R.string.p_l_bindphone);
        this.dialogLoginBinding.btLogin.setText(R.string.p_l_bind);
        this.dialogLoginBinding.tvScaleLogin.setVisibility(8);
        this.dialogLoginBinding.tvPhoneNumberLogin.performClick();
    }

    public boolean isBindPhone() {
        return this.dialogLoginBinding.tvPhoneNumberLogin.isSelected();
    }

    public boolean isAgree() {
        return this.isAgree;
    }

    public void setAgree(boolean agree) {
        this.isAgree = agree;
    }

    public String getPhoneNum() {
        return this.dialogLoginBinding.etPhoneNumber.getText().toString();
    }

    public void setPhoneNum(String phoneNum) {
        this.dialogLoginBinding.etPhoneNumber.setText(phoneNum);
    }

    public String getSmsCode() {
        return this.dialogLoginBinding.etSmsCode.getText().toString();
    }

    public void setSmsCode(String smsCode) {
        this.dialogLoginBinding.etSmsCode.setText(smsCode);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int i;
        int id = view.getId();
        if (id == R.id.bt_login) {
            String string = this.dialogLoginBinding.etPhoneNumber.getText().toString();
            String string2 = this.dialogLoginBinding.etSmsCode.getText().toString();
            if (TextUtils.isEmpty(string)) {
                showMsg(R.string.please_input_phone);
                return;
            }
            if (!CommonUtils.checkPhone(string)) {
                showMsg(R.string.please_input_phone_error);
                return;
            }
            if (TextUtils.isEmpty(string2)) {
                showMsg(R.string.please_input_sms_code);
                return;
            } else if (this.isBindPhone) {
                bindPhone(string, string2, this.weChateCode);
                return;
            } else {
                login(string, string2);
                return;
            }
        }
        if (id == R.id.btn_back || id == R.id.bt_login_cancel) {
            dismiss();
            return;
        }
        if (id == R.id.ll_confirm) {
            this.isAgree = true;
            doAgree();
            return;
        }
        if (id == R.id.bt_sms_code) {
            String string3 = this.dialogLoginBinding.etPhoneNumber.getText().toString();
            if (TextUtils.isEmpty(string3)) {
                showMsg(R.string.please_input_phone);
                return;
            } else if (!CommonUtils.checkPhone(string3)) {
                showMsg(R.string.please_input_phone_error);
                return;
            } else {
                sendSmsCode(string3);
                return;
            }
        }
        if (id == R.id.tv_phone_number_login) {
            if (this.dialogLoginBinding.tvPhoneNumberLogin.isSelected()) {
                return;
            }
            if (this.dialogLoginBinding.llAgreement.getRoot().getVisibility() != 0) {
                onHideSoftInput();
                this.dialogLoginBinding.tvScaleLogin.setTextColor(getContext().getResources().getColor(R.color.p_title_unselected, null));
                this.dialogLoginBinding.tvScaleLogin.setSelected(false);
                this.dialogLoginBinding.tvPhoneNumberLogin.setTextColor(getContext().getResources().getColor(R.color.p_title_selected, null));
                this.dialogLoginBinding.tvPhoneNumberLogin.setSelected(true);
                this.dialogLoginBinding.llPhoneNumber.setVisibility(0);
                this.dialogLoginBinding.llScaleCode.setVisibility(8);
                return;
            }
            showMsg("请先同意协议");
            return;
        }
        if (id == R.id.tv_scale_login) {
            if (this.dialogLoginBinding.tvScaleLogin.isSelected()) {
                return;
            }
            if (this.dialogLoginBinding.llAgreement.getRoot().getVisibility() != 0) {
                onHideSoftInput();
                this.dialogLoginBinding.tvScaleLogin.setTextColor(getContext().getResources().getColor(R.color.p_title_selected, null));
                this.dialogLoginBinding.tvScaleLogin.setSelected(true);
                this.dialogLoginBinding.tvPhoneNumberLogin.setSelected(false);
                this.dialogLoginBinding.tvPhoneNumberLogin.setTextColor(getContext().getResources().getColor(R.color.p_title_unselected, null));
                this.dialogLoginBinding.llPhoneNumber.setVisibility(8);
                this.dialogLoginBinding.llScaleCode.setVisibility(0);
                return;
            }
            showMsg("请先同意协议");
            return;
        }
        if (id == R.id.tv_service_protocol || id == R.id.tv_child_privacy_protocol || id == R.id.tv_privacy_protocol) {
            Context context = this.context;
            if (id == R.id.tv_service_protocol) {
                i = ProtocolDialog.SERVICE_PRO;
            } else if (id == R.id.tv_child_privacy_protocol) {
                i = ProtocolDialog.CHILD_PRI_SERVICE_PRO;
            } else {
                i = ProtocolDialog.PRI_SERVICE_PRO;
            }
            ProtocolDialog protocolDialog = new ProtocolDialog(context, i);
            protocolDialog.setCancelable(true);
            protocolDialog.show();
        }
    }

    public void doAgree() {
        LoadingController loadingController;
        if (this.isQrCodeLoaded) {
            this.handler.removeCallbacks(this.refreshWeChatrLoginRunnable);
            this.handler.postDelayed(this.refreshWeChatrLoginRunnable, 300000L);
        }
        this.dialogLoginBinding.llAgreement.ivSelect.setImageResource(R.drawable.p_login_agree_select);
        this.handler.postDelayed(new Runnable() { // from class: com.wanos.media.ui.login.dialog.LoginDialog.4
            @Override // java.lang.Runnable
            public void run() {
                WanosBaseActivity.isLoginConfirmed = true;
                LoginDialog.this.closeConfirm();
            }
        }, 500L);
        if (!this.confirmToRefresh || (loadingController = this.controller) == null || loadingController.errorView == null) {
            return;
        }
        this.controller.errorView.performClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeConfirm() {
        this.dialogLoginBinding.llAgreement.getRoot().setVisibility(8);
        if (this.dialogLoginBinding.tvPhoneNumberLogin.getCurrentTextColor() == getContext().getResources().getColor(R.color.p_title_selected, null)) {
            this.dialogLoginBinding.llPhoneNumber.setVisibility(0);
            this.dialogLoginBinding.llScaleCode.setVisibility(8);
        } else {
            this.dialogLoginBinding.llPhoneNumber.setVisibility(8);
            this.dialogLoginBinding.llScaleCode.setVisibility(0);
        }
    }

    private void login(String phoneNum, String smsCode) {
        this.dialogLoginBinding.btLogin.setClickable(false);
        WanOSRetrofitUtil.mobileCodeLogin(phoneNum, smsCode, new ResponseCallBack<GetUserInfoResponse>(this.context) { // from class: com.wanos.media.ui.login.dialog.LoginDialog.5
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetUserInfoResponse response) {
                LoginDialog.this.dialogLoginBinding.btLogin.setClickable(true);
                LoginDialog.this.onLoginResponse(response);
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int code, String msg) {
                LoginDialog.this.dialogLoginBinding.btLogin.setClickable(true);
                if (code == 20019) {
                    LoginDialog.this.showMsg("验证码已过期");
                } else {
                    LoginDialog.this.showMsg("验证码错误");
                }
            }
        });
    }

    private void bindPhone(String phoneNum, String smsCode, String weChatCode) {
        this.dialogLoginBinding.btLogin.setClickable(false);
        WanOSRetrofitUtil.bindMobile(phoneNum, smsCode, weChatCode, new ResponseCallBack<GetUserInfoResponse>(this.context) { // from class: com.wanos.media.ui.login.dialog.LoginDialog.6
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetUserInfoResponse response) {
                LoginDialog.this.dialogLoginBinding.btLogin.setClickable(true);
                LoginDialog.this.onLoginResponse(response);
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int code, String msg) {
                LoginDialog.this.dialogLoginBinding.btLogin.setClickable(true);
                LoginDialog.this.showMsg(msg);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onLoginResponse(GetUserInfoResponse response) {
        UserInfoUtil.saveUserInfo(response.data);
        MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.USER_LOGIN_WANOS_APP, "" + UserInfoUtil.getUserInfo().getUserId(), "", "", "", 0);
        EventBus.getDefault().post(new LoginOrLogoutEvent(true));
        if (this.isJumpVip && !UserInfoUtil.isVipAndUnexpired()) {
            MainApplication.topActivity.startActivity(MemberPayActivity.getIntent(MainApplication.topActivity));
        }
        if (this.activityId != 0) {
            if (!TextUtils.isEmpty(this.jumpUrl)) {
                Uri uri = Uri.parse(this.jumpUrl);
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(uri);
                this.context.startActivity(intent);
            } else {
                WanOSRetrofitUtil.giveVipMember(this.activityId, new ResponseCallBack<GetGiveVipMemberResponse>(null) { // from class: com.wanos.media.ui.login.dialog.LoginDialog.7
                    @Override // com.wanos.WanosCommunication.ResponseCallBack
                    public void onResponseSuccessful(GetGiveVipMemberResponse response2) {
                        LoginDialog.this.getUserInfo();
                        LoginDialog.this.showMsg(R.string.member_card_get_success);
                    }

                    @Override // com.wanos.WanosCommunication.ResponseCallBack
                    public void onResponseFailure(int code, String msg) {
                        LoginDialog.this.showMsg(msg);
                    }
                });
            }
            dismiss();
            return;
        }
        dismiss();
        showMsg(R.string.login_successful);
    }

    private void sendSmsCode(String phoneNum) {
        this.dialogLoginBinding.btSmsCode.setClickable(false);
        WanOSRetrofitUtil.getVerifyCode(phoneNum, new ResponseCallBack<BaseResponse>(this.context) { // from class: com.wanos.media.ui.login.dialog.LoginDialog.8
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(BaseResponse response) {
                LoginDialog.this.showMsg(R.string.sms_code_send_a);
                LoginDialog.this.startCountDown();
                LoginDialog.this.dialogLoginBinding.btSmsCode.setClickable(true);
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int code, String msg) {
                if (LoginDialog.this.downTimer != null) {
                    LoginDialog.this.downTimer.onCancel();
                }
                LoginDialog.this.showMsg(msg);
                LoginDialog.this.dialogLoginBinding.btSmsCode.setClickable(true);
            }
        });
    }

    public void startCountDown() {
        startCountDown(60L);
    }

    public void startCountDown(long seconds) {
        if (this.downTimer == null) {
            this.downTimer = new DownTimer(seconds * 1000, 1000L, this.dialogLoginBinding.btSmsCode);
        }
        this.downTimer.start();
    }

    @Override // com.wanos.media.base.BaseDialog, android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        this.dialogLoginBinding.etPhoneNumber.setText("");
        this.dialogLoginBinding.etSmsCode.setText("");
        DownTimer downTimer = this.downTimer;
        if (downTimer != null) {
            downTimer.onCancel();
        }
        this.handler.removeCallbacks(this.refreshWeChatrLoginRunnable);
        if (WebViewActivity.webViewMsgClient != null) {
            WebViewActivity.webViewMsgClient.removeWeb();
        }
        super.dismiss();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        DownTimer downTimer = this.downTimer;
        if (downTimer != null) {
            downTimer.onCancel();
        }
        this.handler.removeCallbacks(this.refreshWeChatrLoginRunnable);
        super.onDetachedFromWindow();
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
        dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getUserInfo() {
        WanOSRetrofitUtil.getUserInfo(new ResponseCallBack<GetUserInfoResponse>(null) { // from class: com.wanos.media.ui.login.dialog.LoginDialog.9
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetUserInfoResponse response) {
                UserInfo userInfo = response.data;
                if (userInfo != null) {
                    UserInfo userInfo2 = UserInfoUtil.getUserInfo();
                    userInfo2.setVip(userInfo.isVip());
                    userInfo2.setVipStartTime(userInfo.getVipStartTime());
                    userInfo2.setVipEndTime(userInfo.getVipEndTime());
                    UserInfoUtil.saveUserInfo(userInfo2);
                    EventBus.getDefault().post(new UserInfoChangeEvent(userInfo2));
                }
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int code, String msg) {
                if (code == 20000 || code == 20001 || code == 20005) {
                    Log.e(LoginDialog.TAG, "error logout");
                    UserInfoUtil.logout();
                    EventBus.getDefault().post(new LoginOrLogoutEvent(false));
                }
            }
        });
    }

    @Override // android.app.Dialog
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == 0) {
            onHideSoftInput();
            return true;
        }
        return super.onTouchEvent(event);
    }

    private void onHideSoftInput() {
        if (getCurrentFocus() == null || getCurrentFocus().getWindowToken() == null) {
            return;
        }
        hideSoftInput();
    }

    private void hideSoftInput() {
        if (this.dialogLoginBinding.etPhoneNumber != null && this.dialogLoginBinding.etPhoneNumber.hasFocus()) {
            this.imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
            this.dialogLoginBinding.etPhoneNumber.clearFocus();
        }
        if (this.dialogLoginBinding.etSmsCode == null || !this.dialogLoginBinding.etSmsCode.hasFocus()) {
            return;
        }
        this.imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
        this.dialogLoginBinding.etSmsCode.clearFocus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showMsg(int msg) {
        showMsg(this.context.getString(msg));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showMsg(String msg) {
        ToastUtil.showMsg(msg);
    }
}
