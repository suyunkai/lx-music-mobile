package com.wanos.media.ui.advertise;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.CookieManager;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatDelegate;
import com.google.gson.Gson;
import com.wanos.commonlibrary.event.LoginOrLogoutEvent;
import com.wanos.commonlibrary.utils.SystemAndServerTimeSynchUtil;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.media.R;
import com.wanos.media.databinding.ActivityWebviewBinding;
import com.wanos.media.presenter.BasePresenter;
import com.wanos.media.util.RequestParameterUtils;
import com.wanos.wanosplayermodule.MediaPlayEngine;

/* JADX INFO: loaded from: classes3.dex */
public class WebViewActivity extends WanosBaseWebActivity<BasePresenter> {
    public static final String EXTRA_DEVICE = "extra_device_token";
    public static final String EXTRA_PLAY_STATUS = "extra_play_status";
    public static final String EXTRA_TOKEN = "extra_token";
    public static final String TAG = "wanos:[WebViewActivity]";
    public static WebViewActivity activity = null;
    public static final String extraUrl = "extra_url";
    public static WebViewMsgClient webViewMsgClient;
    private String baseUrl;
    public ActivityWebviewBinding binding;
    private int currentNightMode = -1;
    private Messenger mMainMessenger;
    WanosJsBridge wanosJsBridge;

