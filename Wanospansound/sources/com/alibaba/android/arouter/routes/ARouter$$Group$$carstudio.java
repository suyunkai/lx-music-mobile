package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.wanos.careditproject.service.CreatorService;
import com.wanos.commonlibrary.router.ServiceRouter;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ARouter$$Group$$carstudio implements IRouteGroup {
    @Override // com.alibaba.android.arouter.facade.template.IRouteGroup
    public void loadInto(Map<String, RouteMeta> map) {
        map.put(ServiceRouter.CREATOR_SERVICE, RouteMeta.build(RouteType.PROVIDER, CreatorService.class, "/carstudio/creatorservice", "carstudio", null, -1, Integer.MIN_VALUE));
    }
}
