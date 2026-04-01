package com.wanos.media.db.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.EntityUpsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.wanos.media.db.entity.music.DbMusicInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class MusicDao_Impl implements MusicDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<DbMusicInfo> __deletionAdapterOfDbMusicInfo;
    private final EntityUpsertionAdapter<DbMusicInfo> __upsertionAdapterOfDbMusicInfo;

    public MusicDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__deletionAdapterOfDbMusicInfo = new EntityDeletionOrUpdateAdapter<DbMusicInfo>(__db) { // from class: com.wanos.media.db.dao.MusicDao_Impl.1
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `music_history_list` WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, DbMusicInfo value) {
                stmt.bindLong(1, value.getId());
            }
        };
        this.__upsertionAdapterOfDbMusicInfo = new EntityUpsertionAdapter<>(new EntityInsertionAdapter<DbMusicInfo>(__db) { // from class: com.wanos.media.db.dao.MusicDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT INTO `music_history_list` (`id`,`name`,`avatar`,`timeLen`,`path`,`lrcPath`,`singer`,`likeStatus`,`isVipAuth`,`isFree`,`freeEndTime`,`previewStartTime`,`previewEndTime`,`dbUpadteTime`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, DbMusicInfo dbMusicInfo) {
                supportSQLiteStatement.bindLong(1, dbMusicInfo.getId());
                if (dbMusicInfo.getName() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, dbMusicInfo.getName());
                }
                if (dbMusicInfo.getAvatar() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, dbMusicInfo.getAvatar());
                }
                supportSQLiteStatement.bindDouble(4, dbMusicInfo.getTimeLen());
                if (dbMusicInfo.getPath() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, dbMusicInfo.getPath());
                }
                if (dbMusicInfo.getLrcPath() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, dbMusicInfo.getLrcPath());
                }
                if (dbMusicInfo.getSinger() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, dbMusicInfo.getSinger());
                }
                supportSQLiteStatement.bindLong(8, dbMusicInfo.isLikeStatus() ? 1L : 0L);
                supportSQLiteStatement.bindLong(9, dbMusicInfo.isVipAuth() ? 1L : 0L);
                supportSQLiteStatement.bindLong(10, dbMusicInfo.isFree() ? 1L : 0L);
                supportSQLiteStatement.bindLong(11, dbMusicInfo.getFreeEndTime());
                supportSQLiteStatement.bindLong(12, dbMusicInfo.getPreviewStartTime());
                supportSQLiteStatement.bindLong(13, dbMusicInfo.getPreviewEndTime());
                supportSQLiteStatement.bindLong(14, dbMusicInfo.getDbUpadteTime());
            }
        }, new EntityDeletionOrUpdateAdapter<DbMusicInfo>(__db) { // from class: com.wanos.media.db.dao.MusicDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE `music_history_list` SET `id` = ?,`name` = ?,`avatar` = ?,`timeLen` = ?,`path` = ?,`lrcPath` = ?,`singer` = ?,`likeStatus` = ?,`isVipAuth` = ?,`isFree` = ?,`freeEndTime` = ?,`previewStartTime` = ?,`previewEndTime` = ?,`dbUpadteTime` = ? WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, DbMusicInfo dbMusicInfo) {
                supportSQLiteStatement.bindLong(1, dbMusicInfo.getId());
                if (dbMusicInfo.getName() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, dbMusicInfo.getName());
                }
                if (dbMusicInfo.getAvatar() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, dbMusicInfo.getAvatar());
                }
                supportSQLiteStatement.bindDouble(4, dbMusicInfo.getTimeLen());
                if (dbMusicInfo.getPath() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, dbMusicInfo.getPath());
                }
                if (dbMusicInfo.getLrcPath() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, dbMusicInfo.getLrcPath());
                }
                if (dbMusicInfo.getSinger() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, dbMusicInfo.getSinger());
                }
                supportSQLiteStatement.bindLong(8, dbMusicInfo.isLikeStatus() ? 1L : 0L);
                supportSQLiteStatement.bindLong(9, dbMusicInfo.isVipAuth() ? 1L : 0L);
                supportSQLiteStatement.bindLong(10, dbMusicInfo.isFree() ? 1L : 0L);
                supportSQLiteStatement.bindLong(11, dbMusicInfo.getFreeEndTime());
                supportSQLiteStatement.bindLong(12, dbMusicInfo.getPreviewStartTime());
                supportSQLiteStatement.bindLong(13, dbMusicInfo.getPreviewEndTime());
                supportSQLiteStatement.bindLong(14, dbMusicInfo.getDbUpadteTime());
                supportSQLiteStatement.bindLong(15, dbMusicInfo.getId());
            }
        });
    }

    @Override // com.wanos.media.db.dao.MusicDao
    public int deleteDbMusicInfo(final DbMusicInfo musicInfo) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            int iHandle = this.__deletionAdapterOfDbMusicInfo.handle(musicInfo) + 0;
            this.__db.setTransactionSuccessful();
            return iHandle;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.wanos.media.db.dao.MusicDao
    public void deleteDbMusicInfoList(final List<DbMusicInfo> musicInfoList) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfDbMusicInfo.handleMultiple(musicInfoList);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.wanos.media.db.dao.MusicDao
    public void insertOrUpdateDbMusicInfo(final DbMusicInfo musicInfo) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__upsertionAdapterOfDbMusicInfo.upsert(musicInfo);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.wanos.media.db.dao.MusicDao
    public List<DbMusicInfo> queryAllDbMusicInfo() throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM music_history_list ORDER BY dbUpadteTime  DESC", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "name");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "avatar");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "timeLen");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "path");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "lrcPath");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "singer");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "likeStatus");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "isVipAuth");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "isFree");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "freeEndTime");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "previewStartTime");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "previewEndTime");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
            try {
                int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "dbUpadteTime");
                ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                while (cursorQuery.moveToNext()) {
                    DbMusicInfo dbMusicInfo = new DbMusicInfo();
                    ArrayList arrayList2 = arrayList;
                    int i = columnIndexOrThrow13;
                    dbMusicInfo.setId(cursorQuery.getLong(columnIndexOrThrow));
                    dbMusicInfo.setName(cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2));
                    dbMusicInfo.setAvatar(cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3));
                    dbMusicInfo.setTimeLen(cursorQuery.getFloat(columnIndexOrThrow4));
                    dbMusicInfo.setPath(cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5));
                    dbMusicInfo.setLrcPath(cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6));
                    dbMusicInfo.setSinger(cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7));
                    boolean z = true;
                    dbMusicInfo.setLikeStatus(cursorQuery.getInt(columnIndexOrThrow8) != 0);
                    dbMusicInfo.setVipAuth(cursorQuery.getInt(columnIndexOrThrow9) != 0);
                    if (cursorQuery.getInt(columnIndexOrThrow10) == 0) {
                        z = false;
                    }
                    dbMusicInfo.setFree(z);
                    dbMusicInfo.setFreeEndTime(cursorQuery.getLong(columnIndexOrThrow11));
                    dbMusicInfo.setPreviewStartTime(cursorQuery.getLong(columnIndexOrThrow12));
                    int i2 = columnIndexOrThrow2;
                    int i3 = columnIndexOrThrow3;
                    dbMusicInfo.setPreviewEndTime(cursorQuery.getLong(i));
                    int i4 = columnIndexOrThrow12;
                    int i5 = columnIndexOrThrow14;
                    dbMusicInfo.setDbUpadteTime(cursorQuery.getLong(i5));
                    arrayList2.add(dbMusicInfo);
                    arrayList = arrayList2;
                    columnIndexOrThrow12 = i4;
                    columnIndexOrThrow3 = i3;
                    columnIndexOrThrow2 = i2;
                    columnIndexOrThrow13 = i;
                    columnIndexOrThrow14 = i5;
                }
                ArrayList arrayList3 = arrayList;
                cursorQuery.close();
                roomSQLiteQuery.release();
                return arrayList3;
            } catch (Throwable th) {
                th = th;
                cursorQuery.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
