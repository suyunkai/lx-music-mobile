package com.danikula.videocache;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes2.dex */
public class ThreadPoolManager {
    private static final int CORE_POOL_SIZE = 0;
    private static final long KEEP_ALIVE_TIME = 60;
    private static final int MAX_POOL_SIZE = Integer.MAX_VALUE;
    private static final int QUEUE_CAPACITY = 10;
    private static final ExecutorService SOCKET_PROCESSOR;
    private static final TimeUnit TIME_UNIT;
    private static final BlockingQueue<Runnable> WORK_QUEUE;

    static {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        TIME_UNIT = timeUnit;
        SynchronousQueue synchronousQueue = new SynchronousQueue();
        WORK_QUEUE = synchronousQueue;
        SOCKET_PROCESSOR = new ThreadPoolExecutor(0, Integer.MAX_VALUE, KEEP_ALIVE_TIME, timeUnit, synchronousQueue, new ThreadFactory() { // from class: com.danikula.videocache.ThreadPoolManager.1
            private final AtomicInteger threadNumber = new AtomicInteger(1);

            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, "※WanosP-" + this.threadNumber.getAndIncrement());
            }
        }, new ThreadPoolExecutor.AbortPolicy());
    }

    private ThreadPoolManager() {
    }

    public static ExecutorService getSocketProcessor() {
        return SOCKET_PROCESSOR;
    }

    public static void shutdownAll() {
        ExecutorService executorService = SOCKET_PROCESSOR;
        executorService.shutdown();
        try {
            if (executorService.awaitTermination(KEEP_ALIVE_TIME, TimeUnit.SECONDS)) {
                return;
            }
            executorService.shutdownNow();
        } catch (InterruptedException unused) {
            SOCKET_PROCESSOR.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
