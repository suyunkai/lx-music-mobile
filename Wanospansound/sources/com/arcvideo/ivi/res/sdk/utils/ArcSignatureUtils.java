package com.arcvideo.ivi.res.sdk.utils;

import android.util.Log;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import com.arcvideo.ivi.res.sdk.data.ActivateBean;
import com.arcvideo.ivi.res.sdk.data.ActivateBodyBean;
import com.arcvideo.ivi.res.sdk.data.CreateTokenBodyBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes.dex */
public class ArcSignatureUtils {
    public static String buildNewVersionSignature(HashMap<String, String> map, ActivateBodyBean activateBodyBean) {
        String str = String.format("AK=%s&SK=%s&VER=%s&ACT=%s&TS=%s", "5wo1db5j81ae", "36uy8wsxkyugn6bfybbf", map.get("X-Plt-Ver"), map.get("X-Plt-Action"), map.get("X-Plt-Ts"));
        Gson gsonCreate = new GsonBuilder().disableHtmlEscaping().create();
        String str2 = str + "&BODY=" + gsonCreate.toJson(activateBodyBean);
        Log.d("ArcResSdkImpl", "body = " + gsonCreate.toJson(activateBodyBean));
        try {
            return initData("5wo1db5j81ae", str2, "36uy8wsxkyugn6bfybbf");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String byteToHex(byte[] bArr) {
        StringBuilder sb = new StringBuilder("");
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & 255);
            if (hexString.length() == 1) {
                hexString = "0" + hexString;
            }
            sb.append(hexString);
        }
        return sb.toString().trim();
    }

    public static String generateSignature(HashMap<String, Object> map, String str) throws Exception {
        ArrayList<String> arrayList = new ArrayList(map.keySet());
        Collections.sort(arrayList, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder(str);
        for (String str2 : arrayList) {
            if (map.get(str2) != null && !str2.equals("signature")) {
                sb.append(str2);
                sb.append("=");
                sb.append(map.get(str2));
            }
        }
        Log.e("-------> sb.toString =", sb.toString());
        String strHmacSHA256Encrypt = hmacSHA256Encrypt(sb.toString(), str);
        Log.e("----------> signature =", strHmacSHA256Encrypt);
        return strHmacSHA256Encrypt;
    }

    public static String hmacSHA256Encrypt(String str, String str2) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        byte[] bytes = str2.getBytes("UTF-8");
        byte[] bytes2 = str.getBytes("UTF-8");
        mac.init(new SecretKeySpec(bytes, "HMACSHA256"));
        return byteToHex(mac.doFinal(bytes2));
    }

    public static String initData(String str, String str2, String str3) throws Exception {
        String strHmacSHA256Encrypt = hmacSHA256Encrypt(str2, str3);
        Log.e("----------> signature =", strHmacSHA256Encrypt);
        return strHmacSHA256Encrypt;
    }

    public static String buildVersionSignature(HashMap<String, String> map, String str, String str2, CreateTokenBodyBean createTokenBodyBean) {
        String str3 = String.format("AK=%s&SK=%s&VER=%s&ACT=%s&TS=%s", str2, str, map.get("X-Plt-Ver"), map.get("X-Plt-Action"), map.get("X-Plt-Ts"));
        Gson gsonCreate = new GsonBuilder().disableHtmlEscaping().create();
        String str4 = str3 + "&BODY=" + gsonCreate.toJson(createTokenBodyBean);
        Log.d("ArcResSdkImpl", "body = " + gsonCreate.toJson(createTokenBodyBean));
        try {
            return initData(str2, str4, str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String buildVersionSignature(HashMap<String, String> map, String str, String str2, ActivateBean activateBean) {
        String str3 = String.format("AK=%s&SK=%s&VER=%s&ACT=%s&TS=%s", str2, str, map.get("X-Plt-Ver"), map.get("X-Plt-Action"), map.get("X-Plt-Ts"));
        Gson gsonCreate = new GsonBuilder().disableHtmlEscaping().create();
        String str4 = str3 + "&BODY=" + gsonCreate.toJson(activateBean);
        Log.d("ArcResSdkImpl", "body = " + gsonCreate.toJson(activateBean));
        try {
            return initData(str2, str4, str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String buildVersionSignature(Map<String, String> map, String str, String str2) {
        try {
            return initData(str2, String.format("AK=%s&SK=%s&VER=%s&ACT=%s&TS=%s&cid=%s", str2, str, map.get("X-Plt-Ver"), map.get("X-Plt-Action"), map.get("X-Plt-Ts"), map.get(CmcdConfiguration.KEY_CONTENT_ID)), str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
