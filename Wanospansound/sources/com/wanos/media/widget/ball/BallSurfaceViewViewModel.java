package com.wanos.media.widget.ball;

import android.content.res.AssetManager;
import android.graphics.Rect;
import android.util.Log;
import android.util.SparseArray;
import androidx.lifecycle.ViewModel;
import com.blankj.utilcode.util.ThreadUtils;
import com.wanos.media.entity.AudioInfoEntity;
import com.wanos.media.entity.BallCollectEntity;
import com.wanos.media.entity.VolumeModifyEntity;
import com.wanos.media.entity.ZeroBallInfoEntity;
import com.wanos.media.util.PictureCacheUtils;
import com.wanos.media.util.SyncSparseArray;
import com.wanos.media.util.ZeroLogcatTools;
import com.wanos.media.widget.ball.ZeroBallMate;
import com.wanos.media.widget.sound.BallMoveWay;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes3.dex */
public class BallSurfaceViewViewModel extends ViewModel implements ZeroBallMate.IOpenGlBallChangeListener {
    private static final String TAG = "BallSurfaceViewViewMode";
    private boolean isCreateOpenGL;
    private final SparseArray<ZeroBallInfoEntity> mBallInfoList = new SparseArray<>();
    private final SyncSparseArray<BallMoveHelp> mBallMoveHelps = new SyncSparseArray<>();
    private int mEditBallId;
    private IBallCallback mIBallCallback;
    private final BallNativeMate mNative;
    private BallMoveHelp mNowMate;
    private Rect mScreenRect;
    private final Timer mTimer;
    private final ZeroBallMate mZeroBallMateWork;

