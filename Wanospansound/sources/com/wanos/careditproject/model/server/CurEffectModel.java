package com.wanos.careditproject.model.server;

/* JADX INFO: loaded from: classes3.dex */
public class CurEffectModel {
    private int AGC = 1;
    private int DRC;
    private int Delay;
    private int Eg;
    private int Fade;
    private int Gaw;
    private int Reverb;

    public void setGaw(int i) {
        this.Gaw = i;
    }

    public int getGaw() {
        return this.Gaw;
    }

    public void setFade(int i) {
        this.Fade = i;
    }

    public int getFade() {
        return this.Fade;
    }

    public void setEg(int i) {
        this.Eg = i;
    }

    public int getEg() {
        return this.Eg;
    }

    public void setReverb(int i) {
        this.Reverb = i;
    }

    public int getReverb() {
        return this.Reverb;
    }

    public void setAGC(int i) {
        this.AGC = i;
    }

    public int getAGC() {
        return this.AGC;
    }

    public void setDRC(int i) {
        this.DRC = i;
    }

    public int getDRC() {
        return this.DRC;
    }

    public void setDelay(int i) {
        this.Delay = i;
    }

    public int getDelay() {
        return this.Delay;
    }
}
