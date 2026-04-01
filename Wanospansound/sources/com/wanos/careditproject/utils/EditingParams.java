package com.wanos.careditproject.utils;

import com.wanos.bean.EditGetPictureListBean;
import com.wanos.bean.ProjectInfo;
import com.wanos.careditproject.data.bean.WorkCateBean;
import com.wanos.careditproject.event.UpdateEditUIEvent;
import com.wanos.careditproject.utils.metronome.metronome;
import com.wanos.careditproject.view.opengl.waveUI.WaveUIGLRender;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes3.dex */
public class EditingParams {
    public static EditingParams editingParams;
    private List<WorkCateBean> cateBeanList;
    public ProjectInfo curProjectInfo;
    private metronome metronome;
    private OperateBallPosInfo operateBallPosInfo;
    private List<EditGetPictureListBean.ProjectPictureInfo> picList;
    private String replaceClipId;
    public int selectTrackIndex = 0;
    public String curSelectPcmWaveId = "";
    public String curSelectBallPosId = "";
    public String copyClipId = "";
    private long progressSampleNum = 0;
    private long progressSampleNumPreview = 0;
    private boolean copyIsClip = false;
    private float trackScaleFactor = 1.0f;
    private int selectTrackIndexForBall = -1;
    private boolean projectDataIsInit = false;
    private String projectId = "";
    private String userId = "";
    private int previewColumn = 50;
    private int maxMemOfM = 200;
    private boolean posDrawClick = false;
    private boolean isShowClickTrack = false;

    public static EditingParams getInstance() {
        if (editingParams == null) {
            editingParams = new EditingParams();
        }
        return editingParams;
    }

    public void init() {
        this.selectTrackIndex = 0;
        this.curSelectPcmWaveId = "";
        this.curSelectBallPosId = "";
        this.copyClipId = "";
        this.progressSampleNum = 0L;
        this.progressSampleNumPreview = 0L;
        this.trackScaleFactor = 1.0f;
        this.selectTrackIndexForBall = -1;
        this.curProjectInfo = null;
    }

    public void setProjectId(String str) {
        this.projectId = str;
    }

