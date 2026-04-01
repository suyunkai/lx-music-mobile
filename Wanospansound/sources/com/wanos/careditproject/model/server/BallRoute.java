package com.wanos.careditproject.model.server;

import com.google.gson.Gson;
import com.wanos.careditproject.utils.EditingUtils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class BallRoute {
    private String BallRouteName = "";
    private long EndSample;
    private long Id;
    private boolean IsLoop;
    private int LoopCount;
    private int LoopDelay;
    private int LoopType;
    private List<SectionModel> Section;
    private int Speed;
    private long StartSample;
    private int Step;
    private long TrackId;
    private long originSampleLength;
    private transient long updateTime;

    public BallRoute() {
        ArrayList arrayList = new ArrayList();
        this.Section = arrayList;
        arrayList.add(new SectionModel());
        this.Step = EditingUtils.objectPosStep;
        setUpdateTime();
    }

    public List<SectionModel> newSection() {
        ArrayList arrayList = new ArrayList();
        this.Section = arrayList;
        arrayList.add(new SectionModel());
        return this.Section;
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public BallRoute m385clone() {
        Gson gson = new Gson();
        return (BallRoute) gson.fromJson(gson.toJson(this), BallRoute.class);
    }

    public void setId(long j) {
        this.Id = j;
        this.BallRouteName = j + "";
        setUpdateTime();
    }

    public long getId() {
        return this.Id;
    }

    public void setBallRouteName(String str) {
        this.BallRouteName = str;
    }

    public String getBallRouteName() {
        return this.BallRouteName;
    }

    public void setTrackId(long j) {
        this.TrackId = j;
    }

    public long getTrackId() {
        return this.TrackId;
    }

    public void setLoopCount(int i) {
        this.LoopCount = i;
    }

    public int getLoopCount() {
        return this.LoopCount;
    }

    public void setLoopType(int i) {
        this.LoopType = i;
    }

    public int getLoopType() {
        return this.LoopType;
    }

    public void setLoopDelay(int i) {
        this.LoopDelay = i;
    }

    public int getLoopDelay() {
        return this.LoopDelay;
    }

    public void setIsLoop(boolean z) {
        this.IsLoop = z;
    }

    public boolean getIsLoop() {
        return this.IsLoop;
    }

    public void setSpeed(int i) {
        this.Speed = i;
    }

    public int getSpeed() {
        return this.Speed;
    }

    public long getStart() {
        return this.StartSample;
    }

    public void setStart(long j) {
        setUpdateTime();
        this.StartSample = j;
    }

    public long getEnd() {
        return this.EndSample;
    }

    public void setEnd(long j) {
        setUpdateTime();
        this.EndSample = j;
    }

    public int getStep() {
        return this.Step;
    }

    public void setStep(int i) {
        this.Step = i;
    }

    public List<SectionModel> getSection() {
        return this.Section;
    }

    public void setSection(List<SectionModel> list) {
        setUpdateTime();
        this.Section = list;
    }

    public List<Float> getEndPos() {
        ArrayList arrayList = new ArrayList(3);
        if (!this.Section.isEmpty()) {
            List<Short> data = this.Section.get(r2.size() - 1).getData();
            if (data != null && data.size() >= 3) {
                int size = data.size();
                arrayList.add(Float.valueOf(getFloat(data.get(size - 3).shortValue())));
                arrayList.add(Float.valueOf(getFloat(data.get(size - 2).shortValue())));
                arrayList.add(Float.valueOf(getFloat(data.get(size - 1).shortValue())));
                return arrayList;
            }
        }
        arrayList.add(Float.valueOf(0.0f));
        arrayList.add(Float.valueOf(0.0f));
        arrayList.add(Float.valueOf(0.0f));
        return arrayList;
    }

    public void mergeBallRoute(BallRoute ballRoute) {
        List<SectionModel> list;
        setUpdateTime();
        if (this.Section == null) {
            this.Section = new ArrayList();
        }
        if (ballRoute != null && (list = ballRoute.Section) != null && list.size() > 0) {
            this.Section.get(0).mergeData(ballRoute.getSection().get(0).getData());
        }
        setEnd(ballRoute.getEnd());
    }

    public List<Float> insertSectionData(long j, float f, float f2, float f3, int i) {
        setUpdateTime();
        ArrayList arrayList = new ArrayList(3);
        short s = getShort(f);
        short s2 = getShort(f2);
        short s3 = getShort(f3);
        if (this.Section == null) {
            this.Section = newSection();
        }
        SectionModel sectionModel = this.Section.get(0);
        long j2 = this.EndSample;
        if (j > j2) {
            EditingUtils.log("insertSectionData sampleNum=" + j + ",EndSample=" + this.EndSample + ", off=" + (j - this.EndSample));
            sectionModel.addData(s, s2, s3);
            setEnd(j);
            arrayList.add(Float.valueOf(f));
            arrayList.add(Float.valueOf(f2));
            arrayList.add(Float.valueOf(f3));
            return arrayList;
        }
        long j3 = this.StartSample;
        if (j3 <= j && j2 >= j) {
            int i2 = (int) ((j - j3) / ((long) this.Step));
            if (j3 == j2) {
                if (sectionModel.getDataSize() == 0) {
                    sectionModel.addData(s, s2, s3);
                } else if (i == 1) {
                    sectionModel.insertXY(i2, s, s2);
                } else {
                    sectionModel.insertZ(i2, s3);
                }
            } else if (i == 1) {
                sectionModel.insertXY(i2, s, s2);
            } else {
                sectionModel.insertZ(i2, s3);
            }
            List<Short> dataItem = sectionModel.getDataItem(i2);
            arrayList.add(Float.valueOf(getFloat(dataItem.get(0).shortValue())));
            arrayList.add(Float.valueOf(getFloat(dataItem.get(1).shortValue())));
            arrayList.add(Float.valueOf(getFloat(dataItem.get(2).shortValue())));
            return arrayList;
        }
        arrayList.add(Float.valueOf(f));
        arrayList.add(Float.valueOf(f2));
        arrayList.add(Float.valueOf(f3));
        return arrayList;
    }

    public List<Float> getSectionDataBySampleNum(long j) {
        SectionModel sectionModel = this.Section.get(0);
        long j2 = j - this.StartSample;
        int i = (int) (j2 / ((long) EditingUtils.objectPosStep));
        int i2 = (int) (j2 % ((long) EditingUtils.objectPosStep));
        if (i2 == 0) {
            return getFloatPos(sectionModel.getDataItem(i));
        }
        ArrayList arrayList = new ArrayList(3);
        List<Short> dataItem = sectionModel.getDataItem(i);
        List<Short> dataItem2 = sectionModel.getDataItem(i + 1);
        for (int i3 = 0; i3 < 3; i3++) {
            arrayList.add(Short.valueOf((short) (dataItem.get(i3).shortValue() + (((dataItem2.get(i3).shortValue() - dataItem.get(i3).shortValue()) * i2) / EditingUtils.objectPosStep))));
        }
        return getFloatPos(arrayList);
    }

    private List<Float> getFloatPos(List<Short> list) {
        ArrayList arrayList = new ArrayList(3);
        for (int i = 0; i < list.size(); i++) {
            arrayList.add(Float.valueOf(getFloat(list.get(i).shortValue())));
        }
        return arrayList;
    }

    private float getFloat(short s) {
        return (s * 1.0f) / EditingUtils.posToShortValue;
    }

    private short getShort(float f) {
        return (short) (f * EditingUtils.posToShortValue);
    }

    public long getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime() {
        this.updateTime = System.currentTimeMillis();
    }
}
