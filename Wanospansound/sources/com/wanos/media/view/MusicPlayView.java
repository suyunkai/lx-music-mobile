package com.wanos.media.view;

import com.wanos.commonlibrary.bean.MusicInfoBean;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface MusicPlayView extends IBaseView {
    void hideListLoading();

    void showListFailOrError();

    void showListLoading();

    default void updateCollectFailer() {
    }

    void updateCollectStatus(long musicId, boolean isCollect);

    void updateMusicListView(long groupId, long groupType, List<MusicInfoBean> mediaMusicBeanList);

    void updateMusicView(MusicInfoBean musicInfoBean);
}
