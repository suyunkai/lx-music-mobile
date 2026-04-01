package com.wanos.careditproject.utils.http;

import android.content.Context;
import android.util.ArrayMap;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;
import com.wanos.WanosCommunication.URLConstan;
import com.wanos.careditproject.utils.EditingParams;
import com.wanos.careditproject.utils.EditingUtils;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import cz.msebera.android.httpclient.Header;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import retrofit2.Call;

/* JADX INFO: loaded from: classes3.dex */
public class UploadSingleUtils {
    private static AsyncHttpClient client;
    private static Context context;
    private static UploadSingleUtils uploadUtils;
    private Map<String, Call> calls = new ArrayMap();
    private List<RequestHandle> requestHandleList = new ArrayList();

    public interface UploadCall {
        void onCall(String str, String str2, boolean z);
    }

    public static void initContext(Context context2) {
        context = context2;
    }

    public static void destroyContext() {
        context = null;
    }

    public static UploadSingleUtils getInstance() {
        if (uploadUtils == null) {
            uploadUtils = new UploadSingleUtils();
        }
        return uploadUtils;
    }

    public void start(final String str, final UploadCall uploadCall) {
        if (this.calls.containsKey(str)) {
            return;
        }
        File file = new File(str);
        if (!file.exists()) {
            if (uploadCall != null) {
                uploadCall.onCall("", str, false);
                return;
            }
            return;
        }
        String projectId = EditingParams.getInstance().getProjectId();
        if (client == null) {
            client = new AsyncHttpClient();
        }
        RequestParams requestParams = new RequestParams();
        String str2 = URLConstan.BASE_CREATOR_URL;
        String token = UserInfoUtil.getToken();
        final String str3 = str2 + "audioproduce/api/project/upload?project_id=" + projectId;
        EditingUtils.log("url = " + str3);
        requestParams.put("project_id", projectId);
        try {
            requestParams.put("file", file);
            client.addHeader("UserToken", token);
            client.setTimeout(600000);
            this.requestHandleList.add(client.post(context, str3, requestParams, new AsyncHttpResponseHandler() { // from class: com.wanos.careditproject.utils.http.UploadSingleUtils.1
                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                    EditingUtils.log("UploadUtils onSuccess ".concat(new String(bArr)));
                    UploadResponseAll uploadResponseAll = (UploadResponseAll) new Gson().fromJson(new String(bArr), UploadResponseAll.class);
                    if (uploadResponseAll != null && uploadResponseAll.code == 0 && uploadResponseAll.data != null) {
                        EditingUtils.log("UploadUtils res all = " + uploadResponseAll.data.netUrl);
                        UploadCall uploadCall2 = uploadCall;
                        if (uploadCall2 != null) {
                            uploadCall2.onCall(uploadResponseAll.data.netUrl, str, true);
                            return;
                        }
                        return;
                    }
                    UploadCall uploadCall3 = uploadCall;
                    if (uploadCall3 != null) {
                        uploadCall3.onCall("", str, false);
                    }
                }

                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
                    EditingUtils.log("UploadUtils fail statusCode = " + i + "," + str3);
                    UploadCall uploadCall2 = uploadCall;
                    if (uploadCall2 != null) {
                        uploadCall2.onCall("", str, false);
                    }
                }
            }));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void cancel() {
        Context context2;
        AsyncHttpClient asyncHttpClient = client;
        if (asyncHttpClient == null || (context2 = context) == null) {
            return;
        }
        asyncHttpClient.cancelRequests(context2, true);
    }

    public class UploadResponseAll {
        public int code;
        public UploadResponse data;
        public String msg;

        public UploadResponseAll() {
        }
    }

    public class UploadResponse {
        public String netUrl;

        public UploadResponse() {
        }
    }
}
