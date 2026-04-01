package com.wanos.media.presenter;

import android.util.Log;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.WanosCommunication.bean.BannerBean;
import com.wanos.WanosCommunication.bean.MediaMusicBannerListBean;
import com.wanos.WanosCommunication.bean.MediaMusicGorupListBean;
import com.wanos.WanosCommunication.bean.MediaMusicListBean;
import com.wanos.WanosCommunication.bean.ModuleCoverDetailsBean;
import com.wanos.WanosCommunication.bean.MusicGroupInfo;
import com.wanos.WanosCommunication.response.GetModuleCoverDetailsResponse;
import com.wanos.WanosCommunication.response.GetMusicBannerListResponse;
import com.wanos.WanosCommunication.response.GetMusicGroupListResponse;
import com.wanos.WanosCommunication.response.GetMusicListResponse;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import com.wanos.media.view.MusicHomeView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class MusicHomePresenter extends BasePresenter<MusicHomeView> {
    private static final int LOADING_STATUS = 1;
    private static final int LOAD_COMPLETE_STATUS = 2;
    private static final int LOAD_ERROR_STATUS = 3;
    private static final int NONE_LOAD_STATUS = 0;
    public static final String TAG = "wanos:[MusicHomePresenter]";
    private List<BannerBean> list;
    private final int[] requestStatus = {0, 0, 0, 0, 0, 0, 0};

    public MusicHomePresenter(MusicHomeView view) {
        this.mView = view;
    }

    public void requestData() {
        if (this.mView != 0) {
            ((MusicHomeView) this.mView).showLoading();
            requestMusicBannerList();
            requestFreeMusicGroupCover(2, "free");
            requestMyMusicCoverDetails(1, "my");
            requestLimitedFreeMusicList();
            requestMyMusicList();
            requestRecommedMusicGroupList();
            requestRakingMusicList();
        }
    }

    private void requestMusicBannerList() {
        this.requestStatus[0] = 1;
        this.list = new ArrayList();
        WanOSRetrofitUtil.getMusicBannerListData(new ResponseCallBack<GetMusicBannerListResponse>(null) { // from class: com.wanos.media.presenter.MusicHomePresenter.1
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetMusicBannerListResponse response) {
                MusicHomePresenter.this.requestStatus[0] = 2;
                MediaMusicBannerListBean mediaMusicBannerListBean = response.data;
                if (mediaMusicBannerListBean != null && MusicHomePresenter.this.mView != 0) {
                    if (mediaMusicBannerListBean.getOperateADList().size() == 0) {
                        BannerBean bannerBean = new BannerBean();
                        bannerBean.setId(0L);
                        bannerBean.setPath("");
                        bannerBean.setCoverImg("");
                        MusicHomePresenter.this.list.add(bannerBean);
                    } else {
                        MusicHomePresenter.this.list = mediaMusicBannerListBean.getOperateADList();
                    }
                    ((MusicHomeView) MusicHomePresenter.this.mView).updateBanner(MusicHomePresenter.this.list);
                }
                MusicHomePresenter.this.dealAllResponseHideLoading();
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int code, String msg) {
                Log.e(MusicHomePresenter.TAG, "request_error:" + msg);
                MusicHomePresenter.this.requestStatus[0] = 2;
                if (MusicHomePresenter.this.mView != 0) {
                    BannerBean bannerBean = new BannerBean();
                    bannerBean.setId(0L);
                    bannerBean.setPath("");
                    bannerBean.setCoverImg("");
                    MusicHomePresenter.this.list.add(bannerBean);
                    ((MusicHomeView) MusicHomePresenter.this.mView).updateBanner(MusicHomePresenter.this.list);
                }
                MusicHomePresenter.this.dealAllResponseHideLoading();
            }
        });
    }

    private void requestFreeMusicGroupCover(int coverType, String location) {
        this.requestStatus[1] = 1;
        WanOSRetrofitUtil.getModuleCoverDetails(coverType, location, new ResponseCallBack<GetModuleCoverDetailsResponse>(null) { // from class: com.wanos.media.presenter.MusicHomePresenter.2
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetModuleCoverDetailsResponse response) {
                MusicHomePresenter.this.requestStatus[1] = 2;
                if (response.data != null && MusicHomePresenter.this.mView != 0) {
                    ((MusicHomeView) MusicHomePresenter.this.mView).updateFreeGroupCover(response.data);
                }
                MusicHomePresenter.this.dealAllResponseHideLoading();
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int code, String msg) {
                MusicHomePresenter.this.requestStatus[1] = 2;
                if (MusicHomePresenter.this.mView != 0) {
                    ModuleCoverDetailsBean moduleCoverDetailsBean = new ModuleCoverDetailsBean();
                    moduleCoverDetailsBean.setCoverImage("");
                    ((MusicHomeView) MusicHomePresenter.this.mView).updateFreeGroupCover(moduleCoverDetailsBean);
                }
                MusicHomePresenter.this.dealAllResponseHideLoading();
            }
        });
    }

    private void requestMyMusicCoverDetails(int coverType, String location) {
        this.requestStatus[2] = 1;
        WanOSRetrofitUtil.getModuleCoverDetails(coverType, location, new ResponseCallBack<GetModuleCoverDetailsResponse>(null) { // from class: com.wanos.media.presenter.MusicHomePresenter.3
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetModuleCoverDetailsResponse response) {
                MusicHomePresenter.this.requestStatus[2] = 2;
                if (response.data != null && MusicHomePresenter.this.mView != 0) {
                    ((MusicHomeView) MusicHomePresenter.this.mView).updateMyMusicCover(response.data);
                }
                MusicHomePresenter.this.dealAllResponseHideLoading();
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int code, String msg) {
                MusicHomePresenter.this.requestStatus[2] = 2;
                if (MusicHomePresenter.this.mView != 0) {
                    ModuleCoverDetailsBean moduleCoverDetailsBean = new ModuleCoverDetailsBean();
                    moduleCoverDetailsBean.setTitle("");
                    moduleCoverDetailsBean.setCoverImage("");
                    moduleCoverDetailsBean.setContent("");
                    ((MusicHomeView) MusicHomePresenter.this.mView).updateMyMusicCover(moduleCoverDetailsBean);
                }
                MusicHomePresenter.this.dealAllResponseHideLoading();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dealAllResponseHideLoading() {
        boolean z = false;
        int i = 0;
        while (true) {
            int[] iArr = this.requestStatus;
            if (i >= iArr.length) {
                z = true;
                break;
            } else if (iArr[i] != 2) {
                break;
            } else {
                i++;
            }
        }
        if (!z || this.mView == 0) {
            return;
        }
        ((MusicHomeView) this.mView).hideLoading();
    }

    private void requestLimitedFreeMusicList() {
        this.requestStatus[3] = 1;
        WanOSRetrofitUtil.getLimitedFreeMusicList(1, 1, false, new ResponseCallBack<GetMusicListResponse>(null) { // from class: com.wanos.media.presenter.MusicHomePresenter.4
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetMusicListResponse response) {
                MusicHomePresenter.this.requestStatus[3] = 2;
                MediaMusicListBean mediaMusicListBean = response.data;
                if (mediaMusicListBean != null && mediaMusicListBean.getMusicList() != null && MusicHomePresenter.this.mView != 0) {
                    ((MusicHomeView) MusicHomePresenter.this.mView).updateHotMusicView(mediaMusicListBean.getMusicList().size() > 0 ? mediaMusicListBean.getMusicList().get(0) : null);
                }
                MusicHomePresenter.this.dealAllResponseHideLoading();
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int code, String msg) {
                MusicHomePresenter.this.requestStatus[3] = 2;
                if (MusicHomePresenter.this.mView != 0) {
                    ((MusicHomeView) MusicHomePresenter.this.mView).updateHotMusicView(null);
                }
                MusicHomePresenter.this.dealAllResponseHideLoading();
            }
        });
    }

    public void requestMyMusicList() {
        this.requestStatus[4] = 1;
        WanOSRetrofitUtil.getCollectMusicList(1, 1, new ResponseCallBack<GetMusicListResponse>(null) { // from class: com.wanos.media.presenter.MusicHomePresenter.5
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetMusicListResponse response) {
                MusicHomePresenter.this.requestStatus[4] = 2;
                MediaMusicListBean mediaMusicListBean = response.data;
                if (mediaMusicListBean != null && mediaMusicListBean.getMusicList() != null && mediaMusicListBean.getMusicList().size() > 0 && MusicHomePresenter.this.mView != 0) {
                    ((MusicHomeView) MusicHomePresenter.this.mView).updateMyMusicView(mediaMusicListBean.getMusicList().get(0));
                }
                MusicHomePresenter.this.dealAllResponseHideLoading();
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int code, String msg) {
                MusicHomePresenter.this.requestStatus[4] = 2;
                MusicHomePresenter.this.dealAllResponseHideLoading();
            }
        });
    }

    private void requestRecommedMusicGroupList() {
        this.requestStatus[5] = 1;
        WanOSRetrofitUtil.getRecommendMusicGroupList(1, 6, new ResponseCallBack<GetMusicGroupListResponse>(null) { // from class: com.wanos.media.presenter.MusicHomePresenter.6
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetMusicGroupListResponse response) {
                MediaMusicGorupListBean mediaMusicGorupListBean = response.data;
                if (mediaMusicGorupListBean == null || MusicHomePresenter.this.mView == 0) {
                    return;
                }
                List<MusicGroupInfo> musicGroupList = mediaMusicGorupListBean.getMusicGroupList();
                if (musicGroupList == null || musicGroupList.size() == 0) {
                    MusicHomePresenter.this.requestMusicGroupMoreList();
                    return;
                }
                MusicHomePresenter.this.requestStatus[5] = 2;
                ((MusicHomeView) MusicHomePresenter.this.mView).updateRecommedMusicGroupView(mediaMusicGorupListBean.getMusicGroupList());
                MusicHomePresenter.this.dealAllResponseHideLoading();
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int code, String msg) {
                MusicHomePresenter.this.requestMusicGroupMoreList();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestMusicGroupMoreList() {
        WanOSRetrofitUtil.getMusicGroupList(1, 6, new ResponseCallBack<GetMusicGroupListResponse>(null) { // from class: com.wanos.media.presenter.MusicHomePresenter.7
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetMusicGroupListResponse response) {
                MediaMusicGorupListBean mediaMusicGorupListBean = response.data;
                if (mediaMusicGorupListBean == null || MusicHomePresenter.this.mView == 0) {
                    return;
                }
                MusicHomePresenter.this.requestStatus[5] = 2;
                ((MusicHomeView) MusicHomePresenter.this.mView).updateRecommedMusicGroupView(mediaMusicGorupListBean.getMusicGroupList());
                MusicHomePresenter.this.dealAllResponseHideLoading();
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int code, String msg) {
                MusicHomePresenter.this.requestStatus[5] = 2;
                if (MusicHomePresenter.this.mView != 0) {
                    ((MusicHomeView) MusicHomePresenter.this.mView).updateRecommedMusicGroupView(new ArrayList());
                }
                MusicHomePresenter.this.dealAllResponseHideLoading();
            }
        });
    }

    private void requestRakingMusicList() {
        Log.i(TAG, "requestRakingMusicList: ");
        this.requestStatus[6] = 1;
        WanOSRetrofitUtil.getRankMusicList(1, 10, new ResponseCallBack<GetMusicListResponse>(null) { // from class: com.wanos.media.presenter.MusicHomePresenter.8
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetMusicListResponse response) {
                MediaMusicListBean mediaMusicListBean = response.data;
                if (mediaMusicListBean == null || MusicHomePresenter.this.mView == 0) {
                    return;
                }
                if (mediaMusicListBean.getMusicList() == null || mediaMusicListBean.getMusicList().size() == 0) {
                    MusicHomePresenter.this.requestRakingMusicMoreList();
                    return;
                }
                MusicHomePresenter.this.requestStatus[6] = 2;
                ((MusicHomeView) MusicHomePresenter.this.mView).updateRankingMusicView(MusicHomePresenter.this.dealData(mediaMusicListBean.getMusicList()));
                MusicHomePresenter.this.dealAllResponseHideLoading();
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int code, String msg) {
                MusicHomePresenter.this.requestRakingMusicMoreList();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestRakingMusicMoreList() {
        WanOSRetrofitUtil.getRankMusicListDetails(1, 10, new ResponseCallBack<GetMusicListResponse>(null) { // from class: com.wanos.media.presenter.MusicHomePresenter.9
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetMusicListResponse response) {
                MediaMusicListBean mediaMusicListBean = response.data;
                if (mediaMusicListBean == null || MusicHomePresenter.this.mView == 0) {
                    return;
                }
                MusicHomePresenter.this.requestStatus[6] = 2;
                ((MusicHomeView) MusicHomePresenter.this.mView).updateRankingMusicView(MusicHomePresenter.this.dealData(mediaMusicListBean.getMusicList()));
                MusicHomePresenter.this.dealAllResponseHideLoading();
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int code, String msg) {
                MusicHomePresenter.this.requestStatus[6] = 2;
                if (MusicHomePresenter.this.mView != 0) {
                    ((MusicHomeView) MusicHomePresenter.this.mView).updateRankingMusicView(new ArrayList());
                }
                MusicHomePresenter.this.dealAllResponseHideLoading();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<MusicInfoBean> dealData(List<MusicInfoBean> mediaMusicBeanList) {
        if (mediaMusicBeanList == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int size = mediaMusicBeanList.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                MusicInfoBean musicInfoBean = mediaMusicBeanList.get(i);
                musicInfoBean.setIndex(i);
                arrayList.add(musicInfoBean);
            }
        }
        return arrayList;
    }
}
