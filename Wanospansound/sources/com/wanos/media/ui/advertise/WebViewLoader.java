package com.wanos.media.ui.advertise;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.gson.Gson;
import java.io.ByteArrayInputStream;

/* JADX INFO: loaded from: classes3.dex */
public class WebViewLoader {
    private static final String TAG = "wanos:[WebViewLoader]";
    private String loadedUrl;
    private Runnable runnable;
    private WebView webView;
    private WebViewClient webViewClient;
    private Handler handler = new Handler();
    private int retryTime = 0;
    private final int retryLimitTime = 1;

    static /* synthetic */ int access$408(WebViewLoader webViewLoader) {
        int i = webViewLoader.retryTime;
        webViewLoader.retryTime = i + 1;
        return i;
    }

    public WebViewLoader() {
        Runnable runnable = new Runnable() { // from class: com.wanos.media.ui.advertise.WebViewLoader.1
            @Override // java.lang.Runnable
            public void run() {
                Log.e(WebViewLoader.TAG, "ping...2:" + WebViewLoader.this.webView.isEnabled());
                WebViewLoader.this.handler.postDelayed(WebViewLoader.this.runnable, 500L);
            }
        };
        this.runnable = runnable;
        this.handler.postDelayed(runnable, 500L);
    }

    public void loadUrl(String content) {
        if (WebViewMessengerService.messengerService == null) {
            return;
        }
        this.loadedUrl = content;
        initWebViewClient();
        WebView webView = new WebView(WebViewMessengerService.messengerService);
        this.webView = webView;
        webView.getSettings().setMixedContentMode(0);
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        this.webView.getSettings().setDomStorageEnabled(true);
        this.webView.getSettings().setCacheMode(2);
        this.webView.setWebViewClient(this.webViewClient);
        this.webView.setWebChromeClient(new WebChromeClient() { // from class: com.wanos.media.ui.advertise.WebViewLoader.2
            @Override // android.webkit.WebChromeClient
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }

            @Override // android.webkit.WebChromeClient
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                return super.onConsoleMessage(consoleMessage);
            }
        });
        this.webView.loadUrl(content);
    }

    private void initWebViewClient() {
        if (this.webViewClient == null) {
            this.webViewClient = new WebViewClient() { // from class: com.wanos.media.ui.advertise.WebViewLoader.3
                @Override // android.webkit.WebViewClient
                public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                    super.onReceivedError(view, request, error);
                    Log.e(WebViewLoader.TAG, "error:" + error.getErrorCode() + "_error:" + ((Object) error.getDescription()));
                    if (TextUtils.isEmpty(WebViewLoader.this.loadedUrl) || WebViewLoader.this.retryTime >= 1) {
                        return;
                    }
                    WebViewLoader.access$408(WebViewLoader.this);
                    try {
                        WebViewLoader.this.webView.loadUrl(WebViewLoader.this.loadedUrl);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override // android.webkit.WebViewClient
                public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                    super.onReceivedHttpError(view, request, errorResponse);
                    Log.e(WebViewLoader.TAG, "errorResp:" + errorResponse.getStatusCode());
                }

                @Override // android.webkit.WebViewClient
                public void onLoadResource(WebView view, String url) {
                    Log.e(WebViewLoader.TAG, "load:" + url);
                    super.onLoadResource(view, url);
                }

                @Override // android.webkit.WebViewClient
                public void onPageFinished(WebView view, String url) {
                    Log.e(WebViewLoader.TAG, "finish:" + url);
                    super.onPageFinished(view, url);
                }

                @Override // android.webkit.WebViewClient
                public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                    Log.e(WebViewLoader.TAG, "shouldInter...1:" + request.getUrl());
                    if (request.getUrl().toString().contains("qrcode")) {
                        WebViewCommunicationBase webViewCommunicationBase = new WebViewCommunicationBase();
                        webViewCommunicationBase.action = WebViewCommunicationBase.ACTION_WECHAT_URL;
                        webViewCommunicationBase.para = request.getUrl().toString();
                        Log.e(WebViewLoader.TAG, "send to web ... 1");
                        if (WebViewMessengerService.messengerService != null && WebViewMessengerService.messengerService.weChatLis != null) {
                            WebViewMessengerService.messengerService.weChatLis.onWechatImgUrl(webViewCommunicationBase.para);
                        } else {
                            WebViewLoader.this.sendToWeb(webViewCommunicationBase);
                        }
                    }
                    if (request.getUrl().toString().contains("wanos") && !request.getUrl().toString().contains("redirect_uri") && !request.getUrl().toString().contains("favicon.ico")) {
                        WebViewCommunicationBase webViewCommunicationBase2 = new WebViewCommunicationBase();
                        webViewCommunicationBase2.action = WebViewCommunicationBase.ACTION_WECHAT_LOGIN_CODE;
                        webViewCommunicationBase2.para = request.getUrl().getQueryParameter("code");
                        Log.e(WebViewLoader.TAG, "send to web ... 2");
                        if (WebViewMessengerService.messengerService != null && WebViewMessengerService.messengerService.weChatLis != null) {
                            WebViewMessengerService.messengerService.weChatLis.onWechatCode(webViewCommunicationBase2.para);
                        } else {
                            WebViewLoader.this.sendToWeb(webViewCommunicationBase2);
                        }
                        return new WebResourceResponse("application/javascript", "UTF-8", new ByteArrayInputStream("console.log('Intercepted response from shouldInterceptRequest');".getBytes()));
                    }
                    return super.shouldInterceptRequest(view, request);
                }
            };
        }
    }

    public void sendToWeb(WebViewCommunicationBase bs) {
        if (WebViewMessengerService.messengerService != null) {
            WebViewMessengerService.messengerService.sendWebToClient(new Gson().toJson(bs));
        }
    }

    public void destory() {
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeCallbacks(this.runnable);
        }
    }
}
