package com.wanos.careditproject.utils;

import android.graphics.PointF;
import android.os.Handler;
import android.os.Looper;
import android.util.ArrayMap;
import com.wanos.careditproject.event.UpdateEditUIEvent;
import com.wanos.careditproject.model.server.TrackItemModel;
import com.wanos.careditproject.model.web.WebBallInfoModel;
import com.wanos.careditproject.utils.codec.AudioPcmData;
import com.wanos.careditproject.utils.http.DownloadUtils;
import com.wanos.careditproject.utils.metronome.metronome;
import com.wanos.carstudio.audiotrackplayer.productionSysPlayer;
import com.wanos.commonlibrary.manager.GlobalAudioFocusManager;
import com.wanos.util.NativeMethod;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes3.dex */
public class PlayerUtils {
    protected static Map<String, RandomAccessFile> clipId2File;
    protected static PlayerListener playerListener;
    protected static Map<Integer, Map<String, Integer>> readCacheData;
    protected static List<String> readClipIdListWanos;
    protected static float[][] readPcmData;
    protected static int readPcmDataIndex;
    protected static EditPlayState playState = EditPlayState.STOP;
    protected static boolean playIsStop = true;
    protected static boolean playIsPause = true;
    protected static float previewColumn = 1.0f;
    protected static long preClickNum = 0;
    private static int progressNum = 0;
    public static String testFilePath = "";
    protected static Map<Long, AudioPcmData.PcmData> clipToPcmDataMap = new ArrayMap();
    protected static Map<Long, String> clipToLocalPathMap = new ArrayMap();
    private static final GlobalAudioFocusManager.IPlayer iPlayer = new GlobalAudioFocusManager.IPlayer() { // from class: com.wanos.careditproject.utils.PlayerUtils.1
        @Override // com.wanos.commonlibrary.manager.GlobalAudioFocusManager.IPlayer
        public void onAudioFocusLost() {
            PlayerUtils.stop(true);
        }
    };
    protected static boolean readStop = true;
    protected static int readNum = 0;
    protected static boolean playIsPreview = false;
    protected static int readCacheMax = 20;
    protected static int channelMax = 2;
    protected static long readCacheSampleNum = 0;
    protected static int readCacheIndex = 0;
    protected static int playCacheIndex = 0;
    protected static int playIndex = 0;
    protected static PointF posZero = new PointF(0.0f, 0.0f);
    protected static Map<Integer, PreBallPosInfo> allBallPosMap = new HashMap();
    protected static int curPlayThreadId = 0;
    private static int baseThreadId = 0;
    private static boolean playerIsAi = false;

    public enum EditPlayState {
        NONE,
        PLAYING,
        TOPAUSE,
        PAUSE,
        TOSTOP,
        STOP
    }

    public interface PlayerListener {
        void onProgress(int i);

        void onStop();

        void setWebBallInfo(List<WebBallInfoModel> list, boolean z);
    }

    public static boolean isPause() {
        return playState == EditPlayState.PAUSE || playState == EditPlayState.TOPAUSE;
    }

    public static boolean isStop() {
        return playState == EditPlayState.TOSTOP || playState == EditPlayState.STOP || playState == EditPlayState.NONE;
    }

    public static boolean isPlaying() {
        return playState == EditPlayState.PLAYING;
    }

    public static EditPlayState getPlayState() {
        return playState;
    }

    public static boolean isPlayIsStop() {
        return playIsStop;
    }

    public static void startReadThread(int i) {
        int maxChannelNum = EditingUtils.getMaxChannelNum();
        readPcmData = (float[][]) Array.newInstance((Class<?>) Float.TYPE, readCacheMax * maxChannelNum, EditingUtils.playerStep * 2);
        readCacheData = new ConcurrentHashMap();
        clipId2File = new ConcurrentHashMap();
        readClipIdListWanos = new ArrayList();
        byte[] bArr = new byte[EditingUtils.playerStep * channelMax * EditingUtils.sizeOfShort];
        float[] fArr = new float[EditingUtils.playerStep * channelMax];
        ArrayList arrayList = new ArrayList();
        readPcmDataIndex = 0;
        clipToPcmDataMap.clear();
        clipToLocalPathMap.clear();
        new Thread(new AnonymousClass2(i, arrayList, fArr, maxChannelNum, bArr)).start();
    }

    /* JADX INFO: renamed from: com.wanos.careditproject.utils.PlayerUtils$2, reason: invalid class name */
    class AnonymousClass2 implements Runnable {
        final /* synthetic */ byte[] val$buffer;
        final /* synthetic */ float[] val$bufferFloat;
        final /* synthetic */ List val$clipIdsUsed;
        final /* synthetic */ int val$maxTrackNum;
        final /* synthetic */ int val$threadId;

