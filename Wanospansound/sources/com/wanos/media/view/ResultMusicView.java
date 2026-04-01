package com.wanos.media.view;

import com.wanos.commonlibrary.bean.MusicInfoBean;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface ResultMusicView extends IBaseView {
    void updateView(boolean isLoadMore, List<MusicInfoBean> list);
}
