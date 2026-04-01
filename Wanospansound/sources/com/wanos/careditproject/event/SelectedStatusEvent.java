package com.wanos.careditproject.event;

/* JADX INFO: loaded from: classes3.dex */
public class SelectedStatusEvent {
    private int type;

    public SelectedStatusEvent() {
    }

    public SelectedStatusEvent(int i) {
        this.type = i;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }
}
