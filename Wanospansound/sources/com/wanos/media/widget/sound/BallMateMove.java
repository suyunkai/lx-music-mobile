package com.wanos.media.widget.sound;

import android.animation.Animator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.LinearInterpolator;
import com.wanos.bean.PressureBean;
import com.wanos.media.util.ZeroLogcatTools;
import java.lang.ref.WeakReference;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes3.dex */
public class BallMateMove {
    private static final int MOVE_CHECK_TIME = 10;
    private static final int MOVE_VALVE = 10;
    private static final int RECORD_POINT_NUM = 10;
    private static final String TAG = "SoundBallMoveHelp";
    private final ISoundBallMove iSoundBallMove;
    private boolean isAutoMove;
    private int mAngle;
    private final CustomAutoMoveEvaluator mAutoMoveEvaluator;
    private final ValueAnimatorListener mAutoMoveListener;
    private final ValueAnimatorUpdateListener mAutoMoveUpdate;
    private int mBreathWidth;
    private MotionEvent mDownEvent;
    private float mDownX;
    private float mDownY;
    private final HandlerThread mHandlerThread;
    private int mMaxBottom;
    private int mMaxLeft;
    private int mMaxRight;
    private int mMaxTop;
    private final CustomMediaMoveEvaluator mMediaMoveEvaluator;
    private final ValueAnimator mMoveAnimator;
    private Near mNear;
    private int mParentBottom;
    private int mParentLeft;
    private int mParentRight;
    private int mParentTop;
    private final PointRecord mPointRecordCallback;
    private float mSpeed;
    private final State mStateStart;
    private final State mStopState;
    private final int mTouchSlop;
    private final Handler mTrackHandler;
    private float mTranslationX;
    private float mTranslationY;
    private int mViewHeight;
    private int mViewWidth;
    private final PointF mPointStart = new PointF();
    private final PointF mPointStop = new PointF();
    private boolean isDownExit = false;
    private boolean mEditState = false;
    private float mNowAudioZ = 0.0f;
    private boolean isClickBall = false;

    private enum Near {
        X,
        Y
    }

    BallMateMove(Context context, ISoundBallMove iSoundBallMove) {
        this.mStateStart = new State();
        this.mStopState = new State();
        this.iSoundBallMove = iSoundBallMove;
        HandlerThread handlerThread = new HandlerThread("_sound_ball_track_");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mTrackHandler = new Handler(handlerThread.getLooper());
        int scaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.mTouchSlop = scaledTouchSlop * scaledTouchSlop;
        ValueAnimator valueAnimator = new ValueAnimator();
        this.mMoveAnimator = valueAnimator;
        valueAnimator.setInterpolator(new LinearInterpolator());
        this.mPointRecordCallback = new PointRecord(this);
        this.mAutoMoveEvaluator = new CustomAutoMoveEvaluator();
        this.mMediaMoveEvaluator = new CustomMediaMoveEvaluator();
        this.mAutoMoveListener = new ValueAnimatorListener(this);
        this.mAutoMoveUpdate = new ValueAnimatorUpdateListener(this);
    }

    void onSizeChange(int i, int i2, View view) {
        if (view == null) {
            ZeroLogcatTools.e(TAG, "onSizeChange: 未获取到ParentView");
            return;
        }
        this.mViewWidth = i;
        this.mViewHeight = i2;
        this.mParentLeft = view.getLeft();
        this.mParentTop = view.getTop();
        this.mParentRight = view.getRight();
        this.mParentBottom = view.getBottom();
        initParentMaxSize();
    }

    boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return false;
        }
        this.mPointRecordCallback.setMotionEvent(MotionEvent.obtain(motionEvent));
        int action = motionEvent.getAction();
        if (action == 0) {
            this.isClickBall = true;
            this.iSoundBallMove.onBallMotionDown();
            MotionEvent motionEvent2 = this.mDownEvent;
            if (motionEvent2 != null) {
                motionEvent2.recycle();
            }
            this.mDownEvent = MotionEvent.obtain(motionEvent);
            this.mDownX = motionEvent.getX();
            this.mDownY = motionEvent.getY();
            setResetValueAnimator();
            this.mPointRecordCallback.removeAllTrack();
            this.mTrackHandler.post(this.mPointRecordCallback);
            if (this.mEditState) {
                this.mEditState = false;
                this.isDownExit = true;
                this.iSoundBallMove.onEditModeChange(false);
            } else {
                this.isDownExit = false;
            }
        } else if (action == 1) {
            this.mTrackHandler.removeCallbacks(this.mPointRecordCallback);
            initInertiaState();
            this.mPointRecordCallback.removeAllTrack();
            this.iSoundBallMove.onMoveInfoChange(this.isAutoMove, this.mSpeed, this.mAngle);
            if (this.isAutoMove) {
                onLaunchMoveEngine(this.mAngle, this.mSpeed);
            } else if (this.mDownEvent != null && ((int) Math.hypot(Math.abs(motionEvent.getRawY() - this.mDownEvent.getRawY()), Math.abs(motionEvent.getRawX() - this.mDownEvent.getRawX()))) < this.mTouchSlop && !this.isDownExit) {
                boolean z = !this.mEditState;
                this.mEditState = z;
                this.iSoundBallMove.onEditModeChange(z);
            }
        } else if (action == 2) {
            float x = motionEvent.getX() - this.mDownX;
            float y = motionEvent.getY() - this.mDownY;
            float f = this.mTranslationX;
            float f2 = this.mTranslationY;
            float f3 = f + x;
            int i = this.mMaxLeft;
            if (f3 < i) {
                x = i - f;
            }
            int i2 = this.mViewWidth;
            float f4 = i2 + f + x;
            int i3 = this.mMaxRight;
            if (f4 > i3) {
                x = (i3 - f) - i2;
            }
            float f5 = f2 + y;
            int i4 = this.mMaxTop;
            if (f5 < i4) {
                y = i4 - f2;
            }
            int i5 = this.mViewHeight;
            float f6 = i5 + f2 + y;
            int i6 = this.mMaxBottom;
            if (f6 > i6) {
                y = (i6 - f2) - i5;
            }
            float f7 = x + f;
            float f8 = y + f2;
            if (f7 != f || f8 != f2) {
                this.mTranslationX = f7;
                this.mTranslationY = f8;
                this.iSoundBallMove.setViewTranslationXY(f7, f8);
                setAudioMoveChange(this.mTranslationX, this.mTranslationY, BallMoveWay.WAY_USER_TOUCH);
            }
        }
        return true;
    }

    void onSeekBarChange(float f, BallMoveWay ballMoveWay) {
        this.mNowAudioZ = f;
        this.iSoundBallMove.setViewTranslationZ(f, ballMoveWay);
        setAudioMoveChange(this.mTranslationX, this.mTranslationY, ballMoveWay);
    }

    void onDetachedFromWindow() {
        this.mHandlerThread.quit();
        setResetValueAnimator();
    }

    void setMediaTrack(PressureBean.PressureLine pressureLine, PressureBean.PressureLine pressureLine2, double d2) {
        if (this.isClickBall || pressureLine == null || pressureLine2 == null || pressureLine.seek < 0 || pressureLine2.seek < 0) {
            return;
        }
        this.mNowAudioZ = pressureLine.z_m;
        if (pressureLine2.x_m == pressureLine.x_m && pressureLine2.y_m == pressureLine.y_m && pressureLine2.z_m == pressureLine.z_m) {
            return;
        }
        setResetValueAnimator();
        this.mStateStart.x = getScreenPositionX(pressureLine.x_m);
        this.mStateStart.y = getScreenPositionY(pressureLine.y_m);
        this.mStateStart.z = pressureLine.z_m;
        this.mStateStart.timer = pressureLine.ballFrameTime;
        this.mStopState.x = getScreenPositionX(pressureLine2.x_m);
        this.mStopState.y = getScreenPositionY(pressureLine2.y_m);
        this.mStopState.z = pressureLine2.z_m;
        this.mStopState.timer = pressureLine2.ballFrameTime;
        this.mMoveAnimator.setObjectValues(this.mStateStart, this.mStopState);
        this.mMoveAnimator.setDuration((long) ((d2 - pressureLine.ballFrameTime) * 1000.0d));
        this.mMoveAnimator.setEvaluator(this.mMediaMoveEvaluator);
        this.mMoveAnimator.addListener(this.mAutoMoveListener);
        this.mMoveAnimator.addUpdateListener(this.mAutoMoveUpdate);
        this.mMoveAnimator.start();
    }

    void setEditState(boolean z) {
        this.mEditState = z;
    }

    void initViewPosition(float f, float f2, float f3) {
        ZeroLogcatTools.d(TAG, "initViewPosition: " + f3);
        this.mNowAudioZ = f3;
        this.mTranslationX = getScreenPositionX(f);
        float screenPositionY = getScreenPositionY(f2);
        this.mTranslationY = screenPositionY;
        this.iSoundBallMove.setViewTranslationXY(this.mTranslationX, screenPositionY);
        this.iSoundBallMove.setViewTranslationZ(this.mNowAudioZ, BallMoveWay.WAY_INIT);
        this.iSoundBallMove.onBallAudioPosition(f, f2, this.mNowAudioZ, BallMoveWay.WAY_INIT);
    }

    void setBreathWidth(int i) {
        this.mBreathWidth = i;
        initParentMaxSize();
    }

    void setAutoMove(float f, float f2) {
        if (f2 <= 0.0f) {
            return;
        }
        this.isClickBall = true;
        int i = (int) f;
        onLaunchMoveEngine(i, f2);
        this.iSoundBallMove.onMoveInfoChange(this.isAutoMove, f2, i);
    }

    boolean isClickBall() {
        return this.isClickBall;
    }

    private float getScreenPositionX(float f) {
        int i = this.mMaxRight - this.mViewWidth;
        int i2 = this.mMaxLeft;
        float f2 = i - i2;
        float f3 = 2.0f / f2;
        float f4 = i2 + (f2 / 2.0f);
        if (f > 0.0f) {
            return f4 + Math.abs(f / f3);
        }
        return f < 0.0f ? f4 - Math.abs(f / f3) : f4;
    }

    private float getScreenPositionY(float f) {
        int i = this.mMaxBottom - this.mViewHeight;
        int i2 = this.mMaxTop;
        float f2 = i - i2;
        float f3 = 2.0f / f2;
        float f4 = i2 + (f2 / 2.0f);
        if (f > 0.0f) {
            return f4 - Math.abs(f / f3);
        }
        return f < 0.0f ? f4 + Math.abs(f / f3) : f4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAudioMoveChange(float f, float f2, BallMoveWay ballMoveWay) {
        int i = this.mMaxRight - this.mViewWidth;
        int i2 = this.mMaxLeft;
        int i3 = this.mMaxBottom - this.mViewHeight;
        int i4 = this.mMaxTop;
        float f3 = i - i2;
        float f4 = 2.0f / f3;
        float f5 = i3 - i4;
        float f6 = 2.0f / f5;
        float f7 = i2 + (f3 / 2.0f);
        float f8 = i4 + (f5 / 2.0f);
        float f9 = 0.0f;
        float f10 = f < f7 ? -((f7 - f) * f4) : f > f7 ? (f - f7) * f4 : 0.0f;
        if (f2 < f8) {
            f9 = (f8 - f2) * f6;
        } else if (f2 > f8) {
            f9 = -((f2 - f8) * f6);
        }
        this.iSoundBallMove.onBallAudioPosition(f10, f9, this.mNowAudioZ, ballMoveWay);
    }

    private void initInertiaState() {
        LinkedList<String> track = this.mPointRecordCallback.getTrack();
        if (track.isEmpty()) {
            return;
        }
        String[] strArrSplit = track.getFirst().split("[|]");
        String[] strArrSplit2 = track.getLast().split("[|]");
        int iRound = (int) Math.round(Math.hypot(d, d));
        if (iRound < 10) {
            this.isAutoMove = false;
            this.mSpeed = 0.0f;
            this.mAngle = 0;
            return;
        }
        int degrees = (int) Math.toDegrees(Math.atan2(d, d));
        int iAbs = degrees < 0 ? Math.abs(degrees) : 360 - degrees;
        float f = iRound / 100.0f;
        if (f > 0.3d) {
            f = 0.3f;
        }
        this.isAutoMove = true;
        this.mSpeed = f;
        this.mAngle = iAbs;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onLaunchMoveEngine(int i, float f) {
        float fRound;
        int iRound;
        float fRound2;
        long jRound;
        setResetValueAnimator();
        this.mAngle = i;
        this.mSpeed = f;
        this.isAutoMove = true;
        this.iSoundBallMove.onMoveInfoChange(true, f, i);
        float f2 = this.mTranslationX;
        float f3 = this.mTranslationY;
        if (i > 0 && i < 90) {
            double radians = Math.toRadians(i);
            fRound = (this.mMaxRight - this.mViewWidth) - f2;
            fRound2 = -Math.round(Math.tan(radians) * ((double) fRound));
            this.mNear = Near.X;
            float f4 = f3 + fRound2;
            int i2 = this.mMaxTop;
            if (f4 < i2) {
                fRound2 = -(f3 - i2);
                fRound = Math.round(((double) Math.abs(fRound2)) / Math.tan(radians));
                this.mNear = Near.Y;
            }
            jRound = Math.round(Math.hypot(fRound, fRound2) / ((double) f));
        } else if (i > 90 && i < 180) {
            double radians2 = Math.toRadians(180 - i);
            fRound = -(f2 - this.mMaxLeft);
            fRound2 = -Math.round(Math.tan(radians2) * ((double) Math.abs(fRound)));
            this.mNear = Near.X;
            float f5 = f3 + fRound2;
            int i3 = this.mMaxTop;
            if (f5 < i3) {
                float f6 = -(f3 - i3);
                float f7 = -Math.round(((double) Math.abs(f6)) / Math.tan(radians2));
                this.mNear = Near.Y;
                fRound2 = f6;
                fRound = f7;
            }
            jRound = Math.round(Math.hypot(fRound, fRound2) / ((double) f));
        } else if (i > 180 && i < 270) {
            double radians3 = Math.toRadians(270 - i);
            fRound = -(f2 - this.mMaxLeft);
            fRound2 = Math.round(((double) Math.abs(fRound)) / Math.tan(radians3));
            this.mNear = Near.X;
            float f8 = f3 + fRound2;
            int i4 = this.mMaxBottom;
            int i5 = this.mViewHeight;
            if (f8 > i4 - i5) {
                float f9 = (i4 - i5) - f3;
                float f10 = -Math.round(((double) f9) * Math.tan(radians3));
                this.mNear = Near.Y;
                fRound2 = f9;
                fRound = f10;
            }
            jRound = Math.round(Math.hypot(fRound, fRound2) / ((double) f));
        } else if (i > 270 && i < 360) {
            double radians4 = Math.toRadians(360 - i);
            fRound = (this.mMaxRight - this.mViewHeight) - f2;
            fRound2 = Math.round(((double) fRound) * Math.tan(radians4));
            this.mNear = Near.X;
            float f11 = f3 + fRound2;
            int i6 = this.mMaxBottom;
            int i7 = this.mViewHeight;
            if (f11 > i6 - i7) {
                float f12 = (i6 - i7) - f3;
                float fRound3 = Math.round(((double) f12) / Math.tan(radians4));
                this.mNear = Near.Y;
                fRound2 = f12;
                fRound = fRound3;
            }
            jRound = Math.round(Math.hypot(fRound, fRound2) / ((double) f));
        } else {
            if (i == 90) {
                fRound2 = -(f3 - this.mMaxTop);
                iRound = Math.round(Math.abs(fRound2) / f);
                this.mNear = Near.Y;
            } else {
                if (i == 180) {
                    fRound = -(f2 - this.mMaxLeft);
                    iRound = Math.round(Math.abs(fRound) / f);
                    this.mNear = Near.X;
                } else if (i == 270) {
                    fRound2 = (this.mMaxBottom - this.mViewHeight) - f3;
                    iRound = Math.round(fRound2 / f);
                    this.mNear = Near.Y;
                } else {
                    fRound = (this.mMaxRight - this.mViewWidth) - f2;
                    iRound = Math.round(fRound / f);
                    this.mNear = Near.X;
                }
                fRound2 = 0.0f;
                this.mPointStart.set(this.mTranslationX, this.mTranslationY);
                this.mPointStop.set(this.mTranslationX + fRound, this.mTranslationY + fRound2);
                this.mMoveAnimator.setObjectValues(this.mPointStart, this.mPointStop);
                this.mMoveAnimator.setDuration(iRound);
                this.mMoveAnimator.setEvaluator(this.mAutoMoveEvaluator);
                this.mMoveAnimator.addListener(this.mAutoMoveListener);
                this.mMoveAnimator.addUpdateListener(this.mAutoMoveUpdate);
                this.mMoveAnimator.start();
            }
            fRound = 0.0f;
            this.mPointStart.set(this.mTranslationX, this.mTranslationY);
            this.mPointStop.set(this.mTranslationX + fRound, this.mTranslationY + fRound2);
            this.mMoveAnimator.setObjectValues(this.mPointStart, this.mPointStop);
            this.mMoveAnimator.setDuration(iRound);
            this.mMoveAnimator.setEvaluator(this.mAutoMoveEvaluator);
            this.mMoveAnimator.addListener(this.mAutoMoveListener);
            this.mMoveAnimator.addUpdateListener(this.mAutoMoveUpdate);
            this.mMoveAnimator.start();
        }
        iRound = (int) jRound;
        this.mPointStart.set(this.mTranslationX, this.mTranslationY);
        this.mPointStop.set(this.mTranslationX + fRound, this.mTranslationY + fRound2);
        this.mMoveAnimator.setObjectValues(this.mPointStart, this.mPointStop);
        this.mMoveAnimator.setDuration(iRound);
        this.mMoveAnimator.setEvaluator(this.mAutoMoveEvaluator);
        this.mMoveAnimator.addListener(this.mAutoMoveListener);
        this.mMoveAnimator.addUpdateListener(this.mAutoMoveUpdate);
        this.mMoveAnimator.start();
    }

    private void setResetValueAnimator() {
        this.mMoveAnimator.removeUpdateListener(this.mAutoMoveUpdate);
        this.mMoveAnimator.removeListener(this.mAutoMoveListener);
        if (this.mMoveAnimator.isRunning()) {
            this.mMoveAnimator.cancel();
        }
    }

    private void initParentMaxSize() {
        int i = this.mParentLeft;
        int i2 = this.mBreathWidth;
        this.mMaxLeft = i - i2;
        this.mMaxTop = this.mParentTop - i2;
        this.mMaxRight = this.mParentRight + i2;
        this.mMaxBottom = this.mParentBottom + i2;
    }

    private static class PointRecord implements Runnable {
        private static final int RECORD_POINT_NUM = 10;
        private final LinkedList<String> MOVE_POINT = new LinkedList<>();
        private final WeakReference<BallMateMove> mHelp;
        private MotionEvent mMoveEvent;

        public PointRecord(BallMateMove ballMateMove) {
            this.mHelp = new WeakReference<>(ballMateMove);
        }

        public void removeAllTrack() {
            this.MOVE_POINT.clear();
        }

        public void setMotionEvent(MotionEvent motionEvent) {
            MotionEvent motionEvent2 = this.mMoveEvent;
            if (motionEvent2 != null) {
                motionEvent2.recycle();
            }
            this.mMoveEvent = motionEvent;
        }

        public LinkedList<String> getTrack() {
            return this.MOVE_POINT;
        }

        @Override // java.lang.Runnable
        public void run() {
            BallMateMove ballMateMove = this.mHelp.get();
            if (ballMateMove == null) {
                return;
            }
            ballMateMove.mTrackHandler.postDelayed(ballMateMove.mPointRecordCallback, 10L);
            MotionEvent motionEvent = this.mMoveEvent;
            if (motionEvent == null || 2 != motionEvent.getAction()) {
                return;
            }
            this.MOVE_POINT.add(((int) this.mMoveEvent.getRawX()) + "|" + ((int) this.mMoveEvent.getRawY()));
            if (this.MOVE_POINT.size() >= 10) {
                this.MOVE_POINT.removeFirst();
            }
        }
    }

    private static class ValueAnimatorUpdateListener implements ValueAnimator.AnimatorUpdateListener {
        private final WeakReference<BallMateMove> mHelp;

        public ValueAnimatorUpdateListener(BallMateMove ballMateMove) {
            this.mHelp = new WeakReference<>(ballMateMove);
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            BallMateMove ballMateMove = this.mHelp.get();
            if (ballMateMove == null) {
                return;
            }
            Object animatedValue = valueAnimator.getAnimatedValue();
            if (animatedValue instanceof PointF) {
                PointF pointF = (PointF) animatedValue;
                if (ballMateMove.mTranslationX == pointF.x && ballMateMove.mTranslationY == pointF.y) {
                    return;
                }
                ballMateMove.mTranslationX = pointF.x;
                ballMateMove.mTranslationY = pointF.y;
                ballMateMove.iSoundBallMove.setViewTranslationXY(ballMateMove.mTranslationX, ballMateMove.mTranslationY);
                ballMateMove.setAudioMoveChange(ballMateMove.mTranslationX, ballMateMove.mTranslationY, BallMoveWay.WAY_AUTO_MOVE);
                return;
            }
            if (animatedValue instanceof State) {
                State state = (State) animatedValue;
                ballMateMove.mTranslationX = state.x;
                ballMateMove.mTranslationY = state.y;
                ballMateMove.mNowAudioZ = state.z;
                ballMateMove.iSoundBallMove.setViewTranslationXY(ballMateMove.mTranslationX, ballMateMove.mTranslationY);
                ballMateMove.iSoundBallMove.setViewTranslationZ(state.z, BallMoveWay.WAY_MEDIA_MOVE);
                ballMateMove.setAudioMoveChange(ballMateMove.mTranslationX, ballMateMove.mTranslationY, BallMoveWay.WAY_MEDIA_MOVE);
            }
        }
    }

    private static class CustomAutoMoveEvaluator implements TypeEvaluator<PointF> {
        private final PointF mPoint;

        private CustomAutoMoveEvaluator() {
            this.mPoint = new PointF();
        }

        @Override // android.animation.TypeEvaluator
        public PointF evaluate(float f, PointF pointF, PointF pointF2) {
            if (pointF == null || pointF2 == null) {
                return this.mPoint;
            }
            this.mPoint.set(pointF.x + ((pointF2.x - pointF.x) * f), pointF.y + (f * (pointF2.y - pointF.y)));
            return this.mPoint;
        }
    }

    private static class CustomMediaMoveEvaluator implements TypeEvaluator<State> {
        private final State mState;

        private CustomMediaMoveEvaluator() {
            this.mState = new State();
        }

        @Override // android.animation.TypeEvaluator
        public State evaluate(float f, State state, State state2) {
            if (state == null || state2 == null) {
                return this.mState;
            }
            this.mState.x = state.x + ((state2.x - state.x) * f);
            this.mState.y = state.y + ((state2.y - state.y) * f);
            this.mState.z = state.z + (f * (state2.z - state.z));
            return this.mState;
        }
    }

    private static class ValueAnimatorListener implements Animator.AnimatorListener {
        private final WeakReference<BallMateMove> mHelp;

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }

        public ValueAnimatorListener(BallMateMove ballMateMove) {
            this.mHelp = new WeakReference<>(ballMateMove);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            BallMateMove ballMateMove = this.mHelp.get();
            if (ballMateMove != null && ballMateMove.isAutoMove) {
                if (ballMateMove.mAngle <= 0 || ballMateMove.mAngle >= 90) {
                    if (ballMateMove.mAngle <= 90 || ballMateMove.mAngle >= 180) {
                        if (ballMateMove.mAngle <= 180 || ballMateMove.mAngle >= 270) {
                            if (ballMateMove.mAngle <= 270 || ballMateMove.mAngle >= 360) {
                                if (ballMateMove.mAngle == 90) {
                                    ballMateMove.onLaunchMoveEngine(180, ballMateMove.mSpeed);
                                    return;
                                }
                                if (ballMateMove.mAngle == 180) {
                                    ballMateMove.onLaunchMoveEngine(0, ballMateMove.mSpeed);
                                    return;
                                }
                                if (ballMateMove.mAngle == 270) {
                                    ballMateMove.onLaunchMoveEngine(90, ballMateMove.mSpeed);
                                    return;
                                } else {
                                    if (ballMateMove.mAngle == 0 || ballMateMove.mAngle == 360) {
                                        ballMateMove.onLaunchMoveEngine(180, ballMateMove.mSpeed);
                                        return;
                                    }
                                    return;
                                }
                            }
                            if (Near.Y == ballMateMove.mNear) {
                                ballMateMove.onLaunchMoveEngine(360 - ballMateMove.mAngle, ballMateMove.mSpeed);
                                return;
                            } else {
                                ballMateMove.onLaunchMoveEngine(360 - (ballMateMove.mAngle - 180), ballMateMove.mSpeed);
                                return;
                            }
                        }
                        if (Near.Y == ballMateMove.mNear) {
                            ballMateMove.onLaunchMoveEngine(360 - ballMateMove.mAngle, ballMateMove.mSpeed);
                            return;
                        } else {
                            ballMateMove.onLaunchMoveEngine(360 - (ballMateMove.mAngle - 180), ballMateMove.mSpeed);
                            return;
                        }
                    }
                    if (Near.Y == ballMateMove.mNear) {
                        ballMateMove.onLaunchMoveEngine(360 - ballMateMove.mAngle, ballMateMove.mSpeed);
                        return;
                    } else {
                        ballMateMove.onLaunchMoveEngine(180 - ballMateMove.mAngle, ballMateMove.mSpeed);
                        return;
                    }
                }
                if (Near.Y == ballMateMove.mNear) {
                    ballMateMove.onLaunchMoveEngine(360 - ballMateMove.mAngle, ballMateMove.mSpeed);
                } else {
                    ballMateMove.onLaunchMoveEngine(180 - ballMateMove.mAngle, ballMateMove.mSpeed);
                }
            }
        }
    }

    private static class State {
        private double timer;
        private float x;
        private float y;
        private float z;

        private State() {
        }

        public String toString() {
            return "State{x=" + this.x + ", y=" + this.y + ", z=" + this.z + ", timer=" + this.timer + '}';
        }
    }
}
