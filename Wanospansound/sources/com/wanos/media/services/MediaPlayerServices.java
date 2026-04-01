package com.wanos.media.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import androidx.core.app.NotificationCompat;
import com.wanos.media.playerlib.R;
import com.wanos.media.util.ZeroLogcatTools;
import com.wanos.zero.ZeroMediaTools;

/* JADX INFO: loaded from: classes3.dex */
public class MediaPlayerServices extends Service {
    private static final String CHANNEL_ID = "wanos.channel";
    private static final String CHANNEL_NAME = "wanos.channel.zero";
    private static final int NOTIFICATION_ID = 1;
    private static final String TAG = "MediaPlayerServices";
    private final LocalBinder mLocalBinder = new LocalBinder();
    private ZeroMediaTools mZeroMediaTools;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.mLocalBinder;
    }

    public class LocalBinder extends Binder {
        public LocalBinder() {
        }

        public MediaPlayerServices getServices() {
            return MediaPlayerServices.this;
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        ZeroLogcatTools.d(TAG, "onCreate");
        createNotification();
        if (Build.VERSION.SDK_INT >= 29) {
            startForeground(2, createNotification());
        } else {
            startForeground(1, createNotification());
        }
        this.mZeroMediaTools = new ZeroMediaTools();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        ZeroLogcatTools.d(TAG, "onStartCommand");
        return super.onStartCommand(intent, i, i2);
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        ZeroLogcatTools.d(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        ZeroLogcatTools.d(TAG, "onDestroy");
    }

    public ZeroMediaTools getZeroMediaTools() {
        return this.mZeroMediaTools;
    }

    private Notification createNotification() {
        ((NotificationManager) getSystemService(NotificationManager.class)).createNotificationChannel(new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, 3));
        return new NotificationCompat.Builder(this, CHANNEL_ID).setSmallIcon(R.drawable.ic_launcher).setContentTitle("零压空间正在运行中").setPriority(0).build();
    }
}
