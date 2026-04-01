package com.wanos.media.base;

import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.extractor.ts.TsExtractor;
import com.wanos.commonlibrary.mediaCenter.AudioConfig;
import com.wanos.commonlibrary.utils.CommonUtils;
import com.wanos.mediacenter.MediaCenterManager;
import com.wanos.play.AudioLayout;
import com.wanos.util.WanosPlayChannelLayoutUtil;
import com.wanos.zero.IVolumeMateData;
import com.wanos.zero.S73_Volume;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

/* JADX INFO: loaded from: classes3.dex */
public class CarConstants {
    public static final int TABLE_ID_AUDIO_BOOK = 2;
    public static final int TABLE_ID_CREATION_CENTER = 5;
    public static final int TABLE_ID_JUYI_HALL = 0;
    public static final int TABLE_ID_MUSIC_HALL = 1;
    public static final int TABLE_ID_VIDEO = 3;
    public static final int TABLE_ID_ZERO = 4;
    private static final String TAG = "Wanos[CarConstants]";
    public static int buildIndex;
    public static Integer[][] carTypeAdvanceV;
    public static int[][] mTableState;
    public static boolean[] needAvailableByPing;
    public static boolean[] needTtsAudioFocus;
    public static boolean[] needWindowAddViewToast;
    public static String[] channel = {"JILIE743", "JILIE245-1", "JILIE371", "JILIDX11", "JILIE371-A1", "JILIE371-2", "JILIE371-A1-1", "JILIE743-A1", "LYKJYPZZMT0415"};
    public static String[] buildCons = {"WANOS_743", "WANOS_245", "WANOS_371", "WANOS_DX11", "WANOS_371_A1", "WANOS_371_2", "WANOS_371_A1_1", "WANOS_743_A1", "WANOS_Demo"};
    public static String[] testBrandId = {"6", "6", "6", "15", "15", "15", "15", "15", "17"};
    public static String[] testModelId = {"5", "7", "6", "15", "33", "34", "35", "36", "48"};
    public static String[] testCompanyId = {ExifInterface.GPS_MEASUREMENT_3D, ExifInterface.GPS_MEASUREMENT_3D, ExifInterface.GPS_MEASUREMENT_3D, ExifInterface.GPS_MEASUREMENT_3D, ExifInterface.GPS_MEASUREMENT_3D, ExifInterface.GPS_MEASUREMENT_3D, ExifInterface.GPS_MEASUREMENT_3D, ExifInterface.GPS_MEASUREMENT_3D, "11"};
    public static int[] testAppId = {4, 5, 6, 11, 25, 26, 27, 28, 40};
    public static String[] releaseBrandId = {"11", "6", "11", "11", "11", "11", "11", "11", "14"};
    public static String[] releaseModelId = {"12", "17", "14", "19", "30", "31", "32", "33", "44"};
    public static String[] releaseCompanyId = {ExifInterface.GPS_MEASUREMENT_3D, ExifInterface.GPS_MEASUREMENT_3D, ExifInterface.GPS_MEASUREMENT_3D, ExifInterface.GPS_MEASUREMENT_3D, ExifInterface.GPS_MEASUREMENT_3D, ExifInterface.GPS_MEASUREMENT_3D, ExifInterface.GPS_MEASUREMENT_3D, ExifInterface.GPS_MEASUREMENT_3D, "7"};
    public static int[] releaseAppId = {4, 11, 6, 13, 25, 26, 27, 28, 40};
    public static int[] densityWidth = {2560, 2560, 2560, 2560, 2560, 2560, 2560, 2560, 2560};
    public static int[] densityHeight = {1600, 1600, 1600, 1600, 1600, 1600, 1600, 1600, 1600};
    public static boolean[] enableAdvance = {true, true, true, true, true, true, true, true, true};
    public static boolean[] useHideIcon = {false, true, false, false, false, false, false, false, true};
    public static boolean[] needCheckAdvDefault = {false, true, false, false, false, false, false, false, true};
    public static boolean[] isVoiceIgnoreFocus = {false, true, false, false, false, false, false, false, true};
    public static boolean[] useNativeTTS = {false, true, false, false, false, false, false, false, true};
    public static boolean[] needAudioConfig = {false, true, false, false, false, false, false, false, true};
    public static boolean[] needActResumeStartPlay = {true, false, true, true, true, true, true, true, false};
    public static boolean[] needRecovery = {true, true, true, true, true, true, true, true, true};
    public static boolean[] needVoiceHint = {false, false, true, false, true, true, true, false, false};
    public static boolean[] showAudioFocusToast = {true, false, true, true, true, true, true, true, false};
    public static boolean[] showWidgetSize = {false, false, true, false, true, true, true, false, false};
    public static boolean[] aiMusic = {false, false, false, false, false, false, false, false, false};

