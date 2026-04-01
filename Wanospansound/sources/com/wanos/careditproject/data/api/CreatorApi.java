package com.wanos.careditproject.data.api;

import com.wanos.WanosCommunication.BaseResponse;
import com.wanos.WanosCommunication.BaseResponse2;
import com.wanos.WanosCommunication.response.GetMaterialCollectResponse;
import com.wanos.WanosCommunication.response.GetMaterialListResponse;
import com.wanos.WanosCommunication.response.GetMaterialTypeListResponse;
import com.wanos.WanosCommunication.response.GetServiceProtocolResponse;
import com.wanos.careditproject.data.bean.AiCancelResponse;
import com.wanos.careditproject.data.bean.AiImplProjectResponse;
import com.wanos.careditproject.data.bean.AiImplProjectStateResponse;
import com.wanos.careditproject.data.response.CloudInfoResponse;
import com.wanos.careditproject.data.response.CloudResponse;
import com.wanos.careditproject.data.response.CreateCloudResponse;
import com.wanos.careditproject.data.response.CredentialResponse;
import com.wanos.careditproject.data.response.EditGetCateListResponse;
import com.wanos.careditproject.data.response.EditGetPictureListResponse;
import com.wanos.careditproject.data.response.EditProjectCopyResponse;
import com.wanos.careditproject.data.response.EditProjectExportListResponse;
import com.wanos.careditproject.data.response.EditProjectExportResponse;
import com.wanos.careditproject.data.response.EditProjectGuideResponse;
import com.wanos.careditproject.data.response.EditProjectInitResponse;
import com.wanos.careditproject.data.response.EditProjectListResponse;
import com.wanos.careditproject.data.response.EditProjectSaveResponse;
import com.wanos.careditproject.data.response.EditProjectUpdateResponse;
import com.wanos.careditproject.data.response.EditShareCodeResponse;
import com.wanos.careditproject.data.response.ProjectInfoListResponse;
import com.wanos.careditproject.data.response.ProjectTagListResponse;
import com.wanos.careditproject.data.response.ProjectUploadAssetTaskResponse;
import com.wanos.careditproject.data.response.ProjectUploadResponse;
import com.wanos.careditproject.data.response.UploadProjectResponse;
import com.wanos.careditproject.data.response.WorkDetail;
import com.wanos.commonlibrary.utils.AppConstants;
import cz.msebera.android.httpclient.cookie.ClientCookie;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/* JADX INFO: loaded from: classes3.dex */
public interface CreatorApi {
    @Headers({"baseUrl:make", "source:2"})
    @POST("audioproduce/car/v1/project/trackSave")
    Call<EditProjectSaveResponse> ProjectTrackSave(@Body RequestBody requestBody);

    @Headers({"baseUrl:make", "source:2", "upLoad:material"})
    @POST("audioproduce/car/v1/project/aiImportCancel")
    Call<AiCancelResponse> aiImplCancel(@Body RequestBody requestBody);

    @Headers({"baseUrl:make", "source:2", "upLoad:material"})
    @POST("audioproduce/car/v1/project/aiImport")
    Call<AiImplProjectResponse> aiImplProject(@Body RequestBody requestBody);

    @Headers({"baseUrl:make", "source:2", "upLoad:material"})
    @GET("audioproduce/car/v1/project/aiImportTaskStatus")
    Call<AiImplProjectStateResponse> aiImplProjectState(@Query("id") int i);

    @Headers({"baseUrl:make", "source:2"})
    @POST("audioproduce/car/v1/assets/cancelCollect")
    Call<GetMaterialCollectResponse> cancelMaterialCollect(@Body RequestBody requestBody);

    @Headers({"baseUrl:make", "source:2"})
    @POST("audioproduce/car/v1/cloud/uploadFile")
    Call<CreateCloudResponse> cloudUpLoadAsset(@Body RequestBody requestBody);

    @Headers({"baseUrl:make", "source:2"})
    @POST("audioproduce/car/v1/cloud/createCloudDir")
    Call<CreateCloudResponse> createCloudDir(@Body RequestBody requestBody);

    @Headers({"baseUrl:make", "source:2"})
    @POST("audioproduce/car/v1/project/aiPublish")
    Call<EditProjectExportResponse> editAIProjectPublish(@Body RequestBody requestBody);

