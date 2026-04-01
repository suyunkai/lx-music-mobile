package com.wanos.media.db;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.wanos.media.MainApplication;
import com.wanos.media.db.dao.AudioBookDao;
import com.wanos.media.db.dao.MusicDao;

/* JADX INFO: loaded from: classes3.dex */
public abstract class WanosDatabase extends RoomDatabase {
    static final Migration MIGRATION_1_2 = new Migration(1, 2) { // from class: com.wanos.media.db.WanosDatabase.1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE music_history_list ADD COLUMN  'isFree' INTEGER NOT NULL DEFAULT 0");
            database.execSQL("ALTER TABLE music_history_list ADD COLUMN  'freeEndTime' INTEGER NOT NULL DEFAULT 0");
        }
    };
    private static volatile WanosDatabase wanosDatabase;

    public abstract AudioBookDao audioBookDao();

    public abstract MusicDao musicDao();

    public static WanosDatabase getInstance() {
        if (wanosDatabase == null) {
            synchronized (WanosDatabase.class) {
                if (wanosDatabase == null) {
                    wanosDatabase = (WanosDatabase) Room.databaseBuilder(MainApplication.getInstance(), WanosDatabase.class, DbConfig.DB_NAME).addMigrations(MIGRATION_1_2).build();
                }
            }
        }
        return wanosDatabase;
    }
}
