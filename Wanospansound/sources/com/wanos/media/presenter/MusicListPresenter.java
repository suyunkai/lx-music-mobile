package com.wanos.media.presenter;

import android.content.Context;
import com.wanos.WanosCommunication.BaseResponse;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.WanosCommunication.bean.MediaMusicGorupDetailBean;
import com.wanos.WanosCommunication.bean.MediaMusicListBean;
import com.wanos.WanosCommunication.bean.MusicGroupInfo;
import com.wanos.WanosCommunication.response.FreeGroupInfoResponse;
import com.wanos.WanosCommunication.response.GetFreeMusicGroupCoverResponse;
import com.wanos.WanosCommunication.response.GetMusicGroupDetailResponse;
import com.wanos.WanosCommunication.response.GetMusicListResponse;
import com.wanos.WanosCommunication.router.HttpRouter;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.media.db.DbCallBack;
import com.wanos.media.db.WanosDbUtils;
import com.wanos.media.view.MusicListView;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class MusicListPresenter extends BasePresenter<MusicListView> {
    public static MediaMusicListBean mediaMusicListBean;
    private long groupId;

    public MusicListPresenter(MusicListView view) {
        this.groupId = -1L;
        this.mView = view;
    }

    public MusicListPresenter(MusicListView view, long groupId) {
        this.groupId = -1L;
        this.mView = view;
        this.groupId = groupId;
    }

    public void requestData(final int page, Context context) {
        if (this.mView != 0) {
            ((MusicListView) this.mView).showLoading();
            long j = this.groupId;
            Context context2 = null;
            if (j == -8) {
                WanOSRetrofitUtil.getLimitedFreeMusicGroupCover(new ResponseCallBack<GetFreeMusicGroupCoverResponse>(context2) { // from class: com.wanos.media.presenter.MusicListPresenter.1
                    @Override // com.wanos.WanosCommunication.ResponseCallBack
                    public void onResponseFailure(int code, String msg) {
                    }

                    @Override // com.wanos.WanosCommunication.ResponseCallBack
                    public void onResponseSuccessful(GetFreeMusicGroupCoverResponse response) {
                        FreeGroupInfoResponse freeGroupInfoResponse = response.data;
                        if (freeGroupInfoResponse == null || freeGroupInfoResponse.getInfo() == null || MusicListPresenter.this.mView == 0) {
                            return;
                        }
                        ((MusicListView) MusicListPresenter.this.mView).updateFreeCover(freeGroupInfoResponse.getInfo());
                    }
                });
                WanOSRetrofitUtil.getLimitedFreeMusicList(page, 100, true, new ResponseCallBack<GetMusicListResponse>(context) { // from class: com.wanos.media.presenter.MusicListPresenter.2
                    @Override // com.wanos.WanosCommunication.ResponseCallBack
                    public void onResponseSuccessful(GetMusicListResponse response) {
                        MediaMusicListBean mediaMusicListBean2 = response.data;
                        if (mediaMusicListBean2 != null && MusicListPresenter.this.mView != 0) {
                            List<MusicInfoBean> musicList = mediaMusicListBean2.getMusicList();
                            MusicGroupInfo musicGroupInfo = new MusicGroupInfo();
                            musicGroupInfo.setMusicGroupId(-8L);
                            if (page == 1) {
                                ((MusicListView) MusicListPresenter.this.mView).updateView(musicGroupInfo, musicList);
                            } else {
                                ((MusicListView) MusicListPresenter.this.mView).addDataView(page, musicList);
                            }
                        }
                        if (MusicListPresenter.this.mView != 0) {
                            ((MusicListView) MusicListPresenter.this.mView).hideLoading();
                        }
                    }

                    @Override // com.wanos.WanosCommunication.ResponseCallBack
                    public void onResponseFailure(int code, String msg) {
                        if (MusicListPresenter.this.mView != 0) {
                            ((MusicListView) MusicListPresenter.this.mView).hideLoading();
                            ((MusicListView) MusicListPresenter.this.mView).showFailOrError(code, msg);
                        }
                    }
                });
                return;
            }
            if (j == -3) {
                WanOSRetrofitUtil.getRankMusicListDetails(page, 100, new ResponseCallBack<GetMusicListResponse>(context) { // from class: com.wanos.media.presenter.MusicListPresenter.3
                    @Override // com.wanos.WanosCommunication.ResponseCallBack
                    public void onResponseSuccessful(GetMusicListResponse response) {
                        MediaMusicListBean mediaMusicListBean2 = response.data;
                        if (mediaMusicListBean2 != null && MusicListPresenter.this.mView != 0) {
                            List<MusicInfoBean> musicList = mediaMusicListBean2.getMusicList();
                            MusicGroupInfo musicGroupInfo = new MusicGroupInfo();
                            musicGroupInfo.setMusicGroupId(-3L);
                            if (page == 1) {
                                ((MusicListView) MusicListPresenter.this.mView).updateView(musicGroupInfo, musicList);
                            } else {
                                ((MusicListView) MusicListPresenter.this.mView).addDataView(page, musicList);
                            }
                        }
                        if (MusicListPresenter.this.mView != 0) {
                            ((MusicListView) MusicListPresenter.this.mView).hideLoading();
                        }
                    }

                    @Override // com.wanos.WanosCommunication.ResponseCallBack
                    public void onResponseFailure(int code, String msg) {
                        if (MusicListPresenter.this.mView != 0) {
                            ((MusicListView) MusicListPresenter.this.mView).hideLoading();
                            ((MusicListView) MusicListPresenter.this.mView).showFailOrError(code, msg);
                        }
                    }
                });
                return;
            }
            if (j == -5) {
                WanosDbUtils.getMusicHistoryList(new DbCallBack<List<MusicInfoBean>>() { // from class: com.wanos.media.presenter.MusicListPresenter.4
                    @Override // com.wanos.media.db.DbCallBack
                    public void callBackData(List<MusicInfoBean> data) {
                        if (data == null || MusicListPresenter.this.mView == 0) {
                            return;
                        }
                        MusicGroupInfo musicGroupInfo = new MusicGroupInfo();
                        musicGroupInfo.setMusicGroupId(-5L);
                        ((MusicListView) MusicListPresenter.this.mView).updateView(musicGroupInfo, data);
                        ((MusicListView) MusicListPresenter.this.mView).hideLoading();
                    }
                });
                return;
            }
            if (j == -4) {
                WanOSRetrofitUtil.getCollectMusicList(page, 100, new ResponseCallBack<GetMusicListResponse>(context) { // from class: com.wanos.media.presenter.MusicListPresenter.5
                    @Override // com.wanos.WanosCommunication.ResponseCallBack
                    public void onResponseSuccessful(GetMusicListResponse response) {
                        MediaMusicListBean mediaMusicListBean2 = response.data;
                        if (mediaMusicListBean2 != null && MusicListPresenter.this.mView != 0) {
                            List<MusicInfoBean> musicList = mediaMusicListBean2.getMusicList();
                            MusicGroupInfo musicGroupInfo = new MusicGroupInfo();
                            musicGroupInfo.setMusicGroupId(-4L);
                            if (page == 1) {
                                ((MusicListView) MusicListPresenter.this.mView).updateView(musicGroupInfo, musicList);
                            } else {
                                ((MusicListView) MusicListPresenter.this.mView).addDataView(page, musicList);
                            }
                        }
                        if (MusicListPresenter.this.mView != 0) {
                            ((MusicListView) MusicListPresenter.this.mView).hideLoading();
                        }
                    }

                    @Override // com.wanos.WanosCommunication.ResponseCallBack
                    public void onResponseFailure(int code, String msg) {
                        if (MusicListPresenter.this.mView != 0) {
                            ((MusicListView) MusicListPresenter.this.mView).hideLoading();
                            ((MusicListView) MusicListPresenter.this.mView).showFailOrError(msg);
                        }
                    }
                });
            } else if (j == -12 || j == -11 || j == -10) {
                HttpRouter.getRecommendService().getRecommendMusicList(page, 100, this.groupId, new ResponseCallBack<GetMusicListResponse>(context) { // from class: com.wanos.media.presenter.MusicListPresenter.6
                    @Override // com.wanos.WanosCommunication.ResponseCallBack
                    public void onResponseSuccessful(GetMusicListResponse response) {
                        MediaMusicListBean mediaMusicListBean2 = response.data;
                        if (mediaMusicListBean2 != null && MusicListPresenter.this.mView != 0) {
                            List<MusicInfoBean> musicList = mediaMusicListBean2.getMusicList();
                            MusicGroupInfo musicGroupInfo = new MusicGroupInfo();
                            musicGroupInfo.setMusicGroupId(MusicListPresenter.this.groupId);
                            if (page == 1) {
                                ((MusicListView) MusicListPresenter.this.mView).updateView(musicGroupInfo, musicList);
                            } else {
                                ((MusicListView) MusicListPresenter.this.mView).addDataView(page, musicList);
                            }
                            if (MusicListPresenter.mediaMusicListBean != null && page == 1) {
                                if (mediaMusicListBean2.getTotal() != MusicListPresenter.mediaMusicListBean.getTotal()) {
                                    ToastUtil.showMsg("推荐列表已更新");
                                } else {
                                    List<MusicInfoBean> musicList2 = MusicListPresenter.mediaMusicListBean.getMusicList();
                                    int i = 0;
                                    while (true) {
                                        if (i < musicList.size()) {
                                            if (i <= musicList2.size() - 1 && !musicList.get(i).equals(musicList2.get(i))) {
                                                ToastUtil.showMsg("推荐列表已更新");
                                                break;
                                            }
                                            i++;
                                        } else {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        if (MusicListPresenter.this.mView != 0) {
                            ((MusicListView) MusicListPresenter.this.mView).hideLoading();
                        }
                        if (page == 1) {
                            MusicListPresenter.mediaMusicListBean = mediaMusicListBean2;
                        }
                    }

                    @Override // com.wanos.WanosCommunication.ResponseCallBack
                    public void onResponseFailure(int code, String msg) {
                        if (MusicListPresenter.this.mView != 0) {
                            ((MusicListView) MusicListPresenter.this.mView).hideLoading();
                            ((MusicListView) MusicListPresenter.this.mView).showFailOrError(msg);
                        }
                    }
                });
            } else {
                WanOSRetrofitUtil.getMusicGroupDetail(j, page, 100, new ResponseCallBack<GetMusicGroupDetailResponse>(context2) { // from class: com.wanos.media.presenter.MusicListPresenter.7
                    @Override // com.wanos.WanosCommunication.ResponseCallBack
                    public void onResponseSuccessful(GetMusicGroupDetailResponse response) {
                        MediaMusicGorupDetailBean mediaMusicGorupDetailBean = response.data;
                        if (mediaMusicGorupDetailBean != null && mediaMusicGorupDetailBean.getMusicGroupInfo() != null && MusicListPresenter.this.mView != 0) {
                            if (page == 1) {
                                ((MusicListView) MusicListPresenter.this.mView).updateView(mediaMusicGorupDetailBean.getMusicGroupInfo(), mediaMusicGorupDetailBean.getMusicGroupInfo().getMusicList());
                            } else {
                                ((MusicListView) MusicListPresenter.this.mView).addDataView(page, mediaMusicGorupDetailBean.getMusicGroupInfo().getMusicList());
                            }
                        }
                        if (MusicListPresenter.this.mView != 0) {
                            ((MusicListView) MusicListPresenter.this.mView).hideLoading();
                        }
                    }

                    @Override // com.wanos.WanosCommunication.ResponseCallBack
                    public void onResponseFailure(int code, String msg) {
                        if (MusicListPresenter.this.mView != 0) {
                            ((MusicListView) MusicListPresenter.this.mView).hideLoading();
                            ((MusicListView) MusicListPresenter.this.mView).showFailOrError(code, msg);
                        }
                    }
                });
            }
        }
    }

    public void collectOrCancleCollectMusicGroupRequest(final long musicGroupId, boolean isCollect, Context context) {
        if (isCollect) {
            WanOSRetrofitUtil.musicGroupCollect(musicGroupId, 1, new ResponseCallBack<BaseResponse>(context) { // from class: com.wanos.media.presenter.MusicListPresenter.8
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int code, String msg) {
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(BaseResponse response) {
                    if (MusicListPresenter.this.mView != 0) {
                        ((MusicListView) MusicListPresenter.this.mView).updateCollectStatus(musicGroupId, true);
                    }
                }
            });
        } else {
            WanOSRetrofitUtil.musicGroupCollectCancel(musicGroupId, 1, new ResponseCallBack<BaseResponse>(context) { // from class: com.wanos.media.presenter.MusicListPresenter.9
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int code, String msg) {
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(BaseResponse response) {
                    if (MusicListPresenter.this.mView != 0) {
                        ((MusicListView) MusicListPresenter.this.mView).updateCollectStatus(musicGroupId, false);
                    }
                }
            });
        }
    }

    @Override // com.wanos.media.presenter.BasePresenter, com.wanos.media.presenter.IPresenter
    public void onDestroy() {
        super.onDestroy();
        dettachView();
    }
}