    public BallSurfaceViewViewModel() {
        Timer timer = new Timer();
        this.mTimer = timer;
        this.mNowMate = null;
        this.mEditBallId = -1;
        this.mScreenRect = new Rect();
        this.isCreateOpenGL = false;
        this.mNative = new BallNativeMate();
        ZeroBallMate zeroBallMate = new ZeroBallMate(this);
        this.mZeroBallMateWork = zeroBallMate;
        zeroBallMate.start();
        timer.schedule(new TimerTask() { // from class: com.wanos.media.widget.ball.BallSurfaceViewViewModel.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                for (int i = 0; i < BallSurfaceViewViewModel.this.mBallMoveHelps.size(); i++) {
                    ((BallMoveHelp) BallSurfaceViewViewModel.this.mBallMoveHelps.valueAt(i)).getBallTrackEngine().onDrawFrame();
                }
                if (BallSurfaceViewViewModel.this.mIBallCallback == null || !BallSurfaceViewViewModel.this.isCreateOpenGL) {
                    return;
                }
                BallSurfaceViewViewModel.this.mIBallCallback.onRequestRender();
            }
        }, 0L, 32L);
    }

    public void setIBallCallback(IBallCallback iBallCallback) {
        this.mIBallCallback = iBallCallback;
    }

    public ZeroBallMate getBallMateWork() {
        return this.mZeroBallMateWork;
    }

    public void setNowMate(BallMoveHelp ballMoveHelp) {
        this.mNowMate = ballMoveHelp;
    }

    public BallMoveHelp getNowMate() {
        return this.mNowMate;
    }

    public void setEditBallId(int i) {
        this.mEditBallId = i;
    }

    public int getEditBallId() {
        return this.mEditBallId;
    }

    public void resetViewModelData() {
        this.mBallInfoList.clear();
        this.mBallMoveHelps.clear();
    }

    public void insertBallInfo(final AudioInfoEntity audioInfoEntity) {
        ThreadUtils.runOnUiThread(new Runnable() { // from class: com.wanos.media.widget.ball.BallSurfaceViewViewModel$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m610x9be04a05(audioInfoEntity);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$insertBallInfo$0$com-wanos-media-widget-ball-BallSurfaceViewViewModel, reason: not valid java name */
    /* synthetic */ void m610x9be04a05(AudioInfoEntity audioInfoEntity) {
        if (audioInfoEntity == null) {
            ZeroLogcatTools.e(TAG, "insertBallInfo: 插入音源信息错误，entity == NULL");
        } else {
            if (contains(this.mBallInfoList, audioInfoEntity.getPlayId())) {
                return;
            }
            this.mBallInfoList.put(audioInfoEntity.getPlayId(), new ZeroBallInfoEntity(audioInfoEntity));
            ZeroLogcatTools.d(TAG, "insertBallInfo: 插入音源信息, 当前音源数量 = " + this.mBallMoveHelps.size());
        }
    }

    public void insertBallMoveHelp(final ZeroBallMate.OpenGlEntity openGlEntity) {
        ThreadUtils.runOnUiThread(new Runnable() { // from class: com.wanos.media.widget.ball.BallSurfaceViewViewModel$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m611x91f95ca0(openGlEntity);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$insertBallMoveHelp$1$com-wanos-media-widget-ball-BallSurfaceViewViewModel, reason: not valid java name */
    /* synthetic */ void m611x91f95ca0(ZeroBallMate.OpenGlEntity openGlEntity) {
        if (contains(this.mBallMoveHelps, openGlEntity.getBallId())) {
            return;
        }
        BallMoveHelp ballMoveHelp = new BallMoveHelp(openGlEntity.getBallId(), this.mScreenRect, this.mIBallCallback);
        ballMoveHelp.setBallTranslationZ(openGlEntity.getAudioCz(), BallMoveWay.WAY_INIT);
        ballMoveHelp.setBallTranslationXY(ballMoveHelp.getScreenPositionX(openGlEntity.getAudioCx()), ballMoveHelp.getScreenPositionY(openGlEntity.getAudioCy()), BallMoveWay.WAY_INIT);
        this.mBallMoveHelps.put(openGlEntity.getBallId(), ballMoveHelp);
        ZeroLogcatTools.d(TAG, "insertBallMoveHelp: 插入音源MoveHelp, 当前Help数量 = " + this.mBallMoveHelps.size());
    }

    public void deleteBall(final int i) {
        ThreadUtils.runOnUiThread(new Runnable() { // from class: com.wanos.media.widget.ball.BallSurfaceViewViewModel$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m609x988a2b1f(i);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$deleteBall$2$com-wanos-media-widget-ball-BallSurfaceViewViewModel, reason: not valid java name */
    /* synthetic */ void m609x988a2b1f(int i) {
        if (!contains(this.mBallInfoList, i)) {
            ZeroLogcatTools.e(TAG, "deleteBall: 删除音源信息错误，集合未包含该音源，音源ID = " + i);
            return;
        }
        this.mBallInfoList.remove(i);
        ZeroLogcatTools.d(TAG, "deleteBall: 删除音源信息, 当前音源数量 = " + this.mBallInfoList.size());
        if (!contains(this.mBallMoveHelps, i)) {
            ZeroLogcatTools.e(TAG, "deleteBall: 删除音源MoveHelp错误，集合未包含该音源，音源ID = " + i);
        }
        this.mBallMoveHelps.remove(i);
        ZeroLogcatTools.d(TAG, "deleteBall: 删除音源MoveHelp, 当前音源数量 = " + this.mBallMoveHelps.size());
    }

    ArrayList<BallCollectEntity> getBallCollectListEntity() {
        ArrayList<BallCollectEntity> arrayList = new ArrayList<>();
        for (int i = 0; i < this.mBallMoveHelps.size(); i++) {
            BallMoveHelp ballMoveHelpValueAt = this.mBallMoveHelps.valueAt(i);
            BallCollectEntity ballCollectEntity = ballMoveHelpValueAt.getBallCollectEntity(findBallInfo(ballMoveHelpValueAt.getBallId()));
            if (ballCollectEntity != null) {
                arrayList.add(ballCollectEntity);
            }
        }
        return arrayList;
    }

    boolean isEditSceneBoll(List<Long> list) {
        boolean z;
        for (int i = 0; i < this.mBallMoveHelps.size(); i++) {
            if (this.mBallMoveHelps.valueAt(i).isDownBall()) {
                return true;
            }
            int i2 = 0;
            while (true) {
                if (i2 >= list.size()) {
                    z = false;
                    break;
                }
                if (r2.getBallId() == list.get(i2).longValue()) {
                    z = true;
                    break;
                }
                i2++;
            }
            if (!z) {
                ZeroLogcatTools.d(TAG, "isEditSceneBall: 场景音源发生变化，变化类型：对音源进行了替换操作");
                return true;
            }
        }
        return false;
    }

    public void onDestroyOpenGl() {
        this.isCreateOpenGL = false;
        this.mNative.onDestroyView();
    }

    public BallMoveHelp findBallMoveHelp(int i) {
        return this.mBallMoveHelps.get(i, null);
    }

    public ZeroBallInfoEntity findBallInfo(int i) {
        return this.mBallInfoList.get(i, null);
    }

    public void setBallVolume(final int i, final float f) {
        ThreadUtils.runOnUiThread(new Runnable() { // from class: com.wanos.media.widget.ball.BallSurfaceViewViewModel$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m612x8435d147(i, f);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$setBallVolume$3$com-wanos-media-widget-ball-BallSurfaceViewViewModel, reason: not valid java name */
    /* synthetic */ void m612x8435d147(int i, float f) {
        ZeroBallInfoEntity zeroBallInfoEntity = this.mBallInfoList.get(i);
        if (zeroBallInfoEntity != null) {
            zeroBallInfoEntity.setAudioVolume(f);
        } else {
            ZeroLogcatTools.e(TAG, "setBallVolume: 设置音源信息错误，集合未包含该音源，音源ID = " + i);
        }
        BallMoveHelp ballMoveHelp = this.mBallMoveHelps.get(i);
        if (ballMoveHelp != null) {
            ballMoveHelp.setModifyVolumeState();
        } else {
            ZeroLogcatTools.e(TAG, "setBallVolume: 设置音源MoveHelp错误，集合未包含该音源，音源ID = " + i);
        }
    }

    public void onSurfaceCreated(AssetManager assetManager) {
        this.mNative.onSurfaceCreated(assetManager);
        String defaultLocalImage = PictureCacheUtils.getDefaultLocalImage(ZeroBallMate.DEFAULT_BREATHE_SRC);
        String defaultLocalImage2 = PictureCacheUtils.getDefaultLocalImage(ZeroBallMate.DEFAULT_SELF_SRC);
        this.mNative.setDefaultDrawable(defaultLocalImage, defaultLocalImage2, "");
        Log.d(TAG, "onSurfaceCreated: " + this.isCreateOpenGL);
        this.isCreateOpenGL = true;
        Log.d(TAG, "onSurfaceCreated: mBreathePath = " + defaultLocalImage + ", mViewPath = " + defaultLocalImage2);
    }

    public void onSurfaceChanged(Rect rect, int i, int i2) {
        this.mScreenRect = rect;
        this.mNative.onSurfaceChanged(rect.width(), rect.height(), i2, i);
    }

    public void onDrawFrame() {
        this.mNative.onDrawFrame();
        ZeroBallMate.OpenGlEntity openGlEntityPoll = this.mZeroBallMateWork.getQueue().poll();
        if (openGlEntityPoll != null) {
            if (openGlEntityPoll.getType() == 101) {
                if (contains(this.mBallMoveHelps, openGlEntityPoll.getBallId())) {
                    BallMoveHelp ballMoveHelp = this.mBallMoveHelps.get(openGlEntityPoll.getBallId());
                    if (openGlEntityPoll.getBallId() == 146) {
                        ZeroLogcatTools.i(TAG, "Z = " + ballMoveHelp.getCurrentZ());
                    }
                    this.mNative.insertBall(openGlEntityPoll.getBallId(), openGlEntityPoll.getIconLocalPath(), openGlEntityPoll.getBallR(), openGlEntityPoll.getBallG(), openGlEntityPoll.getBallB(), openGlEntityPoll.getBallA(), ballMoveHelp.getCurrentX(), ballMoveHelp.getCurrentY(), ballMoveHelp.getCurrentZ());
                    setBallPositionXY(openGlEntityPoll.getBallId(), ballMoveHelp.getCurrentX(), ballMoveHelp.getCurrentY());
                    setBallPositionZ(openGlEntityPoll.getBallId(), ballMoveHelp.getCurrentZ());
                    return;
                }
                Log.d(TAG, "onDrawFrame: 正常添加");
                if (this.mNative.insertBall(openGlEntityPoll.getBallId(), openGlEntityPoll.getIconLocalPath(), openGlEntityPoll.getBallR(), openGlEntityPoll.getBallG(), openGlEntityPoll.getBallB(), openGlEntityPoll.getBallA(), openGlEntityPoll.getAudioCx(), openGlEntityPoll.getAudioCy(), openGlEntityPoll.getAudioCz())) {
                    insertBallInfo(openGlEntityPoll.getEntity());
                    insertBallMoveHelp(openGlEntityPoll);
                    return;
                }
                return;
            }
            if (openGlEntityPoll.getType() == 102) {
                this.mNative.deleteBall(openGlEntityPoll.getBallId());
                deleteBall(openGlEntityPoll.getBallId());
            }
        }
    }

    public int onViewTouchDown(float f, float f2) {
        return this.mNative.onViewTouchDown(f, f2);
    }

    public void setPositionFlagState(boolean z) {
        this.mNative.setPositionFlagState(z);
    }

    public void setBallPositionXY(int i, float f, float f2) {
        this.mNative.setBallPositionXY(i, f, f2);
    }

    public void setBallPositionZ(int i, float f) {
        this.mNative.setBallPositionZ(i, f);
    }

    public float[] findBallPosition(int i) {
        return this.mNative.findBallPosition(i);
    }

    public void setBackgroundImage(String str) {
        this.mNative.setBackgroundImage(str);
    }

    public ArrayList<VolumeModifyEntity> getAudioVolumeInfo() {
        ArrayList<VolumeModifyEntity> arrayList = new ArrayList<>();
        for (int i = 0; i < this.mBallInfoList.size(); i++) {
            arrayList.add(this.mBallInfoList.valueAt(i).getVolumeModifyEntity());
        }
        return arrayList;
    }

    public ZeroBallInfoEntity getEditBallInfoEntity() {
        return getBallInfoEntity(this.mEditBallId);
    }

    public ZeroBallInfoEntity getBallInfoEntity(int i) {
        return this.mBallInfoList.get(i);
    }

    public int getBallInfoSize() {
        return this.mBallInfoList.size();
    }

    private boolean contains(SparseArray<?> sparseArray, int i) {
        return sparseArray != null && sparseArray.indexOfKey(i) >= 0;
    }

    @Override // com.wanos.media.widget.ball.ZeroBallMate.IOpenGlBallChangeListener
    public void onOpenGlInsertInfoMate(AudioInfoEntity audioInfoEntity) {
        insertBallInfo(audioInfoEntity);
    }

    @Override // com.wanos.media.widget.ball.ZeroBallMate.IOpenGlBallChangeListener
    public void onOpenGlInsertMoveHelp(ZeroBallMate.OpenGlEntity openGlEntity) {
        insertBallMoveHelp(openGlEntity);
    }

    @Override // com.wanos.media.widget.ball.ZeroBallMate.IOpenGlBallChangeListener
    public void onOpenGLDelete(int i) {
        deleteBall(i);
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        this.mTimer.cancel();
        this.mZeroBallMateWork.onViewDestroy();
        this.mBallInfoList.clear();
        this.mBallMoveHelps.clear();
    }
}