    static /* synthetic */ boolean lambda$isShowCreate$1(int i) {
        return i == 5;
    }

    static /* synthetic */ boolean lambda$isShowJuYiHall$2(int i) {
        return i == 0;
    }

    static /* synthetic */ boolean lambda$isShowZero$0(int i) {
        return i == 4;
    }

    static {
        Integer numValueOf = Integer.valueOf(TsExtractor.TS_STREAM_TYPE_DTS_HD);
        carTypeAdvanceV = new Integer[][]{new Integer[]{numValueOf}, new Integer[]{132, 133}, new Integer[]{numValueOf}, new Integer[]{Integer.valueOf(TsExtractor.TS_STREAM_TYPE_AC3), Integer.valueOf(TsExtractor.TS_STREAM_TYPE_HDMV_DTS), 132, 133, Integer.valueOf(TsExtractor.TS_STREAM_TYPE_SPLICE_INFO), Integer.valueOf(TsExtractor.TS_STREAM_TYPE_E_AC3), numValueOf}, new Integer[]{numValueOf}, new Integer[]{numValueOf}, new Integer[]{numValueOf}, new Integer[]{numValueOf}, new Integer[]{132, 133}};
        needTtsAudioFocus = new boolean[]{false, false, true, false, true, true, true, false, false};
        needWindowAddViewToast = new boolean[]{false, false, true, false, true, true, true, false, false};
        mTableState = new int[][]{new int[]{0, 1, 2, 3, 4}, new int[]{0, 1, 2, 3, 4, 5}, new int[]{0, 1, 2}, new int[]{0, 1, 2, 3, 4}, new int[]{0, 1, 2}, new int[]{0, 1, 2}, new int[]{0, 1, 2}, new int[]{0, 1, 2, 3, 4}, new int[]{0, 1, 2, 3, 4, 5}};
        needAvailableByPing = new boolean[]{false, true, false, false, false, false, false, false, false};
        buildIndex = 0;
    }

