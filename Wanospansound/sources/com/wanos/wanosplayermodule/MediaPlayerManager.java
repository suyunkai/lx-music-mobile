package com.wanos.wanosplayermodule;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.wanos.WanosCommunication.BaseResponse;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.WanosCommunication.bean.MusicCollectStatuBean;
import com.wanos.WanosCommunication.bean.MusicCollectStatuListBean;
import com.wanos.WanosCommunication.response.AudioBookLikeChapterResponse;
import com.wanos.WanosCommunication.response.GetAudioBookMineChapterListResponse;
import com.wanos.WanosCommunication.response.GetMusicGroupDetailResponse;
import com.wanos.WanosCommunication.response.GetMusicListResponse;
import com.wanos.WanosCommunication.response.MusicCollectStatuListResponse;
import com.wanos.WanosCommunication.router.HttpRouter;
import com.wanos.commonlibrary.bean.AudioBookMineChapterItemBean;
import com.wanos.commonlibrary.bean.AudioBookSpeakerBean;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.wanosplayermodule.data.ExtendOperaData;
import com.wanos.wanosplayermodule.data.MediaInfoCallBack;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import retrofit2.Response;

/* JADX INFO: loaded from: classes3.dex */
public class MediaPlayerManager {
    public static final int LOAD_COMPLETE_DATA_PLAY_DEAL_AUTO = 2;
    public static final int LOAD_COMPLETE_DATA_PLAY_DEAL_NEXT = 0;
    public static final int LOAD_COMPLETE_DATA_PLAY_DEAL_NONE = -1;
    public static final int LOAD_COMPLETE_DATA_PLAY_DEAL_PRE = 1;
    private static final String TAG = "wanos:[MediaPlayerManager]";
    private static MediaPlayerManager mInstance;
    private ExtendOperaData extendOperaData;
    private MediaInfo mCurMediaInfo;
    private List<MediaInfo> mMediaRandomList;
    private List<MediaInfo> mPlayList;
    private static final ExecutorService singleThreadExecutorService = Executors.newSingleThreadExecutor();
    private static final MainThreadExecutor mainThreadExecutor = new MainThreadExecutor();
    private int loadCompleteDataPlayDeal = -1;
    private int mCurrentMediaIndex = -1;
    private long loadingMediaGroupId = -100;
    private long loadingMediaGroupType = -100;
    private MediaPlayerEnum.MediaType loadingMediaType = MediaPlayerEnum.MediaType.Unknown;
    private int mTotalMediaNum = -1;

    public interface RequestCallbackListener {
        void collectRequestComplete(boolean z, MediaInfo mediaInfo);

        void onLoadListDealComplete(int i);

        void onLoadListDealStarted();

        void onLoadListFailed();

        void onUpdateListCollect();
    }

    public void updatePlayMode(MediaPlayerEnum.MediaType mediaType, MediaPlayerEnum.PlayMode playMode) {
        MediaInfo mediaInfo = this.mCurMediaInfo;
        if (mediaInfo == null || mediaInfo.getMediaType() != mediaType) {
            return;
        }
        this.mCurMediaInfo.playMode = playMode;
    }

    public static MediaPlayerManager getInstance() {
        if (mInstance == null) {
            mInstance = new MediaPlayerManager();
        }
        return mInstance;
    }

    public ExtendOperaData getExtendOperaData() {
        return this.extendOperaData;
    }

    public void setExtendOperaData(ExtendOperaData extendOperaData) {
        this.extendOperaData = extendOperaData;
    }

    public int getLoadCompleteDataPlayDeal() {
        return this.loadCompleteDataPlayDeal;
    }

    static class MainThreadExecutor implements Executor {
        private final Handler handler = new Handler(Looper.getMainLooper());

        MainThreadExecutor() {
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            this.handler.post(runnable);
        }
    }

