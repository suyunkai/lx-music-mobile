package com.wanos.media.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.renderscript.RSRuntimeException;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import java.security.MessageDigest;
import jp.wasabeef.glide.transformations.BitmapTransformation;
import jp.wasabeef.glide.transformations.internal.FastBlur;

/* JADX INFO: loaded from: classes3.dex */
public class CustomBlurTransformation extends BitmapTransformation {
    private static final int DEFAULT_DOWN_SAMPLING = 1;
    private static final String ID = "jp.wasabeef.glide.transformations.BlurTransformation.1";
    private static final int MAX_RADIUS = 25;
    private static final int VERSION = 1;
    private final Context mContext;
    private final int radius;
    private final int sampling;

    public CustomBlurTransformation(int i, int i2, Context context) {
        this.radius = i;
        this.sampling = i2;
        this.mContext = context;
    }

    @Override // jp.wasabeef.glide.transformations.BitmapTransformation
    protected Bitmap transform(Context context, BitmapPool bitmapPool, Bitmap bitmap, int i, int i2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i3 = this.sampling;
        Bitmap bitmap2 = bitmapPool.get(width / i3, height / i3, Bitmap.Config.ARGB_8888);
        bitmap2.setDensity(bitmap.getDensity());
        Canvas canvas = new Canvas(bitmap2);
        int i4 = this.sampling;
        canvas.scale(1.0f / i4, 1.0f / i4);
        Paint paint = new Paint();
        paint.setFlags(2);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        try {
            Context context2 = this.mContext;
            if (context2 != null) {
                context = context2;
            }
            return blur(context, bitmap2, this.radius);
        } catch (RSRuntimeException unused) {
            return FastBlur.blur(bitmap2, this.radius, true);
        }
    }

    public String toString() {
        return "BlurTransformation(radius=" + this.radius + ", sampling=" + this.sampling + ")";
    }

    @Override // jp.wasabeef.glide.transformations.BitmapTransformation, com.bumptech.glide.load.Key
    public boolean equals(Object obj) {
        if (obj instanceof CustomBlurTransformation) {
            CustomBlurTransformation customBlurTransformation = (CustomBlurTransformation) obj;
            if (customBlurTransformation.radius == this.radius && customBlurTransformation.sampling == this.sampling) {
                return true;
            }
        }
        return false;
    }

    @Override // jp.wasabeef.glide.transformations.BitmapTransformation, com.bumptech.glide.load.Key
    public int hashCode() {
        return 737513610 + (this.radius * 1000) + (this.sampling * 10);
    }

    @Override // jp.wasabeef.glide.transformations.BitmapTransformation, com.bumptech.glide.load.Key
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update((ID + this.radius + this.sampling).getBytes(CHARSET));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:26:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0069  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.graphics.Bitmap blur(android.content.Context r6, android.graphics.Bitmap r7, int r8) throws java.lang.Throwable {
        /*
            r5 = this;
            r0 = 0
            android.renderscript.RenderScript r6 = android.renderscript.RenderScript.create(r6)     // Catch: java.lang.Throwable -> L54
            android.renderscript.RenderScript$RSMessageHandler r1 = new android.renderscript.RenderScript$RSMessageHandler     // Catch: java.lang.Throwable -> L4e
            r1.<init>()     // Catch: java.lang.Throwable -> L4e
            r6.setMessageHandler(r1)     // Catch: java.lang.Throwable -> L4e
            android.renderscript.Allocation$MipmapControl r1 = android.renderscript.Allocation.MipmapControl.MIPMAP_NONE     // Catch: java.lang.Throwable -> L4e
            r2 = 1
            android.renderscript.Allocation r1 = android.renderscript.Allocation.createFromBitmap(r6, r7, r1, r2)     // Catch: java.lang.Throwable -> L4e
            android.renderscript.Type r2 = r1.getType()     // Catch: java.lang.Throwable -> L4b
            android.renderscript.Allocation r2 = android.renderscript.Allocation.createTyped(r6, r2)     // Catch: java.lang.Throwable -> L4b
            android.renderscript.Element r3 = android.renderscript.Element.U8_4(r6)     // Catch: java.lang.Throwable -> L46
            android.renderscript.ScriptIntrinsicBlur r0 = android.renderscript.ScriptIntrinsicBlur.create(r6, r3)     // Catch: java.lang.Throwable -> L46
            r0.setInput(r1)     // Catch: java.lang.Throwable -> L46
            float r8 = (float) r8     // Catch: java.lang.Throwable -> L46
            r0.setRadius(r8)     // Catch: java.lang.Throwable -> L46
            r0.forEach(r2)     // Catch: java.lang.Throwable -> L46
            r2.copyTo(r7)     // Catch: java.lang.Throwable -> L46
            if (r6 == 0) goto L36
            r6.destroy()
        L36:
            if (r1 == 0) goto L3b
            r1.destroy()
        L3b:
            if (r2 == 0) goto L40
            r2.destroy()
        L40:
            if (r0 == 0) goto L45
            r0.destroy()
        L45:
            return r7
        L46:
            r7 = move-exception
            r4 = r0
            r0 = r6
            r6 = r4
            goto L58
        L4b:
            r7 = move-exception
            r2 = r0
            goto L51
        L4e:
            r7 = move-exception
            r1 = r0
            r2 = r1
        L51:
            r0 = r6
            r6 = r2
            goto L58
        L54:
            r7 = move-exception
            r6 = r0
            r1 = r6
            r2 = r1
        L58:
            if (r0 == 0) goto L5d
            r0.destroy()
        L5d:
            if (r1 == 0) goto L62
            r1.destroy()
        L62:
            if (r2 == 0) goto L67
            r2.destroy()
        L67:
            if (r6 == 0) goto L6c
            r6.destroy()
        L6c:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wanos.media.widget.CustomBlurTransformation.blur(android.content.Context, android.graphics.Bitmap, int):android.graphics.Bitmap");
    }
}
