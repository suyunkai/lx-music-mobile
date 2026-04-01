package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.ITaskHunter;
import com.liulishuo.filedownloader.message.MessageSnapshotFlow;
import com.liulishuo.filedownloader.util.FileDownloadExecutors;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

/* JADX INFO: loaded from: classes2.dex */
class FileDownloadTaskLauncher {
    private final LaunchTaskPool mLaunchTaskPool = new LaunchTaskPool();

    FileDownloadTaskLauncher() {
    }

    private static class HolderClass {
        private static final FileDownloadTaskLauncher INSTANCE = new FileDownloadTaskLauncher();

        private HolderClass() {
        }

        static {
            MessageSnapshotFlow.getImpl().setReceiver(new MessageSnapshotGate());
        }
    }

    public static FileDownloadTaskLauncher getImpl() {
        return HolderClass.INSTANCE;
    }

    synchronized void launch(ITaskHunter.IStarter iStarter) {
        this.mLaunchTaskPool.asyncExecute(iStarter);
    }

    synchronized void expireAll() {
        this.mLaunchTaskPool.expireAll();
    }

    synchronized void expire(ITaskHunter.IStarter iStarter) {
        this.mLaunchTaskPool.expire(iStarter);
    }

    synchronized void expire(FileDownloadListener fileDownloadListener) {
        this.mLaunchTaskPool.expire(fileDownloadListener);
    }

    private static class LaunchTaskPool {
        private ThreadPoolExecutor mPool;
        private LinkedBlockingQueue<Runnable> mWorkQueue;

        LaunchTaskPool() {
            init();
        }

        public void asyncExecute(ITaskHunter.IStarter iStarter) {
            this.mPool.execute(new LaunchTaskRunnable(iStarter));
        }

        public void expire(ITaskHunter.IStarter iStarter) {
            this.mWorkQueue.remove(iStarter);
        }

        public void expire(FileDownloadListener fileDownloadListener) {
            if (fileDownloadListener == null) {
                FileDownloadLog.w(this, "want to expire by listener, but the listener provided is null", new Object[0]);
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (Runnable runnable : this.mWorkQueue) {
                LaunchTaskRunnable launchTaskRunnable = (LaunchTaskRunnable) runnable;
                if (launchTaskRunnable.isSameListener(fileDownloadListener)) {
                    launchTaskRunnable.expire();
                    arrayList.add(runnable);
                }
            }
            if (arrayList.isEmpty()) {
                return;
            }
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(this, "expire %d tasks with listener[%s]", Integer.valueOf(arrayList.size()), fileDownloadListener);
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                this.mPool.remove((Runnable) it.next());
            }
        }

        public void expireAll() {
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(this, "expire %d tasks", Integer.valueOf(this.mWorkQueue.size()));
            }
            this.mPool.shutdownNow();
            init();
        }

        private void init() {
            LinkedBlockingQueue<Runnable> linkedBlockingQueue = new LinkedBlockingQueue<>();
            this.mWorkQueue = linkedBlockingQueue;
            this.mPool = FileDownloadExecutors.newDefaultThreadPool(3, linkedBlockingQueue, "LauncherTask");
        }
    }

    private static class LaunchTaskRunnable implements Runnable {
        private boolean mExpired = false;
        private final ITaskHunter.IStarter mTaskStarter;

        LaunchTaskRunnable(ITaskHunter.IStarter iStarter) {
            this.mTaskStarter = iStarter;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.mExpired) {
                return;
            }
            this.mTaskStarter.start();
        }

        public boolean isSameListener(FileDownloadListener fileDownloadListener) {
            ITaskHunter.IStarter iStarter = this.mTaskStarter;
            return iStarter != null && iStarter.equalListener(fileDownloadListener);
        }

        public boolean equals(Object obj) {
            return super.equals(obj) || obj == this.mTaskStarter;
        }

        public void expire() {
            this.mExpired = true;
        }
    }
}
