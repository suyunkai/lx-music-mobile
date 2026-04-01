package com.wanos.careditproject.model.server;

import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class RootModel {
    private int BitDepth;
    private int BitRate;
    private String ChannelNum;
    private boolean IsMute;
    private boolean IsPos;
    private boolean IsShow;
    private boolean IsSolo;
    private long SampleNum;
    private int SampleRate;
    private int Seek;
    private List<TracksModel> Tracks;
    private String VideoUrl;
    private String WanosUrl;

    public void setTracks(List<TracksModel> list) {
        this.Tracks = list;
    }

    public List<TracksModel> getTracks() {
        return this.Tracks;
    }

    public void setSeek(int i) {
        this.Seek = i;
    }

    public int getSeek() {
        return this.Seek;
    }

    public void setSampleNum(long j) {
        this.SampleNum = j;
    }

    public long getSampleNum() {
        return this.SampleNum;
    }

    public void setVideoUrl(String str) {
        this.VideoUrl = str;
    }

    public String getVideoUrl() {
        return this.VideoUrl;
    }

    public void setWanosUrl(String str) {
        this.WanosUrl = str;
    }

    public String getWanosUrl() {
        return this.WanosUrl;
    }

    public void setChannelNum(String str) {
        this.ChannelNum = str;
    }

    public String getChannelNum() {
        return this.ChannelNum;
    }

    public void setSampleRate(int i) {
        this.SampleRate = i;
    }

    public int getSampleRate() {
        return this.SampleRate;
    }

    public void setBitRate(int i) {
        this.BitRate = i;
    }

    public int getBitRate() {
        return this.BitRate;
    }

    public void setBitDepth(int i) {
        this.BitDepth = i;
    }

    public int getBitDepth() {
        return this.BitDepth;
    }

    public void setIsPos(boolean z) {
        this.IsPos = z;
    }

    public boolean getIsPos() {
        return this.IsPos;
    }

    public void setIsMute(boolean z) {
        this.IsMute = z;
    }

    public boolean getIsMute() {
        return this.IsMute;
    }

    public void setIsShow(boolean z) {
        this.IsShow = z;
    }

    public boolean getIsShow() {
        return this.IsShow;
    }

    public void setIsSolo(boolean z) {
        this.IsSolo = z;
    }

    public boolean getIsSolo() {
        return this.IsSolo;
    }
}
