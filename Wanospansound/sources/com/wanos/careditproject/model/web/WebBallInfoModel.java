package com.wanos.careditproject.model.web;

/* JADX INFO: loaded from: classes3.dex */
public class WebBallInfoModel {
    public static int alphaMax = 100;
    public boolean isShowBall;
    public int trackIndex;
    public boolean Alone = false;
    public String Name = "";
    public float Loudness = 0.0f;
    public float x = 0.0f;
    public float y = 0.0f;
    public float z = 0.0f;
    public float x1 = 0.0f;
    public float y1 = 0.0f;
    public float z1 = 0.0f;
    public int InputChannels = 1;
    public long Id = 1000;
    public boolean RenderButtonState = true;
    public String ballColor = "#455DDC";
    public int alpha = alphaMax;

    public void cloneModel(WebBallInfoModel webBallInfoModel) {
        this.Alone = webBallInfoModel.Alone;
        this.Name = webBallInfoModel.Name;
        this.Loudness = webBallInfoModel.Loudness;
        this.x = webBallInfoModel.x;
        this.y = webBallInfoModel.y;
        this.z = webBallInfoModel.z;
        this.x1 = webBallInfoModel.x1;
        this.y1 = webBallInfoModel.y1;
        this.z1 = webBallInfoModel.z1;
        this.InputChannels = webBallInfoModel.InputChannels;
        this.Id = webBallInfoModel.Id;
        this.RenderButtonState = webBallInfoModel.RenderButtonState;
        this.ballColor = webBallInfoModel.ballColor;
    }
}
