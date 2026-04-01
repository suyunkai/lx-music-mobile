package com.wanos.careditproject.model.server;

import com.google.gson.Gson;

/* JADX INFO: loaded from: classes3.dex */
public class ClipModel {
    private int DB;
    private long End;
    private int FadeIn;
    private int FadeOut;
    private long ID;
    private int NumChannels;
    private long OriginStart;
    private long ParentTrack;
    private long SampleNum;
    private long Start;
    private int Status;
    private String ShowName = "";
    private String FadeInType = "";
    private String FadeOutType = "";
    private float speed = 1.0f;
    private MetaModel Meta = new MetaModel();
    private OriginModel Origin = new OriginModel();
    private DecodedModel Decoded = new DecodedModel();

    public ClipModel(long j, long j2) {
        this.ID = j;
        this.ParentTrack = j2;
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public ClipModel m387clone() {
        Gson gson = new Gson();
        return (ClipModel) gson.fromJson(gson.toJson(this), ClipModel.class);
    }

    public void setID(long j) {
        this.ID = j;
    }

    public long getID() {
        return this.ID;
    }

    public void setShowName(String str) {
        this.ShowName = str;
    }

    public String getShowName() {
        return this.ShowName;
    }

    public void setParentTrack(long j) {
        this.ParentTrack = j;
    }

    public long getParentTrack() {
        return this.ParentTrack;
    }

    public void setMeta(MetaModel metaModel) {
        this.Meta = metaModel;
    }

    public MetaModel getMeta() {
        return this.Meta;
    }

    public void setOrigin(OriginModel originModel) {
        this.Origin = originModel;
    }

    public OriginModel getOrigin() {
        return this.Origin;
    }

    public void setDecoded(DecodedModel decodedModel) {
        this.Decoded = decodedModel;
    }

    public DecodedModel getDecoded() {
        return this.Decoded;
    }

    public void setDB(int i) {
        this.DB = i;
    }

    public int getDB() {
        return this.DB;
    }

    public void setStart(long j) {
        this.Start = j;
    }

    public long getStart() {
        return this.Start;
    }

    public void setEnd(long j) {
        this.End = j;
    }

    public long getEnd() {
        return this.End;
    }

    public void setSampleNum(long j) {
        this.SampleNum = j;
    }

    public long getSampleNum() {
        return this.SampleNum;
    }

    public void setOriginStart(long j) {
        this.OriginStart = j;
    }

    public long getOriginStart() {
        return this.OriginStart;
    }

    public void setFadeIn(int i) {
        this.FadeIn = i;
    }

    public int getFadeIn() {
        return this.FadeIn;
    }

    public void setFadeOut(int i) {
        this.FadeOut = i;
    }

    public int getFadeOut() {
        return this.FadeOut;
    }

    public void setFadeInType(String str) {
        this.FadeInType = str;
    }

    public String getFadeInType() {
        return this.FadeInType;
    }

    public void setFadeOutType(String str) {
        this.FadeOutType = str;
    }

    public String getFadeOutType() {
        return this.FadeOutType;
    }

    public void setNumChannels(int i) {
        this.NumChannels = i;
    }

    public int getNumChannels() {
        return this.NumChannels;
    }

    public void setStatus(int i) {
        this.Status = i;
    }

    public int getStatus() {
        return this.Status;
    }

    public String getOriginUrl() {
        OriginModel originModel = this.Origin;
        return originModel != null ? originModel.getUrl() : "";
    }

    public float getSpeed() {
        float f = this.speed;
        if (f < 0.5d || f > 2.0f) {
            this.speed = 1.0f;
        }
        return this.speed;
    }

    public void setSpeed(float f) {
        this.speed = f;
    }
}
