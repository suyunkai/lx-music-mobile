package com.wanos.media.ui.search.viewmodel;

import android.app.Application;
import android.util.Log;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.WanosCommunication.bean.AudioBookAlbumInfoBean;
import com.wanos.WanosCommunication.bean.MusicGroupInfo;
import com.wanos.WanosCommunication.response.GetAudioBookAlbumResponse;
import com.wanos.WanosCommunication.response.GetMusicGroupListResponse;
import com.wanos.WanosCommunication.response.GetMusicListResponse;
import com.wanos.WanosCommunication.response.RecommendWordsResponse;
import com.wanos.WanosCommunication.service.Result;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import com.wanos.commonlibrary.utils.SharedPreferUtils;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ResultViewModel extends AndroidViewModel {
    public MutableLiveData<Result<List<AudioBookAlbumInfoBean>>> audioBookList;
    public MutableLiveData<List<String>> hotList;
    public MutableLiveData<Boolean> isDialog;
    public MutableLiveData<Boolean> isShowSoftInput;
    public MutableLiveData<String> keyword;
    private int mPageIndex;
    public MutableLiveData<Result<List<MusicGroupInfo>>> musicGroupList;
    public MutableLiveData<Result<List<MusicInfoBean>>> musicList;
    public MutableLiveData<Integer> pageNum;
    public MutableLiveData<Integer> pageNum1;
    public MutableLiveData<List<String>> recordList;

    public ResultViewModel(Application application) {
        super(application);
        this.pageNum = new MutableLiveData<>(0);
        this.keyword = new MutableLiveData<>("");
        this.pageNum1 = new MutableLiveData<>(0);
        this.isDialog = new MutableLiveData<>(false);
        this.isShowSoftInput = new MutableLiveData<>(true);
        this.recordList = new MutableLiveData<>(null);
        this.hotList = new MutableLiveData<>(null);
        this.musicList = new MutableLiveData<>(null);
        this.musicGroupList = new MutableLiveData<>(null);
        this.audioBookList = new MutableLiveData<>(null);
        this.mPageIndex = 1;
    }

    public void requestData() {
        if (this.pageNum1.getValue().intValue() == 0) {
            searchMusic(true);
        } else if (this.pageNum1.getValue().intValue() == 1) {
            searchMusicGroup(true);
        } else {
            searchAudioBook(true);
        }
    }

    public void searchMusic(boolean isRefresh) {
        if (isRefresh) {
            this.mPageIndex = 1;
        } else {
            this.mPageIndex++;
        }
        getResultMusic(this.keyword.getValue(), isRefresh);
    }

    public void searchMusicGroup(boolean isRefresh) {
        if (isRefresh) {
            this.mPageIndex = 1;
        } else {
            this.mPageIndex++;
        }
        getResultMusicGroup(this.keyword.getValue(), isRefresh);
    }

    public void searchAudioBook(boolean isRefresh) {
        if (isRefresh) {
            this.mPageIndex = 1;
        } else {
            this.mPageIndex++;
        }
        getResultAudioBook(this.keyword.getValue(), isRefresh);
    }

    public void getResultMusic(String keyword, final boolean isRefresh) {
        if (this.musicList.getValue() == null) {
            this.musicList.setValue(Result.loading());
        }
        WanOSRetrofitUtil.GetSearchMusicList(keyword, this.mPageIndex + "", "100", new ResponseCallBack<GetMusicListResponse>(null) { // from class: com.wanos.media.ui.search.viewmodel.ResultViewModel.1
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetMusicListResponse response) {
                if (response.data != null) {
                    List<MusicInfoBean> musicList = response.data.getMusicList();
                    if (isRefresh) {
                        ResultViewModel.this.musicList.setValue(Result.success(musicList));
                    } else if (ResultViewModel.this.musicList.getValue() != null) {
                        ResultViewModel.this.musicList.getValue().data.addAll(musicList);
                        ResultViewModel.this.musicList.setValue(ResultViewModel.this.musicList.getValue());
                    }
                }
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int code, String msg) {
                ResultViewModel.this.musicList.setValue(Result.error(msg));
            }
        });
    }

    public void getResultMusicGroup(String keyword, final boolean isRefresh) {
        if (this.musicGroupList.getValue() == null) {
            this.musicGroupList.setValue(Result.loading());
        }
        WanOSRetrofitUtil.GetSearchMusicGroupList(keyword, this.mPageIndex + "", "100", new ResponseCallBack<GetMusicGroupListResponse>(null) { // from class: com.wanos.media.ui.search.viewmodel.ResultViewModel.2
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetMusicGroupListResponse response) {
                if (response.data != null) {
                    List<MusicGroupInfo> musicGroupList = response.data.getMusicGroupList();
                    if (isRefresh) {
                        ResultViewModel.this.musicGroupList.setValue(Result.success(musicGroupList));
                    } else if (ResultViewModel.this.musicGroupList.getValue() != null) {
                        ResultViewModel.this.musicGroupList.getValue().data.addAll(musicGroupList);
                        ResultViewModel.this.musicGroupList.setValue(ResultViewModel.this.musicGroupList.getValue());
                    }
                }
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int code, String msg) {
                ResultViewModel.this.musicGroupList.setValue(Result.error(msg));
            }
        });
    }

    public void getResultAudioBook(String keyword, final boolean isRefresh) {
        if (this.audioBookList.getValue() == null) {
            this.audioBookList.setValue(Result.loading());
        }
        WanOSRetrofitUtil.GetSearchAudioBookAlbumList(keyword, this.mPageIndex + "", "100", new ResponseCallBack<GetAudioBookAlbumResponse>(null) { // from class: com.wanos.media.ui.search.viewmodel.ResultViewModel.3
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetAudioBookAlbumResponse response) {
                if (response.data != null) {
                    List<AudioBookAlbumInfoBean> audioBookAlbumList = response.data.getAudioBookAlbumList();
                    if (isRefresh) {
                        ResultViewModel.this.audioBookList.setValue(Result.success(audioBookAlbumList));
                    } else if (ResultViewModel.this.audioBookList.getValue() != null) {
                        ResultViewModel.this.audioBookList.getValue().data.addAll(audioBookAlbumList);
                        ResultViewModel.this.audioBookList.setValue(ResultViewModel.this.audioBookList.getValue());
                    }
                }
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int code, String msg) {
                ResultViewModel.this.audioBookList.setValue(Result.error(msg));
            }
        });
    }

    public void getRecordList() {
        this.recordList.setValue(SharedPreferUtils.getSearchRecord());
    }

    public void getHotRecommendWords() {
        WanOSRetrofitUtil.GetHotRecommendWords(new ResponseCallBack<RecommendWordsResponse>(null) { // from class: com.wanos.media.ui.search.viewmodel.ResultViewModel.4
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(RecommendWordsResponse response) {
                if (response.data.getRecommendWords().length != 0) {
                    ResultViewModel.this.hotList.setValue(Arrays.asList(response.data.getRecommendWords()));
                }
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int code, String msg) {
                Log.i("异常结果：", code + " || " + msg);
                ResultViewModel.this.hotList.setValue(null);
            }
        });
    }

    public void deleteRecordList() {
        SharedPreferUtils.clearSearchRecord();
    }

    public boolean isListNull(int index) {
        return index == 0 ? this.musicList.getValue() == null : index == 1 ? this.musicGroupList.getValue() == null : this.audioBookList.getValue() == null;
    }

    public void resetOtherList(int index) {
        if (index == 0) {
            this.musicGroupList.setValue(null);
            this.audioBookList.setValue(null);
        } else if (index == 1) {
            this.musicList.setValue(null);
            this.audioBookList.setValue(null);
        } else {
            this.musicList.setValue(null);
            this.musicGroupList.setValue(null);
        }
    }
}
