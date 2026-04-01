package com.app.hubert.guide.model;

import android.graphics.RectF;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public interface HighLight {

    public enum Shape {
        CIRCLE,
        RECTANGLE,
        OVAL,
        ROUND_RECTANGLE
    }

    int getBorderColor();

    int getBorderWidth();

    HighlightOptions getOptions();

    float getRadius();

    RectF getRectF(View view);

    int getRound();

    Shape getShape();
}
