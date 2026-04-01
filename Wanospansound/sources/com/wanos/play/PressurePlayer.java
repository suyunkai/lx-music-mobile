package com.wanos.play;

import android.os.Process;
import com.wanos.bean.PressureBean;
import com.wanos.util.Constant;
import com.wanos.util.EditingUtils;
import com.wanos.util.NativeMethod;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/* JADX INFO: loaded from: classes3.dex */
public class PressurePlayer {
    private static final String TAG = "PressurePlayer";
    private static PressurePlayer instance;
    private UpdateLineListener mUpdateLineListener;
    private Thread thread;
    private final List<PressureBean> pressureList = new ArrayList();
    private volatile boolean isPlaying = false;
    private final Map<Integer, Float> audioObjVolumnList = new HashMap();
    private final ArrayBlockingQueue<List<PressureBean.PressureLine>> mBallFrameQueue = new ArrayBlockingQueue<>(100);
    private volatile long teardTaskId = 0;

    public interface UpdateLineListener {
        void onUpdateLine(List<PressureBean.PressureLine> list, double d2, List<PressureBean.PressureLine> list2);
    }

    public enum ZERO_PlayerState {
        NONE,
        ADD,
        FADE_IN_ING,
        DELETE,
        FADE_OUT_ING
    }

    public static synchronized PressurePlayer getInstance() {
        if (instance == null) {
            instance = new PressurePlayer();
        }
        return instance;
    }

    public void initAudio(List<PressureBean> list) {
        synchronized (this.pressureList) {
            for (PressureBean pressureBean : list) {
                if (pressureBean != null) {
                    pressureBean.operationType = ZERO_PlayerState.ADD.ordinal();
                }
            }
            this.pressureList.clear();
            this.pressureList.addAll(list);
        }
    }

    public void addAudio(PressureBean pressureBean) {
        synchronized (this.pressureList) {
            pressureBean.operationType = ZERO_PlayerState.ADD.ordinal();
            this.pressureList.add(pressureBean);
        }
    }

