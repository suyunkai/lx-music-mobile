package com.baidubce.services.bos.common.utils;

import com.baidubce.services.bos.common.BosConstants;

/* JADX INFO: loaded from: classes.dex */
public class BosUtils {
    public static boolean isCnameLikeHost(String str) {
        for (String str2 : BosConstants.DEFAULT_CNAME_LIKE_LIST) {
            if (str.toLowerCase().endsWith(str2)) {
                return true;
            }
        }
        return false;
    }
}
