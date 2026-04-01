package com.wanos.media.view;

import com.wanos.WanosCommunication.bean.AudioBookAlbumInfoBean;
import com.wanos.commonlibrary.bean.AudioBookChapterItemBean;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface AudioBookAlbumInfoView extends IBaseView {
    void updateAlbumView(AudioBookAlbumInfoBean bean);

    void updateChapterView(List<AudioBookChapterItemBean> list, boolean isLoadMore);
}
