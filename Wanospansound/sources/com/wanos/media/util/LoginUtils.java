package com.wanos.media.util;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.blankj.utilcode.util.ThreadUtils;
import com.wanos.commonlibrary.event.LoginOrLogoutEvent;
import com.wanos.commonlibrary.event.UserInfoChangeEvent;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.media.ComResCenter;
import com.wanos.media.MainServer;
import com.wanos.media.util.LoginUtils;
import java.util.ArrayList;
import java.util.function.Consumer;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes3.dex */
public class LoginUtils {
    public static volatile LoginUtils INSTANCE = null;
    private static final String TAG = "LoginUtils";
    private final MutableLiveData<LoginOrLogoutEvent> _LoginState;
    private final MutableLiveData<UserInfoChangeEvent> _UserInfoChange;
    public final LiveData<LoginOrLogoutEvent> onLoginState;
    public final LiveData<UserInfoChangeEvent> onUserInfoChange;
    private final ArrayList<IUserStateCallback> userStateCallbacks = new ArrayList<>();

    public interface IUserStateCallback {
        void onLoginStateChange(boolean z);

        void onUserInfoChange(UserInfoChangeEvent userInfoChangeEvent);
    }

    public static LoginUtils getInstance() {
        if (INSTANCE == null) {
            synchronized (LoginUtils.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LoginUtils();
                }
            }
        }
        return INSTANCE;
    }

    private LoginUtils() {
        EventBus.getDefault().register(this);
        MutableLiveData<LoginOrLogoutEvent> mutableLiveData = new MutableLiveData<>(new LoginOrLogoutEvent(UserInfoUtil.isLogin()));
        this._LoginState = mutableLiveData;
        this.onLoginState = mutableLiveData;
        MutableLiveData<UserInfoChangeEvent> mutableLiveData2 = new MutableLiveData<>(new UserInfoChangeEvent(UserInfoUtil.getUserInfo()));
        this._UserInfoChange = mutableLiveData2;
        this.onUserInfoChange = mutableLiveData2;
    }

    public void showLoginDialog() {
        if (isLogin()) {
            return;
        }
        MainServer mainServer = ComResCenter.getInstance().mainServer;
        if (mainServer == null) {
            ZeroLogcatTools.i(TAG, "登录模块==> MainServer异常，请检测ComResCenter");
        } else {
            mainServer.showLoginDialog();
        }
    }

    public void showOpenVipDialog() {
        if (isVip()) {
            return;
        }
        MainServer mainServer = ComResCenter.getInstance().mainServer;
        if (mainServer == null) {
            ZeroLogcatTools.i(TAG, "登录模块==> MainServer异常，请检测ComResCenter");
        } else {
            mainServer.showVipDialog();
        }
    }

    public boolean isLogin() {
        return UserInfoUtil.isLogin();
    }

    public boolean isVip() {
        if (UserInfoUtil.isLogin()) {
            return UserInfoUtil.isVipAndUnexpired();
        }
        return false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUserStateChange(final LoginOrLogoutEvent loginOrLogoutEvent) {
        if (loginOrLogoutEvent == null || loginOrLogoutEvent.isLogin() == this._LoginState.getValue().isLogin()) {
            return;
        }
        ZeroLogcatTools.i(TAG, "登录模块==> 用户登录状态发生变化" + loginOrLogoutEvent);
        ThreadUtils.runOnUiThread(new Runnable() { // from class: com.wanos.media.util.LoginUtils$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m566lambda$onUserStateChange$1$comwanosmediautilLoginUtils(loginOrLogoutEvent);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$onUserStateChange$1$com-wanos-media-util-LoginUtils, reason: not valid java name */
    /* synthetic */ void m566lambda$onUserStateChange$1$comwanosmediautilLoginUtils(final LoginOrLogoutEvent loginOrLogoutEvent) {
        this.userStateCallbacks.forEach(new Consumer() { // from class: com.wanos.media.util.LoginUtils$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((LoginUtils.IUserStateCallback) obj).onLoginStateChange(loginOrLogoutEvent.isLogin());
            }
        });
        this._LoginState.setValue(loginOrLogoutEvent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUserInfoChange(final UserInfoChangeEvent userInfoChangeEvent) {
        ZeroLogcatTools.i(TAG, "登录模块==> 用户信息发生变化");
        ThreadUtils.runOnUiThread(new Runnable() { // from class: com.wanos.media.util.LoginUtils$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m565lambda$onUserInfoChange$3$comwanosmediautilLoginUtils(userInfoChangeEvent);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$onUserInfoChange$3$com-wanos-media-util-LoginUtils, reason: not valid java name */
    /* synthetic */ void m565lambda$onUserInfoChange$3$comwanosmediautilLoginUtils(final UserInfoChangeEvent userInfoChangeEvent) {
        this.userStateCallbacks.forEach(new Consumer() { // from class: com.wanos.media.util.LoginUtils$$ExternalSyntheticLambda3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((LoginUtils.IUserStateCallback) obj).onUserInfoChange(userInfoChangeEvent);
            }
        });
        this._UserInfoChange.setValue(userInfoChangeEvent);
    }

    public void addUserStateCallback(IUserStateCallback iUserStateCallback) {
        if (iUserStateCallback == null || this.userStateCallbacks.contains(iUserStateCallback)) {
            return;
        }
        this.userStateCallbacks.add(iUserStateCallback);
    }

    public void removeUserStateCallback(IUserStateCallback iUserStateCallback) {
        if (iUserStateCallback == null) {
            return;
        }
        this.userStateCallbacks.remove(iUserStateCallback);
    }
}
