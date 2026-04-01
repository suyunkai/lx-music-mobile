package com.wanos.zero;

import android.media.AudioTrack;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import androidx.core.util.Pools;
import androidx.media3.exoplayer.ExoPlayer;
import com.wanos.util.Constant;
import com.wanos.util.NativeMethod;
import com.wanos.zero.ZeroPlayEngineTaskMate;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
public class ZeroPlayEngine extends Thread implements ZeroPlayEngineTaskMate.ITaskCallback {
    private static final int MAX_FRAME_SIZE = 20;
    private static final int STATE_DESTROYED = 107;
    private static final int STATE_DESTROYING = 106;
    public static final int STATE_IDLE = 100;
    public static final int STATE_PAUSED = 103;
    private static final int STATE_PAUSING = 102;
    private static final int STATE_PLAYING = 101;
    private static final int STATE_REPLAYING = 104;
    private static final int STATE_RESEEDING = 105;
    private static final String TAG = "wanos[Zero]-ZeroPlayEngine";
    private final int MSG_DESTROY_SAFE;
    private final int[] baseIndices;
    private final int[] in;
    private final float[][] mAudioPositionHandlerPack;
    private Pools.SynchronizedPool<AudioTrackFrameEntity> mAudioTrackFrameEntityPools;
    private final int mChannelNum;
    private ZeroPlayEngineDataMate mDataMate;
    private IAudioFrameCallback mIAudioFrameCallback;
    private final float[][] mInputData;
    private final Handler mMainHandler;
    private final ArrayBlockingQueue<AudioTrackFrameEntity> mMediaTrack;
    private final float[][] mNowFrame;
    private volatile int mState;
    private final int[] mStopBassIds;
    private ZeroPlayEngineTaskMate mTaskMate;
    private final float[] mid;
    private final float[] outData;
    private final float[] s;
    private final float[] v;
    private final float[] x;
    private final float[] y;
    private final float[] z;

    private String formatStateToString(int i) {
        switch (i) {
            case 100:
                return "闲置状态";
            case 101:
                return "播放状态";
            case 102:
                return "暂停中状态";
            case 103:
                return "暂停状态";
            case 104:
                return "重新播放中状态";
            case 105:
                return "重置中状态";
            case 106:
                return "销毁中状态";
            case 107:
                return "销毁状态";
            default:
                return "UNKNOWN";
        }
    }

