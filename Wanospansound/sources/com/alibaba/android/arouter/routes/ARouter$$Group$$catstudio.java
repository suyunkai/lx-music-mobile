package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.wanos.careditproject.CreatorPageRouter;
import com.wanos.careditproject.ui.fragment.CreatorHomeFragment;
import com.wanos.careditproject.view.WanosPlayerActivity;
import com.wanos.commonlibrary.router.PageRouter;
import com.wanos.editmain.activity.CreatorCommunityActivity;
import com.wanos.editmain.activity.CreatorMineActivity;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ARouter$$Group$$catstudio implements IRouteGroup {
    @Override // com.alibaba.android.arouter.facade.template.IRouteGroup
    public void loadInto(Map<String, RouteMeta> map) {
        map.put(CreatorPageRouter.CREATOR_COMMUNITY_ACTIVITY, RouteMeta.build(RouteType.ACTIVITY, CreatorCommunityActivity.class, "/catstudio/creatorcommunityactivity", "catstudio", null, -1, Integer.MIN_VALUE));
        map.put(PageRouter.CREATOR_HOME_FRAGMENT, RouteMeta.build(RouteType.FRAGMENT, CreatorHomeFragment.class, "/catstudio/creatorhomefragment", "catstudio", null, -1, Integer.MIN_VALUE));
        map.put(CreatorPageRouter.CREATOR_MINE_ACTIVITY, RouteMeta.build(RouteType.ACTIVITY, CreatorMineActivity.class, "/catstudio/creatormineactivity", "catstudio", null, -1, Integer.MIN_VALUE));
        map.put(CreatorPageRouter.CREATOR_PLAY_ACTIVITY, RouteMeta.build(RouteType.ACTIVITY, WanosPlayerActivity.class, "/catstudio/wanosplayeractivity", "catstudio", null, -1, Integer.MIN_VALUE));
    }
}
