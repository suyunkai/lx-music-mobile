package com.wanos.mediacenter;

import android.content.Context;
import android.util.Log;
import com.ecarx.eas.sdk.ECarXApiClient;
import com.ecarx.eas.sdk.device.api.DeviceAPI;
import com.wanos.commonlibrary.mediaCenter.CarInfo;

/* JADX INFO: loaded from: classes3.dex */
public class CarInfoImp implements CarInfo {
    private static final String TAG = "wanos:[CarInfoImp]";
    private String mVin;

    @Override // com.wanos.commonlibrary.mediaCenter.CarInfo
    public void init(Context context, final CarInfo.OnVinLis onVinLis) {
        final DeviceAPI deviceAPI = DeviceAPI.get(context.getApplicationContext());
        deviceAPI.init(context.getApplicationContext(), new ECarXApiClient.Callback() { // from class: com.wanos.mediacenter.CarInfoImp.1
            @Override // com.ecarx.eas.sdk.ECarXApiClient.Callback
            public void onAPIReady(boolean z) {
                if (z) {
                    CarInfoImp.this.mVin = deviceAPI.getVIN();
                    CarInfo.OnVinLis onVinLis2 = onVinLis;
                    if (onVinLis2 != null) {
                        try {
                            onVinLis2.onGetVin(CarInfoImp.this.mVin);
                            return;
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    return;
                }
                Log.i(CarInfoImp.TAG, "device init faile");
            }
        });
    }

    @Override // com.wanos.commonlibrary.mediaCenter.CarInfo
    public String getVin() {
        return this.mVin;
    }
}
