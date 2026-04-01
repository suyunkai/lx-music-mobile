package com.just.agentweb;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.WebView;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class AgentWebCompat {
    private static void createFile(File file, boolean z) {
        if (z) {
            try {
                if (file.exists()) {
                    return;
                }
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void setDataDirectorySuffix(Context context) {
        StringBuilder sbAppend;
        try {
            HashSet hashSet = new HashSet();
            String absolutePath = context.getDataDir().getAbsolutePath();
            String currentProcessName = ProcessUtils.getCurrentProcessName(context);
            if (TextUtils.equals(context.getPackageName(), currentProcessName)) {
                String str = "_" + currentProcessName;
                hashSet.add(absolutePath + "/app_webview/webview_data.lock");
                hashSet.add(absolutePath + "/app_webview" + str + "/webview_data.lock");
                if (RomUtils.isHuawei()) {
                    hashSet.add(absolutePath + "/app_hws_webview/webview_data.lock");
                    sbAppend = new StringBuilder().append(absolutePath).append("/app_hws_webview").append(str).append("/webview_data.lock");
                    hashSet.add(sbAppend.toString());
                }
            } else {
                if (TextUtils.isEmpty(currentProcessName)) {
                    currentProcessName = context.getPackageName();
                }
                WebView.setDataDirectorySuffix(currentProcessName);
                String str2 = "_" + currentProcessName;
                hashSet.add(absolutePath + "/app_webview" + str2 + "/webview_data.lock");
                if (RomUtils.isHuawei()) {
                    sbAppend = new StringBuilder().append(absolutePath).append("/app_hws_webview").append(str2).append("/webview_data.lock");
                    hashSet.add(sbAppend.toString());
                }
            }
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                File file = new File((String) it.next());
                if (file.exists()) {
                    tryLockOrRecreateFile(file);
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void tryLockOrRecreateFile(File file) {
        try {
            FileLock fileLockTryLock = new RandomAccessFile(file, "rw").getChannel().tryLock();
            if (fileLockTryLock != null) {
                fileLockTryLock.close();
            } else {
                createFile(file, file.delete());
            }
        } catch (Exception e) {
            e.printStackTrace();
            createFile(file, file.exists() ? file.delete() : false);
        }
    }
}