    @Override // com.wanos.media.ui.advertise.WanosBaseWebActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
        ActivityWebviewBinding activityWebviewBindingInflate = ActivityWebviewBinding.inflate(getLayoutInflater());
        this.binding = activityWebviewBindingInflate;
        setContentView(activityWebviewBindingInflate.getRoot());
        activity = this;
        setTitleBarVisibility(8);
        this.binding.webView.getSettings().setMixedContentMode(0);
        this.binding.webView.getSettings().setJavaScriptEnabled(true);
        this.binding.webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        this.binding.webView.getSettings().setDomStorageEnabled(true);
        this.binding.webView.getSettings().setCacheMode(2);
        this.wanosJsBridge = new WanosJsBridge(this, this.binding.webView);
        this.binding.webView.addJavascriptInterface(this.wanosJsBridge, WanosJsBridge.jsName);
        this.binding.webView.setWebViewClient(new WebViewClient() { // from class: com.wanos.media.ui.advertise.WebViewActivity.1
            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                WebViewActivity.this.initStyle();
                if (WebViewMessengerService.messengerService != null) {
                    WebViewCommunicationBase webViewCommunicationBase = new WebViewCommunicationBase();
                    webViewCommunicationBase.action = WebViewCommunicationBase.ACTION_WEB_INIT_SUCCESS;
                    try {
                        WebViewMessengerService.messengerService.sendMessageToClient(new Gson().toJson(webViewCommunicationBase));
                    } catch (Exception e) {
                        Log.e(WebViewActivity.TAG, "e1:" + e.getMessage());
                    }
                }
            }
        });
        this.binding.webView.setWebChromeClient(new WebChromeClient() { // from class: com.wanos.media.ui.advertise.WebViewActivity.2
            @Override // android.webkit.WebChromeClient
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }

            @Override // android.webkit.WebChromeClient
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                return super.onConsoleMessage(consoleMessage);
            }
        });
        this.binding.ivBack.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.ui.advertise.WebViewActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m488lambda$onCreate$0$comwanosmediauiadvertiseWebViewActivity(view);
            }
        });
        initData();
        String stringExtra = getIntent().getStringExtra(EXTRA_TOKEN);
        this.mMainMessenger = (Messenger) getIntent().getParcelableExtra("main_messenger");
        initCookie(stringExtra);
        if (UserInfoUtil.getUserInfo() != null) {
            UserInfoUtil.getUserInfo().setToken(stringExtra);
        }
        RequestParameterUtils.updateDeviceId(getIntent().getStringExtra(EXTRA_DEVICE));
        String str = this.baseUrl + (this.baseUrl.contains("?") ? "&" : "?") + (getIntent().getBooleanExtra(EXTRA_PLAY_STATUS, false) ? "playStatus=1" : "playStatus=0") + ("&style=" + ((getResources().getConfiguration().uiMode & 48) != 32 ? '1' : '0')) + "&isSupLogin=1";
        this.binding.webView.loadUrl(str);
        Log.i(TAG, " url= " + str);
        this.binding.webView.setBackgroundColor(0);
        this.binding.webView.getBackground().setAlpha(0);
    }

    /* JADX INFO: renamed from: lambda$onCreate$0$com-wanos-media-ui-advertise-WebViewActivity, reason: not valid java name */
    /* synthetic */ void m488lambda$onCreate$0$comwanosmediauiadvertiseWebViewActivity(View view) {
        onBackPressed();
    }

    private void initCookie(String token) {
        long sytemTrueTime = SystemAndServerTimeSynchUtil.getSytemTrueTime() / 1000;
        String deviceToken = RequestParameterUtils.getDeviceToken(sytemTrueTime);
        String str = "deviceToken=" + deviceToken;
        String str2 = "sign=" + RequestParameterUtils.getSign(sytemTrueTime);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeAllCookies(new ValueCallback<Boolean>() { // from class: com.wanos.media.ui.advertise.WebViewActivity.3
            @Override // android.webkit.ValueCallback
            public void onReceiveValue(Boolean value) {
            }
        });
        cookieManager.setCookie(this.baseUrl, "userToken=" + token);
        cookieManager.setCookie(this.baseUrl, str);
        cookieManager.setCookie(this.baseUrl, str2);
        Log.i(TAG, "cookie:" + cookieManager.getCookie(this.baseUrl));
        cookieManager.flush();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initStyle() {
        int i = getResources().getConfiguration().uiMode & 48;
        this.currentNightMode = i;
        this.wanosJsBridge.setNightMode(i);
    }

    private void initData() {
        Intent intent = getIntent();
        if (intent.getData() != null) {
            this.baseUrl = intent.getData().toString().split("url=")[1];
        } else {
            this.baseUrl = getIntent().getStringExtra(extraUrl);
        }
    }

    public static void start(Context context, String url, String token, Messenger mMainMessenger) {
        if (webViewMsgClient == null) {
            webViewMsgClient = new WebViewMsgClient();
        }
        webViewMsgClient.bindService(context);
        Intent intent = new Intent(context, (Class<?>) WebViewActivity.class);
        intent.putExtra("main_messenger", mMainMessenger);
        intent.putExtra(extraUrl, url);
        intent.putExtra(EXTRA_TOKEN, token);
        intent.putExtra(EXTRA_DEVICE, RequestParameterUtils.deviceIdValue);
        intent.putExtra(EXTRA_PLAY_STATUS, !(MediaPlayEngine.getInstance().getMediaPlayerService() == null || MediaPlayEngine.getInstance().getMediaPlayerService().getCurMediaInfo() == null || !MediaPlayEngine.getInstance().getMediaPlayerService().isPlaying()));
        context.startActivity(intent);
    }

    @Override // com.wanos.media.ui.advertise.WanosBaseWebActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int i = newConfig.uiMode & 48;
        if (this.currentNightMode != i) {
            this.currentNightMode = i;
            if (i == 16) {
                AppCompatDelegate.setDefaultNightMode(1);
            } else if (i == 32) {
                AppCompatDelegate.setDefaultNightMode(2);
            }
        }
        this.wanosJsBridge.setNightMode(this.currentNightMode);
        this.binding.ivBack.setImageResource(R.drawable.icon_back);
    }

    @Override // com.wanos.media.ui.advertise.WanosBaseWebActivity
    public void loginOrLogout(LoginOrLogoutEvent event) {
        initCookie(UserInfoUtil.getToken());
        String json = new Gson().toJson(UserInfoUtil.getUserInfo());
        if (this.mMainMessenger != null && UserInfoUtil.getToken() != null) {
            Message messageObtain = Message.obtain();
            Bundle bundle = new Bundle();
            bundle.putString(WanosJsBridge.METHOD_KEY_TOKEN, UserInfoUtil.getToken());
            bundle.putString("userInfo", json);
            messageObtain.setData(bundle);
            try {
                this.mMainMessenger.send(messageObtain);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
        this.wanosJsBridge.updataToken(UserInfoUtil.getToken());
        if (WebViewMessengerService.messengerService != null) {
            new WebViewUserinfo().userInfo = UserInfoUtil.getUserInfo();
            try {
                WebViewMessengerService.messengerService.sendMessageToClient(json);
            } catch (Exception e2) {
                Log.e(TAG, "e:" + e2.getMessage());
            }
        }
    }

    @Override // com.wanos.media.ui.advertise.WanosBaseWebActivity, com.wanos.commonlibrary.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (WebViewMessengerService.messengerService != null) {
            try {
                WebViewMessengerService.messengerService.closeConnect();
                Log.i(TAG, "onDestroy: closeConnect");
                return;
            } catch (Exception e) {
                e.printStackTrace();
                Log.i(TAG, "onDestroy: closeConnect error");
                return;
            }
        }
        if (isFinishing()) {
            Process.killProcess(Process.myPid());
            Log.i(TAG, "onDestroy: isFinishing is true, 杀死进程 ");
        } else {
            Log.i(TAG, "onDestroy:  isFinishing is false, 不需要杀死进程 ");
        }
    }
}
