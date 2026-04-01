package com.wanos.media.presenter;

import android.text.TextUtils;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import com.blankj.utilcode.util.LogUtils;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.WanosCommunication.response.GetH5UrlResponse;
import com.wanos.WanosCommunication.response.GetQAListResponse;
import com.wanos.commonlibrary.utils.CommonUtils;
import com.wanos.commonlibrary.utils.HttpUtil;
import com.wanos.commonlibrary.utils.SystemAndServerTimeSynchUtil;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.media.util.RequestParameterUtils;
import com.wanos.media.util.SignUtils;
import com.wanos.media.view.QuestionView;
import java.util.HashMap;

/* JADX INFO: loaded from: classes3.dex */
public class QuestionPresenter extends BasePresenter<QuestionView> {
    private String feedBackUrl;

    public void loadMore(boolean isRefresh) {
        this.mPageIndex++;
        getQuestionListData(isRefresh);
    }

    public void requestData() {
        getQuestionListData(true);
        getFeedbackUrl();
    }

    public void getQuestionListData(final boolean isRefresh) {
        if (isViewAttached()) {
            if (isRefresh) {
                ((QuestionView) this.mView).showLoading();
            }
            WanOSRetrofitUtil.getQAList(this.mPageIndex + "", this.orderPageSize + "", new ResponseCallBack<GetQAListResponse>(null) { // from class: com.wanos.media.presenter.QuestionPresenter.1
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(GetQAListResponse response) {
                    if (response.data != null) {
                        QuestionPresenter.this.getView().updateQAList(isRefresh, response.data.getItems());
                    }
                    if (isRefresh) {
                        ((QuestionView) QuestionPresenter.this.mView).hideLoading();
                    }
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int code, String msg) {
                    if (isRefresh) {
                        ((QuestionView) QuestionPresenter.this.mView).hideLoading();
                    }
                    ((QuestionView) QuestionPresenter.this.mView).showFailOrError(msg);
                }
            });
        }
    }

    private void getFeedbackUrl() {
        WanOSRetrofitUtil.getFeedbackUrl("prod", new ResponseCallBack<GetH5UrlResponse>(null) { // from class: com.wanos.media.presenter.QuestionPresenter.2
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetH5UrlResponse response) {
                QuestionPresenter.this.feedBackUrl = response.data.getUrl();
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int code, String msg) {
                LogUtils.d("QA", msg);
            }
        });
    }

    public String getFormatedFeedBackUrl() {
        HashMap map = new HashMap();
        try {
            long sytemTrueTime = SystemAndServerTimeSynchUtil.getSytemTrueTime() / 1000;
            String deviceToken = RequestParameterUtils.getDeviceToken(sytemTrueTime);
            String sign = RequestParameterUtils.getSign(sytemTrueTime);
            map.put("deviceToken", deviceToken);
            map.put("sign", sign);
            map.put("encryptType", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
            map.put("clientIp", CommonUtils.getIPAddress());
            if (!TextUtils.isEmpty(UserInfoUtil.getToken())) {
                map.put("userToken", UserInfoUtil.getToken());
            }
            map.put("vin", SignUtils.getInstance().getEncryptedDeviceId());
            String strAppendQueryParamsManually = HttpUtil.appendQueryParamsManually(this.feedBackUrl, map);
            LogUtils.d("QA", strAppendQueryParamsManually);
            return strAppendQueryParamsManually;
        } catch (Exception e) {
            e.printStackTrace();
            return this.feedBackUrl;
        }
    }
}