    @Headers({"baseUrl:make", "source:2"})
    @POST("audioproduce/car/v1/project/export")
    Call<EditProjectExportResponse> editProjectExport(@Body RequestBody requestBody);

    @Headers({"baseUrl:make", "source:2"})
    @GET("audioproduce/car/v1/project/guide")
    Call<EditProjectGuideResponse> editProjectGuide();

    @Headers({"baseUrl:make", "source:2"})
    @POST("audioproduce/car/v1/project/init")
    Call<EditProjectInitResponse> editProjectInit(@Body RequestBody requestBody);

    @Headers({"baseUrl:make", "source:2"})
    @POST("audioproduce/car/v1/project/publish")
    Call<EditProjectExportResponse> editProjectPublish(@Body RequestBody requestBody);

    @Headers({"baseUrl:make", "source:2"})
    @POST("audioproduce/car/v1/project/save")
    Call<EditProjectSaveResponse> editProjectSave(@Body RequestBody requestBody);

    @Headers({"baseUrl:make", "source:2"})
    @POST("audioproduce/car/v1/project/init")
    Call<EditProjectInitResponse> findPlayPreview(@Body RequestBody requestBody);

    @Headers({"baseUrl:make", "source:2"})
    @GET("audioproduce/car/v1/assets/assetsInfo")
    Call<CloudInfoResponse> getAssetsInfo(@Query("id") int i, @Query("source") int i2);

    @Headers({"baseUrl:make", "source:2"})
    @GET("audioproduce/car/v1/cloud/cloudDirList")
    Call<CloudResponse> getCloudDirList(@Query("parentId") int i);

    @Headers({"baseUrl:make", "source:2"})
    @GET("audioproduce/car/v1/cloud/cloudFileInfo")
    Call<CloudInfoResponse> getCloudFileInfo(@Query("id") int i, @Query("source") int i2);

    @Headers({"baseUrl:make", "source:2"})
    @GET("audioproduce/car/v1/cloud/cloudFileList")
    Call<GetMaterialListResponse> getCloudFileList(@Query("dirId") int i, @Query(AppConstants.ACTIVITY_TAB_PAGE) int i2, @Query("pageSize") int i3);

    @Headers({"baseUrl:make", "source:2"})
    @GET("audioproduce/car/v1/cloud/cloudDirList")
    Call<GetMaterialTypeListResponse> getCloudMaterialType(@Query("parentId") int i);

    @Headers({"baseUrl:make", "source:2"})
    @GET("audioproduce/car/v1/assets/collectList")
    Call<GetMaterialListResponse> getCollectMaterialList(@Query("type") int i, @Query(AppConstants.ACTIVITY_TAB_PAGE) int i2, @Query("pageSize") int i3);

    @Headers({"source:2"})
    @GET("/audioproduce/car/v1/works/communityChoiceList")
    Call<ProjectInfoListResponse> getCommunityChoiceList(@Query(AppConstants.ACTIVITY_TAB_PAGE) int i, @Query("pageSize") int i2);

    @Headers({"source:2"})
    @GET("/audioproduce/car/v1/works/communityMoreList")
    Call<ProjectInfoListResponse> getCommunityMoreList(@Query("queryParentLabelId") int i, @Query(AppConstants.ACTIVITY_TAB_PAGE) int i2, @Query("pageSize") int i3);

    @Headers({"source:2"})
    @GET("/audioproduce/car/v1/works/communityMoreList")
    Call<ProjectInfoListResponse> getCommunityMoreList(@Query("queryLabelIds") int[] iArr, @Query(AppConstants.ACTIVITY_TAB_PAGE) int i, @Query("pageSize") int i2);

    @Headers({"source:2"})
    @GET("/audioproduce/car/v1/works/labelList")
    Call<ProjectTagListResponse> getCommunityTagList(@Query("isCommunityMore") int i);

    @Headers({"baseUrl:make", "source:2"})
    @GET("audioproduce/car/v1/project/exportList")
    Call<EditProjectExportListResponse> getEditExportList();

