package com.wanos.careditproject.model.server;

import android.graphics.PointF;
import com.google.gson.Gson;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.careditproject.utils.NativeObjPos;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class BallRouteSample {
    private static PointF posZero = new PointF(0.0f, 0.0f);
    private List<Float> Pos = new ArrayList();
    private long Seek;

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public BallRouteSample m386clone() {
        Gson gson = new Gson();
        return (BallRouteSample) gson.fromJson(gson.toJson(this), BallRouteSample.class);
    }

    public void setSeek(long j) {
        this.Seek = j;
    }

    public long getSeek() {
        return this.Seek;
    }

    public void setPos(List<Float> list, int i) {
        if (list.size() < 3) {
            return;
        }
        if (i >= 2) {
            float fFloatValue = list.get(0).floatValue();
            float fFloatValue2 = list.get(1).floatValue();
            float fFloatValue3 = list.get(2).floatValue();
            float[] displayPos = NativeObjPos.getDisplayPos(fFloatValue, fFloatValue2);
            float pos = formatPos(fFloatValue3);
            this.Pos.clear();
            this.Pos.add(Float.valueOf(formatPos(displayPos[0])));
            this.Pos.add(Float.valueOf(formatPos(displayPos[1])));
            this.Pos.add(Float.valueOf(pos));
            this.Pos.add(Float.valueOf(formatPos(displayPos[2])));
            this.Pos.add(Float.valueOf(formatPos(displayPos[3])));
            this.Pos.add(Float.valueOf(pos));
            this.Pos.add(Float.valueOf(formatPos(fFloatValue)));
            this.Pos.add(Float.valueOf(formatPos(fFloatValue2)));
            this.Pos.add(Float.valueOf(pos));
            EditingUtils.log("setPos this.Pos = " + this.Pos.toString());
            return;
        }
        this.Pos.clear();
        this.Pos.add(Float.valueOf(formatPos(list.get(0).floatValue())));
        this.Pos.add(Float.valueOf(formatPos(list.get(1).floatValue())));
        this.Pos.add(Float.valueOf(formatPos(list.get(2).floatValue())));
    }

    public void changePosChannel(int i) {
        if (i <= 1 || this.Pos.size() != 3) {
            return;
        }
        float fFloatValue = this.Pos.get(0).floatValue();
        float fFloatValue2 = this.Pos.get(1).floatValue();
        float fFloatValue3 = this.Pos.get(2).floatValue();
        float[] displayPos = NativeObjPos.getDisplayPos(fFloatValue, fFloatValue2);
        float pos = formatPos(fFloatValue3);
        this.Pos.clear();
        this.Pos.add(Float.valueOf(formatPos(displayPos[0])));
        this.Pos.add(Float.valueOf(formatPos(displayPos[1])));
        this.Pos.add(Float.valueOf(pos));
        this.Pos.add(Float.valueOf(formatPos(displayPos[2])));
        this.Pos.add(Float.valueOf(formatPos(displayPos[3])));
        this.Pos.add(Float.valueOf(pos));
        this.Pos.add(Float.valueOf(formatPos(fFloatValue)));
        this.Pos.add(Float.valueOf(formatPos(fFloatValue2)));
        this.Pos.add(Float.valueOf(pos));
    }

    public float formatPos(float f) {
        return (float) (Math.floor(f * 1000.0f) / 1000.0d);
    }

    public List<Float> getCenterPos() {
        List<Float> list = this.Pos;
        if (list != null && list.size() >= 9) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(this.Pos.get(6));
            arrayList.add(this.Pos.get(7));
            arrayList.add(this.Pos.get(8));
            return arrayList;
        }
        if (this.Pos.size() >= 3) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(this.Pos.get(0));
            arrayList2.add(this.Pos.get(1));
            arrayList2.add(this.Pos.get(2));
            return arrayList2;
        }
        return new ArrayList();
    }
}
