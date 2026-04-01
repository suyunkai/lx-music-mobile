package com.wanos.media.widget.ball;

import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import com.blankj.utilcode.util.Utils;
import com.wanos.media.ZeroApplication;
import com.wanos.media.entity.AudioBall;
import com.wanos.media.entity.BallCollectEntity;
import com.wanos.media.entity.ZeroBallInfoEntity;
import com.wanos.media.util.ZeroAudioBallTools;
import com.wanos.media.util.ZeroLogcatTools;
import com.wanos.media.widget.ball.BallTrackEngine;
import com.wanos.media.widget.sound.BallMoveWay;
import com.wanos.media.zero_p.R;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes3.dex */
public class BallMoveHelp implements BallTrackEngine.ITrackCallback {
    static final int MOVE_CHECK_TIME = 16;
    private static final int MOVE_VALVE = 10;
    private static final int RECORD_POINT_NUM = 10;
    private static final String TAG = "BallMoveHelp";
    private final int ballId;
    private final IBallCallback iBallCallback;
    private boolean isAutoMove;
    private int mAngle;
    private float mAudioCurrentX;
    private float mAudioCurrentY;
    private final BallTrackEngine mBallTrackEngine;
    private Rect mCanvasRect;
    private MotionEvent mDownEvent;
    private float mDownX;
    private float mDownY;
    private int mMaxBottom;
    private int mMaxLeft;
    private int mMaxRight;
    private int mMaxTop;
    private Near mNear;
    private final PointRecordHelp mPointRecordHelp;
    private float mSpeed;
    private final int mTouchSlop;
    private float mTranslationX;
    private float mTranslationY;
    private float mTranslationZ;
    private boolean isModifyVolume = false;
    private boolean isClickBall = false;
    private final float mBallMaxSize = Utils.getApp().getResources().getDimension(R.dimen.zero_pro_ball_max_size);
    private final float mBallMinSize = Utils.getApp().getResources().getDimension(R.dimen.zero_pro_ball_min_size);
    private final Handler mTrackHandler = new Handler(Looper.getMainLooper());

    private enum Near {
        X,
        Y
    }

    static float getAudioXPos(float f, float f2) {
        float f3 = f / 2.0f;
        if (f2 > f3) {
            return (f2 - f3) * (2.0f / f);
        }
        if (f2 >= f3) {
            return 0.0f;
        }
        return -((f3 - f2) * (2.0f / f));
    }

    static float getAudioYPos(float f, float f2) {
        float f3 = f / 2.0f;
        if (f2 > f3) {
            return -((f2 - f3) * (2.0f / f));
        }
        if (f2 >= f3) {
            return 0.0f;
        }
        return (f3 - f2) * (2.0f / f);
    }

    public BallMoveHelp(int i, Rect rect, IBallCallback iBallCallback) {
        this.ballId = i;
        this.mCanvasRect = rect;
        this.iBallCallback = iBallCallback;
        int scaledTouchSlop = ViewConfiguration.get(ZeroApplication.getApplication()).getScaledTouchSlop();
        this.mTouchSlop = scaledTouchSlop * scaledTouchSlop;
        this.mPointRecordHelp = new PointRecordHelp(this);
        this.mBallTrackEngine = new BallTrackEngine(this);
    }

    int getBallId() {
        return this.ballId;
    }

    boolean isAutoMove() {
        return this.isAutoMove;
    }

    float getCurrentX() {
        return this.mAudioCurrentX;
    }

    float getCurrentY() {
        return this.mAudioCurrentY;
    }

    float getCurrentZ() {
        return this.mTranslationZ;
    }

    void setAutoMove(float f, float f2) {
        if (f2 <= 0.0f) {
            return;
        }
        this.isClickBall = true;
        onLaunchMoveEngine((int) f, f2);
    }

