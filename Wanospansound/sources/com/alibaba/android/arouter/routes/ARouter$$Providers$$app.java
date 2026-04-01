package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IProviderGroup;
import com.wanos.commonlibrary.router.ServiceRouter;
import com.wanos.media.db.MediaPlayServiceImpl;
import com.wanos.media.service.CommonServiceImpl;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ARouter$$Providers$$app implements IProviderGroup {
    @Override // com.alibaba.android.arouter.facade.template.IProviderGroup
    public void loadInto(Map<String, RouteMeta> providers) {
        providers.put("com.wanos.media.service.CommonServiceImpl", RouteMeta.build(RouteType.PROVIDER, CommonServiceImpl.class, ServiceRouter.COMMON_SERVICE, "app", null, -1, Integer.MIN_VALUE));
        providers.put("com.wanos.media.db.MediaPlayServiceImpl", RouteMeta.build(RouteType.PROVIDER, MediaPlayServiceImpl.class, ServiceRouter.MEDIA_PLAY_SERVICE, "app", null, -1, Integer.MIN_VALUE));
    }
}
