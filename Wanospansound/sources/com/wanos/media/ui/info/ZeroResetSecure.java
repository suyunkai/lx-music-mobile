package com.wanos.media.ui.info;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import com.wanos.media.util.ZeroAudioBallTools;
import com.wanos.media.util.ZeroLogcatTools;

/* JADX INFO: loaded from: classes3.dex */
public class ZeroResetSecure {
    private static ZeroResetSecure INSTANCE = null;
    private static final String TAG = "ZeroResetSecure";
    private IResetAction iResetAction;
    private LifecycleOwner mLifecycleOwner;
    private int mResetState = -1;
    private final Handler mMainHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.wanos.media.ui.info.ZeroResetSecure.1
        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            Lifecycle.State state = ZeroResetSecure.this.mLifecycleOwner.getLifecycle().getState();
            if (state != Lifecycle.State.RESUMED) {
                ZeroLogcatTools.e(ZeroResetSecure.TAG, "handleMessage: Fragment状态 = " + state);
                return false;
            }
            switch (message.what) {
                case 101:
                    ZeroLogcatTools.i(ZeroResetSecure.TAG, "handleMessage: 执行删除任务");
                    ZeroResetSecure.this.mResetState = 2;
                    ZeroResetSecure.this.iResetAction.onDeleteBall();
                    return false;
                case 102:
                    ZeroLogcatTools.i(ZeroResetSecure.TAG, "handleMessage: 执行添加任务");
                    ZeroResetSecure.this.mResetState = 3;
                    ZeroResetSecure.this.iResetAction.onInsertBall();
                    return false;
                case 103:
                    ZeroLogcatTools.i(ZeroResetSecure.TAG, "handleMessage: 执行完成");
                    ZeroResetSecure.this.mResetState = -1;
                    return false;
                default:
                    return false;
            }
        }
    });

    public interface IResetAction {
        void onDeleteBall();

        void onInsertBall();
    }

    public static ZeroResetSecure getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ZeroResetSecure();
        }
        return INSTANCE;
    }

    public static void onDestroy() {
        INSTANCE = null;
    }

    private ZeroResetSecure() {
    }

    public void onLaunch(LifecycleOwner lifecycleOwner, int i, IResetAction iResetAction) {
        if (this.mResetState != -1) {
            ZeroLogcatTools.e(TAG, "onLaunch: 任务未完成，跳出");
            return;
        }
        if (ZeroAudioBallTools.getInstance().getSceneSoundIds().size() != i) {
            ZeroLogcatTools.e(TAG, "onLaunch: 音源未加载结束，无法再次重置");
            return;
        }
        this.iResetAction = iResetAction;
        this.mLifecycleOwner = lifecycleOwner;
        this.mResetState = 1;
        this.mMainHandler.sendEmptyMessage(101);
        this.mMainHandler.sendEmptyMessageDelayed(102, 700L);
        this.mMainHandler.sendEmptyMessageDelayed(103, 1400L);
    }

    public int getResetState() {
        return this.mResetState;
    }
}
