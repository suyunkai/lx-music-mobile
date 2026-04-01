package com.wanos.media.util;

import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.wanos.commonlibrary.utils.CommonUtils;
import com.wanos.media.BuildConfig;
import com.wanos.media.MainApplication;
import com.wanos.media.base.CarConstants;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class RequestParameterUtils {
    public static final String TAG = "wanos:[RequestParameterUtils]";
    static final String VERSION_KEY = "version";
    public static String deviceIdValue = "";
    public static boolean isInit = false;
    static Map<String, String> publicParameterMap;
    static final String APPID_KEY = "appId";
    static final String BRANDID_KEY = "brandId";
    static final String CHANNEL_KEY = "channel";
    static final String COMPANYID_KEY = "companyId";
    static final String DEVICEID_KEY = "deviceId";
    static final String MODELID_KEY = "modelId";
    static final String TIMESTAMP_KEY = "timestamp";
    static String[] keys = {APPID_KEY, BRANDID_KEY, CHANNEL_KEY, COMPANYID_KEY, DEVICEID_KEY, MODELID_KEY, TIMESTAMP_KEY, "version"};

    public static void init() {
        Arrays.sort(keys);
        deviceIdValue = CommonUtils.getDeviceId(MainApplication.getInstance());
        HashMap map = new HashMap();
        publicParameterMap = map;
        map.put(APPID_KEY, String.valueOf(CarConstants.releaseAppId[CarConstants.buildIndex]));
        publicParameterMap.put(BRANDID_KEY, CarConstants.releaseBrandId[CarConstants.buildIndex]);
        publicParameterMap.put(CHANNEL_KEY, CommonUtils.getAppChannelId());
        publicParameterMap.put(COMPANYID_KEY, ExifInterface.GPS_MEASUREMENT_3D);
        publicParameterMap.put(DEVICEID_KEY, deviceIdValue);
        publicParameterMap.put(MODELID_KEY, CarConstants.releaseModelId[CarConstants.buildIndex]);
        publicParameterMap.put(TIMESTAMP_KEY, "");
        publicParameterMap.put("version", BuildConfig.VERSION_NAME);
        isInit = true;
    }

    public static void updateDeviceId(String deviceId) {
        if (!isInit) {
            init();
        }
        if (TextUtils.isEmpty(deviceId) || deviceId.equals(publicParameterMap.get(DEVICEID_KEY))) {
            return;
        }
        publicParameterMap.put(DEVICEID_KEY, deviceId);
    }

    public static String getSignParameterString(long timeStamp) {
        if (!isInit) {
            init();
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < keys.length; i++) {
            if (i != 0) {
                stringBuffer.append("&");
            }
            stringBuffer.append(keys[i] + "=");
            if (keys[i].equals(TIMESTAMP_KEY)) {
                stringBuffer.append(timeStamp + "");
            } else {
                stringBuffer.append(publicParameterMap.get(keys[i]));
            }
        }
        return stringBuffer.toString();
    }

    public static String getDeviceToken(long timeStamp) {
        return CommonUtils.base64(getSignParameterString(timeStamp));
    }

    public static String getSign(long timeStamp) {
        return CommonUtils.md5(getSignParameterString(timeStamp) + "&key=r00j}Mlc{vy3{d_x");
    }
}
