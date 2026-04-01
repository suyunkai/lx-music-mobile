package com.wanos.careditproject.utils.exPlayer;

import android.text.TextUtils;
import android.util.Log;
import com.baidubce.BceConfig;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.wanos.careditproject.data.bean.EditProjectInitBean;
import com.wanos.careditproject.data.repo.AiRetrofitUtil;
import com.wanos.careditproject.data.response.AiPreviewProjectResponse;
import com.wanos.careditproject.data.response.EditProjectInitResponse;
import com.wanos.careditproject.model.server.RootModel;
import com.wanos.careditproject.utils.DataHelpAudioTrack;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.careditproject.utils.http.DownloadManager;
import com.wanos.careditproject.view.dialog.EditLoadingValue;
import java.io.File;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes3.dex */
class AiExPlayDataHelp {
    private static final String TAG = "Wanos[AI]-AiExPlayDataHelp";
    private volatile boolean isDestroy = false;
    private final HashMap<String, String> mHistoryData = new HashMap<>();

    public interface IDownLoadCallback {
        void onError(String str);

        void onSuccess();
    }

    AiExPlayDataHelp() {
    }

    public EditProjectInitResponse findProjectData(String str, String str2) throws Throwable {
        long jCurrentTimeMillis;
        if (!TextUtils.equals(str, this.mHistoryData.getOrDefault(str2, "-1"))) {
            Log.i(TAG, "findProjectData: 未创建过项目，创建项目并记录");
            AiPreviewProjectResponse aiPreviewProjectResponseCreateAiPreviewProject = AiRetrofitUtil.getInstance().createAiPreviewProject(str, str2);
            if (aiPreviewProjectResponseCreateAiPreviewProject == null) {
                throw new Throwable("预览项目创建失败");
            }
            if (aiPreviewProjectResponseCreateAiPreviewProject.code != 0) {
                throw new Throwable(aiPreviewProjectResponseCreateAiPreviewProject.msg);
            }
            this.mHistoryData.put(str2, str);
        } else {
            Log.i(TAG, "findProjectData: 项目已创建过，直接查询项目信息");
            TimeUnit.MILLISECONDS.sleep(1000L);
        }
        long jCurrentTimeMillis2 = System.currentTimeMillis();
        do {
            EditProjectInitResponse editProjectInitResponseFindAiPlayPreviewData = AiRetrofitUtil.getInstance().findAiPlayPreviewData(str, str2);
            if (editProjectInitResponseFindAiPlayPreviewData == null) {
                Log.e(TAG, "findProjectData: 工程数据获取失败");
                throw new Throwable("获取工程数据失败~");
            }
            if (editProjectInitResponseFindAiPlayPreviewData.code != 0) {
                Log.e(TAG, "findProjectData: 工程数据获取失败，" + editProjectInitResponseFindAiPlayPreviewData.msg);
                throw new Throwable(editProjectInitResponseFindAiPlayPreviewData.msg);
            }
            EditProjectInitBean editProjectInitBean = editProjectInitResponseFindAiPlayPreviewData.data;
            if (editProjectInitBean == null) {
                Log.e(TAG, "findProjectData: 工程数据获取失败，服务器未返回数据");
                throw new Throwable("获取工程数据失败~");
            }
            if (!StringUtils.isEmpty(editProjectInitBean.project)) {
                return editProjectInitResponseFindAiPlayPreviewData;
            }
            Log.i(TAG, "findProjectData: 服务器数据正在生成中... project = " + editProjectInitBean.project + ", ballRoute = " + editProjectInitBean.ballRoute + ", id = " + str + ", index = " + str2);
            TimeUnit.MILLISECONDS.sleep(3000L);
            jCurrentTimeMillis = System.currentTimeMillis() - jCurrentTimeMillis2;
            if (jCurrentTimeMillis > 60000) {
                break;
            }
        } while (!this.isDestroy);
        Log.w(TAG, "findProjectData: 项目预览超时，设定超时时间 = 60000, 当前已运行时间 = " + jCurrentTimeMillis + ", isDestroy = " + this.isDestroy);
        if (this.isDestroy) {
            throw new Throwable("ActivityDestroy");
        }
        throw new Throwable("获取项目信息超时~");
    }

