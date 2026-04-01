package com.wanos.zero;

import android.util.Log;
import com.wanos.util.Constant;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class BallEntity {
    public static final int LOOP_MODE_INFINITE = 1001;
    public static final int LOOP_MODE_SINGLE = 1002;
    private static final String TAG = "wanos[Zero]-BallEntity";
    private String audioPath;
    private int ballId;
    private ArrayList<BallPosEntity> ballPos;
    private int channelCount;
    private long current;
    private float[][] floats;
    private float[] mAudioBuffer;
    private float[] mModifyPos;
    private int mObjectIdL;
    private int mObjectIdR;
    private float size;
    private int loopMode = 1001;
    private final ZeroVolumeMate mZeroVolumeMate = new ZeroVolumeMate();
    private final ZeroStateMate mZeroStateMate = new ZeroStateMate();

    @Retention(RetentionPolicy.SOURCE)
    @interface BallLoopMode {
    }

    float[] getAudioBuffer() {
        return this.mAudioBuffer;
    }

    ZeroStateMate getStateMate() {
        return this.mZeroStateMate;
    }

    ZeroVolumeMate getVolumeMate() {
        return this.mZeroVolumeMate;
    }

    boolean isFadeOutEnd() {
        return this.mZeroVolumeMate.isFadeOutEnd();
    }

    boolean isFadeInEnd() {
        return this.mZeroVolumeMate.isFadeInEnd();
    }

    int getLoopMode() {
        return this.loopMode;
    }

    public void setLoopMode(int i) {
        this.loopMode = i;
    }

    public int getBallId() {
        return this.ballId;
    }

    public void setBallId(int i) {
        this.ballId = i;
    }

    public int getChannelCount() {
        return this.channelCount;
    }

    public void setChannelCount(int i) {
        this.floats = (float[][]) Array.newInstance((Class<?>) Float.TYPE, i, Constant.BUFFERSIZE);
        this.channelCount = i;
        this.mAudioBuffer = new float[Constant.BUFFERSIZE * i];
    }

    void initObjectId() {
        int i = this.channelCount;
        if (i == 1) {
            int id = ObjectIdsManage.getInstance().getId();
            this.mObjectIdR = id;
            this.mObjectIdL = id;
        } else if (i == 2) {
            this.mObjectIdL = ObjectIdsManage.getInstance().getId();
            this.mObjectIdR = ObjectIdsManage.getInstance().getId();
        }
        Log.d(TAG, "setChannelCount: mObjectIdL = " + this.mObjectIdL + ", mObjectIdR = " + this.mObjectIdR);
    }

    int getObjectIdL() {
        return this.mObjectIdL;
    }

    int getObjectIdR() {
        return this.mObjectIdR;
    }

    void releaseObjectId() {
        ObjectIdsManage.getInstance().releaseId(this.mObjectIdL);
        ObjectIdsManage.getInstance().releaseId(this.mObjectIdR);
    }

    float[][] getFloats() {
        for (float[] fArr : this.floats) {
            Arrays.fill(fArr, 0.0f);
        }
        return this.floats;
    }

    public String getAudioPath() {
        return this.audioPath;
    }

    public void setAudioPath(String str) {
        this.audioPath = str;
    }

    public void setBallPos(ArrayList<BallPosEntity> arrayList) {
        this.ballPos = arrayList;
    }

    long getCurrent() {
        return this.current;
    }

    void setCurrent(long j) {
        if (j == 0 && this.mZeroStateMate.getState() == 103) {
            this.mZeroVolumeMate.resetFadeInState();
            this.mZeroStateMate.setState(101);
        }
        this.current = j;
    }

    public void setVolume(float f) {
        this.mZeroVolumeMate.setVolume(f);
    }

    public float getSize() {
        return this.size;
    }

    public void setSize(float f) {
        this.size = f;
    }

    void setModifyXY(float f, float f2) {
        float[] fArr = this.mModifyPos;
        if (fArr == null) {
            BallPosEntity ballPosEntityFindCurrentPos = findCurrentPos();
            float[] fArr2 = new float[4];
            this.mModifyPos = fArr2;
            fArr2[0] = getBallId();
            float[] fArr3 = this.mModifyPos;
            fArr3[1] = f;
            fArr3[2] = f2;
            fArr3[3] = ballPosEntityFindCurrentPos.getZ_m();
            return;
        }
        fArr[1] = f;
        fArr[2] = f2;
    }

    void setModifyZ(float f) {
        if (this.mModifyPos == null) {
            BallPosEntity ballPosEntityFindCurrentPos = findCurrentPos();
            float[] fArr = new float[4];
            this.mModifyPos = fArr;
            fArr[0] = getBallId();
            this.mModifyPos[1] = ballPosEntityFindCurrentPos.getX_m();
            this.mModifyPos[2] = ballPosEntityFindCurrentPos.getY_m();
        }
        this.mModifyPos[3] = f;
    }

    float[] getModifyPos() {
        return this.mModifyPos;
    }

    BallPosEntity findCurrentPos() {
        ArrayList<BallPosEntity> arrayList = this.ballPos;
        if (arrayList == null || arrayList.isEmpty()) {
            Log.e(TAG, "findCurrentPos: 该音源未设置轨迹信息，音源ID=" + getBallId());
            return new BallPosEntity(getBallId());
        }
        long current = getCurrent();
        if (this.ballPos.size() == 1) {
            return this.ballPos.get(0);
        }
        BallPosEntity ballPosEntity = this.ballPos.get(0);
        if (current <= ballPosEntity.getSeek()) {
            return ballPosEntity;
        }
        ArrayList<BallPosEntity> arrayList2 = this.ballPos;
        BallPosEntity ballPosEntity2 = arrayList2.get(arrayList2.size() - 1);
        if (current >= ballPosEntity2.getSeek()) {
            return ballPosEntity2;
        }
        for (int i = 1; i < this.ballPos.size(); i++) {
            BallPosEntity ballPosEntity3 = this.ballPos.get(i - 1);
            BallPosEntity ballPosEntity4 = this.ballPos.get(i);
            if (current >= ballPosEntity3.getSeek() && current <= ballPosEntity4.getSeek()) {
                return ballPosEntity3;
            }
        }
        StringBuilder sbAppend = new StringBuilder("findCurrentPos: 异常轨迹，音源ID=").append(getBallId()).append(", 当前查询Seek=").append(current).append(", 当前轨迹最后一条Seek=");
        ArrayList<BallPosEntity> arrayList3 = this.ballPos;
        Log.d(TAG, sbAppend.append(arrayList3.get(arrayList3.size() - 1).getSeek()).toString());
        ArrayList<BallPosEntity> arrayList4 = this.ballPos;
        return arrayList4.get(arrayList4.size() - 1);
    }
}
