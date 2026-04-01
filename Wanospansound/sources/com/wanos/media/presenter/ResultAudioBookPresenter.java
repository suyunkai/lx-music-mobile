package com.wanos.media.presenter;

import android.content.Context;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.WanosCommunication.bean.AudioBookAlbumInfoBean;
import com.wanos.WanosCommunication.response.GetAudioBookAlbumResponse;
import com.wanos.media.view.ResultAudioBookView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ResultAudioBookPresenter extends BasePresenter<ResultAudioBookView> {
    private List<AudioBookAlbumInfoBean> audioBookList = new ArrayList();
    private final Context context;

    public ResultAudioBookPresenter(Context context) {
        this.context = context;
    }

    @Override // com.wanos.media.presenter.BasePresenter
    public void attachView(ResultAudioBookView baseView) {
        super.attachView(baseView);
    }

    public void loadMore(boolean isRefresh, String keyword) {
        this.mPageIndex++;
        getResultAudioBookList(isRefresh, keyword);
    }

    public void getResultAudioBookList(final boolean isRefresh, String keyword) {
        if (isViewAttached()) {
            if (isRefresh) {
                ((ResultAudioBookView) this.mView).showLoading();
            }
            WanOSRetrofitUtil.GetSearchAudioBookAlbumList(keyword, this.mPageIndex + "", this.mPageSize + "", new ResponseCallBack<GetAudioBookAlbumResponse>(this.context) { // from class: com.wanos.media.presenter.ResultAudioBookPresenter.1
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(GetAudioBookAlbumResponse response) {
                    if (response.data.getAudioBookAlbumList() != null) {
                        ResultAudioBookPresenter.this.audioBookList = response.data.getAudioBookAlbumList();
                        ResultAudioBookPresenter.this.getView().updataView(isRefresh, ResultAudioBookPresenter.this.audioBookList);
                    }
                    if (isRefresh) {
                        ((ResultAudioBookView) ResultAudioBookPresenter.this.mView).hideLoading();
                    }
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int code, String msg) {
                    if (isRefresh) {
                        ((ResultAudioBookView) ResultAudioBookPresenter.this.mView).hideLoading();
                    }
                    ((ResultAudioBookView) ResultAudioBookPresenter.this.mView).showFailOrError(code, msg);
                }
            });
        }
    }
}
