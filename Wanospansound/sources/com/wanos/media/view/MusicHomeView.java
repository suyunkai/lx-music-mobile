package com.wanos.media.view;

import com.wanos.WanosCommunication.bean.BannerBean;
import com.wanos.WanosCommunication.bean.ModuleCoverDetailsBean;
import com.wanos.WanosCommunication.bean.MusicGroupInfo;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface MusicHomeView extends IBaseView {
    void updateBanner(List<BannerBean> bannerBeanList);

    void updateFreeGroupCover(ModuleCoverDetailsBean freeGroupInfo);

    void updateHotMusicView(MusicInfoBean musicInfoBean);

    void updateMyMusicCover(ModuleCoverDetailsBean moduleCoverDetailsBean);

    void updateMyMusicView(MusicInfoBean musicInfoBean);

    void updateRankingMusicView(List<MusicInfoBean> mediaMusicBeanList);

    void updateRecommedMusicGroupView(List<MusicGroupInfo> mediaMusicGroupBeanList);
}
