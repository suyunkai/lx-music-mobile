package com.wanos.media.db.dao;

import android.database.Cursor;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.EntityUpsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.wanos.media.db.entity.music.DbAudioBookInfo;
import com.wanos.media.ui.advertise.WanosJsBridge;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class AudioBookDao_Impl implements AudioBookDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<DbAudioBookInfo> __deletionAdapterOfDbAudioBookInfo;
    private final EntityUpsertionAdapter<DbAudioBookInfo> __upsertionAdapterOfDbAudioBookInfo;

    public AudioBookDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__deletionAdapterOfDbAudioBookInfo = new EntityDeletionOrUpdateAdapter<DbAudioBookInfo>(__db) { // from class: com.wanos.media.db.dao.AudioBookDao_Impl.1
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `audio_book_history_list` WHERE `dbId` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, DbAudioBookInfo value) {
                stmt.bindLong(1, value.getDbId());
            }
        };
        this.__upsertionAdapterOfDbAudioBookInfo = new EntityUpsertionAdapter<>(new EntityInsertionAdapter<DbAudioBookInfo>(__db) { // from class: com.wanos.media.db.dao.AudioBookDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT INTO `audio_book_history_list` (`dbId`,`dbUpadteTime`,`id`,`isCollect`,`duration`,`isVip`,`isPay`,`canPreview`,`name`,`avatar`,`statusMsg`,`introduction`,`path`,`progress`,`index`,`radioId`,`radioName`,`speakId`,`speakName`,`speakAvatar`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, DbAudioBookInfo value) {
                stmt.bindLong(1, value.getDbId());
                stmt.bindLong(2, value.getDbUpadteTime());
                stmt.bindLong(3, value.getId());
                stmt.bindLong(4, value.getIsCollect());
                stmt.bindLong(5, value.getDuration());
                stmt.bindLong(6, value.getIsVip());
                stmt.bindLong(7, value.getIsPay());
                stmt.bindLong(8, value.getCanPreview());
                if (value.getName() == null) {
                    stmt.bindNull(9);
                } else {
                    stmt.bindString(9, value.getName());
                }
                if (value.getAvatar() == null) {
                    stmt.bindNull(10);
                } else {
                    stmt.bindString(10, value.getAvatar());
                }
                if (value.getStatusMsg() == null) {
                    stmt.bindNull(11);
                } else {
                    stmt.bindString(11, value.getStatusMsg());
                }
                if (value.getIntroduction() == null) {
                    stmt.bindNull(12);
                } else {
                    stmt.bindString(12, value.getIntroduction());
                }
                if (value.getPath() == null) {
                    stmt.bindNull(13);
                } else {
                    stmt.bindString(13, value.getPath());
                }
                stmt.bindLong(14, value.getProgress());
                stmt.bindLong(15, value.getIndex());
                stmt.bindLong(16, value.getRadioId());
                if (value.getRadioName() == null) {
                    stmt.bindNull(17);
                } else {
                    stmt.bindString(17, value.getRadioName());
                }
                stmt.bindLong(18, value.getSpeakId());
                if (value.getSpeakName() == null) {
                    stmt.bindNull(19);
                } else {
                    stmt.bindString(19, value.getSpeakName());
                }
                if (value.getSpeakAvatar() == null) {
                    stmt.bindNull(20);
                } else {
                    stmt.bindString(20, value.getSpeakAvatar());
                }
            }
        }, new EntityDeletionOrUpdateAdapter<DbAudioBookInfo>(__db) { // from class: com.wanos.media.db.dao.AudioBookDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE `audio_book_history_list` SET `dbId` = ?,`dbUpadteTime` = ?,`id` = ?,`isCollect` = ?,`duration` = ?,`isVip` = ?,`isPay` = ?,`canPreview` = ?,`name` = ?,`avatar` = ?,`statusMsg` = ?,`introduction` = ?,`path` = ?,`progress` = ?,`index` = ?,`radioId` = ?,`radioName` = ?,`speakId` = ?,`speakName` = ?,`speakAvatar` = ? WHERE `dbId` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, DbAudioBookInfo value) {
                stmt.bindLong(1, value.getDbId());
                stmt.bindLong(2, value.getDbUpadteTime());
                stmt.bindLong(3, value.getId());
                stmt.bindLong(4, value.getIsCollect());
                stmt.bindLong(5, value.getDuration());
                stmt.bindLong(6, value.getIsVip());
                stmt.bindLong(7, value.getIsPay());
                stmt.bindLong(8, value.getCanPreview());
                if (value.getName() == null) {
                    stmt.bindNull(9);
                } else {
                    stmt.bindString(9, value.getName());
                }
                if (value.getAvatar() == null) {
                    stmt.bindNull(10);
                } else {
                    stmt.bindString(10, value.getAvatar());
                }
                if (value.getStatusMsg() == null) {
                    stmt.bindNull(11);
                } else {
                    stmt.bindString(11, value.getStatusMsg());
                }
                if (value.getIntroduction() == null) {
                    stmt.bindNull(12);
                } else {
                    stmt.bindString(12, value.getIntroduction());
                }
                if (value.getPath() == null) {
                    stmt.bindNull(13);
                } else {
                    stmt.bindString(13, value.getPath());
                }
                stmt.bindLong(14, value.getProgress());
                stmt.bindLong(15, value.getIndex());
                stmt.bindLong(16, value.getRadioId());
                if (value.getRadioName() == null) {
                    stmt.bindNull(17);
                } else {
                    stmt.bindString(17, value.getRadioName());
                }
                stmt.bindLong(18, value.getSpeakId());
                if (value.getSpeakName() == null) {
                    stmt.bindNull(19);
                } else {
                    stmt.bindString(19, value.getSpeakName());
                }
                if (value.getSpeakAvatar() == null) {
                    stmt.bindNull(20);
                } else {
                    stmt.bindString(20, value.getSpeakAvatar());
                }
                stmt.bindLong(21, value.getDbId());
            }
        });
    }

    @Override // com.wanos.media.db.dao.AudioBookDao
    public int deleteDbAudioBookInfo(final DbAudioBookInfo info) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            int iHandle = this.__deletionAdapterOfDbAudioBookInfo.handle(info) + 0;
            this.__db.setTransactionSuccessful();
            return iHandle;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.wanos.media.db.dao.AudioBookDao
    public void deleteDbAudioBookInfoList(final List<DbAudioBookInfo> infoList) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfDbAudioBookInfo.handleMultiple(infoList);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.wanos.media.db.dao.AudioBookDao
    public void insertOrUpdateDbAudioBookInfo(final DbAudioBookInfo info) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__upsertionAdapterOfDbAudioBookInfo.upsert(info);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.wanos.media.db.dao.AudioBookDao
    public List<DbAudioBookInfo> queryAllDbDbAudioBookInfoInfo() throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        String string;
        String string2;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM audio_book_history_list ORDER BY dbUpadteTime  DESC", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "dbId");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "dbUpadteTime");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, WanosJsBridge.PARA_KEY_SONG_IS_COLLECT);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TypedValues.TransitionType.S_DURATION);
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "isVip");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "isPay");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "canPreview");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "name");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "avatar");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "statusMsg");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "introduction");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "path");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "progress");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "index");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "radioId");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "radioName");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "speakId");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "speakName");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "speakAvatar");
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                while (cursorQuery.moveToNext()) {
                    DbAudioBookInfo dbAudioBookInfo = new DbAudioBookInfo();
                    ArrayList arrayList2 = arrayList;
                    int i2 = columnIndexOrThrow13;
                    dbAudioBookInfo.setDbId(cursorQuery.getLong(columnIndexOrThrow));
                    dbAudioBookInfo.setDbUpadteTime(cursorQuery.getLong(columnIndexOrThrow2));
                    dbAudioBookInfo.setId(cursorQuery.getLong(columnIndexOrThrow3));
                    dbAudioBookInfo.setIsCollect(cursorQuery.getInt(columnIndexOrThrow4));
                    dbAudioBookInfo.setDuration(cursorQuery.getLong(columnIndexOrThrow5));
                    dbAudioBookInfo.setIsVip(cursorQuery.getInt(columnIndexOrThrow6));
                    dbAudioBookInfo.setIsPay(cursorQuery.getInt(columnIndexOrThrow7));
                    dbAudioBookInfo.setCanPreview(cursorQuery.getInt(columnIndexOrThrow8));
                    dbAudioBookInfo.setName(cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9));
                    dbAudioBookInfo.setAvatar(cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10));
                    dbAudioBookInfo.setStatusMsg(cursorQuery.isNull(columnIndexOrThrow11) ? null : cursorQuery.getString(columnIndexOrThrow11));
                    dbAudioBookInfo.setIntroduction(cursorQuery.isNull(columnIndexOrThrow12) ? null : cursorQuery.getString(columnIndexOrThrow12));
                    dbAudioBookInfo.setPath(cursorQuery.isNull(i2) ? null : cursorQuery.getString(i2));
                    int i3 = columnIndexOrThrow3;
                    int i4 = i;
                    int i5 = columnIndexOrThrow2;
                    dbAudioBookInfo.setProgress(cursorQuery.getLong(i4));
                    int i6 = columnIndexOrThrow15;
                    dbAudioBookInfo.setIndex(cursorQuery.getInt(i6));
                    int i7 = columnIndexOrThrow16;
                    dbAudioBookInfo.setRadioId(cursorQuery.getLong(i7));
                    int i8 = columnIndexOrThrow17;
                    dbAudioBookInfo.setRadioName(cursorQuery.isNull(i8) ? null : cursorQuery.getString(i8));
                    int i9 = columnIndexOrThrow;
                    int i10 = columnIndexOrThrow18;
                    dbAudioBookInfo.setSpeakId(cursorQuery.getInt(i10));
                    int i11 = columnIndexOrThrow19;
                    if (cursorQuery.isNull(i11)) {
                        columnIndexOrThrow19 = i11;
                        string = null;
                    } else {
                        columnIndexOrThrow19 = i11;
                        string = cursorQuery.getString(i11);
                    }
                    dbAudioBookInfo.setSpeakName(string);
                    int i12 = columnIndexOrThrow20;
                    if (cursorQuery.isNull(i12)) {
                        columnIndexOrThrow20 = i12;
                        string2 = null;
                    } else {
                        columnIndexOrThrow20 = i12;
                        string2 = cursorQuery.getString(i12);
                    }
                    dbAudioBookInfo.setSpeakAvatar(string2);
                    arrayList2.add(dbAudioBookInfo);
                    columnIndexOrThrow18 = i10;
                    columnIndexOrThrow13 = i2;
                    columnIndexOrThrow15 = i6;
                    columnIndexOrThrow2 = i5;
                    i = i4;
                    columnIndexOrThrow16 = i7;
                    columnIndexOrThrow3 = i3;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i9;
                    columnIndexOrThrow17 = i8;
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

    @Override // com.wanos.media.db.dao.AudioBookDao
    public DbAudioBookInfo queryDbAudioBookInfoBean(final long id) throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        DbAudioBookInfo dbAudioBookInfo;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM audio_book_history_list WHERE dbId = ?", 1);
        roomSQLiteQueryAcquire.bindLong(1, id);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "dbId");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "dbUpadteTime");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, WanosJsBridge.PARA_KEY_SONG_IS_COLLECT);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TypedValues.TransitionType.S_DURATION);
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "isVip");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "isPay");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "canPreview");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "name");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "avatar");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "statusMsg");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "introduction");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "path");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "progress");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "index");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "radioId");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "radioName");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "speakId");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "speakName");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "speakAvatar");
                if (cursorQuery.moveToFirst()) {
                    DbAudioBookInfo dbAudioBookInfo2 = new DbAudioBookInfo();
                    dbAudioBookInfo2.setDbId(cursorQuery.getLong(columnIndexOrThrow));
                    dbAudioBookInfo2.setDbUpadteTime(cursorQuery.getLong(columnIndexOrThrow2));
                    dbAudioBookInfo2.setId(cursorQuery.getLong(columnIndexOrThrow3));
                    dbAudioBookInfo2.setIsCollect(cursorQuery.getInt(columnIndexOrThrow4));
                    dbAudioBookInfo2.setDuration(cursorQuery.getLong(columnIndexOrThrow5));
                    dbAudioBookInfo2.setIsVip(cursorQuery.getInt(columnIndexOrThrow6));
                    dbAudioBookInfo2.setIsPay(cursorQuery.getInt(columnIndexOrThrow7));
                    dbAudioBookInfo2.setCanPreview(cursorQuery.getInt(columnIndexOrThrow8));
                    dbAudioBookInfo2.setName(cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9));
                    dbAudioBookInfo2.setAvatar(cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10));
                    dbAudioBookInfo2.setStatusMsg(cursorQuery.isNull(columnIndexOrThrow11) ? null : cursorQuery.getString(columnIndexOrThrow11));
                    dbAudioBookInfo2.setIntroduction(cursorQuery.isNull(columnIndexOrThrow12) ? null : cursorQuery.getString(columnIndexOrThrow12));
                    dbAudioBookInfo2.setPath(cursorQuery.isNull(columnIndexOrThrow13) ? null : cursorQuery.getString(columnIndexOrThrow13));
                    dbAudioBookInfo2.setProgress(cursorQuery.getLong(columnIndexOrThrow14));
                    dbAudioBookInfo2.setIndex(cursorQuery.getInt(columnIndexOrThrow15));
                    dbAudioBookInfo2.setRadioId(cursorQuery.getLong(columnIndexOrThrow16));
                    dbAudioBookInfo2.setRadioName(cursorQuery.isNull(columnIndexOrThrow17) ? null : cursorQuery.getString(columnIndexOrThrow17));
                    dbAudioBookInfo2.setSpeakId(cursorQuery.getInt(columnIndexOrThrow18));
                    dbAudioBookInfo2.setSpeakName(cursorQuery.isNull(columnIndexOrThrow19) ? null : cursorQuery.getString(columnIndexOrThrow19));
                    dbAudioBookInfo2.setSpeakAvatar(cursorQuery.isNull(columnIndexOrThrow20) ? null : cursorQuery.getString(columnIndexOrThrow20));
                    dbAudioBookInfo = dbAudioBookInfo2;
                } else {
                    dbAudioBookInfo = null;
                }
                cursorQuery.close();
                roomSQLiteQuery.release();
                return dbAudioBookInfo;
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
