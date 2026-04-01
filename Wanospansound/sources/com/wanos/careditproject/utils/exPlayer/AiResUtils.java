package com.wanos.careditproject.utils.exPlayer;

import com.wanos.careditproject.utils.EditingUtils;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class AiResUtils {
    private static Map<String, String> url2LocalPath = new HashMap();

    public static void addLocalPath(String str, String str2) {
        url2LocalPath.put(EditingUtils.removeParamOfUrl(str), str2);
    }

    public static String getLocalPath(String str) {
        return url2LocalPath.get(EditingUtils.removeParamOfUrl(str));
    }

    public static void clear() {
        url2LocalPath.clear();
    }
}
