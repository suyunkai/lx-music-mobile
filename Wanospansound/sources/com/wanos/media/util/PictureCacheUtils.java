package com.wanos.media.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import com.baidubce.BceConfig;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.Utils;
import com.wanos.media.entity.DefaultCacheFileEntity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: loaded from: classes3.dex */
public class PictureCacheUtils {
    private static final String TAG = "PictureCacheUtils";
    private static final OkHttpClient mOkHttpClient = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).callTimeout(60, TimeUnit.SECONDS).build();

    public static String toLocalImage(String str, DefaultCacheFileEntity defaultCacheFileEntity) {
        if (StringUtils.isEmpty(str)) {
            return getDefaultLocalImage(defaultCacheFileEntity);
        }
        Request requestBuild = new Request.Builder().url(str).build();
        String filePath = getFilePath(str);
        try {
            Response responseExecute = mOkHttpClient.newCall(requestBuild).execute();
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(filePath);
                try {
                    if (!responseExecute.getIsSuccessful()) {
                        ZeroLogcatTools.e(TAG, "toImageUrl: isSuccessful=false");
                        String defaultLocalImage = getDefaultLocalImage(defaultCacheFileEntity);
                        fileOutputStream.close();
                        if (responseExecute != null) {
                            responseExecute.close();
                        }
                        return defaultLocalImage;
                    }
                    InputStream inputStreamByteStream = responseExecute.body().byteStream();
                    byte[] bArr = new byte[2048];
                    while (true) {
                        int i = inputStreamByteStream.read(bArr);
                        if (i == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, i);
                    }
                    fileOutputStream.close();
                    if (responseExecute != null) {
                        responseExecute.close();
                    }
                    return filePath;
                } finally {
                }
            } finally {
            }
        } catch (IOException e) {
            ZeroLogcatTools.d(TAG, "toImageUrl: IOException=" + e.getMessage());
            return getDefaultLocalImage(defaultCacheFileEntity);
        }
    }

    public static String getDefaultLocalImage(DefaultCacheFileEntity defaultCacheFileEntity) {
        if (defaultCacheFileEntity == null) {
            ZeroLogcatTools.w(TAG, "initOpenGlIconInfo: DefaultCacheFileEntity == NULL");
            return "";
        }
        return bitmapToLocalPath(BitmapFactory.decodeResource(Utils.getApp().getResources(), defaultCacheFileEntity.getDefaultDrawable()), getFileLocalPath(defaultCacheFileEntity.getDefaultFileName()), true);
    }

    public static String bitmapToLocalPath(Bitmap bitmap, String str, boolean z) {
        if (bitmap == null || StringUtils.isEmpty(str)) {
            ZeroLogcatTools.w(TAG, "bitmapToLocalPath: bitmap = " + bitmap + ", filePath = " + str);
            return "";
        }
        if (FileUtils.isFileExists(str) && ImageUtils.isImage(str)) {
            ZeroLogcatTools.d(TAG, "bitmapToLocalPath: 图片已存在,直接使用" + str);
            return str;
        }
        try {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(str);
                try {
                    bitmap.compress(bitmap.hasAlpha() ? Bitmap.CompressFormat.PNG : Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                    fileOutputStream.close();
                    if (z) {
                        bitmap.recycle();
                    }
                    return str;
                } catch (Throwable th) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (IOException e) {
                ZeroLogcatTools.e(TAG, "initOpenGlIconInfo: 图标写入失败=" + e.getMessage());
                if (z) {
                    bitmap.recycle();
                }
                return "";
            }
        } catch (Throwable th3) {
            if (z) {
                bitmap.recycle();
            }
            throw th3;
        }
    }

    public static String toLocalImage(String str, Bitmap bitmap) {
        return bitmapToLocalPath(bitmap, getFilePath(str), true);
    }

    private static String getFilePath(String str) {
        return initPictureCachePath() + getFileNameForUrl(str);
    }

    public static String getImageCachePath(String str) {
        return getFileLocalPath(getFileNameForUrl(str));
    }

    public static String getFileLocalPath(String str) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        String str2 = initPictureCachePath() + str;
        ZeroCacheScan.getInstance().setImageAccess(str2);
        return str2;
    }

    private static String initPictureCachePath() {
        String str = Utils.getApp().getFilesDir().getAbsolutePath() + File.separator + Environment.DIRECTORY_PICTURES + File.separator + "image_zero" + File.separator;
        File file = new File(str);
        if (!file.exists() || !file.isDirectory()) {
            ZeroLogcatTools.d(TAG, "图片缓存路径创建：" + file.mkdirs());
        }
        return str;
    }

    private static String getFileNameForUrl(String str) {
        int i;
        if (StringUtils.isEmpty(str)) {
            ZeroLogcatTools.e(TAG, "音频网络路径:" + str);
            return "";
        }
        String[] strArrSplit = str.split("[?]");
        if (strArrSplit.length == 0) {
            return str;
        }
        if (strArrSplit.length != 1) {
            str = strArrSplit[0];
        }
        int iLastIndexOf = str.lastIndexOf(BceConfig.BOS_DELIMITER);
        return (iLastIndexOf < 0 || (i = iLastIndexOf + 1) >= str.length()) ? str : str.substring(i);
    }
}
