package com.wanos.media.presenter;

import android.content.Context;
import android.util.Log;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.WanosCommunication.response.RecommendWordsResponse;
import com.wanos.commonlibrary.utils.SharedPreferUtils;
import com.wanos.media.view.SearchLabelView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class SearchLabelPresenter extends BasePresenter<SearchLabelView> {
    private final Context context;
    private List<String> recordList = new ArrayList();
    private List<String> hotList = new ArrayList();

    public SearchLabelPresenter(Context context) {
        this.context = context;
    }

    @Override // com.wanos.media.presenter.BasePresenter
    public void attachView(SearchLabelView baseView) {
        super.attachView(baseView);
    }

    public void getRecordList() {
        if (isViewAttached()) {
            this.recordList = SharedPreferUtils.getSearchRecord();
            getView().getRecordListData(this.recordList);
        }
    }

    public void getHotRecommendWords() {
        if (isViewAttached()) {
            WanOSRetrofitUtil.GetHotRecommendWords(new ResponseCallBack<RecommendWordsResponse>(this.context) { // from class: com.wanos.media.presenter.SearchLabelPresenter.1
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(RecommendWordsResponse response) {
                    if (response.data.getRecommendWords().length != 0) {
                        SearchLabelPresenter.this.hotList = Arrays.asList(response.data.getRecommendWords());
                        SearchLabelPresenter.this.getView().getRecommendWordsData(SearchLabelPresenter.this.hotList);
                    }
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int code, String msg) {
                    Log.e("异常结果：", code + " || " + msg);
                }
            });
        }
    }

    public void deleteRecordList() {
        if (isViewAttached()) {
            SharedPreferUtils.clearSearchRecord();
        }
    }
}
