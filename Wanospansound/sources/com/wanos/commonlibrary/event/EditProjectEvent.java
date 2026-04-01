package com.wanos.commonlibrary.event;

/* JADX INFO: loaded from: classes3.dex */
public class EditProjectEvent {
    public Object param;
    public ProjectEventType type;

    public enum ProjectEventType {
        HideDialog,
        UpdateUI,
        ShowTips,
        ShowTipsAndUpdateUI,
        ProjectExported
    }

    public EditProjectEvent(ProjectEventType projectEventType) {
        this.type = projectEventType;
    }

    public EditProjectEvent(ProjectEventType projectEventType, Object obj) {
        this.type = projectEventType;
        this.param = obj;
    }

    public ProjectEventType getType() {
        return this.type;
    }

    public void setType(ProjectEventType projectEventType) {
        this.type = projectEventType;
    }

    public Object getParam() {
        return this.param;
    }

    public void setParam(Object obj) {
        this.param = obj;
    }
}
