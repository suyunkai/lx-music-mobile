package com.wanos.careditproject.event;

import com.wanos.bean.ProjectInfo;

/* JADX INFO: loaded from: classes3.dex */
public class ProjectChangeEvent {
    private int oprateType;
    private ProjectInfo projectInfo;
    private int type;

    public ProjectChangeEvent(int i, int i2, ProjectInfo projectInfo) {
        this.oprateType = i;
        this.type = i2;
        this.projectInfo = projectInfo;
    }

    public int getOprateType() {
        return this.oprateType;
    }

    public int getType() {
        return this.type;
    }

    public ProjectInfo getProjectInfo() {
        return this.projectInfo;
    }

    public void setOprateType(int i) {
        this.oprateType = i;
    }

    public void setType(int i) {
        this.type = i;
    }

    public void setProjectInfo(ProjectInfo projectInfo) {
        this.projectInfo = projectInfo;
    }
}
