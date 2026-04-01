package com.wanos.media.util;

import android.text.TextUtils;
import androidx.core.util.Pools;
import com.blankj.utilcode.util.FileUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/* JADX INFO: loaded from: classes3.dex */
public class ZeroCacheScan extends Thread {
    private static volatile ZeroCacheScan INSTANCE = null;
    private static final String TAG = "ZeroCacheScan";
    private volatile boolean isBreakDelete;
    private final Pools.SynchronizedPool<ScanMessage> mPools;
    private final ArrayBlockingQueue<ScanMessage> mTaskQueue;

    public static ZeroCacheScan getInstance() {
        if (INSTANCE == null) {
            synchronized (ZeroCacheScan.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ZeroCacheScan();
                }
            }
        }
        return INSTANCE;
    }

    private ZeroCacheScan() {
        super("ZeroScanThread");
        this.mTaskQueue = new ArrayBlockingQueue<>(50);
        this.mPools = new Pools.SynchronizedPool<>(20);
        this.isBreakDelete = false;
        start();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        ScanMessage scanMessageTake;
        super.run();
        while (true) {
            try {
                scanMessageTake = this.mTaskQueue.take();
            } catch (InterruptedException unused) {
                ZeroLogcatTools.e(TAG, "垃圾扫描线程异常中断");
                scanMessageTake = null;
            }
            if (scanMessageTake == null) {
                return;
            }
            int type = scanMessageTake.getType();
            if (type != 1001) {
                int i = 0;
                if (type == 1002) {
                    List<String> listWaitDeleteImage = CacheHotHelp.getInstance().waitDeleteImage();
                    while (i < listWaitDeleteImage.size() && !this.isBreakDelete) {
                        String str = listWaitDeleteImage.get(i);
                        if (FileUtils.delete(str)) {
                            CacheHotHelp.getInstance().deleteRecord(str);
                        }
                        i++;
                    }
                } else if (type == 2001) {
                    String filePath = scanMessageTake.getFilePath();
                    if (!TextUtils.isEmpty(filePath)) {
                        CacheHotHelp.getInstance().setVideoAccess(filePath);
                    }
                } else if (type == 2002) {
                    List<String> listWaitDeleteVideo = CacheHotHelp.getInstance().waitDeleteVideo();
                    while (i < listWaitDeleteVideo.size() && !this.isBreakDelete) {
                        String str2 = listWaitDeleteVideo.get(i);
                        if (FileUtils.delete(str2)) {
                            CacheHotHelp.getInstance().deleteRecord(str2);
                        }
                        i++;
                    }
                } else if (type == 3001) {
                    String filePath2 = scanMessageTake.getFilePath();
                    if (!TextUtils.isEmpty(filePath2)) {
                        CacheHotHelp.getInstance().setAudioAccess(filePath2);
                    }
                } else if (type == 3002) {
                    List<String> listWaitDeleteMusic = CacheHotHelp.getInstance().waitDeleteMusic();
                    while (i < listWaitDeleteMusic.size() && !this.isBreakDelete) {
                        String str3 = listWaitDeleteMusic.get(i);
                        if (FileUtils.delete(str3)) {
                            CacheHotHelp.getInstance().deleteRecord(str3);
                        }
                        i++;
                    }
                }
            } else {
                String filePath3 = scanMessageTake.getFilePath();
                if (!TextUtils.isEmpty(filePath3)) {
                    CacheHotHelp.getInstance().setImageAccess(filePath3);
                }
            }
            this.mPools.release(scanMessageTake);
        }
    }

    public void setImageAccess(String str) {
        if (FileUtils.isFileExists(str)) {
            ScanMessage scanMessageObtainMessage = obtainMessage(1001);
            scanMessageObtainMessage.setFilePath(str);
            this.mTaskQueue.offer(scanMessageObtainMessage);
        }
    }

    public void setVideoAccess(String str) {
        if (FileUtils.isFileExists(str)) {
            ScanMessage scanMessageObtainMessage = obtainMessage(2001);
            scanMessageObtainMessage.setFilePath(str);
            this.mTaskQueue.offer(scanMessageObtainMessage);
        }
    }

    public void setMusicAccess(String str) {
        if (FileUtils.isFileExists(str)) {
            ScanMessage scanMessageObtainMessage = obtainMessage(3001);
            scanMessageObtainMessage.setFilePath(str);
            this.mTaskQueue.offer(scanMessageObtainMessage);
        }
    }

    public void setImageClean() {
        this.isBreakDelete = false;
        this.mTaskQueue.offer(obtainMessage(1002));
    }

    public void setVideoClean() {
        this.isBreakDelete = false;
        this.mTaskQueue.offer(obtainMessage(2002));
    }

    public void setMusicClean() {
        this.isBreakDelete = false;
        this.mTaskQueue.offer(obtainMessage(3002));
    }

    public void setScanStop() {
        this.isBreakDelete = true;
    }

    private ScanMessage obtainMessage(int i) {
        ScanMessage scanMessageAcquire = this.mPools.acquire();
        if (scanMessageAcquire != null) {
            scanMessageAcquire.setType(i);
            return scanMessageAcquire;
        }
        return new ScanMessage(i);
    }

    private static class ScanMessage {
        public static final int MSG_IMAGE_CLEAR = 1002;
        public static final int MSG_IMAGE_UP_DATE = 1001;
        public static final int MSG_MUSIC_CLEAR = 3002;
        public static final int MSG_MUSIC_UP_DATE = 3001;
        public static final int MSG_VIDEO_CLEAR = 2002;
        public static final int MSG_VIDEO_UP_DATE = 2001;
        private String filePath;
        private int type;

        @Retention(RetentionPolicy.SOURCE)
        @interface MessageType {
        }

        public ScanMessage(int i) {
            this.type = i;
        }

        public int getType() {
            return this.type;
        }

        public void setType(int i) {
            this.type = i;
        }

        public String getFilePath() {
            return this.filePath;
        }

        public void setFilePath(String str) {
            this.filePath = str;
        }
    }
}
