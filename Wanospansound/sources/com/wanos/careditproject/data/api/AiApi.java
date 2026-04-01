package com.wanos.careditproject.data.api;

import com.wanos.careditproject.data.bean.AiCreateCancelResponse;
import com.wanos.careditproject.data.bean.AiCreateResponse;
import com.wanos.careditproject.data.bean.AiFindCreateStateResponse;
import com.wanos.careditproject.data.bean.AiPromptResponse;
import com.wanos.careditproject.data.bean.AiRandomPromptResponse;
import com.wanos.careditproject.data.response.AiPreviewProjectResponse;
import com.wanos.careditproject.data.response.EditProjectInitResponse;
import com.wanos.commonlibrary.utils.AppConstants;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/* JADX INFO: loaded from: classes3.dex */
public interface AiApi {
    @POST("aiaudio/aigc/cancel")
    Call<AiCreateCancelResponse> cancelAiCreate(@Body RequestBody requestBody);

    @POST("aiaudio/aigc/create")
    Call<AiCreateResponse> createAiMusic(@Body RequestBody requestBody);

    @POST("aiaudio/aigc/wanos/package/create")
    Call<AiPreviewProjectResponse> createAiPreviewProject(@Body RequestBody requestBody);

    @GET("aiaudio/aigc/state")
    Call<AiFindCreateStateResponse> findCreateState(@Query("track_id") String str);

    @GET("aiaudio/aigc/wanos/package")
    Call<EditProjectInitResponse> findPlayPreview(@Query("track_id") String str, @Query("track_index") String str2);

    @GET("aiaudio/aigc/prompt/list")
    Call<AiPromptResponse> getAiPrompt(@Query(AppConstants.ACTIVITY_TAB_PAGE) int i, @Query("page_size") int i2);

    @GET("aiaudio/aigc/prompt/random")
    Call<AiRandomPromptResponse> getRandomPrompt();
}
