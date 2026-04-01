package com.app.hubert.guide.model;

import android.graphics.RectF;
import android.view.View;
import com.app.hubert.guide.model.HighLight;

/* JADX INFO: loaded from: classes.dex */
public class HighlightRectF implements HighLight {
    private int borderColor;
    private int borderWidth;
    private HighlightOptions options;
    private int padding;
    private RectF rectF;
    private int round;
    private HighLight.Shape shape;

    public HighlightRectF(RectF rectF, HighLight.Shape shape, int i) {
        this.rectF = rectF;
        this.shape = shape;
        this.round = i;
    }

    public HighlightRectF(RectF rectF, HighLight.Shape shape, int i, int i2, int i3, int i4) {
        this.rectF = rectF;
        this.shape = shape;
        this.round = i;
        this.padding = i2;
        this.borderWidth = i3;
        this.borderColor = i4;
    }

    public void setOptions(HighlightOptions highlightOptions) {
        this.options = highlightOptions;
    }

    @Override // com.app.hubert.guide.model.HighLight
    public HighLight.Shape getShape() {
        return this.shape;
    }

    @Override // com.app.hubert.guide.model.HighLight
    public RectF getRectF(View view) {
        return this.rectF;
    }

    @Override // com.app.hubert.guide.model.HighLight
    public float getRadius() {
        return Math.min(this.rectF.width() / 2.0f, this.rectF.height() / 2.0f);
    }

    @Override // com.app.hubert.guide.model.HighLight
    public int getRound() {
        return this.round;
    }

    @Override // com.app.hubert.guide.model.HighLight
    public int getBorderColor() {
        return this.borderColor;
    }

    @Override // com.app.hubert.guide.model.HighLight
    public int getBorderWidth() {
        return this.borderWidth;
    }

    @Override // com.app.hubert.guide.model.HighLight
    public HighlightOptions getOptions() {
        return this.options;
    }
}
