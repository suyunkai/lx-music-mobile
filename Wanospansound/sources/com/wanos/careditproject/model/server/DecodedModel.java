package com.wanos.careditproject.model.server;

/* JADX INFO: loaded from: classes3.dex */
public class DecodedModel {
    private int BitDepth;
    private String Format;
    private String Name;
    private int NumChannels;
    private long SampleNum;
    private int SampleRate;
    private String Url;

    public void setUrl(String str) {
        this.Url = str;
    }

    public String getUrl() {
        return this.Url;
    }

    public void setName(String str) {
        this.Name = str;
    }

    public String getName() {
        return this.Name;
    }

    public void setFormat(String str) {
        this.Format = str;
    }

    public String getFormat() {
        return this.Format;
    }

    public void setNumChannels(int i) {
        this.NumChannels = i;
    }

    public int getNumChannels() {
        return this.NumChannels;
    }

    public void setSampleRate(int i) {
        this.SampleRate = i;
    }

    public int getSampleRate() {
        return this.SampleRate;
    }

    public void setBitDepth(int i) {
        this.BitDepth = i;
    }

    public int getBitDepth() {
        return this.BitDepth;
    }

    public void setSampleNum(long j) {
        this.SampleNum = j;
    }

    public long getSampleNum() {
        return this.SampleNum;
    }
}
