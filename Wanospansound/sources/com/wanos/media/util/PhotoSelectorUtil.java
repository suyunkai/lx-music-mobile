package com.wanos.media.util;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import androidx.activity.result.ActivityResultLauncher;
import java.io.File;

/* JADX INFO: loaded from: classes3.dex */
public class PhotoSelectorUtil {
    public static void startPhotoSelector(ActivityResultLauncher launcuer, Activity activity) {
        if (launcuer == null || activity == null) {
            return;
        }
        Intent intent = new Intent("android.intent.action.PICK");
        intent.setType("image/*");
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            launcuer.launch(intent);
        } else {
            com.wanos.commonlibrary.utils.ToastUtil.showMsg("未找到图片查看器");
        }
    }

    public static void startBigPhotoZoom(Uri uri, ActivityResultLauncher launcuer) {
        Uri uriFromFile;
        if (launcuer != null) {
            if (Environment.getExternalStorageState().equals("mounted")) {
                File file = new File(Environment.getExternalStorageDirectory().getPath() + "/bigIcon");
                if (!file.exists() && file.mkdirs()) {
                    Log.e("TAG", "文件夹创建成功");
                }
                uriFromFile = Uri.fromFile(new File(file, System.currentTimeMillis() + ".jpg"));
            } else {
                uriFromFile = null;
            }
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(uri, "image/*");
            intent.putExtra("crop", "true");
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("outputX", 500);
            intent.putExtra("outputY", 500);
            intent.putExtra("scale", true);
            intent.putExtra("return-data", false);
            intent.putExtra("output", uriFromFile);
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            launcuer.launch(intent);
        }
    }
}