    public void requestMediaDataListData(int i, RequestCallbackListener requestCallbackListener) {
        Log.i(TAG, "requestMediaDataListData: dealType = " + i);
        MediaInfo mediaInfo = this.mCurMediaInfo;
        if (mediaInfo != null) {
            MediaPlayerEnum.MediaType mediaType = mediaInfo.getMediaType();
            long groupId = this.mCurMediaInfo.getGroupId();
            long mediaGroupType = this.mCurMediaInfo.getMediaGroupType();
            if (this.loadingMediaGroupId == groupId && mediaType == this.loadingMediaType && this.loadingMediaGroupType == mediaGroupType) {
                return;
            }
            this.loadCompleteDataPlayDeal = i;
            updatePlayList(null);
            if (mediaType != MediaPlayerEnum.MediaType.Music) {
                if (mediaType == MediaPlayerEnum.MediaType.Audiobook) {
                    Log.i(TAG, "requestMediaDataListData: Audiobook");
                    if (requestCallbackListener != null) {
                        requestCallbackListener.onLoadListDealStarted();
                    }
                    this.loadingMediaGroupId = groupId;
                    this.loadingMediaType = mediaType;
                    this.loadingMediaGroupType = mediaGroupType;
                    requestByGroupIdGetAudioBookChapterList(groupId, requestCallbackListener);
                    return;
                }
                return;
            }
            if (mediaGroupType == -1 || mediaGroupType == -8 || mediaGroupType == -2 || mediaGroupType == -3 || mediaGroupType == -4 || mediaGroupType == -9 || mediaGroupType == -10 || mediaGroupType == -12 || mediaGroupType == -11) {
                if (requestCallbackListener != null) {
                    requestCallbackListener.onLoadListDealStarted();
                }
                this.loadingMediaGroupId = groupId;
                this.loadingMediaType = mediaType;
                this.loadingMediaGroupType = mediaGroupType;
                requestMusicSpecialList(requestCallbackListener, groupId, mediaGroupType);
                return;
            }
            if (groupId == -5) {
                requestMusicHistoryList(requestCallbackListener);
                return;
            }
            if (requestCallbackListener != null) {
                requestCallbackListener.onLoadListDealStarted();
            }
            this.loadingMediaGroupId = groupId;
            this.loadingMediaType = mediaType;
            this.loadingMediaGroupType = mediaGroupType;
            if (mediaGroupType == -7) {
                requestByModuleGroupIdGetMusicList(groupId, requestCallbackListener);
            } else {
                requestByGroupIdGetMusicList(groupId, requestCallbackListener);
            }
        }
    }

