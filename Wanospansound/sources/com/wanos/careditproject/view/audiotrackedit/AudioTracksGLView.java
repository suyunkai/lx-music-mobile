package com.wanos.careditproject.view.audiotrackedit;

import android.content.Context;
import android.graphics.PointF;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import com.wanos.careditproject.R;
import com.wanos.careditproject.model.server.ClipModel;
import com.wanos.careditproject.utils.DataHelpAudioTrack;
import com.wanos.careditproject.utils.EditingParams;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.careditproject.view.opengl.waveUI.WaveUIGLRender;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class AudioTracksGLView extends GLSurfaceView {
    private AudioTrackBgView bgView;
    private Map<String, ClipModel> clipModelMap;
    DisplayMetrics dm;
    private boolean isMove;
    private boolean isMoveView;
    private int isScaling;
    private boolean isStopScroll;
    private float lastDistance;
    private PointF lastTouchPoint1;
    private PointF lastTouchPoint2;
    private LeftUIListener leftUIListener;
    private int moveViewCount;
    int pcmToolHeight;
    int pcmToolWidth;
    private PcmWaveViewListener pcmWaveViewListener;
    private PointF pivotPoint;
    private PointF preDeltaPos;
    private int prePasteTrackIndex;
    private PointF preTouchPoint2;
    private WaveViewInfo preWaveViewInfo;
    private float prex;
    private String recordClipId;
    private float scaleFactor;
    private int scaleIndex;
    public int selectPcmWaveState;
    private List<String> testIdList;
    private boolean touchIsSave;
    private int touchMoveCount;
    public int trackMarginH;
    public int trackViewH;
    public int trackViewTop;
    WaveUIGLRender waveUIGLRender;
    private HashMap<String, WaveViewInfo> waveViewInfoMap;
    private int widthOffset;

    public interface LeftUIListener {
        void addTrack(int i);

        void moveX(int i);

        void moveY(int i);

        void redraw();

        void scaleX(float f);
    }

    public interface PcmWaveViewListener {
        void hidePasteView();

        void hidePcmWaveViewTools();

        void showPasteView(int i, int i2);

        void showPcmWaveViewTools(int i, int i2, int i3, boolean z);
    }

    public void drawBallSampleViewById(String str) {
    }

    public void drawPcmWaveView(String str) {
    }

    public void initBallPosViewGroup() {
    }

    public void initTrackView() {
    }

    public void setBallViewStartOfEndOff(boolean z, String str, long j) {
    }

    public void setScrollY(float f) {
    }

    public List<String> updateBallInfoByTrackIndex(int i, int i2, String str) {
        return null;
    }

    public void updateBallSampleViewList(List<String> list, int i) {
    }

    public void updateClipViewByTrackIndex(int i) {
    }

    public void vScrollTo(int i, int i2) {
    }

    public void viewScrollTo(int i, int i2, boolean z) {
    }

    public AudioTracksGLView(Context context) {
        super(context, null);
        this.lastTouchPoint1 = new PointF();
        this.lastTouchPoint2 = new PointF();
        this.preTouchPoint2 = new PointF();
        this.pivotPoint = new PointF(0.0f, 0.0f);
        this.scaleFactor = 1.0f;
        this.isScaling = 0;
        this.scaleIndex = 0;
        this.moveViewCount = 0;
        this.touchMoveCount = 0;
        this.isMove = false;
        this.isMoveView = false;
        this.preDeltaPos = new PointF();
        this.isStopScroll = true;
        this.touchIsSave = false;
        this.testIdList = new ArrayList();
        this.waveViewInfoMap = new HashMap<>();
        this.selectPcmWaveState = 0;
        this.prex = 0.0f;
        this.pcmToolWidth = 0;
        this.pcmToolHeight = 0;
        this.prePasteTrackIndex = -1;
    }

    public AudioTracksGLView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.lastTouchPoint1 = new PointF();
        this.lastTouchPoint2 = new PointF();
        this.preTouchPoint2 = new PointF();
        this.pivotPoint = new PointF(0.0f, 0.0f);
        this.scaleFactor = 1.0f;
        this.isScaling = 0;
        this.scaleIndex = 0;
        this.moveViewCount = 0;
        this.touchMoveCount = 0;
        this.isMove = false;
        this.isMoveView = false;
        this.preDeltaPos = new PointF();
        this.isStopScroll = true;
        this.touchIsSave = false;
        this.testIdList = new ArrayList();
        this.waveViewInfoMap = new HashMap<>();
        this.selectPcmWaveState = 0;
        this.prex = 0.0f;
        this.pcmToolWidth = 0;
        this.pcmToolHeight = 0;
        this.prePasteTrackIndex = -1;
        setEGLContextClientVersion(3);
        initRenderView(context);
        init();
    }

    public void initRenderView(Context context) {
        WaveUIGLRender waveUIGLRender = new WaveUIGLRender(context.getAssets());
        this.waveUIGLRender = waveUIGLRender;
        setRenderer(waveUIGLRender);
        setRenderMode(0);
    }

    public void setRenderInitData(int i, int i2, int i3) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.dm = displayMetrics;
        int i4 = displayMetrics.widthPixels - i;
        int iDp2px = i2 - EditingUtils.dp2px(EditingUtils.context, 300.0f);
        EditingUtils.log("binding.viewAudioTrack dm.widthPixels = " + this.dm.widthPixels + ", w = " + i4 + ", toph=" + iDp2px);
        WaveUIGLRender.initRender(this.dm.widthPixels, this.dm.heightPixels, i4, i2, i3, this.trackViewH, this.trackMarginH, iDp2px, EditingUtils.getTotalTimeOfSecond());
        requestRender();
    }

    public void init() {
        this.clipModelMap = new HashMap();
        EditingParams.getInstance().setTrackScaleFactor(1.0f);
        this.dm = getResources().getDisplayMetrics();
        this.preWaveViewInfo = new WaveViewInfo();
        this.widthOffset = this.dm.widthPixels / 2;
        EditingUtils.setScreenWidth(this.dm.widthPixels);
        Context context = getContext();
        EditingUtils.log("AudioTracksGLView init");
        this.trackViewH = context.getResources().getDimensionPixelSize(R.dimen.track_height);
        this.trackMarginH = EditingUtils.dp2px(context, EditingUtils.DPtrackMarginH);
        this.trackViewTop = EditingUtils.dp2px(context, EditingUtils.DPtrackViewTop + 1);
        initTrackView();
    }

    @Override // android.opengl.GLSurfaceView, android.view.SurfaceView, android.view.View
    protected void onDetachedFromWindow() {
        this.isStopScroll = true;
        super.onDetachedFromWindow();
    }

    public void update() {
        this.waveUIGLRender.flushWaveData();
        requestRender();
    }

    public void drawAll() {
        requestRender();
    }

    public void addClipView(int i, String str, ClipModel clipModel, int i2) {
        EditingUtils.log("addClipView");
        EditingParams.getInstance().setProgressSampleNum(clipModel.getEnd());
        update();
    }

    public void viewScrollToSampleNum(long j) {
        requestRender();
    }

    public void reset() {
        EditingParams.getInstance().setProgressSampleNum(0L);
        update();
    }

    public void toEnd() {
        EditingParams.getInstance().setProgressSampleNum(DataHelpAudioTrack.getMaxSampleNum());
        update();
    }

    public void drawPcmWaveViewById(String str) {
        update();
    }

    public void moveToClipFade(String str, boolean z) {
        long end;
        ClipModel clipById = DataHelpAudioTrack.getClipById(str);
        if (clipById != null) {
            if (z) {
                end = clipById.getStart() + ((long) clipById.getFadeIn());
            } else {
                end = clipById.getEnd() - ((long) clipById.getFadeOut());
            }
            EditingParams.getInstance().setProgressSampleNum(end);
            update();
        }
    }

    public List<String> updateBallInfoByTrackIndexV1(int i) {
        return new ArrayList();
    }

    protected int getWaveViewX(int i) {
        return this.widthOffset + i;
    }

    protected int checkWaveViewX(int i) {
        getResources().getDisplayMetrics();
        int i2 = this.widthOffset;
        return i < i2 ? i2 : i;
    }

    public long px2SampleNum(int i) {
        return (((long) i) * ((long) DataHelpAudioTrack.getSampleRate())) / ((long) EditingUtils.dp2px(getContext(), EditingUtils.distanceWidthMax * EditingUtils.minLineCountOfMaxLine));
    }

    public long pxWithUI2SampleNum(int i) {
        return px2SampleNum(i - this.widthOffset);
    }

    private void allPcmViewRedraw() {
        update();
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return super.dispatchTouchEvent(motionEvent);
    }

    public void setLeftUIListener(LeftUIListener leftUIListener) {
        this.leftUIListener = leftUIListener;
    }

    public void setPcmWaveViewListener(PcmWaveViewListener pcmWaveViewListener) {
        this.pcmWaveViewListener = pcmWaveViewListener;
    }

    public void selectTrackLine(int i) {
        EditingUtils.log("AudioTracksGLView selectTrackIndex index = " + i);
        WaveUIGLRender waveUIGLRender = this.waveUIGLRender;
        if (waveUIGLRender != null) {
            waveUIGLRender.selectTrack(i);
            requestRender();
        }
    }

    public void showAllGLUI() {
        if (this.waveUIGLRender != null) {
            this.waveUIGLRender.showAllUI(DataHelpAudioTrack.getMaxSampleNum());
            requestRender();
        }
    }

    public PointF moveView(float f, float f2) {
        return new PointF(f, f2);
    }

    public int getInfoX(int i) {
        return ((int) ((i - this.widthOffset) * EditingParams.getInstance().getTrackScaleFactor())) + this.widthOffset;
    }

    public class SelectPcmInfo {
        public String id;
        public int touchState;
        public int type;

        public SelectPcmInfo() {
        }
    }

    public int getTouchState(PointF pointF, int i, int i2, int i3, int i4, boolean z) {
        int i5 = z ? 30 : 10;
        int scrollX = getScrollX() + ((int) pointF.x);
        int scrollY = getScrollY() + ((int) pointF.y);
        int infoX = getInfoX(i);
        int infoX2 = getInfoX(i + i2);
        if (scrollX <= infoX - i5 || scrollX >= infoX2 + i5 || scrollY <= i3 || scrollY >= i3 + i4) {
            return -1;
        }
        if (infoX2 - infoX < i5 * 3) {
            i5 = 5;
        }
        if (scrollX < infoX + i5) {
            return 1;
        }
        return scrollX > infoX2 - i5 ? 2 : 0;
    }

    public void moveView(int i) {
        if (getHeight() != 0) {
            float height = ((i * 1.0f) / getHeight()) * 2.0f;
            EditingUtils.log("binding.viewTrackLeft moveView y = " + height);
            this.waveUIGLRender.moveY(height);
            requestRender();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:63:0x017e  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r10) {
        /*
            Method dump skipped, instruction units count: 571
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wanos.careditproject.view.audiotrackedit.AudioTracksGLView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void setPcmViewStartOfEndOff(boolean z, String str, long j) {
        if (z) {
            DataHelpAudioTrack.setClipStart(str, j);
        } else {
            DataHelpAudioTrack.setClipEnd(str, j);
        }
    }

    private float getDistance(PointF pointF, PointF pointF2) {
        float f = pointF.x - pointF2.x;
        float f2 = pointF.y - pointF2.y;
        return (float) Math.sqrt((f * f) + (f2 * f2));
    }

    public void setPcmToolBarSize(int i, int i2) {
        this.pcmToolWidth = i;
        this.pcmToolHeight = i2;
    }

    public void showPasteView(float f, float f2) {
        int selectTrackIndex = EditingParams.getInstance().getSelectTrackIndex();
        if (!EditingParams.getInstance().getCopyClipId().equals("") && this.prePasteTrackIndex == selectTrackIndex) {
            this.pcmWaveViewListener.showPasteView((int) (((f + 1.0f) * getWidth()) / 2.0f), (int) ((((1.0f - f2) * getHeight()) / 2.0f) - 50.0f));
        }
        this.prePasteTrackIndex = selectTrackIndex;
    }

    public void showPcmToolBarV2(int i, float f, float f2, String str, boolean z) {
        EditingUtils.log("showPcmToolBarV2 waveId=" + str + ", showPosY=" + f2);
        EditingParams.getInstance().setCurSelectPcmWaveId(str);
        this.pcmWaveViewListener.showPcmWaveViewTools(i, (int) (((f + 1.0f) * getWidth()) / 2.0f), (int) (((1.0f - f2) * getHeight()) / 2.0f), z);
    }

    public void delWaveId(String str) {
        WaveUIGLRender waveUIGLRender = this.waveUIGLRender;
        if (waveUIGLRender != null) {
            waveUIGLRender.delWaveId(str);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x003b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void showPcmToolBar(int r3, android.graphics.PointF r4, java.lang.String r5, int r6) {
        /*
            r2 = this;
            java.util.HashMap<java.lang.String, com.wanos.careditproject.view.audiotrackedit.AudioTracksGLView$WaveViewInfo> r0 = r2.waveViewInfoMap
            boolean r0 = r0.containsKey(r5)
            if (r0 == 0) goto L3b
            java.util.HashMap<java.lang.String, com.wanos.careditproject.view.audiotrackedit.AudioTracksGLView$WaveViewInfo> r0 = r2.waveViewInfoMap
            java.lang.Object r5 = r0.get(r5)
            com.wanos.careditproject.view.audiotrackedit.AudioTracksGLView$WaveViewInfo r5 = (com.wanos.careditproject.view.audiotrackedit.AudioTracksGLView.WaveViewInfo) r5
            int r0 = r5.x
            int r0 = r2.getInfoX(r0)
            int r1 = r5.x
            int r5 = r5.w
            int r1 = r1 + r5
            int r5 = r2.getInfoX(r1)
            int r1 = r2.getScrollX()
            int r0 = r0 - r1
            android.util.DisplayMetrics r1 = r2.dm
            int r1 = r1.widthPixels
            int r1 = r1 / 2
            if (r0 >= r1) goto L3b
            int r0 = r2.getScrollX()
            int r5 = r5 - r0
            android.util.DisplayMetrics r0 = r2.dm
            int r0 = r0.widthPixels
            int r0 = r0 / 2
            if (r5 <= r0) goto L3b
            r5 = 1
            goto L3c
        L3b:
            r5 = 0
        L3c:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "showPcmToolBar rowNum = "
            r0.<init>(r1)
            java.lang.StringBuilder r0 = r0.append(r6)
            java.lang.String r0 = r0.toString()
            com.wanos.careditproject.utils.EditingUtils.log(r0)
            int r0 = r2.trackViewH
            int r1 = r2.trackMarginH
            int r0 = r0 + r1
            int r0 = r0 * r6
            int r6 = r2.trackViewTop
            int r0 = r0 + r6
            android.content.Context r6 = r2.getContext()
            r1 = 1115815936(0x42820000, float:65.0)
            int r6 = com.wanos.careditproject.utils.EditingUtils.dp2px(r6, r1)
            int r0 = r0 - r6
            int r6 = r2.getScrollY()
            int r0 = r0 - r6
            float r4 = r4.x
            int r6 = r2.getScrollX()
            float r6 = (float) r6
            float r4 = r4 - r6
            int r4 = (int) r4
            com.wanos.careditproject.view.audiotrackedit.AudioTracksGLView$PcmWaveViewListener r6 = r2.pcmWaveViewListener
            r6.showPcmWaveViewTools(r3, r4, r0, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wanos.careditproject.view.audiotrackedit.AudioTracksGLView.showPcmToolBar(int, android.graphics.PointF, java.lang.String, int):void");
    }

    public void hidePcmToolBar() {
        this.pcmWaveViewListener.hidePcmWaveViewTools();
        this.pcmWaveViewListener.hidePasteView();
    }

    public void splitPcmWaveView() {
        String curSelectPcmWaveId = EditingParams.getInstance().getCurSelectPcmWaveId();
        ClipModel clipById = DataHelpAudioTrack.getClipById(curSelectPcmWaveId);
        if (clipById != null) {
            long progressSampleNum = EditingParams.getInstance().getProgressSampleNum() - clipById.getStart();
            if (progressSampleNum < 0) {
                EditingUtils.showTips("分割失败！");
            } else if (DataHelpAudioTrack.splitClip(curSelectPcmWaveId, progressSampleNum)) {
                update();
            } else {
                EditingUtils.showTips("分割失败！");
            }
        }
    }

    public void copyPcmWaveView() {
        boolean copyIsClip = EditingParams.getInstance().getCopyIsClip();
        String copyClipId = EditingParams.getInstance().getCopyClipId();
        long progressSampleNum = EditingParams.getInstance().getProgressSampleNum();
        if (copyIsClip) {
            DataHelpAudioTrack.DataHelpResult dataHelpResultCopyClip = DataHelpAudioTrack.copyClip(copyClipId, EditingParams.getInstance().getSelectTrackIndex(), progressSampleNum);
            if (dataHelpResultCopyClip.isSuccess()) {
                update();
                EditingParams.getInstance().setCopyClipOrBallId("", true);
            } else {
                if (dataHelpResultCopyClip.getId() == 2) {
                    EditingParams.getInstance().setCopyClipOrBallId("", true);
                }
                EditingUtils.showTips(dataHelpResultCopyClip.getErrMsg());
            }
        } else {
            int iCopyBallSample = DataHelpAudioTrack.copyBallSample(copyClipId, EditingParams.getInstance().getSelectTrackIndex(), progressSampleNum);
            if (iCopyBallSample == 0) {
                update();
                EditingParams.getInstance().setCopyClipOrBallId("", true);
            } else if (iCopyBallSample == -2) {
                EditingUtils.showTips("请放置至相应轨道(声道数不一致）");
            } else {
                EditingUtils.showTips("复制失败");
            }
        }
        hidePcmToolBar();
    }

    public class WaveViewInfo {
        public static final int TYPEBALL = 1;
        public static final int TYPEPCM = 0;
        public int h;
        public int type;
        public int w;
        public int x;
        public int y;
        public int trackIndex = 0;
        public String id = "";
        public String audioPath = "";

        public WaveViewInfo() {
        }
    }
}
