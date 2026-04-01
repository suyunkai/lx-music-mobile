package com.wanos.media.ui.advertise;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import com.wanos.media.ui.advertise.WebViewMsgClient;

/* JADX INFO: loaded from: classes3.dex */
public class WebViewMessengerService extends Service {
    static final int MSG_CONTENT = 0;
    static final String MSG_CONTENT_KEY = "TO_SERVICE_CONTENT";
    static final int MSG_OPEN_WEB = 4;
    static final int MSG_REGISTER_CLIENT = 1;
    static final int MSG_TO_WEB = 3;
    static final int MSG_UNREGISTER_CLIENT = 2;
    private static final String TAG = "wanos:[WebViewMessengerService]";
    public static WebViewMessengerService messengerService;
    IncomingHandler target = new IncomingHandler();
    final Messenger messenger = new Messenger(this.target);
    public WebViewLoader loader = null;
    public WebViewMsgClient.WeChatLis weChatLis = null;

    private static class IncomingHandler extends Handler {
        public Messenger mClient;

        private IncomingHandler() {
            this.mClient = null;
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int i = msg.what;
            if (i == 1) {
                this.mClient = msg.replyTo;
                Log.e(WebViewMessengerService.TAG, "got client:" + (msg.replyTo == null));
                return;
            }
            if (i == 2) {
                this.mClient = null;
                return;
            }
            if (i != 3) {
                if (i != 4) {
                    return;
                }
                WebViewMessengerService.messengerService.loadUrl(msg.getData().getString(WebViewMessengerService.MSG_CONTENT_KEY));
            } else {
                String string = msg.getData().getString(WebViewMessengerService.MSG_CONTENT_KEY);
                if (WebViewActivity.activity != null) {
                    WebViewActivity.activity.wanosJsBridge.callJs(string);
                }
            }
        }
    }

    public void loadUrl(String content) {
        if (this.loader == null) {
            this.loader = new WebViewLoader();
        }
        this.loader.loadUrl(content);
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        messengerService = this;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        WebViewLoader webViewLoader = this.loader;
        if (webViewLoader != null) {
            webViewLoader.destory();
        }
        Log.e(TAG, "onDestroy..");
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.messenger.getBinder();
    }

    public void sendMessageToClient(String text) {
        sendToClient(text, 0);
    }

    public void sendOpToClient(String text) {
        sendToClient(text, 1);
    }

    public void sendWebToClient(String text) {
        sendToClient(text, 3);
    }

    public void sendToClient(String text, int type) {
        if (this.target.mClient != null) {
            try {
                Bundle bundle = new Bundle();
                bundle.putString("TO_CLIENT_CONTENT", text);
                Message messageObtain = Message.obtain((Handler) null, type);
                messageObtain.setData(bundle);
                this.target.mClient.send(messageObtain);
            } catch (RemoteException e) {
                this.target.mClient = null;
                Log.e(TAG, "e1:" + e.getMessage());
            }
        }
    }

    public void closeConnect() {
        if (this.target.mClient != null) {
            try {
                Bundle bundle = new Bundle();
                Message messageObtain = Message.obtain((Handler) null, 2);
                messageObtain.setData(bundle);
                this.target.mClient.send(messageObtain);
            } catch (RemoteException e) {
                this.target.mClient = null;
                Log.e(TAG, "e2:" + e.getMessage());
            }
        }
    }
}
