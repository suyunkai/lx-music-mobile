package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IProviderGroup;
import com.wanos.careditproject.service.CreatorService;
import com.wanos.commonlibrary.router.ServiceRouter;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ARouter$$Providers$$carstudio implements IProviderGroup {
    @Override // com.alibaba.android.arouter.facade.template.IProviderGroup
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("com.wanos.careditproject.service.CreatorService", RouteMeta.build(RouteType.PROVIDER, CreatorService.class, ServiceRouter.CREATOR_SERVICE, "carstudio", null, -1, Integer.MIN_VALUE));
    }
}
