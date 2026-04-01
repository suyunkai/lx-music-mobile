package com.wanos.lyric;

import android.content.Context;
import com.baidubce.BceConfig;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public class lyric {
    private static lyric mInstance;

    public static lyric getInstance() {
        if (mInstance == null) {
            mInstance = new lyric();
        }
        return mInstance;
    }

    public String getLrcContext(Context context, String str) {
        try {
            String cacheDirectory = DownloadUtil.get().getCacheDirectory(context, true);
            String[] strArrSplit = str.split(BceConfig.BOS_DELIMITER);
            File file = new File(cacheDirectory + BceConfig.BOS_DELIMITER + strArrSplit[strArrSplit.length - 1]);
            if (!file.exists()) {
                return null;
            }
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = bufferedReader.readLine();
                if (line != null) {
                    sb.append(line).append("\n");
                } else {
                    String string = sb.toString();
                    bufferedReader.close();
                    return string;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
