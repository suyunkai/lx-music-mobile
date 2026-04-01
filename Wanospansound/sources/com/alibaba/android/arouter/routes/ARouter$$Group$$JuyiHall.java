package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.wanos.commonlibrary.router.ServiceRouter;
import com.wanos.media.juyihall.service.RecommendServiceImpl;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ARouter$$Group$$JuyiHall implements IRouteGroup {
    @Override // com.alibaba.android.arouter.facade.template.IRouteGroup
    public void loadInto(Map<String, RouteMeta> map) {
        map.put(ServiceRouter.RECOMMEND_SERVICE, RouteMeta.build(RouteType.PROVIDER, RecommendServiceImpl.class, "/juyihall/recommendservice", "juyihall", null, -1, Integer.MIN_VALUE));
    }
}