    public ZeroPlayEngine() {
        super("ZeroPlayEngine");
        this.MSG_DESTROY_SAFE = 201;
        this.mState = 100;
        this.mNowFrame = (float[][]) Array.newInstance((Class<?>) Float.TYPE, 10, 4);
        int i = 0;
        this.mInputData = (float[][]) Array.newInstance((Class<?>) Float.TYPE, 20, Constant.BUFFERSIZE);
        this.mStopBassIds = new int[]{-1, -1};
        this.x = new float[20];
        this.y = new float[20];
        this.z = new float[20];
        this.v = new float[20];
        this.s = new float[20];
        this.in = new int[20];
        int playerChannelNum = Constant.getPlayerChannelNum();
        this.mChannelNum = playerChannelNum;
        this.mAudioPositionHandlerPack = (float[][]) Array.newInstance((Class<?>) Float.TYPE, playerChannelNum, Constant.BUFFERSIZE);
        this.mid = new float[3];
        this.outData = new float[Constant.BUFFERSIZE * playerChannelNum];
        this.baseIndices = new int[Constant.BUFFERSIZE];
        this.mMainHandler = new Handler(Looper.getMainLooper()) { // from class: com.wanos.zero.ZeroPlayEngine.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (message.what == 201) {
                    ZeroPlayEngine.this.mState = 107;
                }
            }
        };
        this.mMediaTrack = new ArrayBlockingQueue<>(100);
        this.mAudioTrackFrameEntityPools = new Pools.SynchronizedPool<>(100);
        while (true) {
            int[] iArr = this.baseIndices;
            if (i >= iArr.length) {
                return;
            }
            iArr[i] = this.mChannelNum * i;
            i++;
        }
    }

    public int getPlayState() {
        return this.mState;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        super.run();
        this.mTaskMate = new ZeroPlayEngineTaskMate(this);
        this.mDataMate = new ZeroPlayEngineDataMate();
        do {
            this.mDataMate.formatBallPool();
        } while (!this.mTaskMate.onExecuteTask());
    }

    @Override // com.wanos.zero.ZeroPlayEngineTaskMate.ITaskCallback
    public void onInsertTask(BallEntity ballEntity) {
        if (this.mDataMate == null || ballEntity == null) {
            Log.d(TAG, "onInsertTask: ZeroPlayEngineDataMate  = " + this.mDataMate + " BallEntity = " + ballEntity);
        } else if (this.mState == 105) {
            Log.w(TAG, "onInsertTask: 正在重置中调用了添加音源，mState = " + formatStateToString(this.mState));
            this.mTaskMate.setMediaInsert(ballEntity);
        } else {
            this.mDataMate.addToWaitInsertList(ballEntity);
        }
    }

    @Override // com.wanos.zero.ZeroPlayEngineTaskMate.ITaskCallback
    public void onDeleteTask(int i) {
        ZeroPlayEngineDataMate zeroPlayEngineDataMate = this.mDataMate;
        if (zeroPlayEngineDataMate == null) {
            Log.d(TAG, "onInsertTask: ZeroPlayEngineDataMate  = null");
        } else {
            zeroPlayEngineDataMate.setAudioState(i, 201);
            this.mDataMate.setDeleteState(i);
        }
    }

    @Override // com.wanos.zero.ZeroPlayEngineTaskMate.ITaskCallback
    public void onStartPlaying() {
        if (this.mState == 100 || this.mState == 103) {
            Log.i(TAG, "onStartPlaying: mState = " + formatStateToString(this.mState));
            this.mState = 101;
            this.mDataMate.setAudioState(101);
            ZeroAudioTrack.getInstance().onAudioStart();
            setAudioTrackPlayListener();
            return;
        }
        Log.w(TAG, "onStartPlaying: 异常调用，mState = " + formatStateToString(this.mState));
    }

    @Override // com.wanos.zero.ZeroPlayEngineTaskMate.ITaskCallback
    public void onStopPlaying() {
        if (this.mState == 101 || this.mState == 104) {
            Log.i(TAG, "onStopPlaying: mState = " + formatStateToString(this.mState));
            this.mState = 102;
            this.mDataMate.setAudioState(201);
            return;
        }
        Log.w(TAG, "onStopPlaying: 异常调用，mState = " + formatStateToString(this.mState));
    }

    @Override // com.wanos.zero.ZeroPlayEngineTaskMate.ITaskCallback
    public void onReplaying() {
        if (this.mState == 101) {
            Log.i(TAG, "onReplaying: mState = " + formatStateToString(this.mState));
            this.mState = 104;
            this.mDataMate.setAudioState(201);
        } else {
            if (this.mState == 103) {
                Log.i(TAG, "onReplaying: mState = " + formatStateToString(this.mState));
                this.mDataMate.resetProgress();
                this.mTaskMate.setStartMedia();
                return;
            }
            Log.w(TAG, "onReplaying: 异常调用，mState = " + formatStateToString(this.mState));
        }
    }

    @Override // com.wanos.zero.ZeroPlayEngineTaskMate.ITaskCallback
    public void onResetMediaPlayer() {
        ZeroPlayEngineTaskMate zeroPlayEngineTaskMate = this.mTaskMate;
        if (zeroPlayEngineTaskMate != null) {
            zeroPlayEngineTaskMate.removeInsertTask();
        }
        if (this.mDataMate.getBallPool().size() == 0) {
            Log.i(TAG, "onResetMediaPlayer: mState = " + formatStateToString(this.mState) + ", 当前播放器中无音源");
            this.mState = 105;
            return;
        }
        if (this.mState == 101) {
            Log.i(TAG, "onResetMediaPlayer: mState = " + formatStateToString(this.mState));
            this.mState = 105;
            this.mDataMate.setAudioState(201);
            this.mDataMate.setDeleteState();
            return;
        }
        if (this.mState == 102) {
            Log.i(TAG, "onResetMediaPlayer: mState = " + formatStateToString(this.mState));
            this.mState = 105;
            this.mDataMate.setDeleteState();
        } else {
            if (this.mState == 103) {
                Log.i(TAG, "onResetMediaPlayer: mState = " + formatStateToString(this.mState));
                this.mState = 105;
                this.mDataMate.setDeleteState();
                return;
            }
            Log.w(TAG, "onResetMediaPlayer: 异常调用，mState = " + formatStateToString(this.mState));
        }
    }

    @Override // com.wanos.zero.ZeroPlayEngineTaskMate.ITaskCallback
    public void onDestroyMediaPlayer() {
        this.mMainHandler.sendEmptyMessageDelayed(201, ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS);
        if (this.mState == 101) {
            Log.i(TAG, "onDestroyMediaPlayer: mState = " + formatStateToString(this.mState));
            this.mState = 106;
            this.mDataMate.setAudioState(201);
            this.mDataMate.setDeleteState();
            return;
        }
        if (this.mState == 102) {
            Log.i(TAG, "onDestroyMediaPlayer: mState = " + formatStateToString(this.mState));
            this.mState = 106;
            this.mDataMate.setDeleteState();
            return;
        }
        if (this.mState == 103) {
            Log.i(TAG, "onDestroyMediaPlayer: mState = " + formatStateToString(this.mState));
            this.mState = 106;
            this.mDataMate.setDeleteState();
            return;
        }
        if (this.mState == 104) {
            Log.i(TAG, "onDestroyMediaPlayer: mState = " + formatStateToString(this.mState));
            this.mState = 106;
            this.mDataMate.setAudioState(201);
            this.mDataMate.setDeleteState();
            return;
        }
        if (this.mState == 105) {
            Log.i(TAG, "onDestroyMediaPlayer: mState = " + formatStateToString(this.mState));
            this.mState = 106;
        } else if (this.mState == 100) {
            Log.i(TAG, "onDestroyMediaPlayer: mState = " + formatStateToString(this.mState));
            this.mState = 107;
        } else {
            Log.w(TAG, "onDestroyMediaPlayer: 异常调用，mState = " + formatStateToString(this.mState));
        }
    }

    @Override // com.wanos.zero.ZeroPlayEngineTaskMate.ITaskCallback
    public boolean onAudioTrackTask() {
        int i;
        SparseArray<BallEntity> ballPool = this.mDataMate.getBallPool();
        if (this.mDataMate == null) {
            return false;
        }
        if (this.mState == 107 || (this.mState == 106 && ballPool.size() == 0)) {
            Log.i(TAG, "onAudioTrackTask: 播放器状态 = " + formatStateToString(this.mState) + ", 销毁播放器");
            this.mMainHandler.removeMessages(201);
            this.mTaskMate.destroy();
            this.mTaskMate.destroy();
            ZeroAudioTrack.getInstance().onDestroy();
            this.mAudioTrackFrameEntityPools = null;
            return true;
        }
        if (this.mState == 105 && ballPool.size() == 0) {
            this.mState = 100;
        }
        if (this.mState == 104 && this.mDataMate.isFadeOutEnd()) {
            Log.i(TAG, "onAudioTrackTask: 淡出结束，重置播放位置，开始淡入");
            this.mState = 101;
            this.mDataMate.resetProgress();
            this.mDataMate.setAudioState(101);
        }
        if (this.mState == 102 && this.mDataMate.isFadeOutEnd()) {
            this.mState = 103;
            ZeroAudioTrack.getInstance().onAudioStop();
        }
        if (this.mState == 100 || this.mState == 103 || ballPool.size() == 0) {
            if (!this.mTaskMate.isTaskEmpty() || !this.mDataMate.isTaskEmpty()) {
                return false;
            }
            try {
                Log.i(TAG, "onAudioTrackTask: 播放器状态 = " + formatStateToString(this.mState) + ", 播放音源数量 = " + ballPool.size());
                TimeUnit.MILLISECONDS.sleep(200L);
                return false;
            } catch (InterruptedException unused) {
                Log.e(TAG, "onAudioTrackTask: 播放线程异常中断");
                return true;
            }
        }
        AudioTrackFrameEntity audioTrackFrameEntityObtainFrameEntity = obtainFrameEntity();
        ArrayList<BallPosEntity> frameList = audioTrackFrameEntityObtainFrameEntity.getFrameList();
        int[] iArr = this.mStopBassIds;
        iArr[0] = -1;
        iArr[1] = -1;
        int i2 = 0;
        for (int i3 = 0; i3 < ballPool.size(); i3++) {
            BallEntity ballEntityValueAt = ballPool.valueAt(i3);
            if (ballEntityValueAt.getStateMate().isCanDelete()) {
                this.mDataMate.addToWaitDeleteList(ballEntityValueAt.getBallId());
            } else {
                float[] audioBuffer = ballEntityValueAt.getAudioBuffer();
                int iAudioRead = NativeMethod.getInstance().audioRead(String.valueOf(ballEntityValueAt.getBallId()), ballEntityValueAt.getCurrent(), null, audioBuffer);
                if (iAudioRead <= 0) {
                    ballEntityValueAt.setCurrent(0L);
                    audioTrackFrameEntityObtainFrameEntity.setLoopWeekState(ballEntityValueAt.getBallId(), ballEntityValueAt.getLoopMode() == 1002);
                    iAudioRead = NativeMethod.getInstance().audioRead(String.valueOf(ballEntityValueAt.getBallId()), ballEntityValueAt.getCurrent(), null, audioBuffer);
                } else {
                    audioTrackFrameEntityObtainFrameEntity.setLoopWeekState(ballEntityValueAt.getBallId(), false);
                }
                if (iAudioRead <= 0) {
                    this.mDataMate.getBallPool().remove(ballEntityValueAt.getBallId());
                } else {
                    float[][] fArr = formatByte(audioBuffer, ballEntityValueAt);
                    if (2 == ballEntityValueAt.getChannelCount()) {
                        if (i2 < 20 && (i = i2 + 1) < 20) {
                            BallPosEntity threeData = setThreeData(i2, ballEntityValueAt);
                            if (threeData != null) {
                                frameList.add(threeData);
                            }
                            this.mInputData[i2] = fArr[0];
                            this.s[i2] = ballEntityValueAt.getSize();
                            this.in[i2] = ballEntityValueAt.getObjectIdL();
                            this.v[i2] = ballEntityValueAt.getVolumeMate().getVolume();
                            if (-99999 == ballEntityValueAt.getBallId()) {
                                this.mStopBassIds[0] = ballEntityValueAt.getObjectIdL();
                            }
                            this.mInputData[i] = fArr[1];
                            this.s[i] = ballEntityValueAt.getSize();
                            this.in[i] = ballEntityValueAt.getObjectIdR();
                            this.v[i] = ballEntityValueAt.getVolumeMate().getVolume();
                            if (-99999 == ballEntityValueAt.getBallId()) {
                                this.mStopBassIds[1] = ballEntityValueAt.getObjectIdR();
                            }
                            ballEntityValueAt.setCurrent(ballEntityValueAt.getCurrent() + ((long) iAudioRead));
                            i2 += 2;
                        }
                    } else if (1 != ballEntityValueAt.getChannelCount()) {
                        Log.e(TAG, "未知音源的轨道数,音源ID=" + ballEntityValueAt.getBallId() + ",轨道数=" + ballEntityValueAt.getChannelCount());
                    } else if (i2 < 20) {
                        BallPosEntity audioSingleData = setAudioSingleData(i2, ballEntityValueAt, this.mStopBassIds);
                        if (audioSingleData != null) {
                            frameList.add(audioSingleData);
                        }
                        this.mInputData[i2] = fArr[0];
                        ballEntityValueAt.setCurrent(ballEntityValueAt.getCurrent() + ((long) iAudioRead));
                        i2++;
                    }
                }
            }
        }
        this.mMediaTrack.offer(audioTrackFrameEntityObtainFrameEntity);
        NativeMethod.getInstance().doAudioPosHandler(this.mInputData, this.x, this.y, this.z, this.v, this.s, i2, this.in, this.mStopBassIds, this.mAudioPositionHandlerPack);
        for (int i4 = 0; i4 < Constant.BUFFERSIZE; i4++) {
            for (int i5 = 0; i5 < this.mChannelNum; i5++) {
                this.outData[this.baseIndices[i4] + i5] = this.mAudioPositionHandlerPack[i5][i4];
            }
        }
        ZeroAudioTrack.getInstance().onAudioWriteForInt(this.outData);
        return false;
    }

    public void setMediaInsert(BallEntity ballEntity) {
        ZeroPlayEngineTaskMate zeroPlayEngineTaskMate = this.mTaskMate;
        if (zeroPlayEngineTaskMate != null) {
            zeroPlayEngineTaskMate.setMediaInsert(ballEntity);
        }
    }

    public void setMediaDelete(int i) {
        ZeroPlayEngineTaskMate zeroPlayEngineTaskMate = this.mTaskMate;
        if (zeroPlayEngineTaskMate != null) {
            zeroPlayEngineTaskMate.setMediaDelete(i);
        }
    }

    public void setMediaDestroy() {
        ZeroPlayEngineTaskMate zeroPlayEngineTaskMate = this.mTaskMate;
        if (zeroPlayEngineTaskMate != null) {
            zeroPlayEngineTaskMate.setMediaDestroy();
        }
    }

    public void setStartMedia() {
        ZeroPlayEngineTaskMate zeroPlayEngineTaskMate = this.mTaskMate;
        if (zeroPlayEngineTaskMate != null) {
            zeroPlayEngineTaskMate.setStartMedia();
        }
    }

    public void setStopMedia() {
        ZeroPlayEngineTaskMate zeroPlayEngineTaskMate = this.mTaskMate;
        if (zeroPlayEngineTaskMate != null) {
            zeroPlayEngineTaskMate.setStopMedia();
        }
    }

    public void setMediaReset() {
        ZeroPlayEngineTaskMate zeroPlayEngineTaskMate = this.mTaskMate;
        if (zeroPlayEngineTaskMate != null) {
            zeroPlayEngineTaskMate.setMediaReset();
        }
    }

    public void setMediaVolume(int i, float f) {
        ZeroPlayEngineDataMate zeroPlayEngineDataMate = this.mDataMate;
        if (zeroPlayEngineDataMate != null) {
            zeroPlayEngineDataMate.setMediaVolume(i, f);
        }
    }

    public void setMediaModifyPosXY(int i, float f, float f2) {
        ZeroPlayEngineDataMate zeroPlayEngineDataMate = this.mDataMate;
        if (zeroPlayEngineDataMate != null) {
            zeroPlayEngineDataMate.setMediaModifyPosXY(i, f, f2);
        }
    }

    public void setMediaModifyPosZ(int i, float f) {
        ZeroPlayEngineDataMate zeroPlayEngineDataMate = this.mDataMate;
        if (zeroPlayEngineDataMate != null) {
            zeroPlayEngineDataMate.setMediaModifyPosZ(i, f);
        }
    }

    public void setMediaReplay() {
        ZeroPlayEngineTaskMate zeroPlayEngineTaskMate = this.mTaskMate;
        if (zeroPlayEngineTaskMate != null) {
            zeroPlayEngineTaskMate.setMediaReplay();
        }
    }

    public void setMediaFrameCallback(IAudioFrameCallback iAudioFrameCallback) {
        this.mIAudioFrameCallback = iAudioFrameCallback;
    }

    public float[] getMediaBallPos(int i) {
        ZeroPlayEngineDataMate zeroPlayEngineDataMate = this.mDataMate;
        if (zeroPlayEngineDataMate == null) {
            return null;
        }
        return zeroPlayEngineDataMate.getMediaBallPos(i);
    }

    public void setAudioTrackPlayListener() {
        ZeroAudioTrack.getInstance().setAudioTrackFrameListener(new AudioTrack.OnPlaybackPositionUpdateListener() { // from class: com.wanos.zero.ZeroPlayEngine.2
            @Override // android.media.AudioTrack.OnPlaybackPositionUpdateListener
            public void onMarkerReached(AudioTrack audioTrack) {
            }

            @Override // android.media.AudioTrack.OnPlaybackPositionUpdateListener
            public void onPeriodicNotification(AudioTrack audioTrack) {
                AudioTrackFrameEntity audioTrackFrameEntity;
                if (ZeroPlayEngine.this.mMediaTrack.isEmpty() || (audioTrackFrameEntity = (AudioTrackFrameEntity) ZeroPlayEngine.this.mMediaTrack.poll()) == null) {
                    return;
                }
                SparseBooleanArray loopWeek = audioTrackFrameEntity.getLoopWeek();
                if (ZeroPlayEngine.this.mIAudioFrameCallback != null) {
                    for (int i = 0; i < loopWeek.size(); i++) {
                        ZeroPlayEngine.this.mIAudioFrameCallback.onAudioPlayerCompleteState(loopWeek.keyAt(i), loopWeek.valueAt(i));
                    }
                }
                ArrayList<BallPosEntity> frameList = audioTrackFrameEntity.getFrameList();
                if (frameList.isEmpty()) {
                    try {
                        ZeroPlayEngine.this.mAudioTrackFrameEntityPools.release(audioTrackFrameEntity);
                        return;
                    } catch (Exception e) {
                        Log.e(ZeroPlayEngine.TAG, "onPeriodicNotification: " + e.getMessage());
                        return;
                    }
                }
                int i2 = 0;
                for (int i3 = 0; i3 < ZeroPlayEngine.this.mNowFrame.length; i3++) {
                    if (i3 < frameList.size()) {
                        BallPosEntity ballPosEntity = frameList.get(i3);
                        ZeroPlayEngine.this.mNowFrame[i3][0] = ballPosEntity.getBallId();
                        ZeroPlayEngine.this.mNowFrame[i3][1] = ballPosEntity.getX_m();
                        ZeroPlayEngine.this.mNowFrame[i3][2] = ballPosEntity.getY_m();
                        ZeroPlayEngine.this.mNowFrame[i3][3] = ballPosEntity.getZ_m();
                        i2++;
                    }
                }
                if (ZeroPlayEngine.this.mIAudioFrameCallback != null) {
                    ZeroPlayEngine.this.mIAudioFrameCallback.onAudioFrame(ZeroPlayEngine.this.mNowFrame, i2);
                }
                try {
                    ZeroPlayEngine.this.mAudioTrackFrameEntityPools.release(audioTrackFrameEntity);
                } catch (Exception e2) {
                    Log.e(ZeroPlayEngine.TAG, "onPeriodicNotification: " + e2.getMessage());
                }
            }
        });
    }

    private AudioTrackFrameEntity obtainFrameEntity() {
        Pools.SynchronizedPool<AudioTrackFrameEntity> synchronizedPool = this.mAudioTrackFrameEntityPools;
        if (synchronizedPool == null) {
            Log.e(TAG, "obtainFrameEntity: 播放器已释放");
            return new AudioTrackFrameEntity();
        }
        AudioTrackFrameEntity audioTrackFrameEntityAcquire = synchronizedPool.acquire();
        if (audioTrackFrameEntityAcquire == null) {
            return new AudioTrackFrameEntity();
        }
        audioTrackFrameEntityAcquire.getFrameList().clear();
        audioTrackFrameEntityAcquire.getLoopWeek().clear();
        return audioTrackFrameEntityAcquire;
    }

    private float[][] formatByte(float[] fArr, BallEntity ballEntity) {
        float[][] floats = ballEntity.getFloats();
        int i = Constant.BUFFERSIZE;
        int channelCount = ballEntity.getChannelCount();
        if (ballEntity.getStateMate().getState() == 101) {
            ballEntity.getStateMate().setState(102);
        } else if (ballEntity.getStateMate().getState() == 201) {
            ballEntity.getStateMate().setState(202);
        } else if (ballEntity.getStateMate().getState() == 102 && ballEntity.getVolumeMate().isFadeInEnd()) {
            ballEntity.getStateMate().resetCount();
            ballEntity.getStateMate().setState(103);
        } else if (ballEntity.getStateMate().getState() == 202 && ballEntity.getVolumeMate().isFadeOutEnd()) {
            if (ballEntity.getStateMate().getCount() >= 4) {
                ballEntity.getStateMate().setState(203);
            } else {
                ballEntity.getStateMate().addCount();
            }
        }
        fadeHandle(fArr, ballEntity, channelCount, i, floats);
        return floats;
    }

    private void fadeHandle(float[] fArr, BallEntity ballEntity, int i, int i2, float[][] fArr2) {
        int state = ballEntity.getStateMate().getState();
        ZeroVolumeMate volumeMate = ballEntity.getVolumeMate();
        int i3 = 0;
        while (i3 < i) {
            if (state == 103) {
                for (int i4 = 0; i4 < i2; i4++) {
                    fArr2[i3][i4] = fArr[(i4 * i) + i3];
                }
            } else if (state == 203) {
                Arrays.fill(fArr2[i3], 0.0f);
            } else if (state == 102) {
                for (int i5 = 0; i5 < i2; i5++) {
                    fArr2[i3][i5] = fArr[(i5 * i) + i3] * (i3 == 0 ? volumeMate.getFadeInLeftVolume() : volumeMate.getFadeInRightVolume());
                }
            } else if (state == 202) {
                for (int i6 = 0; i6 < i2; i6++) {
                    fArr2[i3][i6] = fArr[(i6 * i) + i3] * (i3 == 0 ? volumeMate.getFadeOutLeftVolume() : volumeMate.getFadeOutRightVolume());
                }
            }
            i3++;
        }
    }

    private BallPosEntity setThreeData(int i, BallEntity ballEntity) {
        float[] modifyPos = ballEntity.getModifyPos();
        if (modifyPos == null) {
            BallPosEntity ballPosEntityFindCurrentPos = ballEntity.findCurrentPos();
            this.x[i] = ballPosEntityFindCurrentPos.getX_l();
            this.y[i] = ballPosEntityFindCurrentPos.getY_l();
            this.z[i] = ballPosEntityFindCurrentPos.getZ_l();
            int i2 = i + 1;
            this.x[i2] = ballPosEntityFindCurrentPos.getX_r();
            this.y[i2] = ballPosEntityFindCurrentPos.getY_r();
            this.z[i2] = ballPosEntityFindCurrentPos.getZ_r();
            return ballPosEntityFindCurrentPos;
        }
        float[] fArr = this.mid;
        fArr[0] = modifyPos[1];
        fArr[1] = modifyPos[2];
        fArr[2] = modifyPos[3];
        float[] fArrStereoPos = NativeMethod.getInstance().stereoPos(this.mid);
        if (fArrStereoPos == null) {
            float[] fArr2 = this.x;
            fArr2[i] = modifyPos[1];
            float[] fArr3 = this.y;
            fArr3[i] = modifyPos[2];
            float[] fArr4 = this.z;
            fArr4[i] = modifyPos[3];
            int i3 = i + 1;
            fArr2[i3] = modifyPos[1];
            fArr3[i3] = modifyPos[2];
            fArr4[i3] = modifyPos[3];
            return null;
        }
        float[] fArr5 = this.x;
        fArr5[i] = fArrStereoPos[3];
        float[] fArr6 = this.y;
        fArr6[i] = fArrStereoPos[4];
        float[] fArr7 = this.z;
        fArr7[i] = fArrStereoPos[5];
        int i4 = i + 1;
        fArr5[i4] = fArrStereoPos[6];
        fArr6[i4] = fArrStereoPos[7];
        fArr7[i4] = fArrStereoPos[8];
        return null;
    }

    private BallPosEntity setAudioSingleData(int i, BallEntity ballEntity, int[] iArr) {
        float[] modifyPos = ballEntity.getModifyPos();
        if (modifyPos == null) {
            BallPosEntity ballPosEntityFindCurrentPos = ballEntity.findCurrentPos();
            this.x[i] = ballPosEntityFindCurrentPos.getX_m();
            this.y[i] = ballPosEntityFindCurrentPos.getY_m();
            this.z[i] = ballPosEntityFindCurrentPos.getZ_m();
            this.v[i] = ballEntity.getVolumeMate().getVolume();
            this.in[i] = ballEntity.getObjectIdL();
            if (-99999 == ballEntity.getBallId()) {
                iArr[0] = ballEntity.getObjectIdL();
                iArr[1] = ballEntity.getObjectIdL();
            }
            return ballPosEntityFindCurrentPos;
        }
        this.x[i] = modifyPos[1];
        this.y[i] = modifyPos[2];
        this.z[i] = modifyPos[3];
        this.v[i] = ballEntity.getVolumeMate().getVolume();
        this.in[i] = ballEntity.getObjectIdL();
        if (-99999 != ballEntity.getBallId()) {
            return null;
        }
        iArr[0] = ballEntity.getObjectIdL();
        iArr[1] = ballEntity.getObjectIdL();
        return null;
    }
}