    @Headers({"source:2"})
    @GET("/audioproduce/car/v1/works/inspirationExplorationList")
    Call<ProjectInfoListResponse> getInspirationList(@Query("queryParentLabelId") int i, @Query(AppConstants.ACTIVITY_TAB_PAGE) int i2, @Query("pageSize") int i3);

    @Headers({"source:2"})
    @GET("/audioproduce/car/v1/works/inspirationExplorationList")
    Call<ProjectInfoListResponse> getInspirationList(@Query("queryLabelIds") int[] iArr, @Query(AppConstants.ACTIVITY_TAB_PAGE) int i, @Query("pageSize") int i2);

    @Headers({"baseUrl:make", "source:2"})
    @POST("audioproduce/car/v1/assets/collect")
    Call<GetMaterialCollectResponse> getMaterialCollect(@Body RequestBody requestBody);

    @Headers({"baseUrl:make", "source:2"})
    @GET("audioproduce/car/v1/assets/assetsList")
    Call<GetMaterialListResponse> getMaterialList(@Query("assetsId") int i, @Query("type") int i2, @Query(AppConstants.ACTIVITY_TAB_PAGE) int i3, @Query("pageSize") int i4);

    @Headers({"baseUrl:make", "source:2"})
    @GET("audioproduce/car/v1/assets/assetsType")
    Call<GetMaterialTypeListResponse> getMaterialTypeList(@Query("type") int i);

    @Headers({"source:2"})
    @GET("/audioproduce/car/v1/works/collectList")
    Call<ProjectInfoListResponse> getMyCollectionList(@Query(AppConstants.ACTIVITY_TAB_PAGE) int i, @Query("pageSize") int i2);

    @Headers({"source:2"})
    @GET("/audioproduce/car/v1/works/list")
    Call<ProjectInfoListResponse> getMyWorkList(@Query(AppConstants.ACTIVITY_TAB_PAGE) int i, @Query("pageSize") int i2);

    @Headers({"baseUrl:make", "source:2"})
    @GET("audioproduce/car/v1/works/pictureList")
    Call<EditGetPictureListResponse> getPictureList(@Query(AppConstants.ACTIVITY_TAB_PAGE) int i, @Query("pageSize") int i2);

    @Headers({"baseUrl:make", "source:2"})
    @GET("audioproduce/car/v1/works/list")
    Call<EditProjectListResponse> getProductionList(@Query("sort") int i, @Query("limitStart") long j, @Query("limitEnd") long j2, @Query(AppConstants.ACTIVITY_TAB_PAGE) int i2, @Query("pageSize") int i3, @Query("reviewStatus") int i4);

    @Headers({"baseUrl:make", "source:2"})
    @GET("audioproduce/car/v1/project/list")
    Call<EditProjectListResponse> getProjectList(@Query("sort") int i, @Query(AppConstants.ACTIVITY_TAB_PAGE) int i2, @Query("pageSize") int i3);

    @Headers({"baseUrl:make", "source:2"})
    @GET("audioproduce/car/v1/project/publishContract")
    Call<GetServiceProtocolResponse> getProjectPublishContract(@Query("name") String str, @Query(ClientCookie.VERSION_ATTR) int i);

    @Headers({"baseUrl:make", "source:2"})
    @GET("audioproduce/car/v1/project/uploadAssetTask")
    Call<ProjectUploadAssetTaskResponse> getProjectUploadAssetTaskList(@Query("assetId") int i, @Query("taskId") int i2);

    Call<GetMaterialListResponse> getSearchMaterialList(@Query("type") int i, @Query("keyword") String str);

    @Headers({"baseUrl:make", "source:2"})
    @GET("audioproduce/car/v1/assets/search")
    Call<GetMaterialListResponse> getSearchMaterialList(@Query("keyword") String str, @Query(AppConstants.ACTIVITY_TAB_PAGE) int i, @Query("pageSize") int i2);

    @Headers({"baseUrl:make", "source:2"})
    @GET("audioproduce/car/v1/upload/tempToken")
    Call<CredentialResponse> getUploadTempToken(@Query("dirType") String str, @Query("suffix") String str2);

    @Headers({"baseUrl:make", "source:2"})
    @GET("audioproduce/car/v1/upload/tempToken")
    Call<CredentialResponse> getUploadTempToken(@Query("dirType") String str, @Query("suffix") String str2, @Query("id") int i);

