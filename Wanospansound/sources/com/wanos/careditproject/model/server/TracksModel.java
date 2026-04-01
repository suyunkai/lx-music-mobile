package com.wanos.careditproject.model.server;

import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class TracksModel {
    private Map<String, BallRoute> BallRouteMap;
    private int Channel_int;
    private int Channel_out;
    private List<TrackItemModel> Children;
    private Map<String, ClipModel> Clips;
    private String Color;
    private int DB;
    private int ID;
    private boolean IsMute;
    private boolean IsPos;
    private boolean IsShow;
    private boolean IsSolo;
    private MetaModel MetaData;
    private String Name;
    private int NumChannels;
    private int Parent;
    private long SampleNum;
    private int Usage;
    private int Wrap_int;
    private int Wrap_out;

    public void setID(int i) {
        this.ID = i;
    }

    public int getID() {
        return this.ID;
    }

    public void setName(String str) {
        this.Name = str;
    }

    public String getName() {
        return this.Name;
    }

    public void setParent(int i) {
        this.Parent = i;
    }

    public int getParent() {
        return this.Parent;
    }

    public void setChildren(List<TrackItemModel> list) {
        this.Children = list;
    }

    public List<TrackItemModel> getChildren() {
        return this.Children;
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

    public void setColor(String str) {
        this.Color = str;
    }

    public String getColor() {
        return this.Color;
    }

    public void setDB(int i) {
        this.DB = i;
    }

    public int getDB() {
        return this.DB;
    }

    public void setSampleNum(long j) {
        this.SampleNum = j;
    }

    public long getSampleNum() {
        return this.SampleNum;
    }

    public void setNumChannels(int i) {
        this.NumChannels = i;
    }

    public int getNumChannels() {
        return this.NumChannels;
    }

    public void setUsage(int i) {
        this.Usage = i;
    }

    public int getUsage() {
        return this.Usage;
    }

    public void setChannel_int(int i) {
        this.Channel_int = i;
    }

    public int getChannel_int() {
        return this.Channel_int;
    }

    public void setChannel_out(int i) {
        this.Channel_out = i;
    }

    public int getChannel_out() {
        return this.Channel_out;
    }

    public void setWrap_int(int i) {
        this.Wrap_int = i;
    }

    public int getWrap_int() {
        return this.Wrap_int;
    }

    public void setWrap_out(int i) {
        this.Wrap_out = i;
    }

    public int getWrap_out() {
        return this.Wrap_out;
    }

    public Map<String, ClipModel> getClips() {
        return this.Clips;
    }

    public void setClips(Map<String, ClipModel> map) {
        this.Clips = map;
    }

    public Map<String, BallRoute> getBallRouteMap() {
        return this.BallRouteMap;
    }

    public void setBallRouteMap(Map<String, BallRoute> map) {
        this.BallRouteMap = map;
    }

    public MetaModel getMetaData() {
        return this.MetaData;
    }

    public void setMetaData(MetaModel metaModel) {
        this.MetaData = metaModel;
    }
}
