package com.wanos.careditproject.view.opengl;

import android.content.Context;
import android.graphics.PointF;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import com.wanos.careditproject.utils.DataHelpAudioTrack;
import com.wanos.careditproject.utils.EditingParams;
import com.wanos.careditproject.utils.EditingUtils;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class BaseObjPosGLView extends GLSurfaceView {
    private PointF ballPosF;
    protected int inW;
    private boolean isMono;
    private boolean isMove;
    private PointF lastTouchPoint1;
    private ObjPosGlViewListener listener;
    protected int outW;
    private BallPoint preBallPoint;
    protected BasePosGLRender render;
    private int touchNum;

    public interface ObjPosGlViewListener {
        void toPlay();

        void updateUI();
    }

    protected void initRender(Context context) {
    }

    protected boolean isLeft() {
        return true;
    }

    public void setPosToUI(float f, float f2, float f3) {
    }

    public BaseObjPosGLView(Context context) {
        this(context, null);
    }

    public BaseObjPosGLView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.lastTouchPoint1 = new PointF();
        this.isMove = false;
        this.ballPosF = new PointF(0.0f, 0.0f);
        this.isMono = true;
        this.outW = 0;
        this.inW = 0;
        this.touchNum = 0;
        this.preBallPoint = new BallPoint();
        setEGLContextClientVersion(3);
        initRender(context);
        setPos(new PointF(0.0f, 0.0f), true);
        setRenderMode(0);
    }

    public void setListener(ObjPosGlViewListener objPosGlViewListener) {
        this.listener = objPosGlViewListener;
    }

    public BasePosGLRender getRender() {
        return this.render;
    }

    public void setChannelState(boolean z) {
        this.isMono = z;
        if (z) {
            this.render.setChannleNumJ(1);
        } else {
            this.render.setChannleNumJ(2);
        }
        requestRender();
    }

    public boolean getChannelState() {
        return this.isMono;
    }

    protected void setPos(PointF pointF, boolean z) {
        if (z || Math.abs(this.ballPosF.x - pointF.x) >= 0.001f || Math.abs(this.ballPosF.y - pointF.y) >= 0.001f) {
            this.ballPosF.x = pointF.x;
            this.ballPosF.y = pointF.y;
            pointerMove(pointF.x, pointF.y);
            requestRender();
        }
    }

    public PointF getPos() {
        return new PointF(this.ballPosF.x, this.ballPosF.y);
    }

    public boolean isTouching() {
        return this.isMove;
    }

    public BallPoint getPreBallPoint() {
        return this.preBallPoint;
    }

    private void setTouchBallPos(boolean z, float f, float f2, boolean z2) {
        long sampleNumUp4096;
        List<Float> ballPosZ;
        ObjPosGlViewListener objPosGlViewListener;
        if (!EditingParams.getInstance().isPosDrawClick()) {
            if (z2) {
                setPosOnce();
                return;
            }
            return;
        }
        if (z2) {
            if (!DataHelpAudioTrack.getIsIsBallPosStop()) {
                int selectTrackIndexForBall = EditingParams.getInstance().getSelectTrackIndexForBall();
                if (isLeft()) {
                    DataHelpAudioTrack.setBallPosXY(selectTrackIndexForBall, this.preBallPoint.sampleNum, f, f2);
                } else {
                    DataHelpAudioTrack.setBallPosZ(selectTrackIndexForBall, this.preBallPoint.sampleNum, f2);
                }
                DataHelpAudioTrack.setBallPosClose();
            }
            ObjPosGlViewListener objPosGlViewListener2 = this.listener;
            if (objPosGlViewListener2 != null) {
                objPosGlViewListener2.updateUI();
            }
            this.preBallPoint.sampleNum = -1L;
            return;
        }
        long progressSampleNum = EditingParams.getInstance().getProgressSampleNum();
        if (this.preBallPoint.sampleNum <= 0 || progressSampleNum - this.preBallPoint.sampleNum >= EditingUtils.objectPosStep) {
            int selectTrackIndexForBall2 = EditingParams.getInstance().getSelectTrackIndexForBall();
            if (z && (objPosGlViewListener = this.listener) != null) {
                objPosGlViewListener.toPlay();
            }
            if (this.preBallPoint.sampleNum < 0) {
                sampleNumUp4096 = EditingUtils.formatSampleNumUp4096(progressSampleNum);
            } else {
                sampleNumUp4096 = this.preBallPoint.sampleNum + ((long) EditingUtils.objectPosStep);
            }
            while (sampleNumUp4096 <= progressSampleNum) {
                float f3 = (sampleNumUp4096 - this.preBallPoint.sampleNum) / (progressSampleNum - this.preBallPoint.sampleNum);
                if (isLeft()) {
                    ballPosZ = DataHelpAudioTrack.setBallPosXY(selectTrackIndexForBall2, sampleNumUp4096, this.preBallPoint.getX() + ((f - this.preBallPoint.getX()) * f3), this.preBallPoint.getY() + ((f2 - this.preBallPoint.getY()) * f3));
                    this.preBallPoint.setXY(ballPosZ.get(0).floatValue(), ballPosZ.get(1).floatValue());
                } else {
                    ballPosZ = DataHelpAudioTrack.setBallPosZ(selectTrackIndexForBall2, sampleNumUp4096, this.preBallPoint.getZ() + ((f2 - this.preBallPoint.getZ()) * f3));
                    this.preBallPoint.setZ(ballPosZ.get(2).floatValue());
                }
                if (ballPosZ == null || ballPosZ.size() < 3) {
                    ballPosZ = DataHelpAudioTrack.getDefaultBallPos();
                }
                EditingParams.getInstance().setOperateBallPosTrackPos(ballPosZ);
                EditingParams.getInstance().setOperateBallPosTrackIndex(selectTrackIndexForBall2);
                this.preBallPoint.sampleNum = sampleNumUp4096;
                sampleNumUp4096 += (long) EditingUtils.objectPosStep;
            }
        }
    }

    public void pointerMove(float f, float f2) {
        this.render.pointerMoveJ(f, f2);
    }

    public boolean pointerDown(float f, float f2) {
        return this.render.pointerDownJ(f, f2);
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0087  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r9) {
        /*
            r8 = this;
            boolean r0 = com.wanos.careditproject.utils.PlayerUtils.isPlaying()
            r1 = 1
            if (r0 == 0) goto L10
            com.wanos.careditproject.utils.EditingParams r0 = com.wanos.careditproject.utils.EditingParams.editingParams
            boolean r0 = r0.isPosDrawClick()
            if (r0 != 0) goto L10
            return r1
        L10:
            int r0 = r9.getActionMasked()
            int r2 = r8.outW
            int r3 = r8.inW
            int r2 = r2 - r3
            r3 = 2
            int r2 = r2 / r3
            boolean r4 = r8.isLeft()
            r5 = 1073741824(0x40000000, float:2.0)
            r6 = 1065353216(0x3f800000, float:1.0)
            if (r4 == 0) goto L32
            float r4 = r9.getX()
            float r7 = (float) r2
            float r4 = r4 - r7
            int r7 = r8.inW
            float r7 = (float) r7
            float r4 = r4 / r7
            float r4 = r4 * r5
            float r4 = r4 - r6
            goto L33
        L32:
            r4 = 0
        L33:
            float r9 = r9.getY()
            float r2 = (float) r2
            float r9 = r9 - r2
            int r2 = r8.inW
            float r2 = (float) r2
            float r9 = r9 / r2
            float r9 = r9 * r5
            float r9 = r6 - r9
            r2 = -1082130432(0xffffffffbf800000, float:-1.0)
            int r5 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r5 >= 0) goto L48
            r4 = r2
            goto L4d
        L48:
            int r5 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r5 <= 0) goto L4d
            r4 = r6
        L4d:
            int r5 = (r9 > r2 ? 1 : (r9 == r2 ? 0 : -1))
            if (r5 >= 0) goto L53
            r6 = r2
            goto L59
        L53:
            int r2 = (r9 > r6 ? 1 : (r9 == r6 ? 0 : -1))
            if (r2 <= 0) goto L58
            goto L59
        L58:
            r6 = r9
        L59:
            r9 = 0
            if (r0 == 0) goto L9a
            if (r0 == r1) goto L87
            r2 = 3
            if (r0 == r3) goto L67
            if (r0 == r2) goto L87
            r2 = 6
            if (r0 == r2) goto L87
            goto Lb4
        L67:
            r8.setTouchBallPos(r9, r4, r6, r9)
            boolean r9 = r8.isMove
            if (r9 == 0) goto Lb4
            int r9 = r8.touchNum
            int r9 = r9 % r2
            if (r9 != 0) goto L79
            r8.pointerMove(r4, r6)
            r8.requestRender()
        L79:
            int r9 = r8.touchNum
            int r9 = r9 + r1
            r8.touchNum = r9
            android.graphics.PointF r9 = r8.ballPosF
            r9.x = r4
            android.graphics.PointF r9 = r8.ballPosF
            r9.y = r6
            goto Lb4
        L87:
            java.lang.String r0 = "AudioTracksView onTouchEvent ACTION_UP "
            com.wanos.careditproject.utils.EditingUtils.log(r0)
            r8.setTouchBallPos(r9, r4, r6, r1)
            com.wanos.careditproject.view.opengl.BasePosGLRender r0 = r8.render
            r0.showLineJ(r9)
            r8.requestRender()
            r8.isMove = r9
            goto Lb4
        L9a:
            r8.touchNum = r9
            boolean r0 = r8.pointerDown(r4, r6)
            r8.isMove = r0
            com.wanos.careditproject.view.opengl.BasePosGLRender r0 = r8.render
            r0.showLineJ(r1)
            boolean r0 = r8.isMove
            if (r0 == 0) goto Lae
            com.wanos.careditproject.utils.DataHelpAudioTrack.setBallPosInit()
        Lae:
            r8.setTouchBallPos(r1, r4, r6, r9)
            r8.requestRender()
        Lb4:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wanos.careditproject.view.opengl.BaseObjPosGLView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void setPosOnce() {
        long progressSampleNum = EditingParams.getInstance().getProgressSampleNum();
        int selectTrackIndexForBall = EditingParams.getInstance().getSelectTrackIndexForBall();
        if (isLeft()) {
            PointF pos = getPos();
            DataHelpAudioTrack.setBallPosInit();
            DataHelpAudioTrack.setBallPosXY(selectTrackIndexForBall, progressSampleNum, pos.x, pos.y);
            DataHelpAudioTrack.setBallPosClose();
            return;
        }
        PointF pos2 = getPos();
        DataHelpAudioTrack.setBallPosInit();
        DataHelpAudioTrack.setBallPosZ(selectTrackIndexForBall, progressSampleNum, pos2.y);
        DataHelpAudioTrack.setBallPosClose();
    }

    public class BallPoint {
        private float[] posList = new float[3];
        public long sampleNum = -1;

        public BallPoint() {
        }

        public float getX() {
            return this.posList[0];
        }

        public float getY() {
            return this.posList[1];
        }

        public float getZ() {
            return this.posList[2];
        }

        public void setXY(float f, float f2) {
            float[] fArr = this.posList;
            fArr[0] = f;
            fArr[1] = f2;
        }

        public void setZ(float f) {
            this.posList[2] = f;
        }
    }
}
