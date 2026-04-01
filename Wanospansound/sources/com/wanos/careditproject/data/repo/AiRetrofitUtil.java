package com.wanos.careditproject.data.repo;

import android.util.Log;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.URLConstan;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.careditproject.data.api.AiApi;
import com.wanos.careditproject.data.bean.AiCreateCancelResponse;
import com.wanos.careditproject.data.bean.AiCreateResponse;
import com.wanos.careditproject.data.bean.AiFindCreateStateEntity;
import com.wanos.careditproject.data.bean.AiFindCreateStateResponse;
import com.wanos.careditproject.data.bean.AiPromptResponse;
import com.wanos.careditproject.data.bean.AiRandomPromptResponse;
import com.wanos.careditproject.data.response.AiPreviewProjectResponse;
import com.wanos.careditproject.data.response.EditProjectInitResponse;
import java.util.HashMap;
import retrofit2.Response;

/* JADX INFO: loaded from: classes3.dex */
public class AiRetrofitUtil {
    private static final String TAG = "Wanos[AI]-AiRetrofitUtil";
    private static final AiRetrofitUtil instance = new AiRetrofitUtil();
    private final AiApi aiApi = (AiApi) WanOSRetrofitUtil.getRetrofit(URLConstan.BASE_CREATOR_URL).create(AiApi.class);

    private AiRetrofitUtil() {
    }

    public static AiRetrofitUtil getInstance() {
        return instance;
    }

    public void getAiPromptList(ResponseCallBack<AiPromptResponse> responseCallBack) {
        this.aiApi.getAiPrompt(1, Integer.MAX_VALUE).enqueue(responseCallBack);
    }

    public AiCreateResponse createAiMusic(String str) {
        HashMap map = new HashMap();
        map.put("text", str);
        try {
            Response<AiCreateResponse> responseExecute = this.aiApi.createAiMusic(WanOSRetrofitUtil.mapToJsonRequestBody(map)).execute();
            if (responseExecute.isSuccessful()) {
                return responseExecute.body();
            }
            AiCreateResponse aiCreateResponse = new AiCreateResponse();
            aiCreateResponse.code = responseExecute.code();
            aiCreateResponse.msg = responseExecute.message();
            return aiCreateResponse;
        } catch (Exception e) {
            Log.e(TAG, "createAiMusic: " + e.getMessage());
            return null;
        }
    }

    public AiFindCreateStateResponse findCreateAiMusicState(String str) {
        try {
            Response<AiFindCreateStateResponse> responseExecute = this.aiApi.findCreateState(str).execute();
            if (responseExecute.isSuccessful()) {
                return responseExecute.body();
            }
            AiFindCreateStateResponse aiFindCreateStateResponse = new AiFindCreateStateResponse();
            aiFindCreateStateResponse.setData(new AiFindCreateStateEntity(4, "AI生成失败，错误码:" + responseExecute.code()));
            Log.e(TAG, "findCreateAiMusicState: 服务器错误，错误码 = " + responseExecute.code());
            return aiFindCreateStateResponse;
        } catch (Exception e) {
            Log.e(TAG, "findCreateAiMusicState: 0619 服务器错误，e = " + e.getMessage());
            return null;
        }
    }

    public void getRandomPrompt(ResponseCallBack<AiRandomPromptResponse> responseCallBack) {
        this.aiApi.getRandomPrompt().enqueue(responseCallBack);
    }

    public AiPreviewProjectResponse createAiPreviewProject(String str, String str2) {
        HashMap map = new HashMap();
        map.put("track_id", str);
        map.put("track_index", str2);
        try {
            return this.aiApi.createAiPreviewProject(WanOSRetrofitUtil.mapToJsonRequestBody(map)).execute().body();
        } catch (Exception unused) {
            return null;
        }
    }

    public EditProjectInitResponse findAiPlayPreviewData(String str, String str2) {
        try {
            return this.aiApi.findPlayPreview(str, str2).execute().body();
        } catch (Throwable th) {
            Log.e(TAG, "findAiPlayPreviewData: " + th.getMessage());
            return null;
        }
    }

    public void cancelAiCreate(String str, ResponseCallBack<AiCreateCancelResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put("track_id", str);
        this.aiApi.cancelAiCreate(WanOSRetrofitUtil.mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }
}
