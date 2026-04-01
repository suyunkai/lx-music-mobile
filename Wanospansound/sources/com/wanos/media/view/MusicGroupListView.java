package com.wanos.media.view;

import com.wanos.WanosCommunication.bean.MusicGroupInfo;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface MusicGroupListView extends IBaseView {
    void addDataView(int page, List<MusicGroupInfo> mediaMusicGroupBeanList);

    void updateView(int total, List<MusicGroupInfo> mediaMusicGroupBeanList);
}
