package com.wanos.careditproject.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.wanos.careditproject.view.EditingActivity;

/* JADX INFO: loaded from: classes3.dex */
public class WanosTestReceiver extends BroadcastReceiver {
    private static final String TAG = "wanos:[WanosTestReceiver]";
    private static WanosTestReceiver instance;

    public static WanosTestReceiver getInstance() {
        if (instance == null) {
            instance = new WanosTestReceiver();
        }
        return instance;
    }

    public static void clearInstance() {
        instance = null;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.d(TAG, "Received broadcast with param: " + intent.getStringExtra("param") + ", Action: " + action);
        if ("com.wanos.DRIVE_ACTION".equals(action)) {
            EditingActivity.finishEditActivity();
        }
    }
}
