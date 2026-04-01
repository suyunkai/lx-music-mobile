package com.wanos.media.util;

import android.util.Log;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.WanosCommunication.bean.DeviceTokenEntity;
import com.wanos.WanosCommunication.bean.SignUtilsEntity;
import com.wanos.WanosCommunication.response.DeviceTokenResponse;
import com.wanos.commonlibrary.utils.CommonUtils;
import com.wanos.media.BuildConfig;
import com.wanos.media.base.CarConstants;
import com.wanos.sdk.WanosAudioManager;
import com.wanos.sdk.audio.IWanosAudioConfig;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import javax.crypto.Cipher;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.Buffer;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class SignUtils {
    private static volatile SignUtils INSTANCE = null;
    private static final int NONCE_STR_SIZE = 5;
    private static String PUBLIC_KEY_STR = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC/wpwQY4fgRFEp6A8NnIruIZHrSfFt74WoqBXZxGY5UNiFZ/5VgSLeo7ZfQS7AiKp+eHR5e5aRKnD8hrug8cvF3bYtBWJgHA6ZL/j/AhaaH3GIvfZyN/M+0MjCMgPLoXagCHurXtTG/9R9t92ZSWImdaWfSkAgwgarh4+iNCELSQIDAQAB";
    private static final String RANDOM_TEXT = "ZXCVBNMLKJHGFDSAQWERTYUIOP0123456789zxcvbnmlkjhgfdsaqwertyuiop";
    public static String SING_KEY = null;
    private static final String TAG = "wanos[SignUtils]";
    private PublicKey PUBLIC_KEY;
    private final Random RANDOM = new Random();
    private IWanosAudioConfig iWanosAudioConfig;

    public void init() {
        SING_KEY = "xArIdn0gAM3oZHTnJdXvLak17zULPP3L";
        PUBLIC_KEY_STR = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDT1t09Zz6Aur6siH9ikMBXUnlTtxVvwx9xN7yJP9+Wd+HDBKuXixyzcCzMKkNXPqH+64bPBrLYvhIKYntNqWNz+nPaqr+DUURLUbK3xlujlXyPysC7w6kHeYQivkpwP+p8BReGZMK1WhSlcr2EogSxwur10rnlxWo7WEuO+QcOoQIDAQAB";
    }

    public static SignUtils getInstance() {
        if (INSTANCE == null) {
            synchronized (SignUtils.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SignUtils();
                }
            }
        }
        return INSTANCE;
    }

    private SignUtils() {
        init();
        try {
            this.iWanosAudioConfig = WanosAudioManager.getWanosAudioInstance(Utils.getApp()).getWanosAudioConfig();
            this.PUBLIC_KEY = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(PUBLIC_KEY_STR)));
        } catch (Throwable th) {
            this.PUBLIC_KEY = null;
            Log.e(TAG, "RSA加密组件初始化失败==>" + th.getMessage());
        }
    }

    public String getNonceStr() {
        String string;
        synchronized (this) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.setLength(0);
            for (int i = 0; i < 5; i++) {
                stringBuffer.append(RANDOM_TEXT.charAt(this.RANDOM.nextInt(62)));
            }
            string = stringBuffer.toString();
        }
        return string;
    }

    public String getSign(Request request, String nonceStr) {
        String upperCase;
        synchronized (this) {
            TreeMap<String, Object> requestParams = getRequestParams(request);
            requestParams.put("nonceStr", nonceStr);
            Set<Map.Entry<String, Object>> setEntrySet = requestParams.entrySet();
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.setLength(0);
            for (Map.Entry<String, Object> entry : setEntrySet) {
                stringBuffer.append(entry.getKey());
                stringBuffer.append("=");
                if (entry.getValue() instanceof String) {
                    stringBuffer.append(entry.getValue());
                } else if (entry.getValue() instanceof Double) {
                    double dDoubleValue = ((Double) entry.getValue()).doubleValue();
                    if (dDoubleValue == 0.0d) {
                        stringBuffer.append(0);
                    } else {
                        int i = (int) dDoubleValue;
                        if (i == dDoubleValue) {
                            stringBuffer.append(i);
                        } else {
                            NumberFormat numberFormat = NumberFormat.getInstance();
                            numberFormat.setGroupingUsed(false);
                            stringBuffer.append(numberFormat.format(dDoubleValue));
                        }
                    }
                } else if (entry.getValue() instanceof ArrayList) {
                    stringBuffer.append(GsonUtils.toJson((ArrayList) entry.getValue()));
                } else {
                    stringBuffer.append(entry.getValue());
                }
                stringBuffer.append("&");
            }
            stringBuffer.append("key=");
            stringBuffer.append(SING_KEY);
            LogUtils.d(TAG, "STRING_BUFFER==>" + stringBuffer.toString());
            upperCase = com.blankj.utilcode.util.EncryptUtils.encryptSHA256ToString(stringBuffer.toString()).toUpperCase();
        }
        return upperCase;
    }

    public String getDeviceToken() {
        synchronized (this) {
            DeviceTokenEntity cacheDeviceToken = getCacheDeviceToken();
            if (cacheDeviceToken != null && !cacheDeviceToken.isExpire()) {
                return cacheDeviceToken.getDeviceToken();
            }
            try {
                Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                cipher.init(1, this.PUBLIC_KEY);
                DeviceTokenResponse deviceTokenResponseInitDeviceToken = WanOSRetrofitUtil.initDeviceToken(Base64.getEncoder().encodeToString(cipher.doFinal(initDeviceInfo().getBytes(StandardCharsets.UTF_8))));
                if (deviceTokenResponseInitDeviceToken.getCode() != 0) {
                    Log.e(TAG, "获取DeviceToken失败==>" + deviceTokenResponseInitDeviceToken.getMsg());
                    return "";
                }
                DeviceTokenEntity data = deviceTokenResponseInitDeviceToken.getData();
                Log.d(TAG, "getDeviceToken: 通过网络读取Token信息");
                SignUtilsEntity.getInstance().setDeviceTokenEntity(data);
                SPUtils.getInstance(SignUtilsEntity.SP_NAME).put(SignUtilsEntity.KEY_DEVICE_TOKEN, GsonUtils.toJson(data));
                return data.getDeviceToken();
            } catch (Exception e) {
                Log.e(TAG, "getDeviceToken: " + e);
                return "";
            }
        }
    }

    private DeviceTokenEntity getCacheDeviceToken() {
        DeviceTokenEntity deviceTokenEntity = SignUtilsEntity.getInstance().getDeviceTokenEntity();
        if (deviceTokenEntity != null) {
            return deviceTokenEntity;
        }
        try {
            String string = SPUtils.getInstance(SignUtilsEntity.SP_NAME).getString(SignUtilsEntity.KEY_DEVICE_TOKEN);
            if (StringUtils.isEmpty(string)) {
                Log.d(TAG, "getCacheDeviceToken: 未在SP缓存Token信息");
                return null;
            }
            DeviceTokenEntity deviceTokenEntity2 = (DeviceTokenEntity) GsonUtils.fromJson(string, DeviceTokenEntity.class);
            SignUtilsEntity.getInstance().setDeviceTokenEntity(deviceTokenEntity2);
            Log.d(TAG, "getCacheDeviceToken: 通过SP缓存读取Token信息");
            return deviceTokenEntity2;
        } catch (Exception unused) {
            return null;
        }
    }

    private String initDeviceInfo() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        String deviceId = CommonUtils.getDeviceId(Utils.getApp());
        if (deviceId.length() > 20) {
            deviceId = deviceId.substring(0, 20);
        }
        jSONObject.put("devId", deviceId);
        jSONObject.put("chan", CommonUtils.getAppChannelId());
        String str = CarConstants.releaseBrandId[CarConstants.buildIndex];
        String str2 = CarConstants.releaseModelId[CarConstants.buildIndex];
        String str3 = CarConstants.releaseCompanyId[CarConstants.buildIndex];
        int i = CarConstants.releaseAppId[CarConstants.buildIndex];
        jSONObject.put("productId", str3 + "_" + str + "_" + str2);
        jSONObject.put("appId", i);
        jSONObject.put("ver", BuildConfig.VERSION_NAME);
        return jSONObject.toString();
    }

    private TreeMap<String, Object> getRequestParams(Request request) {
        if (request == null) {
            Log.e(TAG, "签名生成失败，请求体为空");
            return new TreeMap<>();
        }
        if ("GET".equalsIgnoreCase(request.method())) {
            HttpUrl httpUrlUrl = request.url();
            Set<String> setQueryParameterNames = httpUrlUrl.queryParameterNames();
            if (setQueryParameterNames.isEmpty()) {
                return new TreeMap<>();
            }
            TreeMap<String, Object> treeMap = new TreeMap<>();
            for (String str : setQueryParameterNames) {
                String strQueryParameter = httpUrlUrl.queryParameter(str);
                if (!StringUtils.isEmpty(strQueryParameter)) {
                    treeMap.put(str, strQueryParameter);
                }
            }
            return treeMap;
        }
        if (HttpPost.METHOD_NAME.equalsIgnoreCase(request.method())) {
            RequestBody requestBodyBody = request.body();
            if (requestBodyBody == null) {
                Log.e(TAG, "签名生成失败，RequestBody异常");
                return new TreeMap<>();
            }
            Buffer buffer = new Buffer();
            try {
                requestBodyBody.writeTo(buffer);
                if (buffer.size() <= 0) {
                    return new TreeMap<>();
                }
                String utf8 = buffer.readUtf8();
                if (StringUtils.isEmpty(utf8)) {
                    return new TreeMap<>();
                }
                HashMap map = (HashMap) GsonUtils.fromJson(utf8, new TypeToken<HashMap<String, Object>>() { // from class: com.wanos.media.util.SignUtils.1
                }.getType());
                TreeMap<String, Object> treeMap2 = new TreeMap<>();
                for (Map.Entry entry : map.entrySet()) {
                    Object value = entry.getValue();
                    if (value instanceof ArrayList) {
                        ArrayList arrayList = (ArrayList) value;
                        if (!arrayList.isEmpty()) {
                            if (arrayList.get(0) instanceof Double) {
                                int[] iArr = new int[arrayList.size()];
                                for (int i = 0; i < arrayList.size(); i++) {
                                    iArr[i] = ((Double) arrayList.get(i)).intValue();
                                }
                                treeMap2.put((String) entry.getKey(), new Gson().toJson(iArr));
                            }
                        }
                        treeMap2.put((String) entry.getKey(), value);
                    } else {
                        treeMap2.put((String) entry.getKey(), value);
                    }
                }
                return treeMap2;
            } catch (IOException e) {
                Log.e(TAG, "签名生成失败，" + e.getMessage());
                return new TreeMap<>();
            }
        }
        return new TreeMap<>();
    }

    public String getEncryptedDeviceId() {
        try {
            String deviceId = CommonUtils.getDeviceId(Utils.getApp());
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(1, this.PUBLIC_KEY);
            return Base64.getEncoder().encodeToString(cipher.doFinal(deviceId.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String getEncryptedDecodeVersion() {
        try {
            String deviceWanosDecoderVersion = this.iWanosAudioConfig.getDeviceWanosDecoderVersion(Utils.getApp());
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("v", deviceWanosDecoderVersion);
            LogUtils.d(TAG, "getEncryptedDecodeVersion: " + jSONObject);
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(1, this.PUBLIC_KEY);
            return Base64.getEncoder().encodeToString(cipher.doFinal(jSONObject.toString().getBytes(StandardCharsets.UTF_8)));
        } catch (Throwable th) {
            LogUtils.e(TAG, "getEncryptedDecodeVersion: " + th.getMessage());
            return "";
        }
    }
}
