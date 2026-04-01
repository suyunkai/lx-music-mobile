package com.wanos.careditproject.view.widget;

import android.graphics.LinearGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;

/* JADX INFO: loaded from: classes3.dex */
public class StrokeGradientDrawable extends ShapeDrawable {
    public StrokeGradientDrawable(final int[] iArr, float f, int i) {
        float f2 = i;
        float f3 = f - f2;
        setShape(new RoundRectShape(new float[]{f, f, f, f, f, f, f, f}, new RectF(f2, f2, f2, f2), new float[]{f3, f3, f3, f3, f3, f3, f3, f3}));
        setShaderFactory(new ShapeDrawable.ShaderFactory() { // from class: com.wanos.careditproject.view.widget.StrokeGradientDrawable.1
            @Override // android.graphics.drawable.ShapeDrawable.ShaderFactory
            public Shader resize(int i2, int i3) {
                return new LinearGradient(0.0f, 0.0f, i2, 0.0f, iArr, (float[]) null, Shader.TileMode.CLAMP);
            }
        });
    }
}
