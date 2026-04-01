package com.wanos.commonlibrary.router;

import com.alibaba.android.arouter.launcher.ARouter;

/* JADX INFO: loaded from: classes3.dex */
public class PageRouter {
    public static final String AUDIOBOOK_ALBUM_ACTIVITY = "/app/AudioBookAlbumActivity";
    public static final String COLLECTION_ACTIVITY = "/app/CollectionActivity";
    public static final String CREATOR_HOME_FRAGMENT = "/catstudio/CreatorHomeFragment";
    public static final String JUYIHALL_FRAGMENT = "/juyi/JuyiHallFragment";
    public static final String MUSIC_LIST_ACTIVITY = "/app/MusicListActivity";
    public static final String MUSIC_PLAY_ACTIVITY = "/app/MusicPlayActivity";
    public static final String RECENT_PLAYED_ACTIVITY = "/app/AudioBookMineActivity";

    public static void toMusicListPage(String str) {
        ARouter.getInstance().build(MUSIC_LIST_ACTIVITY).withLong("groupId", Long.parseLong(str)).navigation();
    }

    public static void toMusicPlayPage() {
        ARouter.getInstance().build(MUSIC_PLAY_ACTIVITY).navigation();
    }

    public static void toAudioBoolAlbumPage(String str) {
        ARouter.getInstance().build(AUDIOBOOK_ALBUM_ACTIVITY).withLong("ID", Long.parseLong(str)).navigation();
    }

    public static void toCollectionPage() {
        ARouter.getInstance().build(COLLECTION_ACTIVITY).navigation();
    }

    public static void toRecentPlayedPage() {
        ARouter.getInstance().build(RECENT_PLAYED_ACTIVITY).navigation();
    }
}
