package com.wanos.careditproject.model.server;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class TrackItemModel {
    private int Channel_int;
    private int Channel_out;
    private String Color;
    private int DB;
    private long ID;
    private boolean IsMute;
    private boolean IsPos;
    private boolean IsShow;
    private boolean IsSolo;
    private String Name;
    private int Parent;
    private long SampleNum;
    private int Usage;
    private int Wrap_int;
    private int Wrap_out;
    private Map<String, ClipModel> Clips = new LinkedTreeMap();
    private MetaModel MetaData = new MetaModel();
    private Map<String, BallRoute> BallRouteMap = new LinkedTreeMap();
    private List<Long> BallRouteArr = new ArrayList();
    private int NumChannels = 0;
    private int ColorIndex = -1;

    public TrackItemModel(int i) {
        this.Parent = i;
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public TrackItemModel m388clone() {
        Gson gson = new Gson();
        return (TrackItemModel) gson.fromJson(gson.toJson(this), TrackItemModel.class);
    }

    public void saveBallRouteArr() {
        List<Long> list = this.BallRouteArr;
        if (list == null) {
            this.BallRouteArr = new ArrayList();
        } else {
            list.clear();
        }
        Map<String, BallRoute> map = this.BallRouteMap;
        if (map == null || map.size() <= 0) {
            return;
        }
        Iterator<String> it = this.BallRouteMap.keySet().iterator();
        while (it.hasNext()) {
            this.BallRouteArr.add(Long.valueOf(Long.parseLong(it.next())));
        }
    }

    public void setID(long j) {
        this.ID = j;
    }

    public long getID() {
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

    public void setClips(Map<String, ClipModel> map) {
        this.Clips = map;
    }

    public Map<String, ClipModel> getClips() {
        return this.Clips;
    }

    public void newBallRouteMap() {
        this.BallRouteMap = new LinkedTreeMap();
    }

    public void setBallRouteMap(Map<String, BallRoute> map) {
        this.BallRouteMap = map;
    }

    public Map<String, BallRoute> getBallRouteMap() {
        return this.BallRouteMap;
    }

    public void setMetaData(MetaModel metaModel) {
        this.MetaData = metaModel;
    }

    public MetaModel getMetaData() {
        return this.MetaData;
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

    public int getColor_index() {
        return this.ColorIndex;
    }

    public void setColor_index(int i) {
        this.ColorIndex = i;
    }

    public List<Long> getBallRouteArr() {
        return this.BallRouteArr;
    }

    public void setBallRouteArr(List<Long> list) {
        this.BallRouteArr = list;
    }

    public void newBallRouteArr() {
        this.BallRouteArr = new ArrayList();
    }
}
