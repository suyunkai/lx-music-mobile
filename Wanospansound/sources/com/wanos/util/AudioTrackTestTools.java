package com.wanos.util;

import android.util.Log;
import com.blankj.utilcode.util.Utils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;

/* JADX INFO: loaded from: classes3.dex */
public class AudioTrackTestTools extends Thread {
    private static final String TAG = "wanos-[AudioTrackTestTools]";
    private static HashMap<String, AudioTrackTestTools> mTooles = new HashMap<>();
    private FileOutputStream mDataOutputStream;
    private final ArrayBlockingQueue<TaskEntity> mTask;

    public static AudioTrackTestTools getInstance(String str) {
        AudioTrackTestTools audioTrackTestTools = mTooles.get(str);
        if (audioTrackTestTools != null) {
            return audioTrackTestTools;
        }
        AudioTrackTestTools audioTrackTestTools2 = new AudioTrackTestTools(str);
        mTooles.put(str, audioTrackTestTools2);
        return audioTrackTestTools2;
    }

    public AudioTrackTestTools(String str) {
        super("AudioTrackTest");
        this.mTask = new ArrayBlockingQueue<>(1000);
        String str2 = Utils.getApp().getCacheDir().getAbsolutePath() + File.separator + "Temp" + File.separator;
        File file = new File(str2);
        if (!file.exists()) {
            Log.d(TAG, "AudioTrackTestTools: 创建临时日志目录 = " + file.mkdirs());
        }
        try {
            this.mDataOutputStream = new FileOutputStream(new File(str2 + str + ".pcm"), true);
        } catch (Exception e) {
            Log.e(TAG, "AudioTrackTestTools: 打开输入流 = " + e);
        }
        start();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        TaskEntity taskEntityTake;
        short[] shortData;
        super.run();
        while (true) {
            try {
                taskEntityTake = this.mTask.take();
            } catch (InterruptedException e) {
                Log.e(TAG, "run: " + e.getMessage());
                taskEntityTake = null;
            }
            if (taskEntityTake == null || this.mDataOutputStream == null) {
                return;
            }
            int i = 0;
            if (101 == taskEntityTake.getType()) {
                float[] floatData = taskEntityTake.getFloatData();
                if (floatData == null) {
                    continue;
                } else {
                    ByteBuffer byteBufferOrder = ByteBuffer.allocate(floatData.length * 4).order(ByteOrder.nativeOrder());
                    int length = floatData.length;
                    while (i < length) {
                        byteBufferOrder.putFloat(floatData[i]);
                        i++;
                    }
                    byteBufferOrder.flip();
                    try {
                        this.mDataOutputStream.write(byteBufferOrder.array());
                    } catch (IOException e2) {
                        throw new RuntimeException(e2);
                    }
                }
            } else if (102 == taskEntityTake.getType() && (shortData = taskEntityTake.getShortData()) != null) {
                ByteBuffer byteBufferOrder2 = ByteBuffer.allocate(shortData.length * 2).order(ByteOrder.nativeOrder());
                int length2 = shortData.length;
                while (i < length2) {
                    byteBufferOrder2.putShort(shortData[i]);
                    i++;
                }
                byteBufferOrder2.flip();
                try {
                    this.mDataOutputStream.write(byteBufferOrder2.array());
                } catch (IOException e3) {
                    throw new RuntimeException(e3);
                }
            }
        }
    }

    public void putFloatData(float[] fArr) {
        this.mTask.offer(new TaskEntity(fArr, 101));
    }

    public void putShortData(short[] sArr) {
        this.mTask.offer(new TaskEntity(sArr, 102));
    }

    private static class TaskEntity {
        private static final int TASK_FLOAT = 101;
        private static final int TASK_SHORT = 102;
        private float[] floatData;
        private short[] shortData;
        private final int type;

        public TaskEntity(float[] fArr, int i) {
            this.floatData = fArr;
            this.type = i;
        }

        public TaskEntity(short[] sArr, int i) {
            this.shortData = sArr;
            this.type = i;
        }

        public float[] getFloatData() {
            return this.floatData;
        }

        public short[] getShortData() {
            return this.shortData;
        }

        public int getType() {
            return this.type;
        }
    }
}
