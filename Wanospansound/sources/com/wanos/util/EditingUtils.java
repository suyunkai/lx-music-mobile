package com.wanos.util;

import android.content.Context;
import android.graphics.PointF;
import android.os.Environment;
import android.util.Log;
import com.blankj.utilcode.util.ToastUtils;
import com.google.android.material.timepicker.TimeModel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/* JADX INFO: loaded from: classes3.dex */
public class EditingUtils {
    public static int DPtrackMarginH = 5;
    public static int DPtrackViewH = 68;
    public static int DPtrackViewTop = 4;
    public static String Tag = "wanosEdit";
    public static int ballMaxValue = 1;
    public static String cacheDirPath = null;
    public static final boolean connectServer = true;
    public static Context context = null;
    public static int distanceWidthMax = 10;
    public static int maxTrackNum = 15;
    public static int minLineCountOfMaxLine = 10;
    public static final float minViewScale = 0.05f;
    public static int pcmLineWidth = 2;
    public static int pcmWaveStep = 1024;
    public static final int phoneType = 0;
    public static int playerStep = 1024;
    public static String preViewUrl = "https://car-studio-air.wanos.vip/web/#/";
    public static float redrawDrawValue = 1.3333334f;
    public static float redrawRefreshValue = 0.25f;
    public static int sampleRateDefault = 48000;
    public static int screenWidth = 0;
    public static int sizeOfShort = 2;
    public static int totalTimeOfSecond = 600;
    public static String[] trackColorList = {"455DDC", "48BCFE", "39AFB7", "66F094", "D39436", "D24137"};
    public static String trackColorGray = "9DA1A7";
    private static int angleLR = 30;

    public enum EAUDIOTOOL {
        UP,
        DOWN,
        RENAME,
        COPY,
        DEL
    }

    public static boolean containPos(long j, long j2, long j3, int i) {
        return i == 0 ? j <= j3 && j2 > j3 : i == 1 ? j < j3 && j2 >= j3 : j <= j3 && j2 > j3;
    }

    public static boolean isConnectServer() {
        return true;
    }

    public enum FADETYPE {
        FADETYPE0("linear"),
        FADETYPE1("sCurve"),
        FADETYPE2("logarithmic"),
        FADETYPE3("exponential");

        private String type;

        FADETYPE(String str) {
            this.type = str;
        }

        public String getType() {
            return this.type;
        }
    }

    public static void init(Context context2) {
        context = context2;
        if (cacheDirPath == null) {
            cacheDirPath = context2.getExternalCacheDir().getAbsolutePath();
        }
    }

    public static void destory() {
        context = null;
    }

    public static int dp2px(Context context2, float f) {
        return (int) ((f * context2.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static File getLocalProjectDir() {
        File file;
        Context context2 = context;
        if (context2 != null) {
            file = context2.getExternalCacheDir();
        } else {
            file = new File(Environment.getExternalStorageDirectory(), "Download");
        }
        log("parseMsg saveFile 0 :" + file.getPath());
        File file2 = new File(file, "projectFile");
        if (file2.exists() || file2.mkdirs()) {
            return file2;
        }
        log("parseMsg saveFile 1");
        return null;
    }

    public static String readFile(String str) {
        FileInputStream fileInputStream;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        File file = new File(getLocalProjectDir(), str);
        StringBuilder sb = new StringBuilder();
        try {
            fileInputStream = new FileInputStream(file);
            inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
            sb.append(line);
            return sb.toString();
        }
        bufferedReader.close();
        inputStreamReader.close();
        fileInputStream.close();
        return sb.toString();
    }

    public static String saveFile(String str, String str2) {
        File localProjectDir = getLocalProjectDir();
        log("parseMsg saveFile 2");
        File file = new File(localProjectDir, str2);
        log("parseMsg saveFile 3");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            log("parseMsg saveFile 4");
            fileOutputStream.write(str.getBytes());
            fileOutputStream.close();
            log("File written successfully.");
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void showTips(String str) {
        ToastUtils.showShort(str);
    }

    public static String getTimeBySampleNum(long j) {
        int i = (int) (j / ((long) sampleRateDefault));
        return String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i / 60)) + ":" + String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i % 60));
    }

    public static String getResLocalPath() {
        return cacheDirPath != null ? cacheDirPath + com.wanos.careditproject.utils.EditingUtils.DEFAULT_LONG_FILE_CACHE_PATH : "";
    }

    public static String getPcmLocalPath() {
        return cacheDirPath != null ? cacheDirPath + "/pcm" : "";
    }

    public static void initCacheDir() {
        File file = new File(getResLocalPath());
        if (!file.exists()) {
            file.mkdir();
        }
        File file2 = new File(getPcmLocalPath());
        if (file2.exists()) {
            return;
        }
        file2.mkdir();
    }