    void onTouchEventDown(MotionEvent motionEvent) {
        this.mPointRecordHelp.setMotionEvent(MotionEvent.obtain(motionEvent));
        this.isClickBall = true;
        MotionEvent motionEvent2 = this.mDownEvent;
        if (motionEvent2 != null) {
            motionEvent2.recycle();
        }
        this.mDownEvent = MotionEvent.obtain(motionEvent);
        this.mDownX = motionEvent.getX();
        this.mDownY = motionEvent.getY();
        resetValueAnimator();
        this.mPointRecordHelp.resetTrack();
        this.mTrackHandler.post(this.mPointRecordHelp);
        this.mAngle = 0;
        this.mSpeed = 0.0f;
        this.isAutoMove = false;
    }

    void onTouchEventMove(MotionEvent motionEvent) {
        this.mPointRecordHelp.setMotionEvent(MotionEvent.obtain(motionEvent));
        float x = motionEvent.getX() - this.mDownX;
        float y = motionEvent.getY() - this.mDownY;
        float f = this.mTranslationX;
        float f2 = this.mTranslationY;
        float f3 = f + x;
        int i = this.mMaxLeft;
        if (f3 <= i) {
            x = i - f;
        }
        float f4 = f + x;
        int i2 = this.mMaxRight;
        if (f4 >= i2) {
            x = i2 - f;
        }
        float f5 = f2 + y;
        int i3 = this.mMaxTop;
        if (f5 <= i3) {
            y = i3 - f2;
        }
        float f6 = f2 + y;
        int i4 = this.mMaxBottom;
        if (f6 > i4) {
            y = i4 - f2;
        }
        float f7 = x + f;
        float f8 = y + f2;
        if (f7 != f || f8 != f2) {
            setBallTranslationXY(f7, f8, BallMoveWay.WAY_USER_TOUCH);
        }
        this.mDownX = motionEvent.getX();
        this.mDownY = motionEvent.getY();
    }

    void onTouchEventUp(MotionEvent motionEvent) {
        this.mTrackHandler.removeCallbacks(this.mPointRecordHelp);
        initInertiaState();
        this.mPointRecordHelp.resetTrack();
        ZeroLogcatTools.w(TAG, "onTouchEventUp: isAutoMove = " + this.isAutoMove);
        if (this.isAutoMove) {
            onLaunchMoveEngine(this.mAngle, this.mSpeed);
            return;
        }
        ZeroLogcatTools.w(TAG, "onTouchEventUp: isAutoMove = false , 判断点击还是拖动");
        if (this.mDownEvent == null) {
            ZeroLogcatTools.e(TAG, "onTouchEventUp: mDownEvent == null");
            return;
        }
        int iHypot = (int) Math.hypot(Math.abs(motionEvent.getRawY() - this.mDownEvent.getRawY()), Math.abs(motionEvent.getRawX() - this.mDownEvent.getRawX()));
        ZeroLogcatTools.w(TAG, "onTouchEventUp: mDragLength = " + iHypot + ", mTouchSlop = " + this.mTouchSlop + ", iBallCallback = " + this.iBallCallback);
        if (iHypot <= this.mTouchSlop) {
            setBallTranslationXY(this.mTranslationX, this.mTranslationY, BallMoveWay.WAY_USER_TOUCH);
            if (this.iBallCallback != null) {
                ZeroLogcatTools.w(TAG, "onTouchEventUp: mDragLength <= mTouchSlop , 通知弹出编辑框");
                this.iBallCallback.onMotionClick(this.ballId, this.mTranslationX, this.mTranslationY, this.mTranslationZ);
            }
        }
    }

    BallCollectEntity getBallCollectEntity(ZeroBallInfoEntity zeroBallInfoEntity) {
        int i;
        AudioBall audioInfo = ZeroAudioBallTools.getInstance().getAudioInfo(this.ballId);
        if (audioInfo == null || zeroBallInfoEntity == null) {
            ZeroLogcatTools.e(TAG, "getBallCollectEntity: 获取小球信息错误，audioInfo = " + audioInfo + ", ballInfoMate = " + zeroBallInfoEntity + "，音源ID = " + this.ballId);
            return null;
        }
        int modify = audioInfo.getAudioInfo().getModify();
        if (modify != 0) {
            if (modify != 1) {
                if (modify == 2) {
                    if (this.isClickBall) {
                        boolean z = this.isModifyVolume;
                    }
                }
                i = modify;
            } else {
                boolean z2 = this.isClickBall;
                if ((!z2 || !this.isModifyVolume) && !z2) {
                    i = 1;
                }
            }
            i = 2;
        } else {
            boolean z3 = this.isClickBall;
            if ((!z3 || !this.isModifyVolume) && !z3) {
                if (!this.isModifyVolume) {
                    modify = 0;
                    i = modify;
                }
                i = 1;
            }
            i = 2;
        }
        return new BallCollectEntity(audioInfo, i, zeroBallInfoEntity.getAudioVolume(), this.mAngle, this.mSpeed);
    }

