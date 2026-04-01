package com.wanos.media.util;

import android.os.Environment;
import com.baidubce.BceConfig;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.blankj.utilcode.util.Utils;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloadQueueSet;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.wanos.media.entity.ThemeAudioInfoEntity;
import com.wanos.media.util.MusicCacheUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes3.dex */
public class MusicCacheUtils {
    private static final String TAG = "MusicCacheUtils";
    private static final CopyOnWriteArrayList<Integer> mMusicDownLoadIds = new CopyOnWriteArrayList<>();

    public interface IMultiAudioCacheCallback {
        default void onTaskError(String str) {
        }

        void onTaskSuccess(ArrayList<ThemeAudioInfoEntity> arrayList, int i);
    }

    public interface IMusicCacheListener {
        void onTaskError(int i, String str);

        void onTaskStart(int i);

        void onTaskSuccess(int i, String str, Object obj);
    }

    static {
        FileDownloadLog.NEED_LOG = false;
        FileDownloader.getImpl().setMaxNetworkThreadCount(6);
    }

    public static void onPauseTask() {
        FileDownloader.getImpl().pauseAll();
    }

    public static void onPauseMusicTask() {
        int i = 0;
        while (true) {
            CopyOnWriteArrayList<Integer> copyOnWriteArrayList = mMusicDownLoadIds;
            if (i < copyOnWriteArrayList.size()) {
                FileDownloader.getImpl().pause(copyOnWriteArrayList.get(i).intValue());
                i++;
            } else {
                copyOnWriteArrayList.clear();
                return;
            }
        }
    }

    /* JADX INFO: renamed from: com.wanos.media.util.MusicCacheUtils$1, reason: invalid class name */
    class AnonymousClass1 extends FileDownloadListener {
        int mErrorSize = 0;
        final ArrayList<ThemeAudioInfoEntity> mReadyAudio = new ArrayList<>();
        final int mScreenId;
        final /* synthetic */ List val$audioUrls;
        final /* synthetic */ IMultiAudioCacheCallback val$callback;
        final /* synthetic */ int val$sceneId;

        AnonymousClass1(int i, List list, IMultiAudioCacheCallback iMultiAudioCacheCallback) {
            this.val$sceneId = i;
            this.val$audioUrls = list;
            this.val$callback = iMultiAudioCacheCallback;
            this.mScreenId = i;
        }

        @Override // com.liulishuo.filedownloader.FileDownloadListener
        protected void pending(BaseDownloadTask baseDownloadTask, int i, int i2) {
            ZeroLogcatTools.d(MusicCacheUtils.TAG, "pending: 任务ID=" + baseDownloadTask.getId());
        }

        @Override // com.liulishuo.filedownloader.FileDownloadListener
        protected void progress(BaseDownloadTask baseDownloadTask, int i, int i2) {
            ZeroLogcatTools.d(MusicCacheUtils.TAG, "progress: 任务ID=" + baseDownloadTask.getId());
        }

        static /* synthetic */ boolean lambda$completed$0(BaseDownloadTask baseDownloadTask, Integer num) {
            return baseDownloadTask.getId() == num.intValue();
        }

