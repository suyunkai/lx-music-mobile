package com.wanos.media.view;

import com.wanos.WanosCommunication.bean.AudioBookAlbumInfoBean;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface AudioBookAlbumListView extends IBaseView {
    void updateView(List<AudioBookAlbumInfoBean> beanList, boolean isLoadMore);
}
