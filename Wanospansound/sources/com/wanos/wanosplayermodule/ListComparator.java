package com.wanos.wanosplayermodule;

import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ListComparator {
    public static boolean compare(List<?> list, List<?> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).equals(list2.get(i))) {
                return false;
            }
        }
        return true;
    }
}
