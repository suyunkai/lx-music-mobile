package com.wanos.play;

import android.util.Log;
import android.util.SparseArray;
import com.wanos.util.Constant;
import com.wanos.util.EditingUtils;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class AudioFileMate {
    private static final int CACHE_SIZE = 30;
    private static final String TAG = "AudioFileMate";
    private static final SparseArray<AudioFileMate> sAudioFileMate = new SparseArray<>();
    private RandomAccessFile accessFile;
    private ByteBuffer byteBuffer;
    private FileChannel fileChannel;
    private boolean isRelease = false;
    private final int mBallId;
    private byte[] mCacheByte;

    public AudioFileMate(int i) {
        this.mBallId = i;
    }

    public static AudioFileMate getInstance(int i) {
        SparseArray<AudioFileMate> sparseArray = sAudioFileMate;
        AudioFileMate audioFileMate = sparseArray.get(i);
        if (audioFileMate != null) {
            return audioFileMate;
        }
        AudioFileMate audioFileMate2 = new AudioFileMate(i);
        sparseArray.put(i, audioFileMate2);
        return audioFileMate2;
    }

    public void setAudioFilePath(String str, int i) {
        if (this.accessFile == null) {
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(str, "r");
                this.accessFile = randomAccessFile;
                this.fileChannel = randomAccessFile.getChannel();
                if (i == 2) {
                    this.byteBuffer = ByteBuffer.allocate(Constant.BUFFERSIZE * EditingUtils.sizeOfShort * i * 30);
                } else {
                    this.byteBuffer = ByteBuffer.allocate(Constant.BUFFERSIZE * EditingUtils.sizeOfShort * i * 30 * 2);
                }
                this.mCacheByte = new byte[Constant.BUFFERSIZE * EditingUtils.sizeOfShort * i];
                Log.d(TAG, "setAudioFilePath: 设置音频路径");
            } catch (FileNotFoundException e) {
                Log.d(TAG, "setAudioFilePath: 资源加载错误,错误信息 = " + e.getMessage());
            }
        }
    }

    private byte[] readBuffer() {
        Arrays.fill(this.mCacheByte, (byte) 0);
        this.byteBuffer.get(this.mCacheByte);
        return this.mCacheByte;
    }

    public byte[] getBallPcm(long j) {
        if (this.isRelease || this.accessFile == null) {
            return null;
        }
        if (!this.byteBuffer.hasRemaining()) {
            try {
                this.byteBuffer.clear();
                this.accessFile.seek(j);
                if (this.fileChannel.read(this.byteBuffer) == -1) {
                    return null;
                }
                this.byteBuffer.flip();
            } catch (Exception e) {
                Log.e(TAG, "getData: 音源文件读取错误: " + e.getMessage());
            }
        }
        if (this.byteBuffer.hasRemaining()) {
            return readBuffer();
        }
        return null;
    }

    public void releaseBall() {
        SparseArray<AudioFileMate> sparseArray;
        StringBuilder sb;
        this.isRelease = true;
        try {
            try {
                FileChannel fileChannel = this.fileChannel;
                if (fileChannel != null) {
                    fileChannel.close();
                }
                RandomAccessFile randomAccessFile = this.accessFile;
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                ByteBuffer byteBuffer = this.byteBuffer;
                if (byteBuffer != null) {
                    byteBuffer.clear();
                }
                this.accessFile = null;
                this.byteBuffer = null;
                this.mCacheByte = null;
                sparseArray = sAudioFileMate;
                sparseArray.remove(this.mBallId);
                sb = new StringBuilder("释放音源文件: ");
            } catch (IOException e) {
                Log.e(TAG, "releaseBall: " + e.getMessage());
                this.accessFile = null;
                this.byteBuffer = null;
                this.mCacheByte = null;
                sparseArray = sAudioFileMate;
                sparseArray.remove(this.mBallId);
                sb = new StringBuilder("释放音源文件: ");
            }
            Log.d(TAG, sb.append(sparseArray.size()).toString());
        } catch (Throwable th) {
            this.accessFile = null;
            this.byteBuffer = null;
            this.mCacheByte = null;
            SparseArray<AudioFileMate> sparseArray2 = sAudioFileMate;
            sparseArray2.remove(this.mBallId);
            Log.d(TAG, "释放音源文件: " + sparseArray2.size());
            throw th;
        }
    }
}