    void setMediaTrack(float f, float f2, float f3) {
        if (this.isClickBall) {
            return;
        }
        setBallTranslationXY(getScreenPositionX(f), getScreenPositionY(f2), BallMoveWay.WAY_MEDIA_MOVE);
        setBallTranslationZ(f3, BallMoveWay.WAY_MEDIA_MOVE);
    }

    void setBallTranslationZ(float f, BallMoveWay ballMoveWay) {
        if (this.mCanvasRect == null) {
            ZeroLogcatTools.e(TAG, "setBallTranslationZ: mCanvasRect == null");
            return;
        }
        this.mTranslationZ = f;
        float fAbs = Math.abs(f);
        float f2 = this.mBallMinSize;
        float f3 = (f2 + ((this.mBallMaxSize - f2) * fAbs)) / 2.0f;
        this.mMaxLeft = (int) (this.mCanvasRect.left + f3);
        this.mMaxTop = (int) (this.mCanvasRect.top + f3);
        this.mMaxRight = (int) (this.mCanvasRect.right - f3);
        this.mMaxBottom = (int) (this.mCanvasRect.bottom - f3);
        this.iBallCallback.onBallZ(ballMoveWay, this.ballId, this.mTranslationZ);
        this.mTranslationX = getScreenPositionX(this.mAudioCurrentX);
        this.mTranslationY = getScreenPositionY(this.mAudioCurrentY);
    }

    void setBallTranslationXY(float f, float f2, BallMoveWay ballMoveWay) {
        this.mTranslationX = f;
        this.mTranslationY = f2;
        this.mAudioCurrentX = getAudioXPos(f);
        float audioYPos = getAudioYPos(this.mTranslationY);
        this.mAudioCurrentY = audioYPos;
        this.iBallCallback.onBallPositionXY(ballMoveWay, this.ballId, this.mAudioCurrentX, audioYPos);
    }

    void setModifyVolumeState() {
        this.isModifyVolume = true;
    }

    BallTrackEngine getBallTrackEngine() {
        return this.mBallTrackEngine;
    }

    boolean isDownBall() {
        return this.isClickBall;
    }

    Handler getTrackHandler() {
        return this.mTrackHandler;
    }

    private void resetValueAnimator() {
        this.mBallTrackEngine.setStop();
    }

    float getAudioXPos(float f) {
        int i = this.mMaxRight;
        int i2 = this.mMaxLeft;
        float f2 = i - i2;
        float f3 = i2 + (f2 / 2.0f);
        if (f > f3) {
            return (f - f3) * (2.0f / f2);
        }
        if (f >= f3) {
            return 0.0f;
        }
        return -((f3 - f) * (2.0f / f2));
    }

    float getAudioYPos(float f) {
        int i = this.mMaxBottom;
        int i2 = this.mMaxTop;
        float f2 = i - i2;
        float f3 = i2 + (f2 / 2.0f);
        if (f > f3) {
            return -((f - f3) * (2.0f / f2));
        }
        if (f >= f3) {
            return 0.0f;
        }
        return (f3 - f) * (2.0f / f2);
    }

    float getScreenPositionX(float f) {
        int i;
        float f2 = (this.mMaxRight - this.mMaxLeft) / 2.0f;
        float fAbs = Math.abs(f) * f2;
        if (f > 0.0f) {
            f2 += fAbs;
            i = this.mMaxLeft;
        } else if (f < 0.0f) {
            f2 -= fAbs;
            i = this.mMaxLeft;
        } else {
            i = this.mMaxLeft;
        }
        return f2 + i;
    }

