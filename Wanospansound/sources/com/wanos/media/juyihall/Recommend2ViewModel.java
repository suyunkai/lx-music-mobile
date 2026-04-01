package com.wanos.media.juyihall;

import android.app.Application;
import android.util.Log;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.bean.AudioBookAlbumInfoBean;
import com.wanos.WanosCommunication.bean.BannerBean;
import com.wanos.WanosCommunication.bean.MusicGroupInfo;
import com.wanos.WanosCommunication.response.GetTagListResponse;
import com.wanos.WanosCommunication.service.MediaStatistic;
import com.wanos.commonlibrary.bean.IPlayable;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import com.wanos.commonlibrary.bean.TagInfoBean;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.commonlibrary.router.ServiceRouter;
import com.wanos.commonlibrary.utils.StatisticUtil;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.media.juyihall.bean.GetRecBannerResponse;
import com.wanos.media.juyihall.bean.GetRecDailyResponse;
import com.wanos.media.juyihall.bean.GetRecMusicGroupListResponse;
import com.wanos.media.juyihall.bean.GetRecMusicListResponse;
import com.wanos.media.juyihall.bean.GetRecRadioListResponse;
import com.wanos.media.juyihall.bean.RecMediaInfo;
import com.wanos.media.juyihall.repo.RecommendRepository;
import com.wanos.media.juyihall.utils.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class Recommend2ViewModel extends AndroidViewModel {
    private static final int LOADING_STATUS = 1;
    private static final int LOAD_COMPLETE_STATUS = 2;
    private static final int LOAD_ERROR_STATUS = 3;
    private static final int NONE_LOAD_STATUS = 0;
    public static final String TAG = "wanos:[Recommend2ViewModel]";
    public static final int TYPE_BLOCK_ALBUM = 402;
    public static final int TYPE_BLOCK_DAILY = 102;
    public static final int TYPE_BLOCK_MUSIC = 202;
    public static final int TYPE_BLOCK_RATIO = 302;
    public static final int TYPE_BLOCK_RELAX = 502;
    public static final int TYPE_BLOCK_TAG = 602;
    public static final int TYPE_TITLE_ALBUM = 401;
    public static final int TYPE_TITLE_MUSIC = 201;
    public static final int TYPE_TITLE_RATIO = 301;
    public static final int TYPE_TITLE_RELAX = 501;
    public static final int TYPE_TITLE_TAG = 601;
    public List<MusicGroupInfo> albumList;
    public List<BannerBean> bannerList;
    private MediaPlayerEnum.CallBackState callBackState;
    public List<MusicInfoBean> dailyList;
    public MutableLiveData<Integer> listRefreshLive;
    public MutableLiveData<String> loadErrorLive;
    private MediaInfo mediaInfo;
    public List<MusicInfoBean> musicList;
    private List<IPlayable> playList;
    public List<AudioBookAlbumInfoBean> radioList;
    public List<Integer> recommendList;
    public List<MusicInfoBean> relaxList;
    private RecommendRepository repository;
    private final int[] requestStatus;
    public List<TagInfoBean> tagList;

    public Recommend2ViewModel(Application application) {
        super(application);
        this.recommendList = new ArrayList();
        this.dailyList = new ArrayList();
        this.musicList = new ArrayList();
        this.radioList = new ArrayList();
        this.albumList = new ArrayList();
        this.relaxList = new ArrayList();
        this.tagList = new ArrayList();
        this.bannerList = new ArrayList();
        this.listRefreshLive = new MutableLiveData<>();
        this.loadErrorLive = new MutableLiveData<>();
        this.playList = new ArrayList();
        this.requestStatus = new int[]{0, 0, 0, 0, 0, 0};
        this.repository = new RecommendRepository();
    }

    public void requestData() {
        Log.i(TAG, "requestData: ");
        requestDaily();
        requestBanner();
        requestMusic(false);
        requestRadio(false);
        requestAlbum(false);
        requestRelax(false);
        requestTag();
    }

    public void requestDaily() {
        this.requestStatus[0] = 1;
        this.repository.getRecommendDailyList(1, 10, new ResponseCallBack<GetRecDailyResponse>(null) { // from class: com.wanos.media.juyihall.Recommend2ViewModel.1
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetRecDailyResponse getRecDailyResponse) {
                Recommend2ViewModel.this.requestStatus[0] = 2;
                if (getRecDailyResponse == null || getRecDailyResponse.getList() == null || getRecDailyResponse.getList().isEmpty()) {
                    return;
                }
                ArrayList arrayList = new ArrayList();
                Iterator<RecMediaInfo> it = getRecDailyResponse.getList().iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next().toMediaInfoBean());
                }
                Recommend2ViewModel recommend2ViewModel = Recommend2ViewModel.this;
                recommend2ViewModel.checkIsPlayMusicInfo(arrayList, recommend2ViewModel.mediaInfo, -12L);
                Recommend2ViewModel.this.dailyList.clear();
                Recommend2ViewModel.this.dailyList.addAll(arrayList);
                if (!Recommend2ViewModel.this.recommendList.contains(102)) {
                    Recommend2ViewModel.this.recommendList.add(102);
                    Collections.sort(Recommend2ViewModel.this.recommendList);
                    Recommend2ViewModel.this.listRefreshLive.setValue(-1);
                    return;
                }
                Recommend2ViewModel.this.listRefreshLive.setValue(Integer.valueOf(Recommend2ViewModel.this.recommendList.indexOf(102)));
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i, String str) {
                Recommend2ViewModel.this.requestStatus[0] = 3;
                Recommend2ViewModel.this.dealAllResponseHideLoading(str);
                if (Recommend2ViewModel.this.recommendList.contains(102)) {
                    Recommend2ViewModel.this.recommendList.remove((Object) 102);
                    Recommend2ViewModel.this.recommendList.remove((Object) 102);
                    Recommend2ViewModel.this.listRefreshLive.setValue(-1);
                }
            }
        });
    }

    private void requestBanner() {
        this.repository.getRecommendBannerList(new ResponseCallBack<GetRecBannerResponse>(null) { // from class: com.wanos.media.juyihall.Recommend2ViewModel.2
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i, String str) {
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetRecBannerResponse getRecBannerResponse) {
                Recommend2ViewModel.this.bannerList.clear();
                Recommend2ViewModel.this.bannerList.addAll(getRecBannerResponse.getList() == null ? Collections.emptyList() : getRecBannerResponse.getList());
            }
        });
    }

    public void requestMusic(final boolean z) {
        this.requestStatus[1] = 1;
        this.repository.getRecommendMusicList(z, new ResponseCallBack<GetRecMusicListResponse>(null) { // from class: com.wanos.media.juyihall.Recommend2ViewModel.3
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetRecMusicListResponse getRecMusicListResponse) {
                Recommend2ViewModel.this.requestStatus[1] = 2;
                if (getRecMusicListResponse != null && getRecMusicListResponse.getList() != null) {
                    List<MusicInfoBean> list = getRecMusicListResponse.getList();
                    if (list.isEmpty() && z) {
                        ToastUtil.showMsg(Recommend2ViewModel.this.getApplication().getResources().getString(R.string.next_batch_empty_toast));
                        return;
                    }
                    Recommend2ViewModel recommend2ViewModel = Recommend2ViewModel.this;
                    recommend2ViewModel.checkIsPlayMusicInfo(list, recommend2ViewModel.mediaInfo, -10L);
                    Recommend2ViewModel.this.musicList.clear();
                    Recommend2ViewModel.this.musicList.addAll(list);
                    if (!Recommend2ViewModel.this.recommendList.contains(202)) {
                        Recommend2ViewModel.this.recommendList.add(202);
                        Recommend2ViewModel.this.recommendList.add(201);
                        Collections.sort(Recommend2ViewModel.this.recommendList);
                        Recommend2ViewModel.this.listRefreshLive.setValue(-1);
                        return;
                    }
                    Recommend2ViewModel.this.listRefreshLive.setValue(Integer.valueOf(Recommend2ViewModel.this.recommendList.indexOf(202)));
                    return;
                }
                onLoadFailed();
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i, String str) {
                onLoadFailed();
            }

            private void onLoadFailed() {
                Recommend2ViewModel.this.requestStatus[1] = 3;
                Recommend2ViewModel.this.dealAllResponseHideLoading("");
                if (Recommend2ViewModel.this.recommendList.contains(202)) {
                    Recommend2ViewModel.this.recommendList.remove((Object) 202);
                    Recommend2ViewModel.this.recommendList.remove((Object) 201);
                    Recommend2ViewModel.this.listRefreshLive.setValue(-1);
                }
            }
        });
    }

    public void requestRadio(final boolean z) {
        this.requestStatus[2] = 1;
        this.repository.getRecommendAudioBookList(z, new ResponseCallBack<GetRecRadioListResponse>(null) { // from class: com.wanos.media.juyihall.Recommend2ViewModel.4
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetRecRadioListResponse getRecRadioListResponse) {
                if (getRecRadioListResponse != null && getRecRadioListResponse.getList() != null) {
                    Recommend2ViewModel.this.requestStatus[2] = 2;
                    List<AudioBookAlbumInfoBean> list = getRecRadioListResponse.getList();
                    if (list.isEmpty() && z) {
                        ToastUtil.showMsg(Recommend2ViewModel.this.getApplication().getResources().getString(R.string.next_batch_empty_toast));
                        return;
                    }
                    Recommend2ViewModel recommend2ViewModel = Recommend2ViewModel.this;
                    recommend2ViewModel.checkIsPlayAudioBook(list, recommend2ViewModel.mediaInfo);
                    Recommend2ViewModel.this.radioList.clear();
                    Recommend2ViewModel.this.radioList.addAll(list);
                    if (!Recommend2ViewModel.this.recommendList.contains(302)) {
                        Recommend2ViewModel.this.recommendList.add(302);
                        Recommend2ViewModel.this.recommendList.add(301);
                        Collections.sort(Recommend2ViewModel.this.recommendList);
                        Recommend2ViewModel.this.listRefreshLive.setValue(-1);
                        return;
                    }
                    Recommend2ViewModel.this.listRefreshLive.setValue(Integer.valueOf(Recommend2ViewModel.this.recommendList.indexOf(302)));
                    return;
                }
                onLoadFailed();
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i, String str) {
                onLoadFailed();
            }

            private void onLoadFailed() {
                Recommend2ViewModel.this.requestStatus[2] = 3;
                Recommend2ViewModel.this.dealAllResponseHideLoading("");
                if (Recommend2ViewModel.this.recommendList.contains(302)) {
                    Recommend2ViewModel.this.recommendList.remove((Object) 302);
                    Recommend2ViewModel.this.recommendList.remove((Object) 301);
                    Recommend2ViewModel.this.listRefreshLive.setValue(-1);
                }
            }
        });
    }

    public void requestAlbum(final boolean z) {
        this.requestStatus[3] = 1;
        this.repository.getRecommendMusicGroupList(z, new ResponseCallBack<GetRecMusicGroupListResponse>(null) { // from class: com.wanos.media.juyihall.Recommend2ViewModel.5
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetRecMusicGroupListResponse getRecMusicGroupListResponse) {
                if (getRecMusicGroupListResponse != null && getRecMusicGroupListResponse.getList() != null) {
                    Recommend2ViewModel.this.requestStatus[3] = 2;
                    List<MusicGroupInfo> list = getRecMusicGroupListResponse.getList();
                    if (list.isEmpty() && z) {
                        ToastUtil.showMsg(Recommend2ViewModel.this.getApplication().getResources().getString(R.string.next_batch_empty_toast));
                        return;
                    }
                    Recommend2ViewModel recommend2ViewModel = Recommend2ViewModel.this;
                    recommend2ViewModel.checkIsPlayMusicGroup(list, recommend2ViewModel.mediaInfo);
                    Recommend2ViewModel.this.albumList.clear();
                    Recommend2ViewModel.this.albumList.addAll(list);
                    if (!Recommend2ViewModel.this.recommendList.contains(402)) {
                        Recommend2ViewModel.this.recommendList.add(402);
                        Recommend2ViewModel.this.recommendList.add(401);
                        Collections.sort(Recommend2ViewModel.this.recommendList);
                        Recommend2ViewModel.this.listRefreshLive.setValue(-1);
                        return;
                    }
                    Recommend2ViewModel.this.listRefreshLive.setValue(Integer.valueOf(Recommend2ViewModel.this.recommendList.indexOf(402)));
                    return;
                }
                onLoadFailed();
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i, String str) {
                onLoadFailed();
            }

            private void onLoadFailed() {
                Recommend2ViewModel.this.requestStatus[3] = 3;
                Recommend2ViewModel.this.dealAllResponseHideLoading("");
                if (Recommend2ViewModel.this.recommendList.contains(402)) {
                    Recommend2ViewModel.this.recommendList.remove((Object) 402);
                    Recommend2ViewModel.this.recommendList.remove((Object) 401);
                    Recommend2ViewModel.this.listRefreshLive.setValue(-1);
                }
            }
        });
    }

    public void requestRelax(final boolean z) {
        this.requestStatus[4] = 1;
        this.repository.getRecommendFunctionList(z, new ResponseCallBack<GetRecMusicListResponse>(null) { // from class: com.wanos.media.juyihall.Recommend2ViewModel.6
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetRecMusicListResponse getRecMusicListResponse) {
                Recommend2ViewModel.this.requestStatus[4] = 2;
                if (getRecMusicListResponse != null && getRecMusicListResponse.getList() != null) {
                    List<MusicInfoBean> list = getRecMusicListResponse.getList();
                    if (list.isEmpty() && z) {
                        ToastUtil.showMsg(Recommend2ViewModel.this.getApplication().getResources().getString(R.string.next_batch_empty_toast));
                        return;
                    }
                    Recommend2ViewModel recommend2ViewModel = Recommend2ViewModel.this;
                    recommend2ViewModel.checkIsPlayMusicInfo(list, recommend2ViewModel.mediaInfo, -11L);
                    Recommend2ViewModel.this.relaxList.clear();
                    Recommend2ViewModel.this.relaxList.addAll(list);
                    if (!Recommend2ViewModel.this.recommendList.contains(502)) {
                        Recommend2ViewModel.this.recommendList.add(502);
                        Recommend2ViewModel.this.recommendList.add(501);
                        Collections.sort(Recommend2ViewModel.this.recommendList);
                        Recommend2ViewModel.this.listRefreshLive.setValue(-1);
                        return;
                    }
                    Recommend2ViewModel.this.listRefreshLive.setValue(Integer.valueOf(Recommend2ViewModel.this.recommendList.indexOf(502)));
                    return;
                }
                onLoadFailed();
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i, String str) {
                onLoadFailed();
            }

            private void onLoadFailed() {
                Recommend2ViewModel.this.requestStatus[4] = 3;
                Recommend2ViewModel.this.dealAllResponseHideLoading("");
                if (Recommend2ViewModel.this.recommendList.contains(502)) {
                    Recommend2ViewModel.this.recommendList.remove((Object) 502);
                    Recommend2ViewModel.this.recommendList.remove((Object) 501);
                    Recommend2ViewModel.this.listRefreshLive.setValue(-1);
                }
            }
        });
    }

    public void requestTag() {
        this.requestStatus[5] = 1;
        this.repository.getRecommendTagList(new ResponseCallBack<GetTagListResponse>(null) { // from class: com.wanos.media.juyihall.Recommend2ViewModel.7
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetTagListResponse getTagListResponse) {
                Recommend2ViewModel.this.requestStatus[5] = 2;
                if (getTagListResponse != null && getTagListResponse.data != null && getTagListResponse.data.getTagList() != null) {
                    List<TagInfoBean> tagList = getTagListResponse.data.getTagList();
                    Recommend2ViewModel recommend2ViewModel = Recommend2ViewModel.this;
                    recommend2ViewModel.checkIsPlayTag(tagList, recommend2ViewModel.mediaInfo);
                    Recommend2ViewModel.this.tagList.clear();
                    Recommend2ViewModel.this.tagList.addAll(tagList);
                    if (!Recommend2ViewModel.this.recommendList.contains(602)) {
                        Recommend2ViewModel.this.recommendList.add(602);
                        Recommend2ViewModel.this.recommendList.add(601);
                        Collections.sort(Recommend2ViewModel.this.recommendList);
                        Recommend2ViewModel.this.listRefreshLive.setValue(-1);
                        return;
                    }
                    Recommend2ViewModel.this.listRefreshLive.setValue(Integer.valueOf(Recommend2ViewModel.this.recommendList.indexOf(602)));
                    return;
                }
                onLoadFailed();
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int i, String str) {
                onLoadFailed();
            }

            private void onLoadFailed() {
                Recommend2ViewModel.this.requestStatus[5] = 3;
                Recommend2ViewModel.this.dealAllResponseHideLoading("");
                if (Recommend2ViewModel.this.recommendList.contains(602)) {
                    Recommend2ViewModel.this.recommendList.remove((Object) 602);
                    Recommend2ViewModel.this.recommendList.remove((Object) 601);
                    Recommend2ViewModel.this.listRefreshLive.setValue(-1);
                }
            }
        });
    }

    public void setCurrentPlayId(MediaInfo mediaInfo) {
        this.mediaInfo = mediaInfo;
        refreshCurrentPlayId(mediaInfo);
    }

    private void refreshCurrentPlayId(MediaInfo mediaInfo) {
        if (mediaInfo == null) {
            return;
        }
        Iterator<IPlayable> it = this.playList.iterator();
        while (it.hasNext()) {
            it.next().setPlayStatus(null);
        }
        this.playList.clear();
        if (mediaInfo.getMediaGroupType() == -9) {
            checkIsPlayTag(this.tagList, mediaInfo);
            return;
        }
        checkIsPlayMusicInfo(this.dailyList, mediaInfo, -12L);
        checkIsPlayMusicInfo(this.musicList, mediaInfo, -10L);
        checkIsPlayMusicInfo(this.relaxList, mediaInfo, -11L);
        checkIsPlayMusicGroup(this.albumList, mediaInfo);
        checkIsPlayAudioBook(this.radioList, mediaInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkIsPlayMusicInfo(List<MusicInfoBean> list, MediaInfo mediaInfo, long j) {
        if (list == null || mediaInfo == null || mediaInfo.getMusicInfoBean() == null) {
            return;
        }
        for (MusicInfoBean musicInfoBean : list) {
            if (Utils.equalsId(musicInfoBean.getMusicId(), mediaInfo.getMusicInfoBean().getMusicId())) {
                if (j == -12 && musicInfoBean.getContentType() != mediaInfo.getMusicInfoBean().getContentType()) {
                    musicInfoBean.setPlayStatus(null);
                } else {
                    musicInfoBean.setPlayStatus(this.callBackState);
                    this.playList.add(musicInfoBean);
                }
            } else {
                musicInfoBean.setPlayStatus(null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkIsPlayMusicGroup(List<MusicGroupInfo> list, MediaInfo mediaInfo) {
        boolean z = false;
        if (list != null && mediaInfo != null && mediaInfo.getMediaType() != MediaPlayerEnum.MediaType.Audiobook) {
            for (MusicGroupInfo musicGroupInfo : list) {
                if (Utils.equalsId(musicGroupInfo.getMusicGroupId(), mediaInfo.getGroupId())) {
                    musicGroupInfo.setPlayStatus(this.callBackState);
                    this.playList.add(musicGroupInfo);
                    z = true;
                }
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkIsPlayAudioBook(List<AudioBookAlbumInfoBean> list, MediaInfo mediaInfo) {
        if (list == null || mediaInfo == null || mediaInfo.getMediaType() != MediaPlayerEnum.MediaType.Audiobook) {
            return;
        }
        for (AudioBookAlbumInfoBean audioBookAlbumInfoBean : list) {
            if (Utils.equalsId(audioBookAlbumInfoBean.getId(), mediaInfo.getGroupId())) {
                audioBookAlbumInfoBean.setPlayStatus(this.callBackState);
                this.playList.add(audioBookAlbumInfoBean);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkIsPlayTag(List<TagInfoBean> list, MediaInfo mediaInfo) {
        if (list == null || mediaInfo == null || mediaInfo.getMediaGroupType() != -9) {
            return;
        }
        for (TagInfoBean tagInfoBean : list) {
            if (Utils.equalsId(tagInfoBean.getTagId(), mediaInfo.getGroupId())) {
                tagInfoBean.setPlayStatus(this.callBackState);
                this.playList.add(tagInfoBean);
            }
        }
    }

    public void updatePlayStatus(MediaPlayerEnum.CallBackState callBackState) {
        this.callBackState = callBackState;
        refreshPlayStatus(callBackState);
    }

    private void refreshPlayStatus(MediaPlayerEnum.CallBackState callBackState) {
        Iterator<IPlayable> it = this.playList.iterator();
        while (it.hasNext()) {
            it.next().setPlayStatus(callBackState);
        }
        this.listRefreshLive.setValue(-1);
    }

    public void playAll(int i) {
        if (i == 201) {
            playAll(this.musicList, -10L);
            Log.i("zt", "用户点击甄选天籁播放全部按钮");
            MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.JUYI_CLICK_SELECT_TRANA_PLAY_ALL_BUTTON, "", "", "", "", 0);
        } else if (i == 501) {
            playAll(this.relaxList, -11L);
            Log.i("zt", "用户点击此刻让世界安静下来播放全部按钮点击次数");
            MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.JUYI_CLICK_MAKE_WORLD_QUIET_PLAY_ALL_BUTTON, "", "", "", "", 0);
        } else if (i == 102) {
            playAll(this.dailyList, -12L);
        }
    }

    public void refreshBlock(int i) {
        if (i == 201) {
            requestMusic(true);
            Log.i("zt", "用户点击甄选天籁换一批按钮点击次数");
            MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.JUYI_CLICK_SELECT_TRANA_CHANGE_BATCH_BUTTON, "", "", "", "", 0);
        } else if (i == 501) {
            requestRelax(true);
            Log.i("zt", "此刻让世界安静下来换一批按钮点击次数");
            MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.JUYI_CLICK_MAKE_WORLD_QUIET_CHANGE_BATCH_BUTTON, "", "", "", "", 0);
        } else if (i == 401) {
            requestAlbum(true);
        } else if (i == 301) {
            requestRadio(true);
            Log.i("zt", "用户点击奇幻有声换一批按钮点击次数");
            MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.JUYI_CLICK_FANTASY_SOUND_CHANGE_BATCH_BUTTON, "", "", "", "", 0);
        }
    }

    private void playAll(List<MusicInfoBean> list, long j) {
        MediaInfo mediaInfoCreateRecommendMediaInfo;
        if (list == null || list.isEmpty()) {
            return;
        }
        if (j == -12) {
            mediaInfoCreateRecommendMediaInfo = RecMediaInfo.createRecommendMediaInfo(list.get(0));
        } else {
            MediaInfo mediaInfo = new MediaInfo();
            mediaInfo.setMediaType(MediaPlayerEnum.MediaType.Music);
            mediaInfo.setGroupId(Utils.setFormatId(j));
            mediaInfo.setMediaGroupType(j);
            mediaInfo.setMusicInfoBean(list.get(0));
            mediaInfoCreateRecommendMediaInfo = mediaInfo;
        }
        ServiceRouter.getMediaPlayService().playMediaInfo(mediaInfoCreateRecommendMediaInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dealAllResponseHideLoading(String str) {
        for (int i : this.requestStatus) {
            if (i != 3) {
                return;
            }
        }
        this.loadErrorLive.setValue(str);
    }

    public RecommendRepository getRepository() {
        return this.repository;
    }
}
