package com.tencent.bugly.proguard;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Pair;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class ag {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final SimpleDateFormat f430a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ad f431b;

    /* synthetic */ ag(byte b2) {
        this();
    }

    private ag() {
        this.f430a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS", Locale.US);
        this.f431b = new ad();
    }

    public final void a(List<c> list) {
        if (list == null || list.isEmpty()) {
            al.d("sla batch report event is null", new Object[0]);
            return;
        }
        al.c("sla batch report event size:%s", Integer.valueOf(list.size()));
        ArrayList arrayList = new ArrayList();
        Iterator<c> it = list.iterator();
        while (it.hasNext()) {
            b bVarB = b(it.next());
            if (bVarB != null) {
                arrayList.add(bVarB);
            }
        }
        e(arrayList);
        b(arrayList);
    }

    public final void b(final List<b> list) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            ak.a().a(new Runnable() { // from class: com.tencent.bugly.proguard.ag.1
                @Override // java.lang.Runnable
                public final void run() {
                    ag.c(list);
                }
            });
        } else {
            c(list);
        }
    }

    static void c(List<b> list) {
        if (list == null || list.isEmpty()) {
            al.c("sla batch report data is empty", new Object[0]);
            return;
        }
        al.c("sla batch report list size:%s", Integer.valueOf(list.size()));
        if (list.size() > 30) {
            list = list.subList(0, 29);
        }
        ArrayList arrayList = new ArrayList();
        Iterator<b> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().f437c);
        }
        Pair<Integer, String> pairA = ad.a(arrayList);
        al.c("sla batch report result, rspCode:%s rspMsg:%s", pairA.first, pairA.second);
        if (((Integer) pairA.first).intValue() == 200) {
            d(list);
        }
    }

    private static void e(List<b> list) {
        for (b bVar : list) {
            al.c("sla save id:%s time:%s msg:%s", bVar.f435a, Long.valueOf(bVar.f436b), bVar.f437c);
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put("_id", bVar.f435a);
                contentValues.put("_tm", Long.valueOf(bVar.f436b));
                contentValues.put("_dt", bVar.f437c);
                w.a().a("t_sla", contentValues, (v) null);
            } catch (Throwable th) {
                al.b(th);
            }
        }
    }

    public static void d(List<b> list) {
        if (list == null || list.isEmpty()) {
            al.c("sla batch delete list is null", new Object[0]);
            return;
        }
        al.c("sla batch delete list size:%s", Integer.valueOf(list.size()));
        try {
            String str = "_id in (" + a(",", list) + ")";
            al.c("sla batch delete where:%s", str);
            w.a().a("t_sla", str);
        } catch (Throwable th) {
            al.b(th);
        }
    }

    private static String a(String str, Iterable<b> iterable) {
        Iterator<b> it = iterable.iterator();
        if (!it.hasNext()) {
            return "";
        }
        StringBuilder sb = new StringBuilder("'");
        sb.append(it.next().f435a).append("'");
        while (it.hasNext()) {
            sb.append(str);
            sb.append("'").append(it.next().f435a).append("'");
        }
        return sb.toString();
    }

    public static List<b> a() {
        Cursor cursorA = w.a().a("t_sla", new String[]{"_id", "_tm", "_dt"}, (String) null, "_tm", "30");
        if (cursorA == null) {
            return null;
        }
        if (cursorA.getCount() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        while (cursorA.moveToNext()) {
            try {
                b bVar = new b();
                bVar.f435a = cursorA.getString(cursorA.getColumnIndex("_id"));
                bVar.f436b = cursorA.getLong(cursorA.getColumnIndex("_tm"));
                bVar.f437c = cursorA.getString(cursorA.getColumnIndex("_dt"));
                al.c(bVar.toString(), new Object[0]);
                arrayList.add(bVar);
            } finally {
                try {
                } finally {
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: compiled from: BUGLY */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        String f438a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        String f439b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        long f440c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f441d;
        long e;
        String f;
        String g;

        public c(String str, String str2, long j, boolean z, long j2, String str3, String str4) {
            this.f438a = str;
            this.f439b = str2;
            this.f440c = j;
            this.f441d = z;
            this.e = j2;
            this.f = str3;
            this.g = str4;
        }

        public c() {
        }
    }

    /* JADX INFO: compiled from: BUGLY */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        String f435a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public long f436b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f437c;

        public final String toString() {
            return "SLAData{uuid='" + this.f435a + "', time=" + this.f436b + ", data='" + this.f437c + "'}";
        }
    }

    /* JADX INFO: compiled from: BUGLY */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final ag f434a = new ag(0);
    }

    public final void a(c cVar) {
        if (TextUtils.isEmpty(cVar.f439b)) {
            al.d("sla report event is null", new Object[0]);
        } else {
            al.c("sla report single event", new Object[0]);
            a(Collections.singletonList(cVar));
        }
    }

    private b b(c cVar) {
        if (cVar == null || TextUtils.isEmpty(cVar.f439b)) {
            al.d("sla convert event is null", new Object[0]);
            return null;
        }
        aa aaVarB = aa.b();
        if (aaVarB == null) {
            al.d("sla convert failed because ComInfoManager is null", new Object[0]);
            return null;
        }
        String str = "&app_version=" + aaVarB.o + "&app_name=" + aaVarB.q + "&app_bundle_id=" + aaVarB.f417c + "&client_type=android&user_id=" + aaVarB.f() + "&sdk_version=" + aaVarB.h + "&event_code=" + cVar.f439b + "&event_result=" + (cVar.f441d ? 1 : 0) + "&event_time=" + this.f430a.format(new Date(cVar.f440c)) + "&event_cost=" + cVar.e + "&device_id=" + aaVarB.g() + "&debug=" + (aaVarB.D ? 1 : 0) + "&param_0=" + cVar.f + "&param_1=" + cVar.f438a + "&param_2=" + (aaVarB.M ? "rqd" : "ext") + "&param_4=" + aaVarB.e();
        if (!TextUtils.isEmpty(cVar.g)) {
            str = str + "&param_3=" + cVar.g;
        }
        al.c("sla convert eventId:%s eventType:%s, eventTime:%s success:%s cost:%s from:%s uploadMsg:", cVar.f438a, cVar.f439b, Long.valueOf(cVar.f440c), Boolean.valueOf(cVar.f441d), Long.valueOf(cVar.e), cVar.f, cVar.g);
        String str2 = cVar.f438a + "-" + cVar.f439b;
        b bVar = new b();
        bVar.f435a = str2;
        bVar.f436b = cVar.f440c;
        bVar.f437c = str;
        return bVar;
    }
}
