package com.wanos.commonlibrary.bean;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.ecarx.openapi.deviceinternal.BuildConfig;

/* JADX INFO: loaded from: classes3.dex */
public enum SizeMode {
    IMAGE_SIZE_MAX(BuildConfig.VERSION_INT, BuildConfig.VERSION_INT),
    IMAGE_SIZE_MIDDLE(280, 280),
    IMAGE_SIZE_MIN(150, 150),
    BANNER_HOME(TypedValues.MotionType.TYPE_DRAW_PATH, 320),
    HOME_RECOMMEND(280, 280),
    HOME_RANKING(160, 160),
    PLAY(720, 720),
    PLAY_LIST(BuildConfig.VERSION_INT, BuildConfig.VERSION_INT),
    PLAY_LIST_ICON(120, 120),
    ADVERTISEMENT(1176, 668);

    private final int height;
    private final int width;

    SizeMode(int i, int i2) {
        this.width = i;
        this.height = i2;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}
