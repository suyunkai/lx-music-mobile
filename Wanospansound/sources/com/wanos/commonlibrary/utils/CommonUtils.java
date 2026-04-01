package com.wanos.commonlibrary.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import com.blankj.utilcode.util.StringUtils;
import com.wanos.groove.utils.GrooveSdkAppGlobal;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Collections;
import java.util.Iterator;
import java.util.UUID;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes3.dex */
public class CommonUtils {
    public static final String SP_DEVICE_NAME = "device";
    public static final String TAG = "wanos:[CommonUtils]";
    private static String appChannel = "";
    private static boolean is371 = false;
    private static String mDeviceId;

    public static int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static String getDeviceId(Context context) {
        TelephonyManager telephonyManager;
        String deviceId;
        if (!StringUtils.isEmpty(mDeviceId)) {
            return mDeviceId;
        }
        String deviceIdBySp = getDeviceIdBySp(context);
        if (!TextUtils.isEmpty(deviceIdBySp)) {
            return deviceIdBySp;
        }
        StringBuilder sb = new StringBuilder();
        try {
            telephonyManager = (TelephonyManager) context.getSystemService("phone");
            deviceId = telephonyManager.getDeviceId();
        } catch (Exception e) {
            e.printStackTrace();
            sb.append("id").append(getUUID(context));
        }
        if (!TextUtils.isEmpty(deviceId)) {
            sb.append("imei");
            sb.append(deviceId);
            return sb.toString();
        }
        String simSerialNumber = telephonyManager.getSimSerialNumber();
        if (!TextUtils.isEmpty(simSerialNumber)) {
            sb.append("sn");
            sb.append(simSerialNumber);
            return sb.toString();
        }
        String string = Settings.Secure.getString(context.getApplicationContext().getContentResolver(), "android_id");
        if (!TextUtils.isEmpty(simSerialNumber)) {
            sb.append("androidId");
            sb.append(string);
            return sb.toString();
        }
        String uuid = getUUID(context);
        if (!TextUtils.isEmpty(uuid)) {
            sb.append("uuid");
            sb.append(uuid);
            return sb.toString();
        }
        Log.i(TAG, "getDeviceId : " + sb.toString());
        setDeviceId(context, sb.toString());
        return sb.toString();
    }

    public static String getDeviceIdBySp(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("device", 0);
        return sharedPreferences != null ? sharedPreferences.getString("deviceId", "") : "";
    }

    public static void setDeviceId(Context context, String str) {
        if (StringUtils.isEmpty(str) || str.equals(mDeviceId)) {
            return;
        }
        mDeviceId = str;
        SharedPreferences sharedPreferences = context.getSharedPreferences("device", 0);
        if (sharedPreferences != null) {
            String string = sharedPreferences.getString("deviceId", "");
            if (TextUtils.isEmpty(string) || !string.equals(mDeviceId)) {
                sharedPreferences.edit().putString("deviceId", str).commit();
            }
        }
    }

    public static String getAndroidId(Context context) {
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        Log.i(TAG, "id : " + string);
        return string;
    }

    public static String getSN() {
        if (Build.VERSION.SDK_INT > 29) {
            return "unknown";
        }
        try {
            return Build.getSerial();
        } catch (Exception unused) {
            return "serial";
        }
    }

    public static String getUUID(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("device", 0);
        String string = sharedPreferences != null ? sharedPreferences.getString("uuid", "") : "";
        if (TextUtils.isEmpty(string)) {
            string = UUID.randomUUID().toString();
            sharedPreferences.edit().putString("uuid", string).commit();
        }
        Log.i(TAG, "getUUID : " + string);
        return string;
    }

    public static String md5(String str) {
        try {
            byte[] bArrDigest = MessageDigest.getInstance("MD5").digest(str.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder(bArrDigest.length * 2);
            for (byte b2 : bArrDigest) {
                int i = b2 & 255;
                if (i < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(i));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        }
    }

    public static String base64(String str) {
        return Base64.getEncoder().encodeToString(str.getBytes());
    }

    public static String getVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static long getVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).getLongVersionCode();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -1L;
        }
    }

    public static String getIPAddress() {
        try {
            Iterator it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
            while (it.hasNext()) {
                for (InetAddress inetAddress : Collections.list(((NetworkInterface) it.next()).getInetAddresses())) {
                    if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
            return "";
        } catch (Exception e) {
            Log.e("getIPAddress", e.toString());
            return "";
        }
    }

    public static int getPxByDp(int i, Context context) {
        return (int) ((i * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int getDpByPx(int i, Context context) {
        return (int) ((i / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int getPxBySp(int i, Context context) {
        return (int) ((i * context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public static int getStatusBarHeight(Context context) {
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static boolean isScreenOriatationPortrait(Context context) {
        return context.getResources().getConfiguration().orientation == 1;
    }

    public static String getPhoneFormat(String str) {
        return str.substring(0, 3).intern() + "****" + str.substring(7, 11).intern();
    }

    public static boolean isPwd(String str) {
        return str.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$");
    }

    public static boolean checkPhone(String str) {
        return Pattern.compile("^[1][3,4,5,6,7,8,9][0-9]{9}").matcher(str).matches();
    }

    public static void setAppChannel(String str) {
        appChannel = str;
    }

    public static String getAppChannelId() {
        return appChannel;
    }

    public static Point getScreenSize(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        Point point = new Point();
        windowManager.getDefaultDisplay().getSize(point);
        return point;
    }

    public static boolean isChannelNot245() {
        return !getAppChannelId().equals("JILIE245-1");
    }

    public static Application getApplication() {
        Application application;
        try {
            Method declaredMethod = Class.forName(GrooveSdkAppGlobal.CLASS_FOR_NAME).getDeclaredMethod(GrooveSdkAppGlobal.CURRENT_APPLICATION, new Class[0]);
            declaredMethod.setAccessible(true);
            application = (Application) declaredMethod.invoke(null, new Object[0]);
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException unused) {
            application = null;
        }
        if (application != null) {
            return application;
        }
        try {
            Method declaredMethod2 = Class.forName(GrooveSdkAppGlobal.CLASS_FOR_NAME).getDeclaredMethod(GrooveSdkAppGlobal.GET_INITIAL_APPLICATION, new Class[0]);
            declaredMethod2.setAccessible(true);
            return (Application) declaredMethod2.invoke(null, new Object[0]);
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException unused2) {
            return application;
        }
    }

    public static boolean isIs371() {
        return is371;
    }

    public static void setIs371(boolean z) {
        is371 = z;
    }
}
