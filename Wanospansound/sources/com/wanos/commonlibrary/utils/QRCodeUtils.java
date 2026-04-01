package com.wanos.commonlibrary.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

/* JADX INFO: loaded from: classes3.dex */
public class QRCodeUtils {
    public static Bitmap createQRCode(String str, int i, int i2, int i3, Bitmap bitmap) {
        if (str != null) {
            try {
                if (!"".equals(str)) {
                    HashMap map = new HashMap();
                    map.put(EncodeHintType.CHARACTER_SET, "utf-8");
                    map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
                    map.put(EncodeHintType.MARGIN, Integer.valueOf(i3));
                    BitMatrix bitMatrixEncode = new QRCodeWriter().encode(str, BarcodeFormat.QR_CODE, i, i2, map);
                    int[] iArr = new int[i * i2];
                    for (int i4 = 0; i4 < i2; i4++) {
                        for (int i5 = 0; i5 < i; i5++) {
                            if (bitMatrixEncode.get(i5, i4)) {
                                iArr[(i4 * i) + i5] = -16777216;
                            } else {
                                iArr[(i4 * i) + i5] = -1;
                            }
                        }
                    }
                    Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
                    bitmapCreateBitmap.setPixels(iArr, 0, i, 0, 0, i, i2);
                    if (bitmap != null) {
                        bitmapCreateBitmap = addLogo(bitmapCreateBitmap, bitmap);
                    }
                    saveBitmap(bitmapCreateBitmap);
                    return getQrCodeBitmap();
                }
            } catch (WriterException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static Bitmap addLogo(Bitmap bitmap, Bitmap bitmap2) {
        if (bitmap == null) {
            return null;
        }
        if (bitmap2 == null) {
            return bitmap;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int width2 = bitmap2.getWidth();
        int height2 = bitmap2.getHeight();
        if (width == 0 || height == 0) {
            return null;
        }
        if (width2 == 0 || height2 == 0) {
            return bitmap;
        }
        float f = ((width * 1.0f) / 5.0f) / width2;
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        try {
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
            canvas.scale(f, f, width / 2, height / 2);
            canvas.drawBitmap(bitmap2, (width - width2) / 2, (height - height2) / 2, (Paint) null);
            canvas.save();
            canvas.restore();
            return bitmapCreateBitmap;
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }

    public static void saveBitmap(Bitmap bitmap) {
        String str = Utils.getApp().getExternalCacheDir() + "/qrcode.jpg";
        LogUtils.i("glg", "name=" + str);
        File file = new File(str);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            if (bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fileOutputStream)) {
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }

    public static Bitmap getQrCodeBitmap() {
        File file = new File(Utils.getApp().getExternalCacheDir() + "/qrcode.jpg");
        if (!file.exists()) {
            return null;
        }
        try {
            return BitmapFactory.decodeStream(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
