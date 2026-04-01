package com.wanos.media.ui;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.blankj.utilcode.util.LogUtils;
import com.wanos.wanosplayermodule.MediaPlayerService;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes3.dex */
public class BootBroadReceiver extends BroadcastReceiver {
    public static final String ACTION_KILLED_RESUME_PLAYER = "action_killed_player";
    private static final String TAG = "wanos:[BootBroadReceiver]";
    public static long bootCompleteTime;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        bootCompleteTime = System.currentTimeMillis();
        Log.i(TAG, "BootBroadReceiver onReceive");
        Object[] objArr = new Object[1];
        objArr[0] = "onReceive:" + (intent != null ? intent.getAction() : null);
        LogUtils.iTag(TAG, objArr);
        if (Intrinsics.areEqual(intent != null ? intent.getAction() : null, "ecarx.xsf.mediacenter.action.BROADCAST_MEDIA_CENTER")) {
            intent.setComponent(new ComponentName("", MediaPlayerService.class.getName()));
            intent.setAction(ACTION_KILLED_RESUME_PLAYER);
        }
    }
}
