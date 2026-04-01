package com.wanos.media.view;

import com.wanos.WanosCommunication.bean.MusicGroupInfo;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface ResultMusicGroupView extends IBaseView {
    default void showFailOrError(int code, String msg) {
    }

    void updataView(boolean isRefresh, List<MusicGroupInfo> list);
}
