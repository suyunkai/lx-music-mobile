package com.wanos.commonlibrary.glide;

import android.graphics.Bitmap;
import android.graphics.Rect;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import java.security.MessageDigest;

/* JADX INFO: loaded from: classes3.dex */
public class RightCropTransformation extends BitmapTransformation {
    @Override // com.bumptech.glide.load.Key
    public void updateDiskCacheKey(MessageDigest messageDigest) {
    }

    @Override // com.bumptech.glide.load.resource.bitmap.BitmapTransformation
    protected Bitmap transform(BitmapPool bitmapPool, Bitmap bitmap, int i, int i2) {
        if (bitmap == null) {
            return null;
        }
        int height = bitmap.getHeight();
        int height2 = bitmap.getHeight();
        if (bitmap.getWidth() < bitmap.getHeight()) {
            height = bitmap.getWidth();
        }
        Rect rect = new Rect(bitmap.getWidth() - height, 0, bitmap.getWidth(), height2);
        return Bitmap.createBitmap(bitmap, rect.left, rect.top, rect.width(), rect.height());
    }
}
