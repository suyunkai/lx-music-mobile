package com.wanos.media.view;

import com.wanos.WanosCommunication.bean.AudioBookAlbumInfoBean;
import com.wanos.WanosCommunication.bean.BannerBean;
import com.wanos.WanosCommunication.bean.ModuleCoverDetailsBean;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface AudioBookHomeView extends IBaseView {
    void updateBanner(List<BannerBean> bannerBeanList);

    default void updateChildrenCover(ModuleCoverDetailsBean childrenBean) {
    }

    default void updateMyAudioBookCover(ModuleCoverDetailsBean myAudioBookBean) {
    }

    void updateView(List<AudioBookAlbumInfoBean> beanList);
}
