package com.wanos.media.presenter;

import android.content.Context;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.WanosCommunication.bean.AudioBookAlbumInfoBean;
import com.wanos.WanosCommunication.bean.BannerBean;
import com.wanos.WanosCommunication.bean.MediaMusicBannerListBean;
import com.wanos.WanosCommunication.bean.ModuleCoverDetailsBean;
import com.wanos.WanosCommunication.response.GetAudioBookRadioListResponse;
import com.wanos.WanosCommunication.response.GetAudioBookRecommendListResponse;
import com.wanos.WanosCommunication.response.GetModuleCoverDetailsResponse;
import com.wanos.WanosCommunication.response.GetMusicBannerListResponse;
import com.wanos.media.cacheData.audiobook.AudioBookAlbumsCache;
import com.wanos.media.view.AudioBookHomeView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class AudioBookHomePresenter extends BasePresenter<AudioBookHomeView> {
    private List<BannerBean> list;
    private final Context mContext;

    public AudioBookHomePresenter(Context context, AudioBookHomeView view) {
        this.mContext = context;
        attachView(view);
    }

    public void requestData() {
        if (this.mView != 0) {
            this.list = new ArrayList();
            WanOSRetrofitUtil.getAudioBookBannerList(new ResponseCallBack<GetMusicBannerListResponse>(this.mContext) { // from class: com.wanos.media.presenter.AudioBookHomePresenter.1
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(GetMusicBannerListResponse response) {
                    MediaMusicBannerListBean mediaMusicBannerListBean = response.data;
                    if (mediaMusicBannerListBean == null || AudioBookHomePresenter.this.mView == 0) {
                        return;
                    }
                    if (mediaMusicBannerListBean.getList().size() == 0) {
                        BannerBean bannerBean = new BannerBean();
                        bannerBean.setId(0L);
                        bannerBean.setPath("");
                        bannerBean.setCoverImg("");
                        AudioBookHomePresenter.this.list.add(bannerBean);
                    } else {
                        AudioBookHomePresenter.this.list = mediaMusicBannerListBean.getList();
                    }
                    ((AudioBookHomeView) AudioBookHomePresenter.this.mView).updateBanner(AudioBookHomePresenter.this.list);
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int code, String msg) {
                    if (AudioBookHomePresenter.this.mView != 0) {
                        BannerBean bannerBean = new BannerBean();
                        bannerBean.setId(0L);
                        bannerBean.setPath("");
                        bannerBean.setCoverImg("");
                        AudioBookHomePresenter.this.list.add(bannerBean);
                        ((AudioBookHomeView) AudioBookHomePresenter.this.mView).updateBanner(AudioBookHomePresenter.this.list);
                    }
                }
            });
            requestModuleCoverDetails();
            initHomeAlbumList();
        }
    }

    private void requestModuleCoverDetails() {
        Context context = null;
        WanOSRetrofitUtil.getModuleCoverDetails(3, "children", new ResponseCallBack<GetModuleCoverDetailsResponse>(context) { // from class: com.wanos.media.presenter.AudioBookHomePresenter.2
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetModuleCoverDetailsResponse response) {
                if (response.data == null || AudioBookHomePresenter.this.mView == 0) {
                    return;
                }
                ((AudioBookHomeView) AudioBookHomePresenter.this.mView).updateChildrenCover(response.data);
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int code, String msg) {
                if (AudioBookHomePresenter.this.mView != 0) {
                    ModuleCoverDetailsBean moduleCoverDetailsBean = new ModuleCoverDetailsBean();
                    moduleCoverDetailsBean.setTitle("");
                    moduleCoverDetailsBean.setCoverImage("");
                    moduleCoverDetailsBean.setContent("");
                    ((AudioBookHomeView) AudioBookHomePresenter.this.mView).updateChildrenCover(moduleCoverDetailsBean);
                }
            }
        });
        WanOSRetrofitUtil.getModuleCoverDetails(3, "my", new ResponseCallBack<GetModuleCoverDetailsResponse>(context) { // from class: com.wanos.media.presenter.AudioBookHomePresenter.3
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetModuleCoverDetailsResponse response) {
                if (response.data != null) {
                    ((AudioBookHomeView) AudioBookHomePresenter.this.mView).updateMyAudioBookCover(response.data);
                }
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int code, String msg) {
                if (AudioBookHomePresenter.this.mView != 0) {
                    ModuleCoverDetailsBean moduleCoverDetailsBean = new ModuleCoverDetailsBean();
                    moduleCoverDetailsBean.setTitle("");
                    moduleCoverDetailsBean.setCoverImage("");
                    moduleCoverDetailsBean.setContent("");
                    ((AudioBookHomeView) AudioBookHomePresenter.this.mView).updateMyAudioBookCover(moduleCoverDetailsBean);
                }
            }
        });
    }

    private List<BannerBean> initBannerData() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BannerBean(0L, "", "https://media-1306012690.cos.ap-beijing.myqcloud.com/medialib/musicimage/12.jpg?q-sign-algorithm=sha1&q-ak=AKID2tVu4yd3D8nOkriJjuqWx9KFvLVQ5Vhe&q-sign-time=1698032223;1706672223&q-key-time=1698032223;1706672223&q-header-list=&q-url-param-list=&q-signature=79c683a0df5b98d29661934eb80a5b653beaa198"));
        arrayList.add(new BannerBean(1L, "", "https://media-1306012690.cos.ap-beijing.myqcloud.com/medialib/musicimage/21.jpg?q-sign-algorithm=sha1&q-ak=AKID2tVu4yd3D8nOkriJjuqWx9KFvLVQ5Vhe&q-sign-time=1698032304;1706672304&q-key-time=1698032304;1706672304&q-header-list=&q-url-param-list=&q-signature=a30911d14f606243df2071b5afd91e7bf17543e6"));
        arrayList.add(new BannerBean(2L, "", "https://media-1306012690.cos.ap-beijing.myqcloud.com/medialib/musicimage/25.jpg?q-sign-algorithm=sha1&q-ak=AKID2tVu4yd3D8nOkriJjuqWx9KFvLVQ5Vhe&q-sign-time=1698032340;1706672340&q-key-time=1698032340;1706672340&q-header-list=&q-url-param-list=&q-signature=a41fe7d5f0c948112d152c00e6492758aa0d8ed2"));
        return arrayList;
    }

    private void initHomeAlbumList() {
        WanOSRetrofitUtil.getAudioBookRecommendList(1, 12, new ResponseCallBack<GetAudioBookRecommendListResponse>(this.mContext) { // from class: com.wanos.media.presenter.AudioBookHomePresenter.4
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetAudioBookRecommendListResponse response) {
                if (response.data == null || AudioBookHomePresenter.this.mView == 0) {
                    return;
                }
                List<AudioBookAlbumInfoBean> list = response.data.list;
                if (list == null || list.size() == 0) {
                    AudioBookHomePresenter.this.initHomeAlbumMoreList();
                } else {
                    AudioBookAlbumsCache.getInstance().addAll(list);
                    ((AudioBookHomeView) AudioBookHomePresenter.this.mView).updateView(list);
                }
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int code, String msg) {
                AudioBookHomePresenter.this.initHomeAlbumMoreList();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initHomeAlbumMoreList() {
        WanOSRetrofitUtil.getAudioBookAlbumList(1, 12, 0, new ResponseCallBack<GetAudioBookRadioListResponse>(this.mContext) { // from class: com.wanos.media.presenter.AudioBookHomePresenter.5
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetAudioBookRadioListResponse response) {
                if (response.data == null || AudioBookHomePresenter.this.mView == 0) {
                    return;
                }
                List<AudioBookAlbumInfoBean> list = response.data.list;
                AudioBookAlbumsCache.getInstance().addAll(list);
                ((AudioBookHomeView) AudioBookHomePresenter.this.mView).updateView(list);
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int code, String msg) {
                if (AudioBookHomePresenter.this.mView != 0) {
                    ((AudioBookHomeView) AudioBookHomePresenter.this.mView).updateView(new ArrayList());
                }
            }
        });
    }
}
