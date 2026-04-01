package com.wanos.careditproject.data.repo;

import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.baidubce.AbstractBceClient;
import com.google.gson.Gson;
import com.wanos.WanosCommunication.BaseResponse;
import com.wanos.WanosCommunication.BaseResponse2;
import com.wanos.WanosCommunication.ResponseCallBack;
import com.wanos.WanosCommunication.WanOSRetrofitUtil;
import com.wanos.WanosCommunication.response.GetMaterialCollectResponse;
import com.wanos.WanosCommunication.response.GetMaterialListResponse;
import com.wanos.WanosCommunication.response.GetMaterialTypeListResponse;
import com.wanos.WanosCommunication.response.GetServiceProtocolResponse;
import com.wanos.careditproject.data.api.CreatorApi;
import com.wanos.careditproject.data.bean.AiCancelResponse;
import com.wanos.careditproject.data.bean.AiImplProjectResponse;
import com.wanos.careditproject.data.bean.AiImplProjectStateResponse;
import com.wanos.careditproject.data.bean.ProjectTrackSaveBean;
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
import com.wanos.commonlibrary.utils.CommonUtils;
import com.wanos.media.viewmodel.CollectionDialogViewModel;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

/* JADX INFO: loaded from: classes3.dex */
public class CreatorRetrofitUtil {
    public static CreatorApi getWanOSRetrofitService() {
        return (CreatorApi) WanOSRetrofitUtil.getRetrofit4Creator().create(CreatorApi.class);
    }

    public static RequestBody mapToJsonRequestBody(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        map.put("channel", CommonUtils.getAppChannelId());
        map.put("clientIp", CommonUtils.getIPAddress());
        return RequestBody.create(MediaType.parse(AbstractBceClient.DEFAULT_CONTENT_TYPE), new Gson().toJson(map));
    }

