package com.wanos.media.presenter;

import android.content.Context;
import android.util.ArrayMap;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.WanosCommunication.bean.AudioBookAlbumInfoBean;
import com.wanos.WanosCommunication.response.GetAudioBookRadioListResponse;
import com.wanos.media.cacheData.audiobook.AudioBookAlbumsCache;
import com.wanos.media.view.AudioBookAlbumListView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class AudioBookAlbumListPresenter extends BasePresenter<AudioBookAlbumListView> {
    public Map<Integer, List<AudioBookAlbumInfoBean>> albumListMap;
    private Context mContext;

    public AudioBookAlbumListPresenter(Context context, AudioBookAlbumListView view) {
        this.mView = view;
        this.albumListMap = new ArrayMap();
    }

    public void requestAlbumList(final int page, final int pageSize, final int tag, final IABCallBack callBack) {
        WanOSRetrofitUtil.getAudioBookAlbumList(page, pageSize, tag, new ResponseCallBack<GetAudioBookRadioListResponse>(this.mContext) { // from class: com.wanos.media.presenter.AudioBookAlbumListPresenter.1
            @Override // com.wanos.WanosCommunication.ResponseCallBack
            public void onResponseSuccessful(GetAudioBookRadioListResponse response) {
                List<AudioBookAlbumInfoBean> arrayList;
                if (response.data != null && response.data.list != null) {
                    AudioBookAlbumsCache.getInstance().addAll(response.data.list);
                    if (AudioBookAlbumListPresenter.this.albumListMap.containsKey(Integer.valueOf(tag))) {
                        arrayList = AudioBookAlbumListPresenter.this.albumListMap.get(Integer.valueOf(tag));
                        if (page == 1) {
                            arrayList.clear();
                        }
                    } else {
                        arrayList = new ArrayList<>();
                        AudioBookAlbumListPresenter.this.albumListMap.put(Integer.valueOf(tag), arrayList);
                    }
                    arrayList.addAll(response.data.list);
                    ((AudioBookAlbumListView) AudioBookAlbumListPresenter.this.mView).updateView(arrayList, response.data.list.size() >= pageSize);
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
