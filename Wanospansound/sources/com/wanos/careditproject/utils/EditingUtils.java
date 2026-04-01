package com.wanos.careditproject.utils;

import android.content.Context;
import android.os.Environment;
import android.util.ArrayMap;
import androidx.core.content.ContextCompat;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.google.android.material.timepicker.TimeModel;
import com.loopj.android.http.AsyncHttpClient;
import com.wanos.careditproject.R;
import com.wanos.careditproject.model.web.WebBallInfoModel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class EditingUtils {
    public static final String DEFAULT_AI_FILE_CACHE_PATH = "/ai";
    public static final String DEFAULT_LONG_FILE_CACHE_PATH = "/res";
    public static final long DEFAULT_LONG_FILE_MAX_CACHE_SIZE = 471859200;
    public static final String DEFAULT_SHORT_FILE_CACHE_PATH = "/pre";
    public static final long DEFAULT_SHORT_FILE_MAX_CACHE_SIZE = 52428800;
    public static final String DEFAULT_UNDO_REDO_FILE_CACHE_PATH = "/undo";
    public static int DPtrackMarginH = 5;
    public static int DPtrackViewH = 68;
    public static int DPtrackViewTop = 4;
    public static String cacheDirPath = null;
    public static final boolean connectServer = true;
    public static Context context = null;
    public static int distanceWidthMax = 10;
    public static int minLineCountOfMaxLine = 10;
    public static final float minViewScale = 0.05f;
    public static int pcmLineWidth = 2;
    public static final int phoneType = 0;
    public static String preViewUrl = "https://car-studio-dev.wanos.vip/web/#/";
    public static int sampleRateDefault = 48000;
    private static String[] trackColorStrList;
    public static EditProjectType editProjectType = EditProjectType.MUSIC;
    public static int totalTimeOfSecond = 600;
    public static int ballMaxValue = 1;
    public static int playerStep = 1024;
    public static int encodeStep = 1024;
    public static int pcmWaveStep = 3072;
    public static int objectPosStep = 4096;
    public static int posToShortValue = 1000;
    public static String Tag = "wanosEdit";
    public static int sizeOfShort = 2;
    public static int sizeOfFloat = 4;
    public static int screenWidth = 0;
    public static float redrawDrawValue = 1.3333334f;
    public static float redrawRefreshValue = 0.25f;
    public static int maxTrackNameLen = 10;
    public static int[] trackColorList = {R.color.edit_track_color_0, R.color.edit_track_color_1, R.color.edit_track_color_2, R.color.edit_track_color_3, R.color.edit_track_color_4};
    public static int[] trackDrawableList = {R.drawable.bg_color_0, R.drawable.bg_color_1, R.drawable.bg_color_2, R.drawable.bg_color_3, R.drawable.bg_color_4};
    public static String trackColorGray = "9DA1A7";
    public static int trackDrawableNormal = R.drawable.bg_color_normal;
    public static int trackDrawableGray = R.drawable.bg_color_gray;
    public static String[] playerShowTypeList = {"默认视图", "正视图", "左视图", "右视图", "俯视图", "后视图"};
    private static boolean isLoaded = false;
    private static int alphaStep = 10;
    private static Map<String, String> usbPath2LocalPathMap = new ArrayMap();

    public enum EAUDIOTOOL {
        UP,
        DOWN,
        RENAME,
        COPY,
        DEL
    }

    public static boolean containPos(long j, long j2, long j3, int i) {
        if (i == 0) {
            return j <= j3 && j2 > j3;
        }
        if (i == 1) {
            return j < j3 && j2 >= j3;
        }
        if (j == j3) {
            return true;
        }
        return j <= j3 && j2 > j3;
    }

    public static boolean containPosV2(long j, long j2, long j3) {
        return j <= j3 && j2 >= j3;
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

    public static void loadEditLibrariesOnce() {
        if (isLoaded) {
            return;
        }
        System.loadLibrary("wanosstereodisplay");
        System.loadLibrary("aac_enc");
        System.loadLibrary("carEditNative");
        isLoaded = true;
    }

    public enum EditProjectType {
        MUSIC(2, 20, 300),
        AUDIOBOOK(1, 10, AsyncHttpClient.DEFAULT_RETRY_SLEEP_TIME_MILLIS);

        public int channelCount;
        public int totalTimeOfSecond;
        public int value;

        EditProjectType(int i, int i2, int i3) {
            this.value = i;
            this.channelCount = i2;
            this.totalTimeOfSecond = i3;
        }

        public static EditProjectType getEditProjectType(int i) {
            for (EditProjectType editProjectType : values()) {
                if (editProjectType.value == i) {
                    return editProjectType;
                }
            }
            return MUSIC;
        }

        public int getTotalTimeOfSecond() {
            return this.totalTimeOfSecond;
        }

        public int getMaxChannelNum() {
            return this.channelCount;
        }
    }

    public static void init(Context context2) {
        context = context2;
        trackColorStrList = new String[trackColorList.length];
        int i = 0;
        while (true) {
            int[] iArr = trackColorList;
            if (i >= iArr.length) {
                break;
            }
            trackColorStrList[i] = getColorAsHexRGBString(context, iArr[i]);
            i++;
        }
        if (cacheDirPath == null) {
            cacheDirPath = Utils.getApp().getCacheDir().getAbsolutePath();
        }
    }

    public static String getCacheDir() {
        if (cacheDirPath == null) {
            cacheDirPath = Utils.getApp().getCacheDir().getAbsolutePath();
        }
        return cacheDirPath;
    }

    public static String getAiPath() {
        return getCacheDir() + DEFAULT_AI_FILE_CACHE_PATH;
    }

    public static String getUndoRedoPath() {
        return getCacheDir() + DEFAULT_UNDO_REDO_FILE_CACHE_PATH;
    }

    public static String getShortCacheDir() {
        return getCacheDir() + DEFAULT_SHORT_FILE_CACHE_PATH;
    }

    public static String getLongCacheDir() {
        return getCacheDir() + DEFAULT_LONG_FILE_CACHE_PATH;
    }

    public static Context getContext() {
        return context;
    }

    public static void destory() {
        context = null;
    }

    public static int dp2px(Context context2, float f) {
        return (int) ((f * context2.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static File getLocalProjectDir() {
        File file;
        if (context != null) {
            file = Utils.getApp().getCacheDir();
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
        File file = new File("/storage/emulated/0/Android/data/com.wanos.media/cache/projectFile/");
        log("parseMsg saveFile 2");
        File file2 = new File(file, str2);
        log("parseMsg saveFile 3");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            log("parseMsg saveFile 4");
            fileOutputStream.write(str.getBytes());
            fileOutputStream.close();
            log("File written successfully. targetDir = " + file2.getAbsolutePath());
            return file2.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int sampleNum2px(long j, Context context2) {
        return (int) ((j * ((long) dp2px(context2, distanceWidthMax * minLineCountOfMaxLine))) / ((long) DataHelpAudioTrack.getSampleRate()));
    }

    public static long px2SampleNum(int i, Context context2) {
        return (((long) i) * ((long) DataHelpAudioTrack.getSampleRate())) / ((long) dp2px(context2, distanceWidthMax * minLineCountOfMaxLine));
    }

    public static void showTips(String str) {
        ToastUtils.showShort(str);
    }

    public static String getTimeBySampleNum(long j) {
        int i = (int) (j / ((long) sampleRateDefault));
        return String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i / 60)) + ":" + String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i % 60));
    }

    public static int getMsBySampleNum(long j) {
        return (int) (j / ((long) (sampleRateDefault / 1000)));
    }

    public static long getSampleNumByMs(int i) {
        return i * (sampleRateDefault / 1000);
    }

    public static String getAssetsLocalPath() {
        return getCacheDir();
    }

    public static String getResLocalPath() {
        return getCacheDir() + DEFAULT_LONG_FILE_CACHE_PATH;
    }

    public static String getResJsonPath() {
        return getCacheDir();
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

    public static void log(String str) {
        LogUtils.d(Tag, str);
    }

    public static int getScreenWidth() {
        return screenWidth;
    }

    public static void setScreenWidth(int i) {
        screenWidth = i;
    }

    public static String stringForTime(long j) {
        long j2 = (j / 1000) + ((long) (((int) (j % 1000)) > 0 ? 1 : 0));
        return new Formatter().format("%02d:%02d", Long.valueOf((j2 / 60) % 60), Long.valueOf(j2 % 60)).toString();
    }

    public static String stringForSecond(int i) {
        return new Formatter().format("%02d:%02d", Long.valueOf((i / 60) % 60), Long.valueOf(i % 60)).toString();
    }

    public static String stringForSampleNum(long j, int i) {
        return stringForSecond((int) Math.ceil(j / ((double) i)));
    }

    public static String removeExtension(String str) {
        if (str == null) {
            return "";
        }
        int iLastIndexOf = str.lastIndexOf(46);
        return iLastIndexOf == -1 ? str : str.substring(0, iLastIndexOf);
    }

    public static String getFileExtension(String str) {
        if (str == null) {
            return null;
        }
        int iLastIndexOf = str.lastIndexOf(46);
        return iLastIndexOf == -1 ? "" : str.substring(iLastIndexOf + 1);
    }

    public static void mergeBallList(List<WebBallInfoModel> list, List<WebBallInfoModel> list2, List<Integer> list3) {
        boolean z;
        list3.clear();
        Iterator<WebBallInfoModel> it = list.iterator();
        while (it.hasNext()) {
            WebBallInfoModel next = it.next();
            Iterator<WebBallInfoModel> it2 = list2.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    z = false;
                    break;
                }
                WebBallInfoModel next2 = it2.next();
                if (next.trackIndex == next2.trackIndex) {
                    if (next.alpha < 100) {
                        next.alpha += alphaStep;
                    }
                    next.cloneModel(next2);
                    list3.add(Integer.valueOf(next.trackIndex));
                    z = true;
                }
            }
            if (!z) {
                next.alpha -= alphaStep;
                if (next.alpha == 0) {
                    it.remove();
                }
            }
        }
        for (WebBallInfoModel webBallInfoModel : list2) {
            if (!list3.contains(Integer.valueOf(webBallInfoModel.trackIndex))) {
                webBallInfoModel.alpha = alphaStep;
                list.add(webBallInfoModel);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x00ab A[Catch: IOException -> 0x00a7, TRY_LEAVE, TryCatch #2 {IOException -> 0x00a7, blocks: (B:45:0x00a3, B:49:0x00ab), top: B:55:0x00a3 }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00a3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean copyFile(java.lang.String r7, java.lang.String r8) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "Failed to close streams: "
            java.util.Map<java.lang.String, java.lang.String> r1 = com.wanos.careditproject.utils.EditingUtils.usbPath2LocalPathMap
            boolean r1 = r1.containsKey(r7)
            r2 = 1
            if (r1 == 0) goto Lc
            return r2
        Lc:
            r1 = 0
            r3 = 0
            java.io.BufferedInputStream r4 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> L5e java.io.IOException -> L61
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L5e java.io.IOException -> L61
            r5.<init>(r7)     // Catch: java.lang.Throwable -> L5e java.io.IOException -> L61
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L5e java.io.IOException -> L61
            java.io.BufferedOutputStream r5 = new java.io.BufferedOutputStream     // Catch: java.lang.Throwable -> L56 java.io.IOException -> L5a
            java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L56 java.io.IOException -> L5a
            r6.<init>(r8)     // Catch: java.lang.Throwable -> L56 java.io.IOException -> L5a
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L56 java.io.IOException -> L5a
            r3 = 1024(0x400, float:1.435E-42)
            byte[] r3 = new byte[r3]     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L54
        L26:
            int r6 = r4.read(r3)     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L54
            if (r6 <= 0) goto L30
            r5.write(r3, r1, r6)     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L54
            goto L26
        L30:
            java.util.Map<java.lang.String, java.lang.String> r3 = com.wanos.careditproject.utils.EditingUtils.usbPath2LocalPathMap     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L54
            r3.put(r7, r8)     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L54
            r4.close()     // Catch: java.io.IOException -> L3c
            r5.close()     // Catch: java.io.IOException -> L3c
            goto L51
        L3c:
            r7 = move-exception
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>(r0)
            java.lang.String r7 = r7.getMessage()
            java.lang.StringBuilder r7 = r8.append(r7)
            java.lang.String r7 = r7.toString()
            log(r7)
        L51:
            return r2
        L52:
            r7 = move-exception
            goto L58
        L54:
            r7 = move-exception
            goto L5c
        L56:
            r7 = move-exception
            r5 = r3
        L58:
            r3 = r4
            goto La1
        L5a:
            r7 = move-exception
            r5 = r3
        L5c:
            r3 = r4
            goto L63
        L5e:
            r7 = move-exception
            r5 = r3
            goto La1
        L61:
            r7 = move-exception
            r5 = r3
        L63:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La0
            r8.<init>()     // Catch: java.lang.Throwable -> La0
            java.lang.String r2 = "Failed to copy file: "
            java.lang.StringBuilder r8 = r8.append(r2)     // Catch: java.lang.Throwable -> La0
            java.lang.String r7 = r7.getMessage()     // Catch: java.lang.Throwable -> La0
            java.lang.StringBuilder r7 = r8.append(r7)     // Catch: java.lang.Throwable -> La0
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> La0
            log(r7)     // Catch: java.lang.Throwable -> La0
            if (r3 == 0) goto L85
            r3.close()     // Catch: java.io.IOException -> L83
            goto L85
        L83:
            r7 = move-exception
            goto L8b
        L85:
            if (r5 == 0) goto L9f
            r5.close()     // Catch: java.io.IOException -> L83
            goto L9f
        L8b:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>(r0)
            java.lang.String r7 = r7.getMessage()
            java.lang.StringBuilder r7 = r8.append(r7)
            java.lang.String r7 = r7.toString()
            log(r7)
        L9f:
            return r1
        La0:
            r7 = move-exception
        La1:
            if (r3 == 0) goto La9
            r3.close()     // Catch: java.io.IOException -> La7
            goto La9
        La7:
            r8 = move-exception
            goto Laf
        La9:
            if (r5 == 0) goto Lc3
            r5.close()     // Catch: java.io.IOException -> La7
            goto Lc3
        Laf:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r0)
            java.lang.String r8 = r8.getMessage()
            java.lang.StringBuilder r8 = r1.append(r8)
            java.lang.String r8 = r8.toString()
            log(r8)
        Lc3:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wanos.careditproject.utils.EditingUtils.copyFile(java.lang.String, java.lang.String):boolean");
    }

    public static long getMaxSampleNum() {
        return getTotalTimeOfSecond() * sampleRateDefault;
    }

    public static int getTotalTimeOfSecond() {
        return editProjectType.getTotalTimeOfSecond();
    }

    public static int getMaxChannelNum() {
        return editProjectType.getMaxChannelNum();
    }

    public static int getTrackCount() {
        return editProjectType.getMaxChannelNum();
    }

    public static void setEditProjectType(EditProjectType editProjectType2) {
        editProjectType = editProjectType2;
    }

    public static String[] getTrackColorStringList() {
        return trackColorStrList;
    }

    private static String getColorAsHexRGBString(Context context2, int i) {
        int color = ContextCompat.getColor(context2, i);
        return String.format("#%02X%02X%02X", Integer.valueOf((color >> 16) & 255), Integer.valueOf((color >> 8) & 255), Integer.valueOf(color & 255));
    }

    public static long formatSampleNum(long j) {
        return Math.round(j / ((double) playerStep)) * ((long) playerStep);
    }

    public static long formatSampleNumUp(long j) {
        return formatSampleNumUpIn(j, playerStep);
    }

    public static long formatSampleNumDown(long j) {
        int i = playerStep;
        return j % ((long) i) > 0 ? (j / ((long) i)) * ((long) i) : j;
    }

    public static long formatSampleNumUp4096(long j) {
        return formatSampleNumUpIn(j, objectPosStep);
    }

    public static long formatSampleNumUpIn(long j, int i) {
        long j2 = i;
        return j % j2 > 0 ? ((j / j2) + 1) * j2 : j;
    }

    public static String formatMilliseconds(long j) {
        int i = (int) (j / 1000);
        return String.format("%02d:%02d.%03d", Integer.valueOf((i / 60) % 60), Integer.valueOf(i % 60), Integer.valueOf((int) (j % 1000)));
    }

    public static String removeParamOfUrl(String str) {
        String[] strArrSplit = str.split("\\?");
        return strArrSplit.length > 1 ? strArrSplit[0] : str;
    }
}
