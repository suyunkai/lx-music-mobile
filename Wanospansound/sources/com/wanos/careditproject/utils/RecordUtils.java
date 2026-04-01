package com.wanos.careditproject.utils;

import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.os.Process;
import com.wanos.careditproject.view.EditingActivity;
import com.wanos.util.NativeMethod;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class RecordUtils {
    public static int recordChannel = 1;
    public static RecordUtils recordUtils;
    private EditingActivity editingActivity;
    private RecordListener listener;
    private RecordRes recordRes;
    private Thread recordThread;
    private String recordFileName = "record_";
    private int recordIndex = 0;
    private String curRecordId = "";
    private int curRecordSimpleCount = 0;
    private int recordUIIndex = 0;
    private Runnable recordRunnable = new Runnable() { // from class: com.wanos.careditproject.utils.RecordUtils.1
        @Override // java.lang.Runnable
        public void run() {
            Process.setThreadPriority(-19);
            RecordUtils.this.recordRes = new RecordRes();
            long baseClipId = DataHelpAudioTrack.getBaseClipId();
            RecordUtils.this.curRecordDataSize = 0;
            RecordUtils.this.recordRes.recordWavData = new short[EditingUtils.getTotalTimeOfSecond() * ((EditingUtils.sampleRateDefault / EditingUtils.pcmWaveStep) + 1)];
            RecordUtils.this.recordRes.localPath = EditingUtils.getResLocalPath() + "/record_" + baseClipId + ".m4a";
            EditingUtils.log("record aaa path = " + RecordUtils.this.recordRes.localPath);
            int iEncodeInit = NativeMethod.getInstance().encodeInit(EditingUtils.encodeStep, RecordUtils.recordChannel, 48000, 32, RecordUtils.this.recordRes.localPath);
            if (iEncodeInit <= 0) {
                if (RecordUtils.this.listener != null) {
                    RecordUtils.this.listener.onStop(RecordUtils.this.new RecordResult(false, null, "编码失败"));
                }
                RecordUtils.this.stopRecord();
                return;
            }
            int i = EditingUtils.encodeStep * EditingUtils.sizeOfShort;
            float[] fArr = new float[i];
            byte[] bArr = new byte[i];
            RecordUtils.this.recordUIIndex = 0;
            int totalTimeOfSecond = EditingUtils.getTotalTimeOfSecond() * EditingUtils.sampleRateDefault;
            int i2 = 0;
            while (RecordManager.getInstance().isRecording()) {
                if (RecordManager.getInstance().read(bArr, i) > 0) {
                    int iMin = Math.min(i, i) / EditingUtils.sizeOfShort;
                    RecordUtils.this.recordRes.sampleNum += (long) iMin;
                    float f = 0.0f;
                    for (int i3 = 0; i3 < iMin; i3++) {
                        int i4 = i3 * 2;
                        float f2 = ((short) ((bArr[i4] & 255) | (bArr[i4 + 1] << 8))) / 32768.0f;
                        fArr[i3] = f2;
                        if (f2 > f) {
                            f = f2;
                        }
                    }
                    if (NativeMethod.getInstance().encode(iEncodeInit, fArr, iMin) != 0) {
                        EditingUtils.log("recordRunnable 3");
                    } else {
                        RecordUtils.access$112(RecordUtils.this, i / EditingUtils.sizeOfShort);
                        int i5 = RecordUtils.this.curRecordDataSize / EditingUtils.pcmWaveStep;
                        if (i5 > i2) {
                            while (i2 < i5) {
                                RecordUtils.this.recordRes.recordWavData[i2] = (short) (f * 32768.0f);
                                i2++;
                            }
                            i2 = i5;
                        }
                        RecordUtils.this.recordRes.recordWavDataLen = i2;
                        if (RecordUtils.this.listener != null && RecordUtils.this.recordUIIndex % 3 == 0) {
                            RecordUtils.this.listener.onProgress(RecordUtils.this.recordRes);
                        }
                        RecordUtils.access$308(RecordUtils.this);
                        long j = totalTimeOfSecond;
                        if (RecordUtils.this.recordRes.sampleNum >= j) {
                            RecordUtils.this.recordRes.sampleNum = j;
                            RecordUtils.this.stopRecord();
                        }
                    }
                } else {
                    EditingUtils.log("recordRunnable 2");
                }
            }
            long j2 = totalTimeOfSecond;
            if (RecordUtils.this.recordRes.sampleNum >= j2) {
                RecordUtils.this.recordRes.sampleNum = j2;
            }
            NativeMethod.getInstance().encodeClose(iEncodeInit);
            if (RecordUtils.this.listener != null) {
                RecordListener recordListener = RecordUtils.this.listener;
                RecordUtils recordUtils2 = RecordUtils.this;
                recordListener.onStop(recordUtils2.new RecordResult(true, recordUtils2.recordRes, ""));
            }
            RecordUtils.this.listener = null;
            RecordUtils.this.recordThread = null;
        }
    };
    private Map<String, String> localPath2clipId = new HashMap();
    private int curRecordDataSize = 0;

    public interface RecordListener {
        void onProgress(RecordRes recordRes);

        void onStop(RecordResult recordResult);

        void savePcmData(String str, String str2);
    }

    public static class RecordRes {
        public String localPath;
        public short[] recordWavData;
        public int recordWavDataLen;
        public long sampleNum;
    }

    public enum RecordState {
        playing,
        stop
    }

    static /* synthetic */ int access$112(RecordUtils recordUtils2, int i) {
        int i2 = recordUtils2.curRecordDataSize + i;
        recordUtils2.curRecordDataSize = i2;
        return i2;
    }

    static /* synthetic */ int access$308(RecordUtils recordUtils2) {
        int i = recordUtils2.recordUIIndex;
        recordUtils2.recordUIIndex = i + 1;
        return i;
    }

    public static RecordUtils getInstance() {
        if (recordUtils == null) {
            recordUtils = new RecordUtils();
        }
        return recordUtils;
    }

    public static void deleteInstance() {
        recordUtils = null;
    }

    RecordUtils() {
    }

    public void setActivity(EditingActivity editingActivity) {
        this.editingActivity = editingActivity;
    }

    public int getRecordSize() {
        return this.curRecordDataSize;
    }

    public String startRecord(RecordListener recordListener) {
        this.listener = recordListener;
        int audioInputDevice = StorageUtils.getInstance().getAudioInputDevice();
        boolean z = false;
        for (AudioDeviceInfo audioDeviceInfo : ((AudioManager) this.editingActivity.getSystemService("audio")).getDevices(1)) {
            if (audioDeviceInfo.getId() == audioInputDevice) {
                z = true;
            }
        }
        if (!z) {
            StorageUtils.getInstance().setAudioInputDevice(1);
            audioInputDevice = 1;
        }
        if (!RecordManager.getInstance().start(audioInputDevice)) {
            return "-1";
        }
        String str = this.recordFileName + this.recordIndex;
        if (this.recordThread != null) {
            return "";
        }
        Thread thread = new Thread(this.recordRunnable);
        this.recordThread = thread;
        thread.start();
        this.recordIndex++;
        return this.curRecordId;
    }

    public void stopRecord() {
        RecordManager.getInstance().stop();
        this.recordThread = null;
    }

    public boolean isRecording() {
        return RecordManager.getInstance().isRecording();
    }

    public class RecordResult {
        public boolean isSuccess;
        public String msg;
        public RecordRes recordRes;

        public RecordResult(boolean z, RecordRes recordRes, String str) {
            this.isSuccess = z;
            this.recordRes = recordRes;
            this.msg = str;
        }
    }
}