    public static void projectCopy(String str, ResponseCallBack<EditProjectCopyResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put("id", str);
        getWanOSRetrofitService().projectCopy(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static void projectDelete(String str, ResponseCallBack<BaseResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put("id", str);
        getWanOSRetrofitService().projectDelete(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static void projectShare(String str, ResponseCallBack<EditShareCodeResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put("id", str);
        getWanOSRetrofitService().projectShare(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static void projectCopyByShare(String str, ResponseCallBack<EditProjectCopyResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put("share_code", str);
        getWanOSRetrofitService().projectCopyByShare(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static void worksDelete(String str, ResponseCallBack<BaseResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put("id", str);
        getWanOSRetrofitService().worksDelete(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static void worksUpdate(String str, String str2, String str3, String str4, ResponseCallBack<BaseResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put("id", str);
        map.put(CollectionDialogViewModel.KEY_TITLE, str2);
        map.put("content", str3);
        map.put("picture", str4);
        getWanOSRetrofitService().worksUpdate(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static void getProjectPictureList(ResponseCallBack<EditGetPictureListResponse> responseCallBack) {
        getWanOSRetrofitService().getPictureList(1, 100).enqueue(responseCallBack);
    }

    public static void getWorksCateList(int i, ResponseCallBack<EditGetCateListResponse> responseCallBack) {
        getWanOSRetrofitService().getWorksCateList(i).enqueue(responseCallBack);
    }

    public static void editInit(String str, ResponseCallBack<EditProjectInitResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put("id", Integer.valueOf(Integer.parseInt(str)));
        getWanOSRetrofitService().editProjectInit(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static EditProjectInitResponse findAiPlayPreviewData(String str, String str2) {
        try {
            HashMap map = new HashMap();
            map.put("id", Integer.valueOf(Integer.parseInt("1149")));
            return getWanOSRetrofitService().findPlayPreview(mapToJsonRequestBody(map)).execute().body();
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void editGuide(ResponseCallBack<EditProjectGuideResponse> responseCallBack) {
        getWanOSRetrofitService().editProjectGuide().enqueue(responseCallBack);
    }

    public static void editSave(String str, String str2, ResponseCallBack<EditProjectSaveResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put("id", str);
        map.put("project", str2);
        getWanOSRetrofitService().editProjectSave(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static void editPosSave(String str, List<ProjectTrackSaveBean> list, ResponseCallBack<EditProjectSaveResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put("projectId", str);
        map.put("track", list);
        getWanOSRetrofitService().ProjectTrackSave(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static void editExport(String str, String str2, String str3, String str4, int i, int i2, int i3, ResponseCallBack<EditProjectExportResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put("projectId", str);
        map.put("bitRate", 128000);
        map.put("sampleRate", 48000);
        map.put(TypedValues.AttributesType.S_TARGET, "WANOS");
        map.put(CollectionDialogViewModel.KEY_TITLE, str2);
        map.put("picture", str3);
        map.put("workType", Integer.valueOf(i));
        map.put("canTemplate", Integer.valueOf(i2));
        map.put("publishRange", Integer.valueOf(i3));
        map.put("content", str4);
        getWanOSRetrofitService().editProjectPublish(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static void editAiExport(String str, String str2, String str3, String str4, int i, int i2, int i3, String str5, String str6, ResponseCallBack<EditProjectExportResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put("projectId", str);
        map.put("bitRate", 128000);
        map.put("sampleRate", 48000);
        map.put(TypedValues.AttributesType.S_TARGET, "WANOS");
        map.put(CollectionDialogViewModel.KEY_TITLE, str2);
        map.put("picture", str3);
        map.put("workType", Integer.valueOf(i));
        map.put("canTemplate", Integer.valueOf(i2));
        map.put("publishRange", Integer.valueOf(i3));
        map.put("content", str4);
        HashMap map2 = new HashMap();
        map2.put("trackId", str5);
        map2.put("trackIndex", str6);
        map2.put("publish", map);
        getWanOSRetrofitService().editAIProjectPublish(mapToJsonRequestBody(map2)).enqueue(responseCallBack);
    }

    public static void getEditExportList(ResponseCallBack<EditProjectExportListResponse> responseCallBack) {
        getWanOSRetrofitService().getEditExportList().enqueue(responseCallBack);
    }

    public static void getProjectList(int i, int i2, ResponseCallBack<EditProjectListResponse> responseCallBack) {
        getWanOSRetrofitService().getProjectList(0, i, i2).enqueue(responseCallBack);
    }

    public static void getProductionList(int i, int i2, ResponseCallBack<EditProjectListResponse> responseCallBack) {
        getProductionList(0L, 0L, i, i2, 0, responseCallBack);
    }

    public static void getProductionList(long j, long j2, int i, int i2, int i3, ResponseCallBack<EditProjectListResponse> responseCallBack) {
        getWanOSRetrofitService().getProductionList(0, j, j2, i, i2, i3).enqueue(responseCallBack);
    }

    public static void getWorksCommunityList(int i, int i2, ResponseCallBack<EditProjectListResponse> responseCallBack) {
        getWanOSRetrofitService().getWorksCommunityList(i, i2).enqueue(responseCallBack);
    }

    public static void getCommunityTagList(ResponseCallBack<ProjectTagListResponse> responseCallBack) {
        getWanOSRetrofitService().getCommunityTagList(1).enqueue(responseCallBack);
    }

    public static void getWorksTagList(int i, ResponseCallBack<ProjectTagListResponse> responseCallBack) {
        getWanOSRetrofitService().getWorksTagList(i, 1).enqueue(responseCallBack);
    }

    public static Call<ProjectInfoListResponse> getInspirationList(int i, boolean z, int i2, int i3, ResponseCallBack<ProjectInfoListResponse> responseCallBack) {
        Call<ProjectInfoListResponse> inspirationList;
        if (z) {
            inspirationList = getWanOSRetrofitService().getInspirationList(i, i2, i3);
        } else {
            inspirationList = getWanOSRetrofitService().getInspirationList(new int[]{i}, i2, i3);
        }
        inspirationList.enqueue(responseCallBack);
        return inspirationList;
    }

    public static void getMyWorkList(int i, int i2, ResponseCallBack<ProjectInfoListResponse> responseCallBack) {
        getWanOSRetrofitService().getMyWorkList(i, i2).enqueue(responseCallBack);
    }

    public static void updateWorkPublishRangeStatus(int i, boolean z, ResponseCallBack<BaseResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put("workId", Integer.valueOf(i));
        map.put("status", Integer.valueOf(z ? 1 : 2));
        getWanOSRetrofitService().publishRangeStatusUpdate(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static void getMyCollectionList(int i, int i2, ResponseCallBack<ProjectInfoListResponse> responseCallBack) {
        getWanOSRetrofitService().getMyCollectionList(i, i2).enqueue(responseCallBack);
    }

    public static void getCommunityChoiceList(int i, int i2, ResponseCallBack<ProjectInfoListResponse> responseCallBack) {
        getWanOSRetrofitService().getCommunityChoiceList(i, i2).enqueue(responseCallBack);
    }

    public static void getCommunityMoreList(int i, boolean z, int i2, int i3, ResponseCallBack<ProjectInfoListResponse> responseCallBack) {
        if (z) {
            getWanOSRetrofitService().getCommunityMoreList(i, i2, i3).enqueue(responseCallBack);
        } else {
            getWanOSRetrofitService().getCommunityMoreList(new int[]{i}, i2, i3).enqueue(responseCallBack);
        }
    }

    public static void getWorksCollectList(int i, int i2, ResponseCallBack<EditProjectListResponse> responseCallBack) {
        getWanOSRetrofitService().getWorksCollectList(i, i2).enqueue(responseCallBack);
    }

    public static void getWorksTempleteList(int i, int i2, ResponseCallBack<EditProjectListResponse> responseCallBack) {
        getWanOSRetrofitService().getWorksTempleteList(i, i2).enqueue(responseCallBack);
    }

    public static void workCollect(String str, ResponseCallBack<BaseResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put("id", str);
        getWanOSRetrofitService().workCollect(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static void getWorkDetail(String str, ResponseCallBack<BaseResponse2<WorkDetail>> responseCallBack) {
        getWanOSRetrofitService().getWorkDetail(str).enqueue(responseCallBack);
    }

    public static Response<BaseResponse2<WorkDetail>> getWorkDetail(String str) throws IOException {
        return getWanOSRetrofitService().getWorkDetail(str).execute();
    }

    public static void workCancelCollect(String str, ResponseCallBack<BaseResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put("id", str);
        getWanOSRetrofitService().workCancelCollect(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static void workCopy(String str, ResponseCallBack<EditProjectCopyResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put("id", str);
        getWanOSRetrofitService().workCopy(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static void projectUpdate(String str, String str2, String str3, ResponseCallBack<EditProjectUpdateResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put("id", str);
        map.put(CollectionDialogViewModel.KEY_TITLE, str2);
        map.put("picture", str3);
        getWanOSRetrofitService().projectUpdate(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static void getProjectPublishContract(ResponseCallBack<GetServiceProtocolResponse> responseCallBack) {
        getWanOSRetrofitService().getProjectPublishContract("发布素材服务协议", 1).enqueue(responseCallBack);
    }

    public static void getMaterialType(int i, ResponseCallBack<GetMaterialTypeListResponse> responseCallBack) {
        getWanOSRetrofitService().getMaterialTypeList(i).enqueue(responseCallBack);
    }

    public static void getMaterialList(int i, int i2, int i3, int i4, ResponseCallBack<GetMaterialListResponse> responseCallBack) {
        getWanOSRetrofitService().getMaterialList(i, i2, i3, i4).enqueue(responseCallBack);
    }

    public static void getCloudMaterialType(int i, ResponseCallBack<GetMaterialTypeListResponse> responseCallBack) {
        getWanOSRetrofitService().getCloudMaterialType(i).enqueue(responseCallBack);
    }

    public static void getCloudMaterialList(int i, int i2, int i3, ResponseCallBack<GetMaterialListResponse> responseCallBack) {
        getWanOSRetrofitService().getCloudFileList(i, i2, i3).enqueue(responseCallBack);
    }

    public static void getCollectMaterialList(int i, int i2, int i3, ResponseCallBack<GetMaterialListResponse> responseCallBack) {
        getWanOSRetrofitService().getCollectMaterialList(i, i2, i3).enqueue(responseCallBack);
    }

    public static void collectMaterialMusic(int i, ResponseCallBack<GetMaterialCollectResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put("assetsId", Integer.valueOf(i));
        getWanOSRetrofitService().getMaterialCollect(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static void cancelMaterialCollect(int i, ResponseCallBack<GetMaterialCollectResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put("assetsId", Integer.valueOf(i));
        getWanOSRetrofitService().cancelMaterialCollect(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static Call<CloudResponse> getCloudDirList(int i, ResponseCallBack<CloudResponse> responseCallBack) {
        Call<CloudResponse> cloudDirList = getWanOSRetrofitService().getCloudDirList(i);
        cloudDirList.enqueue(responseCallBack);
        return cloudDirList;
    }

    public static void getCloudFileInfo(int i, int i2, ResponseCallBack<CloudInfoResponse> responseCallBack) {
        getWanOSRetrofitService().getCloudFileInfo(i, i2).enqueue(responseCallBack);
    }

    public static Response<CloudInfoResponse> getCloudFileInfo(int i) throws IOException {
        return getWanOSRetrofitService().getCloudFileInfo(i, 2).execute();
    }

    public static void getAssetsInfo(int i, int i2, ResponseCallBack<CloudInfoResponse> responseCallBack) {
        getWanOSRetrofitService().getAssetsInfo(i, i2).enqueue(responseCallBack);
    }

    public static Response<CloudInfoResponse> getAssetsInfo(int i) throws IOException {
        return getWanOSRetrofitService().getAssetsInfo(i, 2).execute();
    }

    public static Call<CreateCloudResponse> createCloudDir(String str, int i, ResponseCallBack<CreateCloudResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put("name", str);
        map.put("parentId", Integer.valueOf(i));
        Call<CreateCloudResponse> callCreateCloudDir = getWanOSRetrofitService().createCloudDir(mapToJsonRequestBody(map));
        callCreateCloudDir.enqueue(responseCallBack);
        return callCreateCloudDir;
    }

    public static void projectUploadAsset(int i, String str, String str2, String str3, ResponseCallBack<ProjectUploadResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put("name", str);
        map.put("id", Integer.valueOf(i));
        map.put("url", str2);
        map.put("md5", str3);
        getWanOSRetrofitService().projectUploadAsset(mapToJsonRequestBody(map)).enqueue(responseCallBack);
    }

    public static void getProjectUploadAssetTaskList(int i, int i2, ResponseCallBack<ProjectUploadAssetTaskResponse> responseCallBack) {
        getWanOSRetrofitService().getProjectUploadAssetTaskList(i, i2).enqueue(responseCallBack);
    }

    public static Call<CredentialResponse> getUploadTempToken(String str, String str2, ResponseCallBack<CredentialResponse> responseCallBack) {
        Call<CredentialResponse> uploadTempToken = getWanOSRetrofitService().getUploadTempToken(str, str2);
        uploadTempToken.enqueue(responseCallBack);
        return uploadTempToken;
    }

    public static Call<CredentialResponse> getUploadTempToken(String str, String str2, int i, ResponseCallBack<CredentialResponse> responseCallBack) {
        Call<CredentialResponse> uploadTempToken = getWanOSRetrofitService().getUploadTempToken(str, str2, i);
        uploadTempToken.enqueue(responseCallBack);
        return uploadTempToken;
    }

    public static Call<CreateCloudResponse> cloudUpLoadAsset(int i, String str, String str2, String str3, ResponseCallBack<CreateCloudResponse> responseCallBack) {
        HashMap map = new HashMap();
        map.put("dirId", Integer.valueOf(i));
        map.put("name", str);
        map.put("url", str2);
        map.put("md5", str3);
        Call<CreateCloudResponse> callCloudUpLoadAsset = getWanOSRetrofitService().cloudUpLoadAsset(mapToJsonRequestBody(map));
        callCloudUpLoadAsset.enqueue(responseCallBack);
        return callCloudUpLoadAsset;
    }

    public static void getSearchMaterialList(String str, int i, int i2, ResponseCallBack<GetMaterialListResponse> responseCallBack) {
        getWanOSRetrofitService().getSearchMaterialList(str, i, i2).enqueue(responseCallBack);
    }

    public static Call<UploadProjectResponse> uploadProject(String str, File file, ResponseCallBack<UploadProjectResponse> responseCallBack) {
        Call<UploadProjectResponse> callUpLoadProject = getWanOSRetrofitService().upLoadProject(new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("project_id", str + "").addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("application/octet-stream"), file)).build());
        callUpLoadProject.enqueue(responseCallBack);
        return callUpLoadProject;
    }

    public static AiImplProjectResponse getAiImplProjectEntity(String str, String str2) {
        HashMap map = new HashMap();
        map.put("trackId", str);
        map.put("trackIndex", str2);
        try {
            return getWanOSRetrofitService().aiImplProject(WanOSRetrofitUtil.mapToJsonRequestBody(map)).execute().body();
        } catch (Exception unused) {
            return null;
        }
    }

    public static AiImplProjectStateResponse findAiImplProjectState(int i) {
        try {
            Response<AiImplProjectStateResponse> responseExecute = getWanOSRetrofitService().aiImplProjectState(i).execute();
            if (responseExecute.isSuccessful()) {
                return responseExecute.body();
            }
            Log.e("Wanos[AI]-AiRetrofitUtil", "findAiImplProjectState: 服务器错误，错误码 = " + responseExecute.code());
            AiImplProjectStateResponse aiImplProjectStateResponse = new AiImplProjectStateResponse();
            aiImplProjectStateResponse.code = responseExecute.code();
            aiImplProjectStateResponse.msg = responseExecute.message();
            return aiImplProjectStateResponse;
        } catch (Exception unused) {
            return null;
        }
    }

    public static AiCancelResponse cancelAiImplProject(int i, int i2) {
        HashMap map = new HashMap();
        map.put("id", Integer.valueOf(i));
        map.put("taskId", Integer.valueOf(i2));
        try {
            return getWanOSRetrofitService().aiImplCancel(WanOSRetrofitUtil.mapToJsonRequestBody(map)).execute().body();
        } catch (Exception unused) {
            return null;
        }
    }
}
