package com.wanos.media.view;

import com.wanos.WanosCommunication.bean.AudioBookAlbumInfoBean;
import com.wanos.commonlibrary.bean.AudioBookMineChapterItemBean;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface AudioBookMineView extends IBaseView {
    void initData();

    void initListener();

    void initView();

    void updateAlbumView(List<AudioBookAlbumInfoBean> list, boolean isLoadMore);

    void updateChapterView(List<AudioBookMineChapterItemBean> list, boolean isLoadMore);

    void updatePlayedView(List<AudioBookMineChapterItemBean> list, boolean isForce);
}
