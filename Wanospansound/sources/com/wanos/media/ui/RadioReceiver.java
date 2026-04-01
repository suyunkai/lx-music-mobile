package com.wanos.media.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.wanos.media.MainApplication;
import com.wanos.wanosplayermodule.MediaPlayEngine;
import com.wanos.wanosplayermodule.MediaPlayerService;

/* JADX INFO: loaded from: classes3.dex */
public final class RadioReceiver extends BroadcastReceiver {
    public static final String ACTION_CLOSE_PLAYER = "ecarx.intent.broadcast.action.ECARX_VR_APP_CLOSE";
    private static final String TAG = "wanos:[RadioReceiver]";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action;
        MediaPlayerService mediaPlayerService;
        if (intent == null || (action = intent.getAction()) == null) {
            return;
        }
        Log.i(TAG, "onReceive: action = " + action);
        if (!TextUtils.equals(ACTION_CLOSE_PLAYER, action) || (mediaPlayerService = MediaPlayEngine.getInstance().getMediaPlayerService()) == null) {
            return;
        }
        if (mediaPlayerService.isPlaying()) {
            mediaPlayerService.pause();
        }
        if (!MainApplication.getInstance().isAppForeground(context) || MainApplication.topActivity == null) {
            return;
        }
        MainApplication.topActivity.moveTaskToBack(true);
    }
}
