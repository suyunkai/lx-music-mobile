package com.wanos.media.presenter;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.WanosCommunication.bean.AudioBookAlbumInfoBean;
import com.wanos.WanosCommunication.response.GetAudioBookChapterListResponse;
import com.wanos.WanosCommunication.response.GetAudioBookRadioDetailResponse;
import com.wanos.commonlibrary.bean.AudioBookChapterItemBean;
import com.wanos.media.cacheData.audiobook.AudioBookAlbumsCache;
import com.wanos.media.cacheData.audiobook.AudioBookChaptersCache;
import com.wanos.media.view.AudioBookAlbumInfoView;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class AudioBookAlbumPresenter extends BasePresenter<AudioBookAlbumInfoView> {
    public static final String TAG = "wanos:[AudioBookAlbumPresenter]";
    public static int chapterCountOfPage = 20;
    private final Context mContext;

    public AudioBookAlbumPresenter(Context context, AudioBookAlbumInfoView view) {
        this.mContext = context;
        this.mView = view;
    }

    public void requestData(long albumId, boolean isForce) {
        Log.i(TAG, "requestData: ->albumId");
        if (this.mView != 0) {
            AudioBookAlbumInfoBean albumBean = AudioBookAlbumsCache.getInstance().getAlbumBean(albumId);
            if (!isForce && albumBean != null) {
                Log.i(TAG, "requestData: !isForce && bean!=null");
                ((AudioBookAlbumInfoView) this.mView).updateAlbumView(albumBean);
            } else {
                Log.i(TAG, "requestData: isForce || bean==null");
                WanOSRetrofitUtil.getAudioBookAlbumDetail(albumId, new ResponseCallBack<GetAudioBookRadioDetailResponse>(this.mContext) { // from class: com.wanos.media.presenter.AudioBookAlbumPresenter.1
                    @Override // com.wanos.WanosCommunication.ResponseCallBack
                    public void onResponseFailure(int code, String msg) {
                    }

                    @Override // com.wanos.WanosCommunication.ResponseCallBack
                    public void onResponseSuccessful(GetAudioBookRadioDetailResponse response) {
                        if (response.data != null) {
                            ((AudioBookAlbumInfoView) AudioBookAlbumPresenter.this.mView).updateAlbumView(response.data);
                        }
                    }
                });
            }
        }
    }

    public void requestChapterList(final long albumId, final int page, final boolean isCheckLoadMore, final IABCallBack callBack) {
        List<AudioBookChapterItemBean> list = AudioBookChaptersCache.getInstance().getList(albumId, page);
        if (list != null) {
            Log.i(TAG, "requestChapterList: ->list!=null");
            ((AudioBookAlbumInfoView) this.mView).updateChapterView(list, false);
            if (callBack != null) {
                new Handler().postDelayed(new Runnable() { // from class: com.wanos.media.presenter.AudioBookAlbumPresenter.2
                    @Override // java.lang.Runnable
                    public void run() {
                        callBack.requestFinish(0, true);
                    }
                }, 30L);
                return;
            }
            return;
        }
        Log.i(TAG, "requestChapterList: ->list==null");
        WanOSRetrofitUtil.getAudioBookChapterListV1(page, chapterCountOfPage, albumId, new ResponseCallBack<GetAudioBookChapterListResponse>(this.mContext) { // from class: com.wanos.media.presenter.AudioBookAlbumPresenter.3
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetAudioBookChapterListResponse response) {
                if (response.data != null && response.data.list != null) {
                    List<AudioBookChapterItemBean> listAddAll = AudioBookChaptersCache.getInstance().addAll(albumId, page, response.data.list, AudioBookAlbumPresenter.chapterCountOfPage);
                    ((AudioBookAlbumInfoView) AudioBookAlbumPresenter.this.mView).updateChapterView(listAddAll, !isCheckLoadMore || listAddAll.size() < response.data.total);
                    IABCallBack iABCallBack = callBack;
                    if (iABCallBack != null) {
                        iABCallBack.requestFinish(response.code, true);
                        return;
                    }
                    return;
                }
                IABCallBack iABCallBack2 = callBack;
                if (iABCallBack2 != null) {
                    iABCallBack2.requestFinish(response.code, false);
                }
            }

            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseFailure(int code, String msg) {
                IABCallBack iABCallBack = callBack;
                if (iABCallBack != null) {
                    iABCallBack.requestFinish(code, false);
                }
            }
        });
    }

    public int getPrePage(long albumId) {
        return AudioBookChaptersCache.getInstance().getPrePage(albumId);
    }

    public int getNextPage(long albumId) {
        return AudioBookChaptersCache.getInstance().getNextPage(albumId);
    }

    public void clearChapterData(long albumId) {
        AudioBookChaptersCache.getInstance().clearChapterData(albumId);
    }
}
