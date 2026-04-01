package com.tencent.bugly.crashreport.crash.h5;

import android.webkit.JavascriptInterface;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.bugly.crashreport.inner.InnerApi;
import com.tencent.bugly.proguard.al;
import com.tencent.bugly.proguard.ap;
import com.tencent.bugly.proguard.bb;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public class H5JavaScriptInterface {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static HashSet<Integer> f405a = new HashSet<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f406b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Thread f407c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f408d = null;
    private Map<String, String> e = null;

    private H5JavaScriptInterface() {
    }

    public static H5JavaScriptInterface getInstance(CrashReport.a aVar) {
        String string = null;
        if (aVar == null || f405a.contains(Integer.valueOf(aVar.hashCode()))) {
            return null;
        }
        H5JavaScriptInterface h5JavaScriptInterface = new H5JavaScriptInterface();
        f405a.add(Integer.valueOf(aVar.hashCode()));
        Thread threadCurrentThread = Thread.currentThread();
        h5JavaScriptInterface.f407c = threadCurrentThread;
        if (threadCurrentThread != null) {
            StringBuilder sb = new StringBuilder("\n");
            for (int i = 2; i < threadCurrentThread.getStackTrace().length; i++) {
                StackTraceElement stackTraceElement = threadCurrentThread.getStackTrace()[i];
                if (!stackTraceElement.toString().contains("crashreport")) {
                    sb.append(stackTraceElement.toString()).append("\n");
                }
            }
            string = sb.toString();
        }
        h5JavaScriptInterface.f408d = string;
        HashMap map = new HashMap();
        map.put("[WebView] ContentDescription", new StringBuilder().append((Object) aVar.c()).toString());
        h5JavaScriptInterface.e = map;
        return h5JavaScriptInterface;
    }

    private static bb a(String str) {
        String string;
        if (str != null && str.length() > 0) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                bb bbVar = new bb();
                bbVar.f541a = jSONObject.getString("projectRoot");
                if (bbVar.f541a == null) {
                    return null;
                }
                bbVar.f542b = jSONObject.getString("context");
                if (bbVar.f542b == null) {
                    return null;
                }
                bbVar.f543c = jSONObject.getString("url");
                if (bbVar.f543c == null) {
                    return null;
                }
                bbVar.f544d = jSONObject.getString("userAgent");
                if (bbVar.f544d == null) {
                    return null;
                }
                bbVar.e = jSONObject.getString(IjkMediaMeta.IJKM_KEY_LANGUAGE);
                if (bbVar.e == null) {
                    return null;
                }
                bbVar.f = jSONObject.getString("name");
                if (bbVar.f == null || bbVar.f.equals("null") || (string = jSONObject.getString("stacktrace")) == null) {
                    return null;
                }
                int iIndexOf = string.indexOf("\n");
                if (iIndexOf < 0) {
                    al.d("H5 crash stack's format is wrong!", new Object[0]);
                    return null;
                }
                bbVar.h = string.substring(iIndexOf + 1);
                bbVar.g = string.substring(0, iIndexOf);
                int iIndexOf2 = bbVar.g.indexOf(":");
                if (iIndexOf2 > 0) {
                    bbVar.g = bbVar.g.substring(iIndexOf2 + 1);
                }
                bbVar.i = jSONObject.getString("file");
                if (bbVar.f == null) {
                    return null;
                }
                bbVar.j = jSONObject.getLong("lineNumber");
                if (bbVar.j < 0) {
                    return null;
                }
                bbVar.k = jSONObject.getLong("columnNumber");
                if (bbVar.k < 0) {
                    return null;
                }
                al.a("H5 crash information is following: ", new Object[0]);
                al.a("[projectRoot]: " + bbVar.f541a, new Object[0]);
                al.a("[context]: " + bbVar.f542b, new Object[0]);
                al.a("[url]: " + bbVar.f543c, new Object[0]);
                al.a("[userAgent]: " + bbVar.f544d, new Object[0]);
                al.a("[language]: " + bbVar.e, new Object[0]);
                al.a("[name]: " + bbVar.f, new Object[0]);
                al.a("[message]: " + bbVar.g, new Object[0]);
                al.a("[stacktrace]: \n" + bbVar.h, new Object[0]);
                al.a("[file]: " + bbVar.i, new Object[0]);
                al.a("[lineNumber]: " + bbVar.j, new Object[0]);
                al.a("[columnNumber]: " + bbVar.k, new Object[0]);
                return bbVar;
            } catch (Throwable th) {
                if (!al.a(th)) {
                    th.printStackTrace();
                }
            }
        }
        return null;
    }

    @JavascriptInterface
    public void printLog(String str) {
        al.d("Log from js: %s", str);
    }

    @JavascriptInterface
    public void reportJSException(String str) {
        if (str == null) {
            al.d("Payload from JS is null.", new Object[0]);
            return;
        }
        String strC = ap.c(str.getBytes());
        String str2 = this.f406b;
        if (str2 != null && str2.equals(strC)) {
            al.d("Same payload from js. Please check whether you've injected bugly.js more than one times.", new Object[0]);
            return;
        }
        this.f406b = strC;
        al.d("Handling JS exception ...", new Object[0]);
        bb bbVarA = a(str);
        if (bbVarA == null) {
            al.d("Failed to parse payload.", new Object[0]);
            return;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        if (bbVarA.f541a != null) {
            linkedHashMap2.put("[JS] projectRoot", bbVarA.f541a);
        }
        if (bbVarA.f542b != null) {
            linkedHashMap2.put("[JS] context", bbVarA.f542b);
        }
        if (bbVarA.f543c != null) {
            linkedHashMap2.put("[JS] url", bbVarA.f543c);
        }
        if (bbVarA.f544d != null) {
            linkedHashMap2.put("[JS] userAgent", bbVarA.f544d);
        }
        if (bbVarA.i != null) {
            linkedHashMap2.put("[JS] file", bbVarA.i);
        }
        if (bbVarA.j != 0) {
            linkedHashMap2.put("[JS] lineNumber", Long.toString(bbVarA.j));
        }
        linkedHashMap.putAll(linkedHashMap2);
        linkedHashMap.putAll(this.e);
        linkedHashMap.put("Java Stack", this.f408d);
        Thread thread = this.f407c;
        if (bbVarA != null) {
            InnerApi.postH5CrashAsync(thread, bbVarA.f, bbVarA.g, bbVarA.h, linkedHashMap);
        }
    }
}
