package com.wanos.careditproject.utils;

import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class EditProjectCache {
    private static int maxSize = 15;
    private static List<String> undoList = new LinkedList();
    private static List<String> redoList = new LinkedList();

    public static void save(String str) {
        redoClear();
        undoList.add(str);
        if (undoList.size() > maxSize) {
            undoList.remove(0);
        }
    }

    public static int undoListSize() {
        return undoList.size();
    }

    public static int redoListSize() {
        return redoList.size();
    }

    public static String undo(String str) {
        if (undoList.size() <= 0) {
            return "";
        }
        String strRemove = undoList.remove(r0.size() - 1);
        redoList.add(str);
        return strRemove;
    }

    public static String redo(String str) {
        if (redoList.size() <= 0) {
            return "";
        }
        String strRemove = redoList.remove(r0.size() - 1);
        undoList.add(str);
        return strRemove;
    }

    public static void undoClear() {
        undoList.clear();
    }

    public static void redoClear() {
        redoList.clear();
    }

    public static void clear() {
        undoList.clear();
        redoList.clear();
    }
}
