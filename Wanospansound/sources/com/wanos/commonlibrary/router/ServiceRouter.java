package com.wanos.commonlibrary.router;

import com.alibaba.android.arouter.launcher.ARouter;
import com.wanos.commonlibrary.service.CommonService;
import com.wanos.commonlibrary.service.MediaPlayService;

/* JADX INFO: loaded from: classes3.dex */
public class ServiceRouter {
    public static final String COMMON_SERVICE = "/app/CommonService";
    public static final String CREATOR_SERVICE = "/carstudio/CreatorService";
    public static final String MEDIA_PLAY_SERVICE = "/app/MediaPlayService";
    public static final String RECOMMEND_SERVICE = "/JuyiHall/RecommendService";

    public static MediaPlayService getMediaPlayService() {
        return (MediaPlayService) ARouter.getInstance().build(MEDIA_PLAY_SERVICE).navigation();
    }

    public static CommonService getCommonService() {
        return (CommonService) ARouter.getInstance().build(COMMON_SERVICE).navigation();
    }
}
