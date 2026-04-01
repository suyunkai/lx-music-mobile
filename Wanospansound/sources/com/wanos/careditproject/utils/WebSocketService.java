package com.wanos.careditproject.utils;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

/* JADX INFO: loaded from: classes3.dex */
public class WebSocketService extends Service {
    private static final String TAG = "websocket";
    private static final String WS = "ws://121.40.165.18:8800";
    private WebSocket webSocket;
    private WebSocketCallback webSocketCallback;
    private int reconnectTimeout = 5000;
    private boolean connected = false;
    private Handler handler = new Handler();

    public interface WebSocketCallback {
        void onClosed();

        void onMessage(String str);

        void onOpen();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return 1;
    }

    public class LocalBinder extends Binder {
        public LocalBinder() {
        }

        public WebSocketService getService() {
            return WebSocketService.this;
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return new LocalBinder();
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.webSocket = connect();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        if (this.webSocket != null) {
            close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public WebSocket connect() {
        Log.d(TAG, "connect ws://121.40.165.18:8800");
        return new OkHttpClient.Builder().build().newWebSocket(new Request.Builder().url(WS).build(), new WebSocketHandler());
    }

    public void send(String str) {
        Log.d(TAG, "send " + str);
        WebSocket webSocket = this.webSocket;
        if (webSocket != null) {
            webSocket.send(str);
        }
    }

    public void close() {
        WebSocket webSocket = this.webSocket;
        if (webSocket != null) {
            Log.d(TAG, "shutDownFlag " + webSocket.close(1000, "manual close"));
            this.webSocket = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reconnect() {
        this.handler.postDelayed(new Runnable() { // from class: com.wanos.careditproject.utils.WebSocketService.1
            @Override // java.lang.Runnable
            public void run() {
                Log.d(WebSocketService.TAG, "reconnect...");
                if (WebSocketService.this.connected) {
                    return;
                }
                WebSocketService.this.connect();
                WebSocketService.this.handler.postDelayed(this, WebSocketService.this.reconnectTimeout);
            }
        }, this.reconnectTimeout);
    }

    private class WebSocketHandler extends WebSocketListener {
        private WebSocketHandler() {
        }

        @Override // okhttp3.WebSocketListener
        public void onOpen(WebSocket webSocket, Response response) {
            Log.d(WebSocketService.TAG, "onOpen");
            if (WebSocketService.this.webSocketCallback != null) {
                WebSocketService.this.webSocketCallback.onOpen();
            }
            WebSocketService.this.connected = true;
        }

        @Override // okhttp3.WebSocketListener
        public void onMessage(WebSocket webSocket, String str) {
            Log.d(WebSocketService.TAG, "onMessage " + str);
            if (WebSocketService.this.webSocketCallback != null) {
                WebSocketService.this.webSocketCallback.onMessage(str);
            }
        }

        @Override // okhttp3.WebSocketListener
        public void onClosed(WebSocket webSocket, int i, String str) {
            Log.d(WebSocketService.TAG, "onClosed");
            if (WebSocketService.this.webSocketCallback != null) {
                WebSocketService.this.webSocketCallback.onClosed();
            }
            WebSocketService.this.connected = false;
            WebSocketService.this.reconnect();
        }

        @Override // okhttp3.WebSocketListener
        public void onFailure(WebSocket webSocket, Throwable th, Response response) {
            Log.d(WebSocketService.TAG, "onFailure " + th.getMessage());
            WebSocketService.this.connected = false;
            WebSocketService.this.reconnect();
        }
    }

    public void setWebSocketCallback(WebSocketCallback webSocketCallback) {
        this.webSocketCallback = webSocketCallback;
    }
}
