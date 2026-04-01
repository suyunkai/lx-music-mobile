package com.wanos.careditproject.utils.cmd;

import com.wanos.careditproject.utils.EditingUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public class CmdFileCache {
    private static int cmdIndex;

    public static String saveCmdData(String str) {
        File file = new File(EditingUtils.getUndoRedoPath());
        if (!file.exists() && !file.mkdirs()) {
            return "";
        }
        File file2 = new File(EditingUtils.getUndoRedoPath(), getCmdFileName());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            try {
                fileOutputStream.write(str.getBytes());
                fileOutputStream.close();
                return file2.getAbsolutePath();
            } finally {
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getCmdData(String str) {
        File file = new File(str);
        if (!file.exists()) {
            return "";
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[(int) file.length()];
            fileInputStream.read(bArr);
            fileInputStream.close();
            return new String(bArr);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void deleteCmdData(String str) {
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
    }

    public static void clearAllCmdCache() {
        File[] fileArrListFiles;
        File file = new File(EditingUtils.getUndoRedoPath());
        if (!file.exists() || (fileArrListFiles = file.listFiles()) == null) {
            return;
        }
        for (File file2 : fileArrListFiles) {
            file2.delete();
        }
    }

    public static String getCmdFileName() {
        cmdIndex++;
        return "cmd_" + cmdIndex + ".txt";
    }
}
