package com.wanos.careditproject.view.opengl.waveUI;

/* JADX INFO: loaded from: classes3.dex */
public class BallRouteNativeModel {
    private long Id;
    private String ballRouteName;
    private long end;
    private long start;

    public BallRouteNativeModel(long j, long j2, long j3, String str) {
        this.Id = j;
        this.start = j2;
        this.end = j3;
        this.ballRouteName = str;
    }

    public long getId() {
        return this.Id;
    }

    public void setId(long j) {
        this.Id = j;
    }

    public long getStart() {
        return this.start;
    }

    public void setStart(long j) {
        this.start = j;
    }

    public long getEnd() {
        return this.end;
    }

    public void setEnd(long j) {
        this.end = j;
    }

    public String getBallRouteName() {
        return this.ballRouteName;
    }

    public void setBallRouteName(String str) {
        this.ballRouteName = str;
    }
}
