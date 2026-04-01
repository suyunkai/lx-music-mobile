package com.wanos.media.view;

import com.wanos.WanosCommunication.bean.FreeGroupInfo;
import com.wanos.WanosCommunication.bean.MusicGroupInfo;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface MusicListView extends IBaseView {
    void addDataView(int page, List<MusicInfoBean> mediaMusicBeanList);

    default void showFailOrError(int code, String msg) {
    }

    void updateCollectStatus(long musicGroupId, boolean isCollect);

    default void updateFreeCover(FreeGroupInfo info) {
    }

    void updateView(MusicGroupInfo mediaMusicGroupBean, List<MusicInfoBean> mediaMusicBeanList);
}
