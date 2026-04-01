package com.wanos.media.db.dao;

import com.wanos.media.db.entity.music.DbAudioBookInfo;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface AudioBookDao {
    int deleteDbAudioBookInfo(DbAudioBookInfo info);

    void deleteDbAudioBookInfoList(List<DbAudioBookInfo> infoList);

    void insertOrUpdateDbAudioBookInfo(DbAudioBookInfo info);

    List<DbAudioBookInfo> queryAllDbDbAudioBookInfoInfo();

    DbAudioBookInfo queryDbAudioBookInfoBean(long id);
}
