package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.wanos.commonlibrary.router.PageRouter;
import com.wanos.media.juyihall.Recommend2Fragment;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ARouter$$Group$$juyi implements IRouteGroup {
    @Override // com.alibaba.android.arouter.facade.template.IRouteGroup
    public void loadInto(Map<String, RouteMeta> map) {
        map.put(PageRouter.JUYIHALL_FRAGMENT, RouteMeta.build(RouteType.FRAGMENT, Recommend2Fragment.class, "/juyi/juyihallfragment", "juyi", null, -1, Integer.MIN_VALUE));
    }
}
