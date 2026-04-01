package com.wanos.wanosplayermodule.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import com.ecarx.eas.sdk.IServiceManager;

/* JADX INFO: loaded from: classes3.dex */
public class PhoneStateReceiver extends BroadcastReceiver {
    public static final String BT_HEADSET_CLIENT_ACTION_CALL_CHANGED = "android.bluetooth.headsetclient.profile.action.AG_CALL_CHANGED";
    public static final String BT_HEADSET_CLIENT_EXTRA_CALL = "android.bluetooth.headsetclient.extra.CALL";
    private static final String TAG = "PhoneStateReceiver";
    public static int lastState = -1;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.e(TAG, "onReceive: ==============>");
        if (BT_HEADSET_CLIENT_ACTION_CALL_CHANGED.equals(action)) {
            Parcelable parcelableExtra = intent.getParcelableExtra(BT_HEADSET_CLIENT_EXTRA_CALL);
            Log.e(TAG, "PhoneStateReceiver bluetoothHeadsetClientCall = " + String.valueOf(parcelableExtra));
            try {
                int iIntValue = ((Integer) Class.forName("android.bluetooth.BluetoothHeadsetClientCall").getMethod("getState", new Class[0]).invoke(parcelableExtra, new Object[0])).intValue();
                lastState = iIntValue;
                Log.e(TAG, "PhoneStateReceiver state = " + iIntValue);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        Log.e(TAG, "PhoneStateReceiver onReceive state: " + intent.getStringExtra(IServiceManager.SERVICE_STATE));
    }
}
