package com.wanos.media.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import com.wanos.wanosplayermodule.MediaPlayEngine;
import com.wanos.wanosplayermodule.util.MediaSharedPreferUtils;

/* JADX INFO: loaded from: classes3.dex */
public class StrReceiver extends BroadcastReceiver {
    public static final String TAG = "wanos:[StrReceiver]";
    public static boolean isRecovery = false;

    public static boolean isRecovery() {
        return isRecovery;
    }

    public static void setRecovery(boolean recovery) {
        isRecovery = recovery;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: " + intent.getAction());
        if (TextUtils.equals(intent.getAction(), "ecarx.intent.action.AUDIO_FOCUS_RELEASE")) {
            Log.d(TAG, "收到str解锁消息，开始尝试播放");
            if (isRecovery) {
                Log.i(TAG, "onReceive: isRecovery == true");
                isRecovery = false;
                if (MediaSharedPreferUtils.getIsPlaying()) {
                    Log.d(TAG, "收到str开始播放1");
                    MediaPlayEngine.getInstance().getMediaPlayerService().playMedia(MediaSharedPreferUtils.getMediainfo());
                    return;
                }
                return;
            }
            Log.i(TAG, "onReceive: isRecovery == false");
            return;
        }
        if (TextUtils.equals(intent.getAction(), "ecarx.intent.action.DISPLAY_OFF") || TextUtils.equals(intent.getAction(), "ecarx.intent.action.carsignal.AVNOFF_ON")) {
            Log.i(TAG, "onReceive: 收到锁车str消息，action:" + intent.getAction());
            boolean zIsPlaying = MediaPlayEngine.getInstance().getMediaPlayerService().isPlaying();
            Log.d(TAG, "当前播放状态" + zIsPlaying);
            if (zIsPlaying) {
                new Handler().postDelayed(new Runnable() { // from class: com.wanos.media.ui.StrReceiver.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Log.d(StrReceiver.TAG, "延时设置当前sp 为true");
                        MediaSharedPreferUtils.putIsPlaying(true);
                    }
                }, 100L);
            } else {
                MediaPlayEngine.getInstance().getMediaPlayerService().hasDisplayOffMessages();
                Log.d(TAG, "当前未播放状态");
            }
            Log.i(TAG, "onReceive: 收到str消息:" + intent.getAction());
            return;
        }
        Log.i(TAG, "onReceive: 收到str消息2:" + intent.getAction());
    }
}