    public String getProjectId() {
        return this.projectId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public String getUserId() {
        return this.userId;
    }

    public int getSelectTrackIndex() {
        int curSelectTrackIndex = WaveUIGLRender.getCurSelectTrackIndex();
        EditingUtils.log("getSelectTrackIndex index = " + curSelectTrackIndex);
        if (curSelectTrackIndex < 0) {
            return 0;
        }
        return curSelectTrackIndex;
    }

    public int getSelectTrackIndex2() {
        int curSelectTrackIndex = WaveUIGLRender.getCurSelectTrackIndex();
        EditingUtils.log("getSelectTrackIndex2 index = " + curSelectTrackIndex);
        return curSelectTrackIndex;
    }

    public void setCurSelectPcmWaveId(String str) {
        this.curSelectPcmWaveId = str;
    }

    public String getCurSelectPcmWaveId() {
        return this.curSelectPcmWaveId;
    }

    public void setCurSelectBallPosId(String str) {
        this.curSelectBallPosId = str;
    }

    public String getCurSelectBallPosId() {
        return this.curSelectBallPosId;
    }

    public void setCopyClipOrBallId(String str, boolean z) {
        this.copyClipId = str;
        this.copyIsClip = z;
    }

    public String getCopyClipId() {
        return this.copyClipId;
    }

    public boolean getCopyIsClip() {
        return this.copyIsClip;
    }

    public void setProgressSampleNum(long j) {
        setProgressSampleNum(j, true, false);
    }

    public void setProgressSampleNum(long j, boolean z, boolean z2) {
        long sampleNum = EditingUtils.formatSampleNum(j);
        if (!z2) {
            WaveUIGLRender.setCurSampleNum(sampleNum);
        }
        this.progressSampleNum = sampleNum;
        if (z) {
            EventBus.getDefault().post(new UpdateEditUIEvent(UpdateEditUIEvent.eventType.updateTime));
        }
    }

    public long getProgressSampleNum() {
        long j = this.progressSampleNum;
        if (j != EditingUtils.formatSampleNum(j)) {
            EditingUtils.log("leiErr newStart = " + this.progressSampleNum + ", format = " + EditingUtils.formatSampleNum(this.progressSampleNum));
        }
        return this.progressSampleNum;
    }

    public long getProgressSampleNumPreview() {
        return this.progressSampleNum;
    }

    public void setProgressSampleNumPreview(long j) {
        setProgressSampleNum(j);
    }

    public void setProgressSampleNumPreviewAi(long j) {
        this.progressSampleNum = EditingUtils.formatSampleNum(j);
    }

    public float getTrackScaleFactor() {
        return this.trackScaleFactor;
    }

    public void setTrackScaleFactor(float f) {
        this.trackScaleFactor = f;
    }

    public int getSelectTrackIndexForBall() {
        return this.selectTrackIndexForBall;
    }

    public void setSelectTrackIndexForBall(int i) {
        this.selectTrackIndexForBall = i;
    }

    public void setProjectDataIsInit(boolean z) {
        this.projectDataIsInit = z;
    }

    public boolean getProjectDataIsInit() {
        return this.projectDataIsInit;
    }

    public void setShowClickTrack(boolean z) {
        this.isShowClickTrack = z;
    }

    public boolean getShowClickTrack() {
        return this.isShowClickTrack;
    }

    public OperateBallPosInfo getOperateBallPosInfo() {
        return this.operateBallPosInfo;
    }

    public void setOperateBallPosTrackIndex(int i) {
        if (this.operateBallPosInfo == null) {
            this.operateBallPosInfo = new OperateBallPosInfo();
        }
        this.operateBallPosInfo.trackIndex = i;
    }

    public void setOperateBallPosTrackPos(List<Float> list) {
        if (this.operateBallPosInfo == null) {
            OperateBallPosInfo operateBallPosInfo = new OperateBallPosInfo();
            this.operateBallPosInfo = operateBallPosInfo;
            operateBallPosInfo.trackIndex = -1;
        }
        this.operateBallPosInfo.pos = list;
    }

    public class OperateBallPosInfo {
        public List<Float> pos;
        public int trackIndex;

        public OperateBallPosInfo() {
        }
    }

    public void setPreviewColumn(int i) {
        this.previewColumn = i;
    }

    public int getPreviewColumn() {
        return this.previewColumn;
    }

    public void setMetronome(metronome metronomeVar) {
        this.metronome = metronomeVar;
    }

    public metronome getMetronome() {
        return this.metronome;
    }

    public int getMaxMemOfM() {
        return this.maxMemOfM;
    }

    public void setMaxMemOfM(int i) {
        this.maxMemOfM = i;
    }

    public ProjectInfo getCurProjectInfo() {
        return this.curProjectInfo;
    }

    public void setCurProjectInfo(ProjectInfo projectInfo) {
        this.curProjectInfo = projectInfo;
    }

    public List<EditGetPictureListBean.ProjectPictureInfo> getPicList() {
        return this.picList;
    }

    public void setPicList(List<EditGetPictureListBean.ProjectPictureInfo> list) {
        this.picList = list;
    }

    public List<WorkCateBean> getCateBeanList() {
        return this.cateBeanList;
    }

    public void setCateBeanList(List<WorkCateBean> list) {
        this.cateBeanList = list;
    }

    public boolean isPosDrawClick() {
        return this.posDrawClick;
    }

    public void setPosDrawClick(boolean z) {
        this.posDrawClick = z;
    }

    public String getReplaceClipId() {
        return this.replaceClipId;
    }

    public void setReplaceClipId(String str) {
        this.replaceClipId = str;
    }
}
