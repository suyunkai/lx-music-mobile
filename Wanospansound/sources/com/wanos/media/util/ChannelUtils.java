package com.wanos.media.util;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;

/* JADX INFO: loaded from: classes3.dex */
public class ChannelUtils {
    public static final String TAG = "wanos:[ChannelUtils]";
    private static HandlerThread channelThread;
    private static ChannelUtils instance;
    private Handler channelHandler;
    private ChannelPollListener listener;
    private Runnable longPressRunnable;

    public interface ChannelPollListener {
        default void longPressTimeOut() {
        }
    }

    public static ChannelUtils getInstance() {
        if (instance == null) {
            instance = new ChannelUtils();
        }
        return instance;
    }

    private void getPollHandler() {
        HandlerThread handlerThread = new HandlerThread("channelThread");
        channelThread = handlerThread;
        handlerThread.start();
        if (this.channelHandler == null) {
            this.channelHandler = new Handler(channelThread.getLooper());
        }
    }

    public void longPressPoll() {
        getPollHandler();
        this.longPressRunnable = new LongPressRunnable(this);
        Log.i(TAG, "版本号长按计时开始----");
        this.channelHandler.post(this.longPressRunnable);
    }

    public void clearHandler() {
        HandlerThread handlerThread = channelThread;
        if (handlerThread != null && handlerThread.isAlive()) {
            Log.i(TAG, "停止上次轮询线程");
            channelThread.quitSafely();
        }
        if (this.channelHandler != null) {
            Log.i(TAG, "移除上次longPressRunnable");
            this.channelHandler.removeCallbacks(this.longPressRunnable);
            this.channelHandler.removeCallbacksAndMessages(null);
            this.channelHandler = null;
        }
    }

    public void setChannelPollListener(ChannelPollListener listener) {
        this.listener = listener;
    }

    private static class LongPressRunnable implements Runnable {
        private int longPressTime = 0;
        private WeakReference<ChannelUtils> pollUtilsRef;

        public LongPressRunnable(ChannelUtils channelUtils) {
            this.pollUtilsRef = new WeakReference<>(channelUtils);
        }

        @Override // java.lang.Runnable
        public void run() {
            ChannelUtils channelUtils = this.pollUtilsRef.get();
            try {
                int i = this.longPressTime;
                if (i >= 2000) {
                    Log.i(ChannelUtils.TAG, "版本号长按2秒----" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(System.currentTimeMillis())));
                    channelUtils.clearHandler();
                    if (channelUtils.listener != null) {
                        channelUtils.listener.longPressTimeOut();
                        return;
                    }
                    return;
                }
                this.longPressTime = i + 1000;
                Log.i(ChannelUtils.TAG, "版本号长按中----" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(System.currentTimeMillis())));
                channelUtils.channelHandler.postDelayed(this, 1000L);
            } catch (Exception e) {
                Log.e(ChannelUtils.TAG, e.getMessage());
            }
        }
    }
}
