package com.tencent.bugly.proguard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f658a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static w f659b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static x f660c;

    private w(Context context, List<o> list) {
        f660c = new x(context, list);
    }

    public static synchronized w a(Context context, List<o> list) {
        if (f659b == null) {
            f659b = new w(context, list);
        }
        return f659b;
    }

    public static synchronized w a() {
        return f659b;
    }

    public final Cursor a(String str, String[] strArr, String str2) {
        return a(str, strArr, str2, (String) null, (String) null);
    }

    public final Cursor a(String str, String[] strArr, String str2, String str3, String str4) {
        return a(false, str, strArr, str2, null, null, null, str3, str4, null);
    }

    public final int a(String str, String str2) {
        return a(str, str2, (String[]) null, (v) null);
    }

    public final synchronized long a(String str, ContentValues contentValues, v vVar) {
        long j;
        j = -1;
        SQLiteDatabase writableDatabase = null;
        try {
            writableDatabase = f660c.getWritableDatabase();
            if (writableDatabase != null && contentValues != null) {
                long jReplace = writableDatabase.replace(str, "_id", contentValues);
                if (jReplace >= 0) {
                    al.c("[Database] insert %s success.", str);
                } else {
                    al.d("[Database] replace %s error.", str);
                }
                j = jReplace;
            }
        } catch (Throwable th) {
            try {
                if (!al.a(th)) {
                    th.printStackTrace();
                }
                if (vVar != null) {
                    Long.valueOf(-1L);
                }
                if (f658a && 0 != 0) {
                }
            } finally {
                if (vVar != null) {
                    Long.valueOf(-1L);
                }
                if (f658a && 0 != 0) {
                    writableDatabase.close();
                }
            }
        }
        return j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized Cursor a(boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6, v vVar) {
        Cursor cursorQuery;
        cursorQuery = null;
        try {
            SQLiteDatabase writableDatabase = f660c.getWritableDatabase();
            if (writableDatabase != null) {
                cursorQuery = writableDatabase.query(z, str, strArr, str2, strArr2, str3, str4, str5, str6);
            }
        } finally {
        }
        return cursorQuery;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized int a(String str, String str2, String[] strArr, v vVar) {
        int iDelete;
        SQLiteDatabase writableDatabase = null;
        try {
            writableDatabase = f660c.getWritableDatabase();
            iDelete = writableDatabase != null ? writableDatabase.delete(str, str2, strArr) : 0;
        } catch (Throwable th) {
            try {
                if (!al.a(th)) {
                    th.printStackTrace();
                }
                if (vVar != null) {
                    Integer.valueOf(0);
                }
                if (f658a && writableDatabase != null) {
                }
            } finally {
                if (vVar != null) {
                    Integer.valueOf(0);
                }
                if (f658a && writableDatabase != null) {
                    writableDatabase.close();
                }
            }
        }
        return iDelete;
    }

    public final boolean a(int i, String str, byte[] bArr, boolean z) {
        if (!z) {
            a aVar = new a();
            aVar.a(i, str, bArr);
            ak.a().a(aVar);
            return true;
        }
        return a(i, str, bArr, (v) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(int i, String str, byte[] bArr, v vVar) {
        try {
            y yVar = new y();
            yVar.f669a = i;
            yVar.f = str;
            yVar.e = System.currentTimeMillis();
            yVar.g = bArr;
            boolean zB = b(yVar);
            if (vVar == null) {
                return zB;
            }
            Boolean.valueOf(zB);
            return zB;
        } catch (Throwable th) {
            try {
                if (!al.a(th)) {
                    th.printStackTrace();
                }
                return false;
            } finally {
                if (vVar != null) {
                    Boolean bool = Boolean.FALSE;
                }
            }
        }
    }

    public final Map<String, byte[]> a(int i, v vVar) {
        HashMap map = null;
        try {
            List<y> listC = c(i);
            if (listC == null) {
                return null;
            }
            HashMap map2 = new HashMap();
            try {
                for (y yVar : listC) {
                    byte[] bArr = yVar.g;
                    if (bArr != null) {
                        map2.put(yVar.f, bArr);
                    }
                }
                return map2;
            } catch (Throwable th) {
                th = th;
                map = map2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        if (al.a(th)) {
            return map;
        }
        th.printStackTrace();
        return map;
    }

    public final synchronized boolean a(y yVar) {
        ContentValues contentValuesC;
        SQLiteDatabase writableDatabase = null;
        try {
            writableDatabase = f660c.getWritableDatabase();
            if (writableDatabase == null || (contentValuesC = c(yVar)) == null) {
                if (f658a && writableDatabase != null) {
                    writableDatabase.close();
                }
                return false;
            }
            long jReplace = writableDatabase.replace("t_lr", "_id", contentValuesC);
            if (jReplace >= 0) {
                al.c("[Database] insert %s success.", "t_lr");
                yVar.f669a = jReplace;
                return true;
            }
            if (f658a && writableDatabase != null) {
                writableDatabase.close();
            }
            return false;
        } catch (Throwable th) {
            try {
                if (!al.a(th)) {
                    th.printStackTrace();
                }
                if (f658a && writableDatabase != null) {
                    writableDatabase.close();
                }
                return false;
            } finally {
                if (f658a && writableDatabase != null) {
                    writableDatabase.close();
                }
            }
        }
    }

    private synchronized boolean b(y yVar) {
        ContentValues contentValuesD;
        SQLiteDatabase writableDatabase = null;
        try {
            writableDatabase = f660c.getWritableDatabase();
            if (writableDatabase == null || (contentValuesD = d(yVar)) == null) {
                if (f658a && writableDatabase != null) {
                    writableDatabase.close();
                }
                return false;
            }
            long jReplace = writableDatabase.replace("t_pf", "_id", contentValuesD);
            if (jReplace >= 0) {
                al.c("[Database] insert %s success.", "t_pf");
                yVar.f669a = jReplace;
                return true;
            }
            if (f658a && writableDatabase != null) {
                writableDatabase.close();
            }
            return false;
        } catch (Throwable th) {
            try {
                if (!al.a(th)) {
                    th.printStackTrace();
                }
                if (f658a && writableDatabase != null) {
                    writableDatabase.close();
                }
                return false;
            } finally {
                if (f658a && writableDatabase != null) {
                    writableDatabase.close();
                }
            }
        }
    }

    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00b4 A[Catch: all -> 0x00c6, TRY_LEAVE, TryCatch #0 {all -> 0x00c6, blocks: (B:44:0x00ae, B:46:0x00b4), top: B:66:0x00ae, outer: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00b9 A[Catch: all -> 0x00d8, TRY_ENTER, TryCatch #2 {, blocks: (B:3:0x0001, B:14:0x002d, B:15:0x0030, B:18:0x0036, B:35:0x009c, B:36:0x009f, B:39:0x00a5, B:48:0x00b9, B:49:0x00bc, B:52:0x00c2, B:55:0x00c9, B:56:0x00cc, B:59:0x00d2, B:60:0x00d5, B:44:0x00ae, B:46:0x00b4), top: B:70:0x0001, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final synchronized java.util.List<com.tencent.bugly.proguard.y> a(int r12) {
        /*
            Method dump skipped, instruction units count: 219
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.w.a(int):java.util.List");
    }

    public final synchronized void a(List<y> list) {
        if (list != null) {
            if (list.size() != 0) {
                SQLiteDatabase writableDatabase = f660c.getWritableDatabase();
                if (writableDatabase != null) {
                    StringBuilder sb = new StringBuilder();
                    Iterator<y> it = list.iterator();
                    while (it.hasNext()) {
                        sb.append(" or _id = ").append(it.next().f669a);
                    }
                    String string = sb.toString();
                    if (string.length() > 0) {
                        string = string.substring(4);
                    }
                    sb.setLength(0);
                    try {
                        al.c("[Database] deleted %s data %d", "t_lr", Integer.valueOf(writableDatabase.delete("t_lr", string, null)));
                    } catch (Throwable th) {
                        try {
                            if (!al.a(th)) {
                                th.printStackTrace();
                            }
                            if (f658a) {
                                writableDatabase.close();
                            }
                        } finally {
                            if (f658a) {
                                writableDatabase.close();
                            }
                        }
                    }
                }
            }
        }
    }

    public final synchronized void b(int i) {
        String strConcat;
        SQLiteDatabase writableDatabase = f660c.getWritableDatabase();
        if (writableDatabase != null) {
            if (i >= 0) {
                try {
                    strConcat = "_tp = ".concat(String.valueOf(i));
                } catch (Throwable th) {
                    try {
                        if (!al.a(th)) {
                            th.printStackTrace();
                        }
                        if (f658a && writableDatabase != null) {
                            writableDatabase.close();
                            return;
                        }
                    } finally {
                        if (f658a && writableDatabase != null) {
                            writableDatabase.close();
                        }
                    }
                }
            } else {
                strConcat = null;
            }
            al.c("[Database] deleted %s data %d", "t_lr", Integer.valueOf(writableDatabase.delete("t_lr", strConcat, null)));
        }
    }

    private static ContentValues c(y yVar) {
        if (yVar == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (yVar.f669a > 0) {
                contentValues.put("_id", Long.valueOf(yVar.f669a));
            }
            contentValues.put("_tp", Integer.valueOf(yVar.f670b));
            contentValues.put("_pc", yVar.f671c);
            contentValues.put("_th", yVar.f672d);
            contentValues.put("_tm", Long.valueOf(yVar.e));
            if (yVar.g != null) {
                contentValues.put("_dt", yVar.g);
            }
            return contentValues;
        } catch (Throwable th) {
            if (!al.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    private static y a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            y yVar = new y();
            yVar.f669a = cursor.getLong(cursor.getColumnIndex("_id"));
            yVar.f670b = cursor.getInt(cursor.getColumnIndex("_tp"));
            yVar.f671c = cursor.getString(cursor.getColumnIndex("_pc"));
            yVar.f672d = cursor.getString(cursor.getColumnIndex("_th"));
            yVar.e = cursor.getLong(cursor.getColumnIndex("_tm"));
            yVar.g = cursor.getBlob(cursor.getColumnIndex("_dt"));
            return yVar;
        } catch (Throwable th) {
            if (!al.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x00b2 A[Catch: all -> 0x00b6, PHI: r1
  0x00b2: PHI (r1v2 android.database.sqlite.SQLiteDatabase) = (r1v1 android.database.sqlite.SQLiteDatabase), (r1v4 android.database.sqlite.SQLiteDatabase) binds: [B:57:0x00cd, B:43:0x00b0] A[DONT_GENERATE, DONT_INLINE], TRY_LEAVE, TryCatch #2 {, blocks: (B:9:0x0025, B:10:0x0028, B:13:0x002e, B:30:0x0099, B:31:0x009c, B:34:0x00a2, B:54:0x00c6, B:55:0x00c9, B:44:0x00b2, B:63:0x00d5, B:64:0x00d8, B:67:0x00de, B:68:0x00e1, B:41:0x00ac, B:50:0x00bb, B:52:0x00c1), top: B:75:0x0002, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private synchronized java.util.List<com.tencent.bugly.proguard.y> c(int r12) {
        /*
            Method dump skipped, instruction units count: 228
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.w.c(int):java.util.List");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized boolean a(int i, String str, v vVar) {
        boolean z;
        String strConcat;
        z = false;
        SQLiteDatabase sQLiteDatabase = null;
        try {
            SQLiteDatabase writableDatabase = f660c.getWritableDatabase();
            if (writableDatabase != null) {
                try {
                    if (ap.b(str)) {
                        strConcat = "_id = ".concat(String.valueOf(i));
                    } else {
                        strConcat = "_id = " + i + " and _tp = \"" + str + "\"";
                    }
                    int iDelete = writableDatabase.delete("t_pf", strConcat, null);
                    al.c("[Database] deleted %s data %d", "t_pf", Integer.valueOf(iDelete));
                    if (iDelete > 0) {
                        z = true;
                    }
                } catch (Throwable th) {
                    th = th;
                    sQLiteDatabase = writableDatabase;
                    try {
                        if (!al.a(th)) {
                            th.printStackTrace();
                        }
                    } finally {
                        if (vVar != null) {
                            Boolean bool = Boolean.FALSE;
                        }
                        if (f658a && sQLiteDatabase != null) {
                            sQLiteDatabase.close();
                        }
                    }
                }
            }
            if (vVar != null) {
                Boolean.valueOf(z);
            }
            if (f658a && writableDatabase != null) {
                writableDatabase.close();
            }
        } catch (Throwable th2) {
            th = th2;
        }
        return z;
    }

    private static ContentValues d(y yVar) {
        if (yVar != null && !ap.b(yVar.f)) {
            try {
                ContentValues contentValues = new ContentValues();
                if (yVar.f669a > 0) {
                    contentValues.put("_id", Long.valueOf(yVar.f669a));
                }
                contentValues.put("_tp", yVar.f);
                contentValues.put("_tm", Long.valueOf(yVar.e));
                if (yVar.g != null) {
                    contentValues.put("_dt", yVar.g);
                }
                return contentValues;
            } catch (Throwable th) {
                if (!al.a(th)) {
                    th.printStackTrace();
                }
            }
        }
        return null;
    }

    private static y b(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            y yVar = new y();
            yVar.f669a = cursor.getLong(cursor.getColumnIndex("_id"));
            yVar.e = cursor.getLong(cursor.getColumnIndex("_tm"));
            yVar.f = cursor.getString(cursor.getColumnIndex("_tp"));
            yVar.g = cursor.getBlob(cursor.getColumnIndex("_dt"));
            return yVar;
        } catch (Throwable th) {
            if (!al.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: BUGLY */
    class a extends Thread {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f662b = 4;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private v f663c = null;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private String f664d;
        private ContentValues e;
        private boolean f;
        private String[] g;
        private String h;
        private String[] i;
        private String j;
        private String k;
        private String l;
        private String m;
        private String n;
        private String[] o;
        private int p;
        private String q;
        private byte[] r;

        public a() {
        }

        public final void a(int i, String str, byte[] bArr) {
            this.p = i;
            this.q = str;
            this.r = bArr;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            switch (this.f662b) {
                case 1:
                    w.this.a(this.f664d, this.e, this.f663c);
                    break;
                case 2:
                    w.this.a(this.f664d, this.n, this.o, this.f663c);
                    break;
                case 3:
                    Cursor cursorA = w.this.a(this.f, this.f664d, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.f663c);
                    if (cursorA != null) {
                        cursorA.close();
                    }
                    break;
                case 4:
                    w.this.a(this.p, this.q, this.r, this.f663c);
                    break;
                case 5:
                    w.this.a(this.p, this.f663c);
                    break;
                case 6:
                    w.this.a(this.p, this.q, this.f663c);
                    break;
            }
        }
    }
}