    @Headers({"baseUrl:make", "source:2"})
    @GET("audioproduce/car/v1/works/detail")
    Call<BaseResponse2<WorkDetail>> getWorkDetail(@Query("workId") String str);

    @Headers({"baseUrl:make", "source:2"})
    @GET("audioproduce/car/v1/works/labelList")
    Call<EditGetCateListResponse> getWorksCateList(@Query("module_type") int i);

    @Headers({"baseUrl:make", "source:2"})
    @GET("audioproduce/car/v1/works/collectList")
    @Deprecated
    Call<EditProjectListResponse> getWorksCollectList(@Query(AppConstants.ACTIVITY_TAB_PAGE) int i, @Query("pageSize") int i2);

    @Headers({"baseUrl:make", "source:2"})
    @GET("audioproduce/car/v1/works/communityList")
    @Deprecated
    Call<EditProjectListResponse> getWorksCommunityList(@Query(AppConstants.ACTIVITY_TAB_PAGE) int i, @Query("pageSize") int i2);

    @Headers({"source:2"})
    @GET("/audioproduce/car/v1/works/labelList")
    Call<ProjectTagListResponse> getWorksTagList(@Query("moduleType") int i, @Query("isInspirationExploration") int i2);

    @Headers({"baseUrl:make", "source:2"})
    @GET("audioproduce/car/v1/works/inspirationExplorationList")
    Call<EditProjectListResponse> getWorksTempleteList(@Query(AppConstants.ACTIVITY_TAB_PAGE) int i, @Query("pageSize") int i2);

    @Headers({"baseUrl:make", "source:2"})
    @POST("audioproduce/car/v1/project/copy")
    Call<EditProjectCopyResponse> projectCopy(@Body RequestBody requestBody);

    @Headers({"baseUrl:make", "source:2"})
    @POST("audioproduce/car/v1/project/copyByShare")
    Call<EditProjectCopyResponse> projectCopyByShare(@Body RequestBody requestBody);

    @Headers({"baseUrl:make", "source:2"})
    @POST("audioproduce/car/v1/project/delete")
    Call<BaseResponse> projectDelete(@Body RequestBody requestBody);

    @Headers({"baseUrl:make", "source:2"})
    @POST("audioproduce/car/v1/project/share")
    Call<EditShareCodeResponse> projectShare(@Body RequestBody requestBody);

    @Headers({"baseUrl:make", "source:2"})
    @POST("audioproduce/car/v1/project/update")
    Call<EditProjectUpdateResponse> projectUpdate(@Body RequestBody requestBody);

    @Headers({"baseUrl:make", "source:2"})
    @POST("audioproduce/car/v1/project/uploadAsset")
    Call<ProjectUploadResponse> projectUploadAsset(@Body RequestBody requestBody);

    @Headers({"source:2"})
    @POST("/audioproduce/car/v1/works/publishRangeStatusUpdate")
    Call<BaseResponse> publishRangeStatusUpdate(@Body RequestBody requestBody);

    @Headers({"baseUrl:make", "source:2", "upLoad:material"})
    @POST("/audioproduce/car/v1/project/upload")
    Call<UploadProjectResponse> upLoadProject(@Body MultipartBody multipartBody);

    @Headers({"baseUrl:make", "source:2"})
    @POST("audioproduce/car/v1/works/cancelCollect")
    Call<BaseResponse> workCancelCollect(@Body RequestBody requestBody);

    @Headers({"baseUrl:make", "source:2"})
    @POST("audioproduce/car/v1/works/collect")
    Call<BaseResponse> workCollect(@Body RequestBody requestBody);

    @Headers({"baseUrl:make", "source:2"})
    @POST("audioproduce/car/v1/works/copy")
    Call<EditProjectCopyResponse> workCopy(@Body RequestBody requestBody);

    @Headers({"baseUrl:make", "source:2"})
    @POST("audioproduce/car/v1/works/delete")
    Call<BaseResponse> worksDelete(@Body RequestBody requestBody);

    @Headers({"baseUrl:make", "source:2"})
    @POST("audioproduce/car/v1/works/update")
    Call<BaseResponse> worksUpdate(@Body RequestBody requestBody);
}
