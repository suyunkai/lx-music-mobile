package com.wanos.WanosCommunication.service;

import android.util.Log;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.WanosCommunication.response.GetUserEventStatistic;

/* JADX INFO: loaded from: classes3.dex */
public class MediaStatistic {
    public static final String TAG = "wanos:[MediaStatistic]";
    private static MediaStatistic mInstance;
    private boolean isNeedStatistic = true;

    public static MediaStatistic getInstance() {
        if (mInstance == null) {
            mInstance = new MediaStatistic();
        }
        return mInstance;
    }

    public void saveUserEventStatistic(String str, String str2, String str3, String str4, String str5, int i) {
        Log.i(TAG, "saveUserEventStatistic: eventCode=" + str + ", eventObjectId=" + str2 + ", productId=" + str3 + ", extData=" + str4 + ", externalUid=" + str5 + ", source=" + i);
        if (this.isNeedStatistic) {
            WanOSRetrofitUtil.saveUserEventStatistic(str, str2, str3, str4, str5, i, new ResponseCallBack<GetUserEventStatistic>(null) { // from class: com.wanos.WanosCommunication.service.MediaStatistic.1
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int i2, String str6) {
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(GetUserEventStatistic getUserEventStatistic) {
                }
            });
        }
    }

    public void saveUserEventStatistic(String str, String str2, String str3, String str4, String str5) {
        saveUserEventStatistic(str, str2, str3, str4, str5, 0);
    }

    public void saveUserEventStatistic(String str, String str2, String str3, String str4) {
        saveUserEventStatistic(str, str2, str3, str4, "", 0);
    }

    public void saveUserEventStatistic(String str, String str2, String str3) {
        saveUserEventStatistic(str, str2, str3, "", "", 0);
    }

    public void saveUserEventStatistic(String str, String str2) {
        saveUserEventStatistic(str, str2, "", "", "", 0);
    }

    public void saveUserEventStatistic(String str) {
        saveUserEventStatistic(str, "", "", "", "", 0);
    }
}
