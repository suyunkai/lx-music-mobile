package com.flyme.auto.music.source.usage;

import com.flyme.auto.music.source.util.MusicSourceLog;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class MusicSourceUsageManager {
    private static final String TAG = "MusicSourceUsageManager";

    public static void reportEvent(String str, String str2, Map<String, Object> map) {
        if (str2 == null) {
            str2 = "none";
        }
        MusicSourceUsageCallback usageCallback = MusicSourceSdkManager.getInstance().getUsageCallback();
        if (usageCallback != null) {
            try {
                MusicSourceLog.v(TAG, "reportEvent: " + str + ", pageName: " + str2 + ", params: " + map);
                usageCallback.reportEvent(str, str2, map);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}
