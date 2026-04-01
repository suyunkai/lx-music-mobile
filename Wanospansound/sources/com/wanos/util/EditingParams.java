package com.wanos.util;

import com.wanos.WanosCommunication.bean.EditGetPictureListBean;
import com.wanos.bean.ProjectInfo;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class EditingParams {
    public static EditingParams editingParams;
    public ProjectInfo curProjectInfo;
    private OperateBallPosInfo operateBallPosInfo;
    private List<EditGetPictureListBean.ProjectPictureInfo> picList;
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

    public void setSelectTrackIndex(int i) {
        this.selectTrackIndex = i;
    }

    public int getSelectTrackIndex() {
        return this.selectTrackIndex;
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
        this.progressSampleNum = j;
    }

    public long getProgressSampleNum() {
        return this.progressSampleNum;
    }

    public long getProgressSampleNumPreview() {
        return this.progressSampleNumPreview;
    }

    public void setProgressSampleNumPreview(long j) {
        this.progressSampleNumPreview = j;
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
}
