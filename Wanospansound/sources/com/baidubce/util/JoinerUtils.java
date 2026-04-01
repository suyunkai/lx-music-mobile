package com.baidubce.util;

import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class JoinerUtils {
    public static String on(String str, List<String> list) {
        Iterator<String> it = list.iterator();
        String str2 = "";
        while (it.hasNext()) {
            str2 = str2 + it.next() + str;
        }
        return str2.substring(0, str2.length() - 1);
    }

    public static String on(String str, Object... objArr) {
        String str2 = "";
        for (Object obj : objArr) {
            str2 = str2 + obj + str;
        }
        return str2.substring(0, str2.length() - 1);
    }

    public static String cut(String str, String str2) {
        return str2.replace(str, "");
    }
}