    public void setBallVolume(Integer num, float f) {
        synchronized (this.pressureList) {
            Iterator<PressureBean> it = this.pressureList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                PressureBean next = it.next();
                if (next.id == num.intValue()) {
                    next.setVolume(f);
                    break;
                }
            }
        }
    }

    public void deleteAudio(PressureBean pressureBean) {
        synchronized (this.pressureList) {
            Iterator<PressureBean> it = this.pressureList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                PressureBean next = it.next();
                if (next.id == pressureBean.id) {
                    next.operationType = ZERO_PlayerState.DELETE.ordinal();
                    break;
                }
            }
        }
    }

    public void resetAudio() {
        this.mBallFrameQueue.clear();
        synchronized (this.pressureList) {
            for (PressureBean pressureBean : this.pressureList) {
                if (pressureBean != null) {
                    pressureBean.operationType = ZERO_PlayerState.DELETE.ordinal();
                }
            }
        }
    }

    public void onStart() {
        this.isPlaying = true;
        playAudio();
    }

    public void onStop() {
        this.isPlaying = false;
        AudioPlayer.getInstance().stop();
    }

    public void onRelease() {
        this.mBallFrameQueue.clear();
        this.isPlaying = false;
        Thread thread = this.thread;
        if (thread != null) {
            thread.interrupt();
        }
        this.teardTaskId = 0L;
        this.thread = null;
        AudioPlayer.getInstance().release();
        AudioPlayer.deleteInstance();
    }

    public void updatePos(PressureBean.PressureLine pressureLine) {
        synchronized (this.pressureList) {
            int i = 0;
            while (true) {
                if (i >= this.pressureList.size()) {
                    break;
                }
                PressureBean pressureBean = this.pressureList.get(i);
                if (pressureBean != null && pressureBean.id == pressureLine.index) {
                    if (pressureBean.pressureLine == null) {
                        pressureBean.pressureLine = new PressureBean.PressureLine();
                    }
                    pressureBean.pressureLine.index = pressureLine.index;
                    pressureBean.pressureLine.x_m = pressureLine.x_m;
                    pressureBean.pressureLine.y_m = pressureLine.y_m;
                    pressureBean.pressureLine.z_m = pressureLine.z_m;
                    pressureBean.pressureLine.volume = pressureLine.volume;
                    pressureBean.isUsePressureLine = true;
                }
                i++;
            }
        }
    }

    private void playAudio() {
        freeAudioRender();
        AudioPlayer.getInstance().initPan();
        AudioPlayer.getInstance().play();
        if (this.thread == null) {
            Thread thread = new Thread(new Runnable() { // from class: com.wanos.play.PressurePlayer.1
                @Override // java.lang.Runnable
                public void run() {
                    Process.setThreadPriority(-19);
                    PressurePlayer.this.readData();
                }
            });
            this.thread = thread;
            this.teardTaskId = thread.getId();
            this.thread.setName("BallMediaWork");
            this.thread.start();
        }
    }

    private void freeAudioRender() {
        AudioPlayer.getInstance().freePan();
    }

    public Queue<List<PressureBean.PressureLine>> getBallPos() {
        if (this.mBallFrameQueue.size() <= 0) {
            return null;
        }
        return this.mBallFrameQueue;
    }

    private int getFrameChannelLength(List<PressureBean> list) {
        int i = 0;
        if (list != null && !list.isEmpty()) {
            for (PressureBean pressureBean : list) {
                if (pressureBean != null) {
                    if (pressureBean.audioType == 1) {
                        i++;
                    } else if (pressureBean.audioType == 2) {
                        i += 2;
                    }
                }
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void readData() {
        int[] iArr;
        float[] fArr;
        float[] fArr2;
        float[] fArr3;
        ArrayList arrayList;
        float formatVolume;
        float audioWidth;
        ArrayList arrayList2;
        int i;
        float[][] fArr4;
        byte[] bArr;
        PressureBean pressureBean;
        boolean z;
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        float[][] fArr5 = (float[][]) Array.newInstance((Class<?>) Float.TYPE, 20, Constant.BUFFERSIZE);
        float[] fArr6 = new float[20];
        float[] fArr7 = new float[20];
        float[] fArr8 = new float[20];
        float[] fArr9 = new float[20];
        float[] fArr10 = new float[20];
        int[] iArr2 = new int[20];
        float[] fArr11 = new float[Constant.BUFFERSIZE * Constant.getPlayerChannelNum()];
        while (true) {
            if (this.isPlaying || this.teardTaskId == Thread.currentThread().getId()) {
                if (!this.isPlaying) {
                    if (!arrayList5.isEmpty()) {
                        arrayList5.clear();
                    }
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException unused) {
                        onDestroyMedia();
                        return;
                    }
                } else {
                    synchronized (this.pressureList) {
                        if (!arrayList3.isEmpty()) {
                            Iterator it = arrayList3.iterator();
                            while (it.hasNext()) {
                                int iIntValue = ((Integer) it.next()).intValue();
                                for (int size = arrayList5.size() - 1; size >= 0; size--) {
                                    if (arrayList5.get(size).id == iIntValue) {
                                        arrayList5.remove(size);
                                    }
                                }
                            }
                            arrayList3.clear();
                        }
                        int i2 = 0;
                        while (i2 < this.pressureList.size()) {
                            PressureBean pressureBean2 = this.pressureList.get(i2);
                            try {
                                pressureBean = (PressureBean) pressureBean2.clone();
                            } catch (CloneNotSupportedException unused2) {
                                pressureBean = null;
                            }
                            int[] iArr3 = iArr2;
                            if (pressureBean2.operationType == ZERO_PlayerState.DELETE.ordinal()) {
                                arrayList4.add(Integer.valueOf(pressureBean2.id));
                            }
                            float[] fArr12 = fArr9;
                            int i3 = -1;
                            int i4 = 0;
                            while (i4 < arrayList5.size()) {
                                PressureBean pressureBean3 = arrayList5.get(i4);
                                float[] fArr13 = fArr8;
                                float[] fArr14 = fArr7;
                                if (pressureBean3.id == pressureBean2.id) {
                                    pressureBean2.current = pressureBean3.current;
                                    pressureBean.current = pressureBean3.current;
                                    arrayList5.set(i4, pressureBean);
                                    this.pressureList.set(i2, pressureBean2);
                                    i3 = i4;
                                }
                                i4++;
                                fArr8 = fArr13;
                                fArr7 = fArr14;
                            }
                            float[] fArr15 = fArr7;
                            float[] fArr16 = fArr8;
                            if (i3 == -1) {
                                int i5 = 0;
                                while (true) {
                                    if (i5 >= arrayList5.size()) {
                                        z = false;
                                        break;
                                    } else {
                                        if (arrayList5.get(i5).id == pressureBean.id) {
                                            z = true;
                                            break;
                                        }
                                        i5++;
                                    }
                                }
                                if (!z) {
                                    arrayList5.add(pressureBean);
                                }
                            }
                            i2++;
                            fArr9 = fArr12;
                            iArr2 = iArr3;
                            fArr8 = fArr16;
                            fArr7 = fArr15;
                        }
                        iArr = iArr2;
                        fArr = fArr7;
                        fArr2 = fArr8;
                        fArr3 = fArr9;
                        if (!arrayList4.isEmpty()) {
                            Iterator it2 = arrayList4.iterator();
                            while (it2.hasNext()) {
                                int iIntValue2 = ((Integer) it2.next()).intValue();
                                for (int size2 = this.pressureList.size() - 1; size2 >= 0; size2--) {
                                    PressureBean pressureBean4 = this.pressureList.get(size2);
                                    if (pressureBean4.id == iIntValue2) {
                                        this.pressureList.remove(size2);
                                        AudioFileMate.getInstance(pressureBean4.id).releaseBall();
                                    }
                                }
                            }
                            arrayList4.clear();
                        }
                        Iterator<PressureBean> it3 = this.pressureList.iterator();
                        while (it3.hasNext()) {
                            it3.next().operationType = ZERO_PlayerState.NONE.ordinal();
                        }
                    }
                    int frameChannelLength = getFrameChannelLength(arrayList5);
                    if (frameChannelLength == 0) {
                        fArr9 = fArr3;
                        iArr2 = iArr;
                        fArr8 = fArr2;
                        fArr7 = fArr;
                    } else {
                        ArrayList arrayList6 = new ArrayList();
                        int i6 = 0;
                        int i7 = 0;
                        while (i6 < arrayList5.size()) {
                            PressureBean pressureBean5 = arrayList5.get(i6);
                            if (pressureBean5 == null) {
                                arrayList = arrayList4;
                                arrayList2 = arrayList5;
                                fArr4 = fArr5;
                                i = i6;
                            } else {
                                AudioFileMate.getInstance(pressureBean5.id).setAudioFilePath(pressureBean5.audioPath, pressureBean5.getChannelCount());
                                float fFloatValue = this.audioObjVolumnList.containsKey(Integer.valueOf(pressureBean5.id)) ? this.audioObjVolumnList.get(Integer.valueOf(pressureBean5.id)).floatValue() : 1.0f;
                                if (pressureBean5.audioType == 1) {
                                    pressureBean5.channelCount = 1;
                                } else if (pressureBean5.audioType == 2) {
                                    pressureBean5.channelCount = 2;
                                }
                                Iterator<PressureBean> it4 = this.pressureList.iterator();
                                while (true) {
                                    if (!it4.hasNext()) {
                                        arrayList = arrayList4;
                                        formatVolume = 5.0f;
                                        audioWidth = 0.0f;
                                        break;
                                    } else {
                                        PressureBean next = it4.next();
                                        arrayList = arrayList4;
                                        if (next.id == pressureBean5.id) {
                                            formatVolume = next.getFormatVolume();
                                            audioWidth = next.getAudioWidth();
                                            break;
                                        }
                                        arrayList4 = arrayList;
                                    }
                                }
                                float f = fFloatValue;
                                float f2 = audioWidth;
                                byte[] ballPcm = AudioFileMate.getInstance(pressureBean5.id).getBallPcm(pressureBean5.current);
                                float[] fArr17 = new float[Constant.BUFFERSIZE * pressureBean5.channelCount];
                                arrayList2 = arrayList5;
                                i = i6;
                                float[][] fArr18 = (float[][]) Array.newInstance((Class<?>) Float.TYPE, pressureBean5.channelCount, Constant.BUFFERSIZE);
                                if (ballPcm != null) {
                                    int length = ballPcm.length / EditingUtils.sizeOfShort;
                                    ByteBuffer.wrap(ballPcm).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().get(new short[length]);
                                    for (int i8 = 0; i8 < length; i8++) {
                                        fArr17[i8] = (r14[i8] / 32768.0f) * formatVolume;
                                    }
                                    float f3 = f;
                                    int i9 = 0;
                                    while (i9 < pressureBean5.channelCount) {
                                        int i10 = 0;
                                        while (i10 < Constant.BUFFERSIZE) {
                                            int i11 = (pressureBean5.channelCount * i10) + i9;
                                            ArrayList arrayList7 = arrayList6;
                                            if (pressureBean5.operationType == ZERO_PlayerState.ADD.ordinal() || pressureBean5.operationType == ZERO_PlayerState.FADE_IN_ING.ordinal()) {
                                                if (pressureBean5.operationType == ZERO_PlayerState.ADD.ordinal()) {
                                                    pressureBean5.operationType = ZERO_PlayerState.FADE_IN_ING.ordinal();
                                                    f3 = 0.0f;
                                                } else {
                                                    f3 += 0.01f;
                                                }
                                                if (f3 >= 1.0f) {
                                                    pressureBean5.operationType = ZERO_PlayerState.NONE.ordinal();
                                                    f3 = 1.0f;
                                                }
                                            } else if (pressureBean5.operationType == ZERO_PlayerState.DELETE.ordinal() || pressureBean5.operationType == ZERO_PlayerState.FADE_OUT_ING.ordinal()) {
                                                if (pressureBean5.operationType == ZERO_PlayerState.DELETE.ordinal()) {
                                                    pressureBean5.operationType = ZERO_PlayerState.FADE_OUT_ING.ordinal();
                                                }
                                                f3 -= 0.001f;
                                                if (f3 <= 0.0f) {
                                                    pressureBean5.operationType = ZERO_PlayerState.NONE.ordinal();
                                                    arrayList3.add(Integer.valueOf(pressureBean5.id));
                                                    f3 = 0.0f;
                                                }
                                            }
                                            fArr18[i9][i10] = fArr17[i11] * f3;
                                            i10++;
                                            arrayList6 = arrayList7;
                                        }
                                        i9++;
                                        arrayList6 = arrayList6;
                                    }
                                    ArrayList arrayList8 = arrayList6;
                                    this.audioObjVolumnList.put(Integer.valueOf(pressureBean5.id), Float.valueOf(f3));
                                    if (pressureBean5.channelCount != 1) {
                                        arrayList6 = arrayList8;
                                        if (pressureBean5.channelCount == 2) {
                                            if (pressureBean5.isUsePressureLine) {
                                                float[] fArrStereoPos = NativeMethod.getInstance().stereoPos(new float[]{pressureBean5.pressureLine.x_m, pressureBean5.pressureLine.y_m, pressureBean5.pressureLine.z_m});
                                                if (i7 < frameChannelLength) {
                                                    fArr6[i7] = fArrStereoPos[3];
                                                    fArr[i7] = fArrStereoPos[4];
                                                    fArr2[i7] = fArrStereoPos[5];
                                                    fArr3[i7] = pressureBean5.pressureLine.volume;
                                                    int i12 = i7 + 1;
                                                    if (i12 < frameChannelLength) {
                                                        fArr6[i12] = fArrStereoPos[6];
                                                        fArr[i12] = fArrStereoPos[7];
                                                        fArr2[i12] = fArrStereoPos[8];
                                                        fArr3[i12] = pressureBean5.pressureLine.volume;
                                                        PressureBean.PressureLine pressureLine = new PressureBean.PressureLine();
                                                        pressureLine.index = pressureBean5.id;
                                                        fArr4 = fArr5;
                                                        bArr = ballPcm;
                                                        pressureLine.seek = (pressureBean5.current - ((Constant.BUFFERSIZE * pressureBean5.channelCount) * EditingUtils.sizeOfShort)) / (pressureBean5.channelCount * EditingUtils.sizeOfShort);
                                                        pressureLine.x_m = fArrStereoPos[0];
                                                        pressureLine.y_m = fArrStereoPos[1];
                                                        pressureLine.z_m = fArrStereoPos[2];
                                                        pressureLine.x_l = fArrStereoPos[3];
                                                        pressureLine.y_l = fArrStereoPos[4];
                                                        pressureLine.z_l = fArrStereoPos[5];
                                                        pressureLine.x_r = fArrStereoPos[6];
                                                        pressureLine.y_r = fArrStereoPos[7];
                                                        pressureLine.z_r = fArrStereoPos[8];
                                                        pressureLine.size = f2;
                                                        arrayList6.add(pressureLine);
                                                    }
                                                }
                                                fArr4 = fArr5;
                                            } else {
                                                fArr4 = fArr5;
                                                bArr = ballPcm;
                                                PressureBean.PressureLine xyz = getXYZ(pressureBean5.current, pressureBean5);
                                                if (i7 < frameChannelLength) {
                                                    if (xyz != null) {
                                                        fArr6[i7] = xyz.x_l;
                                                        fArr[i7] = xyz.y_l;
                                                        fArr2[i7] = xyz.z_l;
                                                        fArr3[i7] = xyz.volume;
                                                        int i13 = i7 + 1;
                                                        if (i13 < frameChannelLength) {
                                                            fArr6[i13] = xyz.x_r;
                                                            fArr[i13] = xyz.y_r;
                                                            fArr2[i13] = xyz.z_r;
                                                            fArr3[i13] = xyz.volume;
                                                            xyz.seek = (pressureBean5.current - ((Constant.BUFFERSIZE * pressureBean5.channelCount) * EditingUtils.sizeOfShort)) / (pressureBean5.channelCount * EditingUtils.sizeOfShort);
                                                            xyz.size = f2;
                                                            arrayList6.add(xyz);
                                                        }
                                                    }
                                                }
                                            }
                                            fArr4[i7] = fArr18[0];
                                            fArr10[i7] = pressureBean5.size;
                                            iArr[i7] = i7;
                                            int i14 = i7 + 1;
                                            fArr4[i14] = fArr18[1];
                                            fArr10[i14] = pressureBean5.size;
                                            iArr[i14] = i14;
                                            pressureBean5.setCurrent(pressureBean5.current + bArr.length);
                                            i7 += 2;
                                        } else {
                                            fArr4 = fArr5;
                                        }
                                    } else if (i7 >= frameChannelLength) {
                                        fArr4 = fArr5;
                                        arrayList6 = arrayList8;
                                    } else {
                                        fArr5[i7] = fArr18[0];
                                        fArr10[i7] = pressureBean5.size;
                                        if (pressureBean5.isUsePressureLine) {
                                            fArr6[i7] = pressureBean5.pressureLine.x_m;
                                            fArr[i7] = pressureBean5.pressureLine.y_m;
                                            fArr2[i7] = pressureBean5.pressureLine.z_m;
                                            fArr3[i7] = pressureBean5.pressureLine.volume;
                                            PressureBean.PressureLine pressureLine2 = new PressureBean.PressureLine();
                                            pressureLine2.index = pressureBean5.id;
                                            pressureLine2.seek = (pressureBean5.current - ((Constant.BUFFERSIZE * pressureBean5.channelCount) * EditingUtils.sizeOfShort)) / (pressureBean5.channelCount * EditingUtils.sizeOfShort);
                                            pressureLine2.x_m = pressureBean5.pressureLine.x_m;
                                            pressureLine2.y_m = pressureBean5.pressureLine.y_m;
                                            pressureLine2.z_m = pressureBean5.pressureLine.z_m;
                                            pressureLine2.size = f2;
                                            arrayList6 = arrayList8;
                                            arrayList6.add(pressureLine2);
                                        } else {
                                            arrayList6 = arrayList8;
                                            PressureBean.PressureLine xyz2 = getXYZ(pressureBean5.current, pressureBean5);
                                            if (xyz2 != null) {
                                                fArr6[i7] = xyz2.x_m;
                                                fArr[i7] = xyz2.y_m;
                                                fArr2[i7] = xyz2.z_m;
                                                fArr3[i7] = xyz2.volume;
                                                xyz2.seek = (pressureBean5.current - ((Constant.BUFFERSIZE * pressureBean5.channelCount) * EditingUtils.sizeOfShort)) / (pressureBean5.channelCount * EditingUtils.sizeOfShort);
                                                xyz2.size = f2;
                                                arrayList6.add(xyz2);
                                            }
                                            fArr4 = fArr5;
                                        }
                                        iArr[i7] = i7;
                                        pressureBean5.setCurrent(pressureBean5.current + ballPcm.length);
                                        i7++;
                                        if (i7 + 1 < frameChannelLength) {
                                            fArr4 = fArr5;
                                        }
                                        fArr4 = fArr5;
                                    }
                                } else {
                                    fArr4 = fArr5;
                                    pressureBean5.current = 0;
                                }
                                i6 = i + 1;
                                fArr5 = fArr4;
                                arrayList4 = arrayList;
                                arrayList5 = arrayList2;
                            }
                            i6 = i + 1;
                            fArr5 = fArr4;
                            arrayList4 = arrayList;
                            arrayList5 = arrayList2;
                        }
                        this.mBallFrameQueue.offer(arrayList6);
                        fArr9 = fArr3;
                        iArr2 = iArr;
                        fArr8 = fArr2;
                        fArr7 = fArr;
                        arrayList5 = arrayList5;
                        fArr5 = fArr5;
                        arrayList4 = arrayList4;
                    }
                }
            } else {
                onDestroyMedia();
                return;
            }
        }
    }

    private void onDestroyMedia() {
        Iterator<PressureBean> it = this.pressureList.iterator();
        while (it.hasNext()) {
            AudioFileMate.getInstance(it.next().id).releaseBall();
        }
        this.pressureList.clear();
    }

    public void syncLine(List<PressureBean.PressureLine> list, double d2, List<PressureBean.PressureLine> list2) {
        UpdateLineListener updateLineListener = this.mUpdateLineListener;
        if (updateLineListener != null) {
            updateLineListener.onUpdateLine(list, d2, list2);
        }
    }

    private PressureBean.PressureLine getXYZ(int i, PressureBean pressureBean) {
        if (pressureBean == null || pressureBean.line == null) {
            return null;
        }
        int i2 = (i - ((Constant.BUFFERSIZE * pressureBean.channelCount) * EditingUtils.sizeOfShort)) / (pressureBean.channelCount * EditingUtils.sizeOfShort);
        PressureBean.PressureLine pressureLine = new PressureBean.PressureLine();
        List<PressureBean.PressureLine> list = pressureBean.line;
        if (list.size() == 1) {
            PressureBean.PressureLine pressureLine2 = list.get(0);
            pressureLine.index = pressureLine2.index;
            pressureLine.seek = i2;
            pressureLine.x_m = pressureLine2.x_m;
            pressureLine.y_m = pressureLine2.y_m;
            pressureLine.z_m = pressureLine2.z_m;
            pressureLine.x_l = pressureLine2.x_l;
            pressureLine.y_l = pressureLine2.y_l;
            pressureLine.z_l = pressureLine2.z_l;
            pressureLine.x_r = pressureLine2.x_r;
            pressureLine.y_r = pressureLine2.y_r;
            pressureLine.z_r = pressureLine2.z_r;
            pressureLine.volume = pressureLine2.volume;
            return pressureLine;
        }
        PressureBean.PressureLine pressureLine3 = list.get(list.size() - 1);
        long j = i2;
        if (j >= pressureLine3.seek) {
            pressureLine.index = pressureLine3.index;
            pressureLine.seek = j;
            pressureLine.x_m = pressureLine3.x_m;
            pressureLine.y_m = pressureLine3.y_m;
            pressureLine.z_m = pressureLine3.z_m;
            pressureLine.x_l = pressureLine3.x_l;
            pressureLine.y_l = pressureLine3.y_l;
            pressureLine.z_l = pressureLine3.z_l;
            pressureLine.x_r = pressureLine3.x_r;
            pressureLine.y_r = pressureLine3.y_r;
            pressureLine.z_r = pressureLine3.z_r;
            pressureLine.volume = pressureLine3.volume;
            return pressureLine;
        }
        for (int i3 = 1; i3 < list.size(); i3++) {
            PressureBean.PressureLine pressureLine4 = list.get(i3 - 1);
            PressureBean.PressureLine pressureLine5 = list.get(i3);
            if (j >= pressureLine4.seek && j < pressureLine5.seek) {
                pressureLine.index = pressureLine4.index;
                pressureLine.seek = j;
                pressureLine.x_m = pressureLine4.x_m;
                pressureLine.y_m = pressureLine4.y_m;
                pressureLine.z_m = pressureLine4.z_m;
                pressureLine.x_l = pressureLine4.x_l;
                pressureLine.y_l = pressureLine4.y_l;
                pressureLine.z_l = pressureLine4.z_l;
                pressureLine.x_r = pressureLine4.x_r;
                pressureLine.y_r = pressureLine4.y_r;
                pressureLine.z_r = pressureLine4.z_r;
                pressureLine.volume = pressureLine4.volume;
                return pressureLine;
            }
        }
        return pressureLine;
    }

    public void setmUpdateLineListener(UpdateLineListener updateLineListener) {
        this.mUpdateLineListener = updateLineListener;
    }
}
