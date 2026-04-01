package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IProviderGroup;
import com.wanos.commonlibrary.router.ServiceRouter;
import com.wanos.media.juyihall.service.RecommendServiceImpl;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ARouter$$Providers$$JuyiHall implements IProviderGroup {
    @Override // com.alibaba.android.arouter.facade.template.IProviderGroup
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("com.wanos.media.juyihall.service.RecommendServiceImpl", RouteMeta.build(RouteType.PROVIDER, RecommendServiceImpl.class, ServiceRouter.RECOMMEND_SERVICE, "JuyiHall", null, -1, Integer.MIN_VALUE));
    }
}
