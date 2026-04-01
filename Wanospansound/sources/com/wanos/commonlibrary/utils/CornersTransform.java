package com.wanos.commonlibrary.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import java.security.MessageDigest;

/* JADX INFO: loaded from: classes3.dex */
public class CornersTransform implements Transformation<Bitmap> {
    private boolean exceptLeftBottom;
    private boolean exceptLeftTop;
    private boolean exceptRightBotoom;
    private boolean exceptRightTop;
    private final BitmapPool mBitmapPool;
    private float radius;

    @Override // com.bumptech.glide.load.Key
    public void updateDiskCacheKey(MessageDigest messageDigest) {
    }

    public void setExceptCorner(boolean z, boolean z2, boolean z3, boolean z4) {
        this.exceptLeftTop = z;
        this.exceptRightTop = z2;
        this.exceptLeftBottom = z3;
        this.exceptRightBotoom = z4;
    }

    public CornersTransform(Context context, float f) {
        this.mBitmapPool = Glide.get(context).getBitmapPool();
        this.radius = f;
    }

    public String getId() {
        return getClass().getName();
    }

    @Override // com.bumptech.glide.load.Transformation
    public Resource<Bitmap> transform(Context context, Resource<Bitmap> resource, int i, int i2) {
        int height;
        int width;
        Bitmap bitmap = resource.get();
        if (i > i2) {
            float f = i2;
            float f2 = i;
            height = bitmap.getWidth();
            width = (int) (bitmap.getWidth() * (f / f2));
            if (width > bitmap.getHeight()) {
                width = bitmap.getHeight();
                height = (int) (bitmap.getHeight() * (f2 / f));
            }
        } else if (i < i2) {
            float f3 = i;
            float f4 = i2;
            int height2 = bitmap.getHeight();
            int height3 = (int) (bitmap.getHeight() * (f3 / f4));
            if (height3 > bitmap.getWidth()) {
                height = bitmap.getWidth();
                width = (int) (bitmap.getWidth() * (f4 / f3));
            } else {
                height = height3;
                width = height2;
            }
        } else {
            height = bitmap.getHeight();
            width = height;
        }
        this.radius *= width / i2;
        Bitmap bitmapCreateBitmap = this.mBitmapPool.get(height, width, Bitmap.Config.ARGB_8888);
        if (bitmapCreateBitmap == null) {
            bitmapCreateBitmap = Bitmap.createBitmap(height, width, Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        int width2 = (bitmap.getWidth() - height) / 2;
        int height4 = (bitmap.getHeight() - width) / 2;
        if (width2 != 0 || height4 != 0) {
            Matrix matrix = new Matrix();
            matrix.setTranslate(-width2, -height4);
            bitmapShader.setLocalMatrix(matrix);
        }
        paint.setShader(bitmapShader);
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight());
        float f5 = this.radius;
        canvas.drawRoundRect(rectF, f5, f5, paint);
        if (this.exceptLeftTop) {
            float f6 = this.radius;
            canvas.drawRect(0.0f, 0.0f, f6, f6, paint);
        }
        if (this.exceptRightTop) {
            canvas.drawRect(canvas.getWidth() - this.radius, 0.0f, canvas.getWidth(), this.radius, paint);
        }
        if (this.exceptLeftBottom) {
            float height5 = canvas.getHeight();
            float f7 = this.radius;
            canvas.drawRect(0.0f, height5 - f7, f7, canvas.getHeight(), paint);
        }
        if (this.exceptRightBotoom) {
            canvas.drawRect(canvas.getWidth() - this.radius, canvas.getHeight() - this.radius, canvas.getWidth(), canvas.getHeight(), paint);
        }
        return BitmapResource.obtain(bitmapCreateBitmap, this.mBitmapPool);
    }
}
