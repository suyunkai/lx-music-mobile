package com.wanos.media;

import android.app.Application;
import com.blankj.utilcode.util.ProcessUtils;
import com.liulishuo.filedownloader.FileDownloader;
import com.wanos.media.entity.IZeroCallback;
import com.wanos.media.util.ZeroCacheScan;
import com.wanos.media.util.ZeroLogcatTools;
import com.wanos.util.Constant;

/* JADX INFO: loaded from: classes3.dex */
public class ZeroApplication {
    private static final String TAG = "ZeroApplication";
    private static Application mContext;
    private static IZeroCallback mIZeroCallback;

    public static void initZeroModel(Application application, IZeroCallback iZeroCallback) {
        mIZeroCallback = iZeroCallback;
        if (ProcessUtils.isMainProcess()) {
            mContext = application;
            FileDownloader.setupOnApplicationOnCreate(application);
            ZeroCacheScan.getInstance().setMusicClean();
            ZeroCacheScan.getInstance().setVideoClean();
            ZeroCacheScan.getInstance().setImageClean();
            ZeroLogcatTools.i(TAG, getInitConfigStringBuilder().toString());
        }
    }

    private static StringBuilder getInitConfigStringBuilder() {
        StringBuilder sb = new StringBuilder();
        sb.append("| -------------------------------------------- |\n| 播放器布局 = ");
        sb.append(Constant.getPlayerLayoutName());
        sb.append("\n| 播放器声道数 = ");
        sb.append(Constant.getPlayerChannelNum());
        sb.append("\n| 播放器音量配置 = ");
        sb.append(Constant.getVolumeConfig().getMateDataName());
        sb.append("\n| -------------------------------------------- |");
        return sb;
    }

    public static Application getApplication() {
        Application application = mContext;
        if (application != null) {
            return application;
        }
        throw new NullPointerException("请在Application中调用 ZeroApplication.initZeroModel() 方法");
    }

    public static IZeroCallback getZeroCallback() {
        IZeroCallback iZeroCallback = mIZeroCallback;
        if (iZeroCallback != null) {
            return iZeroCallback;
        }
        throw new NullPointerException("请在Application中调用 ZeroApplication.initZeroModel() 方法");
    }
}