    public static void init() {
        int i = 0;
        while (true) {
            String[] strArr = buildCons;
            if (i >= strArr.length) {
                break;
            }
            if ("WANOS_245".equals(strArr[i])) {
                buildIndex = i;
                break;
            }
            i++;
        }
        CommonUtils.setIs371(false);
        CommonUtils.setAppChannel(channel[buildIndex]);
        MediaCenterManager.is245 = true;
        MediaCenterManager.isEnableAdv = enableAdvance[buildIndex];
        MediaCenterManager.isUseNativeTTS = useNativeTTS[buildIndex];
        MediaCenterManager.isUseNewHide = useHideIcon[buildIndex];
        MediaCenterManager.advCCValue = carTypeAdvanceV[buildIndex];
        MediaCenterManager.isNeedTtsAudioFocus = needTtsAudioFocus[buildIndex];
        MediaCenterManager.isCheckDefault = needCheckAdvDefault[buildIndex];
        MediaCenterManager.isVoiceIgnoreFocus = isVoiceIgnoreFocus[buildIndex];
        AudioConfig.isNeedConfigAudioPar = needAudioConfig[buildIndex];
        AudioConfig.isNeedRecovery = needRecovery[buildIndex];
        AudioConfig.isShowAudioFocusToast = showAudioFocusToast[buildIndex];
        AudioConfig.isNeedTTSHint = needVoiceHint[buildIndex];
        AudioConfig.isWindowAddViewToast = needWindowAddViewToast[buildIndex];
        AudioConfig.isNeedAiMusic = aiMusic[buildIndex];
        StringBuilder sb = new StringBuilder("\n---------------------------\n");
        if (isShowZero() || isShowCreate()) {
            AudioLayout[] audioLayoutArr = {AudioLayout.WANOS_CH_712, AudioLayout.WANOS_CH_710, AudioLayout.WANOS_CH_712, AudioLayout.WANOS_CH_712, AudioLayout.WANOS_CH_712, AudioLayout.WANOS_CH_712, AudioLayout.WANOS_CH_712, AudioLayout.WANOS_CH_712, AudioLayout.WANOS_CH_714};
            IVolumeMateData[] iVolumeMateDataArr = {new S73_Volume(), new S73_Volume(), new S73_Volume(), new S73_Volume(), new S73_Volume(), new S73_Volume(), new S73_Volume(), new S73_Volume(), new S73_Volume()};
            writeSpaceInfo(sb, audioLayoutArr);
            int i2 = buildIndex;
            WanosPlayChannelLayoutUtil.init(audioLayoutArr[i2], iVolumeMateDataArr[i2]);
        }
        writeConfigInfo(sb);
        Log.i(TAG, "App配置信息: " + ((Object) sb));
    }

    public static boolean isShowZero() {
        return IntStream.of(mTableState[buildIndex]).anyMatch(new IntPredicate() { // from class: com.wanos.media.base.CarConstants$$ExternalSyntheticLambda2
            @Override // java.util.function.IntPredicate
            public final boolean test(int i) {
                return CarConstants.lambda$isShowZero$0(i);
            }
        });
    }

    public static boolean isShowCreate() {
        return IntStream.of(mTableState[buildIndex]).anyMatch(new IntPredicate() { // from class: com.wanos.media.base.CarConstants$$ExternalSyntheticLambda0
            @Override // java.util.function.IntPredicate
            public final boolean test(int i) {
                return CarConstants.lambda$isShowCreate$1(i);
            }
        });
    }

    public static boolean isShowJuYiHall() {
        return IntStream.of(mTableState[buildIndex]).anyMatch(new IntPredicate() { // from class: com.wanos.media.base.CarConstants$$ExternalSyntheticLambda1
            @Override // java.util.function.IntPredicate
            public final boolean test(int i) {
                return CarConstants.lambda$isShowJuYiHall$2(i);
            }
        });
    }

    private static void writeConfigInfo(StringBuilder sb) {
        sb.append("Flavor:");
        sb.append("WANOS_245");
        sb.append("\n");
        sb.append("BuildType:");
        sb.append("release");
        sb.append("\n---------------------------\n");
        sb.append("渠道名称:");
        sb.append(channel[buildIndex]);
        sb.append("\n");
        sb.append("BrandId:");
        sb.append(releaseBrandId[buildIndex]);
        sb.append("\n");
        sb.append("ModelId:");
        sb.append(releaseModelId[buildIndex]);
        sb.append("\n");
        sb.append("CompanyId:");
        sb.append(releaseCompanyId[buildIndex]);
        sb.append("\n");
        sb.append("AppId:");
        sb.append(releaseAppId[buildIndex]);
        sb.append("\n");
    }

    private static void writeSpaceInfo(StringBuilder sb, AudioLayout[] audioLayouts) {
        sb.append("空间布局:");
        sb.append(audioLayouts[buildIndex].getName());
        sb.append("\n");
        sb.append("声道数量:");
        sb.append(audioLayouts[buildIndex].getChannelNum());
        sb.append("\n");
        sb.append("---------------------------\n");
    }
}
