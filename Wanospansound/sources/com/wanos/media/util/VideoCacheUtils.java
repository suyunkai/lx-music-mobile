package com.wanos.media.util;

import android.os.Environment;
import com.baidubce.BceConfig;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.Utils;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import java.io.File;

/* JADX INFO: loaded from: classes3.dex */
public class VideoCacheUtils {
    private static final String TAG = "VideoCacheUtils";

    static {
        FileDownloadLog.NEED_LOG = false;
    }

    private static String getVideoFilePath(String str) {
        return initVideoCachePath() + getFileNameForUrl(str);
    }

    public static String getVideoCachePath(String str) {
        String str2 = initVideoCachePath() + getFileNameForUrl(str);
        ZeroCacheScan.getInstance().setVideoAccess(str2);
        return str2;
    }

    public static boolean isCached(String str) {
        return FileUtils.isFileExists(initVideoCachePath() + getFileNameForUrl(str));
    }

    public static void onLaunchTask(String str, final IVideoCacheListener iVideoCacheListener, Object obj) {
        int iStart = FileDownloader.getImpl().create(str).setPath(getVideoFilePath(str)).setTag(obj).setListener(new FileDownloadListener() { // from class: com.wanos.media.util.VideoCacheUtils.1
            @Override // com.liulishuo.filedownloader.FileDownloadListener
            protected void progress(BaseDownloadTask baseDownloadTask, int i, int i2) {
            }

            @Override // com.liulishuo.filedownloader.FileDownloadListener
            protected void pending(BaseDownloadTask baseDownloadTask, int i, int i2) {
                ZeroLogcatTools.d(VideoCacheUtils.TAG, "下载任务进入等待状态==>任务ID:" + baseDownloadTask.getId());
            }

            @Override // com.liulishuo.filedownloader.FileDownloadListener
            protected void completed(BaseDownloadTask baseDownloadTask) {
                ZeroLogcatTools.d(VideoCacheUtils.TAG, "下载任务下载结束==>任务ID:" + baseDownloadTask.getId() + " || 本地文件路径:" + baseDownloadTask.getPath());
                IVideoCacheListener iVideoCacheListener2 = iVideoCacheListener;
                if (iVideoCacheListener2 != null) {
                    iVideoCacheListener2.onTaskSuccess(baseDownloadTask.getId(), baseDownloadTask.getPath(), baseDownloadTask.getTag());
                }
            }

            @Override // com.liulishuo.filedownloader.FileDownloadListener
            protected void paused(BaseDownloadTask baseDownloadTask, int i, int i2) {
                ZeroLogcatTools.d(VideoCacheUtils.TAG, "下载任务进入暂停状态==>任务ID:" + baseDownloadTask.getId());
            }

            @Override // com.liulishuo.filedownloader.FileDownloadListener
            protected void error(BaseDownloadTask baseDownloadTask, Throwable th) {
                ZeroLogcatTools.d(VideoCacheUtils.TAG, "下载任务进入失败状态==>任务ID:" + baseDownloadTask.getId() + " || 错误信息:" + th.getMessage());
                IVideoCacheListener iVideoCacheListener2 = iVideoCacheListener;
                if (iVideoCacheListener2 != null) {
                    iVideoCacheListener2.onTaskError(baseDownloadTask.getId(), th.getMessage());
                }
            }

            @Override // com.liulishuo.filedownloader.FileDownloadListener
            protected void warn(BaseDownloadTask baseDownloadTask) {
                ZeroLogcatTools.d(VideoCacheUtils.TAG, "相同的任务==>任务ID:" + baseDownloadTask.getId());
            }
        }).start();
        if (iVideoCacheListener != null) {
            iVideoCacheListener.onTaskStart(iStart);
        }
    }

    private static String initVideoCachePath() {
        String str = Utils.getApp().getFilesDir().getAbsolutePath() + File.separator + Environment.DIRECTORY_MOVIES + File.separator + "video_zero" + File.separator;
        File file = new File(str);
        if (!file.exists() || !file.isDirectory()) {
            ZeroLogcatTools.d(TAG, "视频缓存路径创建：" + file.mkdirs());
        }
        return str;
    }

    private static String getFileNameForUrl(String str) {
        int i;
        if (StringUtils.isEmpty(str)) {
            ZeroLogcatTools.e(TAG, "视频网络路径:" + str);
            return "";
        }
        String[] strArrSplit = str.split("[?]");
        if (strArrSplit.length == 0) {
            return str;
        }
        if (strArrSplit.length != 1) {
            str = strArrSplit[0];
        }
        int iLastIndexOf = str.lastIndexOf(BceConfig.BOS_DELIMITER);
        return (iLastIndexOf < 0 || (i = iLastIndexOf + 1) >= str.length()) ? str : str.substring(i);
    }

    public interface IVideoCacheListener {
        default void onTaskStart(int i) {
        }

        void onTaskSuccess(int i, String str, Object obj);

        default void onTaskError(int i, String str) {
            ZeroLogcatTools.e(VideoCacheUtils.TAG, "视频缓存失败，任务ID=" + i + ", 错误信息=" + str);
        }
    }
}
