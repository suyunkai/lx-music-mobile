package com.wanos.media.util;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
class CacheHotHelp {
    private static final int FILE_TYPE_IMAGE = 1;
    private static final int FILE_TYPE_MUSIC = 3;
    private static final int FILE_TYPE_VIDEO = 2;
    private static CacheHotHelp INSTANCE = null;
    private static final int MAX_DAY = 7;
    private static final String TAG = "CacheHotTools";
    private final DatabaseHelp mHelp = new DatabaseHelp();
    private final long MAX_TIME = System.currentTimeMillis() - 604800000;

    public static CacheHotHelp getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CacheHotHelp();
        }
        return INSTANCE;
    }

    private CacheHotHelp() {
    }

    public void setImageAccess(String str) {
        setAccess(str, 1);
    }

    public void setVideoAccess(String str) {
        setAccess(str, 2);
    }

    public void setAudioAccess(String str) {
        setAccess(str, 3);
    }

    private void setAccess(String str, int i) {
        SQLiteDatabase readableDatabase = this.mHelp.getReadableDatabase();
        SQLiteDatabase writableDatabase = this.mHelp.getWritableDatabase();
        Cursor cursorQuery = readableDatabase.query(DatabaseHelp.TABLE_NAME_CACHE_HOST, new String[]{DatabaseHelp.COLUMN_FILE_PATH}, "_file_path=?", new String[]{str}, null, null, null);
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelp.COLUMN_FILE_ACCESS, Long.valueOf(System.currentTimeMillis()));
        if (cursorQuery.moveToNext()) {
            writableDatabase.update(DatabaseHelp.TABLE_NAME_CACHE_HOST, contentValues, "_file_path=?", new String[]{str});
        } else {
            contentValues.put(DatabaseHelp.COLUMN_FILE_PATH, str);
            contentValues.put(DatabaseHelp.COLUMN_FILE_TYPE, Integer.valueOf(i));
            writableDatabase.insert(DatabaseHelp.TABLE_NAME_CACHE_HOST, null, contentValues);
        }
        cursorQuery.close();
    }

    public List<String> waitDeleteImage() {
        return waitDeleteFile(1);
    }

    public List<String> waitDeleteVideo() {
        return waitDeleteFile(2);
    }

    public List<String> waitDeleteMusic() {
        return waitDeleteFile(3);
    }

    private List<String> waitDeleteFile(int i) {
        SQLiteDatabase readableDatabase = this.mHelp.getReadableDatabase();
        ArrayList arrayList = new ArrayList();
        Cursor cursorQuery = readableDatabase.query(DatabaseHelp.TABLE_NAME_CACHE_HOST, new String[]{DatabaseHelp.COLUMN_FILE_PATH}, "_file_type=? and _access_time<?", new String[]{String.valueOf(i), String.valueOf(this.MAX_TIME)}, null, null, null);
        while (cursorQuery.moveToNext()) {
            int columnIndex = cursorQuery.getColumnIndex(DatabaseHelp.COLUMN_FILE_PATH);
            if (columnIndex >= 0) {
                arrayList.add(cursorQuery.getString(columnIndex));
            }
        }
        cursorQuery.close();
        return arrayList;
    }

    public void deleteRecord(String str) {
        ZeroLogcatTools.d(TAG, "deleteRecord: 删除文件访问记录，删除资源=" + str + ",删除结果=" + this.mHelp.getWritableDatabase().delete(DatabaseHelp.TABLE_NAME_CACHE_HOST, "_file_path=?", new String[]{str}));
    }
}
