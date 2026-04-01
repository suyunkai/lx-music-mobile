package com.wanos.careditproject.model.server;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class PosModel {
    private List<Float> Pos = new ArrayList();
    private long Seek;

    public void setSeek(long j) {
        this.Seek = j;
    }

    public long getSeek() {
        return this.Seek;
    }

    public void setPos(List<Float> list) {
        this.Pos = list;
    }

    public List<Float> getPos() {
        return this.Pos;
    }
}