    float getScreenPositionY(float f) {
        int i;
        float f2 = (this.mMaxBottom - this.mMaxTop) / 2.0f;
        float fAbs = Math.abs(f) * f2;
        if (f > 0.0f) {
            f2 -= fAbs;
            i = this.mMaxTop;
        } else if (f < 0.0f) {
            f2 += fAbs;
            i = this.mMaxTop;
        } else {
            i = this.mMaxTop;
        }
        return f2 + i;
    }

    private void initInertiaState() {
        LinkedList<String> track = this.mPointRecordHelp.getTrack();
        if (track.isEmpty()) {
            ZeroLogcatTools.w(TAG, "initInertiaState: track.isEmpty()");
            return;
        }
        String[] strArrSplit = track.getFirst().split("[|]");
        String[] strArrSplit2 = track.getLast().split("[|]");
        int iRound = (int) Math.round(Math.hypot(d, d));
        if (iRound < 10) {
            ZeroLogcatTools.w(TAG, "initInertiaState: 非惯性移动");
            this.isAutoMove = false;
            this.mSpeed = 0.0f;
            this.mAngle = 0;
            return;
        }
        ZeroLogcatTools.w(TAG, "initInertiaState: 惯性移动，计算角度与速度");
        int degrees = (int) Math.toDegrees(Math.atan2(d, d));
        int iAbs = degrees < 0 ? Math.abs(degrees) : 360 - degrees;
        float f = iRound / 160.0f;
        ZeroLogcatTools.d(TAG, "initInertiaState: " + f);
        if (f > 0.4f) {
            f = 0.4f;
        }
        this.isAutoMove = true;
        this.mSpeed = f;
        this.mAngle = iAbs;
    }

    private void onLaunchMoveEngine(int i, float f) {
        float f2;
        int iRound;
        float f3;
        int iRound2;
        float f4;
        int i2;
        float f5;
        float fRound;
        float fRound2;
        long jRound;
        resetValueAnimator();
        this.mAngle = i;
        this.mSpeed = f;
        this.isAutoMove = true;
        float f6 = this.mTranslationX;
        float f7 = this.mTranslationY;
        if (i > 0 && i < 90) {
            double radians = Math.toRadians(i);
            fRound = this.mMaxRight - f6;
            fRound2 = -Math.round(Math.tan(radians) * ((double) fRound));
            this.mNear = Near.X;
            float f8 = f7 + fRound2;
            int i3 = this.mMaxTop;
            if (f8 < i3) {
                fRound2 = -(f7 - i3);
                fRound = Math.round(((double) Math.abs(fRound2)) / Math.tan(radians));
                this.mNear = Near.Y;
            }
            jRound = Math.round(Math.hypot(fRound, fRound2) / ((double) f));
        } else if (i > 90 && i < 180) {
            double radians2 = Math.toRadians(180 - i);
            fRound = -(f6 - this.mMaxLeft);
            fRound2 = -Math.round(Math.tan(radians2) * ((double) Math.abs(fRound)));
            this.mNear = Near.X;
            float f9 = f7 + fRound2;
            int i4 = this.mMaxTop;
            if (f9 < i4) {
                float f10 = -(f7 - i4);
                float f11 = -Math.round(((double) Math.abs(f10)) / Math.tan(radians2));
                this.mNear = Near.Y;
                fRound2 = f10;
                fRound = f11;
            }
            jRound = Math.round(Math.hypot(fRound, fRound2) / ((double) f));
        } else if (i > 180 && i < 270) {
            double radians3 = Math.toRadians(270 - i);
            fRound = -(f6 - this.mMaxLeft);
            fRound2 = Math.round(((double) Math.abs(fRound)) / Math.tan(radians3));
            this.mNear = Near.X;
            float f12 = f7 + fRound2;
            int i5 = this.mMaxBottom;
            if (f12 > i5) {
                float f13 = i5 - f7;
                float f14 = -Math.round(((double) f13) * Math.tan(radians3));
                this.mNear = Near.Y;
                fRound2 = f13;
                fRound = f14;
            }
            jRound = Math.round(Math.hypot(fRound, fRound2) / ((double) f));
        } else if (i > 270 && i < 360) {
            double radians4 = Math.toRadians(360 - i);
            fRound = this.mMaxRight - f6;
            fRound2 = Math.round(((double) fRound) * Math.tan(radians4));
            this.mNear = Near.X;
            float f15 = f7 + fRound2;
            int i6 = this.mMaxBottom;
            if (f15 > i6) {
                float f16 = i6 - f7;
                float fRound3 = Math.round(((double) f16) / Math.tan(radians4));
                this.mNear = Near.Y;
                fRound2 = f16;
                fRound = fRound3;
            }
            jRound = Math.round(Math.hypot(fRound, fRound2) / ((double) f));
        } else {
            if (i == 90) {
                f3 = -(f7 - this.mMaxTop);
                iRound2 = Math.round(Math.abs(f3) / f);
                this.mNear = Near.Y;
            } else {
                if (i == 180) {
                    f2 = -(f6 - this.mMaxLeft);
                    iRound = Math.round(Math.abs(f2) / f);
                    this.mNear = Near.X;
                } else if (i == 270) {
                    f3 = this.mMaxBottom - f7;
                    iRound2 = Math.round(f3 / f);
                    this.mNear = Near.Y;
                } else {
                    f2 = this.mMaxRight - f6;
                    iRound = Math.round(f2 / f);
                    this.mNear = Near.X;
                }
                f4 = f2;
                i2 = iRound;
                f5 = 0.0f;
                this.mBallTrackEngine.setTrackInfo(this.mTranslationX, this.mTranslationY, f4, f5, i2);
            }
            i2 = iRound2;
            f5 = f3;
            f4 = 0.0f;
            this.mBallTrackEngine.setTrackInfo(this.mTranslationX, this.mTranslationY, f4, f5, i2);
        }
        f4 = fRound;
        i2 = (int) jRound;
        f5 = fRound2;
        this.mBallTrackEngine.setTrackInfo(this.mTranslationX, this.mTranslationY, f4, f5, i2);
    }

