package com.wanos.media.util;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import androidx.media3.exoplayer.dash.DashMediaSource;
import com.blankj.utilcode.util.Utils;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.WanosCommunication.bean.OrderInfoBean;
import com.wanos.WanosCommunication.response.GetOrderInfoByOrderNoResponse;
import com.wanos.WanosCommunication.response.GetUserInfoResponse;
import com.wanos.commonlibrary.bean.UserInfo;
import com.wanos.commonlibrary.event.LoginOrLogoutEvent;
import com.wanos.commonlibrary.event.UserInfoChangeEvent;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes3.dex */
public class PollUtils {
    public static final String TAG = "wanos:[PollUtils]";
    private static PollUtils instance;
    private static HandlerThread pollThread;
    private MemberPayPollListener listener;
    private Handler payHandler;
    private PollStatusRunnable statusRunnable;
    private PollTimeRunnable timeRunnable;

    public interface MemberPayPollListener {
        default void paySuccess() {
        }

        default void timeOut() {
        }
    }

    public static PollUtils getInstance() {
        if (instance == null) {
            instance = new PollUtils();
        }
        return instance;
    }

    private void getPollHandler() {
        HandlerThread handlerThread = new HandlerThread("pollThread");
        pollThread = handlerThread;
        handlerThread.start();
        if (this.payHandler == null) {
            this.payHandler = new Handler(pollThread.getLooper());
        }
    }

    public void startPoll(String orderNo, long expireTime) {
        clearHandler();
        getPollHandler();
        this.timeRunnable = new PollTimeRunnable(this, expireTime);
        this.statusRunnable = new PollStatusRunnable(this, orderNo);
        this.payHandler.post(this.timeRunnable);
        this.payHandler.post(this.statusRunnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pollStatus(String orderNo) {
        WanOSRetrofitUtil.getOrderDetail(orderNo, new ResponseCallBack<GetOrderInfoByOrderNoResponse>(Utils.getApp()) { // from class: com.wanos.media.util.PollUtils.1
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetOrderInfoByOrderNoResponse response) {
                if (response.data != null) {
                    Log.i(PollUtils.TAG, "状态轮询中----" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(System.currentTimeMillis())));
                    OrderInfoBean orderInfo = response.data.getOrderInfo();
                    if (orderInfo == null || orderInfo.getPayStatus() != OrderInfoBean.OrderPayStatus.STATUS_SUCCESS) {
                        return;
                    }
                    Log.i(PollUtils.TAG, "状态轮询成功----" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(System.currentTimeMillis())));
                    PollUtils.this.clearHandler();
                    PollUtils.this.refreshUserInfo();
                }
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int code, String msg) {
                Log.i(PollUtils.TAG, "onResponseFailure: code=" + code + " msg=" + msg);
                if (code == 20000 || code == 20001 || code == 20005) {
                    PollUtils.this.clearHandler();
                    Log.i(PollUtils.TAG, "onResponseFailure: clearHandler");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pollTime(long expireTime) {
        Log.i(TAG, "超时轮询中----" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(System.currentTimeMillis())));
        if (TimerUtil.isTimeOut(expireTime)) {
            Log.i(TAG, "超时轮询成功----" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(System.currentTimeMillis())));
            clearHandler();
            MemberPayPollListener memberPayPollListener = this.listener;
            if (memberPayPollListener != null) {
                memberPayPollListener.timeOut();
                return;
            }
            return;
        }
        this.payHandler.postDelayed(this.timeRunnable, DashMediaSource.DEFAULT_FALLBACK_TARGET_LIVE_OFFSET_MS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshUserInfo() {
        WanOSRetrofitUtil.getUserInfo(new ResponseCallBack<GetUserInfoResponse>(Utils.getApp()) { // from class: com.wanos.media.util.PollUtils.2
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetUserInfoResponse response) {
                UserInfo userInfo = response.data;
                if (userInfo != null) {
                    Log.i("UserInfo：", userInfo.toString());
                    UserInfo userInfo2 = UserInfoUtil.getUserInfo();
                    userInfo2.setVip(userInfo.isVip());
                    userInfo2.setVipStartTime(userInfo.getVipStartTime());
                    userInfo2.setVipEndTime(userInfo.getVipEndTime());
                    UserInfoUtil.saveUserInfo(userInfo2);
                    EventBus.getDefault().post(new UserInfoChangeEvent(userInfo2));
                    if (PollUtils.this.listener != null) {
                        PollUtils.this.listener.paySuccess();
                    }
                }
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int code, String msg) {
                if (code == 20000 || code == 20001 || code == 20005) {
                    Log.i(PollUtils.TAG, "onResponseFailure logout");
                    UserInfoUtil.logout();
                    EventBus.getDefault().post(new LoginOrLogoutEvent(false));
                }
            }
        });
    }

    public void clearHandler() {
        HandlerThread handlerThread = pollThread;
        if (handlerThread != null && handlerThread.isAlive()) {
            Log.i(TAG, "停止上次轮询线程");
            pollThread.quitSafely();
        }
        if (this.payHandler != null) {
            Log.i(TAG, "移除上次Callbacks");
            this.payHandler.removeCallbacks(this.statusRunnable);
            this.payHandler.removeCallbacks(this.timeRunnable);
            this.payHandler.removeCallbacksAndMessages(null);
            this.payHandler = null;
        }
    }

    public void setMemberPayPollListener(MemberPayPollListener listener) {
        this.listener = listener;
    }

    private static class PollStatusRunnable implements Runnable {
        private String orderNo;
        private WeakReference<PollUtils> pollUtilsRef;

        public PollStatusRunnable(PollUtils pollUtils, String orderNo) {
            this.pollUtilsRef = new WeakReference<>(pollUtils);
            this.orderNo = orderNo;
        }

        @Override // java.lang.Runnable
        public void run() {
            PollUtils pollUtils = this.pollUtilsRef.get();
            if (pollUtils != null) {
                pollUtils.pollStatus(this.orderNo);
                pollUtils.payHandler.postDelayed(this, 3000L);
            }
        }
    }

    private static class PollTimeRunnable implements Runnable {
        private long expireTime;
        private WeakReference<PollUtils> pollUtilsRef;

        public PollTimeRunnable(PollUtils pollUtils, long expireTime) {
            this.pollUtilsRef = new WeakReference<>(pollUtils);
            this.expireTime = expireTime;
        }

        @Override // java.lang.Runnable
        public void run() {
            PollUtils pollUtils = this.pollUtilsRef.get();
            if (pollUtils != null) {
                try {
                    pollUtils.pollTime(this.expireTime);
                } catch (Exception e) {
                    Log.e(PollUtils.TAG, e.getMessage());
                }
            }
        }
    }
}
