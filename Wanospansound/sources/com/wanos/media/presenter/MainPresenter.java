package com.wanos.media.presenter;

import android.content.Context;
import android.util.Log;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.WanosCommunication.response.GetAdDialogInfoResponse;
import com.wanos.WanosCommunication.response.GetGiveVipMemberResponse;
import com.wanos.WanosCommunication.response.GetUserInfoResponse;
import com.wanos.commonlibrary.bean.UserInfo;
import com.wanos.commonlibrary.event.LoginOrLogoutEvent;
import com.wanos.commonlibrary.event.UserInfoChangeEvent;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.media.R;
import com.wanos.media.view.MainView;
import org.greenrobot.eventbus.EventBus;
import retrofit2.Call;

/* JADX INFO: loaded from: classes3.dex */
public class MainPresenter extends BasePresenter<MainView> {
    private static final String TAG = "wanos:[MainPresenter]";

    public MainPresenter(MainView view) {
        this.mView = view;
    }

    public void getUserInfo() {
        WanOSRetrofitUtil.getUserInfo(new ResponseCallBack<GetUserInfoResponse>(null) { // from class: com.wanos.media.presenter.MainPresenter.1
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
                }
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int code, String msg) {
                if (code == 20000 || code == 20001 || code == 20005) {
                    Log.e(MainPresenter.TAG, "error logout");
                    UserInfoUtil.logout();
                    EventBus.getDefault().post(new LoginOrLogoutEvent(false));
                }
            }
        });
    }

    public Call<GetAdDialogInfoResponse> getAdDialogInfo(final int type, boolean isRestart) {
        Context context = null;
        if (isViewAttached()) {
            return WanOSRetrofitUtil.getAdDialogInfo(type, isRestart, new ResponseCallBack<GetAdDialogInfoResponse>(context) { // from class: com.wanos.media.presenter.MainPresenter.2
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(GetAdDialogInfoResponse response) {
                    if (response.data != null) {
                        ((MainView) MainPresenter.this.mView).getAdDialogSuccess(response.data, type);
                    }
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int code, String msg) {
                    Log.e("异常结果：", code + " || " + msg);
                }
            });
        }
        return null;
    }

    public void giveVipMember(int activityId) {
        if (isViewAttached()) {
            WanOSRetrofitUtil.giveVipMember(activityId, new ResponseCallBack<GetGiveVipMemberResponse>(null) { // from class: com.wanos.media.presenter.MainPresenter.3
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(GetGiveVipMemberResponse response) {
                    MainPresenter.this.getUserInfo();
                    ToastUtil.showMsg(R.string.member_card_get_success);
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int code, String msg) {
                    ToastUtil.showMsg(msg);
                }
            });
        }
    }
}
