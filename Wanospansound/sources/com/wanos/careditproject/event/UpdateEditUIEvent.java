package com.wanos.careditproject.event;

/* JADX INFO: loaded from: classes3.dex */
public class UpdateEditUIEvent {
    private Object data;
    private eventType type;

    public enum eventType {
        updateTrackLeftView,
        updateTime,
        other
    }

    public UpdateEditUIEvent(eventType eventtype) {
        this.type = eventtype;
    }

    public UpdateEditUIEvent(eventType eventtype, Object obj) {
        this.type = eventtype;
        this.data = obj;
    }

    public eventType getType() {
        return this.type;
    }

    public void setType(eventType eventtype) {
        this.type = eventtype;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object obj) {
        this.data = obj;
    }
}
