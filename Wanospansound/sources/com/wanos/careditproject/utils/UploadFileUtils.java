package com.wanos.careditproject.utils;

import android.util.ArrayMap;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.careditproject.utils.codec.AudioPcmData;
import com.wanos.careditproject.utils.http.DownloadUtils;
import com.wanos.careditproject.utils.http.UploadSingleUtils;
import com.wanos.commonlibrary.utils.ToastUtil;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class UploadFileUtils {
    private static List<UploadSingleUtils.UploadCall> listeners;
    private static Map<String, String> localPath2Url = new ArrayMap();
    private static Map<String, Long> localPath2Id = new ArrayMap();
    private static Map<String, Integer> pathListOfUpFailed = new ArrayMap();
    private static boolean isDestory = false;

    public static void addListener(UploadSingleUtils.UploadCall uploadCall) {
    }

    public static void removeListener(UploadSingleUtils.UploadCall uploadCall) {
    }

    public static class UploadFileInfo {
        public String localPath;
        public int type;

        public UploadFileInfo(int i, String str) {
            this.type = i;
            this.localPath = str;
        }
    }

    public static void setLocalPath2Id(String str, long j) {
        if (localPath2Url.containsKey(str)) {
            uploadOrDecodeFinish(str);
        } else {
            localPath2Id.put(str, Long.valueOf(j));
        }
    }

    public static void start(int i, String str) {
        if (i == 0) {
            start0(str);
        }
    }

    public static boolean isUploadFailed() {
        Iterator<String> it = pathListOfUpFailed.keySet().iterator();
        while (it.hasNext()) {
            if (pathListOfUpFailed.get(it.next()).intValue() == 0) {
                return true;
            }
        }
        return false;
    }

    public static void reStart() {
        for (String str : pathListOfUpFailed.keySet()) {
            if (pathListOfUpFailed.get(str).intValue() == 0) {
                pathListOfUpFailed.put(str, 1);
                start0(str);
            }
        }
    }

    private static void start0(String str) {
        if (isDestory || localPath2Url.containsKey(str)) {
            return;
        }
        pathListOfUpFailed.put(str, 2);
        UploadSingleUtils.getInstance().start(str, new UploadSingleUtils.UploadCall() { // from class: com.wanos.careditproject.utils.UploadFileUtils$$ExternalSyntheticLambda0
            @Override // com.wanos.careditproject.utils.http.UploadSingleUtils.UploadCall
            public final void onCall(String str2, String str3, boolean z) {
                UploadFileUtils.lambda$start0$0(str2, str3, z);
            }
        });
    }

    static /* synthetic */ void lambda$start0$0(String str, String str2, boolean z) {
        EditingUtils.log("UploadUtils isSuccess = " + z);
        if (z) {
            if (pathListOfUpFailed.containsKey(str2)) {
                pathListOfUpFailed.remove(str2);
            }
            EditingUtils.log("UploadUtils new url = " + str);
            localPath2Url.put(str2, str);
            DownloadUtils.getInstance().put(str, str2);
            uploadOrDecodeFinish(str2);
            return;
        }
        if (WanOSRetrofitUtil.isNetConnect) {
            ToastUtil.showMsg("上传失败!");
        } else {
            pathListOfUpFailed.put(str2, 0);
            start0(str2);
        }
        EditingUtils.log("UploadUtils onCall fail");
    }

    public static boolean isUploading() {
        return pathListOfUpFailed.size() > 0;
    }

    public static void removeDataOfLocalPath() {
        Iterator<String> it = pathListOfUpFailed.keySet().iterator();
        while (it.hasNext()) {
            DataHelpAudioTrack.removeClipLocalPath(it.next());
        }
    }

    public static void uploadOrDecodeFinish(String str) {
        if (localPath2Url.containsKey(str) && localPath2Id.containsKey(str)) {
            String str2 = localPath2Url.get(str);
            localPath2Id.get(str).longValue();
            AudioPcmData.getInstance().changeUrl(str, str2);
            DataHelpAudioTrack.changeClipLocalPath(str, str2);
            localPath2Id.remove(str);
            DataHelpAudioTrack.setToSaveServer(true);
        }
    }

    public static String getUrl(String str) {
        return localPath2Url.containsKey(str) ? localPath2Url.get(str) : "";
    }

    public static void stop() {
        UploadSingleUtils.getInstance().cancel();
    }

    public static void init() {
        isDestory = false;
    }

    public static void destory() {
        isDestory = true;
        localPath2Url.clear();
        localPath2Id.clear();
        pathListOfUpFailed.clear();
    }
}