        @Override // com.liulishuo.filedownloader.FileDownloadListener
        protected void completed(final BaseDownloadTask baseDownloadTask) {
            MusicCacheUtils.mMusicDownLoadIds.removeIf(new Predicate() { // from class: com.wanos.media.util.MusicCacheUtils$1$$ExternalSyntheticLambda2
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return MusicCacheUtils.AnonymousClass1.lambda$completed$0(baseDownloadTask, (Integer) obj);
                }
            });
            ThemeAudioInfoEntity themeAudioInfoEntity = (ThemeAudioInfoEntity) baseDownloadTask.getTag();
            ZeroLogcatTools.d(MusicCacheUtils.TAG, "onLaunchSceneTask: 音源加载完成，音源名称=" + themeAudioInfoEntity.getSoundName());
            this.mReadyAudio.add(themeAudioInfoEntity);
            if (this.mErrorSize + this.mReadyAudio.size() == this.val$audioUrls.size()) {
                ZeroLogcatTools.d(MusicCacheUtils.TAG, "onLaunchSceneTask: 音源处理失败数量 = " + this.mErrorSize + ", 解绑服务结果 = " + FileDownloader.getImpl().unBindServiceIfIdle());
                final IMultiAudioCacheCallback iMultiAudioCacheCallback = this.val$callback;
                if (iMultiAudioCacheCallback != null) {
                    ThreadUtils.runOnUiThread(new Runnable() { // from class: com.wanos.media.util.MusicCacheUtils$1$$ExternalSyntheticLambda3
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.m567lambda$completed$1$comwanosmediautilMusicCacheUtils$1(iMultiAudioCacheCallback);
                        }
                    });
                }
            }
        }

        /* JADX INFO: renamed from: lambda$completed$1$com-wanos-media-util-MusicCacheUtils$1, reason: not valid java name */
        /* synthetic */ void m567lambda$completed$1$comwanosmediautilMusicCacheUtils$1(IMultiAudioCacheCallback iMultiAudioCacheCallback) {
            iMultiAudioCacheCallback.onTaskSuccess(this.mReadyAudio, this.mScreenId);
        }

        @Override // com.liulishuo.filedownloader.FileDownloadListener
        protected void paused(BaseDownloadTask baseDownloadTask, int i, int i2) {
            ZeroLogcatTools.d(MusicCacheUtils.TAG, "onLaunchSceneTask: 音源缓存暂停=" + baseDownloadTask.getId());
            this.mReadyAudio.clear();
        }

        @Override // com.liulishuo.filedownloader.FileDownloadListener
        protected void error(final BaseDownloadTask baseDownloadTask, final Throwable th) {
            ZeroLogcatTools.d(MusicCacheUtils.TAG, "onLaunchSceneTask: 音源缓存失败=" + th.getMessage());
            MusicCacheUtils.mMusicDownLoadIds.removeIf(new Predicate() { // from class: com.wanos.media.util.MusicCacheUtils$1$$ExternalSyntheticLambda0
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return MusicCacheUtils.AnonymousClass1.lambda$error$2(baseDownloadTask, (Integer) obj);
                }
            });
            this.mErrorSize++;
            final IMultiAudioCacheCallback iMultiAudioCacheCallback = this.val$callback;
            if (iMultiAudioCacheCallback != null) {
                ThreadUtils.runOnUiThread(new Runnable() { // from class: com.wanos.media.util.MusicCacheUtils$1$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        iMultiAudioCacheCallback.onTaskError(th.getMessage());
                    }
                });
            }
        }

        static /* synthetic */ boolean lambda$error$2(BaseDownloadTask baseDownloadTask, Integer num) {
            return baseDownloadTask.getId() == num.intValue();
        }

        @Override // com.liulishuo.filedownloader.FileDownloadListener
        protected void warn(BaseDownloadTask baseDownloadTask) {
            ZeroLogcatTools.d(MusicCacheUtils.TAG, "warn: 任务ID=" + baseDownloadTask.getId());
        }
    }

    public static void onLaunchSceneTask(List<ThemeAudioInfoEntity> list, int i, IMultiAudioCacheCallback iMultiAudioCacheCallback) {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(i, list, iMultiAudioCacheCallback);
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < list.size(); i2++) {
            ThemeAudioInfoEntity themeAudioInfoEntity = list.get(i2);
            String wanosPath = themeAudioInfoEntity.getWanosPath();
            BaseDownloadTask callbackProgressIgnored = FileDownloader.getImpl().create(wanosPath).setPath(getMusicFilePath(wanosPath)).setTag(themeAudioInfoEntity).setCallbackProgressIgnored();
            mMusicDownLoadIds.add(Integer.valueOf(callbackProgressIgnored.getId()));
            arrayList.add(callbackProgressIgnored);
        }
        FileDownloadQueueSet fileDownloadQueueSet = new FileDownloadQueueSet(anonymousClass1);
        fileDownloadQueueSet.setAutoRetryTimes(1);
        fileDownloadQueueSet.setSyncCallback(true);
        fileDownloadQueueSet.downloadTogether(arrayList);
        fileDownloadQueueSet.start();
        ZeroLogcatTools.d(TAG, "onLaunchSceneTask: 音源加载任务已启动，任务数量=" + arrayList.size());
    }

    public static void onLaunchTask(String str, final IMusicCacheListener iMusicCacheListener, Object obj) {
        String musicFilePath = getMusicFilePath(str);
        if (FileUtils.isFileExists(musicFilePath)) {
            ZeroLogcatTools.d(TAG, "onLaunchTask: 音频文件已存在，直接返回，文件路径=" + musicFilePath);
            if (iMusicCacheListener != null) {
                iMusicCacheListener.onTaskSuccess(-1, musicFilePath, obj);
                return;
            }
            return;
        }
        ZeroLogcatTools.d(TAG, "onLaunchTask: 开始下载音频文件，网络路径=" + str);
        int iStart = FileDownloader.getImpl().create(str).setPath(getMusicFilePath(str)).setTag(obj).setCallbackProgressIgnored().setListener(new FileDownloadListener() { // from class: com.wanos.media.util.MusicCacheUtils.2
            @Override // com.liulishuo.filedownloader.FileDownloadListener
            protected void pending(BaseDownloadTask baseDownloadTask, int i, int i2) {
                ZeroLogcatTools.d(MusicCacheUtils.TAG, "下载任务进入等待状态==>任务ID:" + baseDownloadTask.getId());
            }

            @Override // com.liulishuo.filedownloader.FileDownloadListener
            protected void progress(BaseDownloadTask baseDownloadTask, int i, int i2) {
                ZeroLogcatTools.d(MusicCacheUtils.TAG, "下载任务进入下载中状态==>任务ID:" + baseDownloadTask.getId() + " || 下载进度:" + Math.round((i / i2) * 100.0f));
            }

            @Override // com.liulishuo.filedownloader.FileDownloadListener
            protected void completed(BaseDownloadTask baseDownloadTask) {
                ZeroLogcatTools.d(MusicCacheUtils.TAG, "下载任务下载结束==>任务ID:" + baseDownloadTask.getId() + " || 本地文件路径:" + baseDownloadTask.getPath());
                ZeroLogcatTools.d(MusicCacheUtils.TAG, "onLaunchTask: 解绑服务结果=" + FileDownloader.getImpl().unBindServiceIfIdle());
                IMusicCacheListener iMusicCacheListener2 = iMusicCacheListener;
                if (iMusicCacheListener2 != null) {
                    iMusicCacheListener2.onTaskSuccess(baseDownloadTask.getId(), baseDownloadTask.getPath(), baseDownloadTask.getTag());
                }
            }

            @Override // com.liulishuo.filedownloader.FileDownloadListener
            protected void paused(BaseDownloadTask baseDownloadTask, int i, int i2) {
                ZeroLogcatTools.d(MusicCacheUtils.TAG, "下载任务进入暂停状态==>任务ID:" + baseDownloadTask.getId());
            }

            @Override // com.liulishuo.filedownloader.FileDownloadListener
            protected void error(BaseDownloadTask baseDownloadTask, Throwable th) {
                ZeroLogcatTools.d(MusicCacheUtils.TAG, "下载任务进入失败状态==>任务ID:" + baseDownloadTask.getId() + " || 错误信息:" + th.getMessage());
                ZeroLogcatTools.d(MusicCacheUtils.TAG, "onLaunchTask: 解绑服务结果=" + FileDownloader.getImpl().unBindServiceIfIdle());
                IMusicCacheListener iMusicCacheListener2 = iMusicCacheListener;
                if (iMusicCacheListener2 != null) {
                    iMusicCacheListener2.onTaskError(baseDownloadTask.getId(), th.getMessage());
                }
            }

            @Override // com.liulishuo.filedownloader.FileDownloadListener
            protected void warn(BaseDownloadTask baseDownloadTask) {
                ZeroLogcatTools.d(MusicCacheUtils.TAG, "相同的任务==>任务ID:" + baseDownloadTask.getId());
            }
        }).start();
        if (iMusicCacheListener != null) {
            iMusicCacheListener.onTaskStart(iStart);
        }
    }

    private static String getMusicFilePath(String str) {
        return initAudioCachePath() + getFileNameForUrl(str);
    }

    public static boolean isCache(String str) {
        return FileUtils.isFileExists(getMusicFilePath(str));
    }

    public static String getMusicCachePath(String str) {
        String str2 = initAudioCachePath() + getFileNameForUrl(str);
        ZeroCacheScan.getInstance().setMusicAccess(str2);
        return str2;
    }

    public static String initAudioCachePath() {
        String str = Utils.getApp().getFilesDir().getAbsolutePath() + File.separator + Environment.DIRECTORY_MUSIC + File.separator + "audio_zero" + File.separator;
        File file = new File(str);
        if (!file.exists() || !file.isDirectory()) {
            ZeroLogcatTools.d(TAG, "音源缓存路径创建：" + file.mkdirs());
        }
        return str;
    }

    private static String getFileNameForUrl(String str) {
        int i;
        if (StringUtils.isEmpty(str)) {
            ZeroLogcatTools.e(TAG, "音频网络路径:" + str);
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
}
