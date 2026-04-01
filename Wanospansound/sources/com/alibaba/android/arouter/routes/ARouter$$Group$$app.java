package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.wanos.commonlibrary.router.PageRouter;
import com.wanos.commonlibrary.router.ServiceRouter;
import com.wanos.media.db.MediaPlayServiceImpl;
import com.wanos.media.service.CommonServiceImpl;
import com.wanos.media.ui.actvity.CollectionActivity;
import com.wanos.media.ui.actvity.RecentPlayActivity;
import com.wanos.media.ui.audiobook.activity.AudioBookAlbumActivity;
import com.wanos.media.ui.music.activity.MusicListActivity;
import com.wanos.media.ui.music.activity.MusicPlayActivity;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ARouter$$Group$$app implements IRouteGroup {
    @Override // com.alibaba.android.arouter.facade.template.IRouteGroup
    public void loadInto(Map<String, RouteMeta> atlas) {
        atlas.put(PageRouter.AUDIOBOOK_ALBUM_ACTIVITY, RouteMeta.build(RouteType.ACTIVITY, AudioBookAlbumActivity.class, "/app/audiobookalbumactivity", "app", null, -1, Integer.MIN_VALUE));
        atlas.put(PageRouter.RECENT_PLAYED_ACTIVITY, RouteMeta.build(RouteType.ACTIVITY, RecentPlayActivity.class, "/app/audiobookmineactivity", "app", null, -1, Integer.MIN_VALUE));
        atlas.put(PageRouter.COLLECTION_ACTIVITY, RouteMeta.build(RouteType.ACTIVITY, CollectionActivity.class, "/app/collectionactivity", "app", null, -1, Integer.MIN_VALUE));
        atlas.put(ServiceRouter.COMMON_SERVICE, RouteMeta.build(RouteType.PROVIDER, CommonServiceImpl.class, "/app/commonservice", "app", null, -1, Integer.MIN_VALUE));
        atlas.put(ServiceRouter.MEDIA_PLAY_SERVICE, RouteMeta.build(RouteType.PROVIDER, MediaPlayServiceImpl.class, "/app/mediaplayservice", "app", null, -1, Integer.MIN_VALUE));
        atlas.put(PageRouter.MUSIC_LIST_ACTIVITY, RouteMeta.build(RouteType.ACTIVITY, MusicListActivity.class, "/app/musiclistactivity", "app", null, -1, Integer.MIN_VALUE));
        atlas.put(PageRouter.MUSIC_PLAY_ACTIVITY, RouteMeta.build(RouteType.ACTIVITY, MusicPlayActivity.class, "/app/musicplayactivity", "app", null, -1, Integer.MIN_VALUE));
    }
}
