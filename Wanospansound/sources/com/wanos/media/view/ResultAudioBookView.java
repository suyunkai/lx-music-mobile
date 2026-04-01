package com.wanos.media.view;

import com.wanos.WanosCommunication.bean.AudioBookAlbumInfoBean;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface ResultAudioBookView extends IBaseView {
    default void showFailOrError(int code, String msg) {
    }

    void updataView(boolean isRefresh, List<AudioBookAlbumInfoBean> list);
}
