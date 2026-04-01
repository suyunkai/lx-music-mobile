package com.wanos.media.widget.ball;

import com.wanos.media.widget.sound.BallMoveWay;

/* JADX INFO: loaded from: classes3.dex */
public interface IBallCallback {
    void onBallPositionXY(BallMoveWay ballMoveWay, int i, float f, float f2);

    void onBallZ(BallMoveWay ballMoveWay, int i, float f);

    void onMotionClick(int i, float f, float f2, float f3);

    void onRequestRender();
}
