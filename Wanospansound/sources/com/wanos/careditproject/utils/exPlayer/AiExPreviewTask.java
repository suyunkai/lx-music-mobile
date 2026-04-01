package com.wanos.careditproject.utils.exPlayer;

import android.util.Log;
import com.blankj.utilcode.util.ThreadUtils;
import com.wanos.careditproject.utils.exPlayer.AiExPlayDataHelp;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
class AiExPreviewTask {
    private static final String TAG = "wanos[AI]-AiExPreviewTask";
    private final AiExPlayDataHelp aiExPlayDataHelp;
    private final String index;
    private TaskState state = TaskState.IDLE;
    private final String trackId;

    public interface ITaskCallback {
        void onFail(Throwable th);

        void onSuccess(String str, String str2);
    }

    public enum TaskState {
        IDLE,
        RUNNING,
        SUCCESS,
        FAIL,
        CANCEL
    }

    public AiExPreviewTask(AiExPlayDataHelp aiExPlayDataHelp, String str, String str2) {
        this.aiExPlayDataHelp = aiExPlayDataHelp;
        this.trackId = str;
        this.index = str2;
    }

    public void execute(final ITaskCallback iTaskCallback) {
        this.state = TaskState.RUNNING;
        final long jCurrentTimeMillis = System.currentTimeMillis();
        ThreadUtils.executeByIo(new ThreadUtils.SimpleTask<List<String>>() { // from class: com.wanos.careditproject.utils.exPlayer.AiExPreviewTask.1
            @Override // com.blankj.utilcode.util.ThreadUtils.Task
            public List<String> doInBackground() throws Throwable {
                return AiExPreviewTask.this.aiExPlayDataHelp.initProjectData(AiExPreviewTask.this.aiExPlayDataHelp.findProjectData(AiExPreviewTask.this.trackId, AiExPreviewTask.this.index));
            }

            @Override // com.blankj.utilcode.util.ThreadUtils.Task
            public void onSuccess(List<String> list) {
                AiResUtils.clear();
                if (AiExPreviewTask.this.state == TaskState.CANCEL) {
                    Log.w(AiExPreviewTask.TAG, "execute: trackId = " + AiExPreviewTask.this.trackId + ", index = " + AiExPreviewTask.this.index + ", 任务已取消(接口)");
                } else {
                    AiExPreviewTask.this.aiExPlayDataHelp.downloadAiFiles(list, AiExPreviewTask.this.trackId, AiExPreviewTask.this.index, new AiExPlayDataHelp.IDownLoadCallback() { // from class: com.wanos.careditproject.utils.exPlayer.AiExPreviewTask.1.1
                        @Override // com.wanos.careditproject.utils.exPlayer.AiExPlayDataHelp.IDownLoadCallback
                        public void onSuccess() {
                            if (AiExPreviewTask.this.state == TaskState.CANCEL) {
                                Log.w(AiExPreviewTask.TAG, "execute: trackId = " + AiExPreviewTask.this.trackId + ", index = " + AiExPreviewTask.this.index + ", 任务已取消(下载)");
                                return;
                            }
                            Log.i(AiExPreviewTask.TAG, "onSuccess: AI预览资源加载成功，耗时 = " + (System.currentTimeMillis() - jCurrentTimeMillis) + "ms");
                            AiExPreviewTask.this.state = TaskState.SUCCESS;
                            iTaskCallback.onSuccess(AiExPreviewTask.this.trackId, AiExPreviewTask.this.index);
                        }

                        @Override // com.wanos.careditproject.utils.exPlayer.AiExPlayDataHelp.IDownLoadCallback
                        public void onError(String str) {
                            if (AiExPreviewTask.this.state == TaskState.CANCEL) {
                                Log.w(AiExPreviewTask.TAG, "execute: trackId = " + AiExPreviewTask.this.trackId + ", index = " + AiExPreviewTask.this.index + ", 任务已取消(下载失败)");
                                return;
                            }
                            Log.i(AiExPreviewTask.TAG, "onSuccess: AI预览资源加载失败，耗时 = " + (System.currentTimeMillis() - jCurrentTimeMillis) + "ms");
                            AiExPreviewTask.this.state = TaskState.FAIL;
                            iTaskCallback.onFail(new Throwable(str));
                        }
                    });
                }
            }

            @Override // com.blankj.utilcode.util.ThreadUtils.SimpleTask, com.blankj.utilcode.util.ThreadUtils.Task
            public void onFail(Throwable th) {
                super.onFail(th);
                if (AiExPreviewTask.this.state == TaskState.CANCEL) {
                    Log.w(AiExPreviewTask.TAG, "execute: trackId = " + AiExPreviewTask.this.trackId + ", index = " + AiExPreviewTask.this.index + ", 任务已取消(接口错误)");
                    return;
                }
                if ("ActivityDestroy".equals(th.getMessage())) {
                    Log.w(AiExPreviewTask.TAG, "onFail: ActivityDestroy");
                    return;
                }
                Log.i(AiExPreviewTask.TAG, "onSuccess: AI预览资源加载失败，耗时 = " + (System.currentTimeMillis() - jCurrentTimeMillis) + "ms");
                AiExPreviewTask.this.state = TaskState.FAIL;
                iTaskCallback.onFail(th);
            }
        });
    }

    public void cancel() {
        Log.d(TAG, "cancel: state = " + this.state);
        this.state = TaskState.CANCEL;
    }

    public boolean isRunning() {
        return this.state == TaskState.RUNNING;
    }
}
