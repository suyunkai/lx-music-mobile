package com.wanos.WanosCommunication.router;

import com.alibaba.android.arouter.launcher.ARouter;
import com.wanos.WanosCommunication.service.ICreatorService;
import com.wanos.WanosCommunication.service.RecommendService;
import com.wanos.commonlibrary.router.ServiceRouter;

/* JADX INFO: loaded from: classes3.dex */
public class HttpRouter {
    public static RecommendService getRecommendService() {
        return (RecommendService) ARouter.getInstance().build(ServiceRouter.RECOMMEND_SERVICE).navigation();
    }

    public static ICreatorService getCreatorService() {
        return (ICreatorService) ARouter.getInstance().build(ServiceRouter.CREATOR_SERVICE).navigation();
    }
}
