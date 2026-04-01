package com.wanos.media.presenter;

import android.content.Context;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.WanosCommunication.response.GetMusicListResponse;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import com.wanos.media.view.ResultMusicView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ResultMusicPresenter extends BasePresenter<ResultMusicView> {
    private final Context context;
    private List<MusicInfoBean> musicList = new ArrayList();

    public ResultMusicPresenter(Context context) {
        this.context = context;
    }

    @Override // com.wanos.media.presenter.BasePresenter
    public void attachView(ResultMusicView baseView) {
        super.attachView(baseView);
    }

    public void loadMore(boolean isRefresh, String keyword) {
        this.mPageIndex++;
        getResultMusic(isRefresh, keyword);
    }

    public void getResultMusic(final boolean isRefresh, String keyword) {
        if (isViewAttached()) {
            if (isRefresh) {
                ((ResultMusicView) this.mView).showLoading();
            }
            WanOSRetrofitUtil.GetSearchMusicList(keyword, this.mPageIndex + "", this.mPageSize + "", new ResponseCallBack<GetMusicListResponse>(this.context) { // from class: com.wanos.media.presenter.ResultMusicPresenter.1
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(GetMusicListResponse response) {
                    if (response.data != null) {
                        ResultMusicPresenter.this.musicList = response.data.getMusicList();
                        ResultMusicPresenter.this.getView().updateView(isRefresh, ResultMusicPresenter.this.musicList);
                    }
                    if (isRefresh) {
                        ((ResultMusicView) ResultMusicPresenter.this.mView).hideLoading();
                    }
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int code, String msg) {
                    if (isRefresh) {
                        ((ResultMusicView) ResultMusicPresenter.this.mView).hideLoading();
                    }
                    ((ResultMusicView) ResultMusicPresenter.this.mView).showFailOrError(msg);
                }
            });
        }
    }
}