        AnonymousClass2(int i, List list, float[] fArr, int i2, byte[] bArr) {
            this.val$threadId = i;
            this.val$clipIdsUsed = list;
            this.val$bufferFloat = fArr;
            this.val$maxTrackNum = i2;
            this.val$buffer = bArr;
        }

        /* JADX WARN: Removed duplicated region for block: B:155:0x033b  */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                Method dump skipped, instruction units count: 964
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.wanos.careditproject.utils.PlayerUtils.AnonymousClass2.run():void");
        }

        static /* synthetic */ boolean lambda$run$0(List list, Map.Entry entry) {
            String str = (String) entry.getKey();
            RandomAccessFile randomAccessFile = (RandomAccessFile) entry.getValue();
            if (list.contains(str)) {
                return false;
            }
            try {
                EditingUtils.log("readThread randomFile close " + str);
                randomAccessFile.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return true;
            }
        }

        static /* synthetic */ boolean lambda$run$1(List list, String str) {
            String[] strArrSplit = str.split("_");
            if (strArrSplit.length < 2) {
                return false;
            }
            String str2 = strArrSplit[1];
            if (list.contains(str2)) {
                return false;
            }
            EditingUtils.log("readThread readClipIdListWanos close " + str2);
            NativeMethod.getInstance().audioClose(str);
            return true;
        }
    }

    protected static void startOtherThread(final int i) {
        new Thread(new Runnable() { // from class: com.wanos.careditproject.utils.PlayerUtils.3
            @Override // java.lang.Runnable
            public void run() {
                EditingUtils.log("PlayerUtils startOtherThread 0");
                while (PlayerUtils.playState != EditPlayState.STOP && PlayerUtils.curPlayThreadId == i) {
                    if (PlayerUtils.playState != EditPlayState.PAUSE && PlayerUtils.playState != EditPlayState.TOPAUSE) {
                        PlayerUtils.updateProgressUI();
                    }
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                PlayerUtils.updateProgressUI();
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void updateProgressUI() {
        long progressSampleNumPreview;
        if (playerListener != null) {
            if (!playIsPreview) {
                progressSampleNumPreview = EditingParams.getInstance().getProgressSampleNum();
            } else {
                progressSampleNumPreview = EditingParams.getInstance().getProgressSampleNumPreview();
            }
            int i = (int) progressSampleNumPreview;
            if (!playerIsAi) {
                playerListener.onProgress(i);
            } else {
                if (isStop()) {
                    return;
                }
                playerListener.onProgress(i);
            }
        }
    }

    public static void play(PlayerListener playerListener2, boolean z, boolean z2, boolean z3) {
        play(playerListener2, z, z2, z3, false);
    }

    public static void play(PlayerListener playerListener2, final boolean z, final boolean z2, final boolean z3, boolean z4) {
        playerListener = playerListener2;
        playIsPreview = z;
        playerIsAi = z4;
        playState = EditPlayState.PLAYING;
        playIsStop = false;
        int maxChannelNum = EditingUtils.getMaxChannelNum() * 2;
        final float[][] fArr = (float[][]) Array.newInstance((Class<?>) Float.TYPE, maxChannelNum, EditingUtils.playerStep);
        final float[][] fArr2 = (float[][]) Array.newInstance((Class<?>) Float.TYPE, maxChannelNum, 3);
        final float[] fArr3 = new float[maxChannelNum];
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        final ArrayList arrayList3 = new ArrayList();
        final ArrayList arrayList4 = new ArrayList();
        final int maxSampleNum = (int) EditingUtils.getMaxSampleNum();
        final ArrayList arrayList5 = new ArrayList();
        final float[][] fArr4 = (float[][]) Array.newInstance((Class<?>) Float.TYPE, maxChannelNum, EditingUtils.playerStep);
        final float[] fArr5 = new float[maxChannelNum];
        final float[] fArr6 = new float[maxChannelNum];
        final float[] fArr7 = new float[maxChannelNum];
        final int[] iArr = new int[maxChannelNum];
        final float[] fArr8 = new float[maxChannelNum];
        final String[] trackColorStringList = EditingUtils.getTrackColorStringList();
        progressNum = 0;
        playIndex = 0;
        int i = baseThreadId + 1;
        baseThreadId = i;
        curPlayThreadId = i;
        if (DataHelpAudioTrack.getTrackSize() == 0) {
            PlayerListener playerListener3 = playerListener;
            if (playerListener3 != null) {
                playerListener3.onStop();
            }
            playerListener = null;
        }
        if (!z4) {
            GlobalAudioFocusManager.getInstance().requestFocus(iPlayer);
        }
        if (!z3) {
            if (z2) {
                initClickTrack();
                productionSysPlayer.getInstance().play();
                startReadThread(baseThreadId);
            } else {
                productionSysPlayer.getInstance().playEx();
            }
            startOtherThread(baseThreadId);
        } else {
            productionSysPlayer.getInstance().playEx();
        }
        final int i2 = baseThreadId;
        Thread thread = new Thread(new Runnable() { // from class: com.wanos.careditproject.utils.PlayerUtils.4
            /* JADX WARN: Removed duplicated region for block: B:126:0x0315  */
            /* JADX WARN: Removed duplicated region for block: B:172:0x041a A[PHI: r3 r13
  0x041a: PHI (r3v16 int) = (r3v5 int), (r3v17 int) binds: [B:125:0x0313, B:312:0x041a] A[DONT_GENERATE, DONT_INLINE]
  0x041a: PHI (r13v6 int) = (r13v1 int), (r13v8 int) binds: [B:125:0x0313, B:312:0x041a] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Removed duplicated region for block: B:175:0x0422  */
            /* JADX WARN: Removed duplicated region for block: B:305:0x0437 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void run() {
                /*
                    Method dump skipped, instruction units count: 1676
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.wanos.careditproject.utils.PlayerUtils.AnonymousClass4.run():void");
            }
        });
        thread.setPriority(10);
        thread.start();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:23:0x005c. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:43:0x008e. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0222  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x022e  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x023f  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0242  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x0155 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:222:0x0252 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:6:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0149  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int processClip(com.wanos.careditproject.model.server.ClipModel r28, int r29, long r30, boolean r32, float r33, int r34, boolean r35, java.util.List<java.lang.Float> r36, int r37, float[][] r38, float[][] r39, float[] r40, com.wanos.careditproject.model.web.WebBallInfoModel r41, int[] r42, int r43) {
        /*
            Method dump skipped, instruction units count: 930
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wanos.careditproject.utils.PlayerUtils.processClip(com.wanos.careditproject.model.server.ClipModel, int, long, boolean, float, int, boolean, java.util.List, int, float[][], float[][], float[], com.wanos.careditproject.model.web.WebBallInfoModel, int[], int):int");
    }

    public static float[] getBallLeftAndRightPos(int i, float[] fArr) {
        return NativeObjPos.getDisplayPos(fArr[0], fArr[1]);
    }

    public static AudioPcmData.PcmData getPcmData(long j, String str) {
        if (clipToPcmDataMap.containsKey(Long.valueOf(j))) {
            return clipToPcmDataMap.get(Long.valueOf(j));
        }
        AudioPcmData.PcmData pcmData = AudioPcmData.getInstance().getPcmData(str);
        clipToPcmDataMap.put(Long.valueOf(j), pcmData);
        return pcmData;
    }

    public static String getLocalFilePath(long j, String str) {
        if (clipToLocalPathMap.containsKey(Long.valueOf(j))) {
            return clipToLocalPathMap.get(Long.valueOf(j));
        }
        String localFilePath = DownloadUtils.getInstance().getLocalFilePath(str);
        clipToLocalPathMap.put(Long.valueOf(j), localFilePath);
        return localFilePath;
    }

    public static void getPlayingTrack(boolean z, List<TrackItemModel> list, List<Long> list2) {
        boolean z2 = false;
        for (int i = 0; i < list.size(); i++) {
            TrackItemModel trackItemModel = list.get(i);
            if (trackItemModel != null) {
                if (!z2) {
                    if (trackItemModel.getIsSolo()) {
                        list2.clear();
                        if (!trackItemModel.getIsMute()) {
                            list2.add(Long.valueOf(trackItemModel.getID()));
                        }
                        z2 = true;
                    } else if (!trackItemModel.getIsMute()) {
                        list2.add(Long.valueOf(trackItemModel.getID()));
                    }
                } else if (trackItemModel.getIsSolo() && !trackItemModel.getIsMute()) {
                    list2.add(Long.valueOf(trackItemModel.getID()));
                }
            }
        }
    }

    public static void initBallPos(boolean z, boolean z2) {
        if (!z2) {
            WanosPlayerParamUtils.getInstance().getCurSampleNum();
        } else if (!z) {
            EditingParams.getInstance().getProgressSampleNum();
        } else {
            EditingParams.getInstance().getProgressSampleNumPreview();
        }
    }

    public static void changePlayState(EditPlayState editPlayState) {
        EditingUtils.log("PlayerUtils changePlayState state = " + editPlayState);
        playState = editPlayState;
        if (editPlayState == EditPlayState.PAUSE || playState == EditPlayState.STOP || playState == EditPlayState.NONE) {
            EventBus.getDefault().post(new UpdateEditUIEvent(UpdateEditUIEvent.eventType.updateTrackLeftView));
        }
    }

    public static void stop(boolean z) {
        EditingUtils.log("PlayerUtils stop isForce = " + z);
        if (playState == EditPlayState.STOP || playState == EditPlayState.TOSTOP) {
            return;
        }
        changePlayState(EditPlayState.TOSTOP);
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.wanos.careditproject.utils.PlayerUtils.5
            @Override // java.lang.Runnable
            public void run() {
                productionSysPlayer.getInstance().toStop();
            }
        });
        GlobalAudioFocusManager.getInstance().abandonFocus(iPlayer);
    }

    public static void forceStop() {
        changePlayState(EditPlayState.STOP);
    }

    public static void destoryPlayer(boolean z) {
        if (playIsStop) {
            return;
        }
        if (z) {
            productionSysPlayer.getInstance().setPcmInfo(null, null, null, null, null, 0.0f, 0, null);
            productionSysPlayer.getInstance().setPcmInfo(null, null, null, null, null, 0.0f, 0, null);
        }
        changePlayState(EditPlayState.STOP);
        playIsStop = true;
        Map<String, RandomAccessFile> map = clipId2File;
        if (map != null) {
            for (Map.Entry<String, RandomAccessFile> entry : map.entrySet()) {
                RandomAccessFile value = entry.getValue();
                try {
                    EditingUtils.log("readThread randomFile close " + entry.getKey());
                    value.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            clipId2File.clear();
        }
        Map<Integer, Map<String, Integer>> map2 = readCacheData;
        if (map2 != null) {
            map2.clear();
        }
    }

    public static void initClickTrack() {
        metronome metronome = EditingParams.getInstance().getMetronome();
        if (metronome != null) {
            metronome.setBeat(StorageUtils.getInstance().getBeatNum());
        }
    }

    public static void playClickTrack(int i) {
        metronome metronome = EditingParams.getInstance().getMetronome();
        if (metronome == null || !StorageUtils.getInstance().getBeatPlay()) {
            return;
        }
        float beatSpeed = 60.0f / StorageUtils.getInstance().getBeatSpeed();
        int i2 = (int) ((((i - EditingUtils.playerStep) * 1.0f) / EditingUtils.sampleRateDefault) / beatSpeed);
        int i3 = (int) (((i * 1.0f) / EditingUtils.sampleRateDefault) / beatSpeed);
        if (i2 != i3 || i - EditingUtils.playerStep == 0) {
            EditingUtils.log("playClickTrack cur = " + i3 + ", sampleNum=" + i);
            metronome.changeTick(((long) i3) + 1);
        }
        preClickNum = i3;
    }

    public static void pause() {
        changePlayState(EditPlayState.TOPAUSE);
        productionSysPlayer.getInstance().toPause();
    }

    public static void paused() {
        productionSysPlayer.getInstance().setPcmInfo(null, null, null, null, null, 0.0f, 0, null);
        productionSysPlayer.getInstance().setPcmInfo(null, null, null, null, null, 0.0f, 0, null);
        changePlayState(EditPlayState.PAUSE);
        Map<Integer, Map<String, Integer>> map = readCacheData;
        if (map != null) {
            map.clear();
        }
    }

    public static void resume(boolean z) {
        productionSysPlayer.getInstance().resume();
        changePlayState(EditPlayState.PLAYING);
        if (z) {
            clearReadData();
        }
    }

    public static void clearReadData() {
        if (!playIsPreview) {
            readCacheSampleNum = (int) EditingParams.getInstance().getProgressSampleNum();
        } else {
            readCacheSampleNum = (int) EditingParams.getInstance().getProgressSampleNumPreview();
        }
        EditingUtils.log("resume readCacheSampleNum=" + readCacheSampleNum);
        Map<Integer, Map<String, Integer>> map = readCacheData;
        if (map != null) {
            map.clear();
        }
    }

    public static void setPreviewColumn(float f) {
        previewColumn = f;
    }

    public static class PreBallPosInfo {
        private static int posCount = 4;
        private static int prePosCount = 2;
        private float[] pos;
        private float[] prePos;

        public PreBallPosInfo(float[] fArr, float[] fArr2) {
            int i = prePosCount;
            float[] fArr3 = new float[i];
            this.prePos = fArr3;
            System.arraycopy(fArr, 0, fArr3, 0, i);
            int i2 = posCount;
            float[] fArr4 = new float[i2];
            this.pos = fArr4;
            System.arraycopy(fArr2, 0, fArr4, 0, i2);
        }

        public float[] getPrePos() {
            return this.prePos;
        }

        public float[] getPos() {
            return this.pos;
        }
    }
}