    public List<String> initProjectData(EditProjectInitResponse editProjectInitResponse) throws Throwable {
        if (editProjectInitResponse == null) {
            throw new Throwable("项目信息获取失败~");
        }
        EditProjectInitBean editProjectInitBean = editProjectInitResponse.data;
        if (editProjectInitBean == null) {
            throw new Throwable(editProjectInitResponse.msg);
        }
        if (StringUtils.isEmpty(editProjectInitBean.project)) {
            throw new Throwable("项目信息数据异常");
        }
        Log.i(TAG, "initProjectData");
        DataHelpAudioTrack.initBallRoutes(editProjectInitBean.ballRoute);
        DataHelpAudioTrack.setRootModel((RootModel) GsonUtils.fromJson(editProjectInitBean.project, RootModel.class));
        List<String> allResList = DataHelpAudioTrack.getAllResList();
        if (allResList.isEmpty()) {
            throw new Throwable("项目信息数据解析异常");
        }
        EditLoadingValue.getInstance().stopLoadJson(allResList);
        EditLoadingValue.getInstance().setResFileCount(allResList.size());
        return allResList;
    }

    public void downloadAiFiles(final List<String> list, String str, String str2, final IDownLoadCallback iDownLoadCallback) {
        AiExPlayDataHelp aiExPlayDataHelp = this;
        if (list == null || list.isEmpty()) {
            iDownLoadCallback.onError("工程数据异常，资源地址为空");
            return;
        }
        boolean z = aiExPlayDataHelp.isDestroy;
        String str3 = TAG;
        if (z) {
            Log.w(TAG, "downloadAiFiles: 已销毁(开始下载)");
            return;
        }
        try {
            AtomicInteger atomicInteger = new AtomicInteger(0);
            final AtomicInteger atomicInteger2 = new AtomicInteger(0);
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                final String next = it.next();
                final String strRemoveParamOfUrl = EditingUtils.removeParamOfUrl(next);
                final String localFilePath = aiExPlayDataHelp.getLocalFilePath(strRemoveParamOfUrl, str, str2);
                if (FileUtils.isFileExists(localFilePath)) {
                    AiResUtils.addLocalPath(strRemoveParamOfUrl, localFilePath);
                    atomicInteger.incrementAndGet();
                    if (atomicInteger.get() + atomicInteger2.get() == list.size()) {
                        Log.i(str3, "downloadAiFiles: 文件下载完成，成功 = " + atomicInteger.get() + ", 失败 = " + atomicInteger2.get());
                        iDownLoadCallback.onSuccess();
                    }
                } else {
                    String str4 = str3;
                    Iterator<String> it2 = it;
                    final AtomicInteger atomicInteger3 = atomicInteger;
                    AtomicInteger atomicInteger4 = atomicInteger;
                    DownloadManager.getInstance().downAsynFile(next, localFilePath, Collections.emptyList(), new DownloadManager.DownloadResponse() { // from class: com.wanos.careditproject.utils.exPlayer.AiExPlayDataHelp$$ExternalSyntheticLambda2
                        @Override // com.wanos.careditproject.utils.http.DownloadManager.DownloadResponse
                        public final void onResponse(boolean z2) {
                            this.f$0.m410x6c157da3(localFilePath, next, atomicInteger3, strRemoveParamOfUrl, atomicInteger2, list, iDownLoadCallback, z2);
                        }
                    });
                    aiExPlayDataHelp = this;
                    str3 = str4;
                    it = it2;
                    atomicInteger = atomicInteger4;
                }
            }
        } catch (Throwable th) {
            iDownLoadCallback.onError(th.getMessage());
        }
    }

    /* JADX INFO: renamed from: lambda$downloadAiFiles$1$com-wanos-careditproject-utils-exPlayer-AiExPlayDataHelp, reason: not valid java name */
    /* synthetic */ void m410x6c157da3(String str, String str2, AtomicInteger atomicInteger, String str3, AtomicInteger atomicInteger2, List list, final IDownLoadCallback iDownLoadCallback, boolean z) {
        if (this.isDestroy) {
            Log.w(TAG, "downloadAiFiles: 已销毁(正在下载) isDelete = " + FileUtils.delete(str));
            return;
        }
        Log.d(TAG, "downloadAiFiles: fileUri = " + str2 + ", localFilePath = " + str);
        if (z) {
            atomicInteger.incrementAndGet();
            AiResUtils.addLocalPath(str3, str);
        } else {
            atomicInteger2.incrementAndGet();
        }
        if (atomicInteger.get() + atomicInteger2.get() == list.size()) {
            if (atomicInteger2.get() > 0) {
                ThreadUtils.runOnUiThread(new Runnable() { // from class: com.wanos.careditproject.utils.exPlayer.AiExPlayDataHelp$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        iDownLoadCallback.onError("资源加载失败，请重试~");
                    }
                });
                return;
            }
            Log.i(TAG, "downloadAiFiles: 文件下载完成，成功 = " + atomicInteger.get() + ", 失败 = " + atomicInteger2.get());
            Objects.requireNonNull(iDownLoadCallback);
            ThreadUtils.runOnUiThread(new Runnable() { // from class: com.wanos.careditproject.utils.exPlayer.AiExPlayDataHelp$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    iDownLoadCallback.onSuccess();
                }
            });
        }
    }

    public boolean deleteCache() throws Throwable {
        return FileUtils.deleteAllInDir(EditingUtils.getAiPath());
    }

    private String getLocalFilePath(String str, String str2, String str3) throws Throwable {
        if (StringUtils.isEmpty(str)) {
            throw new Throwable("工程数据异常，资源地址为空");
        }
        String[] strArrSplit = URLDecoder.decode(str, "UTF-8").split(BceConfig.BOS_DELIMITER);
        if (strArrSplit.length == 0) {
            Log.e(TAG, "getLocalFilePath: fileUri = " + str);
            throw new Throwable("工程数据异常，资源地址错误");
        }
        return getLocalCacheFolder(str2, str3) + File.separator + strArrSplit[strArrSplit.length - 1];
    }

    private String getLocalCacheFolder(String str, String str2) throws Throwable {
        String str3 = EditingUtils.getAiPath() + File.separator + str + File.separator + str2;
        if (FileUtils.createOrExistsDir(str3)) {
            return str3;
        }
        throw new Throwable("工程数据异常，缓存路径创建失败");
    }

    public void onCleared() {
        this.isDestroy = true;
        this.mHistoryData.clear();
        ThreadUtils.executeByIo(new ThreadUtils.SimpleTask<Boolean>() { // from class: com.wanos.careditproject.utils.exPlayer.AiExPlayDataHelp.1
            @Override // com.blankj.utilcode.util.ThreadUtils.Task
            public Boolean doInBackground() throws Throwable {
                return Boolean.valueOf(AiExPlayDataHelp.this.deleteCache());
            }

            @Override // com.blankj.utilcode.util.ThreadUtils.Task
            public void onSuccess(Boolean bool) {
                AiResUtils.clear();
                Log.i(AiExPlayDataHelp.TAG, "onCleared: 缓存清理结果 = " + bool);
            }

            @Override // com.blankj.utilcode.util.ThreadUtils.SimpleTask, com.blankj.utilcode.util.ThreadUtils.Task
            public void onFail(Throwable th) {
                super.onFail(th);
                AiResUtils.clear();
                Log.w(AiExPlayDataHelp.TAG, "onCleared: 缓存清理失败, " + th.getMessage());
            }
        });
    }
}