    @Override // com.wanos.media.widget.ball.BallTrackEngine.ITrackCallback
    public void onFrame(float f, float f2) {
        setBallTranslationXY(f, f2, BallMoveWay.WAY_AUTO_MOVE);
    }

    @Override // com.wanos.media.widget.ball.BallTrackEngine.ITrackCallback
    public void onEnd() {
        if (this.isAutoMove) {
            int i = this.mAngle;
            if (i > 0 && i < 90) {
                if (Near.Y == this.mNear) {
                    onLaunchMoveEngine(360 - this.mAngle, this.mSpeed);
                    return;
                } else {
                    onLaunchMoveEngine(180 - this.mAngle, this.mSpeed);
                    return;
                }
            }
            if (i > 90 && i < 180) {
                if (Near.Y == this.mNear) {
                    onLaunchMoveEngine(360 - this.mAngle, this.mSpeed);
                    return;
                } else {
                    onLaunchMoveEngine(180 - this.mAngle, this.mSpeed);
                    return;
                }
            }
            if (i > 180 && i < 270) {
                if (Near.Y == this.mNear) {
                    onLaunchMoveEngine(360 - this.mAngle, this.mSpeed);
                    return;
                } else {
                    onLaunchMoveEngine(360 - (this.mAngle - 180), this.mSpeed);
                    return;
                }
            }
            if (i > 270 && i < 360) {
                if (Near.Y == this.mNear) {
                    onLaunchMoveEngine(360 - this.mAngle, this.mSpeed);
                    return;
                } else {
                    onLaunchMoveEngine(360 - (this.mAngle - 180), this.mSpeed);
                    return;
                }
            }
            if (i == 90) {
                onLaunchMoveEngine(180, this.mSpeed);
                return;
            }
            if (i == 180) {
                onLaunchMoveEngine(0, this.mSpeed);
                return;
            }
            if (i == 270) {
                onLaunchMoveEngine(90, this.mSpeed);
            } else if (i == 0 || i == 360) {
                onLaunchMoveEngine(180, this.mSpeed);
            }
        }
    }
}
