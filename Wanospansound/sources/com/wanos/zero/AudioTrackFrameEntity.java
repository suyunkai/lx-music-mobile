package com.wanos.zero;

import android.util.SparseBooleanArray;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class AudioTrackFrameEntity {
    private final ArrayList<BallPosEntity> frameList = new ArrayList<>();
    private final SparseBooleanArray mLoopWeek = new SparseBooleanArray();

    public SparseBooleanArray getLoopWeek() {
        return this.mLoopWeek;
    }

    public void setLoopWeekState(int i, boolean z) {
        this.mLoopWeek.put(i, z);
    }

    public ArrayList<BallPosEntity> getFrameList() {
        return this.frameList;
    }
}
