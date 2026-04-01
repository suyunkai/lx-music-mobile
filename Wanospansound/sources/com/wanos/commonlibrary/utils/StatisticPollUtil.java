package com.wanos.commonlibrary.utils;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import java.lang.ref.SoftReference;
import java.text.SimpleDateFormat;

/* JADX INFO: loaded from: classes3.dex */
public class StatisticPollUtil {
    public static final String TAG = "wanos:[StatisticPollUtil]";
    private static StatisticPollUtil instance;
    private ReportedPollListener listener;
    private Handler pollHandler;
    private HandlerThread pollThread;
    private Runnable reportedRunnable;

    public interface ReportedPollListener {
        void reported(String str);
    }

    public static StatisticPollUtil getInstance() {
        if (instance == null) {
            instance = new StatisticPollUtil();
        }
        return instance;
    }

    public Handler getpollHandler() {
        HandlerThread handlerThread = new HandlerThread("pollThread");
        this.pollThread = handlerThread;
        handlerThread.start();
        Handler handler = new Handler(this.pollThread.getLooper());
        this.pollHandler = handler;
        return handler;
    }

    public void startPoll(String str) {
        clearHandler();
        getpollHandler();
        ReportedRunnable reportedRunnable = new ReportedRunnable(this, str);
        this.reportedRunnable = reportedRunnable;
        this.pollHandler.post(reportedRunnable);
    }

    public void clearHandler() {
        HandlerThread handlerThread = this.pollThread;
        if (handlerThread != null && handlerThread.isAlive()) {
            Log.i(TAG, "停止上次轮询线程");
            this.pollThread.quitSafely();
        }
        if (this.pollHandler != null) {
            Log.i(TAG, "移除上次Callbacks");
            this.pollHandler.removeCallbacks(this.reportedRunnable);
            this.pollHandler.removeCallbacksAndMessages(null);
            this.pollHandler = null;
        }
    }

    public void setReportedPollListener(ReportedPollListener reportedPollListener) {
        this.listener = reportedPollListener;
    }

    private static class ReportedRunnable implements Runnable {
        private int expireTime = 0;
        private String id;
        private SoftReference<StatisticPollUtil> pollUtilsRef;

        public ReportedRunnable(StatisticPollUtil statisticPollUtil, String str) {
            this.id = "";
            this.pollUtilsRef = new SoftReference<>(statisticPollUtil);
            this.id = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            StatisticPollUtil statisticPollUtil = this.pollUtilsRef.get();
            if (statisticPollUtil != null) {
                try {
                    int i = this.expireTime;
                    if (i == 30000) {
                        Log.i(StatisticPollUtil.TAG, "埋点上报中----" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(System.currentTimeMillis())));
                        statisticPollUtil.clearHandler();
                        if (statisticPollUtil.listener != null) {
                            statisticPollUtil.listener.reported(this.id);
                            return;
                        }
                        return;
                    }
                    this.expireTime = i + 1000;
                    Log.i(StatisticPollUtil.TAG, "埋点上报中----" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(System.currentTimeMillis())));
                    statisticPollUtil.pollHandler.postDelayed(this, 1000L);
                } catch (Exception e) {
                    Log.e(StatisticPollUtil.TAG, e.getMessage());
                }
            }
        }
    }
}