    public static PointF getLeftOfRightPosV1(PointF pointF, PointF pointF2, boolean z, PointF pointF3, PointF pointF4) {
        PointF pointF5 = new PointF(pointF2.x - pointF.x, pointF2.y - pointF.y);
        double dSqrt = (float) Math.sqrt(((pointF2.x - pointF.x) * (pointF2.x - pointF.x)) + ((pointF2.y - pointF.y) * (pointF2.y - pointF.y)));
        double dAtan2 = (float) (Math.atan2(pointF5.y, pointF5.x) + Math.toRadians(z ? 30.0d : -30.0d));
        float fCos = (float) (((double) pointF.x) + (Math.cos(dAtan2) * dSqrt));
        float fSin = (float) (((double) pointF.y) + (dSqrt * Math.sin(dAtan2)));
        if (fCos < pointF3.x) {
            fCos = pointF3.x;
        }
        if (fSin < pointF3.y) {
            fSin = pointF3.y;
        }
        if (fCos > pointF4.x) {
            fCos = pointF4.x;
        }
        if (fSin > pointF4.y) {
            fSin = pointF4.y;
        }
        return new PointF(fCos, fSin);
    }

    public static PointF getLeftOfRightPos(PointF pointF, PointF pointF2, boolean z, PointF pointF3, PointF pointF4) {
        float fAbs;
        PointF pointF5 = new PointF();
        PointF pointF6 = new PointF(pointF2.x - pointF.x, pointF2.y - pointF.y);
        float fSqrt = (float) Math.sqrt(((pointF2.x - pointF.x) * (pointF2.x - pointF.x)) + ((pointF2.y - pointF.y) * (pointF2.y - pointF.y)));
        float fAtan2 = (float) (((double) ((float) Math.atan2(pointF6.y, pointF6.x))) + Math.toRadians(z ? angleLR : -angleLR));
        double d2 = fSqrt;
        double d3 = fAtan2;
        float fCos = (float) (((double) pointF.x) + (Math.cos(d3) * d2));
        float fSin = (float) (((double) pointF.y) + (d2 * Math.sin(d3)));
        float fAbs2 = Math.abs(pointF4.x - pointF3.x);
        float fAbs3 = Math.abs(pointF4.y - pointF3.y);
        if (Math.abs(pointF2.x - pointF.x) >= Math.abs(pointF2.y - pointF.y)) {
            fAbs = Math.abs(pointF2.x - pointF.x) / (fAbs2 / 2.0f);
        } else {
            fAbs = Math.abs(pointF2.y - pointF.y) / (fAbs3 / 2.0f);
        }
        PointF pointF7 = new PointF();
        PointF pointF8 = new PointF();
        PointF pointF9 = new PointF((pointF4.x + pointF3.x) / 2.0f, (pointF4.y + pointF3.y) / 2.0f);
        if (Math.abs(fCos - pointF.x) >= Math.abs(fSin - pointF.y)) {
            if (fCos - pointF.x > 0.0f) {
                pointF7.x = pointF4.x - pointF9.x;
            } else {
                pointF7.x = pointF3.x - pointF9.x;
            }
            float fAbs4 = Math.abs(fCos - pointF.x) / Math.abs(pointF4.x - pointF.x);
            if (fAbs4 == 0.0f) {
                pointF7.y = 0.0f;
            } else {
                pointF7.y = (fSin - pointF.y) / fAbs4;
            }
            pointF8.x = pointF7.x * fAbs;
            pointF8.y = pointF7.y * fAbs;
        } else {
            if (fSin - pointF.y > 0.0f) {
                pointF7.y = pointF4.y - pointF9.y;
            } else {
                pointF7.y = pointF3.y - pointF9.y;
            }
            pointF7.x = (fCos - pointF.x) / (Math.abs(fSin - pointF.y) / Math.abs(pointF4.y - pointF.y));
            pointF8.x = pointF7.x * fAbs;
            pointF8.y = pointF7.y * fAbs;
        }
        float f = 1.0f - fAbs;
        pointF5.x = ((fCos - pointF.x) * f) + (pointF8.x * fAbs) + pointF.x;
        pointF5.y = ((fSin - pointF.y) * f) + (pointF8.y * fAbs) + pointF.y;
        if (pointF5.x < pointF3.x) {
            pointF5.x = pointF3.x;
        }
        if (pointF5.y < pointF3.y) {
            pointF5.y = pointF3.y;
        }
        if (pointF5.x > pointF4.x) {
            pointF5.x = pointF4.x;
        }
        if (pointF5.y > pointF4.y) {
            pointF5.y = pointF4.y;
        }
        return pointF5;
    }

    public static void log(String str) {
        Log.d(Tag, str);
    }

    public static int getScreenWidth() {
        return screenWidth;
    }

    public static void setScreenWidth(int i) {
        screenWidth = i;
    }
}
