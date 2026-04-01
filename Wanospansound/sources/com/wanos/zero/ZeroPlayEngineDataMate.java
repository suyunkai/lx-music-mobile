package com.wanos.zero;

import android.util.Log;
import android.util.SparseArray;
import com.blankj.utilcode.util.ThreadUtils;
import com.wanos.util.NativeMethod;
import java.util.ArrayList;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes3.dex */
class ZeroPlayEngineDataMate {
    private static final int MAX_BALL_NUM = 10;
    private static final String TAG = "wanos[Zero]-ZeroPlayEngineDataMate";
    private final SparseArray<BallEntity> mBallPool = new SparseArray<>();
    private final ArrayList<BallEntity> mWaitInsertList = new ArrayList<>();
    private final ArrayList<Integer> mWaitDeleteList = new ArrayList<>();

    ZeroPlayEngineDataMate() {
    }

    public void destroy() {
        Log.d(TAG, "destroy: WaitInsertList = " + this.mWaitInsertList.size() + ", WaitDeleteList = " + this.mWaitDeleteList.size() + ", BallPool = " + this.mBallPool.size());
    }

    public void formatBallPool() {
        if (!this.mWaitDeleteList.isEmpty()) {
            this.mWaitDeleteList.removeIf(new Predicate() { // from class: com.wanos.zero.ZeroPlayEngineDataMate$$ExternalSyntheticLambda0
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return this.f$0.deleteBall(((Integer) obj).intValue());
                }
            });
        }
        if (this.mWaitInsertList.isEmpty()) {
            return;
        }
        this.mWaitInsertList.removeIf(new Predicate() { // from class: com.wanos.zero.ZeroPlayEngineDataMate$$ExternalSyntheticLambda1
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f$0.insertBall((BallEntity) obj);
            }
        });
    }

    public boolean isTaskEmpty() {
        return this.mWaitInsertList.isEmpty() && this.mWaitDeleteList.isEmpty();
    }

    public boolean addToWaitDeleteList(int i) {
        if (this.mWaitDeleteList.contains(Integer.valueOf(i))) {
            Log.e(TAG, "addToWaitDeleteList: 该音源已添加到待删除集合，音源ID = " + i);
            return false;
        }
        return this.mWaitDeleteList.add(Integer.valueOf(i));
    }

    public boolean addToWaitInsertList(BallEntity ballEntity) {
        if (ballEntity == null) {
            Log.e(TAG, "addToWaitInsertList: 添加到待插入集合错误，entity == NULL");
            return false;
        }
        for (int i = 0; i < this.mWaitInsertList.size(); i++) {
            if (this.mWaitInsertList.get(i).getBallId() == ballEntity.getBallId()) {
                Log.e(TAG, "addToWaitInsertList: 添加到待插入集合错误，集合已包含该音源，音源ID = " + ballEntity.getBallId());
                return false;
            }
        }
        return this.mWaitInsertList.add(ballEntity);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean insertBall(final BallEntity ballEntity) {
        if (ballEntity == null) {
            Log.e(TAG, "insertBall: 添加音源错误，entity == null");
            return false;
        }
        if (this.mBallPool.size() > 10) {
            Log.e(TAG, "insertBall: 添加音源错误，播放器中音源数量已达最大限制");
            return false;
        }
        if (isContains(ballEntity)) {
            Log.e(TAG, "insertBall: 添加音源错误，播放器中已存在该音源，音源ID = " + ballEntity.getBallId());
            return false;
        }
        ThreadUtils.executeByCpu(new ThreadUtils.SimpleTask<BallEntity>() { // from class: com.wanos.zero.ZeroPlayEngineDataMate.1
            @Override // com.blankj.utilcode.util.ThreadUtils.Task
            public BallEntity doInBackground() throws Throwable {
                int iAudioOpen = NativeMethod.getInstance().audioOpen(ballEntity.getAudioPath(), String.valueOf(ballEntity.getBallId()));
                if (iAudioOpen != 0) {
                    Log.e(ZeroPlayEngineDataMate.TAG, "insertBall: 添加音源错误，解码异常，错误码 = " + iAudioOpen);
                    return null;
                }
                return ballEntity;
            }

            @Override // com.blankj.utilcode.util.ThreadUtils.Task
            public void onSuccess(BallEntity ballEntity2) {
                if (ballEntity2 != null) {
                    Log.i(ZeroPlayEngineDataMate.TAG, "insertBall: 音源添加 --> 播放集合。添加音源 = " + ballEntity2.getBallId());
                    ballEntity.initObjectId();
                    ZeroPlayEngineDataMate.this.mBallPool.put(ballEntity.getBallId(), ballEntity);
                }
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean deleteBall(int i) {
        BallEntity ballEntity = this.mBallPool.get(i, null);
        if (ballEntity == null) {
            Log.e(TAG, "deleteBall: 删除音源错误，播放器未包含该音源，音源ID = " + i);
            return false;
        }
        ballEntity.releaseObjectId();
        int iAudioClose = NativeMethod.getInstance().audioClose(String.valueOf(i));
        if (iAudioClose != 0) {
            Log.e(TAG, "deleteBall: 删除音源错误，关闭异常，错误码 = " + iAudioClose);
            return false;
        }
        this.mBallPool.remove(i);
        Log.i(TAG, "deleteBall: 音源删除 --> 播放集合。删除ID = " + i);
        return true;
    }

    public boolean setAudioState(int i) {
        for (int i2 = 0; i2 < this.mBallPool.size(); i2++) {
            this.mBallPool.valueAt(i2).getStateMate().setState(i);
        }
        return true;
    }

    public void resetProgress() {
        for (int i = 0; i < this.mBallPool.size(); i++) {
            this.mBallPool.valueAt(i).setCurrent(0L);
        }
    }

    public boolean setAudioState(int i, int i2) {
        BallEntity ballEntity = this.mBallPool.get(i, null);
        if (ballEntity == null) {
            Log.e(TAG, "setAudioState: 设置音源状态错误，播放器未包含该音源，音源ID = " + i);
            return false;
        }
        ballEntity.getStateMate().setState(i2);
        return true;
    }

    public boolean setDeleteState(int i) {
        BallEntity ballEntity = this.mBallPool.get(i, null);
        if (ballEntity == null) {
            Log.e(TAG, "setDeleteState: 设置音源状态错误，播放器未包含该音源，音源ID = " + i);
            return false;
        }
        ballEntity.getStateMate().setDeleteState();
        return true;
    }

    public void setDeleteState() {
        for (int i = 0; i < this.mBallPool.size(); i++) {
            this.mBallPool.valueAt(i).getStateMate().setDeleteState();
        }
    }

    public boolean isDeleteState() {
        for (int i = 0; i < this.mBallPool.size(); i++) {
            if (!this.mBallPool.valueAt(i).getStateMate().isDeleteState()) {
                return false;
            }
        }
        return true;
    }

    public SparseArray<BallEntity> getBallPool() {
        return this.mBallPool;
    }

    public boolean isFadeInEnd() {
        for (int i = 0; i < this.mBallPool.size(); i++) {
            if (this.mBallPool.valueAt(i).getStateMate().getState() != 103) {
                return false;
            }
        }
        return true;
    }

    public boolean isFadeOutEnd() {
        for (int i = 0; i < this.mBallPool.size(); i++) {
            if (this.mBallPool.valueAt(i).getStateMate().getState() != 203) {
                return false;
            }
        }
        return true;
    }

    public void setMediaVolume(int i, float f) {
        BallEntity ballEntity = this.mBallPool.get(i);
        if (ballEntity == null) {
            Log.e(TAG, "setMediaVolume: 播放器未包含此音源，BallEntity == NULL");
        } else {
            ballEntity.getVolumeMate().setVolume(f);
        }
    }

    public void setMediaModifyPosXY(int i, float f, float f2) {
        BallEntity ballEntity = this.mBallPool.get(i);
        if (ballEntity == null) {
            return;
        }
        ballEntity.setModifyXY(f, f2);
    }

    public void setMediaModifyPosZ(int i, float f) {
        BallEntity ballEntity = this.mBallPool.get(i);
        if (ballEntity == null) {
            Log.e(TAG, "setMediaVolume: 播放器未包含此音源，BallEntity == NULL");
        } else {
            ballEntity.setModifyZ(f);
        }
    }

    public float[] getMediaBallPos(int i) {
        float[] modifyPos;
        BallEntity ballEntity = this.mBallPool.get(i);
        if (ballEntity == null || (modifyPos = ballEntity.getModifyPos()) == null) {
            return null;
        }
        if (ballEntity.getChannelCount() == 1) {
            return new float[]{modifyPos[1], modifyPos[2], modifyPos[3]};
        }
        if (ballEntity.getChannelCount() == 2) {
            float[] fArrStereoPos = NativeMethod.getInstance().stereoPos(new float[]{modifyPos[1], modifyPos[2], modifyPos[3]});
            return new float[]{modifyPos[1], modifyPos[2], modifyPos[3], fArrStereoPos[3], fArrStereoPos[4], fArrStereoPos[5], fArrStereoPos[6], fArrStereoPos[7], fArrStereoPos[8]};
        }
        Log.e(TAG, "getMediaBallPos: 异常声道数");
        return null;
    }

    private boolean isContains(BallEntity ballEntity) {
        return this.mBallPool.indexOfKey(ballEntity.getBallId()) >= 0;
    }
}
