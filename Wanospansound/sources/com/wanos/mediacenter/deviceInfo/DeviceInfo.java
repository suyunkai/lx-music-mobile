package com.wanos.mediacenter.deviceInfo;

import android.content.Context;
import android.util.Log;
import com.ecarx.eas.sdk.ECarXApiClient;
import com.ecarx.eas.sdk.device.api.DeviceAPI;

/* JADX INFO: loaded from: classes3.dex */
public class DeviceInfo {
    public static final String TAG = "wanos:[DeviceInfo]";
    protected static DeviceInfo mDeviceInfo;
    protected String mVin = "";

    public String getVin() {
        return this.mVin;
    }

    public void setVin(String str) {
        this.mVin = str;
    }

    public static DeviceInfo getInstance() {
        if (mDeviceInfo == null) {
            mDeviceInfo = new DeviceInfo();
        }
        return mDeviceInfo;
    }

    public void init(Context context) {
        final DeviceAPI deviceAPI = DeviceAPI.get(context.getApplicationContext());
        deviceAPI.init(context.getApplicationContext(), new ECarXApiClient.Callback() { // from class: com.wanos.mediacenter.deviceInfo.DeviceInfo.1
            @Override // com.ecarx.eas.sdk.ECarXApiClient.Callback
            public void onAPIReady(boolean z) {
                if (z) {
                    DeviceInfo.this.mVin = deviceAPI.getVIN();
                } else {
                    Log.i(DeviceInfo.TAG, "device init faile");
                }
            }
        });
    }
}
