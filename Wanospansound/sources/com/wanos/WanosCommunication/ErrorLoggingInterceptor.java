package com.wanos.WanosCommunication;

import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.baidubce.BceConfig;
import java.io.IOException;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class ErrorLoggingInterceptor implements Interceptor {
    private static final String DEFAULT_TAG = "wanos:[ErrorLoggingInterceptor]";
    private static final int MAX_LOG_LENGTH = Integer.MAX_VALUE;
    private final String logTag;

    public ErrorLoggingInterceptor() {
        this(DEFAULT_TAG);
    }

    public ErrorLoggingInterceptor(String str) {
        this.logTag = str == null ? DEFAULT_TAG : str;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        Response responseProceed = chain.proceed(request);
        try {
            if (isJsonResponse(responseProceed)) {
                processJsonResponse(request, responseProceed);
            }
        } catch (Exception e) {
            Log.e(this.logTag, "Error processing response: " + e.getMessage());
        }
        return responseProceed;
    }

    private boolean isJsonResponse(Response response) {
        MediaType mediaType = response.body().getMediaType();
        return mediaType != null && "json".equalsIgnoreCase(mediaType.subtype());
    }

    private String getLastPathSegment(Request request) {
        String str;
        String[] strArrSplit = request.url().encodedPath().split(BceConfig.BOS_DELIMITER);
        int length = strArrSplit.length - 1;
        while (true) {
            if (length < 0) {
                str = "";
                break;
            }
            if (!strArrSplit[length].isEmpty()) {
                str = strArrSplit[length];
                break;
            }
            length--;
        }
        return str.isEmpty() ? BceConfig.BOS_DELIMITER : str;
    }

    private void processJsonResponse(Request request, Response response) throws IOException {
        Buffer buffer = new Buffer();
        response.body().getSource().peek().readAll(buffer);
        String utf8 = buffer.readUtf8();
        if (utf8.length() > Integer.MAX_VALUE) {
            utf8 = utf8.substring(0, Integer.MAX_VALUE) + "...[TRUNCATED]";
        }
        try {
            JSONObject jSONObject = new JSONObject(utf8);
            int iOptInt = jSONObject.optInt("code", 0);
            if (iOptInt != 0) {
                logErrorDetails(request, iOptInt, jSONObject);
            }
        } catch (JSONException e) {
            Log.e(this.logTag, "Invalid JSON format: " + e.getMessage());
        }
    }

    private String getPathWithQuery(Request request) {
        HttpUrl httpUrlUrl = request.url();
        String strEncodedPath = httpUrlUrl.encodedPath();
        String strEncodedQuery = httpUrlUrl.encodedQuery();
        return strEncodedQuery != null ? strEncodedPath + "?" + strEncodedQuery : strEncodedPath;
    }

    private void logErrorDetails(Request request, int i, JSONObject jSONObject) {
        try {
            Log.w(this.logTag, String.format("API Error\n┌ Endpoint: %s\n├ Code: %d\n├ Message: %s\n└ Data: %s", getLastPathSegment(request), Integer.valueOf(i), parseMessage(jSONObject), parseData(jSONObject)));
        } catch (Exception e) {
            Log.e(this.logTag, "生成日志失败: " + e.getMessage());
        }
    }

    private String parseMessage(JSONObject jSONObject) {
        if (jSONObject.has(NotificationCompat.CATEGORY_MESSAGE)) {
            String strOptString = jSONObject.optString(NotificationCompat.CATEGORY_MESSAGE, "");
            return strOptString.isEmpty() ? "[空msg]" : strOptString;
        }
        if (!jSONObject.has("message")) {
            return "[无msg或message字段]";
        }
        String strOptString2 = jSONObject.optString("message", "");
        return strOptString2.isEmpty() ? "[空message]" : strOptString2;
    }

    private String parseData(JSONObject jSONObject) {
        try {
            if (!jSONObject.has("data")) {
                return "[无数据字段]";
            }
            Object obj = jSONObject.get("data");
            if (obj == null) {
                return "[空数据]";
            }
            if (obj instanceof JSONObject) {
                return ((JSONObject) obj).toString(2);
            }
            return obj.toString();
        } catch (JSONException unused) {
            return "[数据解析失败]";
        }
    }
}
