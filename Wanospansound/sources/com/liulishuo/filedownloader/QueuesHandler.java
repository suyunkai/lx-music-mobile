package com.liulishuo.filedownloader;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.SparseArray;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
class QueuesHandler implements IQueuesHandler {
    static final int WHAT_FREEZE = 2;
    static final int WHAT_SERIAL_NEXT = 1;
    static final int WHAT_UNFREEZE = 3;
    private final SparseArray<Handler> mRunningSerialMap = new SparseArray<>();

    QueuesHandler() {
    }

    @Override // com.liulishuo.filedownloader.IQueuesHandler
    public boolean startQueueParallel(FileDownloadListener fileDownloadListener) {
        int iHashCode = fileDownloadListener.hashCode();
        List<BaseDownloadTask.IRunningTask> listAssembleTasksToStart = FileDownloadList.getImpl().assembleTasksToStart(iHashCode, fileDownloadListener);
        if (onAssembledTasksToStart(iHashCode, listAssembleTasksToStart, fileDownloadListener, false)) {
            return false;
        }
        Iterator<BaseDownloadTask.IRunningTask> it = listAssembleTasksToStart.iterator();
        while (it.hasNext()) {
            it.next().startTaskByQueue();
        }
        return true;
    }

    @Override // com.liulishuo.filedownloader.IQueuesHandler
    public boolean startQueueSerial(FileDownloadListener fileDownloadListener) {
        SerialHandlerCallback serialHandlerCallback = new SerialHandlerCallback();
        int iHashCode = serialHandlerCallback.hashCode();
        List<BaseDownloadTask.IRunningTask> listAssembleTasksToStart = FileDownloadList.getImpl().assembleTasksToStart(iHashCode, fileDownloadListener);
        if (onAssembledTasksToStart(iHashCode, listAssembleTasksToStart, fileDownloadListener, true)) {
            return false;
        }
        HandlerThread handlerThread = new HandlerThread(FileDownloadUtils.formatString("filedownloader serial thread %s-%d", fileDownloadListener, Integer.valueOf(iHashCode)));
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper(), serialHandlerCallback);
        serialHandlerCallback.setHandler(handler);
        serialHandlerCallback.setList(listAssembleTasksToStart);
        serialHandlerCallback.goNext(0);
        synchronized (this.mRunningSerialMap) {
            this.mRunningSerialMap.put(iHashCode, handler);
        }
        return true;
    }

    @Override // com.liulishuo.filedownloader.IQueuesHandler
    public void freezeAllSerialQueues() {
        for (int i = 0; i < this.mRunningSerialMap.size(); i++) {
            freezeSerialHandler(this.mRunningSerialMap.get(this.mRunningSerialMap.keyAt(i)));
        }
    }

    @Override // com.liulishuo.filedownloader.IQueuesHandler
    public void unFreezeSerialQueues(List<Integer> list) {
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            unFreezeSerialHandler(this.mRunningSerialMap.get(it.next().intValue()));
        }
    }

    @Override // com.liulishuo.filedownloader.IQueuesHandler
    public int serialQueueSize() {
        return this.mRunningSerialMap.size();
    }

    @Override // com.liulishuo.filedownloader.IQueuesHandler
    public boolean contain(int i) {
        return this.mRunningSerialMap.get(i) != null;
    }

    private boolean onAssembledTasksToStart(int i, List<BaseDownloadTask.IRunningTask> list, FileDownloadListener fileDownloadListener, boolean z) {
        if (FileDownloadMonitor.isValid()) {
            FileDownloadMonitor.getMonitor().onRequestStart(list.size(), true, fileDownloadListener);
        }
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.v(FileDownloader.class, "start list attachKey[%d] size[%d] listener[%s] isSerial[%B]", Integer.valueOf(i), Integer.valueOf(list.size()), fileDownloadListener, Boolean.valueOf(z));
        }
        if (list != null && !list.isEmpty()) {
            return false;
        }
        FileDownloadLog.w(FileDownloader.class, "Tasks with the listener can't start, because can't find any task with the provided listener, maybe tasks instance has been started in the past, so they are all are inUsing, if in this case, you can use [BaseDownloadTask#reuse] to reuse theme first then start again: [%s, %B]", fileDownloadListener, Boolean.valueOf(z));
        return true;
    }

    private class SerialHandlerCallback implements Handler.Callback {
        private Handler mHandler;
        private List<BaseDownloadTask.IRunningTask> mList;
        private int mRunningIndex = 0;
        private SerialFinishListener mSerialFinishListener = new SerialFinishListener(new WeakReference(this));

        SerialHandlerCallback() {
        }

        public void setHandler(Handler handler) {
            this.mHandler = handler;
        }

        public void setList(List<BaseDownloadTask.IRunningTask> list) {
            this.mList = list;
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what == 1) {
                if (message.arg1 >= this.mList.size()) {
                    synchronized (QueuesHandler.this.mRunningSerialMap) {
                        QueuesHandler.this.mRunningSerialMap.remove(this.mList.get(0).getAttachKey());
                    }
                    Handler handler = this.mHandler;
                    FileDownloadListener listener = null;
                    if (handler != null && handler.getLooper() != null) {
                        this.mHandler.getLooper().quit();
                        this.mHandler = null;
                        this.mList = null;
                        this.mSerialFinishListener = null;
                    }
                    if (FileDownloadLog.NEED_LOG) {
                        Object[] objArr = new Object[2];
                        List<BaseDownloadTask.IRunningTask> list = this.mList;
                        if (list != null && list.get(0) != null) {
                            listener = this.mList.get(0).getOrigin().getListener();
                        }
                        objArr[0] = listener;
                        objArr[1] = Integer.valueOf(message.arg1);
                        FileDownloadLog.d(SerialHandlerCallback.class, "final serial %s %d", objArr);
                    }
                    return true;
                }
                int i = message.arg1;
                this.mRunningIndex = i;
                BaseDownloadTask.IRunningTask iRunningTask = this.mList.get(i);
                synchronized (iRunningTask.getPauseLock()) {
                    if (iRunningTask.getOrigin().getStatus() == 0 && !FileDownloadList.getImpl().isNotContains(iRunningTask)) {
                        iRunningTask.getOrigin().addFinishListener(this.mSerialFinishListener.setNextIndex(this.mRunningIndex + 1));
                        iRunningTask.startTaskByQueue();
                    }
                    if (FileDownloadLog.NEED_LOG) {
                        FileDownloadLog.d(SerialHandlerCallback.class, "direct go next by not contains %s %d", iRunningTask, Integer.valueOf(message.arg1));
                    }
                    goNext(message.arg1 + 1);
                    return true;
                }
            }
            if (message.what == 2) {
                freeze();
            } else if (message.what == 3) {
                unfreeze();
            }
            return true;
        }

        public void freeze() {
            this.mList.get(this.mRunningIndex).getOrigin().removeFinishListener(this.mSerialFinishListener);
            this.mHandler.removeCallbacksAndMessages(null);
        }

        public void unfreeze() {
            goNext(this.mRunningIndex);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void goNext(int i) {
            Handler handler = this.mHandler;
            if (handler == null || this.mList == null) {
                FileDownloadLog.w(this, "need go next %d, but params is not ready %s %s", Integer.valueOf(i), this.mHandler, this.mList);
                return;
            }
            Message messageObtainMessage = handler.obtainMessage();
            messageObtainMessage.what = 1;
            messageObtainMessage.arg1 = i;
            if (FileDownloadLog.NEED_LOG) {
                Object[] objArr = new Object[2];
                List<BaseDownloadTask.IRunningTask> list = this.mList;
                FileDownloadListener listener = null;
                if (list != null && list.get(0) != null) {
                    listener = this.mList.get(0).getOrigin().getListener();
                }
                objArr[0] = listener;
                objArr[1] = Integer.valueOf(messageObtainMessage.arg1);
                FileDownloadLog.d(SerialHandlerCallback.class, "start next %s %s", objArr);
            }
            this.mHandler.sendMessage(messageObtainMessage);
        }
    }

    private static class SerialFinishListener implements BaseDownloadTask.FinishListener {
        private int nextIndex;
        private final WeakReference<SerialHandlerCallback> wSerialHandlerCallback;

        private SerialFinishListener(WeakReference<SerialHandlerCallback> weakReference) {
            this.wSerialHandlerCallback = weakReference;
        }

        public BaseDownloadTask.FinishListener setNextIndex(int i) {
            this.nextIndex = i;
            return this;
        }

        @Override // com.liulishuo.filedownloader.BaseDownloadTask.FinishListener
        public void over(BaseDownloadTask baseDownloadTask) {
            WeakReference<SerialHandlerCallback> weakReference = this.wSerialHandlerCallback;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.wSerialHandlerCallback.get().goNext(this.nextIndex);
        }
    }

    private void freezeSerialHandler(Handler handler) {
        handler.sendEmptyMessage(2);
    }

    private void unFreezeSerialHandler(Handler handler) {
        handler.sendEmptyMessage(3);
    }
}
