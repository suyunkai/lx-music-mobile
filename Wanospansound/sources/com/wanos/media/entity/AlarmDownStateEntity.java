package com.wanos.media.entity;

/* JADX INFO: loaded from: classes3.dex */
public class AlarmDownStateEntity {
    private boolean isDown;
    private int position;
    private boolean state;

    public AlarmDownStateEntity(int i, boolean z, boolean z2) {
        this.position = i;
        this.isDown = z;
        this.state = z2;
    }

    public int getPosition() {
        return this.position;
    }

    public boolean isDown() {
        return this.isDown;
    }

    public boolean isState() {
        return this.state;
    }
}
