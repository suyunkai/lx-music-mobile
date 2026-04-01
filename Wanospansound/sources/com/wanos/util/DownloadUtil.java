package com.wanos.util;

import android.os.Environment;
import com.baidubce.BceConfig;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes3.dex */
public class DownloadUtil {
    public static void savePcm(float[] fArr, String str) {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + BceConfig.BOS_DELIMITER + str);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException unused) {
                throw new IllegalStateException("未能创建" + file.toString());
            }
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file.getPath(), true);
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(fArr.length * 4);
            byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
            for (float f : fArr) {
                byteBufferAllocate.putFloat(f);
            }
            fileOutputStream.write(byteBufferAllocate.array());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
