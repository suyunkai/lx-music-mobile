package com.wanos.media.presenter;

import android.content.Context;
import android.util.Log;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.WanosCommunication.bean.GoodsInfo;
import com.wanos.WanosCommunication.response.CreateVipOrderResponse;
import com.wanos.WanosCommunication.response.GetAdDialogInfoResponse;
import com.wanos.WanosCommunication.response.GetGiveVipMemberResponse;
import com.wanos.WanosCommunication.response.GetOrderInfoByOrderNoResponse;
import com.wanos.WanosCommunication.response.GetUserInfoResponse;
import com.wanos.WanosCommunication.response.GetVipGoodsListResponse;
import com.wanos.WanosCommunication.response.GetVipPriceStatistic;
import com.wanos.WanosCommunication.response.PayVipMemberResponse;
import com.wanos.commonlibrary.bean.UserInfo;
import com.wanos.commonlibrary.event.LoginOrLogoutEvent;
import com.wanos.commonlibrary.event.UserInfoChangeEvent;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.media.R;
import com.wanos.media.view.MemberPayView;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes3.dex */
public class MemberPayPresenter extends BasePresenter<MemberPayView> {
    private final Context context;
    private List<GoodsInfo> goodsList = new ArrayList();

    public MemberPayPresenter(Context context) {
        this.context = context;
    }

    @Override // com.wanos.media.presenter.BasePresenter
    public boolean isViewAttached() {
        return super.isViewAttached();
    }

    public void requestGetMemberGoods() {
        if (isViewAttached()) {
            WanOSRetrofitUtil.getVipGoodsList(new ResponseCallBack<GetVipGoodsListResponse>(this.context) { // from class: com.wanos.media.presenter.MemberPayPresenter.1
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(GetVipGoodsListResponse response) {
                    if (response.data != null) {
                        MemberPayPresenter.this.goodsList = response.data.getGoodsInfo();
                        MemberPayPresenter.this.getView().getVipMemberGoodsData(MemberPayPresenter.this.goodsList);
                    }
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int code, String msg) {
                    ((MemberPayView) MemberPayPresenter.this.mView).showFailOrError(msg);
                }
            });
        }
    }

    public void createVipMember(String goodsNo, float goodsPrice, String goodsName, float originPrice) {
        if (isViewAttached()) {
            WanOSRetrofitUtil.createVipOrder(goodsNo, goodsPrice, goodsName, originPrice, new ResponseCallBack<CreateVipOrderResponse>(this.context) { // from class: com.wanos.media.presenter.MemberPayPresenter.2
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(CreateVipOrderResponse response) {
                    if (response.data != null) {
                        MemberPayPresenter.this.getView().createVipMemberSuccess(response.data.getOrderNo());
                    }
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int code, String msg) {
                    ((MemberPayView) MemberPayPresenter.this.mView).showFailOrError(msg);
                }
            });
        }
    }

    public void saveVipPrice(int vipPriceId, int source) {
        if (isViewAttached()) {
            WanOSRetrofitUtil.saveVipPrice(vipPriceId, "", source, new ResponseCallBack<GetVipPriceStatistic>(null) { // from class: com.wanos.media.presenter.MemberPayPresenter.3
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int code, String msg) {
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(GetVipPriceStatistic response) {
                }
            });
        }
    }

    public void payVipMember(String orderNo) {
        if (isViewAttached()) {
            WanOSRetrofitUtil.payVipMember(orderNo, new ResponseCallBack<PayVipMemberResponse>(this.context) { // from class: com.wanos.media.presenter.MemberPayPresenter.4
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(PayVipMemberResponse response) {
                    if (response.data != null) {
                        MemberPayPresenter.this.getView().getPayVipMember(response.data);
                    }
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int code, String msg) {
                    ((MemberPayView) MemberPayPresenter.this.mView).showFailOrError(msg);
                }
            });
        }
    }

    public void getOrderDetail(String orderNo) {
        WanOSRetrofitUtil.getOrderDetail(orderNo, new ResponseCallBack<GetOrderInfoByOrderNoResponse>(this.context) { // from class: com.wanos.media.presenter.MemberPayPresenter.5
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int code, String msg) {
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetOrderInfoByOrderNoResponse response) {
                if (response.data != null) {
                    MemberPayPresenter.this.getView().getOrderDetail(response.data.getOrderInfo());
                }
            }
        });
    }

    public void getAdDialogInfo(int type, boolean isRestart) {
        if (isViewAttached()) {
            WanOSRetrofitUtil.getAdDialogInfo(type, isRestart, new ResponseCallBack<GetAdDialogInfoResponse>(null) { // from class: com.wanos.media.presenter.MemberPayPresenter.6
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(GetAdDialogInfoResponse response) {
                    if (response.data != null) {
                        MemberPayPresenter.this.getView().getAdDialogSuccess(response.data);
                    }
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int code, String msg) {
                    Log.e("异常结果：", code + " || " + msg);
                }
            });
        }
    }

    public void giveVipMember(int activityId) {
        if (isViewAttached()) {
            WanOSRetrofitUtil.giveVipMember(activityId, new ResponseCallBack<GetGiveVipMemberResponse>(null) { // from class: com.wanos.media.presenter.MemberPayPresenter.7
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(GetGiveVipMemberResponse response) {
                    MemberPayPresenter.this.getUserInfo();
                    ToastUtil.showMsg(R.string.member_card_get_success);
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int code, String msg) {
                    ToastUtil.showMsg(msg);
                }
            });
        }
    }

    public void getUserInfo() {
        WanOSRetrofitUtil.getUserInfo(new ResponseCallBack<GetUserInfoResponse>(null) { // from class: com.wanos.media.presenter.MemberPayPresenter.8
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
                    UserInfoUtil.logout();
                    EventBus.getDefault().post(new LoginOrLogoutEvent(false));
                }
            }
        });
    }
}
