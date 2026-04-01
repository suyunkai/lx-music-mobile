package com.wanos.careditproject.event;

/* JADX INFO: loaded from: classes3.dex */
public class DeleteWorkEvent {
    private String workId;

    public DeleteWorkEvent(String str) {
        this.workId = str;
    }

    public String getProjectInfoId() {
        return this.workId;
    }
}
