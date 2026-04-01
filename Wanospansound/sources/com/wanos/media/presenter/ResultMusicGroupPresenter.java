package com.wanos.media.presenter;

import android.content.Context;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.WanosCommunication.bean.MusicGroupInfo;
import com.wanos.WanosCommunication.response.GetMusicGroupListResponse;
import com.wanos.media.view.ResultMusicGroupView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ResultMusicGroupPresenter extends BasePresenter<ResultMusicGroupView> {
    private final Context context;
    private List<MusicGroupInfo> musicGroupList = new ArrayList();

    public ResultMusicGroupPresenter(Context context) {
        this.context = context;
    }

    @Override // com.wanos.media.presenter.BasePresenter
    public void attachView(ResultMusicGroupView baseView) {
        super.attachView(baseView);
    }

    public void loadMore(boolean isRefresh, String keyword) {
        this.mPageIndex++;
        getResultMusicGroupList(isRefresh, keyword);
    }

    public void getResultMusicGroupList(final boolean isRefresh, String keyword) {
        if (isViewAttached()) {
            if (isRefresh) {
                ((ResultMusicGroupView) this.mView).showLoading();
            }
            WanOSRetrofitUtil.GetSearchMusicGroupList(keyword, this.mPageIndex + "", this.mPageSize + "", new ResponseCallBack<GetMusicGroupListResponse>(this.context) { // from class: com.wanos.media.presenter.ResultMusicGroupPresenter.1
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(GetMusicGroupListResponse response) {
                    if (response.data != null) {
                        ResultMusicGroupPresenter.this.musicGroupList = response.data.getMusicGroupList();
                        ResultMusicGroupPresenter.this.getView().updataView(isRefresh, ResultMusicGroupPresenter.this.musicGroupList);
                    }
                    if (isRefresh) {
                        ((ResultMusicGroupView) ResultMusicGroupPresenter.this.mView).hideLoading();
                    }
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int code, String msg) {
                    if (isRefresh) {
                        ((ResultMusicGroupView) ResultMusicGroupPresenter.this.mView).hideLoading();
                    }
                    ((ResultMusicGroupView) ResultMusicGroupPresenter.this.mView).showFailOrError(code, msg);
                }
            });
        }
    }
}
