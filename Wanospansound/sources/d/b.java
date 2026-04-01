package d;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.collection.SimpleArrayMap;

/* JADX INFO: loaded from: classes3.dex */
public class b {
    private static final String KEY = "wanosaudio-sp";
    private static SimpleArrayMap<String, b> SP_UTILS_MAP = new SimpleArrayMap<>();
    private SharedPreferences sp;

    private b(String str, Context context) {
        this.sp = context.getApplicationContext().getSharedPreferences(str, 0);
    }

    public static b getInstance(Context context) {
        return getInstance(KEY, context);
    }

    public boolean getBoolean(String str) {
        return getBoolean(str, false);
    }

    public float getFloat(String str) {
        return getFloat(str, -1.0f);
    }

    public int getInt(String str) {
        return getInt(str, -1);
    }

    public long getLong(String str) {
        return getLong(str, -1L);
    }

    public String getString(String str) {
        return getString(str, "");
    }

    public void put(String str, float f) {
        this.sp.edit().putFloat(str, f).apply();
    }

    public static b getInstance(String str, Context context) {
        if (TextUtils.isEmpty(str)) {
            str = KEY;
        }
        b bVar = SP_UTILS_MAP.get(str);
        if (bVar != null) {
            return bVar;
        }
        b bVar2 = new b(str, context);
        SP_UTILS_MAP.put(str, bVar2);
        return bVar2;
    }

    public boolean getBoolean(String str, boolean z) {
        return this.sp.getBoolean(str, z);
    }

    public float getFloat(String str, float f) {
        return this.sp.getFloat(str, f);
    }

    public int getInt(String str, int i) {
        return this.sp.getInt(str, i);
    }

    public long getLong(String str, long j) {
        return this.sp.getLong(str, j);
    }

    public String getString(String str, String str2) {
        return this.sp.getString(str, str2);
    }

    public void put(String str, int i) {
        this.sp.edit().putInt(str, i).apply();
    }

    public void put(String str, long j) {
        this.sp.edit().putLong(str, j).apply();
    }

    public void put(String str, String str2) {
        this.sp.edit().putString(str, str2).apply();
    }

    public void put(String str, boolean z) {
        this.sp.edit().putBoolean(str, z).apply();
    }
}
