package com.wanos.media.widget.sound;

/* JADX INFO: loaded from: classes3.dex */
interface ISoundBallMove {
    void onBallAudioPosition(float f, float f2, float f3, BallMoveWay ballMoveWay);

    void onBallMotionDown();

    void onEditModeChange(boolean z);

    void onMoveInfoChange(boolean z, float f, int i);

    void setViewTranslationXY(float f, float f2);

    void setViewTranslationZ(float f, BallMoveWay ballMoveWay);
}
