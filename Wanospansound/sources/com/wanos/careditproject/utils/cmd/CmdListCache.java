package com.wanos.careditproject.utils.cmd;

import com.wanos.careditproject.utils.DataHelpAudioTrack;
import java.util.Iterator;
import java.util.Stack;

/* JADX INFO: loaded from: classes3.dex */
public class CmdListCache {
    private static int maxSize = 15;
    private static Stack<BaseCmd> undoList = new Stack<>();
    private static Stack<BaseCmd> redoList = new Stack<>();

    public static void save(BaseCmd baseCmd) {
        redoClear();
        undoList.add(baseCmd);
        if (undoList.size() > maxSize) {
            undoList.remove(0).clearCache();
        }
        DataHelpAudioTrack.undoRedoStateChanged();
    }

    public static int undoListSize() {
        return undoList.size();
    }

    public static int redoListSize() {
        return redoList.size();
    }

    public static boolean undo() {
        if (undoList.size() <= 0) {
            return false;
        }
        BaseCmd baseCmdPop = undoList.pop();
        baseCmdPop.undoCmd();
        redoList.add(baseCmdPop);
        return true;
    }

    public static boolean redo() {
        if (redoList.size() <= 0) {
            return false;
        }
        BaseCmd baseCmdPop = redoList.pop();
        baseCmdPop.redoCmd();
        undoList.add(baseCmdPop);
        return true;
    }

    public static void undoClear() {
        undoList.clear();
    }

    public static void redoClear() {
        Iterator<BaseCmd> it = redoList.iterator();
        while (it.hasNext()) {
            it.next().clearCache();
        }
        redoList.clear();
    }

    public static void clear() {
        undoList.clear();
        redoList.clear();
        CmdFileCache.clearAllCmdCache();
    }
}