    private void requestMusicSpecialList(final RequestCallbackListener requestCallbackListener, final long j, final long j2) {
        singleThreadExecutorService.execute(new Runnable() { // from class: com.wanos.wanosplayermodule.MediaPlayerManager.1
            @Override // java.lang.Runnable
            public void run() {
                final ArrayList arrayList = new ArrayList();
                Response<GetMusicListResponse> hotMusicList = null;
                int i = -1;
                int i2 = 0;
                GetMusicListResponse getMusicListResponseBody = null;
                do {
                    i2++;
                    try {
                        long j3 = j;
                        if (j3 == -1 || j3 == -2) {
                            hotMusicList = WanOSRetrofitUtil.getHotMusicList(i2, 100);
                        } else if (j3 == -3) {
                            hotMusicList = WanOSRetrofitUtil.getRankMusicListDetailsSync(i2, 100);
                        } else if (j3 == -4) {
                            hotMusicList = WanOSRetrofitUtil.getCollectMusicList(i2, 100);
                        } else if (j3 == -8) {
                            hotMusicList = WanOSRetrofitUtil.getLimitedFreeMusicList(i2, 100, true);
                        } else {
                            long j4 = j2;
                            if (j4 == -9 || j4 == -12 || j4 == -11 || j4 == -10) {
                                hotMusicList = HttpRouter.getRecommendService().getRecommendMusicList(i2, 100, j2, String.valueOf(j));
                            }
                        }
                        if (hotMusicList.isSuccessful() && (getMusicListResponseBody = hotMusicList.body()) != null && getMusicListResponseBody.data != null && getMusicListResponseBody.data.getMusicList() != null) {
                            for (MusicInfoBean musicInfoBean : getMusicListResponseBody.data.getMusicList()) {
                                i++;
                                musicInfoBean.setIndex(i);
                                musicInfoBean.setPageSize(100);
                                MediaInfo mediaInfo = new MediaInfo();
                                if (musicInfoBean.getContentType() > 2) {
                                    mediaInfo.setAudioBookInfoBean(AudioBookMineChapterItemBean.createFrom(musicInfoBean));
                                }
                                mediaInfo.setMediaType(MediaPlayerEnum.MediaType.Music);
                                mediaInfo.setGroupId(j);
                                mediaInfo.setMediaGroupType(j2);
                                mediaInfo.setMusicInfoBean(musicInfoBean);
                                arrayList.add(mediaInfo);
                            }
                        }
                        if (hotMusicList == null || getMusicListResponseBody == null || getMusicListResponseBody.data == null || getMusicListResponseBody.data.getMusicList() == null || getMusicListResponseBody.data.getMusicList().size() != 100) {
                            break;
                        }
                    } catch (IOException unused) {
                    }
                } while (hotMusicList.isSuccessful());
                MediaPlayerManager.mainThreadExecutor.execute(new Runnable() { // from class: com.wanos.wanosplayermodule.MediaPlayerManager.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (MediaPlayerManager.this.mCurMediaInfo != null && MediaPlayerManager.this.loadingMediaType == MediaPlayerManager.this.mCurMediaInfo.getMediaType() && MediaPlayerManager.this.loadingMediaGroupId == MediaPlayerManager.this.mCurMediaInfo.getGroupId() && MediaPlayerManager.this.loadingMediaGroupType == MediaPlayerManager.this.mCurMediaInfo.getMediaGroupType()) {
                            if (MediaPlayerManager.this.mCurMediaInfo.getGroupId() == -4 && arrayList.size() == 0) {
                                arrayList.add(MediaPlayerManager.this.mCurMediaInfo);
                            }
                            if (arrayList.size() > 0) {
                                MediaPlayerManager.this.updatePlayList(arrayList);
                                if (requestCallbackListener != null) {
                                    requestCallbackListener.onLoadListDealComplete(MediaPlayerManager.this.loadCompleteDataPlayDeal);
                                }
                            } else if (requestCallbackListener != null) {
                                requestCallbackListener.onLoadListFailed();
                            }
                            MediaPlayerManager.this.loadingMediaType = MediaPlayerEnum.MediaType.Unknown;
                            MediaPlayerManager.this.loadingMediaGroupId = -100L;
                            MediaPlayerManager.this.loadingMediaGroupType = -100L;
                        }
                    }
                });
            }
        });
    }

    private void requestMusicHistoryList(final RequestCallbackListener requestCallbackListener) {
        if (this.extendOperaData != null) {
            if (requestCallbackListener != null) {
                requestCallbackListener.onLoadListDealStarted();
            }
            this.loadingMediaGroupId = -5L;
            this.loadingMediaType = MediaPlayerEnum.MediaType.Music;
            this.extendOperaData.requestMeidaInfoList(MediaPlayerEnum.MediaType.Music, new MediaInfoCallBack() { // from class: com.wanos.wanosplayermodule.MediaPlayerManager.2
                @Override // com.wanos.wanosplayermodule.data.MediaInfoCallBack
                public void mediaInfoUpdateSuccessful() {
                }

                @Override // com.wanos.wanosplayermodule.data.MediaInfoCallBack
                public void returnQueryMediaInfoList(List<MediaInfo> list) {
                    MediaPlayerManager.this.updatePlayList(list);
                    RequestCallbackListener requestCallbackListener2 = requestCallbackListener;
                    if (requestCallbackListener2 != null) {
                        requestCallbackListener2.onLoadListDealComplete(MediaPlayerManager.this.loadCompleteDataPlayDeal);
                    }
                    MediaPlayerManager.this.loadingMediaType = MediaPlayerEnum.MediaType.Unknown;
                    MediaPlayerManager.this.loadingMediaGroupId = -100L;
                }
            });
        }
    }

    private void requestByGroupIdGetMusicList(final long j, final RequestCallbackListener requestCallbackListener) {
        singleThreadExecutorService.execute(new Runnable() { // from class: com.wanos.wanosplayermodule.MediaPlayerManager.3
            @Override // java.lang.Runnable
            public void run() {
                Response<GetMusicGroupDetailResponse> musicGroupDetail;
                final ArrayList arrayList = new ArrayList();
                int i = 0;
                int i2 = -1;
                GetMusicGroupDetailResponse getMusicGroupDetailResponseBody = null;
                do {
                    i++;
                    try {
                        musicGroupDetail = WanOSRetrofitUtil.getMusicGroupDetail(j, i, 100);
                        if (musicGroupDetail.isSuccessful() && (getMusicGroupDetailResponseBody = musicGroupDetail.body()) != null && getMusicGroupDetailResponseBody.data != null && getMusicGroupDetailResponseBody.data.getMusicGroupInfo() != null && getMusicGroupDetailResponseBody.data.getMusicGroupInfo().getMusicList() != null) {
                            for (MusicInfoBean musicInfoBean : getMusicGroupDetailResponseBody.data.getMusicGroupInfo().getMusicList()) {
                                i2++;
                                musicInfoBean.setIndex(i2);
                                musicInfoBean.setPageSize(100);
                                MediaInfo mediaInfo = new MediaInfo();
                                mediaInfo.setMediaType(MediaPlayerEnum.MediaType.Music);
                                mediaInfo.setGroupId(j);
                                mediaInfo.setMediaGroupType(-6L);
                                mediaInfo.setMusicInfoBean(musicInfoBean);
                                arrayList.add(mediaInfo);
                            }
                        }
                        if (musicGroupDetail == null || getMusicGroupDetailResponseBody == null || getMusicGroupDetailResponseBody.data == null || getMusicGroupDetailResponseBody.data.getMusicGroupInfo() == null || getMusicGroupDetailResponseBody.data.getMusicGroupInfo().getMusicList() == null || getMusicGroupDetailResponseBody.data.getMusicGroupInfo().getMusicList().size() != 100) {
                            break;
                        }
                    } catch (IOException unused) {
                    }
                } while (musicGroupDetail.isSuccessful());
                MediaPlayerManager.mainThreadExecutor.execute(new Runnable() { // from class: com.wanos.wanosplayermodule.MediaPlayerManager.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (MediaPlayerManager.this.mCurMediaInfo != null && MediaPlayerManager.this.loadingMediaType == MediaPlayerManager.this.mCurMediaInfo.getMediaType() && MediaPlayerManager.this.loadingMediaGroupId == MediaPlayerManager.this.mCurMediaInfo.getGroupId()) {
                            if (arrayList.size() > 0) {
                                MediaPlayerManager.this.updatePlayList(arrayList);
                                if (requestCallbackListener != null) {
                                    requestCallbackListener.onLoadListDealComplete(MediaPlayerManager.this.loadCompleteDataPlayDeal);
                                }
                            } else if (requestCallbackListener != null) {
                                requestCallbackListener.onLoadListFailed();
                            }
                            MediaPlayerManager.this.loadingMediaType = MediaPlayerEnum.MediaType.Unknown;
                            MediaPlayerManager.this.loadingMediaGroupId = -100L;
                        }
                    }
                });
            }
        });
    }

    private void requestByModuleGroupIdGetMusicList(final long j, final RequestCallbackListener requestCallbackListener) {
        singleThreadExecutorService.execute(new Runnable() { // from class: com.wanos.wanosplayermodule.MediaPlayerManager.4
            @Override // java.lang.Runnable
            public void run() {
                Response<GetMusicListResponse> moduleMusicListByGroupId;
                final ArrayList arrayList = new ArrayList();
                int i = 0;
                int i2 = -1;
                GetMusicListResponse getMusicListResponseBody = null;
                do {
                    i++;
                    try {
                        moduleMusicListByGroupId = WanOSRetrofitUtil.getModuleMusicListByGroupId(j, i, 100);
                        if (moduleMusicListByGroupId.isSuccessful() && (getMusicListResponseBody = moduleMusicListByGroupId.body()) != null && getMusicListResponseBody.data != null && getMusicListResponseBody.data.getMusicList() != null) {
                            for (MusicInfoBean musicInfoBean : getMusicListResponseBody.data.getMusicList()) {
                                i2++;
                                musicInfoBean.setIndex(i2);
                                musicInfoBean.setPageSize(100);
                                MediaInfo mediaInfo = new MediaInfo();
                                mediaInfo.setMediaType(MediaPlayerEnum.MediaType.Music);
                                mediaInfo.setGroupId(j);
                                mediaInfo.setMediaGroupType(-7L);
                                mediaInfo.setMusicInfoBean(musicInfoBean);
                                arrayList.add(mediaInfo);
                            }
                        }
                        if (moduleMusicListByGroupId == null || getMusicListResponseBody == null || getMusicListResponseBody.data == null || getMusicListResponseBody.data.getMusicList() == null || getMusicListResponseBody.data.getMusicList().size() != 100) {
                            break;
                        }
                    } catch (IOException unused) {
                    }
                } while (moduleMusicListByGroupId.isSuccessful());
                MediaPlayerManager.mainThreadExecutor.execute(new Runnable() { // from class: com.wanos.wanosplayermodule.MediaPlayerManager.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (MediaPlayerManager.this.mCurMediaInfo != null && MediaPlayerManager.this.loadingMediaType == MediaPlayerManager.this.mCurMediaInfo.getMediaType() && MediaPlayerManager.this.loadingMediaGroupId == MediaPlayerManager.this.mCurMediaInfo.getGroupId()) {
                            if (arrayList.size() > 0) {
                                MediaPlayerManager.this.updatePlayList(arrayList);
                                if (requestCallbackListener != null) {
                                    requestCallbackListener.onLoadListDealComplete(MediaPlayerManager.this.loadCompleteDataPlayDeal);
                                }
                            } else if (requestCallbackListener != null) {
                                requestCallbackListener.onLoadListFailed();
                            }
                            MediaPlayerManager.this.loadingMediaType = MediaPlayerEnum.MediaType.Unknown;
                            MediaPlayerManager.this.loadingMediaGroupId = -100L;
                        }
                    }
                });
            }
        });
    }

    public void requestByGroupIdGetAudioBookChapterList(final long j, final RequestCallbackListener requestCallbackListener) {
        Log.i(TAG, "requestByGroupIdGetAudioBookChapterList: groupId = " + j);
        singleThreadExecutorService.execute(new Runnable() { // from class: com.wanos.wanosplayermodule.MediaPlayerManager.5
            @Override // java.lang.Runnable
            public void run() {
                Response<GetAudioBookMineChapterListResponse> audioBookChapterList;
                final ArrayList arrayList = new ArrayList();
                try {
                    AudioBookSpeakerBean speaker = MediaPlayerManager.this.getCurMediaInfo().getAudioBookInfoBean().getSpeaker();
                    int i = 0;
                    int i2 = -1;
                    GetAudioBookMineChapterListResponse getAudioBookMineChapterListResponseBody = null;
                    do {
                        i++;
                        audioBookChapterList = WanOSRetrofitUtil.getAudioBookChapterList(i, 100, MediaPlayerManager.this.getCurMediaInfo().getGroupId());
                        if (audioBookChapterList != null && audioBookChapterList.isSuccessful() && (getAudioBookMineChapterListResponseBody = audioBookChapterList.body()) != null && getAudioBookMineChapterListResponseBody.data != null && getAudioBookMineChapterListResponseBody.data.list != null) {
                            for (AudioBookMineChapterItemBean audioBookMineChapterItemBean : getAudioBookMineChapterListResponseBody.data.list) {
                                i2++;
                                audioBookMineChapterItemBean.setIndex(i2);
                                audioBookMineChapterItemBean.setPageSize(100);
                                audioBookMineChapterItemBean.setSpeaker(speaker);
                                MediaInfo mediaInfo = new MediaInfo();
                                mediaInfo.setMediaType(MediaPlayerEnum.MediaType.Audiobook);
                                mediaInfo.setGroupId(j);
                                mediaInfo.setAudioBookInfoBean(audioBookMineChapterItemBean);
                                arrayList.add(mediaInfo);
                            }
                        }
                        if (audioBookChapterList == null || getAudioBookMineChapterListResponseBody == null || getAudioBookMineChapterListResponseBody.data == null || getAudioBookMineChapterListResponseBody.data.list == null || getAudioBookMineChapterListResponseBody.data.list.size() != 100) {
                            break;
                        }
                    } while (audioBookChapterList.isSuccessful());
                } catch (IOException unused) {
                }
                MediaPlayerManager.mainThreadExecutor.execute(new Runnable() { // from class: com.wanos.wanosplayermodule.MediaPlayerManager.5.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (MediaPlayerManager.this.mCurMediaInfo != null && MediaPlayerManager.this.loadingMediaType == MediaPlayerManager.this.mCurMediaInfo.getMediaType() && MediaPlayerManager.this.loadingMediaGroupId == MediaPlayerManager.this.mCurMediaInfo.getGroupId()) {
                            if (arrayList.size() > 0) {
                                MediaPlayerManager.this.updatePlayList(arrayList);
                                if (requestCallbackListener != null) {
                                    requestCallbackListener.onLoadListDealComplete(MediaPlayerManager.this.loadCompleteDataPlayDeal);
                                }
                            } else if (requestCallbackListener != null) {
                                requestCallbackListener.onLoadListFailed();
                            }
                            MediaPlayerManager.this.loadingMediaType = MediaPlayerEnum.MediaType.Unknown;
                            MediaPlayerManager.this.loadingMediaGroupId = -100L;
                            MediaPlayerManager.this.loadingMediaGroupType = -100L;
                        }
                    }
                });
            }
        });
    }

    public void updatePlayHistory(MediaInfo mediaInfo) {
        ExtendOperaData extendOperaData;
        if (mediaInfo == null || (extendOperaData = this.extendOperaData) == null) {
            return;
        }
        extendOperaData.updateMeidaInfo(mediaInfo, new MediaInfoCallBack() { // from class: com.wanos.wanosplayermodule.MediaPlayerManager.6
            @Override // com.wanos.wanosplayermodule.data.MediaInfoCallBack
            public void mediaInfoUpdateSuccessful() {
            }

            @Override // com.wanos.wanosplayermodule.data.MediaInfoCallBack
            public void returnQueryMediaInfoList(List<MediaInfo> list) {
            }
        });
    }

    public void updateMusicPlayListCollectStatus(final RequestCallbackListener requestCallbackListener) {
        MediaInfo mediaInfo;
        List<MediaInfo> list;
        if (this.loadingMediaType != MediaPlayerEnum.MediaType.Unknown || (mediaInfo = this.mCurMediaInfo) == null || mediaInfo.getMediaType() != MediaPlayerEnum.MediaType.Music || (list = this.mPlayList) == null || list.size() <= 0) {
            return;
        }
        int size = this.mPlayList.size();
        long[] jArr = new long[size];
        for (int i = 0; i < this.mPlayList.size(); i++) {
            MediaInfo mediaInfo2 = this.mPlayList.get(i);
            if (mediaInfo2 != null && mediaInfo2.getMediaType() == MediaPlayerEnum.MediaType.Music && mediaInfo2.getMusicInfoBean() != null) {
                jArr[i] = mediaInfo2.getMusicInfoBean().getMusicId();
            }
        }
        if (size > 0) {
            WanOSRetrofitUtil.getMusicCollectStatusByIds(jArr, new ResponseCallBack<MusicCollectStatuListResponse>(null) { // from class: com.wanos.wanosplayermodule.MediaPlayerManager.7
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int i2, String str) {
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(MusicCollectStatuListResponse musicCollectStatuListResponse) {
                    boolean z;
                    boolean zIsCollect;
                    RequestCallbackListener requestCallbackListener2;
                    MusicCollectStatuListBean musicCollectStatuListBean = musicCollectStatuListResponse.data;
                    int i2 = 0;
                    if (musicCollectStatuListBean == null || MediaPlayerManager.this.mPlayList == null || MediaPlayerManager.this.mPlayList.size() <= 0) {
                        z = false;
                        zIsCollect = false;
                    } else {
                        MediaInfo mediaInfo3 = (MediaInfo) MediaPlayerManager.this.mPlayList.get(0);
                        if (MediaPlayerManager.this.mCurMediaInfo == null || MediaPlayerManager.this.mCurMediaInfo.getMediaType() != MediaPlayerEnum.MediaType.Music || mediaInfo3 == null || mediaInfo3.getMediaType() != MediaPlayerEnum.MediaType.Music || MediaPlayerManager.this.mCurMediaInfo.getMediaGroupType() != mediaInfo3.getMediaGroupType() || MediaPlayerManager.this.mCurMediaInfo.getGroupId() != mediaInfo3.getGroupId()) {
                            return;
                        }
                        List<MusicCollectStatuBean> list2 = musicCollectStatuListBean.getList();
                        int i3 = 0;
                        z = false;
                        zIsCollect = false;
                        while (i2 < MediaPlayerManager.this.mPlayList.size()) {
                            MediaInfo mediaInfo4 = (MediaInfo) MediaPlayerManager.this.mPlayList.get(i2);
                            if (mediaInfo4 != null) {
                                for (MusicCollectStatuBean musicCollectStatuBean : list2) {
                                    if (musicCollectStatuBean != null && mediaInfo4.getMusicInfoBean() != null && musicCollectStatuBean.getMediaId() == mediaInfo4.getMusicInfoBean().getMusicId()) {
                                        MusicInfoBean musicInfoBean = mediaInfo4.getMusicInfoBean();
                                        if (musicInfoBean.isCollection() != musicCollectStatuBean.isCollect()) {
                                            musicInfoBean.setCollection(musicCollectStatuBean.isCollect());
                                            mediaInfo4.setMusicInfoBean(musicInfoBean);
                                            MediaPlayerManager.this.mPlayList.set(i2, mediaInfo4);
                                            i3 = 1;
                                        }
                                    }
                                    if (musicCollectStatuBean != null && MediaPlayerManager.this.mCurMediaInfo.getMusicInfoBean() != null && musicCollectStatuBean.getMediaId() == MediaPlayerManager.this.mCurMediaInfo.getMusicInfoBean().getMusicId()) {
                                        MusicInfoBean musicInfoBean2 = MediaPlayerManager.this.mCurMediaInfo.getMusicInfoBean();
                                        if (musicInfoBean2.isCollection() != musicCollectStatuBean.isCollect()) {
                                            zIsCollect = musicCollectStatuBean.isCollect();
                                            musicInfoBean2.setCollection(musicCollectStatuBean.isCollect());
                                            MediaPlayerManager.this.mCurMediaInfo.setMusicInfoBean(musicInfoBean2);
                                            z = true;
                                        }
                                    }
                                }
                            }
                            i2++;
                        }
                        i2 = i3;
                    }
                    if (i2 == 0 || (requestCallbackListener2 = requestCallbackListener) == null) {
                        return;
                    }
                    requestCallbackListener2.onUpdateListCollect();
                    if (z) {
                        requestCallbackListener.collectRequestComplete(zIsCollect, MediaPlayerManager.this.mCurMediaInfo);
                    }
                }
            });
        }
    }

    public void collectOrCannelRequest(final MediaInfo mediaInfo, final boolean z, int i, final RequestCallbackListener requestCallbackListener) {
        if (mediaInfo != null) {
            MediaPlayerEnum.MediaType mediaType = mediaInfo.getMediaType();
            if (mediaType == MediaPlayerEnum.MediaType.Music && mediaInfo.getMusicInfoBean().getContentType() <= 2) {
                if (mediaInfo == null || mediaInfo.getMusicInfoBean() == null) {
                    return;
                }
                Context context = null;
                if (z) {
                    WanOSRetrofitUtil.musicCollect(mediaInfo.getMusicInfoBean().getMusicId(), i, new ResponseCallBack<BaseResponse>(context) { // from class: com.wanos.wanosplayermodule.MediaPlayerManager.8
                        @Override // com.wanos.WanosCommunication.ResponseCallBack
                        public void onResponseSuccessful(BaseResponse baseResponse) {
                            MusicInfoBean musicInfoBean = mediaInfo.getMusicInfoBean();
                            musicInfoBean.setCollection(true);
                            mediaInfo.setMusicInfoBean(musicInfoBean);
                            requestCallbackListener.collectRequestComplete(true, mediaInfo);
                        }

                        @Override // com.wanos.WanosCommunication.ResponseCallBack
                        public void onResponseFailure(int i2, String str) {
                            Log.i(MediaPlayerManager.TAG, "onResponseFailure: code = " + i2);
                        }
                    });
                    return;
                } else {
                    WanOSRetrofitUtil.musicCollectCancel(mediaInfo.getMusicInfoBean().getMusicId(), i, new ResponseCallBack<BaseResponse>(context) { // from class: com.wanos.wanosplayermodule.MediaPlayerManager.9
                        @Override // com.wanos.WanosCommunication.ResponseCallBack
                        public void onResponseSuccessful(BaseResponse baseResponse) {
                            MusicInfoBean musicInfoBean = mediaInfo.getMusicInfoBean();
                            musicInfoBean.setCollection(false);
                            mediaInfo.setMusicInfoBean(musicInfoBean);
                            requestCallbackListener.collectRequestComplete(false, mediaInfo);
                        }

                        @Override // com.wanos.WanosCommunication.ResponseCallBack
                        public void onResponseFailure(int i2, String str) {
                            Log.i(MediaPlayerManager.TAG, "onResponseFailure: " + i2);
                        }
                    });
                    return;
                }
            }
            if ((mediaType != MediaPlayerEnum.MediaType.Audiobook && mediaInfo.getMusicInfoBean().getContentType() <= 2) || mediaInfo == null || mediaInfo.getAudioBookInfoBean() == null) {
                return;
            }
            WanOSRetrofitUtil.audioBookLikeChapter(mediaInfo.getAudioBookInfoBean().getId(), z ? 1 : 0, new ResponseCallBack<AudioBookLikeChapterResponse>(null) { // from class: com.wanos.wanosplayermodule.MediaPlayerManager.10
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(AudioBookLikeChapterResponse audioBookLikeChapterResponse) {
                    AudioBookMineChapterItemBean audioBookInfoBean = mediaInfo.getAudioBookInfoBean();
                    audioBookInfoBean.setIsCollect(z ? 1 : 0);
                    mediaInfo.setAudioBookInfoBean(audioBookInfoBean);
                    requestCallbackListener.collectRequestComplete(z, mediaInfo);
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int i2, String str) {
                    Log.i(MediaPlayerManager.TAG, "onResponseFailure: code = " + i2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePlayList(List<MediaInfo> list) {
        List<MediaInfo> list2;
        if (list == null || (list2 = this.mPlayList) == null || !ListComparator.compare(list2, list)) {
            List<MediaInfo> list3 = this.mPlayList;
            if (list3 != null) {
                list3.clear();
            } else {
                this.mPlayList = new ArrayList();
            }
            List<MediaInfo> list4 = this.mMediaRandomList;
            if (list4 != null) {
                list4.clear();
            } else {
                this.mMediaRandomList = new ArrayList();
            }
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    MediaInfo mediaInfo = list.get(i);
                    MediaInfo mediaInfo2 = this.mCurMediaInfo;
                    if (mediaInfo2 != null) {
                        if (mediaInfo2.getMediaType() == MediaPlayerEnum.MediaType.Music && this.mCurMediaInfo.getMusicInfoBean().getMusicId() == mediaInfo.getMusicInfoBean().getMusicId()) {
                            MusicInfoBean musicInfoBean = mediaInfo.getMusicInfoBean();
                            MusicInfoBean musicInfoBean2 = this.mCurMediaInfo.getMusicInfoBean();
                            if (musicInfoBean2 != null && musicInfoBean != null) {
                                musicInfoBean.setCollection(musicInfoBean2.isCollection());
                                mediaInfo.setMusicInfoBean(musicInfoBean);
                            }
                            updateCurrentIndex(i);
                        } else if (this.mCurMediaInfo.getMediaType() == MediaPlayerEnum.MediaType.Audiobook && this.mCurMediaInfo.getAudioBookInfoBean().getId() == mediaInfo.getAudioBookInfoBean().getId()) {
                            AudioBookMineChapterItemBean audioBookInfoBean = mediaInfo.getAudioBookInfoBean();
                            AudioBookMineChapterItemBean audioBookInfoBean2 = this.mCurMediaInfo.getAudioBookInfoBean();
                            if (audioBookInfoBean != null && audioBookInfoBean2 != null) {
                                audioBookInfoBean.setIsCollect(audioBookInfoBean2.getIsCollect());
                                mediaInfo.setAudioBookInfoBean(audioBookInfoBean);
                            }
                            updateCurrentIndex(i);
                        }
                    }
                    this.mPlayList.add(mediaInfo);
                    this.mMediaRandomList.add(mediaInfo);
                }
                updateRandomList(this.mMediaRandomList);
                return;
            }
            this.mCurrentMediaIndex = -1;
        }
    }

    public int getMediaInfoIndex(MediaInfo mediaInfo) {
        List<MediaInfo> list;
        if (mediaInfo == null || (list = this.mPlayList) == null || list.size() <= 0) {
            return -1;
        }
        for (int i = 0; i < this.mPlayList.size(); i++) {
            MediaInfo mediaInfo2 = this.mPlayList.get(i);
            if (mediaInfo2 != null && mediaInfo2.getMediaType() == mediaInfo.getMediaType()) {
                if (mediaInfo.getMediaType() == MediaPlayerEnum.MediaType.Music) {
                    MusicInfoBean musicInfoBean = mediaInfo2.getMusicInfoBean();
                    MusicInfoBean musicInfoBean2 = mediaInfo.getMusicInfoBean();
                    if (musicInfoBean != null && musicInfoBean2 != null && musicInfoBean.getMusicId() == musicInfoBean2.getMusicId()) {
                        return i;
                    }
                } else if (mediaInfo.getMediaType() == MediaPlayerEnum.MediaType.Audiobook) {
                    AudioBookMineChapterItemBean audioBookInfoBean = mediaInfo2.getAudioBookInfoBean();
                    AudioBookMineChapterItemBean audioBookInfoBean2 = mediaInfo.getAudioBookInfoBean();
                    if (audioBookInfoBean != null && audioBookInfoBean2 != null && audioBookInfoBean.getId() == audioBookInfoBean2.getId()) {
                        return i;
                    }
                } else {
                    continue;
                }
            }
        }
        return -1;
    }

    public void updateCurrentIndex(int i) {
        this.mCurrentMediaIndex = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0054 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x005a A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.wanos.commonlibrary.bean.MediaInfo updatePlayListCollectStatus(com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum.MediaType r10, long r11, boolean r13) {
        /*
            r9 = this;
            java.util.List<com.wanos.commonlibrary.bean.MediaInfo> r0 = r9.mPlayList
            if (r0 == 0) goto L5d
            int r0 = r0.size()
            if (r0 <= 0) goto L5d
            r0 = 0
            r1 = r0
        Lc:
            java.util.List<com.wanos.commonlibrary.bean.MediaInfo> r2 = r9.mPlayList
            int r2 = r2.size()
            if (r0 >= r2) goto L5d
            java.util.List<com.wanos.commonlibrary.bean.MediaInfo> r2 = r9.mPlayList
            java.lang.Object r2 = r2.get(r0)
            com.wanos.commonlibrary.bean.MediaInfo r2 = (com.wanos.commonlibrary.bean.MediaInfo) r2
            if (r2 == 0) goto L5a
            com.wanos.commonlibrary.bean.MusicInfoBean r3 = r2.getMusicInfoBean()
            com.wanos.commonlibrary.bean.AudioBookMineChapterItemBean r4 = r2.getAudioBookInfoBean()
            com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum$MediaType r5 = com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum.MediaType.Music
            r6 = 1
            if (r10 != r5) goto L3d
            if (r3 == 0) goto L3d
            long r7 = r3.getMusicId()
            int r5 = (r11 > r7 ? 1 : (r11 == r7 ? 0 : -1))
            if (r5 != 0) goto L3d
            r3.setCollection(r13)
            r2.setMusicInfoBean(r3)
        L3b:
            r1 = r6
            goto L52
        L3d:
            com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum$MediaType r3 = com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum.MediaType.Audiobook
            if (r10 != r3) goto L52
            if (r4 == 0) goto L52
            long r7 = r4.getId()
            int r3 = (r11 > r7 ? 1 : (r11 == r7 ? 0 : -1))
            if (r3 != 0) goto L52
            r4.setIsCollect(r13)
            r2.setAudioBookInfoBean(r4)
            goto L3b
        L52:
            if (r1 == 0) goto L5a
            java.util.List<com.wanos.commonlibrary.bean.MediaInfo> r10 = r9.mPlayList
            r10.set(r0, r2)
            return r2
        L5a:
            int r0 = r0 + 1
            goto Lc
        L5d:
            r10 = 0
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wanos.wanosplayermodule.MediaPlayerManager.updatePlayListCollectStatus(com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum$MediaType, long, boolean):com.wanos.commonlibrary.bean.MediaInfo");
    }

    public boolean updateCurMediaCollectStatus(MediaPlayerEnum.MediaType mediaType, long j, boolean z) {
        MediaInfo mediaInfo = this.mCurMediaInfo;
        if (mediaInfo != null) {
            MusicInfoBean musicInfoBean = mediaInfo.getMusicInfoBean();
            AudioBookMineChapterItemBean audioBookInfoBean = this.mCurMediaInfo.getAudioBookInfoBean();
            if (mediaType == MediaPlayerEnum.MediaType.Music && musicInfoBean != null && j == musicInfoBean.getMusicId()) {
                musicInfoBean.setCollection(z);
                this.mCurMediaInfo.setMusicInfoBean(musicInfoBean);
                return true;
            }
            if (mediaType == MediaPlayerEnum.MediaType.Audiobook && audioBookInfoBean != null && j == audioBookInfoBean.getId()) {
                audioBookInfoBean.setIsCollect(z ? 1 : 0);
                this.mCurMediaInfo.setAudioBookInfoBean(audioBookInfoBean);
                return true;
            }
        }
        return false;
    }

    private void updateRandomList(List<MediaInfo> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        Random random = new Random();
        for (int size = list.size() - 1; size > 0; size--) {
            int iNextInt = random.nextInt(size + 1);
            MediaInfo mediaInfo = list.get(size);
            list.set(size, list.get(iNextInt));
            list.set(iNextInt, mediaInfo);
        }
    }

    public List<MediaInfo> getMediaPlayList() {
        return this.mPlayList;
    }

    public List<MediaInfo> getMediaRandomList() {
        return this.mMediaRandomList;
    }

    public MediaInfo getCurMediaInfo() {
        return this.mCurMediaInfo;
    }

    public void setCurMediaInfo(MediaInfo mediaInfo) {
        this.mCurMediaInfo = mediaInfo;
    }

    public int getCurrentMediaIndex() {
        return this.mCurrentMediaIndex;
    }

    public void setTotalMediaNum(int i) {
        this.mTotalMediaNum = i;
    }

    public int getTotalMediaNum() {
        return this.mTotalMediaNum;
    }
}
