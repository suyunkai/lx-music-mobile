package com.wanos.media.presenter;

import android.content.Context;
import android.util.Log;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.WanosCommunication.bean.AudioBookAlbumInfoBean;
import com.wanos.WanosCommunication.response.GetAudioBookChapterListIsLikeResponse;
import com.wanos.WanosCommunication.response.GetAudioBookLikeChapterListResponse;
import com.wanos.WanosCommunication.response.GetAudioBookLikeRadioListResponse;
import com.wanos.commonlibrary.bean.AudioBookMineChapterItemBean;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.media.db.DbCallBack;
import com.wanos.media.db.WanosDbUtils;
import com.wanos.media.view.AudioBookMineView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class AudioBookMinePresenter extends BasePresenter<AudioBookMineView> {
    public static final String TAG = "wanos:[AudioBookMinePresenter]";
    private List<AudioBookAlbumInfoBean> followAlbumList;
    private List<AudioBookMineChapterItemBean> likeChapterList;
    private final Context mContext;

    public AudioBookMinePresenter(Context context, AudioBookMineView mineView) {
        this.mContext = context;
        this.mView = mineView;
    }

    public void requestLikeChapterData(final int page, final int pageSize, final IABCallBack callBack) {
        if (this.mView != 0) {
            WanOSRetrofitUtil.getAudioBookLikeChapterList(page, pageSize, new ResponseCallBack<GetAudioBookLikeChapterListResponse>(this.mContext) { // from class: com.wanos.media.presenter.AudioBookMinePresenter.1
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(GetAudioBookLikeChapterListResponse response) {
                    if (response.data != null && response.data.list != null) {
                        if (AudioBookMinePresenter.this.likeChapterList == null) {
                            AudioBookMinePresenter.this.likeChapterList = new ArrayList();
                        } else if (page == 1) {
                            AudioBookMinePresenter.this.likeChapterList.clear();
                        }
                        AudioBookMinePresenter.this.likeChapterList.addAll(response.data.list);
                        ((AudioBookMineView) AudioBookMinePresenter.this.mView).updateChapterView(AudioBookMinePresenter.this.likeChapterList, response.data.list.size() >= pageSize);
                        IABCallBack iABCallBack = callBack;
                        if (iABCallBack != null) {
                            iABCallBack.requestFinish(true);
                            return;
                        }
                        return;
                    }
                    IABCallBack iABCallBack2 = callBack;
                    if (iABCallBack2 != null) {
                        iABCallBack2.requestFinish(false);
                    }
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int code, String msg) {
                    IABCallBack iABCallBack = callBack;
                    if (iABCallBack != null) {
                        iABCallBack.requestFinish(false);
                    }
                }
            });
        }
    }

    public void requestPlayedData() {
        if (this.mView != 0) {
            WanosDbUtils.getAudioBookHistoryList(new DbCallBack<List<AudioBookMineChapterItemBean>>() { // from class: com.wanos.media.presenter.AudioBookMinePresenter.2
                @Override // com.wanos.media.db.DbCallBack
                public void callBackData(final List<AudioBookMineChapterItemBean> data) {
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < data.size(); i++) {
                        arrayList.add(Long.valueOf(data.get(i).getId()));
                    }
                    if (UserInfoUtil.isLogin()) {
                        WanOSRetrofitUtil.audioBookChapterListIsLike(arrayList, new ResponseCallBack<GetAudioBookChapterListIsLikeResponse>(AudioBookMinePresenter.this.mContext) { // from class: com.wanos.media.presenter.AudioBookMinePresenter.2.1
                            @Override // com.wanos.WanosCommunication.ResponseCallBack
                            public void onResponseSuccessful(GetAudioBookChapterListIsLikeResponse response) {
                                int i2;
                                if (response.data == null || response.data.list == null) {
                                    return;
                                }
                                for (int i3 = 0; i3 < data.size(); i3++) {
                                    AudioBookMineChapterItemBean audioBookMineChapterItemBean = (AudioBookMineChapterItemBean) data.get(i3);
                                    int i4 = 0;
                                    while (true) {
                                        if (i4 >= response.data.list.size()) {
                                            i2 = 0;
                                            break;
                                        } else {
                                            if (response.data.list.get(i4).chapterId == audioBookMineChapterItemBean.getId()) {
                                                i2 = response.data.list.get(i4).isCollect;
                                                break;
                                            }
                                            i4++;
                                        }
                                    }
                                    audioBookMineChapterItemBean.setIsCollect(i2);
                                }
                                ((AudioBookMineView) AudioBookMinePresenter.this.mView).updatePlayedView(data, false);
                            }

                            @Override // com.wanos.WanosCommunication.ResponseCallBack
                            public void onResponseFailure(int code, String msg) {
                                ((AudioBookMineView) AudioBookMinePresenter.this.mView).showFailOrError(msg);
                                Log.i(AudioBookMinePresenter.TAG, "onResponseFailure: ");
                            }
                        });
                    } else {
                        ((AudioBookMineView) AudioBookMinePresenter.this.mView).updatePlayedView(data, true);
                    }
                }
            });
        }
    }

    public void requestAlbumListData(final int page, final int pageSize, final IABCallBack callBack) {
        if (this.mView != 0) {
            WanOSRetrofitUtil.getAudioBookLikeAlbumList(page, pageSize, new ResponseCallBack<GetAudioBookLikeRadioListResponse>(this.mContext) { // from class: com.wanos.media.presenter.AudioBookMinePresenter.3
                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseSuccessful(GetAudioBookLikeRadioListResponse response) {
                    if (response.data != null && response.data.list != null) {
                        if (AudioBookMinePresenter.this.followAlbumList == null) {
                            AudioBookMinePresenter.this.followAlbumList = new ArrayList();
                        } else if (page == 1) {
                            AudioBookMinePresenter.this.followAlbumList.clear();
                        }
                        AudioBookMinePresenter.this.followAlbumList.addAll(response.data.list);
                        ((AudioBookMineView) AudioBookMinePresenter.this.mView).updateAlbumView(AudioBookMinePresenter.this.followAlbumList, response.data.list.size() >= pageSize);
                        IABCallBack iABCallBack = callBack;
                        if (iABCallBack != null) {
                            iABCallBack.requestFinish(true);
                            return;
                        }
                        return;
                    }
                    IABCallBack iABCallBack2 = callBack;
                    if (iABCallBack2 != null) {
                        iABCallBack2.requestFinish(false);
                    }
                }

                @Override // com.wanos.WanosCommunication.ResponseCallBack
                public void onResponseFailure(int code, String msg) {
                    IABCallBack iABCallBack = callBack;
                    if (iABCallBack != null) {
                        iABCallBack.requestFinish(false);
                    }
                }
            });
        }
    }
}
