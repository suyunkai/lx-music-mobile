package com.wanos.media.util;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.wanos.media.ZeroApplication;

/* JADX INFO: loaded from: classes3.dex */
class DatabaseHelp extends SQLiteOpenHelper {
    public static final String COLUMN_FILE_ACCESS = "_access_time";
    public static final String COLUMN_FILE_PATH = "_file_path";
    public static final String COLUMN_FILE_TYPE = "_file_type";
    public static final String COLUMN_ID = "_id";
    private static final String CREATE_TABLE_HOT = "CREATE TABLE cache_hot(_id INTEGER PRIMARY KEY AUTOINCREMENT,_file_type TINYINT,_file_path TEXT NOT NULL,_access_time INTEGER);";
    private static final String DB_NAME = "zero_cache_hot.db";
    private static final int DB_VERSION = 1;
    public static final String TABLE_NAME_CACHE_HOST = "cache_hot";

    public DatabaseHelp() {
        super(ZeroApplication.getApplication(), DB_NAME, (SQLiteDatabase.CursorFactory) null, 1);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(CREATE_TABLE_HOT);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS cache_hot");
        onCreate(sQLiteDatabase);
    }
}
