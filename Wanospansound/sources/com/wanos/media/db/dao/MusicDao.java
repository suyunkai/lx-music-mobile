package com.wanos.media.db.dao;

import com.wanos.media.db.entity.music.DbMusicInfo;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface MusicDao {
    int deleteDbMusicInfo(DbMusicInfo musicInfo);

    void deleteDbMusicInfoList(List<DbMusicInfo> musicInfoList);

    void insertOrUpdateDbMusicInfo(DbMusicInfo musicInfo);

    List<DbMusicInfo> queryAllDbMusicInfo();
}
