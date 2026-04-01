package com.wanos.media.entity;

import com.bumptech.glide.repackaged.com.google.common.collect.Lists;
import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ThemeSoundInfoEntity implements Serializable {
    private float Angle;
    private List<BallRouteList> BallRouteList;
    private float DB;
    private int NumChannels;
    private float Speed;
    private float Volume;

    public void setNumChannels(int i) {
        this.NumChannels = i;
    }

    public void setDB(float f) {
        this.DB = f;
    }

    public void setSpeed(float f) {
        this.Speed = f;
    }

    public void setAngle(float f) {
        this.Angle = f;
    }

    public void setBallRouteList(List<BallRouteList> list) {
        this.BallRouteList = Lists.newArrayList(list);
    }

    public void addBallRouteList(BallRouteList ballRouteList) {
        this.BallRouteList.add(ballRouteList);
    }

    public void setVolume(float f) {
        this.Volume = f;
    }

    public float getVolume() {
        float f = this.DB;
        return f == 999.0f ? this.Volume : f;
    }

    public int getNumChannels() {
        return this.NumChannels;
    }

    public float getSpeed() {
        return this.Speed;
    }

    public float getAngle() {
        return this.Angle;
    }

    public List<BallRouteList> getBallRouteList() {
        return this.BallRouteList;
    }

    public static class BallRouteList implements Serializable {
        private List<Float> Pos;
        private long Seek;

        public void setSeek(long j) {
            this.Seek = j;
        }

        public void setPos(List<Float> list) {
            this.Pos = list;
        }

        public long getSeek() {
            return this.Seek;
        }

        public List<Float> getPos() {
            return this.Pos;
        }
    }
}
