package com.wanos.media.presenter;

import androidx.lifecycle.Observer;
import com.wanos.commonlibrary.event.LoginOrLogoutEvent;
import com.wanos.commonlibrary.event.UserInfoChangeEvent;
import com.wanos.media.util.LoginUtils;
import com.wanos.media.util.ZeroLogcatTools;
import com.wanos.media.view.SoundMenuView;

/* JADX INFO: loaded from: classes3.dex */
public class SoundMenuPresenter extends BasePresenter<SoundMenuView> {
    private static final String TAG = "SoundMenuFragment";
    private final Observer<LoginOrLogoutEvent> mLoginChangeCallback;
    private int mPosition = -1;
    private final Observer<UserInfoChangeEvent> mUserChangeCallback;

    public SoundMenuPresenter(SoundMenuView soundMenuView) {
        Observer<LoginOrLogoutEvent> observer = new Observer<LoginOrLogoutEvent>() { // from class: com.wanos.media.presenter.SoundMenuPresenter.1
            @Override // androidx.lifecycle.Observer
            public void onChanged(LoginOrLogoutEvent loginOrLogoutEvent) {
                if (SoundMenuPresenter.this.mPosition == -1 || loginOrLogoutEvent == null || !loginOrLogoutEvent.isLogin()) {
                    return;
                }
                ZeroLogcatTools.i(SoundMenuPresenter.TAG, "登录成功,进行下一步验证, mPosition = " + SoundMenuPresenter.this.mPosition);
                if (LoginUtils.getInstance().isVip()) {
                    ((SoundMenuView) SoundMenuPresenter.this.mView).onLaunchPlayTask(SoundMenuPresenter.this.mPosition);
                    SoundMenuPresenter.this.mPosition = -1;
                }
            }
        };
        this.mLoginChangeCallback = observer;
        Observer<UserInfoChangeEvent> observer2 = new Observer<UserInfoChangeEvent>() { // from class: com.wanos.media.presenter.SoundMenuPresenter.2
            @Override // androidx.lifecycle.Observer
            public void onChanged(UserInfoChangeEvent userInfoChangeEvent) {
                if (SoundMenuPresenter.this.mPosition == -1 || userInfoChangeEvent == null || !userInfoChangeEvent.getUserInfo().isVip()) {
                    return;
                }
                ZeroLogcatTools.i(SoundMenuPresenter.TAG, "用户状态发生变化：" + userInfoChangeEvent + ", mPosition = " + SoundMenuPresenter.this.mPosition);
                ((SoundMenuView) SoundMenuPresenter.this.mView).onLaunchPlayTask(SoundMenuPresenter.this.mPosition);
                SoundMenuPresenter.this.mPosition = -1;
            }
        };
        this.mUserChangeCallback = observer2;
        this.mView = soundMenuView;
        LoginUtils.getInstance().onLoginState.observeForever(observer);
        LoginUtils.getInstance().onUserInfoChange.observeForever(observer2);
    }

    public void setMediaOptions(int i, boolean z) {
        ZeroLogcatTools.d(TAG, "setMediaOptions: position=" + i + "|| isVipSound=" + z);
        this.mPosition = i;
        if (z && !LoginUtils.getInstance().isLogin()) {
            ZeroLogcatTools.d(TAG, "setMediaOptions: 未登录，去登录");
            LoginUtils.getInstance().showLoginDialog();
        } else if (z && !LoginUtils.getInstance().isVip()) {
            ZeroLogcatTools.d(TAG, "setMediaOptions: 非VIP，去开通VIP");
            LoginUtils.getInstance().showOpenVipDialog();
        } else {
            ((SoundMenuView) this.mView).onLaunchPlayTask(i);
            this.mPosition = -1;
        }
    }

    @Override // com.wanos.media.presenter.BasePresenter, com.wanos.media.presenter.IPresenter
    public void onDestroy() {
        super.onDestroy();
        LoginUtils.getInstance().onLoginState.removeObserver(this.mLoginChangeCallback);
        LoginUtils.getInstance().onUserInfoChange.removeObserver(this.mUserChangeCallback);
    }
}
