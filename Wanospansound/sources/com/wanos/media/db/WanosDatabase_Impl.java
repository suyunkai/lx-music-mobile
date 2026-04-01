package com.wanos.media.db;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.wanos.media.db.dao.AudioBookDao;
import com.wanos.media.db.dao.AudioBookDao_Impl;
import com.wanos.media.db.dao.MusicDao;
import com.wanos.media.db.dao.MusicDao_Impl;
import com.wanos.media.ui.advertise.WanosJsBridge;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public final class WanosDatabase_Impl extends WanosDatabase {
    private volatile AudioBookDao _audioBookDao;
    private volatile MusicDao _musicDao;

    @Override // androidx.room.RoomDatabase
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
        return configuration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(configuration.context).name(configuration.name).callback(new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(2) { // from class: com.wanos.media.db.WanosDatabase_Impl.1
            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPostMigrate(SupportSQLiteDatabase _db) {
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void createAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("CREATE TABLE IF NOT EXISTS `music_history_list` (`id` INTEGER NOT NULL, `name` TEXT, `avatar` TEXT, `timeLen` REAL NOT NULL, `path` TEXT, `lrcPath` TEXT, `singer` TEXT, `likeStatus` INTEGER NOT NULL, `isVipAuth` INTEGER NOT NULL, `isFree` INTEGER NOT NULL, `freeEndTime` INTEGER NOT NULL, `previewStartTime` INTEGER NOT NULL, `previewEndTime` INTEGER NOT NULL, `dbUpadteTime` INTEGER NOT NULL, PRIMARY KEY(`id`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `audio_book_history_list` (`dbId` INTEGER NOT NULL, `dbUpadteTime` INTEGER NOT NULL, `id` INTEGER NOT NULL, `isCollect` INTEGER NOT NULL, `duration` INTEGER NOT NULL, `isVip` INTEGER NOT NULL, `isPay` INTEGER NOT NULL, `canPreview` INTEGER NOT NULL, `name` TEXT, `avatar` TEXT, `statusMsg` TEXT, `introduction` TEXT, `path` TEXT, `progress` INTEGER NOT NULL, `index` INTEGER NOT NULL, `radioId` INTEGER NOT NULL, `radioName` TEXT, `speakId` INTEGER NOT NULL, `speakName` TEXT, `speakAvatar` TEXT, PRIMARY KEY(`dbId`))");
                _db.execSQL(RoomMasterTable.CREATE_QUERY);
                _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '5e66c82d72557d93791b25e296c583c1')");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void dropAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("DROP TABLE IF EXISTS `music_history_list`");
                _db.execSQL("DROP TABLE IF EXISTS `audio_book_history_list`");
                if (WanosDatabase_Impl.this.mCallbacks != null) {
                    int size = WanosDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) WanosDatabase_Impl.this.mCallbacks.get(i)).onDestructiveMigration(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onCreate(SupportSQLiteDatabase _db) {
                if (WanosDatabase_Impl.this.mCallbacks != null) {
                    int size = WanosDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) WanosDatabase_Impl.this.mCallbacks.get(i)).onCreate(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onOpen(SupportSQLiteDatabase _db) {
                WanosDatabase_Impl.this.mDatabase = _db;
                WanosDatabase_Impl.this.internalInitInvalidationTracker(_db);
                if (WanosDatabase_Impl.this.mCallbacks != null) {
                    int size = WanosDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) WanosDatabase_Impl.this.mCallbacks.get(i)).onOpen(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPreMigrate(SupportSQLiteDatabase _db) {
                DBUtil.dropFtsSyncTriggers(_db);
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
                HashMap map = new HashMap(14);
                map.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                map.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, 1));
                map.put("avatar", new TableInfo.Column("avatar", "TEXT", false, 0, null, 1));
                map.put("timeLen", new TableInfo.Column("timeLen", "REAL", true, 0, null, 1));
                map.put("path", new TableInfo.Column("path", "TEXT", false, 0, null, 1));
                map.put("lrcPath", new TableInfo.Column("lrcPath", "TEXT", false, 0, null, 1));
                map.put("singer", new TableInfo.Column("singer", "TEXT", false, 0, null, 1));
                map.put("likeStatus", new TableInfo.Column("likeStatus", "INTEGER", true, 0, null, 1));
                map.put("isVipAuth", new TableInfo.Column("isVipAuth", "INTEGER", true, 0, null, 1));
                map.put("isFree", new TableInfo.Column("isFree", "INTEGER", true, 0, null, 1));
                map.put("freeEndTime", new TableInfo.Column("freeEndTime", "INTEGER", true, 0, null, 1));
                map.put("previewStartTime", new TableInfo.Column("previewStartTime", "INTEGER", true, 0, null, 1));
                map.put("previewEndTime", new TableInfo.Column("previewEndTime", "INTEGER", true, 0, null, 1));
                map.put("dbUpadteTime", new TableInfo.Column("dbUpadteTime", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo = new TableInfo(DbConfig.MUSIC_HISTORY_LIST_TABLE_NAME, map, new HashSet(0), new HashSet(0));
                TableInfo tableInfo2 = TableInfo.read(_db, DbConfig.MUSIC_HISTORY_LIST_TABLE_NAME);
                if (!tableInfo.equals(tableInfo2)) {
                    return new RoomOpenHelper.ValidationResult(false, "music_history_list(com.wanos.media.db.entity.music.DbMusicInfo).\n Expected:\n" + tableInfo + "\n Found:\n" + tableInfo2);
                }
                HashMap map2 = new HashMap(20);
                map2.put("dbId", new TableInfo.Column("dbId", "INTEGER", true, 1, null, 1));
                map2.put("dbUpadteTime", new TableInfo.Column("dbUpadteTime", "INTEGER", true, 0, null, 1));
                map2.put("id", new TableInfo.Column("id", "INTEGER", true, 0, null, 1));
                map2.put(WanosJsBridge.PARA_KEY_SONG_IS_COLLECT, new TableInfo.Column(WanosJsBridge.PARA_KEY_SONG_IS_COLLECT, "INTEGER", true, 0, null, 1));
                map2.put(TypedValues.TransitionType.S_DURATION, new TableInfo.Column(TypedValues.TransitionType.S_DURATION, "INTEGER", true, 0, null, 1));
                map2.put("isVip", new TableInfo.Column("isVip", "INTEGER", true, 0, null, 1));
                map2.put("isPay", new TableInfo.Column("isPay", "INTEGER", true, 0, null, 1));
                map2.put("canPreview", new TableInfo.Column("canPreview", "INTEGER", true, 0, null, 1));
                map2.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, 1));
                map2.put("avatar", new TableInfo.Column("avatar", "TEXT", false, 0, null, 1));
                map2.put("statusMsg", new TableInfo.Column("statusMsg", "TEXT", false, 0, null, 1));
                map2.put("introduction", new TableInfo.Column("introduction", "TEXT", false, 0, null, 1));
                map2.put("path", new TableInfo.Column("path", "TEXT", false, 0, null, 1));
                map2.put("progress", new TableInfo.Column("progress", "INTEGER", true, 0, null, 1));
                map2.put("index", new TableInfo.Column("index", "INTEGER", true, 0, null, 1));
                map2.put("radioId", new TableInfo.Column("radioId", "INTEGER", true, 0, null, 1));
                map2.put("radioName", new TableInfo.Column("radioName", "TEXT", false, 0, null, 1));
                map2.put("speakId", new TableInfo.Column("speakId", "INTEGER", true, 0, null, 1));
                map2.put("speakName", new TableInfo.Column("speakName", "TEXT", false, 0, null, 1));
                map2.put("speakAvatar", new TableInfo.Column("speakAvatar", "TEXT", false, 0, null, 1));
                TableInfo tableInfo3 = new TableInfo(DbConfig.AUDIO_BOOK_HISTORY_LIST_TABLE_NAME, map2, new HashSet(0), new HashSet(0));
                TableInfo tableInfo4 = TableInfo.read(_db, DbConfig.AUDIO_BOOK_HISTORY_LIST_TABLE_NAME);
                if (!tableInfo3.equals(tableInfo4)) {
                    return new RoomOpenHelper.ValidationResult(false, "audio_book_history_list(com.wanos.media.db.entity.music.DbAudioBookInfo).\n Expected:\n" + tableInfo3 + "\n Found:\n" + tableInfo4);
                }
                return new RoomOpenHelper.ValidationResult(true, null);
            }
        }, "5e66c82d72557d93791b25e296c583c1", "35eb7a5bca1928a4ae58030bb5ec3217")).build());
    }

    @Override // androidx.room.RoomDatabase
    protected InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), DbConfig.MUSIC_HISTORY_LIST_TABLE_NAME, DbConfig.AUDIO_BOOK_HISTORY_LIST_TABLE_NAME);
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `music_history_list`");
            writableDatabase.execSQL("DELETE FROM `audio_book_history_list`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            writableDatabase.query("PRAGMA wal_checkpoint(FULL)").close();
            if (!writableDatabase.inTransaction()) {
                writableDatabase.execSQL("VACUUM");
            }
        }
    }

    @Override // androidx.room.RoomDatabase
    protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        HashMap map = new HashMap();
        map.put(MusicDao.class, MusicDao_Impl.getRequiredConverters());
        map.put(AudioBookDao.class, AudioBookDao_Impl.getRequiredConverters());
        return map;
    }

    @Override // androidx.room.RoomDatabase
    public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
        return new HashSet();
    }

    @Override // androidx.room.RoomDatabase
    public List<Migration> getAutoMigrations(Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
        return Arrays.asList(new Migration[0]);
    }

    @Override // com.wanos.media.db.WanosDatabase
    public MusicDao musicDao() {
        MusicDao musicDao;
        if (this._musicDao != null) {
            return this._musicDao;
        }
        synchronized (this) {
            if (this._musicDao == null) {
                this._musicDao = new MusicDao_Impl(this);
            }
            musicDao = this._musicDao;
        }
        return musicDao;
    }

    @Override // com.wanos.media.db.WanosDatabase
    public AudioBookDao audioBookDao() {
        AudioBookDao audioBookDao;
        if (this._audioBookDao != null) {
            return this._audioBookDao;
        }
        synchronized (this) {
            if (this._audioBookDao == null) {
                this._audioBookDao = new AudioBookDao_Impl(this);
            }
            audioBookDao = this._audioBookDao;
        }
        return audioBookDao;
    }
}
